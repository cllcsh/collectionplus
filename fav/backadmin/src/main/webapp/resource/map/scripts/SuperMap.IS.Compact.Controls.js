// ================================================================================
// SuperMap IS .NET 客户端程序，版权所有，北京超图软件股份有限公司，2000-2008。
// 本程序只能在有效的授权许可下使用。未经许可，不得以任何手段擅自使用或传播。 
// 作 者:  SuperMap IS Web Team
// 版 本:  $Id: SuperMap.IS.Compact.Controls.js,v 1.2.2.2 2008/08/18 09:21:47 huzhn Exp $
// 文 件:  SuperMap.IS.Compact.Controls.js
// 描 述:  AjaxScripts 压缩后的控件库，包含各控件库等
// 更 新:  $Date: 2008/08/18 09:21:47 $
// ================================================================================
SuperMap.IS.MapControl = function(container, params) {
    var _self = this;
    var referViewBounds = null,
    referScale = null,
    referViewer = null,
    mapBounds = null;
    var _mapScales = null;
    var _mapName = "";
    var _imageFormat = "png";
    var _mapHandler = "/mapHandler/";
    var _mapScale = "";
    var _zoomTotalSteps = 5,
    _keyboardPanDistance = 15,
    _panStepDistance = 15;
    if (_GetBrowser() != "ie") {
        _zoomTotalSteps = 3;
        _keyboardPanDistance = 45;
        _panStepDistance = 45
    }
    var _buffer = 0;
    var _spacerTile = _scriptLocation + "../images/spacer.gif";
    var _tileSize = 256;
    var _tile_swapZIndex = 1,
    _tile_baseZIndex = 2;
    var _customLayer_baseZIndex = 11,
    _customLayer_topZIndex = 20;
    var _animationEnabled = true;
    var _curParam = new SuperMap.IS.MapParam(),
    _tempParam = new SuperMap.IS.MapParam();
    var _x = 0,
    _y = 0,
    _w = 0,
    _h = 0;
    var _tileCollections = new Array();
    var _oldTileCollections = new Array();
    var _preTileCollections = new Array();
    var _tiles = new Array(),
    _oldTiles = null,
    _preTiles = new Array();
    var _trackingLayerTiles = new Array(),
    _oldTrackingLayerTiles = null,
    _preTrackingLayerTiles = new Array();
    var _originX = 0,
    _originY = 0;
    var _offsetX = 0,
    _offsetY = 0;
    var _tileX1 = new Array(),
    _tileY1 = new Array(),
    _tileX2 = new Array(),
    _tileY2 = new Array();
    var _tileCountX = new Array(),
    _tileCountY = new Array();
    var _dragging = false,
    _keyboardPanning = false;
    var _lastMouseX = 0,
    _lastMouseY = 0;
    var _zooming = false;
    var _zoomCounter = 0;
    var _panning = false;
    var _panCounter = 0;
    var _panningX = 0,
    _panningY = 0;
    var _panMapX = null,
    _panMapY = null;
    var _logo = null;
    var _zoomInAction = null,
    _panAction = null,
    _curAction = null;
    var _map = null;
    var _curMap = null;
    var _curBounds = null,
    _viewBounds = null,
    _viewBoundsBefore = null;
    var _eventsList = new Array(),
    _eventsNameList = new Array();
    var _mapDivID = container.id + "_mapDiv";
    var _mapDiv = document.createElement("div");
    _mapDiv.id = _mapDivID;
    var _trackingLayerDivID = container.id + "_trackingLayerDiv";
    var _trackingLayerDiv = document.createElement("div");
    _trackingLayerDiv.id = _trackingLayerDivID;
    var _geometryLayerDivID = container.id + "_geometryLayerDiv";
    var _geometryLayerDiv = document.createElement("div");
    _geometryLayerDiv.id = _geometryLayerDivID;
    var _workLayer = document.createElement("div");
    _workLayer.id = container.id + "_workLayer";
    var _kbInput = document.createElement("input");
    _kbInput.id = container.id + "_kbInput";
    var _customDiv = null;
    var _marks = new Array(),
    _lines = new Array(),
    _polygons = new Array();
    var _imageBufferCollection = new Array();
    var _imageBufferSize = 64;
    var _useImageBuffer = true;
    var _d = false;
    var _getLayers = false;
    var _firstUpdated = false;
    var _tileLoadedChecking = false;
    var _iTimeoutIDForCheckTileLoaded = null;
    var _iTimeoutIDForShowPolylines = null;
    var _iTimeoutIDForUpdateMap = null;
    var _iTimeoutIDForStepPan = null;
    var _iTimeoutIDForSetFactor = null;
    var _iTimeoutIDForDynamicNavigate = null;
    var _lastBusNum = 0;
    var _layersBackupForControl = new Array();
    var _pointsForMeasure = new Array();
    var _allMapScales = null;
    var _trackingLayerIndex = -1;
    var _userID = "";
    var _trackingLayerUrl = "";
    var _geometryIDs = new Array();
    var _geometryStyles = new Array();
    var _geometryIndexs = new Array();
    var _geometries = new Array();
    var _defaultGeometryStyle = null;
    var _geometryTolerance = 5;
    var _calculateInClient = true;
    var _geometryKey = "";
    var _lastGeometryIDs = new Array();
    var _geometryClicks = new Array();
    var _geometryDblclicks = new Array();
    var _geometryMouseOvers = new Array();
    var _geometryMouseOuts = new Array();
    var query = null,
    spatialAnalyst = null,
    edit = null;
    var _changingMap = false;
    var _switching = false;
    this.workLayer = _workLayer;
    this.container = container;
    this.id = container.id;
    this.mapName = "";
    this.layers = new Array();
    var _t = new Date().getTime();
    var _tileLayerIDs = new Array();
    var _tileLayers = new Array();
    this.mapDiv = null;
    var _tileCheckTimes = 0;
    var _tileCheckCounts = 10;
    var _PrintMode = false;
    var _switchMapHistory = new Array();
    var _isIE6 = false;
    var _switchMapMode = 0;
    var _switchMapWithParam = false;
    var str = navigator.userAgent.toLowerCase();
    if (str.indexOf("msie 6.0") != -1) {
        _isIE6 = true
    }
    var _useVml = false;
    var _oldViewBounds;
    var _fixPngTransparentForIe6 = false;
    var _minsize = -170000;
    var _maxsize = 170000;
    this.wheelZoomByMouse = false;
    this.imageFormat;
    this.tileSize;
    this.customBounds = new SuperMap.IS.MapRect();
    var _customBounds = null;
    this.storeClientInfo = false;
    var _zoomOut = false;
    var _zoomIn = false;
    var _isViewEntie = false;
    this.Init = function() {
        window.curMapControl = _self;
        if (params.mapScales && params.mapScales.length > 0) {
            _mapScales = params.mapScales
        }
        if (params.allMapScales) {
            _allMapScales = params.allMapScales
        }
        if (!params.imageFormat) {
            params.imageFormat = _imageFormat
        }
        if (_self.imageFormat) {
            params.imageFormat = _self.imageFormat;
            _imageFormat = _self.imageFormat
        }
        if (params.mapHandler) {
            _mapHandler = params.mapHandler
        } else {
            params.mapHandler = _mapHandler
        }
        if (params.mapName) {
            _self.mapName = _mapName = params.mapName
        } else {
            params.mapName = ""
        }
        if (params.mapScale) {
            _mapScale = params.mapScale
        } else {
            params.mapScale = ""
        }
        if (params.useImageBuffer) {
            _useImageBuffer = params.useImageBuffer
        } else {
            params.useImageBuffer = _useImageBuffer
        }
        if (params.tileSize) {
            _tileSize = params.tileSize
        } else {
            params.tileSize = _tileSize
        }
        if (_self.tileSize) {
            params.tileSize = _self.tileSize;
            _tileSize = _self.tileSize
        }
        if (!params.layersKey) {
            params.layersKey = 0
        }
        if (typeof(params.trackingLayerIndex) != "undefined") {
            _trackingLayerIndex = params.trackingLayerIndex
        } else {
            params.trackingLayerIndex = _trackingLayerIndex
        }
        if (params.userID) {
            _userID = params.userID
        } else {
            params.userID = _userID
        }
        if (params.zoomLevel) {
            if (params.zoomLevel > 0 && _mapScales && params.zoomLevel <= _mapScales.length) {
                params.mapScale = _mapScales[params.zoomLevel - 1]
            }
        }
        if (params.fixPngTransparentForIe6) {
            _fixPngTransparentForIe6 = params.fixPngTransparentForIe6
        } else {
            params.fixPngTransparentForIe6 = _fixPngTransparentForIe6
        }
        if (params.wheelZoomByMouse) {
            _self.wheelZoomByMouse = params.wheelZoomByMouse
        }
        if (params.tileCheckTime) {
            _tileCheckCounts = params.tileCheckTime * 1000 / 200
        }
        if (params && typeof(params.switchMapMode) != "undefined") {
            _switchMapMode = params.switchMapMode
        }
        if (typeof(params.storeClientInfo) != "undefined" && params.storeClientInfo) {
            _self.storeClientInfo = params.storeClientInfo
        }
        params.id = container.id;
        _map = new SuperMap.IS.Map(params);
        _map.Init(onMapInit)
    };
    function onMapInit(mapStatus) {
        var lastName;
        if (_curMap != null && _curMap.mapName != _map.mapName) {
            lastName = _curMap.mapName
        }
        if (_curMap) {
            _curMap.Destroy();
            _curMap = null
        }
        _curMap = _map;
        if (params && typeof(params.customBounds) != "undefined") {
            _customBounds = params.customBounds[_curMap.mapName];
            _self.customBounds.Copy(_customBounds)
        }
        _curMap.AttachEvent("onchangetrackinglayer", _OnChangeTrackingLayer);
        _curMap.AttachEvent("onchangelayer", _OnChangeLayer);
        _AddTileLayer(_mapDivID, _GetMapTileID, _GetMapTileUrl, null, 0, 0, 0, 1, 1, _tileSize, _tileSize, mapStatus.referBounds, _useImageBuffer);
        _AddTileLayer(_trackingLayerDivID, _GetTrackingLayerTileID, _GetTrackingLayerTileUrl, null, 0, 0, 0, 1, 2, _tileSize, _tileSize, null, false);
        _AddTileLayer(_geometryLayerDivID, _GetGeometryLayerTileID, _GetGeometryLayerTileUrl, null, 0, 0, 0, 1, 3, _tileSize, _tileSize, mapStatus.referBounds, false);
        _InitTileLayerAction();
        _self.mapDiv = _tileLayers[_mapDivID].container;
        var errorOccurs = true;
        if (mapStatus) {
            if (_changingMap) {
                params.mapBounds = mapStatus.mapBounds;
                switch (_switchMapMode) {
                case 0:
                    if (params.x == null || params.y == null) {
                        params.x = mapStatus.mapBounds.Center().x;
                        params.y = mapStatus.mapBounds.Center().y
                    }
                    break;
                case 1:
                default:
                    break
                }
            }
            _self.mapName = mapStatus.mapName;
            if (mapStatus.mapNames && mapStatus.mapNames.length > 0) {
                if (_self.mapNames == null) {
                    _self.mapNames = new Array()
                }
                for (var i = 0; i < mapStatus.mapNames.length; i++) {
                    _self.mapNames[i] = mapStatus.mapNames[i]
                }
            }
            if (!params.layers || params.layers.length == 0) {
                params.layers = mapStatus.layers
            }
            if (mapStatus.mapBounds) {
                if (mapBounds == null) {
                    mapBounds = new SuperMap.IS.MapRect()
                }
                mapBounds.Copy(mapStatus.mapBounds)
            }
            errorOccurs = false
        }
        if (errorOccurs) {
            _TriggerEvent("onerror", new EventArguments(null, SuperMap.IS.MapControlResource.mapInitError));
            return false
        }
        function InitLayers() {
            if (!_changingMap) {
                _BackupLayers(_layersBackupForControl, _curMap.layers)
            }
            _self.layers = _curMap.layers
        }
        if (!params.layers || params.layers.length == 0) {
            _map.GetMapStatus(true, true, 
            function(responseText) {
                var mapStatusJ = eval("(" + responseText + ")");
                if (!mapStatusJ) {
                    return
                }
                var mapStatusTemp = new SuperMap.IS.MapStatus();
                mapStatusTemp.FromJSON(mapStatusJ);
                if (mapStatusTemp.layers) {
                    var layerCount = mapStatusTemp.layers.length;
                    if (_curMap.layers == null) {
                        _curMap.layers = new Array()
                    }
                    for (var i = 0; i < layerCount; i++) {
                        if (_curMap.layers[i] == null) {
                            _curMap.layers[i] = new SuperMap.IS.Layer()
                        }
                        _curMap.layers[i].Copy(mapStatusTemp.layers[i])
                    }
                    params.layers = _curMap.layers
                }
                InitLayers();
                var changedLayersFromControlJSON = _FindDifference(_layersBackupForControl, _curMap.layers);
                _SetLayersHidden(changedLayersFromControlJSON);
                _getLayers = true;
                if (_firstUpdated) {
                    _TriggerEvent("oninit", null)
                }
                if (lastName && lastName != _curMap.mapName) {
                    _TriggerEvent("onendswitchmap")
                }
                mapStatusTemp.Destroy();
                mapStatusTemp = null
            })
        } else {
            _curMap.layers = params.layers;
            InitLayers();
            _getLayers = true
        }
        _useVml = _EnableVML();
        _Update(null, false, _OnFirstUpdateComplete);
        if (_getLayers) {
            if (lastName && lastName != _curMap.mapName) {
                _TriggerEvent("onendswitchmap")
            }
        }
    }
    function _OnFirstUpdateComplete() {
        _workLayer.style.width = "100%";
        _workLayer.style.height = "100%";
        _workLayer.style.overflow = "hidden";
        _workLayer.style.border = "0px";
        _workLayer.style.padding = "0px";
        _workLayer.style.margin = "0px";
        _workLayer.style.position = "relative";
        container.appendChild(_workLayer);
        _GetPosAndSize();
        if (_w < 100) {
            container.style.width = "100px";
            _workLayer.style.width = "100%"
        }
        if (_h < 100) {
            container.style.height = "100px";
            _workLayer.style.height = "100%"
        }
        container.style.overflow = "hidden";
        _GetPosAndSize();
        _workLayer.style.width = _w + "px";
        _workLayer.style.height = _h + "px";
        _GetPosAndSize();
        _GetCustomDiv();
        _kbInput.value = "";
        var ks = _kbInput.style;
        ks.position = "absolute";
        ks.top = _h / 2 + "px";
        ks.left = _w / 2 + "px";
        if (_GetBrowser() == "ie" || _GetBrowser() == "gecko") {
            ks.width = "0px";
            ks.height = "0px"
        }
        ks.padding = "0px";
        ks.margin = "0px";
        ks.border = "0px solid white";
        ks.zIndex = -1;
        ks = null;
        _workLayer.appendChild(_kbInput);
        if (!params.fixedView) {
            _zoomInAction = new SuperMap.IS.ZoomInAction();
            _zoomInAction.Init(_self);
            _panAction = new SuperMap.IS.PanAction();
            _panAction.Init(_self);
            _curAction = _panAction;
            if (!_changingMap) {
                container.attachEvent("onmousedown", _MouseDown);
                container.attachEvent("onmouseup", _MouseUp);
                container.attachEvent("onmousemove", _MouseMove);
                container.attachEvent("onmousewheel", _MouseWheel);
                container.attachEvent("ondblclick", _DblClick);
                container.attachEvent("oncontextmenu", _ContextMenu);
                container.attachEvent("onclick", _Click);
                _kbInput.attachEvent("onkeydown", _KeyDown);
                _kbInput.attachEvent("onkeyup", _KeyUp)
            }
            _changingMap = false;
            _buffer = _tileSize
        }
        if (params.buffer != undefined && params.buffer != null) {
            _buffer = params.buffer
        }
        if (params.x != null && params.y != null && params.mapScale) {
            try {
                var mp = new SuperMap.IS.MapParam(_mapName, _mapScale, _mapScales);
                mp.SetMapScale(eval(params.mapScale));
                mp.SetMapCenter(new SuperMap.IS.MapCoord(params.x, params.y));
                if (_switching) {
                    switch (_switchMapMode) {
                    case 0:
                        if (_switchMapHistory[_mapName] && _switchMapWithParam == false) {
                            mp.SetMapScale(eval(_switchMapHistory[_mapName].mapScale));
                            mp.SetMapCenter(new SuperMap.IS.MapCoord(_switchMapHistory[_mapName].mapCenter.x, _switchMapHistory[_mapName].mapCenter.y))
                        }
                        break;
                    default:
                        break
                    }
                }
                _SetMapParam(mp)
            } catch(e) {
                _SetDefaultParam()
            }
        } else {
            _SetDefaultParam()
        }
        if (!params.disableLogo) {
            _logo = new Logo(_workLayer);
            _logo.Init()
        }
        _oldViewBounds = _GetViewBounds();
        _InitClientAction();
        _InitMarks();
        _InitLines();
        _InitPolygons();
        _firstUpdated = true;
        _switchMapWithParam = false;
        if (_getLayers) {
            _TriggerEvent("oninit", null)
        }
    }
    this.Destroy = function() {
        _TriggerEvent("ondestroying", null);
        _ClearTimeout();
        window.curMapControl = null;
        _getLayers = false;
        _firstUpdated = false;
        _switchMapHistory = null;
        if (!params.fixedView) {
            container.detachEvent("onmousedown", _MouseDown);
            container.detachEvent("onmouseup", _MouseUp);
            container.detachEvent("onmousemove", _MouseMove);
            container.detachEvent("onmousewheel", _MouseWheel);
            container.detachEvent("ondblclick", _DblClick);
            container.detachEvent("oncontextmenu", _ContextMenu);
            container.detachEvent("onclick", _Click);
            _kbInput.detachEvent("onkeydown", _KeyDown);
            _kbInput.detachEvent("onkeyup", _KeyUp)
        }
        while (_tileCollections.length > 0) {
            var tiles = _tileCollections.pop();
            while (tiles.length > 0) {
                var tile = tiles.pop();
                if (tile != null) {
                    tile.RemoveFromMap();
                    tile = null
                }
            }
            tiles = null
        }
        _tileCollections = null;
        while (_preTileCollections.length > 0) {
            var preTiles = _preTileCollections.pop();
            while (preTiles.length > 0) {
                var preTile = preTiles.pop();
                if (preTile != null) {
                    preTile.RemoveFromMap();
                    preTile = null
                }
            }
            preTiles = null
        }
        _preTileCollections = null;
        if (_logo) {
            _logo.Destroy();
            _logo = null
        }
        if (_map) {
            _map.Destroy();
            _map = null
        }
        if (_zoomInAction) {
            _zoomInAction.Destroy();
            _zoomInAction = null
        }
        if (_panAction) {
            _panAction.Destroy();
            _panAction = null
        }
        if (_curAction) {
            _curAction.Destroy();
            _curAction = null
        }
        _ClearMarks();
        _marks = null;
        _ClearLines();
        _lines = null;
        _ClearPolygons();
        _polygons = null;
        _ClearGeometries();
        _geometryIDs = null;
        _geometries = null;
        _geometryStyles = null;
        _geometryIndexs = null;
        _geometryClicks = null;
        _geometryDblclicks = null;
        _geometryMouseOvers = null;
        _geometryMouseOuts = null;
        _defaultGeometryStyle = null;
        _ClearTileLayers(true);
        _tileLayerIDs = null;
        _tileLayers = null;
        _imageBufferCollection = null;
        _ClearEvents();
        if (_customDiv && _customDiv.parentNode) {
            _customDiv.parentNode.removeChild(_customDiv)
        }
        _self.workLayer = null;
        _self.container = null;
        _kbInput = _customDiv = _workLayer = container = _self = null;
        if (params.layers) {
            while (params.layers.length > 0) {
                var layer = params.layers.pop();
                layer.Destroy();
                layer = null
            }
            params.layers = null
        }
        if (this.layers) {
            while (this.layers.length > 0) {
                var layer = this.layers.pop();
                layer.Destroy();
                layer = null
            }
            this.layers = null
        }
        params = null
    };
    function _Update(updateParam, refresh, onComplete, onError, userContext) {
        var bRefresh = refresh;
        if (_self.mapName != _curMap.mapName || (_self.imageFormat != "" && _self.imageFormat != null && _self.imageFormat != _imageFormat) || (_self.tileSize != null && _self.tileSize != _tileSize)) {
            if (_switching) {
                return
            }
            _getLayers = false;
            _firstUpdated = false;
            _switching = true;
            if (_switchMapHistory[_curMap.mapName] == null) {
                _switchMapHistory[_curMap.mapName] = new Object()
            }
            params.mapName = _self.mapName;
            params.x = null;
            params.y = null;
            params.mapScale = null;
            if (params.mapScales) {
                while (params.mapScales.length > 0) {
                    var pMapScale = params.mapScales.pop();
                    pMapScale = null
                }
                params.mapScales = null
            }
            if (params.layers) {
                while (params.layers.length > 0) {
                    var pLayer = params.layers.pop();
                    pLayer.Destroy();
                    pLayer = null
                }
                params.layers = null
            }
            params.zoomLevel = null;
            var mapParam = _GetMapParam();
            switch (_switchMapMode) {
            case 0:
                _switchMapHistory[_curMap.mapName].mapCenter = new SuperMap.IS.MapCoord();
                _switchMapHistory[_curMap.mapName].mapCenter.x = mapParam.mapCenter.x;
                _switchMapHistory[_curMap.mapName].mapCenter.y = mapParam.mapCenter.y;
                _switchMapHistory[_curMap.mapName].mapScale = mapParam.mapScale;
                break;
            case 1:
                params.x = mapParam.mapCenter.x;
                params.y = mapParam.mapCenter.y;
                params.mapScale = mapParam.mapScale;
                break;
            default:
                break
            }
            if (updateParam) {
                if (updateParam.mapName) {
                    params.mapName = updateParam.mapName
                }
                if (updateParam.center) {
                    params.x = updateParam.center.x;
                    params.y = updateParam.center.y
                }
                if (updateParam.mapScale) {
                    params.mapScale = updateParam.mapScale
                }
                _switchMapWithParam = true
            }
            mapParam.Destroy();
            mapParam = null;
            _TriggerEvent("onstartswitchmap", new EventArguments(params, ""));
            while (_mapScales && _mapScales.length > 0) {
                var cMapScales = _mapScales.pop();
                cMapScales = null
            }
            if (_allMapScales && _allMapScales[_self.mapName]) {
                var mapScalesReciprocal = _allMapScales[_self.mapName].split(",");
                if (!_mapScales) {
                    _mapScales = new Array()
                }
                for (var i = 0; i < mapScalesReciprocal.length; i++) {
                    _mapScales.push(1 / parseFloat(mapScalesReciprocal[i]))
                }
                while (mapScalesReciprocal.length > 0) {
                    var mReciprocal = mapScalesReciprocal.pop();
                    mReciprocal = null
                }
                mapScalesReciprocal = null
            }
            function _onUpdateComplete() {
                if (onComplete) {
                    onComplete(params.layersKey, userContext)
                }
                _DetachEvent("oninit", _onUpdateComplete)
            }
            var inputLayersKey = document.getElementById(container.id + "_hiddenLayersKey");
            if (inputLayersKey) {
                inputLayersKey.value = ""
            }
            try {
                params.trackingLayerIndex = -1;
                _AttachEvent("oninit", _onUpdateComplete);
                _SwitchMap()
            } catch(e) {
                if (onError) {
                    onError(e.message, userContext)
                }
                return
            }
            return
        } else {
            var changedLayersFromControlJSON = _FindDifference(_layersBackupForControl, _curMap.layers);
            _SetLayersHidden(changedLayersFromControlJSON);
            function _UpdateComplete(layersKey, userContext) {
                if (params.layersKey != layersKey || bRefresh) {
                    while (_imageBufferCollection.length > 0) {
                        var imageBuffer = _imageBufferCollection.pop();
                        while (imageBuffer && imageBuffer.length > 0) {
                            var imageId = imageBuffer[0];
                            delete imageBuffer[imageId];
                            imageBuffer.shift()
                        }
                    }
                }
                params.layersKey = layersKey;
                if (bRefresh) {
                    _RefreshMap()
                }
                if (onComplete) {
                    onComplete(layersKey, userContext)
                }
                _BackupLayers(_curMap.layersBackupForHandler, _curMap.layers);
                _switching = false
            }
            _curMap.Update(_UpdateComplete, onError, userContext)
        }
    }
    this.Update = function(onComplete, onError, userContext) {
        _Update(null, true, onComplete, onError, userContext)
    };
    this.SwitchMap = function(switchParam, onComplete, onError, userContext) {
        if (switchParam && switchParam.mapName) {
            _self.mapName = switchParam.mapName
        }
        _Update(switchParam, true, onComplete, onError, userContext)
    };
    function _ClearTimeout() {
        if (_iTimeoutIDForCheckTileLoaded) {
            window.clearTimeout(_iTimeoutIDForCheckTileLoaded);
            _iTimeoutIDForCheckTileLoaded = null
        }
        if (_iTimeoutIDForShowPolylines) {
            window.clearTimeout(_iTimeoutIDForShowPolylines);
            _iTimeoutIDForShowPolylines = null
        }
        if (_iTimeoutIDForUpdateMap) {
            window.clearTimeout(_iTimeoutIDForUpdateMap);
            _iTimeoutIDForUpdateMap = null
        }
        if (_iTimeoutIDForStepPan) {
            window.clearTimeout(_iTimeoutIDForStepPan);
            _iTimeoutIDForStepPan = null
        }
        if (_iTimeoutIDForSetFactor) {
            window.clearTimeout(_iTimeoutIDForSetFactor);
            _iTimeoutIDForSetFactor = null
        }
        if (_iTimeoutIDForDynamicNavigate) {
            window.clearTimeout(_iTimeoutIDForDynamicNavigate);
            _iTimeoutIDForDynamicNavigate = null
        }
    }
    function _GetPosAndSize() {
        _x = _GetElementX(_workLayer);
        _y = _GetElementY(_workLayer);
        _w = _workLayer.offsetWidth;
        _h = _workLayer.offsetHeight
    }
    function _SetDefaultParam() {
        var param = new SuperMap.IS.MapParam(_mapName, _mapScale, _mapScales);
        param.SetMapCenter(new SuperMap.IS.MapCoord(0, 0));
        param.SetMapScale(1);
        _SetMapParam(param)
    }
    function _GetMapCenterX() {
        return _curParam.center.x
    }
    function _GetMapCenterY() {
        return _curParam.center.y
    }
    function _GetPixelCenterX() {
        return _curParam.pixelCenter.x
    }
    function _GetPixelCenterY() {
        return _curParam.pixelCenter.y
    }
    function _ComputeCenterPoint(capture) {
        _curParam.center = _curMap.PixelToMapCoord(_curParam.pixelCenter, _curParam.mapScale);
        if (capture) {
            _tempParam.Copy(_curParam)
        }
    }
    function _GetMapScale() {
        return _curParam.mapScale
    }
    function _GetZoomLevel() {
        return _curParam.zoomLevel
    }
    function _MapCoordToPixel(mc) {
        var pc = _curMap.MapCoordToPixel(mc, _curParam.mapScale);
        pc.x -= _originX + _offsetX;
        pc.y -= _originY + _offsetY;
        return pc
    }
    function _PixelToMapCoord(pc) {
        var pj = new SuperMap.IS.PixelCoord(pc.x + _originX + _offsetX, pc.y + _originY + _offsetY);
        var mc = _curMap.PixelToMapCoord(pj, _curParam.mapScale);
        return mc
    }
    function _PixelToMapDistance(pd, ms, byHeight) {
        return _curMap.PixelToMapDistance(pd, ms, byHeight)
    }
    function _GetSize() {
        return new SuperMap.IS.PixelRect(0, 0, _w, _h)
    }
    function _Resize(width, height) {
        if ((!width || width <= 0) && (!height || height <= 0)) {
            return
        }
        if (width && width > 0) {
            _w = width
        }
        if (height && height > 0) {
            _h = height
        }
        container.style.width = _w + "px";
        container.style.height = _h + "px";
        _workLayer.style.width = _w + "px";
        _workLayer.style.height = _h + "px";
        _GetPosAndSize();
        _PanToCurCenter(_curParam);
        if (_logo) {
            _logo.Reposition()
        }
        _SetSizeHidden();
        _TriggerEvent("onresize")
    }
    function _SetAnimationEnabled(enabled) {
        _animationEnabled = enabled
    }
    function _IsAnimationEnabled() {
        return _animationEnabled
    }
    function _Debug(enabled) {
        _d = enabled;
        for (var i = 0; i < _tiles.length; i++) {
            _tiles[i].Debug(enabled)
        }
    }
    function _EnsureWithinBounds(param, bounds) {
        if (_customBounds != null) {
            _PanWithinCustomBounds(param, bounds, 0, 0)
        } else {
            _PanWithinBounds(param, bounds, 0, 0)
        }
    }
    function _PanWithinBounds(param, bounds, deltaX, deltaY) {
        var newPosition = _curMap.PixelToMapCoord(new SuperMap.IS.PixelCoord(deltaX + param.pixelCenter.x, deltaY + param.pixelCenter.y), param.mapScale);
        if (_d) {
            window.status = "oldX:" + newPosition.x
        }
        var changed = false;
        if (bounds == null) {
            return
        }
        if (newPosition.x < bounds.leftBottom.x) {
            newPosition.x = bounds.leftBottom.x;
            changed = true
        }
        if (newPosition.y < bounds.leftBottom.y) {
            newPosition.y = bounds.leftBottom.y;
            changed = true
        }
        if (newPosition.x > bounds.rightTop.x) {
            newPosition.x = bounds.rightTop.x;
            changed = true
        }
        if (newPosition.y > bounds.rightTop.y) {
            newPosition.y = bounds.rightTop.y;
            changed = true
        }
        if (changed) {
            var newPixelPosition = _curMap.MapCoordToPixel(newPosition, param.mapScale);
            param.SetPixelCenter(newPixelPosition)
        } else {
            param.SetPixelCenter(new SuperMap.IS.PixelCoord(param.pixelCenter.x + deltaX, param.pixelCenter.y + deltaY))
        }
        param.SetMapCenter(newPosition);
        if (_d) {
            window.status += "newX:" + newPosition.x
        }
        return
    }
    function _PanWithinCustomBounds(param, bounds, deltaX, deltaY) {
        var changedRect = false;
        var oldScale = param.mapScale;
        var newPosition = _curMap.PixelToMapCoord(new SuperMap.IS.PixelCoord(deltaX + param.pixelCenter.x, deltaY + param.pixelCenter.y), param.mapScale);
        if (_d) {
            window.status = "oldX:" + newPosition.x
        }
        if (!_IsInCustomBounds(newPosition, bounds, param.mapScale)) {
            _ChooseScale(newPosition, param, bounds, param.mapScale);
            if ((_zoomOut && _curMap.mapScale < param.mapScale)) {
                param.SetMapScale(_curMap.mapScale);
                return
            }
            if (_curMap.mapScale == param.mapScale || _isViewEntie) {
                return
            }
        }
        if (_curAction && _curAction.type == "SuperMap.IS.ZoomOutAction") {
            if (!_zoomIn && param.mapScale >= _curMap.mapScale) {
                param.SetMapScale(_curMap.mapScale);
                return
            }
        }
        var newPixelPosition = _curMap.MapCoordToPixel(newPosition, param.mapScale);
        if (_curAction && _curAction.type != "SuperMap.IS.ZoomOutAction") {
            param.SetPixelCenter(newPixelPosition)
        }
        param.SetMapCenter(newPosition);
        if (_d) {
            window.status += "newX:" + newPosition.x
        }
    }
    function _SetCurMapScale(param, mapScale) {
        var mapScale1 = param.mapScales[0];
        for (var i = 0; i < param.mapScales.length; i++) {
            if (mapScale == param.mapScales[i]) {
                return mapScale
            }
            if (mapScale > param.mapScales[i]) {
                mapScale1 = _Min(param.mapScales[i], mapScale1)
            }
        }
        return mapScale1
    }
    function _ChooseScale(position, param, bounds, curMapScale) {
        var newMapScale;
        var chanceScale = null;
        var width = _self.container.style.pixelWidth;
        var height = _self.container.style.pixelHeight;
        var dScaleRatio;
        var mapWidth = _curMap.PixelToMapDistance(width, curMapScale);
        var mapHeight = _curMap.PixelToMapDistance(height, curMapScale);
        var selfWidth = bounds.Width();
        var selfHeight = bounds.Height();
        if (param.mapScales == null || param.mapScales.length <= 0) {
            var mapDistance = 0;
            var selfDistance = 0;
            var distance = 0;
            if (width < height) {
                mapDistance = mapHeight;
                selfDistance = selfHeight;
                distance = height
            } else {
                mapDistance = mapWidth;
                selfDistance = selfWidth;
                distance = width
            }
            newMapScale = _curMap.ComputeMapScaleByDistance(mapDistance, selfDistance, distance);
            if (curMapScale > newMapScale) {
                newMapScale = curMapScale
            }
            _IsInCustomBounds(position, bounds, newMapScale)
        } else {
            newMapScale = _SetCurMapScale(param, curMapScale);
            if (_IsInCustomBounds(position, bounds, newMapScale)) {
                return
            }
            newMapScale = null;
            for (var i = 0; i < param.mapScales.length; i++) {
                if (_IsInCustomBounds(position, bounds, param.mapScales[i])) {
                    if (newMapScale == null) {
                        newMapScale = param.mapScales[i]
                    }
                    if (newMapScale > param.mapScales[i]) {
                        newMapScale = param.mapScales[i]
                    }
                }
            }
        }
        param.SetMapScale(newMapScale)
    }
    function _IsInCustomBounds(position, bounds, mapScale) {
        var curMapHeight = 0;
        var curMapWidth = 0;
        var boundsRightTopX = 0;
        var boundsRightTopY = 0;
        var boundsLeftBottomX = 0;
        var boundsLeftBottomY = 0;
        var right = true;
        if (mapScale != null) {
            curMapHeight = _PixelToMapDistance(_self.container.style.pixelHeight, mapScale);
            curMapWidth = _PixelToMapDistance(_self.container.style.pixelWidth, mapScale)
        }
        boundsRightTopX = bounds.rightTop.x - curMapWidth / 2;
        boundsRightTopY = bounds.rightTop.y - curMapHeight / 2;
        boundsLeftBottomX = curMapWidth / 2 + bounds.leftBottom.x;
        boundsLeftBottomY = curMapHeight / 2 + bounds.leftBottom.y;
        if (position.x < boundsLeftBottomX) {
            position.x = boundsLeftBottomX;
            right = false
        }
        if (position.y < boundsLeftBottomY) {
            position.y = boundsLeftBottomY;
            right = false
        }
        if (position.x > boundsRightTopX) {
            position.x = boundsRightTopX;
            right = false
        }
        if (position.y > boundsRightTopY) {
            position.y = boundsRightTopY;
            right = false
        }
        return right
    }
    function _GenerateEventArg(curCenter, ms, error, e) {
        var param = _curParam.MakeCopy();
        if (curCenter) {
            param.center = curCenter
        }
        if (ms) {
            param.mapScale = ms
        }
        if (!error) {
            error = ""
        }
        return new EventArguments(param, error, e)
    }
    function _AttachEvent(event, listener) {
        var events = _eventsList[event];
        if (!events) {
            events = new Array();
            _eventsList[event] = events;
            _eventsNameList.push(event)
        }
        for (var i = 0; i < events.length; i++) {
            if (events[i] == listener) {
                return true
            }
        }
        events.push(listener)
    }
    function _DetachEvent(event, listener) {
        var events = _eventsList[event];
        if (!events) {
            return
        }
        for (var i = 0; i < events.length; i++) {
            if (events[i] == listener) {
                events.splice(i, 1)
            }
        }
    }
    function _TriggerEvent(event, arguments, userContext) {
        var events = _eventsList[event];
        if (!events) {
            return
        }
        if (!arguments) {
            arguments = _GenerateEventArg()
        }
        var eventsTemp = events.concat();
        for (var i = 0; i < eventsTemp.length; i++) {
            if (eventsTemp[i]) {
                eventsTemp[i](arguments, userContext)
            }
        }
        while (eventsTemp.length > 0) {
            eventsTemp.pop()
        }
    }
    function _ClearEvents() {
        while (_eventsNameList.length) {
            var events = _eventsList[_eventsNameList.pop()];
            if (events) {
                while (events.length) {
                    events.pop()
                }
                events = null
            }
        }
        _eventsList = null;
        _eventsNameList = null
    }
    function _KeyDown(e) {
        e = _GetEvent(e);
        var s = _keyboardPanDistance;
        var x = _panningX,
        y = _panningY;
        switch (e.keyCode) {
        case 9:
            if (_panning && _keyboardPanning) {
                _StopDynamicPan()
            }
            return true;
        case 37:
            x = -s;
            break;
        case 38:
            y = -s;
            break;
        case 39:
            x = s;
            break;
        case 40:
            y = s;
            break;
        case 107:
        case 187:
        case 61:
        case 43:
            x = 0;
            y = 0;
            _ZoomIn();
            break;
        case 109:
        case 189:
        case 45:
            x = 0;
            y = 0;
            _ZoomOut();
            break;
        default:
            return false
        }
        if (x || y) {
            _DynamicPan(x, y, null, true)
        }
        if (_d) {
            window.status = "e.keyCode:" + e.keyCode
        }
        return false
    }
    function _KeyUp(e) {
        e = _GetEvent(e);
        var x = _panningX;
        var y = _panningY;
        switch (e.keyCode) {
        case 37:
            x = 0;
            break;
        case 38:
            y = 0;
            break;
        case 39:
            x = 0;
            break;
        case 40:
            y = 0;
            break;
        default:
            return false
        }
        _DynamicPan(x, y, null, true);
        return false
    }
    function Logo(workLayer) {
        var _logoElement = null;
        this.Init = function() {
            if (navigator.userAgent.toLowerCase().indexOf("msie") != -1) {
                _logoElement = document.createElement("div");
                _logoElement.id = "logoSuperMap";
                _logoElement.className = "logoSuperMap logoSuperMap_IE";
                _logoElement.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + _scriptLocation + "../images/supermapisnet.png', sizingMethod='scale')"
            } else {
                _logoElement = document.createElement("img");
                _logoElement.src = _scriptLocation + "../images/supermapisnet.png";
                _logoElement.className = "logoSuperMap"
            }
            _Reposition();
            workLayer.appendChild(_logoElement)
        };
        this.Destroy = function() {
            _workLayer.removeChild(_logoElement);
            _logoElement = null
        };
        function _Reposition() {
            var logoHeight = _logoElement.style.pixelHeight;
            if (!logoHeight) {
                logoHeight = 30
            }
            _logoElement.style.top = (_h - logoHeight - 3) + "px";
            _logoElement.style.left = "6px";
            _logoElement.style.display = "block"
        }
        function _Update() {
            _Reposition()
        }
        this.Reposition = _Reposition;
        this.Update = _Update
    }
    function _GetEventPosition(e) {
        e.pixelCoord = new SuperMap.IS.PixelCoord(_originX + _offsetX + _GetMouseX(e) - _x, _originY + _offsetY + _GetMouseY(e) - _y);
        e.mapCoord = _curMap.PixelToMapCoord(e.pixelCoord, _curParam.mapScale);
        e.offsetCoord = new SuperMap.IS.PixelCoord(e.pixelCoord.x - _originX, e.pixelCoord.y - _originY);
        if (_d) {
            window.status = "e.pixelCoord:" + e.pixelCoord.x + "," + e.pixelCoord.y
        }
    }
    function _MouseDown(e) {
        e = _GetEvent(e);
        _CancelBubble(e);
        _GetEventPosition(e);
        if (_curAction && _curAction.OnMouseDown && !_PrintMode) {
            _curAction.OnMouseDown(e)
        }
        return false
    }
    function _MouseMove(e) {
        e = _GetEvent(e);
        _CancelBubble(e);
        _GetEventPosition(e);
        e.cancelTriggerGeometryEvent = false;
        if (_curAction && _curAction.OnMouseMove && !_PrintMode) {
            _curAction.OnMouseMove(e)
        }
        _TriggerGeometryEvent(e, "ongeometrymousemove");
        return false
    }
    function _MouseUp(e) {
        e = _GetEvent(e);
        _CancelBubble(e);
        _GetEventPosition(e);
        if (_curAction && _curAction.OnMouseUp && !_PrintMode) {
            _curAction.OnMouseUp(e)
        }
        try {
            _kbInput.focus()
        } catch(ex) {}
        return false
    }
    function _Click(e) {
        e = _GetEvent(e);
        _CancelBubble(e);
        _GetEventPosition(e);
        e.cancelTriggerGeometryEvent = false;
        if (_curAction && _curAction.OnClick && !_PrintMode) {
            _curAction.OnClick(e)
        }
        _TriggerGeometryEvent(e, "ongeometryclick")
    }
    function _DblClick(e) {
        e = _GetEvent(e);
        _CancelBubble(e);
        _GetEventPosition(e);
        e.cancelTriggerGeometryEvent = false;
        if (_curAction && _curAction.OnDblClick && !_PrintMode) {
            _curAction.OnDblClick(e)
        } else {
            _GetPosAndSize();
            if (_panning || _zooming) {
                return false
            }
            var param = _tempParam.MakeCopy();
            param.SetPixelCenter(new SuperMap.IS.PixelCoord(_originX + _offsetX + _GetMouseX(e) - _x, _originY + _offsetY + _GetMouseY(e) - _y));
            if (!e.altKey) {
                if (param.mapScales && param.mapScales.length > 0) {
                    if (_curParam.zoomLevel < param.mapScales.length) {
                        param.SetMapScale(param.mapScales[_curParam.zoomLevel])
                    } else {
                        param.SetMapScale(param.mapScales[param.mapScales.length])
                    }
                } else {
                    param.SetMapScale(_curParam.mapScale * 2)
                }
            } else {
                if (param.mapScales && param.mapScales.length > 0) {
                    if (_curParam.zoomLevel > 1) {
                        param.SetMapScale(param.mapScales[_curParam.zoomLevel - 2])
                    } else {
                        param.SetMapScale(param.mapScales[0])
                    }
                } else {
                    param.SetMapScale(_curParam.mapScale / 2)
                }
            }
            _SetMapParam(param)
        }
        _TriggerGeometryEvent(e, "ongeometrydblclick")
    }
    function _ContextMenu(e) {
        e = _GetEvent(e);
        _CancelBubble(e);
        _GetEventPosition(e);
        if (_curAction && _curAction.OnContextMenu && !_PrintMode) {
            _curAction.OnContextMenu(e)
        }
        if (e.preventDefault) {
            e.preventDefault()
        }
        return false
    }
    function _MouseWheel(e) {
        if (_PrintMode) {
            return false
        }
        e = _GetEvent(e);
        _CancelBubble(e);
        if (_panning || _zooming) {
            return false
        }
        var delta = _GetMouseScrollDelta(e);
        if (delta > 0) {
            if (!_self.wheelZoomByMouse) {
                _ZoomIn()
            } else {
                _ZoomInByFixure(e)
            }
        } else {
            if (delta < 0) {
                if (!_self.wheelZoomByMouse) {
                    _ZoomOut()
                } else {
                    _ZoomOutByFixure(e)
                }
            }
        }
        if (e.preventDefault) {
            e.preventDefault()
        }
        return false
    }
    function _TriggerGeometryEvent(e, eventName) {
        if (e.cancelTriggerGeometryEvent) {
            return
        }
        if (!_geometryKey) {
            return
        }
        var eventNames = new Array();
        var eventAttached = false;
        switch (eventName) {
        case "ongeometrydblclick":
            var events = _eventsList["ongeometrydblclick"];
            if (events && events.length >= 0) {
                eventAttached = true
            }
            if (!eventAttached) {
                for (var i = 0; i < _geometryDblclicks.length; i++) {
                    if (_geometryDblclicks[i] != null) {
                        eventAttached = true;
                        break
                    }
                }
            }
            if (eventAttached) {
                eventNames.push("ongeometrydblclick")
            }
            break;
        case "ongeometryclick":
            var events = _eventsList["ongeometryclick"];
            if (events && events.length >= 0) {
                eventAttached = true
            }
            if (!eventAttached) {
                for (var i = 0; i < _geometryClicks.length; i++) {
                    if (_geometryClicks[i] != null) {
                        eventAttached = true;
                        break
                    }
                }
            }
            if (eventAttached) {
                eventNames.push("ongeometryclick")
            }
            break;
        case "ongeometrymousemove":
            var events;
            events = _eventsList["ongeometrymouseover"];
            if (events && events.length > 0) {
                eventAttached = true
            }
            events = _eventsList["ongeometrymouseout"];
            if (events && events.length > 0) {
                eventAttached = true
            }
            if (!eventAttached) {
                for (var i = 0; i < _geometryMouseOvers.length; i++) {
                    if (_geometryMouseOvers[i] != null) {
                        eventAttached = true;
                        break
                    }
                }
            }
            if (!eventAttached) {
                for (var i = 0; i < _geometryMouseOuts.length; i++) {
                    if (_geometryMouseOuts[i] != null) {
                        eventAttached = true;
                        break
                    }
                }
            }
            if (eventAttached) {
                eventNames.push("ongeometrymouseover");
                eventNames.push("ongeometrymouseout")
            }
            break;
        default:
            return
        }
        if (!eventAttached) {
            return
        }
        function onQueryGeometryComplete(ids, userContext) {
            for (var i = 0; i < eventNames.length; i++) {
                var relatedIds;
                switch (eventNames[i]) {
                case "ongeometrydblclick":
                    relatedIds = ids.concat();
                    break;
                case "ongeometryclick":
                    relatedIds = ids.concat();
                    break;
                case "ongeometrymouseover":
                    relatedIds = new Array();
                    if (ids.length == 0) {} else {
                        for (var j = 0; j < ids.length; j++) {
                            var exists = false;
                            for (var k = 0; k < _lastGeometryIDs.length; k++) {
                                if (ids[j] == _lastGeometryIDs[k]) {
                                    exists = true;
                                    break
                                }
                            }
                            if (!exists) {
                                relatedIds.push(ids[j])
                            }
                        }
                    }
                    break;
                case "ongeometrymouseout":
                    relatedIds = new Array();
                    if (_lastGeometryIDs.length == 0) {} else {
                        for (var j = 0; j < _lastGeometryIDs.length; j++) {
                            var exists = false;
                            for (var k = 0; k < ids.length; k++) {
                                if (_lastGeometryIDs[j] == ids[k]) {
                                    exists = true;
                                    break
                                }
                            }
                            if (!exists) {
                                relatedIds.push(_lastGeometryIDs[j])
                            }
                        }
                    }
                    break;
                default:
                    return
                }
                e.ids = relatedIds;
                for (var j = 0; j < relatedIds.length; j++) {
                    for (var k = 0; k < _geometryIDs.length; k++) {
                        if (relatedIds[j] == _geometryIDs[k]) {
                            switch (eventNames[i]) {
                            case "ongeometrydblclick":
                                if (_geometryDblclicks[k]) {
                                    e.id = relatedIds[j];
                                    _geometryDblclicks[k](e)
                                }
                                break;
                            case "ongeometryclick":
                                if (_geometryClicks[k]) {
                                    e.id = relatedIds[j];
                                    _geometryClicks[k](e)
                                }
                                break;
                            case "ongeometrymouseover":
                                if (_geometryMouseOvers[k]) {
                                    e.id = relatedIds[j];
                                    _geometryMouseOvers[k](e)
                                }
                                break;
                            case "ongeometrymouseout":
                                if (_geometryMouseOuts[k]) {
                                    e.id = relatedIds[j];
                                    _geometryMouseOuts[k](e)
                                }
                                break;
                            default:
                                break
                            }
                        }
                    }
                }
                var events = _eventsList[eventNames[i]];
                if (events && events.length >= 0) {
                    var eventsTemp = events.concat();
                    for (var j = 0; j < eventsTemp.length; j++) {
                        if (eventsTemp[j]) {
                            eventsTemp[j](e, userContext)
                        }
                    }
                    while (eventsTemp.length > 0) {
                        eventsTemp.pop()
                    }
                }
            }
            _lastGeometryIDs = ids
        }
        _QueryGeomtryByPoint(e.mapCoord, _geometryTolerance, _calculateInClient, onQueryGeometryComplete, null, null)
    }
    function _EmptyFunction(e) {}
    function _Pan(deltaX, deltaY, manualStop) {
        if (deltaX == 0 && deltaY == 0) {
            return
        }
        if (_customBounds != null) {
            _PanWithinCustomBounds(_curParam, _customBounds, deltaX, deltaY)
        } else {
            _PanWithinBounds(_curParam, _curBounds, deltaX, deltaY)
        }
        _offsetX = _curParam.pixelCenter.x - _originX - _w / 2;
        _offsetY = _curParam.pixelCenter.y - _originY - _h / 2;
        for (var i = 0; i < _tileLayerIDs.length; i++) {
            _tileLayers[_tileLayerIDs[i]].container.style.top = -_offsetY + "px";
            _tileLayers[_tileLayerIDs[i]].container.style.left = -_offsetX + "px"
        }
        _customDiv.style.top = -_offsetY + "px";
        _customDiv.style.left = -_offsetX + "px";
        _panning = manualStop;
        var fnUpdateMap = function() {
            _UpdateMap(manualStop)
        };
        _iTimeoutIDForUpdateMap = window.setTimeout(fnUpdateMap, 1)
    }
    function _DynamicPan(deltaX, deltaY, count, keyboardPan) {
        if (_zooming) {
            return
        }
        if (!count) {
            count = -1
        }
        _panningX = deltaX;
        _panningY = deltaY;
        _panCounter = count;
        if (!deltaX && !deltaY) {
            _StopDynamicPan();
            return
        }
        _keyboardPanning = keyboardPan;
        if (!_panning) {
            _panning = true;
            _StepPan();
            _TriggerEvent("onstartdynamicpan")
        }
    }
    function _StepPan() {
        if (!_panning) {
            return
        }
        _Pan(_panningX, _panningY, true);
        if (_panCounter > 0) {
            _panCounter--
        }
        if (_panCounter != 0) {
            _iTimeoutIDForStepPan = window.setTimeout(_StepPan, 10)
        } else {
            _iTimeoutIDForStepPan = window.setTimeout(_StopDynamicPan, 10)
        }
    }
    function _StopDynamicPan() {
        if (_panMapX != null && _panMapY != null) {
            var mc = new SuperMap.IS.MapCoord(_panMapX, _panMapY);
            var pc = _curMap.MapCoordToPixel(mc, _curParam.mapScale);
            var dx = pc.x - (_originX + _offsetX + _w / 2);
            var dy = pc.y - (_originY + _offsetY + _h / 2);
            _Pan(dx, dy, true);
            _tempParam.Copy(_curParam);
            _panMapX = null;
            _panMapY = null
        }
        _ComputeCenterPoint(true);
        if (_d) {
            window.status = "_panning:" + _panning
        }
        if (_panning) {
            var viewBounds = _GetViewBounds();
            if (!viewBounds.Equals(_oldViewBounds)) {
                _TriggerEvent("onenddynamicpan");
                _TriggerEvent("onviewboundschanged");
                _TriggerEvent("onchangeview");
                _oldViewBounds = _GetViewBounds()
            }
            _tileCheckTimes = 0;
            if (_iTimeoutIDForCheckTileLoaded) {
                window.clearTimeout(_iTimeoutIDForCheckTileLoaded);
                _iTimeoutIDForCheckTileLoaded = null
            }
            _iTimeoutIDForCheckTileLoaded = setTimeout(_CheckTileLoaded, 200)
        }
        _panningX = 0;
        _panningY = 0;
        _panning = false;
        _keyboardPanning = false
    }
    function _PanToMapCoord(x, y) {
        _panMapX = x;
        _panMapY = y;
        _PanToPixelCoord(_curMap.MapCoordToPixel(new SuperMap.IS.MapCoord(x, y), _curParam.mapScale))
    }
    function _PanToCurCenter(param) {
        _PanToPixelCoord(param.pixelCenter)
    }
    function _PanToPixelCoord(pc) {
        var dx = pc.x - (_originX + _offsetX + _w / 2);
        var dy = pc.y - (_originY + _offsetY + _h / 2);
        var distance = Math.sqrt(dx * dx + dy * dy);
        if (!_animationEnabled) {
            var param = _tempParam.MakeCopy();
            param.SetPixelCenter(pc);
            _SetMapParam(param);
            return
        }
        var dt = Math.atan2(dy, dx);
        var count = _Ceil(distance / _panStepDistance);
        var actualStepDistance = _Round(distance / count);
        dx = _Round(Math.cos(dt) * actualStepDistance);
        dy = _Round(Math.sin(dt) * actualStepDistance);
        _DynamicPan(dx, dy, count)
    }
    function _CreateTile(x, y, ms, tileLayer) {
        var tile = new MapTile(tileLayer);
        tile.Init(x, y, ms, (x * tileLayer.tileWidth - tileLayer.originX), (y * tileLayer.tileHeight - tileLayer.originY));
        return tile
    }
    function _ClearTiles(tcs) {
        while (tcs.length > 0) {
            var ts = tcs.pop();
            if (ts == null) {
                continue
            }
            while (ts.length > 0) {
                var tile = ts.pop();
                if (tile != null) {
                    tile.Destroy();
                    tile = null
                }
            }
        }
    }
    function MapTile(tileLayer) {
        var _image = null;
        var _tempImage = null;
        var _overlay = document.createElement("div");
        _overlay.id = "_overlay.unInited";
        var _tx = 0;
        var _ty = 0;
        var _ms = 0;
        var _zIndex = 0;
        var n = _zoomTotalSteps + 1;
        var xs = new Array(n);
        var ys = new Array(n);
        var ws = new Array(n);
        var hs = new Array(n);
        var _factorable = false;
        var _cx = 0,
        _cy = 0,
        _cw = 0,
        _ch = 0;
        var _nx = 0,
        _ny = 0,
        _nw = 0,
        _nh = 0;
        var _initTime = null;
        var _tileSelf = this;
        this.Init = function(tileX, tileY, mapScale, x, y) {
            _tx = tileX;
            _ty = tileY;
            _ms = mapScale;
            _overlay.style.font = "7pt Verdana, sans-sansserif";
            _overlay.style.color = "Red";
            _overlay.style.backgroundColor = "White";
            if (!_curMap.IsValidTile(_tx, _ty, _ms)) {
                return
            }
            _SetCurrentState(x, y, tileLayer.tileWidth, tileLayer.tileHeight);
            _SetNextState(x, y, tileLayer.tileWidth, tileLayer.tileHeight);
            _PrecomputeSteps();
            var imageId = tileLayer.GetTileID(_tx, _ty, _ms);
            _tileSelf.id = imageId;
            _overlay.id = "_overlay." + imageId;
            var imageBuffer = null;
            for (var i = 0; i < _tileLayerIDs.length; i++) {
                if (_tileLayerIDs[i] == tileLayer.id) {
                    imageBuffer = _imageBufferCollection[i];
                    break
                }
            }
            if (imageBuffer && imageBuffer[imageId]) {
                _image = imageBuffer[imageId];
                _image.onmousedown = function(e) {
                    return false
                };
                if (!_zooming) {
                    _SetFactor(_zoomCounter)
                }
                _tileSelf.Loaded = true
            } else {
                _tempImage = new Image(tileLayer.tileWidth, tileLayer.tileHeight);
                _tempImage.id = imageId;
                _tempImage.onload = _ImgLoad;
                _tempImage.onerror = _ImgError;
                if (tileLayer.opacity != 1) {
                    _tempImage.style.filter = "alpha(opacity=" + tileLayer.opacity * 100 + ")";
                    _tempImage.style.opacity = tileLayer.opacity
                }
                _initTime = new Date();
                if (_isIE6 && _fixPngTransparentForIe6) {
                    var src = tileLayer.GetTileUrl(_tx, _ty, _ms);
                    _tempImage.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + decodeURI(src) + "', sizingMethod='scale')";
                    _tempImage.src = _scriptLocation + "../images/spacer.gif"
                } else {
                    _tempImage.src = tileLayer.GetTileUrl(_tx, _ty, _ms)
                }
            }
        };
        function _RefreshUrl() {
            _initTime = new Date();
            var image = document.getElementById(tileLayer.GetTileID(_tx, _ty, _ms));
            var src = tileLayer.GetTileUrl(_tx, _ty, _ms);
            if (src.indexOf("?") == -1) {
                src += "?refreshTime=" + _initTime.getTime()
            } else {
                src += "&refreshTime=" + _initTime.getTime()
            }
            if (image) {
                if (_isIE6 && _fixPngTransparentForIe6) {
                    image.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + decodeURI(src) + "', sizingMethod='scale')";
                    image.src = _scriptLocation + "../images/spacer.gif"
                } else {
                    image.src = src
                }
            } else {
                if (_tempImage) {
                    if (_isIE6 && _fixPngTransparentForIe6) {
                        _tempImage.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + decodeURI(src) + "', sizingMethod='scale')";
                        _tempImage.src = _scriptLocation + "../images/spacer.gif"
                    } else {
                        _tempImage.src = src
                    }
                }
            }
        }
        this.Destroy = function() {
            if (_image) {
                _image.onmousedown = null;
                if (!tileLayer.useImageBuffer) {
                    var is = _image.style;
                    is.left = xs[0] + "px";
                    is.top = ys[0] + "px";
                    is.width = ws[0] + "px";
                    is.height = hs[0] + "px"
                }
            }
            _RemoveFromMap();
            while (xs.length > 0) {
                xs.pop()
            }
            while (ys.length > 0) {
                ys.pop()
            }
            while (ws.length > 0) {
                ws.pop()
            }
            while (hs.length > 0) {
                hs.pop()
            }
            xs = ys = ws = hs = null;
            _tileSelf = null
        };
        function _SetCurrentState(x, y, width, height) {
            _cx = x;
            _cy = y;
            _cw = width;
            _ch = height
        }
        function _SetNextState(x, y, width, height) {
            _nx = x;
            _ny = y;
            _nw = width;
            _nh = height
        }
        function _ClearSteps() {
            for (var i = 0; i <= _zoomTotalSteps; i++) {
                xs[i] = _cx;
                ys[i] = _cy;
                ws[i] = _cw;
                hs[i] = _ch
            }
        }
        function _PrecomputeSteps() {
            for (var i = 0; i <= _zoomTotalSteps; i++) {
                var a = i / _zoomTotalSteps;
                var b = 1 - a;
                xs[i] = _Floor(b * _cx + a * _nx);
                ys[i] = _Floor(b * _cy + a * _ny);
                ws[i] = _Ceil(b * _cw + a * _nw);
                hs[i] = _Ceil(b * _ch + a * _nh)
            }
        }
        function _SetFactor(i) {
            if (_image == null || (_zooming && !_factorable)) {
                return
            }
            var is = _image.style;
            is.left = xs[i] + "px";
            is.top = ys[i] + "px";
            is.width = ws[i] + "px";
            is.height = hs[i] + "px";
            is.zIndex = _zIndex;
            if (_image.parentNode != tileLayer.container) {
                is.position = "absolute";
                tileLayer.container.appendChild(_image)
            }
            var os = _overlay.style;
            if (_d && i == 0) {
                is.border = "1px dashed red";
                os.left = xs[i] + "px";
                os.top = ys[i] + "px"
            }
            if (_d && _overlay.parentNode != tileLayer.container) {
                _overlay.innerHTML = _image.id;
                os.position = "absolute";
                os.zIndex = (_zIndex + 1);
                tileLayer.container.appendChild(_overlay)
            }
        }
        function _SwapStates() {
            var temp = 0;
            temp = _cx;
            _cx = _nx;
            _nx = temp;
            temp = _cy;
            _cy = _ny;
            _ny = temp;
            temp = _cw;
            _cw = _nw;
            _nw = temp;
            temp = _ch;
            _ch = _nh;
            _nh = temp
        }
        function _RemoveFromMap() {
            if (!tileLayer.useImageBuffer) {
                return _RemoveFromMapTrue()
            }
            if (_preTiles.length > 36) {
                var deletingTiles = _preTiles.splice(0, 18);
                while (deletingTiles.length > 0) {
                    var deletingTile = deletingTiles.pop();
                    deletingTile.RemoveFromMapTrue();
                    deletingTile = null
                }
                deletingTiles = null
            }
            if (!_zooming) {
                while (_preTiles.length > 0) {
                    var preTile = _preTiles.pop();
                    preTile.RemoveFromMapTrue();
                    preTile = null
                }
                _RemoveFromMapTrue();
                return
            }
            if (!_image) {
                _RemoveFromMapTrue();
                return
            }
            _image.style.zIndex = -1;
            var m = _map;
            var o = this._overlay;
            m = null;
            o = null;
            _tileSelf.unused = true;
            _preTiles.push(_tileSelf);
            if (_d) {
                window.status = "preTiles:" + _preTiles.length
            }
            return;
            if (_tempImage) {
                _tempImage.onload = null;
                _tempImage.onerror = null;
                delete _tempImage;
                _tempImage = null
            }
            if (_image) {
                if (_image.parentNode == tileLayer.container) {
                    tileLayer.container.removeChild(_image)
                }
                delete _image;
                _image = null
            }
            if (_overlay) {
                if (_overlay.parentNode == tileLayer.container) {
                    tileLayer.container.removeChild(_overlay)
                }
                _overlay = null
            }
        }
        function _RemoveFromMapTrue() {
            for (var i = 0; i < _preTiles.length; i++) {
                if (_preTiles[i].id == this.id) {
                    _preTiles.splice(i, 1);
                    break
                }
            }
            if (_tempImage) {
                _tempImage.onload = null;
                _tempImage.onerror = null;
                delete _tempImage;
                _tempImage = null
            }
            if (_image) {
                if (_image.parentNode == tileLayer.container) {
                    tileLayer.container.removeChild(_image)
                }
                delete _image;
                _image = null
            }
            if (_overlay) {
                if (_overlay.parentNode == tileLayer.container) {
                    tileLayer.container.removeChild(_overlay)
                }
                _overlay = null
            }
        }
        function _ImgLoad() {
            if (_ms != _curParam.mapScale || _tempImage == null) {
                return
            }
            var index = 0;
            for (var i = 0; i < _tileLayerIDs.length; i++) {
                if (_tileLayerIDs[i] == tileLayer.id) {
                    index = i;
                    break
                }
            }
            if (_imageBufferCollection[index] == null) {
                _imageBufferCollection[index] = new Array()
            }
            if (_imageBufferCollection[index].length > _imageBufferSize) {
                for (var i = 0; i < _imageBufferSize / 3; i++) {
                    delete _imageBufferCollection[index][_imageBufferCollection[index][0]];
                    _imageBufferCollection[index].shift()
                }
            }
            _imageBufferCollection[index][_tempImage.id] = _tempImage;
            _imageBufferCollection[index].push(_tempImage.id);
            if (_d) {
                var loadedTime = new Date();
                var elapsed = loadedTime.getTime() - _initTime.getTime();
                window.status = "elapsedTime=" + elapsed
            }
            _tempImage.onload = null;
            _tempImage.onerror = null;
            _image = _tempImage;
            _image.onmousedown = function(e) {
                return false
            };
            delete _tempImage;
            _tempImage = null;
            if (!_zooming) {
                _SetFactor(_zoomCounter)
            }
            _tileSelf.Loaded = true
        }
        function _ImgError() {
            if (_ms != _curParam.mapScale || _tempImage == null) {
                return
            }
            if (_d) {
                var loadedTime = new Date();
                var elapsed = loadedTime.getTime() - _initTime.getTime();
                window.status = "elapsedTime=" + elapsed
            }
        }
        function _PrepareBaseTile(oldOriginX, oldOriginY, oldScale, newOriginX, newOriginY, newScale) {
            _SetCurrentState(_cx - _offsetX, _cy - _offsetY, _cw, _ch);
            var ratio = newScale / oldScale;
            _nx = _Floor((oldOriginX + _cx) * ratio - newOriginX);
            _ny = _Floor((oldOriginY + _cy) * ratio - newOriginY);
            _nw = _Ceil((oldOriginX + _cx + _cw) * ratio - newOriginX) - _nx;
            _nh = _Ceil((oldOriginY + _cy + _ch) * ratio - newOriginY) - _ny;
            _factorable = true;
            _PrecomputeSteps();
            _zIndex = _tile_baseZIndex;
            if (_image != null) {
                _image.style.zIndex = _zIndex
            }
        }
        function _PrepareSwapTile(oldOriginX, oldOriginY, oldScale, newOriginX, newOriginY, newScale) {
            var ratio = newScale / oldScale;
            _nx = _Floor((oldOriginX + _cx) * ratio - newOriginX);
            _ny = _Floor((oldOriginY + _cy) * ratio - newOriginY);
            _nw = _Ceil((oldOriginX + _cx + _cw) * ratio - newOriginX) - _nx;
            _nh = _Ceil((oldOriginY + _cy + _ch) * ratio - newOriginY) - _ny;
            var factorX = _Ceil(_tileCountX * 0.25);
            var factorY = _Ceil(_tileCountY * 0.25);
            _factorable = (newScale > oldScale) && (_tx < _tileX1 + factorX || _tx > _tileX2 - factorX || _ty < _tileY1 + factorY || _ty > _tileY2 - factorY);
            _factorable = false;
            _SwapStates();
            _PrecomputeSteps();
            _zIndex = _tile_swapZIndex;
            if (_image != null) {
                _image.style.zIndex = _zIndex
            }
        }
        function _Debug(enabled) {
            if (_image != null) {
                _image.style.border = enabled ? "1px dashed blue": "0px"
            }
            _overlay.style.display = enabled ? "block": "none"
        }
        this.ClearSteps = _ClearSteps;
        this.SetFactor = _SetFactor;
        this.SwapStates = _SwapStates;
        this.RemoveFromMap = _RemoveFromMap;
        this.RemoveFromMapTrue = _RemoveFromMapTrue;
        this.PrepareBaseTile = _PrepareBaseTile;
        this.PrepareSwapTile = _PrepareSwapTile;
        this.Debug = _Debug;
        this.RefreshUrl = _RefreshUrl
    }
    this.TriggerServerCompletedEvent = function(eventName, result) {
        var serverEventsJSON = "";
        var inputServerEventsInfo = document.getElementById(container.id + "_hiddenServerEventsInfo");
        if (inputServerEventsInfo) {
            serverEventsJSON = inputServerEventsInfo.value
        }
        var serverEvents = eval("(" + serverEventsJSON + ")");
        var e;
        var bCommit = false;
        switch (eventName) {
        case "DistanceMeasured":
            if (serverEvents.distanceMeasuredRegisted) {
                e = new SuperMap.IS.MeasuredEventArgs();
                e.distance = result.distance;
                bCommit = true
            }
            break;
        case "AreaMeasured":
            if (serverEvents.areaMeasuredRegisted) {
                e = new SuperMap.IS.MeasuredEventArgs();
                e.area = result.area;
                bCommit = true
            }
            break;
        case "PathFound":
            if (serverEvents.pathFoundRegisted) {
                e = new SuperMap.IS.PathFoundEventArgs(result);
                bCommit = true
            }
            break;
        case "QueryCompleted":
            if (serverEvents.queryCompletedRegisted) {
                e = new SuperMap.IS.QueryCompletedEventArgs(result);
                bCommit = true
            }
            break;
        case "ClosestFacilityFound":
            if (serverEvents.closestFacilityFoundRegisted) {
                e = new SuperMap.IS.ClosestFacilityFoundEventArgs(result);
                bCommit = true
            }
            break;
        case "CustomEvent":
            if (serverEvents.customEventRegisted) {
                e = new SuperMap.IS.CustomEventArgs(result);
                bCommit = true
            }
            break;
        default:
            break
        }
        if (bCommit) {
            var eJSON = _ToJSON(e);
            eval(container.id + "_doPostBack('" + container.id + "', '" + eventName + "|" + eJSON + "')")
        }
    };
    this.TriggerServerStartingEvent = function(eventName, e, onComplete) {
        var bCommit = false;
        var serverEventsHidden = document.getElementById(container.id + "_hiddenServerEventsInfo");
        if (serverEventsHidden) {
            try {
                var serverEventsJSON = serverEventsHidden.value;
                var serverEvents = eval("(" + serverEventsJSON + ")");
                switch (eventName) {
                case "DistanceMeasuring":
                    if (serverEvents.distanceMeasuringRegisted) {
                        bCommit = true
                    }
                    break;
                case "AreaMeasuring":
                    if (serverEvents.areaMeasuringRegisted) {
                        bCommit = true
                    }
                    break;
                case "PathFinding":
                    if (serverEvents.pathFindingRegisted) {
                        bCommit = true
                    }
                    break;
                case "Querying":
                    if (serverEvents.queryingRegisted) {
                        bCommit = true
                    }
                    break;
                case "ClosestFacilityFinding":
                    if (serverEvents.closestFacilityFindingRegisted) {
                        bCommit = true
                    }
                    break;
                default:
                    break
                }
            } catch(ex) {}
        }
        var eJSON = _ToJSON(e);
        if (bCommit) {
            eval(container.id + "_CallBack(arguments[0]+'|'+_ToJSON(arguments[1]),arguments[2],null)")
        } else {
            onComplete(eJSON)
        }
    };
    function _StartMap() {
        _ClearTiles(_tileCollections);
        _customDiv.style.top = "0px";
        _customDiv.style.left = "0px";
        for (var i = 0; i < _tileLayerIDs.length; i++) {
            var distance = _PixelToMapDistance(1, _curParam.mapScale);
            var distanceY = _PixelToMapDistance(1, _curParam.mapScale, true);
            var x = parseInt((_curParam.center.x - _tileLayers[_tileLayerIDs[i]].layerBounds.leftBottom.x) / distance);
            var y = parseInt((_tileLayers[_tileLayerIDs[i]].layerBounds.rightTop.y - _curParam.center.y) / distanceY);
            _tileLayers[_tileLayerIDs[i]].originX = _Round(x - _w / 2);
            _tileLayers[_tileLayerIDs[i]].originY = _Round(y - _h / 2)
        }
        _originX = _Round(_curParam.pixelCenter.x - _w / 2);
        _originY = _Round(_curParam.pixelCenter.y - _h / 2);
        _offsetX = 0;
        _offsetY = 0;
        for (var i = 0; i < _tileLayerIDs.length; i++) {
            var tileWidth = _tileLayers[_tileLayerIDs[i]].tileWidth;
            var tileHeight = _tileLayers[_tileLayerIDs[i]].tileHeight;
            var originX = _tileLayers[_tileLayerIDs[i]].originX;
            var originY = _tileLayers[_tileLayerIDs[i]].originY;
            _tileLayers[_tileLayerIDs[i]].container.style.top = "0px";
            _tileLayers[_tileLayerIDs[i]].container.style.left = "0px";
            _tileX1[i] = _Floor((originX - _buffer) / tileWidth);
            _tileY1[i] = _Floor((originY - _buffer) / tileHeight);
            _tileX2[i] = _Floor((originX + _w + _buffer) / tileWidth);
            _tileY2[i] = _Floor((originY + _h + _buffer) / tileHeight);
            _tileCountX[i] = _tileX2[i] - _tileX1[i] + 1;
            _tileCountY[i] = _tileY2[i] - _tileY1[i] + 1
        }
        for (var i = 0; i < _tileLayerIDs.length; i++) {
            if (i == 1 && _trackingLayerIndex < 0) {
                if (!_tileCollections[i]) {
                    _tileCollections[i] = new Array()
                }
                continue
            }
            if (i == 2 && _geometryKey == "") {
                if (!_tileCollections[i]) {
                    _tileCollections[i] = new Array()
                }
                continue
            }
            var tileLayerBounds = _tileLayers[_tileLayerIDs[i]].visibleBounds;
            var tileLayerMaxScale = _tileLayers[_tileLayerIDs[i]].maxScale;
            var tileLayerMinScale = _tileLayers[_tileLayerIDs[i]].minScale;
            var centerX = _Floor((_tileCountX[i] - 1) / 2);
            var centerY = _Floor((_tileCountY[i] - 1) / 2);
            var radius = 0;
            var flag = 0;
            var startX = 0;
            var startY = 0;
            var x = 0;
            var y = 0;
            while (centerX + radius < _tileCountX[i] || centerY + radius < _tileCountY[i]) {
                startX = centerX + radius;
                startY = centerY - radius;
                x = startX;
                y = startY;
                flag = 1;
                do {
                    if (x >= 0 && x < _tileCountX[i] && y >= 0 && y < _tileCountY[i]) {
                        var tileBounds = _GetTileBounds(x + _tileX1[i], y + _tileY1[i], _tileLayers[_tileLayerIDs[i]].tileWidth, _tileLayers[_tileLayerIDs[i]].tileHeight, _tileLayers[_tileLayerIDs[i]].layerBounds);
                        var tile = null;
                        if ((!tileLayerMaxScale || _curParam.mapScale < tileLayerMaxScale) && (!tileLayerMinScale || _curParam.mapScale > tileLayerMinScale) && (!tileLayerBounds || _IsBoundsTouchs(tileBounds, tileLayerBounds))) {
                            tile = _CreateTile(x + _tileX1[i], y + _tileY1[i], _curParam.mapScale, _tileLayers[_tileLayerIDs[i]])
                        }
                        if (!_tileCollections[i]) {
                            _tileCollections[i] = new Array()
                        }
                        var tileIndex = x + y * _tileCountX[i];
                        _tileCollections[i][tileIndex] = tile
                    }
                    if (x == centerX + radius && y == centerY + radius) {
                        flag = 0
                    } else {
                        if (x == centerX - radius && y == centerY + radius) {
                            flag = 3
                        } else {
                            if (x == centerX - radius && y == centerY - radius) {
                                flag = 2
                            } else {
                                if (x == centerX + radius && y == centerY - radius) {
                                    flag = 1
                                }
                            }
                        }
                    }
                    if (radius > 0) {
                        if (flag == 0) {
                            x--
                        } else {
                            if (flag == 1) {
                                y++
                            } else {
                                if (flag == 2) {
                                    x++
                                } else {
                                    if (flag == 3) {
                                        y--
                                    }
                                }
                            }
                        }
                    }
                }
                while (startX != x || startY != y);
                radius++
            }
        }
        if (_viewBoundsBefore) {
            _SetMapParamHidden()
        }
    }
    function _UpdateMap(manualStop) {
        if (_zooming) {
            return
        }
        for (var i = 0; i < _tileLayerIDs.length; i++) {
            if (i == 1 && _trackingLayerIndex < 0) {
                continue
            }
            if (i == 2 && _geometryKey == "") {
                continue
            }
            var tileWidth = _tileLayers[_tileLayerIDs[i]].tileWidth;
            var tileHeight = _tileLayers[_tileLayerIDs[i]].tileHeight;
            var ox = _tileLayers[_tileLayerIDs[i]].originX + _offsetX;
            var oy = _tileLayers[_tileLayerIDs[i]].originY + _offsetY;
            var _x = _Floor((ox - _buffer) / tileWidth);
            var _y = _Floor((oy - _buffer) / tileHeight);
            var _nx = _Floor((ox + _w + _buffer) / tileWidth);
            var _ny = _Floor((oy + _h + _buffer) / tileHeight);
            var tileLayerBounds = _tileLayers[_tileLayerIDs[i]].visibleBounds;
            var tileLayerMaxScale = _tileLayers[_tileLayerIDs[i]].maxScale;
            var tileLayerMinScale = _tileLayers[_tileLayerIDs[i]].minScale;
            while (_tileX1[i] < _x) {
                for (var y = _tileCountY[i] - 1; y >= 0; y--) {
                    var tileBounds = _GetTileBounds(_tileX1[i], _tileY1[i] + y, tileWidth, tileHeight, _tileLayers[_tileLayerIDs[i]].layerBounds);
                    var tile = _tileCollections[i].splice(y * _tileCountX[i], 1)[0];
                    if (tile && (!tileLayerMaxScale || _curParam.mapScale < tileLayerMaxScale) && (!tileLayerMinScale || _curParam.mapScale > tileLayerMinScale) && (!tileLayerBounds || _IsBoundsTouchs(tileBounds, tileLayerBounds))) {
                        tile.RemoveFromMap()
                    }
                }
                _tileX1[i]++;
                _tileCountX[i]--
            }
            while (_tileX1[i] > _x) {
                _tileX1[i]--;
                _tileCountX[i]++;
                for (var y = 0; y < _tileCountY[i]; y++) {
                    var tileBounds = _GetTileBounds(_tileX1[i], _tileY1[i] + y, tileWidth, tileHeight, _tileLayers[_tileLayerIDs[i]].layerBounds);
                    var tile = null;
                    if ((!tileLayerMaxScale || _curParam.mapScale < tileLayerMaxScale) && (!tileLayerMinScale || _curParam.mapScale > tileLayerMinScale) && (!tileLayerBounds || _IsBoundsTouchs(tileBounds, tileLayerBounds))) {
                        tile = _CreateTile(_tileX1[i], _tileY1[i] + y, _curParam.mapScale, _tileLayers[_tileLayerIDs[i]])
                    }
                    _tileCollections[i].splice(y * _tileCountX[i], 0, tile)
                }
            }
            while (_tileY1[i] < _y) {
                for (var x = 0; x < _tileCountX[i]; x++) {
                    var tileBounds = _GetTileBounds(_tileX1[i] + x, _tileY1[i], tileWidth, tileHeight, _tileLayers[_tileLayerIDs[i]].layerBounds);
                    var tile = _tileCollections[i].shift();
                    if (tile && (!tileLayerMaxScale || _curParam.mapScale < tileLayerMaxScale) && (!tileLayerMinScale || _curParam.mapScale > tileLayerMinScale) && (!tileLayerBounds || _IsBoundsTouchs(tileBounds, tileLayerBounds))) {
                        tile.RemoveFromMap()
                    }
                }
                _tileY1[i]++;
                _tileCountY[i]--
            }
            while (_tileY1[i] > _y) {
                _tileY1[i]--;
                _tileCountY[i]++;
                for (var x = _tileCountX[i] - 1; x >= 0; x--) {
                    var tileBounds = _GetTileBounds(_tileX1[i] + x, _tileY1[i], tileWidth, tileHeight, _tileLayers[_tileLayerIDs[i]].layerBounds);
                    var tile = null;
                    if ((!tileLayerMaxScale || _curParam.mapScale < tileLayerMaxScale) && (!tileLayerMinScale || _curParam.mapScale > tileLayerMinScale) && (!tileLayerBounds || _IsBoundsTouchs(tileBounds, tileLayerBounds))) {
                        tile = _CreateTile(_tileX1[i] + x, _tileY1[i], _curParam.mapScale, _tileLayers[_tileLayerIDs[i]])
                    }
                    _tileCollections[i].unshift(tile)
                }
            }
            while (_tileX2[i] > _nx) {
                for (var y = _tileCountY[i] - 1; y >= 0; y--) {
                    var tileBounds = _GetTileBounds(_tileX2[i], _tileY1[i] + y, tileWidth, tileHeight, _tileLayers[_tileLayerIDs[i]].layerBounds);
                    var tile = _tileCollections[i].splice(y * _tileCountX[i] + _tileCountX[i] - 1, 1)[0];
                    if (tile && (!tileLayerMaxScale || _curParam.mapScale < tileLayerMaxScale) && (!tileLayerMinScale || _curParam.mapScale > tileLayerMinScale) && (!tileLayerBounds || _IsBoundsTouchs(tileBounds, tileLayerBounds))) {
                        tile.RemoveFromMap()
                    }
                }
                _tileX2[i]--;
                _tileCountX[i]--
            }
            while (_tileX2[i] < _nx) {
                _tileX2[i]++;
                _tileCountX[i]++;
                for (var y = 0; y < _tileCountY[i]; y++) {
                    var tileBounds = _GetTileBounds(_tileX2[i], _tileY1[i] + y, tileWidth, tileHeight, _tileLayers[_tileLayerIDs[i]].layerBounds);
                    var tile = null;
                    if ((!tileLayerMaxScale || _curParam.mapScale < tileLayerMaxScale) && (!tileLayerMinScale || _curParam.mapScale > tileLayerMinScale) && (!tileLayerBounds || _IsBoundsTouchs(tileBounds, tileLayerBounds))) {
                        tile = _CreateTile(_tileX2[i], _tileY1[i] + y, _curParam.mapScale, _tileLayers[_tileLayerIDs[i]])
                    }
                    _tileCollections[i].splice(y * _tileCountX[i] + _tileCountX[i] - 1, 0, tile)
                }
            }
            while (_tileY2[i] > _ny) {
                for (var x = 0; x < _tileCountX[i]; x++) {
                    var tileBounds = _GetTileBounds(_tileX2[i] - x, _tileY2[i], tileWidth, tileHeight, _tileLayers[_tileLayerIDs[i]].layerBounds);
                    var tile = _tileCollections[i].pop();
                    if (tile && (!tileLayerMaxScale || _curParam.mapScale < tileLayerMaxScale) && (!tileLayerMinScale || _curParam.mapScale > tileLayerMinScale) && (!tileLayerBounds || _IsBoundsTouchs(tileBounds, tileLayerBounds))) {
                        tile.RemoveFromMap()
                    }
                }
                _tileY2[i]--;
                _tileCountY[i]--
            }
            while (_tileY2[i] < _ny) {
                _tileY2[i]++;
                _tileCountY[i]++;
                for (var x = 0; x < _tileCountX[i]; x++) {
                    var tileBounds = _GetTileBounds(_tileX1[i] + x, _tileY2[i], tileWidth, tileHeight, _tileLayers[_tileLayerIDs[i]].layerBounds);
                    var tile = null;
                    if ((!tileLayerMaxScale || _curParam.mapScale < tileLayerMaxScale) && (!tileLayerMinScale || _curParam.mapScale > tileLayerMinScale) && (!tileLayerBounds || _IsBoundsTouchs(tileBounds, tileLayerBounds))) {
                        tile = _CreateTile(_tileX1[i] + x, _tileY2[i], _curParam.mapScale, _tileLayers[_tileLayerIDs[i]])
                    }
                    _tileCollections[i].push(tile)
                }
            }
        }
        _SetMapParamHidden();
        if (!manualStop) {
            var viewBounds = _GetViewBounds();
            if (!viewBounds.Equals(_oldViewBounds)) {
                _TriggerEvent("onviewboundschanged");
                _TriggerEvent("onchangeview");
                _oldViewBounds = _GetViewBounds()
            }
        }
    }
    function _SetMapParam(param) {
        if (_zooming || _panning || _dragging) {
            return
        }
        var center = param.center;
        if (param.GetViewType() == "mr") {
            center = param.mapRect.Center()
        }
        _curMap = _map;
        _viewBoundsBefore = _GetViewBounds();
        container.style.backgroundColor = "#eeeeee";
        param.Resolve(_curMap, _w, _h);
        center = param.center;
        _tempParam.Copy(param);
        _curMap.ValidateMapScale(param);
        if (_customBounds != null) {
            _curBounds = _customBounds
        } else {
            _curBounds = _curMap.GetBounds()
        }
        _EnsureWithinBounds(param, _curBounds);
        param.Resolve(_curMap, _w, _h);
        var deltaX = param.GetPixelX(_curParam.mapScale) - _curParam.pixelCenter.x;
        var deltaY = param.GetPixelY(_curParam.mapScale) - _curParam.pixelCenter.y;
        var distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        var panOnly = (distance < _w && distance < _h) && (param.mapScale == _curParam.mapScale) && _animationEnabled;
        if (panOnly && !_switching) {
            if (deltaX == 0 && deltaY == 0) {
                return
            }
            _PanToMapCoord(center.x, center.y);
            return
        }
        _mapScale = _curParam.mapScale;
        if (_curParam.mapScale != param.mapScale) {
            _curMap.mapScale = param.mapScale;
            _TriggerEvent("onstartzoom");
            _zooming = true
        }
        var scaleRatio = param.mapScale / _curParam.mapScale;
        var dynamic = (distance < _w && distance < _h) && _animationEnabled && scaleRatio > 1 / 8 && scaleRatio < 8;
        if (dynamic && !_switching) {
            var oldZoom = _curParam.mapScale;
            var newZoom = param.mapScale;
            _oldTileCollections = _tileCollections;
            _tileCollections = new Array();
            for (var i = 0; i < _oldTileCollections.length; i++) {
                var oldTiles = _oldTileCollections[i];
                var oldOriginX = _tileLayers[_tileLayerIDs[i]].originX + _offsetX;
                var oldOriginY = _tileLayers[_tileLayerIDs[i]].originY + _offsetY;
                var distance = _PixelToMapDistance(1, param.mapScale);
                var distanceY = _PixelToMapDistance(1, param.mapScale, true);
                var x = parseInt((param.center.x - _tileLayers[_tileLayerIDs[i]].layerBounds.leftBottom.x) / distance);
                var y = parseInt((_tileLayers[_tileLayerIDs[i]].layerBounds.rightTop.y - param.center.y) / distanceY);
                var newOriginX = _Round(x - _w / 2);
                var newOriginY = _Round(y - _h / 2);
                for (var j = 0; j < oldTiles.length; j++) {
                    if (oldTiles[j] != null) {
                        oldTiles[j].PrepareBaseTile(oldOriginX, oldOriginY, oldZoom, newOriginX, newOriginY, newZoom)
                    }
                }
                if (i == 0) {
                    for (var j = 0; j < _marks.length; j++) {
                        _marks[j].PrepareForZoom(newOriginX, newOriginY, newZoom)
                    }
                }
            }
            _HidePolylines();
            _curParam.Destroy();
            _curParam = param;
            _StartMap();
            for (var i = 0; i < _tileCollections.length; i++) {
                var tiles = _tileCollections[i];
                for (var j = 0; j < tiles.length; j++) {
                    if (tiles[j] != null) {
                        tiles[j].PrepareSwapTile(oldOriginX, oldOriginY, oldZoom, newOriginX, newOriginY, newZoom)
                    }
                }
            }
            _zoomCounter = 1;
            _SetFactor();
            return
        }
        _oldTileCollections = _tileCollections;
        _tileCollections = new Array();
        _curParam.Destroy();
        _curParam = param;
        _HidePolylines();
        _StartMap();
        _SwapStates();
        _RepositionMarks();
        _RepositionLines();
        _RepositionPolygons()
    }
    function _SetFactor() {
        if (!_zooming) {
            return
        }
        for (var i = 0; i < _oldTileCollections.length; i++) {
            var oldTiles = _oldTileCollections[i];
            for (var j = 0; j < oldTiles.length; j++) {
                if (oldTiles[j] != null) {
                    oldTiles[j].SetFactor(_zoomCounter)
                }
            }
        }
        for (var i = 0; i < _tileCollections.length; i++) {
            var tiles = _tileCollections[i];
            for (var j = 0; j < tiles.length; j++) {
                if (tiles[j] != null) {
                    tiles[j].SetFactor(_zoomCounter)
                }
            }
        }
        for (var i = 0; i < _marks.length; i++) {
            _marks[i].SetFactor(_zoomCounter)
        }
        if (_zoomCounter < _zoomTotalSteps) {
            _zoomCounter++;
            _iTimeoutIDForSetFactor = window.setTimeout(_SetFactor, 1)
        } else {
            _zoomCounter = 0;
            _SwapStates()
        }
    }
    function _SwapStates() {
        _ClearTiles(_oldTileCollections);
        _oldTileCollections = null;
        _zooming = false;
        for (var i = 0; i < _tileCollections.length; i++) {
            var tiles = _tileCollections[i];
            if (tiles == null) {
                continue
            }
            for (var j = 0; j < tiles.length; j++) {
                if (tiles[j] != null) {
                    tiles[j].SwapStates();
                    tiles[j].ClearSteps();
                    tiles[j].SetFactor(0)
                }
            }
        }
        for (var i = 0; i < _marks.length; i++) {
            _marks[i].SwapStates();
            _marks[i].ClearSteps();
            _marks[i].SetFactor(0)
        }
        _iTimeoutIDForShowPolylines = window.setTimeout(_ShowPolylines, 250);
        if (_mapScale != _curParam.mapScale) {
            _TriggerEvent("onendzoom")
        }
        if (!_switching) {
            var viewBounds = _GetViewBounds();
            if (!viewBounds.Equals(_oldViewBounds)) {
                _TriggerEvent("onviewboundschanged");
                _oldViewBounds = _GetViewBounds()
            }
            _TriggerEvent("onchangeview")
        } else {
            _TriggerEvent("onviewboundschanged");
            _TriggerEvent("onchangeview")
        }
        _tileCheckTimes = 0;
        if (_iTimeoutIDForCheckTileLoaded) {
            window.clearTimeout(_iTimeoutIDForCheckTileLoaded);
            _iTimeoutIDForCheckTileLoaded = null
        }
        _iTimeoutIDForCheckTileLoaded = setTimeout(_CheckTileLoaded, 200);
        try {
            CollectGarbage()
        } catch(ex) {}
    }
    this.ViewEntire = function() {
        var param = _tempParam.MakeCopy();
        var mapBounds = _GetMapBounds();
        if (_customBounds != null) {
            mapBounds = _customBounds
        }
        var viewBounds = _GetViewBounds();
		//alert("viewBounds.Width()="+viewBounds.Width()+" |mapBounds.Width()="+mapBounds.Width())
        var widthRatio = mapBounds.Width() / viewBounds.Width();
        var heightRatio = mapBounds.Height() / viewBounds.Height();
		//alert(mapBounds.Width() +"|"+widthRatio +"|"+ heightRatio)
        var ratio = widthRatio > heightRatio ? widthRatio: heightRatio;
        if (Math.abs(1 - ratio) < Math.pow(10, -13)) {
            ratio = 1
        }
        var mapScale = param.mapScale / ratio;
        param.SetMapScale(mapScale);
        param.SetMapCenter(mapBounds.Center());
        _isViewEntie = true;
        _SetMapParam(param);
        _isViewEntie = false
    };
    function _ViewByBounds(x1, y1, x2, y2) {
        var param = _tempParam.MakeCopy();
        param.SetMapRect(new SuperMap.IS.MapRect(x1, y1, x2, y2));
        _SetMapParam(param)
    }
    function _ViewByPoints(mcs) {
        if (!mcs || mcs.constructor != Array) {
            return
        }
        var a = mcs[0].x;
        var b = mcs[0].y;
        var c = a;
        var d = b;
        for (var i = 1; i < mcs.length; i++) {
            a = _Min(a, mcs[i].x);
            b = _Min(b, mcs[i].y);
            c = _Max(c, mcs[i].x);
            d = _Max(d, mcs[i].y)
        }
        var dx = (c - a) * 0.1;
        var dy = (d - b) * 0.1;
        a -= dx;
        b -= dy;
        c += dx;
        d += dy;
        _ViewByBounds(_ClipMapX(a), _ClipMapY(b), _ClipMapX(c), _ClipMapY(d))
    }
    function _ViewByPoint(x, y) {
        var mc = new SuperMap.IS.MapCoord(x, y);
        var viewBounds = _GetViewBounds();
        var oldWidth = viewBounds.Width();
        var oldHeight = viewBounds.Height();
        viewBounds.leftBottom.x += oldWidth * 0.1;
        viewBounds.leftBottom.y += oldHeight * 0.1;
        viewBounds.rightTop.x -= oldWidth * 0.1;
        viewBounds.rightTop.y -= oldHeight * 0.1;
        if (_customBounds != null) {
            if (x < _customBounds.leftBottom.x || y < _customBounds.leftBottom.y || x > _customBounds.rightTop.x || y > _customBounds.rightTop.x) {
                return
            }
        }
        if (viewBounds.Contains(mc)) {
            return
        }
        var param = _tempParam.MakeCopy();
        param.SetMapCenter(new SuperMap.IS.MapCoord(x, y));
        _SetMapParam(param)
    }
    function _SetMapScale(ms) {
        var param = _tempParam.MakeCopy();
        param.SetMapScale(ms);
        _SetMapParam(param)
    }
    function _SetZoomLevel(level) {
        var param = _tempParam.MakeCopy();
        param.SetZoomLevel(level);
        _SetMapParam(param)
    }
    function _ZoomIn() {
        var param = _tempParam.MakeCopy();
        if (param.mapScales && param.mapScales.length > 0) {
            if (_curParam.zoomLevel < param.mapScales.length) {
                param.SetMapScale(param.mapScales[_curParam.zoomLevel])
            } else {
                param.SetMapScale(param.mapScales[param.mapScales.length])
            }
        } else {
            param.SetMapScale(_curParam.mapScale * 2)
        }
        _zoomIn = true;
        _SetMapParam(param);
        _zoomIn = false
    }
    function _Zoom(ratio) {
        var param = _tempParam.MakeCopy();
        param.SetMapScale(_curParam.mapScale * ratio);
        _SetMapParam(param)
    }
    function _ZoomOut() {
        var param = _tempParam.MakeCopy();
        if (param.mapScales && param.mapScales.length > 0) {
            if (_curParam.zoomLevel > 1) {
                param.SetMapScale(param.mapScales[_curParam.zoomLevel - 2])
            } else {
                param.SetMapScale(param.mapScales[0])
            }
        } else {
            param.SetMapScale(_curParam.mapScale / 2)
        }
        _zoomOut = true;
        _SetMapParam(param);
        _zoomOut = false
    }
    function _SetCenterAndZoom(x, y, ms) {
        var param = _tempParam.MakeCopy();
        var position = new SuperMap.IS.MapCoord(x, y);
        param.SetMapCenter(position);
        param.SetMapScale(ms);
        _SetMapParam(param)
    }
    function _ClipMapX(x) {
        return _Clip(x, mapBounds.leftBottom.x, mapBounds.rightTop.x)
    }
    function _ClipMapY(y) {
        return _Clip(y, mapBounds.leftBottom.y, mapBounds.rightTop.y)
    }
    function _Clip(n, minValue, maxValue) {
        if (n < minValue) {
            return minValue
        }
        if (n > maxValue) {
            return maxValue
        }
        return n
    }
    function _GetMapParam() {
        return _curParam.MakeCopy()
    }
    function _ZoomInByFixure(e) {
        var param = _tempParam.MakeCopy();
        if (param.mapScales && param.mapScales.length > 0) {
            if (_curParam.zoomLevel < param.mapScales.length) {
                param.SetMapScale(param.mapScales[_curParam.zoomLevel])
            } else {
                param.SetMapScale(param.mapScales[param.mapScales.length])
            }
        } else {
            param.SetMapScale(_curParam.mapScale * 2)
        }
        var cp = _MapCoordToPixel(param.center);
        var dx = _PixelToMapDistance(cp.x - e.x, param.mapScale);
        var dy = _PixelToMapDistance(cp.y - e.y, param.mapScale);
        var emc = _PixelToMapCoord(e);
        param.center.x = emc.x + dx;
        param.center.y = emc.y - dy;
        _SetCenterAndZoom(param.center.x, param.center.y, param.mapScale)
    }
    function _ZoomOutByFixure(e) {
        var param = _tempParam.MakeCopy();
        if (param.mapScales && param.mapScales.length > 0) {
            if (_curParam.zoomLevel > 1) {
                param.SetMapScale(param.mapScales[_curParam.zoomLevel - 2])
            } else {
                param.SetMapScale(param.mapScales[0])
            }
        } else {
            param.SetMapScale(_curParam.mapScale / 2)
        }
        var cp = _MapCoordToPixel(param.center);
        var dx = _PixelToMapDistance(cp.x - e.x, param.mapScale);
        var dy = _PixelToMapDistance(cp.y - e.y, param.mapScale);
        var emc = _PixelToMapCoord(e);
        param.center.x = emc.x + dx;
        param.center.y = emc.y - dy;
        _SetCenterAndZoom(param.center.x, param.center.y, param.mapScale)
    }
    function _GetOriginX() {
        return _originX
    }
    function _GetOriginY() {
        return _originY
    }
    function _GetOffsetX() {
        return _offsetX
    }
    function _GetOffsetY() {
        return _offsetY
    }
    function _GetContainerX() {
        return _x
    }
    function _GetContainerY() {
        return _y
    }
    function _SetAction(action) {
        if (_curAction && (_curAction != _zoomInAction && _curAction != _panAction)) {
            _curAction.Destroy()
        }
        if (action) {
            action.Init(_self)
        }
        _curAction = action;
        _SetClientActionHidden(_curAction)
    }
    function _SetClientActionHidden(action) {
        var hiddenLayer = document.getElementById(container.id + "_hiddenClientAction");
        if (!hiddenLayer) {
            return
        }
        if (action == null) {
            hiddenLayer.value = ""
        } else {
            hiddenLayer.value = action.GetJSON()
        }
    }
    function _GetAction() {
        return _curAction
    }
    function _SelectGroup(groupID, curObject) {
        if (!groupID) {
            groupID = "unClassified"
        }
        groupID = _self.id + "_" + groupID;
        var groupDiv = document.getElementById(groupID);
        if (!groupDiv) {
            groupDiv = document.createElement("div");
            groupDiv.id = groupID;
            _customDiv.appendChild(groupDiv)
        }
        if (curObject.parentNode) {
            curObject.parentNode.removeChild(curObject)
        }
        groupDiv.appendChild(curObject)
    }
    function _SetGroupVisible(groupID, visible) {
        groupID = _self.id + "_" + groupID;
        var group = document.getElementById(groupID);
        if (!group) {
            return
        }
        group.style.visibility = visible ? "": "hidden"
    }
    function _SetGroupZindex(groupID, zIndex) {
        groupID = _self.id + "_" + groupID;
        var group = document.getElementById(groupID);
        if (group) {
            group.style.zIndex = zIndex
        }
    }
    function _OnTrackingLayerLoad() {
        this.style.visibility = "visible"
    }
    function _AddGeometry(id, geometry, style, zIndex, onClick, onDblClick, onMouseOver, onMouseOut) {
        for (var i = 0; i < _geometryIDs.length; i++) {
            if (_geometryIDs[i] == id) {
                return false
            }
        }
        _geometryIDs.push(id);
        _geometries.push(geometry);
        _geometryStyles.push(style);
        _geometryIndexs.push(zIndex);
        _geometryClicks.push(onClick);
        _geometryDblclicks.push(onDblClick);
        _geometryMouseOvers.push(onMouseOver);
        _geometryMouseOuts.push(onMouseOut);
        return true
    }
    function _AddGeometries(ids, geometries, style, zIndex, onClick, onDblClick, onMouseOver, onMouseOut) {
        if (!ids || !geometries || !ids.length || !geometries.length) {
            return false
        }
        if (ids.length != geometries.length) {
            return false
        }
        var flag = true;
        for (var i = 0; i < ids.length; i++) {
            flag &= _AddGeometry(ids[i], geometries[i], style, zIndex, onClick, onDblClick, onMouseOver, onMouseOut)
        }
        return flag
    }
    function _InsertGeometry(id, geometry, style, zIndex, onClick, onDblClick, onMouseOver, onMouseOut) {
        for (var i = 0; i < _geometryIDs.length; i++) {
            if (_geometryIDs[i] == id) {
                _geometryIDs[i] = id;
                _geometries[i] = geometry;
                _geometryStyles[i] = style;
                _geometryIndexs[i] = zIndex;
                _geometryClicks[i] = onClick;
                _geometryDblclicks[i] = onDblClick;
                _geometryMouseOvers[i] = onMouseOver;
                _geometryMouseOuts[i] = onMouseOut;
                return true
            }
        }
        _geometryIDs.push(id);
        _geometries.push(geometry);
        _geometryStyles.push(style);
        _geometryIndexs.push(zIndex);
        _geometryClicks.push(onClick);
        _geometryDblclicks.push(onDblClick);
        _geometryMouseOvers.push(onMouseOver);
        _geometryMouseOuts.push(onMouseOut);
        return true
    }
    function _InsertGeometries(ids, geometries, style, zIndex, onClick, onDblClick, onMouseOver, onMouseOut) {
        if (!ids || !geometries || !ids.length || !geometries.length) {
            return false
        }
        if (ids.length != geometries.length) {
            return false
        }
        var flag = true;
        for (var i = 0; i < ids.length; i++) {
            flag &= _InsertGeometry(ids[i], geometries[i], style, zIndex, onClick, onDblClick, onMouseOver, onMouseOut)
        }
        return flag
    }
    function _RemoveGeometry(id) {
        for (var i = 0; i < _geometryIDs.length; i++) {
            if (_geometryIDs[i] == id) {
                _geometryIDs.splice(i, 1);
                _geometries.splice(i, 1);
                _geometryStyles.splice(i, 1);
                _geometryIndexs.splice(i, 1);
                _geometryClicks.splice(i, 1);
                _geometryDblclicks.splice(i, 1);
                _geometryMouseOvers.splice(i, 1);
                _geometryMouseOuts.splice(i, 1);
                return true
            }
        }
        return false
    }
    function _RemoveGeometries(ids) {
        if (!ids || !ids.length) {
            return false
        }
        var flag = true;
        for (var i = 0; i < ids.length; i++) {
            flag &= _RemoveGeometry(ids[i])
        }
        return flag
    }
    function _ClearGeometries() {
        while (_geometryIDs.length > 0) {
            _geometryIDs.pop();
            var geo = _geometries.pop();
            if (geo) {
                geo.Destroy();
                geo = null
            }
            var style = _geometryStyles.pop();
            if (style) {
                style.Destroy();
                style = null
            }
            _geometryIndexs.pop();
            _geometryClicks.pop();
            _geometryDblclicks.pop();
            _geometryMouseOvers.pop();
            _geometryMouseOuts.pop()
        }
        return true
    }
    function _GetGeometry(id) {
        for (var i = 0; i < _geometryIDs.length; i++) {
            if (_geometryIDs[i] == id) {
                return _geometries[i]
            }
        }
        return null
    }
    function _GetGeometries(ids) {
        var geometries = new Array();
        for (var i = 0; i < ids.length; i++) {
            geometries.push(_GetGeometry(ids[i]))
        }
        return geometries
    }
    function _SetDefaultGeometryStyle(defaultStyle) {
        _defaultGeometryStyle = defaultStyle
    }
    function _SetGeometryTolerance(tolerance) {
        _geometryTolerance = tolerance
    }
    function _SetCalculateInClient(calculateInClient) {
        _calculateInClient = calculateInClient
    }
    function _UpdateGeometries(onComplete, onError, userContext) {
        var queryUrl = _mapHandler + "common.ashx";
        var mapName = _mapName;
        function onUpdateGeometryComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            while (_lastGeometryIDs.length > 0) {
                _lastGeometryIDs.pop()
            }
            _geometryKey = eval("(" + responseText + ")");
            _RefreshGeometryLayer();
            if (onComplete) {
                onComplete(_geometryKey, userContext)
            }
        }
        var reuqestManager = new SuperMap.IS.RequestManager(queryUrl, onUpdateGeometryComplete, onError, userContext);
        reuqestManager.AddQueryString("map", mapName);
        reuqestManager.AddQueryString("method", "UpdateGeometry");
        reuqestManager.AddQueryString("geometryIDs", _ToJSON(_geometryIDs));
        reuqestManager.AddQueryString("geometries", _ToJSON(_geometries));
        reuqestManager.AddQueryString("geometrystyles", _ToJSON(_geometryStyles));
        reuqestManager.AddQueryString("geometryIndexs", _ToJSON(_geometryIndexs));
        reuqestManager.AddQueryString("defaultStyle", _ToJSON(_defaultGeometryStyle));
        reuqestManager.Send();
        reuqestManager.Destroy();
        reuqestManager = null
    }
    function _QueryGeomtryByPoint(position, tolerance, calculateInClient, onComplete, onError, userContext) {
        if (!_geometryKey) {
            return
        }
        var distance = _PixelToMapDistance(tolerance, _curParam.mapScale);
        if (calculateInClient) {
            var ids = new Array();
            for (var i = 0; i < _geometries.length; i++) {
                if (isPointInGeometry(position, _geometries[i], distance)) {
                    ids.push(_geometryIDs[i])
                }
            }
            if (onComplete) {
                onComplete(ids, userContext)
            }
        } else {
            var queryUrl = _mapHandler + "common.ashx";
            var mapName = _mapName;
            function onQueryGeometryComplete(responseText) {
                if (!responseText) {
                    if (onComplete) {
                        onComplete(null, userContext)
                    }
                    return
                }
                var ids = eval("(" + responseText + ")");
                if (onComplete) {
                    onComplete(ids, userContext)
                }
            }
            var reuqestManager = new SuperMap.IS.RequestManager(queryUrl, onQueryGeometryComplete, onError, userContext);
            reuqestManager.AddQueryString("map", mapName);
            reuqestManager.AddQueryString("method", "QueryGeomtryByPoint");
            reuqestManager.AddQueryString("geometryKey", _geometryKey);
            reuqestManager.AddQueryString("position", _ToJSON(position));
            reuqestManager.AddQueryString("tolerance", distance);
            reuqestManager.Send();
            reuqestManager.Destroy();
            reuqestManager = null
        }
    }
    function _AddMark(id, x, y, w, h, innerHtml, className, zIndex, groupID, alignStyle) {
        var mk = new Mark();
        mk.Init(id, x, y, w, h, innerHtml, className, zIndex, groupID, alignStyle);
        _marks.push(mk);
        _SetMarksHidden();
        return mk.div
    }
    function _InsertMark(id, x, y, w, h, innerHtml, className, zIndex, groupID, alignStyle) {
        for (var i = 0; i < _marks.length; i++) {
            var mk = _marks[i];
            if (mk.id == id) {
                mk.Init(id, x, y, w, h, innerHtml, className, zIndex, groupID, alignStyle);
                _SetMarksHidden();
                return mk.div
            }
        }
        return _AddMark(id, x, y, w, h, innerHtml, className, zIndex, groupID, alignStyle)
    }
    function _RemoveMark(id) {
        for (var i = 0; i < _marks.length; i++) {
            var mk = _marks[i];
            if (mk.id == id) {
                _marks.splice(i, 1);
                mk.Destroy();
                mk = null;
                _SetMarksHidden();
                return
            }
        }
    }
    function _ClearMarks() {
        while (_marks.length > 0) {
            var mk = _marks.pop();
            mk.Destroy();
            mk = null
        }
        _SetMarksHidden()
    }
    function _RepositionMarks() {
        for (var i = 0; i < _marks.length; i++) {
            _marks[i].Reposition()
        }
    }
    function Mark() {
        var div = document.createElement("div");
        div.mk = this;
        this.div = div;
        var _cx = 0,
        _cy = 0,
        _nx = 0,
        _ny = 0;
        var mc = new SuperMap.IS.MapCoord(0, 0);
        var width = 0;
        var height = 0;
        var n = _zoomTotalSteps + 1;
        var xs = new Array(n);
        var ys = new Array(n);
        this.Init = function(id, x, y, w, h, innerHtml, className, zIndex, groupID, alignStyle) {
            this.id = id;
            div.id = id;
            div.className = className;
            div.style.position = "absolute";
            div.innerHTML = innerHtml;
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
            this.innerHtml = innerHtml;
            this.className = className;
            this.zIndex = zIndex;
            this.groupID = groupID;
            this.alignStyle = alignStyle;
            if (!zIndex) {
                div.style.zIndex = _customLayer_baseZIndex
            } else {
                div.style.zIndex = zIndex
            }
            if (!params.fixedView) {
                div.detachEvent("onmousedown", _EmptyFunction);
                div.detachEvent("ondblclick", _MouseDoubleClick);
                div.detachEvent("onmousewheel", _MouseWheel);
                div.attachEvent("onmousedown", _EmptyFunction);
                div.attachEvent("ondblclick", _MouseDoubleClick);
                div.attachEvent("onmousewheel", _MouseWheel)
            }
            mc.x = x;
            mc.y = y;
            width = w;
            height = h;
            var pc = _curMap.MapCoordToPixel(mc, _curParam.mapScale);
            if (pc) {
                _cx = _Round(pc.x - _originX);
                _cy = _Round(pc.y - _originY);
                _nx = _cx;
                _ny = _cy;
                _PrecomputeSteps();
                _SetFactor(0);
                div.style.display = "block"
            } else {
                div.style.display = "none"
            }
            _RemoveFromMap();
            _SelectGroup(groupID, this.div);
            _AdjustCustomMarkPosition(id, alignStyle)
        };
        this.Destroy = function() {
            div.detachEvent("onmousedown", _EmptyFunction);
            div.detachEvent("ondblclick", _MouseDoubleClick);
            div.detachEvent("onmousewheel", _MouseWheel);
            div.mk = null;
            while (div.childNodes.length > 0) {
                div.removeChild(div.childNodes[0])
            }
            div.innerHtml = null;
            _RemoveFromMap();
            div = null;
            this.div = null;
            while (xs.length > 0) {
                xs.pop()
            }
            xs = null;
            while (ys.length > 0) {
                ys.pop()
            }
            ys = null
        };
        this.GetMapCoordX = function() {
            return mc.x
        };
        this.GetMapCoordY = function() {
            return mc.y
        };
        function _ClearSteps() {
            var n = _zoomTotalSteps;
            for (var i = 0; i <= n; i++) {
                xs[i] = _cx - width / 2;
                ys[i] = _cy - height / 2
            }
        }
        function _PrecomputeSteps() {
            var n = _zoomTotalSteps;
            for (var i = 0; i <= n; i++) {
                var a = i / n;
                var b = 1 - a;
                xs[i] = _Floor(b * _cx + a * _nx - width / 2);
                ys[i] = _Floor(b * _cy + a * _ny - height / 2)
            }
        }
        function _SetFactor(i) {
            div.style.left = xs[i] + "px";
            div.style.top = ys[i] + "px";
            _AdjustCustomMarkPosition(div.mk.id, div.mk.alignStyle)
        }
        function _SwapStates() {
            var temp = 0;
            temp = _cx;
            _cx = _nx;
            _nx = temp;
            temp = _cy;
            _cy = _ny;
            _ny = temp
        }
        function _Reposition() {
            var pc = _curMap.MapCoordToPixel(mc, _curParam.mapScale);
            if (pc) {
                _cx = _Round(pc.x - _originX);
                _cy = _Round(pc.y - _originY);
                _ClearSteps();
                _SetFactor(0);
                div.style.display = "block"
            } else {
                div.style.display = "none"
            }
        }
        function _PrepareForZoom(newOriginX, newOriginY, newZoom) {
            _cx -= _offsetX;
            _cy -= _offsetY;
            var pc = _curMap.MapCoordToPixel(mc, newZoom);
            if (pc) {
                _nx = _Round(pc.x - newOriginX);
                _ny = _Round(pc.y - newOriginY);
                _PrecomputeSteps();
                div.style.display = "block"
            } else {
                div.style.display = "none"
            }
        }
        function _RemoveFromMap() {
            if (div.parentNode != null) {
                div.parentNode.removeChild(div)
            }
        }
        function _MouseDoubleClick(e) {
            e = _GetEvent(e);
            _CancelBubble(e);
            if (_panning || _zooming) {
                return false
            }
            var param = _tempParam.MakeCopy();
            param.SetMapCenter(mc);
            if (!e.altKey) {
                param.SetMapScale(_curParam.mapScale * 2)
            } else {
                param.SetMapScale(_curParam.mapScale / 2)
            }
            _SetMapParam(param);
            return false
        }
        function _MouseWheel(e) {
            e = _GetEvent(e);
            _CancelBubble(e);
            if (_panning || _zooming) {
                return false
            }
            var delta = _GetMouseScrollDelta(e);
            if (delta > 0) {
                if (!_self.wheelZoomByMouse) {
                    _ZoomIn()
                } else {
                    _ZoomInByFixure(e)
                }
            } else {
                if (delta < 0) {
                    if (!_self.wheelZoomByMouse) {
                        _ZoomOut()
                    } else {
                        _ZoomOutByFixure(e)
                    }
                }
            }
            return false
        }
        this.ClearSteps = _ClearSteps;
        this.SetFactor = _SetFactor;
        this.SwapStates = _SwapStates;
        this.Reposition = _Reposition;
        this.PrepareForZoom = _PrepareForZoom;
        this.RemoveFromMap = _RemoveFromMap
    }
function _AddLine(id,xs,ys,strokeWeight,strokeColor,opacity,zIndex,groupID,parts)
{
    if(id==null||xs==null||ys==null){return null}
    var line=new Shape(false);line.Init(id,xs,ys,strokeWeight,strokeColor,null,opacity,zIndex,groupID,parts);_lines.push(line);_SetLinesHidden();
    return line
}
function _AddLineArrow(id,xs,ys,strokeWeight,strokeColor,opacity,zIndex,groupID,parts)
{
    if(id==null||xs==null||ys==null){return null}
    var line=new Shape(false,true);line.Init(id,xs,ys,strokeWeight,strokeColor,null,opacity,zIndex,groupID,parts);_lines.push(line);_SetLinesHidden();
    return line
}
function _InsertLine(id,xs,ys,strokeWeight,strokeColor,opacity,zIndex,groupID,parts)
{
    if(id==null||xs==null||ys==null){return null}
    for(var i=0;i<_lines.length;i++)
    {
        var line=_lines[i];
        if(line.id==id)
        {
            line.Init(id,xs,ys,strokeWeight,strokeColor,null,opacity,zIndex,groupID,parts);
            _SetLinesHidden();
            return line
        }
    }
    return _AddLine(id,xs,ys,strokeWeight,strokeColor,opacity,zIndex,groupID,parts)
}
function _InsertLineArrow(id,xs,ys,strokeWeight,strokeColor,opacity,zIndex,groupID,parts)
{
    if(id==null||xs==null||ys==null){return null}
    for(var i=0;i<_lines.length;i++)
    {
        var line=_lines[i];
        if(line.id==id)
        {
            line.Init(id,xs,ys,strokeWeight,strokeColor,null,opacity,zIndex,groupID,parts);
            _SetLinesHidden();
            return line
        }
    }
    return _AddLineArrow(id,xs,ys,strokeWeight,strokeColor,opacity,zIndex,groupID,parts)
}
function _RemoveLine(id) {
    for (var i = 0; i < _lines.length; i++) {
        var line = _lines[i];
        if (line.id == id) {
            _lines.splice(i, 1);
            line.Destroy();
            _SetLinesHidden();
            return
        }
    }
}
function _ClearLines() {
    while (_lines.length > 0) {
        _lines.pop().Destroy()
    }
    _SetLinesHidden()
}
function _RepositionLines() {
    for (var i = 0; i < _lines.length; i++) {
        _lines[i].Reposition()
    }
}
function _RepositionPolygons() {
    for (var i = 0; i < _polygons.length; i++) {
        _polygons[i].Reposition()
    }
}
function _AddPolygon(id, xs, ys, strokeWeight, strokeColor, fillColor, fillOpacity, zIndex, groupID, parts) {
    if (id == null || xs == null || ys == null) {
        return null
    }
    var polygon = new Shape(true);
    polygon.Init(id, xs, ys, strokeWeight, strokeColor, fillColor, fillOpacity, zIndex, groupID, parts);
    _polygons.push(polygon);
    _SetPolygonsHidden();
    return polygon
}
function _InsertPolygon(id, xs, ys, strokeWeight, strokeColor, fillColor, fillOpacity, zIndex, groupID, parts) {
    if (id == null || xs == null || ys == null) {
        return null
    }
    var polygon = null;
    for (var i = 0; i < _polygons.length; i++) {
        polygon = _polygons[i];
        if (polygon.id == id) {
            polygon.Init(id, xs, ys, strokeWeight, strokeColor, fillColor, fillOpacity, zIndex, groupID, parts);
            _SetPolygonsHidden();
            return polygon
        }
    }
    polygon = null;
    return _AddPolygon(id, xs, ys, strokeWeight, strokeColor, fillColor, fillOpacity, zIndex, groupID, parts)
}
function _RemovePolygon(id) {
    for (var i = 0; _polygons && i < _polygons.length; i++) {
        var polygon = _polygons[i];
        if (polygon.id == id) {
            _polygons.splice(i, 1);
            polygon.Destroy();
            _SetPolygonsHidden();
            return
        }
    }
}
function _ClearPolygons() {
    while (_polygons.length > 0) {
        _polygons.pop().Destroy()
    }
    _SetPolygonsHidden()
}
function Shape(closed, Isarrow) {
    if (!closed) {
        closed = false
    }
    var m_isPolygon = closed;
    var m_defaultStrokeWeight = "3pt";
    var m_defaultStrokeColor = "#316AC5";
    var m_defaultFillColor = "#316AC5";
    var m_defaultFillOpacity = "0.6";
    var m_visible = true;
    var m_shape = null;
    var m_drawLayer = null;
    var m_jg = null;
    var m_fill = null;
    var m_stroke = null;
    var m_shapeId = "";
    var m_strokeWeight = "";
    var m_strokeColor = "";
    var m_fillColor = "";
    var m_fillOpacity = "";
    var m_zIndex = 0;
    var m_groupID = "";
    var m_startX = 0;
    var m_startY = 0;
    var m_endX = 0;
    var m_endY = 0;
    var m_arrayX = null;
    var m_arrayY = null;
    var m_parts = null;
    var m_tempParts = null;
    function _Init(id, xs, ys, strokeWeight, strokeColor, fillColor, fillOpacity, zIndex, groupID, parts) {
        if (!strokeWeight) {
            strokeWeight = m_defaultStrokeWeight
        } else {
            strokeWeight += ""
        }
        if (!strokeColor) {
            strokeColor = m_defaultStrokeColor
        }
        if (!fillColor) {
            fillColor = m_defaultFillColor
        }
        if (!fillOpacity) {
            fillOpacity = m_defaultFillOpacity
        }
        if (!zIndex) {
            zIndex = _customLayer_baseZIndex
        }
        this.id = id;
        m_shapeId = id;
        if (parts == null) {
            parts = new Array();
            if (xs != null && xs.length != 0) {
                parts[0] = xs.length
            }
        }
        this.parts = parts;
        this.xs = xs;
        this.ys = ys;
        this.strokeWeight = strokeWeight;
        this.strokeColor = strokeColor;
        this.fillColor = fillColor;
        this.fillOpacity = fillOpacity;
        this.zIndex = zIndex;
        this.groupID = groupID;
        m_groupID = groupID;
        m_strokeWeight = strokeWeight;
        m_strokeColor = strokeColor;
        m_fillColor = fillColor;
        m_fillOpacity = fillOpacity;
        m_zIndex = zIndex;
        m_arrayX = xs.concat();
        m_arrayY = ys.concat();
        m_startX = m_arrayX[0];
        m_startY = m_arrayY[0];
        m_endX = m_arrayX[m_arrayX.length - 1];
        m_endY = m_arrayY[m_arrayY.length - 1];
        m_parts = new Array();
        while (m_parts.length > 0) {
            m_parts.pop()
        }
        for (var j = 0; j < parts.length; j++) {
            m_parts.push(parts[j])
        }
        _Start()
    }
    function _Destroy() {
        _RemoveFromMap();
        if (m_jg) {
            m_jg.clear()
        }
        m_parts = m_arrayX = m_arrayY = m_shape = m_jg = m_drawLayer = m_fill = m_stroke = null
    }
    function _RemoveFromMap() {
        if (m_shape && m_shape.parentNode != null) {
            m_shape.parentNode.removeChild(m_shape)
        }
        if (m_drawLayer && m_drawLayer.parentNode != null) {
            m_drawLayer.parentNode.removeChild(m_drawLayer)
        }
    }
    function _Show() {
        if (!m_visible) {
            _Hide();
            return
        }
        if (m_shape) {
            m_shape.style.display = "block"
        }
        if (m_drawLayer) {
            m_drawLayer.style.display = "block"
        }
    }
    function _Hide() {
        if (m_shape) {
            m_shape.style.display = "none"
        }
        if (m_drawLayer) {
            m_drawLayer.style.display = "none"
        }
    }
    function _SetVisible(bool) {
        m_visible = bool;
        if (!m_visible) {
            _Hide()
        }
    }
    function _Start() {
        _DrawShape()
    }
    function _Update() {
        _DrawShape()
    }
    function _DrawShape() {
        if (_useVml) {
            var ps = new Array();
            _GeneratePoints(ps);
            _CreateShape(ps);
            ps = null
        } else {
            _CreateShape()
        }
    }
    function _GeneratePoints(ps) {
        var mc = new SuperMap.IS.MapCoord();
        var pcs = new Array();
        m_tempParts = new Array();
        for (var i = 0; i < m_arrayX.length; i++) {
            mc.x = m_arrayX[i];
            mc.y = m_arrayY[i];
            var pc = _MapCoordToPixel(mc);
            pcs.push(pc)
        }
        if (!m_isPolygon) {
            _InterceptLine(pcs, ps)
        } else {
            for (var i = 0; i < pcs.length; i++) {
                ps.push((pcs[i].x + _offsetX));
                ps.push((pcs[i].y + _offsetY))
            }
        }
        while (pcs.length > 0) {
            pcs.pop()
        }
        pcs = null;
        mc = null
    }
    function _InterceptLine(pcs, ps) {
        var isParted = false;
        var pointcount = 0;
        var index = 0;
        for (var k = 0; k < m_parts.length; k++) {
            pointcount = 0;
            for (var i = 0; i < m_parts[k]; i++) {
                var pc = pcs[index + i];
                if (pc.x < _minsize || pc.x > _maxsize || pc.y < _minsize || pc.y > _maxsize) {
                    var pts = new Array();
                    if (i == 0) {
                        pts.push(i + 1)
                    } else {
                        if (i == m_parts[k] - 1) {
                            pts.push(i - 1)
                        } else {
                            pts.push(i - 1);
                            pts.push(i + 1)
                        }
                    }
                    for (var j = 0; j < pts.length; j++) {
                        var pc = _ComputeInterceptPoint(pcs[index + i], pcs[index + pts[j]]);
                        if (pc != null) {
                            ps.push((pc.x + _offsetX));
                            ps.push((pc.y + _offsetY));
                            pointcount++
                        }
                    }
                    if (pts.length == 2) {
                        isParted = true;
                        m_tempParts.push(pointcount - 1);
                        pointcount = 1
                    }
                } else {
                    ps.push((pc.x + _offsetX));
                    ps.push((pc.y + _offsetY));
                    pointcount++
                }
            }
            index = index + m_parts[k];
            if (isParted) {
                m_tempParts.push(pointcount)
            } else {
                m_tempParts = null
            }
        }
    }
    function _ComputeInterceptPoint(pc1, pc2) {
        var pc = new SuperMap.IS.PixelCoord();
        pc.x = pc1.x;
        pc.y = pc1.y;
        if (pc.x < _minsize) {
            pc.y = Math.round((pc2.y - pc1.y) * (_minsize - pc1.x) / (pc2.x - pc1.x) + pc1.y);
            pc.x = _minsize
        } else {
            if (pc.x > _maxsize) {
                pc.y = Math.round((pc2.y - pc1.y) * (_maxsize - pc1.x) / (pc2.x - pc1.x) + pc1.y);
                pc.x = _maxsize
            }
        }
        if (pc.y < _minsize) {
            pc.x = Math.round((pc2.x - pc1.x) * (_minsize - pc1.y) / (pc2.y - pc1.y) + pc1.x);
            pc.y = _minsize
        } else {
            if (pc.y > _maxsize) {
                pc.x = Math.round((pc2.x - pc1.x) * (_maxsize - pc1.y) / (pc2.y - pc1.y) + pc1.x);
                pc.y = _maxsize
            }
        }
        if (pc.x > _maxsize || pc.x < _minsize || pc.y > _maxsize || pc.y < _minsize) {
            pc = null
        }
        return pc
    }
    function _CreateShape(ps) {
        if (!_useVml) {
            if (!m_drawLayer) {
                m_drawLayer = document.createElement("div");
                m_drawLayer.id = m_shapeId;
                m_drawLayer.style.position = "absolute";
                m_drawLayer.unselectable = "on";
                _SelectGroup(m_groupID, m_drawLayer);
                m_jg = new jsGraphics(m_shapeId);
                m_jg.setColor(m_strokeColor);
                m_jg.setStroke(m_strokeWeight)
            } else {
                m_jg.clear()
            }
            m_drawLayer.style.left = _offsetX + "px";
            m_drawLayer.style.top = _offsetY + "px";
            m_drawLayer.style.zIndex = m_zIndex;
            if (m_fillOpacity != 1) {
                m_drawLayer.style.opacity = m_fillOpacity
            }
            if (m_visible) {
                m_drawLayer.style.display = "block";
                m_drawLayer.style.visibility = "visible"
            } else {
                m_drawLayer.style.display = "none";
                m_drawLayer.style.visibility = ""
            }
            var index = 0;
            for (var i = 0; i < m_parts.length; i++) {
                var pxs = new Array();
                var pys = new Array();
                var count = m_parts[i];
                var startPoint = new SuperMap.IS.PixelCoord();
                var mc = new SuperMap.IS.MapCoord();
                for (var j = 0; j < count; j++) {
                    mc.x = m_arrayX[j + index];
                    mc.y = m_arrayY[j + index];
                    var pc = _MapCoordToPixel(mc);
                    if (j == 0) {
                        startPoint = pc
                    }
                    pxs.push(pc.x);
                    pys.push(pc.y)
                }
                if (m_isPolygon) {
                    pxs.push(startPoint.x);
                    pys.push(startPoint.y);
                    m_jg.setColor(m_fillColor);
                    m_jg.fillPolygon(pxs, pys);
                    m_jg.setColor(m_strokeColor);
                    m_jg.drawPolygon(pxs, pys)
                } else {
                    m_jg.setColor(m_strokeColor);
                    m_jg.drawPolyline(pxs, pys)
                }
                mc = null;
                startPoint = null;
                index = index + count
            }
            index = 0;
            m_jg.paint();
            return false
        }
        var created = false;
        if (m_shape) {
            created = true
        }
        var index = 0;
        var starts = new Array();
        var lines = new Array();
        var pathStr = "";
        if (!m_tempParts || m_tempParts.length <= 0) {
            m_tempParts = m_parts
        }
        for (var i = 0; i < m_tempParts.length; i++) {
            starts[i] = "m" + ps[index] + "," + ps[index + 1];
            lines[i] = "l" + ps.slice(index + 2, (index + m_tempParts[i] * 2)).join(",");
            if (m_isPolygon) {
                pathStr = pathStr + (starts[i] + " " + lines[i] + " x ")
            } else {
                pathStr = pathStr + (starts[i] + " " + lines[i] + " e ")
            }
            index = index + m_tempParts[i] * 2
        }
        starts = null;
        lines = null;
        if (!created) {
            m_shape = document.createElement("v:shape");
            m_shape.setAttribute("path", pathStr)
        } else {
            m_shape.path.value = pathStr
        }
        m_shape.coordsize = (container.clientWidth) + "," + (container.clientHeight);
        m_shape.id = m_shapeId;
        if (m_isPolygon) {
            m_shape.filled = "true"
        } else {
            m_shape.filled = "false"
        }
        m_shape.style.zIndex = m_zIndex;
        m_shape.unselectable = "on";
        m_shape.style.position = "absolute";
        m_shape.style.border = "0px";
        m_shape.style.width = container.clientWidth;
        m_shape.style.height = container.clientHeight;
        if (m_visible) {
            m_shape.style.display = "block"
        } else {
            m_shape.style.display = "none"
        }
        if (m_isPolygon) {
            if (!created) {
                m_fill = document.createElement("v:fill");
                m_shape.appendChild(m_fill)
            }
            if (m_fillOpacity != 1) {
                m_fill.setAttribute("opacity", m_fillOpacity)
            }
            m_fill.setAttribute("color", m_fillColor)
        }
        if (!created) {
            m_stroke = document.createElement("v:stroke");
            if (Isarrow) m_stroke.setAttribute("EndArrow", "Classic");
            m_shape.appendChild(m_stroke)
        }
        m_stroke.setAttribute("weight", m_strokeWeight);
        m_stroke.setAttribute("color", m_strokeColor);
        m_stroke.setAttribute("joinstyle", "round");
        m_stroke.setAttribute("endcap", "round");
        m_stroke.setAttribute("opacity", "0.75");
        if (!created) {
            _SelectGroup(m_groupID, m_shape)
        }
    }
    function _Repostion() {
        _Start()
    }
    this.Init = _Init;
    this.Destroy = _Destroy;
    this.RemoveFromMap = _RemoveFromMap;
    this.Show = _Show;
    this.Hide = _Hide;
    this.SetVisible = _SetVisible;
    this.Start = _Start;
    this.Update = _Update;
    this.Reposition = _Repostion
}
function _ShowPolylines() {
    for (var i = 0; i < _lines.length; i++) {
        _lines[i].Start();
        _lines[i].Show()
    }
    for (var i = 0; i < _polygons.length; i++) {
        _polygons[i].Start();
        _polygons[i].Show()
    }
}
function _HidePolylines() {
    for (var i = 0; i < _lines.length; i++) {
        _lines[i].Hide()
    }
    for (var i = 0; i < _polygons.length; i++) {
        _polygons[i].Hide()
    }
}
function _UpdatePolylines() {
    for (var i = 0; i < _lines.length; i++) {
        _lines[i].Update()
    }
    for (var i = 0; i < _polygons.length; i++) {
        _polygons[i].Update()
    }
}
function _CloseInfoWindow(id) {
    _RemoveMark(id)
}
function _OpenInfoWindow(id,x,y,width,height,title,content,opacity)
{
    var zIndex="1000";
    var left=0;
    var top=0;
    if(!x){return }
    if(!y){return }
    if(!width){width=100}
    if(!height){height=100}
    if(!opacity){opacity=0.5}
    if(width<50){width=50}
    if(height<50){height=50}
    if(!title){title="title"}
    if(!content){content="content"}
    var normal="white";
    var str="<div style='opacity:"+opacity+";z-index:"+zIndex+";width:"+width+" px;height:"+height+"px;left:" +left+ "px;top:"+ top +"px; color:"+normal+";font-size:12px;font-family:Verdana;position:absolute;cursor:default;border:0px solid white;' onclick=_CancelBubble(window.event) ondblclick =_CancelBubble(window.event) onmousemove =_CancelBubble(window.event) ><div style='position:absolute;cursor:default;left:0px;top:0px;width:100%;height:75%;z-index:"+(zIndex-2)+";'onclick=_CancelBubble(window.event)><img src='"+_scriptLocation + "../images/form-white1.gif' style='width:100%;height:100%;'/></div><div style='position:absolute;cursor:default;left:"+width/2+"px;top:"+height*3/4+"px;width:"+65*75/183+"%;height:25%;z-index:"+(zIndex-2)+";'><img src='"+_scriptLocation + "../images/form-white2.gif' style='width:100%;height:100%;'/></div><div style='position:absolute;left:0px;top:0px;z-index:"+(zIndex-1)+";width:100%;height:20px;'><table border=0 cellspacing=0 cellpadding=0><tr><td style='width="+width+"'><span style='width:"+(width-12)+";padding-left:3px;font-size:12px' >"+title+"</span></td><td align=right style='width:12px;height:20px;vertical-align:middle;'><div  onclick='tooltipid=null;var infoWindow=document.getElementById(\""+id+"\");    infoWindow.parentNode.removeChild(infoWindow);infoWindow=null;' style='margin:3px; '><img src='"+_scriptLocation + "../images/x.png' style='position:absolute;left:" +(width-20) + "px;border-width:0px;cursor:hand;'></div></td></tr></table><div style='position:absolute;margin:0 5px 5px 5px;width:"+(width-22)+"px;height:"+(height*0.7-13)+"px;line-height:14px;word-break:break-all;padding:3px;overflow:hidden'>"+content+"</div></div>";
    _InsertMark(id,x,y,width,height*2,str)
}
function _GetMapBounds() {
    return _curMap.GetMapBounds()
}
function _GetViewBounds() {
    var pr = new SuperMap.IS.PixelRect();
    var lt = pr.leftTop;
    var rb = pr.rightBottom;//alert("_w="+_w +"|_curParam.pixelCenter.x"+_curParam.pixelCenter.x)
    lt.x = _curParam.pixelCenter.x - _w / 2;
    lt.y = _curParam.pixelCenter.y - _h / 2;
    rb.x = lt.x + _w;
    rb.y = lt.y + _h;
    var mr = new SuperMap.IS.MapRect();
    var lb = mr.leftBottom;
    var rt = mr.rightTop;
    var mlt = _curMap.PixelToMapCoord(lt, _curParam.mapScale);
    var mrb = _curMap.PixelToMapCoord(rb, _curParam.mapScale);
    if (mlt == null || mrb == null) {
        return mr
    }
    lb.x = mlt.x;
    lb.y = mrb.y;
    rt.x = mrb.x;
    rt.y = mlt.y;
    return mr
}
function _CheckTileLoaded() {
    if (_tileLoadedChecking) {
        return
    }
    _tileLoadedChecking = true;
    window.clearTimeout(_iTimeoutIDForCheckTileLoaded);
    _iTimeoutIDForCheckTileLoaded = null;
    var loaded = true;
    if (_tileCheckTimes < _tileCheckCounts) {
        for (var i = 0; i < _tileCollections.length; i++) {
            var tiles = _tileCollections[i];
            if (tiles == null) {
                continue
            }
            for (var j = 0; j < tiles.length; j++) {
                if (tiles[j] && !tiles[j].Loaded) {
                    loaded = false;
                    break
                }
            }
        }
    } else {
        _tileCheckTimes = 0;
        for (var i = 0; i < _tileCollections.length; i++) {
            var tiles = _tileCollections[i];
            if (tiles == null) {
                continue
            }
            for (var j = 0; j < tiles.length; j++) {
                if (tiles[j] && !tiles[j].Loaded) {
                    loaded = false;
                    tiles[j].RefreshUrl();
                    continue
                }
            }
        }
    }
    if (loaded) {
        _tileCheckTimes = 0;
        _TriggerEvent("onimagesload", new EventArguments(null, ""))
    } else {
        _tileCheckTimes++;
        _iTimeoutIDForCheckTileLoaded = setTimeout(_CheckTileLoaded, 200)
    }
    _tileLoadedChecking = false
}
function _GetCustomDiv() {
    if (_customDiv) {
        _customDiv.innerHtml = "";
        if (!_customDiv.parentNode) {
            _workLayer.appendChild(_customDiv)
        }
        return _customDiv
    }
    var customDiv = document.createElement("DIV");
    var es = customDiv.style;
    es.position = "absolute";
    es.padding = "0px";
    es.margin = "0px";
    es.top = "0px";
    es.width = _workLayer.style.width;
    es.height = _workLayer.style.height;
    es.zIndex = _customLayer_baseZIndex;
    es = null;
    var groupDiv = document.createElement("div");
    groupDiv.id = "unClassified";
    customDiv.appendChild(groupDiv);
    _customDiv = customDiv;
    _workLayer.appendChild(_customDiv);
    customDiv = null;
    return _customDiv
}
this.GetMapCenterX = _GetMapCenterX;
this.GetMapCenterY = _GetMapCenterY;
this.GetPixelCenterX = _GetPixelCenterX;
this.GetPixelCenterY = _GetPixelCenterY;
this.MapCoordToPixel = _MapCoordToPixel;
this.PixelToMapCoord = _PixelToMapCoord;
this.GetMapScale = _GetMapScale;
this.GetZoomLevel = _GetZoomLevel;
this.GetMapBounds = _GetMapBounds;
this.GetViewBounds = _GetViewBounds;
this.PixelToMapDistance = _PixelToMapDistance;
this.GetSize = _GetSize;
this.Resize = _Resize;
this.Pan = _Pan;
this.DynamicPan = _DynamicPan;
this.StopDynamicPan = _StopDynamicPan;
this.PanToMapCoord = _PanToMapCoord;
this.ViewByBounds = _ViewByBounds;
this.ViewByPoints = _ViewByPoints;
this.ViewByPoint = _ViewByPoint;
this.SetMapScale = _SetMapScale;
this.SetZoomLevel = _SetZoomLevel;
this.ZoomIn = _ZoomIn;
this.ZoomOut = _ZoomOut;
this.Zoom = _Zoom;
this.SetCenterAndZoom = _SetCenterAndZoom;
this.AttachEvent = _AttachEvent;
this.DetachEvent = _DetachEvent;
this.SetAnimationEnabled = _SetAnimationEnabled;
this.IsAnimationEnabled = _IsAnimationEnabled;
this.SetMapParam = _SetMapParam;
this.GetMapParam = _GetMapParam;
this.Debug = _Debug;
this.GetOriginX = _GetOriginX;
this.GetOriginY = _GetOriginY;
this.GetOffsetX = _GetOffsetX;
this.GetOffsetY = _GetOffsetY;
this.GetContainerX = _GetContainerX;
this.GetContainerY = _GetContainerY;
this.GetAction = _GetAction;
this.SetAction = _SetAction;
this.CustomLayer = new Object();
var cl = this.CustomLayer;
cl.AddMark = _AddMark;
cl.InsertMark = _InsertMark;
cl.RemoveMark = _RemoveMark;
cl.ClearMarks = _ClearMarks;
cl.AddLine = _AddLine;
cl.AddLineArrow = _AddLineArrow;
cl.InsertLine = _InsertLine;
cl.InsertLineArrow = _InsertLineArrow;
cl.RemoveLine = _RemoveLine;
cl.ClearLines = _ClearLines;
cl.AddPolygon = _AddPolygon;
cl.InsertPolygon = _InsertPolygon;
cl.RemovePolygon = _RemovePolygon;
cl.ClearPolygons = _ClearPolygons;
cl.OpenInfoWindow = _OpenInfoWindow;
cl.CloseInfoWindow = _CloseInfoWindow;
cl.SetGroupVisible = _SetGroupVisible;
cl.SetGroupZindex = _SetGroupZindex;
cl.AddGeometry = _AddGeometry;
cl.AddGeometries = _AddGeometries;
cl.InsertGeometry = _InsertGeometry;
cl.InsertGeometries = _InsertGeometries;
cl.RemoveGeometry = _RemoveGeometry;
cl.RemoveGeometries = _RemoveGeometries;
cl.ClearGeometries = _ClearGeometries;
cl.GetGeometry = _GetGeometry;
cl.GetGeometries = _GetGeometries;
cl.UpdateGeometries = _UpdateGeometries;
cl.SetDefaultGeometryStyle = _SetDefaultGeometryStyle;
cl.SetGeometryTolerance = _SetGeometryTolerance;
cl.SetCalculateInClient = _SetCalculateInClient;
cl.QueryGeomtryByPoint = _QueryGeomtryByPoint;
cl = null;
this.GetQueryManager = function() {
    if (!query) {
        query = new SuperMap.IS.QueryManager(_mapHandler, _mapName, _trackingLayerIndex, _userID);
        query.AttachEvent("onchangetrackinglayer", _OnChangeTrackingLayer)
    }
    return query
};
this.GetSpatialAnalystManager = function() {
    if (!spatialAnalyst) {
        spatialAnalyst = new SuperMap.IS.SpatialAnalystManager(_mapHandler, _mapName, _trackingLayerIndex, _userID);
        spatialAnalyst.AttachEvent("onchangetrackinglayer", _OnChangeTrackingLayer);
        spatialAnalyst.AttachEvent("onviewboundschanged", _OnViewBoundsChanged)
    }
    return spatialAnalyst
};
this.GetEditManager = function() {
    if (!edit) {
        edit = new SuperMap.IS.EditManager(_mapHandler, _mapName)
    }
    return edit
};
this.GetMapStatus = function(returnLayers, returnThemes, onComplete, onError, userContext) {
    _curMap.GetMapStatus(returnLayers, returnThemes, onComplete, onError, userContext)
};
this.GetEntity = function(mapName, layerName, id, onComplete, onError, userContext) {
    _curMap.GetEntity(mapName, layerName, id, onComplete, onError, userContext)
};
this.GetEntities = function(mapName, layerName, ids, onComplete, onError, userContext) {
    _curMap.GetEntities(mapName, layerName, ids, onComplete, onError, userContext)
};
this.MeasureDistance = function(points, isHighlight, onComplete, onError, userContext) {
    _curMap.MeasureDistance(points, isHighlight, onComplete, onError, userContext)
};
this.MeasureArea = function(points, isHighlight, onComplete, onError, userContext) {
    _curMap.MeasureArea(points, isHighlight, onComplete, onError, userContext)
};
this.CustomInvoke = function(customParams, identifier, onComplete, onError, userContext) {
    _curMap.CustomInvoke(customParams, identifier, onComplete, onError, userContext)
};
this.GetOverview = function(overview, onComplete, onError, userContext) {
    _curMap.GetOverview(overview, onComplete, onError, userContext)
};
this.GetMapImage = function(mapParam, onComplete, onError, userContext) {
    _curMap.GetMapImage(mapParam, onComplete, onError, userContext)
};
this.GetMapImageByDpi = function(mapParam, imageDpi, onComplete, onError, userContext) {
    _curMap.GetMapImageByDpi(mapParam, imageDpi, onComplete, onError, userContext)
};
this.GetWorkspaceInfo = function(onComplete, onError, userContext) {
    _curMap.GetWorkspaceInfo(onComplete, onError, userContext)
};
this.GetDatasetInfo = function(datasource, dataset, onComplete, onError, userContext) {
    _curMap.GetDatasetInfo(datasource, dataset, onComplete, onError, userContext)
};
this.ClearCache = function(mapName, mapRect, onComplete, onError, userContext) {
    _curMap.ClearCache(mapName, mapRect, onComplete, onError, userContext)
};
this.ConnectDatasources = function(onComplete, onError, userContext) {
    _curMap.ConnectDatasources(onComplete, onError, userContext)
};
this.GetFieldInfo = function(dataset, fieldName, onComplete, onError, userContext) {
    _curMap.GetFieldInfo(dataset, fieldName, onComplete, onError, userContext)
};
this.MakeDefaultDotDensityTheme = function(layerName, expression, colorSet, onComplete, onError, userContext) {
    _curMap.MakeDefaultDotDensityTheme(layerName, expression, colorSet, onComplete, onError, userContext)
};
this.MakeDefaultGraduatedSymbolTheme = function(layerName, expression, colorSet, onComplete, onError, userContext) {
    _curMap.MakeDefaultGraduatedSymbolTheme(layerName, expression, colorSet, onComplete, onError, userContext)
};
this.MakeDefaultGraphTheme = function(layerName, expressions, colorSet, onComplete, onError, userContext) {
    _curMap.MakeDefaultGraphTheme(layerName, expressions, colorSet, onComplete, onError, userContext)
};
this.MakeDefaultRangeTheme = function(layerName, expression, breakCount, colorSet, onComplete, onError, userContext) {
    _curMap.MakeDefaultRangeTheme(layerName, expression, breakCount, colorSet, onComplete, onError, userContext)
};
this.MakeDefaultUniqueTheme = function(layerName, expression, colorSet, startSmID, expectCount, onComplete, onError, userContext) {
    _curMap.MakeDefaultUniqueTheme(layerName, expression, colorSet, startSmID, expectCount, onComplete, onError, userContext)
};
this.MakeDefaultGridRangeTheme = function(layerName, breakCount, colorSet, onComplete, onError, userContext) {
    _curMap.MakeDefaultGridRangeTheme(layerName, breakCount, colorSet, onComplete, onError, userContext)
};
this.ClearHighlight = function(onComplete, onError, userContext) {
    _curMap.ClearHighlight(onComplete, onError, userContext)
};
function _ShowBusStopBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
    var queryUrl = _mapHandler + "path.ashx";
    var mapName = _mapName;
    function onRequestComplete(responseText) {
        if (!responseText) {
            if (onComplete) {
                onComplete(null, userContext)
            }
            return
        }
        var busStopJ = eval("(" + responseText + ")");
        if (!busStopJ) {
            return
        }
        var busStop = new SuperMap.IS.BusStop();
        busStop.FromJSON(busStopJ);
        if (busStopJ.Location) {
            busStopJ.Location = null
        }
        busStopJ = null;
        var innerHtml = "<div style='font-size:16px; color:blue; font-weight:bold'><img src='images/marker.gif' style='cursor:hand' />" + busStop.stopName + "<div>";
        _self.CustomLayer.InsertMark("BusStop", busStop.Location.x, busStop.Location.y, 10, 10, innerHtml);
        _self.PanToMapCoord(busStop.Location.x, busStop.Location.y);
        if (onComplete) {
            onComplete(busStop, userContext)
        }
        busStop.Destroy();
        busStop = null
    }
    var reuqestManager = new SuperMap.IS.RequestManager(queryUrl, onRequestComplete, onError, userContext);
    reuqestManager.AddQueryString("map", mapName);
    reuqestManager.AddQueryString("method", methodName);
    reuqestManager.AddQueryStrings(paramNames, paramValues);
    reuqestManager.Send();
    reuqestManager.Destroy();
    reuqestManager = null;
    while (paramNames.length > 0) {
        paramNames.pop();
        paramValues.pop()
    }
    paramNames = null;
    paramValues = null
}
this.ShowBusStop = function(id, onComplete, onError, userContext) {
    _ShowBusStopBase("ShowBusStop", ["id"], [id], onComplete, onError, userContext)
};
function _ShowBusLineBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
    var queryUrl = _mapHandler + "path.ashx";
    var mapName = _mapName;
    function onRequestComplete(responseText) {
        if (!responseText) {
            if (onComplete) {
                onComplete(null, userContext)
            }
            return
        }
        var busLineJ = eval("(" + responseText + ")");
        if (!busLineJ) {
            return
        }
        var busLine = new SuperMap.IS.BusLine();
        busLine.FromJSON(busLineJ);
        if (busLineJ && busLineJ.points) {
            while (busLineJ.points.length > 0) {
                busLineJ.points.pop()
            }
        }
        busLineJ = null;
        var xs = new Array();
        var ys = new Array();
        for (var i = 0; i < busLine.points.length; i++) {
            xs[i] = busLine.points[i].x;
            ys[i] = busLine.points[i].y
        }
        _self.CustomLayer.InsertLine("BusLine", xs, ys, 3, "blue");
        _self.ViewByPoints(busLine.points);
        while (xs.length > 0) {
            xs.pop();
            ys.pop()
        }
        xs = null;
        ys = null;
        if (onComplete) {
            onComplete(busLine, userContext)
        }
        busLine.Destroy();
        busLine = null
    }
    var reuqestManager = new SuperMap.IS.RequestManager(queryUrl, onRequestComplete, onError, userContext);
    reuqestManager.AddQueryString("map", mapName);
    reuqestManager.AddQueryString("method", methodName);
    reuqestManager.AddQueryStrings(paramNames, paramValues);
    reuqestManager.Send();
    reuqestManager.Destroy();
    reuqestManager = null;
    while (paramNames.length > 0) {
        paramNames.pop();
        paramValues.pop()
    }
    paramNames = null;
    paramValues = null
}
this.ShowBusLine = function(id, onComplete, onError, userContext) {
    _ShowBusLineBase("ShowBusLine", ["id"], [id], onComplete, onError, userContext)
};
function _ShowRoutingBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
    var queryUrl = _mapHandler + "path.ashx";
    var mapName = _mapName;
    function onRequestComplete(responseText) {
        if (!responseText) {
            if (onComplete) {
                onComplete(null, userContext)
            }
            return
        }
        var busRoutingJ = eval("(" + responseText + ")");
        if (!busRoutingJ) {
            return
        }
        var busRouting = new SuperMap.IS.BusRouting();
        busRouting.FromJSON(busRoutingJ);
        if (busRoutingJ.busLines) {
            while (busRoutingJ.busLines.length > 0) {
                var busLineJ = busRoutingJ.busLines.pop();
                if (busLineJ && busLineJ.points) {
                    while (busLineJ.points.length > 0) {
                        busLineJ.points.pop()
                    }
                }
                busLineJ = null
            }
        }
        if (busRoutingJ.upStops) {
            while (busRoutingJ.upStops.length > 0) {
                var busStopJ = busRoutingJ.upStops.pop();
                if (busStopJ.Location) {
                    busStopJ.Location = null
                }
                busStopJ = null
            }
        }
        if (busRoutingJ.downStops) {
            while (busRoutingJ.downStops.length > 0) {
                var busStopJ = busRoutingJ.downStops.pop();
                if (busStopJ.Location) {
                    busStopJ.Location = null
                }
                busStopJ = null
            }
        }
        busRoutingJ = null;
        if (_lastBusNum > 0) {
            for (var i = 0; i < _lastBusNum; i++) {
                _self.CustomLayer.RemoveMark("BusStartStop" + i);
                _self.CustomLayer.RemoveMark("BusEndStop" + i);
                _self.CustomLayer.RemoveLine("BusRouting" + i)
            }
        }
        if (busRouting.upStops) {
            for (var i = 0; i < busRouting.upStops.length; i++) {
                var innerHtml = "<div style='font-size:16px; color:blue;font-weight:bold'><img src='images/marker.gif' alt='" + busRouting.upStops[i].stopName + "'style='cursor:hand' />";
                _self.CustomLayer.InsertMark("BusStartStop" + i, busRouting.upStops[i].Location.x, busRouting.upStops[i].Location.y, 10, 10, innerHtml)
            }
        }
        if (busRouting.downStops) {
            for (var i = 0; i < busRouting.downStops.length; i++) {
                var innerHtml = "<div style='ffont-size:16px; color:blue;font-weight:bold'><img src='images/marker.gif' alt='" + busRouting.downStops[i].stopName + "'style='cursor:hand' />";
                _self.CustomLayer.InsertMark("BusEndStop" + i, busRouting.downStops[i].Location.x, busRouting.downStops[i].Location.y, 10, 10, innerHtml)
            }
        }
        if (busRouting.busLines) {
            for (var i = 0; i < busRouting.busLines.length; i++) {
                var xs = new Array();
                var ys = new Array();
                for (var j = 0; j < busRouting.busLines[i].points.length; j++) {
                    xs[j] = busRouting.busLines[i].points[j].x;
                    ys[j] = busRouting.busLines[i].points[j].y
                }
                _self.CustomLayer.InsertLine("BusRouting" + i, xs, ys, 3, "blue");
                while (xs.length > 0) {
                    xs.pop();
                    ys.pop()
                }
                xs = null;
                ys = null
            }
            var mcs = new Array();
            for (var i = 0; i < busRouting.busLines.length; i++) {
                for (var j = 0; j < busRouting.busLines[i].points.length; j++) {
                    mcs.push(busRouting.busLines[i].points[j])
                }
                _lastBusNum = i + 1
            }
            _self.ViewByPoints(mcs);
            while (mcs.length > 0) {
                mcs.pop()
            }
            mcs = null
        }
        if (onComplete) {
            onComplete(busRouting, userContext)
        }
        busRouting.Destroy();
        busRouting = null
    }
    var reuqestManager = new SuperMap.IS.RequestManager(queryUrl, onRequestComplete, onError, userContext);
    reuqestManager.AddQueryString("map", mapName);
    reuqestManager.AddQueryString("method", methodName);
    reuqestManager.AddQueryStrings(paramNames, paramValues);
    reuqestManager.Send();
    reuqestManager.Destroy();
    reuqestManager = null;
    while (paramNames.length > 0) {
        paramNames.pop();
        paramValues.pop()
    }
    paramNames = null;
    paramValues = null
}
this.ShowRouting = function(busRouting, onComplete, onError, userContext) {
    _ShowRoutingBase("ShowRouting", ["busRouting"], [busRouting], onComplete, onError, userContext)
};
this.GetResource = function(mapName, resourceParam, onComplete, onError, userContext) {
    _curMap.GetResource(mapName, resourceParam, onComplete, onError, userContext)
};
this.GetGeometryImage = function(mapName, geometryParam, onComplete, onError, userContext) {
    _curMap.GetGeometryImage(mapName, geometryParam, onComplete, onError, userContext)
};
this.GenerateResourceRequest = function(resourceParam) {
    var returnString = params.mapHandler + "ajax/" + encodeURI(params.mapName) + "/" + resourceParam.style.brushBackColor + "/" + resourceParam.style.brushBackTransparent + "/" + resourceParam.style.brushColor + "/" + resourceParam.style.brushStyle + "/" + resourceParam.style.penColor + "/" + resourceParam.style.penStyle + "/" + resourceParam.style.penWidth + "/" + resourceParam.style.symbolRotation + "/" + resourceParam.style.symbolSize + "/" + resourceParam.style.symbolStyle + "/" + resourceParam.resourceType + "/" + resourceParam.imageFormat + "/" + resourceParam.width + "/" + resourceParam.height + "/map.ashx?GetResource=true&redirect=" + params.redirect;
    return returnString
};
function _SystemColorToIntegerColor(color) {
    if (!color) {
        return - 1
    }
    return (color.r) | (color.g << 8) | (color.b << 16)
}
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
var _IsDynamicNavigate = false;
var _userContext;
var _onComplete;
var _sectionIndex;
function _StopDynamicNavigate() {
    _IsDynamicNavigate = false;
    _self.CustomLayer.RemoveMark("Walk");
    _points = null;
    _areas = null
}
function _PauseDynamicNavigate() {
    _IsDynamicNavigate = false
}
function _ContinueDynamicNavigate() {
    if (!_points) {
        return
    }
    if (!_IsDynamicNavigate) {
        _IsDynamicNavigate = true;
        _DynamicNavigateStep()
    }
}
function _StartDynamicNavigate(points, areas, onOutAreaUrl, onInAreaUrl, onOutArea, onInArea, bRotative, step, time, onComplete, userContext) {
    if (_IsDynamicNavigate) {
        return
    }
    if (points == null || points.length < 2) {
        return
    }
    _IsDynamicNavigate = true;
    _points = points;
    _areas = areas;
    _onOutAreaUrl = onOutAreaUrl;
    _onInAreaUrl = onInAreaUrl;
    _onOutArea = onOutArea;
    _onInArea = onInArea;
    _bRotative = bRotative;
    _onComplete = onComplete;
    _userContext = userContext;
    _viewBounds = _GetViewBounds();
    _step = 50;
    if (step) {
        _step = step
    }
    _time = 100;
    if (time) {
        _time = time
    }
    _sectionIndex = 0;
    _DynamicNavigateSection()
}
this.StartDynamicNavigate = _StartDynamicNavigate;
this.PauseDynamicNavigate = _PauseDynamicNavigate;
this.ContinueDynamicNavigate = _ContinueDynamicNavigate;
this.StopDynamicNavigate = _StopDynamicNavigate;
function _DynamicNavigateSection() {
    if (!_points) {
        return
    }
    var startPoint = _points[_sectionIndex];
    var endPoint = _points[_sectionIndex + 1];
    _SetViewBounds(startPoint);
    _distanceX = endPoint.x - startPoint.x;
    _distanceY = endPoint.y - startPoint.y;
    _startPointX = startPoint.x;
    _startPointY = startPoint.y;
    _distance = Math.sqrt((Math.pow(_distanceX, 2) + Math.pow(_distanceY, 2)));
    if (_distance == 0) {
        _sectionIndex++;
        if (_sectionIndex < _points.length - 1) {
            _DynamicNavigateSection()
        } else {
            if (_onComplete) {
                _onComplete(_userContext)
            }
            if (_bRotative) {
                _viewBounds = _GetViewBounds();
                if (_points == null || _points.length < 2) {
                    return
                }
                _sectionIndex = 0;
                _DynamicNavigateSection()
            } else {
                _IsDynamicNavigate = false;
                _self.CustomLayer.RemoveMark("Walk")
            }
        }
    } else {
        _length = _distance / _step;
        _stepIndex = 0;
        window.setTimeout(_DynamicNavigateStep, _time)
    }
}
function _DynamicNavigateStep() {
    if (!_IsDynamicNavigate) {
        return
    }
    var actualDis = _step * _stepIndex;
    _stepIndex++;
    if (actualDis > _distance) {
        actualDis = _distance
    }
    var point = new SuperMap.IS.MapCoord(actualDis * _distanceX / _distance + _startPointX, actualDis * _distanceY / _distance + _startPointY);
    _SetViewBounds(point);
    var innerHTML;
    var flag = _IsInAreas(point);
    if (flag) {
        innerHTML = "<img src='" + _onInAreaUrl + "'/>"
    } else {
        innerHTML = "<img src='" + _onOutAreaUrl + "'/>"
    }
    _self.CustomLayer.InsertMark("Walk", point.x, point.y, 20, 40, innerHTML);
    if (_stepIndex < _length) {
        _iTimeoutIDForDynamicNavigate = window.setTimeout(_DynamicNavigateStep, _time)
    } else {
        _sectionIndex++;
        if (_sectionIndex < _points.length - 1) {
            _DynamicNavigateSection(_points[_sectionIndex], _points[_sectionIndex + 1], _areas, _sectionIndex)
        } else {
            if (_onComplete) {
                _onComplete(_userContext)
            }
            if (_bRotative) {
                _viewBounds = _GetViewBounds();
                if (_points == null || _points.length < 2) {
                    return
                }
                _sectionIndex = 0;
                _DynamicNavigateSection()
            } else {
                _IsDynamicNavigate = false;
                _self.CustomLayer.RemoveMark("Walk")
            }
        }
    }
}
function _SetViewBounds(point) {
    if (!_GetViewBounds().Contains(point)) {
        _PanToMapCoord(point.x, point.y)
    }
}
function _IsInAreas(point) {
    if (_areas != null) {
        for (var i = 0; i < _areas.length; i++) {
            if (_areas[i].Contains(point)) {
                if (_onInArea) {
                    _onInArea(point, _areas[i], _userContext)
                }
                return true;
                break
            }
        }
    }
    if (_onOutArea) {
        _onOutArea(point, _userContext)
    }
    return false
}
function _SetLayersHidden(changedLayersJSON) {
    var hidden = document.getElementById(container.id + "_hiddenLayers");
    if (hidden) {
        hidden.value = changedLayersJSON
    }
}
function _SetMapParamHidden() {
    var mapParam = new Object();
    mapParam.center = new SuperMap.IS.MapCoord();
    mapParam.center.x = _GetMapCenterX();
    mapParam.center.y = _GetMapCenterY();
    mapParam.mapScale = _curParam.mapScale;
    mapParam.mapBounds = _GetMapBounds();
    mapParam.viewBounds = _GetViewBounds();
    mapParam.mapName = _mapName;
    mapParam.trackingLayerIndex = _trackingLayerIndex;
    mapParam.userID = _userID;
    var hiddenMapParam = document.getElementById(container.id + "_hiddenMapParam");
    if (hiddenMapParam) {
        hiddenMapParam.value = _ToJSON(mapParam)
    }
}
function _SetSizeHidden() {
    var hiddenValue = container.style.width + "|" + container.style.height;
    var hidden = document.getElementById(container.id + "_hiddenSize");
    if (hidden) {
        hidden.value = hiddenValue
    }
}
function _InitClientAction() {
    var hidden = document.getElementById(container.id + "_hiddenClientAction");
    if (hidden && hidden.value) {
        var json = hidden.value;
        var action = _JSONToAction(json);
        _SetAction(action)
    }
}
function _InitMarks() {
    var hidden = document.getElementById(container.id + "_hiddenMarks");
    if (hidden && hidden.value) {
        hidden.value = unescape(hidden.value);
        var marks = eval("(" + hidden.value + ")");
        if (marks) {
            while (marks.length > 0) {
                var mark = marks.pop();
                mark.innerHtml = mark.innerHtml;
                _AddMark(mark.id, mark.x, mark.y, mark.w, mark.h, mark.innerHtml, mark.className, mark.zIndex, mark.groupID, mark.alignStyle);
                mark = null
            }
        }
    }
}
function _InitLines() {
    var hidden = document.getElementById(container.id + "_hiddenLines");
    if (hidden && hidden.value) {
        var lines = eval("(" + hidden.value + ")");
        if (lines) {
            while (lines.length > 0) {
                var line = lines.pop();
                _AddLine(line.id, line.xs, line.ys, line.strokeWeight, line.strokeColor, line.opacity, line.zIndex, line.groupID, line.parts);
                line = null
            }
        }
    }
}
function _InitPolygons() {
    var hidden = document.getElementById(container.id + "_hiddenPolygons");
    if (hidden && hidden.value) {
        var polygons = eval("(" + hidden.value + ")");
        if (polygons) {
            while (polygons.length > 0) {
                var polygon = polygons.pop();
                _AddPolygon(polygon.id, polygon.xs, polygon.ys, polygon.strokeWeight, polygon.strokeColor, polygon.fillColor, polygon.fillOpacity, polygon.zIndex, polygon.groupID, polygon.parts);
                polygon = null
            }
        }
    }
}
function _OnChangeTrackingLayer(arguments) {
    _t = new Date().getTime();
    _trackingLayerIndex = arguments.trackingLayerIndex;
    _userID = arguments.userID;
    if (_curMap) {
        _curMap.ChangeTrackingLayerKey(_trackingLayerIndex, _userID)
    }
    if (query) {
        query.ChangeTrackingLayerKey(_trackingLayerIndex, _userID)
    }
    if (spatialAnalyst) {
        spatialAnalyst.ChangeTrackingLayerKey(_trackingLayerIndex, _userID)
    }
    if (arguments.bSaveHistory) {
        _SetMapParamHidden()
    }
    _RefreshTrackingLayer()
}
function _OnChangeLayer(arguments) {
    _TriggerEvent("onchangelayer")
}
function _OnViewBoundsChanged(viewBounds) {
    if (viewBounds) {
        _ViewByBounds(viewBounds.leftBottom.x, viewBounds.leftBottom.y, viewBounds.rightTop.x, viewBounds.rightTop.y)
    }
}
function _SetMarksHidden() {
    if (!_self.storeClientInfo) {
        return
    }
    var hidden = document.getElementById(container.id + "_hiddenMarks");
    if (!hidden) {
        return
    }
    if (!_marks) {
        hidden.value = ""
    }
    var marks = new Array();
    for (var i = 0; i < _marks.length; i++) {
        var mark = new Object();
        mark.id = _marks[i].id;
        mark.x = _marks[i].x;
        mark.y = _marks[i].y;
        mark.w = _marks[i].w;
        mark.h = _marks[i].h;
        mark.innerHtml = _marks[i].innerHtml;
        mark.className = _marks[i].className;
        mark.zIndex = _marks[i].zIndex;
        mark.groupID = _marks[i].groupID;
        mark.alignStyle = _marks[i].alignStyle;
        marks.push(mark)
    }
    var json = _ToJSON(marks);
    while (marks.length > 0) {
        marks.pop()
    }
    marks = null;
    json = escape(json);
    hidden.value = json
}
function _SetLinesHidden() {
    if (!_self.storeClientInfo) {
        return
    }
    var hidden = document.getElementById(container.id + "_hiddenLines");
    if (!hidden) {
        return
    }
    var lines = null;
    if (_lines) {
        lines = new Array();
        for (var i = 0; i < _lines.length; i++) {
            var line = new Object();
            line.id = _lines[i].id;
            line.xs = _lines[i].xs;
            line.ys = _lines[i].ys;
            line.strokeWeight = _lines[i].strokeWeight;
            line.strokeColor = _lines[i].strokeColor;
            line.zIndex = _lines[i].zIndex;
            line.groupID = _lines[i].groupID;
            line.parts = _lines[i].parts;
            lines.push(line)
        }
    }
    var json = _ToJSON(lines);
    if (lines) {
        while (lines.length > 0) {
            var line = lines.pop();
            line = null
        }
        lines = null
    }
    hidden.value = json
}
function _SetPolygonsHidden() {
    if (!_self.storeClientInfo) {
        return
    }
    var hidden = document.getElementById(container.id + "_hiddenPolygons");
    if (!hidden) {
        return
    }
    var polygons = null;
    if (_polygons) {
        polygons = new Array();
        for (var i = 0; i < _polygons.length; i++) {
            var polygon = new Object();
            polygon.id = _polygons[i].id;
            polygon.xs = _polygons[i].xs;
            polygon.ys = _polygons[i].ys;
            polygon.parts = _polygons[i].parts;
            polygon.strokeWeight = _polygons[i].strokeWeight;
            polygon.strokeColor = _polygons[i].strokeColor;
            polygon.fillColor = _polygons[i].fillColor;
            polygon.fillOpacity = _polygons[i].fillOpacity;
            polygon.zIndex = _polygons[i].zIndex;
            polygon.groupID = _polygons[i].groupID;
            polygons.push(polygon)
        }
    }
    var json = _ToJSON(polygons);
    if (polygons) {
        while (polygons.length > 0) {
            var polygon = polygons.pop();
            polygon = null
        }
        polygons = null
    }
    hidden.value = json
}
function _RefreshMap() {
    _oldTileCollections = _tileCollections;
    _tileCollections = new Array();
    _StartMap();
    _SwapStates();
    _RepositionMarks();
    _RepositionLines();
    _RepositionPolygons();
    _SetFactor()
}
function _RefreshTrackingLayer() {
    if (_imageBufferCollection[1] != null) {
        while (_imageBufferCollection[1].length > 0) {
            var imageId = _imageBufferCollection[1][0];
            delete _imageBufferCollection[1][imageId];
            _imageBufferCollection[1].shift()
        }
        _imageBufferCollection[1] = null
    }
    if (!_tileCollections[1]) {
        _tileCollections[1] = new Array()
    }
    if (_tileCollections[1].length > 0) {
        for (var i = 0; i < _tileCollections[1].length; i++) {
            _tileCollections[1][i].RefreshUrl()
        }
    } else {
        var tileLayerBounds = _tileLayers[_tileLayerIDs[1]].visibleBounds;
        var tileLayerMaxScale = _tileLayers[_tileLayerIDs[1]].maxScale;
        var tileLayerMinScale = _tileLayers[_tileLayerIDs[1]].minScale;
        var centerX = _Floor((_tileCountX[1] - 1) / 2);
        var centerY = _Floor((_tileCountY[1] - 1) / 2);
        var radius = 0;
        var flag = 0;
        var startX = 0;
        var startY = 0;
        var x = 0;
        var y = 0;
        while (centerX + radius < _tileCountX[1] || centerY + radius < _tileCountY[1]) {
            startX = centerX + radius;
            startY = centerY - radius;
            x = startX;
            y = startY;
            flag = 1;
            do {
                if (x >= 0 && x < _tileCountX[1] && y >= 0 && y < _tileCountY[1]) {
                    var tileBounds = _GetTileBounds(x + _tileX1[1], y + _tileY1[1], _tileLayers[_tileLayerIDs[1]].tileWidth, _tileLayers[_tileLayerIDs[1]].tileHeight, _tileLayers[_tileLayerIDs[1]].layerBounds);
                    var tile = null;
                    if ((!tileLayerMaxScale || _curParam.mapScale < tileLayerMaxScale) && (!tileLayerMinScale || _curParam.mapScale > tileLayerMinScale) && (!tileLayerBounds || _IsBoundsTouchs(tileBounds, tileLayerBounds))) {
                        tile = _CreateTile(x + _tileX1[1], y + _tileY1[1], _curParam.mapScale, _tileLayers[_tileLayerIDs[1]])
                    }
                    if (!_tileCollections[1]) {
                        _tileCollections[1] = new Array()
                    }
                    var tileIndex = x + y * _tileCountX[1];
                    _tileCollections[1][tileIndex] = tile
                }
                if (x == centerX + radius && y == centerY + radius) {
                    flag = 0
                } else {
                    if (x == centerX - radius && y == centerY + radius) {
                        flag = 3
                    } else {
                        if (x == centerX - radius && y == centerY - radius) {
                            flag = 2
                        } else {
                            if (x == centerX + radius && y == centerY - radius) {
                                flag = 1
                            }
                        }
                    }
                }
                if (radius > 0) {
                    if (flag == 0) {
                        x--
                    } else {
                        if (flag == 1) {
                            y++
                        } else {
                            if (flag == 2) {
                                x++
                            } else {
                                if (flag == 3) {
                                    y--
                                }
                            }
                        }
                    }
                }
            }
            while (startX != x || startY != y);
            radius++
        }
    }
}
function _RefreshGeometryLayer() {
    _t = new Date().getTime();
    if (_imageBufferCollection[2] != null) {
        while (_imageBufferCollection[2].length > 0) {
            var imageId = _imageBufferCollection[2][0];
            delete _imageBufferCollection[2][imageId];
            _imageBufferCollection[2].shift()
        }
        _imageBufferCollection[2] = null
    }
    if (!_tileCollections[2]) {
        _tileCollections[2] = new Array()
    }
    if (_tileCollections[2].length > 0) {
        for (var i = 0; i < _tileCollections[2].length; i++) {
            _tileCollections[2][i].RefreshUrl()
        }
    } else {
        var tileLayerBounds = _tileLayers[_tileLayerIDs[2]].visibleBounds;
        var tileLayerMaxScale = _tileLayers[_tileLayerIDs[2]].maxScale;
        var tileLayerMinScale = _tileLayers[_tileLayerIDs[2]].minScale;
        var centerX = _Floor((_tileCountX[2] - 1) / 2);
        var centerY = _Floor((_tileCountY[2] - 1) / 2);
        var radius = 0;
        var flag = 0;
        var startX = 0;
        var startY = 0;
        var x = 0;
        var y = 0;
        while (centerX + radius < _tileCountX[2] || centerY + radius < _tileCountY[2]) {
            startX = centerX + radius;
            startY = centerY - radius;
            x = startX;
            y = startY;
            flag = 1;
            do {
                if (x >= 0 && x < _tileCountX[2] && y >= 0 && y < _tileCountY[2]) {
                    var tileBounds = _GetTileBounds(x + _tileX1[2], y + _tileY1[2], _tileLayers[_tileLayerIDs[2]].tileWidth, _tileLayers[_tileLayerIDs[2]].tileHeight, _tileLayers[_tileLayerIDs[2]].layerBounds);
                    var tile = null;
                    if ((!tileLayerMaxScale || _curParam.mapScale < tileLayerMaxScale) && (!tileLayerMinScale || _curParam.mapScale > tileLayerMinScale) && (!tileLayerBounds || _IsBoundsTouchs(tileBounds, tileLayerBounds))) {
                        tile = _CreateTile(x + _tileX1[2], y + _tileY1[2], _curParam.mapScale, _tileLayers[_tileLayerIDs[2]])
                    }
                    if (!_tileCollections[2]) {
                        _tileCollections[2] = new Array()
                    }
                    var tileIndex = x + y * _tileCountX[2];
                    _tileCollections[2][tileIndex] = tile
                }
                if (x == centerX + radius && y == centerY + radius) {
                    flag = 0
                } else {
                    if (x == centerX - radius && y == centerY + radius) {
                        flag = 3
                    } else {
                        if (x == centerX - radius && y == centerY - radius) {
                            flag = 2
                        } else {
                            if (x == centerX + radius && y == centerY - radius) {
                                flag = 1
                            }
                        }
                    }
                }
                if (radius > 0) {
                    if (flag == 0) {
                        x--
                    } else {
                        if (flag == 1) {
                            y++
                        } else {
                            if (flag == 2) {
                                x++
                            } else {
                                if (flag == 3) {
                                    y--
                                }
                            }
                        }
                    }
                }
            }
            while (startX != x || startY != y);
            radius++
        }
    }
}
function _SwitchMap() {
    _changingMap = true;
    query = null;
    spatialAnalyst = null;
    overview = null;
    edit = null;
    magnifier = null;
    while (_tiles.length > 0) {
        var cTile = _tiles.pop();
        cTile.RemoveFromMap();
        cTile = null
    }
    while (_trackingLayerTiles.length > 0) {
        var cTrackingLayerTiles = _trackingLayerTiles.pop();
        cTrackingLayerTiles.RemoveFromMap();
        cTrackingLayerTiles = null
    }
    while (_preTiles.length > 0) {
        var preTile = _preTiles.pop();
        preTile.RemoveFromMap();
        preTile = null
    }
    while (_preTrackingLayerTiles.length > 0) {
        var cPreTrackingLayerTile = _preTrackingLayerTiles.pop();
        cPreTrackingLayerTile.RemoveFromMap();
        cPreTrackingLayerTile = null
    }
    if (_logo) {
        _logo.Destroy();
        _logo = null
    }
    if (_map) {
        _map.Destroy()
    }
    _ClearMarks();
    _ClearLines();
    _ClearPolygons();
    _ClearTileLayers(true);
    if (_workLayer) {
        _workLayer.parentNode.removeChild(_workLayer);
        _workLayer.innerHTML = ""
    }
    while (_eventsList.length > 0) {
        var events = _eventsList.pop();
        while (events.length > 0) {
            events.pop()
        }
    }
    _self.Init()
}
function _AddTileLayer(id, tileIDPattern, tileUrlPattern, visibleBounds, maxScale, minScale, interval, opacity, zIndex, tileWidth, tileHeight, layerBounds, useImageBuffer) {
    var tileLayer = _tileLayers[id];
    if (tileLayer != null) {
        return false
    }
    if (tileLayer == null) {
        tileLayer = new Object()
    }
    tileLayer.id = id;
    if (typeof(tileIDPattern) == "function") {
        tileLayer.GetTileID = tileIDPattern
    } else {
        tileLayer.tileIDPattern = tileIDPattern;
        tileLayer.GetTileID = function(tx, ty, ms) {
            var returnText = tileIDPattern.replace(/{tx}/g, tx);
            returnText = returnText.replace(/{ty}/g, ty);
            returnText = returnText.replace(/{ms}/g, ms);
            return returnText
        }
    }
    if (typeof(tileUrlPattern) == "function") {
        tileLayer.GetTileUrl = tileUrlPattern
    } else {
        tileLayer.tileUrlPattern = tileUrlPattern;
        tileLayer.GetTileUrl = function(tx, ty, ms) {
            var returnText = tileUrlPattern.replace(/{tx}/g, tx);
            returnText = returnText.replace(/{ty}/g, ty);
            returnText = returnText.replace(/{ms}/g, ms);
            return returnText
        }
    }
    if (visibleBounds) {
        if (tileLayer.visibleBounds == null) {
            tileLayer.visibleBounds = new SuperMap.IS.MapRect()
        }
        tileLayer.visibleBounds.Copy(visibleBounds)
    }
    tileLayer.maxScale = maxScale;
    tileLayer.minScale = minScale;
    tileLayer.interval = interval;
    tileLayer.opacity = opacity;
    if (zIndex <= 0) {
        tileLayer.zIndex = _customLayer_baseZIndex
    } else {
        tileLayer.zIndex = zIndex
    }
    if (tileWidth) {
        tileLayer.tileWidth = tileWidth
    } else {
        tileLayer.tileWidth = _tileSize
    }
    if (tileHeight) {
        tileLayer.tileHeight = tileHeight
    } else {
        tileLayer.tileHeight = _tileSize
    }
    if (layerBounds) {
        if (tileLayer.layerBounds == null) {
            tileLayer.layerBounds = new SuperMap.IS.MapRect()
        }
        tileLayer.layerBounds.Copy(layerBounds)
    } else {
        tileLayer.layerBounds = _curMap.GetMapBounds()
    }
    _tileLayers[tileLayer.id] = tileLayer;
    _tileLayerIDs.push(tileLayer.id);
    var container = document.createElement("div");
    container.id = tileLayer.id;
    container.style.zIndex = tileLayer.zIndex;
    container.style.position = "absolute";
    container.style.padding = "0px";
    container.style.margin = "0px";
    container.style.width = _workLayer.style.width;
    container.style.height = _workLayer.style.height;
    _workLayer.appendChild(container);
    tileLayer.container = container;
    tileLayer.useImageBuffer = useImageBuffer;
    if (tileLayer.interval && tileLayer.interval > 0) {
        tileLayer.timeID = window.setInterval(_RefreshTileLayer(tileLayer.id), tileLayer.interval)
    }
    return true
}
function _InsertTileLayer(id, tileIDPattern, tileUrlPattern, visibleBounds, maxScale, minScale, interval, opacity, zIndex, tileWidth, tileHeight, layerBounds, useImageBuffer) {
    var tileLayer = _tileLayers[id];
    if (!tileLayer) {
        return _AddTileLayer(id, tileIDPattern, tileUrlPattern, visibleBounds, maxScale, minScale, interval, opacity, zIndex, tileWidth, tileHeight, layerBounds, useImageBuffer)
    } else {
        if (typeof(tileIDPattern) == "function") {
            tileLayer.GetTileID = tileIDPattern
        } else {
            tileLayer.tileIDPattern = tileIDPattern;
            tileLayer.GetTileID = function(tx, ty, ms) {
                var returnText = tileIDPattern.replace(/{tx}/g, tx);
                returnText = returnText.replace(/{ty}/g, ty);
                returnText = returnText.replace(/{ms}/g, ms);
                return returnText
            }
        }
        if (typeof(tileUrlPattern) == "function") {
            tileLayer.GetTileUrl = tileUrlPattern
        } else {
            tileLayer.tileUrlPattern = tileUrlPattern;
            tileLayer.GetTileUrl = function(tx, ty, ms) {
                var returnText = tileUrlPattern.replace(/{tx}/g, tx);
                returnText = returnText.replace(/{ty}/g, ty);
                returnText = returnText.replace(/{ms}/g, ms);
                return returnText
            }
        }
        tileLayer.visibleBounds = visibleBounds;
        tileLayer.maxScale = maxScale;
        tileLayer.minScale = minScale;
        tileLayer.interval = interval;
        tileLayer.opacity = opacity;
        if (zIndex <= 0) {
            tileLayer.zIndex = _customLayer_baseZIndex
        } else {
            tileLayer.zIndex = zIndex
        }
        if (tileWidth) {
            tileLayer.tileWidth = tileWidth
        } else {
            tileLayer.tileWidth = _tileSize
        }
        if (tileHeight) {
            tileLayer.tileHeight = tileHeight
        } else {
            tileLayer.tileHeight = _tileSize
        }
        if (layerBounds) {
            tileLayer.layerBounds = layerBounds
        } else {
            tileLayer.layerBounds = _curMap.GetMapBounds()
        }
        tileLayer.useImageBuffer = useImageBuffer;
        if (tileLayer.timeID) {
            window.clearInterval(tileLayer.timeID)
        }
        var imageBuffer = null;
        var index = -1;
        for (var i = 0; i < _tileLayerIDs.length; i++) {
            if (id == _tileLayerIDs[i]) {
                index = i;
                break
            }
        }
        if (index == -1) {
            return
        }
        if (_imageBufferCollection[index] != null) {
            while (_imageBufferCollection[index].length > 0) {
                var imageId = _imageBufferCollection[index][0];
                delete _imageBufferCollection[index][imageId];
                _imageBufferCollection[index].shift()
            }
            _imageBufferCollection[index] = null
        }
        if (tileLayer.timeID) {
            window.clearInterval(tileLayer.timeID)
        }
        if (tileLayer.interval && tileLayer.interval > 0) {
            tileLayer.timeID = window.setInterval(_RefreshTileLayer(tileLayer.id), tileLayer.interval)
        }
        return true
    }
}
function _RemoveTileLayer(id) {
    if (!id || id == _mapDivID || id == _trackingLayerDivID || id == _geometryLayerDivID) {
        return false
    }
    if (_tileLayers[id]) {
        if (_tileLayers[id].timeID) {
            window.clearInterval(_tileLayers[id].timeID)
        }
    }
    _tileLayers[id] = null;
    var imageBuffer = null;
    var index = -1;
    for (var i = 0; i < _tileLayerIDs.length; i++) {
        if (id == _tileLayerIDs[i]) {
            index = i;
            break
        }
    }
    if (index == -1) {
        return true
    }
    if (_imageBufferCollection[index] != null) {
        while (_imageBufferCollection[index].length > 0) {
            var imageId = _imageBufferCollection[index][0];
            delete _imageBufferCollection[index][imageId];
            _imageBufferCollection[index].shift()
        }
        _imageBufferCollection[index] = null
    }
    for (var i = 0; i < _tileLayerIDs.length; i++) {
        if (_tileLayerIDs[i] == id) {
            _tileLayerIDs.splice(i, 1);
            return true
        }
    }
    return false
}
function _ClearTileLayers(bInternal) {
    var success = false;
    var length = _tileLayerIDs.length;
    for (var i = 0; i < length; i++) {
        if (!bInternal) {
            if (_tileLayerIDs[i] == _mapDivID || _tileLayerIDs[i] == _trackingLayerDivID || _tileLayerIDs[i] == _geometryLayerDivID) {
                continue
            }
        }
        if (_tileLayers[_tileLayerIDs[i]]) {
            if (_tileLayers[_tileLayerIDs[i]].timeID) {
                window.clearInterval(_tileLayers[_tileLayerIDs[i]].timeID)
            }
        }
        var imageBuffer = null;
        if (_imageBufferCollection[i] != null) {
            while (_imageBufferCollection[i].length > 0) {
                var imageId = _imageBufferCollection[i][0];
                delete _imageBufferCollection[i][imageId];
                _imageBufferCollection[i].shift()
            }
            _imageBufferCollection[i] = null
        }
        var tileLayersContainer = _tileLayers[_tileLayerIDs[i]].container;
        tileLayersContainer.parentNode.removeChild(tileLayersContainer);
        tileLayersContainer = null;
        _tileLayers[_tileLayerIDs[i]] = null;
        success = true
    }
    var index = 0;
    for (var i = 0; i < length; i++) {
        if (!bInternal) {
            if (_tileLayerIDs[i] == _mapDivID || _tileLayerIDs[i] == _trackingLayerDivID) {
                index++;
                continue
            }
        }
        var tileLayerID = _tileLayerIDs.splice(index, 1);
        tileLayerID = null
    }
    return success
}
this.CustomLayer.AddTileLayer = function(id, tileIDPattern, tileUrlPattern, visibleBounds, maxScale, minScale, interval, opacity, zIndex, tileWidth, tileHeight, layerBounds) {
    var success = _AddTileLayer(id, tileIDPattern, tileUrlPattern, visibleBounds, maxScale, minScale, interval, opacity, zIndex, tileWidth, tileHeight, layerBounds, false);
    if (success) {
        _SetTileLayerHidden();
        _RefreshMap()
    }
    return success
};
this.CustomLayer.InsertTileLayer = function(id, tileIDPattern, tileUrlPattern, visibleBounds, maxScale, minScale, interval, opacity, zIndex, tileWidth, tileHeight, layerBounds) {
    var success = _InsertTileLayer(id, tileIDPattern, tileUrlPattern, visibleBounds, maxScale, minScale, interval, opacity, zIndex, tileWidth, tileHeight, layerBounds);
    if (success) {
        _SetTileLayerHidden();
        _RefreshMap()
    }
    return success
};
this.CustomLayer.RemoveTileLayer = function(id) {
    var success = _RemoveTileLayer(id);
    if (success) {
        _SetTileLayerHidden();
        _RefreshMap()
    }
    return success
};
this.CustomLayer.ClearTileLayers = function() {
    var success = _ClearTileLayers(false);
    if (success) {
        _SetTileLayerHidden();
        _RefreshMap()
    }
    return success
};
function _GetMapTileID(tx, ty, ms) {
    return tx + "," + ty + "," + ms + "," + container.id
}
function _GetMapTileUrl(tx, ty, ms) {
    return _curMap.GetTileUrl(tx, ty, ms)
}
function _GetTrackingLayerTileID(tx, ty, ms) {
    return "tl," + tx + "," + ty + "," + ms + "," + container.id
}
function _GetTrackingLayerTileUrl(tx, ty, ms) {
    if (_trackingLayerIndex < 0) {
        return _scriptLocation + "../images/spacer.gif"
    }
    var returnString = _mapHandler + "ajax/" + encodeURI(_self.mapName) + "/" + ms + "/" + tx + "/" + ty + "/" + _trackingLayerIndex + "/" + _userID + "/" + _tileSize + "/gif/" + params.antiAlias + "/" + params.useReferBounds + "/map.ashx?GetTrackingLayerImage=true&t=" + _t + "&redirect=" + params.redirect;
    return returnString
}
function _GetGeometryLayerTileID(tx, ty, ms) {
    return "gl," + tx + "," + ty + "," + ms + "," + container.id
}
function _GetGeometryLayerTileUrl(tx, ty, ms) {
    if (_geometryKey == "") {
        return _scriptLocation + "../images/spacer.gif"
    }
    var customQueryString = "";
    if (params.customKeys) {
        for (var i = 0; i < params.customKeys.length; i++) {
            customQueryString += "&" + params.customKeys[i] + "=" + params.customValues[i]
        }
    }
    var returnString = _mapHandler + "ajax/" + encodeURI(_self.mapName) + "/" + ms + "/" + tx + "/" + ty + "/" + _tileSize + "/" + _imageFormat + "/" + _geometryKey + "/" + params.antiAlias + "/" + params.useReferBounds + "/map.ashx?GetGeometryImage=true&t=" + _t + "&redirect=" + params.redirect + customQueryString;
    return returnString
}
function _GetTileBounds(x, y, tileWidth, tileHeight, tileBounds) {
    if (!tileBounds) {
        tileBounds = mapBounds
    }
    var lb = new SuperMap.IS.PixelCoord();
    var rt = new SuperMap.IS.PixelCoord();
    lb.x = x * tileWidth;
    lb.y = (y + 1) * tileHeight;
    rt.x = (x + 1) * tileWidth;
    rt.y = (y) * tileHeight;
    var distance = _PixelToMapDistance(1, _curParam.mapScale);
    var distanceY = _PixelToMapDistance(1, _curParam.mapScale, true);
    var mlbX = tileBounds.leftBottom.x + (lb.x * distance);
    var mlbY = tileBounds.rightTop.y - (lb.y * distanceY);
    var mrtX = tileBounds.leftBottom.x + (rt.x * distance);
    var mrtY = tileBounds.rightTop.y - (rt.y * distanceY);
    var bounds = new SuperMap.IS.MapRect(mlbX, mlbY, mrtX, mrtY);
    lb = null;
    rt = null;
    return bounds
}
this.GetTileBounds = _GetTileBounds;
function _IsBoundsTouchs(tileBounds, layerBounds) {
    if (!tileBounds || !layerBounds) {
        return false
    }
    if (tileBounds.leftBottom.x < layerBounds.rightTop.x && tileBounds.rightTop.x > layerBounds.leftBottom.x && tileBounds.rightTop.y > layerBounds.leftBottom.y && tileBounds.leftBottom.y < layerBounds.rightTop.y) {
        return true
    }
    return false
}
function _RefreshTileLayer(id) {
    var refreshTileLayer = function() {
        var layerTiles = null;
        var imageBuffer = null;
        var index = -1;
        for (var i = 0; i < _tileLayerIDs.length; i++) {
            if (id == _tileLayerIDs[i]) {
                layerTiles = _tileCollections[i];
                index = i;
                break
            }
        }
        if (index == -1) {
            return
        }
        if (_imageBufferCollection[index] != null) {
            while (_imageBufferCollection[index].length > 0) {
                var imageId = _imageBufferCollection[index][0];
                delete _imageBufferCollection[index][imageId];
                _imageBufferCollection[index].shift()
            }
            _imageBufferCollection[index] = null
        }
        if (layerTiles.length > 0) {
            for (var j = 0; j < layerTiles.length; j++) {
                if (layerTiles[j] != null) {
                    layerTiles[j].RefreshUrl()
                }
            }
        }
    };
    return refreshTileLayer
}
function _SetTileLayerHidden() {
    var hiddenTileLayer = document.getElementById(container.id + "_hiddenTileLayers");
    if (!hiddenTileLayer) {
        return
    }
    if (_tileLayers == null) {
        hiddenTileLayer.value = ""
    }
    var tileLayers = new Array();
    for (var i = 0; i < _tileLayerIDs.length; i++) {
        if (_tileLayerIDs[i] == _mapDivID || _tileLayerIDs[i] == _trackingLayerDivID) {
            continue
        }
        var tileLayer = _tileLayers[_tileLayerIDs[i]];
        if (!tileLayer.tileIDPattern || !tileLayer.tileUrlPattern) {
            continue
        }
        var tileLayerTemp = new Object();
        tileLayerTemp.id = tileLayer.id;
        tileLayerTemp.tileIDPattern = tileLayer.tileIDPattern;
        tileLayerTemp.tileUrlPattern = tileLayer.tileUrlPattern;
        tileLayerTemp.visibleBounds = tileLayer.visibleBounds;
        tileLayerTemp.maxScale = tileLayer.maxScale;
        tileLayerTemp.minScale = tileLayer.minScale;
        tileLayerTemp.interval = tileLayer.interval;
        tileLayerTemp.opacity = tileLayer.opacity;
        tileLayerTemp.zIndex = tileLayer.zIndex;
        tileLayerTemp.tileWidth = tileLayer.tileWidth;
        tileLayerTemp.tileHeight = tileLayer.tileHeight;
        tileLayerTemp.layerBounds = tileLayer.layerBounds;
        tileLayers.push(tileLayerTemp)
    }
    var json = _ToJSON(tileLayers);
    hiddenTileLayer.value = json
}
function _InitTileLayerAction() {
    var hidden = document.getElementById(container.id + "_hiddenTileLayers");
    if (hidden && hidden.value) {
        var json = hidden.value;
        var tileLayers = eval(json);
        if (tileLayers) {
            for (var i = 0; i < tileLayers.length; i++) {
                if (tileLayers[i]) {
                    _AddTileLayer(tileLayers[i].id, tileLayers[i].tileIDPattern, tileLayers[i].tileUrlPattern, tileLayers[i].visibleBounds, tileLayers[i].maxScale, tileLayers[i].minScale, tileLayers[i].interval, tileLayers[i].opacity, tileLayers[i].zIndex, tileLayers[i].tileWidth, tileLayers[i].tileHeight, tileLayers[i].layerBounds, false)
                }
            }
        }
        tileLayers = null
    }
}
function _SetPrintMode(PrintMode, onComplete, onError, userContext) {
    _PrintMode = PrintMode;
    if (PrintMode) {
        function onGetMapImageComplete(url, userContext) {
            var id = _self.id + "PrintLayer";
            var container = document.getElementById(id);
            if (container == null) {
                container = document.createElement("div");
                container.id = id;
                container.style.zIndex = 3;
                container.style.position = "absolute";
                container.style.padding = "0px";
                container.style.margin = "0px";
                container.style.width = _workLayer.style.width;
                container.style.height = _workLayer.style.height;
                container.style.left = "0px";
                container.style.top = "0px"
            }
            container.style.backgroundImage = "url(" + url + ")";
            container.style.backgroundRepeat = "no-repeat";
            _workLayer.appendChild(container);
            if (onComplete) {
                onComplete(url, userContext)
            }
        }
        _curParam.pixelRect = _GetSize();
        _curMap.GetMapImage(_curParam, onGetMapImageComplete, onError, userContext)
    } else {
        var id = _self.id + "PrintLayer";
        var container = document.getElementById(id);
        if (container != null) {
            _workLayer.removeChild(container)
        }
    }
}
this.SetPrintMode = _SetPrintMode
}
SuperMap.IS.LayerControl = function(container, map, param) {
    var _map = null;
    var _layers = null;
    var _layerItems = null;
    var _checkedAllQueryable = false;
    var _checkedAllVisible = false;
    var _id = container.id;
    var _inited = false;
    var _imageFormat = 1;
    var _isNeedTitle = false;
    if (param && typeof(param.isNeedTitle) != "undefined") {
        _isNeedTitle = param.isNeedTitle
    }
    var _isShowInCurrentPage = true;
    if (param && typeof(param.isShowInCurrentPage) != "undefined") {
        _isShowInCurrentPage = param.isShowInCurrentPage
    }
    var _eventsList = new Array();
    var _eventsNameList = new Array();
    var _curLayerContextMenu;
    var _lastMapName;
    var _indexs;
    var _filterResult;
    var _headerBackColor = "White";
    if (param && typeof(param.headerBackColor) != "undefined") {
        _headerBackColor = param.headerBackColor
    }
    this.headerFont = new SuperMap.IS.Font();
    if (param && typeof(param.headerFont) != "undefined") {
        var headerFont = eval("(" + param.headerFont + ")");
        this.headerFont.FromJSON(headerFont);
        headerFont = null
    }
    var _headerForeColor = "Black";
    if (param && typeof(param.headerForeColor) != "undefined") {
        _headerForeColor = param.headerForeColor
    }
    var _itemBackColor = "White";
    if (param && typeof(param.itemBackColor) != "undefined") {
        _itemBackColor = param.itemBackColor
    }
    this.itemFont = new SuperMap.IS.Font();
    if (param && typeof(param.itemFont) != "undefined") {
        var itemFont = eval("(" + param.itemFont + ")");
        this.itemFont.FromJSON(itemFont);
        itemFont = null
    }
    var _itemForeColor = "Black";
    if (param && typeof(param.itemForeColor) != "undefined") {
        _itemForeColor = param.itemForeColor
    }
    this.layerNameText = "LayerName";
    if (param && typeof(param.layerNameText) != "undefined") {
        this.layerNameText = param.layerNameText
    }
    this.visibleText = "Visible";
    if (param && typeof(param.visibleText) != "undefined") {
        this.visibleText = param.visibleText
    }
    this.queryableText = "Queryable";
    if (param && typeof(param.queryableText) != "undefined") {
        this.queryableText = param.queryableText
    }
    this.submitButtonText = "Refresh";
    if (param && typeof(param.submitButtonText) != "undefined") {
        this.submitButtonText = param.submitButtonText
    }
    this.resetButtonText = "Reset";
    if (param && typeof(param.resetButtonText) != "undefined") {
        this.resetButtonText = param.resetButtonText
    }
    this.buttonCssClass = "";
    if (param && typeof(param.buttonCssClass) != "undefined") {
        this.buttonCssClass = param.buttonCssClass
    }
    this.checkBoxCssClass = "";
    if (param && typeof(param.checkBoxCssClass) != "undefined") {
        this.checkBoxCssClass = param.checkBoxCssClass
    }
    this.separator = "@";
    if (param && typeof(param.separator) != "undefined") {
        this.separator = param.separator
    }
    this.overFlowEnabled = true;
    if (param && typeof(param.overFlowEnabled) != "undefined") {
        this.overFlowEnabled = param.overFlowEnabled
    }
    this.quickSubmitEnabled = false;
    if (param && typeof(param.quickSubmitEnabled) != "undefined") {
        this.quickSubmitEnabled = param.quickSubmitEnabled
    }
    this.visibleEnabled = true;
    if (param && typeof(param.visibleEnabled) != "undefined") {
        this.visibleEnabled = param.visibleEnabled
    }
    this.queryableEnabled = true;
    if (param && typeof(param.queryableEnabled) != "undefined") {
        this.queryableEnabled = param.queryableEnabled
    }
    this.expand = false;
    if (param && typeof(param.expand) != "undefined") {
        this.expand = param.expand
    }
    var _isDescendServer = false;
    if (param && typeof(param.isDescendServer) != "undefined") {
        _isDescendServer = param.isDescendServer
    }
    var _sortType = "caption";
    if (param && typeof(param.sortType) != "undefined") {
        _sortType = param.sortType
    }
    var _resourceWidth = 16;
    if (param && param.resourceWidth) {
        _resourceWidth = param.resourceWidth
    }
    var _resourceHeight = 16;
    if (param && param.resourceHeight) {
        _resourceHeight = param.resourceHeight
    }
    var _themeNameEnabled = true;
    if (param && param.themeNameEnabled) {
        _themeNameEnabled = param.themeNameEnabled
    }
    this.layerFilter = null;
    var _self = this;
    var _containerInside = null;
    var _lastLayers = null;
    this.Destroy = _Destroy;
    this.SetLayerContextMenu = _SetLayerContextMenu;
    this.SaveLayerStatus = _SaveLayerStatus;
    this.SortLayerItem = _SortLayerItem;
    this.Update = _Update;
    this.AttachEvent = _AttachEvent;
    this.DetachEvent = _DetachEvent;
    if (map) {
        map.AttachEvent("oninit", _Init);
        map.AttachEvent("ondestroying", _Destroy)
    }
    function _Init() {
        if (_inited) {
            return
        }
        if (_containerInside) {
            if (_containerInside.parentNode) {
                _containerInside.parentNode.removeChild(_containerInside)
            }
            _containerInside = null
        }
        _containerInside = document.createElement("div");
        var heightUnit = "px";
        var widthUnit = "px";
        var width = container.style.pixelWidth;
        if (param && typeof(param.width) != "undefined") {
            widthUnit = _GetUnit(param.width);
            width = param.width.toString().replace(widthUnit, "")
        }
        var height = container.style.pixelHeight;
        if (param && typeof(param.height) != "undefined") {
            heightUnit = _GetUnit(param.height);
            height = param.height.toString().replace(heightUnit, "")
        }
        _containerInside.style.width = width + widthUnit;
        _containerInside.style.height = height + heightUnit;
        _containerInside.style.position = "relative";
        container.appendChild(_containerInside);
        if (_self.overFlowEnabled) {
            _containerInside.style.overflow = "auto";
            _containerInside.style.overflowX = "auto";
            _containerInside.style.overflowY = "auto"
        }
        _inited = true;
        _map = map;
        _layers = map.layers;
        _InitIndexs();
        _InitLayerFilter();
        _InitLayerItems();
        _RenderLayers();
        _InitContext();
        _lastMapName = map.mapName;
        if (map) {
            map.AttachEvent("onendswitchmap", _Update);
            map.AttachEvent("onchangelayer", _Update)
        }
    }
    function _GetUnit(value) {
        if (value.toString().indexOf("px") != -1) {
            return "px"
        } else {
            if (value.toString().indexOf("%") != -1) {
                return "%"
            }
        }
        return "px"
    }
    function _Destroy() {
        if (_map) {
            _map.DetachEvent("onendswitchmap", _Update);
            _map.DetachEvent("onchangelayer", _Update);
            map.DetachEvent("oninit", _Init);
            map.DetachEvent("ondestroying", _Destroy)
        }
        _layers = null;
        _id = null;
        _inited = null;
        _imageFormat = null;
        _isNeedTitle = null;
        _isShowInCurrentPage = null;
        _eventsList = null;
        _eventsNameList = null;
        _curLayerContextMenu = null;
        _lastMapName = null;
        _indexs = null;
        _filterResult = null;
        _headerBackColor = null;
        _self.headerFont.Destroy();
        _self.headerFont = null;
        _headerForeColor = null;
        _itemBackColor = null;
        _self.itemFont.Destroy();
        _self.itemFont = null;
        _itemForeColor = null;
        if (container) {
            container.innerHTML = ""
        }
        _self = null;
        container = null
    }
    function _InitContext() {
        var hidden = document.getElementById(container.id + "_hiddenLayerContextMenu");
        if (hidden && hidden.value) {
            var layerContextMenuJSON = hidden.value;
            _curLayerContextMenu = _JSONToAction(layerContextMenuJSON);
            _SetLayerContextMenu(_curLayerContextMenu)
        }
    }
    function _InitLayerFilter() {
        var hidden = document.getElementById(container.id + "_hiddenFilter");
        if (hidden) {
            _filterResult = hidden.value.split(",")
        }
    }
    function _InitLayerItems() {
        var hidden = document.getElementById(container.id + "_hiddenLayerItems");
        if (hidden && hidden.value) {
            _layerItems = eval(hidden.value)
        } else {
            _ResetLayerItems(map.layers)
        }
    }
    function _InitIndexs() {
        _indexs = new Array();
        for (var i = 0; i < map.layers.length; i++) {
            _indexs[i] = i
        }
    }
    function _ResetLayerItems(layers) {
        if (!layers) {
            return
        }
        if (_layerItems) {
            _layerItems = null
        }
        _layerItems = new Array();
        for (var i = 0; i < layers.length; i++) {
            _layerItems[i] = new SuperMap.IS.LayerItem();
            _layerItems[i].caption = layers[i].caption;
            _layerItems[i].visibleChecked = layers[i].visible;
            _layerItems[i].queryableChecked = layers[i].queryable;
            _layerItems[i].value = i
        }
        _SetLayerItemsHidden()
    }
    function _RenderLayers() {
        var url;
        var innerHTML = "";
        innerHTML += "<table>";
        if (_isNeedTitle) {
            innerHTML += "<tr><td ColSpan=4 align=center valign=middle>" + SuperMap.IS.LayerControlResource.title + " (" + map.mapName + ")</td></tr>"
        }
        if (typeof(_isDescendServer) == "boolean") {
            _SortLayers(_sortType, _isDescendServer);
            _isDescendServer = null
        }
        var trString = "";
        var tdString = "";
        trString += "<tr style='background-color:" + _headerBackColor + ";color:" + _headerForeColor + ";font-family:" + _self.headerFont.fontFamily.name + ";font-size:" + _self.headerFont.size + ";";
        if (_self.headerFont.bold) {
            trString += "font-weight:bold;"
        }
        if (_self.headerFont.italic) {
            trString += "font-style:itlic;"
        }
        if (_self.headerFont.underline && _self.headerFont.strikeout) {
            trString += "text-decoration:underline line-through;"
        } else {
            if (_self.headerFont.underline) {
                trString += "text-decoration:underline;"
            } else {
                if (_self.headerFont.strikeout) {
                    trString += "text-decoration:line-through;"
                }
            }
        }
        trString += "'>";
        tdString = "<td ColSpan=2>" + _self.layerNameText + "</td>";
        if (_self.visibleEnabled) {
            tdString += "<td>" + _self.visibleText + "</td>"
        }
        if (_self.queryableEnabled) {
            tdString += "<td>" + _self.queryableText + "</td>"
        }
        trString += tdString + "</tr>";
        innerHTML += trString;
        var resourcesParam = new SuperMap.IS.ResourceParam();
        resourcesParam.width = _resourceWidth;
        resourcesParam.height = _resourceHeight;
        for (var i = 0; i < _indexs.length; i++) {
            var m = _indexs[i];
            if (_layerItems[m].renderStyle == 2) {
                continue
            }
            if (!_layers[m]) {
                continue
            }
            if (_filterResult && _filterResult[m] == "false") {
                continue
            }
            if (_self.layerFilter && !_self.layerFilter(_layers[m])) {
                continue
            }
            var innerHTMLForEachLayer = "";
            var resourceType = "";
            switch (_layers[m].type) {
            case SuperMap.IS.LayerType.point:
                resourceType = 0;
                break;
            case SuperMap.IS.LayerType.line:
                resourceType = 1;
                break;
            case SuperMap.IS.LayerType.polygon:
                resourceType = 2;
                break;
            case SuperMap.IS.LayerType.grid:
                resourceType = 2;
                break;
            default:
                break
            }
            var themeImgID = _layers[m].name + "_StyleIMG";
            resourcesParam.style = _layers[m].style;
            resourcesParam.resourceType = resourceType;
            resourcesParam.imageFormat = _imageFormat;
            innerHTMLForEachLayer = "<tr style='background-color:" + _itemBackColor + ";color:" + _itemForeColor + ";font-family:" + _self.itemFont.fontFamily.name + ";font-size:" + _self.itemFont.size + ";";
            if (_self.itemFont.bold) {
                innerHTMLForEachLayer += "font-weight:bold;"
            }
            if (_self.itemFont.italic) {
                innerHTMLForEachLayer += "font-style:itlic;"
            }
            if (_self.itemFont.underline && _self.itemFont.strikeout) {
                innerHTMLForEachLayer += "text-decoration:underline line-through;"
            } else {
                if (_self.itemFont.underline) {
                    innerHTMLForEachLayer += "text-decoration:underline;"
                } else {
                    if (_self.itemFont.strikeout) {
                        innerHTMLForEachLayer += "text-decoration:line-through;"
                    }
                }
            }
            innerHTMLForEachLayer += "'>";
            var bHasTheme = false;
            if (_layers[m].themeUnique || _layers[m].themeRange || _layers[m].themeGraph || _layers[m].themeGraduatedSymbol || _layers[m].themeLabel || _layers[m].themeDotDensity || _layers[m].themeGridRange || _layers[m].themeCustom) {
                bHasTheme = true
            }
            innerHTMLForEachLayer += '<td valign="top" width="20px">';
            if (bHasTheme) {
                if (_self.expand) {
                    innerHTMLForEachLayer += '<img id="Switch_' + _id + "_" + _layers[m].name + '_Themes" src="'+_scriptLocation + '../images/expand.gif"'
                } else {
                    innerHTMLForEachLayer += '<img id="Switch_' + _id + "_" + _layers[m].name + '_Themes" src="'+_scriptLocation + '../images/collapse.gif"'
                }
            }
            innerHTMLForEachLayer += "</td><td>";
            if (_layers[m].type == SuperMap.IS.LayerType.point || _layers[m].type == SuperMap.IS.LayerType.line || _layers[m].type == SuperMap.IS.LayerType.polygon) {
                innerHTMLForEachLayer += '<img id="' + themeImgID + '"  width=' + _resourceWidth + " height=" + _resourceHeight + ' src="' + map.GenerateResourceRequest(resourcesParam) + '" >'
            } else {
                if (_layers[m].type == SuperMap.IS.LayerType.network) {
                    innerHTMLForEachLayer += '<img id="' + themeImgID + '" width=' + _resourceWidth + " height=" + _resourceHeight + ' src="'+_scriptLocation + '../images/Network.gif" >'
                } else {
                    if (_layers[m].type == SuperMap.IS.LayerType.text) {
                        innerHTMLForEachLayer += '<img id="' + themeImgID + '" width=' + _resourceWidth + " height=" + _resourceHeight + ' src="'+_scriptLocation + '../images/Text.gif" >'
                    } else {
                        if (_layers[m].type == SuperMap.IS.LayerType.image) {
                            innerHTMLForEachLayer += '<img id="' + themeImgID + '" width=' + _resourceWidth + " height=" + _resourceHeight + ' src="'+_scriptLocation + '../images/Image.gif" >'
                        } else {
                            if (_layers[m].type == SuperMap.IS.LayerType.mrSID) {
                                innerHTMLForEachLayer += '<img id="' + themeImgID + '"  width=' + _resourceWidth + " height=" + _resourceHeight + ' src="'+_scriptLocation + '../images/MRSID.gif" >'
                            } else {
                                if (_layers[m].type == SuperMap.IS.LayerType.grid) {
                                    innerHTMLForEachLayer += '<img id="' + themeImgID + '"  width=' + _resourceWidth + " height=" + _resourceHeight + ' src="'+_scriptLocation + '../images/GRID.gif" >'
                                } else {
                                    if (_layers[m].type == SuperMap.IS.LayerType.dem) {
                                        innerHTMLForEachLayer += '<img id="' + themeImgID + '"  width=' + _resourceWidth + " height=" + _resourceHeight + ' src="'+_scriptLocation + '../images/Dem.gif" >'
                                    } else {
                                        if (_layers[m].type == SuperMap.IS.LayerType.ecw) {
                                            innerHTMLForEachLayer += '<img id="' + themeImgID + '"  width=' + _resourceWidth + " height=" + _resourceHeight + ' src="'+_scriptLocation + '../images/Ecw.gif" >'
                                        } else {
                                            if (_layers[m].type == SuperMap.IS.LayerType.cad) {
                                                innerHTMLForEachLayer += '<img id="' + themeImgID + '"  width=' + _resourceWidth + " height=" + _resourceHeight + ' src="'+_scriptLocation + '../images/CAD.gif" >'
                                            } else {
                                                innerHTMLForEachLayer += '<img id="' + themeImgID + '"  width=' + _resourceWidth + " height=" + _resourceHeight + ' src="'+_scriptLocation + '../images/spacer.gif" >'
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            innerHTMLForEachLayer += '<span style="font-weight:bold" oncontextmenu =\'' + _id + ".OnContextMenu(event," + m + ");return false;'>" + _layerItems[m].caption.split(_self.separator)[0] + "</span>";
            if (_self.expand) {
                innerHTMLForEachLayer += '<table id="' + _id + "_" + _layers[m].name + '_Themes" style="display:block">'
            } else {
                innerHTMLForEachLayer += '<table id="' + _id + "_" + _layers[m].name + '_Themes" style="display:none">'
            }
            var uniqueTheme = _layers[m].themeUnique;
            if (uniqueTheme != null) {
                innerHTMLForEachLayer += WriteStartScriptPart(m, "Unique", uniqueTheme, resourceType);
                innerHTMLForEachLayer += WriteEndScriptPart()
            }
            var rangeTheme = _layers[m].themeRange;
            if (rangeTheme != null) {
                innerHTMLForEachLayer += WriteStartScriptPart(m, "Range", rangeTheme, resourceType);
                innerHTMLForEachLayer += WriteEndScriptPart()
            }
            var graphTheme = _layers[m].themeGraph;
            if (graphTheme != null) {
                innerHTMLForEachLayer += WriteStartScriptPart(m, "Graph", graphTheme, resourceType);
                innerHTMLForEachLayer += WriteEndScriptPart()
            }
            var graduatedSymbolTheme = _layers[m].themeGraduatedSymbol;
            if (graduatedSymbolTheme != null) {
                innerHTMLForEachLayer += WriteStartScriptPart(m, "GraduatedSymbol", graduatedSymbolTheme, resourceType);
                innerHTMLForEachLayer += WriteEndScriptPart()
            }
            var labelTheme = _layers[m].themeLabel;
            if (labelTheme != null) {
                innerHTMLForEachLayer += WriteStartScriptPart(m, "Label", labelTheme, resourceType);
                innerHTMLForEachLayer += WriteEndScriptPart()
            }
            var dotDemsityTheme = _layers[m].themeDotDensity;
            if (dotDemsityTheme != null) {
                innerHTMLForEachLayer += WriteStartScriptPart(m, "DotDensity", dotDemsityTheme, resourceType);
                innerHTMLForEachLayer += WriteEndScriptPart()
            }
            var gridRangeTheme = _layers[m].themeGridRange;
            if (gridRangeTheme != null) {
                innerHTMLForEachLayer += WriteStartScriptPart(m, "GridRange", gridRangeTheme, resourceType);
                innerHTMLForEachLayer += WriteEndScriptPart()
            }
            var customTheme = _layers[m].themeCustom;
            if (customTheme != null) {
                innerHTMLForEachLayer += WriteStartScriptPart(m, "Custom", customTheme, resourceType);
                innerHTMLForEachLayer += WriteEndScriptPart()
            }
            innerHTMLForEachLayer += "</table></td>";
            if (_self.visibleEnabled) {
                innerHTMLForEachLayer += "<td valign=top><input type='checkbox' id='" + container.id + "_" + _layers[m].name + ":V'";
                if (_self.checkBoxCssClass) {
                    innerHTMLForEachLayer += "class='" + _self.checkBoxCssClass + "'"
                }
                if (_layerItems[m].renderStyle = 1) {
                    innerHTMLForEachLayer += " readonly=true "
                }
                if (_layerItems[m].visibleChecked) {
                    innerHTMLForEachLayer += "checked='checked'  /></td>"
                } else {
                    innerHTMLForEachLayer += " /></td>"
                }
            }
            if (_self.queryableEnabled) {
                innerHTMLForEachLayer += "<td valign=top><input type='checkbox' id='" + container.id + "_" + _layers[m].name + ":Q'";
                if (_self.checkBoxCssClass) {
                    innerHTMLForEachLayer += "class='" + _self.checkBoxCssClass + "'"
                }
                if (_layerItems[m].renderStyle = 1) {
                    innerHTMLForEachLayer += " readonly=true "
                }
                if (_layerItems[m].queryableChecked) {
                    innerHTMLForEachLayer += "checked='checked'  /></td>"
                } else {
                    innerHTMLForEachLayer += " /></td>"
                }
            }
            innerHTMLForEachLayer += "</tr>";
            innerHTML += innerHTMLForEachLayer
        }
        trString = "<tr>";
        tdString = "<td ColSpan=2><input type='button'  id='save' value='" + _self.submitButtonText + "'";
        if (_self.buttonCssClass) {
            tdString += "class='" + _self.buttonCssClass + "'"
        }
        tdString += "/>";
        tdString += "<input type='reset' id='reset' value='" + _self.resetButtonText + "'";
        if (_self.buttonCssClass) {
            tdString += "class='" + _self.buttonCssClass + "'"
        }
        tdString += " /></td>";
        if (_self.visibleEnabled) {
            tdString += "<td><input type='checkbox' id='" + container.id + "_CheckVisibleAll'";
            if (_self.checkBoxCssClass) {
                tdString += "class='" + _self.checkBoxCssClass + "'"
            }
            if (_checkedAllVisible) {
                tdString += " checked "
            }
            tdString += "/></td>"
        }
        if (_self.queryableEnabled) {
            tdString += "<td><input type='checkbox' id='" + container.id + "_CheckQueryableAll'";
            if (_self.checkBoxCssClass) {
                tdString += "class='" + _self.checkBoxCssClass + "'"
            }
            if (_checkedAllQueryable) {
                tdString += " checked "
            }
            tdString += " /></td>"
        }
        trString += tdString + "</tr>";
        innerHTML += trString;
        innerHTML += "</table>";
        var form = document.createElement("form");
        form.id = "layersForm";
        form.innerHTML = innerHTML;
        var layersForm = document.getElementById("layersForm");
        if (_containerInside.contains(layersForm)) {
            _containerInside.removeChild(layersForm)
        }
        _containerInside.appendChild(form);
        _JudgeMethod("save", _SaveLayerStatus);
        _JudgeMethod(container.id + "_CheckVisibleAll", _CheckedAllVisible);
        _JudgeMethod(container.id + "_CheckQueryableAll", _CheckedAllQueryable);
        for (var i = 0; i < _indexs.length; i++) {
            var m = _indexs[i];
            var bHasTheme = false;
            if (_layerItems[m].renderStyle == 2) {
                continue
            }
            if (!_layers[m]) {
                continue
            }
            if (_filterResult && _filterResult[m] == "false") {
                continue
            }
            if (_self.layerFilter && !_self.layerFilter(_layers[m])) {
                continue
            }
            if (_layers[m].themeUnique || _layers[m].themeRange || _layers[m].themeGraph || _layers[m].themeGraduatedSymbol || _layers[m].themeLabel || _layers[m].themeDotDensity || _layers[m].themeGridRange || _layers[m].themeCustom) {
                bHasTheme = true
            }
            if (bHasTheme) {
                _JudgeMethod("Switch_" + _id + "_" + _layers[m].name + "_Themes", 
                function(e) {
                    _SwitchThemesDisplay(e)
                })
            }
            var uniqueTheme = _layers[m].themeUnique;
            if (uniqueTheme != null) {
                _JudgeMethod("Switch_" + _id + "_" + _layers[m].name + "_Unique", 
                function(e) {
                    _SwitchThemesDisplay(e)
                });
                if (_self.quickSubmitEnabled) {
                    _JudgeMethod(_id + "_" + m + "_Unique", 
                    function(e) {
                        _SwitchThemesVisible(e)
                    })
                }
            }
            var rangeTheme = _layers[m].themeRange;
            if (rangeTheme != null) {
                _JudgeMethod("Switch_" + _id + "_" + _layers[m].name + "_Range", 
                function(e) {
                    _SwitchThemesDisplay(e)
                });
                if (_self.quickSubmitEnabled) {
                    _JudgeMethod(_id + "_" + m + "_Range", 
                    function(e) {
                        _SwitchThemesVisible(e)
                    })
                }
            }
            var graphTheme = _layers[m].themeGraph;
            if (graphTheme != null) {
                _JudgeMethod("Switch_" + _id + "_" + _layers[m].name + "_Graph", 
                function(e) {
                    _SwitchThemesDisplay(e)
                });
                if (_self.quickSubmitEnabled) {
                    _JudgeMethod(_id + "_" + m + "_Graph", 
                    function(e) {
                        _SwitchThemesVisible(e)
                    })
                }
            }
            var graduatedSymbolTheme = _layers[m].themeGraduatedSymbol;
            if (graduatedSymbolTheme != null) {
                _JudgeMethod("Switch_" + _id + "_" + _layers[m].name + "_GraduatedSymbol", 
                function(e) {
                    _SwitchThemesDisplay(e)
                });
                if (_self.quickSubmitEnabled) {
                    _JudgeMethod(_id + "_" + m + "_GraduatedSymbol", 
                    function(e) {
                        _SwitchThemesVisible(e)
                    })
                }
            }
            var labelTheme = _layers[m].themeLabel;
            if (labelTheme != null) {
                if (_self.quickSubmitEnabled) {
                    _JudgeMethod(_id + "_" + m + "_Label", 
                    function(e) {
                        _SwitchThemesVisible(e)
                    })
                }
            }
            var dotDemsityTheme = _layers[m].themeDotDensity;
            if (dotDemsityTheme != null) {
                _JudgeMethod("Switch_" + _id + "_" + _layers[m].name + "_DotDensity", 
                function(e) {
                    _SwitchThemesDisplay(e)
                });
                if (_self.quickSubmitEnabled) {
                    _JudgeMethod(_id + "_" + m + "_DotDensity", 
                    function(e) {
                        _SwitchThemesVisible(e)
                    })
                }
            }
            var gridRangeTheme = _layers[m].themeGridRange;
            if (gridRangeTheme != null) {
                _JudgeMethod("Switch_" + _id + "_" + _layers[m].name + "_GridRange", 
                function(e) {
                    _SwitchThemesDisplay(e)
                });
                if (_self.quickSubmitEnabled) {
                    _JudgeMethod(_id + "_" + m + "_GridRange", 
                    function(e) {
                        _SwitchThemesVisible(e)
                    })
                }
            }
            var customTheme = _layers[m].themeCustom;
            if (customTheme != null) {
                if (_self.quickSubmitEnabled) {
                    _JudgeMethod(_id + "_" + m + "_Custom", 
                    function(e) {
                        _SwitchThemesVisible(e)
                    })
                }
            }
            if (_self.visibleEnabled && _self.quickSubmitEnabled) {
                _JudgeMethod(container.id + "_" + _layers[m].name + ":V", _SaveLayerStatus)
            }
            if (_self.queryableEnabled && _self.quickSubmitEnabled) {
                _JudgeMethod(container.id + "_" + _layers[m].name + ":Q", _SaveLayerStatus)
            }
        }
        _lastLayers = new Array();
        for (var i = 0; i < _layers.length; i++) {
            _lastLayers[i] = new SuperMap.IS.Layer();
            _lastLayers[i].FromJSON(_layers[i])
        }
    }
    function _JudgeMethod(id, method) {
        var inputbox = document.getElementById(id);
        if (inputbox != null) {
            if (_ygPos.browser == "ie") {
                inputbox.attachEvent("onclick", method)
            } else {
                inputbox.addEventListener("click", method, true)
            }
            inputbox = null
        }
    }
    function _AttachEvent(event, listener) {
        var events = _eventsList[event];
        if (!events) {
            events = new Array();
            _eventsList[event] = events;
            _eventsNameList.push(event)
        }
        for (var i = 0; i < events.length; i++) {
            if (events[i] == listener) {
                return true
            }
        }
        events.push(listener)
    }
    function _DetachEvent(event, listener) {
        var events = _eventsList[event];
        if (!events) {
            return
        }
        for (var i = 0; i < events.length; i++) {
            if (events[i] == listener) {
                events.splice(i, 1)
            }
        }
    }
    function _TriggerEvent(event, arguments, userContext) {
        var events = _eventsList[event];
        if (!events) {
            return
        }
        if (!arguments) {
            arguments = _GenerateEventArg()
        }
        var eventsTemp = events.concat();
        for (var i = 0; i < eventsTemp.length; i++) {
            if (eventsTemp[i]) {
                eventsTemp[i](arguments, userContext)
            }
        }
        while (eventsTemp.length > 0) {
            eventsTemp.pop()
        }
    }
    function _GenerateEventArg() {
        var arguments = _map.layers;
        return arguments
    }
    this.TriggerServerEvent = function(eventName, e) {
        eval(container.id + "_doPostBack(container.id, eventName)")
    };
    function WriteStartScriptPart(index, themeType, theme, resourceType) {
        var str = '<tr><td valign="top" width="20px">';
        if (themeType != "Label" && themeType != "Custom") {
            str += '<img id="Switch_' + _id + "_" + _layers[index].name + "_" + themeType + '"  src="'+_scriptLocation + '../images/expand.gif">'
        }
        var themeName = "";
        if (theme != null) {
            themeName = theme.caption
        }
        var picName = "";
        var themeInnerHTML = "";
        var resourcesParam = new SuperMap.IS.ResourceParam();
        resourcesParam.width = _resourceWidth;
        resourcesParam.height = _resourceHeight;
        switch (themeType) {
        case "DotDensity":
            picName = "ThemeDotDensity.gif";
            var dotDemsityTheme = theme;
            if (dotDemsityTheme != null) {
                var imgID = _layers[index].name + "_ThemeDotDensity_IMG";
                resourcesParam.style = dotDemsityTheme.dotStyle;
                resourcesParam.resourceType = 0;
                resourcesParam.imageFormat = _imageFormat;
                themeInnerHTML += '<div><img id="' + imgID + '" src="' + map.GenerateResourceRequest(resourcesParam) + '" >' + dotDemsityTheme.dotValue + "</div>"
            }
            break;
        case "GraduatedSymbol":
            picName = "ThemeGraduatedSymbol.gif";
            var graduatedSymbolTheme = theme;
            if (graduatedSymbolTheme != null) {
                var imgID;
                imgID = _layers[index].name + "_ThemeGraph_IMG_0";
                resourcesParam.style = graduatedSymbolTheme.styleForPositive;
                resourcesParam.resourceType = 0;
                resourcesParam.imageFormat = _imageFormat;
                themeInnerHTML += '<Div><img id="' + imgID + '" src="' + map.GenerateResourceRequest(resourcesParam) + '" >' + SuperMap.IS.LayerControlResource.positive + "</div>";
                if (graduatedSymbolTheme.showNegative == true) {
                    imgID = _layers[index].name + "_ThemeGraph_IMG_1";
                    resourcesParam.style = graduatedSymbolTheme.styleForNegative;
                    resourcesParam.resourceType = 0;
                    resourcesParam.imageFormat = _imageFormat;
                    themeInnerHTML += '<Div><img id="' + imgID + '" src="' + map.GenerateResourceRequest(resourcesParam) + '" >' + SuperMap.IS.LayerControlResource.negative + "</div>"
                }
            }
            break;
        case "Graph":
            picName = "ThemeGraph.gif";
            var graphTheme = theme;
            if (graphTheme != null) {
                for (var j = 0; j < graphTheme.graphStyles.length; j++) {
                    var imgID = _layers[index].name + "_ThemeGraph_IMG" + j;
                    resourcesParam.style = graphTheme.graphStyles[j];
                    resourcesParam.resourceType = 2;
                    resourcesParam.imageFormat = _imageFormat;
                    themeInnerHTML += '<div><img id="' + imgID + '" src="' + map.GenerateResourceRequest(resourcesParam) + '" >' + graphTheme.expressions[j] + "</div>"
                }
            }
            break;
        case "Label":
            picName = "ThemeLabel.gif";
            break;
        case "Range":
            picName = "ThemeRange.gif";
            var rangeTheme = theme;
            if (rangeTheme != null) {
                for (var j = 0; j < rangeTheme.displays.length; j++) {
                    var imgID = _layers[index].name + "_ThemeRange_IMG" + j;
                    resourcesParam.style = rangeTheme.displays[j];
                    resourcesParam.resourceType = resourceType;
                    resourcesParam.imageFormat = _imageFormat;
                    var value = "";
                    if (j == 0) {
                        value = "x<" + rangeTheme.breakValues[j]
                    } else {
                        if (j == rangeTheme.displays.length - 1) {
                            value = rangeTheme.breakValues[j - 1] + "<=x"
                        } else {
                            value = rangeTheme.breakValues[j - 1] + "<=x<" + rangeTheme.breakValues[j]
                        }
                    }
                    themeInnerHTML += '<div><img id="' + imgID + '" src="' + map.GenerateResourceRequest(resourcesParam) + '" >' + value + "</div>"
                }
            }
            break;
        case "Unique":
            picName = "ThemeUnique.gif";
            var uniqueTheme = theme;
            if (uniqueTheme != null) {
                var imgID = _layers[index].name + "_ThemeUnique_IMG";
                for (var j = 0; j < uniqueTheme.values.length; j++) {
                    resourcesParam.style = uniqueTheme.displays[j];
                    resourcesParam.resourceType = resourceType;
                    resourcesParam.imageFormat = _imageFormat;
                    themeInnerHTML += '<div><img id="' + imgID + '"src="' + map.GenerateResourceRequest(resourcesParam) + '">' + uniqueTheme.itemCaptions[j] + "</div>"
                }
            }
            break;
        case "Custom":
            themeName = SuperMap.IS.ThemeResource.themeCustom;
            picName = "ThemeCustom.gif";
            break;
        case "GridRange":
            picName = "ThemeRange.gif";
            var gridRangeTheme = theme;
            if (gridRangeTheme != null) {
                for (var j = 0; j < gridRangeTheme.displays.length; j++) {
                    var imgID = _layers[index].name + "_ThemeRange_IMG" + j;
                    resourcesParam.style = new SuperMap.IS.Style();
                    resourcesParam.style.brushColor = gridRangeTheme.displays[j];
                    resourcesParam.resourceType = resourceType;
                    resourcesParam.imageFormat = _imageFormat;
                    if (gridRangeTheme.breakValues[0] == null) {
                        str = '<tr><td valign="top" width="20px">';
                        continue
                    }
                    var value = "";
                    if (j == 0) {
                        value = "x< " + gridRangeTheme.breakValues[j]
                    } else {
                        if (j == gridRangeTheme.displays.length - 1) {
                            value = gridRangeTheme.breakValues[j - 1] + "<=x"
                        } else {
                            value = gridRangeTheme.breakValues[j - 1] + "<=x<" + gridRangeTheme.breakValues[j]
                        }
                    }
                    themeInnerHTML += '<div><img id="' + imgID + '" src="' + map.GenerateResourceRequest(resourcesParam) + '" >' + value + "</div>"
                }
            }
            break
        }
        str += '</td><td><table><tr><td valign="top" width="20px"> <img src="'+_scriptLocation + '../images/' + picName + '"> </td><td>';
        if (_themeNameEnabled == true) {
            str += themeName
        }
        str += "</td><tr><td></td><td>";
        str += '<div id="' + _id + "_" + _layers[index].name + "_" + themeType + '" style="display:block">';
        str += themeInnerHTML;
        str += "</div></td></tr></table></td>";
        str += '<td valign="top" width="20px">';
        if (theme.enabled) {
            str += "<input id='" + _id + "_" + index + "_" + themeType + "' type=checkbox checked ";
            str += "></td>"
        } else {
            str += "<input id='" + _id + "_" + index + "_" + themeType + "' type=checkbox  ";
            str += "></td>"
        }
        return str
    }
    function WriteEndScriptPart() {
        var str = "</tr>";
        return str
    }
    function onGetResourceComplete(url, userContext) {
        var img = document.getElementById(userContext);
        img.src = url
    }
    function onGetResourceError() {}
    this.SwitchThemesDisplay = _SwitchThemesDisplay;
    function _SwitchThemesDisplay(e) {
        var strId = e.srcElement.id;
        var strNew = strId.replace("Switch_", "");
        var img = document.getElementById(strId);
        var o = document.getElementById(strNew);
        if (o == null || img == null) {
            return
        }
        if (o.style.display == "block") {
            o.style.display = "none";
            img.src = _scriptLocation + "../images/collapse.gif"
        } else {
            o.style.display = "block";
            img.src = _scriptLocation + "../images/expand.gif"
        }
    }
    this.SwitchThemesVisible = _SwitchThemesVisible;
    function _SwitchThemesVisible(e) {
        var strids = new Array();
        strIds = e.srcElement.id.split("_");
        var index = strIds[1];
        var themeType = strIds[2];
        var value = document.getElementById(e.srcElement.id).checked;
        if (!index || !themeType) {
            return
        }
        if (index == -1) {
            return
        }
        switch (themeType) {
        case "DotDensity":
            if (_layers[index].themeDotDensity) {
                _layers[index].themeDotDensity.enabled = value
            }
            break;
        case "GraduatedSymbol":
            if (_layers[index].themeGraduatedSymbol) {
                _layers[index].themeGraduatedSymboly.enabled = value
            }
            break;
        case "Graph":
            if (_layers[index].themeGraph) {
                _layers[index].themeGraph.enabled = value
            }
            break;
        case "Label":
            if (_layers[index].themeLabel) {
                _layers[index].themeLabel.enabled = value
            }
            break;
        case "Range":
            if (_layers[index].themeRange) {
                _layers[index].themeRange.enabled = value
            }
            break;
        case "Unique":
            if (_layers[index].themeUnique) {
                _layers[index].themeUnique.enabled = value
            }
            break;
        case "Custom":
            if (_layers[index].themeCustom) {
                _layers[index].themeCustom.enabled = value
            }
            break;
        case "GridRange":
            if (_layers[index].themeGridRange) {
                _layers[index].themeGridRange.enabled = value
            }
            break
        }
        strids = null;
        index = null;
        themeType = null;
        value = null;
        map.Update();
        _TriggerEvent("themeswitched", null, "ThemeSwitched")
    }
    this.OnContextMenu = function(e, index) {
        e = _GetEvent(e);
        _CancelBubble(e);
        e.index = index;
        if (_curLayerContextMenu && _curLayerContextMenu.OpenContextMenu) {
            _curLayerContextMenu.OpenContextMenu(e, _self)
        }
        return false
    };
    function _SetLayerContextMenu(layerContextMenu) {
        if (layerContextMenu) {
            layerContextMenu.Init(_map)
        }
        _curLayerContextMenu = layerContextMenu;
        _curLayerContextMenu.Init(_map);
        _SetLayerContextMenuHidden()
    }
    function _SetLayerContextMenuHidden() {
        var hidden = document.getElementById(container.id + "_hiddenLayerContextMenu");
        if (hidden) {
            hidden.value = _curLayerContextMenu.json
        }
    }
    function _SetLayerItemsHidden() {
        var layerItemsJSON = _ToJSON(_layerItems);
        var hidden = document.getElementById(container.id + "_hiddenLayerItems");
        if (hidden) {
            hidden.value = layerItemsJSON
        }
    }
    function _SaveLayerStatus() {
        var changed = false;
        for (var i = 0; i < map.layers.length; i++) {
            var layer = map.layers[i];
            if (layer) {
                var theme = layer.themeDotDensity;
                if (theme) {
                    var checkboxTheme = document.getElementById(_id + "_" + i + "_DotDensity");
                    if (theme.enable != checkboxTheme.checked) {
                        theme.enable = checkboxTheme.checked;
                        changed = true
                    }
                }
                theme = layer.themeGraduatedSymbol;
                if (theme) {
                    var checkboxTheme = document.getElementById(_id + "_" + i + "_GraduatedSymbol");
                    if (theme.enable != checkboxTheme.checked) {
                        theme.enable = checkboxTheme.checked;
                        changed = true
                    }
                }
                theme = layer.themeGraph;
                if (theme) {
                    var checkboxTheme = document.getElementById(_id + "_" + i + "_Graph");
                    if (theme.enabled != checkboxTheme.checked) {
                        theme.enabled = checkboxTheme.checked;
                        changed = true
                    }
                }
                theme = layer.themeLabel;
                if (theme) {
                    var checkboxTheme = document.getElementById(_id + "_" + i + "_Label");
                    if (theme.enabled != checkboxTheme.checked) {
                        theme.enabled = checkboxTheme.checked;
                        changed = true
                    }
                }
                theme = layer.themeRange;
                if (theme) {
                    var checkboxTheme = document.getElementById(_id + "_" + i + "_Range");
                    if (theme.enabled != checkboxTheme.checked) {
                        theme.enabled = checkboxTheme.checked;
                        changed = true
                    }
                }
                theme = layer.themeUnique;
                if (theme) {
                    var checkboxTheme = document.getElementById(_id + "_" + i + "_Unique");
                    if (theme.enabled != checkboxTheme.checked) {
                        theme.enabled = checkboxTheme.checked;
                        changed = true
                    }
                }
                theme = layer.themeGridRange;
                if (theme) {
                    var checkboxTheme = document.getElementById(_id + "_" + i + "_GridRange");
                    if (checkboxTheme != null) {
                        if (theme.enabled != checkboxTheme.checked) {
                            theme.enabled = checkboxTheme.checked;
                            changed = true
                        }
                    }
                }
                theme = layer.themeCustom;
                if (theme) {
                    var checkboxTheme = document.getElementById(_id + "_" + i + "_Custom");
                    if (checkboxTheme != null) {
                        if (theme.enabled != checkboxTheme.checked) {
                            theme.enabled = checkboxTheme.checked;
                            changed = true
                        }
                    }
                }
            }
            var checkboxV = document.getElementById(container.id + "_" + layer.name + ":V");
            if (checkboxV) {
                var visible = checkboxV.checked;
                if (_layerItems[i].visibleChecked != visible) {
                    map.layers[i].visible = visible;
                    _layerItems[i].visibleChecked = visible;
                    changed = true
                }
            }
            var checkboxQ = document.getElementById(container.id + "_" + layer.name + ":Q");
            if (checkboxQ) {
                var queryable = checkboxQ.checked;
                if (_layerItems[i].queryableChecked != queryable) {
                    map.layers[i].queryable = queryable;
                    _layerItems[i].queryableChecked = queryable;
                    changed = true
                }
            }
        }
        if (changed) {
            map.Update();
            _TriggerEvent("layeritemschanged", null, "LayerItemsChanged")
        }
    }
    this.CheckedAllQueryable = _CheckedAllQueryable;
    function _CheckedAllQueryable() {
        var queryableCheckboxAll = document.getElementById(container.id + "_CheckQueryableAll");
        for (var i = 0; i < map.layers.length; i++) {
            var layer = map.layers[i];
            var queryableCheckbox = document.getElementById(container.id + "_" + layer.name + ":Q");
            if (queryableCheckbox) {
                queryableCheckbox.checked = queryableCheckboxAll.checked
            }
        }
        _checkedAllQueryable = queryableCheckboxAll.checked
    }
    this.CheckedAllVisible = _CheckedAllVisible;
    function _CheckedAllVisible() {
        var visibleCheckboxAll = document.getElementById(container.id + "_CheckVisibleAll");
        for (var i = 0; i < map.layers.length; i++) {
            var layer = map.layers[i];
            var visibleCheckbox = document.getElementById(container.id + "_" + layer.name + ":V");
            if (visibleCheckbox) {
                visibleCheckbox.checked = visibleCheckboxAll.checked
            }
        }
        _checkedAllVisible = visibleCheckboxAll.checked
    }
    function _SortLayers(sortType, isDescend) {
        if (!isDescend) {
            isDescend = false
        }
        for (var i = 0; i < _indexs.length - 1; i++) {
            for (var j = i + 1; j < _indexs.length; j++) {
                var interchange = false;
                if (_Compare(_layerItems[_indexs[i]], _layerItems[_indexs[j]], sortType) == isDescend) {
                    interchange = true
                }
                if (!interchange) {
                    var tempIndex = _indexs[i];
                    _indexs[i] = _indexs[j];
                    _indexs[j] = tempIndex
                }
            }
        }
    }
    function _SortLayerItem(sortType, isDescend) {
        _SortLayers(sortType, isDescend);
        _RenderLayers(this)
    }
    function _Compare(layerItemP, layerItemN, sortType) {
        var valueP;
        var valueN;
        switch (sortType) {
        case 0:
            valueP = layerItemP.value;
            valueN = layerItemN.value;
            break;
        case 1:
            valueP = layerItemP.caption;
            valueN = layerItemN.caption;
            break;
        case 2:
            valueP = layerItemP.order;
            valueN = layerItemN.order;
            break
        }
        if (valueP >= valueN) {
            return true
        } else {
            return false
        }
    }
    function _UpdateCheckBox(layer) {
        var checkboxV = document.getElementById(container.id + "_" + layer.name + ":V");
        var checkboxQ = document.getElementById(container.id + "_" + layer.name + ":Q");
        if (layer.visible) {
            checkboxV.checked = "checked"
        } else {
            checkboxV.checked = ""
        }
        if (layer.queryable) {
            checkboxQ.checked = "checked"
        } else {
            checkboxQ.checked = ""
        }
    }
    function _Update() {
        _ResetLayerItems(map.layers);
        _inited = false;
        _Init()
    }
    function _GetResult(arg) {
        if (_filterResult.join(",") == arg) {
            return
        }
        _filterResult = arg.split(",");
        if (_lastMapName != map.mapName) {
            _layers = map.layers;
            for (var i = 0; i < _layers.length; i++) {
                _indexs[i] = i
            }
            _lastMapName = map.mapName
        }
        if (_isShowInCurrentPage) {
            _RenderLayers()
        } else {
            container.style.visibility = "hidden"
        }
    }
    this.GetLayerItemByIndex = function(index) {
        if (index == null || typeof index == "undefined") {
            return null
        }
        if (!_layerItems) {
            return null
        }
        if (_layerItems[index]) {
            return _layerItems[index]
        }
        return null
    };
    this.GetLayerItemByValue = function(value) {
        if (!_layerItems || value == null || typeof value == "undefined") {
            return null
        }
        for (var i = 0; i < _layerItems.length; i++) {
            if (_layerItems[i].value == value) {
                return _layerItems[i]
            }
        }
    }
}
SuperMap.IS.LegendControl = function(container, map, param) {
    var _map = null;
    var _layers = null;
    var _id = container.id;
    var _inited = false;
    var _imageFormat = 1;
    var _isNeedTitle = false;
    if (param && typeof(param.isNeedTitle) != "undefined") {
        _isNeedTitle = param.isNeedTitle
    }
    var _isShowInCurrentPage = true;
    if (param && typeof(param.isShowInCurrentPage) != "undefined") {
        _isShowInCurrentPage = param.isShowInCurrentPage
    }
    var _legendUrl = "";
    if (param && typeof(param.legendUrl) != "undefined") {
        _legendUrl = param.legendUrl
    }
    var _eventsList = new Array();
    var _eventsNameList = new Array();
    var _indexs;
    var _headerBackColor = "White";
    if (param && typeof(param.headerBackColor) != "undefined") {
        _headerBackColor = param.headerBackColor
    }
    this.titleFont = new SuperMap.IS.Font();
    if (param && typeof(param.titleFont) != "undefined") {
        var titleFont = eval("(" + param.titleFont + ")");
        this.titleFont.FromJSON(titleFont);
        titleFont = null
    }
    var _headerForeColor = "Black";
    if (param && typeof(param.headerForeColor) != "undefined") {
        _headerForeColor = param.headerForeColor
    }
    var _itemBackColor = "White";
    if (param && typeof(param.itemBackColor) != "undefined") {
        _itemBackColor = param.itemBackColor
    }
    this.itemFont = new SuperMap.IS.Font();
    if (param && typeof(param.itemFont) != "undefined") {
        var itemFont = eval("(" + param.itemFont + ")");
        this.itemFont.FromJSON(itemFont);
        itemFont = null
    }
    var _itemForeColor = "Black";
    if (param && typeof(param.itemForeColor) != "undefined") {
        _itemForeColor = param.itemForeColor
    }
    this.separator = "@";
    if (param && typeof(param.separator) != "undefined") {
        this.separator = param.separator
    }
    this.overFlowEnabled = true;
    if (param && typeof(param.overFlowEnabled) != "undefined") {
        this.overFlowEnabled = param.overFlowEnabled
    }
    this.expand = false;
    if (param && typeof(param.expand) != "undefined") {
        this.expand = param.expand
    }
    var _resourceWidth = 16;
    if (param && param.resourceWidth) {
        _resourceWidth = param.resourceWidth
    }
    var _resourceHeight = 16;
    if (param && param.resourceHeight) {
        _resourceHeight = param.resourceHeight
    }
    var _themeNameEnabled = true;
    if (param && param.themeNameEnabled) {
        _themeNameEnabled = param.themeNameEnabled
    }
    var _self = this;
    var _containerInside = null;
    var _lastLayers = null;
    this.Destroy = _Destroy;
    this.Update = _Update;
    if (map) {
        map.AttachEvent("oninit", _Init);
        map.AttachEvent("ondestroying", _Destroy)
    }
    function _Init() {
        if (_inited) {
            return
        }
        if (_containerInside) {
            if (_containerInside.parentNode) {
                _containerInside.parentNode.removeChild(_containerInside)
            }
            _containerInside = null
        }
        _containerInside = document.createElement("div");
        var heightUnit = "px";
        var widthUnit = "px";
        var width = container.style.pixelWidth;
        if (param && typeof(param.width) != "undefined") {
            widthUnit = _GetUnit(param.width);
            width = param.width.toString().replace(widthUnit, "")
        }
        var height = container.style.pixelHeight;
        if (param && typeof(param.height) != "undefined") {
            heightUnit = _GetUnit(param.height);
            height = param.height.toString().replace(heightUnit, "")
        }
        _containerInside.style.width = width + widthUnit;
        _containerInside.style.height = height + heightUnit;
        _containerInside.style.position = "relative";
        container.appendChild(_containerInside);
        if (_self.overFlowEnabled) {
            _containerInside.style.overflow = "auto";
            _containerInside.style.overflowX = "auto";
            _containerInside.style.overflowY = "auto"
        }
        _inited = true;
        _map = map;
        _layers = map.layers;
        _InitIndexs();
        if (_isShowInCurrentPage) {
            _RenderLayers()
        } else {
            container.style.visibility = "hidden"
        }
        if (map) {
            map.AttachEvent("onendswitchmap", _Update);
            map.AttachEvent("onchangelayer", _Update)
        }
    }
    function _GetUnit(value) {
        if (value.toString().indexOf("px") != -1) {
            return "px"
        } else {
            if (value.toString().indexOf("%") != -1) {
                return "%"
            }
        }
        return "px"
    }
    function _Destroy() {
        if (_map) {
            map.DetachEvent("onendswitchmap", _Update);
            map.DetachEvent("onchangelayer", _Update);
            map.DetachEvent("oninit", _Init);
            map.DetachEvent("ondestroying", _Destroy)
        }
        if (_layers) {
            while (_layers.length > 0) {
                var layer = _layers.pop();
                layer.Destroy();
                layer = null
            }
            _layers = null
        }
        if (_lastLayers) {
            while (_lastLayers.length > 0) {
                var layer = _lastLayers.pop();
                layer.Destroy();
                layer = null
            }
            _lastLayers = null
        }
        _id = null;
        _inited = null;
        _imageFormat = null;
        _isNeedTitle = null;
        _isShowInCurrentPage = null;
        _legendUrl = null;
        if (_eventsList) {
            while (_eventsList.length > 0) {
                _eventsList.pop()
            }
            _eventsList = null
        }
        if (_eventsNameList) {
            while (_eventsNameList.length > 0) {
                _eventsNameList.pop()
            }
            _eventsNameList = null
        }
        if (_indexs) {
            while (_indexs.length > 0) {
                _indexs.pop()
            }
            _indexs = null
        }
        _headerBackColor = null;
        _self.titleFont.Destroy();
        _self.titleFont = null;
        _headerForeColor = null;
        _itemBackColor = null;
        _self.itemFont.Destroy();
        _self.itemFont = null;
        _itemForeColor = null;
        if (container) {
            container.innerHTML = ""
        }
        _self = null;
        container = null
    }
    function _InitIndexs() {
        if (_indexs == null) {
            _indexs = new Array()
        }
        for (var i = 0; _layers && i < _layers.length; i++) {
            _indexs[i] = i
        }
    }
    function _RenderLayers() {
        var url;
        var innerHTML = "";
        innerHTML += "<table>";
        if (_isNeedTitle) {
            innerHTML += "<tr style='background-color:" + _headerBackColor + ";color:" + _headerForeColor + ";font-family:" + _self.titleFont.fontFamily.name + ";font-size:" + _self.titleFont.size + ";";
            if (_self.titleFont.bold) {
                innerHTML += "font-weight:bold;"
            }
            if (_self.titleFont.italic) {
                innerHTML += "font-style:itlic;"
            }
            if (_self.titleFont.underline && _self.titleFont.strikeout) {
                innerHTML += "text-decoration:underline line-through;"
            } else {
                if (_self.titleFont.underline) {
                    innerHTML += "text-decoration:underline;"
                } else {
                    if (_self.titleFont.strikeout) {
                        innerHTML += "text-decoration:line-through;"
                    }
                }
            }
            innerHTML += "'>";
            innerHTML += "<td ColSpan=4 align=center valign=middle>" + SuperMap.IS.LegendControlResource.title + " (" + map.mapName + ")</td>";
            innerHTML += "</tr>"
        }
        var trString = "";
        var tdString = "";
        innerHTML += trString;
        var resourcesParam = new SuperMap.IS.ResourceParam();
        resourcesParam.width = _resourceWidth;
        resourcesParam.height = _resourceHeight;
        for (var i = 0; i < _indexs.length; i++) {
            var m = _indexs[i];
            if (!_layers[m]) {
                continue
            }
            var innerHTMLForEachLayer = "";
            var resourceType = "";
            switch (_layers[m].type) {
            case SuperMap.IS.LayerType.point:
                resourceType = 0;
                break;
            case SuperMap.IS.LayerType.line:
                resourceType = 1;
                break;
            case SuperMap.IS.LayerType.polygon:
                resourceType = 2;
                break;
            case SuperMap.IS.LayerType.grid:
                resourceType = 2;
                break;
            default:
                break
            }
            var themeImgID = _layers[m].name + "_StyleIMG";
            resourcesParam.style = _layers[m].style;
            resourcesParam.resourceType = resourceType;
            resourcesParam.imageFormat = _imageFormat;
            innerHTMLForEachLayer = "<tr style='background-color:" + _itemBackColor + ";color:" + _itemForeColor + ";font-family:" + _self.itemFont.fontFamily.name + ";font-size:" + _self.itemFont.size + ";";
            if (_self.itemFont.bold) {
                innerHTMLForEachLayer += "font-weight:bold;"
            }
            if (_self.itemFont.italic) {
                innerHTMLForEachLayer += "font-style:itlic;"
            }
            if (_self.itemFont.underline && _self.itemFont.strikeout) {
                innerHTMLForEachLayer += "text-decoration:underline line-through;"
            } else {
                if (_self.itemFont.underline) {
                    innerHTMLForEachLayer += "text-decoration:underline;"
                } else {
                    if (_self.itemFont.strikeout) {
                        innerHTMLForEachLayer += "text-decoration:line-through;"
                    }
                }
            }
            innerHTMLForEachLayer += "'>";
            var bHasTheme = false;
            if (_layers[m].themeUnique || _layers[m].themeRange || _layers[m].themeGraph || _layers[m].themeGraduatedSymbol || _layers[m].themeLabel || _layers[m].themeDotDensity || _layers[m].themeGridRange || _layers[m].themeCustom) {
                bHasTheme = true
            }
            innerHTMLForEachLayer += '<td valign="top" width="20px">';
            if (bHasTheme) {
                if (_self.expand) {
                    innerHTMLForEachLayer += '<img id="Switch_' + _id + "_" + _layers[m].name + '_Themes" src="'+_scriptLocation + '../images/expand.gif" >'
                } else {
                    innerHTMLForEachLayer += '<img id="Switch_' + _id + "_" + _layers[m].name + '_Themes" src="'+_scriptLocation + '../images/collapse.gif" >'
                }
            }
            innerHTMLForEachLayer += "</td><td>";
            if (_layers[m].type == SuperMap.IS.LayerType.point || _layers[m].type == SuperMap.IS.LayerType.line || _layers[m].type == SuperMap.IS.LayerType.polygon) {
                innerHTMLForEachLayer += '<img id="' + themeImgID + '"  width=' + _resourceWidth + " height=" + _resourceHeight + ' src="' + map.GenerateResourceRequest(resourcesParam) + '" >'
            } else {
                if (_layers[m].type == SuperMap.IS.LayerType.network) {
                    innerHTMLForEachLayer += '<img id="' + themeImgID + '"  width=' + _resourceWidth + " height=" + _resourceHeight + ' src="'+_scriptLocation + '../images/Network.gif" >'
                } else {
                    if (_layers[m].type == SuperMap.IS.LayerType.text) {
                        innerHTMLForEachLayer += '<img id="' + themeImgID + '"  width=' + _resourceWidth + " height=" + _resourceHeight + ' src="'+_scriptLocation + '../images/Text.gif" >'
                    } else {
                        if (_layers[m].type == SuperMap.IS.LayerType.image) {
                            innerHTMLForEachLayer += '<img id="' + themeImgID + '"  width=' + _resourceWidth + " height=" + _resourceHeight + ' src="'+_scriptLocation + '../images/Image.gif" >'
                        } else {
                            if (_layers[m].type == SuperMap.IS.LayerType.mrSID) {
                                innerHTMLForEachLayer += '<img id="' + themeImgID + '"  width=' + _resourceWidth + " height=" + _resourceHeight + ' src="'+_scriptLocation + '../images/MRSID.gif" >'
                            } else {
                                if (_layers[m].type == SuperMap.IS.LayerType.grid) {
                                    innerHTMLForEachLayer += '<img id="' + themeImgID + '"  width=' + _resourceWidth + " height=" + _resourceHeight + ' src="'+_scriptLocation + '../images/GRID.gif" >'
                                } else {
                                    if (_layers[m].type == SuperMap.IS.LayerType.dem) {
                                        innerHTMLForEachLayer += '<img id="' + themeImgID + '"  width=' + _resourceWidth + " height=" + _resourceHeight + ' src="'+_scriptLocation + '../images/Dem.gif" >'
                                    } else {
                                        if (_layers[m].type == SuperMap.IS.LayerType.ecw) {
                                            innerHTMLForEachLayer += '<img id="' + themeImgID + '"  width=' + _resourceWidth + " height=" + _resourceHeight + ' src="'+_scriptLocation + '../images/Ecw.gif" >'
                                        } else {
                                            if (_layers[m].type == SuperMap.IS.LayerType.cad) {
                                                innerHTMLForEachLayer += '<img id="' + themeImgID + '"  width=' + _resourceWidth + " height=" + _resourceHeight + ' src="'+_scriptLocation + '../images/CAD.gif" >'
                                            } else {
                                                innerHTMLForEachLayer += '<img id="' + themeImgID + '"  width=' + _resourceWidth + " height=" + _resourceHeight + ' src="'+_scriptLocation + '../images/spacer.gif" >'
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            innerHTMLForEachLayer += '<span style="font-weight:bold">' + _layers[m].caption.split(_self.separator)[0] + "</span>";
            if (_self.expand) {
                innerHTMLForEachLayer += '<table id="' + _id + "_" + _layers[m].name + '_Themes" style="display:block">'
            } else {
                innerHTMLForEachLayer += '<table id="' + _id + "_" + _layers[m].name + '_Themes" style="display:none">'
            }
            var uniqueTheme = _layers[m].themeUnique;
            if (uniqueTheme != null) {
                innerHTMLForEachLayer += WriteStartScriptPart(m, "Unique", uniqueTheme);
                var imgID = _layers[m].name + "_ThemeUnique_IMG";
                for (var j = 0; j < uniqueTheme.values.length; j++) {
                    resourcesParam.style = uniqueTheme.displays[j];
                    resourcesParam.resourceType = resourceType;
                    resourcesParam.imageFormat = _imageFormat;
                    innerHTMLForEachLayer += '<div><img id="' + imgID + '"src="' + map.GenerateResourceRequest(resourcesParam) + '">' + uniqueTheme.itemCaptions[j] + "</div>"
                }
                innerHTMLForEachLayer += WriteEndScriptPart()
            }
            var rangeTheme = _layers[m].themeRange;
            if (rangeTheme != null) {
                innerHTMLForEachLayer += WriteStartScriptPart(m, "Range", rangeTheme);
                for (var j = 0; j < rangeTheme.displays.length; j++) {
                    var imgID = _layers[m].name + "_ThemeRange_IMG" + j;
                    resourcesParam.style = rangeTheme.displays[j];
                    resourcesParam.resourceType = resourceType;
                    resourcesParam.imageFormat = _imageFormat;
                    var value = "";
                    if (j == 0) {
                        value = "x<" + rangeTheme.breakValues[j]
                    } else {
                        if (j == rangeTheme.displays.length - 1) {
                            value = rangeTheme.breakValues[j - 1] + "<=x"
                        } else {
                            value = rangeTheme.breakValues[j - 1] + "<=x<" + rangeTheme.breakValues[j]
                        }
                    }
                    innerHTMLForEachLayer += '<div><img id="' + imgID + '" src="' + map.GenerateResourceRequest(resourcesParam) + '" >' + value + "</div>"
                }
                innerHTMLForEachLayer += WriteEndScriptPart()
            }
            var graphTheme = _layers[m].themeGraph;
            if (graphTheme != null) {
                innerHTMLForEachLayer += WriteStartScriptPart(m, "Graph", graphTheme);
                for (var j = 0; j < graphTheme.graphStyles.length; j++) {
                    var imgID = _layers[m].name + "_ThemeGraph_IMG" + j;
                    resourcesParam.style = graphTheme.graphStyles[j];
                    resourcesParam.resourceType = 2;
                    resourcesParam.imageFormat = _imageFormat;
                    innerHTMLForEachLayer += '<div><img id="' + imgID + '" src="' + map.GenerateResourceRequest(resourcesParam) + '" >' + graphTheme.expressions[j] + "</div>"
                }
                innerHTMLForEachLayer += WriteEndScriptPart()
            }
            var graduatedSymbolTheme = _layers[m].themeGraduatedSymbol;
            if (graduatedSymbolTheme != null) {
                innerHTMLForEachLayer += WriteStartScriptPart(m, "GraduatedSymbol", graduatedSymbolTheme);
                var imgID;
                imgID = _layers[m].name + "_ThemeGraph_IMG_0";
                resourcesParam.style = graduatedSymbolTheme.styleForPositive;
                resourcesParam.resourceType = 0;
                resourcesParam.imageFormat = _imageFormat;
                innerHTMLForEachLayer += '<Div><img id="' + imgID + '" src="' + map.GenerateResourceRequest(resourcesParam) + '" >' + SuperMap.IS.LegendControlResource.positive + "</div>";
                if (graduatedSymbolTheme.showNegative == true) {
                    imgID = _layers[m].name + "_ThemeGraph_IMG_1";
                    resourcesParam.style = graduatedSymbolTheme.styleForNegative;
                    resourcesParam.resourceType = 0;
                    resourcesParam.imageFormat = _imageFormat;
                    innerHTMLForEachLayer += '<Div><img id="' + imgID + '" src="' + map.GenerateResourceRequest(resourcesParam) + '" >' + SuperMap.IS.LegendControlResource.negative + "</div>";
                    innerHTMLForEachLayer += WriteEndScriptPart()
                }
            }
            var labelTheme = _layers[m].themeLabel;
            if (labelTheme != null) {
                innerHTMLForEachLayer += WriteStartScriptPart(m, "Label", labelTheme);
                innerHTMLForEachLayer += WriteEndScriptPart()
            }
            var dotDemsityTheme = _layers[m].themeDotDensity;
            if (dotDemsityTheme != null) {
                innerHTMLForEachLayer += WriteStartScriptPart(m, "DotDensity", dotDemsityTheme);
                var imgID = _layers[m].name + "_ThemeDotDensity_IMG";
                resourcesParam.style = dotDemsityTheme.dotStyle;
                resourcesParam.resourceType = 0;
                resourcesParam.imageFormat = _imageFormat;
                innerHTMLForEachLayer += '<div><img id="' + imgID + '" src="' + map.GenerateResourceRequest(resourcesParam) + '" >' + dotDemsityTheme.dotValue + "</div>";
                innerHTMLForEachLayer += WriteEndScriptPart()
            }
            var gridRangeTheme = _layers[m].themeGridRange;
            if (gridRangeTheme != null) {
                innerHTMLForEachLayer += WriteStartScriptPart(m, "GridRange", gridRangeTheme);
                for (var j = 0; j < gridRangeTheme.displays.length; j++) {
                    var imgID = _layers[m].name + "_ThemeRange_IMG" + j;
                    resourcesParam.style = new SuperMap.IS.Style();
                    resourcesParam.style.brushColor = gridRangeTheme.displays[j];
                    resourcesParam.resourceType = resourceType;
                    resourcesParam.imageFormat = _imageFormat;
                    var value = "";
                    if (gridRangeTheme.breakValues[0] == null) {
                        continue
                    }
                    if (j == 0) {
                        value = "x< " + gridRangeTheme.breakValues[j]
                    } else {
                        if (j == gridRangeTheme.displays.length - 1) {
                            value = gridRangeTheme.breakValues[j - 1] + "<=x"
                        } else {
                            value = gridRangeTheme.breakValues[j - 1] + "<=x<" + gridRangeTheme.breakValues[j]
                        }
                    }
                    innerHTMLForEachLayer += '<div><img id="' + imgID + '" src="' + map.GenerateResourceRequest(resourcesParam) + '" >' + value + "</div>"
                }
                innerHTMLForEachLayer += WriteEndScriptPart()
            }
            var customTheme = _layers[m].themeCustom;
            if (customTheme != null) {
                innerHTMLForEachLayer += WriteStartScriptPart(m, "Custom", customTheme);
                innerHTMLForEachLayer += WriteEndScriptPart()
            }
            innerHTMLForEachLayer += "</table></td>";
            innerHTMLForEachLayer += "</tr>";
            innerHTML += innerHTMLForEachLayer
        }
        innerHTML += trString;
        innerHTML += "</table>";
        var form = document.createElement("form");
        form.innerHTML = innerHTML;
        _containerInside.appendChild(form);
        for (var i = 0; i < _indexs.length; i++) {
            var m = _indexs[i];
            if (!_layers[m]) {
                continue
            }
            var bHasTheme = false;
            if (_layers[m].themeUnique || _layers[m].themeRange || _layers[m].themeGraph || _layers[m].themeGraduatedSymbol || _layers[m].themeLabel || _layers[m].themeDotDensity || _layers[m].themeGridRange || _layers[m].themeCustom) {
                bHasTheme = true
            }
            if (bHasTheme) {
                _JudgeMethod("Switch_" + _id + "_" + _layers[m].name + "_Themes", 
                function(e) {
                    _SwitchThemesDisplay(e)
                })
            }
            var uniqueTheme = _layers[m].themeUnique;
            if (uniqueTheme != null) {
                _JudgeMethod("Switch_" + _id + "_" + _layers[m].name + "_Unique", 
                function(e) {
                    _SwitchThemesDisplay(e)
                })
            }
            var rangeTheme = _layers[m].themeRange;
            if (rangeTheme != null) {
                _JudgeMethod("Switch_" + _id + "_" + _layers[m].name + "_Range", 
                function(e) {
                    _SwitchThemesDisplay(e)
                })
            }
            var graphTheme = _layers[m].themeGraph;
            if (graphTheme != null) {
                _JudgeMethod("Switch_" + _id + "_" + _layers[m].name + "_Graph", 
                function(e) {
                    _SwitchThemesDisplay(e)
                })
            }
            var graduatedSymbolTheme = _layers[m].themeGraduatedSymbol;
            if (graduatedSymbolTheme != null) {
                _JudgeMethod("Switch_" + _id + "_" + _layers[m].name + "_GraduatedSymbol", 
                function(e) {
                    _SwitchThemesDisplay(e)
                })
            }
            var labelTheme = _layers[m].themeLabel;
            if (labelTheme != null) {}
            var dotDemsityTheme = _layers[m].themeDotDensity;
            if (dotDemsityTheme != null) {
                _JudgeMethod("Switch_" + _id + "_" + _layers[m].name + "_DotDensity", 
                function(e) {
                    _SwitchThemesDisplay(e)
                })
            }
            var gridRangeTheme = _layers[m].themeGridRange;
            if (gridRangeTheme != null) {
                _JudgeMethod("Switch_" + _id + "_" + _layers[m].name + "_GridRange", 
                function(e) {
                    _SwitchThemesDisplay(e)
                })
            }
            var customTheme = _layers[m].themeCustom;
            if (customTheme != null) {}
        }
        if (_lastLayers == null) {
            _lastLayers = new Array()
        }
        for (var i = 0; _layers && i < _layers.length; i++) {
            if (_lastLayers[i] == null) {
                _lastLayers[i] = new SuperMap.IS.Layer()
            }
            _lastLayers[i].Copy(_layers[i])
        }
    }
    function _JudgeMethod(id, method) {
        var inputbox = document.getElementById(id);
        if (inputbox != null) {
            if (_ygPos.browser == "ie") {
                inputbox.attachEvent("onclick", method)
            } else {
                inputbox.addEventListener("click", method, true)
            }
            inputbox = null
        }
    }
    function WriteStartScriptPart(index, themeType, theme) {
        var str = "<tr>";
        str += '<td valign="top" width="20px">';
        if (themeType != "Label" && themeType != "Custom") {
            str += '<img id="Switch_' + _id + "_" + _layers[index].name + "_" + themeType + '"  src="'+_scriptLocation + '../images/expand.gif" >'
        }
        var themeName = "";
        if (theme != null) {
            themeName = theme.caption
        }
        var picName = "";
        switch (themeType) {
        case "DotDensity":
            picName = "ThemeDotDensity.gif";
            break;
        case "GraduatedSymbol":
            picName = "ThemeGraduatedSymbol.gif";
            break;
        case "Graph":
            picName = "ThemeGraph.gif";
            break;
        case "Label":
            picName = "ThemeLabel.gif";
            break;
        case "Range":
            picName = "ThemeRange.gif";
            break;
        case "Unique":
            picName = "ThemeUnique.gif";
            break;
        case "Custom":
            picName = "ThemeCustom.gif";
            break;
        case "GridRange":
            picName = "ThemeRange.gif";
            if (theme.breakValues[0] == null) {
                str = '<tr><td valign="top" width="20px">'
            }
            break
        }
        str += '</td><td><table><tr><td valign="top" width="20px"> <img src="'+_scriptLocation + '../images/' + picName + '"> </td><td>';
        if (_themeNameEnabled == true) {
            str += themeName
        }
        str += "</td><tr><td></td><td>";
        str += '<div id="' + _id + "_" + _layers[index].name + "_" + themeType + '" style="display:block">';
        return str
    }
    function WriteEndScriptPart() {
        var str = "</div></td></tr></table></td></tr>";
        return str
    }
    function onGetResourceComplete(url, userContext) {
        var img = document.getElementById(userContext);
        img.src = url
    }
    function onGetResourceError() {}
    this.SwitchThemesDisplay = _SwitchThemesDisplay;
    function _SwitchThemesDisplay(e) {
        var strId = e.srcElement.id;
        var strNew = strId.replace("Switch_", "");
        var img = document.getElementById(strId);
        var o = document.getElementById(strNew);
        if (o == null || img == null) {
            return
        }
        if (o.style.display == "block") {
            o.style.display = "none";
            img.src = _scriptLocation + "../images/collapse.gif"
        } else {
            o.style.display = "block";
            img.src = _scriptLocation + "../images/expand.gif"
        }
    }
    function _Update() {
        _inited = false;
        _Init()
    }
}
SuperMap.IS.MagnifierControl = function(container, map, param) {
    this.width = 200;
    if (param && typeof(param.width) != "undefined") {
        this.width = param.width
    }
    this.height = 200;
    if (param && typeof(param.height) != "undefined") {
        this.height = param.height
    }
    this.headHeight = 20;
    if (param && typeof(param.headHeight) != "undefined") {
        this.headHeight = param.headHeight
    }
    this.headBackColor = "SkyBlue";
    if (param && typeof(param.headBackColor) != "undefined") {
        this.headBackColor = param.headBackColor
    }
    this.minZoomRadio = 2;
    if (param && typeof(param.minZoomRadio) != "undefined") {
        this.minZoomRadio = param.minZoomRadio
    }
    this.maxZoomRadio = 6;
    if (param && typeof(param.maxZoomRadio) != "undefined") {
        this.maxZoomRadio = param.maxZoomRadio
    }
    this.closeButton = true;
    if (param && typeof(param.closeButton) != "undefined") {
        this.closeButton = param.closeButton
    }
    this.closeButtonImg = _scriptLocation + "../images/close.gif";
    if (param && typeof(param.closeButtonImg) != "undefined") {
        this.closeButtonImg = param.closeButtonImg
    }
    this.draggable = true;
    if (param && typeof(param.draggable) != "undefined") {
        this.draggable = param.draggable
    }
    this.expandCollapseButton = true;
    if (param && typeof(param.expandCollapseButton) != "undefined") {
        this.expandCollapseButton = param.expandCollapseButton
    }
    this.expandButtonImg = _scriptLocation + "../images/restore.gif";
    if (param && typeof(param.expandButtonImg) != "undefined") {
        this.expandButtonImg = param.expandButtonImg
    }
    this.collapseButtonImg = _scriptLocation + "../images/mini.gif";
    if (param && typeof(param.collapseButtonImg) != "undefined") {
        this.collapseButtonImg = param.collapseButtonImg
    }
    this.expanded = true;
    if (param && typeof(param.expanded) != "undefined") {
        this.expanded = param.expanded
    }
    this.title = "";
    if (param && typeof(param.title) != "undefined") {
        this.title = param.title
    }
    this.headFont = new SuperMap.IS.Font();
    if (param && typeof(param.headFont) != "undefined") {
        var headFont = eval("(" + param.headFont + ")");
        this.headFont.FromJSON(headFont);
        headFont = null
    }
    this.headBackgroundImage = "";
    if (param && typeof(param.headBackgroundImage) != "undefined") {
        this.headBackgroundImage = param.headBackgroundImage
    }
    this.headSeparatorLine = true;
    if (param && typeof(param.headSeparatorLine) != "undefined") {
        this.headSeparatorLine = param.headSeparatorLine
    }
    this.left = 0;
    if (param && typeof(param.left) != "undefined") {
        this.left = param.left
    }
    this.top = 0;
    if (param && typeof(param.top) != "undefined") {
        this.top = param.top
    }
    var _mapContainer;
    var _startMoving = false;
    var _leftForMagnifier;
    var _topForMagnifier;
    var _leftForMapContainer;
    var _topForMapContainer;
    var _magnifierBody;
    var _centerPix;
    var _curMapScale;
    var _self = this;
    var _oldleft;
    var _oldtop;
    var _hidden = false;
    var _vertCross;
    var _horizCross;
    var _lastMapName;
    var _oldMousePositionX;
    var _oldMousePositionY;
    var _containerInside = null;
    var _containerBorderWidthOfLeftAndRight = 0;
    this.Destroy = _Destroy;
    this.Close = _Close;
    this.Collapse = _Collapse;
    this.Expand = _Expand;
    this.Update = _Update;
    this.RefreshImage = _RefreshImage;
    if (map) {
        map.AttachEvent("oninit", _Init);
        map.AttachEvent("ondestroying", _Destroy)
    }
    function _Init() {
        if (_containerInside && _containerInside.parentNode) {
            _containerInside.parentNode.removeChild(_containerInside)
        }
        _containerInside = document.createElement("div");
        _containerInside.style.position = "absolute";
        _containerInside.style.width = _self.width + "px";
        _containerInside.style.height = _self.height + "px";
        _containerInside.style.overflow = "hidden";
        container.onmousedown = _OnMouseDown;
        container.onmousemove = _OnMouseMove;
        container.onmouseup = _OnMouseUp;
        container.ondblclick = _OnDblClick;
        container.onmousewheel = _OnMouseWheel;
        container.style.position = "absolute";
        container.style.display = "block";
        _mapContainer = map.workLayer;
        container.style.zIndex = 21;
        container.style.left = _self.left + "px";
        container.style.top = _self.top + "px";
        container.appendChild(_containerInside);
        if (container.style.borderWidth) {
            _containerBorderWidthOfLeftAndRight = container.offsetWidth - container.clientWidth
        }
        _mapContainer.appendChild(container);
        _Render();
        _lastMapName = map.mapName;
        if (map) {
            map.AttachEvent("onchangeview", _RefreshImage)
        }
    }
    function _InitProperty() {
        _self.width = param.width;
        _self.height = param.height;
        _self.headHeight = param.headHeight;
        _self.headBackColor = param.headBackColor;
        _self.minZoomRadio = param.minZoomRadio;
        _self.maxZoomRadio = param.maxZoomRadio;
        _self.closeButton = param.closeButton;
        _self.closeButtonImg = param.closeButtonImg;
        _self.draggable = param.draggable;
        _self.expandCollapseButton = param.expandCollapseButton;
        _self.expandButtonImg = param.expandButtonImg;
        _self.collapseButtonImg = param.collapseButtonImg;
        _self.expanded = param.expanded;
        _self.title = param.title;
        _self.headFont = eval("(" + param.headFont + ")");
        _self.headBackgroundImage = param.headBackgroundImage;
        _self.headSeparatorLine = param.headSeparatorLine
    }
    function _Destroy() {
        if (map) {
            map.DetachEvent("onchangeview", _RefreshImage);
            map.DetachEvent("oninit", _Init);
            map.DetachEvent("ondestroying", _Destroy)
        }
        _mapContainer = null;
        _magnifierBody = null;
        _centerPix = null;
        _curMapScale = null;
        _self = null;
        _oldleft = null;
        _oldtop = null;
        _hidden = null;
        _vertCross = null;
        _horizCross = null;
        _lastMapName = null;
        _oldMousePositionX = null;
        _oldMousePositionY = null;
        if (_containerInside && _containerInside.parentNode) {
            _containerInside.parentNode.removeChild(_containerInside)
        }
        container.innerHTML = "";
        _containerInside = container = param = null
    }
    function _RefreshImage() {
        if (!_lastMapName) {
            _lastMapName = map.mapName;
            return
        }
        if (_hidden) {
            return
        }
        _lastMapName = map.mapName;
        _centerPix = new SuperMap.IS.PixelCoord(_leftForMagnifier + parseInt(_magnifierBody.style.width) / 2 + _magnifierBody.offsetLeft + 1, _topForMagnifier + parseInt(_magnifierBody.style.height) / 2 + _magnifierBody.offsetTop + 1);
        _curMapScale = map.GetMapScale();
        var center = map.PixelToMapCoord(_centerPix, _curMapScale);
        var viewer = new SuperMap.IS.PixelRect(0, 0, parseInt(_magnifierBody.style.width), parseInt(_magnifierBody.style.height));
        var select = document.getElementById(container.id + "_ZoomRadio");
        if (!center.x || !center.y || !viewer || !select) {
            return
        }
        var zoomRadio = select.options[select.selectedIndex].value;
        var mapParam = new SuperMap.IS.MapParam(map.mapName, _curMapScale * zoomRadio);
        mapParam.pixelRect = viewer;
        mapParam.center = center;
        map.GetMapImage(mapParam, OnGetMapImageComplete, OnGetMapImageError);
        mapParam.Destroy();
        viewer = null;
        center = null;
        select = null
    }
    function _Close(e) {
        e = _GetEvent(e);
        if (e) {
            _CancelBubble(e)
        }
        _magnifierBody.style.display = "none";
        container.style.height = _self.height - parseInt(_magnifierBody.style.height) + "px";
        _oldleft = container.style.left;
        _oldtop = container.style.top;
        container.style.left = _self.left + "px";
        container.style.top = _self.top + "px";
        _magnifierBody.style.backgroundImage = "url(images/spacer.gif)";
        _magnifierBody.style.backgroundRepeat = "no-repeat";
        var img = document.getElementById(container.id + "_expandCollapse");
        img.src = _self.expandButtonImg;
        img.onclick = _Expand;
        _vertCross.style.display = "none";
        _horizCross.style.display = "none";
        _hidden = true
    }
    function _Expand(e) {
        if (!_hidden) {
            return
        }
        e = _GetEvent(e);
        if (e) {
            _CancelBubble(e)
        }
        _magnifierBody.style.display = "block";
        container.style.height = _self.height + "px";
        container.style.left = _oldleft;
        container.style.top = _oldtop;
        _hidden = false;
        var img = document.getElementById(container.id + "_expandCollapse");
        img.src = _self.collapseButtonImg;
        _vertCross.style.display = "block";
        _horizCross.style.display = "block";
        img.onclick = _Collapse
    }
    function _Collapse(e) {
        e = _GetEvent(e);
        if (e) {
            _CancelBubble(e)
        }
        _magnifierBody.style.display = "none";
        _oldleft = container.style.left;
        _oldtop = container.style.top;
        container.style.height = _self.height - parseInt(_magnifierBody.style.height) + "px";
        container.style.overflow = "hidden";
        _magnifierBody.style.backgroundImage = "url(images/spacer.gif)";
        _magnifierBody.style.backgroundRepeat = "no-repeat";
        var img = document.getElementById(container.id + "_expandCollapse");
        img.src = null;
        img.src = _self.expandButtonImg;
        img.onclick = null;
        img.onclick = _Expand;
        _vertCross.style.display = "none";
        _horizCross.style.display = "none";
        _hidden = true
    }
    function _OnMouseDown(e) {
        e = _GetEvent(e);
        var target = _GetTarget(e);
        _CancelBubble(e);
        var panAction = new SuperMap.IS.PanAction();
        map.SetAction(panAction);
        if (target.tagName == "IMG" || target.tagName == "SELECT" || target.tagName == "OPTION") {
            return
        }
        if (!_self.draggable) {
            return
        }
        var mouseX = _GetMouseX(e);
        var mouseY = _GetMouseY(e);
        _oldMousePositionX = mouseX;
        _oldMousePositionY = mouseY;
        _magnifierBody.style.backgroundImage = "url(images/spacer.gif)";
        _magnifierBody.style.backgroundRepeat = "no-repeat";
        _startMoving = true;
        _vertCross.style.display = "block";
        _horizCross.style.display = "block";
        return false
    }
    function _OnMouseMove(e) {
        if (!_startMoving) {
            return false
        }
        e = _GetEvent(e);
        var mouseX = _GetMouseX(e);
        var mouseY = _GetMouseY(e);
        var offsetX = mouseX - _oldMousePositionX;
        var offsetY = mouseY - _oldMousePositionY;
        _leftForMagnifier = parseInt(container.style.left) + offsetX;
        _topForMagnifier = parseInt(container.style.top) + offsetY;
        container.style.left = _leftForMagnifier + "px";
        container.style.top = _topForMagnifier + "px";
        _oldMousePositionX = mouseX;
        _oldMousePositionY = mouseY;
        _CancelBubble(e);
        return false
    }
    function _OnMouseUp(e) {
        e = _GetEvent(e);
        var target = _GetTarget(e);
        _CancelBubble(e);
        if (target.tagName == "IMG" || target.tagName == "SELECT" || target.tagName == "OPTION") {
            return false
        }
        _RefreshImage();
        _startMoving = false;
        _vertCross.style.display = "none";
        _horizCross.style.display = "none";
        _oldleft = container.style.left;
        _oldtop = container.style.top;
        return false
    }
    function _OnDblClick(e) {
        if (_hidden) {
            _Expand(e)
        } else {
            _Collapse(e)
        }
        return false
    }
    function _OnMouseWheel(e) {
        e = _GetEvent(e);
        _CancelBubble(e);
        return false
    }
    function OnGetMapImageComplete(mapUrl) {
        _magnifierBody.style.backgroundImage = "url(" + mapUrl + ")";
        _magnifierBody.style.backgroundRepeat = "no-repeat";
        var _browser = _ygPos.browser;
        if (_browser == "opera") {
            _containerInside.removeChild(_magnifierBody);
            _containerInside.appendChild(_magnifierBody)
        }
    }
    function OnGetMapImageError() {}
    function _RenderHead() {
        var head = document.createElement("div");
        head.id = container.id + "_Head";
        head.style.backgroundColor = _self.headBackColor;
        head.style.width = _self.width + "px";
        head.style.height = _self.headHeight + "px";
        if (_self.headSeparatorLine) {
            head.style.borderBottom = "1px solid black"
        }
        if (_self.headFont) {
            head.style.fontFamily = _self.headFont.fontFamily.name;
            head.style.fontSize = _self.headFont.size;
            if (_self.headFont.bold) {
                head.style.fontWeight = "bold"
            }
            if (_self.headFont.italic) {
                head.style.fontStyle = "italic"
            }
            if (_self.headFont.underline && _self.headFont.strikeout) {
                head.style.textDecoration = "underline line-through"
            } else {
                if (_self.headFont.underline) {
                    head.style.textDecoration = "underline"
                } else {
                    if (_self.headFont.strikeout) {
                        head.style.textDecoration = "line-through"
                    }
                }
            }
        }
        if (_self.headBackgroundImage) {
            head.style.backgroundImage = "url(" + _self.headBackgroundImage + ")"
        }
        var headHtml = "<table><tr><td width=" + _self.width * 0.4 + ">";
        if (_self.title) {
            headHtml += _self.title
        }
        headHtml += "</td>";
        headHtml += "<td  width=" + _self.width * 0.3 + " valign='top'>";
        headHtml += "<select style='width:100%;font-family:宋体;font-size:" + _self.headHeight * 0.6 + "px' id='" + container.id + "_ZoomRadio' onchange='" + container.id + ".RefreshImage();'>";
        for (var i = _self.minZoomRadio; i <= _self.maxZoomRadio; i++) {
            headHtml += "<option value=" + i + " >" + i + "x</option>"
        }
        headHtml += "</select>";
        headHtml += "</td>";
        headHtml += "<td align='right' valign='top' width=" + _self.width * 0.3 + " >";
        if (_self.expandCollapseButton) {
            headHtml += "<img id='" + container.id + "_expandCollapse' src='" + _self.collapseButtonImg + "' />"
        }
        if (_self.closeButton) {
            headHtml += "<img id='" + container.id + "_close' src='" + _self.closeButtonImg + "' />"
        }
        headHtml += "</td>";
        headHtml += "</tr></table>";
        head.innerHTML = headHtml;
        _containerInside.appendChild(head);
        var img = document.getElementById(container.id + "_expandCollapse");
        if (img) {
            img.onclick = _Collapse
        }
        img = document.getElementById(container.id + "_close");
        if (img) {
            img.onclick = _Close
        }
    }
    function _RenderBody() {
        _magnifierBody = document.createElement("div");
        _magnifierBody.style.width = _self.width + "px";
        _magnifierBody.style.height = _self.height - _self.headHeight - _containerBorderWidthOfLeftAndRight + "px";
        _magnifierBody.style.position = "absolute";
        _magnifierBody.id = container.id + "_Body";
        _vertCross = document.createElement("div");
        _vertCross.style.width = "20px";
        _vertCross.style.height = "2px";
        _vertCross.style.fontSize = 0;
        _vertCross.style.backgroundColor = "red";
        _vertCross.style.position = "absolute";
        _vertCross.id = container.id + "_vertCross";
        _vertCross.style.top = parseFloat(_magnifierBody.style.height) / 2 - parseFloat(_vertCross.style.height) / 2 + "px";
        _vertCross.style.left = parseFloat(_magnifierBody.style.width) / 2 - parseFloat(_vertCross.style.width) / 2 + "px";
        _magnifierBody.appendChild(_vertCross);
        _horizCross = document.createElement("div");
        _horizCross.style.width = "2px";
        _horizCross.style.height = "20px";
        _horizCross.style.backgroundColor = "red";
        _horizCross.style.position = "absolute";
        _horizCross.id = container.id + "_HorizCross";
        _horizCross.style.top = parseFloat(_magnifierBody.style.height) / 2 - parseFloat(_horizCross.style.height) / 2 + "px";
        _horizCross.style.left = parseFloat(_magnifierBody.style.width) / 2 - parseFloat(_horizCross.style.width) / 2 + "px";
        _magnifierBody.appendChild(_horizCross);
        _magnifierBody.style.backgroundImage = "url(images/spacer.gif)";
        _magnifierBody.style.backgroundRepeat = "no-repeat";
        _containerInside.appendChild(_magnifierBody)
    }
    function _Render() {
        _RenderHead();
        _RenderBody();
        if (!_self.expanded) {
            _Collapse()
        } else {
            container.style.left = _self.left + "px";
            container.style.top = _self.top + "px"
        }
    }
    function _SetPropertyHidden() {
        var o = new Object();
        o.width = _self.width;
        o.height = _self.height;
        o.headHeight = _self.headHeight;
        o.minZoomRadio = _self.minZoomRadio;
        o.maxZoomRadio = _self.maxZoomRadio;
        o.closeButton = _self.closeButton;
        o.draggable = _self.draggable;
        o.expandCollapseButton = _self.expandCollapseButton;
        o.expanded = _self.expanded;
        o.title = _self.title;
        var propertyJSON = _ToJSON(o);
        var hidden = document.getElementById(container.id + "_hiddenProperty");
        if (hidden) {
            hidden.value = propertyJSON
        }
    }
    function _Update() {
        _InitProperty();
        _Render();
        _SetPropertyHidden()
    }
}
SuperMap.IS.NavigationControl = function(K, S, I) {
    var C = false;
    this.backgroundUrl;
    this.navigationRate;
    this.navigationInterval;
    this.width;
    this.height;
    var D = K.id;
    var P = 0;
    var F = this;
    var E;
    this.Destroy = R;
    this.Update = M;
    var Q = "px";
    var H = "px";
    if (S) {
        S.AttachEvent("oninit", L);
        S.AttachEvent("ondestroying", R)
    }
    function L() {
        M();
        K.onmouseover = O;
        K.onmousedown = B;
        K.onmousemove = T;
        K.onmouseup = J;
        K.onmouseout = J
    }
    function R() {
        if (S) {
            S.DetachEvent("oninit", L);
            S.DetachEvent("ondestroying", R)
        }
        D = null;
        P = null;
        F = null;
        E = null;
        K.innerHTML = "";
        K = I = null
    }
    function G() {
        F.backgroundUrl = _scriptLocation + "../images/navigation.gif";
        if (I && typeof(I.backgroundUrl) != "undefined") {
            F.backgroundUrl = I.backgroundUrl
        }
        F.navigationRate = 10;
        if (I && typeof(I.navigationRate) != "undefined") {
            F.navigationRate = I.navigationRate
        }
        F.navigationInterval = 5;
        if (I && typeof(I.navigationInterval) != "undefined") {
            F.navigationInterval = I.navigationInterval
        }
        F.width = 154;
        if (I && typeof(I.width) != "undefined") {
            H = A(I.width);
            F.width = I.width.toString().replace(H, "")
        }
        F.height = 112;
        if (I && typeof(I.height) != "undefined") {
            Q = A(I.height);
            F.height = I.height.toString().replace(Q, "")
        }
    }
    function B(U) {
        C = true;
        T(U)
    }
    function T(Y) {
        if (!C) {
            return
        }
        C = false;
        var Y = _GetEvent(Y);
        var V = document.getElementById(D);
        var X = V.offsetWidth / 2;
        var W = V.offsetHeight / 2;
        var U = Y.offsetX - X;
        var Z = W - Y.offsetY;
        P = Math.atan(Z / U) * 180 / Math.PI;
        if (U < 0) {
            P += 180
        }
        C = true;
        return false
    }
    function J(U) {
        C = false;
        S.StopDynamicPan()
    }
    function O() {
        if (!C) {
            setTimeout(O, F.navigationInterval);
            return
        }
        if (P >= -30 && P < 30) {
            S.Pan(F.navigationRate, 0)
        } else {
            if (P >= 30 && P < 60) {
                S.Pan(F.navigationRate, -1 * F.navigationRate)
            } else {
                if (P >= 60 && P < 120) {
                    S.Pan(0, -1 * F.navigationRate)
                } else {
                    if (P >= 120 && P < 150) {
                        S.Pan( - 1 * F.navigationRate, -1 * F.navigationRate)
                    } else {
                        if (P >= 150 && P < 210) {
                            S.Pan( - 1 * F.navigationRate, 0)
                        } else {
                            if (P >= 210 && P < 240) {
                                S.Pan( - 1 * F.navigationRate, F.navigationRate)
                            } else {
                                if (P >= 240 && P <= 270 || P < -60 && P >= -90) {
                                    S.Pan(0, F.navigationRate)
                                } else {
                                    if (P >= -60 && P < -30) {
                                        S.Pan(F.navigationRate, F.navigationRate)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (C) {
            setTimeout(O, F.navigationInterval)
        }
        return false
    }
    function N() {
        var W = new Object();
        W.backgroundImageUrl = F.backgroundImageUrl;
        W.navigationInterval = F.navigationInterval;
        W.navigationRate = F.navigationRate;
        W.width = F.width;
        W.height = F.height;
        var U = _ToJSON(W);
        var V = document.getElementById(K.id + "_hiddenProperty");
        if (V) {
            V.value = U
        }
    }
    function M() {
        G();
        if (!E) {
            E = new Image()
        }
        if (E.parentNode) {
            E.parentNode.removeChild(E)
        }
        E.src = F.backgroundUrl;
        E.style.width = F.width + H;
        E.style.height = F.height + Q;
        K.appendChild(E);
        N()
    }
    function A(U) {
        if (U.toString().indexOf("px") != -1) {
            return "px"
        } else {
            if (U.toString().indexOf("%") != -1) {
                return "%"
            }
        }
        return "px"
    }
}
SuperMap.IS.OverviewControl = function(container, mapControl, params) {
    if (!mapControl || !container) {
        return
    }
    var _mapControl = mapControl;
    var _viewBounds = null;
    var _indexBounds = null;
    var _overviewUrl = "";
    var _eventsList = new Array();
    var _eventsNameList = new Array();
    var _indexBox = null;
    var _mapName = "";
    var _containerInside = null;
    var _width = container.clientWidth;
    var _height = container.clientHeight;
    var _left = 0,
    _top = 0;
    var _borderWidth = 2;
    var _leftForIndexBox = 0,
    _topForIndexBox = 0;
    var _widthForIndexBox = 0,
    _heightForIndexBox = 0;
    var _originX = 0,
    _originY = 0;
    var _offsetX = 0,
    _offsetY = 0;
    var _bMouseDown;
    var _initing = false;
    var _inited = false;
    var _switchMap = false;
    var _triggeredEvent = false;
    var _url;
    var overviewresize = "none";
    var _originWidth = 0,
    _originHeight = 0;
    var range = 5;
    var _indexBoxBorderColor = "red";
    var _indexBoxBorderStyle = "solid";
    var _indexBoxBorderWidth = "2px";
    var _overviewLayer = null,
    _overviewImage = null;
    _mapControl.AttachEvent("oninit", _OnMapInit);
    this.url;
    this.viewBounds;
    this.mapName;
    this.Destroy = _Destroy;
    this.AttachEvent = _AttachEvent;
    this.DetachEvent = _DetachEvent;
    var _self = this;
    this.Show = function() {
        container.style.visibility = "visible";
        _indexBox.style.visibility = "visible"
    };
    this.Hide = function() {
        container.style.visibility = "hidden";
        _indexBox.style.visibility = "hidden"
    };
    function _OnMapInit() {
        _Init();
        console.log("_OnMapInit")
    }
    function _OnMapChangeView() {
        console.log("_OnMapChangeView");
        if (_switchMap) {
            _switchMap = false;
            return
        }
        if (!_triggeredEvent) {
            _originX = parseInt(_indexBox.style.left);
            _originY = parseInt(_indexBox.style.top)
        }
        _indexBounds = _mapControl.GetViewBounds();
        console.log("_indexBounds:" + _indexBounds.ToString());
        _ComputeIndexBox();
        _DrawIndexBox(true);
        _triggeredEvent = false
    }
    function _OnMapSwitchMap() {
        console.log("_OnMapSwitchMap");
        _mapName = "";
        _viewBounds = null;
        params = null;
        _SwitchMap()
    }
    function _OnMapDestroy() {
        console.log("_OnMapDestroy");
        _Destroy()
    }
    function _Init() {
        if (_initing || _inited) {
            return
        }
        _initing = true;
        if (params) {
            if (params.indexBoxBorderColor) {
                _indexBoxBorderColor = params.indexBoxBorderColor
            }
            if (params.indexBoxBorderStyle) {
                _indexBoxBorderStyle = params.indexBoxBorderStyle
            }
            if (params.indexBoxBorderWidth) {
                _indexBoxBorderWidth = params.indexBoxBorderWidth
            }
            if (params.mapName) {
                _mapName = params.mapName
            }
            if (params.viewBounds) {
                _viewBounds = params.viewBounds
            }
            if (params.overviewUrl) {
                _self.url = params.overviewUrl
            }
        }
        if (_containerInside && _containerInside.parentNode) {
            _containerInside.parentNode.removeChild(_containerInside)
        }
        if (!_containerInside) {
            _containerInside = document.createElement("div")
        }
        var es = null;
        es = _containerInside.style;
        es.width = _width + "px";
        es.height = _height + "px";
        es.position = "relative";
        es.overflow = "hidden";
        container.appendChild(_containerInside);
        if (_indexBox && _indexBox.parentNode) {
            _indexBox.parentNode.removeChild(_indexBox)
        }
        if (!_indexBox) {
            _indexBox = document.createElement("div")
        }
        _indexBox.id = container.id + "_IndexBox";
        es = _indexBox.style;
        es.borderColor = _indexBoxBorderColor;
        es.borderStyle = _indexBoxBorderStyle;
        es.borderWidth = _indexBoxBorderWidth;
        es.position = "absolute";
        es.visibility = "visible";
        es.zIndex = 1;
        es.fontSize = "1px";
        es.width = _widthForIndexBox + "px";
        es.height = _heightForIndexBox + "px";
        es.left = _leftForIndexBox + "px";
        es.top = _topForIndexBox + "px";
        es = null;
        _containerInside.appendChild(_indexBox);
        _mapControl.AttachEvent("onstartswitchmap", _OnStartSwitchMap);
        _mapControl.AttachEvent("onchangeview", _OnMapChangeView);
        _mapControl.AttachEvent("onendswitchmap", _OnMapSwitchMap);
        _mapControl.AttachEvent("ondestroying", _OnMapDestroy);
        container.attachEvent("onmousedown", _OnMouseDown);
        container.attachEvent("onmousemove", _OnMouseMove);
        container.attachEvent("onmouseup", _OnMouseUp);
        container.attachEvent("onmouseover", _OnMouseOver);
        container.attachEvent("onmousewheel", _OnMouseWheel);
        _SwitchMap();
        _inited = true
    }
    function _OnStartSwitchMap() {
        _switchMap = true
    }
    function _Destroy() {
        _mapControl.DetachEvent("oninit", _OnMapInit);
        _mapControl.DetachEvent("onstartswitchmap", _OnStartSwitchMap);
        _mapControl.DetachEvent("onchangeview", _OnMapChangeView);
        _mapControl.DetachEvent("onendswitchmap", _OnMapSwitchMap);
        _mapControl.DetachEvent("ondestroying", _OnMapDestroy);
        _mapControl = null;
        if (_indexBox && _indexBox.parentNode) {
            _indexBox.parentNode.removeChild(_indexBox)
        }
        _indexBox = null;
        if (_containerInside && _containerInside.parentNode) {
            _containerInside.parentNode.removeChild(_containerInside)
        }
        _containerInside = null;
        _viewBounds = null;
        _indexBounds = null;
        container.detachEvent("onmousedown", _OnMouseDown);
        container.detachEvent("onmousemove", _OnMouseMove);
        container.detachEvent("onmouseup", _OnMouseUp);
        container.detachEvent("onmouseover", _OnMouseOver);
        container.detachEvent("onmousewheel", _OnMouseWheel);
        container = params = null
    }
    function _SwitchMap() {
        if (!_mapName) {
            _mapName = _mapControl.mapName
        }
        if (!_viewBounds) {
            _viewBounds = _mapControl.GetMapBounds()
        }
        console.log("_viewBounds:" + _viewBounds.ToString());
        _indexBounds = _mapControl.GetViewBounds();
        console.log("_indexBounds:" + _indexBounds.ToString());
        _ComputeIndexBox();
        _DrawIndexBox(true);
        _GetOverview()
    }
    function _GetOverview() {
        if (!_self.url) {
            var overview = new SuperMap.IS.Overview();
            overview.mapName = _mapName;
            overview.viewer = new SuperMap.IS.PixelRect(0, 0, _width, _height);
            overview.viewBounds = _viewBounds;
            _mapControl.GetOverview(overview, _OnGetOverviewComplete, _OnGetOverviewError, "_SwitchMap")
        } else {
            this.viewBounds = _viewBounds;
            _containerInside.style.backgroundImage = "url(" + _self.url + ")";
            _containerInside.style.backgroundRepeat = "no-repeat"
        }
    }
    function _AttachEvent(event, listener) {
        var events = _eventsList[event];
        if (!events) {
            events = new Array();
            _eventsList[event] = events;
            _eventsNameList.push(event)
        }
        for (var i = 0; i < events.length; i++) {
            if (events[i] == listener) {
                return true
            }
        }
        events.push(listener)
    }
    function _DetachEvent(event, listener) {
        var events = _eventsList[event];
        if (!events) {
            return
        }
        for (var i = 0; i < events.length; i++) {
            if (events[i] == listener) {
                events.splice(i, 1)
            }
        }
    }
    function _TriggerEvent(event, arguments, userContext) {
        var events = _eventsList[event];
        if (!events) {
            return
        }
        if (!arguments) {
            arguments = _GenerateEventArg()
        }
        var eventsTemp = events.concat();
        for (var i = 0; i < eventsTemp.length; i++) {
            if (eventsTemp[i]) {
                eventsTemp[i](arguments, userContext)
            }
        }
        while (eventsTemp.length > 0) {
            eventsTemp.pop()
        }
    }
    function _GenerateEventArg() {
        var _offsetX = parseInt(_indexBox.style.left) - parseInt(_originX);
        var _offsetY = parseInt(_indexBox.style.top) - parseInt(_originY);
        var mapSize = _mapControl.GetSize();
        var ratioX = mapSize.Width() / _indexBox.realWidth;
        var ratioY = mapSize.Height() / _indexBox.realHeight;
        _offsetX = _offsetX * ratioX;
        _offsetY = _offsetY * ratioY;
        var arguments = new Object();
        arguments.offsetX = _offsetX;
        arguments.offsetY = _offsetX;
        return arguments
    }
    this.TriggerServerEvent = function(eventName, e) {
        eval(container.id + "_doPostBack(container.id, eventName+'|'+e.offsetX+','+e.offsetY)")
    };
    function _DrawIndexBox(trigger) {
        var es = _indexBox.style;
        es.width = _widthForIndexBox + "px";
        es.height = _heightForIndexBox + "px";
        es.left = _leftForIndexBox + "px";
        es.top = _topForIndexBox + "px";
        es = null;
        _indexBox.parentNode.removeChild(_indexBox);
        _containerInside.appendChild(_indexBox);
        if (!_triggeredEvent && trigger) {
            _offsetX = parseInt(_indexBox.style.left) - parseInt(_originX);
            _offsetY = parseInt(_indexBox.style.top) - parseInt(_originY);
            if (_offsetX == 0 || _offsetY == 0) {
                return
            }
            var mapSize = _mapControl.GetSize();
            var ratioX = mapSize.Width() / _indexBox.realWidth;
            var ratioY = mapSize.Height() / _indexBox.realHeight;
            _offsetX = _offsetX * ratioX;
            _offsetY = _offsetY * ratioY;
            _triggeredEvent = true;
            _TriggerEvent("indexboxChanged", {
                offsetX: _offsetX,
                offsetY: _offsetX
            },
            "IndexboxChanged")
        }
        _SetPropertyHidden()
    }
    function _SetPropertyHidden() {
        var o = new Object();
        o.viewBounds = _viewBounds;
        o.indexBounds = _indexBounds;
        var propertyJSON = _ToJSON(o);
        var hidden = document.getElementById(container.id + "_hiddenProperty");
        if (hidden) {
            hidden.value = propertyJSON
        }
    }
    function _ComputeIndexBox() {
        var leftRatio = (_indexBounds.leftBottom.x - _viewBounds.leftBottom.x) / (_viewBounds.rightTop.x - _viewBounds.leftBottom.x);
        _leftForIndexBox = _width * leftRatio;
        var topRatio = (_viewBounds.rightTop.y - _indexBounds.rightTop.y) / (_viewBounds.rightTop.y - _viewBounds.leftBottom.y);
        _topForIndexBox = _height * topRatio;
        var widthRatio = (_indexBounds.rightTop.x - _indexBounds.leftBottom.x) / (_viewBounds.rightTop.x - _viewBounds.leftBottom.x);
        _widthForIndexBox = _width * widthRatio;
        _indexBox.realWidth = _widthForIndexBox;
        var heightRatio = (_indexBounds.rightTop.y - _indexBounds.leftBottom.y) / (_viewBounds.rightTop.y - _viewBounds.leftBottom.y);
        _heightForIndexBox = _height * heightRatio;
        _indexBox.realHeight = _heightForIndexBox;
        if (_viewBounds.leftBottom.x <= mapControl.GetMapBounds().leftBottom.x || _viewBounds.leftBottom.y <= mapControl.GetMapBounds().leftBottom.y || _viewBounds.rightTop.x >= mapControl.GetMapBounds().rightTop.x || _viewBounds.rightTop.y >= mapControl.GetMapBounds().rightTop.y) {
            if (_leftForIndexBox < 0) {
                _widthForIndexBox = _widthForIndexBox + _leftForIndexBox;
                _leftForIndexBox = 0
            }
            if (_leftForIndexBox + _widthForIndexBox > _width - 2 * _borderWidth) {
                _widthForIndexBox = _width - 2 * _borderWidth - _leftForIndexBox
            }
            if (_topForIndexBox < 0) {
                _heightForIndexBox = _heightForIndexBox + _topForIndexBox;
                _topForIndexBox = 0
            }
            if (_topForIndexBox + _heightForIndexBox > _height - 2 * _borderWidth) {
                _heightForIndexBox = _height - 2 * _borderWidth - _topForIndexBox
            }
            if (_widthForIndexBox < 5) {
                _widthForIndexBox = 5
            }
            if (_heightForIndexBox < 5) {
                _heightForIndexBox = 5
            }
            if (_widthForIndexBox > _width) {
                _widthForIndexBox = _width
            }
            if (_heightForIndexBox > _height) {
                _heightForIndexBox = _height
            }
        }
    }
    function _OnMouseOver(e) {
        if (_widthForIndexBox > range && _heightForIndexBox > range) {
            e = _GetEvent(e);
            var mouseX = _GetMouseX(e);
            var mouseY = _GetMouseY(e);
            changeCursor(mouseX, mouseY)
        }
    }
    function _OnMouseDown(e) {
        _bMouseDown = true;
        _CancelBubble(e);
        _originX = _leftForIndexBox;
        _originY = _topForIndexBox;
        _originWidth = _indexBox.realWidth;
        _originHeight = _indexBox.realHeight;
        _left = _GetElementX(container);
        _top = _GetElementY(container);
        e = _GetEvent(e);
        var mouseX = _GetMouseX(e);
        var mouseY = _GetMouseY(e);
        if (_widthForIndexBox > range && _heightForIndexBox > range) {
            var indexBoxLeft = _left + _originX;
            var indexBoxTop = _top + _originY;
            var indexBoxRight = indexBoxLeft + _widthForIndexBox;
            var indexBoxBottom = indexBoxTop + _heightForIndexBox;
            if (mouseX >= indexBoxLeft - range && mouseX <= indexBoxLeft + range && mouseY >= indexBoxTop - range && mouseY <= indexBoxTop + range) {
                if (_ygPos.browser == "ie") {
                    container.style.cursor = _scriptLocation + "../images/cur_aero_nwse.cur"
                } else {
                    container.style.cursor = "nw_resize"
                }
                overviewresize = "lt";
                _originX = _originX + _originWidth;
                _originY = _originY + _originHeight
            }
            if (mouseX >= indexBoxLeft - range && mouseX <= indexBoxLeft + range && mouseY >= indexBoxBottom - range && mouseY <= indexBoxBottom + range) {
                if (_ygPos.browser == "ie") {
                    container.style.cursor = _scriptLocation + "../images/cur_aero_nesw.cur"
                } else {
                    container.style.cursor = "sw_resize"
                }
                overviewresize = "lb";
                _originX = _originX + _originWidth
            }
            if (mouseX >= indexBoxRight - range && mouseX <= indexBoxRight + range && mouseY >= indexBoxBottom - range && mouseY <= indexBoxBottom + range) {
                if (_ygPos.browser == "ie") {
                    container.style.cursor = _scriptLocation + "../images/cur_aero_nwse.cur"
                } else {
                    container.style.cursor = "se_resize"
                }
                overviewresize = "rb"
            }
            if (mouseX >= indexBoxRight - range && mouseX <= indexBoxRight + range && mouseY >= indexBoxTop - range && mouseY <= indexBoxTop + range) {
                if (_ygPos.browser == "ie") {
                    container.style.cursor = _scriptLocation + "../images/cur_aero_nesw.cur"
                } else {
                    container.style.cursor = "ne_resize"
                }
                overviewresize = "rt";
                _originY = _originY + _originHeight
            }
        }
        if (overviewresize == "none") {
            _leftForIndexBox = mouseX - _left - _widthForIndexBox / 2;
            _topForIndexBox = mouseY - _top - _heightForIndexBox / 2;
            _DrawIndexBox(false)
        }
    }
    function _OnMouseMove(e) {
        e = _GetEvent(e);
        _CancelBubble(e);
        var mouseX = _GetMouseX(e);
        var mouseY = _GetMouseY(e);
        if (!_bMouseDown) {
            changeCursor(mouseX, mouseY);
            return
        }
        _left = _GetElementX(_containerInside);
        _top = _GetElementY(_containerInside);
        if (overviewresize == "lt" || overviewresize == "lb" || overviewresize == "rb" || overviewresize == "rt") {
            var originWH = _originWidth / _originHeight;
            var offsetX = mouseX - _originX;
            var offsetY = mouseY - _originY;
            _widthForIndexBox = Math.abs(offsetX - _left);
            _heightForIndexBox = Math.abs(offsetY - _top);
            if (offsetX < _left) {
                _leftForIndexBox = _originX - _widthForIndexBox
            } else {
                _leftForIndexBox = _originX
            }
            if (offsetY < _top) {
                _topForIndexBox = _originY - _heightForIndexBox
            } else {
                _topForIndexBox = _originY
            }
            changeCursor(mouseX, mouseY);
            _DrawIndexBox(false);
            return
        } else {
            if (_ygPos.browser == "ie") {
                container.style.cursor = ""
            } else {
                container.style.cursor = "default"
            }
            _leftForIndexBox = mouseX - _left - _widthForIndexBox / 2;
            _topForIndexBox = mouseY - _top - _heightForIndexBox / 2;
            _DrawIndexBox(false);
            return false
        }
    }
    function _OnMouseUp(e) {
        if (!_bMouseDown) {
            return
        }
        _bMouseDown = false;
        e = _GetEvent(e);
        _CancelBubble(e);
        var mapSize = _mapControl.GetSize();
        var ratioX = mapSize.Width() / _indexBox.realWidth;
        var ratioY = mapSize.Height() / _indexBox.realHeight;
        var centerX = _mapControl.GetMapCenterX();
        var centerY = _mapControl.GetMapCenterY();
        var ms = _mapControl.GetMapScale();
        if (overviewresize == "lt" || overviewresize == "lb" || overviewresize == "rb" || overviewresize == "rt") {
            var mouseX = _GetMouseX(e);
            var mouseY = _GetMouseY(e);
            changeCursor(mouseX, mouseY);
            var ratioWidth = _widthForIndexBox / _originWidth;
            _offsetX = _widthForIndexBox - _originWidth;
            _offsetY = _heightForIndexBox - _originHeight;
            _offsetX = _offsetX * ratioX;
            _offsetY = _offsetY * ratioY;
            var distanceH = 0;
            var distanceW = 0;
            var signX = 1;
            var signY = 1;
            var signW = 1;
            var signH = 1;
            var distanceX = _mapControl.PixelToMapDistance(_offsetX, ms);
            var distanceY = _mapControl.PixelToMapDistance( - _offsetY, ms);
            if (_leftForIndexBox < _originX) {
                if (overviewresize == "rb" || overviewresize == "rt") {
                    distanceW = _mapControl.PixelToMapDistance(_originWidth * ratioX, ms);
                    signW = -1
                }
                signX = -1
            } else {
                if (overviewresize == "lt" || overviewresize == "lb") {
                    distanceW = _mapControl.PixelToMapDistance(_originWidth * ratioX, ms)
                }
            }
            if (_topForIndexBox < _originY) {
                if (overviewresize == "rb" || overviewresize == "lb") {
                    distanceH = _mapControl.PixelToMapDistance(_originHeight * ratioY, ms)
                }
                signY = -1
            } else {
                if (overviewresize == "lt" || overviewresize == "rt") {
                    distanceH = _mapControl.PixelToMapDistance(_originHeight * ratioY, ms);
                    signH = -1
                }
            }
            var position = new SuperMap.IS.MapCoord(centerX + distanceW * signW + distanceX / 2 * signX, centerY + distanceH * signH + distanceY / 2 * signY);
            if (_mapControl.customBounds != null) {
                if (!_IsInCustomBounds(position, _mapControl.customBounds, ms / ratioWidth)) {
                    _OnMapChangeView();
                    return
                }
            }
            _mapControl.SetCenterAndZoom(centerX + distanceW * signW + distanceX / 2 * signX, centerY + distanceH * signH + distanceY / 2 * signY, ms / ratioWidth);
            overviewresize = "none"
        } else {
            _offsetX = _leftForIndexBox - _originX;
            _offsetY = _topForIndexBox - _originY;
            _offsetX = _offsetX * ratioX;
            _offsetY = _offsetY * ratioY;
            var distanceX = _mapControl.PixelToMapDistance(_offsetX, ms);
            var distanceY = _mapControl.PixelToMapDistance( - _offsetY, ms);
            var position = new SuperMap.IS.MapCoord(centerX + distanceX, centerY + distanceY);
            if (_mapControl.customBounds != null) {
                if (!_IsInCustomBounds(position, _mapControl.customBounds, ms)) {
                    _OnMapChangeView();
                    return
                }
            }
            _mapControl.SetCenterAndZoom(centerX + distanceX, centerY + distanceY)
        }
        _DrawIndexBox(true);
        _offsetX = 0;
        _offsetY = 0;
        return false
    }
    function _OnMouseWheel(e) {
        e = _GetEvent(e);
        _CancelBubble(e);
        if (e.detail) {
            if (e.detail > 0) {
                _mapControl.ZoomOut()
            } else {
                _mapControl.ZoomIn()
            }
        } else {
            if (e.wheelDelta > 0) {
                _mapControl.ZoomIn()
            } else {
                _mapControl.ZoomOut()
            }
        }
        if (e.preventDefault) {
            e.preventDefault()
        }
        return false
    }
    function _OnGetOverviewComplete(overview, userContext) {
        if (!_self.url) {
            this.url = overview.url
        } else {
            this.url = _self.url
        }
        this.viewBounds = overview.viewBounds;
        _containerInside.style.backgroundImage = "url(" + this.url + ")";
        _containerInside.style.backgroundRepeat = "no-repeat";
        if (!_viewBounds || !_viewBounds.Equals(overview.viewBounds)) {
            console.log("_viewBoundsChanged:" + _viewBounds.ToString());
            _viewBounds = overview.viewBounds;
            _OnMapChangeView()
        }
        console.log("_viewBounds:" + _viewBounds.ToString())
    }
    function _OnGetOverviewError(responseText) {
        if (responseText) {
            alert(SuperMap.IS.OverivewControlResource.getOverivewError + ":" + responseText)
        } else {
            alert(SuperMap.IS.OverivewControlResource.getOverivewError + "!")
        }
    }
    this.Update = function() {
        if (this.viewBounds) {
            _viewBounds = this.viewBounds
        } else {
            if (params && params.viewBounds) {
                _viewBounds = params.viewBounds
            } else {
                _viewBounds = _mapControl.GetMapBounds()
            }
        }
        if (this.mapName) {
            _mapName = this.mapName
        } else {
            if (params && params.mapName) {
                _mapName = params.mapName
            } else {
                _mapName = _mapControl.mapName
            }
        }
        _GetOverview()
    };
    function changeCursor(eX, eY) {
        _left = _GetElementX(container);
        _top = _GetElementY(container);
        var indexBoxLeft = _left + _leftForIndexBox;
        var indexBoxTop = _top + _topForIndexBox;
        var indexBoxRight = indexBoxLeft + _widthForIndexBox;
        var indexBoxBottom = indexBoxTop + _heightForIndexBox;
        if (eX >= indexBoxLeft - range && eX <= indexBoxLeft + range && eY >= indexBoxTop - range && eY <= indexBoxTop + range) {
            if (_ygPos.browser == "ie") {
                container.style.cursor = _scriptLocation + "../images/cur_aero_nwse.cur"
            } else {
                container.style.cursor = "nwse-resize"
            }
        } else {
            if (eX >= indexBoxLeft - range && eX <= indexBoxLeft + range && eY >= indexBoxBottom - range && eY <= indexBoxBottom + range) {
                if (_ygPos.browser == "ie") {
                    container.style.cursor = _scriptLocation + "../images/cur_aero_nesw.cur"
                } else {
                    container.style.cursor = "nesw-resize"
                }
            } else {
                if (eX >= indexBoxRight - range && eX <= indexBoxRight + range && eY >= indexBoxBottom - range && eY <= indexBoxBottom + range) {
                    if (_ygPos.browser == "ie") {
                        container.style.cursor = _scriptLocation + "../images/cur_aero_nwse.cur"
                    } else {
                        container.style.cursor = "nwse-resize"
                    }
                } else {
                    if (eX >= indexBoxRight - range && eX <= indexBoxRight + range && eY >= indexBoxTop - range && eY <= indexBoxTop + range) {
                        if (_ygPos.browser == "ie") {
                            container.style.cursor = _scriptLocation + "../images/cur_aero_nesw.cur"
                        } else {
                            container.style.cursor = "nesw-resize"
                        }
                    } else {
                        if (_ygPos.browser == "ie") {
                            container.style.cursor = ""
                        } else {
                            container.style.cursor = "default"
                        }
                    }
                }
            }
        }
    }
    function _IsInCustomBounds(position, bounds, mapScale) {
        var curMapHeight = 0;
        var curMapWidth = 0;
        var boundsRightTopX = 0;
        var boundsRightTopY = 0;
        var boundsLeftBottomX = 0;
        var boundsLeftBottomY = 0;
        var right = true;
        if (mapScale != null) {
            curMapHeight = mapControl.PixelToMapDistance(mapControl.container.style.pixelHeight, mapScale);
            curMapWidth = mapControl.PixelToMapDistance(mapControl.container.style.pixelWidth, mapScale)
        }
        boundsRightTopX = bounds.rightTop.x - curMapWidth / 2;
        boundsRightTopY = bounds.rightTop.y - curMapHeight / 2;
        boundsLeftBottomX = curMapWidth / 2 + bounds.leftBottom.x;
        boundsLeftBottomY = curMapHeight / 2 + bounds.leftBottom.y;
        if (position.x < boundsLeftBottomX) {
            right = false
        }
        if (position.y < boundsLeftBottomY) {
            right = false
        }
        if (position.x > boundsRightTopX) {
            right = false
        }
        if (position.y > boundsRightTopY) {
            right = false
        }
        return right
    }
}
SuperMap.IS.ScaleBarControl = function(Z, U, C) {
    var O = false;
    var b = Z.id;
    var Y = 1;
    var e = false;
    var a = false;
    var K;
    var H;
    var G;
    var P;
    var h;
    var i;
    var F;
    var R;
    var Q;
    var E = this;
    this.zoomLevels;
    this.curZoomLevel;
    this.width = 20;
    if (C && typeof(C.width) != "undefined") {
        this.width = C.width
    }
    this.height = 150;
    if (C && typeof(C.height) != "undefined") {
        this.height = C.height
    }
    this.zoomBarStartAt = 5;
    if (C && typeof(C.zoomBarStartAt) != "undefined") {
        this.zoomBarStartAt = C.zoomBarStartAt
    }
    this.ordinal = true;
    if (C && typeof(C.ordinal) != "undefined") {
        E.ordinal = C.ordinal
    }
    this.sliderImageUrl = _scriptLocation + "../images/slider.gif";
    if (C && typeof(C.sliderImageUrl) != "undefined") {
        this.sliderImageUrl = C.sliderImageUrl
    }
    this.position = 0;
    if (C && typeof(C.sliderImageUrl) != "undefined") {
        this.position = C.position
    }
    this.useIntersectedZoomBar = true;
    if (C && typeof(C.useIntersectedZoomBar) != "undefined") {
        this.useIntersectedZoomBar = C.useIntersectedZoomBar
    }
    this.zoomBarImageLength = 15;
    if (C && typeof(C.zoomBarImageLength) != "undefined") {
        this.zoomBarImageLength = C.zoomBarImageLength
    }
    this.zoomBarImageUrl = _scriptLocation + "../images/zoom-bg-intersected.gif";
    if (C && typeof(C.zoomBarImageUrl) != "undefined") {
        this.zoomBarImageUrl = C.zoomBarImageUrl
    }
    this.zoomOutImageUrl = _scriptLocation + "../images/ZoomOut.gif";
    if (C && typeof(C.zoomOutImageUrl) != "undefined") {
        this.zoomOutImageUrl = C.zoomOutImageUrl
    }
    this.zoomInImageUrl = _scriptLocation + "../images/ZoomIn.gif";
    if (C && typeof(C.zoomInImageUrl) != "undefined") {
        this.zoomInImageUrl = C.zoomInImageUrl
    }
    var V = null;
    this.ZoomIn = J;
    this.ZoomOut = f;
    this.ZoombarMouseDown = A;
    this.ZoombarMouseMove = B;
    this.ZoombarMouseUp = X;
    this.Update = N;
    if (U) {
        U.AttachEvent("oninit", d);
        U.AttachEvent("ondestroying", D)
    }
    function d() {
        if (V) {
            if (V.parentNode) {
                V.parentNode.removeChild(V)
            }
            V = null
        }
        V = document.createElement("div");
        V.style.position = "";
        V.style.width = E.width + "px";
        V.style.height = E.height + "px";
        Z.appendChild(V);
        S();
        N();
        U.AttachEvent("onchangeview", j)
    }
    function D() {
        if (U) {
            U.DetachEvent("onchangeview", j);
            U.DetachEvent("oninit", d);
            U.DetachEvent("ondestroying", D)
        }
        Z.innerHTML = "";
        Z = null;
        C = null;
        O = null;
        b = null;
        Y = null;
        e = null;
        a = null;
        K = null;
        H = null;
        G = null;
        P = null;
        F = null;
        h = null;
        i = null;
        R = null;
        Q = null;
        E = null
    }
    function S() {
        var m = U.GetMapParam();
        var l = m.mapScales;
        if (!l || l.length <= 0) {
            K = 0;
            E.curZoomLevel = 0;
            E.zoomLevels = 0;
            return
        }
        E.zoomLevels = l.length;
        if (!E.useIntersectedZoomBar) {
            K = (E.zoomBarImageLength - E.zoomBarStartAt) / E.zoomLevels
        } else {
            K = E.zoomBarImageLength
        }
        var n = m.mapScale;
        for (var k = 0; k < l.length; k++) {
            if (n == l[k]) {
                E.curZoomLevel = k;
                break
            }
        }
    }
    function A(n) {
        if (U.GetMapParam().mapScales.length <= 0) {
            return
        }
        if (E.position != SuperMap.IS.ZoomPosition.vertical) {
            P = document.getElementById(Z.id + "_Slider");
            F = document.getElementById(Z.id + "_Zoombar")
        }
        n = _GetEvent(n);
        G = n.clientY;
        H = n.clientX;
        var m = _GetMouseX(n);
        var l = _GetMouseY(n);
        var k;
        if (E.position == SuperMap.IS.ZoomPosition.vertical) {
            k = _GetElementY(F);
            P.style.top = l - k - P.height / 2 + "px"
        } else {
            k = _GetElementX(F);
            P.style.left = m - k - P.width / 2 + "px"
        }
        a = true;
        _CancelBubble(n);
        return false
    }
    function B(n) {
        if (a) {
            if (E.position != SuperMap.IS.ZoomPosition.vertical) {
                P = document.getElementById(Z.id + "_Slider")
            }
            n = _GetEvent(n);
            _CancelBubble(n);
            var k = n.clientX - H;
            var o = n.clientY - G;
            var m = parseInt(P.style.top);
            var l = parseInt(P.style.left);
            if (E.position == SuperMap.IS.ZoomPosition.vertical) {
                m = m + o;
                if (m < 0 || m > (E.zoomLevels - 1) * K + E.zoomBarStartAt) {
                    return false
                }
                P.style.top = m + "px"
            } else {
                l = l + k;
                if (l < 0 || l > (E.zoomLevels - 1) * K + E.zoomBarStartAt) {
                    return false
                }
                P.style.left = l + "px"
            }
            G = n.clientY;
            H = n.clientX
        }
        return false
    }
    function X(m) {
        m = _GetEvent(m);
        if (E.position != SuperMap.IS.ZoomPosition.vertical) {
            P = document.getElementById(Z.id + "_Slider");
            F = document.getElementById(Z.id + "_Zoombar")
        }
        _CancelBubble(m);
        var l = _GetMouseY(m);
        var k;
        if (E.position == SuperMap.IS.ZoomPosition.vertical) {
            k = parseInt(P.style.top)
        } else {
            k = parseInt(P.style.left)
        }
        var n = Math.round((k - E.zoomBarStartAt) / K);
        L(n);
        if (!E.ordinal) {
            n = E.zoomLevels - n - 1
        }
        E.curZoomLevel = n;
        W(E.zoomLevels - n - 1);
        if (U.customBounds != null) {
            g(n)
        }
        a = false;
        return false
    }
    function J() {
        if (E.ordinal) {
            L(E.curZoomLevel - 1);
            if (!e) {
                E.curZoomLevel--;
                W(E.zoomLevels - E.curZoomLevel - 1)
            }
        } else {
            L(E.curZoomLevel + 1);
            if (!e) {
                E.curZoomLevel++;
                W(E.curZoomLevel)
            }
        }
    }
    function f() {
        if (E.ordinal) {
            L(E.curZoomLevel + 1);
            if (!e) {
                E.curZoomLevel++;
                W(E.zoomLevels - E.curZoomLevel - 1)
            }
        } else {
            L(E.curZoomLevel - 1);
            if (!e) {
                E.curZoomLevel--;
                W(E.curZoomLevel)
            }
        }
    }
    function j() {
        var n = U.GetMapParam();
        var l = n.mapScales;
        if (!l || l.length <= 0) {
            E.zoomLevels = 0;
            E.curZoomLevel = 0;
            Z.style.display = "none";
            return
        }
        Z.style.display = "block";
        E.zoomLevels = l.length;
        if (E.useIntersectedZoomBar) {
            K = E.zoomBarImageLength
        } else {
            K = (E.zoomBarImageLength - E.zoomBarStartAt) / E.zoomLevels
        }
        var m = n.mapScale;
        var o = E.zoomLevels;
        for (var k = 0; k < o; k++) {
            if (m == l[k]) {
                E.curZoomLevel = k;
                break
            }
        }
        E.curZoomLevel = E.zoomLevels - E.curZoomLevel - 1;
        if (!E.ordinal) {
            E.curZoomLevel = E.zoomLevels - E.curZoomLevel - 1
        }
        L(E.curZoomLevel)
    }
    function g(l) {
        var k = U.GetMapParam();
        l = E.zoomLevels - k.zoomLevel;
        L(l)
    }
    function L(l) {
        if (l < 0 || l >= E.zoomLevels) {
            e = true;
            return
        }
        var k = l * K + E.zoomBarStartAt;
        if (!E.ordinal) {
            k += E.zoomBarStartAt
        }
        if (E.position == SuperMap.IS.ZoomPosition.vertical) {
            P.style.top = k + "px"
        } else {
            P.style.left = k + "px"
        }
        e = false
    }
    function W(l) {
        var k = U.GetMapParam();
        U.SetMapScale(k.mapScales[l])
    }
    function c() {
        var m = new Object();
        m.ordinal = E.ordinal;
        m.position = E.position;
        m.sliderImageUrl = E.sliderImageUrl;
        m.useIntersectedZoomBar = E.useIntersectedZoomBar;
        m.zoomBarImageLength = E.zoomBarImageLength;
        m.zoomBarImageUrl = E.zoomBarImageUrl;
        m.zoomBarStartAt = E.zoomBarStartAt;
        m.zoomOutImageUrl = E.zoomOutImageUrl;
        m.zoomInImageUrl = E.zoomInImageUrl;
        var k = _ToJSON(m);
        var l = document.getElementById(Z.id + "_hiddenProperty");
        if (l) {
            l.value = k
        }
    }
    function T() {
        h = new Image();
        h.style.styleFloat = "left";
        h.onclick = J;
        V.appendChild(h);
        if (E.position == SuperMap.IS.ZoomPosition.vertical) {
            h.style.width = E.width + "px"
        } else {
            h.style.height = E.height + "px";
            if (_ygPos.browser != "ie") {
                if (!E.ordinal) {
                    h.style.position = "relative";
                    h.style.top = (0 - E.height * 2) + "px";
                    h.style.left = i.offsetWidth + E.zoomBarImageLength + "px"
                }
            }
        }
        h.src = E.zoomInImageUrl
    }
    function I() {
        i = new Image();
        i.style.styleFloat = "left";
        i.onclick = f;
        V.appendChild(i);
        if (E.position == SuperMap.IS.ZoomPosition.vertical) {
            i.style.width = E.width + "px"
        } else {
            i.style.height = E.height + "px";
            if (_ygPos.browser != "ie") {
                if (E.ordinal) {
                    i.style.position = "relative";
                    i.style.top = (0 - E.height * 2) + "px";
                    i.style.left = h.offsetWidth + E.zoomBarImageLength + "px"
                }
            }
        }
        i.src = E.zoomOutImageUrl
    }
    function M() {
        E.curZoomLevel = E.zoomLevels - E.curZoomLevel - 1;
        if (!E.ordinal) {
            E.curZoomLevel = E.zoomLevels - E.curZoomLevel - 1
        }
        var k;
        F = document.createElement("DIV");
        F.id = Z.id + "_Zoombar";
        F.style.position = "relative";
        if (E.position == SuperMap.IS.ZoomPosition.vertical) {
            F.style.width = E.width + "px";
            if (!E.useIntersectedZoomBar) {
                F.style.height = E.zoomBarImageLength + "px";
                F.style.backgroundImage = "url(" + E.zoomBarImageUrl + ")"
            }
        } else {
            F.style.height = E.height + "px";
            if (_ygPos.browser != "ie") {
                F.style.top = (0 - E.height) + "px";
                if (E.ordinal) {
                    F.style.left = h.offsetWidth + "px"
                } else {
                    F.style.left = i.offsetWidth + "px"
                }
            }
            F.style.styleFloat = "left";
            if (!E.useIntersectedZoomBar) {
                F.style.width = E.zoomBarImageLength + "px";
                F.style.backgroundImage = "url(" + E.zoomBarImageUrl + ")"
            }
        }
        if (E.useIntersectedZoomBar) {
            for (var m = 0; m < E.zoomLevels; m++) {
                var l = new Image();
                l.src = E.zoomBarImageUrl;
                if (E.position == SuperMap.IS.ZoomPosition.vertical) {
                    l.style.height = E.zoomBarImageLength + "px";
                    F.style.height = E.zoomBarImageLength * E.zoomLevels + "px"
                } else {
                    l.style.width = E.zoomBarImageLength + "px";
                    F.style.width = E.zoomBarImageLength * E.zoomLevels + "px"
                }
                F.appendChild(l)
            }
        }
        P = new Image();
        P.id = Z.id + "_Slider";
        P.style.position = "absolute";
        if (!E.useIntersectedZoomBar) {
            k = E.curZoomLevel * K + E.zoomBarStartAt
        } else {
            k = E.curZoomLevel * E.zoomBarImageLength + E.zoomBarStartAt;
            if (E.position == SuperMap.IS.ZoomPosition.vertical) {
                P.style.left = "0px"
            } else {
                P.style.top = "0px"
            }
        }
        P.src = E.sliderImageUrl;
        F.appendChild(P);
        if (E.position == SuperMap.IS.ZoomPosition.vertical) {
            P.style.width = E.width + "px";
            P.style.top = k + "px";
            F.onmousedown = A;
            F.onmousemove = B;
            F.onmouseup = X;
            V.appendChild(F)
        } else {
            P.style.height = E.height + "px";
            P.style.left = k - P.width / 2 + "px";
            F.onmousedown = A;
            F.onmousemove = B;
            F.onmouseup = X;
            V.appendChild(F)
        }
    }
    function N() {
        c();
        Z.style.styleFloat = "left";
        var l = U.GetMapParam();
        var k = l.mapScales;
        if (!k) {
            Z.style.display = "none"
        }
        if (E.ordinal) {
            T();
            M();
            I()
        } else {
            I();
            M();
            T()
        }
    }
}