<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title><s:property value="jsp_head_title"/></title>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/curiosityShopMgr.js"></script>
<script type="text/javascript" src="<%=path%>/resource/js/geo.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$(document).ict({
		queryForm:'queryForm',
		queryUrl:'curiosityShop_query.do',
		queryBtn:'btn_query',
		pagecontent:'pagecontent',
		addUrl:'curiosityShop_add.do',
		delsUrl:'curiosityShop_deletes.do',
		delsBtn:'btn_del'
	});
});
</script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<form id="queryForm" action="">
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_query" border="0" cellspacing="1" cellpadding="1" align="center" >
		<tr>
		    <th>名称</th>
		    <td><s:textfield size="25" id="name" name="curiosityShopForm.name" onblur="clearBlank(this);" maxlength="60"  /></td>
		    <th>地址</th>
		    <td><s:textfield size="25" id="address" name="curiosityShopForm.address" onblur="clearBlank(this);" maxlength="60"  /></td>
		</tr>
		<tr>
		    <th>电话</th>
		    <td><s:textfield size="25" id="phone" name="curiosityShopForm.phone" onblur="clearBlank(this);" maxlength="60"  /></td>
		    <th>简介</th>
		    <td><s:textfield size="25" id="introduction" name="curiosityShopForm.introduction" onblur="clearBlank(this);" maxlength="60"  /></td>
		</tr>
		<tr>
			<th>省市县</th>
		    <td>
		    	<select class="select" name="curiosityShopForm.province" id="s1">
				  <option></option>
				</select>
				<select class="select" name="curiosityShopForm.city" id="s2">
				  <option></option>
				</select>
				<select class="select" name="curiosityShopForm.county" id="s3">
				  <option></option>
				</select>
		    </td>
		    <th></th>
		    <td></td>
		</tr>
		<tr>
			<td class="bottom" colspan="4" align="center">
		   		<input name="btn" id="btn_query" type="button" class="button" value="查询" />
		   		<s:if test="%{userSession.userPermissions['/module/fav/curiosityShop_query.do'] != null}">
					<input name="btn" id="btn_query" type="button" class="button" value="查询" />
		   		</s:if>
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
			<s:if test="%{userSession.userPermissions['/module/fav/curiosityShop_add.do'] != null}">
				<input type="button" id="btn_add" class="button" value="添加"/>
			</s:if>
			<input type="button" id="btn_del" class="button" value="删除"/>
			<s:if test="%{userSession.userPermissions['/module/fav/curiosityShop_deletes.do'] != null}">
				<input type="button" id="btn_del" class="button" value="删除"/>
			</s:if>
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
</body>
<script type="text/javascript">
$(document).ready(function(){
	setup();// 查询条件的省市县初始化
	var province = '<s:property value="curiosityShopForm.province"/>';
	if (province != "") {
		preselect(province);
		var city = '<s:property value="curiosityShopForm.city"/>';
		if (city != "") {
			$("#s2").val(city);
			change(2);
			var area = '<s:property value="curiosityShopForm.county"/>';
			if (area != "") {
				$("#s3").val(area);
			}
		}
	}
});
</script>
</html>
