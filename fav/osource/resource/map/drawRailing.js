var _posourArr = new Array();//定位位置来源
_posourArr[1]='SID/NID区域定位';
_posourArr[6]='网络小区号';
_posourArr[8]='混合cell/sector';
_posourArr[18]='手机AGPS';
_posourArr[20]='手机AFLT';
_posourArr[22]='A-GPS+AFLT';

//画行政区域围栏
function showArea(code){
	var Acode = "A"+code;//alert("showArea:"+Acode);
	if(AreaData[Acode]){
		doShowArea(code);
	}else{
		$.getScript("<%=path%>/module/map/railingData/"+Acode+".js", function(){
		//$.getScript("http://202.102.112.163:8080/ictserver/railingData/"+Acode+".js", function(){
			  //alert(AreaData["A"+code].id);
			  doShowArea(code);
		});
	}
	
}

function doShowArea(code){
	if(code == "0") {
		code = "000000";
	}
	var acode = AreaData["A"+code];//alert("acode:"+acode);
    map.addOverlay(new SuperMap.ictmap.RailingArea({xs:acode.xs,ys:acode.ys,xcenter:acode.xCenter,ycenter:acode.yCenter,mapScale:acode.mapScale}));
}

//公用方法，实现在地图上打点，单击显示层内容
function markLocOverlay(rectifyLong, rectifyLat, locId, divContent, divTitle){
	if(divTitle == null || divTitle == "undefined")
		divTitle = "";
	
	map.addOverlay(new SuperMap.ictmap.LocMarker({x:rectifyLong, y:rectifyLat},"marker_"+locId, {infowin:{width:250, height:250, title:divTitle,content:divContent}}));
}

//公用方法，返回用于显示的层内容
function getRemark(locId, name, locNum, longitude, latitude, placeName, locDate, radius, posour,pic_path)
{
	if(radius == null || radius == "undefined")
		radius = "未知";
	if(posour == null || posour == "undefined")
		posour = "未知";
	else
		posour = _posourArr[posour];
	
	/*var remark=
		"<div style='font-size:9pt;text-align:left;color:blue;font-family:宋体,tahoma;'>"+
		"姓名:"+name+"<br/>"+
		"经度:"+longitude+"<br/>"+
		"纬度:"+latitude+"<br/>"+
		"定位号码:"+locNum+"<br/>"+
		"定位地点:"+placeName+"<br/>"+
		"定位时间:"+locDate+"<br/>"+
		"偏差半径:"+radius+"<br/>"+
		"位置来源:"+posour+"<br/>"+
		"</div>";*/
	
	var remark="";
	var strPath="";
	if(pic_path == null || pic_path == ""){
		strPath="</td><td rowspan=\"8\"><img src=\"./images/mrtp.jpg\" width='60' height='90'></td></tr><tr><td>经度:"+longitude;
	}
	else{
		strPath="</td><td rowspan=\"8\"><img src=\"<%=request.getContextPath()%>/upload/file/"+pic_path+"\" width='60' height='90'></td></tr><tr><td>经度:"+longitude;
	}
	remark=
		"<table style='font-size:9pt;text-align:left;color:blue;font-family:宋体,tahoma;' border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td>姓名:"+name+
		strPath+
		"</td></tr><tr><td>纬度:"+latitude+
		"</td></tr><tr><td>定位号码:"+locNum+
		"</td></tr><tr><td>定位地点:"+placeName+
		"</td></tr><tr><td>定位时间:"+locDate+
		"</td></tr><tr><td>偏差半径:"+radius+
		"</td></tr><tr><td>位置来源:"+posour+
		"</td></tr></table>";
	
	//alert(remark);
	
	return remark;
}

//从数据库中取经纬度数据
function draw_railings(railingsId){
	//alert('ssss'+railingsId);
	var params = {'mode':"ajax", 'form.id':railingsId};
	jQuery.getJSON("<%=path%>/module/map/railings_getData.do", params, function(json){//小数据量用getJson
		var i = 0;
		var latitudes = "";
		var longitudes = "";

		for(;i<json.length;i++){
			latitudes += json[i].latitude+",";
			longitudes += json[i].longitude+ ",";
		}
		latitudes = latitudes.substring(0,latitudes.length-1);
		longitudes = longitudes.substring(0,longitudes.length-1);
		map.getMapControl().CustomLayer.ClearLines();//清上一个的图像
		
		drawCrawl(longitudes,latitudes);	//画围栏
	});
}
//画线函数
function DrawLine(name, x1, y1, x2, y2){
	 var xs = new Array();
	 xs.push(x1);
	 xs.push(x2);
	 var ys = new Array();
	 ys.push(y1);
	 ys.push(y2);
	 map.getMapControl().CustomLayer.InsertLine(name, xs, ys, "1.5", "red", 0, "1", "1");
}

//画围栏
function drawCrawl(longitude, latitude) {
	var longitudeArray = new Array();
	var latitudeArray = new Array();
	longitudeArray = longitude.split(",");
	latitudeArray = latitude.split(",");
	var i=0;
	for(i=0;i<longitudeArray.length-1;i++) {
		DrawLine("quyu"+i, longitudeArray[i], latitudeArray[i], longitudeArray[i+1], latitudeArray[i+1]);	
	}
	DrawLine("quyu"+longitudeArray.length, longitudeArray[0], latitudeArray[0], longitudeArray[longitudeArray.length-1], latitudeArray[longitudeArray.length-1]);
	
}