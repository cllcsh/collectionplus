/**
 * 版本管理页面脚本
 * @author pachong
 * @create 2015-07-26
 * @file carVersionMgr.js
 * @since v0.1
 */

 //打开编辑品牌信息页面
function edit_carVersion(id){
	location.href = "carVersion_edit.do?id=" + id;
}

//打开查看品牌信息页面
function view_carVersion(id){
	location.href = "carVersion_view.do?id=" + id;
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
				}
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("获取品牌失败");
		},
		dataType: "json"
	});
}