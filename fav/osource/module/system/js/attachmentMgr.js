/**
 * 通知下载页面脚本
 * @author weiwu
 * @create 2010-12-28
 * @file attachmentMgr.js
 * @since v0.1
 */
//保存信息
function save_attachment(fileName)
{
	$('#save_button').attr('disabled', 'disabled');// 不能重复单击同一按钮
	var formData = $("#attachmentForm").serialize();
	$.ajax( {
		type : "POST",
		url : "attachment_save.do?mode=ajaxJson&fileName="+fileName,
		data : formData,
		success : function(json) {
			if (json.codeid == 0) {
				location.href = "attachment_init.do";
			}
			$('#save_button').attr('disabled', '');
		},
		dataType : "json"
	});
}
function update_attachment(fileName)
{
	$('#save_button').attr('disabled', 'disabled');// 不能重复单击同一按钮
	var formData = $("#attachmentForm").serialize();
	$.ajax( {
		type : "POST",
		url : "attachment_update.do?mode=ajaxJson&fileName="+fileName,
		data : formData,
		success : function(json) {
			alert(json.message);
			if (json.codeid == 0) {
				location.href = "attachment_init.do";
			}
			$('#btn_save').attr('disabled', '');
		},
		dataType : "json"
	});
}

