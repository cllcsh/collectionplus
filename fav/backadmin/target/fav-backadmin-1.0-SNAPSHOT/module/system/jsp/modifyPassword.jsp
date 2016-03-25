<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>修改密码</title>
		<%@include file="/jsp/common.jsp"%>

		<script type="text/javascript">
<!-- 
//修改密码
function modify_password(){
	if(!$.formValidator.pageIsValid("1")){
		return;
	}
		
	var oldPassword = document.getElementById("oldPassword").value;
	var newPassword = document.getElementById("newPassword").value;
	//var newPassConfirm = document.getElementById("newPassConfirm").value;
	
	var params = {'mode':"ajax",'userForm.formerPassword':newPassword,'userForm.oldPassword':oldPassword};
    $.post("user_modifyPassword.do",params,function(json){
    	alert(json.message);
    	if(json.codeid == 0){
            //window.opener.window.closewin();
            window.location.reload();
        }
    },"json");
    
}
-->
</script>

	</head>
	<body style="overflow: hidden">
	<%@include file="/jsp/include/navigation.jsp"%>
		<s:fielderror />
		<s:form id="modifyPassForm" name="modifyPassForm">
			<table class="bg" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td>
						<table class="tb_add" width="100%" border="0" cellpadding="1"
							cellspacing="1">
							<tr>
								<td class="td_title" colspan="3" align="center">
									修改密码
								</td>
							</tr>
							<tr>
								<th class="tb_lable" width="25%" align="right">
									原密码：
									<font color="red">*</font>
								</th>
								<td width="35%">
									<s:password id="oldPassword" name="userForm.oldPassword"
										cssClass="input2" size="40"></s:password>
								</td>
								<td>
									<div id="oldPasswordTip"></div>
								</td>
							</tr>
							<tr>
								<th align="right">
									新密码：
									<font color="red">*</font>
								</th>
								<td>
									<s:password id="newPassword" name="userForm.formerPassword" cssClass="input2"
										size="40"></s:password>
								</td>
								<td>
									<div id="newPasswordTip"></div>
								</td>
							</tr>
							<tr>
								<th align="right">
									确认新密码：
									<font color="red">*</font>
								</th>
								<td>
									<s:password id="newPassConfirm" name="newPassConfirm"
										cssClass="input2" size="40"></s:password>
								</td>
								<td>
									<div id="newPassConfirmTip"></div>
								</td>
							</tr>

							<tr>
								<td class="bottom" align="center" colspan="3">
									<div align="center">
										<input type="button" onclick="javascript:modify_password();"
											class="button" onmouseout="this.className = 'button'"
											onmouseover="this.className = 'buttonOver'" value="确定" />
										<input type="reset" class="button"
											onmouseout="this.className = 'button'"
											onmouseover="this.className = 'buttonOver'" value="重置" />
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</s:form>

		<script type="text/javascript">
$.formValidator.initConfig({formid:"modifyPassForm",onerror:function(msg){alert(msg)},onsuccess:function(){return true;}});
$("#oldPassword").formValidator({onshow:"请输入原密码",onfocus:"请输入0-18个英文字母,数字或下划线",oncorrect:"格式正确"}).regexValidator({regexp:"^[A-Z|a-z|0-9|_]{0,18}$",onerror:"原密码必须由0-18个英文字母,数字或下划线组成"});
$("#newPassword").formValidator({onshow:"请输入新密码",onfocus:"请输入6-18个英文字母,数字或下划线",oncorrect:"格式正确"}).regexValidator({regexp:"^[A-Z|a-z|0-9|_]{6,18}$",onerror:"新密码必须由6-18个英文字母,数字或下划线组成"});
$("#newPassConfirm").formValidator({onshow:"请确认新密码",onfocus:"请输入6-18个英文字母,数字或下划线",oncorrect:"格式正确"}).regexValidator({regexp:"^[A-Z|a-z|0-9|_]{6,18}$",onerror:"确认密码必须由6-18个英文字母,数字或下划线组成"}).compareValidator( {
	desid :"newPassword",
	operateor :"=",
	onerror :"新密码和确认密码不一致，请重新输入！"
});
</script>

	</body>
</html>