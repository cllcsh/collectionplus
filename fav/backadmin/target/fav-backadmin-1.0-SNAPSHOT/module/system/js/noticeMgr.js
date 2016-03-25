/**
 * 会员管理页面脚本
 * @author pachong
 * @create 2015-08-15
 * @file noticeMgr.js
 * @since v0.1
 */

 //打开编辑会员信息页面
function edit_notice(id){
	location.href = "notice_edit.do?id=" + id;
}

//打开查看会员信息页面
function view_notice(id){
	location.href = "notice_view.do?id=" + id;
}