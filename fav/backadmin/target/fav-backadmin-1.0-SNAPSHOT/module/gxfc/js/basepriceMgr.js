/**
 * 系列管理页面脚本
 * @author pachong
 * @create 2015-07-26
 * @file innercolorMgr.js
 * @since v0.1
 */

 //打开编辑系列信息页面
function edit_innercolor(id){
	location.href = "baseprice_edit.do?id=" + id;
}

//打开查看系列信息页面
function view_innercolor(id){
	location.href("baseprice_view.do?id="+id);
}

$(document).ready(function() {
	$("#upload").click(function(){
		if (importIPlan()) {
			$('.uploadForm').ajaxSubmit({
                success: function(data) {
					alert(data.message);
					if(data.codeid == 0){
						document.location.reload();
					}
                },
                dataType: "json"
            });
		}
    });
	
	$("#uploadDialog").dialog({
		bgiframe: true,
		autoOpen: false,
		width:500,
		height:200,
		modal: true,
		close: function() {
			$(this).html('');
		}
	});
});

function openImportDialog() {
	$("#uploadDialog").dialog('open');
}

function importIPlan(){
    var pathName = $("#uploadFile").val();
    var suffix = pathName.substring(pathName.lastIndexOf('.')+1, pathName.length);
    if(suffix != "xlsx"){
        alert("请选择xlsx后缀的excel文件！");
        // document.getElementById("usermanage").reset();//表单内容设置为空
        return false;
    }
    
    return true;
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
				choseModelyear();
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
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("获取年款失败");
		},
		dataType: "json"
	});
}