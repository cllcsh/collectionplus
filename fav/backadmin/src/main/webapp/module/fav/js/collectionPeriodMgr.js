/**
 * 藏品时期管理页面脚本
 * @author gaoxiang
 * @create 2015-12-11
 * @file collectionPeriodMgr.js
 * @since v0.1
 */

 //打开编辑藏品时期信息页面
function edit_collectionPeriod(id){
	location.href = "collectionPeriod_edit.do?id=" + id;
}

//打开查看藏品时期信息页面
function view_collectionPeriod(id){
	location.href = "collectionPeriod_view.do?id="+id;
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