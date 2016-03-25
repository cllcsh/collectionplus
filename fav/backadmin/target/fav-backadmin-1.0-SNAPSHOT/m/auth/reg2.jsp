<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>收藏 填写资料</title>
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
            <a id="J-Submit" href="javascript:userReg();">完成</a>
        </div>
        <h1 class="title">填写资料</h1>
    </header>

    <div class="wrap">
        <div class="comm-form">
            <form id="regForm" action="/mhome_regStepThree.do" method="post">
             	<s:hidden id="loginName" name="userForm.loginName"></s:hidden>
                <s:hidden name="userForm.formerPassword"></s:hidden>
                <div class="split"></div>
                <div class="form-group">
                    <label class="label">设置密码：</label>
                    <div class="input"><input id="password" name="userForm.password" data-validate="password" type="password"  value="" placeholder="请再次确认输入"></div>
                </div>
                <div class="form-group">
                    <label class="label">确认密码：</label>
                    <div class="input"><input id="crmPassword" name="userForm.crmPassword" data-validate="password" type="password"  value="" placeholder="请再次确认输入"></div>
                </div>
                <div class="form-tip">
                    请认真填写并保证如下资料的真实性。
                </div>
                <div class="form-group">
                    <label class="label">真实姓名：</label>
                    <div class="input"><input id="name" name="userForm.name" data-validate="chs" type="text" value="" placeholder="请输入真实姓名"></div>
                </div>
                <div class="form-group">
                    <label class="label">身份证号：</label>
                    <div class="input"><input id="idCard" name="userForm.idCard" data-validate="idNo" type="text" value="" placeholder="请输入身份证号"></div>
                </div>
                <div class="form-group">
                    <label class="label">电子邮箱：</label>
                    <div class="input"><input id="email" name="userForm.email" data-validate="email" type="text" value="" placeholder="请输入邮箱地址"></div>
                </div>
			    <div class="form-group">
                        <label for="">公司所在地区：</label>
                        <div class="row">
                            <div class="col col-4">
                                <span class="select">
                                  <select name="userForm.province" id="s1">
									  <option></option>
								  </select>
                                </span>
                            </div>
                            <div class="col col-4">
                                <span class="select">
								  <select name="userForm.city" id="s2">
									  <option></option>
								  </select>
                                </span>
                            </div>
                            <div class="col col-4">
                                <span class="select">
								  <select name="userForm.area" id="s3">
									  <option></option>
								  </select>
                                </span>
                            </div>
                        </div>
                </div>
                <div class="form-group">
                    <div class="label">详细地址：</div>
                    <div class="input"><input id="address" name="userForm.address" data-validate="required" type="text" value="" placeholder="请输入详细地址"></div>
                </div>
                <div class="form-group">
                    <label class="label">公司名称：</label>
                    <div class="input"><input id="companyName" name="userForm.companyName" data-validate="required" type="text" value="" placeholder="请输入公司名称"></div>
                </div>
                <div class="form-group">
                    <label class="label">职务名称：</label>
                    <div class="input"><input id="duty" name="userForm.duty" type="text" data-validate="chs" type="text" value="" placeholder="请输入职务名称"></div>
                </div>
                <div class="form-group">
                    <label class="label">工商注册号：</label>
                    <div class="input"><input id="regNumber" name="userForm.regNumber" data-validate="number" type="text" value="" placeholder="请输入工商注册号"></div>
                </div>
                <div class="form-group file-upload-group">
                    <div class="row">
                        <div class="col col-4">
                            <div class="file-upload" id="dividcardbPicPath">
                                <div class="img"></div>
                                <div class="title">上传身份证正面照</div>
                            </div>
							<s:hidden id="idcardbPicPath" name="userForm.idcardbPicPath"></s:hidden>

                        </div>
                        <div class="col col-4">
                            <div class="file-upload" id="dividcardfPicPath">
                                <div class="img"></div>
                                <div class="title">上传身份证反面照</div>
                            </div>
							<s:hidden id="idcardfPicPath" name="userForm.idcardfPicPath"></s:hidden>
                        </div>
                        <div class="col col-4">
                            <div class="file-upload" id="divbusinessPicPath">
                                <div class="img"></div>
                                <div class="title">上传营业执照</div>
                            </div>
							<s:hidden id="businessPicPath" name="userForm.businessPicPath"></s:hidden>
                        </div>
                    </div>
                </div>
                    <div class="form-group">
                        <label for="">经营汽车品牌：</label>
                        <div id="J-InputItems" class="comm-brands-mgr">
                            <div class="plus item" onclick="modal.show();"><i class="comm-img white-cross"></i></div>
                        </div>
                        <s:hidden id="formBrand" name="userForm.brand"></s:hidden>
                    </div>
                    <div class="form-group">
                        <!--<button type="button" class="button button-primary" onclick="userReg();">提交</button>
						-->	
					</div>
            </form>
        </div>
    </div>

 <div id="J-BrandModal" class="comm-modal none">
        <div class="modal">
            <div class="close j-close">&times;</div>
            <div class="title">选择汽车品牌</div>
            <div class="module-auth-selcarbrands">
                <div class="title">按品牌<span class="comm-text-red">拼音</span>首字母查找</div>
                <div id="pinyin" class="words strong">
                    <span class="active">A</span><span>B</span><span>C</span><span>D</span><span>E</span>
                    <span>F</span><span>G</span><span>H</span><span>I</span><span>J</span>
                    <span>K</span><span>L</span><span>M</span><span>N</span><span>O</span>
                    <span>P</span><span>Q</span><span>R</span><span>S</span><span>T</span>
                    <span>U</span><span>V</span><span>W</span><span>X</span><span>Y</span>
                    <span>Z</span>
                </div>
                <div id="brand" class="brands">
                    <span>劳斯兰斯</span><span>大众</span><span>奥迪</span><span>劳斯兰斯</span><span>大众</span><span>奥迪</span>
                    <span>劳斯兰斯</span><span>大众</span><span>奥迪</span>
                </div>
            </div>
            <div class="button button-primary j-close">确定</div>
        </div>
    </div>
