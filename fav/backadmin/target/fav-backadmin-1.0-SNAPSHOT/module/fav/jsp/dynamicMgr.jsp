<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title><s:property value="jsp_head_title"/></title>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/dynamicMgr.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$(document).ict({
		queryForm:'queryForm',
		queryUrl:'dynamic_query.do',
		queryBtn:'btn_query',
		pagecontent:'pagecontent',
		addUrl:'dynamic_add.do',
		delsUrl:'dynamic_deletes.do',
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
		    <th>发布人</th>
		    <td><s:textfield size="25" id="releaseName" name="dynamicForm.releaseName" onblur="clearBlank(this);" maxlength="60"  /></td>
		    <th>动态内容</th>
		    <td><s:textfield size="25" id="dynamicContent" name="dynamicForm.dynamicContent" onblur="clearBlank(this);" maxlength="60"  /></td>
		</tr>
		<tr>
		    <th>评论数</th>
		    <td><input type="text" size="25" id="commentNumberDesc" class="easyui-validatebox" data-options="validType:'int'" name="dynamicForm.commentNumberDesc" onblur="clearBlank(this);" maxlength="60"  /></td>
		    <th>点赞数</th>
		    <td><input type="text" size="25" id="likeNumberDesc" class="easyui-validatebox" data-options="validType:'int'" name="dynamicForm.likeNumberDesc" onblur="clearBlank(this);" maxlength="60"  /></td>
		</tr>
		<tr>
		    <th width="15%">发布动态时间</th>
		    <td width="35%">
				<input id="startDate" name="startDate" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}', dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" value="<s:property value='startDate'/>" style="width:150px;" /> - 
			    <input id="endDate" name="endDate" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}', dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" value="<s:property value='endDate'/>" style="width:150px;"/>			
			</td>
			<th></th>
			<td></td>
		</tr>
		<tr>
			<td class="bottom" colspan="4" align="center">
		   		<input name="btn" id="btn_query" type="button" class="button" value="查询" />
		   		<s:if test="%{userSession.userPermissions['/module/fav/dynamic_query.do'] != null}">
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
			<!--  <input type="button" id="btn_add" class="button" value="添加"/>
			<s:if test="%{userSession.userPermissions['/module/fav/dynamic_add.do'] != null}">
				<input type="button" id="btn_add" class="button" value="添加"/>
			</s:if>-->
			<input type="button" id="btn_del" class="button" value="删除"/>
			<s:if test="%{userSession.userPermissions['/module/fav/dynamic_deletes.do'] != null}">
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
