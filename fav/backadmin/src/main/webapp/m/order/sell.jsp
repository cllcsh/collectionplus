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
    <h1 class="title">出货订单</h1>
</header>


    <div class="wrap">
        <div class="mod-order-tab">
            <div class="item"><a href="/morderprotal_init.do">进货订单</a></div>
            <div class="item active"><a href="/morderprotal_sell.do">出货订单</a></div>
            <div class="item"><a href="/morderprotal_my.do">我的车源</a></div>
        </div>

        <div id="J-OrderList" class="mod-order-list">
		<s:iterator var="orderInfo" value="pageList.results">
           <a href="/morderprotal_view.do?viewType=buy&orderForm.id=<s:property value='#orderInfo.id'/>" class="item">
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
                    <div class="img"><img src="<s:property value='#orderInfo.carInfo.modelyearInfo.picPath2'/>" alt=""></div>
                    <div class="info">
                        <div class="title"><s:property value='#orderInfo.carInfo.brandInfo.name'/><span class="comm-text-gray">|</span>  <s:property value='#orderInfo.carInfo.seriesInfo.name'/> <s:property value='#orderInfo.carInfo.modelyearInfo.name'/><s:property value='#orderInfo.carInfo.carVersionInfo.name'/></div>
                        <div class="comm-text-gray">购买时间：<s:date name="#orderInfo.insertDate" format="yyyy-MM-dd"></s:date> <s:date name="#orderInfo.insertDate" format="HH:mm:ss"></s:date></div>
                        <div class="comm-text-gray">总价：<span class="comm-text-red"><s:property value='#orderInfo.showOrderPrice'/>万</span>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            数量：<span class="comm-text-red"><s:property value='#orderInfo.num'/>辆</span>
                        </div>
                    </div>
                </div>
                <div class="actions">
               		<s:if test="%{#orderInfo.orderStatus == 0}">
                    	<div class="item" onclick="makeSureOrder(<s:property value='#orderInfo.id'/>);">确认订单</div>
                    </s:if>
					<s:if test="%{#orderInfo.orderStatus == 0 || #orderInfo.orderStatus == 1}">
						<div class="item" onclick="cancelModal();return false;">取消订单</div>
					 </s:if>
					 <s:if test="%{#orderInfo.orderStatus < 2}">
					 	<div class="item" onclick="editModel(<s:property value='#orderInfo.id'/>);return false;">编辑订单</div>
					 </s:if>
					 <s:if test="%{#orderInfo.orderStatus == 2}">
                   		<div class="item" onclick="prepareOrder(<s:property value='#orderInfo.id'/>);">准备完成</div>
                   	 </s:if>
                   	 <s:if test="%{#orderInfo.orderStatus == 4}">
                   		<div class="item" onclick="delivery(<s:property value='#orderInfo.id'/>);">发货</div>
                   	 </s:if>
                    <!--<div class="item" onclick="cancelModal();return false;">取消订单</div>-->
                </div>
            </a>
		</s:iterator>
        </div>
        <div class="comm-loadmore" data-offset="1" data-list-id="J-OrderList" data-size="10" data-url="/morderprotal_sell.do">点击加载更多</div>
    </div>

    <div id="J-Pay" class="comm-modal none">
        <div class="modal" style="margin-top: 20px;">
            <div class="close j-close">&times;</div>
            <h1 class="title">支付</h1>
            <div class="body">
                <div class="form-group">
                    <label for="" class="label">开户行：</label>
                    <div class="input">中国招商银行</div>
                </div>
                <div class="form-group">
                    <label for="" class="label">银行账号：</label>
                    <div class="input">1234 1234 1234 1234</div>
                </div>
                <div class="form-group">
                    <label for="" class="label">开户人：</label>
                    <div class="input">张三</div>
                </div>
                <div class="form-group">
                    <label for="" class="label">备注：</label>
                    <div class="input">这里是备注这里是备注这里是备注这里是备注这里是备注这里是备注这里是备注这里是备注这里是备注这里是备注这里是备注这里是备注这里是备注</div>
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
                    <textarea name="" id="" cols="30" rows="10"></textarea>
                </div>
                <div class="action text-center">
                    <input type="hidden" id="cancelOrderId" />
                    <div class="button button-primary" onclick="cancelOrder();">确认取消</div>
                </div>
            </div>
        </div>
    </div>
    
    <div id="J-Modal-Edit" class="comm-modal none">
    	<form id="editForm">
	        <div class="modal" style="margin-top: 20px;">
	            <div class="close j-close">&times;</div>
	            <div class="title">编辑订单</div>
	            <div class="mod-buy-modal" style="padding: 10px 20px;">
	            	<input type="hidden" name="orderForm.id" id="id"/>
	                <div class="radio form-group">
	                    <label for="price">价格</label>
	                    <input type="text" id="price" name="price" onkeyup="this.value=this.value.replace(/^\D+(\.\D{2})?$/g,'')" onafterpaste="this.value=this.value.replace(/^\D+(\.\D{2})?$/g,'')"/>
	                </div>
	                <div class="radio form-group">
	                    <label for="price">物流费</label>
	                    <input type="text" id="deliveryPrice" name="orderForm.deliveryPrice" onkeyup="this.value=this.value.replace(/^\D+$/g,'')" onafterpaste="this.value=this.value.replace(/^\D+$/g,'')"/>
	                </div>
	                <div class="radio form-group">
	                    <label for="price">平台服务费</label>
	                    <input type="text" id="tradePrice" name="orderForm.tradePrice" onkeyup="this.value=this.value.replace(/^\D+$/g,'')" onafterpaste="this.value=this.value.replace(/^\D+$/g,'')"/>
	                </div>
	                <div class="radio form-group">
	                    <label for="price">其他费用</label>
	                    <input type="text" id="otherPrice" name="orderForm.otherPrice" onkeyup="this.value=this.value.replace(/^\D+$/g,'')" onafterpaste="this.value=this.value.replace(/^\D+$/g,'')"/>
	                </div>
	                <div class="radio form-group">
	                    <label>预付车款比例</label>
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
	                </div>
	                <div class="radio form-group">
	                    <label for="deliveryPeriod">物流方式</label>
	                    <select name="orderForm.deliveryPeriod" id="deliveryPeriod">
	                        <option value="1">买家自提</option>
	                        <option value="2">卖家配送</option>
	                        <option value="3">平台配送</option>
	                    </select>
	                </div>
	                <div class="radio form-group">
	                    <label for="logistics">提货周期</label>
	                    <select name="orderForm.logistics" id="logistics">
	                        <option value="0">任意</option>
	                        <option value="3">3天</option>
	                        <option value="7">7天</option>
	                        <option value="15">15天</option>
	                        <option value="30">30天</option>
	                    </select>
	                </div>
	                <div class="radio form-group">
	                    <label>总价</label>
	                    <strong id="reviewTotalPrice"></strong>万
	                </div>
	                <div class="action text-center">
	                    <input type="hidden" id="cancelOrderId" />
	                    <div class="button button-primary" onclick="editOrder();">确认修改</div>
	                </div>
	            </div>
	        </div>
        </form>
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
	                                    <select name="orderForm.logisticsCompany">
	                                        <option value="1">顺风快递</option>
	                                        <option value="2">申通快递</option>
	                                        <option value="3">圆通快递</option>
	                                        <option value="4">中通快递</option>
	                                    </select>
	                                </span>
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

    <script src="/m/bower_components/zepto/zepto.min.js"></script>
    <script src="/m/bower_components/zeptotouch/zepto-touch.min.js"></script>
    <script src="/m/vendor/zepto-selector.js"></script>
    <script src="/m/js/app.js"></script>
    <script>
        window.onload = function(){
            Fache.bindLoadMore();

            var payModal;
            window.payModal = function(){
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

            var cancelModal;
            window.cancelModal = function(){
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
            
            var editModel;
            window.editModel = function(id) {
            	if (!editModel) {
	            	editModel = new Fache.UI.Modal({
	                    id: 'J-Modal-Edit',
	                    ok: function(){
	                    	editModel.close();
	                    }
	                });
            	}
            	
            	//加载数据
            	$.ajax({
            		type:'post',
            		data: {"orderForm.id":id},
            		url:'/morderprotal_find.do',
            		success:function(data){
            			var result = $.parseJSON(data);
                    	var order = $.parseJSON(result.message);
                    	$("#id").val(id);
                    	$("#price").val(order.price/10000.0);
            			$("#price").on("keyup", function() {
                    		var price = $(this).val();
                    		var deliveryPrice = $("#deliveryPrice").val(); 
                        	var tradePrice = $("#tradePrice").val(); 
                        	var otherPrice = $("#otherPrice").val();
                    		$("#reviewTotalPrice").html((price+deliveryPrice+tradePrice+otherPrice)*order.num);
                    	});
            			$("#reviewTotalPrice").html(order.orderPrice/10000.0);
                    	$("#depositRatio").val(order.depositRatio);
                    	$("#deliveryPeriod").val(order.deliveryPeriod);
                    	$("#logistics").val(order.logistics); 
                    	$("#deliveryPrice").val(order.deliveryPrice); 
                    	$("#tradePrice").val(order.tradePrice); 
                    	$("#otherPrice").val(order.otherPrice);
					},
					error: function() {
						alert("网络错误");
					}
            	});
            	
            	editModel.show();
            };
            
            
            window.check = function() {      
            	if($("#depositRatio").val() == "") {
            		alert("请选择预付车款比例");
            		return false;
            	}
            	if($("#deliveryPeriod").val() == "") {
            		alert("请选择物流方式");
            		return false;
            	}
            	if($("#logistics").val() == "") {
            		alert("请选择提货周期");
            		return false;
            	}
            	return true;
            };

            window.editOrder = function(){
            	if(!check()) {
            		return;
            	}
            	
            	$.ajax({
                    cache: true,
                    type: "POST",
                    url:"/morderprotal_update.do",
                    data:$('#editForm').serialize(),// 你的formid
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
            };
            
            window.makeSureOrder = function(id) {
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
            
            window.prepareOrder = function(id) {
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
            
            var deliveryModel;
            window.deliveryModel = function(id) {
            	if (!deliveryModel) {
            		deliveryModel = new Fache.UI.Modal({
	                    id: 'J-Modal-Delivery',
	                    ok: function(){
	                    	deliveryModel.close();
	                    }
	                });
            	}
            	$("#delivery_orderId").val(id);
            };

            window.saveDelivery = function() {
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

        };
    </script>
</body>
</html>
