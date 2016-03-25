
function DrawRect() {
    var action = new RectQuery();
    polygonStyle = 0;
    MapControl1.SetAction(action);

}

var RectQuery = function() {
    this.type = "RectQuery";
    var mapDiv = null;
    var zoomRect = null;
    var _cx = 0, _cy = 0, _nx = 0, _ny = 0;
    var originX = 0; originY = 0;
    var actionStarted = false;
    var actionpan = false;
    var mapCoord;
    var newWindow;
    this.Init = function(mapControl) {
        this.mapControl = mapControl;
        this.mapControl.CustomLayer.ClearLines();
        this.mapControl.CustomLayer.RemovePolygon("RectQuery");
        this.mapControl.CustomLayer.RemovePolygon("PolygonQuery");
        this.mapControl.CustomLayer.RemovePolygon("CircleQuery");
        mapDiv = mapControl.mapDiv;
        if (_ygPos.browser == "ie") { mapControl.container.style.cursor = _scriptLocation + "../images/cursors/RectQuery.cur"; } else { mapControl.container.style.cursor = "crosshair"; };
        zoomRect = document.getElementById('zoomRect');
        if (!zoomRect) {
            zoomRect = document.createElement("div");
            zoomRect.className = "zoomRect";
            _Hide();
            //    		_AddEvent(zoomRect,"onmouseup",_OnMouseUp);
            mapDiv.appendChild(zoomRect);
        }
    };
    this.Destroy = function() { /*_RemoveEvent(zoomRect,"onmouseup",_OnMouseUp);this.mapControl.CustomLayer.RemovePolygon("RectQuery");*/mapDiv.removeChild(zoomRect); mapDiv = null; this.mapControl = null; };
    function _OnMouseDown(e) {
        if (actionpan){var MapControl1_SuperMap_IS_PanAction; if (!MapControl1_SuperMap_IS_PanAction) { MapControl1_SuperMap_IS_PanAction = new SuperMap.IS.PanAction(null, null, null); } MapControl1.SetAction(MapControl1_SuperMap_IS_PanAction);}
        
        actionStarted = true;
        originX = e.pixelCoord.x - e.offsetCoord.x; originY = e.pixelCoord.y - e.offsetCoord.y;
        _cx = _nx = e.offsetCoord.x; _cy = _ny = e.offsetCoord.y;

        mapCoord = e.mapCoord;
        //		_Draw(_cx,_cy,1,1);_Show();
    }
    function _OnMouseMove(e) {
        if (actionpan){var MapControl1_SuperMap_IS_PanAction; if (!MapControl1_SuperMap_IS_PanAction) { MapControl1_SuperMap_IS_PanAction = new SuperMap.IS.PanAction(null, null, null); } MapControl1.SetAction(MapControl1_SuperMap_IS_PanAction);}
        
        if (!actionStarted) { return; }
        _nx = e.offsetCoord.x; _ny = e.offsetCoord.y;
        _Draw(_Min(_cx, _nx), _Min(_cy, _ny), _Max(1, _Abs(_nx - _cx)), _Max(1, _Abs(_ny - _cy))); _Show();
    }
    
    var strurl;
    var sFeatures;
    
    function _OnMouseUp(e) {

        var xs = new Array();
        var ys = new Array();

        xs.push(mapCoord.x); xs.push(e.mapCoord.x); xs.push(e.mapCoord.x); xs.push(mapCoord.x);
        ys.push(mapCoord.y); ys.push(mapCoord.y); ys.push(e.mapCoord.y); ys.push(e.mapCoord.y);
        this.mapControl.CustomLayer.InsertPolygon("RectQuery", xs, ys, 2, "blue", "white", 0.4, 1);

        //alert(e.mapCoord.x + ','+ e.mapCoord.y+','+mapCoord.x+','+mapCoord.y);
        
        actionStarted = false;
        _Hide();
        
           
        
        strurl = Efurl+'?Log='+ mapCoord.x+','+e.mapCoord.x + ','+e.mapCoord.x + ','+mapCoord.x +'&Lat='+mapCoord.y+','+mapCoord.y+','+e.mapCoord.y+','+e.mapCoord.y+'&type=1';
        sFeatures = 'height='+Efh+',width='+Efw+',top=100,left=100,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no';
        
        jQuery.timer(10,function (timer){

        RectEF();
        timer.stop();
        });
//        
//        newWindow.focus();
        //var MapControl1_SuperMap_IS_PanAction; if (!MapControl1_SuperMap_IS_PanAction) { MapControl1_SuperMap_IS_PanAction = new SuperMap.IS.PanAction(null, null, null); } MapControl1.SetAction(MapControl1_SuperMap_IS_PanAction);
       actionpan = true;
       this.OnMouseClick();
    }
    
    function RectEF()
    {
        newWindow = window.open (strurl, '电子围栏',sFeatures);
    }
    
    this.OnMouseClick = function(e)
    {
//        newWindow = window.open (strurl, '电子围栏',sFeatures);
   // alert('a');
//    dwindow.blur();
//        newWindow.focus();
    }
    
    function _Draw(x, y, width, height) { _SetPosAndSize(zoomRect, x, y, width, height); }
    function _SetPosAndSize(el, x, y, width, height) { el.style.left = x + "px"; el.style.top = y + "px"; el.style.width = width + "px"; el.style.height = height + "px"; }
    function _Show() { zoomRect.style.display = "block"; }
    function _Hide() { zoomRect.style.display = "none"; }
    function _GetJSON() { return _ActionToJSON(this.type, []); }
    this.OnMouseDown = _OnMouseDown; this.OnMouseMove = _OnMouseMove; this.OnMouseUp = _OnMouseUp; this.GetJSON = _GetJSON;
};
   


