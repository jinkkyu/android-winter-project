<%@page import="sun.misc.BASE64Encoder"%>
<%@ page import="java.sql.*" %>
<%@ page import="org.json.simple.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

try{
	Class.forName("org.gjt.mm.mysql.Driver");
	String mySqlURL="jdbc:mysql://localhost:3306/test";
	Connection conn =null;
	Statement stmt = null;
	String sql = "select * from mpart";
	conn = DriverManager.getConnection(mySqlURL, 
			"root", "1234");  //db접속
	stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery(sql);
	JSONArray jarr = new JSONArray();
	while( rs.next())
	{
		String name = rs.getString("fname");
		//스트링으로
		Blob img = rs.getBlob("partdata");
		byte[] imgData = img.getBytes(1,(int)img.length());
		BASE64Encoder base64Encoder = new BASE64Encoder();
		String strImg = base64Encoder.encode(imgData);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("fname", name);
		jsonObject.put("partdata", strImg);
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


