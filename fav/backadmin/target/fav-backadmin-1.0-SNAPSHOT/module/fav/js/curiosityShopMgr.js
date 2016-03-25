/**
 * 古玩店管理页面脚本
 * @author gaoxiang
 * @create 2015-11-10
 * @file curiosityShopMgr.js
 * @since v0.1
 */

 //打开编辑古玩店信息页面
function edit_curiosityShop(id){
	location.href = "curiosityShop_edit.do?id=" + id;
}

//打开查看古玩店信息页面
function view_curiosityShop(id){
	location.href = "curiosityShop_view.do?id="+id;
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