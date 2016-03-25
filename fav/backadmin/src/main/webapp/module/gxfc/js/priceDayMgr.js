/**
 * 统计管理页面脚本
 * @author pachong
 * @create 2015-07-26
 * @file priceDayMgr.js
 * @since v0.1
 */

 //打开编辑统计信息页面
function edit_priceDay(id){
	location.href("priceDay_edit.do?id=" + id);
}

//打开查看统计信息页面
function view_priceDay(id){
	location.href("priceDay_view.do?id="+id);
}