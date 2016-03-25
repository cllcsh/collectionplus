<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<style type="text/css">
<!--
.itemWrapper {
       background-color: #999999;
}
.widget {
       font-size: 1px;
       color: #cccccc;
       text-decoration: none;
       border: 1px solid #cccccc;
       background-color: #cccccc;
       line-height: 1px;
}

A:visited{TEXT-DECORATION:none;COLOR:#009999} 
A:link{text-decoration:none} 
A:hover{TEXT-DECORATION:COLOR:#FF0000;FONT-WEIGHT:bold;FONT-STYLE:italic} 
A.1:link{text-decoration:none} 
A.1:visited{TEXT-DECORATION:none;COLOR:#000000} 
A.1:hover{TEXT-DECORATION:none;COLOR:#FFffff;FONT-WEIGHT:bold;FONT-STYLE:italic} 

-->
</style>
<script type="text/javascript" src="js/railingsMgr.js"></script>
<script type="text/javascript">
var useFlag="1";//其他页面需要调用时可以定义useFlag的值,默认为1
var getID;//id
var getName;//名称
var getType;//围栏报警类型

/*---------------------------------------------------行政区域------------------------------------------------------------------------------*/
function getAreaName(){getName = areaName;}
var areaName;

//var AreaData = {};

function showArea(code){
	var Acode = "A"+code;
	if(AreaData[Acode]){
		doShowArea(code);
	}else{
		//$.getScript("http://202.102.112.163:8080/ictserver/railingData/"+Acode+".js"+ "?timestamp=" + (new Date()).getTime(), function(){
		$.getScript("<%=path%>/module/map/railingData/"+Acode+".js", function(){
			  //alert(AreaData[Acode].id);
			  doShowArea(code);
		});
	}
	
}

function doShowArea(code){
	var acode;
	
	if(code == AreaCode_static){//如果是根结点
		if(code == "0") {
			code = "000000";
		}
		mapDisplay(); 	
    	acode = AreaData["A"+code];
    	map.addOverlay(new SuperMap.ictmap.RailingArea({xs:acode.xs,ys:acode.ys,xcenter:acode.xCenter,ycenter:acode.yCenter,mapScale:acode.mapScale}));

		return;
	}

	acode = AreaData["A"+code];
	
	var params = {'mode':"ajaxJson", 'code':code};
	jQuery.getJSON("railings_getAreaData.do", params, function(json){
		// 定位数据之间用'**'隔开 
		var message = json.message;
		var name = json.codeid;
		var text = json.text;
		var i = 0;
		var textArr = text.split("#");
		div1HtmlStr = "";
		for(i = 0;i<textArr.length-1;i++){
			var dataArr = textArr[i].split("&");
			div1HtmlStr += "> <a href=\"javascript:;\" id=\"A"+dataArr[0]+"\" onclick=\"showArea('"+dataArr[0]+"')\">" + dataArr[1] + "</a>  ";
		}
		div1HtmlStr += "> "+name;
		document.getElementById("div1").innerHTML = div1HtmlStr;
		
	    div2HtmlStr = "";
		var messageArr = message.split("#");
		for(i = 0;i<messageArr.length-1;i++){
			var dataArr = messageArr[i].split("&");
			div2HtmlStr += "<a href=\"javascript:;\" id=\"A"+dataArr[0]+"\" onclick=\"showArea('"+dataArr[0]+"')\">" + dataArr[1] + "&nbsp;</a> ";
		}
		document.getElementById("div2").innerHTML = div2HtmlStr;
		
		areaName = name;//围栏名称
		getName = areaName;
	});
	
    //将人工围栏 清除 ，以防止跨域访问失败
    div3HtmlStr = "<a href=\"javascript:;\" id=\"R\" onclick=\"setMap()\">" + "> 人工围栏" + "</a> &nbsp; ";
	document.getElementById("div3").innerHTML = div3HtmlStr;

	div4HtmlStr = "";
	document.getElementById("div4").innerHTML = div4HtmlStr;

	if(useFlag!="1"){
	    getID = "a"+code;
		getType = "1";//行政区域围栏默认出围栏报警
		return ;
	}
	
    map.addOverlay(new SuperMap.ictmap.RailingArea({xs:acode.xs,ys:acode.ys,xcenter:acode.xCenter,ycenter:acode.yCenter,mapScale:acode.mapScale}));
}

/*---------------------------------------------------人工围栏 开始------------------------------------------------------------------------------*/

var selName = "";
var sleID = "";
var selType = "";
function showRailing(id,name,type){
	var railingsType = "";
	if(type == 1)
		railingsType = "出围栏报警";
	else 
		railingsType = "进围栏报警"
	document.getElementById("div3").innerHTML = "<a href=\"javascript:;\" id=\"R\" onclick=\"setMap()\">" + "> 人工围栏" + "&nbsp;</a>  "+"> "+name +"&nbsp;&nbsp;";
	document.getElementById("R"+id).outerHTML = "";
	document.getElementById("div4").innerHTML += "<a href=\"javascript:;\" id=\"R"+sleID+"\" onclick=\"showRailing('"+sleID+"','"+selName+"','"+selType+"')\">" + selName + "&nbsp;&nbsp;</a>";

	selName = name;
	sleID = id;
	selType = type;

	if(useFlag!="1"){
	    getID = "r"+id;
		getName = name;
		getType = type;//行政区域围栏默认出围栏报警
		return ;
	}

	draw_railings(id);//画围栏
}

function setMap(){
 	document.getElementById("div3").innerHTML = "<a href=\"javascript:;\" id=\"R\" onclick=\"setMap()\">" + "> 人工围栏" + "&nbsp;</a> ";
	//读取围栏名称
	var params = {'mode':"ajaxJson"};
	jQuery.getJSON("railings_getRailingInfo.do", params, function(json){
		var id;
		var name;
		var type;
		var i = 0;
		div4HtmlStr = "";
		for (i=0;i<json.length;i++){
			var valueArr = json[i];
			id = valueArr.id;		
			name = valueArr.name;
			type = valueArr.type;
			div4HtmlStr += "  <a href=\"javascript:;\" id=\"R"+id+"\" onclick=\"showRailing('"+id+"','"+name+"','"+type+"')\">" + name + " &nbsp;&nbsp;</a>      ";
		}
		document.getElementById("div4").innerHTML = div4HtmlStr;	
	});

	if(useFlag!="1"){
		return ;
	}
	
	//将地图数据从本地读取
 	//parent.document.all.map.contentWindow.location="railings_map.do";  //解决跨域访问 
}

/*---------------------------------------------------人工围栏  结束------------------------------------------------------------------------------*/

var div1HtmlStr = "";
var div2HtmlStr = "";
var div3HtmlStr = "";
var div4HtmlStr = "";
var AreaCode_static= "<s:property value='form.areaCode.areaCode'/>";//根区域的CODE
var AreaName_static= "<s:property value='form.areaCode.areaName'/>";//根区域的NAME
function mapDisplay(){
	//div1HtmlStr,行政区域围栏标题
	var div1HtmlStr = "";
	div1HtmlStr += "<a href=\"javascript:;\" id=\"A<s:property value='form.areaCode.areaCode'/>\" onclick=\"showArea('<s:property value='form.areaCode.areaCode'/>')\">" + "><s:property value='form.areaCode.areaName'/>" + "&nbsp;</a>";
	document.getElementById("div1").innerHTML = div1HtmlStr;
	
	//div2HtmlStr,行政区域围栏内容
	var div2HtmlStr = "";
	<s:iterator value="form.areaCodeList" status="status1">
		div2HtmlStr += "<a href=\"javascript:;\" id=\"A<s:property value='form.areaCode.areaCode'/>\" onclick=\"showArea('<s:property value='areaCode'/>')\">" + "<s:property value='areaName'/>" + "&nbsp;</a> ";
	</s:iterator>
	document.getElementById("div2").innerHTML = div2HtmlStr;

	//div3HtmlStr,人工围栏标题
	div3HtmlStr = "<a href=\"javascript:;\" id=\"R\" onclick=\"setMap()\">" + "> 人工围栏" + "&nbsp;</a>  ";
	document.getElementById("div3").innerHTML = div3HtmlStr;

	//div4HtmlStr,人工围栏内容
	document.getElementById("div4").innerHTML = div4HtmlStr;
} 

function changeBC(){
	if(useFlag!="1"){
		return ;
	}
	document.getElementById("divContent").className="itemWrapper";
	document.getElementById("divImg").style.display = "block";
}

function rechangeBC(){
	if(useFlag!="1"){
		return ;
	}
	document.getElementById("divContent").className="";
	document.getElementById("divImg").style.display = "none";
}

function delRailings(){
	del_railings(sleID);
}
</script>
</head>
<body  onload="">
<s:form id="idFrmMain" name="idFrmMain">
<br/>
<br/>
	<div id="div1"></div>
<br/>
	<div id="div2"></div>
<br/>
<div class="widget">_____</div>
<br/>
	<div onmousemove="changeBC()"  id="divContent" onmouseout="rechangeBC()">
	 	 <div id="div3" style="float:left;"></div>
		 <div style="text-align:right;display:none;" id="divImg">
		 	<img id="" onclick="delRailings()" src="<%=path%>/module/map/images/iconDelFld.gif" style="cursor:hand;"/>
		 </div>
    </div>
<br/><br/>
	<div id="div4"></div>
</s:form>

</body>

<script type="text/javascript">
	mapDisplay();
</script>
</html>