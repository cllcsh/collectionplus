var carObj;
var provinces = new Array("北京市", "天津市", "上海市", "重庆市", "河北省", "山西省", "内蒙古", "辽宁省", "吉林省", "黑龙江省", "江苏省", "浙江省", "安徽省", "福建省", "江西省", "山东省", "河南省", "湖北省", "湖南省", "广东省", "广西", "海南省", "四川省", "贵州省", "云南省", "西藏", "陕西省", "甘肃省", "青海省", "宁夏", "新疆", "香港", "澳门", "台湾省");

$(document).ready(function () {
	initProvince();
});

function initProvince() {
	var optionHtml = '<div class="item" data-key="0" data-value="选择"></div>';
	for (var i = 0; i < provinces.length; i++) {
		optionHtml += '<div class="item" data-key="' + provinces[i] + '" data-value="' + provinces[i] + '"></div>';
	}
	$("#provinceDiv").html(optionHtml);
}
// 生成标题
function genTitle() {
	var title = "";
	if ($("#modelyearId").val() != "" && $("#modelyearId").val() > 0) {
		title += $("#modelyearName").text() + " ";
	}
	title += $("#brandName").val() + " ";
	title += $("#seriesName").val() + " ";
	if ($("#modelsId").val() != "" && $("#modelsId").val() > 0) {
		title += $("#modelsName").text() + " ";
	}
	if ($("#enginesId").val() != "" && $("#enginesId").val() > 0) {
		title += $("#enginesName").text() + " ";
	}
	if ($("#showFlag").val() == 1) {
		title += $("#versionName").val();
	}
	$("#title").val(title);
}

/**
 * 生成预览信息
 * @return
 */
function genReview() {
	$("#reviewTitle").text($("#modelyearId").find("option:selected").text() + $("#brandId").find("option:selected").text());
	$("#reviewImg").css("background-image", "url(" + carObj[$("#modelyearId").val()].picPath2 + ")");
	
	var price = parseInt($("#price").val());
	
	$("#reviewPrice").text((price / 10000).toFixed(2) + "万");
	$("#reviewSource").text($("#source").find("option:selected").text());
	$("#reviewFuel").text($("#fuel").find("option:selected").text());
	$("#reviewEngines").text($("#enginesId").find("option:selected").text());
	$("#reviewColor").text("外饰:" + $("#outercolorId").find("option:selected").text() + " / 内饰:" + $("#innercolorId").find("option:selected").text());
	$("#reviewModels").text($("#modelsId").find("option:selected").text());
	$("#reviewDepositRatio").text($("#depositRatio").find("option:selected").text());
	
	var num = parseInt($("#num").val());
	$("#reviewDepositRatio").text(num);
	$("#reviewTotalPrice").text((price * num / 10000).toFixed(2));
}

// 弹出预览
function carReview() {
	genReview();
	modalReview.show();
}

