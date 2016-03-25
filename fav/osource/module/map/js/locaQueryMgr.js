/**
 * 定位查询页面脚本
 * @author yangs
 * @create 2010-02-24
 * @file locaQueryMgr.js
 * @since v0.1
 */
//全局变量 
var _objType = "0";
var _deptId = "";
var globalLocIds = "";
var interval;
var arrTerIds;
var arrTerNums;

// 定位
function locations() {

	document.getElementById("btn_loca").disabled = true;// 将定位按钮灰化
	globalLocIds = "";//每次重新定位前清空该字符串，added by lif,2011-06-10

	var terIds = getCheckedValue() + "";
	if(terIds == "") {
		document.getElementById("btn_loca").disabled = false;
		alert("请选择需要定位的手机终端！");
		return;
	}
	var terNums = getCheckedNum(terIds) + "";
	arrTerIds = terIds.split(",");
	arrTerNums = terNums.split(",");
	
	if(arrTerIds.length > 50) {
		document.getElementById("btn_loca").disabled = false;
		alert("您选择的定位终端数为：" + arrTerIds.length + "个，为了快速定位成功，建议终端个数在50以内！");
		return;
	}
	
	var terIdsArray = new Array();
	var terIdsStr = "";
	
	for(var i = 0; i < arrTerIds.length; i++) {
		setHtmlValue(arrTerIds[i], "正在定位...", "#0000FF");
	}	
	
	//_deptId = $("#deptId").val();
	//_deptId = parent.document.getElementById("deptId").value;
	//alert(_deptId);
	var params = {'mode':"ajaxJson", 'locationIds':terIds};
	jQuery.getJSON("locaQuery_location.do", params, function(json){
		if(json.codeid == 0){
			globalLocIds += json.message + ",";			
			outTime = 0;// 初始化超时设置
			globalLocIds = globalLocIds.slice(0, globalLocIds.length - 1);
			doSchedule();
		} else {
			var arrTerIds = terIds.split(",");
			for(var i=0; i<arrTerIds.length; i++) {
				setHtmlValue(arrTerIds[i], "系统异常", "#000000");
			}
			document.getElementById("btn_loca").disabled = false;
		}
	});
}

// 定时器
function doSchedule() {
	interval = setInterval("doGetData();", 1000 * 5);
}


function getPlacename(node,id){
	var objType = document.getElementById("objType").value;
	var params = {'mode':"ajaxJson", 'locationIds':id,'locaQueryForm.objType':objType};
	$(node).html('正在查询，请稍后');
	jQuery.getJSON("locaQuery_getData.do", params, function(json){
		var message = json.message;
		var returnDesArr = message.split("**");
		var placeName = returnDesArr[6];
		if(placeName != "" && placeName != null && placeName != 'null'){
			$(node).replaceWith(placeName);
		} else {
			//$(node).html('<a href="javascript:;" onclick="javascript:getPlacename(this,'+id+')">获取位置</a>');
			$(node).html('获取位置');
		}
	})
}


