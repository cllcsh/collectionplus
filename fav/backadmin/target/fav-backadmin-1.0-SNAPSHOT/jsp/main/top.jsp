<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/jsp/common.jsp"%>
<title><s:property value="jsp_head_title"/></title>
<link rel="stylesheet" type="text/css" href="<%=path%>/resource/themes/<%=themeName%>/top.css"/>
<script type="text/javascript" src="<%=path%>/resource/themes/<%=themeName%>/menu.js"></script>
<script type="text/javascript" src="<%=path%>/resource/js/JCore.roller.js"></script>
<script language="javascript">
<!--
var revise = 0;
var flag = false;
function getRevise(){
	var date1 = new Date();
	$.getJSON("main_getTime.do",function(data){
		var date2 = new Date();
		revise = (date1.getTime() + date2.getTime())/2 - data.time;
		flag = true;
	})
}
function showSysTime(){
	if(flag){
		var now = new Date();
		var sysDate = now.getTime() - revise;
		$("#systime").html(new Date(sysDate).format("yyyy-MM-dd hh:mm:ss"));
	}
}

function getAlarmInfo(){
	$.ajax({
	  url: "<%=path%>/module/information/beyondConfirm_getAlarmInfo.do?beyondConfirmForm.status=0",
	  cache: false,
	  dataType: 'json',
	  success: function(data){
		var html = "报警信息：<a href='<%=path%>/module/system/module_center.do?moduleId=30000&subModuleId=30200&mainUrl=/module/information/beyondConfirm_init.do?beyondConfirmForm.status=0' target='mainFrame' class='STYLE1' onclick='changestatus(30000);'><font color='red'>"+data.count+"</font></a>条";
		$("#alarmInfo").html(html);
	  }
	});
}
var mynotices = [{id:1,title:'公告1'},{id:2,title:'公告2'},{id:3,title:'公告3'}];
function getNotices(){
	$.ajax({
	  url: "<%=path%>/module/system/notice_getNotices.do",
	  cache: false,
	  dataType: 'json',
	  success: function(notices){
	    mynotices = notices;
		if(mynotices.length >0)
		  var html = '';
		  for(var i=0;i<mynotices.length;i++){
			html += "<li><a href='<%=path%>/module/system/module_center.do?moduleId=20000&subModuleId=20600&mainUrl=/module/system/notice_init.do?noticeInfoForm.id="+mynotices[i].id+"' target='mainFrame' class='STYLE1' onclick='changestatus(20000);'>"+mynotices[i].title+"</a></li>";
		  }
		$("#notice").html(html);
		var prollnotice = new JCore.UpRoller("notice",3,true,100,1,1,28);
		$("#notice").mouseover(function(){
			clearInterval(prollnotice.pevent);
		}).mouseout(function(){
			prollnotice.pevent=setInterval(function(){prollnotice.roll.call(prollnotice)},prollnotice.speed);
		})
	  }
	});
}

$(document).ready(function(){
	//getAlarmInfo();
	//setInterval("getAlarmInfo()",5*60*1000);
	getNotices();
	getRevise();
	setInterval(getRevise,5*60*1000);
	setInterval(showSysTime,1000);
});

function quitConfirm(obj){
	if(window.confirm("确定要退出系统吗？")){
		obj.href="logout.do";
	}
}
-->
</script>
</head>

<body>
<div class="head">
	<span class="logo"></span>
	<span class="operator">
	<span class="block"></span>
		<span style="height:17;margin-top:5px"><a href="<%=path%>/module/settings/module_center.do?moduleId=70000&subModuleId=70700&mainUrl=/module/system/user_modifyPasswordInit.do" target="mainFrame"><img border="0" src="<%=path%>/resource/themes/<%=themeName%>/main/pass.gif" width="69" height="17"/></a></span>
<%--		<span style="height:17;ma102rgin-top:5px"><a href="<%=path%>/module/settings/module_center.do?moduleId=50000&subModuleId=50100&mainUrl=/module/settings/basicUser_get.do" alt="用户信息" target="mainFrame"><img border="0" src="<%=path%>/resource/themes/<%=themeName%>/main/user.gif" width="69" height="17"/></a></span>--%>
		<span style="height:17;margin-top:5px"><a href="" target="_blank"><img border="0" src="<%=path%>/resource/themes/<%=themeName%>/main/qa.gif" width="69" height="17"/></a></span>
		<span style="height:17;margin-top:5px"><a href="javascript:;" target="_top" onclick="quitConfirm(this);"><img border="0" src="<%=path%>/resource/themes/<%=themeName%>/main/quit.gif" width="69" height="17"/></a></span>	</span>
</div>
<div class="menu outer">
	<div class="inner">
		<ul><li style="background:none;padding-right:100px;"></li>
			<li class="selected" style="background:none;"><a href="<%=path%>/firstpage_init.do" target="mainFrame">首页</a></li>
			<!--<li><a href="<%=path%>/firstpage_navi.do" target="mainFrame">功能导航</a></li>
			-->
			<s:iterator id="moduleMenu" value="moduleMenus" status="sta">
			<li id="li<s:property value="#moduleMenu.id"/>"><a href="<%=path%><s:property value="#moduleMenu.link"/>?moduleId=<s:property value="#moduleMenu.id"/>" target="mainFrame"><s:property value="#moduleMenu.name"/></a></li>
			</s:iterator>
			<!--<li><a href="<%=path%>/jsp/main/docdown.jsp" target="_blank">文档下载</a></li>
			--><li><span style="color:#fff;height:34px;line-height:34px;float:right;text-align:center">服务器时间：<span id="systime"></span></span></li>
		</ul>
	</div>
</div>
<div class="online">
	<div class="lArc"><div class="rArc"><div class="lArc2">
		<span class="blank"></span>
		<span class="userInfo">当前用户：<s:property value="userSession.userName" /></span>
		<!--<span class="alarmInfo" id="alarmInfo"><img align=absMiddle src="<%=path%>/resource/themes/<%=themeName%>/images/loading3.gif"></img>加载报警信息...</span>
		-->
		<span class="noticeInfo">
			<div id="roll-notice" style="overflow:hidden;height:28px;width:800px">
				<div id="roll-orig-notice">
					<ul id="notice" >
					</ul>
				</div>
				<div id="roll-copy-notice"></div>
			</div>
		</span>
		<span class="deptInfo">单位：<s:property value="userSession.deptName" /></span>
	</div></div></div>
</div>
</body>
</html>
