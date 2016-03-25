/// <reference name="MicrosoftAjax.js"/>

//var _fixX = 0.0055;
//var _fixY = -0.0014;
var _fixX = 0.00;
var _fixY = -0.00;
//var _fixX = 0;
//var _fixY = 0;

function Drawmark(name, x, y, remark) {
    //    x += _fixX;
    //    y += _fixY;
    var mc = new SuperMap.IS.MapCoord(x, y);
    if (si) {
        MapControl1.CustomLayer.AddMark(name, mc.x, mc.y, null, null, '<div id="l' + name + '" style="font-size:' + size + ';text-align:left;font-family:' + font + '"><img src="./images/gaim.png"><br>' + remark + '</div>');
    }
     else {
         MapControl1.CustomLayer.AddMark(name, mc.x, mc.y, null, null, '<div id="l' + name + '" style="font-size:' + size + ';text-align:left;font-family:' + font + '">' + remark + '</div>');
    }
}

function DrawHuman(name, x, y, remark1, remark2) {
    //    x += _fixX;
    //    y += _fixY;
    var mc = new SuperMap.IS.MapCoord(x, y);

    if (si) {
        MapControl1.CustomLayer.AddMark(name, mc.x, mc.y, null, null, '<div style="font-size:' + size + ';text-align:left;font-family:' + font + ';"><table border=0><tr><td><img src="./images/Globalguy001.png"></td><td></td></tr><tr><td></td><td style="background-color:' + color + '" onmouseover="show(l' + name + ')" onmouseout="hide(l' + name + ')">' + remark1 + '</td></tr><tr id="l' + name + '" style="display:none"><td></td><td style="background-color:' + color + '">' + remark2 + '</td></tr></table></div>');
    }
    else {
        MapControl1.CustomLayer.AddMark(name, mc.x, mc.y, null, null, '<div style="font-size:' + size + ';text-align:left;font-family:' + font + ';"><table border=0><tr><td></td><td style="background-color:' + color + '" onmouseover="show(l' + name + ')" onmouseout="hide(l' + name + ')">' + remark1 + '</td></tr><tr id="l' + name + '" style="display:none"><td></td><td style="background-color:' + color + '">' + remark2 + '</td></tr></table></div>');
    }
}


function Drawline(name, x1, y1, x2, y2) {
    //    x1 += _fixX;
    //    x2 += _fixX;
    //    y1 += _fixY;
    //    y2 += _fixY;
    var mc1 = new SuperMap.IS.MapCoord(x1, y1);
    var mc2 = new SuperMap.IS.MapCoord(x2, y2);
    var xs = new Array();
    xs.push(mc1.x);
    xs.push(mc2.x);

    var ys = new Array();
    ys.push(mc1.y);
    ys.push(mc2.y);

    MapControl1.CustomLayer.InsertLine(name, xs, ys, 1.5, "blue", 0, "1", "1");
}

function Drawline(name, x1, y1, x2, y2, color) {
    //    x1 += _fixX;
    //    x2 += _fixX;
    //    y1 += _fixY;
    //    y2 += _fixY;
    var mc1 = new SuperMap.IS.MapCoord(x1, y1);
    var mc2 = new SuperMap.IS.MapCoord(x2, y2);
    var xs = new Array();
    xs.push(mc1.x);
    xs.push(mc2.x);

    var ys = new Array();
    ys.push(mc1.y);
    ys.push(mc2.y);

    MapControl1.CustomLayer.InsertLine(name, xs, ys, 1.5, color, 0, "1", "1");
}

function DrawTrackmark(name, x, y, remark) {
    //    x += _fixX;
    //    y += _fixY;
    var mc = new SuperMap.IS.MapCoord(x, y);
    //<img src='./images/icon
    //.png'>
    MapControl1.CustomLayer.AddMark(name, mc.x, mc.y, null, null, "<div style='font-size:9pt;text-align:center;font-family:宋体,tahoma'>" + remark + "</div>");

}

