/**
 * 动态评论顶管理页面脚本
 * @author gaoxiang
 * @create 2015-11-10
 * @file dynamicCommentTopMgr.js
 * @since v0.1
 */

 //打开编辑动态评论顶信息页面
function edit_dynamicCommentTop(id){
	location.href = "dynamicCommentTop_edit.do?id=" + id;
}

//打开查看动态评论顶信息页面
function view_dynamicCommentTop(id){
	location.href = "dynamicCommentTop_view.do?id="+id;
}

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

//打开查看藏品评论信息页面
function view_collectionComments(id){
	location.href = "collectionComments_view.do?id="+id;
}