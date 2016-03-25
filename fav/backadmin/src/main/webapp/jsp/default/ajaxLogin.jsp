<%
String path = request.getContextPath();
%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<%@include file="/jsp/meta.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script language="javascript"> 
       document.openDialog("loginDialog").load("<%=path%>/login_ajax.do?mode=ajaxJson");
</script> 

</head>

<body>
    <s:hidden id="totalNum" value="0"></s:hidden>
</body>
</html>
