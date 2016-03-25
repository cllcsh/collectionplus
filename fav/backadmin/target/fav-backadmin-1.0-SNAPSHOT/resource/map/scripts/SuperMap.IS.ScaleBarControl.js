﻿SuperMap.IS.ScaleBarControl = function(Z, U, C) {
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
    this.sliderImageUrl = "images/slider.gif";
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
    this.zoomBarImageUrl = "images/zoom-bg-intersected.gif";
    if (C && typeof(C.zoomBarImageUrl) != "undefined") {
        this.zoomBarImageUrl = C.zoomBarImageUrl
    }
    this.zoomOutImageUrl = "images/ZoomOut.gif";
    if (C && typeof(C.zoomOutImageUrl) != "undefined") {
        this.zoomOutImageUrl = C.zoomOutImageUrl
    }
    this.zoomInImageUrl = "images/ZoomIn.gif";
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