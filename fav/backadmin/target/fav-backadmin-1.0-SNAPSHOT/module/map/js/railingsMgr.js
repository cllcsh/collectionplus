/**
 * 用户管理页面脚本
 * @author zhouhao
 * @create 2009-4-15
 * @file userMgr.js
 * @since v0.1
 */
//var jQuery = jQuery.noConflict();
//全局变量 
var _railingsName = "";
var _currPage = 1;		//保存当前页码
var _perPage = 10;
var _railingsId = "";

var AreaData = {};

//查询
function query(){

	_railingsName = document.getElementById("railingsName").value;

	_currPage = 1;
	loadPage();
}
//刷新分页数据，分页加载完毕后由回调函数重新设置
reload = function(){};

/**
 * 载入页面
 * 读取全局变量_groupName,_upperGroupId,_currPage作为ajax调用的参数
 */
function loadPage(){
	var ev = window.event;
	var target = ev.target || ev.srcElement;
	disableButton(jQuery(target));
	var param = {'mode':"ajax",'form.railingsName':_railingsName};
	var pageconfig = {
		ajaxDataType:'html',
		proxyUrl:'railings_query.do',
		currentPage:_currPage,
		perPage:_perPage,
		ajaxParam:param,
		barPosition:'bottom',
		showMode:'mini',
		callback:function(perpage,reloadFn){
			_perPage = perpage;
			reload = reloadFn;
			enableButton(jQuery(target));
		}
	};

	jQuery("#pagecontent").pagination(pageconfig);//alert(3);
}

//删除
function del_railings(ids) {
	var id = ids;
	if(id == null || id == ""){
		alert("请选择要删除的记录");
		return ;
	}

	var params = {'mode':"ajax", 'form.ids':id};
	if(window.confirm("确定要删除选中的记录吗?")){
		jQuery.getJSON("railings_deletes.do", params, function(json){
			alert(json.message);
			if(json.codeid == 0)
				document.getElementById("div3").innerHTML = "<a href=\"javascript:;\" id=\"R\" onclick=\"setMap()\">" + "> 人工围栏" + "&nbsp;</a>" ;
			    map.getMapControl().CustomLayer.ClearLines();//清图像		    	
			    selName = "";
		    	sleID = "";
		    	selType = "";
		});
	}
}

//围栏保存
function save_crawl(){ 
	$('#saveBtn').attr('disabled','disabled');//不能重复单击同一按钮
	   var name =  document.getElementById("railingsName2").value;
	   if (name=="" || name== null)
	   {
	   		alert("围栏名称不能为空");
	   		$("#saveBtn").attr("disabled","");
			return;
	   }
	   var alarm =  document.getElementById("alarm").value;
	   var longtit = document.getElementById("longtit").value;
	   var lattit = document.getElementById("lattit").value;
	   var type = document.getElementById("type").value;
	   var params = {'mode':"ajaxJson", 
			   		 'form.longitudes':longtit, 
			         'form.latitudes':lattit, 
			         'form.type':type, 
				     'form.railingsType':alarm,
				     'form.railingsName':name};
	   jQuery.post("railings_saveCrawl.do", params, function(json){//post方法处理中文,而用.getJSON()方法中文为乱码
		   $("#saveBtn").attr("disabled","");
			alert(json.message);
			if(json.codeid == 0){
				var id = json.text;
				document.getElementById("div4").innerHTML += "<a href=\"javascript:;\" id=\"R"+id+"\" onclick=\"showRailing("+id+",'"+name+"')\">" + name + "</a> " ;
				closeAreaWin();
			}
		},"json");
}
//用于刷新list
document.reloadPageInfo = function(){
	reload();
}

//在地图上显示区域，包括行政区域和人工围栏
function show_railings(areaId,longitude,latitude){//alert("areaId:"+areaId);
	if(areaId == null || areaId == "" || areaId == undefined)
		return;
	
	var code = "";
	if(areaId.indexOf('a')>-1 || areaId.indexOf('A')>-1){//画行政区域
		code = areaId.substring(1);
		draw_area(code);
	}else if(areaId.indexOf('r')>-1 || areaId.indexOf('R')>-1){//画人工围栏
		code = areaId.substring(1);
		draw_railings(code);
	}
	map.getMapControl().SetCenterAndZoom(longitude, latitude, map.getMapControl().GetMapScale());
}

