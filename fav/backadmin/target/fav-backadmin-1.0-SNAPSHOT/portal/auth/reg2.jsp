<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include  file="/portal/header.jsp"%>

    <link rel="stylesheet" href="/portal/css/style.css">
    <style type="text/css">
    	.words span {
    		cursor: pointer;
    	}
    	.brands span {
    		cursor: pointer;
    	}
    </style>

</head>
<body>
    <div class="comm-header0">
	    <div class="comm-header-ctn">
	        <div class="container clearfix">
	            <a href="/home.do" class="logo left">LOGO</a>
	            <div class="right">
	                <a href="/home.do"><i class="comm-img gray-home"></i> 返回首页</a>
	            </div>
	        </div>
	    </div>
	</div>

    <div class="comm-white-ctn">
        <div class="module-auth-reg-process">
            <div class="container clearfix">
                <div class="item">
                    <div class="inner">1.设置登录名</div>
                </div>
                <div class="item active text-center">
                    <div class="inner">2.填写账户信息</div>
                </div>
                <div class="item text-right">
                    <div class="inner">3.等待审核</div>
                </div>
            </div>
        </div>

        <div class="module-auth-reg-form">
            <div class="container">
                <form id="regForm" action="/home_regStepThree.do" method="post">
                	<s:hidden name="userForm.loginName"></s:hidden>
                	<s:hidden name="userForm.password"></s:hidden>
                	<s:hidden name="userForm.formerPassword"></s:hidden>
                    <div class="form-group">
                        <label for="">真实姓名：</label>
                        <input id="name" name="userForm.name" data-validate="chs" type="text" value="">
                        <span class="require">(*必填)</span>
                    </div>
                    <div class="form-group">
                        <label for="">身份证号：</label>
                        <input id="idCard" name="userForm.idCard" data-validate="idNo" type="text" value="">
                        <span class="require">(*必填)</span>
                    </div>
                    <div class="form-group">
                        <label for="">电子邮箱：</label>
                        <input id="email" name="userForm.email" data-validate="email" type="text" value="">
                        <span class="require">(*必填)</span>
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
                        <div class="placeholder">详细地址</div>
                        <input id="address" name="userForm.address" data-validate="required" type="text" value="">
                        <span class="require">(*必填)</span>
                    </div>
                    <div class="form-group">
                        <label for="">公司名称：</label>
                        <input id="companyName" name="userForm.companyName" data-validate="chs" type="text" value="">
                        <span class="require">(*必填)</span>
                    </div>
                    <div class="form-group">
                        <label for="">职务名称：</label>
                        <input id="duty" name="userForm.duty" type="text" data-validate="chs" value="">
                        <span class="require">(*必填)</span>
                    </div>
                    <div class="form-group">
                        <label for="">工商注册号：</label>
                        <input id="regNumber" name="userForm.regNumber" data-validate="number" type="text" value="">
                        <span class="require">(*必填)</span>
                    </div>
                    <div class="form-group file-upload-group">
                        <div class="row">
                            <div class="col col-6 pr10">
                                <div class="file-upload" id="dividcardbPicPath">
                                    <div class="comm-img camera-line"></div>
                                    <div class="title">上传身份证正面照</div>
                                </div>
                                <s:hidden id="idcardbPicPath" name="userForm.idcardbPicPath"></s:hidden>
                            </div>
                            <div class="col col-6 pl10">
                                <div class="file-upload" id="dividcardfPicPath">
                                    <div class="comm-img camera-line"></div>
                                    <div class="title">上传身份证反面照</div>
                                </div>
                                <s:hidden id="idcardfPicPath" name="userForm.idcardfPicPath"></s:hidden>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col col-12">
                                <div class="file-upload file-upload-block" id="divbusinessPicPath">
                                    <div class="comm-img camera-line"></div>
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
                        <button type="button" class="button button-primary submit" onclick="userReg();">确认资料并提交</button>
                    </div>
                </form>
            </div>
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

    <%@ include  file="/portal/footer.jsp"%>

<script src="/portal/js/vendor/jquery.js"></script>
<script src="/portal/vendor/jquery.form.js"></script>
<script src="/portal/js/app.js"></script>
<script type="text/javascript" src="/resource/js/geo.js"></script>

<script type="text/javascript">
// 选择品牌
var brands = $('#J-InputItems');
var modal = new FACHE.UI.Modal({
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
new Fache.UI.Form('#regForm');

$(document).ready(function(){
	setup();// 查询条件的省市县初始化

	$("#pinyin").find("span").on("click", function() {
		$("#pinyin").find("span").removeClass("active");
		$(this).addClass("active");
		choseBrand($(this).html());
	});
	choseBrand("A");
});

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
		type: "POST",
		url: "/home_checkIDCardOrRegNumberExist.do",
		data: {"userForm.idCard":idCard, "userForm.regNumber":regNumber},
		success: function(data){
			if (data.codeid == 0) {
				$("#regForm").submit();
			} else {
				alert(data.message);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			console.log(errorThrown);
		},
		dataType: "json"
	});
}

$('#dividcardbPicPath').click(function(){
    new Fache.UI.ImageUploader({
        url :"/upload.do?maximumSize=10240000&allowedTypes=image/bmp,image/png,image/x-png,image/gif,image/jpg,image/jpeg,image/pjpeg&uploadContentType=pic&path=file&gxfcType=1",
        ok : function(data, close){
        	var dataObj=eval("("+data.responseText+")");
        	$("#dividcardbPicPath").css({"background-image":'url(/upload/' + dataObj.message+')'});
        	close.close();
			$("#idcardbPicPath").val('/upload/' + dataObj.message);
			$("#dividcardbPicPath").find("div").each(function () {
				$(this).css("display", "none");
			});
        }
    });
});
$('#dividcardfPicPath').click(function(){
    new Fache.UI.ImageUploader({
        url :"/upload.do?maximumSize=10240000&allowedTypes=image/bmp,image/png,image/x-png,image/gif,image/jpg,image/jpeg,image/pjpeg&uploadContentType=pic&path=file&gxfcType=1",
        ok : function(data, close){
        	var dataObj=eval("("+data.responseText+")");
        	$("#dividcardfPicPath").css({"background-image":'url(/upload/' + dataObj.message+')'});
        	close.close();
			$("#idcardfPicPath").val('/upload/' + dataObj.message);
			$("#dividcardfPicPath").find("div").each(function () {
				$(this).css("display", "none");
			});
        }
    });
});
$('#divbusinessPicPath').click(function(){
    new Fache.UI.ImageUploader({
        url :"/upload.do?maximumSize=10240000&allowedTypes=image/bmp,image/png,image/x-png,image/gif,image/jpg,image/jpeg,image/pjpeg&uploadContentType=pic&path=file&gxfcType=1",
        ok : function(data, close){
        	var dataObj=eval("("+data.responseText+")");
        	$("#divbusinessPicPath").css({"background-image":'url(/upload/' + dataObj.message+')'});
        	close.close();
			$("#businessPicPath").val('/upload/' + dataObj.message);
			$("#divbusinessPicPath").find("div").each(function () {
				$(this).css("display", "none");
			});
        }
    });
});
</script>
</body>
</html>
