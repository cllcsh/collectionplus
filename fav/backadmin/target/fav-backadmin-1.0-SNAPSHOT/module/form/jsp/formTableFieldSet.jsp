<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>
<%@taglib prefix="ict" uri="/ict-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/formTableFieldMgr.js"></script>
<script type="text/javascript">
	<s:if test="%{actionName == 'formTableField_save'}">
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'formTableField_save.do',
			    saveUrlTo:'formTableField_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:if>
	<s:else>
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'formTableField_update.do',
			    saveUrlTo:'formTableField_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:else>
</script>
</head>

<body>
<s:form id="setForm" name="formTableFieldForm" action="%{actionName}">
	<s:hidden name="formTableFieldForm.id"></s:hidden>
	<s:hidden name="tableId"></s:hidden>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr>
					    <th  width="15%">显示名</th>
					    <td width="35%"><s:textfield id="displayName" name="formTableFieldForm.displayName"
										maxlength="50" cssStyle="width:150px" /><font class="redStar">*</font><div id="displayNameTip"></div>
						</td>
						<th width="15%">字段名</th>
						<td width="35%"><s:textfield id="name" name="formTableFieldForm.name"
										maxlength="20" cssStyle="width:150px" /><font class="redStar">*</font><div id="nameTip"></div>
						</td>
					</tr>
					<tr>
					    <th width="15%">数据类型</th>
					    <td width="35%"><dict:select id="type" name="formTableFieldForm.type" codeType="form-dataType" cssClass="input2"></dict:select>
					    <font class="redStar">*</font><div id="typeTip"></div>
						</td>
						<th></th>
						<td></td>
					</tr>
					<tr>
						<td class="bottom" align="center" colspan="4">
							<div align="center">
								<s:if test="%{actionName == 'formTableField_save'}">
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
</body>

<script type="text/javascript">
	$.formValidator.initConfig( {
	formid :"setForm",
	onerror : function(msg) {
		alert(msg)
	},
	onsuccess : function() {
		return true;
	}
	});

	$("#displayName").formValidator( {
		onshow :"请输入显示名",
		oncorrect :"输入合法"
	}).inputValidator( {
		min :1,
		max :50,
		onerror :"请输入显示名(1-50字符)"
	});
	$("#name").formValidator( {
		onshow :"请输入字段名",
		oncorrect :"输入合法"
	}).inputValidator( {
		min :1,
		max :20,
		onerror :"请输入字段名(1-20字符)"
	});
	$("#type").formValidator( {
		onshow :"请选择数据类型",
		oncorrect :"输入合法"
	}).inputValidator( {
		min :1,
		max :20,
		onerror :"请选择数据类型"
	});

</script>
</html>