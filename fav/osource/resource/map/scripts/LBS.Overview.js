function Overviewshow()
{
    var indexBox = document.createElement("div");
    indexBox.style.width = '0px';
    indexBox.style.height = '0px';
    indexBox.style.border = 'solid red 0px';
    indexBox.style.display = 'block';
    indexBox.style.position = "absolute";
    indexBox.style.left = "0px";
    indexBox.style.top = "0px";
    indexBox.style.zIndex = "101";
    indexBox.id = "indexBox";
    var map2 = document.getElementById("MapControl2");
    map2.appendChild(indexBox);
//    var mp = MapControl1.GetMapParam();
//    var mpzoom = mp.zoomLevel;
//    var mbo;var plbo;var prto;
//    if (MapControl2)
//    {
//        mbo = MapControl1.GetViewBounds();
//        plbo = MapControl2.MapCoordToPixel(mbo.leftBottom);
//        prto = MapControl2.MapCoordToPixel(mbo.rightTop);
//        if (mpzoom!=1)
//        {
//            indexBox.style.width = (prto.x - plbo.x).toString() + 'px';
//            indexBox.style.height = (plbo.y - prto.y).toString() + 'px';
//            indexBox.style.left = plbo.x.toString() + 'px';
//            indexBox.style.top = prto.y.toString() + 'px';
//            indexBox.style.border = 'solid red 2px';
//        }
//    }
    AttachEvent();
//    var indexBox = document.createElement("div");
//    indexBox.style.width = "74px";
//    indexBox.style.height = "50px";
//    indexBox.style.border = "solid red 1px";
//    indexBox.style.display = "block";
//    indexBox.style.position = "absolute";
//    indexBox.style.left = "0px";
//    indexBox.style.top = "0px";
//    indexBox.style.zIndex = "101";
//    indexBox.id = "indexBox";
//    var map2 = document.getElementById("MapControl2");
//    ChangeOverview();
//    map2.appendChild(indexBox);
//    
//    
}

function AttachEvent() {
    MapControl1.AttachEvent("onviewboundschanged", ChangeOverview);
}
function ChangeOverview()
{
    var OVzoomL;
    var mp1 = MapControl1.GetMapParam();
    OVzoomL = mp1.zoomLevel;
    var mp2 = MapControl2.GetMapParam();
    MapControl2.SetCenterAndZoom(mp1.center.x,mp1.center.y,mp2.mapScales[OVzoomL-1]);//alert(mp1.mapScales[OVzoomL-1]+':'+mp1.mapScales[mp1.zoomLevel-1]);  
    var mb = MapControl1.GetViewBounds();
    var plb = MapControl2.MapCoordToPixel(mb.leftBottom);
    var prt = MapControl2.MapCoordToPixel(mb.rightTop);
    if (OVzoomL==1)
    {
        indexBox.style.width = '0px';
        indexBox.style.height = '0px';
        indexBox.style.border = 'solid red 0px';
        indexBox.style.left = "0px";
        indexBox.style.top = "0px";
    }
    else
    {
        indexBox.style.width = (prt.x - plb.x).toString() + 'px';
        indexBox.style.height = (plb.y - prt.y).toString() + 'px';
        indexBox.style.left = plb.x.toString() + 'px';
        indexBox.style.top = prt.y.toString() + 'px';
        indexBox.style.border = 'solid red 2px';
    }
}

//var oldScale;
//var vb1;
//function ChangeOverview(e) {
//    vb1 = MapControl1.GetViewBounds();
//    var vb2 = MapControl2.GetViewBounds();
//    var onlyPan = true;
//    if (oldScale != null) {
//        var curScale = MapControl1.GetMapScale();
//        if (oldScale != curScale) {
//            onlyPan = false; 
//            oldScale = curScale;
//        }
//    } else {
//        oldScale = MapControl1.GetMapScale();
//    }
//    var isContain = true;
//    if(vb1.leftBottom.x<vb2.leftBottom.x||vb1.leftBottom.y<vb2.leftBottom.y||vb1.rightTop.x>vb2.rightTop.x||vb1.rightTop.y>vb2.rightTop.y){
//        isContain = false;
//    }
//    if (!(isContain && onlyPan)) {
//        var marginX = (vb1.rightTop.x - vb1.leftBottom.x) / 2;
//        var marginY = (vb1.rightTop.y - vb1.leftBottom.y) / 2;
//        var mb = MapControl1.GetMapBounds();
//        var lb = new SuperMap.IS.MapCoord();
//        lb.x = vb1.leftBottom.x - marginX;
//        lb.y = vb1.leftBottom.y - marginY;
//        if (lb.x <= mb.leftBottom.x) {
//            lb.x = mb.leftBottom.x;
//        }
//        if (lb.y <= mb.leftBottom.y) {
//            lb.y = mb.leftBottom.y;
//        }
//        var rt = new SuperMap.IS.MapCoord();
//        rt.x = vb1.rightTop.x + marginX;
//        rt.y = vb1.rightTop.y + marginY;
//        if (rt.x >= mb.rightTop.x) {
//            rt.x = mb.rightTop.x;
//        }
//        if (rt.y >= mb.rightTop.y) {
//            rt.y = mb.rightTop.y;
//        }
//        var mr = new SuperMap.IS.MapRect();
//        mr.leftBottom = new SuperMap.IS.MapCoord(lb.x, lb.y);
//        mr.rightTop = new SuperMap.IS.MapCoord(rt.x, rt.y);
//        MapControl2.ViewByBounds(mr.leftBottom.x, mr.leftBottom.y, mr.rightTop.x, mr.rightTop.y);
//        MapControl2.AttachEvent("onimagesload", ChangeIndexBox);
//        return;
//    } 
//    var pVb = MapControl2.MapCoordToPixel(vb1.leftBottom);
//    var pRt = MapControl2.MapCoordToPixel(vb1.rightTop);
//    var indexBox = document.getElementById("indexBox");
//    indexBox.style.left = pVb.x + "px";
//    indexBox.style.top = pRt.y + "px";
//}
//function ChangeIndexBox() {
//    var pVb = MapControl2.MapCoordToPixel(vb1.leftBottom);
//    var pRt = MapControl2.MapCoordToPixel(vb1.rightTop);
//    var indexBox = document.getElementById("indexBox");
//    indexBox.style.left = pVb.x + "px";
//    indexBox.style.top = pRt.y + "px";
//}