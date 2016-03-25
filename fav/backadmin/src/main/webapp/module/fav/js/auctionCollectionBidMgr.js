/**
 * 拍卖行藏品竞价管理页面脚本
 * @author gaoxiang
 * @create 2015-11-08
 * @file auctionCollectionBidMgr.js
 * @since v0.1
 */

 //打开编辑拍卖行藏品竞价信息页面
function edit_auctionCollectionBid(id){
	location.href = "auctionCollectionBid_edit.do?id=" + id;
}

//打开查看拍卖行藏品竞价信息页面
function view_auctionCollectionBid(id){
	location.href = "auctionCollectionBid_view.do?id="+id;
}

//打开查看藏品信息页面
function view_collection(id){
	location.href = "collection_view.do?id="+id;
}

//打开查看用户信息页面
function view_favUser(id){
	location.href = "favUser_view.do?id="+id;
}