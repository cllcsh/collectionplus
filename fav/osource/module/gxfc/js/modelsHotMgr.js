/**
 * 版本管理页面脚本
 * @author pachong
 * @create 2015-07-26
 * @file modelsHotMgr.js
 * @since v0.1
 */

 //打开编辑品牌信息页面
function edit_modelsHot(id){
	location.href = "modelsHot_edit.do?id=" + id;
}

//打开查看品牌信息页面
function view_modelsHot(id){
	location.href("modelsHot_view.do?id="+id);
}

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
				
				// 编辑时
				var _brandId = $("#_brandId").val();
				if (_brandId != '0' && _brandId != '') {
					$("#brandId").val(_brandId);
					choseVersion();
				}
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("获取品牌失败");
		},
		dataType: "json"
	});
}

function choseVersion() {
	var brandId = $("#brandId").val();
	$.ajax({
		type: "POST",
		url: "/module/gxfc/carVersion_queryByBrand.do",
		data: {"carVersionForm.brandId":brandId},
		success: function(data){
			$("#versionId").html("<option value='0'>请选择版本</option>");
			if(data.message != undefined && data.message.length>0){
				var optionHtml = "<option value='0'>请选择版本</option>";
				for(var i=0; i<data.message.length; i++){
					optionHtml += "<option value='" + data.message[i].id + "'>" + data.message[i].name + "</option>";
				}
				$("#versionId").html(optionHtml);
				
				// 编辑时
				var _versionId = $("#_versionId").val();
				if (_versionId != '0' && _versionId != '') {
					$("#versionId").val(_versionId);
					choseSeries();
				}
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("获取版本失败");
		},
		dataType: "json"
	});
}

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
				
				// 编辑时
				var _seriesId = $("#_seriesId").val();
				if (_seriesId != '0' && _seriesId != '') {
					$("#seriesId").val(_seriesId);
					choseModels();
				}
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("获取系列失败");
		},
		dataType: "json"
	});
}

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
				
				// 编辑时
				var _modelsId = $("#_modelsId").val();
				if (_modelsId != '0' && _modelsId != '') {
					$("#modelsId").val(_modelsId);
					choseModelyear();
				}
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("获取车型失败");
		},
		dataType: "json"
	});
}

function choseModelyear() {
	var modelsId = $("#modelsId").val();
	$.ajax({
		type: "POST",
		url: "/module/gxfc/modelyear_queryByModels.do",
		data: {"modelyearForm.modelsId":modelsId},
		success: function(data){
			$("#modelyearId").html("<option value='0'>请选择年款</option>");
			if(data.message != undefined && data.message.length>0){
				var optionHtml = "<option value='0'>请选择年款</option>";
				for(var i=0; i<data.message.length; i++){
					optionHtml += "<option value='" + data.message[i].id + "'>" + data.message[i].name + "</option>";
				}
				$("#modelyearId").html(optionHtml);
				
				// 编辑时
				var _modelyearId = $("#_modelyearId").val();
				if (_modelyearId != '0' && _modelyearId != '') {
					$("#modelyearId").val(_modelyearId);
				}
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("获取年款失败");
		},
		dataType: "json"
	});
}