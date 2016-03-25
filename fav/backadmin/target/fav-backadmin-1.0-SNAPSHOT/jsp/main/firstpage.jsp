<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/jsp/common.jsp"%>
<title>首页</title>

<link id="bs-css" href="<%=path%>/admin/css/bootstrap-eagle.css" rel="stylesheet">
<style type="text/css">
    body {
/*        padding-bottom: 10px;*/
    }
    .sidebar-nav {
        padding: 9px 0;
    }
</style>
<link href="<%=path%>/admin/css/opa-icons.css" rel='stylesheet'>
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
</style>
<script language="JavaScript">
<!--
//重复提交检查
var submitFlag=false;

function getIndex(){
	$("#index").html("正在查询数据，请稍候...").load("firstpage_index.do");
}
function getEventCount(){
	$.ajax({
	  url: "<%=path%>/module/basedata/presentation_getEventCount.do",
	  cache: false,
	  dataType: "json",
	  success: function(data){
		$("#unfinishEventCount").html(data.unfinishEventCount);
		$("#newEventCount").html(data.newEventCount);
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
	//getEventCount();
	//getLeaveNum();
	//loadMessage();
}); 


-->
</script>
</head>
<body>
<div class="well span6">
        <table class="table table-bordered table-striped">
            <tbody><tr>
                    <th colspan="2">
                        软件信息
                    </th>
                </tr>
                <tr>
                    <td >
                        系统名称: 
                    </td>
                    <td>
                       	收藏+
                    </td>
                </tr>
                <tr>
                    <td>
                        系统版本: 
                    </td>
                    <td>
                        1.0版
                    </td>
                </tr>
                <tr>
                    <td>
                        官方网站: 
                    </td>
                    <td>
                        <a href="http://120.132.92.55/fav" target="_blank">http://120.132.92.55/fav</a>
                    </td>
                </tr>
              <!-- <tr>
                    <td>
                        交流论坛: 
                    </td>
                    <td>
                        <a href="http://bbs.extensivepro.com">http://bbs.extensivepro.com</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        BUG反馈邮箱: 
                    </td>
                    <td>
                        <a href="mailto:bug@extensivepro.com">bug@extensivepro.com</a>
                    </td>
                </tr>
 -->  
            </tbody>
        </table>
    </div>
    
<!-- 
<div class="well span6">

        <table class="table table-bordered table-striped">
            <tbody><tr>
                    <th colspan="2">
                        待处理事务
                    </th>
                </tr>
                <tr>
                    <td width="110">
                        未结束事件: 
                    </td>
                    <td>
                        <span id="unfinishEventCount"></span><a href="<%=path%>/module/basedata/event_init.do">[安全事件列表]</a>
                    </td>
                </tr>
                <tr>
                    <td width="110">
                        24小时新增事件: 
                    </td>
                    <td>
                       <span id="newEventCount"></span><a href="<%=path%>/module/basedata/event_init.do">[安全事件列表]</a>
                    </td>
                </tr>
<!--                <tr>
                    <td>
                        未读消息: 
                    </td>
                    <td>
                        0 <a href="message!inbox.action">[收件箱]</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        未处理缺货登记数: 
                    </td>
                    <td>
                        0 <a href="goods_notify!list.action">[到货通知]</a>
                    </td>
                </tr>
            </tbody>
        </table>
    </div> -->
</body>
<script language="javascript"> 
//重复提交检查
submitFlag=true;
//-->
</script>
</html>