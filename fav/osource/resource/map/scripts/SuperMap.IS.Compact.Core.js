﻿// ================================================================================
// SuperMap IS .NET 客户端程序，版权所有，北京超图软件股份有限公司，2000-2008。
// 本程序只能在有效的授权许可下使用。未经许可，不得以任何手段擅自使用或传播。 
// 作 者:  SuperMap IS Web Team
// 版 本:  $Id: SuperMap.IS.Compact.Core.js,v 1.2.2.2 2008/08/18 09:21:47 huzhn Exp $
// 文 件:  SuperMap.IS.Compact.Core.js
// 描 述:  AjaxScripts 压缩后的核心库，包含类型库及各组件库等
// 更 新:  $Date: 2008/08/18 09:21:47 $
// ================================================================================
SuperMap.IS.MapCoord = function(A, B) {
    this.x = A;
    this.y = B;
    this.ToString = function() {
        return "(" + this.x + ", " + this.y + ")"
    };
    this.Copy = function(C) {
        if (!C) {
            return
        }
        this.x = C.x;
        this.y = C.y
    };
    this.Equals = function(C) {
        if (!C || C.x != this.x || C.y != this.y) {
            return false
        }
        return true
    };
    this.FromJSON = function(C) {
        if (!C) {
            return
        }
        this.x = C.x;
        this.y = C.y
    };
    this.Destroy = function() {}
};
SuperMap.IS.MapRect = function(D, A, B, C) {
    this.leftBottom = new SuperMap.IS.MapCoord(D, A);
    this.rightTop = new SuperMap.IS.MapCoord(B, C);
    this.ToString = function() {
        return "(" + (this.leftBottom ? this.leftBottom.ToString() : "null") + ", " + (this.rightTop ? this.rightTop.ToString() : "null") + ")"
    };
    this.Copy = function(E) {
        if (!E) {
            return
        }
        if (!this.leftBottom) {
            this.leftBottom = new SuperMap.IS.MapCoord()
        }
        if (!this.rightTop) {
            this.rightTop = new SuperMap.IS.MapCoord()
        }
        this.leftBottom.Copy(E.leftBottom);
        this.rightTop.Copy(E.rightTop)
    };
    this.Center = function() {
        var E = new SuperMap.IS.MapCoord();
        E.x = 0.5 * (this.leftBottom.x + this.rightTop.x);
        E.y = 0.5 * (this.leftBottom.y + this.rightTop.y);
        return E
    };
    this.Contains = function(E) {
        return E.x >= this.leftBottom.x && E.y >= this.leftBottom.y && E.x <= this.rightTop.x && E.y <= this.rightTop.y
    };
    this.Equals = function(E) {
        if (!E || !E.leftBottom || !E.rightTop) {
            return false
        }
        if (!E.leftBottom.Equals(this.leftBottom) || !E.rightTop.Equals(this.rightTop)) {
            return false
        }
        return true
    };
    this.Width = function() {
        return this.rightTop.x - this.leftBottom.x
    };
    this.Height = function() {
        return this.rightTop.y - this.leftBottom.y
    };
    this.FromJSON = function(E) {
        if (!E) {
            return
        }
        if (E.leftBottom && !this.leftBottom) {
            this.leftBottom = new SuperMap.IS.MapCoord()
        }
        if (E.leftBottom) {
            this.leftBottom.x = E.leftBottom.x;
            this.leftBottom.y = E.leftBottom.y
        }
        if (E.rightTop && !this.rightTop) {
            this.rightTop = new SuperMap.IS.MapCoord()
        }
        if (E.rightTop) {
            this.rightTop.x = E.rightTop.x;
            this.rightTop.y = E.rightTop.y
        }
    };
    this.Destroy = function() {
        if (this.leftBottom) {
            this.leftBottom.Destroy();
            this.leftBottom = null
        }
        if (this.rightTop) {
            this.rightTop.Destroy();
            this.rightTop = null
        }
    }
};
SuperMap.IS.PixelCoord = function(A, B) {
    this.x = parseInt(A);
    this.y = parseInt(B);
    this.ToString = function() {
        return "(" + this.x + ", " + this.y + ")"
    };
    this.Copy = function(C) {
        if (!C) {
            return
        }
        this.x = C.x;
        this.y = C.y
    };
    this.Destroy = function() {}
};
SuperMap.IS.PixelRect = function(D, C, B, A) {
    this.leftTop = new SuperMap.IS.PixelCoord(D, C);
    this.rightBottom = new SuperMap.IS.PixelCoord(B, A);
    this.ToString = function() {
        return "(" + (this.leftTop ? this.leftTop.ToString() : "null") + ", " + (this.rightBottom ? this.rightBottom.ToString() : "null") + ")"
    };
    this.Copy = function(E) {
        if (!E) {
            return
        }
        if (!this.leftTop) {
            this.leftTop = new SuperMap.IS.PixelCoord()
        }
        if (!this.rightBottom) {
            this.rightBottom = new SuperMap.IS.PixelCoord()
        }
        this.leftTop.Copy(E.leftTop);
        this.rightBottom.Copy(E.rightBottom)
    };
    this.Width = function() {
        return _Abs(this.rightBottom.x - this.leftTop.x)
    };
    this.Height = function() {
        return _Abs(this.rightBottom.y - this.leftTop.y)
    };
    this.FromJSON = function(E) {
        if (!E) {
            return
        }
        if (E.leftTop && !this.leftTop) {
            this.leftTop = new SuperMap.IS.PixelCoord()
        }
        if (E.leftTop) {
            this.leftTop.x = E.leftTop.x;
            this.leftTop.y = E.leftTop.y
        }
        if (E.rightBottom && !this.rightBottom) {
            this.rightBottom = new SuperMap.IS.PixelCoord()
        }
        if (E.rightBottom) {
            this.rightBottom.x = E.rightBottom.x;
            this.rightBottom.y = E.rightBottom.y
        }
    };
    this.Destroy = function() {
        if (this.leftTop) {
            this.leftTop.Destroy();
            this.leftTop = null
        }
        if (this.rightBottom) {
            this.rightBottom.Destroy();
            this.rightBottom = null
        }
    }
};
SuperMap.IS.MapParam = function(X, D, K) {
    this.mapName = X;
    this.mapScales = K;
    this.mapScale = D;
    this.zoomLevel = 1;
    this.pixelCenter = new SuperMap.IS.PixelCoord();
    this.center = new SuperMap.IS.MapCoord();
    this.mapCenter = this.center;
    this.pixelRect = new SuperMap.IS.PixelRect();
    this.mapRect = new SuperMap.IS.MapRect();
    var O = this;
    var V = "pc";
    this.Destroy = function() {
        this.mapScales = null;
        if (this.pixelCenter) {
            this.pixelCenter.Destroy();
            this.pixelCenter = null
        }
        if (this.center) {
            this.center.Destroy();
            this.center = null
        }
        if (this.mapCenter) {
            this.mapCenter.Destroy();
            this.mapCenter = null
        }
        if (this.pixelRect) {
            this.pixelRect.Destroy();
            this.pixelRect = null
        }
        if (this.mapRect) {
            this.mapRect.Destroy();
            this.mapRect = null
        }
        O = null
    };
    this.GetViewType = function() {
        return V
    };
    function R() {
        var Y = new SuperMap.IS.MapParam();
        Y.Copy(O);
        return Y
    }
    function M(Z) {
        if (Z.mapScales) {
            if (O.mapScales == null) {
                O.mapScales = new Array()
            }
            for (var Y = 0; Y < Z.mapScales.length; Y++) {
                O.mapScales = Z.mapScales
            }
        }
        O.mapName = Z.mapName;
        O.zoomLevel = Z.zoomLevel;
        O.mapScale = Z.mapScale;
        O.pixelCenter.Copy(Z.pixelCenter);
        O.center.Copy(Z.center);
        O.pixelRect.Copy(Z.pixelRect);
        O.mapRect.Copy(Z.mapRect);
        V = Z.GetViewType()
    }
    function T(Y) {
        return Y != null && O.mapName == Y.mapName && O.zoomLevel == Y.zoomLevel && O.mapScale == Y.mapScale && _Abs(O.pixelCenter.x - Y.pixelCenter.x) < 0.000001 && _Abs(O.pixelCenter.y - Y.pixelCenter.y) < 0.000001
    }
    function W() {
        return "(" + O.mapName + ", " + O.center.ToString() + ", " + O.zoomLevel + "," + O.mapScale + ")"
    }
    function P(Y) {
        if (!Y) {
            return
        }
        O.pixelCenter = Y;
        V = "pc"
    }
    function Q(Y) {
        if (!Y) {
            return
        }
        O.center = Y;
        V = "mc"
    }
    function E(Y) {
        O.pixelRect = Y;
        V = "pr"
    }
    function C(Y) {
        O.mapRect = Y;
        V = "mr"
    }
    function G(Y) {
        O.mapScales = Y
    }
    function H(Z) {
        if (!Z || Z <= 0) {
            return
        }
        Z = U(Z);
        var Y = Z / O.mapScale;
        switch (V) {
        case "pc":
            O.pixelCenter.x *= Y;
            O.pixelCenter.y *= Y;
            break;
        case "pr":
            O.pixelRect.leftTop.x *= Y;
            O.pixelRect.leftTop.y *= Y;
            O.pixelRect.rightBottom.x *= Y;
            O.pixelRect.rightBottom.y *= Y;
            break
        }
        O.mapScale = Z
    }
    function U(d) {
        if (!O.mapScales || !O.mapScales.length || O.mapScales.length <= 0) {
            return d
        }
        var Z = 0;
        if (d <= O.mapScales[0]) {
            Z = O.mapScales[0];
            O.zoomLevel = 1;
            return Z
        }
        var b = O.mapScales.length;
        if (d >= O.mapScales[b - 1]) {
            Z = O.mapScales[b - 1];
            O.zoomLevel = b;
            return Z
        }
        var c = 0;
        var f = 0;
        var Y = false;
        var e = 0;
        for (var a = 0; a < b; a++) {
            if (d <= O.mapScales[a]) {
                c = O.mapScales[a - 1];
                f = O.mapScales[a];
                e = a;
                break
            }
        }
        if (d / c <= f / d) {
            O.zoomLevel = e;
            return c
        } else {
            O.zoomLevel = e + 1;
            return f
        }
    }
    function A(Y) {
        O.mapName = Y;
        if (V == "pc") {
            V = "mc"
        }
    }
    function N(Y) {
        if (Y) {
            return O.pixelCenter.x * (Y / O.mapScale)
        }
        return O.pixelCenter.x
    }
    function L(Y) {
        if (Y) {
            return O.pixelCenter.y * (Y / O.mapScale)
        }
        return O.pixelCenter.y
    }
    function B(Y) {
        if (Y == undefined) {
            return O.pixelCenter
        }
        return new SuperMap.IS.PixelCoord(O.GetPixelX(Y), O.GetPixelY(Y))
    }
    function S(b, a, Y) {
        switch (V) {
        case "pc":
            O.center = b.PixelToMapCoord(O.pixelCenter, O.mapScale);
            break;
        case "mc":
            O.pixelCenter = b.MapCoordToPixel(O.center, O.mapScale);
            break;
        case "pr":
            O.pixelCenter.x = 0.5 * (O.pixelRect.leftTop.x + O.pixelRect.rightBottom.x);
            O.pixelCenter.y = 0.5 * (O.pixelRect.leftTop.y + O.pixelRect.rightBottom.y);
            O.center = b.PixelToMapCoord(O.pixelCenter, O.mapScale);
            J(b, a, Y);
            break;
        case "mr":
            var Z = O.mapRect;
            O.pixelRect.leftTop = b.MapCoordToPixel(new SuperMap.IS.MapCoord(Z.leftBottom.x, Z.rightTop.y), O.mapScale);
            O.pixelRect.rightBottom = b.MapCoordToPixel(new SuperMap.IS.MapCoord(Z.rightTop.x, Z.leftBottom.y), O.mapScale);
            O.center = new SuperMap.IS.MapCoord((Z.leftBottom.x + Z.rightTop.x) * 0.5, (Z.rightTop.y + Z.leftBottom.y) * 0.5);
            F(b, a, Y, true);
            break
        }
        V = "pc"
    }
    function J(Y, Z, j, l) {
        var d;
        var f = Z / O.pixelRect.Width();
        var b = j / O.pixelRect.Height();
        d = _Min(f, b);
        if (O.mapScales == null || O.mapScales.length <= 0) {
            O.mapScale = O.mapScale * d
        } else {
            var g = 0;
            var e = O.mapScale;
            var h = 0;
            var k = Number.MAX_VALUE;
            var a = 0;
            for (var c = 0; c < O.mapScales.length; c++) {
                g = O.mapScales[c] / O.mapScale;
                if ((d > 1 && g < 1) || (d < 1 && g > 1)) {
                    continue
                }
                if (l) {
                    h = d / g;
                    if (h > 1 && h < k) {
                        k = h;
                        e = O.mapScales[c];
                        a = c + 1
                    }
                } else {
                    h = g / d > 1 ? g / d: d / g;
                    if (h < k) {
                        k = h;
                        e = O.mapScales[c];
                        a = c + 1
                    }
                }
            }
            O.mapScale = e;
            O.zoomLevel = a
        }
        O.pixelCenter = Y.MapCoordToPixel(O.center, O.mapScale)
    }
    function F(Y, Z, m, p) {
        var d;
        var e = Y.PixelToMapDistance(Z, O.mapScale);
        var g = Y.PixelToMapDistance(m, O.mapScale);
        var f = O.mapRect.rightTop.x - O.mapRect.leftBottom.x;
        var q = O.mapRect.rightTop.y - O.mapRect.leftBottom.y;
        var k = e / f;
        var b = g / q;
        d = _Min(k, b);
        if (O.mapScales == null || O.mapScales.length <= 0) {
            O.mapScale = O.mapScale * d
        } else {
            var j = 0;
            var h = O.mapScale;
            var l = 0;
            var n = Number.MAX_VALUE;
            var a = 0;
            for (var c = 0; c < O.mapScales.length; c++) {
                j = O.mapScales[c] / O.mapScale;
                if ((d > 1 && j < 1) || (d < 1 && j > 1)) {
                    continue
                }
                if (p) {
                    l = d / j;
                    if (l > 1 && l < n) {
                        n = l;
                        h = O.mapScales[c];
                        a = c + 1
                    }
                } else {
                    l = j / d > 1 ? j / d: d / j;
                    if (l < n) {
                        n = l;
                        h = O.mapScales[c];
                        a = c + 1
                    }
                }
            }
            O.mapScale = h;
            O.zoomLevel = a
        }
        O.pixelCenter = Y.MapCoordToPixel(O.center, O.mapScale)
    }
    function I(Z) {
        if (!O.mapScales || !O.mapScales.length || O.mapScales.length <= 0) {
            O.zoomLevel = -1;
            return
        }
        if (Z <= 0 || Z > O.mapScales.length) {
            O.zoomLevel = -1;
            return
        }
        var Y = O.mapScales[Z - 1];
        H(Y)
    }
    this.MakeCopy = R;
    this.Copy = M;
    this.Equals = T;
    this.ToString = W;
    this.SetPixelCenter = P;
    this.SetMapCenter = Q;
    this.SetPixelRect = E;
    this.SetMapRect = C;
    this.SetMapScale = H;
    this.SetZoomLevel = I;
    this.SetMapName = A;
    this.GetPixelX = N;
    this.GetPixelY = L;
    this.GetPixelCenter = B;
    this.Resolve = S
};
SuperMap.IS.QueryLayer = function() {
    this.groupClause = "";
    this.layerId = 0;
    this.layerName = "";
    this.returnFields = null;
    this.sortClause = "";
    this.whereClause = "";
    this.relQueryTableInfos = null;
    this.FromJSON = function(B) {
        if (!B) {
            return
        }
        this.groupClause = B.groupClause;
        this.layerId = B.layerId;
        this.layerName = B.layerName;
        this.returnFields = B.returnFields;
        this.sortClause = B.sortClause;
        this.whereClause = B.whereClause;
        if (B.relQueryTableInfos) {
            if (!this.relQueryTableInfos) {
                this.relQueryTableInfos = new Array()
            }
            for (var A = 0; A < B.relQueryTableInfos.length; A++) {
                if (B.relQueryTableInfos[A]) {
                    this.relQueryTableInfos[A] = new SuperMap.IS.RelQueryTableInfo();
                    this.relQueryTableInfos[A].FromJSON(B.relQueryTableInfos[A])
                }
            }
        }
    };
    this.Destroy = function() {
        if (this.returnFields) {
            while (this.returnFields.length > 0) {
                this.returnFields.pop()
            }
            this.returnFields = null
        }
        if (this.relQueryTableInfos) {
            while (this.relQueryTableInfos.length > 0) {
                var A = this.relQueryTableInfos.pop();
                A.Destroy();
                A = null
            }
            this.relQueryTableInfos = null
        }
    }
};
SuperMap.IS.QueryParam = function() {
    this.customParams = "";
    this.expectCount = 10;
    this.hasGeometry = true;
    this.highlight = null;
    this.queryAllLayer = false;
    this.queryLayers = null;
    this.networkType = 0;
    this.returnFields = null;
    this.startRecord = 0;
    this.whereClause = "";
    this.returnCenterAndBounds = true;
    this.returnShape = false;
    this.FromJSON = function(B) {
        if (!B) {
            return
        }
        this.customParams = B.customParams;
        this.expectCount = B.expectCount;
        this.hasGeometry = B.hasGeometry;
        if (B.highlight) {
            if (!this.highlight) {
                this.highlight = new SuperMap.IS.Highlight()
            }
            this.highlight.FromJSON(B.highlight)
        }
        this.queryAllLayer = B.queryAllLayer;
        if (B.queryLayers) {
            if (!this.queryLayers) {
                this.queryLayers = new Array()
            }
            for (var A = 0; A < B.queryLayers.length; A++) {
                if (B.queryLayers[A]) {
                    this.queryLayers[A] = new SuperMap.IS.QueryLayer();
                    this.queryLayers[A].FromJSON(B.queryLayers[A])
                }
            }
        }
        this.networkType = B.networkType;
        if (B.returnFields) {
            if (!this.returnFields) {
                this.returnFields = new Array()
            }
            for (var A = 0; A < B.returnFields.length; A++) {
                this.returnFields[A] = B.returnFields[A]
            }
        }
        this.startRecord = B.startRecord;
        this.whereClause = B.whereClause;
        this.returnCenterAndBounds = B.returnCenterAndBounds;
        this.returnShape = B.returnShape
    };
    this.Destroy = function() {
        if (this.queryLayers) {
            while (this.queryLayers.length > 0) {
                var A = this.queryLayers.pop();
                A.Destroy();
                A = null
            }
            this.queryLayers = null
        }
        if (this.highlight) {
            this.highlight.Destroy();
            this.highlight = null
        }
        if (this.returnFields) {
            while (this.returnFields.length > 0) {
                this.returnFields.pop()
            }
            this.returnFields = null
        }
    }
};
SuperMap.IS.ResultSet = function() {
    this.currentCount = 0;
    this.customResponse = "";
    this.recordsets = null;
    this.totalCount = 0;
    this.trackingLayerIndex = -1;
    this.userID = "";
    this.Destroy = function() {
        if (this.recordsets) {
            while (this.recordsets.length > 0) {
                var A = this.recordsets.pop();
                A.Destroy();
                A = null
            }
        }
        this.recordsets = null
    };
    this.FromJSON = function(B) {
        if (!B) {
            return
        }
        this.currentCount = B.currentCount;
        this.customResponse = B.customResponse;
        if (B.recordsets && !this.recordsets) {
            this.recordsets = new Array()
        }
        for (var A = 0; B.recordsets && A < B.recordsets.length; A++) {
            if (B.recordsets[A] && !this.recordsets[A]) {
                this.recordsets[A] = new SuperMap.IS.Recordset()
            }
            if (B.recordsets[A]) {
                this.recordsets[A].FromJSON(B.recordsets[A])
            }
        }
        this.totalCount = B.totalCount;
        this.trackingLayerIndex = B.trackingLayerIndex;
        this.userID = B.userID
    }
};
SuperMap.IS.Recordset = function() {
    this.layerId = 0;
    this.layerName = "";
    this.records = null;
    this.returnFields = null;
    this.returnFieldAliases = null;
    this.returnFieldTypes = null;
    this.Destroy = function() {
        if (this.records) {
            while (this.records.length > 0) {
                this.records.pop().Destroy()
            }
            this.records = null
        }
        if (this.returnFields) {
            while (this.returnFields.length > 0) {
                this.returnFields.pop()
            }
            this.returnFields = null
        }
        if (this.returnFieldAliases) {
            while (this.returnFieldAliases.length > 0) {
                this.returnFieldAliases.pop()
            }
            this.returnFieldAliases = null
        }
        if (this.returnFieldTypes) {
            while (this.returnFieldTypes.length > 0) {
                this.returnFieldTypes.pop()
            }
            this.returnFieldTypes = null
        }
    };
    this.FromJSON = function(B) {
        if (!B) {
            return
        }
        this.layerId = B.layerId;
        this.layerName = B.layerName;
        if (B.records && !this.records) {
            this.records = new Array()
        }
        for (var A = 0; B.records && A < B.records.length; A++) {
            if (B.records[A] && !this.records[A]) {
                this.records[A] = new SuperMap.IS.Record()
            }
            if (B.records[A]) {
                this.records[A].FromJSON(B.records[A])
            }
        }
        if (B.returnFieldTypes && !this.returnFieldTypes) {
            this.returnFieldTypes = new Array()
        }
        if (B.returnFieldAliases && !this.returnFieldAliases) {
            this.returnFieldAliases = new Array()
        }
        this.returnFieldTypes = B.returnFieldTypes;
        this.returnFields = B.returnFields;
        this.returnFieldAliases = B.returnFieldAliases
    }
};
SuperMap.IS.Record = function() {
    this.bounds = null;
    this.center = null;
    this.fieldValues = null;
    this.shape = null;
    this.Destroy = function() {
        if (this.bounds) {
            this.bounds.Destroy();
            this.bounds = null
        }
        if (this.center) {
            this.center.Destroy();
            this.center = null
        }
        if (this.fieldValues) {
            while (this.fieldValues.length > 0) {
                this.fieldValues.pop()
            }
            this.fieldValues = null
        }
        if (this.shape) {
            this.shape.Destroy();
            this.shape = null
        }
    };
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        if (A.bounds) {
            if (!this.bounds) {
                this.bounds = new SuperMap.IS.MapRect()
            }
            this.bounds.FromJSON(A.bounds)
        }
        if (A.center && !this.center) {
            this.center = new SuperMap.IS.MapCoord()
        }
        if (A.center) {
            this.center.x = A.center.x;
            this.center.y = A.center.y
        }
        this.fieldValues = A.fieldValues;
        if (A.shape) {
            if (!this.shape) {
                this.shape = new SuperMap.IS.Geometry()
            }
            this.shape.FromJSON(A.shape)
        }
    }
};
function EventArguments(C, A, B) {
    this.param = C;
    this.error = A;
    this.e = B
}
SuperMap.IS.MapStatus = function() {
    this.mapName = "";
    this.mapBounds = null;
    this.referViewBounds = null;
    this.referScale = 0;
    this.referViewer = null;
    this.mapNames = null;
    this.layers = null;
    this.imageFormat = "";
    this.referBounds = null;
    this.Destroy = function() {
        if (this.mapBounds) {
            this.mapBounds.Destroy();
            this.mapBounds = null
        }
        if (this.referViewBounds) {
            this.referViewBounds.Destroy();
            this.referViewBounds = null
        }
        if (this.referViewer) {
            this.referViewer.Destroy();
            this.referViewer = null
        }
        if (this.mapNames) {
            while (this.mapNames.length > 0) {
                this.mapNames.pop()
            }
            this.mapNames = null
        }
        if (this.layers != null) {
            while (this.layers.length > 0) {
                var A = this.layers.pop();
                A.Destroy();
                A = null
            }
            this.layers = null
        }
        if (this.referBounds) {
            this.referBounds.Destroy();
            this.referBounds = null
        }
    };
    this.FromJSON = function(C) {
        if (!C) {
            return
        }
        this.mapName = C.mapName;
        this.mapNames = C.mapNames;
        if (C.mapBounds) {
            if (!this.mapBounds) {
                this.mapBounds = new SuperMap.IS.MapRect()
            }
            this.mapBounds.FromJSON(C.mapBounds)
        }
        if (C.referViewBounds) {
            if (!this.referViewBounds) {
                this.referViewBounds = new SuperMap.IS.MapRect()
            }
            this.referViewBounds.FromJSON(C.referViewBounds)
        }
        if (C.referViewer) {
            if (!this.referViewer) {
                this.referViewer = new SuperMap.IS.PixelRect()
            }
            this.referViewer.FromJSON(C.referViewer)
        }
        this.referScale = C.referScale;
        if (C.layers) {
            var B = C.layers.length;
            if (B > 0) {
                this.layers = new Array(B);
                for (var A = 0; A < B; A++) {
                    if (C.layers[A]) {
                        this.layers[A] = new SuperMap.IS.Layer();
                        this.layers[A].FromJSON(C.layers[A])
                    }
                }
            }
        }
        this.imageFormat = C.imageFormat;
        if (C.referBounds) {
            if (!this.referBounds) {
                this.referBounds = new SuperMap.IS.MapRect()
            }
            this.referBounds.FromJSON(C.referBounds)
        }
    };
    this.Copy = function(C) {
        if (!C) {
            return
        }
        this.mapName = C.mapName;
        if (C.mapNames) {
            if (this.mapNames == null) {
                this.mapNames = new Array()
            }
            for (var A = 0; A < C.mapNames.length; A++) {
                this.mapNames[A] = C.mapNames[A]
            }
        }
        if (C.mapBounds) {
            if (!this.mapBounds) {
                this.mapBounds = new SuperMap.IS.MapRect()
            }
            this.mapBounds.Copy(C.mapBounds)
        }
        if (C.referViewBounds) {
            if (!this.referViewBounds) {
                this.referViewBounds = new SuperMap.IS.MapRect()
            }
            this.referViewBounds.Copy(C.referViewBounds)
        }
        if (C.referViewer) {
            if (!this.referViewer) {
                this.referViewer = new SuperMap.IS.PixelRect()
            }
            this.referViewer.Copy(C.referViewer)
        }
        this.referScale = C.referScale;
        if (C.layers) {
            var B = C.layers.length;
            if (B > 0) {
                this.layers = new Array(B);
                for (var A = 0; A < B; A++) {
                    if (C.layers[A]) {
                        this.layers[A] = new SuperMap.IS.Layer();
                        this.layers[A].Copy(C.layers[A])
                    }
                }
            }
        }
        this.imageFormat = C.imageFormat;
        if (C.referBounds) {
            if (!this.referBounds) {
                this.referBounds = new SuperMap.IS.MapRect()
            }
            this.referBounds.Copy(C.referBounds)
        }
    }
};
SuperMap.IS.RouteParam = function() {
    this.highlight = null;
    this.networkParams = null;
    this.returnPathInfo = null;
    this.returnNodePositions = false;
    this.returnEdgeIDsAndNodeIDs = false;
    this.returnCosts = false;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        if (A.highlight) {
            this.highlight = new SuperMap.IS.Highlight();
            this.highlight.FromJSON(A.highlight)
        }
        if (A.networkParams) {
            this.networkParams = new SuperMap.IS.NetworkParams();
            this.networkParams.FromJSON(A.networkParams)
        }
        if (A.returnPathInfo) {
            this.returnPathInfo = new SuperMap.IS.ReturnPathInfoParam();
            this.returnPathInfo.FromJSON(A.returnPathInfo)
        }
        this.returnNodePositions = A.returnNodePositions;
        this.returnEdgeIDsAndNodeIDs = A.returnEdgeIDsAndNodeIDs;
        this.returnCosts = A.returnCosts
    };
    this.Destroy = function() {
        if (this.highlight) {
            this.highlight.Destroy();
            this.highlight = null
        }
        if (this.networkParams) {
            this.networkParams.Destroy();
            this.networkParams = null
        }
        if (this.returnPathInfo) {
            this.returnPathInfo.Destroy();
            this.returnPathInfo = null
        }
    }
};
SuperMap.IS.NetworkParams = function() {
    this.networkSetting = null;
    this.tolerance = 100;
    this.turnTableSetting = null;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        if (A.networkSetting) {
            this.networkSetting = new SuperMap.IS.NetworkSetting();
            this.networkSetting.FromJSON(A.networkSetting)
        }
        this.tolerance = A.tolerance;
        if (A.turnTableSetting) {
            this.turnTableSetting = new SuperMap.IS.TurnTableSetting();
            this.turnTableSetting.FromJSON(A.turnTableSetting)
        }
    };
    this.Destroy = function() {
        if (this.networkSetting) {
            this.networkSetting.Destroy();
            this.networkSetting = null
        }
        if (this.turnTableSetting) {
            this.turnTableSetting.Destroy();
            this.turnTableSetting = null
        }
    }
};
SuperMap.IS.NetworkSetting = function() {
    this.networkLayerName = "";
    this.nodeIDField = "";
    this.edgeIDField = "";
    this.edgeFilter = "";
    this.FTWeightField = "";
    this.TFWeightField = "";
    this.barrierEdges = null;
    this.barrierNodes = null;
    this.dynamicTimeSpan = 0;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.networkLayerName = A.networkLayerName;
        this.nodeIDField = A.nodeIDField;
        this.edgeIDField = A.edgeIDField;
        this.edgeFilter = A.edgeFilter;
        this.FTWeightField = A.FTWeightField;
        this.TFWeightField = A.TFWeightField;
        this.barrierEdges = A.barrierEdges;
        this.barrierNodes = A.barrierNodes;
        this.dynamicTimeSpan = A.dynamicTimeSpan
    };
    this.Destroy = function() {
        if (this.barrierEdges) {
            while (this.barrierEdges.length > 0) {
                this.barrierEdges.pop()
            }
            this.barrierEdges = null
        }
        if (this.barrierNodes) {
            while (this.barrierNodes.length > 0) {
                this.barrierNodes.pop()
            }
            this.barrierNodes = null
        }
    }
};
SuperMap.IS.TurnTableSetting = function() {
    this.turnTableName = "";
    this.fromEdgeIDField = "";
    this.nodeIDField = "";
    this.toEdgeIDField = "";
    this.weightField = "";
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.turnTableName = A.turnTableName;
        this.fromEdgeIDField = A.fromEdgeIDField;
        this.nodeIDField = A.nodeIDField;
        this.toEdgeIDField = A.toEdgeIDField;
        this.weightField = A.weightField
    };
    this.Copy = this.FromJSON;
    this.Destroy = function() {}
};
SuperMap.IS.ReturnPathInfoParam = function() {
    this.returnPathTable = false;
    this.edgeNameField = "";
    this.nodeNameField = "";
    this.returnPathGuide = false;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.returnPathTable = A.returnPathTable;
        this.edgeNameField = A.edgeNameField;
        this.nodeNameField = A.nodeNameField;
        this.returnPathGuide = A.returnPathGuide
    };
    this.Copy = this.FromJSON;
    this.Destroy = function() {}
};
SuperMap.IS.RouteResult = function() {
    this.pathTable = null;
    this.bounds = null;
    this.nodePositions = null;
    this.edgeIDs = null;
    this.nodeIDs = null;
    this.totalLength = 0;
    this.pathGuides = null;
    this.costs = null;
    this.trackingLayerIndex = -1;
    this.userID = "";
    this.FromJSON = function(B) {
        if (!B) {
            return
        }
        if (B.pathTable) {
            if (!this.pathTable) {
                this.pathTable = new SuperMap.IS.DataTable()
            }
            this.pathTable.FromJSON(B.pathTable)
        }
        this.totalLength = B.totalLength;
        if (B.bounds) {
            if (!this.bounds) {
                this.bounds = new SuperMap.IS.MapRect()
            }
            this.bounds.FromJSON(B.bounds)
        }
        if (B.nodePositions) {
            if (!this.nodePositions) {
                this.nodePositions = new Array()
            }
            for (var A = 0; A < B.nodePositions.length; A++) {
                if (B.nodePositions[A]) {
                    if (!this.nodePositions[A]) {
                        this.nodePositions[A] = new SuperMap.IS.MapCoord()
                    }
                    this.nodePositions[A].x = B.nodePositions[A].x;
                    this.nodePositions[A].y = B.nodePositions[A].y
                }
            }
        }
        if (B.edgeIDs && !this.edgeIDs) {
            this.edgeIDs = new Array()
        }
        for (var A = 0; B.edgeIDs && A < B.edgeIDs.length; A++) {
            this.edgeIDs[A] = B.edgeIDs[A]
        }
        if (B.nodeIDs && !this.nodeIDs) {
            this.nodeIDs = new Array()
        }
        for (var A = 0; B.nodeIDs && A < B.nodeIDs.length; A++) {
            this.nodeIDs[A] = B.nodeIDs[A]
        }
        if (B.pathGuides) {
            if (!this.pathGuides) {
                this.pathGuides = new Array()
            }
            for (var A = 0; A < B.pathGuides.length; A++) {
                if (B.pathGuides[A]) {
                    if (!this.pathGuides[A]) {
                        this.pathGuides[A] = new SuperMap.IS.PathGuide()
                    }
                    this.pathGuides[A].FromJSON(B.pathGuides[A])
                }
            }
        }
        if (B.costs && !this.costs) {
            this.costs = new Array()
        }
        for (var A = 0; B.costs && A < B.costs.length; A++) {
            this.costs[A] = B.costs[A]
        }
        this.trackingLayerIndex = B.trackingLayerIndex;
        this.userID = B.userID
    };
    this.Destroy = function() {
        if (this.pathTable) {
            this.pathTable.Destroy();
            this.pathTable = null
        }
        if (this.bounds) {
            this.bounds.Destroy();
            this.bounds = null
        }
        if (this.nodePositions) {
            while (this.nodePositions.length > 0) {
                var A = this.nodePositions.pop();
                A.Destroy();
                A = null
            }
            this.nodePositions = null
        }
        if (this.edgeIDs) {
            while (this.edgeIDs.length > 0) {
                this.edgeIDs.pop()
            }
            this.edgeIDs = null
        }
        if (this.nodeIDs) {
            while (this.nodeIDs.length > 0) {
                this.nodeIDs.pop()
            }
            this.nodeIDs = null
        }
        if (this.pathGuides) {
            while (this.pathGuides.length > 0) {
                var B = this.pathGuides.pop();
                B.Destroy();
                B = null
            }
            this.pathGuides = null
        }
        if (this.costs) {
            while (this.costs.length > 0) {
                this.costs.pop()
            }
            this.costs = null
        }
    };
    this.Copy = function(A) {
        if (!A) {
            return
        }
        if (A.pathTable) {
            if (!this.pathTable) {
                this.pathTable = new SuperMap.IS.DataTable()
            }
            this.pathTable.Copy(A.pathTable)
        }
        this.totalLength = A.totalLength;
        if (A.bounds && !this.bounds) {
            this.bounds = new SuperMap.IS.MapRect()
        }
        this.bounds.Copy(A.bounds);
        if (A.nodePositions && !this.nodePositions) {
            this.nodePositions = new Array()
        }
        for (var B = 0; A.nodePositions && B < A.nodePositions.length; B++) {
            if (A.nodePositions[B] && !this.nodePositions[B]) {
                this.nodePositions[B] = new SuperMap.IS.MapCoord()
            }
            if (o.nodePositions[B]) {
                this.nodePositions[B].x = A.nodePositions[B].x;
                this.nodePositions[B].y = A.nodePositions[B].y
            }
        }
        if (A.edgeIDs && !this.edgeIDs) {
            this.edgeIDs = new Array()
        }
        for (var B = 0; A.edgeIDs && B < A.edgeIDs.length; B++) {
            this.edgeIDs[B] = A.edgeIDs[B]
        }
        if (A.nodeIDs && !this.nodeIDs) {
            this.nodeIDs = new Array()
        }
        for (var B = 0; A.nodeIDs && B < A.nodeIDs.length; B++) {
            this.nodeIDs[B] = A.nodeIDs[B]
        }
        if (A.pathGuides && !this.pathGuides) {
            this.pathGuides = new Array()
        }
        for (var B = 0; A.pathGuides && B < A.pathGuides.length; B++) {
            if (A.pathGuides[B] && !this.pathGuides[B]) {
                this.pathGuides[B] = new SuperMap.IS.PathGuide()
            }
            if (A.pathGuides[B]) {
                this.pathGuides[B].FromJSON(A.pathGuides[B])
            }
        }
        if (A.costs && !this.costs) {
            this.costs = new Array()
        }
        for (var B = 0; A.costs && B < A.costs.length; B++) {
            this.costs[B] = A.costs[B]
        }
        this.trackingLayerIndex = A.trackingLayerIndex;
        this.userID = A.userID
    }
};
SuperMap.IS.BusLine = function() {
    this.lineName = "";
    this.smId = -1;
    this.lineId = -1;
    this.directionSign = "";
    this.points = null;
    this.FromJSON = function(B) {
        if (!B) {
            return
        }
        this.lineName = B.lineName;
        this.smId = B.smId;
        this.lineId = B.lineId;
        this.directionSign = B.directionSign;
        this.direction = B.direction;
        if (B.points && !this.points) {
            this.points = new Array()
        }
        if (B.points) {
            for (var A = 0; A < B.points.length; A++) {
                if (B.points[A] && !this.points[A]) {
                    this.points[A] = new SuperMap.IS.MapCoord()
                }
                if (B.points[A]) {
                    this.points[A].x = B.points[A].x;
                    this.points[A].y = B.points[A].y
                }
            }
        }
    };
    this.Destroy = function() {
        if (this.points) {
            while (this.points.length > 0) {
                var A = this.points.pop();
                A.Destroy();
                A = null
            }
            this.points = null
        }
    };
    this.Copy = function(B) {
        if (!B) {
            return
        }
        if (B.lineName) {
            this.lineName = B.Name
        }
        if (B.smId) {
            this.smId = B.smId
        }
        if (B.lineId) {
            this.lineId = B.lineId
        }
        if (B.directionSign) {
            this.directionSign = B.directionSign
        }
        if (B.points && !this.points) {
            this.points = new Array()
        }
        if (B.points) {
            for (var A = 0; A < B.points.length; A++) {
                if (B.points[A] && !this.points[A]) {
                    this.points[A] = new SuperMap.IS.MapCoord()
                }
                if (B.points[A]) {
                    this.points[A].Copy(B.points[A])
                }
            }
        }
    }
};
SuperMap.IS.BusStop = function() {
    this.stopName = "";
    this.smId = -1;
    this.stopId = -1;
    this.Location = null;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.stopName = A.stopName;
        this.smId = A.smId;
        this.stopId = A.stopId;
        if (A.Location && !this.Location) {
            this.Location = new SuperMap.IS.MapCoord()
        }
        if (A.Location) {
            this.Location.x = A.Location.x;
            this.Location.y = A.Location.y
        }
    };
    this.Destroy = function() {
        if (this.Location) {
            this.Location.Destroy();
            this.Location = null
        }
    };
    this.Copy = function(A) {
        if (!A) {
            return
        }
        this.stopName = A.stopName;
        this.smId = A.smId;
        this.stopId = A.stopId;
        if (A.Location && !this.Location) {
            this.Location = new SuperMap.IS.MapCoord()
        }
        if (A.Location) {
            this.Location.Copy(A.Location)
        }
    }
};
SuperMap.IS.BusRouting = function() {
    this.busLines = null;
    this.upStops = null;
    this.downStops = null;
    this.distance = 0;
    this.time = 0;
    this.partsAngle = null;
    this.partsDistance = null;
    this.FromJSON = function(B) {
        if (!B) {
            return
        }
        if (B.busLines && B.busLines.length > 0) {
            this.busLines = new Array();
            for (var A = 0; A < B.busLines.length; A++) {
                this.busLines[A] = new SuperMap.IS.BusLine();
                this.busLines[A].FromJSON(B.busLines[A])
            }
        }
        if (B.upStops && B.upStops.length > 0) {
            this.upStops = new Array();
            for (var A = 0; A < B.upStops.length; A++) {
                this.upStops[A] = new SuperMap.IS.BusStop();
                this.upStops[A].FromJSON(B.upStops[A])
            }
        }
        if (B.downStops && B.downStops.length > 0) {
            this.downStops = new Array();
            for (var A = 0; A < B.downStops.length; A++) {
                this.downStops[A] = new SuperMap.IS.BusStop();
                this.downStops[A].FromJSON(B.downStops[A])
            }
        }
        this.distance = B.distance;
        this.time = B.time;
        if (B.partsAngle && B.partsAngle.length > 0) {
            this.partsAngle = new Array();
            for (var A = 0; A < B.partsAngle.length; A++) {
                this.partsAngle[A] = B.partsAngle[A]
            }
        }
        if (B.partsDistance && B.partsDistance.length > 0) {
            this.partsDistance = new Array();
            for (var A = 0; A < B.partsDistance.length; A++) {
                this.partsDistance[A] = B.partsDistance[A]
            }
        }
    };
    this.Destroy = function() {
        if (this.busLines) {
            while (this.busLines.length > 0) {
                var A = this.busLines.pop();
                A.Destroy();
                A = null
            }
            this.busLines = null
        }
        if (this.upStops) {
            while (this.upStops.length > 0) {
                var A = this.upStops.pop();
                A.Destroy();
                A = null
            }
            this.upStops = null
        }
        if (this.downStops) {
            while (this.downStops.length > 0) {
                var A = this.downStops.pop();
                A.Destroy();
                A = null
            }
            this.downStops = null
        }
        if (this.partsAngle) {
            while (this.partsAngle.length > 0) {
                this.partsAngle.pop()
            }
            this.partsAngle = null
        }
        if (this.partsDistance) {
            while (this.partsDistance.length > 0) {
                this.partsDistance.pop()
            }
            this.partsDistance = null
        }
    };
    this.Copy = function(B) {
        if (!B) {
            return
        }
        if (B.busLines && B.busLines.length > 0) {
            this.busLines = new Array();
            for (var A = 0; A < B.busLines.length; A++) {
                this.busLines[A] = new SuperMap.IS.BusLine();
                this.busLines[A].Copy(B.busLines[A])
            }
        }
        if (B.upStops && B.upStops.length > 0) {
            this.upStops = new Array();
            for (var A = 0; A < B.upStops.length; A++) {
                this.upStops[A] = new SuperMap.IS.BusStop();
                this.upStops[A].Copy(B.upStops[A])
            }
        }
        if (B.downStops && B.downStops.length > 0) {
            this.downStops = new Array();
            for (var A = 0; A < B.downStops.length; A++) {
                this.downStops[A] = new SuperMap.IS.BusStop();
                this.downStops[A].Copy(B.downStops[A])
            }
        }
        this.distance = B.distance;
        this.time = B.time;
        if (B.partsAngle && B.partsAngle.length > 0) {
            this.partsAngle = new Array();
            for (var A = 0; A < B.partsAngle.length; A++) {}
            this.partsAngle[A] = B.partsAngle[A]
        }
        if (B.partsDistance && B.partsDistance.length > 0) {
            this.partsDistance = new Array();
            for (var A = 0; A < B.partsDistance.length; A++) {}
            this.partsDistance[A] = B.partsDistance[A]
        }
    }
};
SuperMap.IS.BusSolution = function() {
    this.routings = null;
    this.startStops = null;
    this.endStops = null;
    this.returnRouting = false;
    this.FromJSON = function(B) {
        if (!B) {
            return
        }
        if (B.routings && !this.routings) {
            this.routings = new Array()
        }
        for (var A = 0; B.routings && A < B.routings.length; A++) {
            if (B.routings[A] && !this.routings[A]) {
                this.routings[A] = new SuperMap.IS.BusRouting()
            }
            if (B.routings[A]) {
                this.routings[A].FromJSON(B.routings[A])
            }
        }
        if (B.startStops && !this.startStops) {
            this.startStops = new Array()
        }
        for (var A = 0; B.startStops && A < B.startStops.length; A++) {
            if (B.startStops[A] && !this.startStops[A]) {
                this.startStops[A] = new SuperMap.IS.BusStop()
            }
            if (B.startStops[A]) {
                this.startStops[A].FromJSON(B.startStops[A])
            }
        }
        if (B.endStops && !this.endStops) {
            this.endStops = new Array()
        }
        for (var A = 0; B.endStops && A < B.endStops.length; A++) {
            if (B.endStops[A] && !this.endStops[A]) {
                this.endStops[A] = new SuperMap.IS.BusStop()
            }
            if (B.endStops[A]) {
                this.endStops[A].FromJSON(B.endStops[A])
            }
        }
        this.returnRouting = B.returnRouting
    };
    this.Destroy = function() {
        if (this.routings != null) {
            while (this.routings.length > 0) {
                var C = this.routings.pop();
                C.Destroy();
                C = null
            }
            this.routings = null
        }
        if (this.startStops != null) {
            while (this.startStops.length > 0) {
                var A = this.startStops.pop();
                A.Destroy();
                A = null
            }
            this.startStops = null
        }
        if (this.endStops != null) {
            while (this.endStops.length > 0) {
                var B = this.endStops.pop();
                B.Destroy();
                B = null
            }
            this.endStops = null
        }
    };
    this.Copy = function(B) {
        if (!B) {
            return
        }
        if (B.routings && !this.routings) {
            this.routings = new Array()
        }
        for (var A = 0; B.routings && A < B.routings.length; A++) {
            if (B.routings[A] && !this.routings[A]) {
                this.routings[A] = new SuperMap.IS.BusRouting()
            }
            if (B.routings[A]) {
                this.routings[A].Copy(B.routings[A])
            }
        }
        if (B.startStops && !this.startStops) {
            this.startStops = new Array()
        }
        for (var A = 0; B.startStops && A < B.startStops.length; A++) {
            if (B.startStops[A] && !this.startStops[A]) {
                this.startStops[A] = new SuperMap.IS.BusStop()
            }
            if (B.startStops[A]) {
                this.startStops[A].Copy(B.startStops[A])
            }
        }
        if (B.endStops && !this.endStops) {
            this.endStops = new Array()
        }
        for (var A = 0; B.endStops && A < B.endStops.length; A++) {
            if (B.endStops[A] && !this.endStops[A]) {
                this.endStops[A] = new SuperMap.IS.BusStop()
            }
            if (B.endStops[A]) {
                this.endStops[A].Copy(B.endStops[A])
            }
        }
        this.returnRouting = B.returnRouting
    }
};
SuperMap.IS.Entity = function() {
    this.fieldNames = null;
    this.fieldValues = null;
    this.shape = null;
    this.id = -1;
    this.FromJSON = function(B) {
        if (!B) {
            return
        }
        if (B.fieldNames && !this.fieldNames) {
            this.fieldNames = new Array()
        }
        for (var A = 0; B.fieldNames && A < B.fieldNames.length; A++) {
            this.fieldNames[A] = B.fieldNames[A]
        }
        if (B.fieldValues && !this.fieldValues) {
            this.fieldValues = new Array()
        }
        for (var A = 0; B.fieldValues && A < B.fieldValues.length; A++) {
            this.fieldValues[A] = B.fieldValues[A]
        }
        if (B.shape && !this.shape) {
            this.shape = new SuperMap.IS.Geometry()
        }
        if (B.shape) {
            this.shape.FromJSON(B.shape)
        }
        this.id = B.id
    };
    this.Destroy = function() {
        if (this.fieldNames) {
            while (this.fieldNames.length > 0) {
                this.fieldNames.pop()
            }
            this.fieldNames = null
        }
        if (this.fieldValues) {
            while (this.fieldValues.length > 0) {
                this.fieldValues.pop()
            }
            this.fieldValues = null
        }
        if (this.shape) {
            this.shape.Destroy();
            this.shape = null
        }
    };
    this.Copy = function(A) {
        if (!A) {
            return
        }
        if (A.fieldNames && !this.fieldNames) {
            this.fieldNames = new Array()
        }
        for (var B = 0; A.fieldNames && B < A.fieldNames.length; B++) {
            this.fieldNames[B] = A.fieldNames[B]
        }
        if (A.fieldValues && !this.fieldValues) {
            this.fieldValues = new Array()
        }
        for (var B = 0; A.fieldValues && B < A.fieldValues.length; B++) {
            this.fieldValues[B] = A.fieldValues[B]
        }
        if (A.shape && !this.shape) {
            this.shape = new SuperMap.IS.Geometry()
        }
        if (A.shape) {
            this.shape.Copy(A.shape)
        }
        this.id = A.id
    }
};
SuperMap.IS.Geometry = function() {
    this.feature = 0;
    this.id = -1;
    this.parts = null;
    this.points = null;
    this.FromJSON = function(B) {
        this.feature = B.feature;
        this.id = B.id;
        if (B.parts && !this.parts) {
            this.parts = new Array()
        }
        for (var A = 0; B.parts && A < B.parts.length; A++) {
            this.parts[A] = B.parts[A]
        }
        if (B.points && !this.points) {
            this.points = new Array()
        }
        for (var A = 0; B.points && A < B.points.length; A++) {
            this.points[A] = new SuperMap.IS.MapCoord();
            this.points[A].x = B.points[A].x;
            this.points[A].y = B.points[A].y
        }
    };
    this.Destroy = function() {
        if (this.parts) {
            while (this.parts.length > 0) {
                this.parts.pop()
            }
            this.parts = null
        }
        if (this.points) {
            while (this.points.length > 0) {
                var A = this.points.pop();
                A.Destroy();
                A = null
            }
            this.points = null
        }
    };
    this.Copy = function(B) {
        this.feature = B.feature;
        this.id = B.id;
        if (B.parts && !this.parts) {
            this.parts = new Array()
        }
        for (var A = 0; B.parts && A < B.parts.length; A++) {
            this.parts[A] = B.parts[A]
        }
        if (B.points && !this.points) {
            this.points = new Array()
        }
        for (var A = 0; B.points && A < B.points.length; A++) {
            this.points[A] = new SuperMap.IS.MapCoord();
            this.points[A].x = B.points[A].x;
            this.points[A].y = B.points[A].y
        }
    }
};
SuperMap.IS.Overview = function() {
    this.mapName = "";
    this.url = "";
    this.viewer = null;
    this.viewBounds = null;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.mapName = A.mapName;
        this.url = A.url;
        if (A.viewer) {
            this.viewer = new SuperMap.IS.PixelRect();
            this.viewer.FromJSON(A.viewer)
        }
        if (A.viewBounds) {
            this.viewBounds = new SuperMap.IS.MapRect();
            this.viewBounds.FromJSON(A.viewBounds)
        }
    };
    this.Destroy = function() {
        if (this.viewer) {
            this.viewer.Destroy();
            this.viewer = null
        }
        if (this.viewBounds) {
            this.viewBounds.Destroy();
            this.viewBounds = null
        }
    };
    this.Copy = function(A) {
        if (!A) {
            return
        }
        this.mapName = A.mapName;
        this.url = A.url;
        if (A.viewer) {
            if (this.viewer == null) {
                this.viewer = new SuperMap.IS.PixelRect()
            }
            this.viewer.Copy(A.viewer)
        }
        if (A.viewBounds) {
            if (this.viewBounds == null) {
                this.viewBounds = new SuperMap.IS.MapRect()
            }
            this.viewBounds.Copy(A.viewBounds)
        }
    }
};
SuperMap.IS.TextStyle = function() {
    this.align = 0;
    this.bgColor = 0;
    this.color = 0;
    this.fixedSize = false;
    this.fixedTextSize = 0;
    this.fontHeight = 50;
    this.fontWidth = 20;
    this.fontName = "";
    this.bold = false;
    this.italic = false;
    this.shadow = false;
    this.stroke = false;
    this.outline = false;
    this.transparent = true;
    this.underline = false;
    this.rotation = 0;
    this.italicAngle = 0;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.align = A.align;
        this.bgColor = A.bgColor;
        this.color = A.color;
        this.fixedSize = A.fixedSize;
        this.fixedTextSize = A.fixedTextSize;
        this.fontHeight = A.fontHeight;
        this.fontWidth = A.fontWidth;
        this.fontName = A.fontName;
        this.bold = A.bold;
        this.italic = A.italic;
        this.shadow = A.shadow;
        this.stroke = A.stroke;
        this.outline = A.outline;
        this.transparent = A.transparent;
        this.underline = A.underline;
        this.rotation = A.rotation;
        this.italicAngle = A.italicAngle
    };
    this.Copy = this.FromJSON;
    this.Destroy = function() {}
};
SuperMap.IS.Style = function() {
    this.brushBackColor = 16777215;
    this.brushBackTransparent = false;
    this.brushColor = 255;
    this.brushGradientAngle = 0;
    this.brushGradientCenterOffsetX = 0;
    this.brushGradientCenterOffsetY = 0;
    this.brushGradientMode = 0;
    this.brushOpaqueRate = 100;
    this.brushStyle = 0;
    this.penColor = 0;
    this.penStyle = 0;
    this.penWidth = 1;
    this.symbolRotation = 0;
    this.symbolSize = 0;
    this.symbolStyle = 0;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.brushBackColor = A.brushBackColor;
        this.brushBackTransparent = A.brushBackTransparent;
        this.brushColor = A.brushColor;
        this.brushGradientAngle = A.brushGradientAngle;
        this.brushGradientCenterOffsetX = A.brushGradientCenterOffsetX;
        this.brushGradientCenterOffsetY = A.brushGradientCenterOffsetY;
        this.brushGradientMode = A.brushGradientMode;
        this.brushOpaqueRate = A.brushOpaqueRate;
        this.brushStyle = A.brushStyle;
        this.penColor = A.penColor;
        this.penStyle = A.penStyle;
        this.penWidth = A.penWidth;
        this.symbolRotation = A.symbolRotation;
        this.symbolSize = A.symbolSize;
        this.symbolStyle = A.symbolStyle
    };
    this.Copy = this.FromJSON;
    this.Destroy = function() {}
};
SuperMap.IS.ForeignDataParam = function() {
    this.foreignKeys = new Array();
    this.foreignValues = new Array();
    this.useForeignValue = false;
    this.foreignJoinExpression = "";
    this.FromJSON = function(B) {
        if (!B) {
            return
        }
        this.useForeignValue = B.useForeignValue;
        if (B.foreignKeys && B.foreignKeys.length > 0) {
            if (this.foreignKeys == null) {
                this.foreignKeys = new Array()
            }
            for (var A = 0; A < B.foreignKeys.length; A++) {
                this.foreignKeys[A] = B.foreignKeys[A]
            }
        }
        if (B.foreignValues && B.foreignValues.length > 0) {
            if (this.foreignValues == null) {
                this.foreignValues = new Array()
            }
            for (var A = 0; A < B.foreignValues.length; A++) {
                this.foreignValues[A] = B.foreignValues[A]
            }
        }
        this.foreignJoinExpression = B.foreignJoinExpression
    };
    this.Copy = this.FromJSON;
    this.Destroy = function() {
        if (this.foreignKeys) {
            while (this.foreignKeys.length > 0) {
                this.foreignKeys.pop()
            }
            this.foreignKeys = null
        }
        if (this.foreignValues) {
            while (this.foreignValues.length > 0) {
                this.foreignValues.pop()
            }
            this.foreignValues = null
        }
    }
};
SuperMap.IS.UniqueTheme = function() {
    this.caption = "";
    this.defaultStyle = null;
    this.expression = "";
    this.enabled = false;
    this.displays = null;
    this.values = null;
    this.itemCaptions = null;
    this.filter = "";
    this.foreignDataParam = new SuperMap.IS.ForeignDataParam();
    this.maxScale = 0;
    this.minScale = 0;
    this.Copy = function(B) {
        if (!B) {
            return
        }
        this.caption = B.caption;
        if (B.defaultStyle) {
            if (this.defaultStyle == null) {
                this.defaultStyle = new SuperMap.IS.Style()
            }
            this.defaultStyle.Copy(B.defaultStyle)
        }
        this.expression = B.expression;
        this.enabled = B.enabled;
        if (B.displays && B.displays.length > 0) {
            if (this.displays == null) {
                this.displays = new Array()
            }
            for (var A = 0; A < B.displays.length; A++) {
                if (this.displays[A] == null) {
                    this.displays[A] = new SuperMap.IS.Style()
                }
                this.displays[A].Copy(B.displays[A])
            }
        }
        if (B.values && B.values.length > 0) {
            if (this.values == null) {
                this.values = new Array()
            }
            for (var A = 0; A < B.values.length; A++) {
                this.values[A] = B.values[A]
            }
        }
        if (B.itemCaptions && B.itemCaptions.length > 0) {
            if (this.itemCaptions == null) {
                this.itemCaptions = new Array()
            }
            for (var A = 0; A < B.itemCaptions.length; A++) {
                this.itemCaptions[A] = B.itemCaptions[A]
            }
        }
        this.filter = B.filter;
        if (B.foreignDataParam) {
            if (this.foreignDataParam == null) {
                this.foreignDataParam = new SuperMap.IS.ForeignDataParam()
            }
            this.foreignDataParam.Copy(B.foreignDataParam)
        }
        this.maxScale = B.maxScale;
        this.minScale = B.minScale
    };
    this.FromJSON = function(B) {
        if (!B) {
            return
        }
        this.caption = B.caption;
        if (B.defaultStyle) {
            if (this.defaultStyle == null) {
                this.defaultStyle = new SuperMap.IS.Style()
            }
            this.defaultStyle.FromJSON(B.defaultStyle)
        }
        this.expression = B.expression;
        this.enabled = B.enabled;
        if (B.displays && B.displays.length > 0) {
            if (this.displays == null) {
                this.displays = new Array()
            }
            for (var A = 0; A < B.displays.length; A++) {
                if (this.displays[A] == null) {
                    this.displays[A] = new SuperMap.IS.Style()
                }
                this.displays[A].FromJSON(B.displays[A])
            }
        }
        if (B.values && B.values.length > 0) {
            if (this.values == null) {
                this.values = new Array()
            }
            for (var A = 0; A < B.values.length; A++) {
                this.values[A] = B.values[A]
            }
        }
        if (B.itemCaptions && B.itemCaptions.length > 0) {
            if (this.itemCaptions == null) {
                this.itemCaptions = new Array()
            }
            for (var A = 0; A < B.itemCaptions.length; A++) {
                this.itemCaptions[A] = B.itemCaptions[A]
            }
        }
        this.filter = B.filter;
        if (B.foreignDataParam) {
            if (this.foreignDataParam == null) {
                this.foreignDataParam = new SuperMap.IS.ForeignDataParam()
            }
            this.foreignDataParam.FromJSON(B.foreignDataParam)
        }
        this.maxScale = B.maxScale;
        this.minScale = B.minScale
    };
    this.Destroy = function() {
        if (this.defaultStyle) {
            this.defaultStyle.Destroy();
            this.defaultStyle = null
        }
        if (this.displays) {
            while (this.displays.length > 0) {
                var A = this.displays.pop();
                A.Destroy();
                A = null
            }
            this.displays = null
        }
        if (this.values) {
            while (this.values.length > 0) {
                this.values.pop()
            }
            this.values = null
        }
        if (this.itemCaptions) {
            while (this.itemCaptions.length > 0) {
                this.itemCaptions.pop()
            }
            this.itemCaptions = null
        }
        if (this.foreignDataParam) {
            this.foreignDataParam.Destroy();
            this.foreignDataParam = null
        }
    }
};
SuperMap.IS.DotDensityTheme = function() {
    this.caption = "";
    this.dotStyle = null;
    this.dotValue = 0;
    this.expression = "";
    this.enabled = false;
    this.filter = "";
    this.foreignDataParam = new SuperMap.IS.ForeignDataParam();
    this.maxScale = 0;
    this.minScale = 0;
    this.Copy = function(A) {
        if (!A) {
            return
        }
        this.caption = A.caption;
        this.dotValue = A.dotValue;
        this.expression = A.expression;
        this.enabled = A.enabled;
        if (A.dotStyle) {
            if (this.dotStyle == null) {
                this.dotStyle = new SuperMap.IS.Style()
            }
            this.dotStyle.Copy(A.dotStyle)
        }
        this.filter = A.filter;
        if (A.foreignDataParam) {
            if (this.foreignDataParam == null) {
                this.foreignDataParam = new SuperMap.IS.ForeignDataParam()
            }
            this.foreignDataParam.Copy(A.foreignDataParam)
        }
        this.maxScale = A.maxScale;
        this.minScale = A.minScale
    };
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.caption = A.caption;
        this.dotValue = A.dotValue;
        this.expression = A.expression;
        this.enabled = A.enabled;
        if (A.dotStyle) {
            if (this.dotStyle == null) {
                this.dotStyle = new SuperMap.IS.Style()
            }
            this.dotStyle.FromJSON(A.dotStyle)
        }
        this.filter = A.filter;
        if (A.foreignDataParam) {
            if (this.foreignDataParam == null) {
                this.foreignDataParam = new SuperMap.IS.ForeignDataParam()
            }
            this.foreignDataParam.FromJSON(A.foreignDataParam)
        }
        this.maxScale = A.maxScale;
        this.minScale = A.minScale
    };
    this.Destroy = function() {
        if (this.dotStyle) {
            this.dotStyle.Destroy();
            this.dotStyle = null
        }
        if (this.foreignDataParam) {
            this.foreignDataParam.Destroy();
            this.foreignDataParam = null
        }
    }
};
SuperMap.IS.GraduatedSymbolTheme = function() {
    this.caption = "";
    this.definitionValue = 0;
    this.enableFlow = false;
    this.expression = "";
    this.graduatedMode = 0;
    this.showNegative = false;
    this.styleForNegative = null;
    this.styleForPositive = null;
    this.enabled = false;
    this.onTop = false;
    this.filter = "";
    this.foreignDataParam = new SuperMap.IS.ForeignDataParam();
    this.maxScale = 0;
    this.minScale = 0;
    this.Copy = function(A) {
        if (!A) {
            return
        }
        this.caption = A.caption;
        this.definitionValue = A.definitionValue;
        this.enableFlow = A.enableFlow;
        this.expression = A.expression;
        this.showNegative = A.showNegative;
        this.enabled = A.enabled;
        this.graduatedMode = A.graduatedMode;
        if (A.styleForNegative) {
            if (this.styleForNegative == null) {
                this.styleForNegative = new SuperMap.IS.Style()
            }
            this.styleForNegative.Copy(A.styleForNegative)
        }
        if (A.styleForPositive) {
            if (this.styleForPositive == null) {
                this.styleForPositive = new SuperMap.IS.Style()
            }
            this.styleForPositive.Copy(A.styleForPositive)
        }
        this.onTop = A.onTop;
        this.filter = A.filter;
        if (A.foreignDataParam) {
            if (this.foreignDataParam == null) {
                this.foreignDataParam = new SuperMap.IS.ForeignDataParam()
            }
            this.foreignDataParam.Copy(A.foreignDataParam)
        }
        this.maxScale = A.maxScale;
        this.minScale = A.minScale
    };
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.caption = A.caption;
        this.definitionValue = A.definitionValue;
        this.enableFlow = A.enableFlow;
        this.expression = A.expression;
        this.showNegative = A.showNegative;
        this.enabled = A.enabled;
        this.graduatedMode = A.graduatedMode;
        if (A.styleForNegative) {
            if (this.styleForNegative == null) {
                this.styleForNegative = new SuperMap.IS.Style()
            }
            this.styleForNegative.FromJSON(A.styleForNegative)
        }
        if (A.styleForPositive) {
            if (this.styleForPositive == null) {
                this.styleForPositive = new SuperMap.IS.Style()
            }
            this.styleForPositive.FromJSON(A.styleForPositive)
        }
        this.onTop = A.onTop;
        this.filter = A.filter;
        if (A.foreignDataParam) {
            if (this.foreignDataParam == null) {
                this.foreignDataParam = new SuperMap.IS.ForeignDataParam()
            }
            this.foreignDataParam.FromJSON(A.foreignDataParam)
        }
        this.maxScale = A.maxScale;
        this.minScale = A.minScale
    };
    this.Destroy = function(A) {
        if (this.styleForNegative) {
            this.styleForNegative.Destroy();
            this.styleForNegative = null
        }
        if (this.styleForPositive) {
            this.styleForPositive.Destroy();
            this.styleForPositive = null
        }
        if (this.foreignDataParam) {
            this.foreignDataParam.Destroy();
            this.foreignDataParam = null
        }
    }
};
SuperMap.IS.GraphTheme = function() {
    this.axesTextStyle = null;
    this.caption = "";
    this.enableFlow = false;
    this.expressions = null;
    this.graduatedMode = 0;
    this.graphStyles = null;
    this.graphType = 0;
    this.itemTextFormat = 0;
    this.itemTextStyle = null;
    this.lineColor = 0;
    this.maxSumSize = 0;
    this.minSumSize = 0;
    this.showAxes = false;
    this.showAxesText = false;
    this.showAxisGrid = false;
    this.showItemText = false;
    this.enabled = false;
    this.onTop = false;
    this.filter = "";
    this.foreignDataParam = new SuperMap.IS.ForeignDataParam();
    this.maxScale = 0;
    this.minScale = 0;
    this.itemCaptions = null;
    this.showXAxisText = false;
    this.Copy = function(A) {
        if (!A) {
            return
        }
        if (A.axesTextStyle) {
            if (this.axesTextStyle == null) {
                this.axesTextStyle = new SuperMap.IS.TextStyle()
            }
            this.axesTextStyle.Copy(A.axesTextStyle)
        }
        this.caption = A.caption;
        this.enableFlow = A.enableFlow;
        if (A.expressions) {
            if (this.expressions == null) {
                this.expressions = new Array()
            }
            for (var B = 0; B < A.expressions.length; B++) {
                this.expressions[B] = A.expressions[B]
            }
        }
        this.graduatedMode = A.graduatedMode;
        if (A.graphStyles) {
            if (this.graphStyles == null) {
                this.graphStyles = new Array()
            }
            for (var B = 0; B < A.graphStyles.length; B++) {
                if (this.graphStyles[B] == null) {
                    this.graphStyles[B] = new SuperMap.IS.Style()
                }
                this.graphStyles[B].Copy(A.graphStyles[B])
            }
        }
        this.graphType = A.graphType;
        this.itemTextFormat = A.itemTextFormat;
        if (A.itemTextStyle) {
            if (this.itemTextStyle == null) {
                this.itemTextStyle = new SuperMap.IS.TextStyle()
            }
            this.itemTextStyle.Copy(A.itemTextStyle)
        }
        this.lineColor = A.lineColor;
        this.maxSumSize = A.maxSumSize;
        this.minSumSize = A.minSumSize;
        this.showAxes = A.showAxes;
        this.showAxesText = A.showAxesText;
        this.showAxisGrid = A.showAxisGrid;
        this.showItemText = A.showItemText;
        this.onTop = A.onTop;
        this.filter = A.filter;
        this.enabled = A.enabled;
        if (A.foreignDataParam) {
            if (this.foreignDataParam == null) {
                this.foreignDataParam = new SuperMap.IS.ForeignDataParam()
            }
            this.foreignDataParam.Copy(A.foreignDataParam)
        }
        this.maxScale = A.maxScale;
        this.minScale = A.minScale;
        if (A.itemCaptions) {
            if (this.itemCaptions == null) {
                this.itemCaptions = new Array()
            }
            for (var B = 0; B < A.itemCaptions.length; B++) {
                this.itemCaptions[B] = A.itemCaptions[B]
            }
        }
        this.showXAxisText = A.showXAxisText
    };
    this.FromJSON = function(B) {
        if (!B) {
            return
        }
        if (B.axesTextStyle) {
            if (this.axesTextStyle == null) {
                this.axesTextStyle = new SuperMap.IS.TextStyle()
            }
            this.axesTextStyle.FromJSON(B.axesTextStyle)
        }
        this.caption = B.caption;
        this.enableFlow = B.enableFlow;
        if (B.expressions) {
            if (this.expressions == null) {
                this.expressions = new Array()
            }
            for (var A = 0; A < B.expressions.length; A++) {
                this.expressions[A] = B.expressions[A]
            }
        }
        this.graduatedMode = B.graduatedMode;
        if (B.graphStyles) {
            if (this.graphStyles == null) {
                this.graphStyles = new Array()
            }
            for (var A = 0; A < B.graphStyles.length; A++) {
                if (this.graphStyles[A] == null) {
                    this.graphStyles[A] = new SuperMap.IS.Style()
                }
                this.graphStyles[A].FromJSON(B.graphStyles[A])
            }
        }
        this.graphType = B.graphType;
        this.itemTextFormat = B.itemTextFormat;
        if (B.itemTextStyle) {
            if (this.itemTextStyle == null) {
                this.itemTextStyle = new SuperMap.IS.TextStyle()
            }
            this.itemTextStyle.FromJSON(B.itemTextStyle)
        }
        this.lineColor = B.lineColor;
        this.maxSumSize = B.maxSumSize;
        this.minSumSize = B.minSumSize;
        this.showAxes = B.showAxes;
        this.showAxesText = B.showAxesText;
        this.showAxisGrid = B.showAxisGrid;
        this.showItemText = B.showItemText;
        this.onTop = B.onTop;
        this.filter = B.filter;
        this.enabled = B.enabled;
        if (B.foreignDataParam) {
            if (this.foreignDataParam == null) {
                this.foreignDataParam = new SuperMap.IS.ForeignDataParam()
            }
            this.foreignDataParam.FromJSON(B.foreignDataParam)
        }
        this.maxScale = B.maxScale;
        this.minScale = B.minScale;
        if (B.itemCaptions) {
            if (this.itemCaptions == null) {
                this.itemCaptions = new Array()
            }
            for (var A = 0; A < B.itemCaptions.length; A++) {
                this.itemCaptions[A] = B.itemCaptions[A]
            }
        }
        this.showXAxisText = B.showXAxisText
    };
    this.Destroy = function() {
        if (this.axesTextStyle) {
            this.axesTextStyle.Destroy();
            this.axesTextStyle = null
        }
        if (this.expressions && this.expressions.length > 0) {
            while (this.expressions.length > 0) {
                this.expressions.pop()
            }
            this.expressions = null
        }
        if (this.graphStyles && this.graphStyles.length > 0) {
            while (this.graphStyles.length > 0) {
                var A = this.graphStyles.pop();
                A.Destroy();
                A = null
            }
            this.graphStyles = null
        }
        if (this.itemTextStyle) {
            this.itemTextStyle.Destroy();
            this.itemTextStyle = null
        }
        if (this.foreignDataParam) {
            this.foreignDataParam.Destroy();
            this.foreignDataParam = null
        }
        if (this.itemCaptions && this.itemCaptions.length > 0) {
            while (this.itemCaptions.length > 0) {
                this.itemCaptions.pop()
            }
            this.itemCaptions = null
        }
    }
};
SuperMap.IS.LabelTheme = function() {
    this.alongLine = false;
    this.caption = "";
    this.display = null;
    this.enableFlow = false;
    this.expression = "";
    this.fixedAngle = false;
    this.enabled = false;
    this.onTop = false;
    this.filter = "";
    this.ignoreSmallObject = false;
    this.singleLineCharCount = 0;
    this.textControlMode = 0;
    this.fixedRepeatLength = false;
    this.repeatLength = 0;
    this.textSpacing = 0;
    this.numericFormat = "";
    this.backShape = 0;
    this.backStyle = null;
    this.maxObjectCount = 0;
    this.lineDirection = 0;
    this.foreignDataParam = new SuperMap.IS.ForeignDataParam();
    this.maxScale = 0;
    this.minScale = 0;
    this.Copy = function(A) {
        if (!A) {
            return
        }
        this.alongLine = A.alongLine;
        this.caption = A.caption;
        this.enableFlow = A.enableFlow;
        this.expression = A.expression;
        this.fixedAngle = A.fixedAngle;
        this.enabled = A.enabled;
        if (A.display) {
            if (this.display == null) {
                this.display = new SuperMap.IS.TextStyle()
            }
            this.display.Copy(A.display)
        }
        this.onTop = A.onTop;
        this.filter = A.filter;
        this.ignoreSmallObject = A.ignoreSmallObject;
        this.singleLineCharCount = A.singleLineCharCount;
        this.textControlMode = A.textControlMode;
        this.fixedRepeatLength = A.fixedRepeatLength;
        this.repeatLength = A.repeatLength;
        this.textSpacing = A.textSpacing;
        this.numericFormat = A.numericFormat;
        this.backShape = A.backShape;
        if (A.backStyle) {
            if (this.backStyle == null) {
                this.backStyle = new SuperMap.IS.Style()
            }
            this.backStyle.Copy(A.backStyle)
        }
        this.maxObjectCount = A.maxObjectCount;
        this.lineDirection = A.lineDirection;
        if (A.foreignDataParam) {
            if (this.foreignDataParam == null) {
                this.foreignDataParam = new SuperMap.IS.ForeignDataParam()
            }
            this.foreignDataParam.Copy(A.foreignDataParam)
        }
        this.maxScale = A.maxScale;
        this.minScale = A.minScale
    };
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.alongLine = A.alongLine;
        this.caption = A.caption;
        this.enableFlow = A.enableFlow;
        this.expression = A.expression;
        this.fixedAngle = A.fixedAngle;
        this.enabled = A.enabled;
        if (A.display) {
            if (this.display == null) {
                this.display = new SuperMap.IS.TextStyle()
            }
            this.display.FromJSON(A.display)
        }
        this.onTop = A.onTop;
        this.filter = A.filter;
        this.ignoreSmallObject = A.ignoreSmallObject;
        this.singleLineCharCount = A.singleLineCharCount;
        this.textControlMode = A.textControlMode;
        this.fixedRepeatLength = A.fixedRepeatLength;
        this.repeatLength = A.repeatLength;
        this.textSpacing = A.textSpacing;
        this.numericFormat = A.numericFormat;
        this.backShape = A.backShape;
        if (A.backStyle) {
            if (this.backStyle == null) {
                this.backStyle = new SuperMap.IS.Style()
            }
            this.backStyle.FromJSON(A.backStyle)
        }
        this.maxObjectCount = A.maxObjectCount;
        this.lineDirection = A.lineDirection;
        if (A.foreignDataParam) {
            if (this.foreignDataParam == null) {
                this.foreignDataParam = new SuperMap.IS.ForeignDataParam()
            }
            this.foreignDataParam.FromJSON(A.foreignDataParam)
        }
        this.maxScale = A.maxScale;
        this.minScale = A.minScale
    };
    this.Destroy = function(A) {
        if (this.display) {
            this.display.Destroy();
            this.display = null
        }
        if (this.backStyle) {
            this.backStyle.Destroy();
            this.backStyle = null
        }
        if (this.foreignDataParam) {
            this.foreignDataParam.Destroy();
            this.foreignDataParam = null
        }
    }
};
SuperMap.IS.RangeTheme = function() {
    this.breakValues = null;
    this.caption = "";
    this.displays = null;
    this.expression = "";
    this.enabled = false;
    this.filter = "";
    this.foreignDataParam = new SuperMap.IS.ForeignDataParam();
    this.maxScale = 0;
    this.minScale = 0;
    this.Copy = function(B) {
        if (!B) {
            return
        }
        if (B.breakValues) {
            if (this.breakValues == null) {
                this.breakValues = new Array()
            }
            for (var A = 0; A < B.breakValues.length; A++) {
                this.breakValues[A] = B.breakValues[A]
            }
        }
        this.caption = B.caption;
        if (B.displays) {
            if (this.displays == null) {
                this.displays = new Array()
            }
            for (var A = 0; A < B.displays.length; A++) {
                if (this.displays[A] == null) {
                    this.displays[A] = new SuperMap.IS.Style()
                }
                this.displays[A].Copy(B.displays[A])
            }
        }
        this.expression = B.expression;
        this.enabled = B.enabled;
        this.filter = B.filter;
        if (B.foreignDataParam) {
            if (this.foreignDataParam == null) {
                this.foreignDataParam = new SuperMap.IS.ForeignDataParam()
            }
            this.foreignDataParam.Copy(B.foreignDataParam)
        }
        this.maxScale = B.maxScale;
        this.minScale = B.minScale
    };
    this.FromJSON = function(B) {
        if (!B) {
            return
        }
        if (B.breakValues) {
            if (this.breakValues == null) {
                this.breakValues = new Array()
            }
            for (var A = 0; A < B.breakValues.length; A++) {
                this.breakValues[A] = B.breakValues[A]
            }
        }
        this.caption = B.caption;
        if (B.displays) {
            if (this.displays == null) {
                this.displays = new Array()
            }
            for (var A = 0; A < B.displays.length; A++) {
                if (this.displays[A] == null) {
                    this.displays[A] = new SuperMap.IS.Style()
                }
                this.displays[A].FromJSON(B.displays[A])
            }
        }
        this.expression = B.expression;
        this.enabled = B.enabled;
        this.filter = B.filter;
        if (B.foreignDataParam) {
            if (this.foreignDataParam == null) {
                this.foreignDataParam = new SuperMap.IS.ForeignDataParam()
            }
            this.foreignDataParam.FromJSON(B.foreignDataParam)
        }
        this.maxScale = B.maxScale;
        this.minScale = B.minScale
    };
    this.Destroy = function(B) {
        if (this.breakValues && this.breakValues.length > 0) {
            while (this.breakValues.length > 0) {
                this.breakValues.pop()
            }
            this.breakValues = null
        }
        if (this.displays && this.displays.length > 0) {
            while (this.displays.length > 0) {
                var A = this.displays.pop();
                A.Destroy();
                A = null
            }
            this.displays = null
        }
        if (this.foreignDataParam) {
            this.foreignDataParam.Destroy();
            this.foreignDataParam = null
        }
    }
};
SuperMap.IS.GridRangeTheme = function() {
    this.breakValues = null;
    this.caption = "";
    this.displays = null;
    this.enabled = false;
    this.Copy = function(B) {
        if (!B) {
            return
        }
        if (B.breakValues) {
            if (this.breakValues == null) {
                this.breakValues = new Array()
            }
            for (var A = 0; A < B.breakValues.length; A++) {
                this.breakValues[A] = B.breakValues[A]
            }
        }
        this.caption = B.caption;
        if (B.displays) {
            if (this.displays == null) {
                this.displays = new Array()
            }
            for (var A = 0; A < B.displays.length; A++) {
                this.displays[A] = B.displays[A]
            }
        }
        this.enabled = B.enabled
    };
    this.FromJSON = function(B) {
        if (!B) {
            return
        }
        if (B.breakValues) {
            if (this.breakValues == null) {
                this.breakValues = new Array()
            }
            for (var A = 0; A < B.breakValues.length; A++) {
                this.breakValues[A] = B.breakValues[A]
            }
        }
        this.caption = B.caption;
        if (B.displays) {
            if (this.displays == null) {
                this.displays = new Array()
            }
            for (var A = 0; A < B.displays.length; A++) {
                this.displays[A] = B.displays[A]
            }
        }
        this.enabled = B.enabled
    };
    this.Destroy = function(A) {
        if (this.breakValues && this.breakValues.length > 0) {
            while (this.breakValues.length > 0) {
                this.breakValues.pop()
            }
            this.breakValues = null
        }
        if (this.displays && this.displays.length > 0) {
            while (this.displays.length > 0) {
                this.displays.pop()
            }
            this.displays = null
        }
    }
};
SuperMap.IS.CustomTheme = function() {
    this.enabled = false;
    this.caption = "";
    this.filter = "";
    this.brushBackColorField = "";
    this.brushCenterOffsetXField = "";
    this.brushCenterOffsetYField = "";
    this.brushColorField = "";
    this.brushGradientAngleField = "";
    this.brushGradientTypeField = "";
    this.brushOpaqueRateField = "";
    this.brushStyleField = "";
    this.penColorField = "";
    this.penStyleField = "";
    this.penWidthField = "";
    this.symbolRotationField = "";
    this.symbolSizeField = "";
    this.symbolStyleField = "";
    this.maxScale = 0;
    this.minScale = 0;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.enabled = A.enabled;
        this.caption = A.caption;
        this.filter = A.filter;
        this.brushBackColorField = A.brushBackColorField;
        this.brushCenterOffsetXField = A.brushCenterOffsetXField;
        this.brushCenterOffsetYField = A.brushCenterOffsetYField;
        this.brushColorField = A.brushColorField;
        this.brushGradientAngleField = A.brushGradientAngleField;
        this.brushGradientTypeField = A.brushGradientTypeField;
        this.brushOpaqueRateField = A.brushOpaqueRateField;
        this.brushStyleField = A.brushStyleField;
        this.penColorField = A.penColorField;
        this.penStyleField = A.penStyleField;
        this.penWidthField = A.penWidthField;
        this.symbolRotationField = A.symbolRotationField;
        this.symbolSizeField = A.symbolSizeField;
        this.symbolStyleField = A.symbolStyleField;
        this.maxScale = A.maxScale;
        this.minScale = A.minScale
    };
    this.Copy = this.FromJSON;
    this.Destroy = function(A) {}
};
SuperMap.IS.Layer = function() {
    this.id = 0;
    this.caption = "";
    this.displayFilter = "";
    this.maxScale = 0;
    this.minScale = 0;
    this.name = "";
    this.queryable = true;
    this.style = null;
    this.type = 0;
    this.themeDotDensity = null;
    this.themeGraduatedSymbol = null;
    this.themeGraph = null;
    this.themeLabel = null;
    this.themeRange = null;
    this.visible = false;
    this.relQueryTableInfos = null;
    this.themeUnique = null;
    this.themeGridRange = null;
    this.themeCustom = null;
    this.displaySortClause = "";
    this.rasterOpaqueRate = 100;
    this.Copy = function(B) {
        if (!B) {
            return
        }
        this.id = B.id;
        this.caption = B.caption;
        this.displayFilter = B.displayFilter;
        this.maxScale = B.maxScale;
        this.minScale = B.minScale;
        this.name = B.name;
        this.queryable = B.queryable;
        if (B.style) {
            if (this.style == null) {
                this.style = new SuperMap.IS.Style()
            }
            this.style.Copy(B.style)
        }
        this.type = B.type;
        if (B.themeDotDensity) {
            if (this.themeDotDensity == null) {
                this.themeDotDensity = new SuperMap.IS.DotDensityTheme()
            }
            this.themeDotDensity.Copy(B.themeDotDensity)
        }
        if (B.themeGraduatedSymbol) {
            if (this.themeGraduatedSymbol == null) {
                this.themeGraduatedSymbol = new SuperMap.IS.GraduatedSymbolTheme()
            }
            this.themeGraduatedSymbol.Copy(B.themeGraduatedSymbol)
        }
        if (B.themeGraph) {
            if (this.themeGraph == null) {
                this.themeGraph = new SuperMap.IS.GraphTheme()
            }
            this.themeGraph.Copy(B.themeGraph)
        }
        if (B.themeLabel) {
            if (this.themeLabel == null) {
                this.themeLabel = new SuperMap.IS.LabelTheme()
            }
            this.themeLabel.Copy(B.themeLabel)
        }
        if (B.themeRange) {
            if (this.themeRange == null) {
                this.themeRange = new SuperMap.IS.RangeTheme()
            }
            this.themeRange.Copy(B.themeRange)
        }
        this.visible = B.visible;
        if (B.relQueryTableInfos) {
            if (this.relQueryTableInfos == null) {
                this.relQueryTableInfos = new Array()
            }
            for (var A = 0; A < B.relQueryTableInfos.length; A++) {
                if (this.relQueryTableInfos[A] == null) {
                    this.relQueryTableInfos[A] = new SuperMap.IS.RelQueryTableInfo()
                }
                this.relQueryTableInfos[A].Copy(B.relQueryTableInfos[A])
            }
        }
        if (B.themeUnique) {
            if (this.themeUnique == null) {
                this.themeUnique = new SuperMap.IS.UniqueTheme()
            }
            this.themeUnique.Copy(B.themeUnique)
        }
        if (B.themeGridRange) {
            if (this.themeGridRange == null) {
                this.themeGridRange = new SuperMap.IS.GridRangeTheme()
            }
            this.themeGridRange.Copy(B.themeGridRange)
        }
        if (B.themeCustom) {
            if (this.themeCustom == null) {
                this.themeCustom = new SuperMap.IS.CustomTheme()
            }
            this.themeCustom.Copy(B.themeCustom)
        }
        this.displaySortClause = B.displaySortClause;
        this.rasterOpaqueRate = B.rasterOpaqueRate
    };
    this.FromJSON = function(B) {
        if (!B) {
            return
        }
        this.id = B.id;
        this.caption = B.caption;
        this.queryable = B.queryable;
        this.visible = B.visible;
        this.name = B.name;
        this.displayFilter = B.displayFilter;
        this.type = B.type;
        this.maxScale = B.maxScale;
        this.minScale = B.minScale;
        if (B.style) {
            this.style = new SuperMap.IS.Style();
            this.style.FromJSON(B.style)
        }
        if (B.themeDotDensity) {
            this.themeDotDensity = new SuperMap.IS.DotDensityTheme();
            this.themeDotDensity.FromJSON(B.themeDotDensity)
        }
        if (B.themeGraduatedSymbol) {
            this.themeGraduatedSymbol = new SuperMap.IS.GraduatedSymbolTheme();
            this.themeGraduatedSymbol.FromJSON(B.themeGraduatedSymbol)
        }
        if (B.themeGraph) {
            this.themeGraph = new SuperMap.IS.GraphTheme();
            this.themeGraph.FromJSON(B.themeGraph)
        }
        if (B.themeLabel) {
            this.themeLabel = new SuperMap.IS.LabelTheme();
            this.themeLabel.FromJSON(B.themeLabel)
        }
        if (B.themeRange) {
            this.themeRange = new SuperMap.IS.RangeTheme();
            this.themeRange.FromJSON(B.themeRange)
        }
        if (B.themeUnique) {
            this.themeUnique = new SuperMap.IS.UniqueTheme();
            this.themeUnique.FromJSON(B.themeUnique)
        }
        if (B.relQueryTableInfos) {
            this.relQueryTableInfos = new Array();
            for (var A = 0; A < B.relQueryTableInfos.length; A++) {
                this.relQueryTableInfos[A] = new SuperMap.IS.RelQueryTableInfo();
                this.relQueryTableInfos[A].FromJSON(B.relQueryTableInfos[A])
            }
        }
        if (B.themeGridRange) {
            this.themeGridRange = new SuperMap.IS.GridRangeTheme();
            this.themeGridRange.FromJSON(B.themeGridRange)
        }
        if (B.themeCustom) {
            this.themeCustom = new SuperMap.IS.CustomTheme();
            this.themeCustom.FromJSON(B.themeCustom)
        }
        this.displaySortClause = B.displaySortClause;
        this.rasterOpaqueRate = B.rasterOpaqueRate
    };
    this.Destroy = function() {
        if (this.style) {
            this.style.Destroy();
            this.style = null
        }
        if (this.themeDotDensity) {
            this.themeDotDensity.Destroy();
            this.themeDotDensity = null
        }
        if (this.themeGraduatedSymbol) {
            this.themeGraduatedSymbol.Destroy();
            this.themeGraduatedSymbol = null
        }
        if (this.themeGraph) {
            this.themeGraph.Destroy();
            this.themeGraph = null
        }
        if (this.themeLabel) {
            this.themeLabel.Destroy();
            this.themeLabel = null
        }
        if (this.themeRange) {
            this.themeRange.Destroy();
            this.themeRange = null
        }
        if (this.themeUnique) {
            this.themeUnique.Destroy();
            this.themeUnique = null
        }
        if (this.relQueryTableInfo) {
            this.relQueryTableInfo.Destroy();
            this.relQueryTableInfo = null
        }
        if (this.themeGridRange) {
            this.themeGridRange.Destroy();
            this.themeGridRange = null
        }
        if (this.themeCustom) {
            this.themeCustom.Destroy();
            this.themeCustom = null
        }
    }
};
SuperMap.IS.Highlight = function() {
    this.forceUseDefaultStyles = false;
    this.highlightQueryArea = false;
    this.highlightResult = true;
    this.lineStyle = null;
    this.pointStyle = null;
    this.queryAreaStyle = null;
    this.regionStyle = null;
    this.textStyle = null;
    this.Copy = function(A) {
        if (!A) {
            return
        }
        this.forceUseDefaultStyles = A.forceUseDefaultStyles;
        this.highlightQueryArea = A.highlightQueryArea;
        this.highlightResult = A.highlightResult;
        if (A.lineStyle) {
            if (this.lineStyle == null) {
                this.lineStyle = new SuperMap.IS.Style()
            }
            this.lineStyle.FromJSON(A.lineStyle)
        }
        if (A.pointStyle) {
            if (this.pointStyle == null) {
                this.pointStyle = new SuperMap.IS.Style()
            }
            this.pointStyle.FromJSON(A.pointStyle)
        }
        if (A.queryAreaStyle) {
            if (this.queryAreaStyle == null) {
                this.queryAreaStyle = new SuperMap.IS.Style()
            }
            this.queryAreaStyle.FromJSON(A.queryAreaStyle)
        }
        if (A.regionStyle) {
            if (this.regionStyle == null) {
                this.regionStyle = new SuperMap.IS.Style()
            }
            this.regionStyle.FromJSON(A.regionStyle)
        }
        if (A.textStyle) {
            if (this.textStyle == null) {
                this.textStyle = new SuperMap.IS.Style()
            }
            this.textStyle.FromJSON(A.textStyle)
        }
    };
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.forceUseDefaultStyles = A.forceUseDefaultStyles;
        this.highlightQueryArea = A.highlightQueryArea;
        this.highlightResult = A.highlightResult;
        if (A.lineStyle) {
            this.lineStyle = new SuperMap.IS.Style();
            this.lineStyle.FromJSON(A.lineStyle)
        }
        if (A.pointStyle) {
            this.pointStyle = new SuperMap.IS.Style();
            this.pointStyle.FromJSON(A.pointStyle)
        }
        if (A.queryAreaStyle) {
            this.queryAreaStyle = new SuperMap.IS.Style();
            this.queryAreaStyle.FromJSON(A.queryAreaStyle)
        }
        if (A.regionStyle) {
            this.regionStyle = new SuperMap.IS.Style();
            this.regionStyle.FromJSON(A.regionStyle)
        }
        if (A.textStyle) {
            this.textStyle = new SuperMap.IS.Style();
            this.textStyle.FromJSON(A.textStyle)
        }
    };
    this.Destroy = function() {
        if (this.lineStyle) {
            this.lineStyle.Destroy();
            this.lineStyle = null
        }
        if (this.pointStyle) {
            this.pointStyle.Destroy();
            this.pointStyle = null
        }
        if (this.queryAreaStyle) {
            this.queryAreaStyle.Destroy();
            this.queryAreaStyle = null
        }
        if (this.regionStyle) {
            this.regionStyle.Destroy();
            this.regionStyle = null
        }
        if (this.textStyle) {
            this.textStyle.Destroy();
            this.textStyle = null
        }
    }
};
SuperMap.IS.EditResult = function() {
    this.succeed = false;
    this.ids = null;
    this.bounds = null;
    this.message = "";
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.succeed = A.succeed;
        this.ids = A.ids;
        if (A.bounds) {
            this.bounds = new SuperMap.IS.MapRect();
            this.bounds.FromJSON(A.bounds)
        }
        this.message = A.message
    };
    this.Destroy = function(A) {
        if (this.ids) {
            while (this.ids.length > 0) {
                this.ids.pop()
            }
            this.ids = null
        }
        if (this.bounds) {
            this.bounds.Destroy();
            this.bounds = null
        }
    }
};
SuperMap.IS.WmsParam = function() {
    this.name = "";
    this.url = "";
    this.version = "1.3.0";
    this.request = "GetMap";
    this.layers = "";
    this.styles = "";
    this.crs = "";
    this.format = "image/jpeg";
    this.bgColor = "OxFFFFFF"
};
SuperMap.IS.WmsLayer = function(C, B, A) {
    this.id = C;
    this.param = B;
    this.groupID = A;
    this.zIndex;
    this.Destroy = function() {
        this.param = null
    }
};
SuperMap.IS.WfsParam = function() {
    this.name = "";
    this.url = "";
    this.version = "1.0.0";
    this.typeName = "";
    this.outputFormat = "";
    this.filter = ""
};
SuperMap.IS.WfsLayer = function(C, B, A) {
    this.id = C;
    this.param = B;
    this.groupID = A;
    this.zIndex;
    this.Destroy = function() {
        this.param = null
    }
};
SuperMap.IS.ActionEventArgs = function() {
    this.actionParams = "";
    this.mapCoords = null;
    this.FromJSON = function(B) {
        if (!B) {
            return
        }
        this.actionParams = B.actionParams;
        if (B.mapCoords && B.mapCoords.length > 0) {
            this.mapCoords = new Array();
            for (var A = 0; A < B.mapCoords.length; A++) {
                this.mapCoords[A] = new SuperMap.IS.MapCoord(B.mapCoords[A].x, B.mapCoords[A].y)
            }
        }
    };
    this.Destroy = function() {
        if (this.mapCoords) {
            while (this.mapCoords.length > 0) {
                var A = this.mapCoords.pop();
                A.Destroy();
                A = null
            }
            this.mapCoords = null
        }
    }
};
SuperMap.IS.MeasuringEventArgs = function() {
    this.isHighlight = true;
    this.highlightStyle = null;
    this.clientActionArgs = null;
    this.cancel = false;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.isHighlight = A.isHighlight;
        if (A.highlightStyle) {
            this.highlightStyle = new SuperMap.IS.Style();
            this.highlightStyle.FromJSON(A.highlightStyle)
        }
        if (A.clientActionArgs) {
            this.clientActionArgs = new SuperMap.IS.ActionEventArgs();
            this.clientActionArgs.FromJSON(A.clientActionArgs)
        }
        this.cancel = A.cancel
    };
    this.Destroy = function() {
        if (this.highlightStyle) {
            this.highlightStyle.Destroy();
            this.highlightStyle = null
        }
        if (this.clientActionArgs) {
            this.clientActionArgs.Destroy();
            this.clientActionArgs = null
        }
    }
};
SuperMap.IS.MeasuredEventArgs = function() {
    this.area = 0;
    this.distance = 0;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.area = A.area;
        this.distance = A.distance
    };
    this.Destroy = function() {}
};
SuperMap.IS.PathFindingEventArgs = function() {
    this.clientActionArgs = null;
    this.routeParams = null;
    this.cancel = false;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        if (A.clientActionArgs) {
            this.clientActionArgs = new SuperMap.IS.ActionEventArgs();
            this.clientActionArgs.FromJSON(A.clientActionArgs)
        }
        if (A.routeParams) {
            this.routeParams = new SuperMap.IS.RouteParam();
            this.routeParams.FromJSON(A.routeParams)
        }
        this.cancel = A.cancel
    };
    this.Destroy = function() {
        if (this.routeParams) {
            this.routeParams.Destroy();
            this.routeParams = null
        }
        if (this.clientActionArgs) {
            this.clientActionArgs.Destroy();
            this.clientActionArgs = null
        }
    }
};
SuperMap.IS.PathFoundEventArgs = function(A) {
    this.bounds = A.bounds;
    this.edgeIDs = A.edgeIDs;
    this.nodeIDs = A.nodeIDs;
    this.nodePositions = A.nodePositions;
    this.totalLength = A.totalLength;
    this.FromJSON = function(C) {
        if (!C) {
            return
        }
        if (C.bounds) {
            this.bounds = new SuperMap.IS.MapRect();
            this.bounds.FromJSON(C.bounds)
        }
        this.edgeIDs = C.edgeIDs;
        this.nodeIDs = C.nodeIDs;
        if (C.nodePositions) {
            this.nodePositions = new Array();
            for (var B = 0; B < C.nodePositions.length; B++) {
                this.nodePositions[B] = new SuperMap.IS.MapCoord();
                this.nodePositions[B].x = C.nodePositions[B].x;
                this.nodePositions[B].y = C.nodePositions[B].y
            }
            this.bounds.FromJSON(C.nodePositions)
        }
        this.totalLength = C.totalLength
    };
    this.Destroy = function() {
        if (this.bounds) {
            this.bounds.Destroy();
            this.bounds = null
        }
        if (this.edgeIDs) {
            while (this.edgeIDs.length > 0) {
                this.edgeIDs.pop()
            }
            this.edgeIDs = null
        }
        if (this.nodeIDs) {
            while (this.nodeIDs.length > 0) {
                this.nodeIDs.pop()
            }
            this.nodeIDs = null
        }
        if (this.nodePositions) {
            while (this.nodePositions.length > 0) {
                var B = this.nodePositions.pop();
                B.Destroy();
                B = null
            }
            this.nodePositions = null
        }
    }
};
SuperMap.IS.QueryingEventArgs = function() {
    this.clientActionArgs = null;
    this.queryParams = null;
    this.cancel = false;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        if (A.clientActionArgs) {
            this.clientActionArgs = new SuperMap.IS.ActionEventArgs();
            this.clientActionArgs.FromJSON(A.clientActionArgs)
        }
        if (A.queryParams) {
            this.queryParams = new SuperMap.IS.QueryParam();
            this.queryParams.FromJSON(A.queryParams)
        }
        this.cancel = A.cancel
    };
    this.Destroy = function() {
        if (this.queryParams) {
            this.queryParams.Destroy();
            this.queryParams = null
        }
        if (this.clientActionArgs) {
            this.clientActionArgs.Destroy();
            this.clientActionArgs = null
        }
    }
};
SuperMap.IS.QueryCompletedEventArgs = function(A) {
    this.customResponse = A.customResponse;
    this.recordsets = A.recordsets;
    this.totalCount = A.totalCount;
    this.FromJSON = function(C) {
        if (!C) {
            return
        }
        if (C.recordsets && C.recordsets.length > 0) {
            this.recordsets = new Array();
            for (var B = 0; B < C.recordsets.length; B++) {
                this.recordsets[B] = new SuperMap.IS.Recordset();
                this.recordsets[B].FromJSON(C.recordsets)
            }
        }
        this.customResponse = C.customResponse;
        this.totalCount = C.totalCount
    };
    this.Destroy = function() {
        if (this.customResponse) {
            this.customResponse = null;
            this.customResponse = null
        }
        if (this.totalCount) {
            this.totalCount = null
        }
        if (this.recordsets) {
            while (this.recordsets.length > 0) {
                var B = this.recordsets.pop();
                B.Destroy();
                B = null
            }
            this.recordsets = null
        }
    }
};
SuperMap.IS.ClosestFacilityFindingEventArgs = function() {
    this.clientActionArgs = null;
    this.proximityParams = null;
    this.cancel = false;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        if (A.clientActionArgs) {
            this.clientActionArgs = new SuperMap.IS.ActionEventArgs();
            this.clientActionArgs.FromJSON(A.clientActionArgs)
        }
        if (A.proximityParams) {
            this.proximityParams = new SuperMap.IS.ProximityParam();
            this.proximityParams.FromJSON(A.proximityParams)
        }
        this.cancel = A.cancel
    };
    this.Destroy = function() {
        if (this.clientActionArgs) {
            this.clientActionArgs.Destroy();
            this.clientActionArgs = null
        }
        if (this.proximityParams) {
            this.proximityParams.Destroy();
            this.proximityParams = null
        }
    }
};
SuperMap.IS.ClosestFacilityFoundEventArgs = function(A) {
    this.result = new SuperMap.IS.Recordset();
    this.result.FromJSON(A.returnRecordset);
    this.FromJSON = function(B) {
        if (!B) {
            return
        }
        if (B.result) {
            this.result = new SuperMap.IS.Recordset();
            this.result.FromJSON(B.result)
        }
    };
    this.Destroy = function() {
        if (this.result) {
            this.result.Destroy();
            this.result = null
        }
    }
};
SuperMap.IS.CustomEventArgs = function(A) {
    this.customParams = A;
    this.FromJSON = function(B) {
        if (!B) {
            return
        }
        this.customParams = B.customParams
    };
    this.Destroy = function() {}
};
SuperMap.IS.MeasureResult = function() {
    this.area = 0;
    this.distance = 0;
    this.trackingLayerIndex = -1;
    this.userID = "";
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.area = A.area;
        this.distance = A.distance;
        this.trackingLayerIndex = A.trackingLayerIndex;
        this.userID = A.userID
    };
    this.Destroy = function() {}
};
SuperMap.IS.BufferParam = function() {
    this.geometries = null;
    this.distance = 0;
    this.smoothness = 0;
    this.fromLayer = null;
    this.queryMode = 0;
    this.fromCustomGeo = false;
    this.FromJSON = function(B) {
        if (!B) {
            return
        }
        if (B.geometries) {
            this.geometries = new Array();
            for (var A = 0; A < B.geometries.length; A++) {
                this.geometries[A] = SuperMap.IS.Geometry();
                this.geometries[A].FromJSON(B.geometries[A])
            }
        }
        this.distance = B.distance;
        this.smoothness = B.smoothness;
        if (B.fromLayer) {
            this.fromLayer = new SuperMap.IS.QueryLayer();
            this.fromLayer.FromJSON(B.fromLayer)
        }
        this.queryMode = B.queryMode;
        this.fromCustomGeo = B.fromCustomGeo
    };
    this.Destroy = function() {
        if (this.geometries) {
            while (this.geometries.length > 0) {
                var A = this.geometries.pop();
                A.Destroy();
                A = null
            }
            this.geometries = null
        }
        if (this.fromLayer) {
            this.fromLayer.Destroy();
            this.fromLayer = null
        }
    }
};
SuperMap.IS.ProximityParam = function() {
    this.networkParams = null;
    this.highlight = null;
    this.facilityLayer = "";
    this.facilityFilter = "";
    this.isFromEvent = false;
    this.maxRadius = 0;
    this.facilityCount = 1;
    this.FromJSON = function(A) {
        if (A.networkParams) {
            this.networkParams = new SuperMap.IS.NetworkParams();
            this.networkParams.FromJSON(A.networkParams)
        }
        if (A.highlight) {
            this.highlight = new SuperMap.IS.Highlight();
            this.highlight.FromJSON(A.highlight)
        }
        this.facilityLayer = A.facilityLayer;
        this.facilityFilter = A.facilityFilter;
        this.isFromEvent = A.isFromEvent;
        this.maxRadius = A.maxRadius;
        this.facilityCount = A.facilityCount
    };
    this.Destroy = function() {
        if (this.networkParams) {
            this.networkParams.Destroy();
            this.networkParams = null
        }
        if (this.highlight) {
            this.highlight.Destroy();
            this.highlight = null
        }
    }
};
SuperMap.IS.ProximityResult = function() {
    this.returnRecordset = null;
    this.trackingLayerIndex = -1;
    this.userID = "";
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        if (A.returnRecordset) {
            this.returnRecordset = new SuperMap.IS.Recordset();
            this.returnRecordset.FromJSON(A.returnRecordset)
        }
        this.trackingLayerIndex = A.trackingLayerIndex;
        this.userID = A.userID
    };
    this.Destroy = function() {
        if (this.returnRecordset != null) {
            this.returnRecordset.Destroy();
            this.returnRecordset = null
        }
    }
};
SuperMap.IS.ResourceParam = function(C, D, E, B, A) {
    this.style = null;
    this.resourceType = 0;
    this.imageFormat = 0;
    this.width = 16;
    this.height = 16;
    if (C != null) {
        this.style = C
    }
    if (D != null) {
        this.resourceType = D
    }
    if (E != null) {
        this.imageFormat = E
    }
    if (typeof(B) != "undefined") {
        this.width = B
    }
    if (typeof(A) != "undefined") {
        this.height = A
    }
    this.FromJSON = function(F) {
        if (!F) {
            return
        }
        if (F.style) {
            this.style = new SuperMap.IS.Style();
            this.style.FromJSON(F.style)
        }
        this.resourceType = F.resourceType;
        this.imageFormat = F.imageFormat;
        this.width = F.width;
        this.height = F.height
    };
    this.Destroy = function() {
        if (this.style) {
            this.style.Destroy();
            this.style = null
        }
    }
};
SuperMap.IS.OverlayParam = function() {
    this.operation = 0;
    this.inLayer = "";
    this.operateLayer = "";
    this.operateRegion = null;
    this.resultName = "";
    this.overwriteIfExists = false;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.operation = A.operation;
        this.inLayer = A.inLayer;
        this.operateLayer = A.operateLayer;
        if (A.operateRegion) {
            this.operateRegion = new SuperMap.IS.Geometry();
            this.operateRegion.FromJSON(A.operateRegion)
        }
        this.resultName = A.resultName;
        this.overwriteIfExists = A.overwriteIfExists
    };
    this.Destroy = function() {
        if (this.operateRegion) {
            this.operateRegion.Destroy();
            this.operateRegion = null
        }
    }
};
SuperMap.IS.OverlayResult = function() {
    this.succeed = false;
    this.resultDataset = "";
    this.message = "";
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.succeed = A.succeed;
        this.resultDataset = A.resultDataset;
        this.message = A.message
    };
    this.Destroy = function() {}
};
SuperMap.IS.InterpolateParam = function() {
    this.cellSize = 0;
    this.dataset = "";
    this.fieldName = "";
    this.variantSearch = false;
    this.count = 0;
    this.distance = 0;
    this.resultDataset = "";
    this.overwriteIfExists = false;
    this.interpolateMode = 0;
    this.power = 2;
    this.varMode = 1;
    this.maxPntCountInQuadNode = 50;
    this.maxPntCountForInterpolation = 200;
    this.tension = 40;
    this.smooth = 0.1;
    this.bounds = null;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.cellSize = A.cellSize;
        this.dataset = A.dataset;
        this.fieldName = A.fieldName;
        this.variantSearch = A.variantSearch;
        this.count = A.count;
        this.distance = A.distance;
        this.resultDataset = A.resultDataset;
        this.overwriteIfExists = A.overwriteIfExists;
        this.interpolateMode = A.interpolateMode;
        this.power = A.power;
        this.varMode = A.varMode;
        this.maxPntCountInQuadNode = A.maxPntCountInQuadNode;
        this.maxPntCountForInterpolation = A.maxPntCountForInterpolation;
        this.tension = A.cellSize;
        this.smooth = A.smooth;
        this.bounds = A.bounds
    };
    this.Destroy = function() {
        if (this.bounds) {
            this.bounds.Destroy();
            this.bounds = null
        }
    }
};
SuperMap.IS.InterpolateResult = function() {
    this.succeed = false;
    this.resultDataset = "";
    this.message = "";
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.succeed = A.succeed;
        this.resultDataset = A.resultDataset;
        this.message = A.message
    };
    this.Destroy = function() {}
};
SuperMap.IS.AggregateParam = function() {
    this.dataset = "";
    this.cellFactor = 0;
    this.aggregationType = 0;
    this.expand = true;
    this.nodata = true;
    this.resultDataset = "";
    this.overwriteIfExists = false;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.dataset = A.dataset;
        this.cellFactor = A.cellFactor;
        this.aggregationType = A.aggregationType;
        this.expand = A.expand;
        this.nodata = A.nodata;
        this.resultDataset = A.resultDataset;
        this.overwriteIfExists = A.overwriteIfExists
    };
    this.Destroy = function() {}
};
SuperMap.IS.ReclassParam = function() {
    this.dataset = "";
    this.remapTable = null;
    this.outputGridFormat = 1;
    this.resultDataset = "";
    this.overwriteIfExists = false;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.dataset = A.dataset;
        if (A.remapTable) {
            this.remapTable = new SuperMap.IS.RemapTable();
            this.remapTable.FromJSON(A.remapTable)
        }
        this.outputGridFormat = A.outputGridFormat;
        this.resultDataset = A.resultDataset;
        this.overwriteIfExists = A.overwriteIfExists
    };
    this.Destroy = function() {
        if (this.remapTable) {
            this.remapTable.Destroy();
            this.remapTable = null
        }
    }
};
SuperMap.IS.RemapTable = function() {
    this.breakValues = null;
    this.changeMissingValueTo = 0;
    this.changeMissingValueToNoData = false;
    this.changeNoDataTo = 0;
    this.mappingType = 1;
    this.newValues = null;
    this.newValuesCount = 0;
    this.retainMissingValue = false;
    this.retainNoData = false;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.breakValues = A.breakValues;
        this.changeMissingValueTo = A.changeMissingValueTo;
        this.changeMissingValueToNoData = A.changeMissingValueToNoData;
        this.changeNoDataTo = A.changeNoDataTo;
        this.mappingType = A.mappingType;
        this.newValues = A.newValues;
        this.newValuesCount = A.newValuesCount;
        this.retainMissingValue = A.retainMissingValue;
        this.retainNoData = A.retainNoData
    };
    this.Destroy = function() {
        if (this.breakValues) {
            while (this.breakValues.length > 0) {
                this.breakValues.pop()
            }
            this.breakValues = null
        }
        if (this.newValues) {
            while (this.newValues.length > 0) {
                this.newValues.pop()
            }
            this.newValues = null
        }
    }
};
SuperMap.IS.ReplaceParam = function() {
    this.dataset = "";
    this.tableName = "";
    this.sourceFieldName = "";
    this.replaceFieldName = "";
    this.resultDataset = "";
    this.overwriteIfExists = false;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.dataset = A.dataset;
        this.tableName = A.tableName;
        this.sourceFieldName = A.sourceFieldName;
        this.replaceFieldName = A.replaceFieldName;
        this.resultDataset = A.resultDataset;
        this.overwriteIfExists = A.overwriteIfExists
    };
    this.Destroy = function() {}
};
SuperMap.IS.ResampleParam = function() {
    this.dataset = "";
    this.newCellSize = 0;
    this.resampleType = 1;
    this.resultDataset = "";
    this.overwriteIfExists = false;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.dataset = A.dataset;
        this.newCellSize = A.newCellSize;
        this.resampleType = A.resampleType;
        this.dataSourceName = A.dataSourceName;
        this.resultDataset = A.resultDataset;
        this.overwriteIfExists = A.overwriteIfExists
    };
    this.Destroy = function() {}
};
SuperMap.IS.GeneralizeResult = function() {
    this.succeed = false;
    this.resultDataset = "";
    this.message = "";
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.succeed = A.succeed;
        this.resultDataset = A.resultDataset;
        this.message = A.message
    };
    this.Destroy = function() {}
};
SuperMap.IS.IsoLineParam = function(A) {
    this.interval = A;
    this.baseValue = 0;
    this.smoothMethod = 0;
    this.smoothness = 3;
    this.useFastMethod = false;
    this.saveResultToDataset = true;
    this.resultDataset = "";
    this.saveDatasetAsCad = false;
    this.overwriteIfExists = false;
    this.FromJSON = function(B) {
        if (!B) {
            return
        }
        this.interval = B.interval;
        this.baseValue = B.baseValue;
        this.smoothMethod = B.smoothMethod;
        this.smoothness = B.smoothness;
        this.useFastMethod = B.useFastMethod;
        this.saveResultToDataset = B.saveResultToDataset;
        this.resultDataset = B.resultDataset;
        this.saveDatasetAsCad = B.saveDatasetAsCad;
        this.overwriteIfExists = B.overwriteIfExists
    };
    this.Destroy = function() {}
};
SuperMap.IS.IsoLineResult = function() {
    this.succeed = false;
    this.message = "";
    this.isoLines = null;
    this.isoLineDataset = "";
    this.FromJSON = function(B) {
        if (!B) {
            return
        }
        this.succeed = B.succeed;
        this.message = B.message;
        if (B.isoLines && B.isoLines.length > 0) {
            this.isoLines = new Array();
            for (var A = 0; A < B.isoLines.length; A++) {
                this.isoLines[A] = new SuperMap.IS.Entity();
                this.isoLines[A].FromJSON(B.isoLines[A])
            }
        }
        this.isoLineDataset = B.isoLineDataset
    };
    this.Destroy = function() {
        if (this.isoLines) {
            while (this.isoLines.length > 0) {
                var A = this.isoLines.pop();
                A.Destroy();
                A = null
            }
            this.isoLines = null
        }
    }
};
SuperMap.IS.GridClipParam = function() {
    this.dataset = "";
    this.clipRegion = null;
    this.backValue = 0;
    this.resultDataset = "";
    this.overwriteIfExists = false;
    this.clipInRegion = true;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.dataset = A.dataset;
        if (A.clipRegion) {
            this.clipRegion = new SuperMap.IS.Geometry();
            this.clipRegion.FromJSON(A.clipRegion)
        }
        this.backValue = A.backValue;
        this.resultDataset = A.resultDataset;
        this.overwriteIfExists = A.overwriteIfExists;
        this.clipInRegion = A.clipInRegion
    };
    this.Destroy = function() {
        if (this.clipRegion) {
            this.clipRegion.Destroy();
            this.clipRegion = null
        }
    }
};
SuperMap.IS.GridClipResult = function() {
    this.succeed = false;
    this.resultDataset = "";
    this.message = "";
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.succeed = A.succeed;
        this.resultDataset = A.resultDataset;
        this.message = A.message
    };
    this.Destroy = function() {}
};
SuperMap.IS.BusSolutionParam = function() {
    this.transferTimes = 2;
    this.most = true;
    this.expected = 5;
    this.orderMode = 0;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.transferTimes = A.transferTimes;
        this.most = A.most;
        this.expected = A.expected;
        this.orderMode = A.orderMode
    };
    this.Destroy = function() {}
};
SuperMap.IS.IsoRegionParam = function() {
    this.gridDataset = "";
    this.interval = 0;
    this.baseValue = 0;
    this.smoothMethod = 0;
    this.smoothness = 3;
    this.resultDataset = "";
    this.overwriteIfExists = false;
    this.tolerance = 0;
    this.clipRegion = null;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.gridDataset = A.gridDataset;
        this.interval = A.interval;
        this.baseValue = A.baseValue;
        this.smoothMethod = A.smoothMethod;
        this.smoothness = A.smoothness;
        this.resultDataset = A.resultDataset;
        this.overwriteIfExists = A.overwriteIfExists;
        this.tolerance = A.tolerance;
        if (A.clipRegion) {
            this.clipRegion = new SuperMap.IS.Geometry();
            this.clipRegion.FromJSON(A.clipRegion)
        }
    };
    this.Destroy = function() {
        if (this.clipRegion) {
            this.clipRegion.Destroy();
            this.clipRegion = null
        }
    }
};
SuperMap.IS.IsoRegionResult = function() {
    this.succeed = false;
    this.resultDataset = "";
    this.message = "";
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.succeed = A.succeed;
        this.resultDataset = A.resultDataset;
        this.message = A.message
    };
    this.Destroy = function() {}
};
SuperMap.IS.Datasource = function() {
    this.id = 0;
    this.name = "";
    this.alias = "";
    this.coordsSys = null;
    this.datasets = null;
    this.engineType = 0;
    this.FromJSON = function(B) {
        if (!B) {
            return
        }
        this.id = B.id;
        this.name = B.name;
        this.alias = B.alias;
        if (B.coordsSys) {
            this.coordsSys = new SuperMap.IS.MapCoordsSys();
            this.coordsSys.FromJSON(B.coordsSys)
        }
        if (B.datasets && B.datasets.length > 0) {
            this.datasets = new Array();
            for (var A = 0; A < B.datasets.length; A++) {
                this.datasets[A] = new SuperMap.IS.Dataset();
                this.datasets[A].FromJSON(B.datasets[A])
            }
        }
        this.engineType = B.engineType
    };
    this.Destroy = function(B) {
        if (this.coordsSys) {
            this.coordsSys.Destroy();
            this.coordsSys = null
        }
        if (this.datasets) {
            while (this.datasets.length > 0) {
                var A = this.datasets.pop();
                A.Destroy();
                A = null
            }
            this.datasets = null
        }
    }
};
SuperMap.IS.Workspace = function() {
    this.path = "";
    this.datasources = null;
    this.maps = null;
    this.customInfo = "";
    this.FromJSON = function(B) {
        if (!B) {
            return
        }
        this.path = B.path;
        if (B.datasources && B.datasources.length > 0) {
            this.datasources = new Array();
            for (var A = 0; A < B.datasources.length; A++) {
                this.datasources[A] = new SuperMap.IS.Datasource();
                this.datasources[A].FromJSON(B.datasources[A])
            }
        }
        if (B.maps && B.maps.length > 0) {
            this.maps = new Array();
            for (var A = 0; A < B.maps.length; A++) {
                this.maps[A] = B.maps[A]
            }
        }
        this.customInfo = B.customInfo
    };
    this.Destroy = function(B) {
        if (this.datasources) {
            while (this.datasources.length > 0) {
                var A = this.datasources.pop();
                A.Destroy();
                A = null
            }
            this.datasources = null
        }
        if (this.maps) {
            while (this.maps.length > 0) {
                this.maps.pop()
            }
            this.maps = null
        }
    }
};
SuperMap.IS.MapCoordsSys = function() {
    this.projectionName = "";
    this.coordUnits = 10000;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.projectionName = A.projectionName;
        this.coordUnits = A.coordUnits
    };
    this.Destroy = function() {}
};
SuperMap.IS.Dataset = function() {
    this.id = 0;
    this.name = "";
    this.vector = false;
    this.type = 0;
    this.bounds = null;
    this.fields = null;
    this.sheeted = false;
    this.sheets = null;
    this.maxZ = 0;
    this.minZ = 0;
    this.fieldTypes = null;
    this.FromJSON = function(C) {
        if (!C) {
            return
        }
        this.id = C.id;
        this.name = C.name;
        this.vector = C.vector;
        this.type = C.type;
        if (C.bounds) {
            this.bounds = new SuperMap.IS.MapRect();
            this.bounds.FromJSON(C.bounds)
        }
        if (C.fields && C.fields.length > 0) {
            this.fields = new Array();
            for (var B = 0; B < C.fields.length; B++) {
                this.fields[B] = C.fields[B]
            }
        }
        this.sheeted = C.sheeted;
        if (C.sheets && C.sheets.length > 0) {
            this.sheets = new Array();
            for (var B = 0; B < C.sheets.length; B++) {
                var A = new SuperMap.IS.Sheet();
                A.FromJSON(C.sheets[B]);
                this.sheets.push(A)
            }
        }
        this.maxZ = C.maxZ;
        this.minZ = C.minZ;
        if (C.fieldTypes && C.fieldTypes.length > 0) {
            this.fieldTypes = new Array();
            for (var B = 0; B < C.fieldTypes.length; B++) {
                this.fieldTypes[B] = C.fieldTypes[B]
            }
        }
    };
    this.Destroy = function(B) {
        if (this.bounds) {
            this.bounds.Destroy();
            this.bounds = null
        }
        if (this.fields) {
            while (this.fields.length > 0) {
                this.fields.pop()
            }
            this.fields = null
        }
        if (this.sheets) {
            while (this.sheets.length > 0) {
                var A = this.sheets.pop();
                A.Destroy();
                A = null
            }
            this.sheets = null
        }
        if (this.fieldTypes) {
            while (this.fieldTypes.length > 0) {
                this.fieldTypes.pop()
            }
            this.fieldTypes = null
        }
    }
};
SuperMap.IS.Sheet = function() {
    this.id = 0;
    this.name = "";
    this.bounds = null;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.id = A.id;
        this.name = A.name;
        if (A.bounds) {
            this.bounds = new SuperMap.IS.MapRect();
            this.bounds.FromJSON(A.bounds)
        }
    };
    this.Destroy = function() {
        if (this.bounds) {
            this.bounds.Destroy();
            this.bounds = null
        }
    }
};
SuperMap.IS.FloodParam = function() {
    this.dataset = "";
    this.height = 0;
    this.region = null;
    this.highlight = null;
    this.returnFloodRegion = false;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.dataset = A.dataset;
        this.height = A.height;
        if (A.region) {
            this.region = new SuperMap.IS.Geometry();
            this.region.FromJSON(A.region)
        }
        if (A.highlight) {
            this.highlight = new SuperMap.IS.Highlight();
            this.highlight.FromJSON(A.highlight)
        }
        this.returnFloodRegion = A.returnFloodRegion
    };
    this.Destroy = function() {
        if (this.region) {
            this.region.Destroy();
            this.region = null
        }
        if (this.highlight) {
            this.highlight.Destroy();
            this.highlight = null
        }
    }
};
SuperMap.IS.FloodResult = function() {
    this.succeed = false;
    this.region = null;
    this.message = "";
    this.trackingLayerIndex = -1;
    this.userID = "";
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.succeed = A.succeed;
        if (A.region) {
            this.region = new SuperMap.IS.Geometry();
            this.region.FromJSON(A.region)
        }
        this.message = A.message;
        this.trackingLayerIndex = A.trackingLayerIndex;
        this.userID = A.userID
    };
    this.Destroy = function() {
        if (this.region) {
            this.region.Destroy();
            this.region = null
        }
    }
};
SuperMap.IS.CutFillParam = function() {
    this.sourceDataset = "";
    this.targetDataset = "";
    this.resultDataset = "";
    this.overwriteIfExists = false;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.sourceDataset = A.sourceDataset;
        this.targetDataset = A.targetDataset;
        this.resultDataset = A.resultDataset;
        this.overwriteIfExists = A.overwriteIfExists
    };
    this.Destroy = function() {}
};
SuperMap.IS.CutFillResult = function() {
    this.succeed = false;
    this.resultDataset = "";
    this.message = "";
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.succeed = A.succeed;
        this.resultDataset = A.resultDataset;
        this.message = A.message
    };
    this.Destroy = function() {}
};
SuperMap.IS.CalculateViewShedParam = function() {
    this.dataset = "";
    this.viewPoint = null;
    this.viewRadius = 0;
    this.resultDataset = "";
    this.overwriteIfExists = false;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.dataset = A.dataset;
        if (A.viewPoint) {
            this.viewPoint = new SuperMap.IS.Point3D();
            this.viewPoint.FromJSON(A.viewPoint)
        }
        this.viewRadius = A.viewRadius;
        this.resultDataset = A.resultDataset;
        this.overwriteIfExists = A.overwriteIfExists
    };
    this.Destroy = function() {
        if (this.viewPoint) {
            this.viewPoint.Destroy();
            this.viewPoint = null
        }
    }
};
SuperMap.IS.CalculateViewShedResult = function() {
    this.succeed = false;
    this.resultDataset = "";
    this.message = "";
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.succeed = A.succeed;
        this.resultDataset = A.resultDataset;
        this.message = A.message
    };
    this.Destroy = function() {}
};
SuperMap.IS.Point3D = function() {
    this.x = 0;
    this.y = 0;
    this.z = 0;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.x = A.x;
        this.y = A.y;
        this.z = A.z
    };
    this.Destroy = function() {}
};
SuperMap.IS.AspectParam = function() {
    this.dataset = "";
    this.resultDataset = "";
    this.overwriteIfExists = false;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.dataset = A.dataset;
        this.resultDataset = A.resultDataset;
        this.overwriteIfExists = A.overwriteIfExists
    };
    this.Destroy = function() {}
};
SuperMap.IS.AspectResult = function() {
    this.succeed = false;
    this.resultDataset = "";
    this.message = "";
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.succeed = A.succeed;
        this.resultDataset = A.resultDataset;
        this.message = A.message
    };
    this.Destroy = function() {}
};
SuperMap.IS.SlopeParam = function() {
    this.dataset = "";
    this.slopeType = 0;
    this.zFactor = 1;
    this.resultDataset = "";
    this.overwriteIfExists = false;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.dataset = A.dataset;
        this.slopeType = A.slopeType;
        this.zFactor = A.zFactor;
        this.dataSourceName = A.dataSourceName;
        this.resultDataset = A.resultDataset;
        this.overwriteIfExists = A.overwriteIfExists
    };
    this.Destroy = function() {}
};
SuperMap.IS.SlopeResult = function() {
    this.succeed = false;
    this.resultDataset = "";
    this.message = "";
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.succeed = A.succeed;
        this.resultDataset = A.resultDataset;
        this.message = A.message
    };
    this.Destroy = function() {}
};
SuperMap.IS.SurfaceAreaParam = function() {
    this.dataset = "";
    this.region = null;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.dataset = A.dataset;
        if (A.region) {
            this.region = new SuperMap.IS.Geometry();
            this.region.FromJSON(A.region)
        }
    };
    this.Destroy = function() {
        if (this.region) {
            this.region.Destroy();
            this.region = null
        }
    }
};
SuperMap.IS.SurfaceAreaResult = function() {
    this.succeed = false;
    this.area = 0;
    this.message = "";
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.succeed = A.succeed;
        this.area = A.area;
        this.message = A.message
    };
    this.Destroy = function() {}
};
SuperMap.IS.SurfaceLengthParam = function() {
    this.dataset = "";
    this.line = null;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.dataset = A.dataset;
        if (A.line) {
            this.line = new SuperMap.IS.Geometry();
            this.line.FromJSON(A.line)
        }
    };
    this.Destroy = function() {
        if (this.line) {
            this.line.Destroy();
            this.line = null
        }
    }
};
SuperMap.IS.SurfaceLengthResult = function() {
    this.succeed = false;
    this.length = 0;
    this.message = "";
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.succeed = A.succeed;
        this.length = A.length;
        this.message = A.message
    };
    this.Destroy = function() {}
};
SuperMap.IS.GridCompareParam = function() {
    this.targetDataset = "";
    this.sourceDataset = "";
    this.compareMode = 0;
    this.sourceValue = 0;
    this.ignoreNoValue = false;
    this.resultDataset = "";
    this.overwriteIfExists = false;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.targetDataset = A.targetDataset;
        this.sourceDataset = A.sourceDataset;
        this.compareMode = A.compareMode;
        this.sourceValue = A.sourceValue;
        this.ignoreNoValue = A.ignoreNoValue;
        this.resultDataset = A.resultDataset;
        this.overwriteIfExists = A.overwriteIfExists
    };
    this.Destroy = function() {}
};
SuperMap.IS.GridCompareResult = function() {
    this.succeed = false;
    this.resultDataset = "";
    this.message = "";
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.succeed = A.succeed;
        this.resultDataset = A.resultDataset;
        this.message = A.message
    };
    this.Destroy = function() {}
};
SuperMap.IS.NeighborhoodParam = function() {
    this.neighborhoodMode = 0;
    this.analysisUnitsType = 0;
    this.innerRadius = 0;
    this.outerRadius = 0;
    this.radius = 0;
    this.width = 0;
    this.height = 0;
    this.pieRadius = 0;
    this.startAngle = 0;
    this.endAngle = 0;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.neighborhoodMode = A.neighborhoodMode;
        this.analysisUnitsType = A.analysisUnitsType;
        this.innerRadius = A.innerRadius;
        this.outerRadius = A.outerRadius;
        this.radius = A.radius;
        this.width = A.width;
        this.height = A.height;
        this.pieRadius = A.pieRadius;
        this.startAngle = A.startAngle;
        this.endAngle = A.endAngle
    };
    this.Destroy = function() {};
    this.SetAnnulus = function(C, B, A) {
        this.neighborhoodMode = 0;
        this.innerRadius = C;
        this.outerRadius = B;
        this.analysisUnitsType = A
    };
    this.SetCircle = function(A, B) {
        this.neighborhoodMode = 1;
        this.radius = A;
        this.analysisUnitsType = B
    };
    this.SetRectangle = function(C, A, B) {
        this.neighborhoodMode = 2;
        this.width = C;
        this.height = A;
        this.analysisUnitsType = B
    };
    this.SetWedge = function(B, C, A, D) {
        this.neighborhoodMode = 3;
        this.pieRadius = B;
        this.startAngle = C;
        this.endAngle = A;
        this.AnalysisUnitsType = D
    }
};
SuperMap.IS.NeighbourStatisticsParam = function() {
    this.dataset = "";
    this.statisticMode = 1;
    this.neighbourhood = null;
    this.ignoreNoValue = false;
    this.resultDataset = "";
    this.overwriteIfExists = false;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.dataset = A.dataset;
        this.statisticMode = A.statisticMode;
        if (A.neighbourhood) {
            this.neighbourhood = new SuperMap.IS.NeighborhoodParam();
            this.neighbourhood.FromJSON(A.neighbourhood)
        }
        this.ignoreNoValue = A.ignoreNoValue;
        this.resultDataset = A.resultDataset;
        this.overwriteIfExists = A.overwriteIfExists
    };
    this.Destroy = function() {
        if (this.neighbourhood) {
            this.neighbourhood.Destroy();
            this.neighbourhood = null
        }
    }
};
SuperMap.IS.NeighbourStatisticsResult = function() {
    this.succeed = false;
    this.resultDataset = "";
    this.message = "";
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.succeed = A.succeed;
        this.resultDataset = A.resultDataset;
        this.message = A.message
    };
    this.Destroy = function() {}
};
SuperMap.IS.OverlayStatisticsParam = function() {
    this.zoneDataset = "";
    this.fieldName = "";
    this.valueDataset = "";
    this.statisticMode = 1;
    this.ignoreNoValue = false;
    this.resultDataset = "";
    this.tableDataset = "";
    this.overwriteIfExists = false;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.zoneDataset = A.zoneDataset;
        this.fieldName = A.fieldName;
        this.valueDataset = A.valueDataset;
        this.statisticMode = A.statisticMode;
        this.ignoreNoValue = A.ignoreNoValue;
        this.resultDataset = A.resultDataset;
        this.tableDataset = A.tableDataset;
        this.overwriteIfExists = A.overwriteIfExists
    };
    this.Destroy = function() {}
};
SuperMap.IS.OverlayStatisticsResult = function() {
    this.succeed = false;
    this.resultDataset = "";
    this.message = "";
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.succeed = A.succeed;
        this.resultDataset = A.resultDataset;
        this.message = A.message
    };
    this.Destroy = function() {}
};
SuperMap.IS.GridExecuteParam = function() {
    this.mathExpression = "";
    this.resultDataset = "";
    this.overwriteIfExists = false;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.mathExpression = A.mathExpression;
        this.resultDataset = A.resultDataset;
        this.overwriteIfExists = A.overwriteIfExists
    };
    this.Destroy = function() {}
};
SuperMap.IS.GridExecuteResult = function() {
    this.succeed = false;
    this.resultDataset = "";
    this.message = "";
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.succeed = A.succeed;
        this.resultDataset = A.resultDataset;
        this.message = A.message
    };
    this.Destroy = function() {}
};
SuperMap.IS.StatisticsQueryParam = function() {
    this.layerName = "";
    this.fieldName = "";
    this.whereClause = "";
    this.statisticMode = 0;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.layerName = A.layerName;
        this.fieldName = A.fieldName;
        this.whereClause = A.whereClause;
        this.statisticMode = A.statisticMode
    };
    this.Destroy = function() {}
};
SuperMap.IS.IsVisibleParam = function() {
    this.dataset = "";
    this.points = null;
    this.FromJSON = function(B) {
        if (!B) {
            return
        }
        this.dataset = B.dataset;
        if (B.points && B.points.length > 0) {
            this.points = new Array();
            for (var A = 0; A < B.points.length; A++) {
                this.points[A] = new SuperMap.IS.Point3D();
                this.points[A].FromJSON(B.points[A])
            }
        }
    };
    this.Destroy = function() {
        if (this.points) {
            while (this.points.length > 0) {
                var A = this.points.pop();
                A.Destroy();
                A = null
            }
            this.points = null
        }
    }
};
SuperMap.IS.IsVisibleResult = function() {
    this.succeeds = null;
    this.message = "";
    this.FromJSON = function(B) {
        if (!B) {
            return
        }
        if (B.succeeds && B.succeeds.length > 0) {
            this.succeeds = new Array();
            for (var A = 0; A < B.succeeds.length; A++) {
                this.succeeds[A] = B.succeeds[A]
            }
        }
        this.message = B.message
    };
    this.Destroy = function() {
        if (this.succeeds) {
            while (this.succeeds.length > 0) {
                this.succeeds.pop()
            }
            this.succeeds = null
        }
    }
};
SuperMap.IS.SurfaceProfileParam = function() {
    this.dataset = "";
    this.line = null;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.dataset = A.dataset;
        if (A.line) {
            this.line = new SuperMap.IS.Geometry();
            this.line.FromJSON(A.line)
        }
    };
    this.Destroy = function() {
        if (this.line) {
            this.line.Destroy();
            this.line = null
        }
    }
};
SuperMap.IS.SurfaceProfileResult = function() {
    this.succeed = false;
    this.profileLine = null;
    this.message = "";
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.succeed = A.succeed;
        if (A.profileLine) {
            this.profileLine = new SuperMap.IS.Geometry();
            this.profileLine.FromJSON(A.profileLine)
        }
        this.message = A.message
    };
    this.Destroy = function() {
        if (this.profileLine) {
            this.profileLine.Destroy();
            this.profileLine = null
        }
    }
};
SuperMap.IS.BasinParam = function() {
    this.dataset = "";
    this.resultDataset = "";
    this.overwriteIfExists = false;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.dataset = A.dataset;
        this.resultDataset = A.resultDataset;
        this.overwriteIfExists = A.overwriteIfExists
    };
    this.Destroy = function() {}
};
SuperMap.IS.BasinResult = function() {
    this.succeed = false;
    this.resultDataset = "";
    this.message = "";
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.succeed = A.succeed;
        this.resultDataset = A.resultDataset;
        this.message = A.message
    };
    this.Destroy = function() {}
};
SuperMap.IS.WaterShedParam = function() {
    this.sourceDataset = "";
    this.points = null;
    this.pourDataset = "";
    this.resultDataset = "";
    this.overwriteIfExists = false;
    this.FromJSON = function(B) {
        if (!B) {
            return
        }
        this.sourceDataset = B.sourceDataset;
        if (B.points && B.points.length > 0) {
            this.points = new Array();
            for (var A = 0; A < B.points.length; A++) {
                this.points[A] = new SuperMap.IS.MapCoord();
                this.points[A].FromJSON(B.points[A])
            }
        }
        this.pourDataset = B.pourDataset;
        this.resultDataset = B.resultDataset;
        this.overwriteIfExists = B.overwriteIfExists
    };
    this.Destroy = function() {
        if (this.points) {
            while (this.points.length > 0) {
                var A = this.points.pop();
                A.Destroy();
                A = null
            }
            this.points = null
        }
    }
};
SuperMap.IS.WaterShedResult = function() {
    this.succeed = false;
    this.resultDataset = "";
    this.message = "";
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.succeed = A.succeed;
        this.resultDataset = A.resultDataset;
        this.message = A.message
    };
    this.Destroy = function() {}
};
SuperMap.IS.FillSinkParam = function() {
    this.dataset = "";
    this.resultDataset = "";
    this.overwriteIfExists = false;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.dataset = A.dataset;
        this.resultDataset = A.resultDataset;
        this.overwriteIfExists = A.overwriteIfExists
    };
    this.Destroy = function() {}
};
SuperMap.IS.FillSinkResult = function() {
    this.succeed = false;
    this.resultDataset = "";
    this.message = "";
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.succeed = A.succeed;
        this.resultDataset = A.resultDataset;
        this.message = A.message
    };
    this.Destroy = function() {}
};
SuperMap.IS.FlowAccumulationParam = function() {
    this.dataset = "";
    this.resultDataset = "";
    this.overwriteIfExists = false;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.dataset = A.dataset;
        this.resultDataset = A.resultDataset;
        this.overwriteIfExists = A.overwriteIfExists
    };
    this.Destroy = function() {}
};
SuperMap.IS.FlowAccumulationResult = function() {
    this.succeed = false;
    this.resultDataset = "";
    this.message = "";
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.succeed = A.succeed;
        this.resultDataset = A.resultDataset;
        this.message = A.message
    };
    this.Destroy = function() {}
};
SuperMap.IS.FlowDirectionParam = function() {
    this.dataset = "";
    this.forceFlowAtEdge = true;
    this.createDrop = false;
    this.dropGridName = "";
    this.resultDataset = "";
    this.overwriteIfExists = false;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.dataset = A.dataset;
        this.forceFlowAtEdge = A.forceFlowAtEdge;
        this.createDrop = A.createDrop;
        this.dropGridName = A.dropGridName;
        this.resultDataset = A.resultDataset;
        this.overwriteIfExists = A.overwriteIfExists
    };
    this.Destroy = function() {}
};
SuperMap.IS.FlowDirectionResult = function() {
    this.succeed = false;
    this.resultDataset = "";
    this.message = "";
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.succeed = A.succeed;
        this.resultDataset = A.resultDataset;
        this.message = A.message
    };
    this.Destroy = function() {}
};
SuperMap.IS.RasterToVectorParam = function() {
    this.dataset = "";
    this.type = 0;
    this.fieldName = "";
    this.resultDataset = "";
    this.overwriteIfExists = false;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.dataset = A.dataset;
        this.type = A.type;
        this.fieldName = A.fieldName;
        this.resultDataset = A.resultDataset;
        this.overwriteIfExists = A.overwriteIfExists
    };
    this.Destroy = function() {}
};
SuperMap.IS.RasterToVectorResult = function() {
    this.succeed = false;
    this.resultDataset = "";
    this.message = "";
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.succeed = A.succeed;
        this.resultDataset = A.resultDataset;
        this.message = A.message
    };
    this.Destroy = function() {}
};
SuperMap.IS.GenerateGeometryByEventTableParam = function() {
    this.routeDataset = "";
    this.routeIDField = "";
    this.eventTableDataset = "";
    this.eventRouteIDField = "";
    this.eventType = 0;
    this.outputDataset = "";
    this.measureField = "";
    this.measureFieldTo = "";
    this.offsetField = "";
    this.errorField = "";
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.routeDataset = A.routeDataset;
        this.routeIDField = A.routeIDField;
        this.eventTableDataset = A.eventTableDataset;
        this.eventRouteIDField = A.eventRouteIDField;
        this.eventType = A.eventType;
        this.outputDataset = A.outputDataset;
        this.measureField = A.measureField;
        this.measureFieldTo = A.measureFieldTo;
        this.offsetField = A.offsetField;
        this.errorField = A.errorField
    };
    this.Destroy = function() {}
};
SuperMap.IS.PJCoordSys = function() {
    this.coordUnits = 0;
    this.distUnits = 0;
    this.geoCoordSys = null;
    this.name = "";
    this.pJParams = null;
    this.projection = 0;
    this.type = 0;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.coordUnits = A.coordUnits;
        this.distUnits = A.distUnits;
        if (A.geoCoordSys) {
            this.geoCoordSys = new SuperMap.IS.PJGeoCoordSys();
            this.geoCoordSys.FromJSON(A.geoCoordSys)
        }
        this.name = A.name;
        if (A.pJParams) {
            this.pJParams = new SuperMap.IS.PJParams();
            this.pJParams.FromJSON(A.pJParams)
        }
        this.projection = A.projection;
        this.type = A.type
    };
    this.Destroy = function() {
        if (this.geoCoordSys) {
            this.geoCoordSys.Destroy();
            this.geoCoordSys = null
        }
        if (this.pJParams) {
            this.pJParams.Destroy();
            this.pJParams = null
        }
    }
};
SuperMap.IS.PJDatum = function() {
    this.name = "";
    this.pJSpheroid = null;
    this.type = 0;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.name = A.name;
        if (A.pJSpheroid) {
            this.pJSpheroid = new SuperMap.IS.PJSpheroid();
            this.pJSpheroid.FromJSON(A.pJSpheroid)
        }
        this.type = A.type
    };
    this.Destroy = function() {
        if (this.pJSpheroid) {
            this.pJSpheroid.Destroy();
            this.pJSpheroid = null
        }
    }
};
SuperMap.IS.PJGeoCoordSys = function() {
    this.coordUnits = 0;
    this.name = "";
    this.pJDatum = null;
    this.pJPrimeMeridian = null;
    this.type = 0;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.coordUnits = A.coordUnits;
        this.name = A.name;
        if (A.pJDatum) {
            this.pJDatum = new SuperMap.IS.PJDatum();
            this.pJDatum.FromJSON(A.pJDatum)
        }
        if (A.pJPrimeMeridian) {
            this.pJPrimeMeridian = new SuperMap.IS.PJPrimeMeridian();
            this.pJPrimeMeridian.FromJSON(A.pJPrimeMeridian)
        }
        this.type = A.type
    };
    this.Destroy = function() {
        if (this.pJDatum) {
            this.pJDatum.Destroy();
            this.pJDatum = null
        }
        if (this.pJPrimeMeridian) {
            this.pJPrimeMeridian.Destroy();
            this.pJPrimeMeridian = null
        }
    }
};
SuperMap.IS.PJParams = function() {
    this.falseEasting = 0;
    this.falseNorthing = 0;
    this.centralMeridian = 0;
    this.centralParallel = 0;
    this.standardParallel1 = 0;
    this.standardParallel2 = 0;
    this.scaleFactor = 0;
    this.azimuth = 0;
    this.firstPointLongitude = 0;
    this.secondPointLongitude = 0;
    this.translateX = 0;
    this.translateY = 0;
    this.translateZ = 0;
    this.rotateX = 0;
    this.rotateY = 0;
    this.rotateZ = 0;
    this.scaleDifference = 0;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.falseEasting = A.falseEasting;
        this.falseNorthing = A.falseNorthing;
        this.centralMeridian = A.centralMeridian;
        this.centralParallel = A.centralParallel;
        this.standardParallel1 = A.standardParallel1;
        this.standardParallel2 = A.standardParallel2;
        this.scaleFactor = A.scaleFactor;
        this.azimuth = A.azimuth;
        this.firstPointLongitude = A.firstPointLongitude;
        this.secondPointLongitude = A.secondPointLongitude;
        this.translateX = A.translateX;
        this.translateY = A.translateY;
        this.translateZ = A.translateZ;
        this.rotateX = A.rotateX;
        this.rotateY = A.rotateY;
        this.rotateZ = A.rotateZ;
        this.scaleDifference = A.scaleDifference
    };
    this.Destroy = function() {}
};
SuperMap.IS.PJPrimeMeridian = function() {
    this.longitudeValue = 0;
    this.name = "";
    this.type = 0;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.longitudeValue = A.longitudeValue;
        this.name = A.name;
        this.type = A.type
    };
    this.Destroy = function() {}
};
SuperMap.IS.PJSpheroid = function() {
    this.axis = 0;
    this.flatten = 0;
    this.name = "";
    this.type = 0;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.axis = A.axis;
        this.flatten = A.flatten;
        this.name = A.name;
        this.type = A.type
    };
    this.Destroy = function() {}
};
SuperMap.IS.CopyDatasetParam = function() {
    this.sourceDataset = "";
    this.encodedType = 0;
    this.resultDataset = "";
    this.overwriteIfExists = false;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.sourceDataset = A.sourceDataset;
        this.encodedType = A.encodedType;
        this.resultDataset = A.resultDataset;
        this.overwriteIfExists = A.overwriteIfExists
    };
    this.Destroy = function() {}
};
SuperMap.IS.CreateDatasetParam = function() {
    this.dataset = "";
    this.encodedType = 0;
    this.layerType = 0;
    this.bounds = null;
    this.charset = 0;
    this.overwriteIfExists = false;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.dataset = A.dataset;
        this.encodedType = A.encodedType;
        this.layerType = A.layerType;
        if (A.bounds) {
            this.bounds = new SuperMap.IS.MapRect();
            this.bounds.FromJSON(A.bounds)
        }
        this.charset = A.charset;
        this.overwriteIfExists = A.overwriteIfExists
    };
    this.Destroy = function() {
        if (this.bounds) {
            this.bounds.Destroy();
            this.bounds = null
        }
    }
};
SuperMap.IS.DatasetOperateResult = function() {
    this.resultDataset = "";
    this.succeed = false;
    this.message = "";
    this.bounds = null;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.resultDataset = A.resultDataset;
        this.succeed = A.succeed;
        this.message = A.message;
        if (A.bounds) {
            this.bounds = new SuperMap.IS.MapRect();
            this.bounds.FromJSON(A.bounds)
        }
    };
    this.Destroy = function() {
        if (this.bounds) {
            this.bounds.Destroy();
            this.bounds = null
        }
    }
};
SuperMap.IS.Field = function() {
    this.allowZeroLength = false;
    this.autoIncremental = false;
    this.caption = "";
    this.defaultValue = "";
    this.format = "";
    this.descending = false;
    this.name = "";
    this.precision = 0;
    this.required = false;
    this.scaleFactor = 0;
    this.size = 0;
    this.type = 0;
    this.variableLength = false;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.allowZeroLength = A.allowZeroLength;
        this.autoIncremental = A.autoIncremental;
        this.caption = A.caption;
        this.defaultValue = A.defaultValue;
        this.format = A.format;
        this.descending = A.descending;
        this.name = A.name;
        this.precision = A.precision;
        this.required = A.required;
        this.scaleFactor = A.scaleFactor;
        this.size = A.size;
        this.type = A.type;
        this.variableLength = A.variableLength
    };
    this.Destroy = function() {}
};
SuperMap.IS.FieldOperateResult = function() {
    this.outField = "";
    this.succeed = false;
    this.message = "";
    this.bounds = null;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.outField = A.outField;
        this.succeed = A.succeed;
        this.message = A.message;
        if (A.bounds) {
            this.bounds = new SuperMap.IS.MapRect();
            this.bounds.FromJSON(A.bounds)
        }
    };
    this.Destroy = function() {
        if (this.bounds) {
            this.bounds.Destroy();
            this.bounds = null
        }
    }
};
SuperMap.IS.GetFieldParam = function() {
    this.dataset = "";
    this.fieldName = "";
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.dataset = A.dataset;
        this.fieldName = A.fieldName
    };
    this.Destroy = function() {}
};
SuperMap.IS.GetFieldResult = function() {
    this.field = null;
    this.succeed = false;
    this.message = "";
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        if (A.field) {
            this.field = new SuperMap.IS.Field();
            this.field.FromJSON(A.field)
        }
        this.succeed = A.succeed;
        this.message = A.message
    };
    this.Destroy = function() {
        if (this.field) {
            this.field.Destroy();
            this.field = null
        }
    }
};
SuperMap.IS.LayerItem = function() {
    this.value = 0;
    this.order;
    this.renderStyle = null;
    this.caption = "";
    this.visibleChecked = false;
    this.queryableChecked = false;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.value = A.value;
        this.order = A.order;
        this.renderStyle = A.renderStyle;
        this.caption = A.caption;
        this.visibleChecked = A.visibleChecked;
        this.queryableChecked = A.queryableChecked
    };
    this.Copy = this.FromJSON;
    this.Destroy = function() {
        this.renderStyle = null
    }
};
SuperMap.IS.RelQueryTableInfo = function() {
    this.joinType = 0;
    this.searchCondition = "";
    this.tableName = "";
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.joinType = A.joinType;
        this.searchCondition = A.searchCondition;
        this.tableName = A.tableName
    };
    this.Destroy = function() {}
};
SuperMap.IS.DataTable = function() {
    this.columns = null;
    this.rows = null;
    this.FromJSON = function(B) {
        if (!B) {
            return
        }
        if (B.columns && B.columns.length > 0) {
            this.columns = new Array();
            for (var A = 0; A < B.columns.length; A++) {
                this.columns[A] = new SuperMap.IS.DataColumn();
                this.columns[A].FromJSON(B.columns[A])
            }
        }
        if (B.rows && B.rows.length > 0) {
            this.rows = new Array();
            for (var A = 0; A < B.rows.length; A++) {
                this.rows[A] = new SuperMap.IS.DataRow();
                this.rows[A].FromJSON(B.rows[A])
            }
        }
    };
    this.Destroy = function() {
        if (this.columns) {
            while (this.columns.length > 0) {
                var A = this.columns.pop();
                A.Destroy();
                A = null
            }
            this.columns = null
        }
        if (this.rows) {
            while (this.rows.length > 0) {
                var B = this.rows.pop();
                B.Destroy();
                B = null
            }
            this.rows = null
        }
    }
};
SuperMap.IS.DataColumn = function() {
    this.columnName = "";
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.columnName = A.columnName
    };
    this.Destroy = function() {}
};
SuperMap.IS.DataRow = function() {
    this.values = null;
    this.FromJSON = function(B) {
        if (!B) {
            return
        }
        if (B.values && B.values.length > 0) {
            this.values = new Array();
            for (var A = 0; A < B.values.length; A++) {
                this.values[A] = B.values[A]
            }
        }
    };
    this.Destroy = function() {
        if (this.values) {
            while (this.values.length > 0) {
                this.values.pop()
            }
            this.values = null
        }
    }
};
SuperMap.IS.Font = function() {
    this.fontFamily = new SuperMap.IS.FontFamily();
    this.bold = false;
    this.italic = false;
    this.name = "Microsoft Sans Serif";
    this.strikeout = false;
    this.underline = false;
    this.size = 10;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        if (A.fontFamily != null) {
            this.fontFamily = new SuperMap.IS.FontFamily();
            this.fontFamily.FromJSON(A.fontFamily)
        }
        this.bold = A.bold;
        this.italic = A.italic;
        this.name = A.name;
        this.strikeout = A.strikeout;
        this.underline = A.underline;
        this.size = A.size
    };
    this.Destroy = function() {
        if (this.fontFamily != null) {
            this.fontFamily.Destroy();
            this.fontFamily = null
        }
    }
};
SuperMap.IS.FontFamily = function() {
    this.name = "Microsoft Sans Serif";
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.name = A.name
    };
    this.Destroy = function() {}
};
SuperMap.IS.GeometryImage = function() {
    this.geometryUrl = "";
    this.returnGeometryParam = null;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        this.geometryUrl = A.geometryUrl;
        if (A.returnGeometryParam) {
            this.returnGeometryParam = new SuperMap.IS.GeometryParam();
            this.returnGeometryParam.FromJSON(A.returnGeometryParam)
        }
    };
    this.Destroy = function() {
        if (this.returnGeometryParam) {
            this.returnGeometryParam.Destroy();
            this.returnGeometryParam = null
        }
    }
};
SuperMap.IS.GeometryParam = function() {
    this.geometries = null;
    this.styles = null;
    this.defaultStyle = null;
    this.viewer = null;
    this.center = null;
    this.mapScale = 0;
    this.viewBounds = null;
    this.pJCoordSys = null;
    this.FromJSON = function(B) {
        if (!B) {
            return
        }
        if (B.geometries && B.geometries.length > 0) {
            this.geometries = new Array();
            for (var A = 0; A < B.geometries.length; A++) {
                this.geometries[A] = new SuperMap.IS.Geometry();
                this.geometries[A].FromJSON(B.geometries[A])
            }
        }
        if (B.styles && B.styles.length > 0) {
            this.styles = new Array();
            for (var A = 0; A < B.styles.length; A++) {
                this.styles[A] = new SuperMap.IS.Style();
                this.styles[A].FromJSON(B.styles[A])
            }
        }
        if (B.defaultStyle) {
            this.defaultStyle = new SuperMap.IS.Style();
            this.defaultStyle.FromJSON(B.defaultStyle)
        }
        if (B.viewer) {
            this.viewer = new SuperMap.IS.PixelRect();
            this.viewer.FromJSON(B.viewer)
        }
        if (B.center) {
            this.center = new SuperMap.IS.MapCoord();
            this.center.FromJSON(B.center)
        }
        this.mapScale = B.mapScale;
        if (B.viewBounds) {
            this.viewBounds = new SuperMap.IS.MapRect();
            this.viewBounds.FromJSON(B.viewBounds)
        }
        if (B.pJCoordSys) {
            this.pJCoordSys = new SuperMap.IS.PJCoordSys();
            this.pJCoordSys.FromJSON(B.pJCoordSys)
        }
    };
    this.Destroy = function() {
        if (this.geometries) {
            while (this.geometries.length > 0) {
                var A = this.geometries.pop();
                A.Destroy();
                A = null
            }
            this.geometries = null
        }
        if (this.styles) {
            while (this.styles.length > 0) {
                var A = this.styles.pop();
                A.Destroy();
                A = null
            }
            this.styles = null
        }
        if (this.defaultStyle) {
            this.defaultStyle.Destroy();
            this.defaultStyle = null
        }
        if (this.viewer) {
            this.viewer.Destroy();
            this.viewer = null
        }
        if (this.center) {
            this.center.Destroy();
            this.center = null
        }
        if (this.viewBounds) {
            this.viewBounds.Destroy();
            this.viewBounds = null
        }
        if (this.pJCoordSys) {
            this.pJCoordSys.Destroy();
            this.pJCoordSys = null
        }
    }
};
SuperMap.IS.TimeSegmentInfo = function() {
    this.timeSegmentDatasetName = null;
    this.edgeID = null;
    this.timeSegmentClass = null;
    this.timeSegmentValue = null;
    this.fTTimeSegmentField = null;
    this.fTTimeSegmentIndex = null;
    this.tFTimeSegmentField = null;
    this.tFTimeSegmentIndex = null;
    this.lastUpdateTime = null;
    this.FromJSON = function(B) {
        if (!B) {
            return
        }
        if (B.timeSegmentDatasetName) {
            this.timeSegmentDatasetName = B.timeSegmentDatasetName
        }
        if (B.edgeID) {
            this.edgeID = B.edgeID
        }
        if (B.timeSegmentClass) {
            this.timeSegmentClass = B.timeSegmentClass
        }
        if (B.timeSegmentValue) {
            this.timeSegmentValue = B.timeSegmentValue
        }
        if (B.fTTimeSegmentField && B.fTTimeSegmentField.length > 0) {
            this.fTTimeSegmentField = new Array();
            for (var A = 0; A < B.fTTimeSegmentField.length; A++) {
                this.fTTimeSegmentField[A] = B.fTTimeSegmentField[A]
            }
        }
        if (B.fTTimeSegmentIndex && B.fTTimeSegmentIndex.length > 0) {
            this.fTTimeSegmentIndex = new Array();
            for (var A = 0; A < B.fTTimeSegmentIndex.length; A++) {
                this.fTTimeSegmentIndex[A] = B.fTTimeSegmentIndex[A]
            }
        }
        if (B.tFTimeSegmentField && B.tFTimeSegmentField.length > 0) {
            this.tFTimeSegmentField = new Array();
            for (var A = 0; A < B.tFTimeSegmentField.length; A++) {
                this.tFTimeSegmentField[A] = B.tFTimeSegmentField[A]
            }
        }
        if (B.tFTimeSegmentIndex && B.tFTimeSegmentIndex.length > 0) {
            this.tFTimeSegmentIndex = new Array();
            for (var A = 0; A < B.tFTimeSegmentIndex.length; A++) {
                this.tFTimeSegmentIndex[A] = B.tFTimeSegmentIndex[A]
            }
        }
        if (B.lastUpdateTime) {
            this.lastUpdateTime = B.lastUpdateTime
        }
    };
    this.Destroy = function() {
        if (this.timeSegmentDatasetName) {
            this.timeSegmentDatasetName = null
        }
        if (this.edgeID) {
            this.edgeID = null
        }
        if (this.timeSegmentClass) {
            this.timeSegmentClass = null
        }
        if (this.timeSegmentValue) {
            this.timeSegmentValue = null
        }
        if (this.fTTimeSegmentField) {
            while (this.fTTimeSegmentField.length > 0) {
                this.fTTimeSegmentField.pop()
            }
            this.fTTimeSegmentField = null
        }
        if (this.fTTimeSegmentIndex) {
            while (this.fTTimeSegmentIndex.length > 0) {
                this.fTTimeSegmentIndex.pop()
            }
            this.fTTimeSegmentIndex = null
        }
        if (this.tFTimeSegmentField) {
            while (this.tFTimeSegmentField.length > 0) {
                this.tFTimeSegmentField.pop()
            }
            this.tFTimeSegmentField = null
        }
        if (this.tFTimeSegmentIndex) {
            while (this.tFTimeSegmentIndex.length > 0) {
                this.tFTimeSegmentIndex.pop()
            }
            this.tFTimeSegmentIndex = null
        }
        if (this.lastUpdateTime) {
            this.lastUpdateTime = null
        }
    }
};
SuperMap.IS.TimeSegment = function() {
    this.segmentValue = null;
    this.timeSegmentTable = null;
    this.FromJSON = function(B) {
        if (!B) {
            return
        }
        if (B.segmentValue) {
            this.segmentValue = B.segmentValue
        }
        if (B.timeSegmentTable && B.timeSegmentTable.length > 0) {
            this.timeSegmentTable = new Array();
            for (var A = 0; A < B.timeSegmentTable.length; A++) {
                this.timeSegmentTable[A] = B.timeSegmentTable[A]
            }
        }
    };
    this.Destroy = function() {
        if (this.segmentValue) {
            this.segmentValue = null
        }
        if (this.timeSegmentTable) {
            while (this.timeSegmentTable.length > 0) {
                this.timeSegmentTable.pop()
            }
            this.timeSegmentTable = null
        }
    }
};
SuperMap.IS.PathGuide = function() {
    this.count = null;
    this.pathGuideItems = null;
    this.FromJSON = function(B) {
        if (!B) {
            return
        }
        if (B.count) {
            this.count = B.count
        }
        if (B.pathGuideItems && B.pathGuideItems.length > 0) {
            this.pathGuideItems = new Array();
            for (var A = 0; A < B.pathGuideItems.length; A++) {
                this.pathGuideItems[A] = new SuperMap.IS.PathGuideItem();
                this.pathGuideItems[A].FromJSON(B.pathGuideItems[A])
            }
        }
    };
    this.Destroy = function() {
        if (this.count) {
            this.count = null
        }
        if (this.pathGuideItems) {
            while (this.pathGuideItems.length > 0) {
                var A = this.pathGuideItems.pop();
                A.Destroy();
                A = null
            }
            this.pathGuideItems = null
        }
    }
};
SuperMap.IS.PathGuideItem = function() {
    this.id = 0;
    this.name = "";
    this.index = 0;
    this.weight = 0;
    this.length = 0;
    this.distance = 0;
    this.directionType = 0;
    this.turnType = 0;
    this.sideType = 0;
    this.turnAngle = 0;
    this.isEdge = false;
    this.isStop = false;
    this.bounds = null;
    this.FromJSON = function(A) {
        if (!A) {
            return
        }
        if (A.id) {
            this.id = A.id
        }
        if (A.name) {
            this.name = A.name
        }
        if (A.index) {
            this.index = A.index
        }
        if (A.weight) {
            this.weight = A.weight
        }
        if (A.length) {
            this.length = A.length
        }
        if (A.distance) {
            this.distance = A.distance
        }
        if (A.directionType) {
            this.directionType = A.directionType
        }
        if (A.turnType) {
            this.turnType = A.turnType
        }
        if (A.sideType) {
            this.sideType = A.sideType
        }
        if (A.turnAngle) {
            this.turnAngle = A.turnAngle
        }
        if (A.isEdge) {
            this.isEdge = A.isEdge
        }
        if (A.isStop) {
            this.isStop = A.isStop
        }
        if (A.bounds) {
            this.bounds = new SuperMap.IS.MapRect();
            this.bounds.FromJSON(A.bounds)
        }
    };
    this.Destroy = function() {
        if (this.id) {
            this.id = null
        }
        if (this.name) {
            this.name = null
        }
        if (this.index) {
            this.index = null
        }
        if (this.weight) {
            this.weight = null
        }
        if (this.length) {
            this.length = null
        }
        if (this.distance) {
            this.distance = null
        }
        if (this.directionType) {
            this.directionType = null
        }
        if (this.turnType) {
            this.turnType = null
        }
        if (this.sideType) {
            this.sideType = null
        }
        if (this.turnAngle) {
            this.turnAngle = null
        }
        if (this.isEdge) {
            this.isEdge = null
        }
        if (this.isStop) {
            this.isStop = null
        }
        if (this.bounds) {
            this.bounds.Destroy();
            this.bounds = null
        }
    }
};
SuperMap.IS.SwitchParam = function() {
    this.mapName = "";
    this.center = null;
    this.mapScale = 0
};
SuperMap.IS.SpatialQueryMode = {
    extentOverlap: 0,
    commonPoint: 1,
    lineCross: 2,
    commonLine: 3,
    commonPointOrLineCross: 4,
    edgeTouchOrAreaIntersect: 5,
    areaIntersect: 6,
    areaIntersectNoEdgeTouch: 7,
    containedBy: 8,
    containing: 9,
    containedByNoEdgeTouch: 10,
    containingNoEdgeTouch: 11,
    pointInPolygon: 12,
    centroidInPolygon: 13,
    identical: 14
};
SuperMap.IS.ResourceType = {
    symbolLib: 0,
    lineStyleLib: 1,
    fillStyleLib: 2
};
SuperMap.IS.ImageFormat = {
    Defalut: 0,
    png: 1,
    jpg: 2,
    bmp: 3,
    tiff: 4,
    gif: 5
};
SuperMap.IS.LayerType = {
    undefined: 0,
    point: 1,
    line: 3,
    network: 4,
    polygon: 5,
    text: 7,
    image: 81,
    mrSID: 82,
    grid: 83,
    dem: 84,
    ecw: 85,
    wms: 86,
    wcs: 87,
    cad: 149
};
SuperMap.IS.FeatureType = {
    unknown: 0,
    point: 1,
    line: 3,
    polygon: 5,
    text: 7,
    circle: 15,
    image: 81
};
SuperMap.IS.OverlayAnalystType = {
    undefined: 0,
    clip: 1,
    erase: 2,
    identity: 3,
    intersect: 4,
    symmetricDifference: 5,
    union: 6
};
SuperMap.IS.GraduatedMode = {
    constant: 0,
    squareRoot: 1,
    log: 2
};
SuperMap.IS.GraphType = {
    area: 0,
    step: 1,
    line: 2,
    point: 3,
    bar: 4,
    bar3D: 5,
    pie: 6,
    pie3D: 7,
    rose: 8,
    rose3D: 9,
    pyramidBar: 10,
    pyramidPolygon: 11
};
SuperMap.IS.GraphTextFormat = {
    undefined: 0,
    textPercent: 1,
    textValue: 2,
    textCaption: 3,
    textCaptionPercent: 4,
    textCaptionValue: 5
};
SuperMap.IS.BusSolutionOrderMode = {
    byDistance: 0,
    byTime: 1
};
SuperMap.IS.InterpolateOperateMode = {
    idw: 0,
    krig: 1,
    krigWithQuadTree: 2,
    rbf: 3,
    rbfWithQuadTree: 4
};
SuperMap.IS.VariogramMode = {
    spherical: 1,
    exponential: 2
};
SuperMap.IS.AggregationType = {
    sum: 0,
    min: 1,
    max: 2,
    mean: 3,
    median: 4
};
SuperMap.IS.RemapType = {
    unique: 1,
    range: 2
};
SuperMap.IS.PixelFormat = {
    mono: 1,
    fbit: 4,
    Byte: 8,
    tByte: 16,
    rgb: 24,
    rgba: 32,
    longLong: 64,
    Long: 320,
    Float: 3200,
    Double: 6400
};
SuperMap.IS.ResampleType = {
    resampleNeares: 1,
    resampleBilinear: 2,
    resampleCubic: 3
};
SuperMap.IS.SmoothMethod = {
    bSpline: 0,
    polish: 1
};
SuperMap.IS.TextControlMode = {
    none: 0,
    omit: 1,
    wrapText: 2
};
SuperMap.IS.TextBackShape = {
    none: 0,
    rect: 1,
    roundRect: 2,
    ellipse: 3,
    diamond: 4,
    triangle: 5,
    circle: 6,
    symbol: 100
};
SuperMap.IS.LabelLineDirection = {
    alongLine: 0,
    topToRightBottom: 1,
    rightTopToLeftBottom: 2,
    leftBottomToRightTop: 3,
    rightBottomToLeftTop: 4
};
SuperMap.IS.JoinType = {
    innerJoin: 0,
    leftJoin: 1,
    rightJoin: 2,
    fullJoin: 3
};
SuperMap.IS.Units = {
    meter: 10000,
    kilometer: 10000000,
    mile: 16090000,
    yard: 9114,
    degree: 0,
    millimeter: 10,
    centimeter: 100,
    inch: 254,
    decimeter: 1000,
    foot: 3048,
    Default: 1
};
SuperMap.IS.SlopeType = {
    degree: 0,
    radian: 1,
    percent: 2
};
SuperMap.IS.GridCompareMode = {
    equalTo: 0,
    greaterThan: 1,
    greaterThanOrEqualTo: 2,
    lessThan: 3,
    lessThanOrEqualTo: 4
};
SuperMap.IS.NeighborhoodMode = {
    annulus: 0,
    circle: 1,
    rectangle: 2,
    wedge: 3
};
SuperMap.IS.AnalysisUnitsType = {
    cell: 0,
    map: 1
};
SuperMap.IS.StatisticMode = {
    count: 0,
    max: 1,
    min: 2,
    avg: 3,
    sum: 4,
    stdev: 5,
    Var: 6
};
SuperMap.IS.PJCoordSysType = {
    NON_EARTH: 0,
    LONGITUDE_LATITUDE: 1,
    GGRS_1987_GREEK_GRID: 2100,
    ATS_1977_UTM_19N: 2219,
    ATS_1977_UTM_20N: 2220,
    KKJ_FINLAND_1: 2391,
    KKJ_FINLAND_2: 2392,
    KKJ_FINLAND_3: 2393,
    KKJ_FINLAND_4: 2394,
    PULKOVO_1995_GK_4: 20004,
    PULKOVO_1995_GK_5: 20005,
    PULKOVO_1995_GK_6: 20006,
    PULKOVO_1995_GK_7: 20007,
    PULKOVO_1995_GK_8: 20008,
    PULKOVO_1995_GK_9: 20009,
    PULKOVO_1995_GK_10: 20010,
    PULKOVO_1995_GK_11: 20011,
    PULKOVO_1995_GK_12: 20012,
    PULKOVO_1995_GK_13: 20013,
    PULKOVO_1995_GK_14: 20014,
    PULKOVO_1995_GK_15: 20015,
    PULKOVO_1995_GK_16: 20016,
    PULKOVO_1995_GK_17: 20017,
    PULKOVO_1995_GK_18: 20018,
    PULKOVO_1995_GK_19: 20019,
    PULKOVO_1995_GK_20: 20020,
    PULKOVO_1995_GK_21: 20021,
    PULKOVO_1995_GK_22: 20022,
    PULKOVO_1995_GK_23: 20023,
    PULKOVO_1995_GK_24: 20024,
    PULKOVO_1995_GK_25: 20025,
    PULKOVO_1995_GK_26: 20026,
    PULKOVO_1995_GK_27: 20027,
    PULKOVO_1995_GK_28: 20028,
    PULKOVO_1995_GK_29: 20029,
    PULKOVO_1995_GK_30: 20030,
    PULKOVO_1995_GK_31: 20031,
    PULKOVO_1995_GK_32: 20032,
    PULKOVO_1995_GK_4N: 20064,
    PULKOVO_1995_GK_5N: 20065,
    PULKOVO_1995_GK_6N: 20066,
    PULKOVO_1995_GK_7N: 20067,
    PULKOVO_1995_GK_8N: 20068,
    PULKOVO_1995_GK_9N: 20069,
    PULKOVO_1995_GK_10N: 20070,
    PULKOVO_1995_GK_11N: 20071,
    PULKOVO_1995_GK_12N: 20072,
    PULKOVO_1995_GK_13N: 20073,
    PULKOVO_1995_GK_14N: 20074,
    PULKOVO_1995_GK_15N: 20075,
    PULKOVO_1995_GK_16N: 20076,
    PULKOVO_1995_GK_17N: 20077,
    PULKOVO_1995_GK_18N: 20078,
    PULKOVO_1995_GK_19N: 20079,
    PULKOVO_1995_GK_20N: 20080,
    PULKOVO_1995_GK_21N: 20081,
    PULKOVO_1995_GK_22N: 20082,
    PULKOVO_1995_GK_23N: 20083,
    PULKOVO_1995_GK_24N: 20084,
    PULKOVO_1995_GK_25N: 20085,
    PULKOVO_1995_GK_26N: 20086,
    PULKOVO_1995_GK_27N: 20087,
    PULKOVO_1995_GK_28N: 20088,
    PULKOVO_1995_GK_29N: 20089,
    PULKOVO_1995_GK_30N: 20090,
    PULKOVO_1995_GK_31N: 20091,
    PULKOVO_1995_GK_32N: 20092,
    ADINDAN_UTM_37N: 20137,
    ADINDAN_UTM_38N: 20138,
    AGD_1966_AMG_48: 20248,
    AGD_1966_AMG_49: 20249,
    AGD_1966_AMG_50: 20250,
    AGD_1966_AMG_51: 20251,
    AGD_1966_AMG_52: 20252,
    AGD_1966_AMG_53: 20253,
    AGD_1966_AMG_54: 20254,
    AGD_1966_AMG_55: 20255,
    AGD_1966_AMG_56: 20256,
    AGD_1966_AMG_57: 20257,
    AGD_1966_AMG_58: 20258,
    AGD_1984_AMG_48: 20348,
    AGD_1984_AMG_49: 20349,
    AGD_1984_AMG_50: 20350,
    AGD_1984_AMG_51: 20351,
    AGD_1984_AMG_52: 20352,
    AGD_1984_AMG_53: 20353,
    AGD_1984_AMG_54: 20354,
    AGD_1984_AMG_55: 20355,
    AGD_1984_AMG_56: 20356,
    AGD_1984_AMG_57: 20357,
    AGD_1984_AMG_58: 20358,
    AIN_EL_ABD_UTM_37N: 20437,
    AIN_EL_ABD_UTM_38N: 20438,
    AIN_EL_ABD_UTM_39N: 20439,
    AIN_EL_ABD_BAHRAIN_GRID: 20499,
    AFGOOYE_UTM_38N: 20538,
    AFGOOYE_UTM_39N: 20539,
    LISBON_PORTUGUESE_GRID: 20700,
    ARATU_UTM_22S: 20822,
    ARATU_UTM_23S: 20823,
    ARATU_UTM_24S: 20824,
    BATAVIA_UTM_48S: 21148,
    BATAVIA_UTM_49S: 21149,
    BATAVIA_UTM_50S: 21150,
    BEIJING_1954_GK_13: 21413,
    BEIJING_1954_GK_14: 21414,
    BEIJING_1954_GK_15: 21415,
    BEIJING_1954_GK_16: 21416,
    BEIJING_1954_GK_17: 21417,
    BEIJING_1954_GK_18: 21418,
    BEIJING_1954_GK_19: 21419,
    BEIJING_1954_GK_20: 21420,
    BEIJING_1954_GK_21: 21421,
    BEIJING_1954_GK_22: 21422,
    BEIJING_1954_GK_23: 21423,
    BEIJING_1954_GK_13N: 21473,
    BEIJING_1954_GK_14N: 21474,
    BEIJING_1954_GK_15N: 21475,
    BEIJING_1954_GK_16N: 21476,
    BEIJING_1954_GK_17N: 21477,
    BEIJING_1954_GK_18N: 21478,
    BEIJING_1954_GK_19N: 21479,
    BEIJING_1954_GK_20N: 21480,
    BEIJING_1954_GK_21N: 21481,
    BEIJING_1954_GK_22N: 21482,
    BEIJING_1954_GK_23N: 21483,
    BELGE_LAMBERT_1950: 21500,
    BOGOTA_UTM_17N: 21817,
    BOGOTA_UTM_18N: 21818,
    BOGOTA_COLOMBIA_WEST: 21891,
    BOGOTA_COLOMBIA_BOGOTA: 21892,
    BOGOTA_COLOMBIA_E_CENTRAL: 21893,
    BOGOTA_COLOMBIA_EAST: 21894,
    CAMACUPA_UTM_32S: 22032,
    CAMACUPA_UTM_33S: 22033,
    C_INCHAUSARGENTINA_1: 22191,
    C_INCHAUSARGENTINA_2: 22192,
    C_INCHAUSARGENTINA_3: 22193,
    C_INCHAUSARGENTINA_4: 22194,
    C_INCHAUSARGENTINA_5: 22195,
    C_INCHAUSARGENTINA_6: 22196,
    C_INCHAUSARGENTINA_7: 22197,
    CARTHAGE_UTM_32N: 22332,
    CARTHAGE_NORD_TUNISIE: 22391,
    CARTHAGE_SUD_TUNISIE: 22392,
    CORREGO_ALEGRE_UTM_23S: 22523,
    CORREGO_ALEGRE_UTM_24S: 22524,
    DOUALA_UTM_32N: 22832,
    EGYPT_RED_BELT: 22992,
    EGYPT_PURPLE_BELT: 22993,
    EGYPT_EXT_PURPLE_BELT: 22994,
    ED_1950_UTM_28N: 23028,
    ED_1950_UTM_29N: 23029,
    ED_1950_UTM_30N: 23030,
    ED_1950_UTM_31N: 23031,
    ED_1950_UTM_32N: 23032,
    ED_1950_UTM_33N: 23033,
    ED_1950_UTM_34N: 23034,
    ED_1950_UTM_35N: 23035,
    ED_1950_UTM_36N: 23036,
    ED_1950_UTM_37N: 23037,
    ED_1950_UTM_38N: 23038,
    FAHUD_UTM_39N: 23239,
    FAHUD_UTM_40N: 23240,
    GAROUA_UTM_33N: 23433,
    ID_1974_UTM_46N: 23846,
    ID_1974_UTM_47N: 23847,
    ID_1974_UTM_48N: 23848,
    ID_1974_UTM_49N: 23849,
    ID_1974_UTM_50N: 23850,
    ID_1974_UTM_51N: 23851,
    ID_1974_UTM_52N: 23852,
    ID_1974_UTM_53N: 23853,
    ID_1974_UTM_46S: 23886,
    ID_1974_UTM_47S: 23887,
    ID_1974_UTM_48S: 23888,
    ID_1974_UTM_49S: 23889,
    ID_1974_UTM_50S: 23890,
    ID_1974_UTM_51S: 23891,
    ID_1974_UTM_52S: 23892,
    ID_1974_UTM_53S: 23893,
    ID_1974_UTM_54S: 23894,
    INDIAN_1954_UTM_47N: 23947,
    INDIAN_1954_UTM_48N: 23948,
    INDIAN_1975_UTM_47N: 24047,
    INDIAN_1975_UTM_48N: 24048,
    JAMAICA_1875_OLD_GRID: 24100,
    JAD_1969_JAMAICA_GRID: 24200,
    KALIANPUR_INDIA_0: 24370,
    KALIANPUR_INDIA_I: 24371,
    KALIANPUR_INDIA_IIA: 24372,
    KALIANPUR_INDIA_IIIA: 24373,
    KALIANPUR_INDIA_IVA: 24374,
    KALIANPUR_INDIA_IIB: 24382,
    KALIANPUR_INDIA_IIIB: 24383,
    KALIANPUR_INDIA_IVB: 24384,
    KERTAU_UTM_47N: 24547,
    KERTAU_UTM_48N: 24548,
    KOC_LAMBERT: 24600,
    LA_CANOA_UTM_20N: 24720,
    LA_CANOA_UTM_21N: 24721,
    PSAD_1956_UTM_18N: 24818,
    PSAD_1956_UTM_19N: 24819,
    PSAD_1956_UTM_20N: 24820,
    PSAD_1956_UTM_21N: 24821,
    PSAD_1956_UTM_17S: 24877,
    PSAD_1956_UTM_18S: 24878,
    PSAD_1956_UTM_19S: 24879,
    PSAD_1956_UTM_20S: 24880,
    PSAD_1956_PERU_WEST: 24891,
    PSAD_1956_PERU_CENTRAL: 24892,
    PSAD_1956_PERU_EAST: 24893,
    LEIGON_GHANA_GRID: 25000,
    LOME_UTM_31N: 25231,
    LUZON_PHILIPPINES_I: 25391,
    LUZON_PHILIPPINES_II: 25392,
    LUZON_PHILIPPINES_III: 25393,
    LUZON_PHILIPPINES_IV: 25394,
    LUZON_PHILIPPINES_V: 25395,
    ETRS_1989_UTM_28N: 25828,
    ETRS_1989_UTM_29N: 25829,
    ETRS_1989_UTM_30N: 25830,
    ETRS_1989_UTM_31N: 25831,
    ETRS_1989_UTM_32N: 25832,
    ETRS_1989_UTM_33N: 25833,
    ETRS_1989_UTM_34N: 25834,
    ETRS_1989_UTM_35N: 25835,
    ETRS_1989_UTM_36N: 25836,
    ETRS_1989_UTM_37N: 25837,
    ETRS_1989_UTM_38N: 25838,
    MALONGO_1987_UTM_32S: 25932,
    MERCHICH_NORD_MAROC: 26191,
    MERCHICH_SUD_MAROC: 26192,
    MERCHICH_SAHARA: 26193,
    MASSAWA_UTM_37N: 26237,
    MINNA_UTM_31N: 26331,
    MINNA_UTM_32N: 26332,
    MINNA_NIGERIA_WEST_BELT: 26391,
    MINNA_NIGERIA_MID_BELT: 26392,
    MINNA_NIGERIA_EAST_BELT: 26393,
    MHAST_UTM_32S: 26432,
    MONTE_MARIO_ROME_ITALY_1: 26591,
    MONTE_MARIO_ROME_ITALY_2: 26592,
    MPORALOKO_UTM_32N: 26632,
    MPORALOKO_UTM_32S: 26692,
    NAD_1927_UTM_3N: 26703,
    NAD_1927_UTM_4N: 26704,
    NAD_1927_UTM_5N: 26705,
    NAD_1927_UTM_6N: 26706,
    NAD_1927_UTM_7N: 26707,
    NAD_1927_UTM_8N: 26708,
    NAD_1927_UTM_9N: 26709,
    NAD_1927_UTM_10N: 26710,
    NAD_1927_UTM_11N: 26711,
    NAD_1927_UTM_12N: 26712,
    NAD_1927_UTM_13N: 26713,
    NAD_1927_UTM_14N: 26714,
    NAD_1927_UTM_15N: 26715,
    NAD_1927_UTM_16N: 26716,
    NAD_1927_UTM_17N: 26717,
    NAD_1927_UTM_18N: 26718,
    NAD_1927_UTM_19N: 26719,
    NAD_1927_UTM_20N: 26720,
    NAD_1927_UTM_21N: 26721,
    NAD_1927_UTM_22N: 26722,
    NAD_1927_AL_E: 26729,
    NAD_1927_AL_W: 26730,
    NAD_1927_AK_1: 26731,
    NAD_1927_AK_2: 26732,
    NAD_1927_AK_3: 26733,
    NAD_1927_AK_4: 26734,
    NAD_1927_AK_5: 26735,
    NAD_1927_AK_6: 26736,
    NAD_1927_AK_7: 26737,
    NAD_1927_AK_8: 26738,
    NAD_1927_AK_9: 26739,
    NAD_1927_AK_10: 26740,
    NAD_1927_CA_I: 26741,
    NAD_1927_CA_II: 26742,
    NAD_1927_CA_III: 26743,
    NAD_1927_CA_IV: 26744,
    NAD_1927_CA_V: 26745,
    NAD_1927_CA_VI: 26746,
    NAD_1927_CA_VII: 26747,
    NAD_1927_AZ_E: 26748,
    NAD_1927_AZ_C: 26749,
    NAD_1927_AZ_W: 26750,
    NAD_1927_AR_N: 26751,
    NAD_1927_AR_S: 26752,
    NAD_1927_CO_N: 26753,
    NAD_1927_CO_C: 26754,
    NAD_1927_CO_S: 26755,
    NAD_1927_CT: 26756,
    NAD_1927_DE: 26757,
    NAD_1927_FL_E: 26758,
    NAD_1927_FL_W: 26759,
    NAD_1927_FL_N: 26760,
    NAD_1927_HI_1: 26761,
    NAD_1927_HI_2: 26762,
    NAD_1927_HI_3: 26763,
    NAD_1927_HI_4: 26764,
    NAD_1927_HI_5: 26765,
    NAD_1927_GA_E: 26766,
    NAD_1927_GA_W: 26767,
    NAD_1927_ID_E: 26768,
    NAD_1927_ID_C: 26769,
    NAD_1927_ID_W: 26770,
    NAD_1927_IL_E: 26771,
    NAD_1927_IL_W: 26772,
    NAD_1927_IN_E: 26773,
    NAD_1927_IN_W: 26774,
    NAD_1927_IA_N: 26775,
    NAD_1927_IA_S: 26776,
    NAD_1927_KS_N: 26777,
    NAD_1927_KS_S: 26778,
    NAD_1927_KY_N: 26779,
    NAD_1927_KY_S: 26780,
    NAD_1927_LA_N: 26781,
    NAD_1927_LA_S: 26782,
    NAD_1927_ME_E: 26783,
    NAD_1927_ME_W: 26784,
    NAD_1927_MD: 26785,
    NAD_1927_MA_M: 26786,
    NAD_1927_MA_I: 26787,
    NAD_1927_MI_N: 26788,
    NAD_1927_MI_C: 26789,
    NAD_1927_MI_S: 26790,
    NAD_1927_MN_N: 26791,
    NAD_1927_MN_C: 26792,
    NAD_1927_MN_S: 26793,
    NAD_1927_MS_E: 26794,
    NAD_1927_MS_W: 26795,
    NAD_1927_MO_E: 26796,
    NAD_1927_MO_C: 26797,
    NAD_1927_MO_W: 26798,
    NAD_1983_UTM_3N: 26903,
    NAD_1983_UTM_4N: 26904,
    NAD_1983_UTM_5N: 26905,
    NAD_1983_UTM_6N: 26906,
    NAD_1983_UTM_7N: 26907,
    NAD_1983_UTM_8N: 26908,
    NAD_1983_UTM_9N: 26909,
    NAD_1983_UTM_10N: 26910,
    NAD_1983_UTM_11N: 26911,
    NAD_1983_UTM_12N: 26912,
    NAD_1983_UTM_13N: 26913,
    NAD_1983_UTM_14N: 26914,
    NAD_1983_UTM_15N: 26915,
    NAD_1983_UTM_16N: 26916,
    NAD_1983_UTM_17N: 26917,
    NAD_1983_UTM_18N: 26918,
    NAD_1983_UTM_19N: 26919,
    NAD_1983_UTM_20N: 26920,
    NAD_1983_UTM_21N: 26921,
    NAD_1983_UTM_22N: 26922,
    NAD_1983_UTM_23N: 26923,
    NAD_1983_AL_E: 26929,
    NAD_1983_AL_W: 26930,
    NAD_1983_AK_1: 26931,
    NAD_1983_AK_2: 26932,
    NAD_1983_AK_3: 26933,
    NAD_1983_AK_4: 26934,
    NAD_1983_AK_5: 26935,
    NAD_1983_AK_6: 26936,
    NAD_1983_AK_7: 26937,
    NAD_1983_AK_8: 26938,
    NAD_1983_AK_9: 26939,
    NAD_1983_AK_10: 26940,
    NAD_1983_CA_I: 26941,
    NAD_1983_CA_II: 26942,
    NAD_1983_CA_III: 26943,
    NAD_1983_CA_IV: 26944,
    NAD_1983_CA_V: 26945,
    NAD_1983_CA_VI: 26946,
    NAD_1983_AZ_E: 26948,
    NAD_1983_AZ_C: 26949,
    NAD_1983_AZ_W: 26950,
    NAD_1983_AR_N: 26951,
    NAD_1983_AR_S: 26952,
    NAD_1983_CO_N: 26953,
    NAD_1983_CO_C: 26954,
    NAD_1983_CO_S: 26955,
    NAD_1983_CT: 26956,
    NAD_1983_DE: 26957,
    NAD_1983_FL_E: 26958,
    NAD_1983_FL_W: 26959,
    NAD_1983_FL_N: 26960,
    NAD_1983_HI_1: 26961,
    NAD_1983_HI_2: 26962,
    NAD_1983_HI_3: 26963,
    NAD_1983_HI_4: 26964,
    NAD_1983_HI_5: 26965,
    NAD_1983_GA_E: 26966,
    NAD_1983_GA_W: 26967,
    NAD_1983_ID_E: 26968,
    NAD_1983_ID_C: 26969,
    NAD_1983_ID_W: 26970,
    NAD_1983_IL_E: 26971,
    NAD_1983_IL_W: 26972,
    NAD_1983_IN_E: 26973,
    NAD_1983_IN_W: 26974,
    NAD_1983_IA_N: 26975,
    NAD_1983_IA_S: 26976,
    NAD_1983_KS_N: 26977,
    NAD_1983_KS_S: 26978,
    NAD_1983_KY_N: 26979,
    NAD_1983_KY_S: 26980,
    NAD_1983_LA_N: 26981,
    NAD_1983_LA_S: 26982,
    NAD_1983_ME_E: 26983,
    NAD_1983_ME_W: 26984,
    NAD_1983_MD: 26985,
    NAD_1983_MA_M: 26986,
    NAD_1983_MA_I: 26987,
    NAD_1983_MI_N: 26988,
    NAD_1983_MI_C: 26989,
    NAD_1983_MI_S: 26990,
    NAD_1983_MN_N: 26991,
    NAD_1983_MN_C: 26992,
    NAD_1983_MN_S: 26993,
    NAD_1983_MS_E: 26994,
    NAD_1983_MS_W: 26995,
    NAD_1983_MO_E: 26996,
    NAD_1983_MO_C: 26997,
    NAD_1983_MO_W: 26998,
    NAHRWAN_1967_UTM_38N: 27038,
    NAHRWAN_1967_UTM_39N: 27039,
    NAHRWAN_1967_UTM_40N: 27040,
    NAPARIMA_1972_UTM_20N: 27120,
    NZGD_1949_NORTH_ISLAND: 27291,
    NZGD_1949_SOUTH_ISLAND: 27292,
    DATUM_73_UTM_ZONE_29N: 27429,
    ATF_NORD_DE_GUERRE: 27500,
    NTF_FRANCE_I: 27581,
    NTF_FRANCE_II: 27582,
    NTF_FRANCE_III: 27583,
    NTF_FRANCE_IV: 27584,
    NTF_NORD_FRANCE: 27591,
    NTF_CENTRE_FRANCE: 27592,
    NTF_SUD_FRANCE: 27593,
    NTF_CORSE: 27594,
    OSGB_1936_BRITISH_GRID: 27700,
    POINTE_NOIRE_UTM_32S: 28232,
    GDA_1994_MGA_48: 28348,
    GDA_1994_MGA_49: 28349,
    GDA_1994_MGA_50: 28350,
    GDA_1994_MGA_51: 28351,
    GDA_1994_MGA_52: 28352,
    GDA_1994_MGA_53: 28353,
    GDA_1994_MGA_54: 28354,
    GDA_1994_MGA_55: 28355,
    GDA_1994_MGA_56: 28356,
    GDA_1994_MGA_57: 28357,
    GDA_1994_MGA_58: 28358,
    PULKOVO_1942_GK_4: 28404,
    PULKOVO_1942_GK_5: 28405,
    PULKOVO_1942_GK_6: 28406,
    PULKOVO_1942_GK_7: 28407,
    PULKOVO_1942_GK_8: 28408,
    PULKOVO_1942_GK_9: 28409,
    PULKOVO_1942_GK_10: 28410,
    PULKOVO_1942_GK_11: 28411,
    PULKOVO_1942_GK_12: 28412,
    PULKOVO_1942_GK_13: 28413,
    PULKOVO_1942_GK_14: 28414,
    PULKOVO_1942_GK_15: 28415,
    PULKOVO_1942_GK_16: 28416,
    PULKOVO_1942_GK_17: 28417,
    PULKOVO_1942_GK_18: 28418,
    PULKOVO_1942_GK_19: 28419,
    PULKOVO_1942_GK_20: 28420,
    PULKOVO_1942_GK_21: 28421,
    PULKOVO_1942_GK_22: 28422,
    PULKOVO_1942_GK_23: 28423,
    PULKOVO_1942_GK_24: 28424,
    PULKOVO_1942_GK_25: 28425,
    PULKOVO_1942_GK_26: 28426,
    PULKOVO_1942_GK_27: 28427,
    PULKOVO_1942_GK_28: 28428,
    PULKOVO_1942_GK_29: 28429,
    PULKOVO_1942_GK_30: 28430,
    PULKOVO_1942_GK_31: 28431,
    PULKOVO_1942_GK_32: 28432,
    PULKOVO_1942_GK_4N: 28464,
    PULKOVO_1942_GK_5N: 28465,
    PULKOVO_1942_GK_6N: 28466,
    PULKOVO_1942_GK_7N: 28467,
    PULKOVO_1942_GK_8N: 28468,
    PULKOVO_1942_GK_9N: 28469,
    PULKOVO_1942_GK_10N: 28470,
    PULKOVO_1942_GK_11N: 28471,
    PULKOVO_1942_GK_12N: 28472,
    PULKOVO_1942_GK_13N: 28473,
    PULKOVO_1942_GK_14N: 28474,
    PULKOVO_1942_GK_15N: 28475,
    PULKOVO_1942_GK_16N: 28476,
    PULKOVO_1942_GK_17N: 28477,
    PULKOVO_1942_GK_18N: 28478,
    PULKOVO_1942_GK_19N: 28479,
    PULKOVO_1942_GK_20N: 28480,
    PULKOVO_1942_GK_21N: 28481,
    PULKOVO_1942_GK_22N: 28482,
    PULKOVO_1942_GK_23N: 28483,
    PULKOVO_1942_GK_24N: 28484,
    PULKOVO_1942_GK_25N: 28485,
    PULKOVO_1942_GK_26N: 28486,
    PULKOVO_1942_GK_27N: 28487,
    PULKOVO_1942_GK_28N: 28488,
    PULKOVO_1942_GK_29N: 28489,
    PULKOVO_1942_GK_30N: 28490,
    PULKOVO_1942_GK_31N: 28491,
    PULKOVO_1942_GK_32N: 28492,
    QATAR_GRID: 28600,
    SAD_1969_UTM_18N: 29118,
    SAD_1969_UTM_19N: 29119,
    SAD_1969_UTM_20N: 29120,
    SAD_1969_UTM_21N: 29121,
    SAD_1969_UTM_22N: 29122,
    SAD_1969_UTM_17S: 29177,
    SAD_1969_UTM_18S: 29178,
    SAD_1969_UTM_19S: 29179,
    SAD_1969_UTM_20S: 29180,
    SAD_1969_UTM_21S: 29181,
    SAD_1969_UTM_22S: 29182,
    SAD_1969_UTM_23S: 29183,
    SAD_1969_UTM_24S: 29184,
    SAD_1969_UTM_25S: 29185,
    SAPPER_HILL_UTM_20S: 29220,
    SAPPER_HILL_UTM_21S: 29221,
    SCHWARZECK_UTM_33S: 29333,
    SUDAN_UTM_35N: 29635,
    SUDAN_UTM_36N: 29636,
    TANANARIVE_UTM_38S: 29738,
    TANANARIVE_UTM_39S: 29739,
    TIMBALAI_1948_UTM_49N: 29849,
    TIMBALAI_1948_UTM_50N: 29850,
    TM65_IRISH_GRID: 29900,
    TC_1948_UTM_39N: 30339,
    TC_1948_UTM_40N: 30340,
    VOIROL_N_ALGERIE_ANCIENNE: 30491,
    VOIROL_S_ALGERIE_ANCIENNE: 30492,
    VOIROL_UNIFIE_N_ALGERIE: 30591,
    VOIROL_UNIFIE_S_ALGERIE: 30592,
    NORD_SAHARA_UTM_29N: 30729,
    NORD_SAHARA_UTM_30N: 30730,
    NORD_SAHARA_UTM_31N: 30731,
    NORD_SAHARA_UTM_32N: 30732,
    RT38_STOCKHOLM_SWEDISH_GRID: 30800,
    YOFF_1972_UTM_28N: 31028,
    ZANDERIJ_1972_UTM_21N: 31121,
    MGI_FERRO_AUSTRIA_WEST: 31291,
    MGI_FERRO_AUSTRIA_CENTRAL: 31292,
    MGI_FERRO_AUSTRIA_EAST: 31293,
    DHDN_GERMANY_1: 31491,
    DHDN_GERMANY_2: 31492,
    DHDN_GERMANY_3: 31493,
    DHDN_GERMANY_4: 31494,
    DHDN_GERMANY_5: 31495,
    DEALUL_PISCULUI_1933_STEREO_33: 31600,
    DEALUL_PISCULUI_1970_STEREO_70: 31700,
    NGN_UTM_38N: 31838,
    NGN_UTM_39N: 31839,
    KUDAMS_KTM: 31900,
    NAD_1927_MT_N: 32001,
    NAD_1927_MT_C: 32002,
    NAD_1927_MT_S: 32003,
    NAD_1927_NE_N: 32005,
    NAD_1927_NE_S: 32006,
    NAD_1927_NV_E: 32007,
    NAD_1927_NV_C: 32008,
    NAD_1927_NV_W: 32009,
    NAD_1927_NH: 32010,
    NAD_1927_NJ: 32011,
    NAD_1927_NM_E: 32012,
    NAD_1927_NM_C: 32013,
    NAD_1927_NM_W: 32014,
    NAD_1927_NY_E: 32015,
    NAD_1927_NY_C: 32016,
    NAD_1927_NY_W: 32017,
    NAD_1927_NY_LI: 32018,
    NAD_1927_NC: 32019,
    NAD_1927_ND_N: 32020,
    NAD_1927_ND_S: 32021,
    NAD_1927_OH_N: 32022,
    NAD_1927_OH_S: 32023,
    NAD_1927_OK_N: 32024,
    NAD_1927_OK_S: 32025,
    NAD_1927_OR_N: 32026,
    NAD_1927_OR_S: 32027,
    NAD_1927_PA_N: 32028,
    NAD_1927_PA_S: 32029,
    NAD_1927_RI: 32030,
    NAD_1927_SC_N: 32031,
    NAD_1927_SC_S: 32033,
    NAD_1927_SD_N: 32034,
    NAD_1927_SD_S: 32035,
    NAD_1927_TN: 32036,
    NAD_1927_TX_N: 32037,
    NAD_1927_TX_NC: 32038,
    NAD_1927_TX_C: 32039,
    NAD_1927_TX_SC: 32040,
    NAD_1927_TX_S: 32041,
    NAD_1927_UT_N: 32042,
    NAD_1927_UT_C: 32043,
    NAD_1927_UT_S: 32044,
    NAD_1927_VT: 32045,
    NAD_1927_VA_N: 32046,
    NAD_1927_VA_S: 32047,
    NAD_1927_WA_N: 32048,
    NAD_1927_WA_S: 32049,
    NAD_1927_WV_N: 32050,
    NAD_1927_WV_S: 32051,
    NAD_1927_WI_N: 32052,
    NAD_1927_WI_C: 32053,
    NAD_1927_WI_S: 32054,
    NAD_1927_WY_E: 32055,
    NAD_1927_WY_EC: 32056,
    NAD_1927_WY_WC: 32057,
    NAD_1927_WY_W: 32058,
    NAD_1927_PR: 32059,
    NAD_1927_VI: 32060,
    NAD_1927_BLM_14N: 32074,
    NAD_1927_BLM_15N: 32075,
    NAD_1927_BLM_16N: 32076,
    NAD_1927_BLM_17N: 32077,
    NAD_1983_MT: 32100,
    NAD_1983_NE: 32104,
    NAD_1983_NV_E: 32107,
    NAD_1983_NV_C: 32108,
    NAD_1983_NV_W: 32109,
    NAD_1983_NH: 32110,
    NAD_1983_NJ: 32111,
    NAD_1983_NM_E: 32112,
    NAD_1983_NM_C: 32113,
    NAD_1983_NM_W: 32114,
    NAD_1983_NY_E: 32115,
    NAD_1983_NY_C: 32116,
    NAD_1983_NY_W: 32117,
    NAD_1983_NY_LI: 32118,
    NAD_1983_NC: 32119,
    NAD_1983_ND_N: 32120,
    NAD_1983_ND_S: 32121,
    NAD_1983_OH_N: 32122,
    NAD_1983_OH_S: 32123,
    NAD_1983_OK_N: 32124,
    NAD_1983_OK_S: 32125,
    NAD_1983_OR_N: 32126,
    NAD_1983_OR_S: 32127,
    NAD_1983_PA_N: 32128,
    NAD_1983_PA_S: 32129,
    NAD_1983_RI: 32130,
    NAD_1983_SC: 32133,
    NAD_1983_SD_N: 32134,
    NAD_1983_SD_S: 32135,
    NAD_1983_TN: 32136,
    NAD_1983_TX_N: 32137,
    NAD_1983_TX_NC: 32138,
    NAD_1983_TX_C: 32139,
    NAD_1983_TX_SC: 32140,
    NAD_1983_TX_S: 32141,
    NAD_1983_UT_N: 32142,
    NAD_1983_UT_C: 32143,
    NAD_1983_UT_S: 32144,
    NAD_1983_VT: 32145,
    NAD_1983_VA_N: 32146,
    NAD_1983_VA_S: 32147,
    NAD_1983_WA_N: 32148,
    NAD_1983_WA_S: 32149,
    NAD_1983_WV_N: 32150,
    NAD_1983_WV_S: 32151,
    NAD_1983_WI_N: 32152,
    NAD_1983_WI_C: 32153,
    NAD_1983_WI_S: 32154,
    NAD_1983_WY_E: 32155,
    NAD_1983_WY_EC: 32156,
    NAD_1983_WY_WC: 32157,
    NAD_1983_WY_W: 32158,
    NAD_1983_PR_VI: 32161,
    WGS_1972_UTM_1N: 32201,
    WGS_1972_UTM_2N: 32202,
    WGS_1972_UTM_3N: 32203,
    WGS_1972_UTM_4N: 32204,
    WGS_1972_UTM_5N: 32205,
    WGS_1972_UTM_6N: 32206,
    WGS_1972_UTM_7N: 32207,
    WGS_1972_UTM_8N: 32208,
    WGS_1972_UTM_9N: 32209,
    WGS_1972_UTM_10N: 32210,
    WGS_1972_UTM_11N: 32211,
    WGS_1972_UTM_12N: 32212,
    WGS_1972_UTM_13N: 32213,
    WGS_1972_UTM_14N: 32214,
    WGS_1972_UTM_15N: 32215,
    WGS_1972_UTM_16N: 32216,
    WGS_1972_UTM_17N: 32217,
    WGS_1972_UTM_18N: 32218,
    WGS_1972_UTM_19N: 32219,
    WGS_1972_UTM_20N: 32220,
    WGS_1972_UTM_21N: 32221,
    WGS_1972_UTM_22N: 32222,
    WGS_1972_UTM_23N: 32223,
    WGS_1972_UTM_24N: 32224,
    WGS_1972_UTM_25N: 32225,
    WGS_1972_UTM_26N: 32226,
    WGS_1972_UTM_27N: 32227,
    WGS_1972_UTM_28N: 32228,
    WGS_1972_UTM_29N: 32229,
    WGS_1972_UTM_30N: 32230,
    WGS_1972_UTM_31N: 32231,
    WGS_1972_UTM_32N: 32232,
    WGS_1972_UTM_33N: 32233,
    WGS_1972_UTM_34N: 32234,
    WGS_1972_UTM_35N: 32235,
    WGS_1972_UTM_36N: 32236,
    WGS_1972_UTM_37N: 32237,
    WGS_1972_UTM_38N: 32238,
    WGS_1972_UTM_39N: 32239,
    WGS_1972_UTM_40N: 32240,
    WGS_1972_UTM_41N: 32241,
    WGS_1972_UTM_42N: 32242,
    WGS_1972_UTM_43N: 32243,
    WGS_1972_UTM_44N: 32244,
    WGS_1972_UTM_45N: 32245,
    WGS_1972_UTM_46N: 32246,
    WGS_1972_UTM_47N: 32247,
    WGS_1972_UTM_48N: 32248,
    WGS_1972_UTM_49N: 32249,
    WGS_1972_UTM_50N: 32250,
    WGS_1972_UTM_51N: 32251,
    WGS_1972_UTM_52N: 32252,
    WGS_1972_UTM_53N: 32253,
    WGS_1972_UTM_54N: 32254,
    WGS_1972_UTM_55N: 32255,
    WGS_1972_UTM_56N: 32256,
    WGS_1972_UTM_57N: 32257,
    WGS_1972_UTM_58N: 32258,
    WGS_1972_UTM_59N: 32259,
    WGS_1972_UTM_60N: 32260,
    WGS_1972_UTM_1S: 32301,
    WGS_1972_UTM_2S: 32302,
    WGS_1972_UTM_3S: 32303,
    WGS_1972_UTM_4S: 32304,
    WGS_1972_UTM_5S: 32305,
    WGS_1972_UTM_6S: 32306,
    WGS_1972_UTM_7S: 32307,
    WGS_1972_UTM_8S: 32308,
    WGS_1972_UTM_9S: 32309,
    WGS_1972_UTM_10S: 32310,
    WGS_1972_UTM_11S: 32311,
    WGS_1972_UTM_12S: 32312,
    WGS_1972_UTM_13S: 32313,
    WGS_1972_UTM_14S: 32314,
    WGS_1972_UTM_15S: 32315,
    WGS_1972_UTM_16S: 32316,
    WGS_1972_UTM_17S: 32317,
    WGS_1972_UTM_18S: 32318,
    WGS_1972_UTM_19S: 32319,
    WGS_1972_UTM_20S: 32320,
    WGS_1972_UTM_21S: 32321,
    WGS_1972_UTM_22S: 32322,
    WGS_1972_UTM_23S: 32323,
    WGS_1972_UTM_24S: 32324,
    WGS_1972_UTM_25S: 32325,
    WGS_1972_UTM_26S: 32326,
    WGS_1972_UTM_27S: 32327,
    WGS_1972_UTM_28S: 32328,
    WGS_1972_UTM_29S: 32329,
    WGS_1972_UTM_30S: 32330,
    WGS_1972_UTM_31S: 32331,
    WGS_1972_UTM_32S: 32332,
    WGS_1972_UTM_33S: 32333,
    WGS_1972_UTM_34S: 32334,
    WGS_1972_UTM_35S: 32335,
    WGS_1972_UTM_36S: 32336,
    WGS_1972_UTM_37S: 32337,
    WGS_1972_UTM_38S: 32338,
    WGS_1972_UTM_39S: 32339,
    WGS_1972_UTM_40S: 32340,
    WGS_1972_UTM_41S: 32341,
    WGS_1972_UTM_42S: 32342,
    WGS_1972_UTM_43S: 32343,
    WGS_1972_UTM_44S: 32344,
    WGS_1972_UTM_45S: 32345,
    WGS_1972_UTM_46S: 32346,
    WGS_1972_UTM_47S: 32347,
    WGS_1972_UTM_48S: 32348,
    WGS_1972_UTM_49S: 32349,
    WGS_1972_UTM_50S: 32350,
    WGS_1972_UTM_51S: 32351,
    WGS_1972_UTM_52S: 32352,
    WGS_1972_UTM_53S: 32353,
    WGS_1972_UTM_54S: 32354,
    WGS_1972_UTM_55S: 32355,
    WGS_1972_UTM_56S: 32356,
    WGS_1972_UTM_57S: 32357,
    WGS_1972_UTM_58S: 32358,
    WGS_1972_UTM_59S: 32359,
    WGS_1972_UTM_60S: 32360,
    WGS_1984_UTM_1N: 32601,
    WGS_1984_UTM_2N: 32602,
    WGS_1984_UTM_3N: 32603,
    WGS_1984_UTM_4N: 32604,
    WGS_1984_UTM_5N: 32605,
    WGS_1984_UTM_6N: 32606,
    WGS_1984_UTM_7N: 32607,
    WGS_1984_UTM_8N: 32608,
    WGS_1984_UTM_9N: 32609,
    WGS_1984_UTM_10N: 32610,
    WGS_1984_UTM_11N: 32611,
    WGS_1984_UTM_12N: 32612,
    WGS_1984_UTM_13N: 32613,
    WGS_1984_UTM_14N: 32614,
    WGS_1984_UTM_15N: 32615,
    WGS_1984_UTM_16N: 32616,
    WGS_1984_UTM_17N: 32617,
    WGS_1984_UTM_18N: 32618,
    WGS_1984_UTM_19N: 32619,
    WGS_1984_UTM_20N: 32620,
    WGS_1984_UTM_21N: 32621,
    WGS_1984_UTM_22N: 32622,
    WGS_1984_UTM_23N: 32623,
    WGS_1984_UTM_24N: 32624,
    WGS_1984_UTM_25N: 32625,
    WGS_1984_UTM_26N: 32626,
    WGS_1984_UTM_27N: 32627,
    WGS_1984_UTM_28N: 32628,
    WGS_1984_UTM_29N: 32629,
    WGS_1984_UTM_30N: 32630,
    WGS_1984_UTM_31N: 32631,
    WGS_1984_UTM_32N: 32632,
    WGS_1984_UTM_33N: 32633,
    WGS_1984_UTM_34N: 32634,
    WGS_1984_UTM_35N: 32635,
    WGS_1984_UTM_36N: 32636,
    WGS_1984_UTM_37N: 32637,
    WGS_1984_UTM_38N: 32638,
    WGS_1984_UTM_39N: 32639,
    WGS_1984_UTM_40N: 32640,
    WGS_1984_UTM_41N: 32641,
    WGS_1984_UTM_42N: 32642,
    WGS_1984_UTM_43N: 32643,
    WGS_1984_UTM_44N: 32644,
    WGS_1984_UTM_45N: 32645,
    WGS_1984_UTM_46N: 32646,
    WGS_1984_UTM_47N: 32647,
    WGS_1984_UTM_48N: 32648,
    WGS_1984_UTM_49N: 32649,
    WGS_1984_UTM_50N: 32650,
    WGS_1984_UTM_51N: 32651,
    WGS_1984_UTM_52N: 32652,
    WGS_1984_UTM_53N: 32653,
    WGS_1984_UTM_54N: 32654,
    WGS_1984_UTM_55N: 32655,
    WGS_1984_UTM_56N: 32656,
    WGS_1984_UTM_57N: 32657,
    WGS_1984_UTM_58N: 32658,
    WGS_1984_UTM_59N: 32659,
    WGS_1984_UTM_60N: 32660,
    WGS_1984_UTM_1S: 32701,
    WGS_1984_UTM_2S: 32702,
    WGS_1984_UTM_3S: 32703,
    WGS_1984_UTM_4S: 32704,
    WGS_1984_UTM_5S: 32705,
    WGS_1984_UTM_6S: 32706,
    WGS_1984_UTM_7S: 32707,
    WGS_1984_UTM_8S: 32708,
    WGS_1984_UTM_9S: 32709,
    WGS_1984_UTM_10S: 32710,
    WGS_1984_UTM_11S: 32711,
    WGS_1984_UTM_12S: 32712,
    WGS_1984_UTM_13S: 32713,
    WGS_1984_UTM_14S: 32714,
    WGS_1984_UTM_15S: 32715,
    WGS_1984_UTM_16S: 32716,
    WGS_1984_UTM_17S: 32717,
    WGS_1984_UTM_18S: 32718,
    WGS_1984_UTM_19S: 32719,
    WGS_1984_UTM_20S: 32720,
    WGS_1984_UTM_21S: 32721,
    WGS_1984_UTM_22S: 32722,
    WGS_1984_UTM_23S: 32723,
    WGS_1984_UTM_24S: 32724,
    WGS_1984_UTM_25S: 32725,
    WGS_1984_UTM_26S: 32726,
    WGS_1984_UTM_27S: 32727,
    WGS_1984_UTM_28S: 32728,
    WGS_1984_UTM_29S: 32729,
    WGS_1984_UTM_30S: 32730,
    WGS_1984_UTM_31S: 32731,
    WGS_1984_UTM_32S: 32732,
    WGS_1984_UTM_33S: 32733,
    WGS_1984_UTM_34S: 32734,
    WGS_1984_UTM_35S: 32735,
    WGS_1984_UTM_36S: 32736,
    WGS_1984_UTM_37S: 32737,
    WGS_1984_UTM_38S: 32738,
    WGS_1984_UTM_39S: 32739,
    WGS_1984_UTM_40S: 32740,
    WGS_1984_UTM_41S: 32741,
    WGS_1984_UTM_42S: 32742,
    WGS_1984_UTM_43S: 32743,
    WGS_1984_UTM_44S: 32744,
    WGS_1984_UTM_45S: 32745,
    WGS_1984_UTM_46S: 32746,
    WGS_1984_UTM_47S: 32747,
    WGS_1984_UTM_48S: 32748,
    WGS_1984_UTM_49S: 32749,
    WGS_1984_UTM_50S: 32750,
    WGS_1984_UTM_51S: 32751,
    WGS_1984_UTM_52S: 32752,
    WGS_1984_UTM_53S: 32753,
    WGS_1984_UTM_54S: 32754,
    WGS_1984_UTM_55S: 32755,
    WGS_1984_UTM_56S: 32756,
    WGS_1984_UTM_57S: 32757,
    WGS_1984_UTM_58S: 32758,
    WGS_1984_UTM_59S: 32759,
    WGS_1984_UTM_60S: 32760,
    TOKYO_PLATE_ZONE_I: 32761,
    UTM_JAPANESE_ZONE_II: 32762,
    UTM_JAPANESE_ZONE_III: 32763,
    UTM_JAPANESE_ZONE_IV: 32764,
    UTM_JAPANESE_ZONE_V: 32765,
    UTM_JAPANESE_ZONE_VI: 32766,
    UTM_JAPANESE_ZONE_VII: 32767,
    UTM_JAPANESE_ZONE_VIII: 32768,
    UTM_JAPANESE_ZONE_IX: 32769,
    UTM_JAPANESE_ZONE_X: 32770,
    UTM_JAPANESE_ZONE_XI: 32771,
    UTM_JAPANESE_ZONE_XII: 32772,
    TOKYO_PLATE_ZONE_XIII: 32773,
    UTM_JAPANESE_ZONE_XIV: 32774,
    TOKYO_PLATE_ZONE_XV: 32775,
    UTM_JAPANESE_ZONE_XVI: 32776,
    UTM_JAPANESE_ZONE_XVII: 32777,
    UTM_JAPANESE_ZONE_XVIII: 32778,
    UTM_JAPANESE_ZONE_XIX: 32779,
    TOKYO_UTM_51: 32780,
    TOKYO_UTM_52: 32781,
    TOKYO_UTM_53: 32782,
    TOKYO_UTM_54: 32783,
    TOKYO_UTM_55: 32784,
    TOKYO_UTM_56: 32785,
    JAPAN_PLATE_ZONE_I: 32786,
    JAPAN_PLATE_ZONE_II: 32787,
    JAPAN_PLATE_ZONE_III: 32788,
    JAPAN_PLATE_ZONE_IV: 32789,
    JAPAN_PLATE_ZONE_V: 32790,
    JAPAN_PLATE_ZONE_VI: 32791,
    JAPAN_PLATE_ZONE_VII: 32792,
    JAPAN_PLATE_ZONE_VIII: 32793,
    JAPAN_PLATE_ZONE_IX: 32794,
    JAPAN_PLATE_ZONE_X: 32795,
    JAPAN_PLATE_ZONE_XI: 32796,
    JAPAN_PLATE_ZONE_XII: 32797,
    JAPAN_PLATE_ZONE_XIII: 32798,
    JAPAN_PLATE_ZONE_XIV: 32800,
    JAPAN_PLATE_ZONE_XV: 32801,
    JAPAN_PLATE_ZONE_XVI: 32802,
    JAPAN_PLATE_ZONE_XVII: 32803,
    JAPAN_PLATE_ZONE_XVIII: 32804,
    JAPAN_PLATE_ZONE_XIX: 32805,
    JAPAN_UTM_51: 32806,
    JAPAN_UTM_52: 32807,
    JAPAN_UTM_53: 32808,
    JAPAN_UTM_54: 32809,
    JAPAN_UTM_55: 32810,
    JAPAN_UTM_56: 32811,
    HONG_KONG_1980_GRID: 32851,
    SPHERE_PLATE_CARREE: 53001,
    SPHERE_EQUIDISTANT_CYLINDRICAL: 53002,
    SPHERE_MILLER_CYLINDRICAL: 53003,
    SPHERE_MERCATOR: 53004,
    SPHERE_SINUSOIDAL: 53008,
    SPHERE_MOLLWEIDE: 53009,
    SPHERE_ECKERT_VI: 53010,
    SPHERE_ECKERT_V: 53011,
    SPHERE_ECKERT_IV: 53012,
    SPHERE_ECKERT_III: 53013,
    SPHERE_ECKERT_II: 53014,
    SPHERE_ECKERT_I: 53015,
    SPHERE_GALL_STEREOGRAPHIC: 53016,
    SPHERE_BEHRMANN: 53017,
    SPHERE_WINKEL_I: 53018,
    SPHERE_WINKEL_II: 53019,
    SPHERE_POLYCONIC: 53021,
    SPHERE_QUARTIC_AUTHALIC: 53022,
    SPHERE_LOXIMUTHAL: 53023,
    SPHERE_BONNE: 53024,
    SPHERE_HOTINE: 53025,
    SPHERE_STEREOGRAPHIC: 53026,
    SPHERE_EQUIDISTANT_CONIC: 53027,
    SPHERE_CASSINI: 53028,
    SPHERE_VAN_DER_GRINTEN_I: 53029,
    SPHERE_ROBINSON: 53030,
    SPHERE_TWO_POINT_EQUIDISTANT: 53031,
    WORLD_PLATE_CARREE: 54001,
    WORLD_EQUIDISTANT_CYLINDRICAL: 54002,
    WORLD_MILLER_CYLINDRICAL: 54003,
    WORLD_MERCATOR: 54004,
    WORLD_SINUSOIDAL: 54008,
    WORLD_MOLLWEIDE: 54009,
    WORLD_ECKERT_VI: 54010,
    WORLD_ECKERT_V: 54011,
    WORLD_ECKERT_IV: 54012,
    WORLD_ECKERT_III: 54013,
    WORLD_ECKERT_II: 54014,
    WORLD_ECKERT_I: 54015,
    WORLD_GALL_STEREOGRAPHIC: 54016,
    WORLD_BEHRMANN: 54017,
    WORLD_WINKEL_I: 54018,
    WORLD_WINKEL_II: 54019,
    WORLD_POLYCONIC: 54021,
    WORLD_QUARTIC_AUTHALIC: 54022,
    WORLD_LOXIMUTHAL: 54023,
    WORLD_BONNE: 54024,
    WORLD_HOTINE: 54025,
    WORLD_STEREOGRAPHIC: 54026,
    WORLD_EQUIDISTANT_CONIC: 54027,
    WORLD_CASSINI: 54028,
    WORLD_VAN_DER_GRINTEN_I: 54029,
    WORLD_ROBINSON: 54030,
    WORLD_TWO_POINT_EQUIDISTANT: 54031,
    NAD_1927_GU: 65061,
    NAD_1983_GU: 65161,
    GRS_1980_SWEREF99_TM: 23071,
    GRS_1980_SWEREF99_12_00: 23072,
    GRS_1980_SWEREF99_13_30: 23073,
    GRS_1980_SWEREF99_14_15: 23074,
    GRS_1980_SWEREF99_15_00: 23075,
    GRS_1980_SWEREF99_15_45: 23076,
    GRS_1980_SWEREF99_16_30: 23077,
    GRS_1980_SWEREF99_17_15: 23078,
    GRS_1980_SWEREF99_18_00: 23079,
    GRS_1980_SWEREF99_18_45: 23080,
    GRS_1980_SWEREF99_20_15: 23081,
    GRS_1980_SWEREF99_21_45: 23082,
    GRS_1980_SWEREF99_23_15: 23083,
    USER_DEFINED: -1
};
SuperMap.IS.PJDatumType = {
    AIRY_1830: 6001,
    AIRY_MOD: 6002,
    AUSTRALIAN: 6003,
    BESSEL_1841: 6004,
    BESSEL_MOD: 6005,
    BESSEL_NAMIBIA: 6006,
    CLARKE_1858: 6007,
    CLARKE_1866: 6008,
    CLARKE_1866_MICH: 6009,
    CLARKE_1880_BENOIT: 6010,
    CLARKE_1880_IGN: 6011,
    CLARKE_1880_RGS: 6012,
    CLARKE_1880_ARC: 6013,
    CLARKE_1880_SGA: 6014,
    EVEREST_1830: 6015,
    EVEREST_DEF_1967: 6016,
    EVEREST_DEF_1975: 6017,
    EVEREST_MOD: 6018,
    GRS_1980: 6019,
    HELMERT_1906: 6020,
    INDONESIAN: 6021,
    INTERNATIONAL_1924: 6022,
    INTERNATIONAL_1967: 6023,
    KRASOVSKY_1940: 6024,
    NWL_9D: 6025,
    PLESSIS_1817: 6027,
    STRUVE_1860: 6028,
    WAR_OFFICE: 6029,
    GEM_10C: 6031,
    OSU_86F: 6032,
    OSU_91A: 6033,
    CLARKE_1880: 6034,
    SPHERE: 6035,
    GRS_1967: 6036,
    GREEK: 6120,
    GGRS_1987: 6121,
    ATS_1977: 6122,
    KKJ: 6123,
    PULKOVO_1995: 6200,
    ADINDAN: 6201,
    AGD_1966: 6202,
    AGD_1984: 6203,
    AIN_EL_ABD_1970: 6204,
    AFGOOYE: 6205,
    AGADEZ: 6206,
    LISBON: 6207,
    ARATU: 6208,
    ARC_1950: 6209,
    ARC_1960: 6210,
    BATAVIA: 6211,
    BARBADOS: 6212,
    BEDUARAM: 6213,
    BEIJING_1954: 6214,
    BELGE_1950: 6215,
    BERMUDA_1957: 6216,
    BERN_1898: 6217,
    BOGOTA: 6218,
    BUKIT_RIMPAH: 6219,
    CAMACUPA: 6220,
    CAMPO_INCHAUSPE: 6221,
    CAPE: 6222,
    CARTHAGE: 6223,
    CHUA: 6224,
    CORREGO_ALEGRE: 6225,
    COTE_D_IVOIRE: 6226,
    DEIR_EZ_ZOR: 6227,
    DOUALA: 6228,
    EGYPT_1907: 6229,
    ED_1950: 6230,
    ED_1987: 6231,
    FAHUD: 6232,
    GANDAJIKA_1970: 6233,
    GAROUA: 6234,
    GUYANE_FRANCAISE: 6235,
    HU_TZU_SHAN: 6236,
    HUNGARIAN_1972: 6237,
    INDONESIAN_1974: 6238,
    INDIAN_1954: 6239,
    INDIAN_1975: 6240,
    JAMAICA_1875: 6241,
    JAMAICA_1969: 6242,
    KALIANPUR: 6243,
    KANDAWALA: 6244,
    KERTAU: 6245,
    KOC: 6246,
    LA_CANOA: 6247,
    PSAD_1956: 6248,
    LAKE: 6249,
    LEIGON: 6250,
    LIBERIA_1964: 6251,
    LOME: 6252,
    LUZON_1911: 6253,
    HITO_XVIII_1963: 6254,
    HERAT_NORTH: 6255,
    MAHE_1971: 6256,
    MAKASSAR: 6257,
    ETRS_1989: 6258,
    MALONGO_1987: 6259,
    MANOCA: 6260,
    MERCHICH: 6261,
    MASSAWA: 6262,
    MINNA: 6263,
    MHAST: 6264,
    MONTE_MARIO: 6265,
    MPORALOKO: 6266,
    NAD_1927: 6267,
    NAD_MICH: 6268,
    NAD_1983: 6269,
    NAHRWAN_1967: 6270,
    NAPARIMA_1972: 6271,
    NZGD_1949: 6272,
    NGO_1948: 6273,
    DATUM_73: 6274,
    NTF: 6275,
    NSWC_9Z_2: 6276,
    OSGB_1936: 6277,
    OSGB_1970_SN: 6278,
    OS_SN_1980: 6279,
    PADANG_1884: 6280,
    PALESTINE_1923: 6281,
    POINTE_NOIRE: 6282,
    GDA_1994: 6283,
    PULKOVO_1942: 6284,
    QATAR: 6285,
    QATAR_1948: 6286,
    QORNOQ: 6287,
    LOMA_QUINTANA: 6288,
    AMERSFOORT: 6289,
    SAD_1969: 6291,
    SAPPER_HILL_1943: 6292,
    SCHWARZECK: 6293,
    SEGORA: 6294,
    SERINDUNG: 6295,
    SUDAN: 6296,
    TANANARIVE_1925: 6297,
    TIMBALAI_1948: 6298,
    TM65: 6299,
    TM75: 6300,
    TOKYO: 6301,
    TRINIDAD_1903: 6302,
    TRUCIAL_COAST_1948: 6303,
    VOIROL_1875: 6304,
    VOIROL_UNIFIE_1960: 6305,
    BERN_1938: 6306,
    NORD_SAHARA_1959: 6307,
    STOCKHOLM_1938: 6308,
    YACARE: 6309,
    YOFF: 6310,
    ZANDERIJ: 6311,
    MGI: 6312,
    BELGE_1972: 6313,
    DHDN: 6314,
    CONAKRY_1905: 6315,
    DEALUL_PISCULUI_1933: 6316,
    DEALUL_PISCULUI_1970: 6317,
    NGN: 6318,
    KUDAMS: 6319,
    WGS_1972: 6322,
    WGS_1972_BE: 6324,
    WGS_1984: 6326,
    ATF: 6901,
    NDG: 6902,
    WGS_1966: 39001,
    FISCHER_1960: 39002,
    FISCHER_1968: 39003,
    FISCHER_MOD: 39004,
    HOUGH_1960: 39005,
    EVEREST_MOD_1969: 39006,
    WALBECK: 39007,
    SPHERE_AI: 39008,
    EUROPEAN_1979: 39201,
    EVEREST_BANGLADESH: 39202,
    EVEREST_INDIA_NEPAL: 39203,
    HJORSEY_1955: 39204,
    HONG_KONG_1963: 39205,
    OMAN: 39206,
    S_ASIA_SINGAPORE: 39207,
    AYABELLE: 39208,
    BISSAU: 39209,
    DABOLA: 39210,
    POINT58: 39211,
    BEACON_E_1945: 39212,
    TERN_ISLAND_1961: 39213,
    ASTRO_1952: 39214,
    BELLEVUE: 39215,
    CANTON_1966: 39216,
    CHATHAM_ISLAND_1971: 39217,
    DOS_1968: 39218,
    EASTER_ISLAND_1967: 39219,
    GUAM_1963: 39220,
    GUX_1: 39221,
    JOHNSTON_ISLAND_1961: 39222,
    MIDWAY_1961: 39224,
    OLD_HAWAIIAN: 39225,
    PITCAIRN_1967: 39226,
    SANTO_DOS_1965: 39227,
    VITI_LEVU_1916: 39228,
    WAKE_ENIWETOK_1960: 39229,
    WAKE_ISLAND_1952: 39230,
    ANNA_1_1965: 39231,
    GAN_1970: 39232,
    ISTS_073_1969: 39233,
    KERGUELEN_ISLAND_1949: 39234,
    REUNION: 39235,
    ANTIGUA_ISLAND_1943: 39236,
    ASCENSION_ISLAND_1958: 39237,
    DOS_71_4: 39238,
    CACANAVERAL: 39239,
    FORT_THOMAS_1955: 39240,
    GRACIOSA_1948: 39241,
    ISTS_061_1968: 39242,
    LC5_1961: 39243,
    MONTSERRAT_ISLAND_1958: 39244,
    OBSERV_METEOR_1939: 39245,
    PICO_DE_LAS_NIEVES: 39246,
    PORTO_SANTO_1936: 39247,
    PUERTO_RICO: 39248,
    SAO_BRAZ: 39249,
    SELVAGEM_GRANDE_1938: 39250,
    TRISTAN_1968: 39251,
    SAMOA_1962: 39252,
    CAMP_AREA: 39253,
    DECEPTION_ISLAND: 39254,
    GUNUNG_SEGARA: 39255,
    INDIAN_1960: 39256,
    S42_HUNGARY: 39257,
    S_JTSK: 39258,
    KUSAIE_1951: 39259,
    ALASKAN_ISLANDS: 39260,
    JAPAN_2000: 39301,
    XIAN_1980: 39312,
    HONG_KONG_1980: 39414,
    USER_DEFINED: -1
};
SuperMap.IS.PJGeoCoordSysType = {
    AIRY_1830: 4001,
    AIRY_MOD: 4002,
    AUSTRALIAN: 4003,
    BESSEL_1841: 4004,
    BESSEL_MOD: 4005,
    BESSEL_NAMIBIA: 4006,
    CLARKE_1858: 4007,
    CLARKE_1866: 4008,
    CLARKE_1866_MICH: 4009,
    CLARKE_1880_BENOIT: 4010,
    CLARKE_1880_IGN: 4011,
    CLARKE_1880_RGS: 4012,
    CLARKE_1880_ARC: 4013,
    CLARKE_1880_SGA: 4014,
    EVEREST_1830: 4015,
    EVEREST_DEF_1967: 4016,
    EVEREST_DEF_1975: 4017,
    EVEREST_MOD: 4018,
    GRS_1980: 4019,
    HELMERT_1906: 4020,
    INDONESIAN: 4021,
    INTERNATIONAL_1924: 4022,
    INTERNATIONAL_1967: 4023,
    KRASOVSKY_1940: 4024,
    NWL_9D: 4025,
    PLESSIS_1817: 4027,
    STRUVE_1860: 4028,
    WAR_OFFICE: 4029,
    GEM_10C: 4031,
    OSU_86F: 4032,
    OSU_91A: 4033,
    CLARKE_1880: 4034,
    SPHERE: 4035,
    GRS_1967: 4036,
    GREEK: 4120,
    GGRS_1987: 4121,
    ATS_1977: 4122,
    KKJ: 4123,
    PULKOVO_1995: 4200,
    ADINDAN: 4201,
    AGD_1966: 4202,
    AGD_1984: 4203,
    AIN_EL_ABD_1970: 4204,
    AFGOOYE: 4205,
    AGADEZ: 4206,
    LISBON: 4207,
    ARATU: 4208,
    ARC_1950: 4209,
    ARC_1960: 4210,
    BATAVIA: 4211,
    BARBADOS: 4212,
    BEDUARAM: 4213,
    BEIJING_1954: 4214,
    BELGE_1950: 4215,
    BERMUDA_1957: 4216,
    BERN_1898: 4217,
    BOGOTA: 4218,
    BUKIT_RIMPAH: 4219,
    CAMACUPA: 4220,
    CAMPO_INCHAUSPE: 4221,
    CAPE: 4222,
    CARTHAGE: 4223,
    CHUA: 4224,
    CORREGO_ALEGRE: 4225,
    COTE_D_IVOIRE: 4226,
    DEIR_EZ_ZOR: 4227,
    DOUALA: 4228,
    EGYPT_1907: 4229,
    ED_1950: 4230,
    ED_1987: 4231,
    FAHUD: 4232,
    GANDAJIKA_1970: 4233,
    GAROUA: 4234,
    GUYANE_FRANCAISE: 4235,
    HU_TZU_SHAN: 4236,
    HUNGARIAN_1972: 4237,
    INDONESIAN_1974: 4238,
    INDIAN_1954: 4239,
    INDIAN_1975: 4240,
    JAMAICA_1875: 4241,
    JAMAICA_1969: 4242,
    KALIANPUR: 4243,
    KANDAWALA: 4244,
    KERTAU: 4245,
    KOC_: 4246,
    LA_CANOA: 4247,
    PSAD_1956: 4248,
    LAKE: 4249,
    LEIGON: 4250,
    LIBERIA_1964: 4251,
    LOME: 4252,
    LUZON_1911: 4253,
    HITO_XVIII_1963: 4254,
    HERAT_NORTH: 4255,
    MAHE_1971: 4256,
    MAKASSAR: 4257,
    ETRS_1989: 4258,
    MALONGO_1987: 4259,
    MANOCA: 4260,
    MERCHICH: 4261,
    MASSAWA: 4262,
    MINNA: 4263,
    MHAST: 4264,
    MONTE_MARIO: 4265,
    MPORALOKO: 4266,
    NAD_1927: 4267,
    NAD_MICH: 4268,
    NAD_1983: 4269,
    NAHRWAN_1967: 4270,
    NAPARIMA_1972: 4271,
    NZGD_1949: 4272,
    NGO_1948_: 4273,
    DATUM_73: 4274,
    NTF_: 4275,
    NSWC_9Z_2_: 4276,
    OSGB_1936_: 4277,
    OSGB_1970_SN: 4278,
    OS_SN_1980: 4279,
    PADANG_1884: 4280,
    PALESTINE_1923: 4281,
    POINTE_NOIRE: 4282,
    GDA_1994: 4283,
    PULKOVO_1942: 4284,
    QATAR: 4285,
    QATAR_1948: 4286,
    QORNOQ: 4287,
    LOMA_QUINTANA: 4288,
    AMERSFOORT: 4289,
    SAD_1969: 4291,
    SAPPER_HILL_1943: 4292,
    SCHWARZECK: 4293,
    SEGORA: 4294,
    SERINDUNG: 4295,
    SUDAN: 4296,
    TANANARIVE_1925: 4297,
    TIMBALAI_1948: 4298,
    TM65_: 4299,
    TM75_: 4300,
    TOKYO: 4301,
    TRINIDAD_1903: 4302,
    TRUCIAL_COAST_1948: 4303,
    VOIROL_1875: 4304,
    VOIROL_UNIFIE_1960: 4305,
    BERN_1938: 4306,
    NORD_SAHARA_1959: 4307,
    RT38_: 4308,
    YACARE: 4309,
    YOFF: 4310,
    ZANDERIJ: 4311,
    MGI_: 4312,
    BELGE_1972: 4313,
    DHDN: 4314,
    CONAKRY_1905: 4315,
    DEALUL_PISCULUI_1933: 4316,
    DEALUL_PISCULUI_1970: 4317,
    NGN: 4318,
    KUDAMS: 4319,
    WGS_1972: 4322,
    WGS_1972_BE: 4324,
    WGS_1984: 4326,
    BERN_1898_BERN: 4801,
    BOGOTA_BOGOTA: 4802,
    LISBON_LISBON: 4803,
    MAKASSAR_JAKARTA: 4804,
    MGI_FERRO: 4805,
    MONTE_MARIO_ROME: 4806,
    NTF_PARIS: 4807,
    PADANG_1884_JAKARTA: 4808,
    BELGE_1950_BRUSSELS: 4809,
    TANANARIVE_1925_PARIS: 4810,
    VOIROL_1875_PARIS: 4811,
    VOIROL_UNIFIE_1960_PARIS: 4812,
    BATAVIA_JAKARTA: 4813,
    RT38_STOCKHOLM: 4814,
    GREEK_ATHENS: 4815,
    ATF_PARIS: 4901,
    NDG_PARIS: 4902,
    WGS_1966: 37001,
    FISCHER_1960: 37002,
    FISCHER_1968: 37003,
    FISCHER_MOD: 37004,
    HOUGH_1960: 37005,
    EVEREST_MOD_1969: 37006,
    WALBECK: 37007,
    SPHERE_AI: 37008,
    EUROPEAN_1979: 37201,
    EVEREST_BANGLADESH: 37202,
    EVEREST_INDIA_NEPAL: 37203,
    HJORSEY_1955: 37204,
    HONG_KONG_1963: 37205,
    OMAN: 37206,
    S_ASIA_SINGAPORE: 37207,
    AYABELLE: 37208,
    BISSAU: 37209,
    DABOLA: 37210,
    POINT58: 37211,
    BEACON_E_1945: 37212,
    TERN_ISLAND_1961: 37213,
    ASTRO_1952: 37214,
    BELLEVUE: 37215,
    CANTON_1966: 37216,
    CHATHAM_ISLAND_1971: 37217,
    DOS_1968: 37218,
    EASTER_ISLAND_1967: 37219,
    GUAM_1963: 37220,
    GUX_1: 37221,
    JOHNSTON_ISLAND_1961: 37222,
    CARTHAGE_DEGREE: 37223,
    MIDWAY_1961: 37224,
    OLD_HAWAIIAN: 37225,
    PITCAIRN_1967: 37226,
    SANTO_DOS_1965: 37227,
    VITI_LEVU_1916: 37228,
    WAKE_ENIWETOK_1960: 37229,
    WAKE_ISLAND_1952: 37230,
    ANNA_1_1965: 37231,
    GAN_1970: 37232,
    ISTS_073_1969: 37233,
    KERGUELEN_ISLAND_1949: 37234,
    REUNION: 37235,
    ANTIGUA_ISLAND_1943: 37236,
    ASCENSION_ISLAND_1958: 37237,
    DOS_71_4: 37238,
    CACANAVERAL: 37239,
    FORT_THOMAS_1955: 37240,
    GRACIOSA_1948: 37241,
    ISTS_061_1968: 37242,
    LC5_1961: 37243,
    MONTSERRAT_ISLAND_1958: 37244,
    OBSERV_METEOR_1939: 37245,
    PICO_DE_LAS_NIEVES: 37246,
    PORTO_SANTO_1936: 37247,
    PUERTO_RICO: 37248,
    SAO_BRAZ: 37249,
    SELVAGEM_GRANDE_1938: 37250,
    TRISTAN_1968: 37251,
    SAMOA_1962: 37252,
    CAMP_AREA: 37253,
    DECEPTION_ISLAND: 37254,
    GUNUNG_SEGARA: 37255,
    INDIAN_1960: 37256,
    S42_HUNGARY: 37257,
    S_JTSK: 37258,
    KUSAIE_1951: 37259,
    ALASKAN_ISLANDS: 37260,
    JAPAN_2000: 37301,
    XIAN_1980: 37312,
    HONG_KONG_1980: 37414,
    USER_DEFINED: -1
};
SuperMap.IS.PJObjectType = {
    Undefined: 0,
    PLATE_CARREE: 43001,
    EQUIDISTANT_CYLINDRICAL: 43002,
    MILLER_CYLINDRICAL: 43003,
    MERCATOR: 43004,
    GAUSS_KRUGER: 43005,
    TRANSVERSE_MERCATOR: 43006,
    ALBERS: 43007,
    SINUSOIDAL: 43008,
    MOLLWEIDE: 43009,
    ECKERT_VI: 43010,
    ECKERT_V: 43011,
    ECKERT_IV: 43012,
    ECKERT_III: 43013,
    ECKERT_II: 43014,
    ECKERT_I: 43015,
    GALL_STEREOGRAPHIC: 43016,
    BEHRMANN: 43017,
    WINKEL_I: 43018,
    WINKEL_II: 43019,
    LAMBERT_CONFORMAL_CONIC: 43020,
    POLYCONIC: 43021,
    QUARTIC_AUTHALIC: 43022,
    LOXIMUTHAL: 43023,
    BONNE: 43024,
    HOTINE: 43025,
    STEREOGRAPHIC: 43026,
    EQUIDISTANT_CONIC: 43027,
    CASSINI: 43028,
    VAN_DER_GRINTEN_I: 43029,
    ROBINSON: 43030,
    TWO_POINT_EQUIDISTANT: 43031,
    EQUIDISTANT_AZIMUTHAL: 43032,
    LAMBERT_AZIMUTHAL_EQUAL_AREA: 43033,
    CONFORMAL_AZIMUTHAL: 43034,
    ORTHO_GRAPHIC: 43035,
    GNOMONIC: 43036,
    CHINA_AZIMUTHAL: 43037,
    SANSON: 43040,
    EQUALAREA_CYLINDRICAL: 43041,
    Unknown: -1
};
SuperMap.IS.PJPrimeMeridianType = {
    GREENWICH: 8901,
    LISBON: 8902,
    PARIS: 8903,
    BOGOTA: 8904,
    MADRID: 8905,
    ROME: 8906,
    BERN: 8907,
    JAKARTA: 8908,
    FERRO: 8909,
    BRUSSELS: 8910,
    STOCKHOLM: 8911,
    ATHENS: 8912,
    USER_DEFINED: -1
};
SuperMap.IS.PJSpheroidType = {
    AIRY_1830: 7001,
    AIRY_MOD: 7002,
    AUSTRALIAN: 7003,
    BESSEL_1841: 7004,
    BESSEL_MOD: 7005,
    BESSEL_NAMIBIA: 7006,
    CLARKE_1858: 7007,
    CLARKE_1866: 7008,
    CLARKE_1866_MICH: 7009,
    CLARKE_1880_BENOIT: 7010,
    CLARKE_1880_IGN: 7011,
    CLARKE_1880_RGS: 7012,
    CLARKE_1880_ARC: 7013,
    CLARKE_1880_SGA: 7014,
    EVEREST_1830: 7015,
    EVEREST_DEF_1967: 7016,
    EVEREST_DEF_1975: 7017,
    EVEREST_MOD: 7018,
    GRS_1980: 7019,
    HELMERT_1906: 7020,
    INDONESIAN: 7021,
    INTERNATIONAL_1924: 7022,
    INTERNATIONAL_1967: 7023,
    KRASOVSKY_1940: 7024,
    NWL_9D: 7025,
    NWL_10D: 7026,
    PLESSIS_1817: 7027,
    STRUVE_1860: 7028,
    WAR_OFFICE: 7029,
    WGS_1984: 7030,
    GEM_10C: 7031,
    OSU_86F: 7032,
    OSU_91A: 7033,
    CLARKE_1880: 7034,
    SPHERE: 7035,
    GRS_1967: 7036,
    WGS_1972: 7037,
    ATS_1977: 7041,
    WGS_1966: 40001,
    FISCHER_1960: 40002,
    FISCHER_1968: 40003,
    FISCHER_MOD: 40004,
    HOUGH_1960: 40005,
    EVEREST_MOD_1969: 40006,
    WALBECK: 40007,
    SPHERE_AI: 40008,
    INTERNATIONAL_1975: 40023,
    USER_DEFINED: -1
};
SuperMap.IS.SpatialAutocorrelationMethod = {
    moran: 0,
    localMoran: 1,
    geary: 2
};
SuperMap.IS.FieldType = {
    undefined: 0,
    Boolean: 1,
    Byte: 2,
    integer: 3,
    Long: 4,
    currency: 5,
    single: 6,
    Double: 7,
    date: 8,
    binary: 9,
    text: 10,
    longBinary: 11,
    memo: 12,
    Char: 18,
    numeric: 19,
    time: 22,
    nchar: 127,
    geometry: 128,
    dgnLink: 129
};
SuperMap.IS.EncodedType = {
    none: 0,
    Byte: 1,
    word: 2,
    tbyte: 3,
    dword: 4,
    Float: 5,
    Double: 6,
    spc: 7,
    dct: 8,
    sgl: 9,
    lzw: 11,
    png: 12
};
SuperMap.IS.Charset = {
    ansi: 0,
    Default: 1,
    symbol: 2,
    mac: 77,
    shiftJIS: 128,
    gb2312: 134,
    chineseBIG5: 136,
    greek: 161,
    turkish: 162,
    vietnamese: 163,
    hebrew: 177,
    arabic: 178,
    baltic: 186,
    russian: 204,
    thai: 222,
    eastEurope: 238,
    oem: 255
};
SuperMap.IS.RenderStyle = {
    normal: 0,
    disabled: 1,
    hidden: 2
};
SuperMap.IS.LayerItemCompareModel = {
    value: 0,
    caption: 1,
    order: 2
};
SuperMap.IS.BrushGradientMode = {
    none: 0,
    linear: 1,
    radial: 2,
    conical: 3,
    square: 4
};
SuperMap.IS.TextAlign = {
    topLeft: 0,
    topCenter: 1,
    topRight: 2,
    baslineLeft: 3,
    baslineCenter: 4,
    baslineRight: 5,
    bottomLeft: 6,
    bottomCenter: 7,
    bottomRight: 8,
    middleLeft: 9,
    middleCenter: 10,
    middleRight: 11
};
SuperMap.IS.BusLineDirection = {
    forward: 0,
    backward: 1,
    both: 2
};
SuperMap.IS.InterpolateType = {
    tin: 1,
    idw: 2,
    krig: 3,
    bSpline: 4
};
SuperMap.IS.BrushGradientType = {
    Default: 0,
    blackWhite: 1,
    redWhite: 2,
    greenWhite: 3,
    blueWhite: 4,
    yellowWhite: 5,
    pinkWhite: 6,
    cyanWhite: 7,
    redBlack: 8,
    greenBlack: 9,
    blueBlack: 10,
    yellowBlack: 11,
    pinkBlack: 12,
    cyanBlack: 13,
    yellowRed: 14,
    yellowGreen: 15,
    yellowBlue: 16,
    greenBlue: 17,
    greenRed: 18,
    blueRed: 19,
    pinkRed: 20,
    pinkBlue: 21,
    cyanBlue: 22,
    cyanGreen: 23,
    rainbow: 24,
    greenOrangeViolet: 25,
    terrain: 26,
    spectrum: 27
};
SuperMap.IS.ZoomPosition = {
    vertical: 0,
    horizontal: 1
};
SuperMap.IS.DataEngineType = {
    sdb: 0,
    sqlServer: 2,
    imagePlugins: 5,
    microStation: 8,
    autoCAD: 9,
    oracleSpatial: 10,
    oraclePlus: 12,
    sdbPlus: 14,
    sybasePlus: 15,
    sqlPlus: 16,
    dmPlus: 17,
    memory: 20,
    web: 23
};
SuperMap.IS.AlignStyle = {
    leftTop: 0,
    leftMiddle: 1,
    leftBottom: 2,
    centerTop: 3,
    centerMiddle: 4,
    centerBottom: 5,
    rightTop: 6,
    rightMiddle: 7,
    rightBottom: 8
};
SuperMap.IS.DirectionType = {
    east: 0,
    south: 1,
    west: 2,
    north: 3,
    none: -1
};
SuperMap.IS.SideType = {
    middle: 0,
    left: 1,
    right: 2,
    none: -1
};
SuperMap.IS.TurnType = {
    end: 0,
    left: 1,
    right: 2,
    ahead: 3,
    back: 4,
    none: -1
};
SuperMap.IS.SwitchMapMode = {
    Default: 0,
    keepCenterAndScale: 1
}
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
            var mapStatusJ = eval("(" + responseText + ")");
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
            _params.layersKey = eval("(" + responseText + ")");
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
            var resultJ = eval("(" + responseText + ")");
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
            var resultJ = eval("(" + responseText + ")");
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
            var resultJ = eval("(" + responseText + ")");
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
            var resultJ = eval("(" + responseText + ")");
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
            var entityJ = eval("(" + responseText + ")");
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
            var entitiesJ = eval("(" + responseText + ")");
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
            var resultJ = eval("(" + responseText + ")");
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
            var resultJ = eval("(" + responseText + ")");
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
            var success = eval("(" + responseText + ")");
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
            var success = eval("(" + responseText + ")");
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
            var resultJ = eval("(" + responseText + ")");
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
            var resultJ = eval("(" + responseText + ")");
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
            var resultJ = eval("(" + responseText + ")");
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
            var resultJ = eval("(" + responseText + ")");
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
            var resultJ = eval("(" + responseText + ")");
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
            var resultJ = eval("(" + responseText + ")");
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
            var resultJ = eval("(" + responseText + ")");
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
            var o = eval(layersInfo);
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
SuperMap.IS.QueryManager = function(mapHandler, mapName, trackingLayerIndex, userID) {
    var queryUrl = mapHandler + "query.ashx";
    var onComplete = null;
    var onError = null;
    var _trackingLayerIndex = -1;
    var _userID = "";
    var _eventsList = new Array();
    var _eventsNameList = new Array();
    if (trackingLayerIndex != null) {
        _trackingLayerIndex = trackingLayerIndex
    }
    if (userID != null) {
        _userID = userID
    }
    function _RegisterHandler(onQueryComplete, onQueryError) {
        onComplete = onQueryComplete;
        onError = onQueryError
    }
    this.RegisterHandler = _RegisterHandler;
    function _QueryBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            var resultSetJ = eval("(" + responseText + ")");
            if (resultSetJ == null) {
                onComplete(null, userContext);
                return
            }
            var resultSet = new SuperMap.IS.ResultSet();
            resultSet.FromJSON(resultSetJ);
            _ChangeTrackingLayerKeyInternal(resultSet.trackingLayerIndex, resultSet.userID, true);
            if (onComplete) {
                onComplete(resultSet, userContext);
                resultSet.Destroy();
                resultSet = null
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
    }
    this.QueryByPoint = function(point, tolerance, queryParam, onComplete, onError, userContext) {
        _QueryBase("QueryByPoint", ["point", "tolerance", "queryParam", "trackingLayerIndex", "userID"], [point, tolerance, queryParam, _trackingLayerIndex, _userID], onComplete, onError, userContext)
    };
    this.QueryByLine = function(points, queryParam, onComplete, onError, userContext) {
        _QueryBase("QueryByLine", ["points", "queryParam", "trackingLayerIndex", "userID"], [points, queryParam, _trackingLayerIndex, _userID], onComplete, onError, userContext)
    };
    this.QueryBySql = function(queryParam, onComplete, onError, userContext) {
        _QueryBase("QueryBySql", ["queryParam", "trackingLayerIndex", "userID"], [queryParam, _trackingLayerIndex, _userID], onComplete, onError, userContext)
    };
    this.QueryByRect = function(mapRect, queryParam, onComplete, onError, userContext) {
        _QueryBase("QueryByRect", ["mapRect", "queryParam", "trackingLayerIndex", "userID"], [mapRect, queryParam, _trackingLayerIndex, _userID], onComplete, onError, userContext)
    };
    this.QueryByPolygon = function(points, queryParam, onComplete, onError, userContext) {
        _QueryBase("QueryByPolygen", ["points", "queryParam", "trackingLayerIndex", "userID"], [points, queryParam, _trackingLayerIndex, _userID], onComplete, onError, userContext)
    };
    this.QueryByCircle = function(center, radius, queryParam, onComplete, onError, userContext) {
        _QueryBase("QueryByCircle", ["center", "radius", "queryParam", "trackingLayerIndex", "userID"], [center, radius, queryParam, _trackingLayerIndex, _userID], onComplete, onError, userContext)
    };
    this.QueryByGeometry = function(geometry, queryParam, spatialQueryMode, onComplete, onError, userContext) {
        _QueryBase("QueryByGeometry", ["geometry", "queryParam", "spatialQueryMode", "trackingLayerIndex", "userID"], [geometry, queryParam, spatialQueryMode, _trackingLayerIndex, _userID], onComplete, onError, userContext)
    };
    this.Find = function(queryParam, onComplete, onError, userContext) {
        _QueryBase("Find", ["queryParam", "trackingLayerIndex", "userID"], [queryParam, _trackingLayerIndex, _userID], onComplete, onError, userContext)
    };
    this.FindNearest = function(point, tolerance, queryParam, onComplete, onError, userContext) {
        _QueryBase("FindNearest", ["point", "tolerance", "queryParam", "trackingLayerIndex", "userID"], [point, tolerance, queryParam, _trackingLayerIndex, _userID], onComplete, onError, userContext)
    };
    this.FindNearestWithBounds = function(point, tolerance, queryParam, onComplete, onError, userContext) {
        _QueryBase("FindNearestWithBounds", ["point", "tolerance", "queryParam", "trackingLayerIndex", "userID"], [point, tolerance, queryParam, _trackingLayerIndex, _userID], onComplete, onError, userContext)
    };
    this.StatisticsQuery = function(statisticsQueryParam, onComplete, onError, userContext) {
        _QueryBase("StatisticsQuery", ["statisticsQueryParam"], [statisticsQueryParam], onComplete, onError, userContext)
    };
    function _ChangeTrackingLayerKey(trackingLayerIndex, userID) {
        _trackingLayerIndex = trackingLayerIndex;
        _userID = userID
    }
    function _ChangeTrackingLayerKeyInternal(trackingLayerIndex, userID, bSaveHistory) {
        if (_trackingLayerIndex != trackingLayerIndex || _userID != userID) {
            _trackingLayerIndex = trackingLayerIndex;
            _userID = userID;
            var param = new Object();
            param.trackingLayerIndex = _trackingLayerIndex;
            param.userID = _userID;
            param.bSaveHistory = bSaveHistory;
            _TriggerEvent("onchangetrackinglayer", param)
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
    function _GenerateEventArg(error, e) {
        var param = new Object();
        param.trackingLayerIndex = _trackingLayerIndex;
        param.userID = _userID;
        if (!error) {
            error = ""
        }
        return new EventArguments(param, error, e)
    }
    this.AttachEvent = _AttachEvent;
    this.DetachEvent = _DetachEvent;
    this.ChangeTrackingLayerKey = _ChangeTrackingLayerKey
}
SuperMap.IS.SpatialAnalystManager = function(mapHandler, mapName, trackingLayerIndex, userID) {
    var queryUrl = mapHandler + "path.ashx";
    var onComplete = null;
    var onError = null;
    var _trackingLayerIndex = -1;
    var _userID = "";
    var _eventsList = new Array();
    var _eventsNameList = new Array();
    if (trackingLayerIndex != null) {
        _trackingLayerIndex = trackingLayerIndex
    }
    if (userID != null) {
        _userID = userID
    }
    function _RegisterHandler(onSpatialAnalystComplete, onSpatialAnalystError) {
        onComplete = onSpatialAnalystComplete;
        onError = onSpatialAnalystError
    }
    this.RegisterHandler = _RegisterHandler;
    function _FindPathBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var routeResultJ = eval("(" + responseText + ")");
            var routeResult = new SuperMap.IS.RouteResult();
            routeResult.FromJSON(routeResultJ);
            _ChangeTrackingLayerKeyInternal(routeResult.trackingLayerIndex, routeResult.userID, true);
            if (onComplete) {
                onComplete(routeResult, userContext)
            }
            if (routeResultJ && routeResultJ.nodePositions) {
                while (routeResultJ.nodePositions.length > 0) {
                    routeResultJ.nodePositions.pop()
                }
            }
            routeResultJ = null;
            routeResult.Destroy();
            routeResult = null
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
    this.FindPathByPoints = function(points, routeParam, onComplete, onError, userContext) {
        if (points != null) {
            _FindPathBase("FindPathByPoints", ["points", "routeParam", "trackingLayerIndex", "userID"], [points, routeParam, _trackingLayerIndex, _userID], onComplete, onError, userContext)
        }
    };
    this.FindPathByIds = function(ids, routeParam, onComplete, onError, userContext) {
        if (ids != null) {
            _FindPathBase("FindPathByIds", ["ids", "routeParam", "trackingLayerIndex", "userID"], [ids, routeParam, _trackingLayerIndex, _userID], onComplete, onError, userContext)
        }
    };
    function _DynamicPathBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var routeResultJ = eval("(" + responseText + ")");
            var routeResult = new SuperMap.IS.RouteResult();
            routeResult.FromJSON(routeResultJ);
            _ChangeTrackingLayerKeyInternal(routeResult.trackingLayerIndex, routeResult.userID, true);
            if (onComplete) {
                onComplete(routeResult, userContext)
            }
            if (routeResultJ && routeResultJ.nodePositions) {
                while (routeResultJ.nodePositions.length > 0) {
                    routeResultJ.nodePositions.pop()
                }
            }
            routeResultJ = null;
            routeResult.Destroy();
            routeResult = null
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
    this.DynamicPathByPoints = function(points, curTime, routeParam, timeSegments, timeSegmentInfo, onComplete, onError, userContext) {
        if (points != null) {
            _DynamicPathBase("DynamicPathByPoints", ["points", "curTime", "routeParam", "timeSegments", "timeSegmentInfos", "trackingLayerIndex", "userID"], [points, curTime, routeParam, timeSegments, timeSegmentInfos, _trackingLayerIndex, _userID], onComplete, onError, userContext)
        }
    };
    this.DynamicPathByIds = function(ids, curTime, routeParam, timeSegments, timeSegmentInfo, onComplete, onError, userContext) {
        if (ids != null) {
            _DynamicPathBase("DynamicPathByIds", ["ids", "curTime", "routeParam", "timeSegments", "timeSegmentInfos", "trackingLayerIndex", "userID"], [ids, curTime, routeParam, timeSegments, timeSegmentInfos, _trackingLayerIndex, _userID], onComplete, onError, userContext)
        }
    };
    function _SetTimeSegmentBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var result = eval("(" + responseText + ")");
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
    }
    this.SetTimeSegment = function(segmentValue, timeSegment, onComplete, onError, userContext) {
        _SetTimeSegmentBase("SetTimeSegment", ["segmentValue", "timeSegment"], [segmentValue, timeSegment], onComplete, onError, userContext)
    };
    function _GetTimeSegmentBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var result = eval("(" + responseText + ")");
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
    }
    this.GetTimeSegment = function(segmentValue, onComplete, onError, userContext) {
        _GetTimeSegmentBase("GetTimeSegment", ["segmentValue"], [segmentValue], onComplete, onError, userContext)
    };
    function _RemoveTimeSegmentBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var result = eval("(" + responseText + ")");
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
    }
    this.RemoveTimeSegment = function(segmentValue, onComplete, onError, userContext) {
        _RemoveTimeSegmentBase("RemoveTimeSegment", ["segmentValue"], [segmentValue], onComplete, onError, userContext)
    };
    function _GetValidTimeSegmentIndexBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var result = eval("(" + responseText + ")");
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
    }
    this.GetValidTimeSegmentIndex = function(timeSegmentValue, lastUpdateTime, onComplete, onError, userContext) {
        _GetValidTimeSegmentIndexBase("GetValidTimeSegmentIndex", ["timeSegmentValue", "lastUpdateTime"], [timeSegmentValue, lastUpdateTime], onComplete, onError, userContext)
    };
    function _UpdateTimeSegmentInfoBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var result = eval("(" + responseText + ")");
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
    }
    this.UpdateTimeSegmentInfoByEdge = function(edgeID, timeSegmentValue, edgeTimeSegmentIndex, timeSegmentCost, lastUpdateTime, onComplete, onError, userContext) {
        _UpdateTimeSegmentInfoBase("UpdateTimeSegmentInfoByEdge", ["edgeID", "timeSegmentValue", "edgeTimeSegmentIndex", "timeSegmentCost", "lastUpdateTime"], [edgeID, timeSegmentValue, edgeTimeSegmentIndex, timeSegmentCost, lastUpdateTime], onComplete, onError, userContext)
    };
    this.UpdateTimeSegmentInfoByTimeSegment = function(timeSegmentIndex, timeSegmentValue, edgeIDs, timeSegmentCost, lastUpdateTime, onComplete, onError, userContext) {
        _UpdateTimeSegmentInfoBase("UpdateTimeSegmentInfoByTimeSegment", ["timeSegmentIndex", "timeSegmentValue", "edgeIDs", "timeSegmentCost", "lastUpdateTime"], [timeSegmentIndex, timeSegmentValue, edgeIDs, timeSegmentCost, lastUpdateTime], onComplete, onError, userContext)
    };
    this.UpdateTimeSegmentInfo = function(timeSegmentInfo, onComplete, onError, userContext) {
        _UpdateTimeSegmentInfoBase("UpdateTimeSegmentInfo", ["timeSegmentInfo"], [timeSegmentInfo], onComplete, onError, userContext)
    };
    function _GetBusSolutionBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var busSolutionsJ = eval("(" + responseText + ")");
            if (!busSolutionsJ) {
                onComplete(null, userContext);
                return
            }
            var busSolutions = new Array();
            for (var i = 0; i < busSolutionsJ.length; i++) {
                busSolutions[i] = new SuperMap.IS.BusSolution();
                busSolutions[i].FromJSON(busSolutionsJ[i])
            }
            while (busSolutionsJ[i] && busSolutionsJ[i].length > 0) {
                var busSolutionJ = busSolutionsJ.pop();
                if (busSolutionJ.routings) {
                    while (busSolutionJ.routings.length > 0) {
                        var busRoutingJ = busSolutionJ.routings.pop();
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
                        busRoutingJ = null
                    }
                }
                if (busSolutionJ.startStops) {
                    while (busSolutionJ.startStops.length > 0) {
                        busSolutionJ.startStops.pop()
                    }
                }
                if (busSolutionJ.endStops) {
                    while (busSolutionJ.endStops.length > 0) {
                        busSolutionJ.endStops.pop()
                    }
                }
                busSolutionJ = null
            }
            busSolutionsJ = null;
            if (onComplete) {
                onComplete(busSolutions, userContext)
            }
            if (busSolutions) {
                while (busSolutions.length > 0) {
                    var busSolution = busSolutions.pop();
                    busSolution.Destroy();
                    busSolution = null
                }
                busSolutions = null
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
    }
    this.GetBusSolutionByIds = function(ids, busSolutionParam, onComplete, onError, userContext) {
        _GetBusSolutionBase("GetBusSolutionByIds", ["ids", "busSolutionParam"], [ids, busSolutionParam], onComplete, onError, userContext)
    };
    this.GetBusSolutionByNames = function(names, busSolutionParam, onComplete, onError, userContext) {
        _GetBusSolutionBase("GetBusSolutionByNames", ["names", "busSolutionParam"], [names, busSolutionParam], onComplete, onError, userContext)
    };
    this.GetBusSolutionByPoints = function(points, tolerance, busSolutionParam, onComplete, onError, userContext) {
        _GetBusSolutionBase("GetBusSolutionByPoints", ["points", "tolerance", "busSolutionParam"], [points, tolerance, busSolutionParam], onComplete, onError, userContext)
    };
    function _FuzzyFindBusStopBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var busStopsJ = eval("(" + responseText + ")");
            if (!busStopsJ) {
                if (onComplete) {
                    onComplete(busStopsJ, userContext)
                }
                return
            }
            var busStops = new Array();
            for (var i = 0; i < busStopsJ.length; i++) {
                busStops[i] = new SuperMap.IS.BusStop();
                busStops[i].FromJSON(busStopsJ[i])
            }
            while (busStopsJ.length > 0) {
                var busStopJ = busStopsJ.pop();
                if (busStopJ.Location) {
                    busStopJ.Location = null
                }
                busStopJ = null
            }
            busStopsJ = null;
            if (onComplete) {
                onComplete(busStops, userContext)
            }
            for (var i = 0; i < busStops.length; i++) {
                busStops.pop().Destroy()
            }
            busStops = null
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
    this.FuzzyFindBusStop = function(fuzzyStopName, onComplete, onError, userContext) {
        _FuzzyFindBusStopBase("FuzzyFindBusStop", ["fuzzyStopName"], [fuzzyStopName], onComplete, onError, userContext)
    };
    function _FuzzyFindBusLineBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var busLinesJ = eval("(" + responseText + ")");
            if (!busLinesJ) {
                if (onComplete) {
                    onComplete(busLinesJ, userContext)
                }
                return
            }
            var busLines = new Array();
            for (var i = 0; i < busLinesJ.length; i++) {
                busLines[i] = new SuperMap.IS.BusLine();
                busLines[i].FromJSON(busLinesJ[i])
            }
            while (busLinesJ.length > 0) {
                var busLineJ = busLinesJ.pop();
                if (busLineJ && busLineJ.points) {
                    while (busLineJ.points.length > 0) {
                        busLineJ.points.pop()
                    }
                }
                busLineJ = null
            }
            busLinesJ = null;
            if (onComplete) {
                onComplete(busLines, userContext)
            }
            while (busLines.length > 0) {
                var busLine = busLines.pop();
                busLine.Destroy();
                busLine = null
            }
            busLines = null
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
    this.FuzzyFindBusLine = function(fuzzyBusLineName, onComplete, onError, userContext) {
        _FuzzyFindBusLineBase("FuzzyFindBusLine", ["fuzzyBusLineName"], [fuzzyBusLineName], onComplete, onError, userContext)
    };
    function _GetBusLinesByStopBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var busLinesJ = eval("(" + responseText + ")");
            if (!busLinesJ) {
                if (onComplete) {
                    onComplete(busLinesJ, userContext)
                }
                return
            }
            var busLines = new Array();
            if (busLinesJ) {
                for (var i = 0; i < busLinesJ.length; i++) {
                    busLines[i] = new SuperMap.IS.BusLine();
                    busLines[i].FromJSON(busLinesJ[i])
                }
            }
            while (busLinesJ.length > 0) {
                var busLineJ = busLinesJ.pop();
                if (busLineJ && busLineJ.points) {
                    while (busLineJ.points.length > 0) {
                        busLineJ.points.pop()
                    }
                }
                busLineJ = null
            }
            busLinesJ = null;
            if (onComplete) {
                onComplete(busLines, userContext)
            }
            for (var i = 0; i < busLines.length; i++) {
                busLines.pop().Destroy()
            }
            busLines = null
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
    this.GetBusLinesByStopID = function(interchangeID, onComplete, onError, userContext) {
        _GetBusLinesByStopBase("GetBusLinesByStopID", ["interchangeID"], [interchangeID], onComplete, onError, userContext)
    };
    this.GetBusLinesByStopName = function(stopName, onComplete, onError, userContext) {
        _GetBusLinesByStopBase("GetBusLinesByStopName", ["stopName"], [stopName], onComplete, onError, userContext)
    };
    function _GetFollowingStopsBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var busStopsJ = eval("(" + responseText + ")");
            if (!busStopsJ) {
                if (onComplete) {
                    onComplete(busStopsJ, userContext)
                }
                return
            }
            var busStops = new Array();
            for (var i = 0; i < busStopsJ.length; i++) {
                busStops[i] = new SuperMap.IS.BusStop();
                busStops[i].FromJSON(busStopsJ[i])
            }
            while (busStopsJ.length > 0) {
                var busStopJ = busStopsJ.pop();
                if (busStopJ.Location) {
                    busStopJ.Location = null
                }
                busStopJ = null
            }
            if (onComplete) {
                onComplete(busStops, userContext)
            }
            for (var i = 0; i < busStops.length; i++) {
                busStops.pop().Destroy()
            }
            busStops = null
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
    this.GetFollowingStops = function(busLineID, bForward, onComplete, onError, userContext) {
        _GetFollowingStopsBase("GetFollowingStops", ["busLineID", "bForward"], [busLineID, bForward], onComplete, onError, userContext)
    };
    function _BufferQueryBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var resultSetJ = eval("(" + responseText + ")");
            var resultSet = new SuperMap.IS.ResultSet();
            resultSet.FromJSON(resultSetJ);
            _ChangeTrackingLayerKeyInternal(resultSet.trackingLayerIndex, resultSet.userID, true);
            if (onComplete) {
                onComplete(resultSet, userContext);
                resultSet.Destroy();
                resultSet = null
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
    }
    this.BufferQuery = function(bufferParam, queryParam, onComplete, onError, userContext) {
        _BufferQueryBase("BufferQuery", ["bufferParam", "queryParam", "trackingLayerIndex", "userID"], [bufferParam, queryParam, _trackingLayerIndex, _userID], onComplete, onError, userContext)
    };
    function _ClosestFacilityBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            var proximityResultJ = eval("(" + responseText + ")");
            if (proximityResultJ == null) {
                onComplete(null, userContext);
                return
            }
            var proximityResult = new SuperMap.IS.ProximityResult();
            proximityResult.FromJSON(proximityResultJ);
            _ChangeTrackingLayerKeyInternal(proximityResult.trackingLayerIndex, proximityResult.userID, true);
            if (onComplete) {
                onComplete(proximityResult, userContext);
                proximityResult.Destroy();
                proximityResult = null
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
    }
    this.ClosestFacility = function(point, proximityParam, onComplete, onError, userContext) {
        _ClosestFacilityBase("ClosestFacility", ["point", "proximityParam", "trackingLayerIndex", "userID"], [point, proximityParam, _trackingLayerIndex, _userID], onComplete, onError, userContext)
    };
    function _ShowBusBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var mapParam = eval("(" + responseText + ")");
            _ChangeTrackingLayerKeyInternal(mapParam.trackingLayerIndex, _userID, false);
            _TriggerEvent("onviewboundschanged", mapParam.viewBounds);
            if (onComplete) {
                onComplete(mapParam.viewBounds, userContext)
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
    }
    this.ShowBusStop = function(id, style, onComplete, onError, userContext) {
        _ShowBusBase("AddBusStopToTrackingLayer", ["id", "style", "trackingLayerIndex", "userID"], [id, style, _trackingLayerIndex, _userID], onComplete, onError, userContext)
    };
    this.ShowBusLine = function(id, style, onComplete, onError, userContext) {
        _ShowBusBase("AddBusLineToTrackingLayer", ["id", "style", "trackingLayerIndex", "userID"], [id, style, _trackingLayerIndex, _userID], onComplete, onError, userContext)
    };
    this.ShowRouting = function(busRouting, stopStyle, lineStyle, onComplete, onError, userContext) {
        _ShowBusBase("AddRoutingToTrackingLayer", ["busRouting", "stopStyle", "lineStyle", "trackingLayerIndex", "userID"], [busRouting, stopStyle, lineStyle, _trackingLayerIndex, _userID], onComplete, onError, userContext)
    };
    function _UpdateBusLinePriorityBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var success = eval("(" + responseText + ")");
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
    }
    this.UpdateBusLinePriority = function(busLineSmID, priority, onComplete, onError, userContext) {
        _UpdateBusLinePriorityBase("UpdateBusLinePriority", ["busLineSmID", "priority"], [busLineSmID, priority], onComplete, onError, userContext)
    };
    this.UpdateBusLinesPriority = function(busLineSmIDs, priority, onComplete, onError, userContext) {
        _UpdateBusLinePriorityBase("UpdateBusLinesPriority", ["busLineSmIDs", "priority"], [busLineSmIDs, priority], onComplete, onError, userContext)
    };
    function _OverlayAnalystBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var overlayResultJ = eval("(" + responseText + ")");
            if (!overlayResultJ) {
                onComplete(null, userContext);
                return
            }
            var overlayResult = new SuperMap.IS.OverlayResult();
            overlayResult.FromJSON(overlayResultJ);
            if (onComplete) {
                onComplete(overlayResult, userContext)
            }
            overlayResult.Destroy();
            overlayResult = null
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
    this.OverlayAnalyst = function(overlayParam, onComplete, onError, userContext) {
        _OverlayAnalystBase("OverlayAnalyst", ["overlayParam"], [overlayParam], onComplete, onError, userContext)
    };
    function _InterpolateBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var resultJ = eval("(" + responseText + ")");
            if (!resultJ) {
                onComplete(null, userContext);
                return
            }
            var result = new SuperMap.IS.InterpolateResult();
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
    }
    this.Interpolate = function(interpolateParam, onComplete, onError, userContext) {
        _InterpolateBase("Interpolate", ["interpolateParam"], [interpolateParam], onComplete, onError, userContext)
    };
    function _GeneralizeBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var resultJ = eval("(" + responseText + ")");
            if (!resultJ) {
                onComplete(null, userContext);
                return
            }
            var result = new SuperMap.IS.GeneralizeResult();
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
    }
    this.Aggregate = function(aggregateParam, onComplete, onError, userContext) {
        _GeneralizeBase("Aggregate", ["aggregateParam"], [aggregateParam], onComplete, onError, userContext)
    };
    this.Reclass = function(reclassParam, onComplete, onError, userContext) {
        _GeneralizeBase("Reclass", ["reclassParam"], [reclassParam], onComplete, onError, userContext)
    };
    this.Replace = function(replaceParam, onComplete, onError, userContext) {
        _GeneralizeBase("Replace", ["replaceParam"], [replaceParam], onComplete, onError, userContext)
    };
    this.Resample = function(resampleParam, onComplete, onError, userContext) {
        _GeneralizeBase("Resample", ["resampleParam"], [resampleParam], onComplete, onError, userContext)
    };
    function _IsoLineBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var resultJ = eval("(" + responseText + ")");
            if (!resultJ) {
                onComplete(null, userContext);
                return
            }
            var result = new SuperMap.IS.IsoLineResult();
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
    }
    this.IsoLineByGridDataset = function(gridDataset, isoLineParam, onComplete, onError, userContext) {
        _IsoLineBase("IsoLineByGridDataset", ["gridDataset", "isoLineParam"], [gridDataset, isoLineParam], onComplete, onError, userContext)
    };
    this.IsoLineByPointDataset = function(pointDataset, pointValueField, interpolateType, gridResolution, isoLineParam, onComplete, onError, userContext) {
        _IsoLineBase("IsoLineByPointDataset", ["pointDataset", "pointValueField", "interpolateType", "gridResolution", "isoLineParam"], [pointDataset, pointValueField, interpolateType, gridResolution, isoLineParam], onComplete, onError, userContext)
    };
    this.IsoLineByPointPositions = function(pointPositions, pointValues, interpolateType, gridResolution, isoLineParam, onComplete, onError, userContext) {
        _IsoLineBase("IsoLineByPointPositions", ["pointPositions", "pointValues", "interpolateType", "gridResolution", "isoLineParam"], [pointPositions, pointValues, interpolateType, gridResolution, isoLineParam], onComplete, onError, userContext)
    };
    function _GridClipBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var resultJ = eval("(" + responseText + ")");
            if (!resultJ) {
                onComplete(null, userContext);
                return
            }
            var result = new SuperMap.IS.GridClipResult();
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
    }
    this.GridClip = function(gridClipParam, onComplete, onError, userContext) {
        _GridClipBase("GridClip", ["gridClipParam"], [gridClipParam], onComplete, onError, userContext)
    };
    function _IsoRegionBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var resultJ = eval("(" + responseText + ")");
            if (!resultJ) {
                onComplete(null, userContext);
                return
            }
            var result = new SuperMap.IS.IsoRegionResult();
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
    }
    this.IsoRegion = function(isoRegionParam, onComplete, onError, userContext) {
        _IsoRegionBase("IsoRegion", ["isoRegionParam"], [isoRegionParam], onComplete, onError, userContext)
    };
    function _GridValueOperateBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var result = eval("(" + responseText + ")");
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
    }
    this.GetGridValue = function(layerName, position, onComplete, onError, userContext) {
        _GridValueOperateBase("GetGridValue", ["layerName", "position"], [layerName, position], onComplete, onError, userContext)
    };
    this.GetGridPixelValue = function(layerName, position, onComplete, onError, userContext) {
        _GridValueOperateBase("GetGridPixelValue", ["layerName", "position"], [layerName, position], onComplete, onError, userContext)
    };
    this.SetGridValue = function(layerName, position, value, onComplete, onError, userContext) {
        _GridValueOperateBase("SetGridValue", ["layerName", "position", "value"], [layerName, position, value], onComplete, onError, userContext)
    };
    this.SetGridPixelValue = function(layerName, position, value, onComplete, onError, userContext) {
        _GridValueOperateBase("SetGridPixelValue", ["layerName", "position", "value"], [layerName, position, value], onComplete, onError, userContext)
    };
    function _FloodBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var resultJ = eval("(" + responseText + ")");
            if (!resultJ) {
                onComplete(null, userContext);
                return
            }
            var result = new SuperMap.IS.FloodResult();
            result.FromJSON(resultJ);
            _ChangeTrackingLayerKeyInternal(result.trackingLayerIndex, result.userID, true);
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
    }
    this.Flood = function(floodParam, onComplete, onError, userContext) {
        _FloodBase("Flood", ["floodParam", "trackingLayerIndex", "userID"], [floodParam, _trackingLayerIndex, _userID], onComplete, onError, userContext)
    };
    function _CutFillBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var resultJ = eval("(" + responseText + ")");
            if (!resultJ) {
                onComplete(null, userContext);
                return
            }
            var result = new SuperMap.IS.CutFillResult();
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
    }
    this.CutFill = function(cutFillParam, onComplete, onError, userContext) {
        _CutFillBase("CutFill", ["cutFillParam"], [cutFillParam], onComplete, onError, userContext)
    };
    function _CalculateViewShedBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var resultJ = eval("(" + responseText + ")");
            if (!resultJ) {
                onComplete(null, userContext);
                return
            }
            var result = new SuperMap.IS.CalculateViewShedResult();
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
    }
    this.CalculateViewShed = function(calculateViewShedParam, onComplete, onError, userContext) {
        _CalculateViewShedBase("CalculateViewShed", ["calculateViewShedParam"], [calculateViewShedParam], onComplete, onError, userContext)
    };
    function _AspectBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var resultJ = eval("(" + responseText + ")");
            if (!resultJ) {
                onComplete(null, userContext);
                return
            }
            var result = new SuperMap.IS.AspectResult();
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
    }
    this.Aspect = function(aspectParam, onComplete, onError, userContext) {
        _AspectBase("Aspect", ["aspectParam"], [aspectParam], onComplete, onError, userContext)
    };
    function _SlopeBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var resultJ = eval("(" + responseText + ")");
            if (!resultJ) {
                onComplete(null, userContext);
                return
            }
            var result = new SuperMap.IS.SlopeResult();
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
    }
    this.Slope = function(slopeParam, onComplete, onError, userContext) {
        _SlopeBase("Slope", ["slopeParam"], [slopeParam], onComplete, onError, userContext)
    };
    function _SurfaceAreaBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var resultJ = eval("(" + responseText + ")");
            if (!resultJ) {
                onComplete(null, userContext);
                return
            }
            var result = new SuperMap.IS.SurfaceAreaResult();
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
    }
    this.SurfaceArea = function(surfaceAreaParam, onComplete, onError, userContext) {
        _SurfaceAreaBase("SurfaceArea", ["surfaceAreaParam"], [surfaceAreaParam], onComplete, onError, userContext)
    };
    function _SurfaceLengthBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var resultJ = eval("(" + responseText + ")");
            if (!resultJ) {
                onComplete(null, userContext);
                return
            }
            var result = new SuperMap.IS.SurfaceLengthResult();
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
    }
    this.SurfaceLength = function(surfaceLengthParam, onComplete, onError, userContext) {
        _SurfaceLengthBase("SurfaceLength", ["surfaceLengthParam"], [surfaceLengthParam], onComplete, onError, userContext)
    };
    function _GridCompareBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var resultJ = eval("(" + responseText + ")");
            if (!resultJ) {
                onComplete(null, userContext);
                return
            }
            var result = new SuperMap.IS.GridCompareResult();
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
    }
    this.GridCompare = function(gridCompareParam, onComplete, onError, userContext) {
        _GridCompareBase("GridCompare", ["gridCompareParam"], [gridCompareParam], onComplete, onError, userContext)
    };
    function _NeighbourStatisticsBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var resultJ = eval("(" + responseText + ")");
            if (!resultJ) {
                onComplete(null, userContext);
                return
            }
            var result = new SuperMap.IS.NeighbourStatisticsResult();
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
    }
    this.NeighbourStatistics = function(neighbourStatisticsParam, onComplete, onError, userContext) {
        _NeighbourStatisticsBase("NeighbourStatistics", ["neighbourStatisticsParam"], [neighbourStatisticsParam], onComplete, onError, userContext)
    };
    function _OverlayStatisticsBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var resultJ = eval("(" + responseText + ")");
            if (!resultJ) {
                onComplete(null, userContext);
                return
            }
            var result = new SuperMap.IS.OverlayStatisticsResult();
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
    }
    this.OverlayStatistics = function(overlayStatisticsParam, onComplete, onError, userContext) {
        _OverlayStatisticsBase("OverlayStatistics", ["overlayStatisticsParam"], [overlayStatisticsParam], onComplete, onError, userContext)
    };
    function _GridExecuteBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var resultJ = eval("(" + responseText + ")");
            if (!resultJ) {
                onComplete(null, userContext);
                return
            }
            var result = new SuperMap.IS.GridExecuteResult();
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
    }
    this.GridExecute = function(gridExecuteParam, onComplete, onError, userContext) {
        _GridExecuteBase("GridExecute", ["gridExecuteParam"], [gridExecuteParam], onComplete, onError, userContext)
    };
    function _IsVisibleBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var resultJ = eval("(" + responseText + ")");
            if (!resultJ) {
                onComplete(null, userContext);
                return
            }
            var result = new SuperMap.IS.IsVisibleResult();
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
    }
    this.IsVisible = function(isVisibleParam, onComplete, onError, userContext) {
        _IsVisibleBase("IsVisible", ["isVisibleParam"], [isVisibleParam], onComplete, onError, userContext)
    };
    function _SurfaceProfileBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var resultJ = eval("(" + responseText + ")");
            if (!resultJ) {
                onComplete(null, userContext);
                return
            }
            var result = new SuperMap.IS.SurfaceProfileResult();
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
    }
    this.SurfaceProfile = function(surfaceProfileParam, onComplete, onError, userContext) {
        _SurfaceProfileBase("SurfaceProfile", ["surfaceProfileParam"], [surfaceProfileParam], onComplete, onError, userContext)
    };
    function _BasinBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var resultJ = eval("(" + responseText + ")");
            if (!resultJ) {
                onComplete(null, userContext);
                return
            }
            var result = new SuperMap.IS.BasinResult();
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
    }
    this.Basin = function(basinParam, onComplete, onError, userContext) {
        _BasinBase("Basin", ["basinParam"], [basinParam], onComplete, onError, userContext)
    };
    function _WaterShedBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var resultJ = eval("(" + responseText + ")");
            if (!resultJ) {
                onComplete(null, userContext);
                return
            }
            var result = new SuperMap.IS.WaterShedResult();
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
    }
    this.WaterShed = function(waterShedParam, onComplete, onError, userContext) {
        _WaterShedBase("WaterShed", ["waterShedParam"], [waterShedParam], onComplete, onError, userContext)
    };
    function _FillSinkBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var resultJ = eval("(" + responseText + ")");
            if (!resultJ) {
                onComplete(null, userContext);
                return
            }
            var result = new SuperMap.IS.FillSinkResult();
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
    }
    this.FillSink = function(fillSinkParam, onComplete, onError, userContext) {
        _FillSinkBase("FillSink", ["fillSinkParam"], [fillSinkParam], onComplete, onError, userContext)
    };
    function _FlowDirectionBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var resultJ = eval("(" + responseText + ")");
            if (!resultJ) {
                onComplete(null, userContext);
                return
            }
            var result = new SuperMap.IS.FlowDirectionResult();
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
    }
    this.FlowDirection = function(flowDirectionParam, onComplete, onError, userContext) {
        _FlowDirectionBase("FlowDirection", ["flowDirectionParam"], [flowDirectionParam], onComplete, onError, userContext)
    };
    function _FlowAccumulationBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var resultJ = eval("(" + responseText + ")");
            if (!resultJ) {
                onComplete(null, userContext);
                return
            }
            var result = new SuperMap.IS.FlowAccumulationResult();
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
    }
    this.FlowAccumulation = function(flowAccumulationParam, onComplete, onError, userContext) {
        _FlowAccumulationBase("FlowAccumulation", ["flowAccumulationParam"], [flowAccumulationParam], onComplete, onError, userContext)
    };
    function _RasterToVectorBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var resultJ = eval("(" + responseText + ")");
            if (!resultJ) {
                onComplete(null, userContext);
                return
            }
            var result = new SuperMap.IS.RasterToVectorResult();
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
    }
    this.RasterToVector = function(rasterToVectorParam, onComplete, onError, userContext) {
        _RasterToVectorBase("RasterToVector", ["rasterToVectorParam"], [rasterToVectorParam], onComplete, onError, userContext)
    };
    function _ServiceAreaBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var result = eval("(" + responseText + ")");
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
    }
    this.ServiceAreaByPoints = function(centerPoints, serviceRanges, isFromSite, notOverlap, resultRouteName, serviceAreaName, networkParams, onComplete, onError, userContext) {
        _ServiceAreaBase("ServiceAreaByPoints", ["centerPoints", "serviceRanges", "isFromSite", "notOverlap", "resultRouteName", "serviceAreaName", "networkParams"], [centerPoints, serviceRanges, isFromSite, notOverlap, resultRouteName, serviceAreaName, networkParams], onComplete, onError, userContext)
    };
    this.ServiceAreaByIds = function(centerIds, serviceRanges, isFromSite, notOverlap, resultRouteName, serviceAreaName, networkParams, onComplete, onError, userContext) {
        _ServiceAreaBase("ServiceAreaByIds", ["centerIds", "serviceRanges", "isFromSite", "notOverlap", "resultRouteName", "serviceAreaName", "networkParams"], [centerIds, serviceRanges, isFromSite, notOverlap, resultRouteName, serviceAreaName, networkParams], onComplete, onError, userContext)
    };
    function _CoordTransformateBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                if (onComplete) {
                    onComplete(null, userContext)
                }
                return
            }
            var result = eval("(" + responseText + ")");
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
    }
    this.CoordTransformate = function(coords, sourceCoordSys, targetCoordSys, onComplete, onError, userContext) {
        _CoordTransformateBase("CoordTransformate", ["coords", "sourceCoordSys", "targetCoordSys"], [coords, sourceCoordSys, targetCoordSys], onComplete, onError, userContext)
    };
    function _ChangeTrackingLayerKey(trackingLayerIndex, userID) {
        _trackingLayerIndex = trackingLayerIndex;
        _userID = userID
    }
    function _ChangeTrackingLayerKeyInternal(trackingLayerIndex, userID, bSaveHistory) {
        if (_trackingLayerIndex != trackingLayerIndex || _userID != userID) {
            _trackingLayerIndex = trackingLayerIndex;
            _userID = userID;
            var param = new Object();
            param.trackingLayerIndex = _trackingLayerIndex;
            param.userID = _userID;
            param.bSaveHistory = bSaveHistory;
            _TriggerEvent("onchangetrackinglayer", param)
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
    function _GenerateEventArg(error, e) {
        var param = new Object();
        param.trackingLayerIndex = _trackingLayerIndex;
        param.userID = _userID;
        if (!error) {
            error = ""
        }
        return new EventArguments(param, error, e)
    }
    this.AttachEvent = _AttachEvent;
    this.DetachEvent = _DetachEvent;
    this.ChangeTrackingLayerKey = _ChangeTrackingLayerKey
}
SuperMap.IS.EditManager = function(mapHandler, mapName) {
    var queryUrl = mapHandler + "edit.ashx";
    var onComplete = null;
    var onError = null;
    function _RegisterHandler(onQueryComplete, onQueryError) {
        onComplete = onQueryComplete;
        onError = onQueryError
    }
    this.RegisterHandler = _RegisterHandler;
    function _EditBase(mapName, methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                return
            }
            var editResultJ = eval("(" + responseText + ")");
            if (editResultJ == null) {
                onComplete(null, userContext);
                return
            }
            var editResult = new SuperMap.IS.EditResult();
            editResult.FromJSON(editResultJ);
            if (onComplete) {
                onComplete(editResult, userContext)
            }
            editResult.Destroy();
            editResult = null
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
    this.AddEntity = function(mapName, layerName, entities, onComplete, onError, userContext) {
        _EditBase(mapName, "AddEntity", ["layerName", "entities"], [layerName, entities], onComplete, onError, userContext)
    };
    this.AddEntityByUserID = function(mapName, layerName, entities, userID, onComplete, onError, userContext) {
        _EditBase(mapName, "AddEntity", ["layerName", "entities", "userID"], [layerName, entities, userID], onComplete, onError, userContext)
    };
    this.DeleteEntity = function(mapName, layerName, ids, onComplete, onError, userContext) {
        _EditBase(mapName, "DeleteEntity", ["layerName", "ids"], [layerName, ids], onComplete, onError, userContext)
    };
    this.DeleteEntityByUserID = function(mapName, layerName, ids, userID, onComplete, onError, userContext) {
        _EditBase(mapName, "DeleteEntity", ["layerName", "ids", "userID"], [layerName, ids, userID], onComplete, onError, userContext)
    };
    this.UpdateEntity = function(mapName, layerName, entities, onComplete, onError, userContext) {
        _EditBase(mapName, "UpdateEntity", ["layerName", "entities"], [layerName, entities], onComplete, onError, userContext)
    };
    this.UpdateEntityByUserID = function(mapName, layerName, entities, userID, onComplete, onError, userContext) {
        _EditBase(mapName, "UpdateEntity", ["layerName", "entities", "userID"], [layerName, entities, userID], onComplete, onError, userContext)
    };
    this.AddPoint = function(mapName, layerName, point, fieldNames, fieldValues, onComplete, onError, userContext) {
        var entity = new SuperMap.IS.Entity();
        entity.shape = new SuperMap.IS.Geometry();
        entity.shape.parts = new Array();
        entity.shape.parts[0] = 1;
        entity.shape.feature = SuperMap.IS.LayerType.point;
        entity.shape.points = new Array(point);
        entity.fieldNames = fieldNames;
        entity.fieldValues = fieldValues;
        var entities = new Array(entity);
        _EditBase(mapName, "AddEntity", ["layerName", "entities"], [layerName, entities], onComplete, onError, userContext)
    };
    this.AddPointByUserID = function(mapName, layerName, point, fieldNames, fieldValues, userID, onComplete, onError, userContext) {
        var entity = new SuperMap.IS.Entity();
        entity.shape = new SuperMap.IS.Geometry();
        entity.shape.parts = new Array();
        entity.shape.parts[0] = 1;
        entity.shape.feature = SuperMap.IS.LayerType.point;
        entity.shape.points = new Array(point);
        entity.fieldNames = fieldNames;
        entity.fieldValues = fieldValues;
        var entities = new Array(entity);
        _EditBase(mapName, "AddEntityByUserID", ["layerName", "entities", "userID"], [layerName, entities, userID], onComplete, onError, userContext)
    };
    this.AddLine = function(mapName, layerName, points, fieldNames, fieldValues, onComplete, onError, userContext) {
        var entity = new SuperMap.IS.Entity();
        entity.shape = new SuperMap.IS.Geometry();
        entity.shape.parts = new Array();
        entity.shape.parts[0] = points.length;
        entity.shape.feature = SuperMap.IS.LayerType.line;
        entity.shape.points = points;
        entity.fieldNames = fieldNames;
        entity.fieldValues = fieldValues;
        var entities = new Array(entity);
        _EditBase(mapName, "AddEntity", ["layerName", "entities"], [layerName, entities], onComplete, onError, userContext)
    };
    this.AddLineByUserID = function(mapName, layerName, points, fieldNames, fieldValues, userID, onComplete, onError, userContext) {
        var entity = new SuperMap.IS.Entity();
        entity.shape = new SuperMap.IS.Geometry();
        entity.shape.parts = new Array();
        entity.shape.parts[0] = points.length;
        entity.shape.feature = SuperMap.IS.LayerType.line;
        entity.shape.points = points;
        entity.fieldNames = fieldNames;
        entity.fieldValues = fieldValues;
        var entities = new Array(entity);
        _EditBase(mapName, "AddEntityByUserID", ["layerName", "entities", "userID"], [layerName, entities, userID], onComplete, onError, userContext)
    };
    this.AddPolygon = function(mapName, layerName, points, fieldNames, fieldValues, onComplete, onError, userContext) {
        var entity = new SuperMap.IS.Entity();
        entity.shape = new SuperMap.IS.Geometry();
        entity.shape.parts = new Array();
        entity.shape.parts[0] = points.length;
        entity.shape.feature = SuperMap.IS.LayerType.polygon;
        entity.shape.points = points;
        entity.fieldNames = fieldNames;
        entity.fieldValues = fieldValues;
        var entities = new Array(entity);
        _EditBase(mapName, "AddEntity", ["layerName", "entities"], [layerName, entities], onComplete, onError, userContext)
    };
    this.AddPolygonByUserID = function(mapName, layerName, points, fieldNames, fieldValues, userID, onComplete, onError, userContext) {
        var entity = new SuperMap.IS.Entity();
        entity.shape = new SuperMap.IS.Geometry();
        entity.shape.parts = new Array();
        entity.shape.parts[0] = points.length;
        entity.shape.feature = SuperMap.IS.LayerType.polygon;
        entity.shape.points = points;
        entity.fieldNames = fieldNames;
        entity.fieldValues = fieldValues;
        var entities = new Array(entity);
        _EditBase(mapName, "AddEntityByUserID", ["layerName", "entities", "userID"], [layerName, entities, userID], onComplete, onError, userContext)
    };
    this.UpdatePoint = function(mapName, layerName, id, point, fieldNames, fieldValues, onComplete, onError, userContext) {
        var entity = new SuperMap.IS.Entity();
        entity.shape = new SuperMap.IS.Geometry();
        entity.shape.parts = new Array();
        entity.shape.parts[0] = 1;
        entity.id = id;
        entity.shape.feature = SuperMap.IS.LayerType.point;
        entity.shape.points = new Array(point);
        entity.fieldNames = fieldNames;
        entity.fieldValues = fieldValues;
        var entities = new Array(entity);
        _EditBase(mapName, "UpdateEntity", ["layerName", "entities"], [layerName, entities], onComplete, onError, userContext)
    };
    this.UpdatePointByUserID = function(mapName, layerName, id, point, fieldNames, fieldValues, userID, onComplete, onError, userContext) {
        var entity = new SuperMap.IS.Entity();
        entity.shape = new SuperMap.IS.Geometry();
        entity.shape.parts = new Array();
        entity.shape.parts[0] = 1;
        entity.id = id;
        entity.shape.feature = SuperMap.IS.LayerType.point;
        entity.shape.points = new Array(point);
        entity.fieldNames = fieldNames;
        entity.fieldValues = fieldValues;
        var entities = new Array(entity);
        _EditBase(mapName, "UpdateEntityByUserID", ["layerName", "entities", "userID"], [layerName, entities, userID], onComplete, onError, userContext)
    };
    this.UpdateLine = function(mapName, layerName, id, points, fieldNames, fieldValues, onComplete, onError, userContext) {
        var entity = new SuperMap.IS.Entity();
        entity.shape = new SuperMap.IS.Geometry();
        entity.shape.parts = new Array();
        entity.shape.parts[0] = points.length;
        entity.id = id;
        entity.shape.feature = SuperMap.IS.LayerType.line;
        entity.shape.points = points;
        entity.fieldNames = fieldNames;
        entity.fieldValues = fieldValues;
        var entities = new Array(entity);
        _EditBase(mapName, "UpdateEntity", ["layerName", "entities"], [layerName, entities], onComplete, onError, userContext)
    };
    this.UpdateLineByUserID = function(mapName, layerName, id, points, fieldNames, fieldValues, userID, onComplete, onError, userContext) {
        var entity = new SuperMap.IS.Entity();
        entity.shape = new SuperMap.IS.Geometry();
        entity.shape.parts = new Array();
        entity.shape.parts[0] = points.length;
        entity.id = id;
        entity.shape.feature = SuperMap.IS.LayerType.line;
        entity.shape.points = points;
        entity.fieldNames = fieldNames;
        entity.fieldValues = fieldValues;
        var entities = new Array(entity);
        _EditBase(mapName, "UpdateEntityByUserID", ["layerName", "entities", "userID"], [layerName, entities, userID], onComplete, onError, userContext)
    };
    this.UpdatePolygon = function(mapName, layerName, id, points, fieldNames, fieldValues, onComplete, onError, userContext) {
        var entity = new SuperMap.IS.Entity();
        entity.shape = new SuperMap.IS.Geometry();
        entity.shape.parts = new Array();
        entity.shape.parts[0] = points.length;
        entity.id = id;
        entity.shape.feature = SuperMap.IS.LayerType.polygon;
        entity.shape.points = points;
        entity.fieldNames = fieldNames;
        entity.fieldValues = fieldValues;
        var entities = new Array(entity);
        _EditBase(mapName, "UpdateEntity", ["layerName", "entities"], [layerName, entities], onComplete, onError, userContext)
    };
    this.UpdatePolygonByUserID = function(mapName, layerName, id, points, fieldNames, fieldValues, userID, onComplete, onError, userContext) {
        var entity = new SuperMap.IS.Entity();
        entity.shape = new SuperMap.IS.Geometry();
        entity.shape.parts = new Array();
        entity.shape.parts[0] = points.length;
        entity.id = id;
        entity.shape.feature = SuperMap.IS.LayerType.polygon;
        entity.shape.points = points;
        entity.fieldNames = fieldNames;
        entity.fieldValues = fieldValues;
        var entities = new Array(entity);
        _EditBase(mapName, "UpdateEntityByUserID", ["layerName", "entities", "userID"], [layerName, entities, userID], onComplete, onError, userContext)
    };
    function _DatasetOperateBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                return
            }
            var resultJ = eval("(" + responseText + ")");
            if (resultJ == null) {
                onComplete(null, userContext);
                return
            }
            var result = new SuperMap.IS.DatasetOperateResult();
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
    }
    this.CopyDataset = function(copyDatasetParam, onComplete, onError, userContext) {
        _DatasetOperateBase("CopyDataset", ["copyDatasetParam"], [copyDatasetParam], onComplete, onError, userContext)
    };
    this.CopyDatasetByUserID = function(copyDatasetParam, userID, onComplete, onError, userContext) {
        _DatasetOperateBase("CopyDatasetByUserID", ["copyDatasetParam", "userID"], [copyDatasetParam, userID], onComplete, onError, userContext)
    };
    this.CreateDataset = function(createDatasetParam, onComplete, onError, userContext) {
        _DatasetOperateBase("CreateDataset", ["createDatasetParam"], [createDatasetParam], onComplete, onError, userContext)
    };
    this.CreateDatasetByUserID = function(createDatasetParam, userID, onComplete, onError, userContext) {
        _DatasetOperateBase("CreateDatasetByUserID", ["createDatasetParam", "userID"], [createDatasetParam, userID], onComplete, onError, userContext)
    };
    this.DeleteDataset = function(dataset, onComplete, onError, userContext) {
        _DatasetOperateBase("DeleteDataset", ["dataset"], [dataset], onComplete, onError, userContext)
    };
    this.DeleteDatasetByUserID = function(dataset, userID, onComplete, onError, userContext) {
        _DatasetOperateBase("DeleteDatasetByUserID", ["dataset", "userID"], [dataset, userID], onComplete, onError, userContext)
    };
    function _FieldOperateBase(methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                return
            }
            var resultJ = eval("(" + responseText + ")");
            if (resultJ == null) {
                onComplete(null, userContext);
                return
            }
            var result = new SuperMap.IS.FieldOperateResult();
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
    }
    this.CopyField = function(dataset, sourceFieldName, targetFieldName, onComplete, onError, userContext) {
        _FieldOperateBase("CopyField", ["dataset", "sourceFieldName", "targetFieldName"], [dataset, sourceFieldName, targetFieldName], onComplete, onError, userContext)
    };
    this.CopyFieldByUserID = function(dataset, sourceFieldName, targetFieldName, userID, onComplete, onError, userContext) {
        _FieldOperateBase("CopyFieldByUserID", ["dataset", "sourceFieldName", "targetFieldName", "userID"], [dataset, sourceFieldName, targetFieldName, userID], onComplete, onError, userContext)
    };
    this.CreateField = function(dataset, field, overwriteIfExists, onComplete, onError, userContext) {
        _FieldOperateBase("CreateField", ["dataset", "field", "overwriteIfExists"], [dataset, field, overwriteIfExists], onComplete, onError, userContext)
    };
    this.CreateFieldByUserID = function(dataset, field, overwriteIfExists, userID, onComplete, onError, userContext) {
        _FieldOperateBase("CreateFieldByUserID", ["dataset", "field", "overwriteIfExists", "userID"], [dataset, field, overwriteIfExists, userID], onComplete, onError, userContext)
    };
    this.DeleteField = function(dataset, fieldName, onComplete, onError, userContext) {
        _FieldOperateBase("DeleteField", ["dataset", "fieldName"], [dataset, fieldName], onComplete, onError, userContext)
    };
    this.DeleteFieldByUserID = function(dataset, fieldName, userID, onComplete, onError, userContext) {
        _FieldOperateBase("DeleteFieldByUserID", ["dataset", "fieldName", "userID"], [dataset, fieldName, userID], onComplete, onError, userContext)
    };
    this.SetField = function(dataset, fieldName, field, onComplete, onError, userContext) {
        _FieldOperateBase("SetField", ["dataset", "fieldName", "field"], [dataset, fieldName, field], onComplete, onError, userContext)
    };
    this.SetFieldByUserID = function(dataset, fieldName, field, userID, onComplete, onError, userContext) {
        _FieldOperateBase("SetFieldByUserID", ["dataset", "fieldName", "field", "userID"], [dataset, fieldName, field, userID], onComplete, onError, userContext)
    };
    this.UpdateFieldValues = function(dataset, fieldName, expression, filter, onComplete, onError, userContext) {
        _FieldOperateBase("UpdateFieldValues", ["dataset", "fieldName", "expression", "filter"], [dataset, fieldName, expression, filter], onComplete, onError, userContext)
    };
    this.UpdateFieldValuesByUserID = function(dataset, fieldName, expression, filter, userID, onComplete, onError, userContext) {
        _FieldOperateBase("UpdateFieldValuesByUserID", ["dataset", "fieldName", "expression", "filter", "userID"], [dataset, fieldName, expression, filter, userID], onComplete, onError, userContext)
    };
    function _LockLayerBase(mapName, methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
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
    }
    this.LockLayer = function(mapName, layerName, clientID, onComplete, onError, userContext) {
        _LockLayerBase(mapName, "LockLayer", ["layerName", "clientID"], [layerName, clientID], onComplete, onError, userContext)
    };
    function _UnlockLayerBase(mapName, methodName, paramNames, paramValues, onComplete, onError, userContext) {
        function onRequestComplete(responseText) {
            if (!responseText) {
                return
            }
            var result = eval("(" + responseText + ")");
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
    }
    this.UnlockLayer = function(mapName, layerName, userID, onComplete, onError, userContext) {
        _UnlockLayerBase(mapName, "UnlockLayer", ["layerName", "userID"], [layerName, userID], onComplete, onError, userContext)
    }
}