<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include  file="/portal/header.jsp"%>

    <link rel="stylesheet" href="/portal/css/style.css">
	<style type="text/css">
	.mod-order-list .table .tr .td {
		width: 9%;
	}
	.mod-order-list .table .tr .td-main {
		width: 19%;
		text-align: left;
	}
	</style>
</head>
<body>
<%@ include  file="/portal/inc.jsp"%>
    <div class="comm-white-ctn">
        <div class="container">
            <div class="mod-order-tabs">
                <span class="title">我的订单</span>
                <a href="/orderprotal_init.do" class="item active">进货订单</a>
                <a href="/orderprotal_sell.do" class="item">出货订单</a>
                <a href="/orderprotal_my.do" class="item">我的车源</a>
            </div>
            <div class="mod-order-list">
                <div class="table">
                    <div class="tr thead">
                        <div class="td td-main">商品信息</div>
                        <div class="td">单价</div>
                        <div class="td">数量</div>
                        <div class="td">首款</div>
                        <div class="td">平台服务费</div>
                        <div class="td">物流费</div>
                        <div class="td">其他费用</div>
                        <div class="td">成交价</div>
                        <div class="td">订单状态 <span class="anchor"></span></div>
                        <div class="td">操作</div>
                    </div>
					
					<s:iterator var="orderInfo" value="pageList.results">
                    <div class="order-item">
                        <div class="tr">
                            <div class="td-block"><strong><s:date name="#orderInfo.insertDate" format="yyyy-MM-dd"></s:date></strong> <s:date name="#orderInfo.insertDate" format="HH:mm:ss"></s:date> 订单编号：<s:property value="#orderInfo.orderCode"/></div>
                        </div>
                        <div class="tr">
                            <div class="td td-main">
                                <div class="img"><a href="/buy_view.do?carForm.id=<s:property value='#orderInfo.carId'/>">
                                <s:if test="%{#orderInfo.carInfo.modelyearInfo.picPath2 == null || #orderInfo.carInfo.modelyearInfo.picPath2 == ''}">
                                <img src="/portal/img/nocarpic.png" alt="">
                                </s:if>
                                <s:else>
                                <img src="<s:property value='#orderInfo.carInfo.modelyearInfo.picPath2'/>" alt="">
                                </s:else>
                                </a></div>
                                <a href="/buy_view.do?carForm.id=<s:property value='#orderInfo.carId'/>" class="info">
                                    <s:property value='#orderInfo.carInfo.brandInfo.name'/><br>
                                    <s:property value='#orderInfo.carInfo.seriesInfo.name'/><br>
                                    <s:property value='#orderInfo.carInfo.modelyearInfo.name'/><s:property value='#orderInfo.carInfo.carVersionInfo.name'/>
                                </a>
                            </div>
                            <div class="td lh100">&yen; <span class="comm-text-red"><s:property value='#orderInfo.carInfo.showPrice'/>万</span></div>
                            <div class="td lh100"><s:property value='#orderInfo.num'/></div>
                            <div class="td lh100">&yen; <span class="comm-text-red"><s:property value='#orderInfo.showRatioPrice'/>万</span></div>
                            <div class="td lh100">&yen; <span class="comm-text-red"><s:property value='#orderInfo.showTradePrice'/></span></div>
                            <div class="td lh100">请咨询400客服</div>