function DrawCircle(id, centerx, centery, r, linecolor, fillcolor)
{
    var CircleArrayx = new Array();
    var CircleArrayy = new Array();
    for (var i = 0; i < 36; i++) {
        var x = parseFloat(centerx) + parseFloat(r) * Math.cos(Math.PI * i / 18);
        var y = parseFloat(centery) + parseFloat(r) * Math.sin(Math.PI * i / 18);
        CircleArrayx.push(x);
        CircleArrayy.push(y);
    }
    MapControl1.CustomLayer.InsertPolygon(id.toString(), CircleArrayx, CircleArrayy, 1, linecolor.toString(), fillcolor.toString(), 0.6, 1);
}

function DrawPolygon2(id, coordsstr, linewidth, linecolor, fillcolor, fillopacity)
{
    var coordsarray = coordsstr.toString().split(';');
    var coordxsstrarray = coordsarray[0].split(',');
    var coordysstrarray = coordsarray[1].split(',');
    var coordxsarray = new Array();
    var coordysarray = new Array();
    for (var i = 0;i < coordxsstrarray.length; i++)
    {
        coordxsarray.push(parseFloat(coordxsstrarray[i]));
        coordysarray.push(parseFloat(coordysstrarray[i]));
    }
    MapControl1.CustomLayer.InsertPolygon(id.toString(), coordxsarray, coordysarray, parseFloat(linewidth), linecolor.toString(), fillcolor.toString(), parseFloat(fillopacity), 1);
}

var rectQueryAction = null;
var polygonQueryAction = null;
var circleQueryAction = null;

function SetRectQueryAction() {
    if (!rectQueryAction) {
        rectQueryAction = new RectQueryAction();
    }
    MapControl1.SetAction(rectQueryAction);
    //设置地图当前操作类型为rectQueryAction
}

