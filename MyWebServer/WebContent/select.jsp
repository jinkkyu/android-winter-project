<%@ page import="java.sql.*" %>
<%@ page import="org.json.simple.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String myname = request.getParameter("myname");
	String myage = request.getParameter("myage");
try{
	Class.forName("org.gjt.mm.mysql.Driver");
	String mySqlURL="jdbc:mysql://localhost:3306/test";
	Connection conn =null;
	Statement stmt = null;
	String sql = "select * from sinsang";
	conn = DriverManager.getConnection(mySqlURL, 
			"root", "1234");  //db접속
	stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery(sql);
	JSONArray jarr = new JSONArray();
	while( rs.next())
	{
		String name = rs.getString("name");
		int age = rs.getInt("age");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", name);
		jsonObject.put("age", age);
		jarr.add(jsonObject);
	}
	
	out.print( jarr.toJSONString() );
	rs.close();
	conn.close();
	
	// [{"name":"홍길동","age":30},{"name":"이순신","age":40}]
}catch(Exception ex)
{
	out.print( "에러:"+ex.getMessage());	
}
%>


