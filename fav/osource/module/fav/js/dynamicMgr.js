/**
 * 动态管理页面脚本
 * @author gaoxiang
 * @create 2015-11-10
 * @file dynamicMgr.js
 * @since v0.1
 */

 //打开编辑动态信息页面
function edit_dynamic(id){
	location.href = "dynamic_edit.do?id=" + id;
}

//打开查看动态信息页面
function view_dynamic(id){
	location.href = "dynamic_view.do?id="+id;
}

//打开查看用户信息页面
function view_favUser(id){
	location.href = "favUser_view.do?id="+id;
}