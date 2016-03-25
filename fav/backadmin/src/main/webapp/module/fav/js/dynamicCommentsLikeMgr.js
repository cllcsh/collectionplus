/**
 * 动态评论赞同反对管理页面脚本
 * @author gaoxiang
 * @create 2015-11-11
 * @file dynamicCommentsLikeMgr.js
 * @since v0.1
 */

 //打开编辑动态评论赞同反对信息页面
function edit_dynamicCommentsLike(id){
	location.href = "dynamicCommentsLike_edit.do?id=" + id;
}

//打开查看动态评论赞同反对信息页面
function view_dynamicCommentsLike(id){
	location.href = "dynamicCommentsLike_view.do?id="+id;
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