<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/dailyPolemicVoteMgr.js"></script>
<script type="text/javascript">
	<s:if test="%{actionName == 'dailyPolemicVote_save'}">
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'dailyPolemicVote_save.do',
			    saveUrlTo:'dailyPolemicVote_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:if>
	<s:else>
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'dailyPolemicVote_update.do',
			    saveUrlTo:'dailyPolemicVote_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:else>
</script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<s:form id="setForm" name="dailyPolemicVoteForm" action="%{actionName}">
	<s:hidden name="dailyPolemicVoteForm.id"></s:hidden>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr>
					    <th width="20%" style="text-align:right;">天天论战id：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true" name="dailyPolemicVoteForm.dailyPolemicId" value="<s:property value="dailyPolemicVoteForm.dailyPolemicId"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">投票人：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true" name="dailyPolemicVoteForm.userId" value="<s:property value="dailyPolemicVoteForm.userId"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">1:反对;0:赞成：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true" name="dailyPolemicVoteForm.type" value="<s:property value="dailyPolemicVoteForm.type"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">投票日期 yyyy-MM-dd：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true" name="dailyPolemicVoteForm.day" value="<s:property value="dailyPolemicVoteForm.day"/>"/>
					    </td>
					</tr>
					<tr>
						<td class="bottom" align="center" colspan="4">
							<div align="center">
								<s:if test="%{actionName == 'dailyPolemicVote_save'}">
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