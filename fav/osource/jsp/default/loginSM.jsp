<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="/jsp/common.jsp"%>
<link href="<%=path%>/resource/themes/<%=themeName%>/login.css" rel="stylesheet" type="text/css" />
<title><s:property value="jsp_head_title"/></title>
<script>
	$(document).ready(function(){
		$("input").focusin(function(){
			jQuery(this).addClass('focused');
		}).focusout(function(){
			jQuery(this).removeClass('focused');
		});

	});
	
</script>
<script language="javascript"> 
    
    function keyDown() {  
        if(window.event.keyCode == 13){
        	//window.event.keyCode=9;//模拟Tab键      	
	           	document.getElementById("loginForm").submit(); //　当按下"回车键"时执行Submit事件       	      
        }
    }

	function doLogin(){	
			window.document.loginForm.submit();		
	}

</script> 
</head>

<body>
<div class="login">
  <form id="loginForm" name="loginForm" action="login_smlogin.do" onsubmit="" onkeydown="javascript:keyDown();" method="POST">
	<span class="loginForm">		
		<span>短信验证码：<input type="smpassword" name="loginForm.smpassword" id="smpassword" maxlength="6"></span>
		<span style="padding-top:0"><a href="javascript:;" onclick="doLogin();return false;"></a></span>
	</span>
  </form>
</div>
<div class="message"><span class="err" id="error_span"><s:actionerror cssClass="STYLE3"/></span></div>
<div class="footer">中国电信股份有限公司绥化分公司© 2010
<s:if test="#version != null"><BR>当前版本：<s:property value="#version"/></s:if>
<p align="center"><a class="STYLE3" href="http://www.vnet.cn/about/culture02.htm" target="_blank">[增值电信业务经营许可证 A2.B1.B2 - 20090001]</a></p></div>
</div>

</body>
</html>