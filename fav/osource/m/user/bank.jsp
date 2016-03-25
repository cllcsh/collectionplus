<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<head>
    <title>个人资料</title>
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
        <div class="right opt">
			<a id="J-Submit" href="javascript:;">完成</a>
        </div>
        <h1 class="title">银行账户信息</h1>
    </header>s

    <div class="wrap">
        <div class="comm-form mod-user-info">
            <form action="/muserportal_updateBank.do">
                <div class="form-group">
                    <label class="label">开户行：</label>
                    <div class="input"><input id="bankName" name="userForm.bankName" type="text" data-validate="chs" value="<s:property value='userForm.bankName'/>"></div>
                </div>
                <div class="form-group">
                    <label class="label">卡号：</label>
                    <input id="cardNo" name="userForm.cardNo" type="text" data-validate="username" value="<s:property value='userForm.cardNo'/>">
                </div>
                <div class="form-group">
                    <label class="label">开户人：</label>
                    <input id="accountHolder" name="userForm.accountHolder" type="text" data-validate="username" value="<s:property value='userForm.accountHolder'/>">
                </div>
            </form>
        </div>
    </div>


    <script src="/m/bower_components/zepto/zepto.min.js"></script>
<script src="/m/bower_components/zeptotouch/zepto-touch.min.js"></script>
<script src="/m/js/app.js"></script>
<script type="text/javascript" src="/resource/js/geo.js"></script>

    <script>
    // 提交注册表单
    $('#J-Submit').on('tap', function(){
    	$.ajax({
    		type: 'POST',
    		url: '/muserportal_updateBank.do',
    		data: $('form').serializeArray(),
    		success: function(data) {
    			data = $.parseJSON(data);
    			if(data.codeid == 0){
    				document.location.href="/muserportal_bank.do";
    			}
    		},
    		error: function(XMLHttpRequest, textStatus, errorThrown){
    			//alert("获取品牌失败");
    		}
    	});
    })
    </script>

</body>
</html>
