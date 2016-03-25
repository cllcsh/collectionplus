/**
 * 用户管理页面脚本
 * @author gaoxiang
 * @create 2015-11-10
 * @file favUserMgr.js
 * @since v0.1
 */

 //打开编辑用户信息页面
function edit_favUser(id){
	location.href = "favUser_edit.do?id=" + id;
}

//打开查看用户信息页面
function view_favUser(id){
	location.href = "favUser_view.do?id="+id;
}

function reset_favUser(id){
	$.ajax({
		type: "POST",
		url: "favUser_reset.do",
		data: {"id":id},
		success: function(data){
			alert(data.message);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("重置密码失败");
		},
		dataType: "json"
	});
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