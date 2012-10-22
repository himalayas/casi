# coding=utf-8
from socket import *
import struct
sockobj = socket(AF_INET, SOCK_STREAM)
sockobj.connect(('localhost', 80))
message='中国'
sockobj.send(message)

print sockobj.recv(1024)
sockobj.close( )
