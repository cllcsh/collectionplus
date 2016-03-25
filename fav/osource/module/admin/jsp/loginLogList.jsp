<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户列表</title>

</head>

<body>
<%@include file="/jsp/include/pageInc.jsp"%>
<s:else>
	<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_result" border="0" cellspacing="1" cellpadding="1" align="center">
      <tr class="tr_head">
		<td><input id="checkboxtop"	type="checkbox" class="ckboxAll" title="选中/取消选中" value="" onclick="checkAll('checkboxtop','ckboxItem');" /></td>
        <td width="10%">帐号</td>
        <td width="20%">IP</td>
		 <td width="30%">登录时间</td>
		 <td width="20%">登录地址</td>
        <td width="20%">状态</td>
      </tr>
      <s:iterator id="loginLog" value="%{pageList.results}" status="sta">
      <tr class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
		<td><input name="checkbox1" type="checkbox" class="ckboxItem" value="<s:property value="#loginLog.id"/>" onclick="check('checkboxtop','checkbox1');" /></td>
<!--        <td><s:property value="#loginLog.id"/></td>-->
        <td align="center"><s:property value="#loginLog.loginName"/></td>
		<td align="center"><s:property value="#loginLog.loginIp"/></td>
        <td align="center"><s:date name="#loginLog.loginDate" format="yyyy-MM-dd HH:mm:ss"/></td>
        <td align="center"><s:property value="#loginLog.loginAddr"/></td>
      <td align="center"><dict:property  codeType="ts_login_log-login_result"  value="#loginLog.loginResult"/></td> 
      </tr>
      </s:iterator>
    </table>
	</td></tr></table>
</s:else>

</body>
</html>