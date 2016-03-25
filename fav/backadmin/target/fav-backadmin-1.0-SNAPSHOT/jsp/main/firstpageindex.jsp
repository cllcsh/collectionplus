<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title><s:property value="jsp_head_title"/></title>
<%@include file="/jsp/common.jsp"%>
<link href="<%=path%>/resource/themes/<%=themeName%>/icon.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/resource/js/jquery-1.4.2.js"></script>
<style>
#menu1 {width: 90%;margin:0 auto;margin-left:5%;}
ul {list-style:none;margin:0}
ul li {float:left;display:block;line-height:10px}
.icon1 {margin:0 180px 140px 0;}
.icon1 a{display:block; position:absolute;width:180px; height:140px; margin:0; padding:0 0 0 0px;background-repeat:no-repeat}
.icon1 a:hover{}
</style>
</head>
<body >
<div id="menu1">
	<ul>
	<s:if test="%{userSession.userPermissions['/module/map/locaQuery_frame.do'] != null}">
		<li class="icon1 link_1"><a href="<%=path%>/module/assess/module_center.do?moduleId=10000&subModuleId=10100&mainUrl=/module/map/locaQuery_frame.do" onclick='top.topFrame.document.changestatus(10000);'><p class="title">定位查询</p><p class="main">对手机终端进行定位</p></a></li>
	</s:if>
	<s:if test="%{userSession.userPermissions['/module/information/informationSend_add.do'] != null}">
		<li class="icon1 link_2"><a href="<%=path%>/module/assess/module_center.do?moduleId=20000&subModuleId=20100&mainUrl=/module/information/informationSend_add.do" onclick='top.topFrame.document.changestatus(20000);'><p class="title">信息交互</p><p class="main">向手机终端发送短信</p></a></li>
	</s:if>	
	<s:if test="%{userSession.userPermissions['/module/archives/criminalArchives_init.do'] != null}">	
		<li class="icon1 link_3"><a href="<%=path%>/module/assess/module_center.do?moduleId=40000&subModuleId=40100&mainUrl=/module/archives/criminalArchives_init.do" onclick='top.topFrame.document.changestatus(40000);'><p class="title"><s:text name="ictmap.tittle"/>档案</p><p class="main">查看<s:text name="ictmap.tittle"/>的所有档案</p></a></li>
	</s:if>	
	<s:if test="%{userSession.userPermissions['/module/system/volunteer_index.do'] != null}">
		<li class="icon1 link_4"><a href="<%=path%>/module/assess/module_center.do?moduleId=70000&subModuleId=70100&mainUrl=/module/system/volunteer_index.do" onclick='top.topFrame.document.changestatus(70000);'><p class="title">人员档案</p><p class="main">查看工作人员基本信息</p></a></li>
	</s:if>
	</ul>
</div>
</body>
</html>
