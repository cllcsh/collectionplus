<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include  file="/portal/header.jsp"%>

    <link rel="stylesheet" href="/portal/css/style.css">
	<style type="text/css">
	.mod-order-list .table .tr .td {
		width: 11%;
	}
	.mod-order-list .table .tr .td-main {
		width: 23%;
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
                <a href="/orderprotal_init.do" class="item">进货订单</a>
                <a href="/orderprotal_sell.do" class="item active">出货订单</a>
                <a href="/orderprotal_my.do" class="item">我的车源</a>
            </div>
            <div class="mod-order-list">
                <div class="table">
                    <div class="tr thead">
                        <div class="td td-main">商品信息</div>
                        <div class="td">单价</div>
                        <div class="td">数量</div>
                        <div class="td">其他费用</div>
                        <div class="td">首款</div>
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
                            <div class="td lh100">&yen; <span class="comm-text-red"><s:property value='#orderInfo.showPrice'/>万</span></div>
                            <div class="td lh100"><s:property value='#orderInfo.num'/></div>
                            <div class="td lh100">&yen; <span class="comm-text-red"><s:property value='#orderInfo.otherPrice'/>元</span></div>
                            <div class="td lh100">&yen; <span class="comm-text-red"><s:property value='#orderInfo.showRatioPrice'/>万</span></div>
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
                            <div class="td">
                            	<div class="button button-link" onclick="viewOrder(<s:property value='#orderInfo.id'/>);">查看订单</div>
                            	
                            	<s:if test="%{#orderInfo.orderStatus == 0}">
                            		<div class="button button-link" onclick="editOrder(<s:property value='#orderInfo.id'/>);">编辑</div>
                            		<div class="button button-link" onclick="makeSureOrder(<s:property value='#orderInfo.id'/>);">确认</div>
                            	</s:if>
                            	<s:if test="%{#orderInfo.orderStatus == 2}">
                            		<div class="button button-link" onclick="prepareOrder(<s:property value='#orderInfo.id'/>);">准备完成</div>
                            	</s:if>
                            	<s:if test="%{#orderInfo.orderStatus == 4}">
                            		<div class="button button-link" onclick="delivery(<s:property value='#orderInfo.id'/>);">发货</div>
                            	</s:if>
<%--                                <div class="button button-link">删除</div>--%>
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
            
            <div id="J-Modal-Edit" class="comm-modal none">
            	<form id="editForm">
			        <div class="modal">
			            <div class="close j-close">&times;</div>
			            <div class="title">编辑订单</div>
			            <div class="mod-buy-modal">
			                <div id="reviewTitle" class="title"></div>
			                <input type="hidden" id="id" name="orderForm.id"/>
			                <div class="">
			                    <div class="col col-12">
			                        <div class="info-item">
			                            <span class="label">单价：</span>
			                            <div class="input" id="editPrice"><input type="text" id="price" name="price" placeholder="价格" onkeyup="this.value=this.value.replace(/^\D+(\.\D{2})?$/g,'')" onafterpaste="this.value=this.value.replace(/^\D+(\.\D{2})?$/g,'')" />万</div>
			                        </div>
			                    </div>
			                </div>
			                <div class="" style="display:none;">
			                    <div class="col col-12">
			                        <div class="info-item">
			                            <span class="label">物流费：</span>
			                            <div class="input" id="editPrice"><input type="text" id="deliveryPrice" name="orderForm.deliveryPrice" placeholder="物流费" onkeyup="this.value=this.value.replace(/^\D+$/g,'')" onafterpaste="this.value=this.value.replace(/^\D+$/g,'')" />元</div>
			                        </div>
			                    </div>
			                </div>
			                <div class="" style="display:none;">
			                    <div class="col col-12">
			                        <div class="info-item">
			                            <span class="label">平台服务费：</span>
			                            <div class="input" id="editPrice"><input type="text" id="tradePrice" name="orderForm.tradePrice" placeholder="平台服务费" onkeyup="this.value=this.value.replace(/^\D+$/g,'')" onafterpaste="this.value=this.value.replace(/^\D+$/g,'')" />元</div>
			                        </div>
			                    </div>
			                </div>
			                <div class="">
			                    <div class="col col-12">
			                        <div class="info-item">
			                            <span class="label">其他费用：</span>
			                            <div class="input" id="editPrice"><input type="text" id="otherPrice" name="orderForm.otherPrice" placeholder="其他费用" onkeyup="this.value=this.value.replace(/^\D+$/g,'')" onafterpaste="this.value=this.value.replace(/^\D+$/g,'')" />元</div>
			                        </div>
			                    </div>
			                </div>
			                <div class="">
			                    <div class="col col-12">
			                        <div class="info-item">
			                            <span class="label">预付车款比例：</span>
			                            <div class="input" id="editDepositRatio">
			                            	<span class="select">
			                                    <select name="orderForm.depositRatio" id="depositRatio">
			                                        <option value="0">请选择预付车款比例</option>
			                                        <option value="1">10%</option>
			                                        <option value="2">20%</option>
			                                        <option value="3">30%</option>
			                                        <option value="4">40%</option>
			                                        <option value="5">50%</option>
			                                        <option value="6">60%</option>
			                                        <option value="7">70%</option>
			                                        <option value="8">80%</option>
			                                        <option value="9">90%</option>
			                                        <option value="10">100%</option>
			                                    </select>
			                                </span>
			                            </div>
			                        </div>
			                    </div>
			                </div>
			                <div class="">
			                    <div class="col col-12">
			                        <div class="info-item">
			                            <span class="label">物流方式：</span>
			                            <div class="input" id="editDeliveryPeriod">
			                            	<span class="select">
			                                    <select name="orderForm.logistics" id="logistics" disabled="disabled">
			                                        <option value="1">买家自提</option>
			                                        <option value="2">卖家配送</option>
			                                        <option value="3">平台配送</option>
			                                    </select>
			                                </span>
			                            </div>
			                        </div>
			                    </div>
			                </div>
			                <div class="">
			                    <div class="col col-12">
			                        <div class="info-item">
			                            <span class="label">提货周期：</span>
			                            <div class="input">
			                            	<span class="select">
			                                   	<select name="orderForm.deliveryPeriod" id="deliveryPeriod">
			                                        <option value="0">任意</option>
			                                        <option value="3">3天</option>
			                                        <option value="7">7天</option>
			                                        <option value="15">15天</option>
			                                        <option value="30">30天</option>
			                                    </select>
			                                </span>
			                            </div>
			                        </div>
			                    </div>
			                </div>
			                <div class="">
			                    <div class="col col-12">
			                        <div class="info-item">
			                            <span class="label">数量：</span>
			                            <div class="input">
			                                <div class="comm-number-selection">
			                                    <div id="subTrigger" class="sub comm-trigger">-</div>
			                                    <input id="num" name="orderForm.num" class="value" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
			                                    <div id="plusTrigger" class="plus comm-trigger">+</div>
			                                </div>
			                            </div>
			                        </div>
			                    </div>
			                </div>
			                <div class="">
			                    <div class="col col-12">
			                        <div class="info-item">
			                            <span class="label">总价：</span>
			                            <div class="input text-right comm-text-red"><span class="comm-price">&yen;<strong id="reviewTotalPrice"></strong><strong>万</strong></span></div>
			                        </div>
			                    </div>
			                </div>
			                <div class="action text-center">
			                    <div class="button button-primary" onclick="saveEditOrder();">确认修改</div>
			                </div>
			            </div>
			        </div>
		        </form>
		    </div>
		    <div id="J-Modal-Delivery" class="comm-modal none">
            	<form id="deliveryForm">
			        <div class="modal">
			            <div class="close j-close">&times;</div>
			            <div class="title">发货</div>
			            <div class="mod-buy-modal">
			                <div id="reviewTitle" class="title"></div>
			                <input type="hidden" id="delivery_orderId" name="orderForm.id"/>
			                <div class="row">
			                    <div class="col col-12">
			                        <div class="info-item">
			                            <span class="label">物流公司：</span>
			                            <div class="input">
			                            	<span class="select">
			                                    <select name="orderForm.logisticsCompany" onchange="changeLogistics(this)">
			                                        <option value="1">顺风快递</option>
			                                        <option value="2">申通快递</option>
			                                        <option value="3">圆通快递</option>
			                                        <option value="4">中通快递</option>
			                                        <option value="5">其他</option>
			                                    </select>
			                                </span>
			                            </div>
			                        </div>
			                    </div>
			                </div>
			                <div class="row" id="logisticsCompanyContainer" style="display:none">
			                    <div class="col col-12">
			                        <div class="info-item">
			                            <div class="input" style="margin-left:70px;">
		                                    <input type="text" placeholder="其他物流公司名称" name="orderForm.logisticsCompanyName"/>
			                            </div>
			                        </div>
			                    </div>
			                </div>
			                <div class="row">
			                    <div class="col col-12">
			                        <div class="info-item">
			                            <span class="label">物流单号：</span>
			                            <div class="input"><input type="text" name="orderForm.logisticsNo" placeholder="物流单号" onkeyup="this.value=this.value.replace(/^\D+$/g,'')" onafterpaste="this.value=this.value.replace(/^\D+$/g,'')" /></div>
			                        </div>
			                    </div>
			                </div>
			                <div class="action text-center">
			                    <div class="button button-primary" onclick="saveDelivery();">确认</div>
			                </div>
			            </div>
			        </div>
		        </form>
		    </div>
        </div>
    </div>

<%@include file="/portal/footer.jsp" %>   
<script src="/portal/js/vendor/jquery.js"></script>
<script src="/portal/js/app.js"></script>
<script src="/portal/js/numberFormat.js"></script>
<script type="text/javascript">
var modalEdit = new FACHE.UI.Modal({
    id : 'J-Modal-Edit'
});

var modalDelivery = new FACHE.UI.Modal({
    id : 'J-Modal-Delivery'
});

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
		var page = parseInt($("#cuurentPage").val()) - 1;
		if (page < 1) {
			$("#cuurentPage").val(1);
		} else {
			$("#cuurentPage").val(page);
		}
	} else if (toPage == -2) { // 下一页
		if ($("#cuurentPage").val() == $("#allPage").val()) {
			return false;
		}
		var page = parseInt($("#cuurentPage").val()) + 1;
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

	document.location.href = "/orderprotal_sell.do?page=" + $("#cuurentPage").val();
}

