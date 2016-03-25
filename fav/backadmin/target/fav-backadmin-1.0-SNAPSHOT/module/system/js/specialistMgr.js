/**
 * 专家管理页面脚本
 * @author lifa
 * @create 2009-10-28
 * @file specialistMgr.js
 * @since v0.1
 */

//全局变量 
var _certificateId = "";
var _specialistName = "";
var _idNum = "";
var _workUnit = "";
var _sex = "";
//var _useFlag = "";
//var _deptId = "";

var _currPage = 1;		//保存当前页码
var _perPage = 10;

function checkInput(){
	var specialistName = $('#specialistName').val();
	
	//var pattern1 = /^([0-9a-zA-Z]|_){0,20}$/;
	var pattern2 = /^[\u4E00-\u9FA5|a-z|0-9]{0,60}$/;
	
	if(!pattern2.test(specialistName)){
		alert('您要查询的专家名字不合法，请重新输入');
		$('#specialistName').val('');
		return false;
	}
	
	return true;
	
}

//查询
function query(){
	if(!checkInput()){
		return false;
	}
	
	_certificateId = document.getElementById("certificateId").value;
	_specialistName = document.getElementById("specialistName").value;
	_idNum = document.getElementById("idNum").value;
	_workUnit = document.getElementById("workUnit").value;
	_sex = document.getElementById("sex").value;
	//_useFlag = document.getElementById("useFlag").value;
	//_deptId = document.getElementById("deptId").value;
		
	_currPage = 1;
	loadPage();
}
//刷新分页数据，分页加载完毕后由回调函数重新设置
reload = function(){};

/**
 * 载入页面
 * 读取全局变量作为ajax调用的参数
 */
function loadPage(){
	var ev = window.event;
	var target = ev.target || ev.srcElement;
	disableButton(jQuery(target));
	
	var param = {'mode':"ajax",
				 'specialistForm.certificateId':_certificateId,
				 'specialistForm.name':_specialistName,
				 'specialistForm.idNum':_idNum,
				 'specialistForm.workUnit':_workUnit,
				 'specialistForm.sex':_sex
				};
	
	var pageconfig = {
		ajaxDataType:'html',
		proxyUrl:'specialist_query.do',
		currentPage:_currPage,
		perPage:_perPage,
		ajaxParam:param,
		barPosition:'bottom',
		showMode:'full',
		callback:function(perpage,reloadFn){
			_perPage = perpage;
			reload = reloadFn;
			enableButton(jQuery(target));
		}
	};
	$("#pagecontent").pagination(pageconfig);
}


//添加矫正专家
function add_specialist(){
	//$("#dialog").dialog('open').html("正在加载页面...").load("specialist_add.do?mode=ajax");
	location.href("specialist_add.do");
}

//保存矫正专家信息
function save_specialist(){
	if(!$.formValidator.pageIsValid("1")){
		return;
	}
	$('#button').attr('disabled','disabled');//不能重复单击同一按钮
	
	var formData = $("#specialistForm").serialize();
	//alert(formData);
	$.ajax({
		type: "POST",
		url: "specialist_save.do?mode=ajaxJson",
		data: formData,
		success: function(json){
			alert(json.message);
			if(json.codeid == 0){
				//$("#dialog").dialog('close');
				//reload();
				location.href("specialist_init.do");
			}
			$('#button').attr('disabled','');
		},
		dataType: "json"
	});
	
}

//在页面对话框中编辑矫正专家信息
function edit_specialist(specialistId){
	//$("#dialog").dialog('open').html("正在加载页面...").load("specialist_edit.do?mode=ajax&specialistId="+specialistId);
	location.href("specialist_edit.do?specialistId=" + specialistId);
}

//在页面对话框中编辑矫正专家信息(暂未使用)
function edit_checked_user(checkboxName){
	if($(":checkbox[name='" + checkboxName + "']:checked").size() < 1){
		alert("请选择一条记录!");
	} else if($(":checkbox[name='" + checkboxName + "']:checked").size() > 1) {
		alert("一次只能编辑一条记录!")
	} else {
		var specialistId = $(":checkbox[name='" + checkboxName + "']:checked").val();
		$("#dialog").dialog('open').html("正在加载页面...").load("specialist_edit.do?mode=ajax&specialistId=" + specialistId);
	}
}

//在页面对话框中查看矫正专家信息
function view_specialist(specialistId){
	//$("#dialog").dialog('open').html("正在加载页面...").load("specialist_view.do?mode=ajax&specialistId="+specialistId);
	location.href("specialist_view.do?specialistId="+specialistId);
}

//更新矫正专家信息
function update_specialist(){
	if(!$.formValidator.pageIsValid("1")){
		return;
    }
	$('#button').attr('disabled','disabled');//不能重复单击同一按钮
	
	var formData = $("#specialistForm").serialize();
	//alert(formData);
	$.ajax({
		type: "POST",
		url: "specialist_update.do?mode=ajaxJson",
		data: formData,
		success: function(json){
			alert(json.message);
			if(json.codeid == 0){
				//$("#dialog").dialog('close');
				//reload();
				location.href("specialist_init.do");
			}
			$('#button').attr('disabled','');
		},
		dataType: "json"
	});

}

//删除矫正专家（暂未使用）
function del_specialist(id) {
	if(window.confirm("确定要删除吗?")){
		$.getJSON("specialist_delete.do", { specialistId: id, mode:'ajaxJson'}, function(json){
			alert(json.message);
			if(json.codeid == 0)
			//loadPage();
				reload();
		});
	}
}

//删除矫正专家
function del_specialists(name) {
	var id = getCheckboxValuesByName(name);
	if(id.length < 1){
		alert("请选择要删除的记录");
		return;
	}
	if(window.confirm("确定要删除选中的记录吗?")){
		$.getJSON("specialist_deletes.do", { specialistId: id, mode:'ajaxJson' }, function(json){
			alert(json.message);
			if(json.codeid == 0)
			   reload();
		});
	}
}

//上传照片
function uploadpic(type) {
	$("#dialog").dialog('open').html("正在加载页面...").load(_contextPath + "/upload_uploadInit.do?mode=ajax&uploadContentType=" + type +"&path=specialist-pic");
}

function callbackpic(fileName, type) {
	//alert(type);
	//alert(fileName);
	if(type == "pic") {
		$("#picPath").val(fileName);
		var td = document.getElementById("picTd");
		var str = "<img src=\"" + _contextPath + "/upload/" + fileName + "\"  width='90' height='120' />";
		td.innerHTML = str;
	}
	closeDialog();
	
}

//清除输入文本框内容的前后空格
function clearBlank(widget){
	var messageBox = $(widget);
	var content = messageBox.val();
	messageBox.val($.trim(content));
}

function checkIDNum()
{
	if(!((window.event.keyCode >= 48) && (window.event.keyCode <= 57) || (window.event.keyCode == 120) || (window.event.keyCode == 88)))
	{
	   window.event.keyCode = 0 ;
	}
	return;
}

//关闭弹出对话框
function closeDialog(){
	$("#dialog").dialog('close');
}

//定义弹出对话框
$(function() {
	$("#dialog").dialog({
		bgiframe: true,
		autoOpen: false,
		width:680,
		height:200,
		modal: true,
		close: function() {
			$(this).html('');
		}
	});
});