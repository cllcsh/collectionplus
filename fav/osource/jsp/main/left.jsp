<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><s:property value="jsp_head_title"/></title>
<%@include file="/jsp/common.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=path%>/resource/themes/<%=themeName%>/left.css"/>
<script type="text/javascript" src="<%=path%>/resource/themes/<%=themeName%>/left.js"></script>
</head>
<body>
<div class="moduleName title_head"><s:property value="#moduleName" /></div>
<table width="147" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr><td valign="top">
			<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
				<s:iterator id="menu" value="menus">
					<tr>
						<td height="30">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="20" height="20"  >
										<img src="<%=path %>/resource/themes/<%=themeName%>/ico/<s:property value="#menu.id"/>.gif" width="20" height="20">
									</td>
									<td width="99" style="padding-left: 5px">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td height="20" class="STYLE4" style="cursor: hand"
													id="<s:property value="#menu.id"/>"
													onclick="onclick_urlchange('<%=path%><s:property value="#menu.link"/>')";
													onMouseOver="mouseover_me(this);"
													onmouseout="mouseout_me(this);">
													<s:property value="#menu.name" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</s:iterator>
			</table>
		</td>
	</tr>
</table>
<s:hidden id="menulink" value="%{subModuleId}"></s:hidden>
<div class="bottom_all title_head">版本：<s:property value="#version"/></div>
</body>
</html>
