/**
 * 收藏夹管理页面脚本
 * @author gaoxiang
 * @create 2015-11-10
 * @file favoritesMgr.js
 * @since v0.1
 */

 //打开编辑收藏夹信息页面
function edit_favorites(id){
	location.href = "favorites_edit.do?id=" + id;
}

//打开查看收藏夹信息页面
function view_favorites(id){
	location.href = "favorites_view.do?id="+id;
}

//打开查看用户信息页面
function view_favUser(id){
	location.href = "favUser_view.do?id="+id;
}

//打开查看藏品信息页面
function view_collection(id){
	location.href = "collection_view.do?id="+id;
}