# coding=utf-8
from time import ctime
from socket import *
address=('localhost',80)
socket_server=socket(AF_INET,SOCK_STREAM)
socket_server.bind(address)
socket_server.listen(5)
while True:
    cli_socket,cli_address=socket_server.accept()
    while True:
        data=cli_socket.recv(1024)
        if not data:
            break
        print([ctime()],data)
        cli_socket.send(data)
    cli_socket.close()
