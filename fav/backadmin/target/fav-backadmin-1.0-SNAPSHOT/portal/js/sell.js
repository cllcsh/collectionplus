var modal = new FACHE.UI.Modal({
    id : 'J-Modal'
});

var modalReview = new FACHE.UI.Modal({
    id : 'J-Modal-Review'
});

var carObj;
var versionObj;
var provinces = new Array("北京市", "天津市", "上海市", "重庆市", "河北省", "山西省", "内蒙古", "辽宁省", "吉林省", "黑龙江省", "江苏省", "浙江省", "安徽省", "福建省", "江西省", "山东省", "河南省", "湖北省", "湖南省", "广东省", "广西", "海南省", "四川省", "贵州省", "云南省", "西藏", "陕西省", "甘肃省", "青海省", "宁夏", "新疆", "香港", "澳门", "台湾省");

$(document).ready(function () {
	choseBrand();
	initProvince();
	
	$(".small-imgs img").on("click", function() {
		$("#carImg").prop("src", $(this).prop("src"));
	});
});

function initProvince() {
	var optionHtml = "<option value='0'>请选择省份</option>";
	for (var i = 0; i < provinces.length; i++) {
		optionHtml += "<option value='" + provinces[i] + "'>" + provinces[i] + "</option>";
	}
	$("#province").html(optionHtml);
}
// 生成标题
function genTitle() {
	var title = "";
	/**
	if ($("#enginesId").val() != "" && $("#enginesId").val() > 0) {
		title += $("#enginesId").find("option:selected").text() + " ";
	}
	**/
	if ($("#brandId").val() != "" && $("#brandId").val() > 0) {
		title += $("#brandId").find("option:selected").text() + " ";
	}
	if ($("#seriesId").val() != "" && $("#seriesId").val() > 0) {
		title += $("#seriesId").find("option:selected").text() + " ";
	}
	/**
	if ($("#modelsId").val() != "" && $("#modelsId").val() > 0) {
		title += $("#modelsId").find("option:selected").text() + " ";
	}
	**/

	if ($("#modelyearId").val() != "" && $("#modelyearId").val() > 0) {
		// title += $("#modelyearId").find("option:selected").text() + " ";
		title += carObj[$("#modelyearId").val()].modelsName + " ";
		title += carObj[$("#modelyearId").val()].name + " ";
		$("#modelsId").val(carObj[$("#modelyearId").val()].modelsId);
	}
	if ($("#versionId").val() != "" && $("#versionId").val() > 0) {
		if (versionObj[$("#versionId").val()].showFlag == 1) {
			title += $("#versionId").find("option:selected").text();
		}
	}
	$("#title").val(title);
}

/**
 * 生成预览信息
 * @return
 */
function genReview() {
	$("#reviewTitle").text($("#title").val());
	if (carObj[$("#modelyearId").val()].picPath2 != null && carObj[$("#modelyearId").val()].picPath2 != "") {
		$("#reviewImg").css("background-image", "url(" + carObj[$("#modelyearId").val()].picPath2 + ")");
	} else {
		$("#reviewImg").css("background-image", "url(/portal/img/nocarpic.png)");
	}
	
	var price = parseFloat($("#price").val());
	
	$("#reviewPrice").text($("#price").val() + "万");
	$("#reviewSource").text($("#source").find("option:selected").text());
	$("#reviewFuel").text($("#fuel").find("option:selected").text());
	$("#reviewEngines").text($("#enginesId").find("option:selected").text());
	$("#reviewColor").text("外饰:" + $("#outercolorId").find("option:selected").text() + " / 内饰:" + $("#innercolorId").find("option:selected").text());
	$("#reviewModels").text(carObj[$("#modelyearId").val()].modelsName);
	// $("#reviewModels").text($("#modelsId").find("option:selected").text());
	$("#reviewDepositRatio").text($("#depositRatio").find("option:selected").text());
	
	var num = parseInt($("#num").val());
	$("#reviewNum").text(num);
	// $("#reviewTotalPrice").text((price * num).toFixed(2));
	$("#reviewTotalPrice").text(number_format((price * num), 4));
}

