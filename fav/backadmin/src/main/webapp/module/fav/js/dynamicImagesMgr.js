/**
 * 动态图片地址管理页面脚本
 * @author gaoxiang
 * @create 2015-11-15
 * @file dynamicImagesMgr.js
 * @since v0.1
 */

 //打开编辑动态图片地址信息页面
function edit_dynamicImages(id){
	location.href = "dynamicImages_edit.do?id=" + id;
}

//打开查看动态图片地址信息页面
function view_dynamicImages(id){
	location.href = "dynamicImages_view.do?id="+id;
}