/**
 * 热门品牌管理页面脚本
 * @author pachong
 * @create 2015-07-26
 * @file brandHotMgr.js
 * @since v0.1
 */

 //打开编辑热门品牌信息页面
function edit_brandHot(id){
	location.href = "brandHot_edit.do?id=" + id;
}

//打开查看热门品牌信息页面
function view_brandHot(id){
	location.href = "brandHot_view.do?id="+id;
}

function choseBrand() {
	var pinyin = $("#pinyin").val();
	$.ajax({
		type: "POST",
		url: "/module/gxfc/brand_queryBypinyin.do",
		data: {"brandForm.pinyin":pinyin},
		success: function(data){
			$("#brandId").html("");
			if(data.message != undefined && data.message.length>0){
				var optionHtml = "";
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