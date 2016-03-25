<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/userPermissionMgr.js"></script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_add" width="100%"  border="0" cellpadding="1" cellspacing="1">
		<tr>
          <td class="td_title" colspan="2" align="center">用户权限信息</td>
        </tr>
         <tr>
          <th class="tb_lable" width="50%" align="right">登录名：</th>
          <td><s:property value="userPermissionForm.loginName" /></td>
        </tr>
        <tr>
          <th align="right">用户名：</th>
          <td><s:property value="userPermissionForm.userName" /></td>
        </tr>
		<tr>
          <th align="right">司法单位：</th>
          <td><s:property value="userPermissionForm.deptName" /></td>
        </tr>
		<tr>
          <th align="right">所属角色：</th>
          <td>
			 <s:iterator id="userRole" value="%{userRoleList}" status="sta">
                <LABEL title="<s:property value="#userRole.roleName"/>" ><s:property value="#userRole.roleName"/>&nbsp;&nbsp;</LABEL>
		     </s:iterator>
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