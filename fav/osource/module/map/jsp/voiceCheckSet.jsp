<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>
<%@taglib prefix="ict" uri="/ict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/voiceCheckMgr.js"></script>
<script type="text/javascript">
	<s:if test="%{actionName == 'voiceCheck_save'}">
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'voiceCheck_save.do',
			    saveUrlTo:'voiceCheck_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:if>
	<s:else>
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'voiceCheck_update.do',
			    saveUrlTo:'voiceCheck_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:else>
</script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<s:form id="setForm" name="voiceCheckForm" action="%{actionName}">
	<s:hidden name="voiceCheckForm.id"></s:hidden>
	<s:hidden name="voiceCheckForm.streamNo"></s:hidden>
	<s:hidden name="criminalId" id="criminalId"></s:hidden>
	<s:hidden name="allCriminalId" id="allCriminalId"></s:hidden>
	
	<s:hidden name="recordflag" id="recordflag" value="0"></s:hidden>
	<s:hidden name="imptype" id="imptype" value="9"></s:hidden>
	<s:hidden name="startTime" id="startTime"></s:hidden>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
				<s:if test="%{actionName == 'voiceCheck_save'}">
				<tr>
					<th>司法单位：</th>
					<td width="35%">
					<ict:select id="deptId" name="deptId" beanContextId="deptSelect" onchange="makeEmpty()" 
						 emptyOption="false" cssStyle="width:155px"></ict:select>
				    </td>
					<th><s:text name="ictmap.tittle"/>：</th>
					<td width="35%">
				    <input id="name" type="text"    onkeyup="change()" onfocus="change()"
							name="name" value=""/>
							 <font class="redStar">*</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="##" onclick="addCriminal();makeEmpty()">增加至待抽查人员列表</a>
							 <div id="divAutoList" style="display: none;overflow: auto;position: absolute;z-index:10"></div>
					    </td>
		            </tr>
		            <tr>
								<th>
									待抽查人员列表：
								</th>
								<td colspan="3">
									<div align="left">
											<table width="100%" border="0" cellpadding="1" cellspacing="1" height="20" id="table_add">	
												<tr>
													<th style="text-align: left;padding-left: 36%">社区矫正人员</th>
												</tr>
											</table>
									</div>
								</td>
							</tr>
				   </s:if>
				   <s:else>
				            <tr>
								<th>
									司法单位：
								</th>
								<td>
									<ict:select id="deptId" name="voiceCheckForm.deptId" beanContextId="deptSelect" disabled="true" 
						             emptyOption="false" cssStyle="width:155px"></ict:select>
								</td>
								<th>
									矫正对象：
								</th>
								<td>
							    <ict:select id="criminalId" cssStyle="width:150px" 
											name="voiceCheckForm.criminalId" beanContextId="tb_criminal"
											emptyOption="true" cssClass="input2" disabled="true"></ict:select>
								</td>
							</tr>
				            <tr>
								<th>
									定位号码：
								</th>
								<td>
									<s:textfield id="msdn" disabled="true" name="voiceCheckForm.msdn" disabled="true"/>
								</td>
								<th>
									起始时间：
								</th>
								<td>
								<input id="startDate" type="text" name="voiceCheckForm.startDate" size="25" disabled="true"  
							 readonly="true" class="Wdate" value='<s:date format="yyyy-MM-dd HH:mm:ss" name="voiceCheckForm.startDate"/>'/>
								</td>
							</tr>
							 <tr>
								<th>
									结束时间：
								</th>
								<td>
									<input id="endDate" type="text" name="voiceCheckForm.endDate" size="25" disabled="true"  
							 readonly="true" class="Wdate" value='<s:date format="yyyy-MM-dd HH:mm:ss" name="voiceCheckForm.endDate"/>'/>
								</td>
								<th>
									抽查结果：
								</th>
								<td>
								<dict:select codeType="voice_check_code" name="voiceCheckForm.resultCode" id="resultCode" emptyOption="true" />
								</td>
							</tr>
				   
				   <tr>
								<th>
									声纹录入时声音片段：
								</th>
								<td>
								<s:iterator id="it" value="voiceCheckForm.oldVoicePath1" status="s">
								<a href="javascript: play('http://221.226.150.105:8082/record/'+'<s:property value="it"/>')">片段${s.index+1}</a>
								</s:iterator>
								</td>
								<th>
									声纹验证时声音片段：
								</th>
								<td>
								
								<s:iterator var="it" value="voiceCheckForm.voicePath1" status="s">
								<a href="javascript: play('http://221.226.150.105:8082/record/'+'<s:property value="it"/>')">片段${s.index+1}</a>
								</s:iterator>
								</td>
							</tr>
							<tr id="tr" style="display: none">
							<td colspan="4" align="center">
							<div align="center">
							<object ID="video2" CLASSID="clsid:CFCDAA03-8BE4-11cf-B84B-0020AFBBCCFA">
							  <embed id="video" src="" type="audio/x-pn-realaudio-plugin" autostart="true" align="center"  height="40">
							  </embed>
							</object>
							</div>
							</td>
							</tr>
				    </s:else>
					<tr>
						<td class="bottom" align="center" colspan="4">
							<div align="center">
								<s:if test="%{actionName == 'voiceCheck_save'}">
									<input type="button" id="btn_save" class="button"  value="抽查"/>
								</s:if>
								<s:else>
									<input type="button" id="btn_save" class="button" value="保存" />
								</s:else>
								<input type="button" onclick="javascript:history.back();" class="button" value="返回"/>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</s:form>