// 弹出预览
function carReview() {
	if (!check()) {
		return false;
	}
	genReview();
	modalReview.show();
}

// 查询品牌
function choseBrand() {
	var pinyin = $("#pinyin").val();
	$.ajax({
		type: "POST",
		url: "/module/gxfc/brand_queryBypinyin.do",
		data: {"brandForm.pinyin":pinyin},
		success: function(data){
			$("#brandId").html("<option value='0'>请选择品牌</option>");
			if(data.message != undefined && data.message.length>0){
				var optionHtml = "<option value='0'>请选择品牌</option>";
				for(var i=0; i<data.message.length; i++){
					optionHtml += "<option value='" + data.message[i].id + "'>" + data.message[i].name + "</option>";
				}
				$("#brandId").html(optionHtml);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("获取品牌失败");
		},
		dataType: "json"
	});
}

// 查询版本
function choseVersion() {
	var brandId = $("#brandId").val();
	versionObj = [];
	$.ajax({
		type: "POST",
		url: "/module/gxfc/carVersion_queryByBrand.do",
		data: {"carVersionForm.brandId":brandId},
		success: function(data){
			$("#versionId").html("<option value='0'>请选择版本</option>");
			if(data.message != undefined && data.message.length>0){
				var optionHtml = "<option value='0'>请选择版本</option>";
				for(var i=0; i<data.message.length; i++){
					versionObj[data.message[i].id] = data.message[i];
					optionHtml += "<option value='" + data.message[i].id + "'>" + data.message[i].name + "</option>";
				}
				$("#versionId").html(optionHtml);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("获取版本失败");
		},
		dataType: "json"
	});
}

// 判断是否显示车辆手续
function showProcedures() {
	$("#proceduresDiv").css("display", "");
	/*var versionId = $("#versionId").val();
	if (versionId == 0) {
		$("#proceduresDiv").css("display", "none");
	} else {
		if (versionObj[versionId].showFlag == 1) {
			$("#proceduresDiv").css("display", "");
		} else {
			$("#proceduresDiv").css("display", "none");
		}
	}*/
}

// 查询系列
function choseSeries() {
	var versionId = $("#versionId").val();
	$.ajax({
		type: "POST",
		url: "/module/gxfc/series_queryByVersion.do",
		data: {"seriesForm.versionId":versionId},
		success: function(data){
			$("#seriesId").html("<option value='0'>请选择系列</option>");
			if(data.message != undefined && data.message.length>0){
				var optionHtml = "<option value='0'>请选择系列</option>";
				for(var i=0; i<data.message.length; i++){
					optionHtml += "<option value='" + data.message[i].id + "'>" + data.message[i].name + "</option>";
				}
				$("#seriesId").html(optionHtml);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("获取系列失败");
		},
		dataType: "json"
	});
}

// 查询排量
function choseEngines() {
	var seriesId = $("#seriesId").val();
	$.ajax({
		type: "POST",
		url: "/module/gxfc/engines_queryBySeries.do",
		data: {"enginesForm.seriesId":seriesId},
		success: function(data){
			$("#enginesId").html("<option value='0'>请选择排量</option>");
			if(data.message != undefined && data.message.length>0){
				var optionHtml = "<option value='0'>请选择排量</option>";
				for(var i=0; i<data.message.length; i++){
					optionHtml += "<option value='" + data.message[i].id + "'>" + data.message[i].name + "</option>";
				}
				$("#enginesId").html(optionHtml);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("获取车型失败");
		},
		dataType: "json"
	});
}

// 根据系列查年款
function choseModelsModelyear() {
	var seriesId = $("#seriesId").val();
	$("#modelsId").val(0);
	carObj = [];
	$.ajax({
		type: "POST",
		url: "/module/gxfc/modelyear_queryBySeries.do",
		data: {"modelyearForm.seriesId":seriesId},
		success: function(data){
			$("#modelyearId").html("<option value='0'>请选择年款</option>");
			if(data.message != undefined && data.message.length>0){
				var optionHtml = "<option value='0'>请选择年款</option>";
				for(var i=0; i<data.message.length; i++){
					carObj[data.message[i].id] = data.message[i];
					optionHtml += "<option value='" + data.message[i].id + "'>" + data.message[i].showPrice + "万  " + data.message[i].modelsName + "  " + data.message[i].name + "</option>";
				}
				$("#modelyearId").html(optionHtml);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("获取年款失败");
		},
		dataType: "json"
	});
}

// 查询车型
function choseModels() {
	var seriesId = $("#seriesId").val();
	$.ajax({
		type: "POST",
		url: "/module/gxfc/models_queryBySeries.do",
		data: {"modelsForm.seriesId":seriesId},
		success: function(data){
			$("#modelsId").html("<option value='0'>请选择车型</option>");
			if(data.message != undefined && data.message.length>0){
				var optionHtml = "<option value='0'>请选择车型</option>";
				for(var i=0; i<data.message.length; i++){
					optionHtml += "<option value='" + data.message[i].id + "'>" + data.message[i].name + "</option>";
				}
				$("#modelsId").html(optionHtml);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("获取车型失败");
		},
		dataType: "json"
	});
}

// 查询年款
function choseModelyear() {
	var modelsId = $("#modelsId").val();
	carObj = [];
	$.ajax({
		type: "POST",
		url: "/module/gxfc/modelyear_queryByModels.do",
		data: {"modelyearForm.modelsId":modelsId},
		success: function(data){
			$("#modelyearId").html("<option value='0'>请选择年款</option>");
			if(data.message != undefined && data.message.length>0){
				var optionHtml = "<option value='0'>请选择年款</option>";
				for(var i=0; i<data.message.length; i++){
					carObj[data.message[i].id] = data.message[i];
					optionHtml += "<option value='" + data.message[i].id + "'>" + data.message[i].name + "</option>";
				}
				$("#modelyearId").html(optionHtml);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("获取年款失败");
		},
		dataType: "json"
	});
}

function changeCarImg() {
	var modelyearId = $("#modelyearId").val();
	if (carObj[modelyearId].picPath2 == null || carObj[modelyearId].picPath2 == "") {
		$("#carImg").prop("src", "/portal/img/nocarpic.png");
	} else {
		$("#carImg").prop("src", carObj[modelyearId].picPath2);
	}
	if (carObj[modelyearId].picPath1 == null || carObj[modelyearId].picPath1 == "") {
		$("#carImg1").prop("src", "/portal/img/nocarpic.png");
	} else {
		$("#carImg1").prop("src", carObj[modelyearId].picPath1);
	}
	if (carObj[modelyearId].picPath2 == null || carObj[modelyearId].picPath2 == "") {
		$("#carImg2").prop("src", "/portal/img/nocarpic.png");
	} else {
		$("#carImg2").prop("src", carObj[modelyearId].picPath2);
	}
	if (carObj[modelyearId].picPath3 == null || carObj[modelyearId].picPath3 == "") {
		$("#carImg3").prop("src", "/portal/img/nocarpic.png");
	} else {
		$("#carImg3").prop("src", carObj[modelyearId].picPath3);
	}
	if (carObj[modelyearId].picPath4 == null || carObj[modelyearId].picPath4 == "") {
		$("#carImg4").prop("src", "/portal/img/nocarpic.png");
	} else {
		$("#carImg4").prop("src", carObj[modelyearId].picPath4);
	}
	if (carObj[modelyearId].picPath5 == null || carObj[modelyearId].picPath5 == "") {
		$("#carImg5").prop("src", "/portal/img/nocarpic.png");
	} else {
		$("#carImg5").prop("src", carObj[modelyearId].picPath5);
	}
	if (carObj[modelyearId].picPath6 == null || carObj[modelyearId].picPath6 == "") {
		$("#carImg6").prop("src", "/portal/img/nocarpic.png");
	} else {
		$("#carImg6").prop("src", carObj[modelyearId].picPath6);
	}
}

//查询外饰
function choseOutercolor() {
	var modelyearId = $("#modelyearId").val();
	$.ajax({
		type: "POST",
		url: "/module/gxfc/outercolor_queryByModelyear.do",
		data: {"outercolorForm.modelyearId":modelyearId},
		success: function(data){
			$("#outercolorId").html("<option value='0'>请选择外饰颜色</option>");
			if(data.message != undefined && data.message.length>0){
				var optionHtml = "<option value='0'>请选择外饰颜色</option>";
				for(var i=0; i<data.message.length; i++){
					optionHtml += "<option value='" + data.message[i].id + "'>" + data.message[i].name + "</option>";
				}
				$("#outercolorId").html(optionHtml);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("获取外饰颜色失败");
		},
		dataType: "json"
	});
}

//查询内饰
function choseInnercolor() {
	var modelyearId = $("#modelyearId").val();
	$.ajax({
		type: "POST",
		url: "/module/gxfc/innercolor_queryByModelyear.do",
		data: {"innercolorForm.modelyearId":modelyearId},
		success: function(data){
			$("#innercolorId").html("<option value='0'>请选择内饰颜色</option>");
			if(data.message != undefined && data.message.length>0){
				var optionHtml = "<option value='0'>请选择内饰颜色</option>";
				for(var i=0; i<data.message.length; i++){
					optionHtml += "<option value='" + data.message[i].id + "'>" + data.message[i].name + "</option>";
				}
				$("#innercolorId").html(optionHtml);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("获取内饰颜色失败");
		},
		dataType: "json"
	});
}

// 保存车源信息
function saveCar() {
	if (!check()) {
		return false;
	}
	var modelyearId = $("#modelyearId").val();
	var price = parseFloat($("#price").val()) * 10000;
	if ((price > carObj[modelyearId].price) && ((price - carObj[modelyearId].price) > (carObj[modelyearId].price * 0.2))) {
		if(!confirm("该售价高于官方指导价20%，请确认您的售价是否正确!")) {
    		return false;
   		}
		 // alert("该售价高于官方指导价20%，请确认您的售价是否正确!");
	}
	if ((price < carObj[modelyearId].price) && ((carObj[modelyearId].price - price) > (carObj[modelyearId].price * 0.5))) {
		alert("该售价低于官方指导价50%，请确认您的售价是否正确!");
		return false;
	}

	$.ajax({
        cache: true,
        type: "POST",
        url:"/sell_save.do",
        data:$('#sellForm').serialize(),// 你的formid
        async: false,
        error: function(request) {
            alert("网络错误");
        },
        success: function(data) {
            modal.show();
        }
    });
}

function check() {
	if ($("#brandId").val() == 0) {
		alert("请选择品牌");
		return false;
	}
	if ($("#versionId").val() == 0) {
		alert("请选择版本");
		return false;
	}
	if ($("#seriesId").val() == 0) {
		alert("请选择系列");
		return false;
	}
	if ($("#modelsId").val() == 0) {
		alert("请选择车型");
		return false;
	}
	if ($("#modelyearId").val() == 0) {
		alert("请选择年款");
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
	if ($("#depositRatio").val() == 0) {
		alert("请选择首款比例");
		return false;
	}
	if ($("#source").val() == 0) {
		alert("请选择货源");
		return false;
	}

	var procedures = getCheckboxValuesByName("procedures");
	$("#procedures").val(procedures);
	if (versionObj[$("#versionId").val()].showFlag == 1 && procedures == 0) {
		alert("请选择车辆手续");
		return false;
	}
	if ($("#source").val() == 1 && ($("#num").val() == 0 || $("#num").val() == "")) {
		alert("请填写出售数量");
		return false;
	}
	if ($("#source").val() == 2 && $("#num").val() == "") {
		$("#num").val(0);
	}
	if ($("#price").val() == 0 || $("#price").val() == "") {
		alert("请填写出售价格");
		return false;
	}
	return true;
}

// 从预览保存
function saveReviewCar() {
	modalReview.close();
	saveCar();
}

/**
 * 根据name属性取得所有选中的checkbox的值用逗号分隔
 * @param name
 * @return
 */
function getCheckboxValuesByName(name){
	var str = 0;
    jQuery(":checkbox[name='" + name + "']:checked").each(function(){
    	str += parseInt(jQuery(this).val());
    });
    return str;
}