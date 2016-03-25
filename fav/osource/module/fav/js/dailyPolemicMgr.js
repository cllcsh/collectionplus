/**
 * 天天论战管理页面脚本
 * @author gaoxiang
 * @create 2015-11-10
 * @file dailyPolemicMgr.js
 * @since v0.1
 */

 //打开编辑天天论战信息页面
function edit_dailyPolemic(id){
	location.href = "dailyPolemic_edit.do?id=" + id;
}

//打开查看天天论战信息页面
function view_dailyPolemic(id){
	location.href = "dailyPolemic_view.do?id="+id;
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