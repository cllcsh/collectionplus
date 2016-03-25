/**
 * 流程管理页面脚本
 * @author lif
 * @create 2014-03-29
 * @file flowMgr.js
 * @since v0.1
 */

 //打开编辑流程信息页面
function edit_flow(id){
	//location.href("flow_edit.do?id=" + id);
	window.location = "flow_edit.do?id=" + id;
}

//打开查看流程信息页面
function view_flow(id){
	//location.href("flow_view.do?id="+id);
	window.location = "flow_view.do?id="+id;
}

function uploadFlowPic(){
	var orderId = $("#orderId").val();
	var flowId = $("#flowId").val();
	
	$("#uploadDialog").dialog('open').html("正在加载页面...").load("flow_uploadPicInit.do?orderId="+orderId+"&id="+flowId);
}


function editFlowPic(id){
	var orderId = $("#orderId").val();
	var flowId = $("#flowId").val();
	$("#uploadDialog").dialog('open').html("正在加载页面...").load("flow_updatePicInit.do?orderFlowForm.id="+id + "&orderId=" +orderId +"&id="+flowId);
}

function deleteFlowPic(id){
	var orderId = $("#orderId").val();
	var flowId = $("#flowId").val();
	$.ajax({
		type: "POST",
		url: "flow_deleteFlowPic.do?mode=ajaxJson&orderFlowForm.id=" + id + "&orderId=" +orderId +"&id="+flowId,
		success: function(json){			
			alert(json.message);
			if(json.codeid == 0){
				//刷新页面
				var arr = json.text.split("|");//text中包含订单Id和流程Id信息：orderId + "|" + id
				showFlowInfo(arr[0], arr[1], arr[2]);
			}	
		
		},
		  dataType: "json"
	    });
        	//attachfileManager.delbyId(attachId, function(){
        	
           // });

}


function closeDialog(){
	$("#uploadDialog").dialog('close');
}

//清空标题图片输入框内容
function clear_upfile(id){
	$('#'+id).val('');
}

//定义弹出对话框
$(function() {
	$("#uploadDialog").dialog({
		bgiframe: true,
		autoOpen: false,
		width:600,
		height:500,
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

function view_order(id){
	//location.href("product_view.do?id="+id);
	
	window.location = "order_view.do?id="+id;
}
