<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title><s:property value="jsp_head_title"/></title>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/userMgr.js"></script>
<script type="text/javascript" src="<%=path%>/resource/js/geo.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$(document).ict({
		queryForm:'queryForm',
		queryUrl:'user_query.do',
		queryBtn:'btn_query',
		pagecontent:'pagecontent',
		addUrl:'user_add.do',
		delsUrl:'user_deletes.do',
		delsBtn:'btn_del'
	});

	setup();// 查询条件的省市县初始化
});
</script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<form id="queryForm" action="">
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_query" border="0" cellspacing="1" cellpadding="1" align="center" >
		<tr>
			<th width="15%">登录名</th>
			<td width="35%"><s:textfield name="userForm.loginName"></s:textfield></td>
 			<th width="15%">用户名</th>
			<td width="35%"><s:textfield name="userForm.name"></s:textfield></td>
		</tr>
		<tr>
			<th>公司名</th>
			<td><s:textfield name="userForm.companyName"></s:textfield></td>
 			<th>省市县</th>
			<td>
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
		</tr>
		<tr>
			<th>用户类型</th>
			<td><s:select name="userForm.userType" list="#{'':'所有',0:'系统管理员',1:'客服人员',2:'普通用户'}"></s:select></td>
 			<th>用户状态</th>
			<td><s:select name="userForm.approveFlag" list="#{'':'所有',0:'审核中',1:'审核通过',2:'未通过',3:'已冻结'}"></s:select></td>
		</tr>
		<tr>
			<td class="bottom" colspan="4" align="center">
		   		<input name="btn" id="btn_query" type="button" class="button" value="查询" />
				<input name="btn" type="reset" class="button" value="重置" />
			</td>
		</tr>
	</table>
</td></tr></table>
</form>
<br/>
<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td align="right" style="padding-right: 20px">
			<input type="button" id="btn_add" class="button" value="添加"/>
			<input type="button" id="btn_del" class="button" value="删除"/>
		</td>
	</tr>
	<tr>
		<td>
			<form id="listForm" action="">
				<div id="pagecontent"></div>
			</form>
		</td>
	</tr>
</table>
<div id="approveDialog" title="车源审核">
	<form id="approveForm" action="">
	<input type="hidden" id="userid"/>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tbody><tr><td>
		<table class="tb_query" border="0" cellspacing="1" cellpadding="1" align="center">
			<tbody>		
			<tr height="40">
				<th>审核:</th>
				<td><select name="approveForm.approveType" id="approveType">
						<option value="1">审核通过</option>
						<option value="2">未通过</option>
						<option value="3">冻结</option>
					</select>
				</td>
			</tr>
			<tr height="40">
				<th>原因:</th>
				<td>
				<s:textarea  name="reason" id="reason" cols="30" rows="8" tooltipDelay="300" tooltip="hi" label="备注" javascriptTooltip="true"></s:textarea>
				</td>
			</tr>
			<tr height="40">
				</br>
				<td class="bottom" colspan="4" align="center">
					<input name="btn" type="button" class="button" onclick="javascript:userApprove();" value="确定" alt="">
				</td>
			</tr>
		</tbody></table>
	</td></tr></tbody></table>
	</form>
</div>
</body>
</html>
