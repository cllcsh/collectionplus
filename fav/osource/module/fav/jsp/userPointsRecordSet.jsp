<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/userPointsRecordMgr.js"></script>
<script type="text/javascript">
	<s:if test="%{actionName == 'userPointsRecord_save'}">
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'userPointsRecord_save.do',
			    saveUrlTo:'userPointsRecord_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:if>
	<s:else>
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'userPointsRecord_update.do',
			    saveUrlTo:'userPointsRecord_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:else>
</script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<s:form id="setForm" name="userPointsRecordForm" action="%{actionName}">
	<s:hidden name="userPointsRecordForm.id"></s:hidden>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr>
					    <th width="20%" style="text-align:right;">用户id：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true" name="userPointsRecordForm.userid" value="<s:property value="userPointsRecordForm.userid"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">积分：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true" name="userPointsRecordForm.points" value="<s:property value="userPointsRecordForm.points"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">积分获取描述：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true" name="userPointsRecordForm.comment" value="<s:property value="userPointsRecordForm.comment"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">每日任务id：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true" name="userPointsRecordForm.taskid" value="<s:property value="userPointsRecordForm.taskid"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">获取日期yyyyMMdd：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true" name="userPointsRecordForm.day" value="<s:property value="userPointsRecordForm.day"/>"/>
					    </td>
					</tr>

					<tr>
						<td class="bottom" align="center" colspan="4">
							<div align="center">
								<s:if test="%{actionName == 'userPointsRecord_save'}">
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