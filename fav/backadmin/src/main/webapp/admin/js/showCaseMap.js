function showCaseMap(WebRoot){
	//	alert(OpenLayers._getScriptLocation());
	var host = "http://42.121.124.27:8082/geoserver/wms";
	var map;
	var untiled;
	var tiled;
	var imgurl = WebRoot + "admin/js/img/";
	//标注图标
	var icon = {
		0 :imgurl+"markerpoint.gif",
		1 :imgurl+"huang.png",
		2 :imgurl+"fei.png",
		3 :imgurl+"nui.png"
	};

	//画线图层
	var lineVectors =new OpenLayers.Layer.Vector("line");
	//画点图层
	var pointVectors = new OpenLayers.Layer.Vector("point");
	//标注图层
	var markers = new OpenLayers.Layer.Markers( "markers" );
	//部门标注图层
	var depts = new OpenLayers.Layer.Markers( "depts" );

	//var WebRoot = "<c:url value="">";
	OpenLayers.IMAGE_RELOAD_ATTEMPTS = 5;
	OpenLayers.DOTS_PER_INCH = 25.4 / 0.28;
	OpenLayers.ImgPath = WebRoot + "admin/js/img/";

	this.init=function(sssize, callback){
		var format = 'image/png';
//		 alert(sssize);
		if (sssize == undefined || sssize == null){
			sssize = {x1:73.447,y1: 6.319,
			x2:135.086, y2:53.558};
		}
		var bounds = new OpenLayers.Bounds(
		sssize.x1,sssize.y1,sssize.x2,sssize.y2
//			ss[0], ss[1],
//			ss[2], ss[3]
			);

//		var bounds =
//			new OpenLayers.Bounds(
////			73.447, 6.319,
////			135.086, 53.558
//			 107.17,
//			21.00,
//			132.00,
//			40.79
//			) ;
		var options = {
			controls: [],
			maxExtent: bounds,
			maxResolution: 0.24077734375,
			projection: "EPSG:2010",
			resolutions: [
			0.1,
			//				0.09,
			0.08,
			//				0.07,
			0.06,
			//				0.05,
			0.04,
			//				0.03,
			0.02,
			//				0.01,
			0.009
//			//				0.008,
//			0.007,
//			//				0.006,
//			0.005,
			//				0.007890625,
			//				0.00625,
			//				0.00515625,
			//				0.00439453125,
			//				0.003125,
			//				0.003578125
			],
			minScale:1000000,
			numZoomLevels:5,
			units: 'm'
		};
		map = new OpenLayers.Map('map', options);
		// setup tiled layer
		tiled = new OpenLayers.Layer.WMS(
			"Geoserver layers - Tiled", host,
			{
				srs: 'EPSG:2010',
				layers: 'global',
				styles: '',
				format: format,
				tiled: 'true',

				tilesOrigin : map.maxExtent.left + ',' + map.maxExtent.bottom
			},
			{
				buffer: 0,
				displayOutsideMaxExtent: true
			}
			);
		// setup single tiled layer

		untiled = new OpenLayers.Layer.WMS(
			"Geoserver layers - Tiled", host,
			{
				srs: 'EPSG:2010',
				layers: 'global',
				WIDTH:'1900',
				bgcolor:'#E8ff9f',
				//bgcolor: '0xA6CAE0',
				format: format

			},
			{
				singleTile: true,
				ratio: 1
			}
			);
		map.addLayers([untiled,lineVectors,pointVectors,markers,depts]);
		map.addControl(new OpenLayers.Control.PanZoomBar({
			position: new OpenLayers.Pixel(2, 15)
		}));
		map.addControl(new OpenLayers.Control.Navigation());
		map.addControl(new OpenLayers.Control.Scale());
		map.addControl(new OpenLayers.Control.MousePosition());
		map.zoomToExtent(bounds);

	//		this.addNode(10,106.79466,39.56959,3,false,"ssss");
	//this.remvoeNode(10);
	//this.changeStatus(10, 2);
		if($.isFunction(callback)){
			callback();
		}
	}

	function CreateMaker(id,x,y,t,showLable,text,fnc){
		var icon_1 = getIcon(t);
		var feature = new OpenLayers.Feature(markers,new OpenLayers.LonLat(x,y),{
			'icon': icon_1
		});
		var marker =  feature.createMarker();

		marker.text=text;
		//以id进行标注
		marker.nodeid = id;
		marker.showLabel=showLable;
		//设置imgDivid
		// marker.icon.imageDiv.id = id;
		marker.icon.imageDiv.style.fontSize = "12px";
		marker.icon.imageDiv.style.fontWeight = "bold";
		marker.icon.imageDiv.style.whiteSpace = "nowrap";
		marker.icon.imageDiv.style.color = "#ffffff";
		marker.icon.imageDiv.style.textShadow = "1px 1px 2px #000000";
		marker.icon.imageDiv.style.cursor="pointer";
		var str = marker.icon.imageDiv.innerHTML;
		if(marker.showLabel){
			str = str + marker.text;
		}
		marker.icon.imageDiv.innerHTML = str;
		marker.events.register('click',marker,function(ent){
			fnc(id);
		});
		return marker;
	}

	function CreatePoint(x,y){
		var point = new OpenLayers.Geometry.Point(x,y);
		var feature = new OpenLayers.Feature.Vector(point);
		return feature;
	}
	function CreateLine(pointList){
		var style_line = {
			strokeColor: "red",
			strokeOpacity: 0.8,
			strokeWidth: 2,
			pointRadius: 20,
			label:"",
			fontSize:'12px',
			fontFamily:'宋体',
			labelXOffset:30,
			labelYOffset:10,
			labelAlign:'rm'
		};
		var lineFeature = new OpenLayers.Feature.Vector(new OpenLayers.Geometry.LineString(pointList),null,style_line);
		return lineFeature;
	}
	/**
	 * 添加一个节点
	 * id 节点ID
	 * x 经度
	 * y 纬度
	 * icontype,图标类型 0:红 1 黄 2 灰 3绿
	 * showLable 是否显示文字
	 * text 文本内容
	 * fnc 点击后的回调函数,会传入节点ID
	 */
	this.addNode = function(id,x,y,icontype,showLable,text,fnc){
		markers.addMarker(CreateMaker(id,x,y,icontype,showLable,text,fnc));
	}
	this.addDept = function(id,x,y,icontype,showLable,text,fnc){
		depts.addMarker(CreateMaker(id,x,y,icontype,showLable,text,fnc));
	}
	this.setCenter = function(lon, lat, zoom){
		map.setCenter(new OpenLayers.LonLat(lon, lat), zoom);
	}
	//销毁地图对象
	this.destroy = function(){
		map.destroy();
	}
	//从地图上移除某个节点
	this.remvoeNode = function(nodeid){
		for(var i =0;i<markers.markers.length;i++)
		{
			//找到标注点进行删除
			if(nodeid == markers.markers[i].nodeid){
				markers.removeMarker(markers.markers[i]);
			}
		}
	}
	this.clearMarkers = function(){
		markers.clearMarkers();
	}
	this.clearDepts = function(){
		depts.clearMarkers();
	}
	//从地图上移除某个节点
	this.remvoeAllNodes = function(){
		console.debug("移除地图点所有点!")
		for(var i =0;i<markers.markers.length;i++) {console.debug("移除点"+i)
			//找到标注点进行删除
			markers.removeMarker(markers.markers[i]);
		}
	}
	//查看节点是否存在
	this.isExistsNode = function(nodeid){
		for(var i =0;i<markers.markers.length;i++){
			if(nodeid == markers.markers[i].nodeid){
				return true;
			}
		}
		return false;
	}
	//改变节点状态.对应地图图标颜色改变
	this.changeStatus = function(nodeid,status){
		for(var i =0;i<markers.markers.length;i++)
		{
			if(nodeid == markers.markers[i].nodeid){
				var marker = markers.markers[i];
				markers.removeMarker(markers.markers[i]);
				marker.icon = getIcon(status);
				marker.icon.imageDiv.style.fontSize = "2px";
				marker.icon.imageDiv.style.whiteSpace = "nowrap";
				marker.icon.imageDiv.style.color = "white";
				marker.icon.imageDiv.style.cursor="pointer";
				var str = marker.icon.imageDiv.innerHTML;
				if(marker.showLabel){
					str = str + marker.text;
				}
				marker.icon.imageDiv.innerHTML = str;
				//找到标注点进行删除

				//改变图标路径


				//直接修改图片
				//$("#"+nodeid).find("img").attr("src",marker.icon.url);
				//alert(markers.markers[i].icon.imageDiv.innerHTML)
				//alert($("#"+marker.icon.imageDiv.id+"").find("img").attr("src"))
				//重新增加到图层中
				markers.addMarker(marker);
			}
		}
	}
	//获得图标
	function getIcon(t){
		var icon_1;
		if(icon[t]){
			var size = new OpenLayers.Size(10,10);
			var offset = new OpenLayers.Pixel(-size.w, -size.h);
			icon_1 = new OpenLayers.Icon(icon[t], size, offset);
		} else {
			var size = new OpenLayers.Size(40,40);
			var offset = new OpenLayers.Pixel(-size.w, -size.h);
			icon_1 = new OpenLayers.Icon(t, size, offset);
		}
		return icon_1;
	}

	this.resize = function(){
		map.updateSize();
	}
}