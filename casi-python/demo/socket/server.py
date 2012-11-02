#coding=utf-8
from time import ctime
from socket import *
address=('',80)
socket_server=socket(AF_INET,SOCK_STREAM)
socket_server.bind(address)
socket_server.listen(5)
while True:
    cli_socket,cli_address=socket_server.accept()
    data=[]
    try:
        while True:
            tmp=cli_socket.recv(1)
            if not tmp:
                break
            data.append('%s' % tmp)
            cli_socket.send('ok')
        for d in data:
            print str(d)
    except Exception,e:
        print '%s:%s' % (e.__class__.__name__ , e)
        import sys
        exc_tuple = sys.exc_info()
        print exc_tuple
    finally:
        cli_socket.close()



