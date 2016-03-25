<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title><s:property value="jsp_head_title"/></title>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/dynamicCommentTopMgr.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$(document).ict({
		queryForm:'queryForm',
		queryUrl:'dynamicCommentTop_query.do',
		queryBtn:'btn_query',
		pagecontent:'pagecontent',
		addUrl:'dynamicCommentTop_add.do',
		delsUrl:'dynamicCommentTop_deletes.do',
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
		    <th>来源</th>
		    <td><s:textfield size="25" id="sourceName" name="dynamicCommentTopForm.sourceName" onblur="clearBlank(this);" maxlength="60"  /></td>
		    <th>来源类型</th>
		    <td>
		    	<s:select id="sourceType" name="dynamicCommentTopForm.sourceType" list="sourceTypeMap" listKey="key" listValue="value" headerKey="-1" headerValue="所有" cssStyle="width:180px"/>
		    </td>
		</tr>
		<tr>
		    <th width="15%">顶的人</th>
		    <td width="35%"><s:textfield size="25" id="userId" name="dynamicCommentTopForm.userName" onblur="clearBlank(this);" maxlength="60"  /></td>
		    <th width="15%">顶时间</th>
			<td width="35%">
				<input id="startDate" name="startDate" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}', dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" value="<s:property value='startDate'/>" style="width:150px;" /> - 
			    <input id="endDate" name="endDate" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}', dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" value="<s:property value='endDate'/>" style="width:150px;"/>			
			</td>
		</tr>
		<tr>
		    <th>评论内容</th>
		    <td><s:textfield size="25" id="commentContent" name="dynamicCommentTopForm.commentContent" onblur="clearBlank(this);" maxlength="60"  /></td>
		    <th></th>
		    <td></td>
		</tr>
		<tr>
			<td class="bottom" colspan="4" align="center">
		   		<input name="btn" id="btn_query" type="button" class="button" value="查询" />
		   		<s:if test="%{userSession.userPermissions['/module/fav/dynamicCommentTop_query.do'] != null}">
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
			<s:if test="%{userSession.userPermissions['/module/fav/dynamicCommentTop_add.do'] != null}">
				<input type="button" id="btn_add" class="button" value="添加"/>
			</s:if>
			<input type="button" id="btn_del" class="button" value="删除"/>
			<s:if test="%{userSession.userPermissions['/module/fav/dynamicCommentTop_deletes.do'] != null}">
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
