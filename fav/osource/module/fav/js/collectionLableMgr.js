/**
 * 藏品标签管理页面脚本
 * @author gaoxiang
 * @create 2015-11-12
 * @file collectionLableMgr.js
 * @since v0.1
 */

 //打开编辑藏品标签信息页面
function edit_collectionLable(id){
	location.href = "collectionLable_edit.do?id=" + id;
}

//打开查看藏品标签信息页面
function view_collectionLable(id){
	location.href = "collectionLable_view.do?id="+id;
}