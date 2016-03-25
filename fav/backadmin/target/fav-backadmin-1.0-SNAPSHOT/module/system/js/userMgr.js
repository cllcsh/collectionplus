/**
 * 系列管理页面脚本
 * @author pachong
 * @create 2015-07-26
 * @file userMgr.js
 * @since v0.1
 */

 //打开编辑系列信息页面
function edit_user(id){
	location.href = "user_edit.do?id=" + id;
}

 //打开审核系列信息页面
function approve_user(id){
	location.href = "user_approve.do?id=" + id;
}

//打开查看系列信息页面
function view_user(id){
	location.href = "user_view.do?id=" + id;
}

//定义弹出对话框
$(function() {
	$("#approveDialog").dialog({
		bgiframe: true,
		autoOpen: false,
		width:500,
		height:350,
		modal: true,
		close: function() {
			//$(this).html('');
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

function userApprove()
{
	var userid = $("#userid").val();
	var approveType = $("#approveType").val();
	var reason = $("#reason").val();

	$.ajax({
		type: "POST",
		url: "/module/system/user_approve.do",
		data: {"userid":userid, "approveType":approveType, "reason":reason},
		success: function(data){
			alert(data.message);
			if(data.codeid == 0){
				document.location.reload();
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("审核失败");
		},
		dataType: "json"
	});
}

//添加图片
function openDialogUpload(id){
	$("#approveDialog").dialog('open');
	$("#userid").val(id);
}