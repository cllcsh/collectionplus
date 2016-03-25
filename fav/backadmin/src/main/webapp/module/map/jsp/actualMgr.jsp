<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>
<%@taglib prefix="ict" uri="/ict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title><s:property value="jsp_head_title"/></title>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/actualMgr.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$(document).ict({
		queryForm:'queryForm',
		queryUrl:'actual_query.do',
		queryBtn:'btn_query',
		pagecontent:'pagecontent'
	});
});
</script>
</head>

<body>
<form id="queryForm" action="">
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_query" border="0" cellspacing="1" cellpadding="1" align="center" >
		<tr>
			<th>
				机构名称
			</th>
			<td>
		       <ict:select beanContextId="deptSelect" id="dept_id" name="actualForm.deptId" cssStyle="width:275px" emptyOption="true"></ict:select>
			</td>
 			<th>
				姓名
			</th>
			<td>
				<s:textfield size="20" id="name" cssClass="td03" name="actualForm.criminalName" maxlength="60" onblur="clearBlank(this);"/>
			</td>
		</tr>
		<tr>
		    <th>性别</th>
		    <td><s:radio id="sex" name="actualForm.sex" list="%{#{'1':'男','2':'女','0':'全部'}}" value="'0'"
								 onclick="" />
			</td>
							
		    <th>状态</th>
		    <td><s:radio id="status" name="actualForm.status" list="%{#{'1':'矫正','2':'解矫','3':'其他','0':'全部'}}" value="'1'"
								 onclick="" />
			</td>
		</tr>
		<tr>
			<th>定位结果</th>
			<td><s:select id="locCode" name="actualForm.locCode" list="%{#{'':'','0':'成功','1':'失败','130':'130用户手机关机','131':'131定位激活失败','104':'104用户目前不可到达','3004':'3004欠费或号码不正确','3005':'3005用户关机'}}">
			</s:select></td>
			<th></th><td></td>
		</tr>
		<tr>
			<td class="bottom" colspan="4" align="center">
		   		<s:if test="%{userSession.userPermissions['/module/map/actual_query.do'] != null}">
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
			<s:if test="%{userSession.userPermissions['/module/map/locaQuery_mapView.do'] != null}">
				<input type="button" id="mapView" class="button" value="地图显示" onclick="javascript:mapView('checkbox1');"/>
			</s:if>
			<s:if test="%{userSession.userPermissions['/module/map/actual_export.do'] != null}">
				<input type="button" id="dataExport" class="button" value="导出" onclick="export_Query();"/>
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
