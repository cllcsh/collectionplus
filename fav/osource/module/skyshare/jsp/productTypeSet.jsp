<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/productTypeMgr.js"></script>
<script type="text/javascript">
	<s:if test="%{actionName == 'productType_save'}">
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'productType_save.do',
			    saveUrlTo:'productType_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:if>
	<s:else>
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'productType_update.do',
			    saveUrlTo:'productType_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:else>
</script>
</head>

<body>
<s:form id="setForm" name="productTypeForm" action="%{actionName}">
	<s:hidden name="productTypeForm.id"></s:hidden>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr>
						<th width="15%">
							名称：
						</th>
						<td colspan="2">
							<s:textfield size="20" id="typeName" cssClass="td03"
										name="productTypeForm.typeName" maxlength="60"
										onblur="clearBlank(this);" />
									<font class="redStar">*</font>
						</td>
						<td width="35%"><div id="typeNameTip"></div></td>
					</tr>
					<tr>
						<th>
							描述：
						</th>
						<td colspan="2">
							<s:textfield size="20" id="remark" cssClass="td03"
										name="productTypeForm.remark" maxlength="100"
										onblur="clearBlank(this);" />
									
						</td>
						<td><div id="remarkTip"></div></td>
					</tr>
					<tr>
						<td class="bottom" align="center" colspan="4">
							<div align="center">
								<s:if test="%{actionName == 'productType_save'}">
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

$("#typeName").formValidator( {
	onshow : "请输入产品类型名称，长度为1~60个字符",
	onfocus : "类型名称不能为空",
	oncorrect : "输入合法"
}).inputValidator( {
	min:1,
	max:60,
	onerror:"请确认类型名称长度(1~60个字符)"
});	

$("#remark").formValidator( {
	onshow : "请输入类型描述",
	onfocus : "类型描述长度不能超过100个字符",
	oncorrect : "输入合法"
}).inputValidator( {
	min:0,
	max:100,
	onerror:"类型描述长度不能超过100个字符"
});
</script>

</body>
</html>