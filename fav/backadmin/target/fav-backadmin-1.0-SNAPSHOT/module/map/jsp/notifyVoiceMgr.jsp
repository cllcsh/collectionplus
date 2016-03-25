<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>
<%@taglib prefix="ict" uri="/ict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title><s:property value="jsp_head_title"/></title>
<script type="text/javascript" src="js/notifyVoiceMgr.js"></script>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript">
var aa;
$(document).ready(function(){
	aa = $(document).ict({
		formid:'queryForm',
		queryUrl:'notifyVoice_query.do',
		queryBtn:'btn_query',
		pagecontent:'pagecontent',
		addUrl:'notifyVoice_add.do',
		delsUrl:'notifyVoice_deletes.do',
		delsBtn:'btn_dels',
		isloadQuery:false
	});
});

function update_monitor(){
	$.ajax({
		type: "POST",
		url: "notifyVoice_update.do",
		data: $('#listForm').serialize(),
		dataType:"json",
		success: function(msg){
			alert( msg.message );
			if(msg.codeid == 0){
				aa.reload();
			}
		}
	});
}
</script>
</head>
<body>
<%@include file="/jsp/include/navigation.jsp"%>
<form id="queryForm" action="">
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_query" border="0" cellspacing="1" cellpadding="1" align="center" >
		<tr>
 			<th width="20%">
				所属单位：
			</th>
			<td width="30%">
				<ict:select beanContextId="deptSelect" id="deptId" name="criminalQueryForm.dept_id" emptyOption="true" cssStyle="width:135px" onchange="selectOnChange('deptId','staffId','managerSelectDao');"></ict:select>
			</td>
			
			<th width="20%">
				矫正工作者：
			</th>
			<td width="30%">
				<s:select id="staffId" name="criminalQueryForm.staff_id" list="#{}" emptyOption="true" cssStyle="width:135px"/>
			</td>
		</tr>
		<tr>
			<td class="bottom" colspan="4" align="center">
<%--		   		<input name="btn" id="btn_query" type="button" class="button" value="查询" />--%>
<%--		   		<s:if test="%{userSession.userPermissions['/module/archives/monitor_query.do'] != null}">--%>
					<input name="btn" id="btn_query" type="button" class="button" value="查询" />
<%--		   		</s:if>--%>
				<input name="btn" type="reset" class="button" value="重置" onclick="cleanDept();" />
				<script>
					function cleanDept(){
						document.getElementById("staffId").options.length=0;	
					}
				</script>
			</td>
		</tr>
	</table>
</td></tr></table>
</form>
<br/>
<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td align="right" style="padding-right: 20px">
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
