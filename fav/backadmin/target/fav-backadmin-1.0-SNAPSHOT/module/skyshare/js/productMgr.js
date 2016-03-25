/**
 * 产品页面脚本
 * @author nyl
 * @create 2014-03-28
 * @file productMgr.js
 * @since v0.1
 */

 //打开编辑产品页面
function edit_product(id){
	//location.href("product_edit.do?id=" + id);
	window.location = "product_edit.do?id=" + id;
}

//打开查看产品页面
function view_product(id){
	//location.href("product_view.do?id="+id);
	window.location = "product_view.do?id="+id;
}

//添加图片
function up_file(){
	//document.openDialog("uploadDialog").html("正在加载页面...").load(_contextPath+"/upload_init.do");
	$("#uploadDialog").dialog('open').html("正在加载页面...").load(_contextPath + "/upload_uploadInit.do?mode=ajax&uploadContentType=pic&path=file");
}

//清空标题图片输入框内容
function clear_upfile(id){
	var id1 = '#'+id;
	$(id1).val('');
}

function callbackpic(fileName){
	$('#picPath').val(fileName);
	$("#uploadDialog").dialog('close');
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

//定义弹出对话框
$(function() {
	$("#uploadFileDialog").dialog({
		bgiframe: true,
		autoOpen: false,
		width:400,
		height:250,
		modal: true,
		close: function() {
			$(this).html('');
		}
	});
});

function up_file(){
//	document.open("upload").html("正在加载页面...").load(_contextPath+"/upload_init.do");
	$("#uploadFileDialog").dialog('open').html("正在加载页面...").load(_contextPath + "/upload_init.do?mode=ajax&uploadContentType=pic&path=file");
}