// 获取定位数据
function doGetData() {
	outTime = outTime + 5;
	//_objType = $("#objType").val();
	_objType = document.getElementById("objType").value;
	var params = {'mode':"ajaxJson", 'locationIds':globalLocIds,'locaQueryForm.objType':_objType};
	jQuery.getJSON("locaQuery_getData.do", params, function(json){
		globalLocIds = json.text; // 未获取到数据的终端的id
		// json.message 为获取到的定位数据，终端之间用'$$'隔开
		// 定位数据之间用'**'隔开 
		var message = json.message;
		var outNum = json.codeid;// 未获取到定位数据的终端的个数
		if(message != undefined && message != ""){//message不为空的情况
			var returnStrArr = message.split("$$");//将得到的数据按人分开
			for(var i=0;i<returnStrArr.length;i++){
				if(returnStrArr[i] != ""){//returnStr不为空的情况
					var returnDesArr = returnStrArr[i].split("**");
					var Hoder = returnDesArr[0];//getHolder
					var LocNum = returnDesArr[1];//getLocNum
					var Date = returnDesArr[2];//getLocDate
					var Longitude = returnDesArr[3];//getRectifyLong
					var Latitude = returnDesArr[4];//getRectifyLat
					var LocCode = returnDesArr[5];//getLocCode
					var placeName = returnDesArr[6];
					var locInfo = returnDesArr[7];
					var locId = returnDesArr[8];//getLocId
					var radius = returnDesArr[9];//radius
					var posour = returnDesArr[10];//posour
					var picPath = returnDesArr[11];//picPath
					var areaId = returnDesArr[12];//areaId
					var areaName = returnDesArr[13];//areaName
					var staffId = returnDesArr[14];//staffId
					var staffName = returnDesArr[15];//staffName
					var id = returnDesArr[16];//id 
					var onRemark = getRemark(locId, Hoder, LocNum, Longitude, Latitude, placeName, Date, radius, posour, picPath, areaId, areaName,staffId,staffName, id);
					
					var tempStr = LocNum;
					if(returnDesArr[5] == "0"){//定位成功
						markLocOverlay(Longitude, Latitude, locId, onRemark, Hoder);
						//map.addOverlay(new SuperMap.ictmap.LocMarker({x:Longitude,y:Latitude},"marker_"+locId, {infowin:{width:250, height:300, title:"",content:onRemark}}));
						//map.getMapControl().SetCenterAndZoom(Longitude, Latitude, map.getMapControl().GetMapScale());
						
						for ( var j = 0; j < arrTerNums.length; j++) {
							if(arrTerNums[j] == tempStr) {
								setHtmlValue(arrTerIds[j], "定位成功", "#000000");
								break;
							}
		     		   	}
					   //showDiv();// 显示定位信息提示框
					} else {
							//定位失败
							for ( var j = 0; j < arrTerNums.length; j++) {
								if(arrTerNums[j] == tempStr) {
									setHtmlValue(arrTerIds[j], "<span class=\"locCode\" code="+LocCode+" title='" + locInfo + "'>定位失败(" + LocCode + ")</span>", "#FF0000");
									initTooltip();
									break;
								}
							}
							//showDiv();// 显示定位信息提示框
							//显示上次定位成功后的点
							getLastPoint(tempStr);
					}
				}
			}
		}
		if (globalLocIds == "") {
			document.getElementById("btn_loca").disabled = false; // 将定位按钮的灰化状态去除
			clearInterval(interval);
		}
		// 超时 2 分钟
		if(outTime > 120) {
			document.getElementById("btn_loca").disabled = false; // 将定位按钮的灰化状态去除
			clearInterval(interval);
			if(globalLocIds != "") {
				var arrOutTimeIds = outNum.split(",");
				for(var i = 0; i < arrOutTimeIds.length; i++) {
					for ( var j = 0; j < arrTerNums.length; j++) {
						if(arrOutTimeIds[i] == arrTerNums[j]) {
							setHtmlValue(arrTerIds[j], "定位超时！", "#FF0000");
							break;
						}
					}
				}
			}
		}
	});
}

//上一次定位成功后的点
function getLastPoint(locNum) {
	//_objType = $("#objType").val();
	_objType = document.getElementById("objType").value;
	var params = {'mode':"ajaxJson", 'locaQueryForm.locNum':locNum,'locaQueryForm.objType':_objType};
	jQuery.getJSON("locaQuery_getLastPoint.do", params, function(json){
		// json.message 为获取到的定位数据，定位数据之间用'**'隔开
		if(json.codeid == 0) {//本次定位成功
			var message = json.message;
			if(message != undefined && message != ""){//message不为空的情况
				var returnDesArr = message.split("**");
				var Hoder = returnDesArr[0];//getHolder
				var LocNum = returnDesArr[1];//getLocNum
				var Date = returnDesArr[2];//getLocDate
				var Longitude = returnDesArr[3];//getRectifyLong
				var Latitude = returnDesArr[4];//getRectifyLat
				var LocCode = returnDesArr[5];//getLocCode
				var placeName = returnDesArr[6];//getPlaceName
				var locId = returnDesArr[7];//getLocId
				var radius = returnDesArr[8];//radius
				var posour = returnDesArr[9];//posour
				var pic_path= returnDesArr[10];//pic_path
				var areaId = returnDesArr[11];//areaId
				var areaName = returnDesArr[12];//areaName
				var staffId = returnDesArr[14];//staffId
				var staffName = returnDesArr[15];//staffNamer
				var id = returnDesArr[16];//id
				
				var remark = getRemark(locId, Hoder, LocNum, Longitude, Latitude, placeName, Date, radius, posour, pic_path, areaId, areaName,staffId, staffName, id);
				markLocOverlay(Longitude, Latitude, locId, remark, Hoder);
				//map.addOverlay(new SuperMap.ictmap.LocMarker({x:Longitude,y:Latitude},"marker_"+locId, {infowin:{width:250, height:300,title:"",content:remark}}));
			}
		}
		if(json.codeid == 1) {//本次定位失败，显示上次成功定位位置
			var message = json.message;
			if(message != undefined && message != ""){//message不为空的情况
				var returnDesArr = message.split("**");
				var Hoder = returnDesArr[0];//getHolder
				var LocNum = returnDesArr[1];//getLocNum
				var Date = returnDesArr[2];//getLocDate
				var Longitude = returnDesArr[3];//getRectifyLong
				var Latitude = returnDesArr[4];//getRectifyLat
				var LocCode = returnDesArr[5];//getLocCode
				var placeName = returnDesArr[6];//getPlaceName
				var locId = returnDesArr[7];//getLocId
				var radius = returnDesArr[8];//radius
				var posour = returnDesArr[9];//posour
				var pic_path= returnDesArr[10];//pic_path
				var areaId = returnDesArr[11];//areaId
				var areaName = returnDesArr[12];//areaName
				var staffId = returnDesArr[14];//staffId
				var staffName = returnDesArr[15];//staffName
				var id = returnDesArr[16];//id
				
				var remark = getRemark(locId, Hoder, LocNum, Longitude, Latitude, placeName, Date, radius, posour, pic_path, areaId, areaName,staffId,staffName, id);
				markLocOverlay(Longitude, Latitude, locId, remark, Hoder);
				//map.addOverlay(new SuperMap.ictmap.LocMarker({x:Longitude,y:Latitude},"marker_"+locId, {infowin:{width:250, height:300,title:"",content:remark}}));
			}
		}
	});
}

