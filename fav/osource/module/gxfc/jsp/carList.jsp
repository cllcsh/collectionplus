<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<html>
<head>
<title><s:property value="jsp_head_title"/>-车源管理</title>
<%@include file="/jsp/common.jsp"%>
</head>

<body>
<%@include file="/jsp/include/pageInc.jsp"%>
<s:else>
	<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_result" border="0" cellspacing="1" cellpadding="1" align="center">
      <tr class="tr_head" align="center">
		<td width="40" nowrap><input id="checkboxtop"	type="checkbox" class="ckboxAll" title="选中/取消选中" value="" onclick="checkAll('checkboxtop','ckboxItem');" /></td>
		<td width="20%">标题</td>
		<td width="10%">品牌</td>
        <td width="10%">版本</td>
		<td width="5%">系列</td>
        <td width="5%">车型</td>
        <td width="5%">年款</td>
		<td width="10%">车源时间</td>
		<td width="10%">车源状态</td>
		<td width="10%">更新时间</td>
		<td width="5%">所属用户名</td>
		<td width="10%">操作</td>
      </tr>
      <s:iterator id="carInfo" value="%{pageList.results}" status="sta">
      <tr align="center" class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
		<td>
			<input name="items[<s:property value="#sta.index"/>].id" type="checkbox" class="ckboxItem" value="<s:property value="#carInfo.id"/>" onclick="check('checkboxtop','checkbox1');" />
		</td>
        <td>
			<a href="javascript:view_car(<s:property value='#carInfo.id'/>)"><s:property value="#carInfo.title"/></a>
		</td>
        <td><s:property value="#carInfo.brandInfo.name"/></td>
        <td><s:property value="#carInfo.carVersionInfo.name"/></td>
        <td><s:property value="#carInfo.seriesInfo.name"/></td>
        <td><s:property value="#carInfo.modelsInfo.name"/></td>
        <td><s:property value="#carInfo.modelyearInfo.name"/></td>
		<td><s:date name="#carInfo.insertDate" format="yy-MM-dd HH:mm" /></td>
		<td>
			<s:if test="%{#carInfo.carStatus == 0}">审核中</s:if>
            <s:if test="%{#carInfo.carStatus == 1}">在售中</s:if>
            <s:if test="%{#carInfo.carStatus == 2}">已下架</s:if>
            <s:if test="%{#carInfo.carStatus == 3}">已售完</s:if>
            <s:if test="%{#carInfo.carStatus == 4}">未通过</s:if>
		</td>
		<td><s:date name="#carInfo.updateDate" format="yy-MM-dd HH:mm" /></td>
		<td><s:property value="#carInfo.userInfo.loginName"/></td>
        <td align="center" >
        	<a href="javascript:edit_car(<s:property value='#carInfo.id'/>);" >编辑</a>
        	<s:if test="%{#carInfo.carStatus == 0}">
			<a href="javascript:openDialogUpload(<s:property value='#carInfo.id'/>);" >审核</a>
			</s:if>
			<s:if test="%{#carInfo.carStatus < 2}">
        	<a href="javascript:carOffopenDialogUpload(<s:property value='#carInfo.id'/>)" >下架</a>
			</s:if>
		</td>
      </tr>
      </s:iterator>
    </table>
    <script type="text/javascript">
	$(".tb_result").checkbox([{all:'ckboxAll',item:'ckboxItem'}]);
	</script>
	</td></tr></table>
</s:else>

<div id="uploadDialog" title="车源审核" style="display:none;">
	<form id="approveForm" action="">
	<input type="hidden" id="carid"/>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tbody><tr><td>
		<table class="tb_query" border="0" cellspacing="1" cellpadding="1" align="center">
			<tbody>		
			<tr height="40">
				<th>审核:</th>
				<td><select name="approveForm.approveType" id="approveType">
						<option value="1">通过</option>
						<option value="4">不通过</option>
						<option value="2">下架</option>
					</select>
				</td>
			</tr>
			<tr height="40">
				<th>备注:</th>
				<td>
				<s:textarea  name="approveForm.approveRemark" id="approveRemark" cols="30" rows="8" tooltipDelay="300" tooltip="hi" label="备注" javascriptTooltip="true"></s:textarea>
				</td>
			</tr>
			<tr height="40">
				</br>
				<td class="bottom" colspan="4" align="center">
					<input name="btn" type="button" class="button" onclick="javascript:carApprove();" value="确定" alt="">
				</td>
			</tr>
		</tbody></table>
	</td></tr></tbody></table>
	</form>
</div>

<div id="carOffuploadDialog" title="车源下架" style="display:none;">
	<form id="approveForm" action="">
	<input type="hidden" id="caroffid"/>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tbody><tr><td>
		<table class="tb_query" border="0" cellspacing="1" cellpadding="1" align="center">
			<tbody>		
			<tr height="40">
				<th>原因:</th>
				<td>
				<s:textarea  name="reason" id="reason" cols="30" rows="8" tooltipDelay="300" tooltip="hi" label="备注" javascriptTooltip="true"></s:textarea>
				</td>
			</tr>
			<tr height="40">
				</br>
				<td class="bottom" colspan="4" align="center">
					<input name="btn" type="button" class="button" onclick="javascript:carOffSell();" value="确定" alt="">
				</td>
			</tr>
		</tbody></table>
	</td></tr></tbody></table>
	</form>
</div>

<script type="text/javascript">
$(function() {
	/**
	$("#uploadDialog").dialog({
		bgiframe: true,
		autoOpen: false,
		width:450,
		height:300,
		modal: true,
		close: function() {
			//$(this).html('');
		}
	});
	
	$("#carOffuploadDialog").dialog({
		bgiframe: true,
		autoOpen: false,
		width:400,
		height:250,
		modal: true,
		close: function() {
			//$(this).html('');
		}
	});
	**/
})
//下架
function carOffSell()
{
	var carid = $("#caroffid").val();
	var reason = $("#reason").val();

	$.ajax({
		type: "POST",
		url: "/module/gxfc/car_carOff.do",
		data: {"carid":carid, "reason":reason},
		success: function(data){
			alert(data.message);
			if(data.codeid == 0){
				document.location.reload();
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("下架失败");
		},
		dataType: "json"
	});
}

function carApprove()
{
	var carid = $("#carid").val();
	var approveType = $("#approveType").val();
	var approveRemark = $("#approveRemark").val();
	if (approveType != 1 && approveRemark == '')
	{
		alert('请输入备注信息!');
		return false;
	}

	$.ajax({
		type: "POST",
		url: "/module/gxfc/car_approve.do",
		data: {"carid":carid, "approveType":approveType, "approveRemark":approveRemark},
		success: function(data){
			alert(data.message);
			if(data.codeid == 0){
				document.location.reload();
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("审批失败");
		},
		dataType: "json"
	});
}

function carOffopenDialogUpload(id){
	$("#caroffid").val(id);
	$("#carOffuploadDialog").dialog({
		bgiframe: true,
		autoOpen: false,
		width:400,
		height:250,
		modal: true,
		close: function() {
			// $("#carOffuploadDialog").dialog('close');
			//$(this).html('');
		}
	});
	$("#carOffuploadDialog").dialog('open');
}

function openDialogUpload(id){
	$("#carid").val(id);
	$("#uploadDialog").dialog({
		bgiframe: true,
		autoOpen: false,
		width:450,
		height:300,
		modal: true,
		close: function() {
			// $("#uploadDialog").dialog('close');
			//$(this).html('');
		}
	});
	$("#uploadDialog").dialog('open');
}
</script>
</body>
</html>
