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
        <td width="10%">帐号</td>
        <td width="10%">IP</td>
        <td width="10%">姓名</td>
        <td width="15%">司法单位</td>
		<td width="20%">登录时间</td>
		<td width="10%">登录时长</td>
		<td width="20%">登录地址</td>
        <td width="10%">状态</td>
      </tr>
      <s:iterator id="loginSta" value="%{pageList.results}" status="sta">
      <tr class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
        <td align="center"><s:property value="#loginSta.loginName"/></td>
		<td align="center"><s:property value="#loginSta.loginIp"/></td>
		<td align="center"><s:property value="#loginSta.userName"/></td>
		<td align="center"><s:property value="#loginSta.deptName"/></td>
        <td align="center"><s:date name="#loginSta.loginDate" format="yyyy-MM-dd HH:mm:ss"/></td>
        <td align="center"><s:property value="#loginSta.onlineTime"/></td>
        <td align="center"><s:property value="#loginSta.loginAddr"/></td>
      <td align="center"><dict:property  codeType="ts_login_log-login_result"  value="#loginSta.loginResult"/></td> 
      </tr>
      </s:iterator>
    </table>
	</td></tr></table>
</s:else>

</body>
</html>