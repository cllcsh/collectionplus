/**
* 买车脚本
*/

/**
 * 重新载入界面
 * @param type 类型，页码是否重置
 * @return
 */
function reloadPage() {
	$("#queryForm").submit();
}

function changeOrder(orderObj) {
	var orderName = $("#orderName").val();
	var orderType = $("#orderType").val();
	
	if (orderName == $(orderObj).attr("value")) {
		if (orderType == "desc") {
			$("#orderType").val("asc");
		} else {
			$("#orderType").val("desc");
		}
	} else {
		if ($(orderObj).attr("value") == "price") {
			$("#orderType").val("asc");
		} else {
			$("#orderType").val("desc");
		}
	}
	$("#orderName").val($(orderObj).attr("value"));
	reloadPage();
}

//查询系列
function choseSeries(selector) {
	if (selector == 0) {
		$("#seriesValue").html("全部");
		$("#seriesId").val(0);
		$("#modelsValue").html("全部");
		$("#modelsId").val(0);
		$("#modelyearValue").html("全部");
		$("#modelyearId").val(0);
		$("#outercolorValue").html("全部");
		$("#outercolorId").val(0);
		$("#innercolorValue").html("全部");
		$("#innercolorId").val(0);
		$("#enginesValue").html("全部");
		$("#enginesId").val(0);
		$("#searchPriceValue").html("全部");
		$("#searchPrice").val("");
	} else {
		$.ajax({
			type: "POST",
			url: "/module/gxfc/series_queryByVersion.do",
			data: {"seriesForm.versionId":selector},
			success: function(data){
				$("#seriesOption").html('<div class="item" data-key="0" data-value="全部"></div>');
				if(data.message != undefined && data.message.length>0){
					var optionHtml = '<div class="item" data-key="0" data-value="全部"></div>';
					for(var i=0; i<data.message.length; i++){
						optionHtml += '<div class="item" data-key="' + data.message[i].id + '" data-value="' + data.message[i].name + '"></div>';
					}
					$("#seriesOption").html(optionHtml);
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				alert("获取系列失败");
			},
			dataType: "json"
		});
	}
}

//查询排量
function choseEngines(selector) {
	$.ajax({
		type: "POST",
		url: "/module/gxfc/engines_queryBySeries.do",
		data: {"enginesForm.seriesId":selector},
		success: function(data){
			$("#enginesOption").html('<div class="item" data-key="0" data-value="全部"></div>');
			if(data.message != undefined && data.message.length>0){
				var optionHtml = '<div class="item" data-key="0" data-value="全部"></div>';
				for(var i=0; i<data.message.length; i++){
					optionHtml += '<div class="item" data-key="' + data.message[i].id + '" data-value="' + data.message[i].name + '"></div>';
				}
				$("#enginesOption").html(optionHtml);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("获取车型失败");
		},
		dataType: "json"
	});
}

//查询车型
function choseModels(selector) {
	if (selector == 0) {
		$("#modelsValue").html("全部");
		$("#modelsId").val(0);
		$("#modelyearValue").html("全部");
		$("#modelyearId").val(0);
		$("#outercolorValue").html("全部");
		$("#outercolorId").val(0);
		$("#innercolorValue").html("全部");
		$("#innercolorId").val(0);
		$("#enginesValue").html("全部");
		$("#enginesId").val(0);
		$("#searchPriceValue").html("全部");
		$("#searchPrice").val("");
	} else {
		$.ajax({
			type: "POST",
			url: "/module/gxfc/models_queryBySeries.do",
			data: {"modelsForm.seriesId":selector},
			success: function(data){
				$("#modelsOption").html('<div class="item" data-key="0" data-value="全部"></div>');
				if(data.message != undefined && data.message.length>0){
					var optionHtml = '<div class="item" data-key="0" data-value="全部"></div>';
					for(var i=0; i<data.message.length; i++){
						optionHtml += '<div class="item" data-key="' + data.message[i].id + '" data-value="' + data.message[i].name + '"></div>';
					}
					$("#modelsOption").html(optionHtml);
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				alert("获取车型失败");
			},
			dataType: "json"
		});
	}
}

//查询年款
function choseModelyear(selector) {
	if (selector == 0) {
		$("#modelyearValue").html("全部");
		$("#modelyearId").val(0);
		$("#outercolorValue").html("全部");
		$("#outercolorId").val(0);
		$("#innercolorValue").html("全部");
		$("#innercolorId").val(0);
		$("#searchPriceValue").html("全部");
		$("#searchPrice").val("");
	} else {
		$.ajax({
			type: "POST",
			url: "/module/gxfc/modelyear_queryByModels.do",
			data: {"modelyearForm.modelsId":selector},
			success: function(data){
				$("#modelyearOption").html('<div class="item" data-key="0" data-value="全部"></div>');
				if(data.message != undefined && data.message.length>0){
					var optionHtml = '<div class="item" data-key="0" data-value="全部"></div>';
					for(var i=0; i<data.message.length; i++){
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

//查询外饰
function choseOutercolor(selector) {
	if (selector == 0) {
		$("#outercolorValue").html("全部");
		$("#outercolorId").val(0);
		$("#innercolorValue").html("全部");
		$("#innercolorId").val(0);
		$("#searchPriceValue").html("全部");
		$("#searchPrice").val("");
	} else {
		$.ajax({
			type: "POST",
			url: "/module/gxfc/outercolor_queryByModelyear.do",
			data: {"outercolorForm.modelyearId":selector},
			success: function(data){
				$("#outercolorOption").html('<div class="item" data-key="0" data-value="全部"></div>');
				if(data.message != undefined && data.message.length>0){
					var optionHtml = '<div class="item" data-key="0" data-value="全部"></div>';
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
		data: {"innercolorForm.modelyearId":selector},
		success: function(data){
			$("#innercolorOption").html('<div class="item" data-key="0" data-value="全部"></div>');
			if(data.message != undefined && data.message.length>0){
				var optionHtml = '<div class="item" data-key="0" data-value="全部"></div>';
				for(var i=0; i<data.message.length; i++){
					optionHtml += '<div class="item" data-key="' + data.message[i].id + '" data-value="' + data.message[i].name + '"></div>';
				}
				$("#innercolorOption").html(optionHtml);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("获取内饰颜色失败");
		},
		dataType: "json"
	});
}