// ===============
// 基于超图（SuperMap）地图引擎的自定义地图操作
// 实现地图上工具条按钮功能
// @version 1.0
// =============


/**
 * 中心点设置
 **/
SuperMap.ictmap.PointQueryAction = function (callback) {
	this.type = "SuperMap.ictmap.PointQueryAction";
	var Efurl = '';
    var mapDiv = null;
    var zoomRect = null;
    var _cx = 0, _cy = 0, _nx = 0, _ny = 0;
    var originX = 0; originY = 0;
    var actionStarted = false;
    var mapCoord;
    
    this.Init = function(mapControl) {
        this.mapControl = mapControl;
        this.mapControl.CustomLayer.ClearLines();
        this.mapControl.CustomLayer.RemovePolygon("RectQuery");
        this.mapControl.CustomLayer.RemovePolygon("PolygonQuery");
        this.mapControl.CustomLayer.RemovePolygon("CircleQuery");
        this.mapControl.CustomLayer.RemovePolygon("Polygon");//自定义
        mapDiv = mapControl.mapDiv;
        
        if (_ygPos.browser == "ie") { 
        	mapControl.container.style.cursor = "url("+_scriptLocation + "../images/cursors/PointQuery.cur)"; 
        } else { 
        	mapControl.container.style.cursor = "crosshair"; 
        }
        
        zoomRect = document.getElementById('zoomRect');
        if (!zoomRect) {
            zoomRect = document.createElement("div");
            zoomRect.className = "zoomRect";
            _Hide();
            mapDiv.appendChild(zoomRect);
        }
    };
    this.Destroy = function() { 
    	mapDiv.removeChild(zoomRect); 
    	mapDiv = null; 
    	this.mapControl = null; 
    };
    function _OnMouseDown(e) {
        actionStarted = true;
        originX = e.pixelCoord.x - e.offsetCoord.x; 
        originY = e.pixelCoord.y - e.offsetCoord.y;
        _cx = _nx = e.offsetCoord.x; 
        _cy = _ny = e.offsetCoord.y;

        mapCoord = e.mapCoord;

        var array = new Array();
        array.push(mapCoord); 
        //alert(mapCoord.x);
 
        if(callback && typeof(callback) == "function"){
        	callback(array);
		}

        actionStarted = false;
        var MapControl1_SuperMap_IS_PanAction; 
        if (!MapControl1_SuperMap_IS_PanAction) { 
        	MapControl1_SuperMap_IS_PanAction = new SuperMap.IS.PanAction(null, null, null); 
        } 
        this.mapControl.SetAction(MapControl1_SuperMap_IS_PanAction);

    }
    function _OnMouseMove(e) {
        
    }
    function _OnMouseUp(e) {
    	actionStarted = false;
        var MapControl1_SuperMap_IS_PanAction; 
        if (!MapControl1_SuperMap_IS_PanAction) { 
        	MapControl1_SuperMap_IS_PanAction = new SuperMap.IS.PanAction(null, null, null); 
        } 
        this.mapControl.SetAction(MapControl1_SuperMap_IS_PanAction);
        
        _Hide();
    
    }
    function _Draw(x, y, width, height) { _SetPosAndSize(zoomRect, x, y, width, height); }
    function _SetPosAndSize(el, x, y, width, height) { el.style.left = x + "px"; el.style.top = y + "px"; el.style.width = width + "px"; el.style.height = height + "px"; }
    function _Show() { zoomRect.style.display = "block"; }
    function _Hide() { zoomRect.style.display = "none"; }
    function _GetJSON() { return _ActionToJSON(this.type, []); }
    this.OnMouseDown = _OnMouseDown; this.OnMouseMove = _OnMouseMove; this.OnMouseUp = _OnMouseUp; this.GetJSON = _GetJSON;
}

/**
 * 兴趣点设置
 **/
