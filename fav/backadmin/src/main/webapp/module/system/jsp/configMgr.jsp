<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <%@include file="/jsp/common.jsp" %>
    <script type="text/javascript" src="js/configMgr.js"></script>
    <script type="text/javascript">
    </script>
</head>
<body onload="query()">
<%@include file="/jsp/include/navigation.jsp"%>
<form id="configForm">
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_query" border="0" cellspacing="1" cellpadding="1" align="center" >
		<tr>
			<th>
				配置键:
			</th>
			<td>
				<s:textfield size="20" id="config_Key" name="configInfoForm.configKey"/>
			</td>
		</tr>
		<tr>
			<td class="bottom" colspan="4" align="center">
			<input name="btn" type="button" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" onclick="query();" value="查询" />
			<input name="btn" type="reset" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="重置" />
			</td>
		</tr>
	</table>
</td></tr></table>
</form>
<br/>
<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td align="right" style="padding-right: 20px">
			<input type="button" id="btn_add_user" onclick="add_config();" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="添加"/>
			<input type="button" onclick="javascript:del_configs('checkbox1');" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="删除"/>
			<%--<input type="button" onclick="javascript:edit_checked_config('checkbox1');" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="编辑"/>
		--%></td>
	</tr>
	<tr>
		<td>
		 	<div id="pagecontent"></div>
		</td>
	</tr>
</table>
<div id="dialog" title="配置管理"></div>
</body>
</html>