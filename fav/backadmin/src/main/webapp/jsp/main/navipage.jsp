<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/jsp/common.jsp"%>
<title>功能导航</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/resource/themes/<%=themeName%>/top.css"/>
<script type="text/javascript" src="<%=path%>/resource/themes/<%=themeName%>/menu.js"></script>
<script type="text/javascript" src="<%=path%>/resource/js/JCore.roller.js"></script>
<style>
html,body {margin:0px;padding:0px;font-size:12px;text-align:center;height:100%;}
#bg_div {
	background:#FFFFFF;
	height:100%;
	border-left-width: 8px;
	border-left-style: solid;
	border-left-color: #353c44;
	border-right-width: 8px;
	border-right-style: solid;
	border-right-color: #353c44;
	text-align:center;
}

#dynamicMessage {
width:100%;
margin:0px auto;
}
#dynamicMessage li {
list-style-type:none;
width:100%;
height:28px;
line-height:28px;
text-align:center;
float:left;
margin-left:1px;
margin-bottom:0;
}
#title {
color:#666666;
font-size:18px;
font-family:"微软雅黑";
}

a:hover {
BACKGROUND: #E6F0F2;
color: #666666; 
text-decoration: underline;
}
</style>
<script language="JavaScript"><!--
//重复提交检查
var submitFlag=false;

function getIndex(){
	$("#index").html("正在查询数据，请稍候...").load("firstpage_index.do");
}
function getLeftNum(){
	$.ajax({
	  url: "<%=path%>/module/archives/criminal_getLeftNum.do?sourceFlag=1",
	  cache: false,
	  dataType: "json",
	  success: function(data){
		var html = "近期有<a href='<%=path%>/module/archives/module_center.do?moduleId=40000&subModuleId=40100&mainUrl=/module/archives/criminal_init.do%3FsourceFlag%3D1' target='mainFrame' class='STYLE1' onclick='top.topFrame.document.changestatus(40000);'><font color='red'>"+data.leftnum+"</font></a>名<s:text name="ictmap.tittle"/>将要矫正期满";
		$("#leftnum").html(html);
	  }
	});
}
function getLeaveNum(){
	$.ajax({
	  url: "<%=path%>/module/assess/leaveRegister_getLeaveNum.do?leaveRegisterForm.appStatus=1",
	  cache: false,
	  dataType: "json",
	  success: function(data){
		var html = "有<a href='<%=path%>/module/assess/module_center.do?moduleId=50000&subModuleId=50200&mainUrl=/module/assess/leaveRegister_init.do?leaveRegisterForm.appStatus=1' target='mainFrame' class='STYLE1' onclick='top.topFrame.document.changestatus(50000);'><font color='red'>"+data.leavenum+"</font></a>份请假申请单等待销假";
		$("#leavenum").html(html);
	  }
	});
}

//function loadMessage(){
//	$.ajax({
//	  url: "<%=path%>/module/information/informationDownQuery_loadMessage.do",
//	  cache: false,
//	  dataType: 'json',
//	  success: function(data){
//	   		 var inHtml = "";
//			for(var i=0;i<data.length;i++){
//				inHtml += "<li><a href='<%=path%>/module/module_center.do?moduleId=20000&subModuleId=20300&mainUrl=/module/information/informationDownQuery_init.do?informationDownQueryForm.insertId=" + data[i].insertId+
//				"'target='mainFrame' class='STYLE1' onclick='changestatus(20000);'>" + "发送人:" + data[i].userName + "，内容:" + data[i].content + "。</a></li>";
//		}
//		$("#dynamicMessage").html(inHtml);
//		var prollnotice = new JCore.UpRoller("dynamicMessage",3,true,100,1,1,28);
//		$("#dynamicMessage").mouseover(function(){
//			clearInterval(prollnotice.pevent);
//		}).mouseout(function(){
//			prollnotice.pevent=setInterval(function(){prollnotice.roll.call(prollnotice)},prollnotice.speed);
//		})
//	  }
//	});
//}

$(document).ready(function(){
	//getIndex();
	//getLeftNum();
	//getLeaveNum();
	//loadMessage();
}); 