SuperMap.ictmap.InterestPointAction = function (callback) {
	this.type = "SuperMap.ictmap.InterestPointAction";
	var Efurl = '';
    var mapDiv = null;
    var zoomRect = null;
    var _cx = 0, _cy = 0, _nx = 0, _ny = 0;
    var originX = 0; originY = 0;
    var actionStarted = false;
    var mapCoord;
    
    this.Init = function(mapControl) {
        this.mapControl = mapControl;
        this.mapControl.CustomLayer.ClearLines();
        this.mapControl.CustomLayer.RemovePolygon("RectQuery");
        this.mapControl.CustomLayer.RemovePolygon("PolygonQuery");
        this.mapControl.CustomLayer.RemovePolygon("CircleQuery");
        this.mapControl.CustomLayer.RemovePolygon("Polygon");//自定义
        mapDiv = mapControl.mapDiv;
        
        if (_ygPos.browser == "ie") { 
        	mapControl.container.style.cursor = "url("+_scriptLocation + "../images/cursors/PointQuery.cur)"; 
        } else { 
        	mapControl.container.style.cursor = "crosshair"; 
        }
        
        zoomRect = document.getElementById('zoomRect');
        if (!zoomRect) {
            zoomRect = document.createElement("div");
            zoomRect.className = "zoomRect";
            _Hide();
            mapDiv.appendChild(zoomRect);
        }
    };
    this.Destroy = function() { 
    	mapDiv.removeChild(zoomRect); 
    	mapDiv = null; 
    	this.mapControl = null; 
    };
    function _OnMouseDown(e) {
        actionStarted = true;
        originX = e.pixelCoord.x - e.offsetCoord.x; 
        originY = e.pixelCoord.y - e.offsetCoord.y;
        _cx = _nx = e.offsetCoord.x; 
        _cy = _ny = e.offsetCoord.y;

        mapCoord = e.mapCoord;

        var array = new Array();
        array.push(mapCoord); 
        //alert(mapCoord.x);
 
        if(callback && typeof(callback) == "function"){
        	callback(array);
		}

        actionStarted = false;
        var MapControl1_SuperMap_IS_PanAction; 
        if (!MapControl1_SuperMap_IS_PanAction) { 
        	MapControl1_SuperMap_IS_PanAction = new SuperMap.IS.PanAction(null, null, null); 
        } 
        this.mapControl.SetAction(MapControl1_SuperMap_IS_PanAction);

    }
    function _OnMouseMove(e) {
        
    }
    function _OnMouseUp(e) {
    	actionStarted = false;
        var MapControl1_SuperMap_IS_PanAction; 
        if (!MapControl1_SuperMap_IS_PanAction) { 
        	MapControl1_SuperMap_IS_PanAction = new SuperMap.IS.PanAction(null, null, null); 
        } 
        this.mapControl.SetAction(MapControl1_SuperMap_IS_PanAction);
        
        _Hide();
    
    }
    function _Draw(x, y, width, height) { _SetPosAndSize(zoomRect, x, y, width, height); }
    function _SetPosAndSize(el, x, y, width, height) { el.style.left = x + "px"; el.style.top = y + "px"; el.style.width = width + "px"; el.style.height = height + "px"; }
    function _Show() { zoomRect.style.display = "block"; }
    function _Hide() { zoomRect.style.display = "none"; }
    function _GetJSON() { return _ActionToJSON(this.type, []); }
    this.OnMouseDown = _OnMouseDown; this.OnMouseMove = _OnMouseMove; this.OnMouseUp = _OnMouseUp; this.GetJSON = _GetJSON;
}

/**
 * 保存多边形区域
 **/
