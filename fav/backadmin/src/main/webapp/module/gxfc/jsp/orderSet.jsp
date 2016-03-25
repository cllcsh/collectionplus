<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/orderMgr.js"></script>
<script type="text/javascript">
	<s:if test="%{actionName == 'order_save'}">
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'order_save.do',
			    saveUrlTo:'order_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:if>
	<s:else>
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'order_update.do',
			    saveUrlTo:'order_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:else>
</script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<s:form id="setForm" name="orderForm" action="%{actionName}">
	<s:hidden name="orderInfo.id"></s:hidden>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td>
							<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr>
						<td class="td_title" colspan="4" align="center">
							<b>详细信息</b>
						</td>         
					</tr>
					<tr>
						<th align="right" width="50%">车型名称：</th>
						<td><s:property value="orderInfo.carInfo.title"/></td> 
					</tr>
					<tr>
						<th align="right" width="50%">订单编号：</th>
						<td><s:property value="orderInfo.orderCode"/></td> 
					</tr>
					<tr>
						<th align="right" width="50%">车型名称：</th>
						<td><s:property value="orderInfo.carInfo.modelsInfo.brandName"/></td> 
					</tr>		
					<tr>
						<th align="right" width="50%">车型价格（单价）：</th>
						<td><input type="text" id="price" name="orderInfo.price" value="<s:property value="orderInfo.price"/>"/></td> 
					</tr>
					<tr>
						<th align="right" width="50%">物流费：</th>
						<td><input type="text" id="deliveryPrice" name="orderInfo.deliveryPrice" value="<s:property value="orderInfo.deliveryPrice"/>"/></td> 
					</tr>
					<tr>
						<th align="right" width="50%">平台服务费：</th>
						<td><input type="text" id="tradePrice" name="orderInfo.tradePrice" value="<s:property value="orderInfo.tradePrice"/>"/></td> 
					</tr>
					<tr>
						<th align="right" width="50%">其他费用：</th>
						<td><input type="text" id="otherPrice" name="orderInfo.otherPrice" value="<s:property value="orderInfo.otherPrice"/>"/></td> 
					</tr>
					<tr>
						<th align="right" width="50%">订单编号：</th>
						<td><s:property value="orderInfo.orderCode"/></td> 
					</tr>
					 <tr>
						<th align="right">总价：</th>
						<td><s:property value="orderInfo.orderPrice"/></td> 
					</tr>	
					<tr>
						<th align="right">首款比例：</th>
						<td>
						<s:select name="orderInfo.depositRatio" list="#{'0':'请选择预付款车款比例',1:'10%',2:'10%',3:'30%',4:'40%',5:'50%',6:'60%',7:'70%',8:'80%',9:'90%',10:'100%'}"></s:select>
						</td> 
					</tr>				
					<tr>
						<th align="right">物流方式：</th>
						<td>
						<s:select name="orderInfo.logistics" list="#{'':'所有',1:'买家自提',2:'卖家配送',3:'平台配送'}"></s:select>
						</td> 
					</tr>
					<tr>
						<th align="right">提货周期：</th>
						<td>
						<s:select name="orderInfo.deliveryPeriod" list="#{'':'所有',0:'0天',7:'7天',15:'15天',30:'30天'}"></s:select>
						</td> 
					</tr>					
					 <tr>
						<th align="right">订单状态：</th>
						<td>
							<s:select name="orderInfo.orderStatus" list="#{0:'等待卖家确认',1:'等待买家支付首款',2:'买家已付首款，交易准备中',3:'等待买家支付尾款',4:'已付尾款，等待卖家发货',5:'物流中',6:'交易完成',7:'交易关闭'}"></s:select>		
						</td> 
					</tr>
					<tr>
						<th align="right">取消理由：</th>
						<td><s:textarea id="reason" name="orderInfo.reason" cols="50" rows="5"></s:textarea></td> 
					</tr>
					<tr>
						<th align="right">详细说明：</th>
						<td><s:textarea id="remark" name="orderInfo.remark" cols="50" rows="5"></s:textarea></td> 
					</tr>
					<tr>
						<td class="bottom" align="center" colspan="4">
							<div align="center">
								<s:if test="%{actionName == 'order_save'}">
									<input type="button" id="btn_save" class="button" value="增加" />
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

<script type="text/javascript">
$.formValidator.initConfig({
	formid:"setForm",
	onerror:function(msg){
	alert(msg)},
	onsuccess:function(){
		return true;
}});
</script>

</body>
</html>