/**
 * 动态评论管理页面脚本
 * @author gaoxiang
 * @create 2015-11-10
 * @file dynamicCommentsMgr.js
 * @since v0.1
 */

 //打开编辑动态评论信息页面
function edit_dynamicComments(id){
	location.href = "dynamicComments_edit.do?id=" + id;
}

//打开查看动态评论信息页面
function view_dynamicComments(id){
	location.href = "dynamicComments_view.do?id="+id;
}