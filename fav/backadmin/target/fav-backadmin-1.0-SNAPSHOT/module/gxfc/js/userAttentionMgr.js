/**
 * 个人关注页面脚本
 * @author yangs
 * @create 2015-09-23
 * @file userAttentionMgr.js
 * @since v0.1
 */

 //打开编辑个人关注页面
function edit_userAttention(id){
	location.href("userAttention_edit.do?id=" + id);
}

//打开查看个人关注页面
function view_userAttention(id){
	location.href("userAttention_view.do?id="+id);
}