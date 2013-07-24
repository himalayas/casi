import json
import logging
import traceback
import pika
import time
import acknowledge
LOG_FORMAT = ('%(levelname) -10s %(asctime)s %(name) -30s %(funcName) '
              '-35s %(lineno) -5d: %(message)s')
LOGGER = logging.getLogger(__name__)

class Consumer(object):
    EXCHANGE = 'nbr_fanout'
    EXCHANGE_TYPE = 'fanout'
    QUEUE = 'nbr_new_task'
    ROUTING_KEY = 'new_task'
    def __init__(self, amqp_url):
        self._connection = None
        self._channel = None
        self._closing = True
        self._consumer_tag = None
        self._url = amqp_url
        self.session=acknowledge.Session()

    def connect(self):
        self._closing=False
        return  pika.SelectConnection(parameters=pika.URLParameters(self._url),
                                       on_open_callback=self.on_open,
                                       on_close_callback=self.on_close,
                                       stop_ioloop_on_close=False)
    def reconnect(self):
        while True:
            try:
                self._closing = True
                self._connection=None
                self._channel = None
                self._connection = self.connect()
                self._connection.ioloop.start()
                break
            except:
                LOGGER.info('Connection to MQ-server.')
            time.sleep(3)


    def on_channel_closed(self, channel, reply_code, reply_text):
        LOGGER.warning('Channel %i was closed: (%s) %s',channel, reply_code, reply_text)
        self._connection.close()

    def on_channel_open(self, channel):
        LOGGER.info('Channel opened')
        self._channel = channel
        self._channel.add_on_close_callback(self.on_channel_closed)
        self._channel.queue_bind(self.on_bindok, self.QUEUE,self.EXCHANGE, self.ROUTING_KEY)


    def add_on_cancel_callback(self):
        LOGGER.info('Adding consumer cancellation callback')
        self._channel.add_on_cancel_callback(self.on_consumer_cancelled)

    def on_consumer_cancelled(self, method_frame):
        LOGGER.info('Consumer was cancelled remotely, shutting down: %r',method_frame)
        if self._channel:
            self._channel.close()

    def acknowledge_message(self, delivery_tag):
        LOGGER.info('Acknowledging message %s', delivery_tag)
        self._channel.basic_ack(delivery_tag)

    def on_message(self, unused_channel, basic_deliver, properties, body):
        # body is string not json
        try:
            LOGGER.info('Received message # %s from %s: %s',basic_deliver.delivery_tag, properties.app_id, body)
            # TODO
            self.session.put_task(task=(basic_deliver.delivery_tag,(unused_channel,basic_deliver.delivery_tag)))
            LOGGER.info('session length %s jobID[%s]',len(self.session.task_map),json.loads(body)["body"]["jobID"])
            self.acknowledge_message(basic_deliver.delivery_tag)
        except:
            LOGGER.error("on_message %s",traceback)

    def on_cancelok(self, unused_frame):
        LOGGER.info('RabbitMQ acknowledged the cancellation of the consumer')
        self.close_channel()

    def stop_consuming(self):
        if self._channel:
            LOGGER.info('Sending a Basic.Cancel RPC command to RabbitMQ')
            self._channel.basic_cancel(callback=self.on_cancelok, consumer_tag=self._consumer_tag)

    def start_consuming(self):
        LOGGER.info('Issuing consumer related RPC commands')
        self.add_on_cancel_callback()
        self._consumer_tag = self._channel.basic_consume(self.on_message,self.QUEUE)

    def on_bindok(self, unused_frame):
        LOGGER.info('Queue bound IS OK.and start consuming')
        self.start_consuming()

    def close_channel(self):
        LOGGER.info('Closing the channel')
        self._channel.close()

    def on_open(self,unused_connection):
        LOGGER.info('Creating a new channel')
        self._connection.channel(on_open_callback=self.on_channel_open)

    def on_close(self, connection, reply_code, reply_text):
        LOGGER.warning('Connection closed, reopening in 5 seconds: (%s) %s',reply_code, reply_text)
        self.reconnect()

    def start(self):
        self._connection= self.connect()
        self._connection.ioloop.start()

def main():
    logging.basicConfig(level=logging.INFO, format=LOG_FORMAT)
    # amqp://username:password@host:port/<virtual_host>[?query-string]
    consumer = Consumer('amqp://nbr:nbr@10.224.57.118:8111/%2Fnbr_vhost')
    try:
        consumer.session.start()
        consumer.start()
    except:
        consumer.reconnect()

if __name__ == '__main__':
    main()