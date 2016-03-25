<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/famousHomeMgr.js"></script>
<script type="text/javascript">
	<s:if test="%{actionName == 'famousHome_save'}">
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'famousHome_save.do',
			    saveUrlTo:'famousHome_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:if>
	<s:else>
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'famousHome_update.do',
			    saveUrlTo:'famousHome_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:else>
</script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<s:form id="setForm" name="famousHomeForm" action="%{actionName}">
	<s:hidden name="famousHomeForm.id"></s:hidden>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr>
					    <th width="20%" style="text-align:right;">名字：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true" name="famousHomeForm.name" value="<s:property value="famousHomeForm.name"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">图标：</th>
					    <td width="40%">
					   		<s:if test="%{famousHomeForm.icon != null && famousHomeForm.icon != ''}">
								<img id="imgPathImg" src="${qnImageUrl}<s:property value='famousHomeForm.icon'/>?imageView2/1/w/32/h/32" alt="点击编辑" style="cursor: pointer;" width="32" height="32" onclick="openDialogUpload('imgPath');"/>
							</s:if>
							<s:else>
								<img id="imgPathImg" src="images/ryxx02.gif" alt="点击上传" style="cursor: pointer;" width="32" height="32" onclick="openDialogUpload('imgPath');"/>
							</s:else>
					        <input type="hidden" id="imgPath"  name="famousHomeForm.icon" value="<s:property value="famousHomeForm.icon"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">个人简介：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true" name="famousHomeForm.introduction" value="<s:property value="famousHomeForm.introduction"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">是否入驻：</th>
					    <td width="40%">
					    	<s:select id="status" name="famousHomeForm.status" list="famousHomeStatusTypeMap" listKey="key" listValue="value" cssStyle="width:150px"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">专项：</th>
					    <td width="40%">
					    	<s:iterator value="specialMap" id="column">  
							  <input type="checkbox" name="special" value="<s:property value="key"/>"/><s:property value="value"/>&nbsp;&nbsp;&nbsp;&nbsp;
							</s:iterator>  
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">名人类型：</th>
					    <td width="40%">
					        <s:select id="type" name="famousHomeForm.type" list="famousHomeTypeMap" listKey="key" listValue="value" cssClass="easyui-validatebox" data-options="required:true" cssStyle="width:150px"/>
					    </td>
					</tr>

					<tr>
						<td class="bottom" align="center" colspan="4">
							<div align="center">
								<s:if test="%{actionName == 'famousHome_save'}">
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
$(function(){
	var specialids = "<s:property value="famousHomeForm.specialids"/>";
	var specialidsArray = specialids.split(",");
	for(var i=0;i<specialidsArray.length;i++){
     	$("input[name='special']").each(function(index, item){
        	 if ($(item).val() == $.trim(specialidsArray[i])){
        	 	$(item).attr("checked", true);
        	 }
        });
	}
});

var picType;
//添加图片
function openDialogUpload(labelId){
	picType = labelId;
	$("#uploadDialog").dialog('open').html("正在加载页面...").load(_contextPath + "/upload_uploadInit.do?mode=ajax&uploadContentType=pic&path=file");
}
function callbackpic(fileName, type) {
//	fileName = "/upload/" + fileName;
	$("#" + picType).val(fileName);
	$("#" + picType + "Img").prop("src", "${qnImageUrl}"+fileName);
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