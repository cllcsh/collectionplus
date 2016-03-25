<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="com.osource.core.PropertiesManager"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="ict" uri="/ict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<%@include file="/jsp/common.jsp"%>
<head>
<title><s:property value="jsp_head_title"/></title>
<script type="text/javascript" src="js/locaQueryMgr.js"></script>
<script type="text/javascript" src="js/railingsMgr.js"></script>
<%
	String savePath=PropertiesManager.getProperty("common.properties", "LOCATION_ALARM_IMAGE");
	pageContext.setAttribute("LOCATION_ALARM_IMAGE",savePath);
	savePath=PropertiesManager.getProperty("common.properties", "LOCATION_LAST_ALARM_IMAGE");
	pageContext.setAttribute("LOCATION_LAST_ALARM_IMAGE",savePath);
%>
<script type="text/javascript">	
	var _posourArr = new Array();
	_posourArr[0]='未使用';
	_posourArr[1]='SID/NID区域定位';
	_posourArr[6]='网络小区号';
	_posourArr[8]='混合cell/sector';
	_posourArr[18]='手机AGPS（包括AGPS+AFLT的混合模式）';
	_posourArr[20]='手机AFLT';
	_posourArr[22]='A-GPS+AFLT';
	
	//查询
	function query_locaQuery(){
		var _deptId = $("#deptId").val();
		var _objType = $("#objType").val();
		
		$("#userInfoDiv").html("数据正在加载中，请稍候...").load("locaQuery_query.do?mode=ajax&locaQueryForm.deptId="+_deptId+"&locaQueryForm.objType="+_objType);
	}

	//显示
	function view_locaQuery(){
		$("#btn_show").attr("disabled","disabled");
		var locationIds = getCheckedValue();
		if(locationIds == ''){
			alert('请选择需要显示的手机终端！');
			$("#btn_show").attr("disabled","");
			return;
		}

		showLocaQuery(locationIds);
	}

	function showLocaQuery(locationIds){
		var formData = $("#queryForm").serialize();
		
		$.ajax({
			type: "POST",
			url: "locaQuery_view.do?locationIds="+locationIds,
			data: formData,
			success: function(result){
				$('#btn_show').attr('disabled','');//恢复按钮可用
				var data = result || [];
				if(data.length > 0){
					for(var i=0;i<data.length;i++){
						showLocation(data[i]);
						//if((i+1) == data.length){
						//	map.getMapControl().SetCenterAndZoom(data[i].rectifyLong, data[i].rectifyLat, map.getMapControl().GetMapScale());
						//}
					}
				}
				/*else{
					alert("没有可以显示的位置信息");
				}*/
			},
			dataType: "json",
			error: function(XMLHttpRequest, textStatus, errorThrown) {
			    alert("获取数据失败");
			}
		});
	}

	function showLocation(dataObj)
	{
		var remark = getRemark(dataObj.locId, dataObj.name, dataObj.locNum, dataObj.rectifyLong, dataObj.rectifyLat, dataObj.placeName, dataObj.locDate, dataObj.radius, dataObj.posour, dataObj.pic_path, dataObj.areaId, dataObj.areaName, dataObj.staffId, dataObj.staffName);
		//alert(remark);
		markLocOverlay(dataObj.rectifyLong, dataObj.rectifyLat, dataObj.locId, remark, dataObj.name);
	}

	//公用方法，实现在地图上打点，单击显示层内容
	function markLocOverlay(rectifyLong, rectifyLat, locId, divContent, holder, divTitle){
		if(divTitle == null || divTitle == "undefined")
			divTitle = "";
		
		map.addOverlay(new SuperMap.ictmap.LocMarker({x:rectifyLong, y:rectifyLat},"marker_"+locId, {minZoomLevel:5,name:holder, infowin:{width:300, height:300, title:divTitle,content:divContent}}));
	}
	
	//公用方法，返回用于显示的层内容
	function getRemark(locId, name, locNum, longitude, latitude, placeName, locDate, radius, posour, pic_path, railingId, railingName, staffId, staffName, id)
	{
		if(radius == null || radius == "null" || radius == "" || radius == undefined || radius == "undefined")
			radius = "未定义";
		if(posour == null || posour == "null" || posour == "" || posour == undefined || posour == "undefined")
			posour = "未定义";
		else
			posour = _posourArr[posour];
			
		if(placeName == null || placeName == "null" || placeName == "" || placeName == undefined || placeName == "undefined")
			placeName = '<a href="javascript:;" onclick="javascript:getPlacename(this,'+locId+')">获取位置</a>';
			
		if(railingName == null || railingName == "null" || railingName == "" || railingName == undefined || railingName == "undefined")
			railingName = "未设置";

		if(railingId == null || railingId == "null" || railingId == "" || railingId == undefined || railingId == "undefined")
			railingId = "";
		
		/*var remark=
			"<div style='font-size:9pt;text-align:left;color:blue;font-family:宋体,tahoma;'>"+
			"姓名:"+name+"<br/>"+
			"经度:"+longitude+"<br/>"+
			"纬度:"+latitude+"<br/>"+
			"定位号码:"+locNum+"<br/>"+
			"定位地点:"+placeName+"<br/>"+
			"定位时间:"+locDate+"<br/>"+
			"偏差半径:"+radius+"<br/>"+
			"位置来源:"+posour+"<br/>"+
			"</div>";*/
			
		var strPath="</td><td rowspan=\"8\">";
		
		if(pic_path == null || pic_path=="null" || pic_path == ""){
			strPath += "<img src=\"./images/mrtp.jpg\" width='60' height='90'>";
		}
		else{
			strPath += "<img src=\"<%=request.getContextPath()%>/upload/"+pic_path+"\" width='60' height='90'>";
		}
		
		var remark=
			"<table style='font-size:9pt;text-align:left;color:blue;font-family:宋体,tahoma;' border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td>姓名: <a target='_blank' href =\"<%=request.getContextPath()%>/module/archives/criminal_view.do?criminalId="+ id + "\"\">"+ name + "</a>" + 
			strPath+	
			//"</td><td rowspan=\"10\"><img src=\"<%=request.getContextPath()%>/upload/file/"+pic_path+"\" width='60' height='90'></td></tr>" +
			"<tr><td>监管责任人:<a target='_blank' href=\"<%=request.getContextPath()%>/module/system/user_view.do?userForm.userId="+ staffId +"\"\">" +staffName + "</a>" +
			"</td></tr><tr><td>经度:"+longitude+
			"</td></tr><tr><td>纬度:"+latitude+
			"</td></tr><tr><td>定位号码:"+locNum+
			"</td></tr><tr><td>定位地点:"+placeName+
			"</td></tr><tr><td>定位时间:"+locDate+
			"</td></tr><tr><td>活动区域:<a href='javascript:;' onclick='show_railings(\""+railingId+"\","+longitude+","+latitude+");'><font color='red'>"+railingName+"</font></a>(点击查看)"+
			"</td></tr><tr><td>偏差半径:"+radius+
			"</td></tr><tr><td>位置来源:"+posour+
			"</td></tr></table>";
		
		return remark;
	}
	
	//切换地图类型
	function changeMap(mapType){
		location.href= _contextPath+"/module/map/locaQuery_init.do?mapType="+mapType;
	}
