# -*-coding=utf-8-*-
from socket import *
sockobj = socket(AF_INET, SOCK_STREAM)
sockobj.connect(('localhost', 8080))
message='无论你使用哪一种地址家族。套接字的类型只有两种。一种是面向连接的套接字，即在通讯之\
前一定要建立一条连接，就像跟朋友打电话时那样。这种通讯方式也被称为“虚电路”或“流套接 \
字”。面向连接的通讯方式提供了顺序的，可靠的，不会重复的数据传输，而且也不会被加上数据边\
界。这也意味着，每一个要发送的信息，可能会被拆分成多份，每一份都会不多不少地正确到达目 \
的地。然后被重新按顺序拼装起来，传给正在等待的应用程序。'
sockobj.send(message)
print sockobj.recv(1024)
sockobj.close( )
