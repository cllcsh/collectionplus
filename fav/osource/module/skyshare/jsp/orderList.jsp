<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<html>
<head>
<title><s:property value="jsp_head_title"/>-消息</title>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/orderMgr.js"></script>
</head>

<body>
<%@include file="/jsp/include/pageInc.jsp"%>
<s:else>
	<form id="orderForm" name="orderForm">
	<s:hidden id="orderId" name="id"/>
	<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_result" border="0" cellspacing="1" cellpadding="1" align="center">
      <tr class="tr_head" align="center">
		<td><input id="checkboxtop"	type="checkbox" class="ckboxAll" title="选中/取消选中" value="" onclick="checkAll('checkboxtop','ckboxItem');" /></td>
		<td width="">编号</td>
		<td width="">订单人</td>
        <td width="">订单日期</td>
        <td width="">总价</td>
		<td width="">操作</td>
      </tr>
      <s:iterator id="order" value="%{pageList.results}" status="sta">
      <tr align="center" class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
		<td>
			<input name="items[<s:property value="#sta.index"/>].id" type="checkbox" class="ckboxItem" value="<s:property value="#order.id"/>" onclick="check('checkboxtop','ckboxItem');" />
		</td>
		
        <td>
			
			<a href="javascript:view_order(<s:property value='#order.id'/>)" ><s:property value='#order.orderCode'/></a>
			<input type="hidden" value="<s:property value="#order.id"/>" name="orderList[<s:property value="#sta.index"/>].orderId"/>
		</td>
		<td>
			<s:property value="#order.userName"/>
		</td>
		<td><s:date format="yyyy-MM-dd HH:mm:ss" name="orderDate"/></td>
		
		<td><s:property value="#order.amount"/></td>
		
		
        <td align="center" >        	
				<a href="javascript:edit_order(<s:property value='#order.id'/>)" >修改</a> &nbsp;&nbsp; 
				
				 <s:if test="%{userSession.userPermissions['/module/skyshare/flow_init.do'] != null}">
					<a href="javascript:view_orderFlow(<s:property value='#order.id'/>)" >查看流程</a> &nbsp;&nbsp;
	            </s:if>
		</td>
      </tr>
      </s:iterator>
    </table>
    <script type="text/javascript">
	$(".tb_result").checkbox([{all:'ckboxAll',item:'ckboxItem'}]);
	</script>
	</td></tr></table>
	</form>
</s:else>

</body>
</html>
