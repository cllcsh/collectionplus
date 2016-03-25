//================================================================================ 
// SuperMap IS .NET 客户端程序，版权所有，北京超图软件股份有限公司，2000-2009。 
// 本程序只能在有效的授权许可下使用。未经许可，不得以任何手段擅自使用或传播。 
// 作 者:  SuperMap IS Web Team 
// 版 本:  $Id: SuperMap.IS.MapControl.js,v 1.172.2.63 2009/11/17 06:15:25 zhangyt Exp $
// 文 件:  SuperMap.IS.MapControl.js 
// 描 述:  AjaxScripts 地图控件 
// 更 新:  $Date: 2009/11/17 06:15:25 $  
//================================================================================ 
SuperMap.IS.MapControl=function(container,params){var _self=this;
var referViewBounds=null,referScale=null,referViewer=null,mapBounds=null;
var _mapScales=null;
var _mapName="";
var _imageFormat="png";
var _mapHandler="/mapHandler/";
var _mapScale="";
var _zoomTotalSteps=5,_keyboardPanDistance=15,_panStepDistance=15;
if(_GetBrowser()!="ie"){_zoomTotalSteps=3;
_keyboardPanDistance=45;
_panStepDistance=45}var _buffer=0;
var _spacerTile="images/spacer.gif";
var _tileSize=256;
var _tile_swapZIndex=1,_tile_baseZIndex=2;
var _customLayer_baseZIndex=11,_customLayer_topZIndex=20;
var _animationEnabled=true;
var _curParam=new SuperMap.IS.MapParam(),_tempParam=new SuperMap.IS.MapParam();
var _x=0,_y=0,_w=0,_h=0;
var _tileCollections=new Array();
var _oldTileCollections=new Array();
var _preTileCollections=new Array();
var _tiles=new Array(),_oldTiles=null,_preTiles=new Array();
var _trackingLayerTiles=new Array(),_oldTrackingLayerTiles=null,_preTrackingLayerTiles=new Array();
var _originX=0,_originY=0;
var _offsetX=0,_offsetY=0;
var _tileX1=new Array(),_tileY1=new Array(),_tileX2=new Array(),_tileY2=new Array();
var _tileCountX=new Array(),_tileCountY=new Array();
var _dragging=false,_keyboardPanning=false;
var _lastMouseX=0,_lastMouseY=0;
var _zooming=false;
var _zoomCounter=0;
var _panning=false;
var _panCounter=0;
var _panningX=0,_panningY=0;
var _panMapX=null,_panMapY=null;
var _logo=null;
var _zoomInAction=null,_panAction=null,_curAction=null;
var _map=null;
var _curMap=null;
var _curBounds=null,_viewBounds=null,_viewBoundsBefore=null;
var _eventsList=new Array(),_eventsNameList=new Array();
var _mapDivID=container.id+"_mapDiv";
var _mapDiv=document.createElement("div");
_mapDiv.id=_mapDivID;
var _trackingLayerDivID=container.id+"_trackingLayerDiv";
var _trackingLayerDiv=document.createElement("div");
_trackingLayerDiv.id=_trackingLayerDivID;
var _geometryLayerDivID=container.id+"_geometryLayerDiv";
var _geometryLayerDiv=document.createElement("div");
_geometryLayerDiv.id=_geometryLayerDivID;
var _workLayer=document.createElement("div");
_workLayer.id=container.id+"_workLayer";
var _kbInput=document.createElement("input");
_kbInput.id=container.id+"_kbInput";
var _customDiv=null;
var _marks=new Array(),_lines=new Array(),_polygons=new Array();
var _imageBufferCollection=new Array();
var _imageBufferSize=64;
var _useImageBuffer=true;
var _d=false;
var _getLayers=false;
var _firstUpdated=false;
var _tileLoadedChecking=false;
var _iTimeoutIDForCheckTileLoaded=null;
var _iTimeoutIDForShowPolylines=null;
var _iTimeoutIDForUpdateMap=null;
var _iTimeoutIDForStepPan=null;
var _iTimeoutIDForSetFactor=null;
var _iTimeoutIDForDynamicNavigate=null;
var _lastBusNum=0;
var _layersBackupForControl=new Array();
var _pointsForMeasure=new Array();
var _allMapScales=null;
var _trackingLayerIndex=-1;
var _userID="";
var _trackingLayerUrl="";
var _geometryIDs=new Array();
var _geometryStyles=new Array();
var _geometryIndexs=new Array();
var _geometries=new Array();
var _defaultGeometryStyle=null;
var _geometryTolerance=5;
var _calculateInClient=true;
var _geometryKey="";
var _lastGeometryIDs=new Array();
var _geometryClicks=new Array();
var _geometryDblclicks=new Array();
var _geometryMouseOvers=new Array();
var _geometryMouseOuts=new Array();
var query=null,spatialAnalyst=null,edit=null;
var _changingMap=false;
var _switching=false;
this.workLayer=_workLayer;
this.container=container;
this.id=container.id;
this.mapName="";
this.layers=new Array();
var _t=new Date().getTime();
var _tileLayerIDs=new Array();
var _tileLayers=new Array();
this.mapDiv=null;
var _tileCheckTimes=0;
var _tileCheckCounts=10;
var _PrintMode=false;
var _switchMapHistory=new Array();
var _isIE6=false;
var _switchMapMode=0;
var _switchMapWithParam=false;
var str=navigator.userAgent.toLowerCase();
if(str.indexOf("msie 6.0")!=-1){_isIE6=true}var _useVml=false;
var _oldViewBounds;
var _fixPngTransparentForIe6=false;
var _minsize=-170000;
var _maxsize=170000;
this.wheelZoomByMouse=false;
this.imageFormat;
this.tileSize;
this.customBounds=new SuperMap.IS.MapRect();
var _customBounds=null;
this.storeClientInfo=false;
var _zoomOut=false;
var _zoomIn=false;
var _isViewEntie=false;
this.Init=function(){
	window.curMapControl=_self;
	if(params.mapScales&&params.mapScales.length>0){_mapScales=params.mapScales}
	if(params.allMapScales){_allMapScales=params.allMapScales}
	if(!params.imageFormat){params.imageFormat=_imageFormat}
	if(_self.imageFormat){params.imageFormat=_self.imageFormat;
		_imageFormat=_self.imageFormat}
	if(params.mapHandler){_mapHandler=params.mapHandler}
	else{params.mapHandler=_mapHandler}
	if(params.mapName){_self.mapName=_mapName=params.mapName}
	else{params.mapName=""}
	if(params.mapScale){_mapScale=params.mapScale}
	else{params.mapScale=""}
	if(params.useImageBuffer){_useImageBuffer=params.useImageBuffer}
	else{params.useImageBuffer=_useImageBuffer}
	if(params.tileSize){_tileSize=params.tileSize}
	else{params.tileSize=_tileSize}
	if(_self.tileSize){params.tileSize=_self.tileSize;
		_tileSize=_self.tileSize}
	if(!params.layersKey){params.layersKey=0}
	if(typeof (params.trackingLayerIndex)!="undefined"){_trackingLayerIndex=params.trackingLayerIndex}
	else{params.trackingLayerIndex=_trackingLayerIndex}
	if(params.userID){_userID=params.userID}
	else{params.userID=_userID}
	if(params.zoomLevel){if(params.zoomLevel>0&&_mapScales&&params.zoomLevel<=_mapScales.length){params.mapScale=_mapScales[params.zoomLevel-1]}
	}
	if(params.fixPngTransparentForIe6){_fixPngTransparentForIe6=params.fixPngTransparentForIe6}
	else{params.fixPngTransparentForIe6=_fixPngTransparentForIe6}
	if(params.wheelZoomByMouse){_self.wheelZoomByMouse=params.wheelZoomByMouse}
	if(params.tileCheckTime){_tileCheckCounts=params.tileCheckTime*1000/200}
	if(params&&typeof (params.switchMapMode)!="undefined"){_switchMapMode=params.switchMapMode}
	if(typeof (params.storeClientInfo)!="undefined"&&params.storeClientInfo){_self.storeClientInfo=params.storeClientInfo}
	params.id=container.id;
	_map=new SuperMap.IS.Map(params);
	_map.Init(onMapInit)
};
function onMapInit(mapStatus){
	var lastName;
if(_curMap!=null&&_curMap.mapName!=_map.mapName){
lastName=_curMap.mapName}
if(_curMap){
_curMap.Destroy();
_curMap=null}
_curMap=_map;
if(params&&typeof (params.customBounds)!="undefined"){
_customBounds=params.customBounds[_curMap.mapName];
_self.customBounds.Copy(_customBounds)}
_curMap.AttachEvent("onchangetrackinglayer",_OnChangeTrackingLayer);
_curMap.AttachEvent("onchangelayer",_OnChangeLayer);
_AddTileLayer(_mapDivID,_GetMapTileID,_GetMapTileUrl,null,0,0,0,1,1,_tileSize,_tileSize,mapStatus.referBounds,_useImageBuffer);
_AddTileLayer(_trackingLayerDivID,_GetTrackingLayerTileID,_GetTrackingLayerTileUrl,null,0,0,0,1,2,_tileSize,_tileSize,null,false);
_AddTileLayer(_geometryLayerDivID,_GetGeometryLayerTileID,_GetGeometryLayerTileUrl,null,0,0,0,1,3,_tileSize,_tileSize,mapStatus.referBounds,false);
_InitTileLayerAction();
_self.mapDiv=_tileLayers[_mapDivID].container;
var errorOccurs=true;
if(mapStatus){
if(_changingMap){
params.mapBounds=mapStatus.mapBounds;
switch(_switchMapMode){
case 0:if(params.x==null||params.y==null){
params.x=mapStatus.mapBounds.Center().x;
params.y=mapStatus.mapBounds.Center().y}
break;
case 1:default:break}
}
_self.mapName=mapStatus.mapName;
if(mapStatus.mapNames&&mapStatus.mapNames.length>0){
if(_self.mapNames==null){
_self.mapNames=new Array()}
for(var i=0;
i<mapStatus.mapNames.length;
i++){
_self.mapNames[i]=mapStatus.mapNames[i]}
}
if(!params.layers||params.layers.length==0){
params.layers=mapStatus.layers}
if(mapStatus.mapBounds){
if(mapBounds==null){
mapBounds=new SuperMap.IS.MapRect()}
mapBounds.Copy(mapStatus.mapBounds)}
errorOccurs=false}
if(errorOccurs){
_TriggerEvent("onerror",new EventArguments(null,SuperMap.IS.MapControlResource.mapInitError));
return false}
function InitLayers(){
if(!_changingMap){
_BackupLayers(_layersBackupForControl,_curMap.layers)}
_self.layers=_curMap.layers}
if(!params.layers||params.layers.length==0){
_map.GetMapStatus(true,true,function(responseText){
var mapStatusJ=_eval("("+responseText+")");
if(!mapStatusJ){
return }
var mapStatusTemp=new SuperMap.IS.MapStatus();
mapStatusTemp.FromJSON(mapStatusJ);
if(mapStatusTemp.layers){
var layerCount=mapStatusTemp.layers.length;
if(_curMap.layers==null){
_curMap.layers=new Array()}
for(var i=0;
i<layerCount;
i++){
if(_curMap.layers[i]==null){
_curMap.layers[i]=new SuperMap.IS.Layer()}
_curMap.layers[i].Copy(mapStatusTemp.layers[i])}
params.layers=_curMap.layers}
InitLayers();
var changedLayersFromControlJSON=_FindDifference(_layersBackupForControl,_curMap.layers);
_SetLayersHidden(changedLayersFromControlJSON);
_getLayers=true;
if(_firstUpdated){
_TriggerEvent("oninit",null)}
if(lastName&&lastName!=_curMap.mapName){
_TriggerEvent("onendswitchmap")}
mapStatusTemp.Destroy();
mapStatusTemp=null}
)}
else{
_curMap.layers=params.layers;
InitLayers();
_getLayers=true}
_useVml=_EnableVML();
_Update(null,false,_OnFirstUpdateComplete);
if(_getLayers){
if(lastName&&lastName!=_curMap.mapName){
_TriggerEvent("onendswitchmap")}
}
}
function _OnFirstUpdateComplete(){
_workLayer.style.width="100%";
_workLayer.style.height="100%";
_workLayer.style.overflow="hidden";
_workLayer.style.border="0px";
_workLayer.style.padding="0px";
_workLayer.style.margin="0px";
_workLayer.style.position="relative";
container.appendChild(_workLayer);
_GetPosAndSize();
if(_w<100){
container.style.width="100px";
_workLayer.style.width="100%"}
if(_h<100){
container.style.height="100px";
_workLayer.style.height="100%"}
container.style.overflow="hidden";
_GetPosAndSize();
_workLayer.style.width=_w+"px";
_workLayer.style.height=_h+"px";
_GetPosAndSize();
_GetCustomDiv();
_kbInput.value="";
var ks=_kbInput.style;
ks.position="absolute";
ks.top=_h/2+"px";
ks.left=_w/2+"px";
if(_GetBrowser()=="ie"||_GetBrowser()=="gecko"){
ks.width="0px";
ks.height="0px"}
ks.padding="0px";
ks.margin="0px";
ks.border="0px solid white";
ks.zIndex=-1;
ks=null;
_workLayer.appendChild(_kbInput);
if(!params.fixedView){
_zoomInAction=new SuperMap.IS.ZoomInAction();
_zoomInAction.Init(_self);
_panAction=new SuperMap.IS.PanAction();
_panAction.Init(_self);
_curAction=_panAction;
if(!_changingMap){
container.attachEvent("onmousedown",_MouseDown);
container.attachEvent("onmouseup",_MouseUp);
container.attachEvent("onmousemove",_MouseMove);
container.attachEvent("onmousewheel",_MouseWheel);
container.attachEvent("ondblclick",_DblClick);
container.attachEvent("oncontextmenu",_ContextMenu);
container.attachEvent("onclick",_Click);
_kbInput.attachEvent("onkeydown",_KeyDown);
_kbInput.attachEvent("onkeyup",_KeyUp)}
_changingMap=false;
_buffer=_tileSize}
if(params.buffer!=undefined&&params.buffer!=null){
_buffer=params.buffer}
if(params.x!=null&&params.y!=null&&params.mapScale){
try{
var mp=new SuperMap.IS.MapParam(_mapName,_mapScale,_mapScales);
mp.SetMapScale(_eval(params.mapScale));
mp.SetMapCenter(new SuperMap.IS.MapCoord(params.x,params.y));
if(_switching){
switch(_switchMapMode){
case 0:if(_switchMapHistory[_mapName]&&_switchMapWithParam==false){
mp.SetMapScale(_eval(_switchMapHistory[_mapName].mapScale));
mp.SetMapCenter(new SuperMap.IS.MapCoord(_switchMapHistory[_mapName].mapCenter.x,_switchMapHistory[_mapName].mapCenter.y))}
break;
default:break}
}
_SetMapParam(mp)}
catch(e){
_SetDefaultParam()}
}
else{
_SetDefaultParam()}
if(!params.disableLogo){
_logo=new Logo(_workLayer);
_logo.Init()}
_oldViewBounds=_GetViewBounds();
_InitClientAction();
_InitMarks();
_InitLines();
_InitPolygons();
_firstUpdated=true;
_switchMapWithParam=false;
if(_getLayers){
_TriggerEvent("oninit",null)}
}
this.Destroy=function(){
_TriggerEvent("ondestroying",null);
_ClearTimeout();
window.curMapControl=null;
_getLayers=false;
_firstUpdated=false;
_switchMapHistory=null;
if(!params.fixedView){
container.detachEvent("onmousedown",_MouseDown);
container.detachEvent("onmouseup",_MouseUp);
container.detachEvent("onmousemove",_MouseMove);
container.detachEvent("onmousewheel",_MouseWheel);
container.detachEvent("ondblclick",_DblClick);
container.detachEvent("oncontextmenu",_ContextMenu);
container.detachEvent("onclick",_Click);
_kbInput.detachEvent("onkeydown",_KeyDown);
_kbInput.detachEvent("onkeyup",_KeyUp)}
while(_tileCollections.length>0){
var tiles=_tileCollections.pop();
while(tiles.length>0){
var tile=tiles.pop();
if(tile!=null){
tile.RemoveFromMap();
tile=null}
}
tiles=null}
_tileCollections=null;
while(_preTileCollections.length>0){
var preTiles=_preTileCollections.pop();
while(preTiles.length>0){
var preTile=preTiles.pop();
if(preTile!=null){
preTile.RemoveFromMap();
preTile=null}
}
preTiles=null}
_preTileCollections=null;
if(_logo){
_logo.Destroy();
_logo=null}
if(_map){
_map.Destroy();
_map=null}
if(_zoomInAction){
_zoomInAction.Destroy();
_zoomInAction=null}
if(_panAction){
_panAction.Destroy();
_panAction=null}
if(_curAction){
_curAction.Destroy();
_curAction=null}
_ClearMarks();
_marks=null;
_ClearLines();
_lines=null;
_ClearPolygons();
_polygons=null;
_ClearGeometries();
_geometryIDs=null;
_geometries=null;
_geometryStyles=null;
_geometryIndexs=null;
_geometryClicks=null;
_geometryDblclicks=null;
_geometryMouseOvers=null;
_geometryMouseOuts=null;
_defaultGeometryStyle=null;
_ClearTileLayers(true);
_tileLayerIDs=null;
_tileLayers=null;
_imageBufferCollection=null;
_ClearEvents();
if(_customDiv&&_customDiv.parentNode){
_customDiv.parentNode.removeChild(_customDiv)}
_self.workLayer=null;
_self.container=null;
_kbInput=_customDiv=_workLayer=container=_self=null;
if(params.layers){
while(params.layers.length>0){
var layer=params.layers.pop();
layer.Destroy();
layer=null}
params.layers=null}
if(this.layers){
while(this.layers.length>0){
var layer=this.layers.pop();
layer.Destroy();
layer=null}
this.layers=null}
params=null}
;
function _Update(updateParam,refresh,onComplete,onError,userContext){
var bRefresh=refresh;
if(_self.mapName!=_curMap.mapName||(_self.imageFormat!=""&&_self.imageFormat!=null&&_self.imageFormat!=_imageFormat)||(_self.tileSize!=null&&_self.tileSize!=_tileSize)){
if(_switching){
return }
_getLayers=false;
_firstUpdated=false;
_switching=true;
if(_switchMapHistory[_curMap.mapName]==null){
_switchMapHistory[_curMap.mapName]=new Object()}
params.mapName=_self.mapName;
params.x=null;
params.y=null;
params.mapScale=null;
if(params.mapScales){
while(params.mapScales.length>0){
var pMapScale=params.mapScales.pop();
pMapScale=null}
params.mapScales=null}
if(params.layers){
while(params.layers.length>0){
var pLayer=params.layers.pop();
pLayer.Destroy();
pLayer=null}
params.layers=null}
params.zoomLevel=null;
var mapParam=_GetMapParam();
switch(_switchMapMode){
case 0:_switchMapHistory[_curMap.mapName].mapCenter=new SuperMap.IS.MapCoord();
_switchMapHistory[_curMap.mapName].mapCenter.x=mapParam.mapCenter.x;
_switchMapHistory[_curMap.mapName].mapCenter.y=mapParam.mapCenter.y;
_switchMapHistory[_curMap.mapName].mapScale=mapParam.mapScale;
break;
case 1:params.x=mapParam.mapCenter.x;
params.y=mapParam.mapCenter.y;
params.mapScale=mapParam.mapScale;
break;
default:break}
if(updateParam){
if(updateParam.mapName){
params.mapName=updateParam.mapName}
if(updateParam.center){
params.x=updateParam.center.x;
params.y=updateParam.center.y}
if(updateParam.mapScale){
params.mapScale=updateParam.mapScale}
_switchMapWithParam=true}
mapParam.Destroy();
mapParam=null;
_TriggerEvent("onstartswitchmap",new EventArguments(params,""));
while(_mapScales&&_mapScales.length>0){
var cMapScales=_mapScales.pop();
cMapScales=null}
if(_allMapScales&&_allMapScales[_self.mapName]){
var mapScalesReciprocal=_allMapScales[_self.mapName].split(",");
if(!_mapScales){
_mapScales=new Array()}
for(var i=0;
i<mapScalesReciprocal.length;
i++){
_mapScales.push(1/parseFloat(mapScalesReciprocal[i]))}
while(mapScalesReciprocal.length>0){
var mReciprocal=mapScalesReciprocal.pop();
mReciprocal=null}
mapScalesReciprocal=null}
function _onUpdateComplete(){
if(onComplete){
onComplete(params.layersKey,userContext)}
_DetachEvent("oninit",_onUpdateComplete)}
var inputLayersKey=document.getElementById(container.id+"_hiddenLayersKey");
if(inputLayersKey){
inputLayersKey.value=""}
try{
params.trackingLayerIndex=-1;
_AttachEvent("oninit",_onUpdateComplete);
_SwitchMap()}
catch(e){
if(onError){
onError(e.message,userContext)}
return }
return }
else{
var changedLayersFromControlJSON=_FindDifference(_layersBackupForControl,_curMap.layers);
_SetLayersHidden(changedLayersFromControlJSON);
function _UpdateComplete(layersKey,userContext){
if(params.layersKey!=layersKey||bRefresh){
while(_imageBufferCollection.length>0){
var imageBuffer=_imageBufferCollection.pop();
while(imageBuffer&&imageBuffer.length>0){
var imageId=imageBuffer[0];
delete imageBuffer[imageId];
imageBuffer.shift()}
}
}
params.layersKey=layersKey;
if(bRefresh){
_RefreshMap()}
if(onComplete){
onComplete(layersKey,userContext)}
_BackupLayers(_curMap.layersBackupForHandler,_curMap.layers);
_switching=false}
_curMap.Update(_UpdateComplete,onError,userContext)}
}
this.Update=function(onComplete,onError,userContext){
_Update(null,true,onComplete,onError,userContext)}
;
this.SwitchMap=function(switchParam,onComplete,onError,userContext){
if(switchParam&&switchParam.mapName){
_self.mapName=switchParam.mapName}
_Update(switchParam,true,onComplete,onError,userContext)}
;
function _ClearTimeout(){
if(_iTimeoutIDForCheckTileLoaded){
window.clearTimeout(_iTimeoutIDForCheckTileLoaded);
_iTimeoutIDForCheckTileLoaded=null}
if(_iTimeoutIDForShowPolylines){
window.clearTimeout(_iTimeoutIDForShowPolylines);
_iTimeoutIDForShowPolylines=null}
if(_iTimeoutIDForUpdateMap){
window.clearTimeout(_iTimeoutIDForUpdateMap);
_iTimeoutIDForUpdateMap=null}
if(_iTimeoutIDForStepPan){
window.clearTimeout(_iTimeoutIDForStepPan);
_iTimeoutIDForStepPan=null}
if(_iTimeoutIDForSetFactor){
window.clearTimeout(_iTimeoutIDForSetFactor);
_iTimeoutIDForSetFactor=null}
if(_iTimeoutIDForDynamicNavigate){
window.clearTimeout(_iTimeoutIDForDynamicNavigate);
_iTimeoutIDForDynamicNavigate=null}
}
function _GetPosAndSize(){
_x=_GetElementX(_workLayer);
_y=_GetElementY(_workLayer);
_w=_workLayer.offsetWidth;
_h=_workLayer.offsetHeight}
function _SetDefaultParam(){
var param=new SuperMap.IS.MapParam(_mapName,_mapScale,_mapScales);
param.SetMapCenter(new SuperMap.IS.MapCoord(0,0));
param.SetMapScale(1);
_SetMapParam(param)}
function _GetMapCenterX(){
return _curParam.center.x}
function _GetMapCenterY(){
return _curParam.center.y}
function _GetPixelCenterX(){
return _curParam.pixelCenter.x}
function _GetPixelCenterY(){
return _curParam.pixelCenter.y}
function _ComputeCenterPoint(capture){
_curParam.center=_curMap.PixelToMapCoord(_curParam.pixelCenter,_curParam.mapScale);
if(capture){
_tempParam.Copy(_curParam)}
}
function _GetMapScale(){
return _curParam.mapScale}
function _GetZoomLevel(){
return _curParam.zoomLevel}
function _MapCoordToPixel(mc){
var pc=_curMap.MapCoordToPixel(mc,_curParam.mapScale);
pc.x-=_originX+_offsetX;
pc.y-=_originY+_offsetY;
return pc}
function _PixelToMapCoord(pc){
var pj=new SuperMap.IS.PixelCoord(pc.x+_originX+_offsetX,pc.y+_originY+_offsetY);
var mc=_curMap.PixelToMapCoord(pj,_curParam.mapScale);
return mc}
function _PixelToMapDistance(pd,ms,byHeight){
return _curMap.PixelToMapDistance(pd,ms,byHeight)}
function _GetSize(){
return new SuperMap.IS.PixelRect(0,0,_w,_h)}
function _Resize(width,height){
if((!width||width<=0)&&(!height||height<=0)){
return }
if(width&&width>0){
_w=width}
if(height&&height>0){
_h=height}
container.style.width=_w+"px";
container.style.height=_h+"px";
_workLayer.style.width=_w+"px";
_workLayer.style.height=_h+"px";
_GetPosAndSize();
_PanToCurCenter(_curParam);
if(_logo){
_logo.Reposition()}
_SetSizeHidden();
_TriggerEvent("onresize")}
function _SetAnimationEnabled(enabled){
_animationEnabled=enabled}
function _IsAnimationEnabled(){
return _animationEnabled}
function _Debug(enabled){
_d=enabled;
for(var i=0;
i<_tiles.length;
i++){
_tiles[i].Debug(enabled)}
}
function _EnsureWithinBounds(param,bounds){
if(_customBounds!=null){
_PanWithinCustomBounds(param,bounds,0,0)}
else{
_PanWithinBounds(param,bounds,0,0)}
}
function _PanWithinBounds(param,bounds,deltaX,deltaY){
var newPosition=_curMap.PixelToMapCoord(new SuperMap.IS.PixelCoord(deltaX+param.pixelCenter.x,deltaY+param.pixelCenter.y),param.mapScale);
if(_d){
window.status="oldX:"+newPosition.x}
var changed=false;
if(bounds==null){
return }
if(newPosition.x<bounds.leftBottom.x){
newPosition.x=bounds.leftBottom.x;
changed=true}
if(newPosition.y<bounds.leftBottom.y){
newPosition.y=bounds.leftBottom.y;
changed=true}
if(newPosition.x>bounds.rightTop.x){
newPosition.x=bounds.rightTop.x;
changed=true}
if(newPosition.y>bounds.rightTop.y){
newPosition.y=bounds.rightTop.y;
changed=true}
if(changed){
var newPixelPosition=_curMap.MapCoordToPixel(newPosition,param.mapScale);
param.SetPixelCenter(newPixelPosition)}
else{
param.SetPixelCenter(new SuperMap.IS.PixelCoord(param.pixelCenter.x+deltaX,param.pixelCenter.y+deltaY))}
param.SetMapCenter(newPosition);
if(_d){
window.status+="newX:"+newPosition.x}
return }
function _PanWithinCustomBounds(param,bounds,deltaX,deltaY){
var changedRect=false;
var oldScale=param.mapScale;
var newPosition=_curMap.PixelToMapCoord(new SuperMap.IS.PixelCoord(deltaX+param.pixelCenter.x,deltaY+param.pixelCenter.y),param.mapScale);
if(_d){
window.status="oldX:"+newPosition.x}
if(!_IsInCustomBounds(newPosition,bounds,param.mapScale)){
_ChooseScale(newPosition,param,bounds,param.mapScale);
if((_zoomOut&&_curMap.mapScale<param.mapScale)){
param.SetMapScale(_curMap.mapScale);
return }
if(_curMap.mapScale==param.mapScale||_isViewEntie){
return }
}
if(_curAction&&_curAction.type=="SuperMap.IS.ZoomOutAction"){
if(!_zoomIn&&param.mapScale>=_curMap.mapScale){
param.SetMapScale(_curMap.mapScale);
return }
}
var newPixelPosition=_curMap.MapCoordToPixel(newPosition,param.mapScale);
if(_curAction&&_curAction.type!="SuperMap.IS.ZoomOutAction"){
param.SetPixelCenter(newPixelPosition)}
param.SetMapCenter(newPosition);
if(_d){
window.status+="newX:"+newPosition.x}
}
function _SetCurMapScale(param,mapScale){
var mapScale1=param.mapScales[0];
for(var i=0;
i<param.mapScales.length;
i++){
if(mapScale==param.mapScales[i]){
return mapScale}
if(mapScale>param.mapScales[i]){
mapScale1=_Min(param.mapScales[i],mapScale1)}
}
return mapScale1}
function _ChooseScale(position,param,bounds,curMapScale){
var newMapScale;
var chanceScale=null;
var width=_self.container.style.pixelWidth;
var height=_self.container.style.pixelHeight;
var dScaleRatio;
var mapWidth=_curMap.PixelToMapDistance(width,curMapScale);
var mapHeight=_curMap.PixelToMapDistance(height,curMapScale);
var selfWidth=bounds.Width();
var selfHeight=bounds.Height();
if(param.mapScales==null||param.mapScales.length<=0){
var mapDistance=0;
var selfDistance=0;
var distance=0;
if(width<height){
mapDistance=mapHeight;
selfDistance=selfHeight;
distance=height}
else{
mapDistance=mapWidth;
selfDistance=selfWidth;
distance=width}
newMapScale=_curMap.ComputeMapScaleByDistance(mapDistance,selfDistance,distance);
if(curMapScale>newMapScale){
newMapScale=curMapScale}
_IsInCustomBounds(position,bounds,newMapScale)}
else{
newMapScale=_SetCurMapScale(param,curMapScale);
if(_IsInCustomBounds(position,bounds,newMapScale)){
return }
newMapScale=null;
for(var i=0;
i<param.mapScales.length;
i++){
if(_IsInCustomBounds(position,bounds,param.mapScales[i])){
if(newMapScale==null){
newMapScale=param.mapScales[i]}
if(newMapScale>param.mapScales[i]){
newMapScale=param.mapScales[i]}
}
}
}
param.SetMapScale(newMapScale)}
function _IsInCustomBounds(position,bounds,mapScale){
var curMapHeight=0;
var curMapWidth=0;
var boundsRightTopX=0;
var boundsRightTopY=0;
var boundsLeftBottomX=0;
var boundsLeftBottomY=0;
var right=true;
if(mapScale!=null){
curMapHeight=_PixelToMapDistance(_self.container.style.pixelHeight,mapScale);
curMapWidth=_PixelToMapDistance(_self.container.style.pixelWidth,mapScale)}
boundsRightTopX=bounds.rightTop.x-curMapWidth/2;
boundsRightTopY=bounds.rightTop.y-curMapHeight/2;
boundsLeftBottomX=curMapWidth/2+bounds.leftBottom.x;
boundsLeftBottomY=curMapHeight/2+bounds.leftBottom.y;
if(position.x<boundsLeftBottomX){
position.x=boundsLeftBottomX;
right=false}
if(position.y<boundsLeftBottomY){
position.y=boundsLeftBottomY;
right=false}
if(position.x>boundsRightTopX){
position.x=boundsRightTopX;
right=false}
if(position.y>boundsRightTopY){
position.y=boundsRightTopY;
right=false}
return right}
function _GenerateEventArg(curCenter,ms,error,e){
var param=_curParam.MakeCopy();
if(curCenter){
param.center=curCenter}
if(ms){
param.mapScale=ms}
if(!error){
error=""}
return new EventArguments(param,error,e)}
function _AttachEvent(event,listener){
var events=_eventsList[event];
if(!events){
events=new Array();
_eventsList[event]=events;
_eventsNameList.push(event)}
for(var i=0;
i<events.length;
i++){
if(events[i]==listener){
return true}
}
events.push(listener)}
function _DetachEvent(event,listener){
var events=_eventsList[event];
if(!events){
return }
for(var i=0;
i<events.length;
i++){
if(events[i]==listener){
events.splice(i,1)}
}
}
function _TriggerEvent(event,arguments,userContext){
var events=_eventsList[event];
if(!events){
return }
if(!arguments){
arguments=_GenerateEventArg()}
var eventsTemp=events.concat();
for(var i=0;
i<eventsTemp.length;
i++){
if(eventsTemp[i]){
eventsTemp[i](arguments,userContext)}
}
while(eventsTemp.length>0){
eventsTemp.pop()}
}
function _ClearEvents(){
while(_eventsNameList.length){
var events=_eventsList[_eventsNameList.pop()];
if(events){
while(events.length){
events.pop()}
events=null}
}
_eventsList=null;
_eventsNameList=null}
function _KeyDown(e){
e=_GetEvent(e);
var s=_keyboardPanDistance;
var x=_panningX,y=_panningY;
switch(e.keyCode){
case 9:if(_panning&&_keyboardPanning){
_StopDynamicPan()}
return true;
case 37:x=-s;
break;
case 38:y=-s;
break;
case 39:x=s;
break;
case 40:y=s;
break;
case 107:case 187:case 61:case 43:x=0;
y=0;
_ZoomIn();
break;
case 109:case 189:case 45:x=0;
y=0;
_ZoomOut();
break;
default:return false}
if(x||y){
_DynamicPan(x,y,null,true)}
if(_d){
window.status="e.keyCode:"+e.keyCode}
return false}
function _KeyUp(e){
e=_GetEvent(e);
var x=_panningX;
var y=_panningY;
switch(e.keyCode){
case 37:x=0;
break;
case 38:y=0;
break;
case 39:x=0;
break;
case 40:y=0;
break;
default:return false}
_DynamicPan(x,y,null,true);
return false}
function Logo(workLayer){
var _logoElement=null;
this.Init=function(){
if(navigator.userAgent.toLowerCase().indexOf("msie")!=-1){
_logoElement=document.createElement("div");
_logoElement.id="logoSuperMap";
_logoElement.className="logoSuperMap logoSuperMap_IE";
_logoElement.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+_scriptLocation+"../images/supermapisnet.png', sizingMethod='scale')"}
else{
_logoElement=document.createElement("img");
_logoElement.src=_scriptLocation+"../images/supermapisnet.png";
_logoElement.className="logoSuperMap"}
_Reposition();
workLayer.appendChild(_logoElement)}
;
this.Destroy=function(){
_workLayer.removeChild(_logoElement);
_logoElement=null}
;
function _Reposition(){
var logoHeight=_logoElement.style.pixelHeight;
if(!logoHeight){
logoHeight=30}
_logoElement.style.top=(_h-logoHeight-3)+"px";
_logoElement.style.left="6px";
_logoElement.style.display="block"}
function _Update(){
_Reposition()}
this.Reposition=_Reposition;
this.Update=_Update}
function _GetEventPosition(e){
e.pixelCoord=new SuperMap.IS.PixelCoord(_originX+_offsetX+_GetMouseX(e)-_x,_originY+_offsetY+_GetMouseY(e)-_y);
e.mapCoord=_curMap.PixelToMapCoord(e.pixelCoord,_curParam.mapScale);
e.offsetCoord=new SuperMap.IS.PixelCoord(e.pixelCoord.x-_originX,e.pixelCoord.y-_originY);
if(_d){
window.status="e.pixelCoord:"+e.pixelCoord.x+","+e.pixelCoord.y}
}
function _MouseDown(e){
e=_GetEvent(e);
_CancelBubble(e);
_GetEventPosition(e);
if(_curAction&&_curAction.OnMouseDown&&!_PrintMode){
_curAction.OnMouseDown(e)}
return false}
function _MouseMove(e){
e=_GetEvent(e);
_CancelBubble(e);
_GetEventPosition(e);
e.cancelTriggerGeometryEvent=false;
if(_curAction&&_curAction.OnMouseMove&&!_PrintMode){
_curAction.OnMouseMove(e)}
_TriggerGeometryEvent(e,"ongeometrymousemove");
return false}
function _MouseUp(e){
e=_GetEvent(e);
_CancelBubble(e);
_GetEventPosition(e);
if(_curAction&&_curAction.OnMouseUp&&!_PrintMode){
_curAction.OnMouseUp(e)}
try{
_kbInput.focus()}
catch(ex){
}
return false}
function _Click(e){
e=_GetEvent(e);
_CancelBubble(e);
_GetEventPosition(e);
e.cancelTriggerGeometryEvent=false;
if(_curAction&&_curAction.OnClick&&!_PrintMode){
_curAction.OnClick(e)}
_TriggerGeometryEvent(e,"ongeometryclick")}
function _DblClick(e){
e=_GetEvent(e);
_CancelBubble(e);
_GetEventPosition(e);
e.cancelTriggerGeometryEvent=false;
if(_curAction&&_curAction.OnDblClick&&!_PrintMode){
_curAction.OnDblClick(e)}
else{
_GetPosAndSize();
if(_panning||_zooming){
return false}
var param=_tempParam.MakeCopy();
param.SetPixelCenter(new SuperMap.IS.PixelCoord(_originX+_offsetX+_GetMouseX(e)-_x,_originY+_offsetY+_GetMouseY(e)-_y));
if(!e.altKey){
if(param.mapScales&&param.mapScales.length>0){
if(_curParam.zoomLevel<param.mapScales.length){
param.SetMapScale(param.mapScales[_curParam.zoomLevel])}
else{
param.SetMapScale(param.mapScales[param.mapScales.length])}
}
else{
param.SetMapScale(_curParam.mapScale*2)}
}
else{
if(param.mapScales&&param.mapScales.length>0){
if(_curParam.zoomLevel>1){
param.SetMapScale(param.mapScales[_curParam.zoomLevel-2])}
else{
param.SetMapScale(param.mapScales[0])}
}
else{
param.SetMapScale(_curParam.mapScale/2)}
}
_SetMapParam(param)}
_TriggerGeometryEvent(e,"ongeometrydblclick")}
function _ContextMenu(e){
e=_GetEvent(e);
_CancelBubble(e);
_GetEventPosition(e);
if(_curAction&&_curAction.OnContextMenu&&!_PrintMode){
_curAction.OnContextMenu(e)}
if(e.preventDefault){
e.preventDefault()}
return false}
function _MouseWheel(e){
if(_PrintMode){
return false}
e=_GetEvent(e);
_CancelBubble(e);
if(_panning||_zooming){
return false}
var delta=_GetMouseScrollDelta(e);
if(delta>0){
if(!_self.wheelZoomByMouse){
_ZoomIn()}
else{
_ZoomInByFixure(e)}
}
else{
if(delta<0){
if(!_self.wheelZoomByMouse){
_ZoomOut()}
else{
_ZoomOutByFixure(e)}
}
}
if(e.preventDefault){
e.preventDefault()}
return false}
function _TriggerGeometryEvent(e,eventName){
if(e.cancelTriggerGeometryEvent){
return }
if(!_geometryKey){
return }
var eventNames=new Array();
var eventAttached=false;
switch(eventName){
case"ongeometrydblclick":var events=_eventsList["ongeometrydblclick"];
if(events&&events.length>=0){
eventAttached=true}
if(!eventAttached){
for(var i=0;
i<_geometryDblclicks.length;
i++){
if(_geometryDblclicks[i]!=null){
eventAttached=true;
break}
}
}
if(eventAttached){
eventNames.push("ongeometrydblclick")}
break;
case"ongeometryclick":var events=_eventsList["ongeometryclick"];
if(events&&events.length>=0){
eventAttached=true}
if(!eventAttached){
for(var i=0;
i<_geometryClicks.length;
i++){
if(_geometryClicks[i]!=null){
eventAttached=true;
break}
}
}
if(eventAttached){
eventNames.push("ongeometryclick")}
break;
case"ongeometrymousemove":var events;
events=_eventsList["ongeometrymouseover"];
if(events&&events.length>0){
eventAttached=true}
events=_eventsList["ongeometrymouseout"];
if(events&&events.length>0){
eventAttached=true}
if(!eventAttached){
for(var i=0;
i<_geometryMouseOvers.length;
i++){
if(_geometryMouseOvers[i]!=null){
eventAttached=true;
break}
}
}
if(!eventAttached){
for(var i=0;
i<_geometryMouseOuts.length;
i++){
if(_geometryMouseOuts[i]!=null){
eventAttached=true;
break}
}
}
if(eventAttached){
eventNames.push("ongeometrymouseover");
eventNames.push("ongeometrymouseout")}
break;
default:return }
if(!eventAttached){
return }
function onQueryGeometryComplete(ids,userContext){
for(var i=0;
i<eventNames.length;
i++){
var relatedIds;
switch(eventNames[i]){
case"ongeometrydblclick":relatedIds=ids.concat();
break;
case"ongeometryclick":relatedIds=ids.concat();
break;
case"ongeometrymouseover":relatedIds=new Array();
if(ids.length==0){
}
else{
for(var j=0;
j<ids.length;
j++){
var exists=false;
for(var k=0;
k<_lastGeometryIDs.length;
k++){
if(ids[j]==_lastGeometryIDs[k]){
exists=true;
break}
}
if(!exists){
relatedIds.push(ids[j])}
}
}
break;
case"ongeometrymouseout":relatedIds=new Array();
if(_lastGeometryIDs.length==0){
}
else{
for(var j=0;
j<_lastGeometryIDs.length;
j++){
var exists=false;
for(var k=0;
k<ids.length;
k++){
if(_lastGeometryIDs[j]==ids[k]){
exists=true;
break}
}
if(!exists){
relatedIds.push(_lastGeometryIDs[j])}
}
}
break;
default:return }
e.ids=relatedIds;
for(var j=0;
j<relatedIds.length;
j++){
for(var k=0;
k<_geometryIDs.length;
k++){
if(relatedIds[j]==_geometryIDs[k]){
switch(eventNames[i]){
case"ongeometrydblclick":if(_geometryDblclicks[k]){
e.id=relatedIds[j];
_geometryDblclicks[k](e)}
break;
case"ongeometryclick":if(_geometryClicks[k]){
e.id=relatedIds[j];
_geometryClicks[k](e)}
break;
case"ongeometrymouseover":if(_geometryMouseOvers[k]){
e.id=relatedIds[j];
_geometryMouseOvers[k](e)}
break;
case"ongeometrymouseout":if(_geometryMouseOuts[k]){
e.id=relatedIds[j];
_geometryMouseOuts[k](e)}
break;
default:break}
}
}
}
var events=_eventsList[eventNames[i]];
if(events&&events.length>=0){
var eventsTemp=events.concat();
for(var j=0;
j<eventsTemp.length;
j++){
if(eventsTemp[j]){
eventsTemp[j](e,userContext)}
}
while(eventsTemp.length>0){
eventsTemp.pop()}
}
}
_lastGeometryIDs=ids}
_QueryGeomtryByPoint(e.mapCoord,_geometryTolerance,_calculateInClient,onQueryGeometryComplete,null,null)}
function _EmptyFunction(e){
}
function _Pan(deltaX,deltaY,manualStop){
if(deltaX==0&&deltaY==0){
return }
if(_customBounds!=null){
_PanWithinCustomBounds(_curParam,_customBounds,deltaX,deltaY)}
else{
_PanWithinBounds(_curParam,_curBounds,deltaX,deltaY)}
_offsetX=_curParam.pixelCenter.x-_originX-_w/2;
_offsetY=_curParam.pixelCenter.y-_originY-_h/2;
for(var i=0;
i<_tileLayerIDs.length;
i++){
_tileLayers[_tileLayerIDs[i]].container.style.top=-_offsetY+"px";
_tileLayers[_tileLayerIDs[i]].container.style.left=-_offsetX+"px"}
_customDiv.style.top=-_offsetY+"px";
_customDiv.style.left=-_offsetX+"px";
_panning=manualStop;
var fnUpdateMap=function(){
_UpdateMap(manualStop)}
;
_iTimeoutIDForUpdateMap=window.setTimeout(fnUpdateMap,1)}
function _DynamicPan(deltaX,deltaY,count,keyboardPan){
if(_zooming){
return }
if(!count){
count=-1}
_panningX=deltaX;
_panningY=deltaY;
_panCounter=count;
if(!deltaX&&!deltaY){
_StopDynamicPan();
return }
_keyboardPanning=keyboardPan;
if(!_panning){
_panning=true;
_StepPan();
_TriggerEvent("onstartdynamicpan")}
}
function _StepPan(){
if(!_panning){
return }
_Pan(_panningX,_panningY,true);
if(_panCounter>0){
_panCounter--}
if(_panCounter!=0){
_iTimeoutIDForStepPan=window.setTimeout(_StepPan,10)}
else{
_iTimeoutIDForStepPan=window.setTimeout(_StopDynamicPan,10)}
}
function _StopDynamicPan(){
if(_panMapX!=null&&_panMapY!=null){
var mc=new SuperMap.IS.MapCoord(_panMapX,_panMapY);
var pc=_curMap.MapCoordToPixel(mc,_curParam.mapScale);
var dx=pc.x-(_originX+_offsetX+_w/2);
var dy=pc.y-(_originY+_offsetY+_h/2);
_Pan(dx,dy,true);
_tempParam.Copy(_curParam);
_panMapX=null;
_panMapY=null}
_ComputeCenterPoint(true);
if(_d){
window.status="_panning:"+_panning}
if(_panning){
var viewBounds=_GetViewBounds();
if(!viewBounds.Equals(_oldViewBounds)){
_TriggerEvent("onenddynamicpan");
_TriggerEvent("onviewboundschanged");
_TriggerEvent("onchangeview");
_oldViewBounds=_GetViewBounds()}
_tileCheckTimes=0;
if(_iTimeoutIDForCheckTileLoaded){
window.clearTimeout(_iTimeoutIDForCheckTileLoaded);
_iTimeoutIDForCheckTileLoaded=null}
_iTimeoutIDForCheckTileLoaded=setTimeout(_CheckTileLoaded,200)}
_panningX=0;
_panningY=0;
_panning=false;
_keyboardPanning=false}
function _PanToMapCoord(x,y){
_panMapX=x;
_panMapY=y;
_PanToPixelCoord(_curMap.MapCoordToPixel(new SuperMap.IS.MapCoord(x,y),_curParam.mapScale))}
function _PanToCurCenter(param){
_PanToPixelCoord(param.pixelCenter)}
function _PanToPixelCoord(pc){
var dx=pc.x-(_originX+_offsetX+_w/2);
var dy=pc.y-(_originY+_offsetY+_h/2);
var distance=Math.sqrt(dx*dx+dy*dy);
if(!_animationEnabled){
var param=_tempParam.MakeCopy();
param.SetPixelCenter(pc);
_SetMapParam(param);
return }
var dt=Math.atan2(dy,dx);
var count=_Ceil(distance/_panStepDistance);
var actualStepDistance=_Round(distance/count);
dx=_Round(Math.cos(dt)*actualStepDistance);
dy=_Round(Math.sin(dt)*actualStepDistance);
_DynamicPan(dx,dy,count)}
function _CreateTile(x,y,ms,tileLayer){
var tile=new MapTile(tileLayer);
tile.Init(x,y,ms,(x*tileLayer.tileWidth-tileLayer.originX),(y*tileLayer.tileHeight-tileLayer.originY));
return tile}
function _ClearTiles(tcs){
while(tcs.length>0){
var ts=tcs.pop();
if(ts==null){
continue}
while(ts.length>0){
var tile=ts.pop();
if(tile!=null){
tile.Destroy();
tile=null}
}
}
}
function MapTile(tileLayer){
var _image=null;
var _tempImage=null;
var _overlay=document.createElement("div");
_overlay.id="_overlay.unInited";
var _tx=0;
var _ty=0;
var _ms=0;
var _zIndex=0;
var n=_zoomTotalSteps+1;
var xs=new Array(n);
var ys=new Array(n);
var ws=new Array(n);
var hs=new Array(n);
var _factorable=false;
var _cx=0,_cy=0,_cw=0,_ch=0;
var _nx=0,_ny=0,_nw=0,_nh=0;
var _initTime=null;
var _tileSelf=this;
this.Init=function(tileX,tileY,mapScale,x,y){
_tx=tileX;
_ty=tileY;
_ms=mapScale;
_overlay.style.font="7pt Verdana, sans-sansserif";
_overlay.style.color="Red";
_overlay.style.backgroundColor="White";
if(!_curMap.IsValidTile(_tx,_ty,_ms)){
return }
_SetCurrentState(x,y,tileLayer.tileWidth,tileLayer.tileHeight);
_SetNextState(x,y,tileLayer.tileWidth,tileLayer.tileHeight);
_PrecomputeSteps();
var imageId=tileLayer.GetTileID(_tx,_ty,_ms);
_tileSelf.id=imageId;
_overlay.id="_overlay."+imageId;
var imageBuffer=null;
for(var i=0;
i<_tileLayerIDs.length;
i++){
if(_tileLayerIDs[i]==tileLayer.id){
imageBuffer=_imageBufferCollection[i];
break}
}
if(imageBuffer&&imageBuffer[imageId]){
_image=imageBuffer[imageId];
_image.onmousedown=function(e){
return false}
;
if(!_zooming){
_SetFactor(_zoomCounter)}
_tileSelf.Loaded=true}
else{
_tempImage=new Image(tileLayer.tileWidth,tileLayer.tileHeight);
_tempImage.id=imageId;
_tempImage.onload=_ImgLoad;
_tempImage.onerror=_ImgError;
if(tileLayer.opacity!=1){
_tempImage.style.filter="alpha(opacity="+tileLayer.opacity*100+")";
_tempImage.style.opacity=tileLayer.opacity}
_initTime=new Date();
if(_isIE6&&_fixPngTransparentForIe6){
var src=tileLayer.GetTileUrl(_tx,_ty,_ms);
_tempImage.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+decodeURI(src)+"', sizingMethod='scale')";
_tempImage.src="images/spacer.gif"}
else{
_tempImage.src=tileLayer.GetTileUrl(_tx,_ty,_ms)}
}
}
;
function _RefreshUrl(){
_initTime=new Date();
var image=document.getElementById(tileLayer.GetTileID(_tx,_ty,_ms));
var src=tileLayer.GetTileUrl(_tx,_ty,_ms);
if(src.indexOf("?")==-1){
src+="?refreshTime="+_initTime.getTime()}
else{
src+="&refreshTime="+_initTime.getTime()}
if(image){
if(_isIE6&&_fixPngTransparentForIe6){
image.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+decodeURI(src)+"', sizingMethod='scale')";
image.src="images/spacer.gif"}
else{
image.src=src}
}
else{
if(_tempImage){
if(_isIE6&&_fixPngTransparentForIe6){
_tempImage.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+decodeURI(src)+"', sizingMethod='scale')";
_tempImage.src="images/spacer.gif"}
else{
_tempImage.src=src}
}
}
}
this.Destroy=function(){
if(_image){
_image.onmousedown=null;
if(!tileLayer.useImageBuffer){
var is=_image.style;
is.left=xs[0]+"px";
is.top=ys[0]+"px";
is.width=ws[0]+"px";
is.height=hs[0]+"px"}
}
_RemoveFromMap();
while(xs.length>0){
xs.pop()}
while(ys.length>0){
ys.pop()}
while(ws.length>0){
ws.pop()}
while(hs.length>0){
hs.pop()}
xs=ys=ws=hs=null;
_tileSelf=null}
;
function _SetCurrentState(x,y,width,height){
_cx=x;
_cy=y;
_cw=width;
_ch=height}
function _SetNextState(x,y,width,height){
_nx=x;
_ny=y;
_nw=width;
_nh=height}
function _ClearSteps(){
for(var i=0;
i<=_zoomTotalSteps;
i++){
xs[i]=_cx;
ys[i]=_cy;
ws[i]=_cw;
hs[i]=_ch}
}
function _PrecomputeSteps(){
for(var i=0;
i<=_zoomTotalSteps;
i++){
var a=i/_zoomTotalSteps;
var b=1-a;
xs[i]=_Floor(b*_cx+a*_nx);
ys[i]=_Floor(b*_cy+a*_ny);
ws[i]=_Ceil(b*_cw+a*_nw);
hs[i]=_Ceil(b*_ch+a*_nh)}
}
function _SetFactor(i){
if(_image==null||(_zooming&&!_factorable)){
return }
var is=_image.style;
is.left=xs[i]+"px";
is.top=ys[i]+"px";
is.width=ws[i]+"px";
is.height=hs[i]+"px";
is.zIndex=_zIndex;
if(_image.parentNode!=tileLayer.container){
is.position="absolute";
tileLayer.container.appendChild(_image)}
var os=_overlay.style;
if(_d&&i==0){
is.border="1px dashed red";
os.left=xs[i]+"px";
os.top=ys[i]+"px"}
if(_d&&_overlay.parentNode!=tileLayer.container){
_overlay.innerHTML=_image.id;
os.position="absolute";
os.zIndex=(_zIndex+1);
tileLayer.container.appendChild(_overlay)}
}
function _SwapStates(){
var temp=0;
temp=_cx;
_cx=_nx;
_nx=temp;
temp=_cy;
_cy=_ny;
_ny=temp;
temp=_cw;
_cw=_nw;
_nw=temp;
temp=_ch;
_ch=_nh;
_nh=temp}
function _RemoveFromMap(){
if(!tileLayer.useImageBuffer){
return _RemoveFromMapTrue()}
if(_preTiles.length>36){
var deletingTiles=_preTiles.splice(0,18);
while(deletingTiles.length>0){
var deletingTile=deletingTiles.pop();
deletingTile.RemoveFromMapTrue();
deletingTile=null}
deletingTiles=null}
if(!_zooming){
while(_preTiles.length>0){
var preTile=_preTiles.pop();
preTile.RemoveFromMapTrue();
preTile=null}
_RemoveFromMapTrue();
return }
if(!_image){
_RemoveFromMapTrue();
return }
_image.style.zIndex=-1;
var m=_map;
var o=this._overlay;
m=null;
o=null;
_tileSelf.unused=true;
_preTiles.push(_tileSelf);
if(_d){
window.status="preTiles:"+_preTiles.length}
return ;
if(_tempImage){
_tempImage.onload=null;
_tempImage.onerror=null;
delete _tempImage;
_tempImage=null}
if(_image){
if(_image.parentNode==tileLayer.container){
tileLayer.container.removeChild(_image)}
delete _image;
_image=null}
if(_overlay){
if(_overlay.parentNode==tileLayer.container){
tileLayer.container.removeChild(_overlay)}
_overlay=null}
}
function _RemoveFromMapTrue(){
for(var i=0;
i<_preTiles.length;
i++){
if(_preTiles[i].id==this.id){
_preTiles.splice(i,1);
break}
}
if(_tempImage){
_tempImage.onload=null;
_tempImage.onerror=null;
delete _tempImage;
_tempImage=null}
if(_image){
if(_image.parentNode==tileLayer.container){
tileLayer.container.removeChild(_image)}
delete _image;
_image=null}
if(_overlay){
if(_overlay.parentNode==tileLayer.container){
tileLayer.container.removeChild(_overlay)}
_overlay=null}
}
function _ImgLoad(){
if(_ms!=_curParam.mapScale||_tempImage==null){
return }
var index=0;
for(var i=0;
i<_tileLayerIDs.length;
i++){
if(_tileLayerIDs[i]==tileLayer.id){
index=i;
break}
}
if(_imageBufferCollection[index]==null){
_imageBufferCollection[index]=new Array()}
if(_imageBufferCollection[index].length>_imageBufferSize){
for(var i=0;
i<_imageBufferSize/3;
i++){
delete _imageBufferCollection[index][_imageBufferCollection[index][0]];
_imageBufferCollection[index].shift()}
}
_imageBufferCollection[index][_tempImage.id]=_tempImage;
_imageBufferCollection[index].push(_tempImage.id);
if(_d){
var loadedTime=new Date();
var elapsed=loadedTime.getTime()-_initTime.getTime();
window.status="elapsedTime="+elapsed}
_tempImage.onload=null;
_tempImage.onerror=null;
_image=_tempImage;
_image.onmousedown=function(e){
return false}
;
delete _tempImage;
_tempImage=null;
if(!_zooming){
_SetFactor(_zoomCounter)}
_tileSelf.Loaded=true}
function _ImgError(){
if(_ms!=_curParam.mapScale||_tempImage==null){
return }
if(_d){
var loadedTime=new Date();
var elapsed=loadedTime.getTime()-_initTime.getTime();
window.status="elapsedTime="+elapsed}
}
function _PrepareBaseTile(oldOriginX,oldOriginY,oldScale,newOriginX,newOriginY,newScale){
_SetCurrentState(_cx-_offsetX,_cy-_offsetY,_cw,_ch);
var ratio=newScale/oldScale;
_nx=_Floor((oldOriginX+_cx)*ratio-newOriginX);
_ny=_Floor((oldOriginY+_cy)*ratio-newOriginY);
_nw=_Ceil((oldOriginX+_cx+_cw)*ratio-newOriginX)-_nx;
_nh=_Ceil((oldOriginY+_cy+_ch)*ratio-newOriginY)-_ny;
_factorable=true;
_PrecomputeSteps();
_zIndex=_tile_baseZIndex;
if(_image!=null){
_image.style.zIndex=_zIndex}
}
function _PrepareSwapTile(oldOriginX,oldOriginY,oldScale,newOriginX,newOriginY,newScale){
var ratio=newScale/oldScale;
_nx=_Floor((oldOriginX+_cx)*ratio-newOriginX);
_ny=_Floor((oldOriginY+_cy)*ratio-newOriginY);
_nw=_Ceil((oldOriginX+_cx+_cw)*ratio-newOriginX)-_nx;
_nh=_Ceil((oldOriginY+_cy+_ch)*ratio-newOriginY)-_ny;
var factorX=_Ceil(_tileCountX*0.25);
var factorY=_Ceil(_tileCountY*0.25);
_factorable=(newScale>oldScale)&&(_tx<_tileX1+factorX||_tx>_tileX2-factorX||_ty<_tileY1+factorY||_ty>_tileY2-factorY);
_factorable=false;
_SwapStates();
_PrecomputeSteps();
_zIndex=_tile_swapZIndex;
if(_image!=null){
_image.style.zIndex=_zIndex}
}
function _Debug(enabled){
if(_image!=null){
_image.style.border=enabled?"1px dashed blue":"0px"}
_overlay.style.display=enabled?"block":"none"}
this.ClearSteps=_ClearSteps;
this.SetFactor=_SetFactor;
this.SwapStates=_SwapStates;
this.RemoveFromMap=_RemoveFromMap;
this.RemoveFromMapTrue=_RemoveFromMapTrue;
this.PrepareBaseTile=_PrepareBaseTile;
this.PrepareSwapTile=_PrepareSwapTile;
this.Debug=_Debug;
this.RefreshUrl=_RefreshUrl}
this.TriggerServerCompletedEvent=function(eventName,result){
var serverEventsJSON="";
var inputServerEventsInfo=document.getElementById(container.id+"_hiddenServerEventsInfo");
if(inputServerEventsInfo){
serverEventsJSON=inputServerEventsInfo.value}
var serverEvents=_eval("("+serverEventsJSON+")");
var e;
var bCommit=false;
switch(eventName){
case"DistanceMeasured":if(serverEvents.distanceMeasuredRegisted){
e=new SuperMap.IS.MeasuredEventArgs();
e.distance=result.distance;
bCommit=true}
break;
case"AreaMeasured":if(serverEvents.areaMeasuredRegisted){
e=new SuperMap.IS.MeasuredEventArgs();
e.area=result.area;
bCommit=true}
break;
case"PathFound":if(serverEvents.pathFoundRegisted){
e=new SuperMap.IS.PathFoundEventArgs(result);
bCommit=true}
break;
case"QueryCompleted":if(serverEvents.queryCompletedRegisted){
e=new SuperMap.IS.QueryCompletedEventArgs(result);
bCommit=true}
break;
case"ClosestFacilityFound":if(serverEvents.closestFacilityFoundRegisted){
e=new SuperMap.IS.ClosestFacilityFoundEventArgs(result);
bCommit=true}
break;
case"CustomEvent":if(serverEvents.customEventRegisted){
e=new SuperMap.IS.CustomEventArgs(result);
bCommit=true}
break;
default:break}
if(bCommit){
var eJSON=_ToJSON(e);
_eval(container.id+"_doPostBack('"+container.id+"', '"+eventName+"|"+eJSON+"')")}
}
;
this.TriggerServerStartingEvent=function(eventName,e,onComplete){
var bCommit=false;
var serverEventsHidden=document.getElementById(container.id+"_hiddenServerEventsInfo");
if(serverEventsHidden){
try{
var serverEventsJSON=serverEventsHidden.value;
var serverEvents=_eval("("+serverEventsJSON+")");
switch(eventName){
case"DistanceMeasuring":if(serverEvents.distanceMeasuringRegisted){
bCommit=true}
break;
case"AreaMeasuring":if(serverEvents.areaMeasuringRegisted){
bCommit=true}
break;
case"PathFinding":if(serverEvents.pathFindingRegisted){
bCommit=true}
break;
case"Querying":if(serverEvents.queryingRegisted){
bCommit=true}
break;
case"ClosestFacilityFinding":if(serverEvents.closestFacilityFindingRegisted){
bCommit=true}
break;
default:break}
}
catch(ex){
}
}
var eJSON=_ToJSON(e);
if(bCommit){
_eval(container.id+"_CallBack(arguments[0]+'|'+_ToJSON(arguments[1]),arguments[2],null)")}
else{
onComplete(eJSON)}
}
;
function _StartMap(){
_ClearTiles(_tileCollections);
_customDiv.style.top="0px";
_customDiv.style.left="0px";
for(var i=0;
i<_tileLayerIDs.length;
i++){
var distance=_PixelToMapDistance(1,_curParam.mapScale);
var distanceY=_PixelToMapDistance(1,_curParam.mapScale,true);
var x=parseInt((_curParam.center.x-_tileLayers[_tileLayerIDs[i]].layerBounds.leftBottom.x)/distance);
var y=parseInt((_tileLayers[_tileLayerIDs[i]].layerBounds.rightTop.y-_curParam.center.y)/distanceY);
_tileLayers[_tileLayerIDs[i]].originX=_Round(x-_w/2);
_tileLayers[_tileLayerIDs[i]].originY=_Round(y-_h/2)}
_originX=_Round(_curParam.pixelCenter.x-_w/2);
_originY=_Round(_curParam.pixelCenter.y-_h/2);
_offsetX=0;
_offsetY=0;
for(var i=0;
i<_tileLayerIDs.length;
i++){
var tileWidth=_tileLayers[_tileLayerIDs[i]].tileWidth;
var tileHeight=_tileLayers[_tileLayerIDs[i]].tileHeight;
var originX=_tileLayers[_tileLayerIDs[i]].originX;
var originY=_tileLayers[_tileLayerIDs[i]].originY;
_tileLayers[_tileLayerIDs[i]].container.style.top="0px";
_tileLayers[_tileLayerIDs[i]].container.style.left="0px";
_tileX1[i]=_Floor((originX-_buffer)/tileWidth);
_tileY1[i]=_Floor((originY-_buffer)/tileHeight);
_tileX2[i]=_Floor((originX+_w+_buffer)/tileWidth);
_tileY2[i]=_Floor((originY+_h+_buffer)/tileHeight);
_tileCountX[i]=_tileX2[i]-_tileX1[i]+1;
_tileCountY[i]=_tileY2[i]-_tileY1[i]+1}
for(var i=0;
i<_tileLayerIDs.length;
i++){
if(i==1&&_trackingLayerIndex<0){
if(!_tileCollections[i]){
_tileCollections[i]=new Array()}
continue}
if(i==2&&_geometryKey==""){
if(!_tileCollections[i]){
_tileCollections[i]=new Array()}
continue}
var tileLayerBounds=_tileLayers[_tileLayerIDs[i]].visibleBounds;
var tileLayerMaxScale=_tileLayers[_tileLayerIDs[i]].maxScale;
var tileLayerMinScale=_tileLayers[_tileLayerIDs[i]].minScale;
var centerX=_Floor((_tileCountX[i]-1)/2);
var centerY=_Floor((_tileCountY[i]-1)/2);
var radius=0;
var flag=0;
var startX=0;
var startY=0;
var x=0;
var y=0;
while(centerX+radius<_tileCountX[i]||centerY+radius<_tileCountY[i]){
startX=centerX+radius;
startY=centerY-radius;
x=startX;
y=startY;
flag=1;
do{
if(x>=0&&x<_tileCountX[i]&&y>=0&&y<_tileCountY[i]){
var tileBounds=_GetTileBounds(x+_tileX1[i],y+_tileY1[i],_tileLayers[_tileLayerIDs[i]].tileWidth,_tileLayers[_tileLayerIDs[i]].tileHeight,_tileLayers[_tileLayerIDs[i]].layerBounds);
var tile=null;
if((!tileLayerMaxScale||_curParam.mapScale<tileLayerMaxScale)&&(!tileLayerMinScale||_curParam.mapScale>tileLayerMinScale)&&(!tileLayerBounds||_IsBoundsTouchs(tileBounds,tileLayerBounds))){
tile=_CreateTile(x+_tileX1[i],y+_tileY1[i],_curParam.mapScale,_tileLayers[_tileLayerIDs[i]])}
if(!_tileCollections[i]){
_tileCollections[i]=new Array()}
var tileIndex=x+y*_tileCountX[i];
_tileCollections[i][tileIndex]=tile}
if(x==centerX+radius&&y==centerY+radius){
flag=0}
else{
if(x==centerX-radius&&y==centerY+radius){
flag=3}
else{
if(x==centerX-radius&&y==centerY-radius){
flag=2}
else{
if(x==centerX+radius&&y==centerY-radius){
flag=1}
}
}
}
if(radius>0){
if(flag==0){
x--}
else{
if(flag==1){
y++}
else{
if(flag==2){
x++}
else{
if(flag==3){
y--}
}
}
}
}
}
while(startX!=x||startY!=y);
radius++}
}
if(_viewBoundsBefore){
_SetMapParamHidden()}
}
function _UpdateMap(manualStop){
if(_zooming){
return }
for(var i=0;
i<_tileLayerIDs.length;
i++){
if(i==1&&_trackingLayerIndex<0){
continue}
if(i==2&&_geometryKey==""){
continue}
var tileWidth=_tileLayers[_tileLayerIDs[i]].tileWidth;
var tileHeight=_tileLayers[_tileLayerIDs[i]].tileHeight;
var ox=_tileLayers[_tileLayerIDs[i]].originX+_offsetX;
var oy=_tileLayers[_tileLayerIDs[i]].originY+_offsetY;
var _x=_Floor((ox-_buffer)/tileWidth);
var _y=_Floor((oy-_buffer)/tileHeight);
var _nx=_Floor((ox+_w+_buffer)/tileWidth);
var _ny=_Floor((oy+_h+_buffer)/tileHeight);
var tileLayerBounds=_tileLayers[_tileLayerIDs[i]].visibleBounds;
var tileLayerMaxScale=_tileLayers[_tileLayerIDs[i]].maxScale;
var tileLayerMinScale=_tileLayers[_tileLayerIDs[i]].minScale;
while(_tileX1[i]<_x){
for(var y=_tileCountY[i]-1;
y>=0;
y--){
var tileBounds=_GetTileBounds(_tileX1[i],_tileY1[i]+y,tileWidth,tileHeight,_tileLayers[_tileLayerIDs[i]].layerBounds);
var tile=_tileCollections[i].splice(y*_tileCountX[i],1)[0];
if(tile&&(!tileLayerMaxScale||_curParam.mapScale<tileLayerMaxScale)&&(!tileLayerMinScale||_curParam.mapScale>tileLayerMinScale)&&(!tileLayerBounds||_IsBoundsTouchs(tileBounds,tileLayerBounds))){
tile.RemoveFromMap()}
}
_tileX1[i]++;
_tileCountX[i]--}
while(_tileX1[i]>_x){
_tileX1[i]--;
_tileCountX[i]++;
for(var y=0;
y<_tileCountY[i];
y++){
var tileBounds=_GetTileBounds(_tileX1[i],_tileY1[i]+y,tileWidth,tileHeight,_tileLayers[_tileLayerIDs[i]].layerBounds);
var tile=null;
if((!tileLayerMaxScale||_curParam.mapScale<tileLayerMaxScale)&&(!tileLayerMinScale||_curParam.mapScale>tileLayerMinScale)&&(!tileLayerBounds||_IsBoundsTouchs(tileBounds,tileLayerBounds))){
tile=_CreateTile(_tileX1[i],_tileY1[i]+y,_curParam.mapScale,_tileLayers[_tileLayerIDs[i]])}
_tileCollections[i].splice(y*_tileCountX[i],0,tile)}
}
while(_tileY1[i]<_y){
for(var x=0;
x<_tileCountX[i];
x++){
var tileBounds=_GetTileBounds(_tileX1[i]+x,_tileY1[i],tileWidth,tileHeight,_tileLayers[_tileLayerIDs[i]].layerBounds);
var tile=_tileCollections[i].shift();
if(tile&&(!tileLayerMaxScale||_curParam.mapScale<tileLayerMaxScale)&&(!tileLayerMinScale||_curParam.mapScale>tileLayerMinScale)&&(!tileLayerBounds||_IsBoundsTouchs(tileBounds,tileLayerBounds))){
tile.RemoveFromMap()}
}
_tileY1[i]++;
_tileCountY[i]--}
while(_tileY1[i]>_y){
_tileY1[i]--;
_tileCountY[i]++;
for(var x=_tileCountX[i]-1;
x>=0;
x--){
var tileBounds=_GetTileBounds(_tileX1[i]+x,_tileY1[i],tileWidth,tileHeight,_tileLayers[_tileLayerIDs[i]].layerBounds);
var tile=null;
if((!tileLayerMaxScale||_curParam.mapScale<tileLayerMaxScale)&&(!tileLayerMinScale||_curParam.mapScale>tileLayerMinScale)&&(!tileLayerBounds||_IsBoundsTouchs(tileBounds,tileLayerBounds))){
tile=_CreateTile(_tileX1[i]+x,_tileY1[i],_curParam.mapScale,_tileLayers[_tileLayerIDs[i]])}
_tileCollections[i].unshift(tile)}
}
while(_tileX2[i]>_nx){
for(var y=_tileCountY[i]-1;
y>=0;
y--){
var tileBounds=_GetTileBounds(_tileX2[i],_tileY1[i]+y,tileWidth,tileHeight,_tileLayers[_tileLayerIDs[i]].layerBounds);
var tile=_tileCollections[i].splice(y*_tileCountX[i]+_tileCountX[i]-1,1)[0];
if(tile&&(!tileLayerMaxScale||_curParam.mapScale<tileLayerMaxScale)&&(!tileLayerMinScale||_curParam.mapScale>tileLayerMinScale)&&(!tileLayerBounds||_IsBoundsTouchs(tileBounds,tileLayerBounds))){
tile.RemoveFromMap()}
}
_tileX2[i]--;
_tileCountX[i]--}
while(_tileX2[i]<_nx){
_tileX2[i]++;
_tileCountX[i]++;
for(var y=0;
y<_tileCountY[i];
y++){
var tileBounds=_GetTileBounds(_tileX2[i],_tileY1[i]+y,tileWidth,tileHeight,_tileLayers[_tileLayerIDs[i]].layerBounds);
var tile=null;
if((!tileLayerMaxScale||_curParam.mapScale<tileLayerMaxScale)&&(!tileLayerMinScale||_curParam.mapScale>tileLayerMinScale)&&(!tileLayerBounds||_IsBoundsTouchs(tileBounds,tileLayerBounds))){
tile=_CreateTile(_tileX2[i],_tileY1[i]+y,_curParam.mapScale,_tileLayers[_tileLayerIDs[i]])}
_tileCollections[i].splice(y*_tileCountX[i]+_tileCountX[i]-1,0,tile)}
}
while(_tileY2[i]>_ny){
for(var x=0;
x<_tileCountX[i];
x++){
var tileBounds=_GetTileBounds(_tileX2[i]-x,_tileY2[i],tileWidth,tileHeight,_tileLayers[_tileLayerIDs[i]].layerBounds);
var tile=_tileCollections[i].pop();
if(tile&&(!tileLayerMaxScale||_curParam.mapScale<tileLayerMaxScale)&&(!tileLayerMinScale||_curParam.mapScale>tileLayerMinScale)&&(!tileLayerBounds||_IsBoundsTouchs(tileBounds,tileLayerBounds))){
tile.RemoveFromMap()}
}
_tileY2[i]--;
_tileCountY[i]--}
while(_tileY2[i]<_ny){
_tileY2[i]++;
_tileCountY[i]++;
for(var x=0;
x<_tileCountX[i];
x++){
var tileBounds=_GetTileBounds(_tileX1[i]+x,_tileY2[i],tileWidth,tileHeight,_tileLayers[_tileLayerIDs[i]].layerBounds);
var tile=null;
if((!tileLayerMaxScale||_curParam.mapScale<tileLayerMaxScale)&&(!tileLayerMinScale||_curParam.mapScale>tileLayerMinScale)&&(!tileLayerBounds||_IsBoundsTouchs(tileBounds,tileLayerBounds))){
tile=_CreateTile(_tileX1[i]+x,_tileY2[i],_curParam.mapScale,_tileLayers[_tileLayerIDs[i]])}
_tileCollections[i].push(tile)}
}
}
_SetMapParamHidden();
if(!manualStop){
var viewBounds=_GetViewBounds();
if(!viewBounds.Equals(_oldViewBounds)){
_TriggerEvent("onviewboundschanged");
_TriggerEvent("onchangeview");
_oldViewBounds=_GetViewBounds()}
}
}
function _SetMapParam(param){
if(_zooming||_panning||_dragging){
return }
var center=param.center;
if(param.GetViewType()=="mr"){
center=param.mapRect.Center()}
_curMap=_map;
_viewBoundsBefore=_GetViewBounds();
container.style.backgroundColor="#eeeeee";
param.Resolve(_curMap,_w,_h);
center=param.center;
_tempParam.Copy(param);
_curMap.ValidateMapScale(param);
if(_customBounds!=null){
_curBounds=_customBounds}
else{
_curBounds=_curMap.GetBounds()}
_EnsureWithinBounds(param,_curBounds);
param.Resolve(_curMap,_w,_h);
var deltaX=param.GetPixelX(_curParam.mapScale)-_curParam.pixelCenter.x;
var deltaY=param.GetPixelY(_curParam.mapScale)-_curParam.pixelCenter.y;
var distance=Math.sqrt(deltaX*deltaX+deltaY*deltaY);
var panOnly=(distance<_w&&distance<_h)&&(param.mapScale==_curParam.mapScale)&&_animationEnabled;
if(panOnly&&!_switching){
if(deltaX==0&&deltaY==0){
return }
_PanToMapCoord(center.x,center.y);
return }
_mapScale=_curParam.mapScale;
if(_curParam.mapScale!=param.mapScale){
_curMap.mapScale=param.mapScale;
_TriggerEvent("onstartzoom");
_zooming=true}
var scaleRatio=param.mapScale/_curParam.mapScale;
var dynamic=(distance<_w&&distance<_h)&&_animationEnabled&&scaleRatio>1/8&&scaleRatio<8;
if(dynamic&&!_switching){
var oldZoom=_curParam.mapScale;
var newZoom=param.mapScale;
_oldTileCollections=_tileCollections;
_tileCollections=new Array();
for(var i=0;
i<_oldTileCollections.length;
i++){
var oldTiles=_oldTileCollections[i];
var oldOriginX=_tileLayers[_tileLayerIDs[i]].originX+_offsetX;
var oldOriginY=_tileLayers[_tileLayerIDs[i]].originY+_offsetY;
var distance=_PixelToMapDistance(1,param.mapScale);
var distanceY=_PixelToMapDistance(1,param.mapScale,true);
var x=parseInt((param.center.x-_tileLayers[_tileLayerIDs[i]].layerBounds.leftBottom.x)/distance);
var y=parseInt((_tileLayers[_tileLayerIDs[i]].layerBounds.rightTop.y-param.center.y)/distanceY);
var newOriginX=_Round(x-_w/2);
var newOriginY=_Round(y-_h/2);
for(var j=0;
j<oldTiles.length;
j++){
if(oldTiles[j]!=null){
oldTiles[j].PrepareBaseTile(oldOriginX,oldOriginY,oldZoom,newOriginX,newOriginY,newZoom)}
}
if(i==0){
for(var j=0;
j<_marks.length;
j++){
_marks[j].PrepareForZoom(newOriginX,newOriginY,newZoom)}
}
}
_HidePolylines();
_curParam.Destroy();
_curParam=param;
_StartMap();
for(var i=0;
i<_tileCollections.length;
i++){
var tiles=_tileCollections[i];
for(var j=0;
j<tiles.length;
j++){
if(tiles[j]!=null){
tiles[j].PrepareSwapTile(oldOriginX,oldOriginY,oldZoom,newOriginX,newOriginY,newZoom)}
}
}
_zoomCounter=1;
_SetFactor();
return }
_oldTileCollections=_tileCollections;
_tileCollections=new Array();
_curParam.Destroy();
_curParam=param;
_HidePolylines();
_StartMap();
_SwapStates();
_RepositionMarks();
_RepositionLines();
_RepositionPolygons()}
function _SetFactor(){
if(!_zooming){
return }
for(var i=0;
i<_oldTileCollections.length;
i++){
var oldTiles=_oldTileCollections[i];
for(var j=0;
j<oldTiles.length;
j++){
if(oldTiles[j]!=null){
oldTiles[j].SetFactor(_zoomCounter)}
}
}
for(var i=0;
i<_tileCollections.length;
i++){
var tiles=_tileCollections[i];
for(var j=0;
j<tiles.length;
j++){
if(tiles[j]!=null){
tiles[j].SetFactor(_zoomCounter)}
}
}
for(var i=0;
i<_marks.length;
i++){
_marks[i].SetFactor(_zoomCounter)}
if(_zoomCounter<_zoomTotalSteps){
_zoomCounter++;
_iTimeoutIDForSetFactor=window.setTimeout(_SetFactor,1)}
else{
_zoomCounter=0;
_SwapStates()}
}
function _SwapStates(){
_ClearTiles(_oldTileCollections);
_oldTileCollections=null;
_zooming=false;
for(var i=0;
i<_tileCollections.length;
i++){
var tiles=_tileCollections[i];
if(tiles==null){
continue}
for(var j=0;
j<tiles.length;
j++){
if(tiles[j]!=null){
tiles[j].SwapStates();
tiles[j].ClearSteps();
tiles[j].SetFactor(0)}
}
}
for(var i=0;
i<_marks.length;
i++){
_marks[i].SwapStates();
_marks[i].ClearSteps();
_marks[i].SetFactor(0)}
_iTimeoutIDForShowPolylines=window.setTimeout(_ShowPolylines,250);
if(_mapScale!=_curParam.mapScale){
_TriggerEvent("onendzoom")}
if(!_switching){
var viewBounds=_GetViewBounds();
if(!viewBounds.Equals(_oldViewBounds)){
_TriggerEvent("onviewboundschanged");
_oldViewBounds=_GetViewBounds()}
_TriggerEvent("onchangeview")}
else{
_TriggerEvent("onviewboundschanged");
_TriggerEvent("onchangeview")}
_tileCheckTimes=0;
if(_iTimeoutIDForCheckTileLoaded){
window.clearTimeout(_iTimeoutIDForCheckTileLoaded);
_iTimeoutIDForCheckTileLoaded=null}
_iTimeoutIDForCheckTileLoaded=setTimeout(_CheckTileLoaded,200);
try{
CollectGarbage()}
catch(ex){
}
}
this.ViewEntire=function(){
var param=_tempParam.MakeCopy();
var mapBounds=_GetMapBounds();
if(_customBounds!=null){
mapBounds=_customBounds}
var viewBounds=_GetViewBounds();
var widthRatio=mapBounds.Width()/viewBounds.Width();
var heightRatio=mapBounds.Height()/viewBounds.Height();
var ratio=widthRatio>heightRatio?widthRatio:heightRatio;
if(Math.abs(1-ratio)<Math.pow(10,-13)){
ratio=1}
var mapScale=param.mapScale/ratio;
param.SetMapScale(mapScale);
param.SetMapCenter(mapBounds.Center());
_isViewEntie=true;
_SetMapParam(param);
_isViewEntie=false}
;
function _ViewByBounds(x1,y1,x2,y2){
var param=_tempParam.MakeCopy();
param.SetMapRect(new SuperMap.IS.MapRect(x1,y1,x2,y2));
_SetMapParam(param)}
function _ViewByPoints(mcs){
if(!mcs||mcs.constructor!=Array){
return }
var a=mcs[0].x;
var b=mcs[0].y;
var c=a;
var d=b;
for(var i=1;
i<mcs.length;
i++){
a=_Min(a,mcs[i].x);
b=_Min(b,mcs[i].y);
c=_Max(c,mcs[i].x);
d=_Max(d,mcs[i].y)}
var dx=(c-a)*0.1;
var dy=(d-b)*0.1;
a-=dx;
b-=dy;
c+=dx;
d+=dy;
_ViewByBounds(_ClipMapX(a),_ClipMapY(b),_ClipMapX(c),_ClipMapY(d))}
function _ViewByPoint(x,y){
var mc=new SuperMap.IS.MapCoord(x,y);
var viewBounds=_GetViewBounds();
var oldWidth=viewBounds.Width();
var oldHeight=viewBounds.Height();
viewBounds.leftBottom.x+=oldWidth*0.1;
viewBounds.leftBottom.y+=oldHeight*0.1;
viewBounds.rightTop.x-=oldWidth*0.1;
viewBounds.rightTop.y-=oldHeight*0.1;
if(_customBounds!=null){
if(x<_customBounds.leftBottom.x||y<_customBounds.leftBottom.y||x>_customBounds.rightTop.x||y>_customBounds.rightTop.x){
return }
}
if(viewBounds.Contains(mc)){
return }
var param=_tempParam.MakeCopy();
param.SetMapCenter(new SuperMap.IS.MapCoord(x,y));
_SetMapParam(param)}
function _SetMapScale(ms){
var param=_tempParam.MakeCopy();
param.SetMapScale(ms);
_SetMapParam(param)}
function _SetZoomLevel(level){
var param=_tempParam.MakeCopy();
param.SetZoomLevel(level);
_SetMapParam(param)}
function _ZoomIn(){
var param=_tempParam.MakeCopy();
if(param.mapScales&&param.mapScales.length>0){
if(_curParam.zoomLevel<param.mapScales.length){
param.SetMapScale(param.mapScales[_curParam.zoomLevel])}
else{
param.SetMapScale(param.mapScales[param.mapScales.length])}
}
else{
param.SetMapScale(_curParam.mapScale*2)}
_zoomIn=true;
_SetMapParam(param);
_zoomIn=false}
function _Zoom(ratio){
var param=_tempParam.MakeCopy();
param.SetMapScale(_curParam.mapScale*ratio);
_SetMapParam(param)}
function _ZoomOut(){
var param=_tempParam.MakeCopy();
if(param.mapScales&&param.mapScales.length>0){
if(_curParam.zoomLevel>1){
param.SetMapScale(param.mapScales[_curParam.zoomLevel-2])}
else{
param.SetMapScale(param.mapScales[0])}
}
else{
param.SetMapScale(_curParam.mapScale/2)}
_zoomOut=true;
_SetMapParam(param);
_zoomOut=false}
function _SetCenterAndZoom(x,y,ms){
var param=_tempParam.MakeCopy();
var position=new SuperMap.IS.MapCoord(x,y);
param.SetMapCenter(position);
param.SetMapScale(ms);
_SetMapParam(param)}
function _ClipMapX(x){
return _Clip(x,mapBounds.leftBottom.x,mapBounds.rightTop.x)}
function _ClipMapY(y){
return _Clip(y,mapBounds.leftBottom.y,mapBounds.rightTop.y)}
function _Clip(n,minValue,maxValue){
if(n<minValue){
return minValue}
if(n>maxValue){
return maxValue}
return n}
function _GetMapParam(){
return _curParam.MakeCopy()}
function _ZoomInByFixure(e){
var param=_tempParam.MakeCopy();
if(param.mapScales&&param.mapScales.length>0){
if(_curParam.zoomLevel<param.mapScales.length){
param.SetMapScale(param.mapScales[_curParam.zoomLevel])}
else{
param.SetMapScale(param.mapScales[param.mapScales.length])}
}
else{
param.SetMapScale(_curParam.mapScale*2)}
var cp=_MapCoordToPixel(param.center);
var dx=_PixelToMapDistance(cp.x-e.x,param.mapScale);
var dy=_PixelToMapDistance(cp.y-e.y,param.mapScale);
var emc=_PixelToMapCoord(e);
param.center.x=emc.x+dx;
param.center.y=emc.y-dy;
_SetCenterAndZoom(param.center.x,param.center.y,param.mapScale)}
function _ZoomOutByFixure(e){
var param=_tempParam.MakeCopy();
if(param.mapScales&&param.mapScales.length>0){
if(_curParam.zoomLevel>1){
param.SetMapScale(param.mapScales[_curParam.zoomLevel-2])}
else{
param.SetMapScale(param.mapScales[0])}
}
else{
param.SetMapScale(_curParam.mapScale/2)}
var cp=_MapCoordToPixel(param.center);
var dx=_PixelToMapDistance(cp.x-e.x,param.mapScale);
var dy=_PixelToMapDistance(cp.y-e.y,param.mapScale);
var emc=_PixelToMapCoord(e);
param.center.x=emc.x+dx;
param.center.y=emc.y-dy;
_SetCenterAndZoom(param.center.x,param.center.y,param.mapScale)}
function _GetOriginX(){
return _originX}
function _GetOriginY(){
return _originY}
function _GetOffsetX(){
return _offsetX}
function _GetOffsetY(){
return _offsetY}
function _GetContainerX(){
return _x}
function _GetContainerY(){
return _y}
function _SetAction(action){
if(_curAction&&(_curAction!=_zoomInAction&&_curAction!=_panAction)){
_curAction.Destroy()}
if(action){
action.Init(_self)}
_curAction=action;
_SetClientActionHidden(_curAction)}
function _SetClientActionHidden(action){
var hiddenLayer=document.getElementById(container.id+"_hiddenClientAction");
if(!hiddenLayer){
return }
if(action==null){
hiddenLayer.value=""}
else{
hiddenLayer.value=action.GetJSON()}
}
function _GetAction(){
return _curAction}
function _SelectGroup(groupID,curObject){
if(!groupID){
groupID="unClassified"}
groupID=_self.id+"_"+groupID;
var groupDiv=document.getElementById(groupID);
if(!groupDiv){
groupDiv=document.createElement("div");
groupDiv.id=groupID;
_customDiv.appendChild(groupDiv)}
if(curObject.parentNode){
curObject.parentNode.removeChild(curObject)}
groupDiv.appendChild(curObject)}
function _SetGroupVisible(groupID,visible){
groupID=_self.id+"_"+groupID;
var group=document.getElementById(groupID);
if(!group){
return }
group.style.visibility=visible?"":"hidden"}
function _SetGroupZindex(groupID,zIndex){
groupID=_self.id+"_"+groupID;
var group=document.getElementById(groupID);
if(group){
group.style.zIndex=zIndex}
}
function _OnTrackingLayerLoad(){
this.style.visibility="visible"}
function _AddGeometry(id,geometry,style,zIndex,onClick,onDblClick,onMouseOver,onMouseOut){
for(var i=0;
i<_geometryIDs.length;
i++){
if(_geometryIDs[i]==id){
return false}
}
_geometryIDs.push(id);
_geometries.push(geometry);
_geometryStyles.push(style);
_geometryIndexs.push(zIndex);
_geometryClicks.push(onClick);
_geometryDblclicks.push(onDblClick);
_geometryMouseOvers.push(onMouseOver);
_geometryMouseOuts.push(onMouseOut);
return true}
function _AddGeometries(ids,geometries,style,zIndex,onClick,onDblClick,onMouseOver,onMouseOut){
if(!ids||!geometries||!ids.length||!geometries.length){
return false}
if(ids.length!=geometries.length){
return false}
var flag=true;
for(var i=0;
i<ids.length;
i++){
flag&=_AddGeometry(ids[i],geometries[i],style,zIndex,onClick,onDblClick,onMouseOver,onMouseOut)}
return flag}
function _InsertGeometry(id,geometry,style,zIndex,onClick,onDblClick,onMouseOver,onMouseOut){
for(var i=0;
i<_geometryIDs.length;
i++){
if(_geometryIDs[i]==id){
_geometryIDs[i]=id;
_geometries[i]=geometry;
_geometryStyles[i]=style;
_geometryIndexs[i]=zIndex;
_geometryClicks[i]=onClick;
_geometryDblclicks[i]=onDblClick;
_geometryMouseOvers[i]=onMouseOver;
_geometryMouseOuts[i]=onMouseOut;
return true}
}
_geometryIDs.push(id);
_geometries.push(geometry);
_geometryStyles.push(style);
_geometryIndexs.push(zIndex);
_geometryClicks.push(onClick);
_geometryDblclicks.push(onDblClick);
_geometryMouseOvers.push(onMouseOver);
_geometryMouseOuts.push(onMouseOut);
return true}
function _InsertGeometries(ids,geometries,style,zIndex,onClick,onDblClick,onMouseOver,onMouseOut){
if(!ids||!geometries||!ids.length||!geometries.length){
return false}
if(ids.length!=geometries.length){
return false}
var flag=true;
for(var i=0;
i<ids.length;
i++){
flag&=_InsertGeometry(ids[i],geometries[i],style,zIndex,onClick,onDblClick,onMouseOver,onMouseOut)}
return flag}
function _RemoveGeometry(id){
for(var i=0;
i<_geometryIDs.length;
i++){
if(_geometryIDs[i]==id){
_geometryIDs.splice(i,1);
_geometries.splice(i,1);
_geometryStyles.splice(i,1);
_geometryIndexs.splice(i,1);
_geometryClicks.splice(i,1);
_geometryDblclicks.splice(i,1);
_geometryMouseOvers.splice(i,1);
_geometryMouseOuts.splice(i,1);
return true}
}
return false}
function _RemoveGeometries(ids){
if(!ids||!ids.length){
return false}
var flag=true;
for(var i=0;
i<ids.length;
i++){
flag&=_RemoveGeometry(ids[i])}
return flag}
function _ClearGeometries(){
while(_geometryIDs.length>0){
_geometryIDs.pop();
var geo=_geometries.pop();
if(geo){
geo.Destroy();
geo=null}
var style=_geometryStyles.pop();
if(style){
style.Destroy();
style=null}
_geometryIndexs.pop();
_geometryClicks.pop();
_geometryDblclicks.pop();
_geometryMouseOvers.pop();
_geometryMouseOuts.pop()}
return true}
function _GetGeometry(id){
for(var i=0;
i<_geometryIDs.length;
i++){
if(_geometryIDs[i]==id){
return _geometries[i]}
}
return null}
function _GetGeometries(ids){
var geometries=new Array();
for(var i=0;
i<ids.length;
i++){
geometries.push(_GetGeometry(ids[i]))}
return geometries}
function _SetDefaultGeometryStyle(defaultStyle){
_defaultGeometryStyle=defaultStyle}
function _SetGeometryTolerance(tolerance){
_geometryTolerance=tolerance}
function _SetCalculateInClient(calculateInClient){
_calculateInClient=calculateInClient}
function _UpdateGeometries(onComplete,onError,userContext){
var queryUrl=_mapHandler+"common.ashx";
var mapName=_mapName;
function onUpdateGeometryComplete(responseText){
if(!responseText){
if(onComplete){
onComplete(null,userContext)}
return }
while(_lastGeometryIDs.length>0){
_lastGeometryIDs.pop()}
_geometryKey=_eval("("+responseText+")");
_RefreshGeometryLayer();
if(onComplete){
onComplete(_geometryKey,userContext)}
}
var reuqestManager=new SuperMap.IS.RequestManager(queryUrl,onUpdateGeometryComplete,onError,userContext);
reuqestManager.AddQueryString("map",mapName);
reuqestManager.AddQueryString("method","UpdateGeometry");
reuqestManager.AddQueryString("geometryIDs",_ToJSON(_geometryIDs));
reuqestManager.AddQueryString("geometries",_ToJSON(_geometries));
reuqestManager.AddQueryString("geometrystyles",_ToJSON(_geometryStyles));
reuqestManager.AddQueryString("geometryIndexs",_ToJSON(_geometryIndexs));
reuqestManager.AddQueryString("defaultStyle",_ToJSON(_defaultGeometryStyle));
reuqestManager.Send();
reuqestManager.Destroy();
reuqestManager=null}
function _QueryGeomtryByPoint(position,tolerance,calculateInClient,onComplete,onError,userContext){
if(!_geometryKey){
return }
var distance=_PixelToMapDistance(tolerance,_curParam.mapScale);
if(calculateInClient){
var ids=new Array();
for(var i=0;
i<_geometries.length;
i++){
if(isPointInGeometry(position,_geometries[i],distance)){
ids.push(_geometryIDs[i])}
}
if(onComplete){
onComplete(ids,userContext)}
}
else{
var queryUrl=_mapHandler+"common.ashx";
var mapName=_mapName;
function onQueryGeometryComplete(responseText){
if(!responseText){
if(onComplete){
onComplete(null,userContext)}
return }
var ids=_eval("("+responseText+")");
if(onComplete){
onComplete(ids,userContext)}
}
var reuqestManager=new SuperMap.IS.RequestManager(queryUrl,onQueryGeometryComplete,onError,userContext);
reuqestManager.AddQueryString("map",mapName);
reuqestManager.AddQueryString("method","QueryGeomtryByPoint");
reuqestManager.AddQueryString("geometryKey",_geometryKey);
reuqestManager.AddQueryString("position",_ToJSON(position));
reuqestManager.AddQueryString("tolerance",distance);
reuqestManager.Send();
reuqestManager.Destroy();
reuqestManager=null}
}
function _AddMark(id,x,y,w,h,innerHtml,className,zIndex,groupID,alignStyle){
var mk=new Mark();
mk.Init(id,x,y,w,h,innerHtml,className,zIndex,groupID,alignStyle);
_marks.push(mk);
_SetMarksHidden();
return mk.div}
function _InsertMark(id,x,y,w,h,innerHtml,className,zIndex,groupID,alignStyle){
for(var i=0;
i<_marks.length;
i++){
var mk=_marks[i];
if(mk.id==id){
mk.Init(id,x,y,w,h,innerHtml,className,zIndex,groupID,alignStyle);
_SetMarksHidden();
return mk.div}
}
return _AddMark(id,x,y,w,h,innerHtml,className,zIndex,groupID,alignStyle)}
function _RemoveMark(id){
for(var i=0;
i<_marks.length;
i++){
var mk=_marks[i];
if(mk.id==id){
_marks.splice(i,1);
mk.Destroy();
mk=null;
_SetMarksHidden();
return }
}
}
function _ClearMarks(){
while(_marks.length>0){
var mk=_marks.pop();
mk.Destroy();
mk=null}
_SetMarksHidden()}
function _RepositionMarks(){
for(var i=0;
i<_marks.length;
i++){
_marks[i].Reposition()}
}
function Mark(){
var div=document.createElement("div");
div.mk=this;
this.div=div;
var _cx=0,_cy=0,_nx=0,_ny=0;
var mc=new SuperMap.IS.MapCoord(0,0);
var width=0;
var height=0;
var n=_zoomTotalSteps+1;
var xs=new Array(n);
var ys=new Array(n);
this.Init=function(id,x,y,w,h,innerHtml,className,zIndex,groupID,alignStyle){
this.id=id;
div.id=id;
div.className=className;
div.style.position="absolute";
div.innerHTML=innerHtml;
this.x=x;
this.y=y;
this.w=w;
this.h=h;
this.innerHtml=innerHtml;
this.className=className;
this.zIndex=zIndex;
this.groupID=groupID;
this.alignStyle=alignStyle;
if(!zIndex){
div.style.zIndex=_customLayer_baseZIndex}
else{
div.style.zIndex=zIndex}
if(!params.fixedView){
div.detachEvent("onmousedown",_EmptyFunction);
div.detachEvent("ondblclick",_MouseDoubleClick);
div.detachEvent("onmousewheel",_MouseWheel);
div.attachEvent("onmousedown",_EmptyFunction);
div.attachEvent("ondblclick",_MouseDoubleClick);
div.attachEvent("onmousewheel",_MouseWheel)}
mc.x=x;
mc.y=y;
width=w;
height=h;
var pc=_curMap.MapCoordToPixel(mc,_curParam.mapScale);
if(pc){
_cx=_Round(pc.x-_originX);
_cy=_Round(pc.y-_originY);
_nx=_cx;
_ny=_cy;
_PrecomputeSteps();
_SetFactor(0);
div.style.display="block"}
else{
div.style.display="none"}
_RemoveFromMap();
_SelectGroup(groupID,this.div);
_AdjustCustomMarkPosition(id,alignStyle)}
;
this.Destroy=function(){
div.detachEvent("onmousedown",_EmptyFunction);
div.detachEvent("ondblclick",_MouseDoubleClick);
div.detachEvent("onmousewheel",_MouseWheel);
div.mk=null;
while(div.childNodes.length>0){
div.removeChild(div.childNodes[0])}
div.innerHtml=null;
_RemoveFromMap();
div=null;
this.div=null;
while(xs.length>0){
xs.pop()}
xs=null;
while(ys.length>0){
ys.pop()}
ys=null}
;
this.GetMapCoordX=function(){
return mc.x}
;
this.GetMapCoordY=function(){
return mc.y}
;
function _ClearSteps(){
var n=_zoomTotalSteps;
for(var i=0;
i<=n;
i++){
xs[i]=_cx-width/2;
ys[i]=_cy-height/2}
}
function _PrecomputeSteps(){
var n=_zoomTotalSteps;
for(var i=0;
i<=n;
i++){
var a=i/n;
var b=1-a;
xs[i]=_Floor(b*_cx+a*_nx-width/2);
ys[i]=_Floor(b*_cy+a*_ny-height/2)}
}
function _SetFactor(i){
div.style.left=xs[i]+"px";
div.style.top=ys[i]+"px";
_AdjustCustomMarkPosition(div.mk.id,div.mk.alignStyle)}
function _SwapStates(){
var temp=0;
temp=_cx;
_cx=_nx;
_nx=temp;
temp=_cy;
_cy=_ny;
_ny=temp}
function _Reposition(){
var pc=_curMap.MapCoordToPixel(mc,_curParam.mapScale);
if(pc){
_cx=_Round(pc.x-_originX);
_cy=_Round(pc.y-_originY);
_ClearSteps();
_SetFactor(0);
div.style.display="block"}
else{
div.style.display="none"}
}
function _PrepareForZoom(newOriginX,newOriginY,newZoom){
_cx-=_offsetX;
_cy-=_offsetY;
var pc=_curMap.MapCoordToPixel(mc,newZoom);
if(pc){
_nx=_Round(pc.x-newOriginX);
_ny=_Round(pc.y-newOriginY);
_PrecomputeSteps();
div.style.display="block"}
else{
div.style.display="none"}
}
function _RemoveFromMap(){
if(div.parentNode!=null){
div.parentNode.removeChild(div)}
}
function _MouseDoubleClick(e){
e=_GetEvent(e);
_CancelBubble(e);
if(_panning||_zooming){
return false}
var param=_tempParam.MakeCopy();
param.SetMapCenter(mc);
if(!e.altKey){
param.SetMapScale(_curParam.mapScale*2)}
else{
param.SetMapScale(_curParam.mapScale/2)}
_SetMapParam(param);
return false}
function _MouseWheel(e){
e=_GetEvent(e);
_CancelBubble(e);
if(_panning||_zooming){
return false}
var delta=_GetMouseScrollDelta(e);
if(delta>0){
if(!_self.wheelZoomByMouse){
_ZoomIn()}
else{
_ZoomInByFixure(e)}
}
else{
if(delta<0){
if(!_self.wheelZoomByMouse){
_ZoomOut()}
else{
_ZoomOutByFixure(e)}
}
}
return false}
this.ClearSteps=_ClearSteps;
this.SetFactor=_SetFactor;
this.SwapStates=_SwapStates;
this.Reposition=_Reposition;
this.PrepareForZoom=_PrepareForZoom;
this.RemoveFromMap=_RemoveFromMap}
function _AddLine(id,xs,ys,strokeWeight,strokeColor,opacity,zIndex,groupID,parts){
if(id==null||xs==null||ys==null){
return null}
var line=new Shape(false);
line.Init(id,xs,ys,strokeWeight,strokeColor,null,opacity,zIndex,groupID,parts);
_lines.push(line);
_SetLinesHidden();
return line}
function _InsertLine(id,xs,ys,strokeWeight,strokeColor,opacity,zIndex,groupID,parts){
if(id==null||xs==null||ys==null){
return null}
for(var i=0;
i<_lines.length;
i++){
var line=_lines[i];
if(line.id==id){
line.Init(id,xs,ys,strokeWeight,strokeColor,null,opacity,zIndex,groupID,parts);
_SetLinesHidden();
return line}
}
return _AddLine(id,xs,ys,strokeWeight,strokeColor,opacity,zIndex,groupID,parts)}
function _RemoveLine(id){
for(var i=0;
i<_lines.length;
i++){
var line=_lines[i];
if(line.id==id){
_lines.splice(i,1);
line.Destroy();
_SetLinesHidden();
return }
}
}
function _ClearLines(){
while(_lines.length>0){
_lines.pop().Destroy()}
_SetLinesHidden()}
function _RepositionLines(){
for(var i=0;
i<_lines.length;
i++){
_lines[i].Reposition()}
}
function _RepositionPolygons(){
for(var i=0;
i<_polygons.length;
i++){
_polygons[i].Reposition()}
}
function _AddPolygon(id,xs,ys,strokeWeight,strokeColor,fillColor,fillOpacity,zIndex,groupID,parts){
if(id==null||xs==null||ys==null){
return null}
var polygon=new Shape(true);
polygon.Init(id,xs,ys,strokeWeight,strokeColor,fillColor,fillOpacity,zIndex,groupID,parts);
_polygons.push(polygon);
_SetPolygonsHidden();
return polygon}
function _InsertPolygon(id,xs,ys,strokeWeight,strokeColor,fillColor,fillOpacity,zIndex,groupID,parts){
if(id==null||xs==null||ys==null){
return null}
var polygon=null;
for(var i=0;
i<_polygons.length;
i++){
polygon=_polygons[i];
if(polygon.id==id){
polygon.Init(id,xs,ys,strokeWeight,strokeColor,fillColor,fillOpacity,zIndex,groupID,parts);
_SetPolygonsHidden();
return polygon}
}
polygon=null;
return _AddPolygon(id,xs,ys,strokeWeight,strokeColor,fillColor,fillOpacity,zIndex,groupID,parts)}
function _RemovePolygon(id){
for(var i=0;
_polygons&&i<_polygons.length;
i++){
var polygon=_polygons[i];
if(polygon.id==id){
_polygons.splice(i,1);
polygon.Destroy();
_SetPolygonsHidden();
return }
}
}
function _ClearPolygons(){
while(_polygons.length>0){
_polygons.pop().Destroy()}
_SetPolygonsHidden()}
function Shape(closed){
if(!closed){
closed=false}
var m_isPolygon=closed;
var m_defaultStrokeWeight="3pt";
var m_defaultStrokeColor="#316AC5";
var m_defaultFillColor="#316AC5";
var m_defaultFillOpacity="0.6";
var m_visible=true;
var m_shape=null;
var m_drawLayer=null;
var m_jg=null;
var m_fill=null;
var m_stroke=null;
var m_shapeId="";
var m_strokeWeight="";
var m_strokeColor="";
var m_fillColor="";
var m_fillOpacity="";
var m_zIndex=0;
var m_groupID="";
var m_startX=0;
var m_startY=0;
var m_endX=0;
var m_endY=0;
var m_arrayX=null;
var m_arrayY=null;
var m_parts=null;
var m_tempParts=null;
function _Init(id,xs,ys,strokeWeight,strokeColor,fillColor,fillOpacity,zIndex,groupID,parts){
if(!strokeWeight){
strokeWeight=m_defaultStrokeWeight}
else{
strokeWeight+=""}
if(!strokeColor){
strokeColor=m_defaultStrokeColor}
if(!fillColor){
fillColor=m_defaultFillColor}
if(!fillOpacity){
fillOpacity=m_defaultFillOpacity}
if(!zIndex){
zIndex=_customLayer_baseZIndex}
this.id=id;
m_shapeId=id;
if(parts==null){
parts=new Array();
if(xs!=null&&xs.length!=0){
parts[0]=xs.length}
}
this.parts=parts;
this.xs=xs;
this.ys=ys;
this.strokeWeight=strokeWeight;
this.strokeColor=strokeColor;
this.fillColor=fillColor;
this.fillOpacity=fillOpacity;
this.zIndex=zIndex;
this.groupID=groupID;
m_groupID=groupID;
m_strokeWeight=strokeWeight;
m_strokeColor=strokeColor;
m_fillColor=fillColor;
m_fillOpacity=fillOpacity;
m_zIndex=zIndex;
m_arrayX=xs.concat();
m_arrayY=ys.concat();
m_startX=m_arrayX[0];
m_startY=m_arrayY[0];
m_endX=m_arrayX[m_arrayX.length-1];
m_endY=m_arrayY[m_arrayY.length-1];
m_parts=new Array();
while(m_parts.length>0){
m_parts.pop()}
for(var j=0;
j<parts.length;
j++){
m_parts.push(parts[j])}
_Start()}
function _Destroy(){
_RemoveFromMap();
if(m_jg){
m_jg.clear()}
m_parts=m_arrayX=m_arrayY=m_shape=m_jg=m_drawLayer=m_fill=m_stroke=null}
function _RemoveFromMap(){
if(m_shape&&m_shape.parentNode!=null){
m_shape.parentNode.removeChild(m_shape)}
if(m_drawLayer&&m_drawLayer.parentNode!=null){
m_drawLayer.parentNode.removeChild(m_drawLayer)}
}
function _Show(){
if(!m_visible){
_Hide();
return }
if(m_shape){
m_shape.style.display="block"}
if(m_drawLayer){
m_drawLayer.style.display="block"}
}
function _Hide(){
if(m_shape){
m_shape.style.display="none"}
if(m_drawLayer){
m_drawLayer.style.display="none"}
}
function _SetVisible(bool){
m_visible=bool;
if(!m_visible){
_Hide()}
}
function _Start(){
_DrawShape()}
function _Update(){
_DrawShape()}
function _DrawShape(){
if(_useVml){
var ps=new Array();
_GeneratePoints(ps);
_CreateShape(ps);
ps=null}
else{
_CreateShape()}
}
function _GeneratePoints(ps){
var mc=new SuperMap.IS.MapCoord();
var pcs=new Array();
m_tempParts=new Array();
for(var i=0;
i<m_arrayX.length;
i++){
mc.x=m_arrayX[i];
mc.y=m_arrayY[i];
var pc=_MapCoordToPixel(mc);
pcs.push(pc)}
if(!m_isPolygon){
_InterceptLine(pcs,ps)}
else{
for(var i=0;
i<pcs.length;
i++){
ps.push((pcs[i].x+_offsetX));
ps.push((pcs[i].y+_offsetY))}
}
while(pcs.length>0){
pcs.pop()}
pcs=null;
mc=null}
function _InterceptLine(pcs,ps){
var isParted=false;
var pointcount=0;
var index=0;
for(var k=0;
k<m_parts.length;
k++){
pointcount=0;
for(var i=0;
i<m_parts[k];
i++){
var pc=pcs[index+i];
if(pc.x<_minsize||pc.x>_maxsize||pc.y<_minsize||pc.y>_maxsize){
var pts=new Array();
if(i==0){
pts.push(i+1)}
else{
if(i==m_parts[k]-1){
pts.push(i-1)}
else{
pts.push(i-1);
pts.push(i+1)}
}
for(var j=0;
j<pts.length;
j++){
var pc=_ComputeInterceptPoint(pcs[index+i],pcs[index+pts[j]]);
if(pc!=null){
ps.push((pc.x+_offsetX));
ps.push((pc.y+_offsetY));
pointcount++}
}
if(pts.length==2){
isParted=true;
m_tempParts.push(pointcount-1);
pointcount=1}
}
else{
ps.push((pc.x+_offsetX));
ps.push((pc.y+_offsetY));
pointcount++}
}
index=index+m_parts[k];
if(isParted){
m_tempParts.push(pointcount)}
else{
m_tempParts=null}
}
}
function _ComputeInterceptPoint(pc1,pc2){
var pc=new SuperMap.IS.PixelCoord();
pc.x=pc1.x;
pc.y=pc1.y;
if(pc.x<_minsize){
pc.y=Math.round((pc2.y-pc1.y)*(_minsize-pc1.x)/(pc2.x-pc1.x)+pc1.y);
pc.x=_minsize}
else{
if(pc.x>_maxsize){
pc.y=Math.round((pc2.y-pc1.y)*(_maxsize-pc1.x)/(pc2.x-pc1.x)+pc1.y);
pc.x=_maxsize}
}
if(pc.y<_minsize){
pc.x=Math.round((pc2.x-pc1.x)*(_minsize-pc1.y)/(pc2.y-pc1.y)+pc1.x);
pc.y=_minsize}
else{
if(pc.y>_maxsize){
pc.x=Math.round((pc2.x-pc1.x)*(_maxsize-pc1.y)/(pc2.y-pc1.y)+pc1.x);
pc.y=_maxsize}
}
if(pc.x>_maxsize||pc.x<_minsize||pc.y>_maxsize||pc.y<_minsize){
pc=null}
return pc}
function _CreateShape(ps){
if(!_useVml){
if(!m_drawLayer){
m_drawLayer=document.createElement("div");
m_drawLayer.id=m_shapeId;
m_drawLayer.style.position="absolute";
m_drawLayer.unselectable="on";
_SelectGroup(m_groupID,m_drawLayer);
m_jg=new jsGraphics(m_shapeId);
m_jg.setColor(m_strokeColor);
m_jg.setStroke(m_strokeWeight)}
else{
m_jg.clear()}
m_drawLayer.style.left=_offsetX+"px";
m_drawLayer.style.top=_offsetY+"px";
m_drawLayer.style.zIndex=m_zIndex;
if(m_fillOpacity!=1){
m_drawLayer.style.opacity=m_fillOpacity}
if(m_visible){
m_drawLayer.style.display="block";
m_drawLayer.style.visibility="visible"}
else{
m_drawLayer.style.display="none";
m_drawLayer.style.visibility=""}
var index=0;
for(var i=0;
i<m_parts.length;
i++){
var pxs=new Array();
var pys=new Array();
var count=m_parts[i];
var startPoint=new SuperMap.IS.PixelCoord();
var mc=new SuperMap.IS.MapCoord();
for(var j=0;
j<count;
j++){
mc.x=m_arrayX[j+index];
mc.y=m_arrayY[j+index];
var pc=_MapCoordToPixel(mc);
if(j==0){
startPoint=pc}
pxs.push(pc.x);
pys.push(pc.y)}
if(m_isPolygon){
pxs.push(startPoint.x);
pys.push(startPoint.y);
m_jg.setColor(m_fillColor);
m_jg.fillPolygon(pxs,pys);
m_jg.setColor(m_strokeColor);
m_jg.drawPolygon(pxs,pys)}
else{
m_jg.setColor(m_strokeColor);
m_jg.drawPolyline(pxs,pys)}
mc=null;
startPoint=null;
index=index+count}
index=0;
m_jg.paint();
return false}
var created=false;
if(m_shape){
created=true}
var index=0;
var starts=new Array();
var lines=new Array();
var pathStr="";
if(!m_tempParts||m_tempParts.length<=0){
m_tempParts=m_parts}
for(var i=0;
i<m_tempParts.length;
i++){
starts[i]="m"+ps[index]+","+ps[index+1];
lines[i]="l"+ps.slice(index+2,(index+m_tempParts[i]*2)).join(",");
if(m_isPolygon){
pathStr=pathStr+(starts[i]+" "+lines[i]+" x ")}
else{
pathStr=pathStr+(starts[i]+" "+lines[i]+" e ")}
index=index+m_tempParts[i]*2}
starts=null;
lines=null;
if(!created){
m_shape=document.createElement("v:shape");
m_shape.setAttribute("path",pathStr)}
else{
m_shape.path.value=pathStr}
m_shape.coordsize=(container.clientWidth)+","+(container.clientHeight);
m_shape.id=m_shapeId;
if(m_isPolygon){
m_shape.filled="true"}
else{
m_shape.filled="false"}
m_shape.style.zIndex=m_zIndex;
m_shape.unselectable="on";
m_shape.style.position="absolute";
m_shape.style.border="0px";
m_shape.style.width=container.clientWidth;
m_shape.style.height=container.clientHeight;
if(m_visible){
m_shape.style.display="block"}
else{
m_shape.style.display="none"}
if(m_isPolygon){
if(!created){
m_fill=document.createElement("v:fill");
m_shape.appendChild(m_fill)}
if(m_fillOpacity!=1){
m_fill.setAttribute("opacity",m_fillOpacity)}
m_fill.setAttribute("color",m_fillColor)}
if(!created){
m_stroke=document.createElement("v:stroke");
m_shape.appendChild(m_stroke)}
m_stroke.setAttribute("weight",m_strokeWeight);
m_stroke.setAttribute("color",m_strokeColor);
m_stroke.setAttribute("joinstyle","round");
m_stroke.setAttribute("endcap","round");
m_stroke.setAttribute("opacity","0.75");
if(!created){
_SelectGroup(m_groupID,m_shape)}
}
function _Repostion(){
_Start()}
this.Init=_Init;
this.Destroy=_Destroy;
this.RemoveFromMap=_RemoveFromMap;
this.Show=_Show;
this.Hide=_Hide;
this.SetVisible=_SetVisible;
this.Start=_Start;
this.Update=_Update;
this.Reposition=_Repostion}
function _ShowPolylines(){
for(var i=0;
i<_lines.length;
i++){
_lines[i].Start();
_lines[i].Show()}
for(var i=0;
i<_polygons.length;
i++){
_polygons[i].Start();
_polygons[i].Show()}
}
function _HidePolylines(){
for(var i=0;
i<_lines.length;
i++){
_lines[i].Hide()}
for(var i=0;
i<_polygons.length;
i++){
_polygons[i].Hide()}
}
function _UpdatePolylines(){
for(var i=0;
i<_lines.length;
i++){
_lines[i].Update()}
for(var i=0;
i<_polygons.length;
i++){
_polygons[i].Update()}
}
function _CloseInfoWindow(id){
_RemoveMark(id)}
function _OpenInfoWindow(id,x,y,width,height,title,content,opacity){
var zIndex="1000";
var left=0;
var top=0;
if(!x){
return }
if(!y){
return }
if(!width){
width=100}
if(!height){
height=100}
if(!opacity){
opacity=0.5}
if(width<50){
width=50}
if(height<50){
height=50}
if(!title){
title="title"}
if(!content){
content="content"}
var normal="white";
var str="<div style='filter:alpha(opacity="+(opacity)*100+");opacity:"+opacity+";z-index:"+zIndex+";width:"+width*2+"px;height:"+height+"px;left:"+left+"px;top:"+top+"px;color:"+normal+";font-size:12px;font-family:Verdana;position:absolute;border:0px solid white;'  ondblclick =_CancelBubble(window.event)  onmousemove =_CancelBubble(window.event) ><div style='position:absolute;left:"+width/10+"px;top:"+height/2+"px;width:70%;height:50%;z-index:"+(zIndex-3)+";'><img src='images/shadow.gif' style='width:100%;height:100%;' /></div><div style='position:absolute;cursor:default;left:0px;top:0px;width:50%;height:75%;z-index:"+(zIndex-2)+";' onclick=_CancelBubble(window.event)><img src='images/form-white1.gif' style='width:100%;height:100%;' /></div><div style='position:absolute;cursor:default;left:"+width/2+"px;top:"+height*3/4+"px;width:"+25*75/183+"%;height:25%;z-index:"+(zIndex-2)+";'><img src='images/form-white2.gif' style='width:100%;height:100%;' /></div><div style='position:absolute;left:0px;top:0px;z-index:"+(zIndex-1)+";width:100%;height:20px;><table border=0 cellspacing=0 cellpadding=0><tr><td style='width="+width+"'><span style='width:"+(width-12)+";color:orange;padding-left:3px;font-size:12px' >"+title+"</span></td><td align=right style='width:12'><span style='position:absolute;left:"+(width-10)+"px;width:12px;border-width:0px;color:orange;' onclick='var infoWindow=document.getElementById(\""+id+"\");infoWindow.parentNode.removeChild(infoWindow);infoWindow=null;'>X</span></td></tr></table><div style='position:absolute;width:"+(width-12)+"px;height:"+(height*0.7-20-4)+"px;color: orange;line-height:14px;word-break:break-all;padding:3px;overflow:hidden'>"+content+"</div></div>";
_InsertMark(id,x,y,width,height*2,str)
	}function _GetMapBounds(){
return _curMap.GetMapBounds()}
function _GetViewBounds(){
var pr=new SuperMap.IS.PixelRect();
var lt=pr.leftTop;
var rb=pr.rightBottom;
lt.x=_curParam.pixelCenter.x-_w/2;
lt.y=_curParam.pixelCenter.y-_h/2;
rb.x=lt.x+_w;
rb.y=lt.y+_h;
var mr=new SuperMap.IS.MapRect();
var lb=mr.leftBottom;
var rt=mr.rightTop;
var mlt=_curMap.PixelToMapCoord(lt,_curParam.mapScale);
var mrb=_curMap.PixelToMapCoord(rb,_curParam.mapScale);
if(mlt==null||mrb==null){
return mr}
lb.x=mlt.x;
lb.y=mrb.y;
rt.x=mrb.x;
rt.y=mlt.y;
return mr}
function _CheckTileLoaded(){
if(_tileLoadedChecking){
return }
_tileLoadedChecking=true;
window.clearTimeout(_iTimeoutIDForCheckTileLoaded);
_iTimeoutIDForCheckTileLoaded=null;
var loaded=true;
if(_tileCheckTimes<_tileCheckCounts){
for(var i=0;
i<_tileCollections.length;
i++){
var tiles=_tileCollections[i];
if(tiles==null){
continue}
for(var j=0;
j<tiles.length;
j++){
if(tiles[j]&&!tiles[j].Loaded){
loaded=false;
break}
}
}
}
else{
_tileCheckTimes=0;
for(var i=0;
i<_tileCollections.length;
i++){
var tiles=_tileCollections[i];
if(tiles==null){
continue}
for(var j=0;
j<tiles.length;
j++){
if(tiles[j]&&!tiles[j].Loaded){
loaded=false;
tiles[j].RefreshUrl();
continue}
}
}
}
if(loaded){
_tileCheckTimes=0;
_TriggerEvent("onimagesload",new EventArguments(null,""))}
else{
_tileCheckTimes++;
_iTimeoutIDForCheckTileLoaded=setTimeout(_CheckTileLoaded,200)}
_tileLoadedChecking=false}
function _GetCustomDiv(){
if(_customDiv){
_customDiv.innerHtml="";
if(!_customDiv.parentNode){
_workLayer.appendChild(_customDiv)}
return _customDiv}
var customDiv=document.createElement("DIV");
var es=customDiv.style;
es.position="absolute";
es.padding="0px";
es.margin="0px";
es.top="0px";
es.width=_workLayer.style.width;
es.height=_workLayer.style.height;
es.zIndex=_customLayer_baseZIndex;
es=null;
var groupDiv=document.createElement("div");
groupDiv.id="unClassified";
customDiv.appendChild(groupDiv);
_customDiv=customDiv;
_workLayer.appendChild(_customDiv);
customDiv=null;
return _customDiv}
this.GetMapCenterX=_GetMapCenterX;
this.GetMapCenterY=_GetMapCenterY;
this.GetPixelCenterX=_GetPixelCenterX;
this.GetPixelCenterY=_GetPixelCenterY;
this.MapCoordToPixel=_MapCoordToPixel;
this.PixelToMapCoord=_PixelToMapCoord;
this.GetMapScale=_GetMapScale;
this.GetZoomLevel=_GetZoomLevel;
this.GetMapBounds=_GetMapBounds;
this.GetViewBounds=_GetViewBounds;
this.PixelToMapDistance=_PixelToMapDistance;
this.GetSize=_GetSize;
this.Resize=_Resize;
this.Pan=_Pan;
this.DynamicPan=_DynamicPan;
this.StopDynamicPan=_StopDynamicPan;
this.PanToMapCoord=_PanToMapCoord;
this.ViewByBounds=_ViewByBounds;
this.ViewByPoints=_ViewByPoints;
this.ViewByPoint=_ViewByPoint;
this.SetMapScale=_SetMapScale;
this.SetZoomLevel=_SetZoomLevel;
this.ZoomIn=_ZoomIn;
this.ZoomOut=_ZoomOut;
this.Zoom=_Zoom;
this.SetCenterAndZoom=_SetCenterAndZoom;
this.AttachEvent=_AttachEvent;
this.DetachEvent=_DetachEvent;
this.SetAnimationEnabled=_SetAnimationEnabled;
this.IsAnimationEnabled=_IsAnimationEnabled;
this.SetMapParam=_SetMapParam;
this.GetMapParam=_GetMapParam;
this.Debug=_Debug;
this.GetOriginX=_GetOriginX;
this.GetOriginY=_GetOriginY;
this.GetOffsetX=_GetOffsetX;
this.GetOffsetY=_GetOffsetY;
this.GetContainerX=_GetContainerX;
this.GetContainerY=_GetContainerY;
this.GetAction=_GetAction;
this.SetAction=_SetAction;
this.CustomLayer=new Object();
var cl=this.CustomLayer;
cl.AddMark=_AddMark;
cl.InsertMark=_InsertMark;
cl.RemoveMark=_RemoveMark;
cl.ClearMarks=_ClearMarks;
cl.AddLine=_AddLine;
cl.InsertLine=_InsertLine;
cl.RemoveLine=_RemoveLine;
cl.ClearLines=_ClearLines;
cl.AddPolygon=_AddPolygon;
cl.InsertPolygon=_InsertPolygon;
cl.RemovePolygon=_RemovePolygon;
cl.ClearPolygons=_ClearPolygons;
cl.OpenInfoWindow=_OpenInfoWindow;
cl.CloseInfoWindow=_CloseInfoWindow;
cl.SetGroupVisible=_SetGroupVisible;
cl.SetGroupZindex=_SetGroupZindex;
cl.AddGeometry=_AddGeometry;
cl.AddGeometries=_AddGeometries;
cl.InsertGeometry=_InsertGeometry;
cl.InsertGeometries=_InsertGeometries;
cl.RemoveGeometry=_RemoveGeometry;
cl.RemoveGeometries=_RemoveGeometries;
cl.ClearGeometries=_ClearGeometries;
cl.GetGeometry=_GetGeometry;
cl.GetGeometries=_GetGeometries;
cl.UpdateGeometries=_UpdateGeometries;
cl.SetDefaultGeometryStyle=_SetDefaultGeometryStyle;
cl.SetGeometryTolerance=_SetGeometryTolerance;
cl.SetCalculateInClient=_SetCalculateInClient;
cl.QueryGeomtryByPoint=_QueryGeomtryByPoint;
cl=null;
this.GetQueryManager=function(){
if(!query){
query=new SuperMap.IS.QueryManager(_mapHandler,_mapName,_trackingLayerIndex,_userID);
query.AttachEvent("onchangetrackinglayer",_OnChangeTrackingLayer)}
return query}
;
this.GetSpatialAnalystManager=function(){
if(!spatialAnalyst){
spatialAnalyst=new SuperMap.IS.SpatialAnalystManager(_mapHandler,_mapName,_trackingLayerIndex,_userID);
spatialAnalyst.AttachEvent("onchangetrackinglayer",_OnChangeTrackingLayer);
spatialAnalyst.AttachEvent("onviewboundschanged",_OnViewBoundsChanged)}
return spatialAnalyst}
;
this.GetEditManager=function(){
if(!edit){
edit=new SuperMap.IS.EditManager(_mapHandler,_mapName)}
return edit}
;
this.GetMapStatus=function(returnLayers,returnThemes,onComplete,onError,userContext){
_curMap.GetMapStatus(returnLayers,returnThemes,onComplete,onError,userContext)}
;
this.GetEntity=function(mapName,layerName,id,onComplete,onError,userContext){
_curMap.GetEntity(mapName,layerName,id,onComplete,onError,userContext)}
;
this.GetEntities=function(mapName,layerName,ids,onComplete,onError,userContext){
_curMap.GetEntities(mapName,layerName,ids,onComplete,onError,userContext)}
;
this.MeasureDistance=function(points,isHighlight,onComplete,onError,userContext){
_curMap.MeasureDistance(points,isHighlight,onComplete,onError,userContext)}
;
this.MeasureArea=function(points,isHighlight,onComplete,onError,userContext){
_curMap.MeasureArea(points,isHighlight,onComplete,onError,userContext)}
;
this.CustomInvoke=function(customParams,identifier,onComplete,onError,userContext){
_curMap.CustomInvoke(customParams,identifier,onComplete,onError,userContext)}
;
this.GetOverview=function(overview,onComplete,onError,userContext){
_curMap.GetOverview(overview,onComplete,onError,userContext)}
;
this.GetMapImage=function(mapParam,onComplete,onError,userContext){
_curMap.GetMapImage(mapParam,onComplete,onError,userContext)}
;
this.GetMapImageByDpi=function(mapParam,imageDpi,onComplete,onError,userContext){
_curMap.GetMapImageByDpi(mapParam,imageDpi,onComplete,onError,userContext)}
;
this.GetWorkspaceInfo=function(onComplete,onError,userContext){
_curMap.GetWorkspaceInfo(onComplete,onError,userContext)}
;
this.GetDatasetInfo=function(datasource,dataset,onComplete,onError,userContext){
_curMap.GetDatasetInfo(datasource,dataset,onComplete,onError,userContext)}
;
this.ClearCache=function(mapName,mapRect,onComplete,onError,userContext){
_curMap.ClearCache(mapName,mapRect,onComplete,onError,userContext)}
;
this.ConnectDatasources=function(onComplete,onError,userContext){
_curMap.ConnectDatasources(onComplete,onError,userContext)}
;
this.GetFieldInfo=function(dataset,fieldName,onComplete,onError,userContext){
_curMap.GetFieldInfo(dataset,fieldName,onComplete,onError,userContext)}
;
this.MakeDefaultDotDensityTheme=function(layerName,expression,colorSet,onComplete,onError,userContext){
_curMap.MakeDefaultDotDensityTheme(layerName,expression,colorSet,onComplete,onError,userContext)}
;
this.MakeDefaultGraduatedSymbolTheme=function(layerName,expression,colorSet,onComplete,onError,userContext){
_curMap.MakeDefaultGraduatedSymbolTheme(layerName,expression,colorSet,onComplete,onError,userContext)}
;
this.MakeDefaultGraphTheme=function(layerName,expressions,colorSet,onComplete,onError,userContext){
_curMap.MakeDefaultGraphTheme(layerName,expressions,colorSet,onComplete,onError,userContext)}
;
this.MakeDefaultRangeTheme=function(layerName,expression,breakCount,colorSet,onComplete,onError,userContext){
_curMap.MakeDefaultRangeTheme(layerName,expression,breakCount,colorSet,onComplete,onError,userContext)}
;
this.MakeDefaultUniqueTheme=function(layerName,expression,colorSet,startSmID,expectCount,onComplete,onError,userContext){
_curMap.MakeDefaultUniqueTheme(layerName,expression,colorSet,startSmID,expectCount,onComplete,onError,userContext)}
;
this.MakeDefaultGridRangeTheme=function(layerName,breakCount,colorSet,onComplete,onError,userContext){
_curMap.MakeDefaultGridRangeTheme(layerName,breakCount,colorSet,onComplete,onError,userContext)}
;
this.ClearHighlight=function(onComplete,onError,userContext){
_curMap.ClearHighlight(onComplete,onError,userContext)}
;
function _ShowBusStopBase(methodName,paramNames,paramValues,onComplete,onError,userContext){
var queryUrl=_mapHandler+"path.ashx";
var mapName=_mapName;
function onRequestComplete(responseText){
if(!responseText){
if(onComplete){
onComplete(null,userContext)}
return }
var busStopJ=_eval("("+responseText+")");
if(!busStopJ){
return }
var busStop=new SuperMap.IS.BusStop();
busStop.FromJSON(busStopJ);
if(busStopJ.Location){
busStopJ.Location=null}
busStopJ=null;
var innerHtml="<div style='font-size:16px; color:blue; font-weight:bold'><img src='images/marker.gif' style='cursor:hand' />"+busStop.stopName+"<div>";
_self.CustomLayer.InsertMark("BusStop",busStop.Location.x,busStop.Location.y,10,10,innerHtml);_self.PanToMapCoord(busStop.Location.x,busStop.Location.y);
if(onComplete){
onComplete(busStop,userContext)}
busStop.Destroy();
busStop=null}
var reuqestManager=new SuperMap.IS.RequestManager(queryUrl,onRequestComplete,onError,userContext);
reuqestManager.AddQueryString("map",mapName);
reuqestManager.AddQueryString("method",methodName);
reuqestManager.AddQueryStrings(paramNames,paramValues);
reuqestManager.Send();
reuqestManager.Destroy();
reuqestManager=null;
while(paramNames.length>0){
paramNames.pop();
paramValues.pop()}
paramNames=null;
paramValues=null}
this.ShowBusStop=function(id,onComplete,onError,userContext){
_ShowBusStopBase("ShowBusStop",["id"],[id],onComplete,onError,userContext)}
;
function _ShowBusLineBase(methodName,paramNames,paramValues,onComplete,onError,userContext){
var queryUrl=_mapHandler+"path.ashx";
var mapName=_mapName;
function onRequestComplete(responseText){
if(!responseText){
if(onComplete){
onComplete(null,userContext)}
return }
var busLineJ=_eval("("+responseText+")");
if(!busLineJ){
return }
var busLine=new SuperMap.IS.BusLine();
busLine.FromJSON(busLineJ);
if(busLineJ&&busLineJ.points){
while(busLineJ.points.length>0){
busLineJ.points.pop()}
}
busLineJ=null;
var xs=new Array();
var ys=new Array();
for(var i=0;
i<busLine.points.length;
i++){
xs[i]=busLine.points[i].x;
ys[i]=busLine.points[i].y}
_self.CustomLayer.InsertLine("BusLine",xs,ys,3,"blue");
_self.ViewByPoints(busLine.points);
while(xs.length>0){
xs.pop();
ys.pop()}
xs=null;
ys=null;
if(onComplete){
onComplete(busLine,userContext)}
busLine.Destroy();
busLine=null}
var reuqestManager=new SuperMap.IS.RequestManager(queryUrl,onRequestComplete,onError,userContext);
reuqestManager.AddQueryString("map",mapName);
reuqestManager.AddQueryString("method",methodName);
reuqestManager.AddQueryStrings(paramNames,paramValues);
reuqestManager.Send();
reuqestManager.Destroy();
reuqestManager=null;
while(paramNames.length>0){
paramNames.pop();
paramValues.pop()}
paramNames=null;
paramValues=null}
this.ShowBusLine=function(id,onComplete,onError,userContext){
_ShowBusLineBase("ShowBusLine",["id"],[id],onComplete,onError,userContext)}
;
function _ShowRoutingBase(methodName,paramNames,paramValues,onComplete,onError,userContext){
var queryUrl=_mapHandler+"path.ashx";
var mapName=_mapName;
function onRequestComplete(responseText){
if(!responseText){
if(onComplete){
onComplete(null,userContext)}
return }
var busRoutingJ=_eval("("+responseText+")");
if(!busRoutingJ){
return }
var busRouting=new SuperMap.IS.BusRouting();
busRouting.FromJSON(busRoutingJ);
if(busRoutingJ.busLines){
while(busRoutingJ.busLines.length>0){
var busLineJ=busRoutingJ.busLines.pop();
if(busLineJ&&busLineJ.points){
while(busLineJ.points.length>0){
busLineJ.points.pop()}
}
busLineJ=null}
}
if(busRoutingJ.upStops){
while(busRoutingJ.upStops.length>0){
var busStopJ=busRoutingJ.upStops.pop();
if(busStopJ.Location){
busStopJ.Location=null}
busStopJ=null}
}
if(busRoutingJ.downStops){
while(busRoutingJ.downStops.length>0){
var busStopJ=busRoutingJ.downStops.pop();
if(busStopJ.Location){
busStopJ.Location=null}
busStopJ=null}
}
busRoutingJ=null;
if(_lastBusNum>0){
for(var i=0;
i<_lastBusNum;
i++){
_self.CustomLayer.RemoveMark("BusStartStop"+i);
_self.CustomLayer.RemoveMark("BusEndStop"+i);
_self.CustomLayer.RemoveLine("BusRouting"+i)}
}
if(busRouting.upStops){
for(var i=0;
i<busRouting.upStops.length;
i++){
var innerHtml="<div style='font-size:16px; color:blue;font-weight:bold'><img src='images/marker.gif' alt='"+busRouting.upStops[i].stopName+"'style='cursor:hand' />";
_self.CustomLayer.InsertMark("BusStartStop"+i,busRouting.upStops[i].Location.x,busRouting.upStops[i].Location.y,10,10,innerHtml)}
}
if(busRouting.downStops){
for(var i=0;
i<busRouting.downStops.length;
i++){
var innerHtml="<div style='ffont-size:16px; color:blue;font-weight:bold'><img src='images/marker.gif' alt='"+busRouting.downStops[i].stopName+"'style='cursor:hand' />";
_self.CustomLayer.InsertMark("BusEndStop"+i,busRouting.downStops[i].Location.x,busRouting.downStops[i].Location.y,10,10,innerHtml)}
}
if(busRouting.busLines){
for(var i=0;
i<busRouting.busLines.length;
i++){
var xs=new Array();
var ys=new Array();
for(var j=0;
j<busRouting.busLines[i].points.length;
j++){
xs[j]=busRouting.busLines[i].points[j].x;
ys[j]=busRouting.busLines[i].points[j].y}
_self.CustomLayer.InsertLine("BusRouting"+i,xs,ys,3,"blue");
while(xs.length>0){
xs.pop();
ys.pop()}
xs=null;
ys=null}
var mcs=new Array();
for(var i=0;
i<busRouting.busLines.length;
i++){
for(var j=0;
j<busRouting.busLines[i].points.length;
j++){
mcs.push(busRouting.busLines[i].points[j])}
_lastBusNum=i+1}
_self.ViewByPoints(mcs);
while(mcs.length>0){
mcs.pop()}
mcs=null}
if(onComplete){
onComplete(busRouting,userContext)}
busRouting.Destroy();
busRouting=null}
var reuqestManager=new SuperMap.IS.RequestManager(queryUrl,onRequestComplete,onError,userContext);
reuqestManager.AddQueryString("map",mapName);
reuqestManager.AddQueryString("method",methodName);
reuqestManager.AddQueryStrings(paramNames,paramValues);
reuqestManager.Send();
reuqestManager.Destroy();
reuqestManager=null;
while(paramNames.length>0){
paramNames.pop();
paramValues.pop()}
paramNames=null;
paramValues=null}
this.ShowRouting=function(busRouting,onComplete,onError,userContext){
_ShowRoutingBase("ShowRouting",["busRouting"],[busRouting],onComplete,onError,userContext)}
;
this.GetResource=function(mapName,resourceParam,onComplete,onError,userContext){
_curMap.GetResource(mapName,resourceParam,onComplete,onError,userContext)}
;
this.GetGeometryImage=function(mapName,geometryParam,onComplete,onError,userContext){
_curMap.GetGeometryImage(mapName,geometryParam,onComplete,onError,userContext)}
;
this.GenerateResourceRequest=function(resourceParam){
var returnString=params.mapHandler+"ajax/"+encodeURI(params.mapName)+"/"+resourceParam.style.brushBackColor+"/"+resourceParam.style.brushBackTransparent+"/"+resourceParam.style.brushColor+"/"+resourceParam.style.brushStyle+"/"+resourceParam.style.penColor+"/"+resourceParam.style.penStyle+"/"+resourceParam.style.penWidth+"/"+resourceParam.style.symbolRotation+"/"+resourceParam.style.symbolSize+"/"+resourceParam.style.symbolStyle+"/"+resourceParam.resourceType+"/"+resourceParam.imageFormat+"/"+resourceParam.width+"/"+resourceParam.height+"/map.ashx?GetResource=true&redirect="+params.redirect;
return returnString}
;
function _SystemColorToIntegerColor(color){
if(!color){
return -1}
return(color.r)|(color.g<<8)|(color.b<<16)}
var _points;
var _areas;
var _startPointX;
var _startPointY;
var _distanceX;
var _distanceY;
var _stepIndex;
var _sectonIndex;
var _distance;
var _step;
var _time;
var _length;
var _onOutAreaUrl;
var _onInAreaUrl;
var _onOutArea;
var _onInArea;
var _bRotative;
var _IsDynamicNavigate=false;
var _userContext;
var _onComplete;
var _sectionIndex;
function _StopDynamicNavigate(){
_IsDynamicNavigate=false;
_self.CustomLayer.RemoveMark("Walk");
_points=null;
_areas=null}
function _PauseDynamicNavigate(){
_IsDynamicNavigate=false}
function _ContinueDynamicNavigate(){
if(!_points){
return }
if(!_IsDynamicNavigate){
_IsDynamicNavigate=true;
_DynamicNavigateStep()}
}
function _StartDynamicNavigate(points,areas,onOutAreaUrl,onInAreaUrl,onOutArea,onInArea,bRotative,step,time,onComplete,userContext){
if(_IsDynamicNavigate){
return }
if(points==null||points.length<2){
return }
_IsDynamicNavigate=true;
_points=points;
_areas=areas;
_onOutAreaUrl=onOutAreaUrl;
_onInAreaUrl=onInAreaUrl;
_onOutArea=onOutArea;
_onInArea=onInArea;
_bRotative=bRotative;
_onComplete=onComplete;
_userContext=userContext;
_viewBounds=_GetViewBounds();
_step=50;
if(step){
_step=step}
_time=100;
if(time){
_time=time}
_sectionIndex=0;
_DynamicNavigateSection()}
this.StartDynamicNavigate=_StartDynamicNavigate;
this.PauseDynamicNavigate=_PauseDynamicNavigate;
this.ContinueDynamicNavigate=_ContinueDynamicNavigate;
this.StopDynamicNavigate=_StopDynamicNavigate;
function _DynamicNavigateSection(){
if(!_points){
return }
var startPoint=_points[_sectionIndex];
var endPoint=_points[_sectionIndex+1];
_SetViewBounds(startPoint);
_distanceX=endPoint.x-startPoint.x;
_distanceY=endPoint.y-startPoint.y;
_startPointX=startPoint.x;
_startPointY=startPoint.y;
_distance=Math.sqrt((Math.pow(_distanceX,2)+Math.pow(_distanceY,2)));
if(_distance==0){
_sectionIndex++;
if(_sectionIndex<_points.length-1){
_DynamicNavigateSection()}
else{
if(_onComplete){
_onComplete(_userContext)}
if(_bRotative){
_viewBounds=_GetViewBounds();
if(_points==null||_points.length<2){
return }
_sectionIndex=0;
_DynamicNavigateSection()}
else{
_IsDynamicNavigate=false;
_self.CustomLayer.RemoveMark("Walk")}
}
}
else{
_length=_distance/_step;
_stepIndex=0;
window.setTimeout(_DynamicNavigateStep,_time)}
}
function _DynamicNavigateStep(){
if(!_IsDynamicNavigate){
return }
var actualDis=_step*_stepIndex;
_stepIndex++;
if(actualDis>_distance){
actualDis=_distance}
var point=new SuperMap.IS.MapCoord(actualDis*_distanceX/_distance+_startPointX,actualDis*_distanceY/_distance+_startPointY);
_SetViewBounds(point);
var innerHTML;
var flag=_IsInAreas(point);
if(flag){
innerHTML="<img src='"+_onInAreaUrl+"'/>"}
else{
innerHTML="<img src='"+_onOutAreaUrl+"'/>"}
_self.CustomLayer.InsertMark("Walk",point.x,point.y,20,40,innerHTML);
if(_stepIndex<_length){
_iTimeoutIDForDynamicNavigate=window.setTimeout(_DynamicNavigateStep,_time)}
else{
_sectionIndex++;
if(_sectionIndex<_points.length-1){
_DynamicNavigateSection(_points[_sectionIndex],_points[_sectionIndex+1],_areas,_sectionIndex)}
else{
if(_onComplete){
_onComplete(_userContext)}
if(_bRotative){
_viewBounds=_GetViewBounds();
if(_points==null||_points.length<2){
return }
_sectionIndex=0;
_DynamicNavigateSection()}
else{
_IsDynamicNavigate=false;
_self.CustomLayer.RemoveMark("Walk")}
}
}
}
function _SetViewBounds(point){
if(!_GetViewBounds().Contains(point)){
_PanToMapCoord(point.x,point.y)}
}
function _IsInAreas(point){
if(_areas!=null){
for(var i=0;
i<_areas.length;
i++){
if(_areas[i].Contains(point)){
if(_onInArea){
_onInArea(point,_areas[i],_userContext)}
return true;
break}
}
}
if(_onOutArea){
_onOutArea(point,_userContext)}
return false}
function _SetLayersHidden(changedLayersJSON){
var hidden=document.getElementById(container.id+"_hiddenLayers");
if(hidden){
hidden.value=changedLayersJSON}
}
function _SetMapParamHidden(){
var mapParam=new Object();
mapParam.center=new SuperMap.IS.MapCoord();
mapParam.center.x=_GetMapCenterX();
mapParam.center.y=_GetMapCenterY();
mapParam.mapScale=_curParam.mapScale;
mapParam.mapBounds=_GetMapBounds();
mapParam.viewBounds=_GetViewBounds();
mapParam.mapName=_mapName;
mapParam.trackingLayerIndex=_trackingLayerIndex;
mapParam.userID=_userID;
var hiddenMapParam=document.getElementById(container.id+"_hiddenMapParam");
if(hiddenMapParam){
hiddenMapParam.value=_ToJSON(mapParam)}
}
function _SetSizeHidden(){
var hiddenValue=container.style.width+"|"+container.style.height;
var hidden=document.getElementById(container.id+"_hiddenSize");
if(hidden){
hidden.value=hiddenValue}
}
function _InitClientAction(){
var hidden=document.getElementById(container.id+"_hiddenClientAction");
if(hidden&&hidden.value){
var json=hidden.value;
var action=_JSONToAction(json);
_SetAction(action)}
}
function _InitMarks(){
var hidden=document.getElementById(container.id+"_hiddenMarks");
if(hidden&&hidden.value){
hidden.value=unescape(hidden.value);
var marks=_eval("("+hidden.value+")");
if(marks){
while(marks.length>0){
var mark=marks.pop();
mark.innerHtml=mark.innerHtml;
_AddMark(mark.id,mark.x,mark.y,mark.w,mark.h,mark.innerHtml,mark.className,mark.zIndex,mark.groupID,mark.alignStyle);
mark=null}
}
}
}
function _InitLines(){
var hidden=document.getElementById(container.id+"_hiddenLines");
if(hidden&&hidden.value){
var lines=_eval("("+hidden.value+")");
if(lines){
while(lines.length>0){
var line=lines.pop();
_AddLine(line.id,line.xs,line.ys,line.strokeWeight,line.strokeColor,line.opacity,line.zIndex,line.groupID,line.parts);
line=null}
}
}
}
function _InitPolygons(){
var hidden=document.getElementById(container.id+"_hiddenPolygons");
if(hidden&&hidden.value){
var polygons=_eval("("+hidden.value+")");
if(polygons){
while(polygons.length>0){
var polygon=polygons.pop();
_AddPolygon(polygon.id,polygon.xs,polygon.ys,polygon.strokeWeight,polygon.strokeColor,polygon.fillColor,polygon.fillOpacity,polygon.zIndex,polygon.groupID,polygon.parts);
polygon=null}
}
}
}
function _OnChangeTrackingLayer(arguments){
_t=new Date().getTime();
_trackingLayerIndex=arguments.trackingLayerIndex;
_userID=arguments.userID;
if(_curMap){
_curMap.ChangeTrackingLayerKey(_trackingLayerIndex,_userID)}
if(query){
query.ChangeTrackingLayerKey(_trackingLayerIndex,_userID)}
if(spatialAnalyst){
spatialAnalyst.ChangeTrackingLayerKey(_trackingLayerIndex,_userID)}
if(arguments.bSaveHistory){
_SetMapParamHidden()}
_RefreshTrackingLayer()}
function _OnChangeLayer(arguments){
_TriggerEvent("onchangelayer")}
function _OnViewBoundsChanged(viewBounds){
if(viewBounds){
_ViewByBounds(viewBounds.leftBottom.x,viewBounds.leftBottom.y,viewBounds.rightTop.x,viewBounds.rightTop.y)}
}
function _SetMarksHidden(){
if(!_self.storeClientInfo){
return }
var hidden=document.getElementById(container.id+"_hiddenMarks");
if(!hidden){
return }
if(!_marks){
hidden.value=""}
var marks=new Array();
for(var i=0;
i<_marks.length;
i++){
var mark=new Object();
mark.id=_marks[i].id;
mark.x=_marks[i].x;
mark.y=_marks[i].y;
mark.w=_marks[i].w;
mark.h=_marks[i].h;
mark.innerHtml=_marks[i].innerHtml;
mark.className=_marks[i].className;
mark.zIndex=_marks[i].zIndex;
mark.groupID=_marks[i].groupID;
mark.alignStyle=_marks[i].alignStyle;
marks.push(mark)}
var json=_ToJSON(marks);
while(marks.length>0){
marks.pop()}
marks=null;
json=escape(json);
hidden.value=json}
function _SetLinesHidden(){
if(!_self.storeClientInfo){
return }
var hidden=document.getElementById(container.id+"_hiddenLines");
if(!hidden){
return }
var lines=null;
if(_lines){
lines=new Array();
for(var i=0;
i<_lines.length;
i++){
var line=new Object();
line.id=_lines[i].id;
line.xs=_lines[i].xs;
line.ys=_lines[i].ys;
line.strokeWeight=_lines[i].strokeWeight;
line.strokeColor=_lines[i].strokeColor;
line.zIndex=_lines[i].zIndex;
line.groupID=_lines[i].groupID;
line.parts=_lines[i].parts;
lines.push(line)}
}
var json=_ToJSON(lines);
if(lines){
while(lines.length>0){
var line=lines.pop();
line=null}
lines=null}
hidden.value=json}
function _SetPolygonsHidden(){
if(!_self.storeClientInfo){
return }
var hidden=document.getElementById(container.id+"_hiddenPolygons");
if(!hidden){
return }
var polygons=null;
if(_polygons){
polygons=new Array();
for(var i=0;
i<_polygons.length;
i++){
var polygon=new Object();
polygon.id=_polygons[i].id;
polygon.xs=_polygons[i].xs;
polygon.ys=_polygons[i].ys;
polygon.parts=_polygons[i].parts;
polygon.strokeWeight=_polygons[i].strokeWeight;
polygon.strokeColor=_polygons[i].strokeColor;
polygon.fillColor=_polygons[i].fillColor;
polygon.fillOpacity=_polygons[i].fillOpacity;
polygon.zIndex=_polygons[i].zIndex;
polygon.groupID=_polygons[i].groupID;
polygons.push(polygon)}
}
var json=_ToJSON(polygons);
if(polygons){
while(polygons.length>0){
var polygon=polygons.pop();
polygon=null}
polygons=null}
hidden.value=json}
function _RefreshMap(){
_oldTileCollections=_tileCollections;
_tileCollections=new Array();
_StartMap();
_SwapStates();
_RepositionMarks();
_RepositionLines();
_RepositionPolygons();
_SetFactor()}
function _RefreshTrackingLayer(){
if(_imageBufferCollection[1]!=null){
while(_imageBufferCollection[1].length>0){
var imageId=_imageBufferCollection[1][0];
delete _imageBufferCollection[1][imageId];
_imageBufferCollection[1].shift()}
_imageBufferCollection[1]=null}
if(!_tileCollections[1]){
_tileCollections[1]=new Array()}
if(_tileCollections[1].length>0){
for(var i=0;
i<_tileCollections[1].length;
i++){
_tileCollections[1][i].RefreshUrl()}
}
else{
var tileLayerBounds=_tileLayers[_tileLayerIDs[1]].visibleBounds;
var tileLayerMaxScale=_tileLayers[_tileLayerIDs[1]].maxScale;
var tileLayerMinScale=_tileLayers[_tileLayerIDs[1]].minScale;
var centerX=_Floor((_tileCountX[1]-1)/2);
var centerY=_Floor((_tileCountY[1]-1)/2);
var radius=0;
var flag=0;
var startX=0;
var startY=0;
var x=0;
var y=0;
while(centerX+radius<_tileCountX[1]||centerY+radius<_tileCountY[1]){
startX=centerX+radius;
startY=centerY-radius;
x=startX;
y=startY;
flag=1;
do{
if(x>=0&&x<_tileCountX[1]&&y>=0&&y<_tileCountY[1]){
var tileBounds=_GetTileBounds(x+_tileX1[1],y+_tileY1[1],_tileLayers[_tileLayerIDs[1]].tileWidth,_tileLayers[_tileLayerIDs[1]].tileHeight,_tileLayers[_tileLayerIDs[1]].layerBounds);
var tile=null;
if((!tileLayerMaxScale||_curParam.mapScale<tileLayerMaxScale)&&(!tileLayerMinScale||_curParam.mapScale>tileLayerMinScale)&&(!tileLayerBounds||_IsBoundsTouchs(tileBounds,tileLayerBounds))){
tile=_CreateTile(x+_tileX1[1],y+_tileY1[1],_curParam.mapScale,_tileLayers[_tileLayerIDs[1]])}
if(!_tileCollections[1]){
_tileCollections[1]=new Array()}
var tileIndex=x+y*_tileCountX[1];
_tileCollections[1][tileIndex]=tile}
if(x==centerX+radius&&y==centerY+radius){
flag=0}
else{
if(x==centerX-radius&&y==centerY+radius){
flag=3}
else{
if(x==centerX-radius&&y==centerY-radius){
flag=2}
else{
if(x==centerX+radius&&y==centerY-radius){
flag=1}
}
}
}
if(radius>0){
if(flag==0){
x--}
else{
if(flag==1){
y++}
else{
if(flag==2){
x++}
else{
if(flag==3){
y--}
}
}
}
}
}
while(startX!=x||startY!=y);
radius++}
}
}
function _RefreshGeometryLayer(){
_t=new Date().getTime();
if(_imageBufferCollection[2]!=null){
while(_imageBufferCollection[2].length>0){
var imageId=_imageBufferCollection[2][0];
delete _imageBufferCollection[2][imageId];
_imageBufferCollection[2].shift()}
_imageBufferCollection[2]=null}
if(!_tileCollections[2]){
_tileCollections[2]=new Array()}
if(_tileCollections[2].length>0){
for(var i=0;
i<_tileCollections[2].length;
i++){
_tileCollections[2][i].RefreshUrl()}
}
else{
var tileLayerBounds=_tileLayers[_tileLayerIDs[2]].visibleBounds;
var tileLayerMaxScale=_tileLayers[_tileLayerIDs[2]].maxScale;
var tileLayerMinScale=_tileLayers[_tileLayerIDs[2]].minScale;
var centerX=_Floor((_tileCountX[2]-1)/2);
var centerY=_Floor((_tileCountY[2]-1)/2);
var radius=0;
var flag=0;
var startX=0;
var startY=0;
var x=0;
var y=0;
while(centerX+radius<_tileCountX[2]||centerY+radius<_tileCountY[2]){
startX=centerX+radius;
startY=centerY-radius;
x=startX;
y=startY;
flag=1;
do{
if(x>=0&&x<_tileCountX[2]&&y>=0&&y<_tileCountY[2]){
var tileBounds=_GetTileBounds(x+_tileX1[2],y+_tileY1[2],_tileLayers[_tileLayerIDs[2]].tileWidth,_tileLayers[_tileLayerIDs[2]].tileHeight,_tileLayers[_tileLayerIDs[2]].layerBounds);
var tile=null;
if((!tileLayerMaxScale||_curParam.mapScale<tileLayerMaxScale)&&(!tileLayerMinScale||_curParam.mapScale>tileLayerMinScale)&&(!tileLayerBounds||_IsBoundsTouchs(tileBounds,tileLayerBounds))){
tile=_CreateTile(x+_tileX1[2],y+_tileY1[2],_curParam.mapScale,_tileLayers[_tileLayerIDs[2]])}
if(!_tileCollections[2]){
_tileCollections[2]=new Array()}
var tileIndex=x+y*_tileCountX[2];
_tileCollections[2][tileIndex]=tile}
if(x==centerX+radius&&y==centerY+radius){
flag=0}
else{
if(x==centerX-radius&&y==centerY+radius){
flag=3}
else{
if(x==centerX-radius&&y==centerY-radius){
flag=2}
else{
if(x==centerX+radius&&y==centerY-radius){
flag=1}
}
}
}
if(radius>0){
if(flag==0){
x--}
else{
if(flag==1){
y++}
else{
if(flag==2){
x++}
else{
if(flag==3){
y--}
}
}
}
}
}
while(startX!=x||startY!=y);
radius++}
}
}
function _SwitchMap(){
_changingMap=true;
query=null;
spatialAnalyst=null;
overview=null;
edit=null;
magnifier=null;
while(_tiles.length>0){
var cTile=_tiles.pop();
cTile.RemoveFromMap();
cTile=null}
while(_trackingLayerTiles.length>0){
var cTrackingLayerTiles=_trackingLayerTiles.pop();
cTrackingLayerTiles.RemoveFromMap();
cTrackingLayerTiles=null}
while(_preTiles.length>0){
var preTile=_preTiles.pop();
preTile.RemoveFromMap();
preTile=null}
while(_preTrackingLayerTiles.length>0){
var cPreTrackingLayerTile=_preTrackingLayerTiles.pop();
cPreTrackingLayerTile.RemoveFromMap();
cPreTrackingLayerTile=null}
if(_logo){
_logo.Destroy();
_logo=null}
if(_map){
_map.Destroy()}
_ClearMarks();
_ClearLines();
_ClearPolygons();
_ClearTileLayers(true);
if(_workLayer){
_workLayer.parentNode.removeChild(_workLayer);
_workLayer.innerHTML=""}
while(_eventsList.length>0){
var events=_eventsList.pop();
while(events.length>0){
events.pop()}
}
_self.Init()}
function _AddTileLayer(id,tileIDPattern,tileUrlPattern,visibleBounds,maxScale,minScale,interval,opacity,zIndex,tileWidth,tileHeight,layerBounds,useImageBuffer){
var tileLayer=_tileLayers[id];
if(tileLayer!=null){
return false}
if(tileLayer==null){
tileLayer=new Object()}
tileLayer.id=id;
if(typeof (tileIDPattern)=="function"){
tileLayer.GetTileID=tileIDPattern}
else{
tileLayer.tileIDPattern=tileIDPattern;
tileLayer.GetTileID=function(tx,ty,ms){
var returnText=tileIDPattern.replace(/{tx}/g,tx);
returnText=returnText.replace(/{ty}/g,ty);
returnText=returnText.replace(/{ms}/g,ms);
return returnText}
}
if(typeof (tileUrlPattern)=="function"){
tileLayer.GetTileUrl=tileUrlPattern}
else{
tileLayer.tileUrlPattern=tileUrlPattern;
tileLayer.GetTileUrl=function(tx,ty,ms){
var returnText=tileUrlPattern.replace(/{tx}/g,tx);
returnText=returnText.replace(/{ty}/g,ty);
returnText=returnText.replace(/{ms}/g,ms);
return returnText}
}
if(visibleBounds){
if(tileLayer.visibleBounds==null){
tileLayer.visibleBounds=new SuperMap.IS.MapRect()}
tileLayer.visibleBounds.Copy(visibleBounds)}
tileLayer.maxScale=maxScale;
tileLayer.minScale=minScale;
tileLayer.interval=interval;
tileLayer.opacity=opacity;
if(zIndex<=0){
tileLayer.zIndex=_customLayer_baseZIndex}
else{
tileLayer.zIndex=zIndex}
if(tileWidth){
tileLayer.tileWidth=tileWidth}
else{
tileLayer.tileWidth=_tileSize}
if(tileHeight){
tileLayer.tileHeight=tileHeight}
else{
tileLayer.tileHeight=_tileSize}
if(layerBounds){
if(tileLayer.layerBounds==null){
tileLayer.layerBounds=new SuperMap.IS.MapRect()}
tileLayer.layerBounds.Copy(layerBounds)}
else{
tileLayer.layerBounds=_curMap.GetMapBounds()}
_tileLayers[tileLayer.id]=tileLayer;
_tileLayerIDs.push(tileLayer.id);
var container=document.createElement("div");
container.id=tileLayer.id;
container.style.zIndex=tileLayer.zIndex;
container.style.position="absolute";
container.style.padding="0px";
container.style.margin="0px";
container.style.width=_workLayer.style.width;
container.style.height=_workLayer.style.height;
_workLayer.appendChild(container);
tileLayer.container=container;
tileLayer.useImageBuffer=useImageBuffer;
if(tileLayer.interval&&tileLayer.interval>0){
tileLayer.timeID=window.setInterval(_RefreshTileLayer(tileLayer.id),tileLayer.interval)}
return true}
function _InsertTileLayer(id,tileIDPattern,tileUrlPattern,visibleBounds,maxScale,minScale,interval,opacity,zIndex,tileWidth,tileHeight,layerBounds,useImageBuffer){
var tileLayer=_tileLayers[id];
if(!tileLayer){
return _AddTileLayer(id,tileIDPattern,tileUrlPattern,visibleBounds,maxScale,minScale,interval,opacity,zIndex,tileWidth,tileHeight,layerBounds,useImageBuffer)}
else{
if(typeof (tileIDPattern)=="function"){
tileLayer.GetTileID=tileIDPattern}
else{
tileLayer.tileIDPattern=tileIDPattern;
tileLayer.GetTileID=function(tx,ty,ms){
var returnText=tileIDPattern.replace(/{tx}/g,tx);
returnText=returnText.replace(/{ty}/g,ty);
returnText=returnText.replace(/{ms}/g,ms);
return returnText}
}
if(typeof (tileUrlPattern)=="function"){
tileLayer.GetTileUrl=tileUrlPattern}
else{
tileLayer.tileUrlPattern=tileUrlPattern;
tileLayer.GetTileUrl=function(tx,ty,ms){
var returnText=tileUrlPattern.replace(/{tx}/g,tx);
returnText=returnText.replace(/{ty}/g,ty);
returnText=returnText.replace(/{ms}/g,ms);
return returnText}
}
tileLayer.visibleBounds=visibleBounds;
tileLayer.maxScale=maxScale;
tileLayer.minScale=minScale;
tileLayer.interval=interval;
tileLayer.opacity=opacity;
if(zIndex<=0){
tileLayer.zIndex=_customLayer_baseZIndex}
else{
tileLayer.zIndex=zIndex}
if(tileWidth){
tileLayer.tileWidth=tileWidth}
else{
tileLayer.tileWidth=_tileSize}
if(tileHeight){
tileLayer.tileHeight=tileHeight}
else{
tileLayer.tileHeight=_tileSize}
if(layerBounds){
tileLayer.layerBounds=layerBounds}
else{
tileLayer.layerBounds=_curMap.GetMapBounds()}
tileLayer.useImageBuffer=useImageBuffer;
if(tileLayer.timeID){
window.clearInterval(tileLayer.timeID)}
var imageBuffer=null;
var index=-1;
for(var i=0;
i<_tileLayerIDs.length;
i++){
if(id==_tileLayerIDs[i]){
index=i;
break}
}
if(index==-1){
return }
if(_imageBufferCollection[index]!=null){
while(_imageBufferCollection[index].length>0){
var imageId=_imageBufferCollection[index][0];
delete _imageBufferCollection[index][imageId];
_imageBufferCollection[index].shift()}
_imageBufferCollection[index]=null}
if(tileLayer.timeID){
window.clearInterval(tileLayer.timeID)}
if(tileLayer.interval&&tileLayer.interval>0){
tileLayer.timeID=window.setInterval(_RefreshTileLayer(tileLayer.id),tileLayer.interval)}
return true}
}
function _RemoveTileLayer(id){
if(!id||id==_mapDivID||id==_trackingLayerDivID||id==_geometryLayerDivID){
return false}
if(_tileLayers[id]){
if(_tileLayers[id].timeID){
window.clearInterval(_tileLayers[id].timeID)}
}
_tileLayers[id]=null;
var imageBuffer=null;
var index=-1;
for(var i=0;
i<_tileLayerIDs.length;
i++){
if(id==_tileLayerIDs[i]){
index=i;
break}
}
if(index==-1){
return true}
if(_imageBufferCollection[index]!=null){
while(_imageBufferCollection[index].length>0){
var imageId=_imageBufferCollection[index][0];
delete _imageBufferCollection[index][imageId];
_imageBufferCollection[index].shift()}
_imageBufferCollection[index]=null}
for(var i=0;
i<_tileLayerIDs.length;
i++){
if(_tileLayerIDs[i]==id){
_tileLayerIDs.splice(i,1);
return true}
}
return false}
function _ClearTileLayers(bInternal){
var success=false;
var length=_tileLayerIDs.length;
for(var i=0;
i<length;
i++){
if(!bInternal){
if(_tileLayerIDs[i]==_mapDivID||_tileLayerIDs[i]==_trackingLayerDivID||_tileLayerIDs[i]==_geometryLayerDivID){
continue}
}
if(_tileLayers[_tileLayerIDs[i]]){
if(_tileLayers[_tileLayerIDs[i]].timeID){
window.clearInterval(_tileLayers[_tileLayerIDs[i]].timeID)}
}
var imageBuffer=null;
if(_imageBufferCollection[i]!=null){
while(_imageBufferCollection[i].length>0){
var imageId=_imageBufferCollection[i][0];
delete _imageBufferCollection[i][imageId];
_imageBufferCollection[i].shift()}
_imageBufferCollection[i]=null}
var tileLayersContainer=_tileLayers[_tileLayerIDs[i]].container;
tileLayersContainer.parentNode.removeChild(tileLayersContainer);
tileLayersContainer=null;
_tileLayers[_tileLayerIDs[i]]=null;
success=true}
var index=0;
for(var i=0;
i<length;
i++){
if(!bInternal){
if(_tileLayerIDs[i]==_mapDivID||_tileLayerIDs[i]==_trackingLayerDivID){
index++;
continue}
}
var tileLayerID=_tileLayerIDs.splice(index,1);
tileLayerID=null}
return success}
this.CustomLayer.AddTileLayer=function(id,tileIDPattern,tileUrlPattern,visibleBounds,maxScale,minScale,interval,opacity,zIndex,tileWidth,tileHeight,layerBounds){
var success=_AddTileLayer(id,tileIDPattern,tileUrlPattern,visibleBounds,maxScale,minScale,interval,opacity,zIndex,tileWidth,tileHeight,layerBounds,false);
if(success){
_SetTileLayerHidden();
_RefreshMap()}
return success}
;
this.CustomLayer.InsertTileLayer=function(id,tileIDPattern,tileUrlPattern,visibleBounds,maxScale,minScale,interval,opacity,zIndex,tileWidth,tileHeight,layerBounds){
var success=_InsertTileLayer(id,tileIDPattern,tileUrlPattern,visibleBounds,maxScale,minScale,interval,opacity,zIndex,tileWidth,tileHeight,layerBounds);
if(success){
_SetTileLayerHidden();
_RefreshMap()}
return success}
;
this.CustomLayer.RemoveTileLayer=function(id){
var success=_RemoveTileLayer(id);
if(success){
_SetTileLayerHidden();
_RefreshMap()}
return success}
;
this.CustomLayer.ClearTileLayers=function(){
var success=_ClearTileLayers(false);
if(success){
_SetTileLayerHidden();
_RefreshMap()}
return success}
;
function _GetMapTileID(tx,ty,ms){
return tx+","+ty+","+ms+","+container.id}
function _GetMapTileUrl(tx,ty,ms){
return _curMap.GetTileUrl(tx,ty,ms)}
function _GetTrackingLayerTileID(tx,ty,ms){
return"tl,"+tx+","+ty+","+ms+","+container.id}
function _GetTrackingLayerTileUrl(tx,ty,ms){
if(_trackingLayerIndex<0){
return"images/spacer.gif"}
var returnString=_mapHandler+"ajax/"+encodeURI(_self.mapName)+"/"+ms+"/"+tx+"/"+ty+"/"+_trackingLayerIndex+"/"+_userID+"/"+_tileSize+"/gif/"+params.antiAlias+"/"+params.useReferBounds+"/map.ashx?GetTrackingLayerImage=true&t="+_t+"&redirect="+params.redirect;
return returnString}
function _GetGeometryLayerTileID(tx,ty,ms){
return"gl,"+tx+","+ty+","+ms+","+container.id}
function _GetGeometryLayerTileUrl(tx,ty,ms){
if(_geometryKey==""){
return"images/spacer.gif"}
var customQueryString="";
if(params.customKeys){
for(var i=0;
i<params.customKeys.length;
i++){
customQueryString+="&"+params.customKeys[i]+"="+params.customValues[i]}
}
var returnString=_mapHandler+"ajax/"+encodeURI(_self.mapName)+"/"+ms+"/"+tx+"/"+ty+"/"+_tileSize+"/"+_imageFormat+"/"+_geometryKey+"/"+params.antiAlias+"/"+params.useReferBounds+"/map.ashx?GetGeometryImage=true&t="+_t+"&redirect="+params.redirect+customQueryString;
return returnString}
function _GetTileBounds(x,y,tileWidth,tileHeight,tileBounds){
if(!tileBounds){
tileBounds=mapBounds}
var lb=new SuperMap.IS.PixelCoord();
var rt=new SuperMap.IS.PixelCoord();
lb.x=x*tileWidth;
lb.y=(y+1)*tileHeight;
rt.x=(x+1)*tileWidth;
rt.y=(y)*tileHeight;
var distance=_PixelToMapDistance(1,_curParam.mapScale);
var distanceY=_PixelToMapDistance(1,_curParam.mapScale,true);
var mlbX=tileBounds.leftBottom.x+(lb.x*distance);
var mlbY=tileBounds.rightTop.y-(lb.y*distanceY);
var mrtX=tileBounds.leftBottom.x+(rt.x*distance);
var mrtY=tileBounds.rightTop.y-(rt.y*distanceY);
var bounds=new SuperMap.IS.MapRect(mlbX,mlbY,mrtX,mrtY);
lb=null;
rt=null;
return bounds}
this.GetTileBounds=_GetTileBounds;
function _IsBoundsTouchs(tileBounds,layerBounds){
if(!tileBounds||!layerBounds){
return false}
if(tileBounds.leftBottom.x<layerBounds.rightTop.x&&tileBounds.rightTop.x>layerBounds.leftBottom.x&&tileBounds.rightTop.y>layerBounds.leftBottom.y&&tileBounds.leftBottom.y<layerBounds.rightTop.y){
return true}
return false}
function _RefreshTileLayer(id){
var refreshTileLayer=function(){
var layerTiles=null;
var imageBuffer=null;
var index=-1;
for(var i=0;
i<_tileLayerIDs.length;
i++){
if(id==_tileLayerIDs[i]){
layerTiles=_tileCollections[i];
index=i;
break}
}
if(index==-1){
return }
if(_imageBufferCollection[index]!=null){
while(_imageBufferCollection[index].length>0){
var imageId=_imageBufferCollection[index][0];
delete _imageBufferCollection[index][imageId];
_imageBufferCollection[index].shift()}
_imageBufferCollection[index]=null}
if(layerTiles.length>0){
for(var j=0;
j<layerTiles.length;
j++){
if(layerTiles[j]!=null){
layerTiles[j].RefreshUrl()}
}
}
}
;
return refreshTileLayer}
function _SetTileLayerHidden(){
var hiddenTileLayer=document.getElementById(container.id+"_hiddenTileLayers");
if(!hiddenTileLayer){
return }
if(_tileLayers==null){
hiddenTileLayer.value=""}
var tileLayers=new Array();
for(var i=0;
i<_tileLayerIDs.length;
i++){
if(_tileLayerIDs[i]==_mapDivID||_tileLayerIDs[i]==_trackingLayerDivID){
continue}
var tileLayer=_tileLayers[_tileLayerIDs[i]];
if(!tileLayer.tileIDPattern||!tileLayer.tileUrlPattern){
continue}
var tileLayerTemp=new Object();
tileLayerTemp.id=tileLayer.id;
tileLayerTemp.tileIDPattern=tileLayer.tileIDPattern;
tileLayerTemp.tileUrlPattern=tileLayer.tileUrlPattern;
tileLayerTemp.visibleBounds=tileLayer.visibleBounds;
tileLayerTemp.maxScale=tileLayer.maxScale;
tileLayerTemp.minScale=tileLayer.minScale;
tileLayerTemp.interval=tileLayer.interval;
tileLayerTemp.opacity=tileLayer.opacity;
tileLayerTemp.zIndex=tileLayer.zIndex;
tileLayerTemp.tileWidth=tileLayer.tileWidth;
tileLayerTemp.tileHeight=tileLayer.tileHeight;
tileLayerTemp.layerBounds=tileLayer.layerBounds;
tileLayers.push(tileLayerTemp)}
var json=_ToJSON(tileLayers);
hiddenTileLayer.value=json}
function _InitTileLayerAction(){
var hidden=document.getElementById(container.id+"_hiddenTileLayers");
if(hidden&&hidden.value){
var json=hidden.value;
var tileLayers=_eval(json);
if(tileLayers){
for(var i=0;
i<tileLayers.length;
i++){
if(tileLayers[i]){
_AddTileLayer(tileLayers[i].id,tileLayers[i].tileIDPattern,tileLayers[i].tileUrlPattern,tileLayers[i].visibleBounds,tileLayers[i].maxScale,tileLayers[i].minScale,tileLayers[i].interval,tileLayers[i].opacity,tileLayers[i].zIndex,tileLayers[i].tileWidth,tileLayers[i].tileHeight,tileLayers[i].layerBounds,false)}
}
}
tileLayers=null}
}
function _SetPrintMode(PrintMode,onComplete,onError,userContext){
_PrintMode=PrintMode;
if(PrintMode){
function onGetMapImageComplete(url,userContext){
var id=_self.id+"PrintLayer";
var container=document.getElementById(id);
if(container==null){
container=document.createElement("div");
container.id=id;
container.style.zIndex=3;
container.style.position="absolute";
container.style.padding="0px";
container.style.margin="0px";
container.style.width=_workLayer.style.width;
container.style.height=_workLayer.style.height;
container.style.left="0px";
container.style.top="0px"}
container.style.backgroundImage="url("+url+")";
container.style.backgroundRepeat="no-repeat";
_workLayer.appendChild(container);
if(onComplete){
onComplete(url,userContext)}
}
_curParam.pixelRect=_GetSize();
_curMap.GetMapImage(_curParam,onGetMapImageComplete,onError,userContext)}
else{
var id=_self.id+"PrintLayer";
var container=document.getElementById(id);
if(container!=null){
_workLayer.removeChild(container)}
}
}
this.SetPrintMode=_SetPrintMode}

