var poly; //折线数组
var polygon;//封闭多边形
var googleMarkersArray = [];//标记数组

//在地图上打点
function placeMarker(location,map,markerOptions,infoWinFlag, infoWinOptions) {
  var clickedLocation = new google.maps.LatLng(location.lat,location.lng);

  var marker = new google.maps.Marker({
	  position: clickedLocation, 
	  map: map,
	  icon:markerOptions.image,
	  title:markerOptions.title
  });  
  //alert(''+location.lat+" "+location.lng+" "+markerOptions.image);
  googleMarkersArray.push(marker);//save to markers  array
 
  if(infoWinFlag && infoWinOptions){//点击标志弹出信息窗
	  google.maps.event.addListener(marker, 'click', function(event) {
		 var infowindow = new google.maps.InfoWindow();//信息窗口
		 infowindow.setContent(infoWinOptions.content);//alert(infoWinOptions.content);
		 infowindow.setPosition(clickedLocation);
		 infowindow.open(map,marker);
	  });
  }
  
  map.setCenter(clickedLocation);
}

// Removes the overlays from the map, but keeps them in the array
//清除所有打点标志
 function clearOverlays1() {
   if (googleMarkersArray) {//标记
     for (i in googleMarkersArray) {
       //alert(googleMarkersArray[i]);
       googleMarkersArray[i].setMap(null);
       //googleMarkersArray[i].set_map(null);
     }
   }

	if(poly){//折线
	     poly.setMap(null);
	}

	if(polygon){//多边形
	     polygon.setMap(null);
	}
 }

// Shows any overlays currently in the array
//显示所有打点标志
 function showOverlays(map) {
   if (googleMarkersArray) {//标记
     for (i in googleMarkersArray) {
       googleMarkersArray[i].setMap(map);
     }
   }

	if(poly){//折线
	     poly.setMap(map);
	}

	if(polygon){//多边形
	     polygon.setMap(map);
	}
 }

// Deletes all markers in the array by removing references to them
//删除所有打点标志
function deleteOverlays() {
   if (googleMarkersArray) {//标记
     for (i in googleMarkersArray) {
           googleMarkersArray[i].setMap(null);
     }
     googleMarkersArray.length = 0;
   }

	if(poly){//折线
	    var polyLen = poly.getPath().getLength();
	    for (var j =0;j< polyLen;j++) {
          poly.getPath().removeAt(0);
	    }
	    // poly.getPath().length=0;
	}

	if(polygon){//多边形
	    polygon.getPaths().removeAt(0);
	}
 }

/**
 *设置中心点（地图工具栏控件）
 * 
 */
function setCenterPoint(map){
	var x = '';
	var y = '';
	
	//单击事件
	google.maps.event.addListenerOnce(map, 'click', function(event) {
		x = event.latLng.lng();
		y = event.latLng.lat();
		
		var strurl = _contextPath+'/module/map/centerPoint_dialog.do?maptype=google&log='+ x +'&lat='+ y +'&laytype=2';//alert("strurl:"+strurl);
		document.openDialog('saveCenterPoint',{
			bgiframe: true,
			autoOpen: false,
			width:480,
			height: 250,
			modal: true
		}).load(strurl);
		
	});
}

/**
 *设置兴趣点（地图工具栏控件）
 * 
 */
function setInterestPoint(map){
	var x = '';
	var y = '';
	
	//单击事件
	google.maps.event.addListenerOnce(map, 'click', function(event) {
		x = event.latLng.lng();
		y = event.latLng.lat();
		
		var strurl = _contextPath+'/module/map/tabs_addInterest.do?maptype=google&log='+ x +'&lat='+ y +'&laytype=2';
		document.openDialog('saveInterestPoint',{
			bgiframe: true,
			autoOpen: false,
			width:480,
			height: 350,
			modal: true
		}).load(strurl);
	});
}

/**
 *自定义控件（地图工具栏控件）
* 
*/