SuperMap.ictmap.PolygonQueryAction =  function(onComplete, onError, onStart, userContext) {
    this.type = "SuperMap.ictmap.PolygonQueryAction";
    var actionStarted = false;
    var line = null;
    var keyPoints = new Array();
    var xs = new Array();
    var ys = new Array();
    var _self = this;
    var firstMapCoord = null;
    this.Init = function(mapControl) {
        this.mapControl = mapControl;
        this.mapControl.CustomLayer.ClearLines();
        this.mapControl.CustomLayer.RemovePolygon("RectQuery");
        this.mapControl.CustomLayer.RemovePolygon("PolygonQuery");
        this.mapControl.CustomLayer.RemovePolygon("CircleQuery");
        this.mapControl.CustomLayer.RemovePolygon("Polygon");//自定义
        if (_ygPos.browser == "ie") { 
        	mapControl.container.style.cursor = "url("+_scriptLocation + "../images/cursors/PolygonQuery.cur)"; 
        } else {
        	mapControl.container.style.cursor = "crosshair"; 
        };
    };
    this.Destroy = function() { /*this.mapControl.CustomLayer.RemovePolygon("PolygonQuery");*/line = null; this.mapControl = null; };
    this.OnClick = function(e) {
        if (!actionStarted) {
            firstMapCoord = e.mapCoord;
            xs.push(e.mapCoord.x); ys.push(e.mapCoord.y);
        } else {
            xs.pop(); ys.pop();
            xs.push(e.mapCoord.x); ys.push(e.mapCoord.y);
        }
        actionStarted = true;
        keyPoints.push(e.mapCoord);
        xs.push(firstMapCoord.x); ys.push(firstMapCoord.y);

    };
    this.OnDblClick = function(e) {
        keyPoints.push(e.mapCoord);
        keyPoints.push(firstMapCoord);
        xs.pop(); ys.pop();
        xs.push(e.mapCoord.x); ys.push(e.mapCoord.y);
        xs.push(firstMapCoord.x); ys.push(firstMapCoord.y);
        var tempx = "", tempy = "";
        for (var i = 0; i < xs.length - 2; i++) {
            if (i == 0) {
                tempx = xs[i];
                tempy = ys[i];
            }
            else {
                tempx += "," + xs[i];
                tempy += "," + ys[i];
            }
        }

        tempx += "," + xs[0];
        tempy += "," + ys[0];

        this.mapControl.CustomLayer.InsertPolygon("PolygonQuery", xs, ys, 2, "blue", "white", 0.4, 1);

        if(onComplete && typeof(onComplete) == "function"){
        	onComplete({x:tempx, y:tempy});
		}

        keyPoints.length = 0;
        xs.length = 0;
        ys.length = 0;

        actionStarted = false;

        var MapControl1_SuperMap_IS_PanAction; 
        if (!MapControl1_SuperMap_IS_PanAction) { 
        	MapControl1_SuperMap_IS_PanAction = new SuperMap.IS.PanAction(null, null, null); 
        } 
        this.mapControl.SetAction(MapControl1_SuperMap_IS_PanAction);

    };

    this.OnMouseMove = function(e) {
        if (!actionStarted) { return false; }
        keyPoints.pop();
        xs.pop(); ys.pop();
        keyPoints.push(e.mapCoord);
        xs.push(e.mapCoord.x); ys.push(e.mapCoord.y);
        this.mapControl.CustomLayer.InsertPolygon("PolygonQuery", xs, ys, 2, "blue", "white", 0.6, 1);
        //this.mapControl.CustomLayer.InsertLine("MeasureArea",xs,ys,2,"blue");
    };
    this.OnMouseDown = function(e) { };
    this.OnMouseUp = function(e) { };
    this.OnContextMenu = function(e) { };
    this.GetJSON = function() { return _ActionToJSON(this.type, [/*layerNames,returnFields,whereClause,*/onComplete, onError, onStart, userContext]); };
}

/**
 * 保存矩形区域
 **/