function DrawPolygon() {
    var action = new PolygonQuery();
    polygonStyle = 0;
    MapControl1.SetAction(action);

}

var PolygonQuery = function(onComplete, onError, onStart, userContext) {
    this.type = "PolygonQuery";
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
        if (_ygPos.browser == "ie") { mapControl.container.style.cursor = _scriptLocation + "../images/cursors/PolygonQuery.cur"; } else { mapControl.container.style.cursor = "crosshair"; };
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


        //		alert(tempx);
        //		alert(tempy);

        this.mapControl.CustomLayer.InsertPolygon("PolygonQuery", xs, ys, 2, "blue", "white", 0.4, 1);

        var strurl = Efurl + '?Log=' + tempx + '&Lat=' + tempy + '&type=1';
        //alert(Efh);
        var sFeatures = 'height=' + Efh + ',width=' + Efw + ',top=100,left=100,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no';
        window.open(strurl, '电子围栏', sFeatures);


        //this.mapControl.CustomLayer.ClearPolygons();
        //this.mapControl.CustomLayer.ClearLines();

        keyPoints.length = 0;
        xs.length = 0;
        ys.length = 0;

        actionStarted = false;

        var MapControl1_SuperMap_IS_PanAction; if (!MapControl1_SuperMap_IS_PanAction) { MapControl1_SuperMap_IS_PanAction = new SuperMap.IS.PanAction(null, null, null); } MapControl1.SetAction(MapControl1_SuperMap_IS_PanAction);




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
};


function DrawLine(complete) {
    var action = new LineQuery(null, null, null, null, complete);
    polygonStyle = 0;
    MapControl1.SetAction(action);
}



function showSavArea(x, y) {
    var strurl = Efurl + '?Log=' + x + '&Lat=' + y + '&type=0';
    //alert(Efh);
    var sFeatures = 'height='+Efh+',width='+Efw+',top=100,left=100,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no';
    window.open (strurl, '电子围栏',sFeatures);
}




