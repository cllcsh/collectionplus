 //打开编辑事件页面
function edit_album(id){
	window.location="album_edit.do?id=" + id;
}

function view_album(picPath){
	//$("#viewDialog").dialog('open').html("正在加载页面...");
	$("#viewDialog").dialog('open').html("<img width='100%' height='100%' src='/"+picPath + "'/>");
}

//添加图片
function up_file(){
	//document.openDialog("uploadDialog").html("正在加载页面...").load(_contextPath+"/upload_init.do");
	$("#uploadDialog").dialog('open').html("正在加载页面...").load(_contextPath + "/upload_uploadInit.do?mode=ajax&uploadContentType=pic&path=file");
}

//清空标题图片输入框内容
function clear_upfile(){
	$('#picPath').val('');
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
	$("#viewDialog").dialog({
		bgiframe: true,
		autoOpen: false,
		width:500,
		height:500,
		modal: true,
		close: function() {
			$(this).html('');
		}
	});
});


