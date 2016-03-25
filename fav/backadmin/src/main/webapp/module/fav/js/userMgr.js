/**
 * 系统用户管理页面脚本
 * @author gaoxiang
 * @create 2015-11-10
 * @file userMgr.js
 * @since v0.1
 */

 //打开编辑系统用户信息页面
function edit_user(id){
	location.href = "user_edit.do?id=" + id;
}

//打开查看系统用户信息页面
function view_user(id){
	location.href = "user_view.do?id="+id;
}