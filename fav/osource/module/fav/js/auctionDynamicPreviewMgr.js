/**
 * 拍卖动态预展管理页面脚本
 * @author gaoxiang
 * @create 2015-11-08
 * @file auctionDynamicPreviewMgr.js
 * @since v0.1
 */

 //打开编辑拍卖动态预展信息页面
function edit_auctionDynamicPreview(id){
	location.href = "auctionDynamicPreview_edit.do?id=" + id;
}

//打开查看拍卖动态预展信息页面
function view_auctionDynamicPreview(id){
	location.href = "auctionDynamicPreview_view.do?id="+id;
}

//打开查看拍卖行动态信息页面
function view_auctionDynamics(id){
	location.href = "auctionDynamics_view.do?id="+id;
}

//打开查看藏品信息页面
function view_collection(id){
	location.href = "collection_view.do?id="+id;
}