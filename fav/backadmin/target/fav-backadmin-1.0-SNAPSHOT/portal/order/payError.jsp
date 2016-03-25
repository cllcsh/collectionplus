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
        	<h3>付款遇到问题了？先看看是不是由于下面的原因。</h3>
        	<ul style="margin-left: -35px;">
        		<li>
        			<h5><span style="display:block;color: black">要求开通网上银行？</span></h5>
        			<span style="display:block">建议选择银联在线支付付款，如果是信用卡还可选择快捷支付，再选择对应银行支付。</span>
        		</li>
        		<li>
        			<h5><span style="display:block;color: black">所需支付金额超过了银行支付限额？</span></h5>
        			<span style="display:block">建议登录网上银行提高上限额度，即可轻松支付。</span>
        		</li>
        		<li>
        			<h5><span style="display:block;color: black">收不到银行的短信验证码？</span></h5>
        			<span style="display:block">建议重新获取短信验证码，如果还是收不到短信，直接打各银行的客服电话获取短信验证码。</span>
        		</li>
        		<li>
        			<h5><span style="display:block;color: black">网银页面显示错误或者空白？</span></h5>
        			<span style="display:block">建议更换到IE浏览器进行支付操作，或使用银联在线支付或支付宝付款。</span>
        		</li>
        		<li>
        			<h5><span style="display:block;color: black">这里没有您遇到的问题？</span></h5>
        			<span style="display:block">请您及时我们的官网客服(400-966-8300)为您解决。</span>
        		</li>
        	</ul>
        </div>
    </div>

<%@include file="/portal/footer.jsp" %>   
<script src="/portal/js/vendor/jquery.js"></script>
<script src="/portal/js/app.js"></script>
<script type="text/javascript">
</script>
</body>
</html>
