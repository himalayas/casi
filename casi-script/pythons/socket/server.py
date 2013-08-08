#-*-coding=utf-8-*-

from socket import *
import struct
import traceback

def run():
    address=("localhost",8080)
    socket_server=socket()
    socket_server.bind(address)
    socket_server.listen(5)
    while True:
        try:
            cli_socket,cli_address=socket_server.accept()
            tmp=''
            while True:
                data=cli_socket.recv(4)
                if not data:
                    break
                tmp+=data
            print struct.unpack_from("!2I",tmp)
            print tmp
        except Exception,e:
            traceback.print_exc()
        finally:
            cli_socket.close()

if __name__=="__main__":
    run()

