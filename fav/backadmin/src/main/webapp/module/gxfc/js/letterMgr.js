/**
 * 系列管理页面脚本
 * @author pachong
 * @create 2015-10-09
 * @file letterMgr.js
 * @since v0.1
 */

 //打开编辑站内信页面
function edit_letter(id){
	location.href = "letter_edit.do?id=" + id;
}

//打开查看站内信页面
function view_letter(id){
	location.href = "letter_view.do?id="+id;
}