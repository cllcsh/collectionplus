<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>角色列表</title>
<%@include file="/jsp/common.jsp"%>
</head>
<body>
<%@include file="/jsp/include/pageInc.jsp"%> 
<s:else>
	<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_result" border="0" cellspacing="1" cellpadding="1" align="center">
      <tr class="tr_head" align="center">
		<td width="10%"><input id="checkboxtop"	type="checkbox" class="ckboxAll" title="选中/取消选中" value="" onclick="checkAll('checkboxtop','ckboxItem');" /></td>
        <td width="40%">角色名称</td>
		<!-- <td width="40%">所属机构</td> -->
   		<td width="10%">操作</td>
      </tr>
      <s:iterator id="roleBean" value="%{pageList.results}" status="sta">
      <tr align="center" class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
		<td><input name="checkbox1" type="checkbox" class="ckboxItem" value="<s:property value="#roleBean.roleId"/>" onclick="check('checkboxtop','ckboxItem');" /></td>
        <td align="center">
        	<a href="javascript:view_role(<s:property value='#roleBean.roleId'/>)"><s:property value="#roleBean.roleName"/></a>
        </td>
		<!-- <td align="center"><s:property value="#roleBean.departmentName"/></td>  -->
        <td align="center">
        <s:if test="%{userSession.userPermissions['/module/system/role_edit.do'] != null}">
        	<s:if test="%{#roleBean.roleId != 1}">
   		    	<a href="javascript:edit_role(<s:property value='#roleBean.roleId'/>)" ><s:text name="ictmap.modify"/></a>&nbsp;&nbsp;
   		    </s:if>
		</s:if>
		</td>
      </tr>
      </s:iterator>
    </table>
	</td></tr></table>
</s:else>
</body>
</html>