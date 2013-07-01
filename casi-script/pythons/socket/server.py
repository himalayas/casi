#-*-coding=utf-8-*-

from socket import *
import traceback

def run():
    address=("localhost",8080)
    socket_server=socket()
    socket_server.bind(address)
    socket_server.listen(5)
    while True:
        cli_socket,cli_address=socket_server.accept()
        data=[]
        try:
            while True:
                tmp=cli_socket.recv(1024)
                if not tmp:
                    break
                data.append(tmp)
                cli_socket.send('ok')
            print str(data)
        except Exception,e:
            traceback.print_exc()
        finally:
            cli_socket.close()

if __name__=="__main__":
    run()

