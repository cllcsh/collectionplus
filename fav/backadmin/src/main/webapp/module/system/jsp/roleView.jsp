<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/jsp/meta.jsp" %>
</head>
<body style="overflow:hidden"><s:fielderror/>
<s:form id="roleInfoForm" name="roleInfoForm">
<s:hidden id="roleId" name="roleInfoForm.roleId"></s:hidden>
<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
        <tr>
          <td class="td_title" colspan="6" align="center">查看角色信息</td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">角色名称：</th>
          <td width="50%">
          	<s:property value="roleInfoForm.roleName"/>
           <!-- <s:textfield name="roleInfoForm.roleName" id="roleInfoForm_roleName" cssClass="input2" size="40" readonly="true"></s:textfield>  --> 
          </td>
		  <td width="35%"><div id="roleInfoForm_roleNameTip"></div></td>
        </tr>
        <%-- <tr>
	        <td class="tb_lable" align="right">是否共享：</td>
	          <td>
	          	<s:if test="%{roleInfoForm.shareFlag == 1}">
	          		共享
	          	</s:if>
	          	<s:else>
	          		不共享
	          	</s:else>
	          </td>
			  <td><div id="roleInfoForm_departmentNameTip"></div></td>
		 </tr>--%>
		<tr>
        <th class="tb_lable" align="right">所在机构：</th>
          <td>
          	  <s:property value="roleInfoForm.departmentName"/>
          </td>
		  <td><div id="roleInfoForm_departmentNameTip"></div></td>
		 </tr>
<!--edit by weiwu-->        
        <tr>
        <th class="tb_lable" align="right">功能：</th>
          <td  id="treeboxTd">
          <div id="treeboxbox_tree" style="width:100%; height:218px;"></div>
            <%--<iframe height="200" width="100%" id="frameFunctionList" frameborder="0" scrolling="auto" src="role_toTree.do?disable=1"></iframe> --%>
          </td>
		  <td><div id="roleInfoForm_functionNameTip"></div></td>
		 </tr>
<!--edit by weiwu-->    

        <tr>
          <td class="bottom" align="center" colspan="3"><div align="center">
          
          <input type="hidden" id="functionValue" name="roleInfoForm.functionValue">
          
          <input type="button" onclick="javascript:closeDialog();" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="关闭"/>
			
          </div></td>
          </tr>
      </table></td>
    </tr>
  </table>
</s:form>
</body>
</html>
<script type="text/javascript">
function getRoleId() {
	return document.getElementById("roleId").value;
}
</script>