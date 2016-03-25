<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/albumMgr.js"></script>
<script type="text/javascript">
	<s:if test="%{actionName == 'album_save'}">
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'album_save.do',
			    saveUrlTo:'album_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:if>
	<s:else>
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'album_update.do',
			    saveUrlTo:'album_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:else>
	//定义弹出对话框
	$(function() {
		$("#uploadDialog").dialog({
			bgiframe: true,
			autoOpen: false,
			width:500,
			height:200,
			modal: true,
			close: function() {
				$(this).html('');
			}
		});
	});
</script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<s:form id="setForm" name="albumForm" action="%{actionName}">
	<s:hidden name="albumForm.id"></s:hidden>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr>
						<th width="20%" style="text-align:right;">名称<font color="red">*</font></th>
						<td width="40%"><s:textfield id="name" name="albumForm.name"></s:textfield></td>
						<td width="40%"><div id="nameTip"></div></td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">图片<font color="red">*</font></th>
						<td width="40%">
							<s:if test="%{actionName == 'album_save'}">
							<img id="picPathImg" src="" style="cursor: pointer;" width="240" height="180" onclick="openPic()">
							</s:if>
							<s:else>
							<img id="picPathImg" src="<s:property value='albumForm.picPath'/>" style="cursor: pointer;" width="240" height="180" onclick="openPic()" >
							</s:else>
							<s:hidden id="picPath" name="albumForm.picPath"></s:hidden>
						</td>
						<td width="40%"><div id="picPathTip"></div></td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">排序</th>
						<td width="40%"><s:textfield name="albumForm.sorting"></s:textfield></td>
						<td width="40%">&nbsp;</td>
					</tr>
					<tr>
						<td class="bottom" align="center" colspan="4">
							<div align="center">
								<s:if test="%{actionName == 'album_save'}">
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
<div id="uploadDialog" title="图片上传"></div>
<script type="text/javascript">
$.formValidator.initConfig({
	formid:"setForm",
	onerror:function(msg){
	alert(msg)},
	onsuccess:function(){
		return true;
}});
$("#name").formValidator( {
	empty :false,
	onshow :"请输入焦点图名称",
	onfocus :"焦点图名称长度1-50个字符",
	oncorrect :"输入合法",
	forcevalid :false
})
.inputValidator( {
	min :1,
	max :50,
	onerror :"你输入的焦点图名称长度非法，请确认"
});
$("#picPath").formValidator( {
	empty :false,
	onshow :"点击图片编辑",
	onfocus :"请选择图片",
	oncorrect :"输入合法",
	forcevalid :false
})
.inputValidator( {
	min :1,
	onerror :"请点击图片编辑图片"
});

//添加图片
function openPic(){
	$("#uploadDialog").dialog('open').html("正在加载页面...").load(_contextPath + "/upload_uploadInit.do?mode=ajax&uploadContentType=pic&path=file");
}

function callbackpic(fileName, type) {
	fileName = "/upload/" + fileName;
	$("#picPath").val(fileName);
	$("#picPathImg").prop("src", fileName);
	$("#uploadDialog").dialog('close');
}
</script>

</body>
</html>