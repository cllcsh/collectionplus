<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<html>
<head>
<title><s:property value="jsp_head_title"/>-在线列表</title>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/onlineMgr.js"></script>
</head>

<body onload="doSchedule();">
<%@include file="/jsp/include/pageInc.jsp"%>
<s:else>
	<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_result" border="0" cellspacing="1" cellpadding="1" align="center">
	  <tr align="center">
		<td class="bottom" style="height: 22px" colspan="2"><b>在线用户列表</b></td>
      </tr>
      <tr class="tr_head" align="center">
		<td width="">用户名</td>
        <td width="">所属机构</td>
      </tr>
      <s:iterator id="onlineUserInfo" value="%{pageList.results}" status="sta">
      <tr align="center" class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
        <td><s:property value="#onlineUserInfo.name"/></td>
        <td><s:property value="#onlineUserInfo.deptName"/></td>
      </tr>
      </s:iterator>
    </table>
	</td></tr></table>
</s:else>

</body>
</html>
