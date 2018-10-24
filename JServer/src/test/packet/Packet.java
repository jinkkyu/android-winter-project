package test.packet;

import java.io.Serializable;

//서버와 클라이언 객체를 주고받으려면
// 1. 패키지가 동일해야된다
// 2. 클래스는 Seializable인터페이스상속
public class Packet implements Serializable 
{
	public String name;
	public int age;
}