--></script>
</head>
<body>
<div>
	</br>
	<div>
		<span id="title">社区矫正工作流程</span>
		</br>
	</div>
 <div class="div_allinline">&nbsp;
	<div class="subdiv_allinline">
		&nbsp;&nbsp;&nbsp;
		<font class="title">矫正衔接</font></br>&nbsp;
		<s:if test="%{userSession.userPermissions['/module/archives/criminal_init.do'] != null}">
		<li><a href="<%=path%>/module/assess/module_center.do?moduleId=40000&subModuleId=40100&mainUrl=/module/archives/criminal_init.do" onclick='top.topFrame.document.changestatus(40000);'><font class="content">矫正对象基本信息</font></a></li>
		</s:if>
		<s:if test="%{userSession.userPermissions['/module/archives/surveyAssess_init.do'] != null}">
		<li><a href="<%=path%>/module/assess/module_center.do?moduleId=40000&subModuleId=48500&mainUrl=/module/archives/surveyAssess_init.do" onclick='top.topFrame.document.changestatus(40000);'><font class="content">调查评估</font></a></li>
		</s:if>
		<s:if test="%{userSession.userPermissions['/module/archives/criminalEscort_init.do'] != null}">
		<li><a href="<%=path%>/module/assess/module_center.do?moduleId=40000&subModuleId=48200&mainUrl=/module/archives/criminalEscort_init.do" onclick='top.topFrame.document.changestatus(40000);'><font class="content">暂予监外执行罪犯押送信息</font></a></li>
		</s:if>
		<s:if test="%{userSession.userPermissions['/module/archives/newCrime_init.do'] != null}">
		<li><a href="<%=path%>/module/assess/module_center.do?moduleId=40000&subModuleId=49500&mainUrl=/module/archives/newCrime_init.do" onclick='top.topFrame.document.changestatus(40000);'><font class="content">余罪或新罪有关情况记录</font></a></li>
		</s:if>
	</div>&nbsp;&nbsp;
	<div class="subdiv_allinline">
		&nbsp;&nbsp;&nbsp;
		<font class="title">矫正执行</font></br></br>
		<s:if test="%{userSession.userPermissions['/module/archives/collectGroup_init.do'] != null}">
		<li><a href="<%=path%>/module/assess/module_center.do?moduleId=40000&subModuleId=80000&mainUrl=/module/archives/collectGroup_init.do" onclick='top.topFrame.document.changestatus(40000);'><font class="content">矫正小组成员</font></a></li>
		</s:if>
		<s:if test="%{userSession.userPermissions['/module/archives/rectifyProject_init.do'] != null}">
		<li><a href="<%=path%>/module/assess/module_center.do?moduleId=40000&subModuleId=40115&mainUrl=/module/archives/rectifyProject_init.do" onclick='top.topFrame.document.changestatus(40000);'><font class="content">矫正方案信息</font></a></li>
		</s:if>
		<s:if test="%{userSession.userPermissions['/module/archives/dailyReport_init.do'] != null}">
		<li><a href="<%=path%>/module/assess/module_center.do?moduleId=50000&subModuleId=51100&mainUrl=/module/archives/dailyReport_init.do" onclick='top.topFrame.document.changestatus(50000);'><font class="content">日常报告</font></a></li>
		</s:if>
		<s:if test="%{userSession.userPermissions['/module/archives/study_init.do'] != null}">
		<li><a href="<%=path%>/module/assess/module_center.do?moduleId=50000&subModuleId=50400&mainUrl=/module/assess/study_init.do" onclick='top.topFrame.document.changestatus(50000);'><font class="content">教育学习</font></a></li>
		</s:if>
		<s:if test="%{userSession.userPermissions['/module/archives/labor_init.do'] != null}">
		<li><a href="<%=path%>/module/assess/module_center.do?moduleId=50000&subModuleId=50500&mainUrl=/module/assess/labor_init.do" onclick='top.topFrame.document.changestatus(50000);'><font class="content">社区服务</font></a></li>
		</s:if>
	</div>&nbsp;&nbsp;
	<div class="subdiv_allinline">
		&nbsp;&nbsp;&nbsp;
		<font class="title">管理监督</font></br></br>
		<s:if test="%{userSession.userPermissions['/module/archives/rectifyAreaJudgement_init.do'] != null}">
		<li><a href="<%=path%>/module/assess/module_center.do?moduleId=40000&subModuleId=42800&mainUrl=/module/archives/rectifyAreaJudgement_init.do" onclick='top.topFrame.document.changestatus(40000);'><font class="content">进入特定区域（场所）申请</font></a></li>
		</s:if>
		<s:if test="%{userSession.userPermissions['/module/archives/outApply_init.do'] != null}">
		<li><a href="<%=path%>/module/assess/module_center.do?moduleId=40000&subModuleId=49000&mainUrl=/module/archives/outApply_init.do" onclick='top.topFrame.document.changestatus(40000);'><font class="content">外出申请</font></a></li>
		</s:if>
		<s:if test="%{userSession.userPermissions['/module/archives/newRelocate_init.do'] != null}">
		<li><a href="<%=path%>/module/assess/module_center.do?moduleId=40000&subModuleId=42900&mainUrl=/module/archives/newRelocate_init.do" onclick='top.topFrame.document.changestatus(40000);'><font class="content">居住地变更</font></a></li>
		</s:if>
		<s:if test="%{userSession.userPermissions['/module/archives/checkEvidenve_init.do'] != null}">
		<li><a href="<%=path%>/module/assess/module_center.do?moduleId=40000&subModuleId=48300&mainUrl=/module/archives/checkEvidenve_init.do" onclick='top.topFrame.document.changestatus(40000);'><font class="content">核实取证</font></a></li>
		</s:if>
	</div>&nbsp;&nbsp;
	<div class="subdiv_allinline">
		&nbsp;&nbsp;
		<font class="title">考核奖惩</font></br></br>
		<s:if test="%{userSession.userPermissions['/module/archives/alarmExamine_init.do'] != null}">
		<li><a href="<%=path%>/module/assess/module_center.do?moduleId=40000&subModuleId=47400&mainUrl=/module/archives/alarmExamine_init.do" onclick='top.topFrame.document.changestatus(40000);'><font class="content">警告信息</font></a></li>
		</s:if>
		<s:if test="%{userSession.userPermissions['/module/archives/submitCheck_init.do'] != null}">
		<li><a href="<%=path%>/module/assess/module_center.do?moduleId=40000&subModuleId=45200&mainUrl=/module/archives/submitCheck_init.do" onclick='top.topFrame.document.changestatus(40000);'><font class="content">提请治安处罚</font></a></li>
		</s:if>
		<s:if test="%{userSession.userPermissions['/module/archives/submitCheck_init.do'] != null}">
		<li><a href="<%=path%>/module/assess/module_center.do?moduleId=40000&subModuleId=45200&mainUrl=/module/archives/submitCheck_init.do" onclick='top.topFrame.document.changestatus(40000);'><font class="content">提请减刑</font></a></li>
		</s:if>
		<s:if test="%{userSession.userPermissions['/module/archives/submitCheck_init.do'] != null}">
		<li><a href="<%=path%>/module/assess/module_center.do?moduleId=40000&subModuleId=45200&mainUrl=/module/archives/submitCheck_init.do" onclick='top.topFrame.document.changestatus(40000);'><font class="content">提请撤销缓刑（假释）</font></a></li>
		</s:if>
		<s:if test="%{userSession.userPermissions['/module/archives/submitCheck_init.do'] != null}">
		<li><a href="<%=path%>/module/assess/module_center.do?moduleId=40000&subModuleId=45200&mainUrl=/module/archives/submitCheck_init.do" onclick='top.topFrame.document.changestatus(40000);'><font class="content">提请收监执行</font></a></li>
		</s:if>
		<s:if test="%{userSession.userPermissions['/module/archives/outLine_init.do'] != null}">
		<li><a href="<%=path%>/module/assess/module_center.do?moduleId=40000&subModuleId=49200&mainUrl=/module/archives/outLine_init.do" onclick='top.topFrame.document.changestatus(40000);'><font class="content">社区矫正人员违规记录</font></a></li>
		</s:if>
		<s:if test="%{userSession.userPermissions['/module/archives/crimeAgain_init.do'] != null}">
		<li><a href="<%=path%>/module/assess/module_center.do?moduleId=40000&subModuleId=49300&mainUrl=/module/archives/crimeAgain_init.do" onclick='top.topFrame.document.changestatus(40000);'><font class="content">社区矫正人员再犯罪记录</font></a></li>
		</s:if>
		<s:if test="%{userSession.userPermissions['/module/archives/prisonTermChange_init.do'] != null}">
		<li><a href="<%=path%>/module/assess/module_center.do?moduleId=40000&subModuleId=49400&mainUrl=/module/archives/prisonTermChange_init.do" onclick='top.topFrame.document.changestatus(40000);'><font class="content">刑期变动情况记录</font></a></li>
		</s:if>
		<s:if test="%{userSession.userPermissions['/module/archives/checkInfo_init.do'] != null}">
		<li><a href="<%=path%>/module/assess/module_center.do?moduleId=50000&subModuleId=51200&mainUrl=/module/archives/checkInfo_init.do" onclick='top.topFrame.document.changestatus(50000);'><font class="content">考核信息管理</font></a></li>
		</s:if>
	</div>&nbsp;&nbsp;
	<div class="subdiv_allinline">
		&nbsp;&nbsp;&nbsp;
		<font class="title">矫正解除</font></br></br>
		<s:if test="%{userSession.userPermissions['/module/archives/newRectifyRemove_init.do'] != null}">
		<li><a href="<%=path%>/module/assess/module_center.do?moduleId=40000&subModuleId=49600&mainUrl=/module/archives/newRectifyRemove_init.do" onclick='top.topFrame.document.changestatus(40000);'><font class="content">矫正解除（终止）记录</font></a></li>
		</s:if>
	</div>
</div>
</body>
<script language="javascript"> 
//重复提交检查
submitFlag=true;
//-->
</script>
</html>