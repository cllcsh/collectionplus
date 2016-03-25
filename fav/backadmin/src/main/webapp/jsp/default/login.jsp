<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/jquery-1.4.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/jquery.cookie.js"/></script>
<link href="<%=request.getContextPath()%>/admin/css/charisma-app.css" rel="stylesheet">
<link id="bs-css" href="<%=request.getContextPath()%>/admin/css/bootstrap-eagle.css" rel="stylesheet">
<style type="text/css">
body {
/*        padding-bottom: 10px;*/
    }
.sidebar-nav {
	padding: 9px 0;
}
</style>
<link rel="Shortcut Icon" href="<%=request.getContextPath()%>/resource/themes/default/images/favicon.ico" type="image/x-icon" />
<title>
<s:property value="jsp_head_title"/>
</title>
<script>
	$(document).ready(function(){
		$("input").focusin(function(){
			jQuery(this).addClass('focused');
		}).focusout(function(){
			jQuery(this).removeClass('focused');
		});		
		if( $.cookie("loginUserName")){
		     $("#loginname").val($.cookie("loginUserName"));
		}
/*
		if($("#loginname").val() != null && $("#loginname").val() != "")
		{
			$("#password").focus();
		}
		else
		{
			$("#loginname").focus();
		}*/
		
	});
	
</script>
<script language="javascript"> 
    function loadimage(){
        document.getElementById("auimg").src="<%=request.getContextPath()%>/authimg.gif?"+Math.random();
    }
    function showimage(){
    	var _auimg = document.getElementById("auimg");
    	if(_auimg.style.display == 'none'){
    		_auimg.src="<%=request.getContextPath()%>/authimg.gif?"+Math.random();
    		_auimg.style.display = '';
    	}
    }
    function keyDown() {  
        if(window.event.keyCode == 13){
        	//window.event.keyCode=9;//模拟Tab键
        	if(check()){
        		$.cookie("loginUserName",$("#loginname").val(),{ path: '/', expires: 10 });
	           	document.getElementById("loginForm").submit(); //　当按下"回车键"时执行Submit事件
	       	}         
        }
    }

	function doLogin(){
		if(check()){
			$.cookie("loginUserName",$("#loginname").val(),{ path: '/', expires: 10 });
			window.document.loginForm.submit();
		}
	}

    /* 校验*/
    function check()
    {
		//alert('');
    	//var p1=/^[0-9a-zA-Z]{1,8}$/;
    	//var p2=/^[0-9a-zA-Z@#$%^&*()-_~!{}|\+]{1,15}$/;
    	
    	var p1 = /^[A-Z|a-z|0-9|_]{1,20}$/; //测试用户名
    	var p2 = /^[A-Z|a-z|0-9|_]{6,18}$/; //测试密码

    	var fm = window.document.loginForm;
    	var name = fm.loginname;
    	var password = fm.password;
    	var authcode = fm.authcode;
    	//var authcode = document.all("authcode");
    	
    	if (name.value == "")
    	{
    		alert("用户名不能为空");
    		name.focus();
    		return false;
    	}
    	if (password.value == "")
    	{
    		alert("密码不能为空");
    		password.focus();
    		return false;
    	}
    	return true;
    }

</script>
</head>

<body>

<div class="container-fluid">
            <div class="row-fluid">
                <div class="row-fluid">
                    <%-- <img class="center" src="<%=request.getContextPath()%>/admin/img/logo_1.png"> --%>
					</br></br></br></br></br></br></br></br></br>
                    <div class="well span5 center login-box">
                        <div class="alert alert-info hide">
                            请输入您的用户名与密码
                        </div>
                        <form id="loginForm" name="loginForm" action="login.do" onSubmit="" onKeyDown="javascript:keyDown();" method="POST" class="form-horizontal">
                            	<s:hidden id="errorCode" value="%{errorCode}"></s:hidden>
                                <s:hidden name="tourl" value="%{tourl}"></s:hidden>
                                <s:hidden id="totalNum" value="%{pageList.pages.total}"></s:hidden>
                            <fieldset>
                                <p><b><img alt="Charisma Logo" src="admin/img/icon.png" style="height: 40px ;width: auto ;margin: -8px 0px -12px"></b></p>
								</br>
								<div class="input-prepend" title="用户名" data-rel="tooltip">
                                    <span class="add-on"><i class="icon-user"></i></span><input autofocus class="input-large span10" name="loginForm.loginname" id="loginname" type="text" placeholder="用户名"/>
                                </div>
                                <div class="clearfix"></div>

                                <div class="input-prepend" title="密码" data-rel="tooltip">
                                    <span class="add-on"><i class="icon-lock"></i></span><input class="input-large span10" name="loginForm.password" id="password" type="password"  placeholder="密码"/>
                                </div>
                                <div class="clearfix"></div>

                                <!--                                <div class="input-prepend">
                                                                    <label class="remember" for="remember"><input type="checkbox" id="remember" />Remember me</label>
                                                                </div>-->
                                <div class="clearfix"></div>

                                <p class="center span5">
                                    <button type="button" class="btn btn-primary" onClick="javascript:doLogin();return false;">登录</button>
                                </p>
                            </fieldset>
                        </form>
                    </div><!--/span-->
                </div><!--/row-->
            </div><!--/fluid-row-->

        </div>
<script language="javascript">
	var fm = window.document.form;
    var error = document.getElementById("errorCode").value;
    if(error == 2){
       document.loginForm.loginname.focus();
	   alert('用户名或密码错误！');
     }else if(error == 3){
    	document.loginForm.password.focus();
		 alert('用户名或密码错误！');
     }else if(error == 4) {
        document.loginForm.authcode.focus();
		 alert('用户名或密码错误！');
    } 
</script>
</body>
</html>