SuperMap.ictmap.DrawRectAction = function (callback) {
	this.type = "SuperMap.ictmap.DrawRectAction";
    var mapDiv = null;
    var zoomRect = null;
    var _cx = 0, _cy = 0, _nx = 0, _ny = 0;
    var originX = 0; originY = 0;
    var actionStarted = false;
    var actionpan = false;
    var mapCoord;
    this.Init = function(mapControl) {
        this.mapControl = mapControl;
        this.mapControl.CustomLayer.ClearLines();
        this.mapControl.CustomLayer.RemovePolygon("RectQuery");
        this.mapControl.CustomLayer.RemovePolygon("PolygonQuery");
        this.mapControl.CustomLayer.RemovePolygon("CircleQuery");
        this.mapControl.CustomLayer.RemovePolygon("Polygon");//自定义
        mapDiv = mapControl.mapDiv;
        if (_ygPos.browser == "ie") { 
        	mapControl.container.style.cursor = "url("+_scriptLocation + "../images/cursors/RectQuery.cur)"; 
        } else {
        	mapControl.container.style.cursor = "crosshair"; 
        };
        zoomRect = document.getElementById('zoomRect');
        if (!zoomRect) {
            zoomRect = document.createElement("div");
            zoomRect.className = "zoomRect";
            _Hide();
            mapDiv.appendChild(zoomRect);
        }
    };
    this.Destroy = function() { /*_RemoveEvent(zoomRect,"onmouseup",_OnMouseUp);this.mapControl.CustomLayer.RemovePolygon("RectQuery");*/
		mapDiv.removeChild(zoomRect); 
		mapDiv = null; 
		this.mapControl = null; 
	};
    function _OnMouseDown(e) {
        if (actionpan){
			var MapControl1_SuperMap_IS_PanAction; 
			if (!MapControl1_SuperMap_IS_PanAction) {
				MapControl1_SuperMap_IS_PanAction = new SuperMap.IS.PanAction(null, null, null); 
			} 
			this.mapControl.SetAction(MapControl1_SuperMap_IS_PanAction);
		}
        
        actionStarted = true;
        originX = e.pixelCoord.x - e.offsetCoord.x; originY = e.pixelCoord.y - e.offsetCoord.y;
        _cx = _nx = e.offsetCoord.x; _cy = _ny = e.offsetCoord.y;

        mapCoord = e.mapCoord;
    }
    function _OnMouseMove(e) {
        if (actionpan){
			var MapControl1_SuperMap_IS_PanAction; 
			if (!MapControl1_SuperMap_IS_PanAction) {
				MapControl1_SuperMap_IS_PanAction = new SuperMap.IS.PanAction(null, null, null); 
			}
			this.mapControl.SetAction(MapControl1_SuperMap_IS_PanAction);}
        
        if (!actionStarted) { 
			return; 
		}
        _nx = e.offsetCoord.x; _ny = e.offsetCoord.y;
        _Draw(_Min(_cx, _nx), _Min(_cy, _ny), _Max(1, _Abs(_nx - _cx)), _Max(1, _Abs(_ny - _cy))); _Show();
    }
    
    function _OnMouseUp(e) {

        var xs = new Array();
        var ys = new Array();

        xs.push(mapCoord.x); xs.push(e.mapCoord.x); xs.push(e.mapCoord.x); xs.push(mapCoord.x);
        ys.push(mapCoord.y); ys.push(mapCoord.y); ys.push(e.mapCoord.y); ys.push(e.mapCoord.y);
        this.mapControl.CustomLayer.InsertPolygon("RectQuery", xs, ys, 2, "blue", "white", 0.4, 1);

        actionStarted = false;
        _Hide();
        
        //strurl = Efurl+'?Log='+ mapCoord.x+','+e.mapCoord.x + ','+e.mapCoord.x + ','+mapCoord.x +'&Lat='+mapCoord.y+','+mapCoord.y+','+e.mapCoord.y+','+e.mapCoord.y+'&type=1';
        if(callback && typeof(callback) == "function"){
			//jQuery.timer(10,function (timer){
				callback({logs:[mapCoord.x,e.mapCoord.x,e.mapCoord.x,mapCoord.x],lats:[mapCoord.y,mapCoord.y,e.mapCoord.y,e.mapCoord.y]});
			//	timer.stop();
			//});
		}
		actionpan = true;
		this.OnMouseClick();
    }
    
    this.OnMouseClick = function(e) {
    };
    
    function _Draw(x, y, width, height) { _SetPosAndSize(zoomRect, x, y, width, height); }
    function _SetPosAndSize(el, x, y, width, height) { el.style.left = x + "px"; el.style.top = y + "px"; el.style.width = width + "px"; el.style.height = height + "px"; }
    function _Show() { zoomRect.style.display = "block"; }
    function _Hide() { zoomRect.style.display = "none"; }
    function _GetJSON() { return _ActionToJSON(this.type, []); }
    this.OnMouseDown = _OnMouseDown; this.OnMouseMove = _OnMouseMove; this.OnMouseUp = _OnMouseUp; this.GetJSON = _GetJSON;
}

/**
 * 画多边形区域
 **/
