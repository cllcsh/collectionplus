<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/letterMgr.js"></script>
<script type="text/javascript">
	<s:if test="%{actionName == 'letter_save'}">
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'letter_save.do',
			    saveUrlTo:'letter_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:if>
	<s:else>
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'letter_update.do',
			    saveUrlTo:'letter_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:else>
</script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<s:form id="setForm" name="letterForm" action="%{actionName}">
	<s:hidden name="letterForm.id"></s:hidden>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr>
						<th width="20%" style="text-align:right;">标题：</th>
						<td width="40%">
							<s:textfield id="name" name="letterForm.title"></s:textfield>
						</td>
						<td width="40%">&nbsp;</td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">接收人：</th>
						<td width="40%">
							<s:textfield id="loginName" name="letterForm.loginName" maxlength="500"></s:textfield>
							<label><input type="checkbox" name="letterForm.sendToAll" value="1"/>全部发送</label>
						</td>
						<td width="40%"><span style="color:red;">请输入登陆名，多个接收人请用逗号分隔</span></td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">类型：</th>
						<td width="40%">
							<select id="type" name="letterForm.type">
								<option value='0'>请选择类型</option>
								<option value='1'>用户审核通过</option>
								<option value='2'>用户审核未通过</option>
								<option value='3'>用户资料更新</option>
								<option value='4'>上挂车源通过</option>
								<option value='5'>上挂车源未通过</option>
								<option value='6'>订单状态变更</option>
								<option value='7'>用户等待审核</option>
								<option value='8'>车源等待审核</option>
							</select>
						</td>
						<td width="40%">&nbsp;</td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">内容：</th>
						<td width="40%">
							<s:textarea id="name" name="letterForm.content" cols="50" rows="5"></s:textarea>
						</td>
						<td width="40%">&nbsp;</td>
					</tr>
					<tr>
						<td class="bottom" align="center" colspan="4">
							<div align="center">
								<s:if test="%{actionName == 'letter_save'}">
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