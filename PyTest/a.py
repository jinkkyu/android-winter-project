import asyncore
import socket
import json

class Echoer(asyncore.dispatcher_with_send):
    def handle_read(self):
        data = self.recv(1024)
        data1 = data.decode("utf-8")
        j = json.loads(data1)
        print( j['name'] )
        self.send(data)

    def handle_close(self):
#         asyncore.dispatcher_with_send.handle_close(self)
        print("disconnect")
        self.close()
    
    def handle_error(self):
        print("client close")
#         asyncore.dispatcher_with_send.handle_error(self)
  
        
        
class EchoServer(asyncore.dispatcher):
    def __init__(self):
        asyncore.dispatcher.__init__(self)
        self.create_socket(socket.AF_INET, socket.SOCK_STREAM)
        self.bind(('', 4915))
        self.listen(1)
    def handle_accept(self):
        self.sock, addr = self.accept()
        handler = Echoer(self.sock)
    
        
server = EchoServer()
print("start...")
asyncore.loop()