/**
 * 查看详情
 */
function viewOrder(id) {
	document.location.href = "/orderprotal_view.do?viewType=sell&orderForm.id=" + id;
}

/**
 * 显示修改页面
 */
function genEdit(id) {
	$.ajax({
        cache: true,
        type: "POST",
        url:"/orderprotal_find.do",
        data:{"orderForm.id":id},
        async: false,
        error: function(request) {
            alert("网络错误");
        },
        success: function(data) {
        	var result = $.parseJSON(data);
        	var order = $.parseJSON(result.message);
        	$("#id").val(id);
        	$("#price").val(order.price/10000.0);
			$("#price").on("keyup", function() {
        		var price = parseFloat($(this).val()) * 10000.0;
        		// var deliveryPrice = parseInt($("#deliveryPrice").val()); 
            	// var tradePrice = parseInt($("#tradePrice").val()); 
            	var otherPrice = parseInt($("#otherPrice").val());
            	var num = parseInt($("#num").val());
        		// $("#reviewTotalPrice").html(((price*order.num+deliveryPrice+tradePrice+otherPrice) / 10000.0).toFixed(2));
        		$("#reviewTotalPrice").html(number_format(((price*num+otherPrice) / 10000.0), 4));
        	});
			$("#otherPrice").on("keyup", function() {
        		var price = parseFloat($("#price").val()) * 10000.0;
        		// var deliveryPrice = parseInt($("#deliveryPrice").val()); 
            	// var tradePrice = parseInt($("#tradePrice").val()); 
            	var otherPrice = parseInt($("#otherPrice").val());
            	var num = parseInt($("#num").val());
        		// $("#reviewTotalPrice").html(((price*order.num+deliveryPrice+tradePrice+otherPrice) / 10000.0).toFixed(2));
        		$("#reviewTotalPrice").html(number_format(((price*num+otherPrice) / 10000.0), 4));
        	});
			$("#deliveryPrice").on("keyup", function() {
        		var price = parseFloat($("#price").val()) * 10000.0;
        		var deliveryPrice = parseInt($("#deliveryPrice").val());
        		// var deliveryPrice = parseInt($("#deliveryPrice").val()); 
            	// var tradePrice = parseInt($("#tradePrice").val()); 
            	var otherPrice = parseInt($("#otherPrice").val());
            	var num = parseInt($("#num").val());
        		// $("#reviewTotalPrice").html(((price*order.num+deliveryPrice+tradePrice+otherPrice) / 10000.0).toFixed(2));
        		$("#reviewTotalPrice").html(number_format(((price*num+otherPrice) / 10000.0), 4));
        	});
			$("#tradePrice").on("keyup", function() {
        		var price = parseFloat($("#price").val()) * 10000.0;
        		// var deliveryPrice = parseInt($("#deliveryPrice").val()); 
            	// var tradePrice = parseInt($("#tradePrice").val()); 
            	var otherPrice = parseInt($("#otherPrice").val());
            	var num = parseInt($("#num").val());
        		// $("#reviewTotalPrice").html(((price*order.num+deliveryPrice+tradePrice+otherPrice) / 10000.0).toFixed(2));
        		$("#reviewTotalPrice").html(number_format(((price*num+otherPrice) / 10000.0), 4));
        	});
			$("#subTrigger").on("click", function() {
        		var price = parseFloat($("#price").val()) * 10000.0;
        		// var deliveryPrice = parseInt($("#deliveryPrice").val()); 
            	// var tradePrice = parseInt($("#tradePrice").val()); 
            	var otherPrice = parseInt($("#otherPrice").val());
            	var num = parseInt($("#num").val());
        		// $("#reviewTotalPrice").html(((price*order.num+deliveryPrice+tradePrice+otherPrice) / 10000.0).toFixed(2));
        		$("#reviewTotalPrice").html(number_format(((price*num+otherPrice) / 10000.0), 4));
        	});
			$("#plusTrigger").on("click", function() {
        		var price = parseFloat($("#price").val()) * 10000.0;
        		// var deliveryPrice = parseInt($("#deliveryPrice").val()); 
            	// var tradePrice = parseInt($("#tradePrice").val()); 
            	var otherPrice = parseInt($("#otherPrice").val());
            	var num = parseInt($("#num").val());
        		// $("#reviewTotalPrice").html(((price*order.num+deliveryPrice+tradePrice+otherPrice) / 10000.0).toFixed(2));
        		$("#reviewTotalPrice").html(number_format(((price*num+otherPrice) / 10000.0), 4));
        	});
			$("#num").on("keyup", function() {
        		var price = parseFloat($("#price").val()) * 10000.0;
        		// var deliveryPrice = parseInt($("#deliveryPrice").val()); 
            	// var tradePrice = parseInt($("#tradePrice").val()); 
            	var otherPrice = parseInt($("#otherPrice").val());
            	var num = parseInt($("#num").val());
        		// $("#reviewTotalPrice").html(((price*order.num+deliveryPrice+tradePrice+otherPrice) / 10000.0).toFixed(2));
        		$("#reviewTotalPrice").html(number_format(((price*num+otherPrice) / 10000.0), 4));
        	});
			$("#reviewTotalPrice").html(order.showOrderPrice);
        	$("#depositRatio").val(order.depositRatio);
        	$("#deliveryPeriod").val(order.deliveryPeriod);
        	$("#logistics").val(order.logistics); 
        	$("#deliveryPrice").val(order.deliveryPrice); 
        	$("#tradePrice").val(order.tradePrice);
        	$("#otherPrice").val(order.otherPrice);
        	$("#num").val(order.num);
        	
        	modalEdit.show();
        }
    });
}