function draw_area(code){
	if(code == "0") {
		code = "000000";
	}	
	
	var Acode = "A"+code;
	
	if(AreaData[Acode]){
		doDrawArea(Acode);
	}else{
		$.getScript(_contextPath+"/module/map/railingData/"+Acode+".js", function(){
			  //alert(_AreaData[Acode].id);
			doDrawArea(Acode);
		});
	}	
}

function doDrawArea(Acode){
	var acode = AreaData[Acode];
	map.addOverlay(new SuperMap.ictmap.RailingArea({xs:acode.xs,ys:acode.ys,xcenter:acode.xCenter,ycenter:acode.yCenter,mapScale:acode.mapScale}));	
}

//画围栏
function draw_railings(railingsId){
	if(railingsId == null || railingsId == "" || railingsId == undefined)
		return;
	
	var params = {'mode':"ajax", 'form.id':railingsId};
	
	jQuery.getJSON("railings_getData.do", params, function(json){//小数据量用getJson
		var i = 0;
		var cx = 0,cy = 0;
		var pointArray = new Array();
		for(;i<json.length;i++){
			pointArray.push({x:json[i].longitude, y:json[i].latitude, id:i});
			
			cx += json[i].longitude;
			cy += json[i].latitude;	
		}
		
		if(json.length>0){
			cx = cx/json.length;
			cy = cy/json.length;
		}
		
		map.getMapControl().CustomLayer.ClearLines();//清上一个的图像
		//画围栏
		map.addOverlay(new SuperMap.ictmap.Polygon(pointArray,null,{id:"Polygon"}));
		map.getMapControl().SetCenterAndZoom(cx, cy, map.getMapControl().GetMapScale());
			
	});
}

//定义弹出对话框
jQuery(function() {
	jQuery("#dialog").dialog({
		bgiframe: true,
		autoOpen: false,
		width:480,
		height: 200,
		modal: true,
		close: function() {
			jQuery(this).html('');
		}
	});
});

/*----------------------------------------------*/
/* 以下方法暂时未使用, by lifa*/
/*--------------------------*/

function isImplementedOnload(script){ 
	script = script || document.createElement('script') ; 
	if('onload' in script) return true ;  
	script.setAttribute('onload',''); 
	return typeof elScript.onload == 'function' ; // ff true ie false . 
}

//动态加载js,by lif,2010-6-30
function jsLoader(path, callback){
	var flag=false;
	var script=document.createElement("script");
	script.type="text/javascript";
	script.language="javascript";
	script.src=path;
	//alert(script.src);
	
	script.onload=script.onreadystatechange=function(){//alert("script.readyState:"+script.readyState);
		if(!flag&&(!script.readyState || script.readyState == "loaded" ||script.readyState=="complete")){
			flag=true;
			script.onload=script.onreadystatechange=null;
			if(callback && isImplementedOnload(script)){//alert(callback);
				callback.call(script);
			}
		}
	};
	
	document.getElementsByTagName("head")[0].appendChild(script);

};

//动态加载js,by lif,2010-6-30
function jsLoader2(path, callback) { 
	try {  
		var flag=false;
		var script = document.createElement('script');            
		script.src = path;            
		script.type = "text/javascript";
		
		if( script.addEventListener ) {  //Firefox用                
			script.addEventListener("onload", callback, false);          
		} else if(script.attachEvent) { //IE用               
			script.attachEvent("onreadystatechange", function(){
				if(!flag&&(!script.readyState || script.readyState == 4 || script.readyState == "complete" || script.readyState == "loaded")) {                            
					//callback(); 
					flag = true;
					if(callback){
						callback.call(script);
					}
				}              
			});            
		}
		
		document.getElementsByTagName("head")[0].appendChild(script);	
	} catch(e) {            
		//this.handlerError(e); 
		alert(e);
		callback();        
	}    
}
