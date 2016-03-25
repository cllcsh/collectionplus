<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>下单购买</title>
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
    <h1 class="title">下单购买</h1>
</header>


    <div class="wrap" style="margin-bottom: 5rem;">
        <div class="mod-buy-prepare">
            <div class="base-info">
                <div class="img">
                    <img src="<s:property value='carInfo.modelyearInfo.picPath2'/>" alt="">
                </div>
                <div class="price-info">
                    <div class="title"><s:property value='carInfo.title'/></div>
                    <div class="info-group">
                        发车价：<span class="comm-text-red comm-price">&yen; <strong><s:property value='carInfo.showPrice'/>万</strong></span>
                        库存：<span class="comm-text-red"><s:property value='carInfo.surplusNum'/>辆</span>
                    </div>
                    <div class="info-group comm-text-gray comm-text-small">市场指导价：<s:property value='carInfo.modelyearInfo.showPrice'/>万，为您节省：<s:property value='carInfo.showDifferentPrice'/>万</div>
                    <div class="info-group">
                        汽车状态：<span class="comm-text-large">
                  	<s:if test="%{carInfo.source == 1}">现货</s:if>
                    <s:if test="%{carInfo.source == 2}">期货</s:if>
                  </span>
                    </div>
                </div>
            </div>
            <div class="comm-form">
                <div class="split"></div>
                
                <div class="form-group">
                    <span class="label">车型：</span>
                    <div class="input"><s:property value="carInfo.modelyearInfo.name"/><s:property value="carInfo.brandInfo.name"/><s:property value="carInfo.seriesInfo.name"/><s:property value="carInfo.modelsInfo.name"/></div>
                </div>
                <div class="form-group">
                    <span class="label">燃油：</span>
                    <div class="input"><s:property value="carInfo.fuel"/></div>
                </div>
                <div class="form-group">
                    <span class="label">排量：</span>
                    <div class="input"><s:property value="carInfo.enginesName"/></div>
                </div>
                <div class="form-group">
                    <span class="label">车身颜色：</span>
                    <div class="input"><s:property value="carInfo.innercolorName"/></div>
                </div>
                <div class="form-group">
                    <span class="label">内饰颜色：</span>
                    <div class="input"><s:property value="carInfo.outercolorName"/></div>
                </div>
                <div class="form-group">
                    <span class="label">预付车款比例：</span>
                    <div class="input">
                    	<s:if test="%{carInfo.depositRatio == 1}">10%</s:if>
                       	<s:elseif test="%{carInfo.depositRatio == 2}">20%</s:elseif>
                       	<s:elseif test="%{carInfo.depositRatio == 3}">30%</s:elseif>
                       	<s:elseif test="%{carInfo.depositRatio == 4}">40%</s:elseif>
                       	<s:elseif test="%{carInfo.depositRatio == 5}">50%</s:elseif>
                       	<s:elseif test="%{carInfo.depositRatio == 6}">60%</s:elseif>
                       	<s:elseif test="%{carInfo.depositRatio == 7}">70%</s:elseif>
                       	<s:elseif test="%{carInfo.depositRatio == 8}">80%</s:elseif>
                       	<s:elseif test="%{carInfo.depositRatio == 9}">90%</s:elseif>
                       	<s:elseif test="%{carInfo.depositRatio == 10}">100%</s:elseif>
                    </div>
                </div>
                <div class="form-group">
                    <span class="label">提货周期：</span>
                    <div class="input">
                    	<s:if test="%{orderForm.deliveryPeriod == 3}">3天</s:if>
                       	<s:elseif test="%{orderForm.deliveryPeriod == 7}">7天</s:elseif>
                       	<s:elseif test="%{orderForm.deliveryPeriod == 15}">15天</s:elseif>
                       	<s:elseif test="%{orderForm.deliveryPeriod == 30}">30天</s:elseif>
                       	<s:else>任意</s:else>
                    </div>
                </div>
                <div class="form-group">
                    <span class="label">物流方式：</span>
                    <div class="input">
                       	<s:if test="%{carInfo.logistics == 2}">
                       		卖家配送<input id="logistics" type="hidden" name="orderForm.logistics" value="2">
                       	</s:if>
                        <s:else>
                        	<div class="comm-selector" data-single="1">
	                            <span class="value">买家自提</span>
	                            <div class="options">
	                                <div class="item" data-key="1" data-value="买家自提"></div>
	                                <div class="item" data-key="3" data-value="平台配送"></div>
	                            </div>
	                            <input id="logistics" type="hidden" name="orderForm.logistics" value="1">
	                        </div>
                        </s:else>
                    </div>
                </div>
                <div class="form-group">
                    <span class="label">平台服务费：</span>
                    <div class="input">
                        <s:if test="%{carInfo.versionId != 161}">
                         	<span class="comm-text-red" id="oriTradePrice"><s:property value="oriTradePrice"/></span>元，实收<span class="comm-text-red" id="tradePrice"><s:property value="tradePrice"/></span>元<img src="/portal/img/hot.gif"/>
                        </s:if>
                        <span style="display:block;font-size: 13px;margin-top: -24px;">9月27日至10月31日贵宾体验期，为回馈优质客户，平台服务费4.8折
                        	<s:if test="%{carInfo.versionId == 161}">
                    		;具体收费方式咨询400客服.
                    		</s:if>
                    	</span>
                    </div>
                </div>
                <div class="split"></div>
                <div class="form-group" style="line-height: 2.4;">
                    <span class="label" style="line-height: 3.4;">总价：</span>
                    <div class="input text-right"><span class="comm-price comm-text-red">&yen;<strong id="showTotalPrice"><s:property value='carInfo.showPrice'/>万</strong></span></div>
                </div>
                <div class="form-group">
                    <span class="label">进货数量：</span>
                    <div class="input">
                        <div class="right text-right">
                            <span class="inline-block vertical-top">库存：<span class="comm-text-red"><s:property value='carInfo.surplusNum'/>辆</span></span>
                        </div>
                        <div class="comm-number-selection" style="display: block;margin: 5px 0 0 5%;">
                            <div class="sub comm-trigger" onclick="setCarData()">-</div>
                            <input onchange="setCarData()" id="orderNum" class="value" value="1" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
                            <div class="plus comm-trigger" onclick="setCarData()">+</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="mod-buy-action text-center">
        	<input type="hidden" id="carId" value="<s:property value='carInfo.id'/>" />
           	<input type="hidden" id="surplusNum" value="<s:property value='carInfo.surplusNum'/>" />
           	<input type="hidden" id="price" value="<s:property value='carInfo.price'/>" />
           	<s:hidden id="carUserId" name="carInfo.insertId"></s:hidden>
    		<s:hidden id="userId" name="userSession.userId"></s:hidden>
    		<input type="hidden" id="totalPrice" value="<s:property value='carInfo.price'/>" />
            <div onclick="orderCar();" class="button button-primary">立即购买</div>
        </div>
    </div>

