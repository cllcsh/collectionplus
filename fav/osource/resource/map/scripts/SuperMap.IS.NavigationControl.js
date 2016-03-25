// ================================================================================
// SuperMap IS .NET 客户端程序，版权所有，北京超图软件股份有限公司，2000-2008。
// 本程序只能在有效的授权许可下使用。未经许可，不得以任何手段擅自使用或传播。 
// 作 者:  SuperMap IS Web Team
// 版 本:  $Id: SuperMap.IS.NavigationControl.js,v 1.20.2.2 2009/09/10 00:17:06 zengyw Exp $
// 文 件:  SuperMap.IS.NavigationControl.js
// 描 述:  AjaxScripts NavigationControl 类 
// 更 新:  $Date: 2009/09/10 00:17:06 $
// ================================================================================
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
        F.backgroundUrl = "images/navigation.gif";
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