/**
 * 热门搜索管理页面脚本
 * @author pachong
 * @create 2015-07-26
 * @file brandSearchMgr.js
 * @since v0.1
 */

 //打开编辑热门搜索信息页面
function edit_brandSearch(id){
	location.href = "brandSearch_edit.do?id=" + id;
}

//打开查看热门搜索信息页面
function view_brandSearch(id){
	location.href = "brandSearch_view.do?id="+id;
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