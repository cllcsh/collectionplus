<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>
<%@taglib prefix="ict" uri="/ict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title><s:property value="jsp_head_title"/></title>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	$(document).ict({
		formid:'productForm',
		queryForm:'productForm',
		queryUrl:'orderProduct_query.do',
		queryBtn:'btn_query',
		pagecontent:'pagecontento'
	});
});
</script>
</head>

<body>
<form id="productForm" name="productForm">
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_query" border="0" cellspacing="1" cellpadding="1" align="center" >
		<tr>
			<th> 
				产品名称： 
			</th>
			<td>
				<s:textfield size="20" id="productName" cssClass="td03"
										name="productForm.productName" maxlength="60"
										onblur="clearBlank(this);" />
				
			</td>
		<!--  	<th>
				产品类型：
			</th>
			<td>
				<s:select list="productTypeList" listKey="id" listValue="typeName"
										id="productTypeId" value="productForm.typeId"
										name="productForm.typeId">
									</s:select>
                </td>
                -->
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
				<input type="button" onclick="javascript:closeDialog();" class="button" value="添加"/>
<%--				<input type="button" onclick="javascript:del_infoToPersons('checkbox1');" class="button" value="删除"/>--%>
		</td>
	</tr>
	<tr>
	<td>
	<div id="pagecontento"></div>
	</td>
	</tr>
</table>
<script type="text/javascript">
		
</script>
</body>

</html>
