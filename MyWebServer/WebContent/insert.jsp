<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	String myname = request.getParameter("myname");
	String myage = request.getParameter("myage");
/* 	out.print("<h1>hello</h1>");//response
	out.print("<h2>hello1....</h2>");
	out.print("<h2>이름:"+myname+"나이:"+myage+"</h2>");
 */
 %>
 <h1>hello</h1>
 <h1>hello1</h1>
 <h1>이름:<%=myname %> 나이:<%=myage%></h1>