// 查询年款
function choseModelyear(selector) {
	carObj = [];
	if (selector.length == 0) {
		$("#modelyearName").html("选择");
		$("#modelyearId").val(0);
		$("#outercolorValue").html("选择");
		$("#outercolorId").val(0);
		$("#innercolorValue").html("选择");
		$("#innercolorId").val(0);
	} else {
		$.ajax({
			type: "POST",
			url: "/module/gxfc/modelyear_queryByModels.do",
			data: {"modelyearForm.modelsId":selector[0].key},
			success: function(data){
				$("#modelyearOption").html('<div class="item" data-key="0" data-value="选择"></div>');
				if(data.message != undefined && data.message.length>0){
					var optionHtml = '<div class="item" data-key="0" data-value="选择"></div>';
					for(var i=0; i<data.message.length; i++){
						carObj[data.message[i].id] = data.message[i];
						optionHtml += '<div class="item" data-key="' + data.message[i].id + '" data-value="' + data.message[i].name + '"></div>';
					}
					$("#modelyearOption").html(optionHtml);
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				alert("获取年款失败");
			},
			dataType: "json"
		});
	}
}

function changeCarImg(selector) {
	var picHtml = '';
	if (selector.length == 0) {
		picHtml = '<div class="swiper-slide"><img src="' + $("#seriesPic").val() + '" alt=""></div>';
	} else {
		picHtml = '<div class="swiper-slide"><img src="' + carObj[selector[0].key].picPath1 + '" alt=""></div>';
		picHtml += '<div class="swiper-slide"><img src="' + carObj[selector[0].key].picPath2 + '" alt=""></div>';
		picHtml += '<div class="swiper-slide"><img src="' + carObj[selector[0].key].picPath3 + '" alt=""></div>';
		picHtml += '<div class="swiper-slide"><img src="' + carObj[selector[0].key].picPath4 + '" alt=""></div>';
		picHtml += '<div class="swiper-slide"><img src="' + carObj[selector[0].key].picPath5 + '" alt=""></div>';
		picHtml += '<div class="swiper-slide"><img src="' + carObj[selector[0].key].picPath6 + '" alt=""></div>';
	}
	$("#carImg").html(picHtml);
	mySwiper = new Swiper ('.swiper-container', {
	    loop: true,
	    pagination: '.swiper-pagination',
	    autoplay: 3000,
	    loop: true
	});
}

//查询外饰
function choseOutercolor(selector) {
	if (selector.length == 0) {
		$("#outercolorValue").html("选择");
		$("#outercolorId").val(0);
		$("#innercolorValue").html("选择");
		$("#innercolorId").val(0);
	} else {
		$.ajax({
			type: "POST",
			url: "/module/gxfc/outercolor_queryByModelyear.do",
			data: {"outercolorForm.modelyearId":selector[0].key},
			success: function(data){
				$("#outercolorOption").html('<div class="item" data-key="0" data-value="选择"></div>');
				if(data.message != undefined && data.message.length>0){
					var optionHtml = '<div class="item" data-key="0" data-value="选择"></div>';
					for(var i=0; i<data.message.length; i++){
						optionHtml += '<div class="item" data-key="' + data.message[i].id + '" data-value="' + data.message[i].name + '"></div>';
					}
					$("#outercolorOption").html(optionHtml);
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				alert("获取外饰颜色失败");
			},
			dataType: "json"
		});
	}
}

//查询内饰
function choseInnercolor(selector) {
	$.ajax({
		type: "POST",
		url: "/module/gxfc/innercolor_queryByModelyear.do",
		data: {"innercolorForm.modelyearId":selector[0].key},
		success: function(data){
			$("#innercolorId").html("<option value='0'>请选择内饰颜色</option>");
			if(data.message != undefined && data.message.length>0){
				$("#innercolorOption").html('<div class="item" data-key="0" data-value="选择"></div>');
				if(data.message != undefined && data.message.length>0){
					var optionHtml = '<div class="item" data-key="0" data-value="选择"></div>';
					for(var i=0; i<data.message.length; i++){
						optionHtml += '<div class="item" data-key="' + data.message[i].id + '" data-value="' + data.message[i].name + '"></div>';
					}
					$("#innercolorOption").html(optionHtml);
				}
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("获取内饰颜色失败");
		},
		dataType: "json"
	});
}

// 计算车辆手续的值
function culProcedures(selector) {
	if (selector.length == 0) {
		$("#procedures").val("");
	} else {
		var procedures = 0;
		for (var i = 0; i < selector.length; i++) {
			procedures += selector[i].key;
		}
		$("#procedures").val(procedures);
	}
}

// 保存车源信息
function saveCar() {
	if (!check()) {
		return false;
	}
	var modelyearId = $("#modelyearId").val();
	var price = parseFloat($("#price").val()) * 10000;
	if ((price > carObj[modelyearId].price) && ((price - carObj[modelyearId].price) > (carObj[modelyearId].price * 0.2))) {
		alert("该售价高于官方指导价20%，请确认您的售价是否正确!");
		return false;
	}
	if ((price < carObj[modelyearId].price) && ((carObj[modelyearId].price - price) > (carObj[modelyearId].price * 0.5))) {
		alert("该售价低于官方指导价50%，请确认您的售价是否正确!");
		return false;
	}

	$.ajax({
        cache: true,
        type: "POST",
        url:"/msell_save.do",
        data:$('#sellForm').serialize(),// 你的formid
        async: false,
        error: function(request) {
            alert("网络错误");
        },
        success: function(data) {
            document.location.href = "/morderprotal_my.do";
        }
    });
}

function check() {
	if ($("#modelsId").val() == 0) {
		alert("请选择车型");
		return false;
	}
	if ($("#modelyearId").val() == 0) {
		alert("请选择年款");
		return false;
	}
	if ($("#fuel").val() == "") {
		alert("请选择燃油");
		return false;
	}
	if ($("#enginesId").val() == 0) {
		alert("请选择排量");
		return false;
	}
	if ($("#outercolorId").val() == 0) {
		alert("请选择外饰");
		return false;
	}
	if ($("#innercolorId").val() == 0) {
		alert("请选择内饰");
		return false;
	}
	if ($("#province").val() == 0) {
		alert("请选择省份");
		return false;
	}
	if ($("#showFlag").val() == 1 && $("#procedures").val() == 0) {
		alert("请选择车辆手续");
		return false;
	}
	if ($("#depositRatio").val() == 0) {
		alert("请选择首款比例");
		return false;
	}
	if ($("#source").val() == 0) {
		alert("请选择货源");
		return false;
	}
	if ($("#price").val() == 0 || $("#price").val() == "") {
		alert("请填写出售价格");
		return false;
	}
	if ($("#source").val() == 1 && ($("#num").val() == 0 || $("#num").val() == "")) {
		alert("请填写出售数量");
		return false;
	}
	if ($("#source").val() == 2 && $("#num").val() == "") {
		$("#num").val(0);
	}
	return true;
}

// 从预览保存
function saveReviewCar() {
	modalReview.close();
	saveCar();
}