<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/brandMgr.js"></script>
<script type="text/javascript">
	<s:if test="%{actionName == 'brand_save'}">
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'brand_save.do',
			    saveUrlTo:'brand_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:if>
	<s:else>
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'brand_update.do',
			    saveUrlTo:'brand_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:else>
</script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<s:form id="setForm" name="brandForm" action="%{actionName}">
	<s:hidden name="brandForm.id"></s:hidden>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td>
							<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
								<tr>
									<th  width="15%">
										拼音： 
									</th>
									<td colspan="2">
										 <s:select list="#{'A':'A','B':'B','C':'C','D':'D','E':'E','F':'F','G':'G','H':'H','I':'I','J':'J','K':'K','L':'L','M':'M','N':'N','O':'O','P':'P','Q':'Q','R':'R','S':'S','T':'T','U':'U','V':'V','W':'W','X':'X','Y':'Y','Z':'Z'}" name="brandForm.pinyin"></s:select>
									</td>
									<td>
										<div id="pinyinTip"></div>
									</td>
								</tr>
								<tr>
									<th  width="15%">
										名称： 
									</th>
									<td colspan="2">
										<s:textfield id="name" name="brandForm.name"></s:textfield>
									</td>
									<td>
										<div id="nameTip"></div>
									</td>
								</tr>
								<tr>
									 <th align="right">图标上传：</th>
										<td colspan="2">
											<s:textfield id="picPath" name="brandForm.picPath"
												cssClass="input2" size="20" onblur="clearBlank(this);"
												readonly="true"></s:textfield>
											<input type="button" id="btn_upfile" onclick="up_file();"
												class="button2" onmouseout="this.className = 'button2'"
												onmouseover="this.className = 'button2Over'" value="图片上传" />
											<input type="button" id="btn_clear" onclick="clear_upfile();"
												class="button" onmouseout="this.className = 'button'"
												onmouseover="this.className = 'buttonOver'" value="清空" />
										</td>
									<td>
										<div id="picPathTip"></div>
									</td>
								</tr>
								<tr>
									<td class="bottom" align="center" colspan="4">
										<div align="center">
											<s:if test="%{actionName == 'brand_save'}">
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
</s:form>
<div id="uploadDialog" title="文件上传"></div>
<script type="text/javascript">
$.formValidator.initConfig({
	formid:"setForm",
	onerror:function(msg){
	alert(msg)},
	onsuccess:function(){
		return true;
}});

$("#name").formValidator( {
		onshow : "请输入品牌标题，长度为1~60个字符",
		onfocus : "品牌标题不能为空",
		oncorrect : "输入合法"
	}).inputValidator( {
		min:1,
		max:60,
		onerror:"请确认标题长度(1~60个字符)"
});	
$("#picPath").formValidator( {
		onshow : "请输入图标",
		onfocus : "图标不能为空",
		oncorrect : "输入合法"
	}).inputValidator( {
		min:1,
		max:60,
		onerror:"请确认上传图标！"
});	

function callbackpic(fileName, type) {
	fileName = "/upload/" + fileName;
	$("#picPath").val(fileName);
	$("#uploadDialog").dialog('close');
}
</script>

</body>
</html>