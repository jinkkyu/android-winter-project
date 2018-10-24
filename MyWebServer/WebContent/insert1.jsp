<%@ page language="java" import="java.sql.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String myname = request.getParameter("myname");
	String myage = request.getParameter("myage");

try{
	Class.forName("org.gjt.mm.mysql.Driver");
	String mySqlURL="jdbc:mysql://localhost:3306/test";
	Connection conn =null;
	Statement stmt = null;
	String sql = 
		String.format("insert into sinsang( name, age) values('%s',%d)",
					myname, Integer.parseInt(myage));
	
//	String sql=
	//"insert into sinsang( name, age) values('김철수',30)";
	conn = DriverManager.getConnection(mySqlURL, 
			"root", "1234");  //db접속
	stmt = conn.createStatement();
	stmt.execute(sql); //sql 문
	conn.close();
	out.print("추가성공");
}catch( Exception ex){
	out.print( "에러:"+ex.getMessage());	
}

%>