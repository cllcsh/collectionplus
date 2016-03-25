<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<head>
    <title>订单详情</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="baidu-site-verification" content="hWGw6qbfv7" />
    <link rel="stylesheet" href="/m/css/style.css">

</head>
<body>
    <header class="comm-header">
    <div class="left opt">
        <a href="javascript:window.history.go(-1);">返回</a>
    </div>
    <h1 class="title">订单详情</h1>
</header>


    <div class="wrap">
        <div class="mod-order-detail">
            <div class="order-info">
                <div class="status right" style="background-color: #59d58d">
				    <s:if test="%{orderInfo.orderStatus == 0}">等待卖家确认</s:if>
                    <s:if test="%{orderInfo.orderStatus == 1}">等待支付首款</s:if>
                    <s:if test="%{orderInfo.orderStatus == 2}">交车中</s:if>
                    <s:if test="%{orderInfo.orderStatus == 3}">待付尾款</s:if>
                    <s:if test="%{orderInfo.orderStatus == 4}">交易完成</s:if>
                    <s:if test="%{orderInfo.orderStatus == 5}">交易关闭</s:if>
				</div>
                <div class="no">订单编号：<s:property value="orderInfo.orderCode"/></div>
                <div class="comm-text-gray">购买时间：<s:date name="orderInfo.insertDate" format="yyyy-MM-dd HH:mm:ss"></s:date></div>
            </div>
            <div class="car-info">
                <div class="img"><img src="../img/data/car.jpg" alt=""></div>
                <div class="info">
                    <div class="title"><s:property value='orderInfo.carInfo.brandInfo.name'/><span class="comm-text-gray">|</span><s:property value='orderInfo.carInfo.modelyearInfo.name'/><s:property value='orderInfo.carInfo.seriesInfo.name'/><s:property value='orderInfo.carInfo.carVersionInfo.name'/></div>
                    <div class="comm-text-gray">
                        总价：<span class="comm-text-red"><s:property value='orderInfo.showPrice'/><small>万</small></span>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        车辆状态：<span class="comm-text-black">
						<s:if test="%{orderInfo.carInfo.source == 1}">现货</s:if><s:else>期货</s:else>
						</span>
                    </div>
                </div>
            </div>
        </div>

        <div class="comm-form mod-order-form">
            <div class="form-group">
                <span class="label">车型：</span>
                <div class="input"><s:property value="orderInfo.carInfo.modelsInfo.name"/></div>
            </div>
            <div class="form-group">
                <span class="label">燃油：</span>
                <div class="input"><s:property value="orderInfo.carInfo.fuel"/></div>
            </div>
            <div class="form-group">
                <span class="label">排量：</span>
                <div class="input"><s:property value="orderInfo.carInfo.enginesName"/></div>
            </div>
            <div class="form-group">
                <span class="label">车身颜色：</span>
                <div class="input"><s:property value="orderInfo.carInfo.outercolorName"/> </div>
            </div>
            <div class="form-group">
                <span class="label">内饰颜色：</span>
                <div class="input"><s:property value="orderInfo.carInfo.innercolorName"/></div>
            </div>
            <div class="form-group">
                <span class="label">所在省份：</span>
                <div class="input"><s:property value="carInfo.province"/></div>
            </div>
            <div class="form-group">
                <span class="label">首款比例：</span>
                <div class="input">
					<s:if test="%{orderInfo.carInfo.depositRatio == 1}">10%</s:if>
                   	<s:elseif test="%{orderInfo.carInfo.depositRatio == 2}">20%</s:elseif>
                   	<s:elseif test="%{orderInfo.carInfo.depositRatio == 3}">30%</s:elseif>
                   	<s:elseif test="%{orderInfo.carInfo.depositRatio == 4}">40%</s:elseif>
                   	<s:elseif test="%{orderInfo.carInfo.depositRatio == 5}">50%</s:elseif>
                   	<s:elseif test="%{orderInfo.carInfo.depositRatio == 6}">60%</s:elseif>
                   	<s:elseif test="%{orderInfo.carInfo.depositRatio == 7}">70%</s:elseif>
                   	<s:elseif test="%{orderInfo.carInfo.depositRatio == 8}">80%</s:elseif>
                   	<s:elseif test="%{orderInfo.carInfo.depositRatio == 9}">90%</s:elseif>
                   	<s:elseif test="%{orderInfo.carInfo.depositRatio == 10}">100%</s:elseif>			
				</div>
            </div>
            <div class="form-group">
                <span class="label">提货周期：</span>
                <div class="input"><s:if test="orderInfo.deliveryPeriod == 0">任意</s:if><s:else><s:property value="orderInfo.deliveryPeriod"/>天</s:else></div>
            </div>
            <div class="form-group">
                <span class="label">物流方式：</span>
                <div class="input">
				  <s:if test="orderInfo.logistics == 1">买家自提</s:if>
                  <s:if test="orderInfo.logistics == 2">卖家配送</s:if>
                  <s:if test="orderInfo.logistics == 3">平台配送</s:if>				
				</div>
            </div>
            <div class="form-group">
                <span class="label">物流费：</span>
                <div class="input">请咨询400客服<!--<s:property value="orderInfo.deliveryPrice"/><small>元</small>--></div>
            </div>
            <div class="form-group">
                <span class="label">平台服务费：</span>
                <div class="input">请咨询400客服<!--<s:property value="orderInfo.tradePrice"/><small>元</small>--></div>
            </div>
            <div class="form-group">
                <span class="label">其他费用：</span>
                <div class="input"><s:property value="orderInfo.otherPrice"/><small>元</small></div>
            </div>
            <div class="form-group">
                <span class="label">数量：</span>
                <div class="input comm-text-red"><s:property value="orderInfo.num"/><small>辆</small></div>
            </div>
            <div class="form-group">
                <span class="label">总价：</span>
                <div class="input comm-text-red"><s:property value='orderInfo.showOrderPrice'/><small>万</small></div>
            </div>
        </div>
    </div>

</body>
</html>
