__author__ = 'Think'
from socket import *
import struct
sockobj = socket(AF_INET, SOCK_STREAM)
sockobj.connect(('localhost', 80))
message="赵钱孙李周吴郑王冯陈楮卫蒋沈韩杨朱秦尤许何吕施张孔曹赵钱孙李周吴郑"
while True:
    sockobj.send(message.encode())
#关闭套接字
sockobj.close( )
