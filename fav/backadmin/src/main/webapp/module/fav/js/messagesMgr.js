/**
 * 消息管理页面脚本
 * @author gaoxiang
 * @create 2015-11-10
 * @file messagesMgr.js
 * @since v0.1
 */

 //打开编辑消息信息页面
function edit_messages(id){
	location.href = "messages_edit.do?id=" + id;
}

//打开查看消息信息页面
function view_messages(id){
	location.href = "messages_view.do?id="+id;
}

//打开查看用户信息页面
function view_favUser(id){
	location.href = "favUser_view.do?id="+id;
}