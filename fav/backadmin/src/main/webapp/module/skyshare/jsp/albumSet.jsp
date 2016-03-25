<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>
<%@taglib prefix="ict" uri="/ict-tags"%>
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
</script>
	</head>

	<body>
		<s:form id="setForm" name="albumForm" action="%{actionName}">
			<s:hidden name="album.id"></s:hidden>
			<table class="bg" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td>
						<table class="tb_add" width="100%" border="0" cellpadding="1"
							cellspacing="1">
							<tr>
								<th width="15%">
									标题：
								</th>
								<td colspan="2">
									<s:textfield size="20" id="title" cssClass="td03"
										name="albumForm.name" maxlength="60"
										onblur="clearBlank(this);" />
									<font class="redStar">*</font>

								</td>
								<td width="35%">
									<div id="nameTip"></div>
								</td>
							</tr>
							<tr>
								<th align="right">
									图标：
								</th>
								<td colspan="3">
									<s:textfield id="picPath" name="albumForm.picPath"
										cssClass="input2" size="20" onblur="clearBlank(this);"
										readonly="true"></s:textfield>
									<input type="button" id="btn_upfile" onclick="up_file();"
										class="button2" onmouseout="this.className = 'button2'"
										onmouseover="this.className = 'button2Over'" value="图片上传" />
									<input type="button" id="btn_clear" onclick="clear_upfile();"
										class="button" onmouseout="this.className = 'button'"
										onmouseover="this.className = 'buttonOver'" value="清空" />
								</td>
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
										<input type="button" onclick="javascript:history.back();"
											class="button" value="返回" />
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<div id="uploadDialog" title="文件上传"></div>
		</s:form>

		<script type="text/javascript">
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

$("#content").formValidator({
	onshow : "请输入消息内容",
	onfocus : "消息内容长度不能超过200个字符",
	oncorrect : "输入合法"
}).inputValidator({
	min:0,
	max:200,
	onerror:"消息内容长度不能超过200个字符"
});		
</script>

	</body>
</html>