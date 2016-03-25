/**
 * 用户设置管理页面脚本
 * @author gaoxiang
 * @create 2015-11-22
 * @file favUserSetMgr.js
 * @since v0.1
 */

 //打开编辑用户设置信息页面
function edit_favUserSet(id){
	location.href = "favUserSet_edit.do?id=" + id;
}

//打开查看用户设置信息页面
function view_favUserSet(id){
	location.href = "favUserSet_view.do?id="+id;
}

//打开查看用户信息页面
function view_favUser(id){
	location.href = "favUser_view.do?id="+id;
}