/**
 * 修改
 */
function editOrder(id) {
 	genEdit(id);
}

function makeSureOrder(id) {
	var se=confirm("是否确认订单？");
	if (se==true) {
		$.ajax({
	        cache: true,
	        type: "POST",
	        url:"/orderprotal_makesureOrder.do",
	        data:{"orderForm.id":id},// 你的formid
	        async: false,
	        error: function(request) {
	        	alert("网络错误");
	        },
	        success: function(data) {
	        	data = $.parseJSON(data);
            	alert(data.message);
            	if(data.codeid=='0') {
                    window.location.reload();
            	}
	        }
	    });
	}
}

function prepareOrder(id) {
	var se=confirm("订单是否已准备好？");
	if (se==true) {
		$.ajax({
	        cache: true,
	        type: "POST",
	        url:"/orderprotal_prepareOrder.do",
	        data:{"orderInfo.id":id},// 你的formid
	        async: false,
	        error: function(request) {
	        	alert("网络错误");
	        },
	        success: function(data) {
	        	data = $.parseJSON(data);
            	alert(data.message);
            	if(data.codeid=='0') {
                    window.location.reload();
            	}
	        }
	    });
	}
}

/**
 * 显示发货界面
 */
function delivery(id) {
	$("#delivery_orderId").val(id);
	modalDelivery.show();
}