LineQuery = function(onComplete, onError, onStart, userContext, tempfunc) {
    this.type = "LineQueryAction";
    var actionStarted = false;
    var keyPoints = new Array();
    var xs = new Array();
    var ys = new Array();
    var _self = this;
    this.Init = function(mapControl) {
        this.mapControl = mapControl;
        this.mapControl.CustomLayer.ClearLines();
        this.mapControl.CustomLayer.RemovePolygon("RectQuery");
        this.mapControl.CustomLayer.RemovePolygon("PolygonQuery");
        this.mapControl.CustomLayer.RemovePolygon("CircleQuery");
        if (_ygPos.browser == "ie") { mapControl.container.style.cursor = _scriptLocation + "../images/cursors/PointQuery.cur"; } else { mapControl.container.style.cursor = "crosshair"; };

    };
    this.Destroy = function() {
        //this.mapControl.CustomLayer.RemoveLine("QueryByLine");
        this.mapControl = null;
    };

    this.OnClick = function(e) {
        //客户端查询前事件
        if (!actionStarted) {
            keyPoints.push(e.mapCoord);
            xs.push(e.mapCoord.x); ys.push(e.mapCoord.y);
        }
        actionStarted = true;
        keyPoints.push(e.mapCoord);
        xs.push(e.mapCoord.x); ys.push(e.mapCoord.y);
    };

    this.OnDblClick = function(e) {
        if (!actionStarted) { return false; }
        keyPoints.push(e.mapCoord);
        xs.push(e.mapCoord.x); ys.push(e.mapCoord.y);

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

        actionStarted = false;

        var MapControl1_SuperMap_IS_PanAction; if (!MapControl1_SuperMap_IS_PanAction) { MapControl1_SuperMap_IS_PanAction = new SuperMap.IS.PanAction(null, null, null); } MapControl1.SetAction(MapControl1_SuperMap_IS_PanAction);

        if (tempfunc) {
            tempfunc(tempx, tempy);
        }
        keyPoints.length = 0;
        xs.length = 0;
        ys.length = 0;
    };

    this.OnMouseMove = function(e) {
        if (!actionStarted) { return false; }
        keyPoints.pop();
        xs.pop(); ys.pop();
        keyPoints.push(e.mapCoord);
        xs.push(e.mapCoord.x); ys.push(e.mapCoord.y);
        this.mapControl.CustomLayer.InsertLine("QueryByLine", xs, ys, 2, "blue");
    };
    this.OnMouseDown = function(e) { };
    this.OnMouseUp = function(e) { };
    this.OnContextMenu = function(e) { };
    this.GetJSON = function() { return _ActionToJSON(this.type, [/*layerNames,returnFields,whereClause,*/onComplete, onError, onStart, userContext]); }
}

function DrawPoint(completeEvent) {
    var action = new PintQuery(completeEvent);
    polygonStyle = 0;
    MapControl1.SetAction(action);
}


var PintQuery = function(completeEvent) {
    this.type = "PointQuery";
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
        mapDiv = mapControl.mapDiv;
        if (_ygPos.browser == "ie") { mapControl.container.style.cursor = _scriptLocation + "../images/cursors/PointQuery.cur"; } else { mapControl.container.style.cursor = "crosshair"; };
        zoomRect = document.getElementById('zoomRect');
        if (!zoomRect) {
            zoomRect = document.createElement("div");
            zoomRect.className = "zoomRect";
            _Hide();
            //    		_AddEvent(zoomRect,"onmouseup",_OnMouseUp);
            mapDiv.appendChild(zoomRect);
        }
    };
    this.Destroy = function() { /*_RemoveEvent(zoomRect,"onmouseup",_OnMouseUp);this.mapControl.CustomLayer.RemovePolygon("RectQuery");*/mapDiv.removeChild(zoomRect); mapDiv = null; this.mapControl = null; };
    function _OnMouseDown(e) {
        actionStarted = true;
        originX = e.pixelCoord.x - e.offsetCoord.x; originY = e.pixelCoord.y - e.offsetCoord.y;
        _cx = _nx = e.offsetCoord.x; _cy = _ny = e.offsetCoord.y;

        mapCoord = e.mapCoord;

        var array = new Array();
        array.push(mapCoord);
        //alert(mapCoord.x);

        //alert('complete');
        if(completeEvent)
            completeEvent(array);

        actionStarted = false;
        var MapControl1_SuperMap_IS_PanAction; if (!MapControl1_SuperMap_IS_PanAction) { MapControl1_SuperMap_IS_PanAction = new SuperMap.IS.PanAction(null, null, null); } MapControl1.SetAction(MapControl1_SuperMap_IS_PanAction);

    }
    function _OnMouseMove(e) {
        
    }
    function _OnMouseUp(e) {

      
        actionStarted = false;

        var MapControl1_SuperMap_IS_PanAction; if (!MapControl1_SuperMap_IS_PanAction) { MapControl1_SuperMap_IS_PanAction = new SuperMap.IS.PanAction(null, null, null); } MapControl1.SetAction(MapControl1_SuperMap_IS_PanAction);

        
        _Hide();
    }
    function _Draw(x, y, width, height) { _SetPosAndSize(zoomRect, x, y, width, height); }
    function _SetPosAndSize(el, x, y, width, height) { el.style.left = x + "px"; el.style.top = y + "px"; el.style.width = width + "px"; el.style.height = height + "px"; }
    function _Show() { zoomRect.style.display = "block"; }
    function _Hide() { zoomRect.style.display = "none"; }
    function _GetJSON() { return _ActionToJSON(this.type, []); }
    this.OnMouseDown = _OnMouseDown; this.OnMouseMove = _OnMouseMove; this.OnMouseUp = _OnMouseUp; this.GetJSON = _GetJSON;
};


