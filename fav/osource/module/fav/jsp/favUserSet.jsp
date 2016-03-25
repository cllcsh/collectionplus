<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/favUserMgr.js"></script>
<script type="text/javascript">
	<s:if test="%{actionName == 'favUser_save'}">
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'favUser_save.do',
			    saveUrlTo:'favUser_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:if>
	<s:else>
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'favUser_update.do',
			    saveUrlTo:'favUser_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:else>
</script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<s:form id="setForm" name="favUserForm" action="%{actionName}">
	<input type="hidden" name="favUserForm.id" id="favUserId" value="<s:property value="favUserForm.id"/>">
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr>
					    <th width="20%" style="text-align:right;">名称：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true" name="favUserForm.userName" value="<s:property value="favUserForm.userName"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">账号：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true,validType:['mobile','checkAccount[\'favUserId\']']" name="favUserForm.account" value="<s:property value="favUserForm.account"/>"/>
					    </td>
					</tr>
					<s:if test="%{actionName == 'favUser_save'}">
						<tr>
					    <th width="20%" style="text-align:right;">密码：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true" name="favUserForm.password" value="<s:property value="favUserForm.password"/>"/>
					    </td>
					</tr>
					</s:if>
					<tr>
					    <th width="20%" style="text-align:right;">手机号：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true,validType:'mobile'" name="favUserForm.phone" value="<s:property value="favUserForm.phone"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">热度：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true,validType:'int'" name="favUserForm.heat" value="<s:property value="favUserForm.heat"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">个性签名：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true" name="favUserForm.signature" value="<s:property value="favUserForm.signature"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">用户等级：</th>
					    <td width="40%">
					    	<s:select id="userLevel" name="favUserForm.userLevel" list="levelMap" listKey="key" listValue="value" cssClass="easyui-validatebox" data-options="required:true" cssStyle="width:150px"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">用户称号：</th>
					    <td width="40%">
					    	<s:iterator value="userTitleImgMap" id="column">  
							  <input type="checkbox" name="userTitle" value="<s:property value="key"/>" style="width:20px;height:20px;"/>&nbsp;&nbsp;<img src="<s:property value="value"/>"  style="width:50px;height:50px;" />&nbsp;&nbsp;&nbsp;&nbsp;
							</s:iterator> 
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">个人头像：</th>
					    <td width="40%">
					    	<s:if test="%{favUserForm.avatar != null && favUserForm.avatar != ''}">
								<img id="imgPathImg" src="<s:property value='favUserForm.avatar'/>" alt="点击编辑" style="cursor: pointer;" width="32" height="32" onclick="openDialogUpload('imgPath');"/>
							</s:if>
							<s:else>
								<img id="imgPathImg" src="images/ryxx02.gif" alt="点击上传" style="cursor: pointer;" width="32" height="32" onclick="openDialogUpload('imgPath');"/>
							</s:else>
					        <input type="hidden" id="imgPath" name="favUserForm.avatar" value="<s:property value="favUserForm.avatar"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">更换头像时间：</th>
					    <td width="40%">
					    	<input id="upateAvatarTime" name="favUserForm.upateAvatarTime" value="<s:date name="favUserForm.upateAvatarTime" format="yyyy-MM-dd HH:mm:ss" />" type="text" cssStyle="width:150px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" />
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">描述：</th>
					    <td width="40%">
					        <input type="text" name="favUserForm.description" value="<s:property value="favUserForm.description"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">用户当前积分：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true" name="favUserForm.userPoints" value="<s:property value="favUserForm.userPoints"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">用户累计积分：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true" name="favUserForm.userAllPoints" value="<s:property value="favUserForm.userAllPoints"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">经验值：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true" name="favUserForm.experience" value="<s:property value="favUserForm.experience"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">个人私信设置：</th>
					    <td width="40%">
					    	<s:select id="personalMsgSet" name="favUserForm.personalMsgSet" list="personalMsgSetMap" listKey="key" listValue="value" cssClass="easyui-validatebox" data-options="required:true" cssStyle="width:150px"/>
					    </td>
					</tr>
					<tr>
						<td class="bottom" align="center" colspan="4">
							<div align="center">
								<s:if test="%{actionName == 'favUser_save'}">
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
	var userTitles = "<s:property value="favUserForm.userTitle"/>";
	var userTitlesArray = userTitles.split(",");
	for(var i=0;i<userTitlesArray.length;i++){
     	$("input[name='userTitle']").each(function(index, item){
        	 if ($(item).val() == $.trim(userTitlesArray[i])){
        	 	$(item).attr("checked", true);
        	 }
        });
	}
});
$.formValidator.initConfig({
	formid:"setForm",
	onerror:function(msg){
	alert(msg)},
	onsuccess:function(){
		return true;
}});

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
</script>

</body>
</html>