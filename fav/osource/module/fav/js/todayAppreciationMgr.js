/**
 * 今日鉴赏管理页面脚本
 * @author gaoxiang
 * @create 2015-11-10
 * @file todayAppreciationMgr.js
 * @since v0.1
 */

 //打开编辑今日鉴赏信息页面
function edit_todayAppreciation(id){
	location.href = "todayAppreciation_edit.do?id=" + id;
}

//打开查看今日鉴赏信息页面
function view_todayAppreciation(id){
	location.href = "todayAppreciation_view.do?id="+id;
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