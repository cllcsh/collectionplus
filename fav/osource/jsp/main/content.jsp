<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/jsp/common.jsp"%>
<title><s:property value="jsp_head_title"/></title>
<style type="text/css">
<!--
html,body{ 
	margin:0px; 
	height:100%; 
}
body {overflow:hidden;}
-->
</style>
<script type="text/javascript">
var imgClose = '<%=path%>/resource/themes/<%=themeName%>/images/FClose.gif';
var imgOpen = '<%=path%>/resource/themes/<%=themeName%>/images/FOpen.gif';
function toggleBar(){
	$('#menuTd').toggle();
	if($('#menuTd').css('display') == 'none'){
		$('#barImg').attr('src',imgOpen);
	} else {
		$('#barImg').attr('src',imgClose);
	}
}

/* 
 * 显示报警信息框
 */ 
 


//在地图上显示详细的越界信息
function view_alarminfo(id, longitude,latitude,railingsId,name,num,place,dates){
	window.open('<%=path %>/module/map/alarm_view.do?id='+id+'&displayWay=1&longitude='+longitude+'&latitude='+latitude+'&name='+name+'&num='+num+'&place='+place+'&dates='+dates+'&railingsId='+railingsId,'newwindow','width=600,height=500, top=150, left=200');
}

//定期查询报警数据库，有新的报警信息的话，弹出报警信息提示框
function getAlarmInfoList(){
	$.getJSON("<%=path %>/module/map/alarm_getAlarmInfoList.do",function(data){
		var results = data.results || [];
		if(results.length>0){
			var strs = "";
			var map_href = "";
			
			for(var i=0;i<results.length;i++){
				var type;
				var holder = results[i].locationInfo.holder;
				var time = new Date(results[i].locationInfo.locDate.time).format("yyyy-MM-dd hh:mm:ss");
				var place = results[i].locationInfo.placeName;
				
				if(results[i].type == 1){type="出界";}else{type="入界";}

				map_href = "javascript:view_alarminfo("+results[i].locationInfo.id+",'"+results[i].locationInfo.rectifyLong+"','"+results[i].locationInfo.rectifyLat+"','"+results[i].locationInfo.railingsId+"','\\\'"+holder+"\\\'','\\\'"+results[i].locationInfo.locNum+"\\\'','\\\'"+place+"\\\'','\\\'"+time+"\\\'')";
				
				strs += "<a href=\""+map_href+"\"><font color=red>"+holder+"</font>&nbsp;&nbsp;"+time+"&nbsp;&nbsp;在&nbsp;&nbsp;<font color=red>"+place+"</font>&nbsp;&nbsp;"+type+"</a><BR>";
			}
			$.messager.show('<font color=black>最新报警信息</font>', strs, 0, {'width':300,'height':150});
		}
	})
}

</script>
</head>

<body class="coolscrollbar">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="8" bgcolor="#353c44">&nbsp;</td>
    <td id="menuTd" width="147" height="100%" valign="top"><iframe height="100%" width="100%" style="height:100%" name="leftIFrame" frameborder="0" src="module_menu.do?moduleId=<s:property value="moduleId"/>&subModuleId=<s:property value="subModuleId"/>"></iframe></td>
    <td width="8" class="probar" onclick="javaScript:toggleBar()" style="cursor: hand;"><img id="barImg" src="<%=path%>/resource/themes/<%=themeName%>/images/FClose.gif" alt=""></td>
    <td height="100%" valign="top"><iframe height="100%" width="100%" style="height:100%" name="mainIFrame" frameborder="0" src="<%=path%><s:property value="mainUrl"/>"></iframe></td>
    <td width="8" bgcolor="#353c44">&nbsp;</td>
  </tr>
</table>
</body>
</html>