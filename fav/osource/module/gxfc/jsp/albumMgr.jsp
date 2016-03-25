<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title><s:property value="jsp_head_title"/></title>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/albumMgr.js"></script>
<script type="text/javascript">
$(document).ready(function(){
});
</script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<br/>
<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<s:iterator var="albumInfo" value="albumInfoList" status="sta">
		<td width="25%">
			<table width="90%" border="0" align="center" cellpadding="1" cellspacing="0">
				<tr>
					<td style="text-align:center;">&nbsp;<s:property value="name"/>&nbsp;</td>
				</tr>
				<tr>
					<td style="text-align:center;">
						<s:if test="%{#albumInfo.picPath != null && #albumInfo.picPath != ''}">
						<img src="<s:property value='#albumInfo.picPath'/>" width="240" height="180" >
						</s:if>
						<s:else>
						<img src="" width="240" height="180">
						</s:else>
					</td>
				</tr>
				<tr>
					<td style="text-align:center;">
						<s:if test="%{#albumInfo.id != null && #albumInfo.id > 0}">
						<a href="javascript:edit_album(<s:property value='#albumInfo.id'/>)" >编辑</a>
						</s:if>
						<s:else>
						<a href="javascript:add_album()" >添加</a>
						</s:else>
					</td>
				</tr>
			</table>
		</td>
		</s:iterator>
	</tr>
</table>
</body>
</html>
