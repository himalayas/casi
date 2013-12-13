#-*-coding=UTF-8-*-
import SocketServer
class RequestHandler(SocketServer.StreamRequestHandler):
    def handle(self):
        print self.request.recv(1024).strip()