RectQueryAction=function()
{
    this.type = "RectQuery";
    var mapDiv = null;
    var zoomRect = null;
    var _cx = 0, _cy = 0, _nx = 0, _ny = 0;
    var originX = 0; originY = 0;
    var actionStarted = false;
    var actionpan = false;
    var mapCoord;
    this.Init = function(mapControl) {
        actionpan = false; //alert(actionpan);
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

    function _OnMouseUp(e) {

        var xs = new Array();
        var ys = new Array();

        xs.push(mapCoord.x); xs.push(e.mapCoord.x); xs.push(e.mapCoord.x); xs.push(mapCoord.x);
        ys.push(mapCoord.y); ys.push(mapCoord.y); ys.push(e.mapCoord.y); ys.push(e.mapCoord.y);
        this.mapControl.CustomLayer.InsertPolygon("RectQuery", xs, ys, 2, "blue", "white", 0.4, 1);

//        //alert(e.mapCoord.x + ','+ e.mapCoord.y+','+mapCoord.x+','+mapCoord.y);
//        
        
        _Hide();
        
        
        jQuery.ajax({
            type:"POST",
            url:"ws/wsFindbyArea.ashx",
            cache:false,
            data:'log=' +xs+'&lat='+ys,
            success:function(msg){//alert(msg);
                jQuery('#frm').attr('action', Qurl);
                jQuery('#q').val(msg);
                document.all.frm.submit();
                MapControl1.CustomLayer.RemoveMark('query');
            }
        });//alert('log=' +xs+'&lat='+ys);
        MapControl1.CustomLayer.AddMark('query', MapControl1.GetMapCenterX(), MapControl1.GetMapCenterY(), null, null, "<div style='font-size:12pt;text-align:center;font-family:宋体,tahoma'>正在查询中,请稍候！</div>");
        
        actionStarted = false;
        actionpan = true;

        //var MapControl1_SuperMap_IS_PanAction; if (!MapControl1_SuperMap_IS_PanAction) { MapControl1_SuperMap_IS_PanAction = new SuperMap.IS.PanAction(null, null, null); } MapControl1.SetAction(MapControl1_SuperMap_IS_PanAction);

    }
    function _Draw(x, y, width, height) { _SetPosAndSize(zoomRect, x, y, width, height); }
    function _SetPosAndSize(el, x, y, width, height) { el.style.left = x + "px"; el.style.top = y + "px"; el.style.width = width + "px"; el.style.height = height + "px"; }
    function _Show() { zoomRect.style.display = "block"; }
    function _Hide() { zoomRect.style.display = "none"; }
    function _GetJSON() { return _ActionToJSON(this.type, []); }
    this.OnMouseDown = _OnMouseDown; this.OnMouseMove = _OnMouseMove; this.OnMouseUp = _OnMouseUp; this.GetJSON = _GetJSON;
};

//多边形查询 
function SetPolygonQueryAction() {
    if (!polygonQueryAction) {
        polygonQueryAction = new PolygonQueryAction();
    }
    MapControl1.SetAction(polygonQueryAction);
}

var PolygonQueryAction = function(onComplete, onError, onStart, userContext) {
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

        jQuery.ajax({
            type:"POST",
            url:"ws/wsFindbyArea.ashx",
            cache:false,
            data:'log=' +tempx+'&lat='+tempy,
            success:function(msg){//alert(msg);
                jQuery('#frm').attr('action', Qurl);
                jQuery('#q').val(msg);
                document.all.frm.submit();
                MapControl1.CustomLayer.RemoveMark('query');
            }
        });
        MapControl1.CustomLayer.AddMark('query', MapControl1.GetMapCenterX(), MapControl1.GetMapCenterY(), null, null, "<div style='font-size:12pt;text-align:center;font-family:宋体,tahoma'>正在查询中,请稍候！</div>");
    

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
}


function SetCircleQueryAction() {
    var action = new circleQueryAction(null, null, null, null);

    MapControl1.SetAction(action);
}



circleQueryAction = function(onComplete, onError, onStart, userContext) {
    this.type = "circleQueryAction";
    var actionStarted = false;
    var actionpan = false;
    var moveStarted = false;
    var line = null;
    var firstMapCoord = null;
    var curMapCoord = null;
    var firstPoint_x;
    var firstPoint_y;
    var curPoint_x;
    var curPoint_y;
    var _self = this;
    var stringcars = new Array();
    var startmapcoordx, startmapcoordy, currentmapcoordx, currentmapcoordy;
    this.Init = function(mapControl) {
        this.mapControl = mapControl;
        this.mapControl.CustomLayer.ClearLines();
        this.mapControl.CustomLayer.RemovePolygon("RectQuery");
        this.mapControl.CustomLayer.RemovePolygon("PolygonQuery");
        this.mapControl.CustomLayer.RemovePolygon("CircleQuery");
        moveStarted = true;
        if (_ygPos.browser == "ie") { mapControl.container.style.cursor = _scriptLocation + "../images/cursors/CircleQuery.cur"; } else { mapControl.container.style.cursor = "crosshair"; };
       
    };
    this.Destroy = function() { /*this.mapControl.CustomLayer.RemoveMark("TipCirclePoint");*/this.mapControl = null; };
    this.OnClick = function(e) { };
    this.OnDblClick = function(e) { };


    this.OnMouseMove = function(e) {
        if (actionpan){var MapControl1_SuperMap_IS_PanAction; if (!MapControl1_SuperMap_IS_PanAction) { MapControl1_SuperMap_IS_PanAction = new SuperMap.IS.PanAction(null, null, null); } MapControl1.SetAction(MapControl1_SuperMap_IS_PanAction);}
        
        if (e.button == 1) {
            if (!actionStarted) { return false; }
            curMapCoord = e.mapCoord;
            currentmapcoordx = curMapCoord.x;
            currentmapcoordy = curMapCoord.y;
            DrawCircleMove(this.mapControl, startmapcoordx, startmapcoordy, currentmapcoordx, currentmapcoordy);
        }

    };
    this.OnMouseDown = function(e) {
        if (e.button == 1) {
            if (actionpan){var MapControl1_SuperMap_IS_PanAction; if (!MapControl1_SuperMap_IS_PanAction) { MapControl1_SuperMap_IS_PanAction = new SuperMap.IS.PanAction(null, null, null); } MapControl1.SetAction(MapControl1_SuperMap_IS_PanAction);}
        
            actionStarted = true;
            lastMapCoord = e.mapCoord;
            startmapcoordx = lastMapCoord.x;
            startmapcoordy = lastMapCoord.y;
        }

    };
    this.OnMouseUp = function(e) {
        if (e.button == 1) {
            curMapCoord = e.mapCoord;
            currentmapcoordx = curMapCoord.x;
            currentmapcoordy = curMapCoord.y;
            DrawCircleUp(this.mapControl, startmapcoordx, startmapcoordy, currentmapcoordx, currentmapcoordy);
            
            
            //var MapControl1_SuperMap_IS_PanAction; if (!MapControl1_SuperMap_IS_PanAction) { MapControl1_SuperMap_IS_PanAction = new SuperMap.IS.PanAction(null, null, null); } MapControl1.SetAction(MapControl1_SuperMap_IS_PanAction);
   
            actionStarted = false;
            stringcars.length = 0;
            line = null;
            firstMapCoord = null;
            curMapCoord = null;
            actionpan = true;
        }

    };
    

    this.OnContextMenu = function(e) { };

    this.GetJSON = function() { return _ActionToJSON(this.type, [onComplete, onError, onStart, userContext]); };

};

function DrawCircleMove(mapControl, startmapcoordx, startmapcoordy, currentmapcoordx, currentmapcoordy) {
    mapControl.CustomLayer.RemovePolygon("CircleQuery");
    var r = Math.sqrt(Math.pow((currentmapcoordx - startmapcoordx), 2) + Math.pow((currentmapcoordy - startmapcoordy), 2));
    var CircleArrayx = new Array();
    var CircleArrayy = new Array();
    for (var i = 0; i < 36; i++) {
        var x = startmapcoordx + r * Math.cos(Math.PI * i / 18);
        var y = startmapcoordy + r * Math.sin(Math.PI * i / 18);
        CircleArrayx.push(x);
        CircleArrayy.push(y);
    }
    mapControl.CustomLayer.InsertPolygon("CircleQuery", CircleArrayx, CircleArrayy, 2, "blue", "white", 0.6, 1);
}
function DrawCircleUp(mapControl, startmapcoordx, startmapcoordy, currentmapcoordx, currentmapcoordy) {

    var r = Math.sqrt(Math.pow((currentmapcoordx - startmapcoordx), 2) + Math.pow((currentmapcoordy - startmapcoordy), 2));
    var CircleArrayx = new Array();
    var CircleArrayy = new Array();
    for (var i = 0; i < 36; i++) {
        var x = startmapcoordx + r * Math.cos(Math.PI * i / 18);
        var y = startmapcoordy + r * Math.sin(Math.PI * i / 18);
        CircleArrayx.push(x);
        CircleArrayy.push(y);
    }
    mapControl.CustomLayer.InsertPolygon("CircleQuery", CircleArrayx, CircleArrayy, 2, "blue", "white", 0.4, 1);
    jQuery.ajax({
            type:"POST",
            url:"ws/wsFindbyArea.ashx",
            cache:false,
            data:'log=' +CircleArrayx+'&lat='+CircleArrayy,
            success:function(msg){//alert(msg);
                jQuery('#frm').attr('action', Qurl);
                jQuery('#q').val(msg);
                document.all.frm.submit();
                MapControl1.CustomLayer.RemoveMark('query');
            }
        });
        MapControl1.CustomLayer.AddMark('query', MapControl1.GetMapCenterX(), MapControl1.GetMapCenterY(), null, null, "<div style='font-size:12pt;text-align:center;font-family:宋体,tahoma'>正在查询中,请稍候！</div>");
    
    //    mapControl.CustomLayer.RemovePolygon("CircleQueryCars1");
}

function onQueryStart() {
//    MapControl1.CustomLayer.AddMark('query', MapControl1.GetMapCenterX(), MapControl1.GetMapCenterY(), null, null, "<div style='font-size:12pt;text-align:center;font-family:宋体,tahoma'>正在查询中,请不要点击鼠标！</div>");
//    MapControl1.container.style.cursor = _scriptLocation + "../images/cursors/Pan.cur";
}
function onQueryComplete(resultSet) {
////    var str = '共有' + resultSet.recordsets.length + '图层查询出' + resultSet.currentCount + '条信息：\n';
////    for (var i = 0; i < resultSet.recordsets.length; i++) {
////        str += '图层' + i + '：名称：' + resultSet.recordsets[i].layerName + '，共有' + resultSet.recordsets[i].records.length + '记录：\n';
////        for (var j = 0; j < resultSet.recordsets[i].records.length; j++) {
////            str += '记录' + j + '：';
////            for (var x = 0; x < resultSet.recordsets[i].records[j].fieldValues.length; x++) {
////                str += resultSet.recordsets[i].returnFields[x] + '：' + resultSet.recordsets[i].records[j].fieldValues[x] + ', ';
////            }
////            str += '\n';
////        }
////    }
//    var str = resultSet.recordsets.length + ',' + resultSet.currentCount + ';';
//    for (var i = 0; i < resultSet.recordsets.length; i++) {
//        str += resultSet.recordsets[i].layerName + ',' + resultSet.recordsets[i].records.length + '/';
//        str += resultSet.recordsets[i].returnFields.length;
//        for (var k=0;k<resultSet.recordsets[i].returnFields.length;k++)
//        {
//            str += ','+resultSet.recordsets[i].returnFields[k];
//        }
//        str +='/';
//        for (var j = 0; j < resultSet.recordsets[i].records.length; j++) {
//            for (var x = 0; x < resultSet.recordsets[i].records[j].fieldValues.length; x++) {
//                if(resultSet.recordsets[i].records[j].fieldValues.length == x+1)
//                {
//                    if(resultSet.recordsets[i].records.length==j+1)
//                    {
//                        str += resultSet.recordsets[i].records[j].fieldValues[x]+';';
//                    }
//                    else{
//                        str += resultSet.recordsets[i].records[j].fieldValues[x]+'/';
//                    }
//                }
//                else
//                {
//                    str += resultSet.recordsets[i].records[j].fieldValues[x]+',';
//                }
//            }
//            str += '\n';
//        }
//    }
//    
//    alert(str);
//    

//    MapControl1.ClearHighlight();
//    MapControl1.CustomLayer.RemoveMark('query');
//    var MapControl1_SuperMap_IS_PanAction; if (!MapControl1_SuperMap_IS_PanAction) { MapControl1_SuperMap_IS_PanAction = new SuperMap.IS.PanAction(null, null, null); } MapControl1.SetAction(MapControl1_SuperMap_IS_PanAction);


}

function onQueryError(responseText) {
    if (responseText) {
        alert(resource_queryError + ":" + responseText);
    }
    else {
        alert(resource_queryError + "!");
    }
}


function SetMeasureDistanceAction() {
    var action = new MeasureDistanceAction(onMeasureDistanceComplete, onMeasureDistanceError, onMeasureDistanceStart, "");
    MapControl1.SetAction(action);
}
var DistanceIndex = 0;
function InsertDistance(xs, ys) {
    DistanceIndex = 0;
    if (xs.length > 0) {
        var DistanceL = 0;
        for (var i = 0; i < xs.length; i++) {
            DistanceIndex += 1;
            if (i == 0) {
                //                var L="0 米";
                //                var scriptstring="";
                //                scriptstring+='<div style="position:absolute; width:auto; height:auto; z-index:1;">';
                //                scriptstring+='<table  cellpadding="0" cellspacing="0"  style="width:auto; height:auto;background-color: #FFFFCA;border: #000000 1px solid; font-size:9px;">';
                //                scriptstring+='<tr><td>'+L+'</td></tr>';
                //                scriptstring+='</table></div>';
                //                MapControl1.CustomLayer.InsertMark(DistanceIndex,xs[i],ys[i],10,10,scriptstring,"",200)
            }
            else {
                var L = Point2Distance(xs[i - 1], ys[i - 1], xs[i], ys[i]);
                if (L == 0) {
                    return;
                }
                DistanceL += L;
                var scriptstring = "";
                scriptstring += '<div style="position:absolute; width:auto; height:auto; z-index:1;">';
                scriptstring += '<table  cellpadding="0" cellspacing="0"  style="width:auto; height:auto;background-color: #FFFFCA;border: #000000 1px solid; font-size:9px;">';
                if (DistanceL > 1000) {
                    scriptstring += '<tr><td>' + Math.round(parseFloat(DistanceL)) / 1000 + ' 千米</td></tr>'
                }
                else {
                    scriptstring += '<tr><td>' + Math.round(DistanceL) + ' 米</td></tr>'
                }
                scriptstring += '</table></div>';
                MapControl1.CustomLayer.InsertMark(DistanceIndex, xs[i], ys[i], 10, 10, scriptstring, "", 200)
            }
        }
    }
}
function onMeasureDistanceStart() { }
function onMeasureDistanceComplete(result) { }
function onMeasureDistanceError(result) { }

function Point2Distance(x1, y1, x2, y2) {
    var v = GetDistance(y1, x1, y2, x2);
    return v
}

var EARTH_RADIUS = 6378137;
function rad(d) {
    var temp = d * Math.PI / 180.0;
    return temp;
}

function GetDistance(lat1, lng1, lat2, lng2)//lat 是纬度(latitude)，lng 是经度(longitude)
{
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



MeasureDistanceAction = function(onComplete, onError, onStart, userContext) {
    this.type = "MeasureDistanceAction";
    var actionStarted = false;
    var line = null;
    var keyPoints = new Array();
    var xs = new Array();
    var ys = new Array();
    var xsM = new Array();
    var ysM = new Array();
    this.Init = function(mapControl) {
        this.mapControl = mapControl;
        if (_ygPos.browser == "ie") { mapControl.container.style.cursor = _scriptLocation + "../images/cursors/MeasureDistance.cur"; } else { mapControl.container.style.cursor = "pointer"; };
    };
    this.Destroy = function() {
        actionStarted = false;
        this.mapControl.CustomLayer.RemoveLine("MeasureDistance");
        this.mapControl.CustomLayer.RemoveMark("Tip");
        for (var i = 1; i < DistanceIndex + 1; i++) {
            this.mapControl.CustomLayer.RemoveMark(i);
        }
        line = null;
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
        scriptstring += '<div style="position:absolute; width:auto; height:auto; z-index:1;">';
        scriptstring += '<table  cellpadding="0" cellspacing="0"  style="width:auto; height:auto;background-color: #FFFFCA;border: #000000 1px solid; font-size:12px;">';
        scriptstring += '<tr><td>' + "单击右键结束" + '</td></tr>';
        scriptstring += '</table></div>'

        this.mapControl.CustomLayer.InsertMark("Tip", e.mapCoord.x, e.mapCoord.y, 10, 10, scriptstring, "", 200);
    };
    this.OnMouseUp = function(e) {
        if (!e) e = window.event;
        if (e.button == 1) {
            if (!actionStarted) {
                keyPoints.push(e.mapCoord);
                xs.push(e.mapCoord.x); ys.push(e.mapCoord.y);
                xsM.push(e.mapCoord.x); ysM.push(e.mapCoord.y);
                for (var i = 1; i < DistanceIndex + 1; i++) {
                    this.mapControl.CustomLayer.RemoveMark(i);
                }
            }
            actionStarted = true;
            keyPoints.push(e.mapCoord);
            xs.push(e.mapCoord.x); ys.push(e.mapCoord.y);
            xsM.push(e.mapCoord.x); ysM.push(e.mapCoord.y);
            this.mapControl.CustomLayer.InsertLine("MeasureDistance", xsM, ysM, 2, "blue", 100);
            //显示距离
            InsertDistance(xs, ys);
        }
        if (e.button == 2) {
            if (!actionStarted) { return false; }
            this.mapControl.CustomLayer.RemoveLine("MeasureDistance1");
            this.mapControl.CustomLayer.RemoveMark("Tip");

            keyPoints.push(e.mapCoord);
            xs.push(e.mapCoord.x); ys.push(e.mapCoord.y);
            xsM.push(e.mapCoord.x); ysM.push(e.mapCoord.y);


            this.mapControl.CustomLayer.InsertLine("MeasureDistance", xsM, ysM, 2, "blue", 100); InsertDistance(xs, ys);

            while (keyPoints.length > 0) {
                keyPoints.pop();
                xs.pop(); ys.pop();
                xsM.pop(); ysM.pop();
            }
            actionStarted = false

        }
    };
    this.OnMouseDown = function(e) { };
    this.OnContextMenu = function(e) { };
    this.GetJSON = function() { return _ActionToJSON(this.type, [onComplete, onError, onStart, userContext]); }
}


function InsertLineByLBS(id,xs,ys,lineWeight,lineColor,opacity,zIndex)//id:string,标识;xs:Array,经度坐标数组;ys:Array,纬度坐标数组;lineWeight:String,线宽度;lineColor:String,线颜色;opacity:int,线透明度;zIndex:int,线所在的div层的index;
{
    var mc = MapControl1;

    var pcount = xs.length;
    for (var i=0;i<xs.length-1;i++)
    {
        var pmidx = (xs[i]+xs[i+1])/2;
        var pmidy = (ys[i]+ys[i+1])/2;
        var pxs = new Array(xs[i],pmidx);
        var pys = new Array(ys[i],pmidy);
        var lid = id + i.toString() + 's';
        mc.CustomLayer.InsertLineArrow(lid,pxs,pys,lineWeight,lineColor,opacity,zIndex);
        pxs = new Array(pmidx,xs[i+1]);
        pys = new Array(pmidy,ys[i+1]);
        lid = id + i.toString() + 'e';
        mc.CustomLayer.InsertLine(lid,pxs,pys,lineWeight,lineColor,opacity,zIndex);
    }
      
}

function ViewEntire() 
{
    MapControl1.ViewEntire();
}

function QuickZoomIn()
{
    MapControl1.Zoom(2);
}

function QuickZoomOut()
{
    MapControl1.Zoom(0.5);
}

var zoomInAction = null;
function SetZoomInAction()
{
    if (!zoomInAction)
    {
        zoomInAction = new SuperMap.IS.ZoomInAction();    
    }
    MapControl1.SetAction(zoomInAction); 
}

var zoomOutAction = null;
function SetZoomOutAction()
{
    if (!zoomOutAction)
    {
        zoomOutAction = new SuperMap.IS.ZoomOutAction();            
    }
    MapControl1.SetAction(zoomOutAction);      
}

function SetPanAction()
{
    if (!panAction) 
    { 
        panAction = new SuperMap.IS.PanAction();
    }	
    MapControl1.SetAction(panAction);
}