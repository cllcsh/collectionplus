<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title><s:property value="jsp_head_title"/></title>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/orderMgr.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$(document).ict({
		queryForm:'queryForm',
		queryUrl:'order_query.do',
		queryBtn:'btn_query',
		pagecontent:'pagecontent',
		addUrl:'order_add.do',
		delsUrl:'order_deletes.do',
		delsBtn:'btn_del'
	});
});
</script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<form id="queryForm" action="">
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_query" border="0" cellspacing="1" cellpadding="1" align="center" >
		<tr>
			<th width="15%">订单编号</th>
			<td width="35%"><s:textfield name="orderForm.orderCode"></s:textfield></td>
 			<th width="15%">订单时间</th>
			<td width="35%">
				<input id="startDate" name="startDate" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}', dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" value="<s:property value='startDate'/>" style="width:150px;" /> - 
			    <input id="endDate" name="endDate" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}', dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" value="<s:property value='endDate'/>" style="width:150px;"/>			
			
			
			
			</td>
		</tr>
		<tr>
			<th>订单状态</th>
			<td><s:select name="orderForm.orderStatus" list="#{'':'所有',0:'等待卖家确认',1:'等待支付首款',2:'交车中',3:'待付尾款',4:'交易完成',5:'交易关闭'}"></s:select></td>
 			<th>物流方式</th>
			<td><s:select name="orderForm.logistics" list="#{'':'所有',1:'买家自提',2:'卖家配送',3:'平台配送'}"></s:select></td>
		</tr>
		<!--
			在此填写查询字段
		-->
		<tr>
			<td class="bottom" colspan="4" align="center">
		   		<input name="btn" id="btn_query" type="button" class="button" value="查询" />
				<input name="btn" type="reset" class="button" value="重置" />
			</td>
		</tr>
	</table>
</td></tr></table>
</form>
<br/>
<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td align="right" style="padding-right: 20px">
<%--			<input type="button" id="btn_add" class="button" value="添加"/>--%>
<%--			<input type="button" id="btn_del" class="button" value="删除"/>--%>
		</td>
	</tr>
	<tr>
		<td>
			<form id="listForm" action="">
				<div id="pagecontent"></div>
			</form>
		</td>
	</tr>
</table>
</body>
</html>
