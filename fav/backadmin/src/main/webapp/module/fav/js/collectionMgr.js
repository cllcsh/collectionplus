/**
 * 藏品管理页面脚本
 * @author gaoxiang
 * @create 2015-11-03
 * @file collectionMgr.js
 * @since v0.1
 */

 //打开编辑藏品信息页面
function edit_collection(id){
	location.href = "collection_edit.do?id=" + id;
}

//打开查看藏品信息页面
function view_collection(id){
	location.href = "collection_view.do?id="+id;
}

//打开查看拍卖行信息页面
function view_auction(id){
	location.href = "auction_view.do?id="+id;
}

//打开查看用户信息页面
function view_favUser(id){
	location.href = "favUser_view.do?id="+id;
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