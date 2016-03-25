/**
 * 抽查人机分离页面脚本
 * @author xiaoxubing
 * @create 2010-02-23
 * @file checkMgr.js
 * @since v0.1
 */

function edit_check(id)
{
	location.href="check_edit.do?id="+id;
}

function view_check(id)
{
	location.href="check_view.do?id="+id;
}


function findPhone()
{
	var criminalId = document.getElementById("criminalId").value;
	$.ajax({
		type: "POST",
		url: "check_findPhone.do?criminalId="+criminalId,
		success: function(json){
			if(json.codeid == 0){
				document.getElementById("phone").value = json.message;
			}
		},
		dataType: "json"
	})
}

//判断开始时间和结束时间
function judegstarttime() {
	_startTime = document.getElementById("startDate").value;
	_endTime = document.getElementById("endDate").value;
	if (!_endTime == "")
		if (_startTime >= _endTime)
		{
			alert("请注意开始时间和结束时间");
			document.getElementById("startDate").value ="";
		}
}
//判断开始时间和结束时间
function judegendtime() {
	_startTime = document.getElementById("startDate").value;
	_endTime = document.getElementById("endDate").value;
	if (!_startTime == "")
		if (_startTime >= _endTime)
		{
			alert("请注意开始时间和结束时间");
			document.getElementById("endDate").value ="";
		}
}


//清除输入文本框内容的前后空格
function clearBlank(widget){
	var messageBox = $(widget);
	var content = messageBox.val();
	messageBox.val($.trim(content));
}