<!--                            <div class="td lh100">&yen; <span class="comm-text-red"><s:property value='#orderInfo.deliveryPrice'/></span></div>-->
                            <div class="td lh100">&yen; <span class="comm-text-red"><s:property value='#orderInfo.otherPrice'/></span></div>
                            <div class="td lh100">&yen; <span class="comm-text-red"><s:property value='#orderInfo.showOrderPrice'/>万</span></div>
                            <div class="td lh100" style="line-height:18px;">
                            <s:if test="%{#orderInfo.orderStatus == 0}">等待卖家确认</s:if>
                            <s:if test="%{#orderInfo.orderStatus == 1}">等待买家支付首款</s:if>
                            <s:if test="%{#orderInfo.orderStatus == 2}">买家已付首款，交易准备中</s:if>
                            <s:if test="%{#orderInfo.orderStatus == 3}">等待买家支付尾款</s:if>
                            <s:if test="%{#orderInfo.orderStatus == 4}">已付尾款，等待卖家发货</s:if>
                            <s:if test="%{#orderInfo.orderStatus == 5}">物流中</s:if>
                            <s:if test="%{#orderInfo.orderStatus == 6}">交易完成</s:if>
                            <s:if test="%{#orderInfo.orderStatus == 7}">已取消</s:if>
                            </div>
                            <div class="td action">
                            	<s:if test="%{#orderInfo.orderStatus == 1||#orderInfo.orderStatus == 3}">
	                            	<input type="hidden" name="orderCode" value="<s:property value='#orderInfo.orderCode'/>"/>
	                            	<input type="hidden" name="orderStatus" value="<s:property value='#orderInfo.orderStatus'/>"/>
	                            	<input type="hidden" name="showRatioPrice" value="<s:property value='#orderInfo.showRatioPrice'/>"/>
	                            	<input type="hidden" name="showPrice" value="<s:property value='#orderInfo.showPrice'/>"/>
	                            	<input type="hidden" name="num" value="<s:property value='#orderInfo.num'/>"/>
	                            	<input type="hidden" name="deliveryPrice" value="<s:property value='#orderInfo.deliveryPrice'/>"/>
	                            	<input type="hidden" name="tradePrice" value="<s:property value='#orderInfo.tradePrice'/>"/>
	                            	<input type="hidden" name="otherPrice" value="<s:property value='#orderInfo.otherPrice'/>"/>
	                            	<input type="hidden" name="showOrderPrice" value="<s:property value='#orderInfo.showOrderPrice'/>"/>
	                            	<input type="hidden" name="orderId" value="<s:property value='#orderInfo.id'/>"/>
                            	</s:if>
                            	<s:if test="%{#orderInfo.orderStatus == 1}">
	                            	<div class="button button-primary" onclick="payModal(this);">立即付款</div>
                            	</s:if>
                            	<s:if test="%{#orderInfo.orderStatus == 2 && #orderInfo.isSureFirstPay!=1}">
                            		<div class="button button-primary" onclick="sureFirstPay(this);">首款确认</div>
                            	</s:if>
                            	<s:if test="%{#orderInfo.orderStatus == 3}">
                            		<s:if test="%{#orderInfo.isSureFirstPay==1}">
		                            	<div class="button button-primary" onclick="payModal(this);">立即付款</div>
                            		</s:if>
                            		<s:else>
                            			<div class="button button-primary" onclick="sureFirstPay(this);">首款确认</div>
                            		</s:else>
                            	</s:if>
                                <s:if test="%{#orderInfo.orderStatus == 5}">
                                	<div class="button button-primary" onclick="sign(<s:property value='#orderInfo.id'/>);">确认收货</div>
                                </s:if>
                                <div class="button button-link" onclick="viewOrder(<s:property value='#orderInfo.id'/>);">查看订单</div>
                                <s:if test="%{#orderInfo.orderStatus == 0 || #orderInfo.orderStatus == 1}">
                                	<div class="button button-link" onclick="showReason(<s:property value='#orderInfo.id'/>);">取消订单</div>
                                </s:if>
                            </div>
                        </div>
                    </div>
					</s:iterator>
                </div>
            </div>
            <div class="comm-pagin clearfix">
                <a class="item" onclick="turnToPage(-1);">上一页</a>
            	<s:if test="%{(pageList.pages.allPage - page) > 4}">
            	<s:iterator begin="page" end="page + 3" var="currentPage">
            	<a onclick="turnToPage(<s:property value='#currentPage'/>);" class="item <s:if test='%{#currentPage == page}'>active</s:if>"><s:property value='#currentPage'/></a>
            	</s:iterator>
            	<a class="text">...</a>
            	</s:if>
            	<s:else>
            	<s:if test="%{0 >= (pageList.pages.allPage - 4)}">
            	<s:set name="firstPage" value="1"></s:set>
            	</s:if>
            	<s:else>
            	<s:set name="firstPage" value="pageList.pages.allPage - 3"></s:set>
            	</s:else>
            	<s:iterator begin="firstPage" end="pageList.pages.allPage" var="currentPage">
            	<a onclick="turnToPage(<s:property value='#currentPage'/>);" class="item <s:if test='%{#currentPage == page}'>active</s:if>"><s:property value='#currentPage'/></a>
            	</s:iterator>
            	</s:else>
                <a class="item" onclick="turnToPage(-2);">下一页</a>
                <div class="text">共<s:property value="pageList.pages.allPage"/>页，到第<input id="turnPage" type="text" value="<s:property value='page'/>">页</div>
                <a class="item" onclick="turnToPage(0);">确定</a>
                <s:hidden id="cuurentPage" name="page"></s:hidden>
                <s:hidden id="allPage" name="pageList.pages.allPage"></s:hidden>
            </div>
        </div>
    </div>
    
    <div id="J-Modal" class="comm-modal none">
        <div class="modal">
            <div class="close j-close">&times;</div>
            <div class="title">取消订单的理由</div>
            <div class="mod-buy-modal">
                <div class="radio form-group">
                    <label for="dontwant">不想买了</label>
                    <input type="radio" id="dontwant" name="reason" value="不想买了"/>
                </div>
                <div class="radio form-group">
                    <label for="cantgo">无法及时到店提车</label>
                    <input type="radio" id="cantgo" name="reason" value="无法及时到店提车"/>
                </div>
                <div class="radio form-group">
                    <label for="missing">卖家车源已不在</label>
                    <input type="radio" id="missing" name="reason" value="卖家车源已不在"/>
                </div>
                <div class="radio form-group">
                    <label for="notfit">车源颜色、配置、随车工具与合同不符</label>
                    <input type="radio" id="notfit" name="reason" value="车源颜色、配置、随车工具与合同不符"/>
                </div>
                <div class="radio form-group">
                    <label for="unsell">卖家未按合同规定日期发货</label>
                    <input type="radio" id="unsell" name="reason" value="卖家未按合同规定日期发货"/>
                </div>
                <div class="radio form-group">
                    <label for="other">其他理由</label>
                    <input type="radio" id="other" name="reason" value="其他理由"/>
                </div>
                <div class="form-group">
                    <textarea name="" id="remark" cols="30" rows="10"></textarea>
                </div>
                <div class="action text-center">
                	<input type="hidden" id="cancelOrderId" />
                    <div class="button button-primary" onclick="cancelOrder();">确认取消</div>
                </div>
            </div>
        </div>
    </div>
    
    <div id="J-Pay" class="comm-modal none">
        <div class="modal">
            <div class="close j-close">&times;</div>
            <h1 class="title">支付</h1>
            <div class="body">
                <div class="form-group">
                    <label for="" class="label">订单号：</label>
                    <div id="orderCodeText" class="input"></div>
                </div>
                <div class="form-group">
                    <label for="" class="label">交易类型：</label>
                    <div id="orderStatusText" class="input"></div>
                </div>
                <div id="dingjin" style="border-top: 1px solid red;height: 50px;line-height: 50px;">
	                <div class="form-group">
	                    <label for="" class="label">应付金额：</label>
	                    <div id="dingjinPriceText" class="input" style="font-size: 27px;color: red;"></div>
	                </div>
                </div>
               <div id="quankuan">
	                <div class="form-group">
	                    <label for="" class="label">单价：</label>
	                    <div id="priceText" class="input"></div>
	                </div>
	                <div class="form-group">
	                    <label for="" class="label">数量：</label>
	                    <div id="numText" class="input"></div>
	                </div>
	                <div class="form-group">
	                    <label for="" class="label">物流费：</label>
	                    <div id="deliveryPriceText" class="input"></div>
	                </div>
	                <div class="form-group">
	                    <label for="" class="label">平台服务费：</label>
	                    <div id="tradePriceText" class="input"></div>
	                </div>
	                <div class="form-group">
	                    <label for="" class="label">其他费用：</label>
	                    <div id="otherPriceText" class="input"></div>
	                </div>
	                <div class="form-group">
	                    <label for="" class="label">成交总价：</label>
	                    <div id="orderPriceText" class="input"></div>
	                </div>
	                <div class="form-group" style="border-top: 1px solid red;height: 50px;line-height: 50px;">
	                    <label for="" class="label">已付首款：</label>
	                    <div id="ratioPriceText" class="input"></div>
	                </div>
	                <div class="form-group">
	                    <label for="" class="label">应付金额：</label>
	                    <div id="payPriceText" class="input" style="font-size: 27px;color: red;"></div>
	                </div>
                </div>
            </div>
            <div class="action">
                <div class="button button-primary j-ok">确认</div>
            </div>
        </div>
    </div>

