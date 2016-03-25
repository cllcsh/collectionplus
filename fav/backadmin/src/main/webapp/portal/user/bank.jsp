<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include  file="/portal/header.jsp"%>

    <link rel="stylesheet" href="/portal/css/style.css">

</head>
<script src="/portal/js/vendor/jquery.js"></script>
<script src="/portal/vendor/jquery.form.js"></script>
<script src="/portal/js/app.js"></script>
<script type="text/javascript" src="/resource/js/geo.js"></script>
<body>
<%@ include  file="/portal/inc.jsp"%>
    <div class="comm-white-ctn">
        <div class="container">
            <%@ include  file="/portal/user/left.jsp"%>
            <!-- 用户中心主内容区 -->
            <div class="mod-user-main">
                <div class="mod-user-section-title">银行账户信息</div>
                <div class="mod-user-form">
                    <form id="bankForm" action="/userportal_updateBank.do" method="post">
                        <div class="form-group">
                            <label class="label" for="new">开户行：</label>
                            <input id="bankName" name="userForm.bankName" type="text" data-validate="username" value="<s:property value='userForm.bankName'/>">
                        </div>
                        <div class="form-group">
                            <label class="label" for="origin">卡号：</label>
                            <input id="cardNo" name="userForm.cardNo" type="text" data-validate="chs" value="<s:property value='userForm.cardNo'/>">
                        </div>
                        <div class="form-group">
                            <label class="label" for="new">开户人：</label>
                            <input id="accountHolder" name="userForm.accountHolder" type="text" data-validate="chs" value="<s:property value='userForm.accountHolder'/>">
                        </div>
                        <div class="form-group">
                            <span class="label"></span>
                            <div class="actions">
								<button type="button" class="button button-primary" onclick="updateBank();">确认</button>
								<button class="button button-secondary" onclick="history.back();">取消</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <!-- ./用户中心主内容区 -->
        </div>
    </div>


 <%@ include  file="/portal/footer.jsp"%>


<script>
function updateBank()
{
	//$("#profileForm").submit();
	$.ajax({
		type: "POST",
		url: "/userportal_updateBank.do",
		data: $("#bankForm").formSerialize(),
		success: function(data){
			alert(data.message);
			if(data.codeid == 0){
				document.location.href="userportal_bank.do";
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			//alert("获取品牌失败");
		},
		dataType: "json"
	});

}
</script>
</body>
</html>
