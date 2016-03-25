<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/userMgr.js"></script>
<script type="text/javascript" src="<%=path%>/resource/js/geo.js"></script>
<script type="text/javascript">
	<s:if test="%{actionName == 'user_save'}">
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'user_save.do',
			    saveUrlTo:'user_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:if>
	<s:else>
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'user_update.do',
			    saveUrlTo:'user_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:else>
</script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<s:form id="setForm" name="userForm" action="%{actionName}">
	<s:hidden name="userForm.id"></s:hidden>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr>
						<th width="20%" style="text-align:right;">头像：</th>
						<td width="40%">
						<s:if test="%{userForm.imgPath != null && userForm.imgPath != ''}">
							<img id="imgPathImg" src="<s:property value='userForm.imgPath'/>" alt="点击编辑" style="cursor: pointer;" width="32" height="32" onclick="openDialogUpload('imgPath');"/>
						</s:if>
						<s:else>
							<img id="imgPathImg" src="images/ryxx02.gif" alt="点击上传" style="cursor: pointer;" width="32" height="32" onclick="openDialogUpload('imgPath');"/>
						</s:else>
						<s:hidden id="imgPath" name="userForm.imgPath"></s:hidden>
						</td>
						<td width="40%">&nbsp;</td>
					</tr>
					<s:if test="%{actionName == 'user_save'}">
					<tr>
						<th width="20%" style="text-align:right;">登录名<font style="color: red;">*</font>：</th>
						<td width="40%"><s:textfield id="loginName" name="userForm.loginName"></s:textfield></td>
						<td width="40%"><div id="loginNameTip"></div></td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">密码<font style="color: red;">*</font>：</th>
						<td width="40%"><s:password id="password" name="userForm.password"></s:password></td>
						<td width="40%"><div id="passwordTip"></div></td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">确认密码<font style="color: red;">*</font>：</th>
						<td width="40%"><s:password id="confirmPassword"></s:password></td>
						<td width="40%"><div id="confirmPasswordTip"></div></td>
					</tr>
					</s:if>
					<s:else>
					<tr>
						<th width="20%" style="text-align:right;">登录名<font style="color: red;">*</font>：</th>
						<td width="40%"><s:property value="userForm.loginName"/></td>
						<td width="40%"><div id="loginNameTip"></div></td>
					</tr>
					<s:hidden name="userForm.loginName"></s:hidden>
					<s:hidden name="userForm.password"></s:hidden>
					<s:hidden name="userForm.formerPassword"></s:hidden>
					</s:else>
					<tr>
						<th width="20%" style="text-align:right;">用户类型<font style="color: red;">*</font>：</th>
						<td width="40%"><s:select id="userType" name="userForm.userType" list="#{0:'系统管理员',1:'客服人员',2:'普通用户'}"></s:select></td>
						<td width="40%"><div id="userTypeTip"></div></td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">用户姓名<font style="color: red;">*</font>：</th>
						<td width="40%"><s:textfield id="name" name="userForm.name"></s:textfield></td>
						<td width="40%"><div id="nameTip"></div></td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">身份证号：</th>
						<td width="40%"><s:textfield id="idCard" name="userForm.idCard"></s:textfield></td>
						<td width="40%"><div id="idCardTip"></div></td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">邮箱：</th>
						<td width="40%"><s:textfield id="email" name="userForm.email"></s:textfield></td>
						<td width="40%"><div id="emailTip"></div></td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">公司名称：</th>
						<td width="40%"><s:textfield id="companyName" name="userForm.companyName"></s:textfield></td>
						<td width="40%"><div id="companyNameTip"></div></td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">注册号：</th>
						<td width="40%"><s:textfield id="regNumber" name="userForm.regNumber"></s:textfield></td>
						<td width="40%"><div id="regNumberTip"></div></td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">省市县：</th>
						<td width="40%">
							<select class="select" name="userForm.province" id="s1">
							  <option></option>
							</select>
							<select class="select" name="userForm.city" id="s2">
							  <option></option>
							</select>
							<select class="select" name="userForm.area" id="s3">
							  <option></option>
							</select>
						</td>
						<td width="40%"><div id="provinceTip"></div></td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">详细地址：</th>
						<td width="40%"><s:textfield id="address" name="userForm.address"></s:textfield></td>
						<td width="40%"><div id="addressTip"></div></td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">身份证正面：</th>
						<td width="40%">
						<s:if test="%{userForm.idcardbPicPath != null && userForm.idcardbPicPath != ''}">
							<img id="idcardbPicPathImg" src="<s:property value='userForm.idcardbPicPath'/>" alt="点击编辑" style="cursor: pointer;" width="32" height="32" onclick="openDialogUpload('idcardbPicPath');"/>
						</s:if>
						<s:else>
							<img id="idcardbPicPathImg" src="images/ryxx02.gif" alt="点击上传" style="cursor: pointer;" width="32" height="32" onclick="openDialogUpload('idcardbPicPath');"/>
						</s:else>
						<s:hidden id="idcardbPicPath" name="userForm.idcardbPicPath"></s:hidden>
						</td>
						<td width="40%">&nbsp;</td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">身份证反面：</th>
						<td width="40%">
						<s:if test="%{userForm.idcardfPicPath != null && userForm.idcardfPicPath != ''}">
							<img id="idcardfPicPathImg" src="<s:property value='userForm.idcardfPicPath'/>" alt="点击编辑" style="cursor: pointer;" width="32" height="32" onclick="openDialogUpload('idcardfPicPath');"/>
						</s:if>
						<s:else>
							<img id="idcardfPicPathImg" src="images/ryxx02.gif" alt="点击上传" style="cursor: pointer;" width="32" height="32" onclick="openDialogUpload('idcardfPicPath');"/>
						</s:else>
						<s:hidden id="idcardfPicPath" name="userForm.idcardfPicPath"></s:hidden>
						</td>
						<td width="40%">&nbsp;</td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">营业执照：</th>
						<td width="40%">
						<s:if test="%{userForm.businessPicPath != null && userForm.businessPicPath != ''}">
							<img id="businessPicPathImg" src="<s:property value='userForm.businessPicPath'/>" alt="点击编辑" style="cursor: pointer;" width="32" height="32" onclick="openDialogUpload('businessPicPath');"/>
						</s:if>
						<s:else>
							<img id="businessPicPathImg" src="images/ryxx02.gif" alt="点击上传" style="cursor: pointer;" width="32" height="32" onclick="openDialogUpload('businessPicPath');"/>
						</s:else>
						<s:hidden id="businessPicPath" name="userForm.businessPicPath"></s:hidden>
						</td>
						<td width="40%">&nbsp;</td>
					</tr>
					<tr>
						<td class="bottom" align="center" colspan="4">
							<div align="center">
								<s:if test="%{actionName == 'user_save'}">
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
$("#loginName").formValidator( {
	empty :false,
	onshow :"请输入登录名",
	onfocus :"登录名长度0-20个字符",
	oncorrect :"输入合法",
	forcevalid :false
})
.inputValidator( {
	min :1,
	max :20,
	onerror :"你输入的登录名长度非法，请确认"
});
$("#name").formValidator( {
	empty :false,
	onshow :"请输入用户姓名",
	onfocus :"用户姓名长度1-20个字符",
	oncorrect :"输入合法",
	forcevalid :false
})
.inputValidator( {
	min :1,
	max :20,
	onerror :"你输入的用户姓名长度非法，请确认"
});
$("#password").formValidator({
	onshow:"请输入密码",
	onfocus:"至少6个长度",
	oncorrect:"密码合法"
})
.inputValidator({
	min:6,
	empty:{leftempty:false,rightempty:false,emptyerror:"密码两边不能有空符号"},
	onerrorMin:"密码至少6位",
	onerror:"密码不能为空,请确认"
});
$("#confirmPassword").formValidator({
	onshow:"请再次输入密码",
	onfocus:"至少6个长度",
	oncorrect:"密码一致"
})
.inputValidator({
	min:6,
	empty:{leftempty:false,rightempty:false,emptyerror:"重复密码两边不能有空符号"},
	onerror:"重复密码不能为空,请确认"
})
.compareValidator({
	desid:"password",
	operateor:"=",
	onerror:"2次密码不一致,请确认"
});
$("#email").formValidator( {
	empty :true,
	onshow :"请输入邮箱",
	onfocus :"邮箱长度0-60个字符",
	oncorrect :"输入合法",
	forcevalid :false
})
.inputValidator( {
	min :0,
	max :60,
	onerror :"你输入的邮箱长度非法，请确认"
})
.regexValidator( {
	regexp:"email",
	datatype:"enum",
	onerror:"E-mail格式不正确"
});
$(document).ready(function(){
	setup();// 查询条件的省市县初始化
	var province = '<s:property value="userForm.province"/>';
	if (province != "") {
		preselect(province);
		var city = '<s:property value="userForm.city"/>';
		if (city != "") {
			$("#s2").val(city);
			change(2);
			var area = '<s:property value="userForm.area"/>';
			if (area != "") {
				$("#s3").val(area);
			}
		}
	}
});

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