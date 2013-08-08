# -*-coding=utf-8-*-
from socket import *
sockobj = socket(AF_INET, SOCK_STREAM)
sockobj.connect(('localhost', 8080))
message='abcd'
sockobj.send(message)
sockobj.close( )
