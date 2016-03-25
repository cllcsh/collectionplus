<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<title><s:property value="jsp_head_title"/></title>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script type="text/javascript" src="js/googleMap.js"></script>

<link rel="stylesheet" type="text/css" href="<%=path%>/resource/jquery-easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/resource/jquery-easyui/themes/icon.css">
<script type="text/javascript" src="<%=path%>/resource/jquery-easyui/jquery.easyui.min.js"></script>

<script type="text/javascript">
var $map;
var currentMapType;//当前地图类型

var mapDiv = document.getElementById('myMap');
var myLatlng = new google.maps.LatLng(${session.PERSON_CENTER_LATITUDE},${session.PERSON_CENTER_LONGITITUDE});

var myOptions = {
  zoom: 14,
  center: myLatlng,
  mapTypeControl: true,
  mapTypeControlOptions: {style:google.maps.MapTypeControlStyle.DROPDOWN_MENU},
  streetViewControl: false,
  mapTypeId: google.maps.MapTypeId.ROADMAP
}


 
$(function(){
	$map = new google.maps.Map(mapDiv, myOptions);

	//添加自定义地图工具控件到地图上
	var toolbarControlDiv = document.createElement('DIV');
	var toolbarControl = new ToolbarControl(toolbarControlDiv, $map);
	toolbarControlDiv.index = 1;
	$map.controls[google.maps.ControlPosition.TOP_RIGHT].push(toolbarControlDiv);

	//地图类型改变事件
	 google.maps.event.addListener($map, 'maptypeid_changed', function() {
	     //alert($map.getMapTypeId());
	     currentMapType = $map.getMapTypeId();
	 });
	 
	$('#mapfind').html('正在加载页面...').load("<%=path%>/module/map/locaQuery_init.do?mode=ajax");
});
</script>
</head>
<body class="easyui-layout">
	<div region="east" split="true" title="定位查询" style="width:280px;padding:1px;overflow:hidden;">
		<div class="easyui-accordion" fit="true" border="false">
			<div id="mapfind" ></div>
		</div>
	</div>
	<div region="center" style="overflow:hidden;">
	<div id="myMap" class="map"  style="position: relative; margin: 0px auto; overflow: hidden; width: 100%;min-height: 500px; height: 100%"></div>
	</div>
</body>
</html>
