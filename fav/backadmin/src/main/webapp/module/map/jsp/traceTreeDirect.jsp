<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
  <head>
    <%@include file="/jsp/common.jsp" %>
  </head>
  <body>
  	<s:hidden id="his_ShowType" name="showType"></s:hidden>
  	<s:hidden id="his_depId" name="depId"></s:hidden>
	<div id="mask" class="mask" style="background-color: #F5FAFE;"></div>
  </body>
</html>
<script language="javascript">
	document.location = "historyTrace_getTree.do?showType="+document.getElementById("his_ShowType").value+"&depId="+document.getElementById("his_depId").value;
</script>