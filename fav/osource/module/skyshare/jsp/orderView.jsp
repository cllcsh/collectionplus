<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<html>
<head>
<title><s:property value="jsp_head_title"/>-订单流程管理</title>
<%@include file="/jsp/common.jsp"%>
</head>

<body>
<%@include file="/jsp/include/pageInc.jsp"%>
<s:else>
	<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr><td class="bottom" align="right" colspan="2">
			<input type="button" onclick="javascript:history.back();" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="返回"/>
          </td></tr>
    <tr><td>
	<table class="tb_add" width="100%"  border="0" cellpadding="1" cellspacing="1">
		
		<tr>
		<th  align="right" width="50%">
			订单编号：
		</th>
		<td>
			<s:property value="orderForm.orderCode" />
		</td>
		</tr>
		<tr>
		<th  align="right" width="50%">
			订单人：
		</th>
		<td>
			<s:property value="orderForm.userName" />
		</td>
		</tr>
		<tr>
		<th  align="right" width="50%">
			订单日期：
		</th>
		<td>
			<s:date format="yyyy-MM-dd HH:mm:ss" name="orderForm.orderDate"/>
		</td>
		</tr>
		<tr>
		<th  align="right" width="50%">
			订单总价：
		</th>
		<td>
			<s:property value="orderForm.amount" />
		</td>
		</tr>
		<tr>
		<th  align="right" width="50%">
			step5 PRCDSSED：
		</th>
		<td>
			<s:property value="orderForm.step5" />
		</td>
		</tr>
		<tr>
		<th  align="right" width="50%">
			step6 ON BOARD：
		</th>
		<td>
			<s:property value="orderForm.step6" />
		</td>
		</tr>
		<tr>
		<th  align="right" width="50%">
			step7 DOCS：
		</th>
		<td>
			<s:property value="orderForm.step7" />
		</td>
		</tr>
		<tr>
		<th  align="right" width="50%">
			step8：
		</th>
		<td>
			<s:property value="orderForm.step8" />
		</td>
		</tr>
	</table>
	</td></tr>
    <tr><td>
	<table class="tb_result" border="0" cellspacing="1" cellpadding="1" align="center">
      <tr class="tr_head" align="center">
		<td width="">产品名称</td>
		<td width="">单价</td>
		<td width="">单位</td>
		<td width="">数量</td>
      </tr>
      <s:iterator id="orderProduct" value="%{orderForm.product}" status="sta">
      <tr align="center" class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
        <td>
			<s:property value="#orderProduct.productName"/>
		</td>
    	<td><s:property value="#orderProduct.salePrice"/></td>
		<td><s:property value="#orderProduct.weight"/></td>
        <td><s:property value="#orderProduct.num"/></td>
		
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
