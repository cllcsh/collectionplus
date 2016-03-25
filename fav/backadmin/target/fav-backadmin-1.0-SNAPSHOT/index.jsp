<%@page contentType="text/html; charset=UTF-8"%>
<%--@page import="java.util.regex.Matcher" %>
<%@page import="java.util.regex.Pattern" %>
<% 
	String phoneReg = "\\b(ip(hone|od)|android|opera m(ob|in)i|windows (phone|ce)|blackberry|s(ymbian|eries60|amsung)|p(laybook|alm|rofile/midp|laystation portable)|nokia|fennec|htc[-_]|mobile|up.browser|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";
	String tableReg = "\\b(ipad|tablet|(Nexus 7)|up.browser|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";
	
	// 移动设备正则匹配：手机端、平板
	Pattern phonePat = Pattern.compile(phoneReg, Pattern.CASE_INSENSITIVE);
	Pattern tablePat = Pattern.compile(tableReg, Pattern.CASE_INSENSITIVE);
	
	String userAgent = request.getHeader("USER-AGENT").toLowerCase();
	if (null == userAgent) {
	    userAgent = "";
	}
	// 匹配
	Matcher matcherPhone = phonePat.matcher(userAgent);
	Matcher matcherTable = tablePat.matcher(userAgent);
	if (matcherPhone.find() || matcherTable.find()) {
	    response.sendRedirect("/mhome.do");
	} else {
	    response.sendRedirect("home.do");
	}
--%>
<%
response.sendRedirect("login.do");
%>