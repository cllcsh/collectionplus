<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include  file="/portal/header.jsp"%>

    <link rel="stylesheet" href="/portal/css/style.css">
    <link rel="stylesheet" href="/portal/css/pay.css">
    <style>
    	.tab-ul li.checked {
    		background-color:rgb(204, 8, 8);
    		color:white;
    	}
    	.tab-ul {
    		margin: auto;line-height: 40px;padding: 0px;list-style: none;text-indent:20px;
    	}
    	.tab-ul li {
    		padding-right: 20px;
    		cursor:pointer;
    		background-color:rgba(124, 234, 247, 0.22);
    		margin:0px;
    	}
    	.pay-type {
    		float:left;
    		padding-left: 20px;
    	}
    	.pay-type .title {
    	  	display: block;
	 	 	margin: 10px 0px;
    	}
    	.pay-type span {
    		display:block;
    	  	line-height: 25px;
    	}
    	.pay-type p.account {
    	  	padding: 10px;
  			border: 2px solid #CECECE;
    	}
    	.hide {
    		display:none;
    	}
    </style>
</head>
<body>
<%@ include  file="/portal/inc.jsp"%>
    <div class="comm-white-ctn">
        <div class="container">
        	<div style="margin:30px 0px;font-size:14px;">
	        	<input type="hidden" name="payCode" value="<s:property value="payRecordInfo.payCode"/>"/>
	        	<input type="hidden" name="price" value="<s:property value="payRecordInfo.price"/>"/>
	        	<div>订单号：<s:property value="orderInfo.orderCode"/></div>
	        	<div style="height: 50px;line-height: 50px;">
	        		<div style="float:left;">
	        			支付项目：<a href="/buy_view.do?carForm.id=<s:property value='#orderInfo.carId'/>"><s:property value="orderInfo.carInfo.modelsInfo.name"/></a>
	        		</div>
	        		<div style="float: right;">
	        			<span style="float: left;">应付金额：</span>
	        			<span style="color: red;font-size:30px;float: left;">￥<s:property value="payRecordInfo.price"/></span>
	        		</div>
	        		<br style="clear:both"/>
	        	</div>
	        	<div style="margin-bottom: 14px;border-bottom: 2px solid #CC0808;">点击选择平台前往付款:</div>
	        	<div style="border-bottom: 1px solid #B9B9B9;margin-bottom: 20px;padding-bottom: 20px;">
	        		<div style="float:left;">
	        			<ul class="tab-ul">
	        				<li class="checked" show-id="online">在线支付（第三方支付）</li>
	        				<li  show-id="huikuan">银行汇款</li>
	        			</ul>
	        		</div>
	        		<div class="pay-type" id="online">
	       				<label>
		        			<input type="radio" value="1" checked="checked" style="float: left;margin-top: 10px;margin-right: 5px;"/>
		        			<img src="/portal/img/Chinapay_logo.jpg" style="width:150px;">
		        		</label>
	        		</div>
	        		<div class="pay-type hide" id="huikuan">
	       				<span class="title">收藏对公账号信息</span>
	       				<p class="account">
		       				<span>收款账号：1219 1719 3210 602</span>
		       				<span>公司名称：上海发车信息技术有限公司</span>
		       				<span>开户银行：招商银行股份有限公司上海田林支行</span>
		       				<span>备注信息：(请输入订单号，如银行无法输入备注信息，可省略不填写)</span>
	       				</p>
	       				
	        		</div>
	        		<br style="clear:both"/>
	        	</div>
	        	<button id="payBtn" onclick="payModal();" style="float: right;color: white;background-color: #CC0808;border: none;width: 150px;height: 40px;font-size: 16px;border-radius: 3px;">立即支付</button>
	        	<br style="clear:both;float:right;"/>
        	</div>
        	<div id="J-Pay" class="comm-modal none">
		        <div class="modal">
		            <div class="close j-close">&times;</div>
		            <h1 class="title">支付完成</h1>
		            <div class="body">
		            	请在支付完成后点击"支付完成"
		            </div>
		            <div class="action">
		                <div class="button button-primary j-ok">支付完成</div>
		                <div class="button button-primary j-close">支付遇到问题</div>
		            </div>
		        </div>
		    </div>
        </div>
    </div>

<%@include file="/portal/footer.jsp" %>   
<script src="/portal/js/vendor/jquery.js"></script>
<script src="/portal/js/app.js"></script>
<script type="text/javascript">
$(function(){
	var payCode = $("input[name=payCode]").val();
	$("input[name=payCode]").remove();
	var price = parseFloat($("input[name=price]").val());
	$("input[name=price]").remove();
    var payModal;
    window.payModal = function(btn){
    	if(price <= 0) {
    		alert("订单金额必须大于0");
    	} else {
	    	var div = $(btn).parent(".td.action");
	    	if(div) {
	    		$("#bankNameText").text(div.find("input[name=bankName]").val());
	    		$("#cardNoText").text(div.find("input[name=cardNo]").val());
	    		$("#accountHolderText").text(div.find("input[name=accountHolder]").val());
	    		orderId = div.find("input[name=orderId]").val();
	    	}
	        if (!payModal) {
		        payModal = new Fache.UI.Modal({
		            id: 'J-Pay',
		            ok: function(){
		                payModal.close();
		                location.reload(true);
		            },
		            fail: function() {
		            	alert("失败");
		            }
		        });
	        }
	        payModal.show();
	        window.open("/pay_pay.do?payRecordInfo.payCode="+payCode);
    	}
        
    };
});

$(function() {
	//切换tab
	$("ul.tab-ul li").on("click", function() {
		var me = $(this),
			id = me.attr("show-id");
		$("ul.tab-ul li").removeClass("checked");
		me.addClass("checked");
		$(".pay-type").addClass("hide");
		$("#"+id).removeClass("hide");
		if(id != 'online') {
			$("#payBtn").hide();
		} else {
			$("#payBtn").show();
		}
	});
});
</script>
</body>
</html>
