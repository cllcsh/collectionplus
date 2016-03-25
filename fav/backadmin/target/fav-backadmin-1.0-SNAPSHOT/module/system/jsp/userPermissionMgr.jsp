<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title><s:property value="jsp_head_title"/></title>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/userPermissionMgr.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$(document).ict({
		queryForm:'queryForm',
		queryUrl:'userPermission_query.do',
		queryBtn:'btn_query',
		pagecontent:'pagecontent',
		addUrl:'userPermission_add.do',
		delsUrl:'userPermission_deletes.do',
		delsBtn:'btn_del'
	});
});
</script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<form id="queryForm" action="">
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_query" border="0" cellspacing="1" cellpadding="1" align="center" >
		<tr>
			<th>
				登录名：
			</th>
			<td>
				<s:textfield size="20" id="loginName" cssClass="td03" name="userPermissionForm.loginName" maxlength="60" onblur="clearBlank(this);"/>
			</td>
			<th>
				用户名：
			</th>
			<td>
				<s:textfield size="20" id="userName" name="userPermissionForm.userName" maxlength="60" onblur="clearBlank(this);"/>
			</td>
		</tr>
		<tr>
			<th>
				司法单位：
			</th>
			<td>
				<s:select id="deptId" name="userPermissionForm.deptId" list="departmentList" listKey="key" listValue="value" cssClass="input2" emptyOption="true" cssStyle="width:135px"></s:select>
			</td>
			<td colspan="2">
			    &nbsp;
			</td>
		</tr>
		
		<tr>
			<td class="bottom" colspan="4" align="center">
		   		<s:if test="%{userSession.userPermissions['/module/system/userPermission_query.do'] != null}">
					<input name="btn" id="btn_query" type="button" class="button" value="查询" />
		   		</s:if>
				<input name="btn" type="reset" class="button" value="重置" />
			</td>
		</tr>
	</table>
</td></tr></table>
</form>
<br/>
<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td align="right" style="padding-right: 20px">
			<s:if test="%{userSession.userPermissions['/module/system/userPermission_add.do'] != null}">
				<input type="button" id="btn_add" class="button" value="添加"/>
			</s:if>
			<s:if test="%{userSession.userPermissions['/module/system/userPermission_deletes.do'] != null}">
				<input type="button" id="btn_del" class="button" value="删除" />
			</s:if>
		</td>
	</tr>
	<tr>
		<td>
			<form id="listForm" action="">
				<div id="pagecontent"></div>
			</form>
		</td>
	</tr>
</table>
</body>
</html>
