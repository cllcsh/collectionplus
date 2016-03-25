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
                <div class="item">
                    <div class="inner">1.设置登录名</div>
                </div>
                <div class="item text-center">
                    <div class="inner">2.填写账户信息</div>
                </div>
                <div class="item active text-right">
                    <div class="inner">3.等待审核</div>
                </div>
            </div>
        </div>

        <div class="comm-result">
            <div class="img"><i class="comm-img big-tick"></i></div>
            <div class="title">注册并提交成功！</div>
            <div class="body">请等待我们审核资料之后与您取得联系，感谢您选择收藏。</div>
            <div class="action"><a href="/" class="button button-primary button-radius button-large">去首页逛逛</a></div>
        </div>
    </div>
    <%@ include  file="/portal/footer.jsp"%>

<script src="/portal/js/vendor/jquery.js"></script>
<script src="/portal/js/app.js"></script>

</body>
</html>
