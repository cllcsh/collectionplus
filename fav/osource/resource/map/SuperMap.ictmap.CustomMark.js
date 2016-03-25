//该方法未使用，by lifa.
SuperMap.ictmap.SetInterestPointAction=function(completeEvent, opts) {
	var _this = this;
	var color = '#E28964';
    var font = 'tahoma';
    var size = '11px';
    var Efurl = '';
    var Qurl = '';
    var Efw = '330';
    var Efh = '260';
    var Qw = '400';
    var Qh = '300';
    var Plog = '';
    var Plat = '';
    var Pr = '25';
    var ovopen = 1;
    var si = 'true';
    var fixcs = '';
    var fixcs2 = '';
    var shapeinmap = new Array();//当前在map中的shape（有显示级别）
    var shapefixcs2 = new Array();//所有的shape（有显示级别）
    var completeEvent = 'showSaveEF';
    
    this.type="SuperMap.ictmap.SetInterestPointAction";
    
    if(opts && opts.Efurl &&  typeof(opts.Efurl) != "undefined"){
    	Efurl = opts.Efurl;
	};
	this.Efurl = Efurl;
	
	if(opts && opts.Qurl &&  typeof(opts.Qurl) != "undefined"){
		Qurl = opts.Qurl;
	};
	this.Qurl = Qurl;
	
	if(opts && opts.completeEvent &&  typeof(opts.completeEvent) != "undefined"){
		completeEvent = opts.completeEvent;
	};
	this.completeEvent = completeEvent;
	
	this.Init = function(mapControl) {
		var action = new PintQuery(completeEvent);
	    polygonStyle = 0;
	    MapControl.SetAction(action);
	}
	_init();
	
}


/*********************************************/

//用户自定义兴趣点标注
//title: 标注名
//note: 备注
//showPic: 是否显示logo ture/false
//hotpic: logo图片名(包含后缀)
SuperMap.IS.DrawMarkPointAction=function(title, note, showPic, hotpic)
{
  this.type="SuperMap.IS.DrawMarkPointAction";
  var x=null;
  var y=null;
  var _showPic=false;
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
		
		if(showPic != null)
		{
		    _showPic = showPic;
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
      x=null;
      y=null;
  }
  
  function _OnClick(e)
  {
     x = e.mapCoord.x;
     y = e.mapCoord.y;
     
     if(_showPic == true)
     {
         this.mapControl.CustomLayer.AddMark("markPoint", x, y, null, null, "<div><img src='" + _hotpic + "' style='cursor:pointer' title='" + note + "' /><span>&nbsp;" + title + "</span></div>");
     }
     else
     {
         this.mapControl.CustomLayer.AddMark("markPoint", x, y, null, null, "<div><b>" + title + "</b> " + note + "</div>");
     }
  }
  
  function _OnContextMenu(e)
  {
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


//用户自定义兴趣线标注
//title: 标注名
//note: 备注
//showPic: 是否显示标注内容
//hotpic: logo图片名(包含后缀)
SuperMap.IS.DrawMarkLineAction=function(title, note, showLabel, hotpic)
{
    this.type="SuperMap.IS.DrawMarkLineAction";
    var actionStarted=false;    
	var keyPoints=new Array();
	var xs=new Array();
	var ys=new Array();
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
        this.mapControl.CustomLayer.InsertLine(title,xs,ys,2,"blue");
    }
        
    function _OnContextMenu(e)
    {
        if(_showLabel == true)
        {
            var i = Math.round(keyPoints.length / 2) - 1;    
            this.mapControl.CustomLayer.InsertMark("markPoint", xs[i], ys[i], null, null, "<div><img src='" + _hotpic + "' style='cursor:pointer' title='" + note + "' /><span>" + title + "</span></div>");
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

//用户自定义兴趣面标注
//title: 标注名
//note: 备注
//showPic: 是否显示标注内容
//hotpic: logo图片名(包含后缀)
SuperMap.IS.DrawMarkPolygonAction=function(title, note, showLabel, hotpic)
{
    this.type="SuperMap.IS.DrawMarkPolygonAction";
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
            
            this.mapControl.CustomLayer.InsertMark("markPoint", xcenter, ycenter, null, null, "<div><img src='" + _hotpic + "' style='border:0px; cursor:pointer' title='" + note + "' /><span>" + title + "</span></div>");
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