<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>
<%@taglib prefix="ict" uri="/ict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title><s:property value="jsp_head_title"/></title>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/voiceCheckMgr.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$(document).ict({
		queryForm:'queryForm',
		queryUrl:'voiceCheck_query.do',
		queryBtn:'btn_query',
		pagecontent:'pagecontent',
		addUrl:'voiceCheck_add.do',
		delsUrl:'voiceCheck_deletes.do',
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
		    <th>司法单位：</th>
		    <td><ict:select beanContextId="deptSelect" id="deptId" name="voiceCheckForm.deptId" onchange="selectOnChange('deptId','criminalId','rectifySelectDao');" cssStyle="width:155px" emptyOption="true"/></td>
		    <th><s:text name="ictmap.tittle"/>：</th>
		    <td><ict:select beanContextId="tb_criminal" id="criminalId" name="voiceCheckForm.criminalId" cssStyle="width:155px" emptyOption="true"/></td>
		</tr>
		<tr>
		    <th>开始日期：</th>
		    <td><input type="text" id="startDate" name="voiceCheckForm.startDate" size="25" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd', maxDate:'#F{$dp.$D(\'endDate\')}'})" readonly="readonly" class="Wdate"/></td>
		    <th>结束日期：</th>
		    <td><input type="text" id="endDate" name="voiceCheckForm.endDate" size="25" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd', minDate:'#F{$dp.$D(\'startDate\')}'})" readonly="readonly"  class="Wdate"/></td>
		</tr>
		<tr>
		    <th>抽查结果：</th>
		    <td colspan="3"><dict:select id="resultCode" emptyOption="true" cssStyle="width:155px"
									name="voiceCheckForm.resultCode" codeType="voice_check_code"></dict:select>
			</td>
		</tr>
		<tr>
			<td class="bottom" colspan="4" align="center">
		   		<s:if test="%{userSession.userPermissions['/module/map/voiceCheck_query.do'] != null}">
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
			<s:if test="%{userSession.userPermissions['/module/map/voiceCheck_add.do'] != null}">
				<input type="button" id="btn_add" class="button" value="抽查"/>
			</s:if>
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
