/**
 * 藏品类别管理页面脚本
 * @author gaoxiang
 * @create 2015-12-11
 * @file collectionCategoryMgr.js
 * @since v0.1
 */

 //打开编辑藏品类别信息页面
function edit_collectionCategory(id){
	location.href = "collectionCategory_edit.do?id=" + id;
}

//打开查看藏品类别信息页面
function view_collectionCategory(id){
	location.href = "collectionCategory_view.do?id="+id;
}