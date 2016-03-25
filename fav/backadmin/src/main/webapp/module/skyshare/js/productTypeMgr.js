/**
 * 产品类型页面脚本
 * @author nyl
 * @create 2014-03-27
 * @file productTypeMgr.js
 * @since v0.1
 */

 //打开编辑工作页面
function edit_productType(id){
	//location.href("productType_edit.do?id=" + id);
	window.location = "productType_edit.do?id=" + id;
}

//打开查看工作页面
function view_productType(id){
	//location.href("productType_view.do?id="+id);
	window.location = "productType_view.do?id="+id;
}