<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title><s:property value="jsp_head_title"/></title>
<%@include file="/jsp/common.jsp"%>
<link href="<%=path%>/resource/themes/<%=themeName%>/icon.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%@include file="/jsp/include/navigation.jsp"%>
<br>
<div id="menu">
	<ul>
	<s:if test="%{userSession.userPermissions['/module/system/user_init.do'] != null}">
		<li class="icon volunteer_1"><a href="user_init.do"><p class="title">执法人员和专职人员登记</p><p class="main">社区执法人员和社会专职人员登记表</p></a></li>
	</s:if>
	<s:if test="%{userSession.userPermissions['/module/system/specialist_init.do'] != null}">
		<li class="icon volunteer_2"><a href="specialist_init.do"><p class="title">矫正专家登记</p><p class="main">矫正专家详细信息登记</p></a></li>
	</s:if>
	<s:if test="%{userSession.userPermissions['/module/system/volunteer_init.do'] != null}">
		<li class="icon volunteer_3"><a href="volunteer_init.do"><p class="title">心理矫正志愿者登记</p><p class="main">登记心理矫正志愿者的详细资料</p></a></li>
	</s:if>
	</ul>
</div>
</body>
</html>