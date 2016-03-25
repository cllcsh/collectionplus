<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
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
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/locationMgr.js"></script>

<link rel="stylesheet" type="text/css" href="<%=path%>/resource/jquery-easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/resource/jquery-easyui/themes/icon.css">
<script type="text/javascript" src="<%=path%>/resource/jquery-easyui/jquery.easyui.min.js"></script>

<script type="text/javascript" src="<%=path%>/resource/map/scripts/SuperMap.IS.Include.js"></script>
<script type="text/javascript" src="<%=path%>/resource/map/SuperMap.ictmap.CustomMark.js"></script>
<script type="text/javascript" src="<%=path%>/resource/map/SuperMap.ictmap.ToolbarControl.js"></script>
<script type="text/javascript" src="<%=path%>/resource/map/SuperMap.ictmap.Overlay.js"></script>
<script type="text/javascript" src="<%=path%>/resource/map/SuperMap.ictmap.Action.js"></script>
<script type="text/javascript" src="<%=path%>/resource/map/jquery.ictmap.js"></script>
<script type="text/javascript" src="<%=path%>/resource/map/jquery.json-2.3.min.js"></script>
<!--<script type="text/javascript" src="js/pagination.js"></script>-->
<script type="text/javascript">
var map;
var stations = new Array();
/*var locationNum;
var holder;
var railingsId;//围栏id
var locationId;//定位记录编号
var placeName;//定位后地点
var locDate;//定位时间
var rectifyLong;//纠偏后经度
var rectifyLat;//纠偏后纬度
var displayWay;//显示方式：1-显示 围栏 ,0-不显示围栏
*/

$(function(){
	map = jQuery('#myMap').ictmap({
			x:$("#longitude").val(),
			y:$("#latitude").val(),
			mapScale:$("#zoomLevel").val(),
			mapHandler:'http://202.102.112.26:8089/'
		},{toolmode:"normal"},
		function(){
			$('#MapControl_workLayer').width('100%').height('100%');
			doDrawOnMap();
	});
	
	//window.setTimeout("doDrawOnMap()",3000);
});

function doDrawOnMap(){//alert("doDrawOnMap");
//	var locationId = $("#locationId").val();
//	var rectifyLong = $("#rectifyLong").val();
//	var rectifyLat = $("#rectifyLat").val();
//	var railingsId = $("#railingsId").val();
//	var locationNum = $("#locationNum").val();
//	var placeName = $("#placeName").val();
//	var locDate = $("#locDate").val();
//	var holder = $("#holder").val();
//	var placeName = $("#placeName").val();
//	var displayWay = $("#displayWay").val();
	var terminalIds = $("#terminalIds").val();
	
//	drawMarker(locationId, rectifyLong, rectifyLat, railingsId, holder, locationNum, placeName, locDate);
	
//	if(displayWay == "1" && (railingsId != null && railingsId != ""))
//		window.setTimeout("drawRailing("+railingsId+")",3000);

	$.ajax({
			type: "POST",
			url: "locaQuery_view.do?locationIds="+terminalIds+"&locaQueryForm.objType=0",
			success: function(result){
				var data = result || [];
				if(data.length > 0){
					for(var i=0;i<data.length;i++){
						showLocation(data[i]);
						//if((i+1) == data.length){
						//	map.getMapControl().SetCenterAndZoom(data[i].rectifyLong, data[i].rectifyLat, map.getMapControl().GetMapScale());
						//}
					}
				}
				/*else{
					alert("没有可以显示的位置信息");
				}*/
			},
			dataType: "json",
			error: function(XMLHttpRequest, textStatus, errorThrown) {
			    alert("获取数据失败");
			}
		});
}

function showLocation(dataObj)
{
	drawMarker(dataObj.locId,dataObj.rectifyLong,dataObj.rectifyLat,dataObj.railingsId,dataObj.name,dataObj.locNum,dataObj.placeName,dataObj.locDate);
}
	
function drawMarker(locationId, rectifyLong, rectifyLat, railingsId, holder, locNum, placeName, locDate){//alert("drawMarker");
	var onRemark = getRemark(locationId, holder, locNum, rectifyLong, rectifyLat, placeName, locDate);//alert("onRemark:"+onRemark);
	var locMarker = new SuperMap.ictmap.LocMarker({x:rectifyLong, y:rectifyLat, id:locationId},"loc_"+locationId, {img:"images/locationImage.png",minZoomLevel:5, infowin:{width:300,height:250,title:"", content:onRemark,onshow:function(info){info.html(onRemark)}}});
	map.addOverlay(locMarker);
}

function drawRailing(railingsId){//alert("drawRailing");
	var params = {'mode':"ajax", 'form.id':railingsId};
	jQuery.getJSON("railings_getData.do", params, function(json){//小数据量用getJson
		var i = 0;
		var cx = 0,cy = 0;
		var pointArray = new Array();
		for(;i<json.length;i++){
			pointArray.push({x:json[i].longitude, y:json[i].latitude, id:i});
			
			cx += json[i].longitude;
			cy += json[i].latitude;	
		}
		
		if(json.length>0){
			cx = cx/json.length;
			cy = cy/json.length;
		}
		
		map.getMapControl().CustomLayer.ClearLines();//清上一个的图像
		//画围栏
		map.addOverlay(new SuperMap.ictmap.Polygon(pointArray,null,{id:"Polygon"}));
		map.getMapControl().SetCenterAndZoom(cx, cy, map.getMapControl().GetMapScale());
			
	});
}
</script>
</head>
<body class="easyui-layout">
	<!--
	<s:hidden id="locationId" name="mapForm.locationId"/>
	<s:hidden id="rectifyLong" name="mapForm.rectifyLong"/>
	<s:hidden id="rectifyLat" name="mapForm.rectifyLat"/>
	<s:hidden id="railingsId" name="mapForm.railingsId"/>
	<s:hidden id="locationNum" name="mapForm.locationNum"/>
	<s:hidden id="placeName" name="mapForm.placeName"/>
	<s:hidden id="locDate" name="mapForm.locDate"/>
	<s:hidden id="holder" name="mapForm.holder"/>
	<s:hidden id="displayWay" name="mapForm.displayWay"/>
	-->
	<s:hidden id="terminalIds" name="mapForm.terminalIds"/>
	<s:hidden id="longitude" name="mapForm.longitude"/>
	<s:hidden id="latitude" name="mapForm.latitude"/>
	<s:hidden id="zoomLevel" name="mapForm.zoomLevel"/>
	
	<div region="center" style="overflow:hidden;">
	<div id="myMap" class="map"  style="position: relative; margin: 0px auto; overflow: hidden; width: 100%;min-height: 500px; height: 100%"></div>
	</div>
</body>
<script type="text/javascript">

</script>
</html>
