/**
 * 粉丝管理页面脚本
 * @author gaoxiang
 * @create 2015-11-10
 * @file userFansMgr.js
 * @since v0.1
 */

 //打开编辑粉丝信息页面
function edit_userFans(id){
	location.href = "userFans_edit.do?id=" + id;
}

//打开查看粉丝信息页面
function view_userFans(id){
	location.href = "userFans_view.do?id="+id;
}

//打开查看用户信息页面
function view_favUser(id){
	location.href = "favUser_view.do?id="+id;
}