<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title><s:property value="jsp_head_title"/></title>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/dynamicCommentsMgr.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$(document).ict({
		queryForm:'queryForm',
		queryUrl:'dynamicComments_query.do',
		queryBtn:'btn_query',
		pagecontent:'pagecontent',
		addUrl:'dynamicComments_add.do',
		delsUrl:'dynamicComments_deletes.do',
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
		    <th>动态id</th>
		    <td><s:textfield size="25" id="dynamicId" name="dynamicCommentsForm.dynamicId" onblur="clearBlank(this);" maxlength="60"  /></td>
		    <th>好友用户id</th>
		    <td><s:textfield size="25" id="friendId" name="dynamicCommentsForm.friendId" onblur="clearBlank(this);" maxlength="60"  /></td>
		</tr>
		<tr>
		    <th>评论内容</th>
		    <td><s:textfield size="25" id="commentContent" name="dynamicCommentsForm.commentContent" onblur="clearBlank(this);" maxlength="60"  /></td>
		    <th>评论时间</th>
		    <td><s:textfield size="25" id="commentTime" name="dynamicCommentsForm.commentTime" onblur="clearBlank(this);" maxlength="60"  /></td>
		</tr>
		<tr>
		    <th>评论类型 0：评论 1：回复</th>
		    <td><s:textfield size="25" id="type" name="dynamicCommentsForm.type" onblur="clearBlank(this);" maxlength="60"  /></td>
		    <th>顶的数量</th>
		    <td><s:textfield size="25" id="topSize" name="dynamicCommentsForm.topSize" onblur="clearBlank(this);" maxlength="60"  /></td>
		</tr>

		<tr>
			<td class="bottom" colspan="4" align="center">
		   		<input name="btn" id="btn_query" type="button" class="button" value="查询" />
		   		<s:if test="%{userSession.userPermissions['/module/fav/dynamicComments_query.do'] != null}">
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
			<s:if test="%{userSession.userPermissions['/module/fav/dynamicComments_add.do'] != null}">
				<input type="button" id="btn_add" class="button" value="添加"/>
			</s:if>
			<input type="button" id="btn_del" class="button" value="删除"/>
			<s:if test="%{userSession.userPermissions['/module/fav/dynamicComments_deletes.do'] != null}">
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