SuperMap.ictmap.DrawPolygonAction=function(title, note, showLabel, hotpic)
{
    this.type="SuperMap.ictmap.DrawPolygonAction";
    var actionStarted=false;
	var keyPoints=new Array();
	var xs=new Array();
	var ys=new Array();
	var xcenter=0;
	var ycenter=0;
	var _showLabel=false;
    var _hotpic=null;
	
	this.Init=function(mapControl)
    {
    	this.mapControl=mapControl;
    	if(_ygPos.browser=="ie")
		{
		    mapControl.container.style.cursor = "url("+_scriptLocation + "../images/cursors/PointQuery.cur)";
		}
		else
		{
		    mapControl.container.style.cursor="crosshair";
		}
		
		if(showLabel != null)
		{
		    _showLabel = showLabel;
		}
		
		if(hotpic == null)
		{
		    _hotpic = _scriptLocation + "../images/hotball.gif";
		}
		else
		{
		    _hotpic = _scriptLocation + "../images/" + hotpic;
		}
	}
	
	this.Destroy=function()
    {
        this.mapControl=null;
        xs=null;
        ys=null;
    }
    
    function _OnClick(e)
    {        
        if(!actionStarted)
        {
		    keyPoints.push(e.mapCoord);
		    xs.push(e.mapCoord.x);ys.push(e.mapCoord.y);
		    actionStarted=true;		    
		}		
		
		keyPoints.push(e.mapCoord);
		xs.push(e.mapCoord.x);ys.push(e.mapCoord.y);
    }
    
    this.OnMouseMove=function(e){
        if(!actionStarted){return false;}
		keyPoints.pop();
		xs.pop();ys.pop();
		keyPoints.push(e.mapCoord);
		xs.push(e.mapCoord.x);ys.push(e.mapCoord.y);
        this.mapControl.CustomLayer.InsertPolygon(title, xs, ys, 2, "blue", "white", 0.6,1);
    }
        
    function _OnContextMenu(e)
    {
        if(_showLabel == true)
        {
            // 多边形几何中心
            var n = keyPoints.length;
            for(var i=0; i<n; i++)
            {
                xcenter += xs[i] / n;
                ycenter += ys[i] / n;
            }
            
           // this.mapControl.CustomLayer.InsertMark("markPoint", xcenter, ycenter, null, null, "<div><img src='" + _hotpic + "' style='border:0px; cursor:pointer' title='" + note + "' /><span>" + title + "</span></div>");
        }
        
        this.mapControl.SetAction(null);
    }
    
    function _GetJSON()
    {
        return _ActionToJSON(this.type,[]);
    }

    this.OnClick=_OnClick;
    this.OnContextMenu=_OnContextMenu;
    this.GetJSON=_GetJSON;
}

/**
 * 测距
 **/
