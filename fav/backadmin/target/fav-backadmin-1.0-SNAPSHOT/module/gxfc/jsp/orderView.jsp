<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/orderMgr.js"></script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_add" width="100%"  border="0" cellpadding="1" cellspacing="1">
		<tr>
	        <td class="td_title" colspan="4" align="center">
	        	<b>详细信息</b>
	        </td>         
        </tr>
        <tr>
            <th align="right" width="30%">查看车源：</th>
            <td><a href="javascript:view_car(<s:property value='orderInfo.carId'/>)">点击查看</a></td> 
        </tr>
        <tr>
            <th align="right" width="30%">订单编号：</th>
            <td><s:property value="orderInfo.orderCode"/></td> 
        </tr>
        <tr>
            <th align="right" width="30%">车型名称：</th>
            <td><s:property value="orderInfo.carInfo.title"/></td> 
        </tr>	
        <tr>
            <th align="right">物流方式：</th>
            <td>
				<s:if test="%{orderInfo.logistics == 1}">买家自提</s:if>
				<s:if test="%{orderInfo.logistics == 2}">卖家配送</s:if>
				<s:if test="%{orderInfo.logistics == 3}">平台配送</s:if>
			</td> 
        </tr>
        <tr>
            <th align="right">提货周期：</th>
            <td>
				<s:if test="%{orderInfo.deliveryPeriod == 0}">3天</s:if>
				<s:if test="%{orderInfo.deliveryPeriod == 7}">7天</s:if>
				<s:if test="%{orderInfo.deliveryPeriod == 15}">15天</s:if>
				<s:if test="%{orderInfo.deliveryPeriod == 30}">30天</s:if>
			</td> 
        </tr>		
        <tr>
            <th align="right" width="30%">发车价格：</th>
            <td><s:property value="orderInfo.showPrice"/>万</td> 
        </tr>	
         <tr>
            <th align="right">数量：</th>
            <td><s:property value="orderInfo.num"/></td> 
        </tr>
         <tr>
            <th align="right">总价：</th>
            <td><s:property value="orderInfo.showOrderPrice"/>万</td> 
        </tr>
         <tr>
            <th align="right">首款：</th>
            <td><s:property value="orderInfo.showRatioPrice"/></td> 
        </tr>
         <tr>
            <th align="right">平台服务费：</th>
            <td><s:property value="orderInfo.showTradePrice"/></td> 
        </tr>
         <tr>
            <th align="right">买家信息：</th>
            <td><s:property value="buyUser.name"/>(<s:property value="buyUser.loginName"/>)</td> 
        </tr>
         <tr>
            <th align="right">买家公司：</th>
            <td><s:property value="buyUser.companyName"/></td> 
        </tr>
         <tr>
            <th align="right">卖家信息：</th>
            <td><s:property value="sellUser.name"/>(<s:property value="sellUser.loginName"/>)</td> 
        </tr>
         <tr>
            <th align="right">卖家公司：</th>
            <td><s:property value="sellUser.companyName"/>(<s:property value="sellUser.loginName"/>)</td> 
        </tr>
         <tr>
            <th align="right">订单状态：</th>
            <td>
                    <s:if test="%{orderInfo.orderStatus == 0}">等待卖家确认</s:if>
                    <s:if test="%{orderInfo.orderStatus == 1}">等待支付首款</s:if>
                    <s:if test="%{orderInfo.orderStatus == 2}">交车中</s:if>
                    <s:if test="%{orderInfo.orderStatus == 3}">待付尾款</s:if>
                    <s:if test="%{orderInfo.orderStatus == 4}">交易完成</s:if>
                    <s:if test="%{orderInfo.orderStatus == 5}">交易关闭</s:if>			
			</td> 
        </tr>
        <tr>
            <th align="right">订单更新时间：</th>
            <td><s:date name="orderInfo.insertDate" format="yyyy-MM-dd HH:mm:ss"/></td> 
        </tr>
        <tr>
            <th align="right">取消理由：</th>
            <td><s:property value="orderInfo.reason"/></td> 
        </tr>
        <tr>
            <th align="right">详细说明：</th>
            <td><s:property value="orderInfo.remark"/></td> 
        </tr>
        <tr>
          <td class="bottom" align="center" colspan="2">
			<input type="button" onclick="javascript:history.back();" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="返回"/>
          </td>
        </tr>
      </table></td>
    </tr>
  </table>

</body>
</html>