<script src="/portal/js/vendor/jquery.js"></script>
<script src="/m/bower_components/zepto/zepto.min.js"></script>
<script src="/m/bower_components/zeptotouch/zepto-touch.min.js"></script>
<script src="/m/js/app.js"></script>
<script type="text/javascript" src="/resource/js/geo.js"></script>

<script>
new Fache.UI.Form('regForm');

    // 提交注册表单
$('#J-Submit').on(Fache.mainEvent, function () {
   $('regForm').submit();
});

$(document).ready(function(){
	setup();// 查询条件的省市县初始化

	$("#pinyin").find("span").on("click", function() {
		$("#pinyin").find("span").removeClass("active");
		$(this).addClass("active");
		choseBrand($(this).html());
	});
	choseBrand("A");
	
	if ($("#loginName").val() == "") {
		alert("注册出错，请返回重新注册。");
	}
});

    // 上传图片逻辑
$('#dividcardbPicPath').on(Fache.mainEvent, function(){
   new Fache.UI.ImageUploader({
       url: '/upload.do?maximumSize=2048000&allowedTypes=image/bmp,image/png,image/x-png,image/gif,image/jpg,image/jpeg,image/pjpeg&uploadContentType=pic&path=file&gxfcType=1',
       ok: function(data, close){
			var dataObj=eval("("+data.responseText+")");
        	$("#dividcardbPicPath").find('.img').css({"background-image":'url(/upload/' + dataObj.message+')', 'background-size' : 'cover'});
        	close.close();
			$("#idcardbPicPath").val('/upload/' + dataObj.message);
//			$("#dividcardbPicPath").find("div").each(function () {
//				$(this).css("display", "none");
//			});

       },
       error: function(xhr) {
            console.debug(xhr);
       }
   });
});

