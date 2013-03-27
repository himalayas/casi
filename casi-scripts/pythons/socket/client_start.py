#-*-coding:utf-8-*-
import traceback
import select
from pythons.socket import socket_client

class ClientManager(object):
    def __init__(self):
        self.clientList=[]
        self.fds={}

    def addClient(self,ip=None,port=None):
        client= socket_client.SocketClient(ip=ip,port=port)
        try:
            client.connect()
            self.clientList.append(client)
            self.fds.update({client.fileno():client})
        except:
            print traceback.print_exc()

    def checkHealth(self):
        epoller=select.epoll()
        for client in self.clientList:
            epoller.register(client)
            client.handleWrite()
        while True:
            events=epoller.poll()
            for fd,event in events:
                client=self.fds[fd]
                print "EPOLLOUT[%s]" %select.EPOLLOUT
                print "EPOLLHUP[%s]" %select.EPOLLHUP
                print "EPOLLIN[%s]" %select.EPOLLIN
                print "EPOLLERR[%s]" %select.EPOLLERR
                print "EPOLLMSG[%s]" %select.EPOLLMSG
                print "EPOLLET[%s]" %select.EPOLLET

                print event
                if event & (select.EPOLLERR | select.EPOLLHUP):
                    try:
                        client.connect()
                    except:
                        traceback.print_exc()
                if event & select.EPOLLIN:
                    client.handleRead()
                if event & select.EPOLLOUT:
                    client.handleWrite()


if __name__=="__main__":
   manager=ClientManager()
   manager.addClient("localhost",8080)
   manager.addClient("localhost",8080)
   manager.addClient("localhost",8080)
   manager.checkHealth()
