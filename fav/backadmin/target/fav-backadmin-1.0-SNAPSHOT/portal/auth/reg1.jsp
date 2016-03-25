<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include  file="/portal/header.jsp"%>

    <link rel="stylesheet" href="/portal/css/style.css">

</head>
<body>
    <div class="comm-header0">
	    <div class="comm-header-ctn">
	        <div class="container clearfix">
	            <a href="/home.do" class="logo left">LOGO</a>
	            <div class="right">
	                <a href="/home.do"><i class="comm-img gray-home"></i> 返回首页</a>
	            </div>
	        </div>
	    </div>
	</div>

    <div class="comm-white-ctn">
        <div class="module-auth-reg-process">
            <div class="container clearfix">
                <div class="item active">
                    <div class="inner">1.设置登录名</div>
                </div>
                <div class="item text-center">
                    <div class="inner">2.填写账户信息</div>
                </div>
                <div class="item text-right">
                    <div class="inner">3.等待审核</div>
                </div>
            </div>
        </div>

        <div class="module-auth-reg-form">
            <div class="container">
                <form id="regForm" action="/home_regStepTwo.do" method="post">
                    <div class="form-group">
                        <span class="placeholder">填写手机号</span>
                        <input id="mobile" name="mobile" data-validate="mobile" type="text" value="">
                        <span class="require">(*必填)</span>
                    </div>
                    <div class="form-group">
                        <span class="placeholder">输入短信验证码</span>
                        <input id="authCode" data-validate="phoneCode" type="text" value="">
                        <div id="btnCode" class="button button-primary btn-small" onclick="sendAuthCode()">获取验证码</div>
                    </div>
                    <div class="form-group">
                        <span class="placeholder">输入账户密码</span>
                        <input id="password" data-validate="password" name="password" type="password" value="">
                        <span class="require">(*必填)</span>
                    </div>
                    <div class="form-group">
                        <span class="placeholder">再次确认密码</span>
                        <input id="crmPassword" data-validate="password" type="password" value="">
                        <span class="require">(*必填)</span>
                    </div>
                    <div class="text-center mb20">
                        <span class="checkbox">
                            <label for="agree"><a href="/home_agree.do" target="_blank">我同意并遵守《收藏平台服务协议》</a></label>
                            <input id="agree" type="checkbox">
                        </span>
                    </div>
                    <div class="form-group">
                        <button type="button" class="button button-primary submit" onclick="validCode();">接受协议并注册</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <%@ include  file="/portal/footer.jsp"%>

<script src="/portal/js/vendor/jquery.js"></script>
<script src="/portal/js/app.js"></script>
<script type="text/javascript">
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
		url: "/home_validPhone.do",
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
	var crmPassword = $("#crmPassword").val();
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
	if (crmPassword == "") {
		alert("请输入您的确认密码");
		return false;
	}
	if (password != password) {
		alert("您两次输入的密码不一致，请重新输入");
		return false;
	}

	if (!$("#agree").prop("checked")) {
		alert("请您先同意接受用户协议");
		return false;
	}

	$.ajax({
		type: "POST",
		url: "/home_validCode.do",
		data: {"mobile":mobile, "authCode":authCode},
		success: function(data){
			if (data.codeid == 0) {
				$("#regForm").submit();
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

new Fache.UI.Form('#regForm');
</script>
</body>
</html>
