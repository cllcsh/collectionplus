/**
 * 黑名单管理页面脚本
 * @author gaoxiang
 * @create 2015-11-21
 * @file userBlackMgr.js
 * @since v0.1
 */

 //打开编辑黑名单信息页面
function edit_userBlack(id){
	location.href = "userBlack_edit.do?id=" + id;
}

//打开查看黑名单信息页面
function view_userBlack(id){
	location.href = "userBlack_view.do?id="+id;
}

//打开查看用户信息页面
function view_favUser(id){
	location.href = "favUser_view.do?id="+id;
}