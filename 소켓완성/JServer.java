package test.javaserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class JServer {
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		try {
			ServerSocket serverSocket =
					new ServerSocket(4500);//create listen
			System.out.println("Ŭ���̾�Ʈ���Ӵ��..");
			//hold Ŭ���̾�Ʈ ���ӿ�û(�������)
			//���ť����
			Socket dataSock = serverSocket.accept(); 
			System.out.println("Ŭ���̾�Ʈ ������.....");
			OutputStream oos= dataSock.getOutputStream(); //������ ��¹���
			InputStream iis= dataSock.getInputStream(); //������ ��¹���
			while(true){
				byte[] buffer = new byte[256];
				//�Է¹��� ������ ���ö����� �� ���ڼ���
				int nRead = iis.read(buffer);//����
				String s = new String(buffer,0, nRead);
				System.out.println("����������:"+s);
				oos.write(buffer);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
