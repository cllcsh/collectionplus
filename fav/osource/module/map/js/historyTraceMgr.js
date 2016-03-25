/**
 * 页面脚本
 * @author zhouhao
 * @create 2009-4-15
 * @file userMgr.js
 * @since v0.1
 */
//var jQuery = jQuery.noConflict();
//全局变量 
var _terminalNum = "";
var _currPage = 1;		//保存当前页码
var _perPage = 1000;
var _startTime = "";
var _endTime = "";

function pickedFunc_s(){
	$dp.$('d523_y').value=$dp.cal.getP('y');
	$dp.$('d523_M').value=$dp.cal.getP('M');
	$dp.$('d523_d').value=$dp.cal.getP('d');
	$dp.$('d523_HH').value=$dp.cal.getP('H');
	$dp.$('d523_mm').value=$dp.cal.getP('m');
	$dp.$('d523_ss').value=$dp.cal.getP('s');
}
function pickedFunc_e(){
	$dp.$('d524_y').value=$dp.cal.getP('y');
	$dp.$('d524_M').value=$dp.cal.getP('M');
	$dp.$('d524_d').value=$dp.cal.getP('d');
	$dp.$('d524_HH').value=$dp.cal.getP('H');
	$dp.$('d524_mm').value=$dp.cal.getP('m');
	$dp.$('d524_ss').value=$dp.cal.getP('s');
}


function selDestineDateChange() {
    // 默认为今天
    var date = new Date();
    var startYear = date.getYear();
    var startMonth = date.getMonth() + 1;
    var startDay = date.getDate();
    var startHour = 0;
    var startMinute = 0;
    var endYear = date.getYear();
    var endMonth = date.getMonth() + 1;
    var endDay = date.getDate();
    var endHour = date.getHours();
    var endMinute = date.getMinutes();
	
    var destineDateValue = frmHistory.destineDate.value;
	
    // 昨天
    if (destineDateValue == "yesterday") {
        date.setDate(endDay - 1);
        startYear = date.getYear();
		endYear = date.getYear();
        startMonth = endMonth = date.getMonth() + 1;
        startDay = endDay = date.getDate();
        startHour = startMinute = 0;
        endHour = 23;
        endMinute = 59;
    } else if (destineDateValue == "hrs8") {
		date.setHours(endHour - 8);
        startYear = date.getYear();
        startMonth = date.getMonth() + 1;
        startDay = date.getDate();
        startHour = date.getHours();
        startMinute = date.getMinutes();
    } else if (destineDateValue == "hrs24") {
		date.setHours(endHour - 24);
        startYear = date.getYear();
        startMonth = date.getMonth() + 1;
        startDay = date.getDate();
        startHour = date.getHours();
        startMinute = date.getMinutes();
    }
    frmHistory.d523_y.value = startYear;
    frmHistory.d524_y.value = endYear;
    frmHistory.d523_M.value = startMonth;
    frmHistory.d524_M.value = endMonth;
    frmHistory.d523_d.value = startDay;
    frmHistory.d524_d.value = endDay;
    frmHistory.d523_HH.value = startHour;
    frmHistory.d523_mm.value = startMinute;
    frmHistory.d524_HH.value = endHour;
    frmHistory.d524_mm.value = endMinute;
}

