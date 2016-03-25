<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/jsp/meta.jsp" %>
<script type="text/javascript">
</script>
</head>
<body style="overflow:hidden"><s:fielderror/>
<s:form id="roleInfoForm" name="roleInfoForm" action="%{actionName}" onsubmit="return false">
<s:hidden id="roleId" name="roleInfoForm.roleId"></s:hidden>
<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
        <tr>
          <td class="td_title" colspan="6" align="center">角色管理</td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">角色名称：</th>
          <td>
            <s:textfield name="roleInfoForm.roleName" id="roleInfoForm_roleName" cssClass="input2" size="40" onblur="clearBlank(this)"></s:textfield>
            <font class="redStar">*</font>
          </td>
		  <td width="40%"><div id="roleInfoForm_roleNameTip"></div></td>
        </tr>
        <!--
        <tr>
          <th class="tb_lable" align="right">是否共享：</th>
          <td>
         	<s:select id="shareFlag" name="roleInfoForm.shareFlag" list="%{#{'1':'共享','0':'不共享'}}" cssClass="input2" cssStyle="width:120px" onchange="selectShareFlag();"></s:select>
          </td>
		  <td><div id="shareFlagTip"></div></td>
        </tr> 
 		-->
 		<tr>
          <th class="tb_lable" align="right">机构：</th>
          <td>
          	<s:select id="departmentId" name="roleInfoForm.departmentId" list="departmentList" listKey="key" listValue="value" cssClass="input2"  cssStyle="width:120px"></s:select>
          	<s:hidden id="tempDepartmentId" name="tempDepartmentId"></s:hidden>
          </td>
		  <td><div id="departmentIdTip"></div></td>
        </tr>     
        <tr>
        <th class="tb_lable" align="right">功能选择：</th>
          <td  id="treeboxTd">
          <div id="treeboxbox_tree" style="width:100%; height:218px;"></div>
          <!-- 
            <iframe height="200" width="100%" id="frameFunctionList" frameborder="0" scrolling="auto" src="role_toTree.do"></iframe>
             -->
          </td>
		  <td><div id="roleInfoForm_functionNameTip"></div></td>
		 </tr>
   

        <tr>
          <td class="bottom" align="center" colspan="3"><div align="center">          
          <input type="hidden" id="functionValue" name="roleInfoForm.functionValue">         
			<s:if test="%{actionName == 'role_save'}">
				<input type="button" onclick="javascript:save_role(this);" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="增加"/>
			</s:if><s:else>
				<input type="button" onclick="javascript:update_role(this);" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="保存"/>
			</s:else>
			
          </div></td>
          </tr>
      </table></td>
    </tr>
  </table>
</s:form>
<script type="text/javascript">


function getRoleId() {
	return document.getElementById("roleId").value;
}
function setFunctionValue()
{
	$('#functionValue').val(frames["frameFunctionList"].getCheckedValue());
}
$.formValidator.initConfig({
	formid:"roleInfoForm",
	onerror:function(msg){
	alert(msg)},
	onsuccess:function(){
		return true;
	}});
$("#roleInfoForm_roleName").formValidator({
	onshow:"请输入角色名称",
	onfocus:"不能为空，且最多输入30个字符或10个汉字",
	oncorrect:"输入合法"
	}).inputValidator({
		min:1,
		max:30,
		onerror:"请确认角色名称长度"
	}).regexValidator({
		regexp:"^[\u4E00-\u9FA5|a-z|0-9]{1,30}$",onerror :"只能输入中文、数字和字母的组合"});

</script>
<script type="text/javascript">

$(function(){
	//selectShareFlag();
})

function selectShareFlag()
{
	var shareFlag = $("#shareFlag").val();

	if(shareFlag == 1)
	{
		$("#departmentId").val('1');
		$("#departmentId").attr("disabled","disabled"); 
		$("#tempDepartmentId").val('1');
	}
	else
	{
		$("#departmentId").removeAttr("disabled"); 
	}
}
</script>
</body>
</html>