<script src="/m/bower_components/zepto/zepto.min.js"></script>
<script src="/m/bower_components/zeptotouch/zepto-touch.min.js"></script>
<script src="/m/vendor/zepto-selector.js"></script>
<script src="/m/js/app.js"></script>
<script type="text/javascript">
$('.comm-selector').each(function () {
    var _this = $(this);
    var title = _this.parents('.form-group').find('.label').html();
    _this.on(Fache.mainEvent, function () {
        new Fache.UI.Selector(_this, {title: title});
    });
});


//弹出订单详情时，设置数据
function setCarData() {
	var orderNum = parseInt($("#orderNum").val());
	if (orderNum <= 0) {
		alert("请您输入购车数量");
		return false;
	}
	var surplusNum = parseInt($("#surplusNum").val());
	if (orderNum > surplusNum) {
		alert("您购车数量超过了库存总量，请重新输入购车数量");
		return false;
	}
	
	var price = parseInt($("#price").val());
	var totalPrice = price * orderNum;
	$("#totalPrice").val(totalPrice);
	$("#showTotalPrice").text((totalPrice / 10000).toFixed(2) + "万");
}

//下订单
function orderCar() {
	var orderNum = parseInt($("#orderNum").val());
	if (orderNum <= 0) {
		alert("请您输入购车数量");
		return false;
	}
	var surplusNum = parseInt($("#surplusNum").val());
	if (orderNum > surplusNum) {
		alert("您购车数量超过了库存总量，请重新输入购车数量");
		return false;
	}
	
	var logistics =  $("input[name='orderForm.logistics']").val();
	var data = {"orderForm.carId":$("#carId").val(), 
			"orderForm.deliveryPeriod":$("#orderDeliveryPeriod").val(),
			"orderForm.logistics":$("#orderLogistics").val(),
			"orderForm.num":$("#orderNum").val(),
			"orderForm.orderPrice":$("#totalPrice").val(),
			"orderForm.logistics":logistics};
	$.ajax({
		type: "POST",
		url: "/mbuy_order.do",
		data: data,
		success: function(data){
			Fache.UI.alert(data.message);
			if (data.codeid == 0) {
				document.location.href = "/morderprotal_init.do";
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("订单提交失败");
		},
		dataType: "json"
	});
}
</script>
</body>
</html>
