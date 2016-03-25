<%@ page language="java" pageEncoding="UTF-8"%>
<%
String username = request.getParameter("username");
String password = request.getParameter("password");
response.sendRedirect("login.do?loginForm.loginname="+username+"&loginForm.password="+password+"&isauth=false");
%>