function toList(){
	var startYear = parseInt(frmHistory.d523_y.value);//如果是完整日期格式，直接取前4位 
	var startMonth = parseInt(frmHistory.d523_M.value);
    var startDay = parseInt(frmHistory.d523_d.value);
    var startHour = parseInt(frmHistory.d523_HH.value);
    var startMinute = parseInt(frmHistory.d523_mm.value);
    var endYear = parseInt(frmHistory.d524_y.value);
    var endMonth = parseInt(frmHistory.d524_M.value);
    var endDay = parseInt(frmHistory.d524_d.value);
    var endHour = parseInt(frmHistory.d524_HH.value);
    var endMinute = parseInt(frmHistory.d524_mm.value);
    
    var startDate = startYear+"/"+startMonth+"/"+startDay+" "+startHour+":"+startMinute+":01";
	var endDate = endYear+"/"+endMonth+"/"+endDay+" "+endHour+":"+endMinute+":59";
	
    if(Date.parse(startDate)-Date.parse(endDate)>0){   
    	  alert("起始日期要在结束日期之前!");    
    	  return false;   
    }  
    
    var value = getCheckedValue();
	if(value == "" || value == null){
		alert("请先选择一个终端");
		return ;
	}

	startDate = startYear+"-"+startMonth+"-"+startDay+"@"+startHour+":"+startMinute+":01"
	endDate = endYear+"-"+endMonth+"-"+endDay+"@"+endHour+":"+endMinute+":59"
	$('#hisWin').html("");
	doGetList( value, startDate, endDate);
}
var checkedIndexs = new Array();
var points = new Array();
function doGetList(value, startTime, endTime){
	$("#his_SubmitButton").attr('disabled','disabled');
	var url = _contextPath+"/module/map/historyTrace_getTraceList.do?mode=ajax&limit=200&id="+value+"&startTime="+startTime+"&endTime="+endTime;
	var iframe = "<iframe style=\"position:absolute;z-index:-1;width:expression(this.nextSibling.offsetWidth);height:expression(this.nextSibling.offsetHeight);top:expression(this.nextSibling.offsetTop);left:expression(this.nextSibling.offsetLeft);\" frameborder=\"0\"></iframe>";
	$('#hisWin').window({width: 300,height:400,left:$(document).width()-300,top:2, draggable:false,closed:false}).html(iframe+"<table id=\"tt\"></table>");
	$('#hisWin').find('#tt').datagrid({
		//title:'Editable DataGrid',
		//iconCls:'icon-edit',
		fit:true,
		width:300,
		height:400,
		singleSelect:false,
		idField:'id',
		url:url,
		onBeforeLoad:function(data){
			checkedIndexs = new Array();
			points = new Array();
			for(var i=0;i<data.rows.length;i++){
				var point = {};
				point.x = data.rows[i].rectifyLong;
				point.y = data.rows[i].rectifyLat;
				point.id = data.rows[i].id;
				point.info = "经度"+data.rows[i].rectifyLong+"<p>";
				if(data.rows[i].checked == 'checked'){
					checkedIndexs.push(i);
				}else{
					point.display = 'none';
				}
				points.push(point);
			}
			if(data.rows.length == 0)
				$("#his_SubmitButton").attr('disabled','');
		},
		onLoadSuccess:function(){
			for(var i=0;i<checkedIndexs.length;i++){
				$('#tt').datagrid("selectRow",checkedIndexs[i]);
			}
			if(checkedIndexs.length>0){
				//map.addOverlay(new SuperMap.ictmap.LocHisPolyline(points));
				var hisPolyline = new SuperMap.ictmap.LocHisPolyline(points,"hisLine",{strokeColor:"blue"});
				map.addOverlay(hisPolyline);
			}
			$("#his_SubmitButton").attr('disabled','');
		},
		onLoadError:function(){
			$("#his_SubmitButton").attr('disabled','');
		},
		onSelect:function(rowIndex, rowData){
			var num = new Number(rowIndex);
			var info = "";
			//info += "姓名："+rowData.holder + "<br>";
			info += "定位号码："+rowData.locNum + "<br>";
			info += "经度："+rowData.rectifyLong + "<br>";
			info += "纬度："+rowData.rectifyLat + "<br>";
			info += "地名："+rowData.placeName + "<br>";
			info += "定位时间："+rowData.locDate + "<br>";
			
			map.addOverlay(new SuperMap.ictmap.NumMarker({x:rowData.rectifyLong, y:rowData.rectifyLat}, 'his_p_'+rowIndex, num+1,{infowin:{title:"", content:info}}));
			map.getMapControl().SetCenterAndZoom(rowData.rectifyLong,rowData.rectifyLat);
		},
		onUnselect:function(rowIndex, rowData){
			map.getMapControl().CustomLayer.RemoveMark('his_p_'+rowIndex);
		},
		frozenColumns1:[[
		]],
		columns:[[
			{field:'id',title:'id',checkbox:true,align:'center'},
			{field:'num',title:'序号',width:25,align:'center'},
			{field:'placeName',title:'地理位置',width:100,align:'center'},
			{field:'locDate',title:'定位时间',width:80,align:'center'},
			{field:'alarmStatus',title:'状态',width:80,formatter:function(value){
				if(value = '0')return '正常';
				return "报警";
			}}
		]]
	});
}
