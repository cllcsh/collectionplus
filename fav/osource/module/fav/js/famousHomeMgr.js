/**
 * 名人堂管理页面脚本
 * @author gaoxiang
 * @create 2015-11-10
 * @file famousHomeMgr.js
 * @since v0.1
 */

 //打开编辑名人堂信息页面
function edit_famousHome(id){
	location.href = "famousHome_edit.do?id=" + id;
}

//打开查看名人堂信息页面
function view_famousHome(id){
	location.href = "famousHome_view.do?id="+id;
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