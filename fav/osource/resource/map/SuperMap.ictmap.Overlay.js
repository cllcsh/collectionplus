// ===============
// 基于超图（SuperMap）地图引擎的自定义地图覆盖物
// @version 1.0
// =============

SuperMap.ictmap.Marker = function(point, opts) {
	var _this = this;
	var width = 20;
	var height =20;
	var img = "images/marker.gif";
	var title = null;
	var zIndex = 101;
	var groupID = null;
	var alignStyle = null;
	var maxMapScale = 1;
	var minZoomLevel = 0;
	var innerHtml = "";
	var id = "marker";

	this.openInfoWindow;
    this.type="SuperMap.ictmap.Marker";
    this.x=point.x;
    this.y=point.y;
	if(opts && opts.id &&  typeof(opts.id) != "undefined"){
		id = opts.id;
	};
	this.id = id;

	if(opts && opts.width &&  typeof(opts.width) != "undefined"){
		width = opts.width;
	};
	this.width = width;

	if(opts && opts.height &&  typeof(opts.height) != "undefined"){
		height = opts.height;
	};
	this.height = height;

	if(opts && opts.img &&  typeof(opts.img) != "undefined"){
		img = opts.img;
	};
	this.img = img;

	if(opts && opts.title &&  typeof(opts.title) != "undefined"){
		title = opts.title;
	};
	this.title = title;

	if(opts && opts.zIndex &&  typeof(opts.zIndex) != "undefined"){
		zIndex = opts.zIndex;
	};
	this.zIndex = zIndex;

	if(opts && opts.groupID &&  typeof(opts.groupID) != "undefined"){
		groupID = opts.groupID;
	};
	this.groupID = groupID;

	if(opts && opts.alignStyle &&  typeof(opts.alignStyle) != "undefined"){
		alignStyle = opts.alignStyle;
	};
	this.alignStyle;

	function _init() {
		innerHtml = "<img src=\"" + _scriptLocation + "../" + _this.img + "\" " + "width=\"" + _this.width +"px\"" + " height=\"" + _this.height +"px\"/>";
	}
	_init();
    this.innerHtml = innerHtml;

	if(opts && opts.maxMapScale &&  typeof(opts.maxMapScale) != "undefined"){
		maxMapScale = opts.maxMapScale;
	};
	this.maxMapScale = maxMapScale;

	if(opts && opts.minZoomLevel &&  typeof(opts.minZoomLevel) != "undefined"){
		minZoomLevel = opts.minZoomLevel;
	};
	this.minZoomLevel = minZoomLevel;

    var OnClick = function(map){
		//map.SetAction(new SuperMap.IS.PanAction());
		//map.CustomLayer.OpenInfoWindow(id + "_info", _this.x, _this.y, 190, 100, '<div style="color: red;">avc</div>', '<DIV style="color: red;">Upload时必须调用此方法，否则会导致内存泄漏</div>', 1);
		//map.SetCenterAndZoom(_this.x,_this.y)
	};
	
	if(opts && opts.OnClick &&  typeof(opts.OnClick) == "function" ){
		OnClick = opts.OnClick;
	}
	this.OnClick = OnClick;

	this.Destroy = function(){
		width=null;
		height=null;
		img=null;
		title=null;
		zIndex=null;
		groupID = null;
		alignStyle = null;
		maxMapScale = null;
		minZoomLevel = null;
		innerHtml = null;
		OnClick = null;
	}
}

