/**
 * 首页管理页面脚本
 * @author gaoxiang
 * @create 2015-11-10
 * @file homeMgr.js
 * @since v0.1
 */

 //打开编辑首页信息页面
function edit_home(id){
	location.href = "home_edit.do?id=" + id;
}

//打开查看首页信息页面
function view_home(id){
	location.href = "home_view.do?id="+id;
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