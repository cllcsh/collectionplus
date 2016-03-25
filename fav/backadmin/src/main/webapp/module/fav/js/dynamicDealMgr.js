/**
 * 拍卖动态成交管理页面脚本
 * @author gaoxiang
 * @create 2015-11-08
 * @file dynamicDealMgr.js
 * @since v0.1
 */

 //打开编辑拍卖动态成交信息页面
function edit_dynamicDeal(id){
	location.href = "dynamicDeal_edit.do?id=" + id;
}

//打开查看拍卖动态成交信息页面
function view_dynamicDeal(id){
	location.href = "dynamicDeal_view.do?id="+id;
}

//打开查看拍卖行动态信息页面
function view_auctionDynamics(id){
	location.href = "auctionDynamics_view.do?id="+id;
}

//打开查看藏品信息页面
function view_collection(id){
	location.href = "collection_view.do?id="+id;
}