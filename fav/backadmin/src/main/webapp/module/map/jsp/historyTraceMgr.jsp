<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp" %>
<title><s:property value="jsp_head_title"/></title>
<style>
.font_12376A {border-bottom:1px solid #1D55A2;

padding-top:8px;
padding-left:10px;
padding-bottom:8px
input{border:1px solid #1D55A2;}
}
td {font: 12px;}
</style>
</head>
<script type="text/javascript">

function query() {
	var objTypeList = document.getElementById("objTypeList").value;
	var deptId = document.getElementById("deptList").value;
	
	//$("#frameCustomerList").html("页面正在加载中，请稍候...").load("historyTrace_treeNodes.do?showType="+objTypeList+"&depId="+deptId);
	$("#frameCustomerList").html("正在加载页面...").load("historyTrace_getTree.do?mode=ajax&showType="+objTypeList+"&depId="+deptId);
}

function pageOnLoad(){
	if(map.getMapControl()!=undefined){//防止刚加载的时候地图加载未完成报js脚本错误
		map.getMapControl().CustomLayer.ClearMarks();
		map.getMapControl().CustomLayer.ClearLines();
	}

	document.getElementById("hisTraceMgrDiv").style.display='block';
	document.getElementById("hisTraceListDiv").style.display='none';

	query();
	
}
</script>

<body id="body" style="overflow:hidden;">
<div id='hisTraceMgrDiv' style="padding-top:3px;">
<table  bgcolor="#C8DBF5" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_query" border="0" cellspacing="1" cellpadding="1" align="center" >
   		<tr>
			<td>
				<s:select id="deptList" list="form.deptList"
                          listKey="key" listValue="value"
                          value=""
                          name="" emptyOption="true" cssStyle="width: 100%;">
                </s:select>
			</td>
		</tr>
		<tr>
			<td>
				<select id="objTypeList" style="width: 100%;">
					<option value="0"><s:text name="ictmap.tittle"/></option>
					<option value="1">矫正工作者</option>
				</select>
			</td>
		</tr>
		<tr>
			<td class="bottom" colspan="2" align="center">
		   		<input name="btn" id="btn_query" type="button" class="button" value="查询" onclick="query();"/>
		   		<!-- <input name="btn" id="btn_query" type="button" class="button" value="清除" onclick="clean();"/> -->
			</td>
		</tr>
  </table></td></tr>
  <tr>	
    <td colspan="3" style="border-bottom:1px solid #1D55A2 ">
		<div height=290 width="100%" id="frameCustomerList" scrolling="auto"  />
    </td>
  </tr>
  <tr>
    <td height="60" colspan="3">
    <form id="frm" name="frmHistory">  
  	<table width="100%" border="0" cellpadding="0" cellspacing="0">
    	<tr><td class="font_12376A" style="width: 280px;">       	
       	<input type="text" id="d523_y" style="width: 35px;"/> 年
		<input type="text" id="d523_M" style="width: 20px;"/> 月
		<input type="text" id="d523_d" style="width: 20px;"/> 日
		<input type="text" id="d523_HH" style="width: 20px;"/> 时
		<input type="text" id="d523_mm" style="width: 20px;"/> 分
		<input type="text" id="d523_ss" style="width: 20px;display:none;"/>
		<img onclick="WdatePicker({el:'d523_y',dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:pickedFunc_s,maxDate:'#F{$dp.$D(\'d524_y\')}'})" src="../../resource/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle" style="cursor:pointer"/>
       	
   		</td></tr>
    	<tr><td class="font_12376A">
		<tr><td class="font_12376A" style="width: 280px;">       	
       	<input type="text" id="d524_y"  style="width: 33px;"/> 年
		<input type="text" id="d524_M" style="width: 20px;"/> 月
		<input type="text" id="d524_d" style="width: 20px;"/> 日
		<input type="text" id="d524_HH" style="width: 20px;"/> 时
		<input type="text" id="d524_mm" style="width: 20px;"/> 分
		<input type="text" id="d524_ss" style="width: 20px;display:none;"/>
		<img onclick="WdatePicker({el:'d524_y',dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:pickedFunc_e,minDate:'#F{$dp.$D(\'d523_y\')}'})" src="../../resource/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle" style="cursor:pointer"/>
       	
    	</td></tr>
    	<tr><td class="font_12376A">
     	         预设：<select title="提供几种常用时间区间供快速填入" name="destineDate" onchange="selDestineDateChange()">
           <option value="today">今天</option>
           <option value="yesterday">昨天</option>
           <option value="hrs8">过去8小时</option>
           <option value="hrs24">过去24小时</option>
       	   </select>   
      	   <input name="Submit" id="his_SubmitButton" type="button" class="button" value="确定" onclick="toList();"/> 
    	</td></tr>
    </table>
    </form>
    </td>
  </tr>
</table>
</div>
<div id="hisTraceListDiv"></div>
</body>
</html>
