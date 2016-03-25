<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="<%=path%>/resource/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="js/noticeMgr.js"></script>
<script type="text/javascript">
	<s:if test="%{actionName == 'notice_save'}">
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'notice_save.do',
			    saveUrlTo:'notice_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:if>
	<s:else>
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'notice_update.do',
			    saveUrlTo:'notice_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:else>
	
	function check() {
		var oEditor = CKEDITOR.instances.ckeditor1; 
		var content = oEditor.getData();
		//alert(content);
		$("#content").val(content);
		return true;
	}
</script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<s:form id="setForm" name="noticeForm" action="%{actionName}" onsubmit="javascript:return check()">
	<s:hidden name="noticeForm.id"></s:hidden>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr>
						<th  width="15%">
							标题： 
						</th>
						<td colspan="2">
						<s:textfield size="60" id="title" cssClass="td03"
										name="noticeForm.title" maxlength="60"
										onblur="clearBlank(this);" />
									<font class="redStar">*</font>
						</td>
						<td>
							<div id="contentTip"></div>
						</td>
					</tr>
					<tr>
						<th  width="15%">
							消息内容： 
						</th>
						<td colspan="2">
							<textarea class="ckeditor" id="ckeditor1" name="ckeditor1"></textarea>
							<s:hidden id="content" name="noticeForm.content"></s:hidden>
						</td>
						<td>
							<div id="contentTip"></div>
						</td>
					</tr>
					<tr>
						<td class="bottom" align="center" colspan="4">
							<div align="center">
								<s:if test="%{actionName == 'notice_save'}">
									<input type="button" id="btn_save" onmouseover="check()" class="button" value="增加" />
								</s:if>
								<s:else>
									<input type="button" id="btn_save" onmouseover="check()" class="button" value="保存" />
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
$(document).ready(function() {	
	setTimeout(loadEditorData, 10);
	//alert(content);
	//$("#noticeContent").val(content);
});

function loadEditorData() {
	var content = $("#content").val();
	
	if (content != "")
	{
		var oEditor = CKEDITOR.instances.ckeditor1; 
		oEditor.setData(content);
	}
}
$.formValidator.initConfig({
	formid:"setForm",
	onerror:function(msg){
	alert(msg)},
	onsuccess:function(){
		return true;
}});
$("#title").formValidator( {
		onshow : "请输入消息标题，长度为1~60个字符",
		onfocus : "消息标题不能为空",
		oncorrect : "输入合法"
	}).inputValidator( {
		min:1,
		max:60,
		onerror:"请确认标题长度(1~60个字符)"
	});			
</script>

</body>
</html>