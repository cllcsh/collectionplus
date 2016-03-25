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
                <div class="mod-user-section-title">修改密码</div>
                <div class="mod-user-form">
					<form id="passwordForm">
                        <div class="form-group">
                            <label class="label" for="origin">输入原密码：</label>
                            <input id="oldpassword" data-validate="password" name="oldpassword" type="password" value="">
                        </div>
                        <div class="form-group">
                            <label class="label" for="new">输入新密码：</label>
                            <input id="password" data-validate="password" name="password" type="password" value="">
                        </div>
                        <div class="form-group">
                            <label class="label" for="repeat">重复新密码：</label>
                            <input id="newpassword" data-validate="password" name="newpassword" type="password" value="">
                        </div>
                        <div class="form-group">
                            <span class="label"></span>
                            <div class="actions">
                                <button type="button" class="button button-primary" onclick="validCode();">确定</button>
                                <button class="button button-secondary">取消</button>
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
function validCode() {
	
	var oldpassword = $("#oldpassword").val();
	var password = $("#password").val();
	var newpassword = $("#newpassword").val();
	if (oldpassword == "") {
		alert("请输入您的旧密码");
		return false;
	}
	if (oldpassword.length < 6) {
		alert("请输入超过6位数的旧密码");
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
	if (newpassword == "") {
		alert("请输入您的确认密码");
		return false;
	}
	if (password != newpassword) {
		alert("您两次输入的密码不一致，请重新输入");
		return false;
	}

	$.ajax({
		type: "POST",
		url: "/module/system/user_modifyPassword.do",
		data: {"oldpassword":oldpassword, "password":password},
		success: function(data){
			if (data.codeid == 0) {
				//$("#passwordForm").submit();
				alert(data.message);
			} else {
				alert(data.message);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("保存失败");
		},
		dataType: "json"
	});
}

//new Fache.UI.Form('#passwordForm');
</script>
</body>
</html>

