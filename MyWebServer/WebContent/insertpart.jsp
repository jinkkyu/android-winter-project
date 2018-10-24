<%@ page language="java" import="java.sql.*,java.io.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
//	String myname = request.getParameter("myname");
//	String myage = request.getParameter("myage");
// mutipart  ==> getpart

	Part fname = request.getPart("fname");
	Part sfile = request.getPart("sfile");
	//sfile.getName();
	//전송이미지
	InputStream sfileStream =  sfile.getInputStream();
	
	//전송파일이름
	InputStream fnameStream =  fname.getInputStream();
	byte[] fbyte = new byte[256];
	int nRead = fnameStream.read(fbyte);
	String strFname = new String(fbyte,0, nRead,"utf-8" );
try{
	Class.forName("org.gjt.mm.mysql.Driver");
	String mySqlURL="jdbc:mysql://localhost:3306/test";
	Connection conn =null;
	PreparedStatement pstmt = null;
	String sql =
	"insert into mpart( partdata, fname) values(?,?)"; 
	conn = DriverManager.getConnection(mySqlURL, 
			"root", "1234");  //db접속
	pstmt = conn.prepareStatement(sql);
	pstmt.setBlob(1, sfileStream);
	pstmt.setString(2, strFname );
	pstmt.execute(); //sql 문
	conn.close();
	out.print("추가성공");
}catch( Exception ex){
	out.print( "에러:"+ex.getMessage());	
}
%>