function ToolbarControl(controlDiv, map) {
	//controlDiv.style = "top: 5px; position: absolute; right: 10px; display: block; text-align: center; vertical-align: middle; width: 470px; height: 22px;";
	controlDiv.style.top = "5px";
	controlDiv.style.position = "absolute";
	controlDiv.style.right = "10px";
	controlDiv.style.display = "block";
	controlDiv.style.textAlign = "center";
	controlDiv.style.verticalAlign = "middle";
	controlDiv.style.width = "470px";
	controlDiv.style.height = "22px";
	
	//地图工具栏样式
	var controlUI = document.createElement('DIV');
	//controlUI.style = "border-color:#182B57;border-width:1px;border-style:dashed;height:22px;width:220px;vertical-align: middle; text-align: center; padding: 2px;";
	controlUI.style.borderColor = "#182B57";
	controlUI.style.borderWidth = "1px";
	controlUI.style.borderStyle = "dashed";
	controlUI.style.height = "22px";
	controlUI.style.width= "220px";
	controlUI.style.textAlign = "center";
	controlUI.style.verticalAlign = "middle";
	controlUI.style.padding = "2px";
	
	controlUI.title ="地图工具栏";
	controlDiv.appendChild(controlUI);
	
	//查看全地图按钮
	var controlUIIcon1 = document.createElement('IMG');
	controlUIIcon1.id = "ToolbarControl_ViewEntireToolControl"; 
	controlUIIcon1.src = "images/btn_ViewEntire.gif"; 
	controlUIIcon1.alt = "全地图"; 
	controlUIIcon1.title = "全地图"; 
	//controlUIIcon1.style="cursor:hand;height:22px;width:24px;" ; 
	controlUIIcon1.style.cursor = "hand";
	controlUIIcon1.style.height = "22px";
	controlUIIcon1.style.width= "24px";
	controlUI.appendChild(controlUIIcon1);
	
	google.maps.event.addDomListener(controlUIIcon1, 'click', function() {
		map.setZoom(4);//查看全地图，设为4时可以显示全国地图
	});
	
	//放大按钮
	var controlUIIcon2 = document.createElement('IMG');
	controlUIIcon2.id = "ToolbarControl_ZoomInToolControl"; 
	controlUIIcon2.src = "images/btn_ZoomIn.gif"; 
	controlUIIcon2.alt = "放大"; 
	controlUIIcon2.title = "放大"; 
	//controlUIIcon1.style="cursor:hand;height:22px;width:24px;" ; 
	controlUIIcon2.style.cursor = "hand";
	controlUIIcon2.style.height = "22px";
	controlUIIcon2.style.width= "24px";
	controlUI.appendChild(controlUIIcon2);
	
	google.maps.event.addDomListener(controlUIIcon2, 'click', function() {
	    map.setZoom(map.getZoom()+1);//放大
	});
	
	//缩小按钮
	var controlUIIcon3 = document.createElement('IMG');
	controlUIIcon3.id = "ToolbarControl_ZoomOutToolControl"; 
	controlUIIcon3.src = "images/btn_ZoomOut.gif"; 
	controlUIIcon3.alt = "缩小"; 
	controlUIIcon3.title = "缩小"; 
	//controlUIIcon1.style="cursor:hand;height:22px;width:24px;" ; 
	controlUIIcon3.style.cursor = "hand";
	controlUIIcon3.style.height = "22px";
	controlUIIcon3.style.width= "24px";
	controlUI.appendChild(controlUIIcon3);
	
	google.maps.event.addDomListener(controlUIIcon3, 'click', function() {
	   map.setZoom(map.getZoom()-1);//缩小
	});
	
	//中心点设置按钮
	var controlUIIcon4 = document.createElement('IMG');
	controlUIIcon4.id = "ToolbarControl_CenterPointToolControl"; 
	controlUIIcon4.src = "images/btn_13_on.gif"; 
	controlUIIcon4.alt = "中心点设置"; 
	controlUIIcon4.title = "中心点设置"; 
	//controlUIIcon1.style="cursor:hand;height:22px;width:24px;" ; 
	controlUIIcon4.style.cursor = "point";
	controlUIIcon4.style.height = "22px";
	controlUIIcon4.style.width= "24px";
	controlUI.appendChild(controlUIIcon4);
	
	google.maps.event.addDomListener(controlUIIcon4, 'click', function(event) {
		setCenterPoint(map);
		//map.setCenter(event.latLng);//中心点设置
	});
	
	//兴趣点设置按钮
	/*var controlUIIcon5= document.createElement('IMG');
	controlUIIcon5.id = "ToolbarControl_InterestPointToolControl"; 
	controlUIIcon5.src = "images/btn_100_on.gif"; 
	controlUIIcon5.alt = "兴趣点设置"; 
	controlUIIcon5.title = "兴趣点设置"; 
	//controlUIIcon1.style="cursor:hand;height:22px;width:24px;" ; 
	controlUIIcon5.style.cursor = "point";
	controlUIIcon5.style.height = "22px";
	controlUIIcon5.style.width= "24px";
	controlUI.appendChild(controlUIIcon5);
	
	google.maps.event.addDomListener(controlUIIcon5, 'click', function(event) {
		setInterestPoint(map);//兴趣点设置
	});
	
	//添加多边型区域按钮
	var controlUIIcon6= document.createElement('IMG');
	controlUIIcon6.id = "ToolbarControl_PolygonToolControl"; 
	controlUIIcon6.src = "images/btn_17_on.gif"; 
	controlUIIcon6.alt = "添加多边型区域"; 
	controlUIIcon6.title = "添加多边型区域"; 
	//controlUIIcon1.style="cursor:hand;height:22px;width:24px;" ; 
	controlUIIcon6.style.cursor = "point";
	controlUIIcon6.style.height = "22px";
	controlUIIcon6.style.width= "24px";
	controlUI.appendChild(controlUIIcon6);
	
	google.maps.event.addDomListener(controlUIIcon6, 'click', function(event) {
	  alert('添加多边型区域');//添加多边型区域
	});
	
	  //添加清除标记按钮
	var controlUIIcon7= document.createElement('IMG');
	controlUIIcon7.id = "ToolbarControl_ClearMapToolControl"; 
	controlUIIcon7.src = "images/btn_24_on.gif"; 
	controlUIIcon7.alt = "清除标记"; 
	controlUIIcon7.title = "清除标记"; 
	//controlUIIcon1.style="cursor:hand;height:22px;width:24px;" ; 
	controlUIIcon7.style.cursor = "hand";
	controlUIIcon7.style.height = "22px";
	controlUIIcon7.style.width= "24px";
	controlUI.appendChild(controlUIIcon7);
	
	google.maps.event.addDomListener(controlUIIcon7, 'click', function(event) {
		clearOverlays1();//清除标记
	});
	 */
}

//测试桩
function locMarker(){
	var mapDiv = document.getElementById('myMap');
	var myOptions = {
		      zoom: 10,
		      center: new google.maps.LatLng(-33, 151),
			  mapTypeControl: false,
			//streetViewControl: false,
		      mapTypeId: google.maps.MapTypeId.ROADMAP
		    }
    var $map = new google.maps.Map(mapDiv, myOptions);
    var image = 'images/locationImage.gif';
   
    //添加自定义地图工具控件到地图上
    var toolbarControlDiv = document.createElement('DIV');
    var toolbarControl = new ToolbarControl(toolbarControlDiv, $map);
    toolbarControlDiv.index = 1;
    $map.controls[google.maps.ControlPosition.TOP_RIGHT].push(toolbarControlDiv);
    
    var myLatLng = new google.maps.LatLng(-33.890542, 151.274856);
    var beachMarker = new google.maps.Marker({
        position: myLatLng,
        map: $map,
        icon: image
    });
     googleMarkersArray.push(beachMarker);
    
    //placeMarker({lat:-33.890542,lng:151.274856},$map, {image:image,title:"loc_test"}, true, {content:"测试用打点"});
}