function initTooltip() {
	$("span.locCode[code][code!=0]").tooltip({
		delay: 0,
		showURL: false,
		top: -15,
		left: -60,
		bodyHandler: function() {
			return locationErrorCode[$(this).attr("code")]||"未知";
		}
	});
}

/** 矫正对象树形结构操作 **/

// 显示目录图标
function display(nodeId) {
    var node = document.getElementById("tr_" + nodeId);
    var img = document.getElementById("img_" + nodeId);
    if (img.src.indexOf("images/openfoldericon.png") != -1) {
        img.src = _contextPath+"/module/map/images/foldericon.png";
        if (node != null) {
            node.style.display="none";
        }
    } else {
        img.src = _contextPath+"/module/map/images/openfoldericon.png";
        if (node != null) {
            node.style.display="";
        }
    }
}

// 选择一个终端,如果选择的是组则同时选择组下的所有项,如果选择的是终端则选择该终端
function selectNode(node) {
    if (node.name.indexOf("cb_group")!=-1) {
        var tab = document.getElementById("tb_group" + node.value);
        if (tab != null) {
            var arr = tab.getElementsByTagName("input");
            for (var i = 0; i < arr.length; i++) {
                if (arr[i].type=="checkbox") {
                    arr[i].checked = node.checked;
                    if (arr[i].name.indexOf("cb_tu") != -1) {
	                    var trNode = arr[i].parentNode.parentNode;
	                    if (trNode != null) {
	                        setSelectColumnColor(arr[i].value, trNode, node.checked);
	                    }
                    }
                }
            }
        }
    } else {
        var trNode = node.parentNode.parentNode;
        if (trNode != null) {
            setSelectColumnColor(node.value, trNode, node.checked);
        }
    }
    
    checkNode_level(); // 如果终端全选中，则组也选中
}

function checkNode_level() {
	var groupCheck = document.all.cb_group;
	var tuArrCheck;
	if(groupCheck.length == undefined) {
		tuArrCheck = document.all.cb_tu;
		if((tuArrCheck != null) && (tuArrCheck != undefined)) {
			if(tuArrCheck.length == undefined) {
				if(tuArrCheck.checked) {
					groupCheck.checked = true;
				} else {
					groupCheck.checked = false;
				}
			} else {
				var temp = 1;
				for(var i = 0; i < tuArrCheck.length; i++) {
					if(tuArrCheck[i].checked == false) {
						temp = 0;
						break;
					}
				}
				if(temp == 1) {
					groupCheck.checked = true;
				} else {
					groupCheck.checked = false;
				}
			}
		}
	} else {
		//selectedNode(groupCheck[0]);
		checkedAllGroupNode(groupCheck[0]);
	}
}

