/**
 * 版本管理页面脚本
 * @author pachong
 * @create 2015-07-26
 * @file userActionMgr.js
 * @since v0.1
 */

 //打开编辑品牌信息页面
function edit_userAction(id){
	location.href("userAction_edit.do?id=" + id);
}

//打开查看品牌信息页面
function view_userAction(id){
	location.href("userAction_view.do?id="+id);
}