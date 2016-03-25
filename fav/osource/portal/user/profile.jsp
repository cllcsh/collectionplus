<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include  file="/portal/header.jsp"%>

    <link rel="stylesheet" href="/portal/css/style.css">

<script src="/portal/js/vendor/jquery.js"></script>
</head>
<body>
<%@ include  file="/portal/inc.jsp"%>
    <div class="comm-white-ctn">
        <div class="container">
            <%@ include  file="/portal/user/left.jsp"%>
            <!-- 用户中心主内容区 -->
            <div class="mod-user-main">
                <div class="mod-user-section-title">资料设置</div>
                <div class="mod-user-form">
                     <form id="profileForm" action="/userportal_updateProfile.do" method="post">
                        <div class="form-group">
                            <label class="label" for="origin">用户名：</label>
							<input id="loginName" name="userForm.loginName" type="text" data-validate="username" value="<s:property value='userForm.loginName'/>" disabled>
                        </div>
                        <div class="form-group">
                            <label class="label" for="origin">真实姓名：</label>
							<input id="name" name="userForm.name" type="text" data-validate="chs" value="<s:property value='userForm.name'/>">
                        </div>                      
                        <div class="form-group">
                            <label class="label">身份证号：</label>
							<input id="idCard" name="userForm.idCard" type="text" data-validate="idNo" value="<s:property value='userForm.idCard'/>">
                        </div>
                        <div class="form-group">
                            <label class="label">电子邮箱：</label>
							<input id="email" name="userForm.email" type="text" data-validate="email" value="<s:property value='userForm.email'/>">
                        </div>
                        <div class="form-group">
                            <label class="label">地址：</label>
							<input id="address" name="userForm.address" type="text" data-validate="chs" value="<s:property value='userForm.address'/>">
                        </div>
                        <div class="form-group">
                            <label class="label">职务名称：</label>
							<input id="duty" name="userForm.duty" type="text" data-validate="chs" value="<s:property value='userForm.duty'/>">
                        </div>
                        <div class="form-group">
                            <label class="label">身份照片：</label>
                            <div class="input">
                                <div class="row">
                                    <div class="col col-6">
                                        <div class="comm-file-upload" id="dividcardbPicPath">
                                            <!-- <div class="comm-img camera-line"></div>
                                            <div class="title">上传正面照</div> -->
                                            <img src="<s:property value='userForm.idcardbPicPath'/>" alt="">
											<s:hidden id="idcardbPicPath" name="userForm.idcardbPicPath"></s:hidden>
                                        </div>
                                    </div>
                                    <div class="col col-6">
                                        <div class="comm-file-upload" id="dividcardfPicPath">
                                            <!-- <div class="comm-img camera-line"></div>
                                            <div class="title">上传正面照</div>-->
											<img src="<s:property value='userForm.idcardfPicPath'/>" alt="">
											<s:hidden id="idcardfPicPath" name="userForm.idcardfPicPath"></s:hidden>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <span class="label"></span>
                            <div class="actions">
								<s:if test="%{userSession.approveFlag != 1}">
									<button type="button" class="button button-primary" onclick="updateProfile();">确认</button>
									<button class="button button-secondary" onclick="history.back();">取消</button>
								</s:if>
								<s:else>
									<script>
										$('input').attr("disabled",true)//将input元素设置为disabled
									</script>									 
								</s:else>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <!-- ./用户中心主内容区 -->
        </div>
    </div>

 <%@ include  file="/portal/footer.jsp"%>

<script src="/portal/vendor/jquery.form.js"></script>
<script src="/portal/js/app.js"></script>
<script>
// 绑定表单校验
new Fache.UI.Form('#profileForm');
$('#dividcardbPicPath').click(function(){
    new Fache.UI.ImageUploader({
        url :"/upload.do?maximumSize=10240000&allowedTypes=image/bmp,image/png,image/x-png,image/gif,image/jpg,image/jpeg,image/pjpeg&uploadContentType=pic&path=file&gxfcType=1",
        ok : function(data, close){
        	var dataObj=eval("("+data.responseText+")");
        	// $("#J-avatar").css({"background-image":'url(/upload/' + dataObj.message+')','background-size': '100%'});
        	$('#dividcardbPicPath').find("img").prop("src", '/upload/' + dataObj.message);
        	close.close();
			$("#idcardbPicPath").val('/upload/' + dataObj.message);

        }
    });
});
function updateProfile()
{
	//$("#profileForm").submit();
	$.ajax({
		type: "POST",
		url: "/userportal_updateProfile.do",
		data: $("#profileForm").formSerialize(),
		success: function(data){
			alert(data.message);
			if(data.codeid == 0){
				document.location.href="userportal_profile.do";
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			//alert("获取品牌失败");
		},
		dataType: "json"
	});

}
$('#dividcardfPicPath').click(function(){
    new Fache.UI.ImageUploader({
        url :"/upload.do?maximumSize=10240000&allowedTypes=image/bmp,image/png,image/x-png,image/gif,image/jpg,image/jpeg,image/pjpeg&uploadContentType=pic&path=file&gxfcType=1",
        ok : function(data, close){
        	var dataObj=eval("("+data.responseText+")");
        	// $("#J-avatar").css({"background-image":'url(/upload/' + dataObj.message+')','background-size': '100%'});
        	$('#dividcardfPicPath').find("img").prop("src", '/upload/' + dataObj.message);
        	close.close();
			$("#idcardfPicPath").val('/upload/' + dataObj.message);

        }
    });
});

</script>
</body>
</html>
