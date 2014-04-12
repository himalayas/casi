#-*-coding=UTF-8-*-
import SocketServer

from pythons.protobuffer.request_handler import RequestHandler


class PBServer(SocketServer.TCPServer):
    pass
if __name__=="__main__":
    server=PBServer(("localhost",8080),RequestHandler)
    server.serve_forever()