import json as org_json
import logging
import traceback
import time

import pika
from pythons.rabbitmq import acknowledge


LOG_FORMAT = ('%(levelname) -10s %(asctime)s %(name) -30s %(funcName) '
              '-35s %(lineno) -5d: %(message)s')
LOGGER = logging.getLogger(__name__)

EXCHANGE = 'nbr_fanout'
EXCHANGE_TYPE = 'fanout'
QUEUE = 'nbr_new_task'
ROUTING_KEY = 'new_task'
class BlockingConsumer:
    def __init__(self,url):
        self.list=[]
        
        self._connection = None
        self._channel = None
        self._closing = True
        self._consumer_tag = None
        self._url = url
        self.session= acknowledge.Session()

    def connect(self):
        self._connection=  pika.BlockingConnection(pika.URLParameters(url=self._url))
        self._channel=self._connection.channel()
        self._channel.add_on_cancel_callback(self.on_cancel)
        self._channel.add_on_close_callback(self.on_close)


    def on_message(self,channel, basic_deliver, properties, body):
        # body is string not json
        try:
            msg=org_json.loads(body)["body"]
            LOGGER.info('%s,Received task :%s',__name__,msg["jobID"])
            if len(self.list)>0:
                LOGGER.info("%s ,WorkEngine is busy,#%s,this task was discarded jobID: %s"%(__name__,basic_deliver.delivery_tag,msg["jobID"]))
                self._channel.basic_nack(basic_deliver.delivery_tag,requeue=True)
                self.stop_consuming()
                while True:
                    if len(self.list)==0:
                        self.start_consuming()
                        return
                    LOGGER.info('%s,Check system,status is busy.',__name__)
                    time.sleep(5)
                return
            LOGGER.info('%s,Ack and Runing task : %s',__name__,msg["jobID"])
            self.acknowledge_message(basic_deliver.delivery_tag)
            self.run_task(msg=msg)
        except:
            LOGGER.error("on_message %s",traceback.format_exc())

    def run_task(self,msg=None):
        self.list.append(msg)
        print msg

    def stop_consuming(self):
        if self._channel:
            LOGGER.info('Sending a Basic.Cancel RPC command to RabbitMQ')
            self._channel.stop_consuming(consumer_tag=self._consumer_tag)

    def acknowledge_message(self, delivery_tag):
        LOGGER.info('Acknowledging message %s', delivery_tag)
        self._channel.basic_ack(delivery_tag=delivery_tag)

    def start_consuming(self):
        self._channel.basic_qos(prefetch_count=1)
        self._consumer_tag =self._channel.basic_consume(self.on_message,queue=QUEUE,no_ack=False)
        self._channel.start_consuming()
        LOGGER.info("%s,Start consumer is OK.",__name__)

    def on_cancel(self):
        LOGGER.info("%s,channel is cancel.",__name__)
    def on_close(self):
        LOGGER.info("%s,channel is close.",__name__)

    def reconnect(self):
        while True:
            try:
                self._closing = True
                self._connection=None
                self._channel = None
                self._connection = self.connect()
                self.start_consuming()
                break
            except:
                LOGGER.error('Connection to MQ-server.')
            time.sleep(3)

    def start(self):
        self.connect()
        self.start_consuming()
if __name__=="__main__":
    logging.basicConfig(level=logging.INFO, format=LOG_FORMAT)
    url='amqp://nbr:nbr@10.224.57.118:8111/%2Fnbr_vhost'
    consumer=BlockingConsumer(url=url)
    try:
        consumer.start()
    except:
        consumer.reconnect()