SuperMap.ictmap.NumMarker_Old = function(point, id, num, opts) {
	var _this = this;
	var width = 20;
	var height =20;
	var img = "images/marker.gif"
	var title = null;
	var zIndex = 101;
	var groupID = null;
	var alignStyle = null;
	var maxMapScale = 1;
	var minZoomLevel = 0;
	var innerHtml = "";
	var _id = "marker";
	var num = 0 || num;
	var display = '';

	this.openInfoWindow;
    this.type="SuperMap.ictmap.Marker";
    this.x=point.x;
    this.y=point.y;
	
    if(id && typeof(id) != "undefined")
    	_id = id;
	if(opts && opts.id &&  typeof(opts.id) != "undefined"){
		_id = opts.id;
	};
	this.id = _id;

	if(opts && opts.width &&  typeof(opts.width) != "undefined"){
		width = opts.width;
	};
	this.width = width;

	if(opts && opts.height &&  typeof(opts.height) != "undefined"){
		height = opts.height;
	};
	this.height = height;

	if(opts && opts.img &&  typeof(opts.img) != "undefined"){
		img = opts.img;
	};
	this.img = img;

	if(opts && opts.title &&  typeof(opts.title) != "undefined"){
		title = opts.title;
	};
	this.title = title;

	if(opts && opts.zIndex &&  typeof(opts.zIndex) != "undefined"){
		zIndex = opts.zIndex;
	};
	this.zIndex = zIndex;

	if(opts && opts.groupID &&  typeof(opts.groupID) != "undefined"){
		groupID = opts.groupID;
	};
	this.groupID = groupID;

	if(opts && opts.alignStyle &&  typeof(opts.alignStyle) != "undefined"){
		alignStyle = opts.alignStyle;
	};
	this.alignStyle = alignStyle;

	if(opts && opts.display &&  typeof(opts.display) != "undefined"){
		display = opts.display;
	};
	this.display = display;

	function _init() {
		var backgroundImg = _scriptLocation + "../images/num.png";
		innerHtml = "<div style=\"position:absolute;background:url("+backgroundImg+");width:33px;height:33px;color:#FFFFFF;line-height:33px;text-align:center; vertical-align:middle;font-weight:bold\">"
					+num+"<div style=\"position:absolute;left:0;top:0px;width:33px;height:33px;opacity:0.7;filter:alpha(opacity=80);\"></div>"
					+"</div>";
	}
	_init();
    this.innerHtml = innerHtml;

	if(opts && opts.maxMapScale &&  typeof(opts.maxMapScale) != "undefined"){
		maxMapScale = opts.maxMapScale;
	};
	this.maxMapScale = maxMapScale;

	if(opts && opts.minZoomLevel &&  typeof(opts.minZoomLevel) != "undefined"){
		minZoomLevel = opts.minZoomLevel;
	};
	this.minZoomLevel = minZoomLevel;

    var OnClick = function(map){
		//map.SetAction(new SuperMap.IS.PanAction());
		//map.CustomLayer.OpenInfoWindow(id + "_info", _this.x, _this.y, 190, 100, '<div style="color: red;">avc</div>', '<DIV style="color: red;">Upload时必须调用此方法，否则会导致内存泄漏</div>', 1);
		//map.SetCenterAndZoom(_this.x,_this.y)
	};
	
	if(opts && opts.OnClick &&  typeof(opts.OnClick) == "function" ){
		OnClick = opts.OnClick;
	}
	this.OnClick = OnClick;

	this.Destroy = function(){
		width=null;
		height=null;
		img=null;
		title=null;
		zIndex=null;
		groupID = null;
		alignStyle = null;
		maxMapScale = null;
		minZoomLevel = null;
		innerHtml = null;
		OnClick = null;
	}
}

SuperMap.ictmap.NumMarker = function(point, id, num, opts) {
	var _opts = $.extend({width:18,height:20,img:"images/locationImage.gif",zIndex:101,OnClick:OnClick}, opts, {id:id});
	_opts.infowin = $.extend({width:240, height:220, title:"标题", content:""}, opts.infowin);
	var superMarker = new SuperMap.ictmap.Marker(point, _opts);
	$.extend(this, superMarker, opts);

	
	function _init() {
		var backgroundImg = _scriptLocation + "../images/num.png";
		innerHtml = "<div style=\"position:absolute;background:url("+backgroundImg+");width:33px;height:33px;color:#FFFFFF;line-height:33px;text-align:center; vertical-align:middle;font-weight:bold\">"
					+num+"<div style=\"position:absolute;left:0;top:0px;width:33px;height:33px;opacity:0.7;filter:alpha(opacity=80);\"></div>"
					+"</div>";
	}
	_init();
    this.innerHtml = innerHtml;
	
	this.type = "SuperMap.ictmap.Marker";
	var infoWindow = new SuperMap.ictmap.InfoWindow(id, point.x, point.y, _opts.infowin);
	this.infoWindow = infoWindow;
	var OnClick = function(map,callback){
		map.SetAction(new SuperMap.IS.PanAction());
		infoWindow.show(map);
	};
	this.OnClick = OnClick;
	this.Destroy = function(){
		superMarker.Destroy();

	}
	return this;
}

