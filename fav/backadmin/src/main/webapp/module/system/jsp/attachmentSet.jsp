<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/attachmentMgr.js"></script>
<script type="text/javascript">
function up_load()
{
	if (!$.formValidator.pageIsValid("1")) {
		 return;
	}
	var attachFile = document.getElementById("attachFile").value;
	
	if(attachFile == null || attachFile == "")
	{
		alert("请选择相关的文件");
		return;
	}
	else
	{
		document.getElementById("save_button").disable = "disabled";
		document.getElementById("pictureForm").submit();
	}
}

function up_load_edit()
{	
	if (!$.formValidator.pageIsValid("1")) {
		 return;
	}
	
	var attachFile = document.getElementById("attachFile").value;

	if(attachFile == null||attachFile == "")
	{
		alert("请选择相关的文件");
		return;
	}
	else
	{
		document.getElementById("save_button").disabled = "disabled";
		document.pictureForm.submit();
	}
}
function success(fileName)
{
	alert("上传成功");
	if(actionName.value == "attachment_save")
		save_attachment(fileName);
	else
		update_attachment(fileName);
}
</script>
</head>

<body>
<s:hidden id="actionName" name="actionName"></s:hidden>
<%@include file="/jsp/include/navigation.jsp"%>
<s:form id="attachmentForm" name="attachmentForm" action="%{actionName}">
	<s:hidden name="attachmentForm.id"></s:hidden>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr>
			          <td class="td_title" colspan="6" align="center">通知下载</td>
			        </tr>
			        <tr>
			          <th class="tb_lable" width="15%" align="right">文件名：</th>
			          <td >
			            <s:textfield id="attachmentName" name="attachmentForm.name"  cssClass="input2" size="40" onblur="clearBlank(this)"></s:textfield>
			            <font class="redStar">*</font>
			          </td>
					  <td width="40%"><div id="attachmentNameTip"></div></td>
			        </tr>
	         		<tr>
			          <th class="tb_lable" align="right">所属单位：</th>
			          <td>
			          	<s:select id="departmentId" name="attachmentForm.departmentId" list="departmentList" listKey="key" listValue="value" cssClass="input2"  cssStyle="width:120px"></s:select>
			          	<s:hidden id="hDepartmentId" name="hDepartmentId"></s:hidden>
			          	<font class="redStar">*</font>
			          </td>
					  <td><div id="departmentIdTip"></div></td>
			        </tr>
			        <tr>
			          <th class="tb_lable" width="15%" align="right">文件描述：</th>
			          <td >
			            <s:textfield id="description" name="attachmentForm.description"  cssClass="input2" size="40" onblur="clearBlank(this)"></s:textfield>
			          </td>
					  <td width="40%"><div id="descriptionTip"></div></td>
			        </tr>	
</s:form>			        		        
			        <tr>
					<form id="pictureForm" name="pictureForm" action="<%=request.getContextPath()%>/module/archives/uploads_fileUpload.do?path=attachment" method="post" enctype="multipart/form-data" target="aaa">
				          <th width="20%" align="right">上传附件：</th>
				          <td>
				          <input id="attachFile" type="file" name="upload" style="width: 350px"/>
				          	<font class="redStar">*</font>
				          </td>
				          <td>
							<div id="attachFileTip"></div>
						  </td>
					</form>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td class="bottom" align="center" colspan="4">
					<div align="center">
						<s:if test="%{actionName == 'attachment_save'}">
							<input type="button" id="save_button" class="button" value="增加" onclick="up_load();"/>
						</s:if>
						<s:else>
							<input type="button" id="save_button" class="button" value="保存" onclick="up_load_edit();"/>
						</s:else>
						<input type="button" onclick="javascript:history.back();" class="button" value="返回"/>
					</div>
				</td>
			</tr>
	</table>

<iframe name="aaa" style="display:none"></iframe>
<script type="text/javascript">
$.formValidator.initConfig({
	formid:"attachmentForm",
	onerror:function(msg){
	alert(msg)},
	onsuccess:function(){
		return true;
	}});
$("#attachmentName").formValidator({
	onshow:"请输入文件名称",
	onfocus:"不能为空，且最多输入30个字符或10个汉字",
	oncorrect:"输入合法"
	}).inputValidator({
		min:1,
		max:30,
		onerror:"请确认文件名称长度"
	}).regexValidator({
		regexp:"^[\u4E00-\u9FA5|a-z|0-9]{1,30}$",onerror :"只能输入中文、数字和字母的组合"});

</script>
</body>
</html>