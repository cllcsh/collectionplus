<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>绑定手机号</title>
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
    <h1 class="title">绑定手机号</h1>
</header>


    <div class="wrap">
        <div class="mod-user-form">
            <form action="">
                <div class="form-group">
                    <div class="input">
                        <i class="ico-phone"></i>
                        <input id="mobile" name="mobile" data-validate="mobile" type="text" placeholder="输入手机号">
                    </div>
                </div>
                <div class="form-group">
                    <div class="input">
                        <i class="ico-mail"></i>
                        <input id="authCode" data-validate="phoneCode" type="text" placeholder="输入验证码">
                        <div class="button" onclick="sendAuthCode()">获取验证码</div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="input">
                        <i class="ico-lock"></i>
                        <input type="text" id="password" data-validate="password" name="password" placeholder="输入密码">
                    </div>
                </div>
                <div class="form-group action">
                    <button class="button" type="submit" onclick="validCode();">完成</button>
                </div>
            </form>
        </div>
<script src="/portal/js/vendor/jquery.js"></script>
<script src="/m/bower_components/zepto/zepto.min.js"></script>
<script src="/m/bower_components/zeptotouch/zepto-touch.min.js"></script>
<script src="/m/js/app.js"></script>
<script type="text/javascript" src="/resource/js/geo.js"></script>
<script>
var intv;
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
		url: "/userportal_validPhone.do",
		data: {"mobile":mobile},
		success: function(data){
			alert(data.message);
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
	var password = $("#password").val();
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
	if (password == "") {
		alert("请输入您的密码");
		return false;
	}
	if (password.length < 6) {
		alert("请输入超过6位数的密码");
		return false;
	}

	$.ajax({
		type: "POST",
		url: "/userportal_validCode.do",
		data: {"mobile":mobile, "authCode":authCode, "password": password},
		success: function(data){
			if (data.codeid == 0) {
				alert(data.message);
			} else {
				alert(data.message);
				var _this = $('#btnCode');
				clearInterval(intv);
				_this.addClass('button-primary').removeClass('button-gray').html('获取验证码');
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			//alert("保存失败");
		},
		dataType: "json"
	});
}
</script>
</body>
</html>