</script>
</head>

<body>
<form id="queryForm" name="queryForm" action="" style="padding-top:3px;">
<div class="bg100">
	<table class="tb_query" border="0" cellspacing="1" cellpadding="1" align="center" >
		<tr style="display:none;">
			<td width="100%">地图类型：
				<select id="sltMapType" size="1"  onchange="changeMap(this.value);">
					<option value="supermap">默认地图</option>
					<!--<option value="google">Google地图</option>
					 <option value="baidu">Baidu地图</option>-->
				</select>
			</td>
		</tr>
		<tr>
			<td width="100%"><ict:select beanContextId="deptSelect" id="deptId" name="locaQueryForm.deptId" emptyOption="true" cssStyle="width:100%"/></td>
		</tr>
		<tr>
			<td width="100%">
				<select id="objType" name="locaQueryForm.objType" style="width:100%">
					<option value="0" selected><s:text name="ictmap.tittle"/></option>
					<!--  <option value="1">矫正工作者</option>-->
				</select>
			</td>
		</tr>
		<tr>
			<td class="bottom" colspan="2" align="center">
		   		<input type="button" id="btn_query" name="btn_query" class="button" value="查询" onclick="query_locaQuery();"/>
				<input type="button" id="btn_show" name="btn_show" class="button" value="显示" onclick="view_locaQuery();"/>
				<input type="button" id="btn_loca" name="btn_loca" class="button" value="定位" onclick="locations();"/>
				<%-- 
				<s:if test="%{userSession.userPermissions['/module/map/locaQuery_view.do'] != null}">
				</s:if>
				<s:if test="%{userSession.userPermissions['/module/map/locaQuery_location.do'] != null}">
				</s:if>
				--%>
			</td>
		</tr>
	</table>
</div>
	<div id="userInfoDiv" style="min-height:395px;width:100%" scrolling="auto"></div>			
	<div id="hidLocQueryView" style="display:none"></div>
</form>

<script type="text/javascript">	
	$('#btn_query').click();
</script>
</body>
</html>