var showSaveEF = function(points) {
    //alert(1);
    var i = 0;
    var x = '';
    var y = '';
//    alert(points[0].x);
//    alert(points[0].y);
//    alert(points.length);
    for (i = 0; i < points.length; i++) {
        if (i > 0) {
            x += ',';
            y += ',';
        }
//        alert(points[i].x);
//        alert(points[i].y);
        x += points[i].x;
        y += points[i].y;
    }
jQuery.timer(10,function (timer){

        var strurl = Efurl+'?Log='+ x +'&Lat='+ y +'&type=2';
        var sFeatures = 'height='+Efh+',width='+Efw+',top=100,left=100,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no';
        window.open (strurl, '电子围栏',sFeatures);
        
        timer.stop();
        });

}



function SetPanAction() {
    //	if (!panAction) {
    var panAction = new PanAction();
    //	}
    MapControl1.SetAction(panAction);
}

PanAction = function() {
    this.type = "PanAction";
    var actionStarted = false;
    var lastMouseX, lastMouseY;
    this.Init = function(mapControl) {
        this.mapControl = mapControl;
        this.mapControl.CustomLayer.ClearLines();
        this.mapControl.CustomLayer.RemovePolygon("RectQuery");
        this.mapControl.CustomLayer.RemovePolygon("PolygonQuery");
        this.mapControl.CustomLayer.RemovePolygon("CircleQuery");
        this.mapControl.CustomLayer.RemoveMark("img");
        if (_ygPos.browser == "ie") { mapControl.container.style.cursor = _scriptLocation + "../images/cursors/Pan.cur"; } else { mapControl.workLayer.style.cursor = "move"; };
    };
    this.Destroy = function() { this.mapControl = null; };
    function _OnMouseDown(e) {

        actionStarted = true;
        lastMouseX = _GetMouseX(e); lastMouseY = _GetMouseY(e);
        if (this.mapControl.container.setCapture) { this.mapControl.container.setCapture(); }
    }
    function _OnMouseMove(e) {

        if (!actionStarted) { return; }
        var mouseX = _GetMouseX(e); var mouseY = _GetMouseY(e);
        this.mapControl.Pan(lastMouseX - mouseX, lastMouseY - mouseY);
        lastMouseX = mouseX; lastMouseY = mouseY;
    }
    function _OnMouseUp(e) {
        actionStarted = false;
        this.mapControl.StopDynamicPan();
        if (this.mapControl.container.releaseCapture) { this.mapControl.container.releaseCapture(); }
    }
    function _GetJSON() { return _ActionToJSON(this.type, []); }
    this.OnMouseDown = _OnMouseDown; this.OnMouseMove = _OnMouseMove; this.OnMouseUp = _OnMouseUp; this.GetJSON = _GetJSON;
};