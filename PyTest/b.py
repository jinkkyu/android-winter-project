from socket import *
from select import select
import sys
import json

# 호스트, 포트와 버퍼 사이즈를 지정
HOST = '127.0.0.1'
PORT = 4915
BUFSIZE = 1024
ADDR = (HOST, PORT)

clientSocket = socket(AF_INET, SOCK_STREAM)
try:
    clientSocket.connect(ADDR)
except Exception as e:
    print('채팅 서버(%s:%s)에 연결 할 수 없습니다.' % ADDR)
    sys.exit()
print('채팅 서버(%s:%s)에 연결 되었습니다.' % ADDR)

d = {'name':'홍길동', 'age':20}
s = json.dumps(d,ensure_ascii=False)
s = s.encode('utf-8')
clientSocket.send(s)
while True:
    data = clientSocket.recv(BUFSIZE)
    data1 = data.decode("utf-8")
    j = json.loads(data1)
    print( j['name'] )
    
