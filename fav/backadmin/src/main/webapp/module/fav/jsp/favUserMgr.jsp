<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title><s:property value="jsp_head_title"/></title>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/favUserMgr.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$(document).ict({
		queryForm:'queryForm',
		queryUrl:'favUser_query.do',
		queryBtn:'btn_query',
		pagecontent:'pagecontent',
		addUrl:'favUser_add.do',
		delsUrl:'favUser_deletes.do',
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
		    <th>名称</th>
		    <td><s:textfield size="25" id="userName" name="favUserForm.userName" onblur="clearBlank(this);" maxlength="60"  /></td>
		    <th>账号</th>
		    <td><s:textfield size="25" id="account" name="favUserForm.account" onblur="clearBlank(this);" maxlength="60"  /></td>
		</tr>
		<tr>
		    <th>手机号</th>
		    <td><s:textfield size="25" id="phone" name="favUserForm.phone" onblur="clearBlank(this);" maxlength="60"  /></td>
		    <th>热度</th>
		    <td><input type="text" size="25" id="heat" class="easyui-validatebox" data-options="validType:'int'" name="favUserForm.heatDesc" onblur="clearBlank(this);" maxlength="60"  /></td>
		</tr>
		<tr>
		    <th>用户等级</th>
		    <td>
		    	<s:select id="userLevel" name="favUserForm.userLevel" list="levelMap" listKey="key" listValue="value" headerKey="-1" headerValue="所有" cssClass="easyui-validatebox" data-options="required:true" cssStyle="width:150px"/>
		    </td>
		    <th>用户称号</th>
		    <td>
		    	<s:select id="userTitle" name="favUserForm.userTitle" list="userTitleMap" listKey="key" listValue="value" headerKey="-1" headerValue="所有" cssClass="easyui-validatebox" data-options="required:true" cssStyle="width:150px"/>
		    </td>
		</tr>
		<tr>
			<th width="15%">更换头像时间</th>
			<td width="35%">
				<input id="startDate" name="startDate" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}', dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" value="<s:property value='startDate'/>" style="width:150px;" /> - 
			    <input id="endDate" name="endDate" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}', dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" value="<s:property value='endDate'/>" style="width:150px;"/>			
			</td>
		    <th>用户当前积分</th>
		    <td><input type="text" size="25" id="userPoints" class="easyui-validatebox" data-options="validType:'int'" name="favUserForm.userPointsDesc" onblur="clearBlank(this);" maxlength="60"  /></td>
		</tr>
		<tr>
		    <th>用户累计积分</th>
		    <td><input type="text" size="25" id="userAllPoints" class="easyui-validatebox" data-options="validType:'int'" name="favUserForm.userAllPointsDesc" onblur="clearBlank(this);" maxlength="60"  /></td>
		    <th>经验值</th>
		    <td><input type="text" size="25" id="experience" class="easyui-validatebox" data-options="validType:'int'" name="favUserForm.experienceDesc" onblur="clearBlank(this);" maxlength="60"  /></td>
		</tr>
		<tr>
		    <th>个人私信设置</th>
		    <td><s:select id="userLevel" name="favUserForm.personalMsgSet" list="personalMsgSetMap" listKey="key" listValue="value" headerKey="-1" headerValue="所有" cssClass="easyui-validatebox" data-options="required:true" cssStyle="width:150px"/></td>
		    <th></th>
		    <td></td>
		</tr>
		<tr>
			<td class="bottom" colspan="4" align="center">
		   		<input name="btn" id="btn_query" type="button" class="button" value="查询" />
		   		<s:if test="%{userSession.userPermissions['/module/fav/favUser_query.do'] != null}">
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
			<input type="button" id="btn_add" class="button" value="添加"/>
			<s:if test="%{userSession.userPermissions['/module/fav/favUser_add.do'] != null}">
				<input type="button" id="btn_add" class="button" value="添加"/>
			</s:if>
			<input type="button" id="btn_del" class="button" value="删除"/>
			<s:if test="%{userSession.userPermissions['/module/fav/favUser_deletes.do'] != null}">
				<input type="button" id="btn_del" class="button" value="删除"/>
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
