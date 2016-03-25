<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<%@include file="/jsp/meta.jsp" %>
<script type="text/javascript">
function goBack(){
	map.getMapControl().CustomLayer.ClearLines();
	map.getMapControl().CustomLayer.ClearMarks();
	document.getElementById("hisTraceListDiv").innerHTML = "";
	document.getElementById("his_SubmitButton").disabled = false;// 将定位按钮去灰化
	
	document.getElementById("hisTraceMgrDiv").style.display='block';
	document.getElementById("hisTraceListDiv").style.display='none';
}


function pageonLoad(){
	/*index,name,ntel,longitude,latitude,insertDate*/
	//index是顺序下来的
	var checkbox = document.getElementsByName("chkNameValue");
	var pointArray = new Array();//用于画历史轨迹
	if (checkbox.length!=undefined && checkbox.length > 0) {
		for(var i = 0; i < checkbox.length ; i++) {
			if(checkbox[i].checked == true){
				var value = checkbox[i].value;
				var valueArr = value.split(",");

				pointArray.push({x:valueArr[4], y:valueArr[5], id:valueArr[1]});
			}
		}

		//画历史轨迹
		map.addOverlay(new SuperMap.ictmap.LocHisPolyline(pointArray));
	}else{
		return;
	}

}


function onSelect(index, params, flag) {

	var isInArrFlag = false;//未选中状态的
	if(!document.getElementById("cb" + index).checked)
		document.getElementById("cb" + index).checked = true;
	else
		isInArrFlag = true;//选中状态的

	
	onCheck(document.getElementById("cb" + index), index, params, flag);
	
}

function checkAll()
{
	var checkboxes = document.getElementsByName("chkNameValue");
	var checkall = document.getElementById("checkall");
	for(var i=0;i<checkboxes.length;i++)
	{
		var checkbox = checkboxes[i];
		if(checkbox.checked != checkall.checked)
			checkbox.checked = checkall.checked;
	}
}

function checkForAll()
{
	var checkboxes = document.getElementsByName("chkNameValue");
	var checkall = document.getElementById("checkall");
	var allChecked = true;
	for(var i=0;i<checkboxes.length;i++)
	{
		var checkbox = checkboxes[i];
		if(!checkbox.checked)
		{
			allChecked = false;
		}
		
	}
	
	checkall.checked = allChecked;
}




</script>
<title>历史轨迹列表</title>
</head>
<body>
<s:hidden id="totalNum" value="%{pageList.pages.total}"></s:hidden>
<s:if test="pageList.pages.total == -1">
<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<br/><br/><br/>数据库异常，请稍后再试!<br/><br/><br/>
</td></tr></table>
</s:if>
<s:elseif test="pageList.pages.total == 0">
<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<br/><br/><br/>未查找到历史轨迹！<br/><br/><br/>
	<input type="button" class="button" onclick="goBack()" value="返回"/>
</td></tr></table>
</s:elseif>
<s:else>
	<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_result" border="0" cellspacing="1" cellpadding="1" align="center">
      <tr class="tr_head">
		<td width="10%" align="center"><input id="checkall"	type="checkbox" class="ckboxAll" title="选中/取消选中" value="" onclick="checkAll()" /></td>
        <td width="10%" align="center">序号</td>
        <td width="35%" align="center">定位时间</td>
        <td width="35%" align="center">地理位置</td>
        <td width="10%" align="center">状态</td>
      </tr>
      <% int count = 0; %>
      <s:iterator id="traceInfo" value="%{pageList.results}" status="sta">
      <%count += 1; %>
      <tr class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
		<td align="center"><input id="cb<%=count %>" type="checkbox" name="chkNameValue" class="ckboxItem" <s:property value='#traceInfo.checked'/> 
				value="<%=count%>,<s:property value='#traceInfo.paramStr'/>" 
				onpropertychange="" /></td>
        <td align="center" style='cursor:hand;' 
				onclick=""><%=count %></td>
		<td align="center" style='cursor:hand;' 
				onclick="">
				<s:property value="#traceInfo.locDate"/></td>
		<td align="center"><s:property value='#traceInfo.placeName'/></td>
		<td align="center"><s:if test="#traceInfo.alarmStatus == 0">正常</s:if><s:else>报警</s:else></td>
      </tr>
     </s:iterator>
     <tr>
		<td colspan="4">
			<input type="button" class="button" onclick="goBack()" value="返回"/>
		</td>
	 </tr>
    </table>
	</td></tr></table>
</s:else>
</body>
</html>
<script type="text/javascript">
//延迟加载
setTimeout("doit()", 200); 

function doit(){
	<s:if test="pageList.pages.total > 0">
	   pageonLoad();
	</s:if>
}
</script>