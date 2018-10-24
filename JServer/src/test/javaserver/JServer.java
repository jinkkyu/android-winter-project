package test.javaserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import test.packet.Packet;

public class JServer {
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		try {
			ServerSocket serverSocket =
					new ServerSocket(4500);//create listen
			System.out.println("클라이언트접속대기");
			//hold 클라이언트 접속요청(동기소켓)
			//대기큐감시
			Socket dataSock = serverSocket.accept(); 
			System.out.println("클라이언트 접속함.....");
			OutputStream oos= dataSock.getOutputStream(); //소켓의 출력버퍼
			InputStream iis= dataSock.getInputStream(); //소켓의 출력버퍼
			
			while(true) {
				ObjectInputStream ois = 
						new ObjectInputStream(iis);
				ObjectOutputStream oouts = 
						new ObjectOutputStream(oos);
				try {
					Packet packet = (Packet) ois.readObject();//행...
					System.out.println("이름:"+packet.name+" 나이:"+packet.age);
					oouts.writeObject(packet);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
//			while(true){
//				byte[] buffer = new byte[256];
//				//입력버퍼 데이터 들어올때까지 행 에코서버
//				int nRead = iis.read(buffer);//멈춤
//				String s = new String(buffer,0, nRead);
//				System.out.println("받은데이터:"+s);
//				oos.write(buffer);
//			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
