/**
 * 地图插件
 * 功能：地图功能封装
 * @author luoj
 * @version 1.0
 * @date 2010-5-21
 * @param config 插件配置
 */


(function($) {
	$.fn.ictmap = function(options,configs,callback) {
		this.getMapControl = _getMapControl;
		this.addOverlay = _addOverlay;
		this.addLocHisPolyline = _addLocHisPolyline;
		this.addMarker = null;
		this.getZoomLevel = _getZoomLevel;
		this.getMapScale = _getMapScale;
		this.setCenterAndZoom = _setCenterAndZoom;
		this.clearMap = _clearMap;
		
		if ( configs ){
			if ( jQuery.isFunction( configs ) ) {
				callback = configs;
				configs = null;
			} else if( typeof configs === "object" ) {
				params = jQuery.param( configs );
			}
		}
		
		var opts = $.extend({}, $.fn.ictmap.defaults, options);
		var _config = $.extend({}, $.fn.ictmap.configs, configs);
		this.each(function()	{
			$(this).append("<div style=\"z-index: 1; position: absolute; top: 1px; left: 1px; background-color: #eaeafe;height:100%;width:100%; padding: 0px; margin: 0px;\"><div id=\"MapControl\" style=\"border-color:Gray;border-width:1px;border-style:Solid;height:100%;width:100%;position:absolute\"></div></div>");
			$gmap = new SuperMap.IS.MapControl($(this).find("#MapControl").get(0),opts);
			
			if(_config.navigation){
				$(this).append("<div style=\"z-index: 98; left: 10px; position: absolute; top: 10px; display: block; text-align: center;\"><div id=\"NavigationControl\" style=\"border-width:0px;height:58px;width:58px;\"></div></div>");
				var param = {navigationRate:5,navigationInterval:10,width:58,height:58};
				new SuperMap.IS.NavigationControl($(this).find("#NavigationControl").get(0), $gmap, param);
			}
			if(_config.scaleBar){
				$(this).append("<div style=\"z-index: 99; left: 29px; position: absolute; top: 80px; display: block; text-align: center;\"><div id=\"ScaleBarControl\" style=\"border-width:1px;height:150px;width:20px;\"></div></div>");
				var param = {width:20,height:150,zoomBarImageLength:12,zoomBarStartAt:5,useIntersectedZoomBar:true,ordinal:true,position:0};//,sliderImageUrl:'images/slider.gif',zoomBarImageUrl:'images/zoom-bg-intersected.gif',zoomOutImageUrl:'images/ZoomOut.gif',zoomInImageUrl:'images/ZoomIn.gif'
				new SuperMap.IS.ScaleBarControl($(this).find("#ScaleBarControl").get(0), $gmap, param);
			}
			if(_config.toolbar){
				$(this).append("<div style=\"z-index: 97; top: 5px; position: absolute; right: 10px; display: block;text-align: right; vertical-align: middle;\"><div id=\"ToolbarControl\" ></div></div>")
				var param = {width:255,height:22,position:1,toolmode:_config.toolmode};
				new SuperMap.IS.ToolbarControl($(this).find("#ToolbarControl").get(0), $gmap, param);
			}
//			if(_config.overview){
//				$(this).append("<div style=\"z-index: 100; position: absolute; bottom: 2px; right: 2px; width: 18px;height: 18px; \"><img id=\"ov\" src=\"images/vClose.gif\"  /><div id=\"OverviewControl\" ></div></div>")
//				var param = {};
//				new SuperMap.IS.OverviewControl($(this).find("#OverviewControl").get(0), $gmap, param);
//
//			}
            
			$gmap.Init();
			$gmap.AttachEvent('oninit', callback);
			$gmap.AttachEvent("onresize", function(){
				
			});
			$(window).unload( function () {
				$gmap.DetachEvent('oninit', callback);
				$gmap.Destroy();
				$gmap = null;
			});
		})
		return this;
	}
	
	function _getMapControl() {
		return $gmap;
	}
	
	function _addControl(control){
		
	}
	
	//获得缩放级别
	function _getZoomLevel(){
		return $gmap.GetZoomLevel();
	}
	//获得比例尺
	function _getMapScale(){
		return $gmap.GetMapScale();
	}
	//设置中心点和缩放级别
	function _setCenterAndZoom(centerX, centerY, mapScale){
		$gmap.SetCenterAndZoom(centerX, centerY, mapScale);
	}
	//清空地图上的所有标记
	function _clearMap(){
		$gmap.CustomLayer.ClearMarks();
		$gmap.CustomLayer.ClearLines();
		$gmap.CustomLayer.RemovePolygon("RectQuery");
		$gmap.CustomLayer.RemovePolygon("PolygonQuery");
		$gmap.CustomLayer.RemovePolygon("CircleQuery");
		$gmap.CustomLayer.RemovePolygon("Polygon");//自定义
	}
	
	function _addOverlay(overlay) {
		if(overlay && overlay.type && overlay.type == "SuperMap.ictmap.Marker"){
			_addMarker(overlay);
		}
		if(overlay && overlay.type && overlay.type == "SuperMap.ictmap.LocMarker"){
			_addLocMarker(overlay);
		}
		if(overlay && overlay.type && overlay.type == "SuperMap.ictmap.Polyline"){
			_addPolyline(overlay);
		}
		if(overlay && overlay.type && overlay.type == "SuperMap.ictmap.LocHisPolyline"){
			_addLocHisPolyline(overlay);
		}
		if(overlay && overlay.type && overlay.type == "SuperMap.ictmap.Polygon"){
			_addPolygon(overlay);
		}
		if(overlay && overlay.type && overlay.type == "SuperMap.ictmap.AreaPolygon"){
			_addAreaPolygon(overlay);
		}
		if(overlay && overlay.type && overlay.type == "SuperMap.ictmap.RailingArea"){
			_addRailingArea(overlay);
		}
		if(overlay && overlay.type && overlay.type == "SuperMap.ictmap.Label"){
			_addLabel(overlay);
		}
	}
	function _removeOverlay(overlay){
		
	}
	function _addMarker(overlay, callback) {
		var _div = $gmap.CustomLayer.InsertMark(overlay.id, overlay.x, overlay.y, overlay.width, overlay.height, overlay.innerHtml, overlay.className, overlay.zIndex, overlay.groupID, overlay.alignStyle)
		var onchangviewFun = function(){
			if($gmap.GetZoomLevel() < overlay.minZoomLevel){
					_div.style.display = 'none';
			} else {
					_div.style.display = '';
			}
		};
		$gmap.AttachEvent('onchangeview', onchangviewFun);
		$gmap.AttachEvent('ondestroying', function(){overlay.Destroy();$gmap.DetachEvent('onchangeview', onchangviewFun);});
		var onclickFun = function(map){
			return function(){
				overlay.OnClick(map);
			}
		};
		if (_ygPos.browser == "ie") {
			_div.attachEvent("onclick", onclickFun($gmap));
		} else {
			_div.addEventListener("click", onclickFun($gmap), true);
		}
		if(callback){
			if ( jQuery.isFunction( callback ) ) {
				callback();
			}
		}
	}
	function _addLocMarker(overlay, callback) {
		var _div = $gmap.CustomLayer.InsertMark(overlay.id, overlay.x, overlay.y, overlay.width, overlay.height, overlay.innerHtml, overlay.className, overlay.zIndex, overlay.groupID, overlay.alignStyle);
		$gmap.SetCenterAndZoom(overlay.x, overlay.y, $gmap.GetMapScale());
		
		var onchangviewFun = function(){
			if($gmap.GetZoomLevel() < overlay.minZoomLevel){
					_div.style.display = 'none';
			} else {
					_div.style.display = '';
			}
		};
		$gmap.AttachEvent('onchangeview', onchangviewFun);
		$gmap.AttachEvent('ondestroying', function(){overlay.Destroy();$gmap.DetachEvent('onchangeview', onchangviewFun);});
		var onclickFun = function(map){
			return function(){
				overlay.OnClick(map);
			}
		};
		if (_ygPos.browser == "ie") {
			_div.attachEvent("onclick", onclickFun($gmap));
		} else {
			_div.addEventListener("click", onclickFun($gmap), true);
		}
		if(callback){
			if ( jQuery.isFunction( callback ) ) {
				callback();
			}
		}
	}
	function _addPolyline(overlay) {
		$gmap.CustomLayer.InsertLine(overlay.id, overlay.xs, overlay.ys, overlay.strokeWeight, overlay.strokeColor, overlay.opacity, overlay.zIndex, overlay.groupID, overlay.parts);
	}
	function _addLocHisPolyline(overlay) {
		var points = overlay.locPoints;
		var centerx = 0;
		var centery = 0;
		for(var i=0;i<points.length;i++){
			centerx += points[i].x/points.length;
			centery += points[i].y/points.length;
			if(i>0){
				var pmidx = (points[i-1].x+points[i].x)/2;
				var pmidy = (points[i-1].y+points[i].y)/2;
				var pxs = new Array(points[i-1].x,pmidx);
				var pys = new Array(points[i-1].y,pmidy);
				var lid = overlay.id + i.toString() + 's';
				$gmap.CustomLayer.InsertLineArrow(lid,pxs,pys,1.5,overlay.strokeColor,0.9,100);
			}
			if(points[i].display != 'none')
				_addMarker(points[i]);
		}
		_addPolyline(overlay);
		if(centerx > 0 && centery > 0)
		$gmap.SetCenterAndZoom(centerx, centery);
		//$gmap.CustomLayer.InsertLine(overlay.id, overlay.xs, overlay.ys, overlay.strokeWeight, overlay.strokeColor, overlay.opacity, overlay.zIndex, overlay.groupID, overlay.parts);

	}
	function _addPolygon(overlay) {
		if(overlay.xs != null && overlay.xs != undefined && overlay.xs != '' && overlay.ys != null && overlay.ys != undefined && overlay.ys != '')
			$gmap.CustomLayer.InsertPolygon(overlay.id, overlay.xs, overlay.ys, overlay.strokeWeight, overlay.strokeColor, overlay.fillColor, overlay.fillOpacity, overlay.zIndex, overlay.groupID, overlay.parts);
		
		//$gmap.CustomLayer.InsertMark("markPoint", overlay.xcenter, overlay.ycenter, null, null, "<div>markPoint</div>");
	}
	function _addAreaPolygon(overlay) {
		if(overlay.xs != null && overlay.xs != undefined && overlay.xs != '' && overlay.ys != null && overlay.ys != undefined && overlay.ys != '')
			$gmap.CustomLayer.InsertPolygon(overlay.id, overlay.xs, overlay.ys, overlay.strokeWeight, overlay.strokeColor, overlay.fillColor, overlay.fillOpacity, overlay.zIndex, overlay.groupID, overlay.parts);
		
		$gmap.SetCenterAndZoom(overlay.xcenter, overlay.ycenter, overlay.mapScale);
		
		//$gmap.CustomLayer.InsertMark("markPoint", overlay.xcenter, overlay.ycenter, null, null, "<div>markPoint</div>");
	}
	
	function _addRailingArea(overlay) {//用于行政区域围栏的显示,added by lifa
		if(overlay.xs != null && overlay.xs != undefined && overlay.xs != '' && overlay.ys != null && overlay.ys != undefined && overlay.ys != '')
			$gmap.CustomLayer.InsertPolygon(overlay.id, overlay.xs, overlay.ys, overlay.strokeWeight, overlay.strokeColor, overlay.fillColor, overlay.fillOpacity, overlay.zIndex, overlay.groupID, overlay.parts);
		
		$gmap.SetCenterAndZoom(overlay.xcenter, overlay.ycenter, overlay.mapScale);
		//$gmap.CustomLayer.InsertMark("markPoint", overlay.xcenter, overlay.ycenter, null, null, "<div>markPoint</div>");
	}
	
	function _addLabel(overlay) {
		
	}

	$.fn.ictmap.configs = {
		scaleBar:true,
		navigation:true,
		toolbar:true,
		toolmode:"normal",
		overview:true
	}
	$.fn.ictmap.defaults = {
		mapHandler:'http://202.102.112.27:9008/maphandler/',
//		mapHandler:'http://www.supermap.com.cn/mapHandler3/',
//		mapHandler:'http://202.102.112.26:8089/',
		mapScales:[1/50000000,1/30000000,1/10000000,1/5000000,1/2000000,1/1000000,1/500000,1/250000,1/100000,1/35000,1/25000,1/15000,1/10000,1/5000,1/2500,1/1500],
		mapScale:1/15000,
		x:118.769464113121,
		y:32.0434903971,
		useImageBuffer:true,
		tileSize:256,
		mapName:"automap",
		imageFormat:"png",
		//zoomLevel:12,
		redirect:false,
		buffer:64,
		fixedView:false,
		disableLogo:true
	}
	
})(jQuery);