// ===============
// 基于超图（SuperMap）地图引擎的自定义工具栏
// @version 1.0
// =============
SuperMap.IS.ToolbarControl = function(container, map, param) {
    var _this = this;
    this.width = 260;
    if (param && typeof(param.width) != "undefined") {
        this.width = param.width;
    }
    this.height = 26;
    if (param && typeof(param.height) != "undefined") {
        this.height = param.height;
    }
    this.ordinal = true;
    if (param && typeof(param.ordinal) != "undefined") {
        _this.ordinal = param.ordinal;
    }
    this.position = 1;
    if (param && typeof(param.position) != "undefined") {
        this.position = param.position;
    }
    var mode = "normal";
    if (param && typeof(param.toolmode) != "undefined") {
    	mode = param.toolmode;
    }
    
    var _div = null;
    
    if (map) {
        map.AttachEvent("oninit", _init);
        map.AttachEvent("ondestroying", _destroying);
        map.AttachEvent('onchangeview', changeView);
    }
    function _init() {
        if (_div) {
            if (_div.parentNode) {
                _div.parentNode.removeChild(_div);
            }
            _div = null;
        };
        
        var toolsWidth = _getToolLen() * 26;
        if(_this.width < toolsWidth){
        	_this.width = toolsWidth + 50;
        }
        
        _div = document.createElement("div");
        _div.style.position = "";
        _div.style.width = _this.width + "px";
        _div.style.height = _this.height + "px";
		_div.style.borderColor="Gray";
		_div.style.borderWidth="1px";
		_div.style.borderStyle="Dashed";
		_div.style.verticalAlign="middle";
		_div.style.textAlign="center";
		_div.style.padding="2px";
        container.appendChild(_div);
        _addTools();
    }
    function _destroying() {
        if (map) {
            map.DetachEvent("oninit", _init);
            map.DetachEvent("ondestroying", _destroying);
            map.DetachEvent('onchangeview', changeView);
        }
        container.innerHTML = "";
        container = null;
        param = null;
		_destroyingTools();
        _this = null;
    }
    
	function _addTools() {
		var modeArr = _toolModeSet[mode];
		for(var i=0;i<modeArr.length;i++){
			creatTool(_tools[modeArr[i]]);
		}
	}
	
	function _destroyingTools() {
		
	}
	
	function creatTool(imgParam) {
		var img = new Image();
        img.onclick = imgParam.onclick;
        _div.appendChild(img);
		if(imgParam.alt && typeof(imgParam.alt) != "undefined"){
			img.alt = imgParam.alt;
		}
		if(imgParam.title && typeof(imgParam.title) != "undefined"){
			img.title = imgParam.title;
		}
		if(imgParam.style && imgParam.style.padding && typeof(imgParam.style.padding) != "undefined"){
			img.style.padding = imgParam.style.padding;
		} else {
			img.style.padding = '0 2px';
		}
		
        img.src = _scriptLocation + '../'+imgParam.imageUrl;
	}
	
	function _getToolLen(){
		var toolLen =  _toolModeSet[mode].length;
		return toolLen;
	}
	
	function onMeasureDistanceStart() { }
	function onMeasureDistanceComplete(result) { }
	function onMeasureDistanceError(result) { }
	function onDrawCenterPointComplete(points){
		var i = 0;
		var x = '';
		var y = '';
		for (i = 0; i < points.length; i++) {
			if (i > 0) {
				x += ',';
				y += ',';
			}
			x += points[i].x;
			y += points[i].y;
		}
		
		var strurl = _contextPath+'/module/map/centerPoint_dialog.do?maptype=supermap&log='+ x +'&lat='+ y +'&laytype=2';
		document.openDialog('saveCenterPoint',{
		    bgiframe: true,
			autoOpen: false,
			width:480,
			height: 250,
			modal: true
		}).load(strurl);
	}
	function onDrawRectComplete(result) {
		//{logs:[mapCoord.x,e.mapCoord.x,e.mapCoord.x,mapCoord.x],lats:[mapCoord.y,mapCoord.y,e.mapCoord.y,e.mapCoord.y]}
		var logs = result.logs;
		var lats = result.lats;
		var mapCoord_x = logs[0];
		var e_mapCoord_x = logs[1];
		var mapCoord_y = lats[0];
		var e_mapCoord_y = lats[2];
			
		var strurl = _contextPath+'/module/map/railings_addCrawlCommon.do';
		strurl = strurl	+'?maptype=supermap&log='+ mapCoord_x+','+e_mapCoord_x + ','+e_mapCoord_x + ','+mapCoord_x +'&lat='+mapCoord_y+','+mapCoord_y+','+e_mapCoord_y+','+e_mapCoord_y+'&laytype=1';
		
	    document.openDialog('saveArea',{
		    bgiframe: true,
			autoOpen: false,
			width:480,
			height: 200,
			modal: true
		}).load(strurl);
	    
	}

	function new_onDrawRectComplete(result) {
		//{logs:[mapCoord.x,e.mapCoord.x,e.mapCoord.x,mapCoord.x],lats:[mapCoord.y,mapCoord.y,e.mapCoord.y,e.mapCoord.y]}
		var logs = result.logs;
		var lats = result.lats;
		var mapCoord_x = logs[0];
		var e_mapCoord_x = logs[1];
		var mapCoord_y = lats[0];
		var e_mapCoord_y = lats[2];
		document.getElementById('new_log').value= mapCoord_x+','+e_mapCoord_x +','+e_mapCoord_x + ','+mapCoord_x;
		document.getElementById('new_lat').value=mapCoord_y+','+mapCoord_y+','+e_mapCoord_y+','+e_mapCoord_y;	
	}

	
	function new_onDrawPolygonComplete(result) {

		document.getElementById('new_log').value= result.x;
		document.getElementById('new_lat').value=result.y ;	
	}
	function onDrawPolygonComplete(result) {
		  var strurl = _contextPath+'/module/map/railings_addCrawlCommon.do' + '?maptype=supermap&log=' + result.x + '&lat=' + result.y + '&laytype=1';
		  
		  document.openDialog('saveArea',{
	    	    bgiframe: true,
	    		autoOpen: false,
	    		width:480,
	    		height: 200,
	    		modal: true
		  }).load(strurl);
	}
	
	var _tools = {
			btn_ViewEntire:{alt:'全地图',title:'全地图',imageUrl:'images/btn_ViewEntire.gif',onclick:function(){map.ViewEntire();}},
			btn_Pan:{alt:'拖动',title:'拖动',imageUrl:'images/btn_Pan.gif',onclick:function(){map.SetAction(new SuperMap.IS.PanAction());}},
			btn_ZoomIn:{alt:'选取放大',title:'选取放大',imageUrl:'images/btn_ZoomIn.gif',onclick:function(){map.SetAction(new SuperMap.IS.ZoomInAction());}},
			btn_ZoomOut:{alt:'选取缩小',title:'选取缩小',imageUrl:'images/btn_ZoomOut.gif',onclick:function(){map.SetAction(new SuperMap.IS.ZoomOutAction());}},
			btn_DrawRect:{alt:'矩形区域',title:'矩形区域',imageUrl:'images/btn_14_on.gif',onclick:function(){map.SetAction(new SuperMap.ictmap.DrawRectAction(onDrawRectComplete));}},
			//btn_DrawPolygon:{alt:'多边区域',title:'多边区域',imageUrl:'images/btn_17_on.gif',onclick:function(){map.SetAction(new SuperMap.ictmap.DrawPolygonAction(onDrawPolygonComplete));}},
			btn_DrawPolygon:{alt:'多边形区域',title:'多边形区域',imageUrl:'images/btn_17_on.gif',onclick:function(){map.SetAction(new SuperMap.ictmap.PolygonQueryAction(onDrawPolygonComplete));}},
			btn_Distance:{alt:'测距',title:'测距',imageUrl:'images/btn_Distance.gif',onclick:function(){map.SetAction(new SuperMap.ictmap.MeasureDistanceAction(onMeasureDistanceStart, onMeasureDistanceComplete, onMeasureDistanceError, ""));}},
			btn_DrawMarkLine:{alt:'画线',title:'画线',imageUrl:'images/line.gif',onclick:function(){map.SetAction(new SuperMap.IS.DrawMarkLineAction('标题','note',true));}},
			btn_DrawCenterPoint:{alt:'中心点设置',title:'中心点设置',imageUrl:'images/btn_13_on.gif', onclick:function(){map.SetAction(new SuperMap.ictmap.PointQueryAction(onDrawCenterPointComplete));}},
			btn_Undo:{alt:'前一视图',title:'前一视图',imageUrl:'images/undo.gif', onclick:function(){undo();}},
			btn_Redo:{alt:'后一视图',title:'后一视图',imageUrl:'images/redo.gif', onclick:function(){redo();}},

			new_btn_DrawRect:{alt:'矩形区域',title:'矩形区域',imageUrl:'images/btn_14_on.gif',onclick:function(){map.SetAction(new SuperMap.ictmap.DrawRectAction(new_onDrawRectComplete));}},
			new_btn_DrawPolygon:{alt:'多边形区域',title:'多边形区域',imageUrl:'images/btn_17_on.gif',onclick:function(){map.SetAction(new SuperMap.ictmap.PolygonQueryAction(new_onDrawPolygonComplete));}},
			
			btn_ClearMap:{alt:'清除标记',title:'清除标记',imageUrl:'images/btn_24_on.gif',onclick:function(){clearMap();}}
			
			/*btn_QuickZoomIn:{alt:'放大',title:'放大',imageUrl:'images/btn_QuickZoomIn.gif', onclick:function(){map.SetAction(new SuperMap.IS.QuickZoomInAction());}},
			btn_QuickZoomOut:{alt:'缩小',title:'缩小',imageUrl:'images/btn_QuickZoomOut.gif', onclick:function(){map.SetAction(new SuperMap.IS.QuickZoomOutAction());}},
			
			btn_AddShap:{alt:'添加标记',title:'添加标记',imageUrl:'images/btn_26_on.gif', onclick:function(){map.SetAction(new SuperMap.IS.AddShapAction());}},
			btn_Print:{alt:'打印',title:'打印',imageUrl:'images/btn_09_on.gif', onclick:function(){map.SetAction(new SuperMap.IS.PrintAction());}}*/
		};
	
	/***********  工具条显示模式 *************/
		var _toolModeSet ={
			full:['btn_ViewEntire','btn_Pan','btn_ZoomIn','btn_ZoomOut','btn_DrawRect','btn_DrawPolygon','btn_Distance','btn_DrawMarkLine','btn_DrawCenterPoint','btn_Undo','btn_Redo','btn_ClearMap'],
			medium:['btn_ViewEntire','btn_Pan','btn_ZoomIn','btn_ZoomOut','btn_DrawRect','btn_DrawPolygon','btn_Distance','btn_DrawMarkLine','btn_ClearMap'],
			normal:['btn_ViewEntire','btn_Pan','btn_ZoomIn','btn_ZoomOut','btn_Distance','btn_ClearMap'],
			normal1:['btn_ViewEntire','btn_Pan','btn_ZoomIn','btn_ZoomOut','btn_Distance','btn_DrawCenterPoint','btn_ClearMap'],
			new_full:['btn_ViewEntire','btn_Pan','btn_ZoomIn','btn_ZoomOut','new_btn_DrawRect','new_btn_DrawPolygon','btn_Undo','btn_Redo','btn_ClearMap'],
			normal2:['btn_ViewEntire','btn_Pan','btn_ZoomIn','btn_ZoomOut','btn_Distance','btn_Undo','btn_Redo','btn_ClearMap']
		}

	

	var MapCenterLatArray = new Array(); 
	var MapCenterLogArray = new Array(); 
	var MapCenterScaleArray = new Array(); 
	var Level = -1;
	var init = -1;//判断地图初始化
	var status = -1;

	//////////////////////////////////////////////////////////地图前放和回放,与lbs.ef.js配合
    //map.AttachEvent('onchangeview', changeView());

    function changeView(){
    	if(status == -1){
    		MapCenterLatArray.push(map.GetMapCenterY());
    		MapCenterLogArray.push(map.GetMapCenterX());
    		MapCenterScaleArray.push(map.GetMapScale());
        }else{
        	status = -1 ;
        }
    }
    
	function redo(){
		status = 0 ;
		
		if(init == -1){//地图初始化时
			Level = MapCenterScaleArray.length - 1;
			init = 0;
			return;
		}else{
			if(Level < MapCenterScaleArray.length-1)
				Level ++;
			else 
				return;
		}
		
		map.SetCenterAndZoom(MapCenterLogArray[Level],MapCenterLatArray[Level] ,MapCenterScaleArray[Level] );
	}

	function undo(){
		status = 0 ;
		
		if(init == -1){//地图初始化时
			Level = MapCenterScaleArray.length - 1;
			init = 0;
			Level--;
		}else{
			if(Level > 0)
				Level --;
			else
				return;
		}
		
		map.SetCenterAndZoom(MapCenterLogArray[Level],MapCenterLatArray[Level] ,MapCenterScaleArray[Level] );
	}
 
	function clearMap(){
		map.CustomLayer.ClearMarks();
		map.CustomLayer.ClearLines();
		map.CustomLayer.RemovePolygon("RectQuery");
		map.CustomLayer.RemovePolygon("PolygonQuery");
		map.CustomLayer.RemovePolygon("CircleQuery");
		map.CustomLayer.RemovePolygon("Polygon");//自定义
	}
}