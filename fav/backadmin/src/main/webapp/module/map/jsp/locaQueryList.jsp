<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<title><s:property value="jsp_head_title"/>-定位查询</title>
<%@include file="/jsp/common.jsp"%>
<%@include file="/jsp/xtree.jsp" %>
<script type="text/javascript" src="js/locaQueryMgr.js"></script>
</head>

<body>
    <%=request.getAttribute("strHtml")%>
    <script type="text/javascript">
    	document.getElementById("btn_query").disabled = false;
    </script>
</body>
</html>
