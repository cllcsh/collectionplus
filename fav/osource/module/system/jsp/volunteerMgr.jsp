<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/volunteerMgr.js"></script>
<script type="text/javascript">

</script>
</head>
<body onload="loadPage()">
<%@include file="/jsp/include/navigation.jsp"%>
<s:form id="volunteerQueryForm" name="volunteerQueryForm">
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table class="tb_query" border="0" cellspacing="1" cellpadding="1" align="center">
					<tr>
						<th width="10%">全国志愿者注册编号：</th>
						<td width="15%">
							<s:textfield size="20" id="registerId" name="volunteerSearchInfo.registerId" maxlength="180" />
						</td>
						<th width="10%">咨询师证书编号：</th>
						<td width="15%">
							<s:textfield size="20" id="consultantCertificateId" name="volunteerSearchInfo.consultantCertificateId" maxlength="60" />
						</td>
					</tr>
					<tr>
						<th width="10%">姓名：</th>
						<td width="15%">
							<s:textfield size="20" id="name" name="volunteerSearchInfo.name" maxlength="60" />
						</td>
						<th width="10%">咨询师等级：</th>
						<td width="15%">
							<dict:select id="rank" name="volunteerSearchInfo.rank" codeType="tb_volunteer-rank" emptyOption="true" cssStyle="width:135px"/>
						</td>
					</tr>
					<tr>
						<th width="10%">身份证号码：</th>
						<td width="15%">
							<s:textfield size="20" id="idNum" name="volunteerSearchInfo.idNum" cssClass="td03 IDNumOnly"  maxlength="180"/>
						</td>
						<th width="10%">使用状态：</th>
						<td width="15%">
							<input type="radio" name="useFlag" value="1" checked="checked"/>正常
							<input type="radio" name="useFlag" value="0"/>删除
						</td>
					</tr>
					<tr>
						<td class="bottom" colspan="6" align="center">
							<input name="btn" type="button" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" onclick="query();" value="查询" />
							<input name="btn" type="reset" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="重置" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</s:form>
<br/>
<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td align="right" style="padding-right: 20px">		
		<s:if test="%{userSession.userPermissions['/module/system/volunteer_add.do'] != null}">
    		<input type="button" id="btn_add_user" onclick="add_volunteer();" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="添加"/>	
   		</s:if>
   		<s:if test="%{userSession.userPermissions['/module/system/volunteer_delete.do'] != null}">
    		<input type="button" onclick="javascript:del_volunteers('checkbox1');" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="删除"/>	
   		</s:if>		
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