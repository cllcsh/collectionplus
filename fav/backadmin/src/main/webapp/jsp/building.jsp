<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<%@include file="/jsp/meta.jsp" %>
</head>
<body style="overflow:hidden">
<% String fun = request.getParameter("fun"); %>
<div align="center"><%=fun%>：正在开发中...</div>
</body>
</html>