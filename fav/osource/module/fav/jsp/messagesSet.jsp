<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/messagesMgr.js"></script>
<script type="text/javascript">
	<s:if test="%{actionName == 'messages_save'}">
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'messages_save.do',
			    saveUrlTo:'messages_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:if>
	<s:else>
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'messages_update.do',
			    saveUrlTo:'messages_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:else>
</script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<s:form id="setForm" name="messagesForm" action="%{actionName}">
	<s:hidden name="messagesForm.id"></s:hidden>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr>
					    <th width="20%" style="text-align:right;">发送 人（发送人的用户id）：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true" name="messagesForm.sender" value="<s:property value="messagesForm.sender"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">接收人（接收人的用户id）：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true" name="messagesForm.receiver" value="<s:property value="messagesForm.receiver"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">发送时间：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true" name="messagesForm.sendTime" value="<s:property value="messagesForm.sendTime"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">消息内容：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true" name="messagesForm.content" value="<s:property value="messagesForm.content"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">是否已读，Y:已读，N：未读：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true" name="messagesForm.isRead" value="<s:property value="messagesForm.isRead"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">是否删除，Y:已删除，N：未删除：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true" name="messagesForm.isDelete" value="<s:property value="messagesForm.isDelete"/>"/>
					    </td>
					</tr>

					<tr>
						<td class="bottom" align="center" colspan="4">
							<div align="center">
								<s:if test="%{actionName == 'messages_save'}">
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