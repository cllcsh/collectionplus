<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<html>
<head>
<title><s:property value="jsp_head_title"/>-订单管理</title>
<%@include file="/jsp/common.jsp"%>
</head>

<body>
<%@include file="/jsp/include/pageInc.jsp"%>
<s:else>
	<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_result" border="0" cellspacing="1" cellpadding="1" align="center">
      <tr class="tr_head" align="center">
		<td><input id="checkboxtop"	type="checkbox" class="ckboxAll" title="选中/取消选中" value="" onclick="checkAll('checkboxtop','ckboxItem');" /></td>
		<td width="20%">订单编号</td>
		<td width="6%">数量</td>
        <td width="10%">订单状态</td>
		<td width="8%">订单价格</td>
        <td width="8%">物流方式</td>
        <td width="8%">提货周期</td>
		<td width="10%">订单时间</td>
		<td width="10%">修改时间</td>
		<td width="10%">操作</td>
      </tr>
      <s:iterator id="orderInfo" value="%{pageList.results}" status="sta">
      <tr align="center" class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
		<td>
			<input name="items[<s:property value="#sta.index"/>].id" type="checkbox" class="ckboxItem" value="<s:property value="#orderInfo.id"/>" onclick="check('checkboxtop','checkbox1');" />
		</td>
        <td><a href="javascript:view_order(<s:property value='#orderInfo.id'/>)"><s:property value="#orderInfo.orderCode"/></a></td>
        <td><s:property value="#orderInfo.num"/></td>
        <td>
			<s:if test="%{#orderInfo.orderStatus == 0}">等待卖家确认</s:if>
            <s:if test="%{#orderInfo.orderStatus == 1}">等待买家支付首款</s:if>
            <s:if test="%{#orderInfo.orderStatus == 2}">买家已付首款，交易准备中</s:if>
            <s:if test="%{#orderInfo.orderStatus == 3}">等待买家支付尾款</s:if>
            <s:if test="%{#orderInfo.orderStatus == 4}">已付尾款，等待卖家发货</s:if>
            <s:if test="%{#orderInfo.orderStatus == 5}">物流中</s:if>
            <s:if test="%{#orderInfo.orderStatus == 6}">交易完成</s:if>
            <s:if test="%{#orderInfo.orderStatus == 7}">交易关闭</s:if>
		</td>
		<td><s:property value="#orderInfo.orderPrice"/>元</td>
        <td>
			<s:if test="%{#orderInfo.logistics == 1}">买家自提</s:if>
        	<s:elseif test="%{#orderInfo.logistics == 2}">卖家配送</s:elseif>
        	<s:elseif test="%{#orderInfo.logistics == 3}">平台配送</s:elseif>

		</td>
        <td>
			<s:if test="%{#orderInfo.deliveryPeriod == 0}">任意</s:if>
        	<s:elseif test="%{#orderInfo.deliveryPeriod == 3}">3天</s:elseif>
        	<s:elseif test="%{#orderInfo.deliveryPeriod == 7}">7天</s:elseif>		
			<s:elseif test="%{#orderInfo.deliveryPeriod == 15}">15天</s:elseif>
        	<s:elseif test="%{#orderInfo.deliveryPeriod == 30}">30天</s:elseif>		
		</td>
		<td><s:date name="#orderInfo.updateDate" format="yy-MM-dd HH:mm" /></td>
		<td><s:date name="#orderInfo.insertDate" format="yy-MM-dd HH:mm" /></td>
        <td align="center" >
        	<a href="javascript:edit_order(<s:property value='#orderInfo.id'/>)" >修改</a>
        	<s:if test="%{#orderInfo.orderStatus == 0}">
        		<a href="javascript:disable_order(<s:property value='#orderInfo.id'/>)" >置为无效</a>
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

</body>
</html>
