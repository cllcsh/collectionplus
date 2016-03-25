<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<head>
    <title>进货订单</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="baidu-site-verification" content="hWGw6qbfv7" />
    <link rel="stylesheet" href="/m/css/style.css">

</head>
<body>
    <header class="comm-header">
    <div class="left opt">
        <a href="/muserportal_init.do">返回</a>
    </div>
    <h1 class="title">进货订单</h1>
</header>


    <div class="wrap">
        <div class="mod-order-tab">
            <div class="item active"><a href="/morderprotal_init.do">进货订单</a></div>
            <div class="item"><a href="/morderprotal_sell.do">出货订单</a></div>
            <div class="item"><a href="/morderprotal_my.do">我的车源</a></div>
        </div>

        <div id="J-OrderList" class="mod-order-list">
		<s:iterator var="orderInfo" value="pageList.results">
            <div class="no">订单号：<s:property value="#orderInfo.orderCode"/> <span class="right status" style="background-color: #59d58d">
			<s:if test="%{#orderInfo.orderStatus == 0}">等待卖家确认</s:if>
            <s:if test="%{#orderInfo.orderStatus == 1}">等待买家支付首款</s:if>
            <s:if test="%{#orderInfo.orderStatus == 2}">买家已付首款，交易准备中</s:if>
            <s:if test="%{#orderInfo.orderStatus == 3}">等待买家支付尾款</s:if>
            <s:if test="%{#orderInfo.orderStatus == 4}">已付尾款，等待卖家发货</s:if>
            <s:if test="%{#orderInfo.orderStatus == 5}">物流中</s:if>
            <s:if test="%{#orderInfo.orderStatus == 6}">交易完成</s:if>
            <s:if test="%{#orderInfo.orderStatus == 7}">已取消</s:if>
			</span></div>
            <div class="base-info">
	        	<a href="/morderprotal_view.do?viewType=buy&orderForm.id=<s:property value='#orderInfo.id'/>" class="item">
                    <div class="img"><img src="<s:property value='#orderInfo.carInfo.modelyearInfo.picPath2'/>" alt=""></div>
                    <div class="info">
                        <div class="title"><s:property value='#orderInfo.carInfo.brandInfo.name'/><span class="comm-text-gray">|</span>  <s:property value='#orderInfo.carInfo.seriesInfo.name'/> <s:property value='#orderInfo.carInfo.modelyearInfo.name'/><s:property value='#orderInfo.carInfo.carVersionInfo.name'/></div>
                        <div class="comm-text-gray">购买时间：<s:date name="#orderInfo.insertDate" format="yyyy-MM-dd"></s:date> <s:date name="#orderInfo.insertDate" format="HH:mm:ss"></s:date></div>
                        <div class="comm-text-gray">总价：<span class="comm-text-red"><s:property value='#orderInfo.showOrderPrice'/>万</span>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            数量：<span class="comm-text-red"><s:property value='#orderInfo.num'/>辆</span>
                        </div>
                    </div>
	            </a>
             </div>
             <div class="actions">
		 	 <s:if test="%{#orderInfo.orderStatus == 1 || #orderInfo.orderStatus == 3}">
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
		 	 	<div  class="item" onclick="payModal(this);return false;">立即付款</div>
           	 </s:if>
           	 <s:if test="%{#orderInfo.orderStatus == 2 && #orderInfo.isSureFirstPay!=1}">
           		<div class="item" onclick="sureFirstPay(this);">首款确认</div>
           	 </s:if>
           	 <s:if test="%{#orderInfo.orderStatus == 3}">
           		<s:if test="%{#orderInfo.isSureFirstPay==1}">
             		<div  class="item" onclick="payModal(this);return false;">立即付款</div>
           		</s:if>
           		<s:else>
           			<div class="button button-primary" onclick="sureFirstPay(this);">首款确认</div>
           		</s:else>
           	 </s:if>
		 	 
		 	 
		 	 <s:if test="%{#orderInfo.orderStatus == 5}">
		 	 	<div  class="item" onclick="sign(this);return false;">确认收货</div>
		 	 </s:if>
		 	 <s:if test="%{#orderInfo.orderStatus == 0 || #orderInfo.orderStatus == 1}">
		 	 	<div  class="item" onclick="cancelModal(<s:property value='#orderInfo.id'/>);return false;">取消订单</div>
		 	  </s:if>
                 <!--<div class="item" onclick="cancelModal();return false;">取消订单</div>-->
             </div>
		</s:iterator>
        </div>
        <div class="comm-loadmore" data-offset="1" data-list-id="J-OrderList" data-size="10" data-url="/morderprotal_init.do">点击加载更多</div>
    </div>

    <div id="J-Pay" class="comm-modal none">
        <div class="modal" style="margin-top: 20px;">
            <div class="close j-close">&times;</div>
            <h1 class="title">支付</h1>
            <div class="body">
                <div class="form-group">
                    <label for="" class="label">开户行：</label>
                    <div id="bankNameText" class="input">招商银行股份有限公司上海田林支行</div>
                </div>
                <div class="form-group">
                    <label for="" class="label">银行账号：</label>
                    <div id="cardNoText" class="input">1219 1719 3210 602</div>
                </div>
                <div class="form-group">
                    <label for="" class="label">开户人：</label>
                    <div id="accountHolderText" class="input">上海发车信息技术有限公司</div>
                </div>
                <div class="form-group">
                    <label for="" class="label">备注：</label>
                    <div class="input">订单号（如银行无输入备注信息，可省略不填写）</div>
                </div>
            </div>
            <div class="action">
                <div class="button button-primary j-ok">确认</div>
            </div>
        </div>
    </div>

    <div id="J-Modal" class="comm-modal none">
        <div class="modal" style="margin-top: 20px;">
            <div class="close j-close">&times;</div>
            <div class="title">取消订单的理由</div>
            <div class="mod-buy-modal" style="padding: 10px 20px;">
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

    <div id="J-UndoSell" class="comm-modal none">
        <div class="modal">
            <div class="close j-close">&times;</div>
            <h1 class="title">商品下架</h1>
            <div class="text"><textarea id="J-TextReason" placeholder="填写下架原因"></textarea></div>
            <div class="action">
                <div class="button button-primary j-ok">确认下架</div>
            </div>
        </div>
    </div>

    <script src="/m/bower_components/zepto/zepto.min.js"></script>
    <script src="/m/bower_components/zeptotouch/zepto-touch.min.js"></script>
    <script src="/m/vendor/zepto-selector.js"></script>
    <script src="/m/js/app.js"></script>
    <script>
        window.onload = function(){
            Fache.bindLoadMore();

            var payModal;
            var orderId;
            window.payModal = function(btn){
            	var div = $(btn).parent(".actions");
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
//             		$("#accountHolderText").text(div.find("input[name=accountHolder]").val());
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
                    }
                });
                payModal.show();
            };

          	//确认首付款
            window.sureFirstPay = function(btn){
            	//查找orderId
            	//更新状态
            	var div = $(btn).parent(".actions");
            	if(div) {
            		if(confirm("是否确认首款？")){
            			var orderId = div.find("input[name=orderId]").val();
                		$.ajax({
                			type: "POST",
                			url: "/morderprotal_sureFirstPay.do",
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
            
            var cancelModal;
            window.cancelModal = function(id){
				$("#cancelOrderId").val(id);
                if (cancelModal) {
                    cancelModal.show();
                    return;
                }
                cancelModal = new Fache.UI.Modal({
                    id: 'J-Modal',
                    ok: function(){
                        cancelModal.close();
                    }
                });
                cancelModal.show();
            };

            // 显示下架原因
            var undoModal;
            window.undoSell = function(){
                var textarea = $('#J-TextReason');
                textarea.val('');
                if (undoModal) {
                    undoModal.show();
                    return;
                }
                undoModal = new Fache.UI.Modal({
                    id: 'J-UndoSell',
                    ok: function(){
                        console.debug(textarea.val());
                        undoModal.close();
                    }
                });
                undoModal.show();
            };
        };

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
		url: "/morderprotal_cancelOrder.do",
		data: {"orderForm.id":$("#cancelOrderId").val(), "orderForm.reason":reason, "orderForm.remark":$.trim($("#remark").val()), "orderForm.orderStatus":7},
		success: function(data){
			if (data.codeid == 0) {
				document.location.href = "/morderprotal_init.do";
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("订单取消失败");
		},
		dataType: "json"
	});
}

function sign(id) {
	$.ajax({
		type: "POST",
		url: "/orderprotal_sign.do",
		data: {"orderForm.id":id},
		success: function(data){
			alert(data.message);
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
    </script>
</body>
</html>
