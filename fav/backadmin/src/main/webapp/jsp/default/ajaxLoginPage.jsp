<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="/jsp/common.jsp"%>

<style type="text/css">
<!--
  .STYLE3 {font-size: 12px; color: #adc9d9; }
-->
</style>

<style type="text/css">
<!--
body {
 font-family: Arial, Helvetica, sans-serif;
 font-size:12px;
 color:#666666;
 background:#fff;
 text-align:center;

}

* {
 margin:0;
 padding:0;
}

a {
 color:#1E7ACE;
 text-decoration:none; 
}

a:hover {
 color:#000;
 text-decoration:underline;
}
h3 {
 font-size:14px;
 font-weight:bold;
}

pre,p {
 color:#1E7ACE;
 margin:4px;
}
input, select,textarea {
 padding:1px;
 margin:2px;
 font-size:11px;
}
.button{
 padding:1px 10px;
 font-size:12px;
 border:1px #1E7ACE solid;
 background:#D0F0FF;
 margin-right: auto; 
 margin-left: auto;
}
#formwrapper {
 width:400px;
 padding:18px;
 text-align:left;
 border:1px #1E7ACE solid;
}

fieldset {
 padding:10px;
 margin-top:4px;
 border:1px solid #1E7ACE;
 background:#fff;
}

fieldset legend {
 color:#1E7ACE;
 font-weight:bold;
 padding:3px 20px 3px 20px;
 border:1px solid #1E7ACE; 
 background:#fff;
}

fieldset label {
 float:left;
 width:120px;
 text-align:right;
 padding:4px;
 margin:1px;
}

fieldset div {
 clear:left;
 margin-bottom:2px;
}

.enter{ text-align:center;}
.clear {
 clear:both;
}

.cookiechk{
	with:100%;
}

-->
</style>

<script language="javascript"> 

    function loadimage(){
        document.getElementById("auimg").src="<%=path%>/authimg.gif?"+Math.random();
    } 
    function keyDown() {  
        if(window.event.keyCode == 13){
        	//window.event.keyCode=9;//模拟Tab键
            document.getElementById("ajaxLoginForm").submit(); //　当按下"回车键"时执行Submit事件         
            
         }
    }

  //ajax登陆
    function ajaxLogin(){

    	var ajaxLoginForm_loginName = $("#loginname").val();
    	var ajaxLoginForm_password = $("#password").val();
    	var ajaxLoginForm_authcode = $("#authcode").val();
        //alert(ajaxLoginForm_loginName);

    	var params = {'mode':"ajaxJson", 
    			      'loginForm.loginname':ajaxLoginForm_loginName, 
    			      'loginForm.password':ajaxLoginForm_password, 
    			      'loginForm.authcode':ajaxLoginForm_authcode
    			      };
    	$.post("<%=path%>/login_ajaxValidate.do", params, function(json){
    		alert(json.message);
    		if(json.codeid == 0){
    			$("#loginDialog").dialog('close');//loginDialog为打开的对话框Id
    		}
    	},"json");
    }

</script> 
</head>

<body>

<div id="formwrapper">
<h3>登录过期或者权限不够，请重新登录</h3>
<form id="ajaxLoginForm" name="ajaxLoginForm" action="" onkeydown="keyDown();">
 <fieldset>
  <legend>用户登录</legend>
  <div>
   <label for="loginForm.loginname">用户名</label>
   <input type="text" name="loginForm.loginname" id="loginname" maxlength="20"><br />
  </div>
  <div>
   <label for="loginForm.password">密码</label>
   <input type="password" name="loginForm.password" id="password" maxlength="60"><br />
  </div>
  <div>
  	<label for="loginForm.authcode">验证码</label>
    <input type="text" name="loginForm.authcode" id="authcode" size="5" maxlength="4" style="width:50px; height:17px;" >
    <img id="auimg" style="cursor:pointer" onclick="javascript:loadimage();" src="<%=path%>/authimg.gif" width="38" height="17">
  </div>
  <div>
   <label for="login"></label>
   <input name="login" type="button" class="button" value="登录" onclick="ajaxLogin();"/>
   <input name="reset" type="reset" class="button" value="重置" />
  </div> 
 </fieldset>
<br />
</form>
</div>
</body>
</html>