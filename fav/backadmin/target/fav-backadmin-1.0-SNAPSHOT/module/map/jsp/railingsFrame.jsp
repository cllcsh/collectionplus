<%@ page contentType="text/html; charset=utf-8" import="java.util.*"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<title><s:property value="jsp_head_title"/></title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	overflow:hidden;
}
-->
</style>
<link rel="stylesheet" type="text/css" href="<%=path%>/resource/jquery-easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/resource/jquery-easyui/themes/icon.css">
<script type="text/javascript" src="<%=path%>/resource/jquery-easyui/jquery.easyui.min.js"></script>

<script type="text/javascript" src="<%=path%>/resource/map/scripts/SuperMap.IS.Include.js"></script>
<script type="text/javascript" src="<%=path%>/resource/map/SuperMap.ictmap.CustomMark.js"></script>
<script type="text/javascript" src="<%=path%>/resource/map/SuperMap.ictmap.ToolbarControl.js"></script>
<script type="text/javascript" src="<%=path%>/resource/map/SuperMap.ictmap.Overlay.js"></script>
<script type="text/javascript" src="<%=path%>/resource/map/SuperMap.ictmap.Action.js"></script>
<script type="text/javascript" src="<%=path%>/resource/map/jquery.ictmap.js"></script>
<script type="text/javascript">
var map;
$(function(){
	map = jQuery('#myMap').ictmap({
			x:<s:property value="#centerPointUserSession.centerPointInfo.longitude" />,
			y:<s:property value="#centerPointUserSession.centerPointInfo.latitude" />,
			mapScale:<s:property value="#centerPointUserSession.centerPointInfo.zoomLevel" />,
			mapHandler:'http://202.102.112.26:8089/'
		},{toolmode:"full"},
		function(){
			$('#MapControl_workLayer').width('100%').height('100%');
	});
	$('#mapfind').html('正在加载页面...').load("<%=path%>/module/map/railings_init.do?mode=ajax");
});
</script>
</head>

<body class="easyui-layout">
	<div region="east" split="true" title="活动区域显示" style="width:280px;padding1:1px;overflow:hidden;">
		<div class="easyui-accordion" fit="true" border="false">
			<div id="mapfind" ></div>
		</div>
	</div>
	<div region="center" style="overflow:hidden;">
	<div id="myMap" class="map"  style="position: relative; margin: 0px auto; overflow: hidden; width: 100%;min-height: 500px; height: 100%"></div>
	</div>
</body>

</html>