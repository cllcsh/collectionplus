/**
 * 拍卖行藏品动态直播管理页面脚本
 * @author gaoxiang
 * @create 2015-11-08
 * @file auctionDynamicLiveMgr.js
 * @since v0.1
 */

 //打开编辑拍卖行藏品动态直播表信息页面
function edit_auctionDynamicLive(id){
	location.href = "auctionDynamicLive_edit.do?id=" + id;
}

//打开查看拍卖行藏品动态直播表信息页面
function view_auctionDynamicLive(id){
	location.href = "auctionDynamicLive_view.do?id="+id;
}

//打开查看藏品信息页面
function view_collection(id){
	location.href = "collection_view.do?id="+id;
}

//打开查看拍卖行动态信息页面
function view_auctionDynamics(id){
	location.href = "auctionDynamics_view.do?id="+id;
}