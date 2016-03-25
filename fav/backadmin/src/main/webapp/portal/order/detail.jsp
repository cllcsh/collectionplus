<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include  file="/portal/header.jsp"%>

    <link rel="stylesheet" href="/portal/css/style.css">
</head>
<body>
<%@ include  file="/portal/inc.jsp"%>
    <div class="comm-white-ctn">
        <div class="container">
            <div class="comm-breadcrumbs">
                当前位置：
                <a href="#">订单</a>&gt;
                <s:if test="%{viewType == 'buy'}">
                <a href="/orderprotal_init.do">进货订单</a>&gt;
                </s:if>
                <s:else>
                <a href="/orderprotal_sell.do">出货订单</a>&gt;
                </s:else>
                <span class="current">订单详情</span>
            </div>

            <div class="mod-order-detail">
                <div class="title-bar">
                    <strong>订单详情：<s:date name="orderInfo.insertDate" format="yyyy-MM-dd HH:mm:ss"></s:date>&nbsp;&nbsp;&nbsp;&nbsp;订单编号：<s:property value="orderInfo.orderCode"/></strong>
                    <div class="right">
                    <s:if test="%{orderInfo.orderStatus == 0}">等待卖家确认</s:if>
                    <s:if test="%{orderInfo.orderStatus == 1}">等待买家支付首款</s:if>
                    <s:if test="%{orderInfo.orderStatus == 2}">买家已付首款，交易准备中</s:if>
                    <s:if test="%{orderInfo.orderStatus == 3}">等待买家支付尾款</s:if>
                    <s:if test="%{orderInfo.orderStatus == 4}">已付尾款，等待卖家发货</s:if>
                    <s:if test="%{orderInfo.orderStatus == 5}">物流中</s:if>
                    <s:if test="%{orderInfo.orderStatus == 6}">交易完成</s:if>
                    <s:if test="%{orderInfo.orderStatus == 7}">已取消</s:if>
                    </div>
                </div>
                <div class="body">
                    <h1><s:property value='orderInfo.carInfo.title'/></h1>
                    <div class="table-wrap">
                        <table>
                            <tr>
                                <td rowspan="2" style="border:none;"><div class="img">
                                <s:if test="%{orderInfo.carInfo.modelyearInfo.picPath2 == null || orderInfo.carInfo.modelyearInfo.picPath2 == ''}">
                                <img src="/portal/img/nocarpic.png" alt="">
                                </s:if>
                                <s:else>
                                <img src="<s:property value='orderInfo.carInfo.modelyearInfo.picPath2'/>" alt="">
                                </s:else>
                                </div></td>
                                <td style="border-left: none;">零售价： <span class="comm-price comm-text-red">&yen;<strong><s:property value='orderInfo.showPrice'/>万</strong></span></td>
                                <td colspan="3">基础参数：
                                功率:<s:property value="orderInfo.carInfo.modelyearInfo.efficiency"/>&nbsp;
                                驱动方式:<s:property value="orderInfo.carInfo.modelyearInfo.driving"/>&nbsp;
                                座位数:<s:property value="orderInfo.carInfo.modelyearInfo.seats"/>&nbsp;
                                油耗:<s:property value="orderInfo.carInfo.modelyearInfo.fuel"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="border-left: none;">车辆状态：<strong class="comm-text-huge"><s:if test="%{orderInfo.carInfo.source == 1}">现货</s:if><s:else>期货</s:else></strong></td>
                                <td>燃油：<s:property value="orderInfo.carInfo.fuel"/></td>
                                <td>排量：<s:property value="orderInfo.carInfo.enginesName"/></td>
                                <td>颜色：<s:property value="orderInfo.carInfo.outercolorName"/> / <s:property value="orderInfo.carInfo.innercolorName"/></td>
                            </tr>
                            <tr>
                                <td colspan="2" style="border-left: none;border-top: none;">数量： <span class="right"><span class="comm-text-red"><s:property value="orderInfo.num"/></span>辆</span></td>
                                <td>订金：<s:property value="orderInfo.showRatioPrice"/>万(
                                <s:if test="%{orderInfo.carInfo.depositRatio == 1}">10%</s:if>
                                <s:if test="%{orderInfo.carInfo.depositRatio == 2}">20%</s:if>
                                <s:if test="%{orderInfo.carInfo.depositRatio == 3}">30%</s:if>
                                <s:if test="%{orderInfo.carInfo.depositRatio == 4}">40%</s:if>
                                <s:if test="%{orderInfo.carInfo.depositRatio == 5}">50%</s:if>
                                <s:if test="%{orderInfo.carInfo.depositRatio == 6}">60%</s:if>
                                <s:if test="%{orderInfo.carInfo.depositRatio == 7}">70%</s:if>
                                <s:if test="%{orderInfo.carInfo.depositRatio == 8}">80%</s:if>
                                <s:if test="%{orderInfo.carInfo.depositRatio == 9}">90%</s:if>
                                <s:if test="%{orderInfo.carInfo.depositRatio == 10}">100%</s:if>)
                                </td>
                                <td>提货周期：<s:if test="orderInfo.deliveryPeriod == 0">任意</s:if><s:else><s:property value="orderInfo.deliveryPeriod"/>天</s:else></td>
                                <td>物流：
                                <s:if test="orderInfo.logistics == 1">买家自提</s:if>
                                <s:if test="orderInfo.logistics == 2">卖家配送</s:if>
                                <s:if test="orderInfo.logistics == 3">平台配送</s:if>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" style="border-left: none;border-bottom: none;">总价：
                                    <span class="right comm-price comm-text-red">&yen;<strong><s:property value='orderInfo.showOrderPrice'/>万</strong></span>
                                </td>
                                <td style="border-bottom: none;">物流费：请咨询400客服<!-- <s:property value='orderInfo.deliveryPrice'/> --></td>
                                <td style="border-bottom: none;">平台服务费：请咨询400客服<!-- <s:property value='orderInfo.tradePrice'/> --></td>
                                <td style="border-bottom: none;">其他费用：<s:property value='orderInfo.otherPrice'/></td>
                            </tr>
                            <s:if test="%{orderInfo.orderStatus == 5}">
                            	<tr>
	                                <td style="border-left: none;border-bottom: none;">
	                                	物流公司：<s:property value='orderInfo.logisticsCompanyName'/>
	                                </td>
	                                <td style="border-bottom: none;">物流单号：<s:property value='orderInfo.logisticsNo'/></td>
	                                <td colspan="3" style="border-bottom: none;"></td>
                            	</tr>
                            </s:if>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>

<%@include file="/portal/footer.jsp" %>   
<script src="/portal/js/vendor/jquery.js"></script>
<script src="/portal/js/app.js"></script>

</body>
</html>