/**
 * 动态点赞管理页面脚本
 * @author gaoxiang
 * @create 2015-11-10
 * @file dynamicLikeMgr.js
 * @since v0.1
 */

 //打开编辑动态点赞信息页面
function edit_dynamicLike(id){
	location.href = "dynamicLike_edit.do?id=" + id;
}

//打开查看动态点赞信息页面
function view_dynamicLike(id){
	location.href = "dynamicLike_view.do?id="+id;
}