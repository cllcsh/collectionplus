<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/roleMgr.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path%>/resource/dhtmlxtree/dhtmlxtree_std.css" />
<script type="text/javascript" src="<%=path%>/resource/dhtmlxtree/dhtmlxtree_std.js"></script>
<script type="text/javascript">
function initTree(contain,roleId,mode){
			var tree=new dhtmlXTreeObject(contain,"100%","100%",0);

			tree.setSkin('dhx_skyblue');
			tree.setImagePath("<%=path%>/resource/dhtmlxtree/imgs/csh_scbrblue/");
			tree.enableCheckBoxes(1);
			tree.enableThreeStateCheckboxes(true);
			tree.loadXML("role_treeNodes.do?&roleId="+roleId,function(){
				$("#treeboxTd").removemask();
			});
			if(mode=="1"){
				tree.attachEvent("onBeforeCheck", function(id,state){
				});
			}	
			
			return tree;
	}
function resetAndQuery(){
	$("#roleForm")[0].reset();
	$("#btn_query").click();
}

</script>
</head>
<body onload="loadPage()">
<%@include file="/jsp/include/navigation.jsp"%>
<form id="roleForm" action="">
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_query" border="0" cellspacing="1" cellpadding="1" align="center" >
		<tr>
		<th width="10%">
			角色名称：
		</th>
		<td width="15%">
			<s:textfield size="20" id="roleName" name="roleInfoForm.roleName" maxlength="60"/>
		</td>
 		</tr>
		<tr>
		<td class="bottom" colspan="6" align="center">
			<input id="btn_query" name="btn" type="button" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" onclick="query();" value="查询" />
			<input name="btn" type="button" class="button" value="重置" onclick="resetAndQuery();" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'"/>
			<!--  
			<input name="btn" type="reset" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="重置" />-->
		</td>
		</tr>
	</table>
</td></tr></table>
</form>
<br/>
<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td align="right" style="padding-right: 20px">		
			<s:if test="%{userSession.userPermissions['/module/system/role_add.do'] != null}">
	    		<input type="button" id="btn_add_user" onclick="add_role();" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="添加"/>	
			</s:if>
			<s:if test="%{userSession.userPermissions['/module/system/role_delete.do'] != null}">
	    		<input type="button" onclick="javascript:del_roles('checkbox1');" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="删除"/>	
			</s:if>
		</td>
	</tr>
	<tr>
	<td>
	<div id="pagecontent"></div>
	</td>
	</tr>
</table>
<div id="dialog" title="角色管理"></div>
</body>
</html>