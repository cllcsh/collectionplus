<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.struts2.components.Include"%>
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


    <div class="module-auth-login-banner">
        <div class="container">
            <div class="module-auth-login-form">
                <form action="">
                    <div class="form-group">
                        <div class="input-wrap">
                            <i class="comm-img user-line"></i>
                            <input id="loginName" class="input" type="text" placeholder="请输入手机号">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-wrap">
                            <i class="comm-img lock-line"></i>
                            <input id="password" class="input" type="password" placeholder="请输入密码">
                        </div>
                    </div>
                    <div class="form-group text-center">
                        <span class="checkbox">
                            <label for="autologin">下次自动登录</label>
                            <input id="autologin" type="checkbox">
                        </span>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;
                        <a href="home_password.do" class="comm-text-blue">忘记密码</a>
                    </div>
                    <div class="form-group">
                        <button type="button" class="button button-primary" onclick="login();">登录</button>
                    </div>
                    <div class="text-bottom clearfix">
                        <div class="left"><i class="comm-img double-msg"></i> <a href="help.html" class="comm-text-gray">问题帮助</a></div>
                        <div class="right">您还不是用户？<a href="/home_reg.do">立即注册</a></div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <%@ include  file="/portal/footer.jsp"%>
</div>
<script src="/portal/js/vendor/jquery.js"></script>
<script src="/portal/js/app.js"></script>
<script type="text/javascript">
function login() {
	var loginName = $("#loginName").val();
	var password = $("#password").val();
	$.ajax({
		type: "POST",
		url: "/login_ajaxValidate.do",
		data: {"loginForm.loginname":loginName, "loginForm.password":password, "isAutoLogin":$("#autologin").prop("checked")},
		success: function(data){
			if (data.codeid == 0) {
				document.location.href = "/home.do";
			} else {
				alert(data.message);
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