SuperMap.ictmap.MeasureDistanceAction = function(onComplete, onError, onStart, userContext) {
    var DistanceIndex = 0;
	var Distance = 0;
	this.type = "SuperMap.ictmap.MeasureDistanceAction";
    var actionStarted = false;
    var line = null;
    var keyPoints = new Array();
    var xs = new Array();
    var ys = new Array();
    var xsM = new Array();
    var ysM = new Array();
	var _mdMarks = new Array();
    this.Init = function(mapControl) {
        this.mapControl = mapControl;
        if (_ygPos.browser == "ie") { 
			mapControl.container.style.cursor = "url("+_scriptLocation + "../images/cur_MeasureDistance.cur)"; 
		} else { 
			mapControl.workLayer.style.cursor = "url("+_scriptLocation + "../images/cur_MeasureDistance.cur),auto"; 
		};
    };
    this.Destroy = function() {
		actionStarted = false; 
		this.mapControl.CustomLayer.RemoveLine("MeasureDistance");
		while(_mdMarks && _mdMarks.length>0){
			var _mdMark = _mdMarks.pop();
			this.mapControl.CustomLayer.RemoveMark(_mdMark);
		}
		line = null; 
		var keyPoints = null; 
		this.mapControl = null; 
	};

    this.OnClick = function(e) { };
    this.OnDblClick = function(e) { };

    this.OnMouseMove = function(e) {
        if (!e) e = window.event;
        if (!actionStarted) { return false; }
        keyPoints.pop();
        xs.pop(); ys.pop();
        keyPoints.push(e.mapCoord);
        xs.push(e.mapCoord.x); ys.push(e.mapCoord.y);
        this.mapControl.CustomLayer.InsertLine("MeasureDistance1", xs, ys, 2, "#afacaa", 99);

        var scriptstring = "";
        scriptstring += '<div style="position:absolute; width:auto;left:10px;top:-10px; height:auto; z-index:1;">';
        scriptstring += '<table cellpadding="0" cellspacing="0"  style="width:auto; height:auto;background-color: #FFFFCA;border: #000000 1px solid; font-size:12px;">';
        scriptstring += '<tr><td>' + "单击右键结束" + '</td></tr>';
        scriptstring += '</table></div>';

        this.mapControl.CustomLayer.InsertMark("Tip", e.mapCoord.x, e.mapCoord.y, 10, 10, scriptstring, "", 200);
    };
    this.OnMouseUp = function(e) {
        if (!e) e = window.event;
        if (e.button == 1) {
            if (!actionStarted) {
                keyPoints.push(e.mapCoord);
                xs.push(e.mapCoord.x); ys.push(e.mapCoord.y);
                xsM.push(e.mapCoord.x); ysM.push(e.mapCoord.y);
                for (var i = 0; i < DistanceIndex; i++) {
                    this.mapControl.CustomLayer.RemoveMark("MeasureDistance_"+i);
                }
            }
            actionStarted = true;
            keyPoints.push(e.mapCoord);
            xs.push(e.mapCoord.x); ys.push(e.mapCoord.y);
            xsM.push(e.mapCoord.x); ysM.push(e.mapCoord.y);
            this.mapControl.CustomLayer.InsertLine("MeasureDistance", xsM, ysM, 2, "blue", 100);
            //显示距离
            InsertDistance(this.mapControl,xs, ys);
        }
        if (e.button == 2) {
            if (!actionStarted) { return false; }
            this.mapControl.CustomLayer.RemoveLine("MeasureDistance1");
            this.mapControl.CustomLayer.RemoveMark("Tip");

            keyPoints.push(e.mapCoord);
            xs.push(e.mapCoord.x); ys.push(e.mapCoord.y);
            xsM.push(e.mapCoord.x); ysM.push(e.mapCoord.y);


            this.mapControl.CustomLayer.InsertLine("MeasureDistance", xsM, ysM, 2, "blue", 100); 
			InsertDistance(this.mapControl, xs, ys);

            while (keyPoints.length > 0) {
                keyPoints.pop();
                xs.pop(); ys.pop();
                xsM.pop(); ysM.pop();
            }
            actionStarted = false;

        }
    };
    this.OnMouseDown = function(e) { };
    this.OnContextMenu = function(e) { };
    this.GetJSON = function() { return _ActionToJSON(this.type, [onComplete, onError, onStart, userContext]); };

	function rad(d) {
		var temp = d * Math.PI / 180.0;
		return temp;
	}

	function GetDistance(lat1, lng1, lat2, lng2){//lat 是纬度(latitude)，lng 是经度(longitude)
		var EARTH_RADIUS = 6378137;
		var radLat1 = rad(lat1);
		var radLat2 = rad(lat2);
		var a = radLat1 - radLat2;
		var b = rad(lng1) - rad(lng2);
		var s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
		Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}

	function InsertDistance(mapControl,xs, ys) {
		DistanceIndex = 0;
		if (xs.length > 0) {
			var DistanceL = 0;
			for (var i = 0; i < xs.length; i++) {
				if (i == 0) {
				}
				else {
					var L = GetDistance(ys[i - 1], xs[i - 1], ys[i], xs[i]);
					if (L == 0) {
						return;
					}
					DistanceL += L;
					var scriptstring = "";
					scriptstring += '<div style="position:absolute; width:auto;left:10px;white-space:nowrap; height:auto; z-index:1;">';
					scriptstring += '<div style="width:auto; height:auto;left:10px;line-height:12px;white-space:nowrap;background-color: #FFFFCA;border: #000000 1px solid; font-size:9px;">';
					if (DistanceL > 1000) {
						scriptstring += Math.round(parseFloat(DistanceL)) / 1000 + ' 千米';
					}
					else {
						scriptstring += Math.round(DistanceL) + ' 米';
					}
					scriptstring += '</div></div>';
					mapControl.CustomLayer.InsertMark("MeasureDistance_"+DistanceIndex, xs[i], ys[i], 10, 10, scriptstring, "", 200);
					_mdMarks.push("MeasureDistance_"+DistanceIndex);
				}
				DistanceIndex ++;
			}
		}
	}
};

