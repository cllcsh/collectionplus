/**
 * 藏品评论管理页面脚本
 * @author gaoxiang
 * @create 2015-11-06
 * @file collectionCommentsMgr.js
 * @since v0.1
 */

 //打开编辑藏品评论信息页面
function edit_collectionComments(id){
	location.href = "collectionComments_edit.do?id=" + id;
}

//打开查看藏品评论信息页面
function view_collectionComments(id){
	location.href = "collectionComments_view.do?id="+id;
}

//打开查看用户信息页面
function view_favUser(id){
	location.href = "favUser_view.do?id="+id;
}

//打开查看藏品信息页面
function view_collection(id){
	location.href = "collection_view.do?id="+id;
}

//打开查看动态信息页面
function view_dynamic(id){
	location.href = "dynamic_view.do?id="+id;
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
