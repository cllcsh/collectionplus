/**
 * 拍卖行管理页面脚本
 * @author gaoxiang
 * @create 2015-11-08
 * @file auctionMgr.js
 * @since v0.1
 */

 //打开编辑拍卖行信息页面
function edit_auction(id){
	location.href = "auction_edit.do?id=" + id;
}

//打开查看拍卖行信息页面
function view_auction(id){
	location.href = "auction_view.do?id="+id;
}

//定义弹出对话框
$(function() {
	$("#uploadDialog").dialog({
		bgiframe: true,
		autoOpen: false,
		width:500,
		height:200,
		modal: true,
		close: function() {
			$(this).html('');
		}
	});
});