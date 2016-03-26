<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/dynamicCommentsMgr.js"></script>
<script type="text/javascript">
	<s:if test="%{actionName == 'dynamicComments_save'}">
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'dynamicComments_save.do',
			    saveUrlTo:'dynamicComments_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:if>
	<s:else>
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'dynamicComments_update.do',
			    saveUrlTo:'dynamicComments_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:else>
</script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<s:form id="setForm" name="dynamicCommentsForm" action="%{actionName}">
	<s:hidden name="dynamicCommentsForm.id"></s:hidden>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr>
					    <th width="20%" style="text-align:right;">动态id：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true" name="dynamicCommentsForm.dynamicId" value="<s:property value="dynamicCommentsForm.dynamicId"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">好友用户id：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true" name="dynamicCommentsForm.friendId" value="<s:property value="dynamicCommentsForm.friendId"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">评论内容：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true" name="dynamicCommentsForm.commentContent" value="<s:property value="dynamicCommentsForm.commentContent"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">评论时间：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true" name="dynamicCommentsForm.commentTime" value="<s:property value="dynamicCommentsForm.commentTime"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">评论类型 0：评论 1：回复：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true" name="dynamicCommentsForm.type" value="<s:property value="dynamicCommentsForm.type"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">顶的数量：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true" name="dynamicCommentsForm.topSize" value="<s:property value="dynamicCommentsForm.topSize"/>"/>
					    </td>
					</tr>

					<tr>
						<td class="bottom" align="center" colspan="4">
							<div align="center">
								<s:if test="%{actionName == 'dynamicComments_save'}">
									<input type="button" id="btn_save" class="button" value="增加" />
								</s:if>
								<s:else>
									<input type="button" id="btn_save" class="button" value="保存" />
								</s:else>
								<input type="button" onclick="javascript:history.back();" class="button" value="返回"/>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</s:form>

<script type="text/javascript">
$.formValidator.initConfig({
	formid:"setForm",
	onerror:function(msg){
	alert(msg)},
	onsuccess:function(){
		return true;
}});
</script>

</body>
</html>