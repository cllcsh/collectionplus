<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="ict" uri="/ict-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/rpTypeMgr.js"></script>
<script type="text/javascript">
</script>
</head>
<title><s:property value="jsp_head_title"/></title>
<body onload="loadPage()">
<%@include file="/jsp/include/navigation.jsp"%>
<s:form id="rpTypeForm" name="rpTypeForm">
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table class="tb_query" border="0" cellspacing="1" cellpadding="1" align="center">
					<tr>
						<th width="10%">奖惩类型名称：</th>
						<td width="15%">
							<s:textfield size="20" id="rpName" name="rpTypeBean.rpName" maxlength="60" />
						</td>
						<th width="10%">默认分值：</th>
						<td width="15%">
							<s:textfield size="20" id="defScore" name="rpTypeBean.defScore" maxlength="60" />
						</td>
					</tr>
					<tr>
						<th>
							奖惩类型：
						</th>
						<td>
						 <dict:select name="rpTypeBean.rp" id="rp"  codeType="common-rp_type" emptyOption="true" cssStyle="width:135px"> </dict:select>
						</td>
						<td  colspan="2" >&nbsp;</td>
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
		
		
		<s:if test="%{userSession.userPermissions['/module/system/rpType_add.do'] != null}">
    	<input type="button" id="btn_add_user" onclick="add_rpType();" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="添加"/>	
   		</s:if> 
   		
<%--   		<input type="button" id="btn_add_user" onclick="add_rpType();" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="添加"/>	--%>
   		
   		
   		<s:if test="%{userSession.userPermissions['/module/system/rpType_deletes.do'] != null}">
    	<input type="button" onclick="javascript:del_rpType('checkbox1');" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="删除"/>	
   		</s:if>	
   		
<%--   		<input type="button" onclick="javascript:del_rpType('checkbox1');" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="删除"/>	--%>
		</td>
	</tr>
	<tr>
	<td>
	<div id="pagecontent"></div>
	</td>
	</tr>
</table>
<div id="dialog" title="奖惩类型编辑"></div>
</body>
</html>