$('#dividcardfPicPath').on(Fache.mainEvent, function(){
   new Fache.UI.ImageUploader({
       url: '/upload.do?maximumSize=2048000&allowedTypes=image/bmp,image/png,image/x-png,image/gif,image/jpg,image/jpeg,image/pjpeg&uploadContentType=pic&path=file&gxfcType=1',
       ok: function(data, close){
			var dataObj=eval("("+data.responseText+")");
        	$("#dividcardfPicPath").find('.img').css({"background-image":'url(/upload/' + dataObj.message+')', 'background-size' : 'cover'});
        	close.close();
			$("#idcardfPicPath").val('/upload/' + dataObj.message);
//			$("#dividcardbPicPath").find("div").each(function () {
//				$(this).css("display", "none");
//			});

       },
       error: function(xhr) {
            console.debug(xhr);
       }
   });
});


$('#divbusinessPicPath').on(Fache.mainEvent, function(){
   new Fache.UI.ImageUploader({
       url: '/upload.do?maximumSize=2048000&allowedTypes=image/bmp,image/png,image/x-png,image/gif,image/jpg,image/jpeg,image/pjpeg&uploadContentType=pic&path=file&gxfcType=1',
       ok: function(data, close){
			var dataObj=eval("("+data.responseText+")");
        	$("#divbusinessPicPath").find('.img').css({"background-image":'url(/upload/' + dataObj.message+')', 'background-size' : 'cover'});
        	close.close();
			$("#businessPicPath").val('/upload/' + dataObj.message);
//			$("#dividcardbPicPath").find("div").each(function () {
//				$(this).css("display", "none");
//			});

       },
       error: function(xhr) {
            console.debug(xhr);
       }
   });
});
// 选择品牌
var brands = $('#J-InputItems');
 var modal = new Fache.UI.Modal({
    id : 'J-BrandModal',
    close : function(obj) {
		this.modal.find('.brands').find('.active').each(function() {
			var brand = $(this).html();
			var formBrand = $("#formBrand").val();
			if (formBrand.indexOf(brand) < 0) {
				$("#formBrand").val(formBrand + brand + ",");
		        brands.append('<div class="item">'+ brand +'<i class="comm-img angle-close"></i></div>');
			}
		});
		brands.find('.item').click(function(){
			_removeBrand($(this));
		});
    }
});
// 删除品牌
function _removeBrand(_this) {
    if (_this.hasClass('plus')) {
        return;
    }
    _this.remove();
}

// 绑定表单校验
//new Fache.UI.Form('#regForm');
/**
$(document).ready(function(){
	setup();// 查询条件的省市县初始化

	$("#pinyin").find("span").on("click", function() {
		$("#pinyin").find("span").removeClass("active");
		$(this).addClass("active");
		choseBrand($(this).html());
	});
	choseBrand("A");
});
**/

