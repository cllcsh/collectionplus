<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title><s:property value="jsp_head_title"/></title>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/productTypeMgr.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$(document).ict({
		queryForm:'queryForm',
		queryUrl:'productType_query.do',
		queryBtn:'btn_query',
		pagecontent:'pagecontent',
		addUrl:'productType_add.do',
		delsUrl:'productType_deletes.do',
		delsBtn:'btn_del'
	});
});
</script>
</head>

<body>
<form id="queryForm" action="">
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_query" border="0" cellspacing="1" cellpadding="1" align="center" >
		<tr>
			<th>产品类型名称
			</th>
			<td>
			<s:textfield size="20" id="typeName" cssClass="td03"
										name="productTypeForm.typeName" maxlength="60"
										onblur="clearBlank(this);" />
			</td>
 			<th>
			</th>
			<td>
			</td>
		</tr>
		<!--
			在此填写查询字段
		-->
		<tr>
			<td class="bottom" colspan="4" align="center">
		   		<s:if test="%{userSession.userPermissions['/module/skyshare/productType_query.do'] != null}">
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

			<s:if test="%{userSession.userPermissions['/module/skyshare/productType_add.do'] != null}">
				<input type="button" id="btn_add" class="button" value="添加"/>
			</s:if>
			
			<s:if test="%{userSession.userPermissions['/module/skyshare/productType_deletes.do'] != null}">
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
</html>
