<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户列表</title>

</head>

<body>
<s:hidden id="totalNum" value="%{pageList.pages.total}"></s:hidden>
<s:if test="pageList.pages.total == -1">
	<br/><br/><br/>数据库异常，请稍后再试!<br/><br/><br/>
</s:if>
<s:elseif test="pageList.pages.total == 0">
	<br/><br/><br/>抱歉!没有检索到您要的信息<br/><br/><br/>
</s:elseif>
<s:else>
	<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_result" border="0" cellspacing="1" cellpadding="1" align="center">
      <tr class="tr_head">
		<td><input id="checkboxtop"	type="checkbox" class="ckboxAll" title="选中/取消选中" value="" onclick="checkAll('checkboxtop','ckboxItem');" /></td>
        <td width="15%">配置类型</td>
        <td width="15%">配置键</td>
        <td width="25%">配置值</td>
        <td width="40%">描述</td>
        <td width="10%">操作</td>
      </tr>
      <s:iterator id="configInfo" value="%{pageList.results}" status="sta">
      <tr class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
		<td><input name="checkbox1" type="checkbox" class="ckboxItem" value="<s:property value="#configInfo.configCode"/>" onclick="check('checkboxtop','checkbox1');" /></td>
        <td><s:property value="#configInfo.configType"/></td>
        <td>
        	<s:property value="#configInfo.configKey"/>
        </td>
		<td><s:property value="#configInfo.configValue"/></td>
		<td><s:property value="#configInfo.configDesc"/></td>
		<td align="center">
        <s:if test="%{userSession.userPermissions['/module/system/config_edit.do'] != null}">
   		    <a href="javascript:edit_config(<s:property value='#configInfo.configCode'/>)" ><s:text name="ictmap.modify"/></a>&nbsp;&nbsp;
		</s:if>
		</td>
      </tr>
      </s:iterator>
    </table>
	</td></tr></table>
</s:else>

</body>
</html>
