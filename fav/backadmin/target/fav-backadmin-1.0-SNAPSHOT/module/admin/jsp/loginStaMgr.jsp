<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/loginStaMgr.js"></script>
<title><s:property value="jsp_head_title"/></title>
<script type="text/javascript">

$(document).ready(function(){
	$(document).ict({formid:'loginStaForm',queryUrl:'loginSta_query.do',queryBtn:'btn_query',pagecontent:'pagecontent'});
});
</script>
</head>
<body >
<%@include file="/jsp/include/navigation.jsp"%>
	<form action=""  id="loginStaForm" onsubmit="return false" >
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_query" border="0" cellspacing="1" cellpadding="1" align="center" >
		<tr>
		<th>
			帐号：
		</th>
		<td >
			<s:textfield size="25" id="loginName" name="loginStaForm.loginName" onblur="clearBlank(this);" maxlength="60"  />
		</td>
		<th>
			IP：
		</th>
		<td >
			<s:textfield size="25" id="loginIp" cssClass="td03" name="loginStaForm.loginIp" onblur="clearBlank(this);" />
		</td>
		<th>
			状态：
		</th>
		<td>
		 <dict:select name="loginStaForm.loginResult" id="loginResult"  codeType="ts_login_log-login_result" emptyOption="true"> </dict:select>
		</td>
        </tr>
        
		<tr>
        <th>
			开始时间：
		</th>
		<td>
		    <input id="loginFirDate" name="loginStaForm.loginFirDate" type="text" size="25" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'loginEndDate\')}'})" class="Wdate" />
        </td>
		<th>
			结束时间：
		</th>
		<td>
			 <input id="loginEndDate" name="loginStaForm.loginEndDate" size="25" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'loginFirDate\')}'})" class="Wdate" />
        </td>
 		  <td  colspan="2" >&nbsp;</td>
		</tr>
		<!--  
		<th>
			登录时间
		</th>
		<td>
		    <input id="loginDate" name="loginLogForm.loginDate" type="text" size="25" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'loginDate\')}'})" class="Wdate" />
        </td>
        -->
       
		<tr>
		<td class="bottom" colspan="8" align="center">
		<input name="btn" id="btn_query" type="button" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'"  value="查询" />
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
			<input type="button" id="btn_export" class="button" value="导出" onclick="export_Query();"/>
		</td>
	</tr>
	<tr>
	<td>
	<div id="pagecontent"></div>
	</td>
	</tr>
</table>
</body>
</html>