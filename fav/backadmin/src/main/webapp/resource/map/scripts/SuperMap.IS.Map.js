//================================================================================ 
// SuperMap IS .NET 客户端程序，版权所有，北京超图软件股份有限公司，2000-2009。 
// 本程序只能在有效的授权许可下使用。未经许可，不得以任何手段擅自使用或传播。 
// 作 者:  SuperMap IS Web Team 
// 版 本:  $Id: SuperMap.IS.Map.js,v 1.39.2.9 2009/11/09 05:50:12 zengyw Exp $
// 文 件:  SuperMap.IS.Map.js 
// 描 述:  AjaxScripts Map 类 
// 更 新:  $Date: 2009/11/09 05:50:12 $ 
//================================================================================ 
SuperMap.IS.Map = function(params) {
    var _self = this;
    var referViewBounds = null,
    referScale = 0,
    referViewer = null,
    mapBounds = null;
    var _params = params;
    var _layersBackupForHandler = new Array();
    var _eventsList = new Array(),
    _eventsNameList = new Array();
    var _t = new Date().getTime();
    var useReferBounds = false;
    this.layers = new Array();
    this.layersBackupForHandler = _layersBackupForHandler;
    this.mapName = "";
    this.mapScale = 0;
    this.Init = function(onComplete) {
        if (!_params.imageFormat) {
            _params.imageFormat = "png"
        }
        if (!_params.antiAlias) {
            _params.antiAlias = false
        }
        if (!_params.mapHandler) {
            _params.mapHandler = "./"
        }
        if (!_params.mapName) {
            _params.mapName = ""
        }
        if (!_params.mapScale) {
            _params.mapScale = null
        }
        if (_params.useImageBuffer == undefined) {
            _params.useImageBuffer = true
        }
        if (!_params.tileSize) {
            _params.tileSize = 256
        }
        if (!_params.layersKey) {
            _params.layersKey = 0
        }
        if (_params.redirect == undefined) {
            _params.redirect = true
        }
        if (_params.returnLayers == undefined) {
            _params.returnLayers = false
        }
        if (_params.returnThemes == undefined) {
            _params.returnThemes = false
        }
        this.mapName = _params.mapName;
        this.mapScale = _params.mapScale;
        _InitLayersKey();
        _InitHandlerLayers();
        function onRequestComplete(responseText) {
            var mapStatusJ = _eval("(" + responseText + ")");
            if (!mapStatusJ) {
                return
            }
            var mapStatus = new SuperMap.IS.MapStatus();
            mapStatus.FromJSON(mapStatusJ);
            if (_params.x == null) {
                _params.x = mapStatus.referViewBounds.Center().x
            }
            if (_params.y == null) {
                _params.y = mapStatus.referViewBounds.Center().y
            }
            if (_params.mapScale == null) {
                this.mapScale = _params.mapScale = mapStatus.referScale
            }
            _params.mapName = mapStatus.mapName;
            if (referViewBounds == null) {
                referViewBounds = new SuperMap.IS.MapRect()
            }
            if (mapBounds == null) {
                mapBounds = new SuperMap.IS.MapRect()
            }
            referViewBounds.FromJSON(mapStatus.referViewBounds);
            mapBounds.FromJSON(mapStatus.mapBounds);
            referScale = mapStatus.referScale;
            if (referViewer == null) {
                referViewer = new SuperMap.IS.PixelRect()
            }
            referViewer.FromJSON(mapStatus.referViewer);
            _self.mapName = mapStatus.mapName;
            if (_params.imageFormat.toLowerCase() == "default") {
                _params.imageFormat = mapStatus.imageFormat
            }
            if (typeof(_params.antiAlias) == "undefined") {
                _params.antiAlias = mapStatus.antiAlias
            }
            if (mapStatus.referBounds) {
                useReferBounds = true;
                _params.useReferBounds = true
            } else {
                _params.useReferBounds = false
            }
            if (onComplete) {
                onComplete(mapStatus)
            }
            mapStatus.Destroy();
            mapStatus = null
        }
        this.GetMapStatus(_params.returnLayers, _params.returnThemes, onRequestComplete)
    };
    this.Destroy = function() {
        if (this.layers != null) {
            while (this.layers.length > 0) {
                var cLayer = this.layers.pop();
                cLayer.Destroy();
                cLayer = null
            }
            this.layers = null
        }
        if (this.layersBackupForHandler != null) {
            while (this.layersBackupForHandler.length > 0) {
                var backLayer = this.layersBackupForHandler.pop();
                backLayer.Destroy();
                backLayer = null
            }
            this.layersBackupForHandler = null
        }
        if (this.referViewBounds != null) {
            this.referViewBounds.Destroy();
            this.referViewBounds = null
        }
        if (this.mapBounds != null) {
            this.mapBounds.Destroy();
            this.mapBounds = null
        }
        if (this.referViewer != null) {
            this.referViewer.Destroy();
            this.referViewer = null
        }
        if (_eventsList != null) {
            while (_eventsList.length > 0) {
                var eventList = _eventsList.pop();
                eventList = null
            }
            _eventsList = null
        }
        if (_eventsNameList != null) {
            while (_eventsNameList.length > 0) {
                var eventNameList = _eventsNameList.pop();
                eventNameList = null
            }
            _eventsNameList = null
        }
    };
    function _IsValidTile(_tx, _ty, ms) {
        return true
    }
    function _GetTileUrl(_tx, _ty, ms) {
        var returnString = _params.mapHandler + "ajax/" + encodeURI(_params.mapName) + "/" + ms + "/" + _tx + "/" + _ty + "/" + _params.tileSize + "/" + _params.imageFormat + "/" + _params.layersKey + "/" + _params.antiAlias + "/" + useReferBounds + "/map.ashx?t=" + _t + "&redirect=" + _params.redirect;
        return returnString
    }
    function _PixelToMapDistance(pd, ms, byHeight) {
        if (referViewBounds == null || referViewer == null) {
            return
        }
        if (byHeight) {
            return (referScale / ms) * referViewBounds.Height() / referViewer.Height() * pd
        } else {
            return (referScale / ms) * referViewBounds.Width() / referViewer.Width() * pd
        }
    }
    function _ComputeMapScaleByDistance(md, mc, pd) {
        return (referScale / mc) * referViewBounds.Width() / referViewer.Width() * pd
    }
    function _PixelToMapCoord(pc, ms) {
        var distance = _PixelToMapDistance(1, ms);
        var distanceY = _PixelToMapDistance(1, ms, true);
        var mc = new SuperMap.IS.MapCoord();
        if (distance == null || distanceY == null) {
            return mc
        }
        mc.x = mapBounds.leftBottom.x + (pc.x * distance);
        mc.y = mapBounds.rightTop.y - (pc.y * distanceY);
        return mc
    }
    function _MapCoordToPixel(mc, ms) {
        var distance = _PixelToMapDistance(1, ms);
        var distanceY = _PixelToMapDistance(1, ms, true);
        var pc = new SuperMap.IS.PixelCoord();
        if (distance == null || distanceY == null) {
            return pc
        }
        pc.x = parseInt((mc.x - mapBounds.leftBottom.x) / distance);
        pc.y = parseInt((mapBounds.rightTop.y - mc.y) / distanceY);
        return pc
    }
    function _GetBounds() {
        return mapBounds
    }
    function _ValidateMapScale(param) {
        if (param.mapScale <= 0) {
            param.SetMapScale(1)
        }
    }
    this.IsValidTile = _IsValidTile;
    this.GetTileUrl = _GetTileUrl;
    this.PixelToMapDistance = _PixelToMapDistance;
    this.PixelToMapCoord = _PixelToMapCoord;
    this.MapCoordToPixel = _MapCoordToPixel;
    this.GetBounds = _GetBounds;
    this.ValidateMapScale = _ValidateMapScale;
    this.ComputeMapScaleByDistance = _ComputeMapScaleByDistance;
    this.Update = function(onComplete, onError, userContext) {
        if (!_layersBackupForHandler) {
            _BackupLayers(_layersBackupForHandler, _params.layers)
        }
        var changedLayersFromHandlerJSON = _FindDifference(_layersBackupForHandler, _params.layers);
        var methodName = "UpdateLayers";
        var bModifiedByServer = false;
        if (_params.bModifiedByServer == true) {
            bModifiedByServer = true;
            _params.bModifiedByServer = false
        }
        var queryUrl = _params.mapHandler + "common.ashx";
        var mapName = _params.mapName;
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var oldLayerKey = _params.layersKey;
            _params.layersKey = _eval("(" + responseText + ")");
            _SetLayersKeyHidden(_params.layersKey);
            _BackupLayers(_layersBackupForHandler, _params.layers);
            _SetHandlerLayersHidden();
            _t = new Date().getTime();
            if (oldLayerKey != 0) {
                _TriggerEvent("onchangelayer")
            }
            if (onComplete) {
                onComplete(_params.layersKey, userContext)
            }
        }
        var reuqestManager = new SuperMap.IS.RequestManager(queryUrl, onRequestComplete, onError, userContext);
        reuqestManager.AddQueryString("map", mapName);
        reuqestManager.AddQueryString("method", methodName);
        reuqestManager.AddQueryString("layers", changedLayersFromHandlerJSON);
        reuqestManager.AddQueryString("layersKey", _params.layersKey);
        reuqestManager.AddQueryString("bModifiedByServer", bModifiedByServer);
        reuqestManager.Send();
        reuqestManager.Destroy();
        reuqestManager = null
    };
    this.MeasureDistance = function(points, isHighlight, onComplete, onError, userContext) {
        var methodName = "MeasureDistance";
        var paramNames = ["points", "isHighlight", "trackingLayerIndex", "userID"];
        var paramValues = [points, isHighlight, _params.trackingLayerIndex, _params.userID];
        var queryUrl = _params.mapHandler + "common.ashx";
        var mapName = _params.mapName;
        function onRequestComplete(responseText) {
            var resultJ = _eval("(" + responseText + ")");
            if (resultJ == null) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var result = new SuperMap.IS.MeasureResult();
            result.FromJSON(resultJ);
            _ChangeTrackingLayerKeyInternal(result.trackingLayerIndex, result.userID, true);
            if (onComplete) {
                onComplete(result, userContext)
            }
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
    };
    this.MeasureArea = function(points, isHighlight, onComplete, onError, userContext) {
        var methodName = "MeasureArea";
        var paramNames = ["points", "isHighlight", "trackingLayerIndex", "userID"];
        var paramValues = [points, isHighlight, _params.trackingLayerIndex, _params.userID];
        var queryUrl = _params.mapHandler + "common.ashx";
        var mapName = _params.mapName;
        function onRequestComplete(responseText) {
            var resultJ = _eval("(" + responseText + ")");
            if (resultJ == null) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var result = new SuperMap.IS.MeasureResult();
            result.FromJSON(resultJ);
            _ChangeTrackingLayerKeyInternal(result.trackingLayerIndex, result.userID, true);
            if (onComplete) {
                onComplete(result, userContext)
            }
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
    };
    this.CustomInvoke = function(customParams, identifier, onComplete, onError, userContext) {
        var methodName = "CustomInvoke";
        var paramNames = ["customParams", "identifier"];
        var paramValues = [customParams, identifier];
        var queryUrl = _params.mapHandler + "common.ashx";
        var mapName = _params.mapName;
        function onRequestComplete(responseText) {
            if (onComplete) {
                onComplete(responseText, userContext)
            }
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
    };
    this.GetOverview = function(overview, onComplete, onError, userContext) {
        var methodName = "GetOverview";
        var paramNames = ["overview"];
        var paramValues = [overview];
        var queryUrl = _params.mapHandler + "common.ashx";
        var mapName = _params.mapName;
        function onRequestComplete(responseText) {
            var resultJ = _eval("(" + responseText + ")");
            if (resultJ == null) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var result = new SuperMap.IS.Overview();
            result.FromJSON(resultJ);
            if (onComplete) {
                onComplete(result, userContext)
            }
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
    };
    this.GetMapImage = function(mapParam, onComplete, onError, userContext) {
        var methodName = "GetMapImage";
        var paramNames = ["mapParam"];
        var paramValues = [mapParam];
        var queryUrl = _params.mapHandler + "common.ashx";
        var mapName = _params.mapName;
        function onRequestComplete(responseText) {
            if (onComplete) {
                onComplete(responseText, userContext)
            }
        }
        var reuqestManager = new SuperMap.IS.RequestManager(queryUrl, onRequestComplete, onError, userContext);
        reuqestManager.AddQueryString("map", mapName);
        reuqestManager.AddQueryString("method", methodName);
        reuqestManager.AddQueryString("layersKey", _params.layersKey);
        reuqestManager.AddQueryString("userId", _params.userID);
        reuqestManager.AddQueryString("trackingLayerHistoryIndex", _params.trackingLayerIndex);
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
    };
    this.GetMapImageByDpi = function(mapParam, imageDpi, onComplete, onError, userContext) {
        var methodName = "GetMapImageByDpi";
        var paramNames = ["mapParam", "imageDpi"];
        var paramValues = [mapParam, imageDpi];
        var queryUrl = _params.mapHandler + "common.ashx";
        var mapName = _params.mapName;
        function onRequestComplete(responseText) {
            if (onComplete) {
                onComplete(responseText, userContext)
            }
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
    };
    this.GetResource = function(mapName, resourceParam, onComplete, onError, userContext) {
        var methodName = "GetResource";
        var paramNames = ["resourceParam"];
        var paramValues = [resourceParam];
        var queryUrl = _params.mapHandler + "common.ashx";
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            if (onComplete) {
                onComplete(responseText, userContext)
            }
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
    };
    this.GetGeometryImage = function(mapName, geometryParam, onComplete, onError, userContext) {
        var methodName = "GetGeometryImage";
        var paramNames = ["geometryParam"];
        var paramValues = [geometryParam];
        var queryUrl = _params.mapHandler + "common.ashx";
        function onRequestComplete(responseText) {
            var resultJ = _eval("(" + responseText + ")");
            if (resultJ == null) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var result = new SuperMap.IS.GeometryImage();
            result.FromJSON(resultJ);
            if (onComplete) {
                onComplete(result, userContext)
            }
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
    };
    this.ClearHighlight = function(onComplete, onError, userContext) {
        var methodName = "ClearHighlight";
        var paramNames = ["trackingLayerIndex", "userID"];
        var paramValues = [_params.trackingLayerIndex, _params.userID];
        var queryUrl = _params.mapHandler + "common.ashx";
        var mapName = _params.mapName;
        function onRequestComplete() {
            _ChangeTrackingLayerKeyInternal(_params.trackingLayerIndex + 1, _params.userID, true);
            if (onComplete) {
                onComplete(userContext)
            }
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
    };
    this.GetEntity = function(mapName, layerName, id, onComplete, onError, userContext) {
        var methodName = "GetEntity";
        var paramNames = ["layerName", "id"];
        var paramValues = [layerName, id];
        var queryUrl = _params.mapHandler + "common.ashx";
        var mapName = _params.mapName;
        function onRequestComplete(responseText) {
            var entityJ = _eval("(" + responseText + ")");
            if (entityJ == null) {
                onComplete(null, userContext);
                return
            }
            var entity = new SuperMap.IS.Entity();
            entity.FromJSON(entityJ);
            if (onComplete) {
                onComplete(entity, userContext);
                entity.Destroy();
                entity = null
            }
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
    };
    this.GetEntities = function(mapName, layerName, ids, onComplete, onError, userContext) {
        var methodName = "GetEntities";
        var paramNames = ["layerName", "ids"];
        var paramValues = [layerName, ids];
        var queryUrl = _params.mapHandler + "common.ashx";
        var mapName = _params.mapName;
        function onRequestComplete(responseText) {
            var entitiesJ = _eval("(" + responseText + ")");
            if (entitiesJ == null) {
                onComplete(null, userContext);
                return
            }
            var entities = new Array();
            for (var i = 0; i < entitiesJ.length; i++) {
                entities[i] = new SuperMap.IS.Entity();
                entities[i].FromJSON(entitiesJ[i])
            }
            if (onComplete) {
                onComplete(entities, userContext)
            }
            while (entities.length > 0) {
                var entity = entities.pop();
                entity.Destroy();
                entity = null
            }
            entities = null
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
    };
    this.GetWorkspaceInfo = function(onComplete, onError, userContext) {
        var methodName = "GetWorkspaceInfo";
        var queryUrl = _params.mapHandler + "common.ashx";
        var mapName = _params.mapName;
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var resultJ = _eval("(" + responseText + ")");
            if (!resultJ) {
                onComplete(null, userContext);
                return
            }
            var result = new SuperMap.IS.Workspace();
            result.FromJSON(resultJ);
            if (onComplete) {
                onComplete(result, userContext)
            }
            result.Destroy();
            result = null
        }
        var reuqestManager = new SuperMap.IS.RequestManager(queryUrl, onRequestComplete, onError, userContext);
        reuqestManager.AddQueryString("map", mapName);
        reuqestManager.AddQueryString("method", methodName);
        reuqestManager.Send();
        reuqestManager.Destroy();
        reuqestManager = null
    };
    this.GetDatasetInfo = function(datasource, dataset, onComplete, onError, userContext) {
        var methodName = "GetDatasetInfo";
        var paramNames = ["datasource", "dataset"];
        var paramValues = [datasource, dataset];
        var queryUrl = _params.mapHandler + "common.ashx";
        var mapName = _params.mapName;
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var resultJ = _eval("(" + responseText + ")");
            if (!resultJ) {
                onComplete(null, userContext);
                return
            }
            var result = new SuperMap.IS.Dataset();
            result.FromJSON(resultJ);
            if (onComplete) {
                onComplete(result, userContext)
            }
            result.Destroy();
            result = null
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
    };
    this.ClearCache = function(mapName, mapRect, onComplete, onError, userContext) {
        var methodName = "ClearCache";
        var paramNames = ["mapName", "mapRect"];
        var paramValues = [mapName, mapRect];
        var queryUrl = _params.mapHandler + "common.ashx";
        var mapName = _params.mapName;
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var success = _eval("(" + responseText + ")");
            if (onComplete) {
                onComplete(success, userContext)
            }
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
    };
    this.ConnectDatasources = function(onComplete, onError, userContext) {
        var methodName = "ConnectDatasources";
        var queryUrl = _params.mapHandler + "common.ashx";
        var mapName = _params.mapName;
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var success = _eval("(" + responseText + ")");
            if (onComplete) {
                onComplete(success, userContext)
            }
        }
        var reuqestManager = new SuperMap.IS.RequestManager(queryUrl, onRequestComplete, onError, userContext);
        reuqestManager.AddQueryString("map", mapName);
        reuqestManager.AddQueryString("method", methodName);
        reuqestManager.Send();
        reuqestManager.Destroy();
        reuqestManager = null
    };
    this.GetFieldInfo = function(dataset, fieldName, onComplete, onError, userContext) {
        var methodName = "GetFieldInfo";
        var paramNames = ["dataset", "fieldName"];
        var paramValues = [dataset, fieldName];
        var queryUrl = _params.mapHandler + "common.ashx";
        var mapName = _params.mapName;
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var resultJ = _eval("(" + responseText + ")");
            if (!resultJ) {
                onComplete(null, userContext);
                return
            }
            var result = new SuperMap.IS.GetFieldResult();
            result.FromJSON(resultJ);
            if (onComplete) {
                onComplete(result, userContext)
            }
            result.Destroy();
            result = null
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
    };
    this.MakeDefaultDotDensityTheme = function(layerName, expression, colorSet, onComplete, onError, userContext) {
        var methodName = "MakeDefaultDotDensityTheme";
        var paramNames = ["layerName", "expression", "colorSet"];
        var paramValues = [layerName, expression, colorSet];
        var queryUrl = _params.mapHandler + "common.ashx";
        var mapName = _params.mapName;
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var resultJ = _eval("(" + responseText + ")");
            if (!resultJ) {
                onComplete(null, userContext);
                return
            }
            var result = new SuperMap.IS.DotDensityTheme();
            result.FromJSON(resultJ);
            if (onComplete) {
                onComplete(result, userContext)
            }
            result.Destroy();
            result = null
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
    };
    this.MakeDefaultGraduatedSymbolTheme = function(layerName, expression, colorSet, onComplete, onError, userContext) {
        var methodName = "MakeDefaultGraduatedSymbolTheme";
        var paramNames = ["layerName", "expression", "colorSet"];
        var paramValues = [layerName, expression, colorSet];
        var queryUrl = _params.mapHandler + "common.ashx";
        var mapName = _params.mapName;
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var resultJ = _eval("(" + responseText + ")");
            if (!resultJ) {
                onComplete(null, userContext);
                return
            }
            var result = new SuperMap.IS.GraduatedSymbolTheme();
            result.FromJSON(resultJ);
            if (onComplete) {
                onComplete(result, userContext)
            }
            result.Destroy();
            result = null
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
    };
    this.MakeDefaultGraphTheme = function(layerName, expressions, colorSet, onComplete, onError, userContext) {
        var methodName = "MakeDefaultGraphTheme";
        var paramNames = ["layerName", "expressions", "colorSet"];
        var paramValues = [layerName, expressions, colorSet];
        var queryUrl = _params.mapHandler + "common.ashx";
        var mapName = _params.mapName;
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var resultJ = _eval("(" + responseText + ")");
            if (!resultJ) {
                onComplete(null, userContext);
                return
            }
            var result = new SuperMap.IS.GraphTheme();
            result.FromJSON(resultJ);
            if (onComplete) {
                onComplete(result, userContext)
            }
            result.Destroy();
            result = null
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
    };
    this.MakeDefaultRangeTheme = function(layerName, expression, breakCount, colorSet, onComplete, onError, userContext) {
        var methodName = "MakeDefaultRangeTheme";
        var paramNames = ["layerName", "expression", "breakCount", "colorSet"];
        var paramValues = [layerName, expression, breakCount, colorSet];
        var queryUrl = _params.mapHandler + "common.ashx";
        var mapName = _params.mapName;
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var resultJ = _eval("(" + responseText + ")");
            if (!resultJ) {
                onComplete(null, userContext);
                return
            }
            var result = new SuperMap.IS.RangeTheme();
            result.FromJSON(resultJ);
            if (onComplete) {
                onComplete(result, userContext)
            }
            result.Destroy();
            result = null
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
    };
    this.MakeDefaultUniqueTheme = function(layerName, expression, colorSet, startSmID, expectCount, onComplete, onError, userContext) {
        var methodName = "MakeDefaultUniqueTheme";
        var paramNames = ["layerName", "expression", "expectCount", "colorSet", "startSmID"];
        var paramValues = [layerName, expression, expectCount, colorSet, startSmID];
        var queryUrl = _params.mapHandler + "common.ashx";
        var mapName = _params.mapName;
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var resultJ = _eval("(" + responseText + ")");
            if (!resultJ) {
                onComplete(null, userContext);
                return
            }
            var result = new SuperMap.IS.UniqueTheme();
            result.FromJSON(resultJ);
            if (onComplete) {
                onComplete(result, userContext)
            }
            result.Destroy();
            result = null
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
    };
    this.MakeDefaultGridRangeTheme = function(layerName, breakCount, colorSet, onComplete, onError, userContext) {
        var methodName = "MakeDefaultGridRangeTheme";
        var paramNames = ["layerName", "breakCount", "colorSet"];
        var paramValues = [layerName, breakCount, colorSet];
        var queryUrl = _params.mapHandler + "common.ashx";
        var mapName = _params.mapName;
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var resultJ = _eval("(" + responseText + ")");
            if (!resultJ) {
                onComplete(null, userContext);
                return
            }
            var result = new SuperMap.IS.GridRangeTheme();
            result.FromJSON(resultJ);
            if (onComplete) {
                onComplete(result, userContext)
            }
            result.Destroy();
            result = null
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
    };
    this.GetMapBounds = function() {
        var mb = new SuperMap.IS.MapRect();
        mb.Copy(mapBounds);
        return mb
    };
    this.GetMapStatus = function(returnLayers, returnThemes, onComplete, onError, userContext) {
        var url = _params.mapHandler + "common.ashx";
        var requestManager = new SuperMap.IS.RequestManager(url, onComplete, onError, userContext);
        requestManager.AddQueryString("map", _params.mapName);
        requestManager.AddQueryString("method", "GetMapStatus");
        requestManager.AddQueryString("tileSize", _params.tileSize);
        requestManager.AddQueryString("returnLayers", returnLayers);
        requestManager.AddQueryString("returnThemes", returnThemes);
        if (_params.customKeys) {
            requestManager.AddQueryStrings(_params.customKeys, _params.customValues)
        }
        requestManager.Send();
        requestManager.Destroy();
        requestManager = null
    };
    function _SetLayersKeyHidden(layersKey) {
        var hidden = document.getElementById(_params.id + "_hiddenLayersKey");
        if (hidden) {
            hidden.value = layersKey
        }
    }
    function _SetHandlerLayersHidden() {
        var hidden = document.getElementById(_params.id + "_hiddenHandlerLayers");
        if (hidden) {
            hidden.value = _ToJSON(_layersBackupForHandler)
        }
    }
    function _InitLayersKey() {
        var layersKey;
        var hidden = document.getElementById(_params.id + "_hiddenLayersKey");
        if (hidden) {
            layersKey = hidden.value
        }
        if (!layersKey) {
            _params.layersKey = 0
        } else {
            _params.layersKey = layersKey
        }
    }
    function _InitHandlerLayers() {
        var layersInfo;
        var hidden = document.getElementById(_params.id + "_hiddenHandlerLayers");
        if (hidden) {
            layersInfo = hidden.value
        }
        if (layersInfo) {
            var o = _eval(layersInfo);
            if (o) {
                for (var i = 0; i < o.length; i++) {
                    _layersBackupForHandler[i] = new SuperMap.IS.Layer();
                    _layersBackupForHandler[i].FromJSON(o[i])
                }
            }
        }
    }
    function _ChangeTrackingLayerKey(trackingLayerIndex, userID) {
        _params.trackingLayerIndex = trackingLayerIndex;
        _params.userID = userID
    }
    function _ChangeTrackingLayerKeyInternal(trackingLayerIndex, userID, bSaveHistory) {
        if (_params.trackingLayerIndex != trackingLayerIndex || _params.userID != userID) {
            _params.trackingLayerIndex = trackingLayerIndex;
            _params.userID = userID;
            var param = new Object();
            param.trackingLayerIndex = _params.trackingLayerIndex;
            param.userID = _params.userID;
            param.bSaveHistory = bSaveHistory;
            _TriggerEvent("onchangetrackinglayer", param)
        }
    }
    function _AttachEvent(event, listener) {
        if (_eventsList == null) {
            return
        }
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
        if (_eventsList == null) {
            return
        }
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
        if (_eventsList == null) {
            return
        }
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
    function _GenerateEventArg(error, e) {
        var param = new Object();
        param.trackingLayerIndex = _params.trackingLayerIndex;
        param.userID = _params.userID;
        if (!error) {
            error = ""
        }
        return new EventArguments(param, error, e)
    }
    this.AttachEvent = _AttachEvent;
    this.DetachEvent = _DetachEvent;
    this.ChangeTrackingLayerKey = _ChangeTrackingLayerKey
}