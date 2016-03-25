/**
 * 系列管理页面脚本
 * @author pachong
 * @create 2015-07-26
 * @file seriesMgr.js
 * @since v0.1
 */

 //打开编辑系列信息页面
function edit_series(id){
	location.href = "series_edit.do?id=" + id;
}

//打开查看系列信息页面
function view_series(id){
	location.href = "series_view.do?id=" + id;
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
				}
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("获取版本失败");
		},
		dataType: "json"
	});
}

//添加图片
function up_file(){
	//document.openDialog("uploadDialog").html("正在加载页面...").load(_contextPath+"/upload_init.do");
	$("#uploadDialog").dialog('open').html("正在加载页面...").load(_contextPath + "/upload_uploadInit.do?mode=ajax&uploadContentType=pic&path=file");
}

//清空标题图片输入框内容
function clear_upfile(){
	$('#picPath').val('');
}

function callbackpic(fileName){
	$('#picPath').val(fileName);
	$("#uploadDialog").dialog('close');
}

//定义弹出对话框
$(function() {
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

//定义弹出对话框
$(function() {
	$("#viewDialog").dialog({
		bgiframe: true,
		autoOpen: false,
		width:500,
		height:500,
		modal: true,
		close: function() {
			$(this).html('');
		}
	});
});