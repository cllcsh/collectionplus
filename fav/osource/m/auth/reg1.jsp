<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.struts2.components.Include"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>收藏 注册</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="baidu-site-verification" content="hWGw6qbfv7" />
    <link rel="stylesheet" href="/m/css/style.css">

</head>
<body class="white">
    <header class="comm-header">
    <div class="left opt">
        <a href="javascript:window.history.go(-1);">返回</a>
    </div>
    <h1 class="title">注册</h1>
</header>

    <div class="wrap">
        <div class="mod-auth-form mod-auth-reg-form">
               <form id="regForm" action="/mhome_regStepTwo.do" method="post">
                <div class="form-group">
                    <div class="input-wrap">
                        <i class="ico-phone"></i>
                        <input id="mobile" name="mobile" data-validate="mobile" class="input" type="text" placeholder="请输入手机号">
                    </div>
                </div>
                <div class="form-group">
                    <div class="input-wrap">
                        <i class="ico-mail"></i>
                        <input id="authCode" data-validate="phoneCode" class="input" type="text" placeholder="请输入验证码">
						<div class="button" id="J-GetPhoneCode" data-ref="#mobile">获取验证码</div>
                    </div>
                </div>
                <div class="form-group">
                    <span class="checkbox">
                        <label for="agree">我已阅读并同意 <a href="#" onclick="validCode();">《发车无忧APP使用协议》</a></label>
                        <input id="agree" type="checkbox" checked>
                    </span>
                </div>
                <div class="form-group action text-center">
					<button type="button" class="button button-primary" onclick="validCode();">提交</button>
                </div>
            </form>
        </div>
    </div>
<script src="/portal/js/vendor/jquery.js"></script>
<script src="/m/bower_components/zepto/zepto.min.js"></script>
<script src="/m/bower_components/zeptotouch/zepto-touch.min.js"></script>
<script src="/m/js/app.js"></script>

<script type="text/javascript">
var intv;
Fache.UI.checkbox();

    // 绑定发送手机验证码
Fache.PhoneCode({
        url: '/home_validPhone.do',
        btnId: 'J-GetPhoneCode',
        countDown: 100,
        name: 'mobile',
		success: function(data) {
			if (parseInt(data.codeid) !== 0) {
				alert(data.message);
				return false;
			}
			return true;
		}
});

function sendAuthCode() {
	var mobile = $.trim($("#mobile").val());
	if (mobile == "") {
		alert("请输入您的手机号码");
		return false;
	}
	if (mobile.length != 11) {
		alert("您的手机号码输入不正确，请重新输入");
		return false;
	}

    var count = 100;
    var _this = $('#btnCode');

    if (_this.hasClass('button-gray')) return false;

	$.ajax({
		type: "POST",
		url: "/home_validPhone.do",
		data: {"mobile":mobile},
		success: function(data){
			if (data.codeid == 0)
			{
				_this.addClass('button-gray').removeClass('button-primary');
				_this.html(count + '秒后');
				intv = setInterval(function(){
					_this.html(--count + '秒后');
					if (count === 0) {
						clearInterval(intv);
						_this.addClass('button-primary').removeClass('button-gray').html('获取验证码');
					}
				}, 1000);
			}
            
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			//alert("保存失败");
		},
		dataType: "json"
	});
}

function validCode() {

	var mobile = $.trim($("#mobile").val());
	var authCode = $.trim($("#authCode").val());
	if (mobile == "") {
		alert("请输入您的手机号码");
		return false;
	}
	if (mobile.length != 11) {
		alert("您的手机号码输入不正确，请重新输入");
		return false;
	}
	if (authCode == "") {
		alert("请输入您收到的短信验证码");
		return false;
	}
	if (authCode.length != 6) {
		alert("您的短信验证码输入不正确，请重新输入");
		return false;
	}

	if (!$("#agree").prop("checked")) {
		alert("请您先同意接受用户协议");
		return false;
	}

	$.ajax({
		type: "POST",
		url: "/mhome_validCode.do",
		data: {"mobile":mobile, "authCode":authCode},
		success: function(data){			
			if (data.codeid == 0) {
				$("#regForm").submit();
			} else {
				alert(data.message);
				//var _this = $('#btnCode');
				//clearInterval(intv);
				//_this.addClass('button-primary').removeClass('button-gray').html('获取验证码');
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			//alert("保存失败");
		},
		dataType: "json"
	});
}
Fache.UI.checkbox();
</script>
</body>
</html>
