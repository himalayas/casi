import threading
import time


class Session(threading.Thread):
    task_map={}

    def put_task(self,task=None):
        self.task_map[task[0]]=task[1]

    def run(self):
        v=self.task_map[1]
        print v
        v[0].basic_ack(v[1])
        time.sleep(5)