function changeLogistics(select) {
	//判断是否等于5其他
	var value = $(select).val();
	if(value == "5"){
		//如果是：设置输入框的值为空，并且显示输入框
		$("#logisticsCompanyContainer input").val("");
		$("#logisticsCompanyContainer").css("display", "");
	} else {
		//如果不是：隐藏输入框
		$("#logisticsCompanyContainer").css("display", "none");
	}
}

function saveDelivery() {
	if($("input[name='orderForm.logisticsNo']").val() == "") {
		alert("请输入物流单号");
		return;
	} else {
		var text = $("select[name='orderForm.logisticsCompany'] option:selected").text()
		var value = $("select[name='orderForm.logisticsCompany']").val();
		if(value != "5"){
			$("#logisticsCompanyContainer input").val(text);
		} else if($("#logisticsCompanyContainer input").val() == "") {
			alert("请输入物流公司");
			return;
		}
		$.ajax({
	        cache: true,
	        type: "POST",
	        url:"/orderprotal_delivery.do",
	        data:$('#deliveryForm').serialize(),// 你的formid
	        async: false,
	        error: function(request) {
	        	alert("网络错误");
	        },
	        success: function(data) {
	        	data = $.parseJSON(data);
	        	alert(data.message);
	        	if(data.codeid=='0') {
	                window.location.reload();
	        	}
	        }
	    });
	}
}

