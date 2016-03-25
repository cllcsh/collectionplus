<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/userMgr.js"></script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_add" width="100%"  border="0" cellpadding="1" cellspacing="1">
		<tr>
			<th width="20%" style="text-align:right;">头像：</th>
			<td width="40%">
			<s:if test="%{userForm.imgPath != null && userForm.imgPath != ''}">
				<img id="imgPathImg" src="<s:property value='userForm.imgPath'/>" alt="点击编辑" style="cursor: pointer;" width="32" height="32" onclick="openDialogUpload('imgPath');"/>
			</s:if>
			<s:else>
				<img id="imgPathImg" src="images/ryxx02.gif" alt="点击上传" style="cursor: pointer;" width="32" height="32" onclick="openDialogUpload('imgPath');"/>
			</s:else>
			</td>
		</tr>
		<tr>
			<th width="20%" style="text-align:right;">登录名<font style="color: red;">*</font>：</th>
			<td width="40%"><s:property value="userForm.loginName"/></td>
		</tr>
		<tr>
			<th width="20%" style="text-align:right;">用户类型<font style="color: red;">*</font>：</th>
			<td width="40%"><s:select id="userType" name="userForm.userType" list="#{0:'系统管理员',1:'客服人员',2:'普通用户'}"></s:select></td>
		</tr>
		<tr>
			<th width="20%" style="text-align:right;">用户姓名<font style="color: red;">*</font>：</th>
			<td width="40%"><s:property value="userForm.name"/></td>
		</tr>
		<tr>
			<th width="20%" style="text-align:right;">身份证号：</th>
			<td width="40%"><s:property value="userForm.idCard"/></td>
		</tr>
		<tr>
			<th width="20%" style="text-align:right;">邮箱：</th>
			<td width="40%"><s:property value="userForm.email"/></td>
		</tr>
		<tr>
			<th width="20%" style="text-align:right;">公司名称：</th>
			<td width="40%"><s:property value="userForm.companyName"/></td>
		</tr>
		<tr>
			<th width="20%" style="text-align:right;">注册号：</th>
			<td width="40%"><s:property value="userForm.regNumber"/></td>
		</tr>
		<tr>
			<th width="20%" style="text-align:right;">省市县：</th>
			<td width="40%">
				<s:property value="userForm.province"/>
				<s:property value="userForm.city"/>
				<s:property value="userForm.area"/>
			</td>
		</tr>
		<tr>
			<th width="20%" style="text-align:right;">详细地址：</th>
			<td width="40%"><s:property value="userForm.address"/></td>
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
			</td>
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
			</td>
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
			</td>
		</tr>
        <tr>
          <td class="bottom" align="center" colspan="2">
			<input type="button" onclick="javascript:history.back();" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="返回"/>
          </td>
        </tr>
      </table></td>
    </tr>
  </table>

</body>
</html>