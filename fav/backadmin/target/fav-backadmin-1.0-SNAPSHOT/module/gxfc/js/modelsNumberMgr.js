/**
 * 热门车型数量管理页面脚本
 * @author pachong
 * @create 2015-07-26
 * @file modelsNumberMgr.js
 * @since v0.1
 */

 //打开编辑热门车型数量信息页面
function edit_modelsNumber(id){
	location.href("modelsNumber_edit.do?id=" + id);
}

//打开查看热门车型数量信息页面
function view_modelsNumber(id){
	location.href("modelsNumber_view.do?id="+id);
}

function saveModelsNumber() {
	if ($("#num").val() == "") {
		alert("请输入热门车型数量");
		return false;
	}
	var formData = $('#setForm').serialize();
	$.ajax({
        cache: false,
        type: "POST",
        url:"modelsNumber_save.do",
        data:formData,// 你的formid
        async: false,
        error: function(request) {
            alert("保存出错");
        },
        success: function(data) {
        	alert(data.message);
        	if (data.codeid == "0") {
        		document.location.reload();
        	}
        },
        dataType: 'json'
    });
}