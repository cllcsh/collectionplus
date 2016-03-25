<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/userPermissionMgr.js"></script>
<script type="text/javascript">
	<s:if test="%{actionName == 'userPermission_save'}">
		$(document).ready(function(){
			$(document).ict({
				saveForm:'userPermissionForm',
			    saveUrl:'userPermission_save.do',
			    saveUrlTo:'userPermission_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:if>
	<s:else>
		$(document).ready(function(){
			$(document).ict({
				saveForm:'userPermissionForm',
			    saveUrl:'userPermission_update.do',
			    saveUrlTo:'userPermission_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:else>

	function findRoles(obj){
    	var deptId = obj.value;
    	var length = deptId.length;
    	if(length <8){
    		for(var i=0;i<8-length;i++){
    			deptId='0'+deptId;
    		}
    	}
    	//alert(deptId);

		var role= document.getElementById("role");
		role.innerHTML='';
		var htmlContent='';
		$.getJSON("userPermission_getRolesByDeptId.do?mode=ajaxJson&deptId="+deptId,function(data){	
			htmlContent="<div style='OVERFLOW-y:scroll;height=100'>";
			for(var i=0;i<data.length;i++){
				//alert(data[i].roleId);
				htmlContent = htmlContent+ "<input type='checkbox' id="+data[i].roleId+" value="+data[i].roleId+ " name="+'userPermissionForm.userRoles.roleId'+" />"
				+"<label>"+data[i].roleName+"</label></br>";
			}
			htmlContent=htmlContent +"</div>";
			//alert(htmlContent);
		   	role.innerHTML=htmlContent;
		});

    }
	/*
	$(document).ready(function(){
		//(event.keyCode < 48 || event.keyCode > 57)为只允许输入数字
		$("#userForm_userInfoForm_loginName").keypress(function(event) {   
	        if (!$.browser.mozilla) {
	            if (event.keyCode && event.keyCode == 32) {
	                // ie6,7,8,opera,chrome管用
	                event.preventDefault();
	            } else {
	            	$('#button').attr('disabled','true');
			    }
	        } else {
	            if (event.charCode && event.charCode == 32) {
	                // firefox管用
	                event.preventDefault();
	            } else {
	            	$('#button').attr('disabled','true');
			    }
	        }
	    });
	})*/
</script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<s:form id="userPermissionForm" name="userPermissionForm" action="%{actionName}">
	<s:hidden name="userPermissionForm.id"></s:hidden>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr>
						<td class="td_title" colspan="3" align="center">
							用户权限管理
						</td>
					</tr>
					<tr>
						<th class="tb_lable" width="20%" align="right">
							登录名：
							<font class="redStar">*</font>
						</th>
						<td width="35%">
							<s:if test="%{actionName == 'user_save'}">
								<s:textfield id="loginName" name="userPermissionForm.loginName" cssClass="input2" size="40" onkeypress="" onblur="clearBlank(this);"></s:textfield>
							</s:if>
							<s:else>
								<s:property value="userPermissionForm.loginName" />
							</s:else>
						</td>
						<td width="45%">
							<div id="loginNameTip"></div>
						</td>
					</tr>
					<tr>
						<th align="right">
							用户名：
							<font class="redStar">*</font>
						</th>
						<td>
							<s:textfield id="userName" name="userPermissionForm.userName" cssClass="input2" size="40" onblur="clearBlank(this);"></s:textfield>
						</td>
						<td>
							<div id="userNameTip"></div>
						</td>
					</tr>
					<tr>
						<th align="right">
							司法单位：
						</th>
						<td>
							<s:select id="deptId" name="userPermissionForm.deptId" list="departmentList"
										listKey="key" listValue="value"
										cssClass="input2" onchange="findRoles(this);"></s:select>
						</td>
						<td>
							<div id="deptIdTip"></div>
						</td>
					</tr>
					<tr>
						<th align="right">
							所属角色：
						</th>
						<td id="role" >
							<ul
								style="PADDING-RIGHT: 0px; PADDING-LEFT: 0px; BACKGROUND: #fff; PADDING-BOTTOM: 0px; MARGIN: 0px; OVERFLOW: auto; WIDTH: 100%; PADDING-TOP: 0px; HEIGHT: 90px">
								<s:iterator id="roleBean" value="%{roleList}" status="sta">
									<LI
										style="OVERFLOW: hidden; WIDTH: 162px; WHITE-SPACE: nowrap; TEXT-OVERFLOW: ellipsis">
										<input id="<s:property value="#roleBean.roleId"/>"
											type="checkbox"
											value="<s:property value="#roleBean.roleId"/>"
											name="userPermissionForm.userRoles.roleId"
											<s:if test="%{#roleBean.roleId in roleIdList}">checked</s:if> />
										<LABEL title="<s:property value="#roleBean.roleName"/>">
											<s:property value="#roleBean.roleName" />
										</LABEL>
									</LI>
								</s:iterator>
							</ul>
						</td>
						<td>
							<div id=""></div>
						</td>
					</tr>
					
					<tr>
						<td class="bottom" align="center" colspan="4">
							<div align="center">
								<s:if test="%{actionName == 'userPermission_save'}">
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

<script type="text/javascript">
$.formValidator.initConfig( {
	formid :"userPermissionForm",
	onerror : function(msg) {
		alert(msg);
	},
	onsuccess : function() {
		return true;
	}
});
<%--$("#role").addmask();--%>
$("#userName").formValidator({
	onshow:"请输入用户名",
    onfocus:"请输入用户名最多20个字符",
    oncorrect:"输入合法"
}).inputValidator({
	min:1,
	max:20,
	onerror:"用户名20个字符"
});

</script>

</body>
</html>