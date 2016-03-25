<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>区域监控查询</title>
<%@include file="/jsp/common.jsp"%>

<link rel="stylesheet" type="text/css" href="<%=path%>/resource/jquery-easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/resource/jquery-easyui/themes/icon.css">
<script type="text/javascript" src="<%=path%>/resource/jquery-easyui/jquery.easyui.min.js"></script>

<script type="text/javascript" src="<%=path%>/resource/map/scripts/SuperMap.IS.Include.js"></script>
<script type="text/javascript" src="<%=path%>/resource/map/SuperMap.ictmap.CustomMark.js"></script>
<script type="text/javascript" src="<%=path%>/resource/map/SuperMap.ictmap.ToolbarControl.js"></script>
<script type="text/javascript" src="<%=path%>/resource/map/SuperMap.ictmap.Overlay.js"></script>
<script type="text/javascript" src="<%=path%>/resource/map/SuperMap.ictmap.Action.js"></script>
<script type="text/javascript" src="<%=path%>/resource/map/jquery.ictmap.js"></script>
<style>
.font_12376A {border-bottom:1px solid #1D55A2;

padding-top:8px;
padding-left:10px;
padding-bottom:8px
input{border:1px solid #1D55A2;}
}
td {font: 12px;}
</style>
<script type="text/javascript" src="js/historyTraceMgr.js"></script>
<script type="text/javascript">
var map;
$(function(){
	map = jQuery('#myMap').ictmap({
			x:<s:property value="#centerPointUserSession.centerPointInfo.longitude" />,
			y:<s:property value="#centerPointUserSession.centerPointInfo.latitude" />,
			mapScale:<s:property value="#centerPointUserSession.centerPointInfo.zoomLevel" />,
			mapHandler:'http://202.102.112.26:8089/'
		},{toolmode:"new_full"},
		function(){
			$('#MapControl_workLayer').width('100%').height('100%');
	});
	$('#mapfind').html('正在加载页面...').load(_contextPath+"/module/map/areaTime_init.do?mode=ajax",function(){
		selDestineDateChange();
	});
});
</script>
</head>
<body class="easyui-layout">
	<div region="east" split="true" title="区域监控查询" style="width:300px;padding:1px;overflow:hidden;">
		<div class="easyui-accordion" fit="true" border="false">
			<div id="mapfind" >
				
			</div>
		</div>
	</div>
	<div region="center" style="overflow:hidden;">
	<div id="myMap" class="map"  style="position: relative; margin: 0px auto; overflow: hidden; width: 100%;min-height: 500px; height: 100%"></div>
	</div>
	<div id="hisWin" class="easyui-window" closed="true" modal="false" title="区域监控查询列表" style="width:300px;height:400px;">
	</div>
</body>
</html>