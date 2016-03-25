<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title><s:property value="jsp_head_title"/></title>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/modelsNumberMgr.js"></script>
<script type="text/javascript">
</script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<form id="setForm" action="">
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_query" border="0" cellspacing="1" cellpadding="1" align="center" >
		<s:hidden name="modelsNumberForm.id"></s:hidden>
		<tr>
			<th>热门车型数量
			</th>
			<td><s:textfield id="num" name="modelsNumberForm.num"></s:textfield>
			</td>
			<td>
				<input name="btn" id="btn_save" type="button" class="button" value="保存" onclick="saveModelsNumber();" />
			</td>
		</tr>
	</table>
</td></tr></table>
</form>
</body>
</html>
