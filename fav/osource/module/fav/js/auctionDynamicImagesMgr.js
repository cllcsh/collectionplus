/**
 * 拍卖行动态拍品征集管理页面脚本
 * @author gaoxiang
 * @create 2015-11-08
 * @file auctionDynamicImagesMgr.js
 * @since v0.1
 */

 //打开编辑拍卖行动态拍品征集信息页面
function edit_auctionDynamicImages(id){
	location.href = "auctionDynamicImages_edit.do?id=" + id;
}

//打开查看拍卖行动态拍品征集信息页面
function view_auctionDynamicImages(id){
	location.href = "auctionDynamicImages_view.do?id="+id;
}

//打开查看拍卖行动态信息页面
function view_auctionDynamics(id){
	location.href = "auctionDynamics_view.do?id="+id;
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