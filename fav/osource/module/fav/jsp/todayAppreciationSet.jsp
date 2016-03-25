<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/todayAppreciationMgr.js"></script>
<script type="text/javascript">
	<s:if test="%{actionName == 'todayAppreciation_save'}">
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'todayAppreciation_save.do',
			    saveUrlTo:'todayAppreciation_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:if>
	<s:else>
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'todayAppreciation_update.do',
			    saveUrlTo:'todayAppreciation_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:else>
</script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<s:form id="setForm" name="todayAppreciationForm" action="%{actionName}">
	<s:hidden name="todayAppreciationForm.id"></s:hidden>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr>
					    <th width="20%" style="text-align:right;">标题：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true" name="todayAppreciationForm.title" value="<s:property value="todayAppreciationForm.title"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">内容：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true" name="todayAppreciationForm.content" value="<s:property value="todayAppreciationForm.content"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">发布时间：</th>
					    <td width="40%">
					    	<input id="releaseTime" name="todayAppreciationForm.releaseTime" value="<s:date name="todayAppreciationForm.releaseTime" format="yyyy-MM-dd HH:mm:ss" />" type="text" cssStyle="width:150px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" />
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">上传图片：</th>
					    <td width="40%">
					    	<s:if test="%{todayAppreciationForm.image != null && todayAppreciationForm.image != ''}">
								<img id="imgPathImg" src="<s:property value='todayAppreciationForm.image'/>" alt="点击编辑" style="cursor: pointer;" width="32" height="32" onclick="openDialogUpload('imgPath');"/>
							</s:if>
							<s:else>
								<img id="imgPathImg" src="images/ryxx02.gif" alt="点击上传" style="cursor: pointer;" width="32" height="32" onclick="openDialogUpload('imgPath');"/>
							</s:else>
					        <input type="hidden" id="imgPath" name="todayAppreciationForm.image" value="<s:property value="todayAppreciationForm.image"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">显示顺序：</th>
					    <td width="40%">
					    	<s:select id="displayOrder" name="todayAppreciationForm.displayOrder" list="orderMap" listKey="key" listValue="value" cssStyle="width:150px"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">是否显示：</th>
					    <td width="40%">
					    	<s:select id="isShow" name="todayAppreciationForm.isShow" list="isShowMap" listKey="key" listValue="value" cssStyle="width:150px"/>
					    </td>
					</tr>
					<tr>
						<td class="bottom" align="center" colspan="4">
							<div align="center">
								<s:if test="%{actionName == 'todayAppreciation_save'}">
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
var picType;
//添加图片
function openDialogUpload(labelId){
	picType = labelId;
	$("#uploadDialog").dialog('open').html("正在加载页面...").load(_contextPath + "/upload_uploadInit.do?mode=ajax&uploadContentType=pic&path=file");
}
function callbackpic(fileName, type) {
	fileName = "/upload/" + fileName;
	$("#" + picType).val(fileName);
	$("#" + picType + "Img").prop("src", fileName);
	$("#uploadDialog").dialog('close');
}
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