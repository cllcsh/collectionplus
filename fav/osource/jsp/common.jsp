<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
Object obj = session.getAttribute(com.osource.base.Constants.USER_SESSION_KEY);
String themeName = "default";
if(obj instanceof com.osource.base.web.UserSession){
	com.osource.base.web.UserSession userSession = (com.osource.base.web.UserSession)obj;
	themeName = userSession.getThemeName();
}
String remoteServer="http://"+request.getServerName();
if(request.getServerPort() != 80){
	remoteServer +=":"+request.getServerPort();
}
remoteServer += path;
%>
<%
String mode = request.getParameter("mode");
if (mode != null && mode.startsWith("ajax"))
{
%>
<%@include file="/jsp/include/meta.jsp"%> 
<%
} else {
%>
<%@include file="/jsp/include/common.jsp"%>
<%
}
%>