<%@include file="/portal/footer.jsp" %>   
<script src="/portal/js/vendor/jquery.js"></script>
<script src="/portal/js/app.js"></script>
<script type="text/javascript">
$(function(){
    modal = new FACHE.UI.Modal({
        id : 'J-Modal'
    });
    new FACHE.UI.Btp();

    var payModal;
    var orderId;
    window.payModal = function(btn){
    	var div = $(btn).parent(".td.action");
    	if(div) {
    		$("#orderCodeText").text(div.find("input[name=orderCode]").val());
    		if(div.find("input[name=orderStatus]").val()=='1') {
	    		$("#orderStatusText").text("首款");
	    		$("#dingjin").css("display", "");
	    		$("#quankuan").css("display", "none");
	    		$("#dingjinPriceText").text(div.find("input[name=showRatioPrice]").val()+"万元");
    		} else {
    			$("#dingjin").css("display", "none");
	    		$("#quankuan").css("display", "");
    			$("#orderStatusText").text("余款");
	    		$("#priceText").text(div.find("input[name=showPrice]").val()+"万元");
	    		$("#numText").text(div.find("input[name=num]").val());
	    		$("#deliveryPriceText").text(div.find("input[name=deliveryPrice]").val());
	    		$("#tradePriceText").text(div.find("input[name=tradePrice]").val());
	    		$("#otherPriceText").text(div.find("input[name=otherPrice]").val());
	    		$("#orderPriceText").text(div.find("input[name=showOrderPrice]").val()+"万元");
	    		$("#ratioPriceText").text(div.find("input[name=showRatioPrice]").val()+"万元");
	    		
	    		var orderPrice = parseFloat(div.find("input[name=showOrderPrice]").val())*10000;
	    		var dingjin = parseInt(div.find("input[name=showRatioPrice]").val())*10000;
	    		
	    		$("#payPriceText").text((orderPrice-dingjin)/10000.0 + "万元");
    		}
//     		$("#accountHolderText").text(div.find("input[name=accountHolder]").val());
    		orderId = div.find("input[name=orderId]").val();
    	}
        if (payModal) {
            payModal.show();
            return;
        }
        payModal = new Fache.UI.Modal({
            id: 'J-Pay',
            ok: function(){
                payModal.close();
                document.location.href = "/orderprotal_preparePayOrder.do?orderInfo.id="+orderId;
            }
        });
        payModal.show();
    };

    //确认首付款
    window.sureFirstPay = function(btn){
    	//查找orderId
    	//更新状态
    	var div = $(btn).parent(".td.action");
    	if(div) {
    		if(confirm("是否确认首款？")){
    			var orderId = div.find("input[name=orderId]").val();
        		$.ajax({
        			type: "POST",
        			url: "/orderprotal_sureFirstPay.do",
        			data: {"orderForm.id":orderId},
        			success: function(data){
        				if (data.codeid == 0) {
        					document.location.reload(true);
        				}
        			},
        			error: function(XMLHttpRequest, textStatus, errorThrown){
        				alert("订单签收失败");
        			},
        			dataType: "json"
        		});
    		}
    	}
    };
});

