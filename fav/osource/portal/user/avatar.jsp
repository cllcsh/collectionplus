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
                <div class="mod-user-section-title">更换头像</div>
                <div class="mod-user-uploadavatar">
                    <!-- <div class="img"><img src="../img/data/car.jpg" style="width: 100%;height: 100%;" alt=""></div> -->
                    <div class="img"><i id="J-avatar" class="plus comm-input-file-wrap"></i></div>
                    <div class="title">请点击按钮选择图片</div>
                    <div class="comm-text-lightgray">仅支持JPG、JPEG、GIF、PNG格式的图片文件，文件大小不能大于2MB</div>
                </div>
            </div>
            <!-- ./用户中心主内容区 -->
        </div>
    </div>
 <%@ include  file="/portal/footer.jsp"%>
<script src="/portal/js/vendor/jquery.js"></script>
<script src="/portal/vendor/jquery.form.js"></script>
<script src="/portal/js/app.js"></script>

<script>
// 上传图片
//$("#J-avatar").css({"background-image":'url(/upload/' + dataObj.message+')','background-size': '100%'});
var imgPath = '<s:property value='userForm.imgPath'/>';
if (imgPath != '')
{
	$("#J-avatar").css({"background-image":'url(' + imgPath +')','background-size': '100%'});
}
$('.mod-user-uploadavatar').click(function(){
    new Fache.UI.ImageUploader({
        url :"/upload.do?maximumSize=2048000&allowedTypes=image/bmp,image/png,image/x-png,image/gif,image/jpg,image/jpeg,image/pjpeg&uploadContentType=pic&path=file&gxfcType=1",
        ok : function(data, close){
        	var dataObj=eval("("+data.responseText+")");
        	$("#J-avatar").css({"background-image":'url(/upload/' + dataObj.message+')','background-size': '100%'});
        	close.close();
			saveImgPath('/upload/' + dataObj.message);

        }
    });
});

function saveImgPath(imgPath){
	alert(imgPath);
	$.ajax({
		type: "POST",
		url: "/userportal_saveAvatar.do",
		data: {"imgPath":imgPath},
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

</script>

</body>
</html>
