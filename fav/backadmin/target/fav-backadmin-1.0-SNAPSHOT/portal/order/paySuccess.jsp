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
        	<div style="margin: 0 auto;width: 500px;">
				<h1 style="text-indent:50px;font-size:1.5rem;background-image:url(/portal/img/order.png);background-repeat:no-repeat;">订单支付成功！</h1>
				<h2 style="font-size:1.2rem">订单号：c<span style="padding-left: 50px;"></span>应付金额：<strong><s:property value="payRecordInfo.price"/></strong>元</h2>
				<h2 style="font-size:1.2rem">
					付款项目：<s:property value="orderInfo.carInfo.modelsInfo.name"/>
					<s:if test="payRecordInfo.type==0">预付款</s:if>
					<s:if test="payRecordInfo.type==1">尾款</s:if>
				</h2>
				<h3 style="font-size:1rem">您可以：<a href="/orderprotal_view.do?viewType=buy&orderForm.id=<s:property value="orderInfo.id"/>">查看订单详情</a></h3>
			</div>
        </div>
    </div>

<%@include file="/portal/footer.jsp" %>   
<script src="/portal/js/vendor/jquery.js"></script>
<script src="/portal/js/app.js"></script>
<script type="text/javascript">
$(function(){
    var payModal;
    window.payModal = function(btn){
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
	                alert("成功");
	            },
	            fail: function() {
	            	alert("失败");
	            }
	        });
        }
        payModal.show();
        window.open("/pay_pay.do");
        
    };
});
</script>
</body>
</html>