<s:form id="commonForm">
<input type="hidden" id="nameTemp" name="voiceCheckForm.criminalName"/>
<input type="hidden" id="eduDeptTemp" name="voiceCheckForm.deptId"/>
<input type="hidden" id="allCriminalIdTemp" name="voiceCheckForm.allCriminalId"/>
</s:form>
<script type="text/javascript" src="js/findCriminal.js"></script>
<script>
function play(it){
document.getElementById("tr").style.display="";
document.getElementById('video').outerHTML="<embed src="+it+" id=video type=audio/x-pn-realaudio-plugin autostart=true height=40>";
}
</script>
<script type="text/javascript">
$.formValidator.initConfig( {
		formid :"setForm",
		onerror : function(msg) {
			alert(msg)
		},
		onsuccess : function() {
			return true;
		}
	});
	<s:if test="%{actionName == 'voiceCheck_save'}">
$("#allCriminalId").formValidator( {
		}).functionValidator({
	isvalid : true,
	fun : function(){
    if(document.getElementById("allCriminalId").value!=""){
    return true;
    }
    return false;
    },
	onerror:"请正确输入并选择已存在的社区矫正人员增加至待抽查人员列表中！"
});	
</s:if>
</script>
<script type="text/javascript">
    var allCriminalId="";
	function addCriminal(){
	var select=document.getElementById("criminalId");
	var NowCriminalId=select.value;
	if(""==NowCriminalId){
	alert("请选择待抽查的社区矫正人员！");
    return;
	}
	if(allCriminalId.indexOf(NowCriminalId)>=0){
	alert("该社区矫正人员已在待抽查人员列表中！");
	return;
	}else{
	allCriminalId=allCriminalId+NowCriminalId+",";
	var selectName = document.getElementById("name").value;
	addNewLine(selectName,NowCriminalId);
	}
	}
	function addNewLine(selectName,NowCriminalId){
		var tableTop = document.getElementById("table_add");
		//创建新表格
		var newTr  = tableTop.insertRow();
		var newTd1 = newTr.insertCell(0);

		newTd1.style.cssText = "text-align: left;padding-left: 30%";

		//创建每个新的元素
		var newReceiveDate = document.createElement("<input id='"+NowCriminalId+"' type='text' name='criminalId1' value='"+selectName+"'/>");
		var newReceiveDate1 = document.createElement("<input type='button' class='button' name='criminalId1' value='删除' onclick='del("+NowCriminalId+")'/>");

		//为每个新元素设置属性和绑定事件
		newReceiveDate.style.cssText = "width: 170px";
		newTd1.appendChild(newReceiveDate);
		newTd1.appendChild(newReceiveDate1);
		document.getElementById("allCriminalId").value=allCriminalId;
	}
	function del(obj){
	var index=document.getElementById(obj).parentNode.parentNode.rowIndex;
    var table = document.getElementById("table_add");
    table.deleteRow(index);
    allCriminalId=allCriminalId.replace(obj+",","")
	document.getElementById("allCriminalId").value=allCriminalId;
	}
</script>

</body>
</html>