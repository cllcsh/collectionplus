/**
 * 系列管理页面脚本
 * @author pachong
 * @create 2015-10-11
 * @file letterUserMgr.js
 * @since v0.1
 */

 //打开编辑站内信发送页面
function edit_letterUser(id){
	location.href("letterUser_edit.do?id=" + id);
}

//打开查看站内信发送页面
function view_letterUser(id){
	location.href("letterUser_view.do?id="+id);
}