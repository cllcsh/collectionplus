/**
 * 藏品图片管理页面脚本
 * @author gaoxiang
 * @create 2015-11-14
 * @file collectionImagesMgr.js
 * @since v0.1
 */

 //打开编辑藏品图片信息页面
function edit_collectionImages(id){
	location.href = "collectionImages_edit.do?id=" + id;
}

//打开查看藏品图片信息页面
function view_collectionImages(id){
	location.href = "collectionImages_view.do?id="+id;
}