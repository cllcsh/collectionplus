<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title><s:property value="jsp_head_title"/></title>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/specialistMgr.js"></script>
<script type="text/javascript">

</script>
</head>
<body onload="loadPage()">
<%@include file="/jsp/include/navigation.jsp"%>
<form action="">
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_query" border="0" cellspacing="1" cellpadding="1" align="center" >
		<tr>
			<th>
				证书编号：
			</th>
			<td>
				<s:textfield size="20" id="certificateId" cssClass="td03" name="specialistForm.certificateId" maxlength="60" onblur="clearBlank(this);"/>
			</td>
 			<th>
				姓名：
			</th>
			<td>
				<s:textfield size="20" id="specialistName" cssClass="td03" name="specialistForm.name" maxlength="60" onblur="clearBlank(this);"/>
			</td>
		</tr>
		<tr>
			<th>
				身份证号码：
			</th>
			<td>
				<s:textfield size="20" id="idNum" cssClass="td03 IDNumOnly" name="specialistForm.idNum" maxlength="60" onblur="clearBlank(this);" />
			</td>
 			<th>
				工作单位：
			</th>
			<td>
				<s:textfield size="20" id="workUnit" cssClass="td03" name="specialistForm.workUnit" maxlength="60" onblur="clearBlank(this);"/>
			</td>
		</tr>
		<tr>
			<th>
				性别：
			</th>
			<td>
				<dict:select id="sex" name="specialistForm.sex" codeType="common-sex" emptyOption="true" cssStyle="width:135px"/>
				<%--<select id="sex" name="specialistForm.sex">
					<option value=""></option>
					<option value="1">男</option>
					<option value="2">女</option>
				</select>
			--%></td>
			<td colspan="2">
				<%--<dict:select id="useFlag" name="specialistForm.useFlag" codeType="common-use_flag"/>
				<select id="useFlag" name="specialistForm.useFlag">
					<option value=""></option>
					<option value="1">正常</option>
					<option value="0">删除</option>
				</select>
			--%></td>
		</tr>
		<tr>
		<td class="bottom" colspan="4" align="center">
<%--		   <s:if test="%{userSession.userPermissions['/module/system/specialist_query.do'] != null}">--%>
				<input name="btn" type="button" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" onclick="query();" value="查询" />
<%--		   </s:if> --%>
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
			<s:if test="%{userSession.userPermissions['/module/system/specialist_add.do'] != null}">
				<input type="button" id="btn_add" onclick="add_specialist();" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="添加"/>
			</s:if>
			<s:if test="%{userSession.userPermissions['/module/system/specialist_deletes.do'] != null}">
				<input type="button" onclick="javascript:del_specialists('checkbox1');" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="删除"/>
            </s:if>
		</td>
	</tr>
	<tr>
	<td>
	<div id="pagecontent"></div>
	</td>
	</tr>
</table>
<div id="dialog" title="矫正专家信息"></div>
</body>
</html>