/**
 * 检查表单
 */
function check() {
	if($("#price").val() == "") {
		alert("请输入价格");
		return false;
	}
	/**
	if($("#deliveryPrice").val() == "") {
		alert("请输入物流费");
		return false;
	}
	if($("#tradePrice").val() == "") {
		alert("请输入平台服务费");
		return false;
	}
	if($("#logistics").val() == "") {
		alert("请选择物流方式");
		return false;
	}
	**/
	if($("#otherPrice").val() == "") {
		alert("请输入其他费用");
		return false;
	}
	if($("#depositRatio").val() == "") {
		alert("请选择预付车款比例");
		return false;
	}
	if($("#deliveryPeriod").val() == "") {
		alert("请选择提货周期");
		return false;
	}
	if($("#num").val() == "" || parseInt($("#num").val()) == 0) {
		alert("请输入订单数量");
		return false;
	}
	return true;
}

function saveEditOrder() {
	if(!check()) {
		return;
	}
	
	$.ajax({
        cache: true,
        type: "POST",
        url:"/orderprotal_update.do",
        data:$('#editForm').serialize(),// 你的formid
        async: false,
        error: function(request) {
            alert("网络错误");
        },
        success: function(data) {
        	alert(data.message);
        	if(data.codeid == '0') {
	            window.location.reload();
        	}
        },
		dataType: "json"
    });
	
}
</script>
</body>
</html>