function selectedNode(node) {
	var tab = document.getElementById("tb_group" + node.value);
	if(tab != null) {
		var arrTabGroupCheck = tab.getElementsByTagName("input");
		if(arrTabGroupCheck.length == undefined) {
			if(arrTabGroupCheck.type == "checkbox") {
				if(arrTabGroupCheck.checked) {
					return true;//node.checked = true;
				} else {
					return false;//node.checked = false;
				}
			  }
		}else {
			var temp = 1;
			for(var i = 0; i < arrTabGroupCheck.length; i++) {
				if(arrTabGroupCheck[i].type == "checkbox") {
					if(arrTabGroupCheck[i].name.indexOf("cb_group") != -1) {
						selectedNode(arrTabGroupCheck[i]);
					} else {
						if(arrTabGroupCheck[i].checked == false) {
							temp = 0;
						}
					}
				}
			}
			if(temp == 1) {
				return true;//node.checked = true;
			} else {
				return false;//node.checked = false;
			}
		}
	}
}

function checkedAllGroupNode(node) {
	var tab = document.getElementById("tb_group" + node.value);
	if(tab != null) {
		var groupCheck = tab.all.cb_group;
		var temp = 1;
		var flag = true;
		if(groupCheck != undefined) {
			if(groupCheck.length != undefined) {
				for(var i = 0; i < groupCheck.length; i++) {
					var childTab = document.getElementById("tb_group" + groupCheck[i].value);
					if(childTab != null) {
						checkedAllGroupNode(groupCheck[i]);
					}
					if(groupCheck[i].checked == false) {
						temp = 0;
					}
				}
			} else {
				var childTab2 = document.getElementById("tb_group" + groupCheck.value);
				if(childTab2 != null) {
					checkedAllGroupNode(groupCheck);
				}
				if(groupCheck.checked) {
					temp = 1;
				} else {
					temp = 0;
				}
			}
		}
		var tuCheck = tab.all.cb_tu;
		if(tuCheck != undefined) {
			if(tuCheck.length == undefined) {
				flag = tuCheck.checked;
			} else {
				for(var j = 0; j < tuCheck.length; j++) {
					if(tuCheck[j].checked == false) {
						flag = false;
						break;
					}
				}
			}
		}
		if((temp == 1) && (flag)) {
			node.checked = true;
		} else {
			node.checked = false;
		}
	}
}

function setSelectColumnColor(selectId, trNode, isSelect) {
    //var lockImg = document.getElementById("lock" + selectId);
    //if (lockImg != null && lockImg.src.indexOf("/images/unlock.png") != -1) {
        if (isSelect) {
            trNode.bgColor="#C8DBF5";
        } else {
            trNode.bgColor="#f7fafe";
        }
    //}
}

function showTarget(target) {
    if (target.id != lockTuId) {
        var element = document.getElementById("locationTU" + target.id);
        if (element != null && !element.disabled) {
            //frameShowTarget.location.href="/StarMap/palcemap/placeservice/selecttarget.jsp?tuId=" + target.id;
            left.webgps.gps.selectGPS(target.id);
        }
        var node = left.framePositionInfo.document.getElementById(target.id);
        if (node != null) {
            left.framePositionInfo.selectNode(node);
        }
    }
}

function getCheckedValue() {
	var checkboxes = document.all.cb_tu;
	var i = 0;
	var arrValue = "";
	if(checkboxes != null) {
		if(checkboxes.length == undefined) {
			if(checkboxes.checked) {
				arrValue = arrValue + checkboxes.value + ",";
			}
		} else {
			for(;i<checkboxes.length;i++) {
				if(checkboxes[i].checked) {
					arrValue = arrValue + checkboxes[i].value + ",";
				}
			}
		}
	}
	
	return arrValue.slice(0, arrValue.length - 1);
}

function getCheckedNum(ids) {
	if(ids == "") {
		return "";
	}
	var arrValue = "";
	var arr = ids.split(",");
	for(var i = 0; i < arr.length; i++) {
		arrValue = arrValue + document.getElementById("hd_tu" + arr[i]).value + ",";
	}
	return arrValue.slice(0, arrValue.length - 1);
}

function setHtmlValue(id, htmlValue, color) {
	var tdTag = document.getElementById("td_tu" + id);
	tdTag.style.color = color;
	tdTag.innerHTML = htmlValue;
}

/** 矫正对象树形结构操作 **/