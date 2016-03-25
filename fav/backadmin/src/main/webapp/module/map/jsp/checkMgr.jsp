<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>
<%@taglib prefix="ict" uri="/ict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title><s:property value="jsp_head_title"/></title>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/checkMgr.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$(document).ict({
		queryForm:'checkForm',
		queryUrl:'check_query.do',
		queryBtn:'btn_query',
		pagecontent:'pagecontent',
		addUrl:'check_add.do',
		delsUrl:'check_deletes.do',
		delsBtn:'btn_del'
	});
});
</script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<form id="checkForm" name="checkForm">
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_query" border="0" cellspacing="1" cellpadding="1" align="center" >
		<tr>
			<th>
				抽查对象：
			</th>
			<td>
				<ict:select beanContextId="tb_criminal" id="criminalId" name="checkForm.criminalId" cssStyle="width:200px" emptyOption="true"></ict:select>
			</td>
 			<th>
 				抽查类型：
			</th>
			<td>
				<dict:select codeType="check_result" id="checkResult" name="checkForm.checkResult" emptyOption="true" cssStyle="width:200px"/>
			</td>
		</tr>
		<tr>
			<th>开始日期：</th>
			<td><input id="startDate" style="width:200px" name="checkForm.startDate" readonly="readonly" value='<s:date format="yyyy-MM-dd" name="%{checkForm.startDate}"/>'  size="25" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd' , maxDate:'#F{$dp.$D(\'endDate\')}'})" class="Wdate"></input></td>
			<th>结束日期：</th>
			<td><input id="endDate" style="width:200px" name="checkForm.endDate" readonly="readonly" value='<s:date format="yyyy-MM-dd" name="%{checkForm.endDate}"/>'  size="25" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd', minDate:'#F{$dp.$D(\'startDate\')}'})" class="Wdate"></input></td>
		</tr>
		<tr>
			<td class="bottom" colspan="4" align="center">
		   		<input name="btn" id="btn_query" type="button" class="button" value="查询" />
<%--		   		<s:if test="%{userSession.userPermissions['/module/map/check_query.do'] != null}">--%>
<%--					<input name="btn" id="btn_query" type="button" class="button" value="查询" />--%>
<%--		   		</s:if>--%>
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
<%--			<input type="button" id="btn_add" class="button" value="抽查"/>--%>
			<s:if test="%{userSession.userPermissions['/module/map/check_add.do'] != null}">
				<input type="button" id="btn_add" class="button" value="抽查"/>
			</s:if>
<%--			<input type="button" id="btn_del" class="button" value="删除"/>--%>
			<s:if test="%{userSession.userPermissions['/module/map/check_deletes.do'] != null}">
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
