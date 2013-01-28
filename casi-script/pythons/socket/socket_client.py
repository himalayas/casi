#-*- coding:utf-8 -*-
import socket

class SocketClient(object):
    def __init__(self,ip=None,port=None):
        self.ip=ip
        self.port=port
        self.address=(self.ip,self.port)
        self.conn=socket.socket()
        self.connected=False
        self.bufferSize=1024
        self.readBuffer=[]
        self.writeBuffer="message info"

    def fileno(self):
        return self.conn.fileno()

    def connect(self):
        self.conn.setblocking(False)
        self.conn.settimeout(5)
        self.conn.connect(self.address)
        self.connected=True
    def handleRead(self):
        while True:
             tmp=self.conn.recv(self.bufferSize)
             if tmp:
                 self.readBuffer.append(tmp)
             else:
                 break
        print "reading: %s"%self.readBuffer

    def handleWrite(self):
        msg=self.writeBuffer
        self.conn.send(msg)

    def close(self):
        self.conn.close()