SuperMap.ictmap.NumMarker1 = function(point, id, num, opts) {
	var _opts = $.extend({width:18,height:20,img:"images/locationImage.gif",zIndex:101,OnClick:OnClick}, opts, {id:id});
	_opts.infowin = $.extend({width:240, height:220, title:"标题", content:""}, opts.infowin);
	var superMarker = new SuperMap.ictmap.Marker(point, _opts);
	$.extend(this, superMarker, opts);

	
	function _init() {
		var backgroundImg = _scriptLocation + "../images/num.png";
		innerHtml = "<div style=\"position:absolute;background:url("+backgroundImg+");width:33px;height:33px;color:#FFFFFF;line-height:33px;text-align:center; vertical-align:middle;font-weight:bold\">"
					+"<div style=\"position:absolute;left:0;top:0px;width:33px;height:33px;opacity:0.7;filter:alpha(opacity=80);\"></div>"
					+ "<span style=\"background:"+_opts.backgroundColor+";color:"+_opts.labelColor+"\">"+_opts.name+"</span>"
					+"</div>";
	}
	_init();
    this.innerHtml = innerHtml;
	
	this.type = "SuperMap.ictmap.Marker";
	var infoWindow = new SuperMap.ictmap.InfoWindow(id, point.x, point.y, _opts.infowin);
	this.infoWindow = infoWindow;
	var OnClick = function(map,callback){
		map.SetAction(new SuperMap.IS.PanAction());
		infoWindow.show(map);
	};
	this.OnClick = OnClick;
	this.Destroy = function(){
		superMarker.Destroy();

	}
	return this;
}

SuperMap.ictmap.LocMarker = function(point, id, opts) {
	var _opts = $.extend({width:23,height:25,img:"images/locationImage.gif",labelColor:'blue',backgroundColor:'white',zIndex:101,OnClick:OnClick}, opts, {id:id});
	_opts.infowin = $.extend({width:240, height:220, title:"标题", content:""}, opts.infowin);
	var superMarker = new SuperMap.ictmap.Marker(point, _opts);
	$.extend(this, superMarker, opts);

	function _init() {
		innerHtml = "<img src=\"" + _scriptLocation + "../" + _opts.img + "\" " + "width=\"" + _opts.width +"px\"" + " height=\"" + _opts.height +"px\"/>";
		if(_opts.name) innerHtml += "<span style=\"background:"+_opts.backgroundColor+";color:"+_opts.labelColor+"\">"+_opts.name+"</span>";
	}
	_init();
    this.innerHtml = innerHtml;

	this.type = "SuperMap.ictmap.LocMarker";
	var infoWindow = new SuperMap.ictmap.InfoWindow(id, point.x, point.y, _opts.infowin);
	this.infoWindow = infoWindow;
	var OnClick = function(map,callback){
		map.SetAction(new SuperMap.IS.PanAction());
		infoWindow.show(map);
	};
	this.OnClick = OnClick;
	this.Destroy = function(){
		superMarker.Destroy();

	}
	return this;
}

SuperMap.ictmap.Polyline = function(points, opts){
	this.type = "SuperMap.ictmap.Polyline";
	var defaults = {id:"Polyline", strokeWeight:1.5, strokeColor:"blue", opacity:0, zIndex:1, groupID:"Polyline", parts:null};
	$.extend(this, defaults, opts);
	var xs = new Array();
	var ys = new Array();
	for(var i in points){
		xs.push(points[i].x);
		ys.push(points[i].y);
	}
	this.xs = xs;
	this.ys = ys;
	return this;
}

/**
 * added by luoj,用于历史轨迹中多点一次性画线
 */
SuperMap.ictmap.LocHisPolyline = function(points, id, opts){
	this.type = "SuperMap.ictmap.LocHisPolyline";
	var defaults = {id:"LocHisPolyline", strokeWeight:1.5, strokeColor:opts.strokeColor, opacity:0, zIndex:1, groupID:"LocHisPolyline", parts:null};
	$.extend(this, defaults, opts);
	var xs = new Array();
	var ys = new Array();
	var locPoints = new Array();
	for(var i=0;i<points.length;i++){
		xs.push(points[i].x);
		ys.push(points[i].y);
		var id = "his_p_"+i;
		locPoints.push(new SuperMap.ictmap.NumMarker(points[i], id, i+1, {img:"images/tag_red2.png",display:points[i].display,infowin:{title:"", content:points[i].info}}));
	}
	this.xs = xs;
	this.ys = ys;
	this.locPoints = locPoints;
	return this;
}