/**
 * 弹出取消原因框
 */
function showReason(id) {
	$("#cancelOrderId").val(id);
	modal.show();
}

function sign(id) {
	$.ajax({
		type: "POST",
		url: "/orderprotal_sign.do",
		data: {"orderForm.id":id},
		success: function(data){
			if (data.codeid == 0) {
				document.location.reload(true);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("订单签收失败");
		},
		dataType: "json"
	});
}

/**
 * 取消订单
 */
function cancelOrder() {
	 var reason = $('input[name="reason"]:checked ').val();
	 if (reason == undefined) {
		 alert("请您选择取消订单的理由！");
		 return false;
	 }
	 if (reason == "其他理由") {
		 if ($.trim($("#remark").val()) == "") {
			 alert("请您填写其他理由!");
			 return false;
		 }
	 }

	 $.ajax({
		type: "POST",
		url: "/orderprotal_cancelOrder.do",
		data: {"orderForm.id":$("#cancelOrderId").val(), "orderForm.reason":reason, "orderForm.remark":$.trim($("#remark").val()), "orderForm.orderStatus":7},
		success: function(data){
			alert(data.message);
			if (data.codeid == 0) {
				document.location.href = "/orderprotal_init.do";
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("订单取消失败");
		},
		dataType: "json"
	});
}

/**
 * 分页跳转
 * @param toPage
 * @return
 */
function turnToPage(toPage) {
	if (toPage == -1) { // 上一页
		if ($("#cuurentPage").val() == 1) {
			return false;
		}
		var page = $("#cuurentPage").val() - 1;
		if (page < 1) {
			$("#cuurentPage").val(1);
		} else {
			$("#cuurentPage").val(page);
		}
	} else if (toPage == -2) { // 下一页
		if ($("#cuurentPage").val() == $("#allPage").val()) {
			return false;
		}
		var page = $("#cuurentPage").val() + 1;
		if (page > $("#allPage").val()) {
			$("#cuurentPage").val($("#allPage").val());
		} else {
			$("#cuurentPage").val(page);
		}
	} else if (toPage == 0) { // 输入框跳转
		if ($("#cuurentPage").val() == $("#turnPage").val()) {
			return false;
		}
		var page = $("#turnPage").val();
		if (page > $("#allPage").val()) {
			return false;
		} else if (page < 1) {
			return false;
		} else {
			$("#cuurentPage").val(page);
		}
	} else {
		if (toPage == $("#cuurentPage").val()) {
			return false;
		} else {
			$("#cuurentPage").val(toPage);
		}
	}

	document.location.href = "/orderprotal_init.do?page=" + $("#cuurentPage").val();
}

/**
 * 查看详情
 */
function viewOrder(id) {
	document.location.href = "/orderprotal_view.do?viewType=buy&orderForm.id=" + id;
}
</script>
</body>
</html>