function choseBrand(pinyin) {
	$("#brand").html("");
	$.ajax({
		type: "POST",
		url: "/module/gxfc/brand_queryBypinyin.do",
		data: {"brandForm.pinyin":pinyin},
		success: function(data){
			if(data.message != undefined && data.message.length>0){
				var optionHtml = "";
				for(var i=0; i<data.message.length; i++){
					optionHtml += "<span>" + data.message[i].name + "</span>";
				}
				$("#brand").html(optionHtml);
				$("#brand").find("span").on("click", function() {
					selectBrand($(this));
				});
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("获取品牌失败");
		},
		dataType: "json"
	});
}

function selectBrand(brandObj) {
	if (brandObj.hasClass("active")) {
		brandObj.removeClass("active");
	} else {
		brandObj.addClass("active");
	}
}

function userReg() {
	if ($("#loginName").val() == "") {
		alert("注册出错，请返回重新注册。");
		return false;
	}
	
	var password = $("#password").val();
	var crmPassword = $("#crmPassword").val();
	if (password == "") {
		alert("请输入您的密码");
		return false;
	}
	if (password.length < 6) {
		alert("请输入超过6位数的密码");
		return false;
	}
	if (crmPassword == "") {
		alert("请输入您的确认密码");
		return false;
	}
	if (password != crmPassword) {
		alert("您两次输入的密码不一致，请重新输入");
		return false;
	}
	
	var cnReg = new RegExp('^[^u4e00-u9fa5]+$', 'g');
	var emailReg = new RegExp(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/);
	var idNoReg = new RegExp('(^\\d{15}$)|(^\\d{17}([0-9]|X)$)', 'g');
	var numReg = new RegExp('^[0-9]*$', 'g');
	
	var name = $.trim($("#name").val());
	var idCard = $.trim($("#idCard").val());
	var email = $.trim($("#email").val());
	var s1 = $("#s1").val();
	var s2 = $("#s2").val();
	var s3 = $("#s3").val();
	var address = $.trim($("#address").val());
	var companyName = $.trim($("#companyName").val());
	var duty = $.trim($("#duty").val());
	var regNumber = $.trim($("#regNumber").val());
	var businessPicPath = $.trim($("#businessPicPath").val());
	var idcardbPicPath = $.trim($("#idcardbPicPath").val());
	var idcardfPicPath = $.trim($("#idcardfPicPath").val());
	 
	if (name == "") {
		alert("请输入您的真实姓名");
		return false;
	}
	if (!cnReg.test(name)) {
		alert("真实姓名必须是中文");
		return false;
	}
	if(idCard == "") {
		alert("请输入您的身份证号");
		return false;
	}
	if(!idNoReg.test(idCard)) {
		alert("身份证号格式不正确");
		return false;
	}
	if (email == "") {
		alert("请输入您的电子邮箱 ");
		return false;
	}
	if (!emailReg.test(email)) {
		alert("请输入正确的电子邮箱 ");
		return false;
	}
	if (s1 == "" || s2 == "" || s3 == "") {
		alert("请选择正确的地区");
		return false;
	}
	if (address == "") {
		alert("请输入详细地址");
		return false;
	}
// 	if (!cnReg.test(address)) {
// 		alert("详细地址只能是中文" + address);
// 		return false;
// 	}
	if (companyName == "") {
		alert("请输入公司名称");
		return false;
	}
// 	if (!cnReg.test(companyName)) {
// 		alert("公司名称只能是中文 ");
// 		return false;
// 	}
	if (duty == "") {
		alert("请输入职务名称");
		return false;
	}
// 	if (!cnReg.test(duty)) {
// 		alert("职务名称只能是中文");
// 		return false;
// 	}
	if (regNumber == "") {
		alert("请输入工商注册号");
		return false;
	}
// 	if (!numReg.test(regNumber)) {
// 		alert("工商注册号只能是数字");
// 		return false;
// 	}
	if (idcardbPicPath == "") {
		alert("请上传身份证正面照");
		return false;
	}
	if (idcardfPicPath == "") {
		alert("请上传身份证反面照");
		return false;
	}
	if (businessPicPath == "") {
		alert("请上传营业执照");
		return false;
	}
	
	var brand = $("#formBrand").val();
	brand = brand.substring(0, brand.length - 1);
	
	//验证身份证和工商号是否已经被注册
	$.ajax({
		url: "/mhome_checkIDCardOrRegNumberExist.do",
		data: {"userForm.idCard":$("#idCard").val, "userForm.regNumber":$("#regNumber").val()},
		success: function(data) {
			var dataObj=eval("("+data+")");
			if(dataObj.codeid != '0')
				alert(dataObj.message);
			else 
				$("#regForm").submit();
		},
		failure: function(data) {
			console.log(data);
		}
	});
}
</script>
</body>
</html>
