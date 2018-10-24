<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String myname = request.getParameter("myname");
	String myage = request.getParameter("myage");
	//out.print("hello android");
	out.print("이름:"+myname+"나이:"+myage );
%>