/**
 * 画圆形，返回圆心经纬度和圆上一点的经纬度
 * add by gehengzhi
 */
SuperMap.ictmap.CircleAction = function(callback) {
    this.type = "SuperMap.ictmap.CircleAction";
    var actionStarted = false;
    var line = null;
    var firstMapCoord = null;
    var curMapCoord = null;
    var firstPoint_x;
    var firstPoint_y;
    var curPoint_x;
    var curPoint_y;
    var mapNode; //地图所在的结点
    var mapLeft = 0; //得到地图所在的绝对位置
    var mapTop = 0;
    var _self = this;
    this.Init = function(mapControl) {
        this.mapControl = mapControl;
        map = this.mapControl;
        if (_ygPos.browser == "ie") {
        	mapControl.container.style.cursor = "url("+_scriptLocation + "../images/cur_CIRCLEQUERY.cur)";
        } else {
        	mapControl.workLayer.style.cursor = "url(images/cur_CIRCLEQUERY.cur),auto"; 
        }
        mapNode = this.mapControl.container;
        while (mapNode != null) {
            mapLeft += mapNode.offsetLeft;
            mapTop += mapNode.offsetTop;
            mapNode = mapNode.offsetParent;
        }
    };

    this.Destroy = function() { SMISRemoveCircle(); this.mapControl = null; };
    this.OnClick = function(e) { };
    this.OnDblClick = function(e) { };
    this.OnMouseDown = function(e) {
        actionStarted = true;
        lastMapCoord = e.mapCoord;
    	firstPoint_x = e.clientX - mapLeft;
    	firstPoint_y = e.clientY - mapTop;
        if (document.documentElement) {
            firstPoint_x = e.clientX - mapLeft + document.documentElement.scrollLeft;
            firstPoint_y = e.clientY - mapTop + document.documentElement.scrollTop;
        }
        else if (document.body) {
            firstPoint_x = e.clientX - mapLeft + document.body.scrollLeft;
            firstPoint_y = e.clientY - mapTop + document.body.scrollTop;
        }
    };
    this.OnMouseMove = function(e) {
        if (!actionStarted) { return false; }
        curPoint_x = e.clientX - mapLeft;
        curPoint_y = e.clientY - mapTop;
        if (document.documentElement) {
            curPoint_x = e.clientX - mapLeft + document.documentElement.scrollLeft;
            curPoint_y = e.clientY - mapTop + document.documentElement.scrollTop;
        }
        else if (document.body) {
            curPoint_x = e.clientX - mapLeft + document.body.scrollLeft;
            curPoint_y = e.clientY - mapTop + document.body.scrollTop;
        }
        curMapCoord = e.mapCoord;
        SMISDrawingCircle(firstPoint_x, firstPoint_y, curPoint_x, curPoint_y);
        //this.mapControl.CustomLayer.InsertPolygon("RectQuery", xs, ys, 2, "blue", "white", 0.6,1);
    };
    this.OnMouseUp = function(e) {
        curMapCoord = e.mapCoord;
        SMISOnMouseUp(this.mapControl);
        var MapControl1_SuperMap_IS_PanAction; 
        if (!MapControl1_SuperMap_IS_PanAction) { 
        	MapControl1_SuperMap_IS_PanAction = new SuperMap.IS.PanAction(null, null, null); 
        } 
        this.mapControl.SetAction(MapControl1_SuperMap_IS_PanAction);

        if(callback && typeof(callback) == "function"){
        	var rad = Math.sqrt(Math.pow((curMapCoord.x - lastMapCoord.x), 2) + Math.pow((curMapCoord.y - lastMapCoord.y), 2))
			callback({x1:lastMapCoord.x,y1:lastMapCoord.y,x2:curMapCoord.x,y2:curMapCoord.y,radius : rad});
		}
    };
    this.OnContextMenu = function(e) { };
    function SMISDrawingCircle(startPoint_x, startPoint_y, curPoint_x, curPoint_y) {
        var circle = document.getElementById("SMISCircle");
        var m_drawLayer;
        if (_ygPos.browser != "ie" || !_EnableVML()) {
            if (!circle) {
                circle = document.createElement("div");
                circle.id = "SMISCircle";
                circle.style.position = "absolute";
                circle.style.left = "0px";
                circle.style.top = "0px";
                circle.unselectable = "on";
                circle.onmouseup = SMISOnMouseUp;
                _self.mapControl.container.appendChild(circle); //将圆画在地图所在的控件
                var m_jg = new jsGraphics("SMISCircle");
                circle.jg = m_jg;
            }
            else {
                circle.jg.clear();
            }
            circle.jg.setColor("blue"); circle.jg.setStroke(2);
            circle.style.zIndex = 2000;
            circle.style.opacity = 0.5;
            var radius = Math.sqrt(Math.pow((curPoint_x - startPoint_x), 2) + Math.pow((curPoint_y - startPoint_y), 2))
            circle.jg.drawEllipse(startPoint_x - radius, startPoint_y - radius, radius * 2, radius * 2);
            circle.jg.paint();
            return;
        }
        
        if (!circle) {
            _EnableVML();
            circle = document.createElement("<v:arc startangle='0' endangle='360'/>");
            circle.style.position = "absolute";
            circle.style.visibility = 'visible';
            circle.id = "SMISCircle";
            circle.style.zIndex = 1000;
            //circle.style.zIndex = map.parentElement.style.zIndex + 200;
            var fill = document.createElement("<v:fill opacity=0.3></v:fill>");
            var stroke = document.createElement("<v:stroke dashstyle='solid' Color='blue'></v:stroke>");
            _self.mapControl.container.appendChild(circle);
            circle.appendChild(fill);
            circle.appendChild(stroke);
        }
        var radius = Math.sqrt(Math.pow((curPoint_x - startPoint_x), 2) + Math.pow((curPoint_y - startPoint_y), 2))
        circle.style.left = (startPoint_x - radius) + "px";
        circle.style.top = (startPoint_y - radius) + "px";
        circle.style.width = 2 * radius + "px";
        circle.style.height = circle.style.width;
    }
    function SMISOnMouseUp() {
        actionStarted = false;
        //SMISRemoveCircle();

//        //客户端查询前事件
//        var qe = new SuperMap.IS.QueryingEventArgs();
//        qe.queryParams = new SuperMap.IS.QueryParam();
//        qe.queryParams = _queryParam; //point
//        qe.clientActionArgs = new SuperMap.IS.ActionEventArgs();
//        qe.clientActionArgs.mapCoords = new Array();
//        qe.clientActionArgs.mapCoords[0] = lastMapCoord;
//        qe.clientActionArgs.mapCoords[1] = curMapCoord;
//        if (onStart) { onStart(qe, userContext); }
//        //服务器查询前事件
//        _self.mapControl.TriggerServerStartingEvent("Querying", qe, QueryByCircle);

    };

    function QueryByCircle(eJSON) {
        var eJ = eval("(" + eJSON + ")");
        var qe = new SuperMap.IS.QueryingEventArgs();
        qe.FromJSON(eJ);
        var startPoint = qe.clientActionArgs.mapCoords[0];
        var endPoint = qe.clientActionArgs.mapCoords[1];
        var left;
        var bottom;
        var right;
        var top;
        radius = Math.sqrt(Math.pow((endPoint.x - startPoint.x), 2) + Math.pow((endPoint.y - startPoint.y), 2));
        if (_self.mapControl != null) {
            _self.mapControl.GetQueryManager().QueryByCircle(startPoint, radius, qe.queryParams, onComplete, onError, userContext);
        }
    }
    function SMISRemoveCircle() {
        var circle = document.getElementById("SMISCircle");
        if (circle) { _self.mapControl.container.removeChild(circle); }
        circle = null;
    };
    this.GetJSON = function() { return _ActionToJSON(this.type, [layerNames, returnFields, whereClause, onComplete, onError, onStart, userContext]); };
};