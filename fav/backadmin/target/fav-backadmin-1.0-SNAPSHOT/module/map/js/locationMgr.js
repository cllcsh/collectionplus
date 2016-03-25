//定义位置来源
var _posourArr = new Array();
_posourArr[0]='未使用';
_posourArr[1]='SID/NID区域定位';
_posourArr[6]='网络小区号';
_posourArr[8]='混合cell/sector';
_posourArr[18]='手机AGPS（包括AGPS+AFLT的混合模式）';
_posourArr[20]='手机AFLT';
_posourArr[22]='A-GPS+AFLT';

//定义定位类型
var _loctypeArr = new Array();
_loctypeArr[0]='未知';
_loctypeArr[1]='人工定位';
_loctypeArr[2]='定时定位';
_loctypeArr[3]='快速定位';
_loctypeArr[4]='短信指令定位';
_loctypeArr[5]='手机上报';
_loctypeArr[6]='手机上报';

var _locModArr = new Array();
_locModArr[1]='精定位';
_locModArr[3]='粗定位';
_locModArr[20]='手机基站上报';
_locModArr[21]='手机GPS上报';

//切换地图类型
function changeMap(mapType){
	location.href= _contextPath+"/module/map/location_init.do?mapType="+mapType;
}

//公用方法，实现在地图上打点，单击显示层内容
function markLocOverlay(map, rectifyLong, rectifyLat, locId, divContent, divTitle){
	if(divTitle == null || divTitle == "undefined")
		divTitle = "";
	
	map.addOverlay(new SuperMap.ictmap.LocMarker({x:rectifyLong, y:rectifyLat},"marker_"+locId, {infowin:{width:250, height:250, title:divTitle,content:divContent}}));
}

//公用方法，返回用于显示的层内容
function getRemark(locId, name, locNum, longitude, latitude, placeName, locDate, radius, posour,pic_path)
{
	
	var strlocDate = "";
	if(locDate != null && typeof locDate!="undefined" && typeof locDate != "string")//locDate不是字符串对象
		strlocDate = new Date(locDate.time).format("yyyy-MM-dd hh:mm:ss");
	else
		strlocDate = locDate;
	
	if(radius == null || radius == "null" || radius == "" || radius == undefined || radius == "undefined")
		radius = "未定义";
	if(posour == null || posour == "null" || posour == "" || posour == undefined || posour == "undefined")
		posour = "未定义";
	else
		posour = _posourArr[posour];
	
	var remark="";
	var strPath="";
	if(pic_path == null || posour == "null" || pic_path == "" || pic_path == undefined){
		//strPath="</td><td rowspan=\"8\"><img src=\"./images/mrtp.jpg\" width='60' height='90'></td></tr><tr><td>经度:"+longitude;
		strPath="<tr><td>经度:"+longitude;
	}
	else{
		//strPath="</td><td rowspan=\"8\"><img src=\"<%=request.getContextPath()%>/upload/"+pic_path+"\" width='60' height='90'></td></tr><tr><td>经度:"+longitude;
		strPath="<tr><td>经度:"+longitude;
	}
	remark=
		"<table style='font-size:9pt;text-align:left;color:blue;font-family:宋体,tahoma;' border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td>姓名:"+name+
		strPath+
		//"</td><td rowspan=\"10\"><img src=\"<%=request.getContextPath()%>/upload/file/"+pic_path+"\" width='60' height='90'></td></tr><tr><td>经度:"+longitude+
		"</td></tr><tr><td>纬度:"+latitude+
		"</td></tr><tr><td>定位号码:"+locNum+
		"</td></tr><tr><td>定位地点:"+placeName+
		"</td></tr><tr><td>定位时间:"+strlocDate+
		"</td></tr><tr><td>偏差半径:"+radius+
		"</td></tr><tr><td>位置来源:"+posour+
		"</td></tr></table>";
	
	//alert(remark);
	
	return remark;
}

//公用方法，返回用于显示的层内容
function getRemark2(data)
{
	
	var strlocDate = data.locDate;
	
	if(data.radius == null || data.radius == "null" || data.radius == "" || data.radius == undefined || data.radius == "undefined")
		data.radius = "未定义";
	if (data.resultMod == 3) {
		data.posour = "粗定位";
	} else if (data.resultMod == 20) {
		data.posour = "手机基站上报";
	} else if (data.resultMod == 21) {
		data.posour = "手机GPS上报";
	} else if(data.posour == null || data.posour == "null" || data.posour == "" || data.posour == undefined || data.posour == "undefined")
		data.posour = "未定义";
	else
		data.posour = _posourArr[data.posour];
	if(data.placeName == null || data.placeName == "null" || data.placeName == "" || data.placeName == undefined || data.placeName == "undefined")
		data.placeName = '<a href="javascript:;" onclick="javascript:getPlacename(this,'+data.id+')">获取位置</a>';

	var locType;
	if (data.mod == "1"){
		locType = "GPSone";
	}
	else if (data.mod == "2"){
		locType = "GPS";
	}
	else if (data.mod == "3"){
		locType = "粗定位";
	}
	else if (data.mod == "4"){
		locType = "精定位优先";
	}

	var remark=
		"<table style='font-size:9pt;text-align:left;color:blue;font-family:宋体,tahoma;' border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td>姓名:"+data.holder+
		"</td></tr><tr><td>经度:"+data.longitude+
		"</td></tr><tr><td>纬度:"+data.latitude+
		"</td></tr><tr><td>定位号码:"+data.locNum+
		"</td></tr><tr><td>定位地点:"+data.placeName+
		"</td></tr><tr><td>定位时间:"+data.locDate+
		"</td></tr><tr><td>偏差半径:"+data.radius+
		"</td></tr><tr><td>位置来源:"+data.posour+
		"</td></tr><tr><td>定位方式:"+locType;
	if (data.mod == "4" && data.locCodeGpsone != null && data.locCodeGpsone != "null" && data.locCodeGpsone != undefined && data.locCodeGpsone != "undefined"){
		remark = remark + "</td></tr><tr><td>精定位错误码:" + data.locCodeGpsone + "</td></tr></table>";
	}
	else {
		remark = remark + "</td></tr></table>";
	}
	return remark;
}

function getPlacename(node,id){
	$(node).html('正在查询，请稍后');
	jQuery.getJSON("location_getData.do?id="+id, function(data){
		var placeName="";
		if(!(data.placeName == null || data.placeName == "null" || data.placeName == "" || data.placeName == undefined || data.placeName == "undefined")){
			placeName = data.placeName;
		}
		if(placeName != ""){
			$(node).replaceWith(placeName);
		} else {
			//$(node).html('<a href="javascript:;" onclick="javascript:getPlacename(this,'+id+')">获取位置</a>');
			$(node).html('获取位置');
		}
	})
}