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
			System.out.println("Ŭ���̾�Ʈ���Ӵ��");
			//hold Ŭ���̾�Ʈ ���ӿ�û(�������)
			//���ť����
			Socket dataSock = serverSocket.accept(); 
			System.out.println("Ŭ���̾�Ʈ ������.....");
			OutputStream oos= dataSock.getOutputStream(); //������ ��¹���
			InputStream iis= dataSock.getInputStream(); //������ ��¹���
			
			while(true) {
				ObjectInputStream ois = 
						new ObjectInputStream(iis);
				ObjectOutputStream oouts = 
						new ObjectOutputStream(oos);
				try {
					Packet packet = (Packet) ois.readObject();//��...
					System.out.println("�̸�:"+packet.name+" ����:"+packet.age);
					oouts.writeObject(packet);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
//			while(true){
//				byte[] buffer = new byte[256];
//				//�Է¹��� ������ ���ö����� �� ���ڼ���
//				int nRead = iis.read(buffer);//����
//				String s = new String(buffer,0, nRead);
//				System.out.println("����������:"+s);
//				oos.write(buffer);
//			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
