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
            <%@ include  file="/portal/user/left.jsp"%>

            <!-- 用户中心主内容区 -->
            <div class="mod-user-main">
                <div class="mod-user-section-title">更换手机</div>
                <div class="mod-user-form">
					<form id="phoneForm" action="" method="post">
                        <div class="form-group">
                            <label class="label" for="origin">输入手机号</label>
                            <input id="mobile" name="mobile" data-validate="mobile" type="text" value="">
                        </div>
                        <div class="form-group">
                            <label class="label" for="new">填写验证码</label>
                            <input id="authCode" data-validate="phoneCode" type="text" value="">
                             <div id="btnCode" class="button button-primary input-action" onclick="sendAuthCode()">获取验证码</div>
                        </div>
                        <div class="form-group">
                            <label class="label" for="newpwd">输入密码</label>
                            <input id="password" data-validate="password" name="password" type="password" value="">
                        </div>
                        <div class="form-group">
                            <span class="label"></span>
                            <div class="actions">
                                <button type="button" class="button button-primary" onclick="validCode();">完成</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <!-- ./用户中心主内容区 -->
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

new Fache.UI.Form('#regForm');
</script>
</body>
</html>

