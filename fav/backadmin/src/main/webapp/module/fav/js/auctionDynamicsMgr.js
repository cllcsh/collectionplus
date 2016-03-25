/**
 * 拍卖行动态管理页面脚本
 * @author gaoxiang
 * @create 2015-11-08
 * @file auctionDynamicsMgr.js
 * @since v0.1
 */

 //打开编辑拍卖行动态信息页面
function edit_auctionDynamics(id){
	location.href = "auctionDynamics_edit.do?id=" + id;
}

//打开查看拍卖行动态信息页面
function view_auctionDynamics(id){
	location.href = "auctionDynamics_view.do?id="+id;
}

//打开查看拍卖行信息页面
function view_auction(id){
	location.href = "auction_view.do?id="+id;
}