SuperMap.ictmap.Label = function(point, id, content, opts){
	var _opts = $.extend({width:23,height:25,img:"images/locationImage.gif",labelColor:'blue',backgroundColor:'white',zIndex:101,OnClick:OnClick,infowin:false}, opts, {id:id});
	_opts.infowin = $.extend({width:240, height:220, title:"标题", content:""}, opts.infowin);
	var superMarker = new SuperMap.ictmap.Marker(point, _opts);
	$.extend(this, superMarker, opts);
	var _this = this;
	function _init() {
		innerHtml = "<img src=\"" + _scriptLocation + "../" + _this.img + "\" " + "width=\"" + _this.width +"px\"" + " height=\"" + _this.height +"px\"/>";
		if(content) innerHtml += "<span style=\"background:"+_opts.backgroundColor+";color:"+_opts.labelColor+"\">"+content+"</span>";
	}
	_init();
    this.innerHtml = innerHtml;

	this.type = "SuperMap.ictmap.Marker";
	var infoWindow = new SuperMap.ictmap.InfoWindow(id, point.x, point.y, _opts.infowin);
	this.infoWindow = infoWindow;
	var OnClick = function(map,callback){
		if(_this.infowin){
			map.SetAction(new SuperMap.IS.PanAction());
			infoWindow.show(map);
		}
	};
	this.OnClick = OnClick;
	this.Destroy = function(){
		superMarker.Destroy();

	}
	return this;
}

SuperMap.ictmap.Polygon = function(points,id,opts){
	this.type = "SuperMap.ictmap.Polygon";
	var defaults = {id:"Polygon", strokeWeight:1.5, strokeColor:"blue", fillColor:"white", fillOpacity:0.5, zIndex:1, groupID:"Polygon", parts:null};
	$.extend(this, defaults, opts);
	var xs = new Array();
	var ys = new Array();
	var n = points.length;
	var xcenter=0;
	var ycenter = 0;
	for(var i=0; i<n; i++){
		xs.push(points[i].x);
		ys.push(points[i].y);
		xcenter += points[i].x / n;
		ycenter += points[i].y / n;
	}
	this.xs = xs;
	this.ys = ys;
	this.xcenter = xcenter;
	this.ycenter = ycenter;
	return this;
}

SuperMap.ictmap.AreaPolygon = function(xs,ys,id,opts){
	this.type = "SuperMap.ictmap.AreaPolygon";
	var defaults = {id:"AreaPolygon", strokeWeight:1.5, strokeColor:"blue", fillColor:"white", fillOpacity:0.5, zIndex:1, groupID:"AreaPolygon", parts:null};
	$.extend(this, defaults, opts);
	this.xs = xs;
	this.ys = ys;
	this.xcenter = 118.845371;
	this.ycenter = 32.0549135;
	this.mapScale = 2e-06;
	return this;
}

/**
 * 画行政区域围栏，by lifa
 * similar to above:SuperMap.ictmap.AreaPolygon
 */
SuperMap.ictmap.RailingArea = function(railingOpts,id,settingOpts){
	this.type = "SuperMap.ictmap.RailingArea";
	var settingDefaults = {id:"RailingArea", strokeWeight:1.5, strokeColor:"blue", fillColor:"white", fillOpacity:0.5, zIndex:1, groupID:"RailingArea", parts:null};
	$.extend(this, settingDefaults, settingOpts);
	this.xs = railingOpts.xs;
	this.ys = railingOpts.ys;
	this.xcenter = railingOpts.xcenter;
	this.ycenter = railingOpts.ycenter;
	this.mapScale = railingOpts.mapScale;
	return this;
}

SuperMap.ictmap.InfoWindow = function(id, x, y, opts){
	var defaults = {width:150, height:100, title:"", content:"", opacity:1, zIndex:102, ajaxLoad:false, url:'',onshow:function(info){}};
	var _opts = $.extend({}, defaults, opts);
	var _show = function(map,callback){
		if(!map) return;
		if(!x) return;
		if(!y) return;
		var infoId = "info_" + id;
		map.CustomLayer.OpenInfoWindow(infoId, x, y, _opts.width, _opts.height, '<div style="color: green;">'+_opts.title+'</div>', '<DIV id=\"' + infoId + '_content' + '\" style="color: green;">' + _opts.content + '</div>', _opts.opacity);
		map.SetCenterAndZoom(x, y, map.GetMapScale());
		_opts.onshow($('#'+infoId).find('#'+infoId + "_content"));
		if(_opts.ajaxLoad){
			if(_opts.url && typeof(_opts.url) != "undefined"){
				$('#'+infoId).find('#'+infoId + "_content").html("正在加载信息...");//.load(url+id);
			}
		}
	}
	this.show = _show;
	return this;
}