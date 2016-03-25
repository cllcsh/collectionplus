<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/feedbackMgr.js"></script>
<script type="text/javascript">
	<s:if test="%{actionName == 'feedback_save'}">
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'feedback_save.do',
			    saveUrlTo:'feedback_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:if>
	<s:else>
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'feedback_update.do',
			    saveUrlTo:'feedback_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:else>
</script>
</head>

<body>
<s:form id="setForm" name="feedbackForm" action="%{actionName}">
	<s:hidden name="feedbackForm.id"></s:hidden>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr>
						<th width="15%" > 
							反馈人：
						</th>
						<td  colspan="2" width="40%" >  
							<s:select id="userId" name="feedbackForm.userId" list="userList" listKey="id.stringValue"
												listValue="name.stringValue" value="%{feedbackForm.userId}" cssStyle="width:155px"></s:select>
							<font class="redStar">*</font>
						</td>
						<td>
							<div id="userIdTip"></div>
						</td>
					</tr>
					<tr>
						<th  width="15%">
							反馈内容： 
						</th>
						<td colspan="2">
							<s:textarea id="content" name="feedbackForm.content" cols="50" rows="5"></s:textarea>
						</td>
						<td>
							<div id="contentTip"></div>
						</td>
					</tr>
					<tr>
						<th  width="15%">
							反馈时间：
						</th>
						<td colspan="2">
							<input id="feedbackTime" style="" name="feedbackForm.time" 
								type="text"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate"/>
						</td>
						<td>
							<div id="feedbackTimeTip"></div>
						</td>
					</tr>
					
					<tr>
						<td class="bottom" align="center" colspan="4">
							<div align="center">
								<s:if test="%{actionName == 'feedback_save'}">
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