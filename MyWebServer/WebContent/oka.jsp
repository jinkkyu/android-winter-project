<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");

	String myname = request.getParameter("myname");
	String myage = request.getParameter("myage");
	out.print("hello ok:이름"+myname+" 나이:"+myage);
%>