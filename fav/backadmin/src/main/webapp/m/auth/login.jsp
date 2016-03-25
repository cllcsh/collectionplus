<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>收藏 登录</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-status-bar-style" content="black"><meta name="baidu-site-verification" content="hWGw6qbfv7" />
    <link rel="stylesheet" href="/m/css/style.css">

    <style>html{height: 100%;}</style>

</head>
<body class="mod-auth-login-page">
    <div class="wrap">
        <div class="logo"></div>
        <div class="mod-auth-form">
            <form action="">
                <div class="form-group">
                    <div class="input-wrap">
                        <i class="ico-user"></i>
                        <input id="loginName" class="input" type="text" placeholder="账号">
                    </div>
                </div>
                <div class="form-group">
                    <div class="input-wrap">
                        <i class="ico-lock"></i>
                        <input id="password" class="input" type="password" placeholder="密码">
                    </div>
                </div>
                <div class="form-group action text-center">
                    <button type="button" class="button button-primary" onclick="login();">登录</button>
                </div>
                <div class="text-bottom">
                    <div class="left"><i class="ico-help"></i> <a href="/mhome_help.do" class="comm-text-gray">问题帮助</a></div>
                    <div class="right">您还不是用户？<a href="/mhome_reg.do">立即注册</a></div>
                </div>
            </form>
        </div>
    </div>
<script src="/portal/js/vendor/jquery.js"></script>
<script src="/m/js/app.js"></script>
<script type="text/javascript">
function login() {
	var loginName = $("#loginName").val();
	var password = $("#password").val();
	$.ajax({
		type: "POST",
		url: "/login_ajaxValidate.do",
		data: {"loginForm.loginname":loginName, "loginForm.password":password},
		success: function(data){			
			if (data.codeid == 0) {
				document.location.href = "/mhome.do";
			}else{
				alert(data.message);
			}

		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("保存失败");
		},
		dataType: "json"
	});
}
</script>
</body>
</html>
