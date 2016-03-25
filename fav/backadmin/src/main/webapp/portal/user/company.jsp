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
                <div class="mod-user-section-title">公司信息</div>
                <div class="mod-user-form">
                    <form id="companyForm" action="/userportal_updateCompany.do" method="post">
                        <div class="form-group">
                            <span class="label">所在地：</span>
                            <div class="input">
								<select class="select" name="userForm.province" id="s1">
								  <option></option>
								</select>
								<select class="select" name="userForm.city" id="s2">
								  <option></option>
								</select>
								<select class="select" name="userForm.area" id="s3">
								  <option></option>
								</select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="label" for="origin">详细地址：</label>
                            <input id="address" name="userForm.address" type="text" data-validate="chs" value="<s:property value='userForm.address'/>">
                        </div>
                        <div class="form-group">
                            <label class="label" for="new">公司名称：</label>
                            <input id="companyName" name="userForm.companyName" type="text" data-validate="username" value="<s:property value='userForm.companyName'/>">
                        </div>
                        <div class="form-group">
                            <label class="label" for="new">工商注册号：</label>
                            <input id="companyName" name="userForm.regNumber" type="text" data-validate="username" value="<s:property value='userForm.regNumber'/>">
                        </div>
                        <div class="form-group">
                            <label class="label" for="new">营业执照：</label>
                            <div class="input">
                                <div class="comm-file-upload">
									<!--
                                    <div class="comm-img camera-line"></div>
                                    <div class="title">上传营业执照</div>-->
									 <img src="<s:property value='userForm.businessPicPath'/>" alt="">
									 <s:hidden id="businessPicPath" name="userForm.businessPicPath"></s:hidden>
                                </div>
                            </div>
                        </div>
						<!--
                        <div class="form-group">
                            <label class="label" for="new">经营品牌：</label>
                            <div class="input">

                                <div id="J-InputItems" class="comm-brands-mgr clearfix">
									<div class="plus item" onclick="modal.show();"><i class="comm-img white-cross"></i></div>
                                    <div class="item">品牌二<i class="comm-img angle-close"></i></div>
									<div class="item"><i class="comm-img white-cross"><s:property value='userForm.brand'/></i></div>
                                </div>
                            </div>
                        </div>-->
                        <div class="form-group">
                            <span class="label"></span>
                            <div class="actions">
								<s:if test="%{userSession.approveFlag != 1}">
									<button type="button" class="button button-primary" onclick="updateCompany();">确认</button>
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

    <div id="J-BrandModal" class="comm-modal none">
        <div class="modal">
            <div class="close j-close">&times;</div>
            <div class="title">选择汽车品牌</div>
            <div class="module-auth-selcarbrands">
                <div class="title">按品牌<span class="comm-text-red">拼音</span>首字母查找</div>
                <div class="words strong">
                    <span class="active">A</span><span>B</span><span>C</span><span>D</span><span>E</span>
                    <span>A</span><span>B</span><span>C</span><span>D</span><span>E</span>
                    <span>A</span><span>B</span><span>C</span><span>D</span><span>E</span>
                    <span>A</span><span>B</span><span>C</span><span>D</span><span>E</span>
                    <span>A</span><span>B</span><span>C</span><span>D</span><span>E</span>
                    <span>A</span><span>B</span><span>C</span><span>D</span><span>E</span>
                </div>
                <div class="brands">
                    <span class="active">劳斯兰斯</span><span>大众</span><span>奥迪</span><span>劳斯兰斯</span><span>大众</span><span>奥迪</span>
                    <span>劳斯兰斯</span><span>大众</span><span>奥迪</span>
                </div>
            </div>
            <div class="button button-primary j-close">确定</div>
        </div>
    </div>

 <%@ include  file="/portal/footer.jsp"%>

<script src="/portal/vendor/jquery.form.js"></script>
<script src="/portal/js/app.js"></script>
<script type="text/javascript" src="/resource/js/geo.js"></script>
<script>
$(document).ready(function(){
	setup();// 查询条件的省市县初始化
	var province = '<s:property value="userForm.province"/>';
	if (province != "") {
		preselect(province);
		var city = '<s:property value="userForm.city"/>';
		if (city != "") {
			$("#s2").val(city);
			change(2);
			var area = '<s:property value="userForm.area"/>';
			if (area != "") {
				$("#s3").val(area);
			}
		}
	}
});

new Fache.UI.Form('#companyForm');
$('.comm-file-upload').click(function(){
    new Fache.UI.ImageUploader({
        url :"/upload.do?maximumSize=10240000&allowedTypes=image/bmp,image/png,image/x-png,image/gif,image/jpg,image/jpeg,image/pjpeg&uploadContentType=pic&path=file&gxfcType=1",
        ok : function(data, close){
        	var dataObj=eval("("+data.responseText+")");
        	// $("#J-avatar").css({"background-image":'url(/upload/' + dataObj.message+')','background-size': '100%'});
        	$('.comm-file-upload').find("img").prop("src", '/upload/' + dataObj.message);
        	close.close();
			$("#businessPicPath").val('/upload/' + dataObj.message);

        }
    });
});


function updateCompany()
{
	//$("#profileForm").submit();
	$.ajax({
		type: "POST",
		url: "/userportal_updateCompany.do",
		data: $("#companyForm").formSerialize(),
		success: function(data){
			alert(data.message);
			if(data.codeid == 0){
				document.location.href="userportal_company.do";
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			//alert("获取品牌失败");
		},
		dataType: "json"
	});

}

    // 选择品牌
    var brands = $('#J-InputItems');
    var modal = new FACHE.UI.Modal({
        id : 'J-BrandModal',
        close : function(obj) {
            var brand = this.modal.find('.brands').find('.active').html();
            brands.append('<div class="item">'+ brand +'<i class="comm-img angle-close"></i></div>');
        }
    });
    // 删除品牌
    brands.find('.item').click(function(){
        var _this = $(this);
        if (_this.hasClass('plus')) {
            return;
        }
        _this.remove();
    });
</script>
</body>
</html>
