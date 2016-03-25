//全局变量 
var _name = ""; // 姓名
var _rank = ""; // 等级
var _consultantCertificateId = ""; // 咨询师证书编号
var _useFlag = "1"; // 使用状态
var _idNum = ""; // 身份证号码
var _registerId = ""; // 全国志愿者注册编号
var _currPage = 1;		//保存当前页码
var _perPage = 10;

//查询
function query(){
	_name = document.getElementById("name").value;
	_rank = document.getElementById("rank").value;
	_consultantCertificateId = document.getElementById("consultantCertificateId").value;
	var radios = document.getElementsByName("useFlag");
	for(var i=0; i<radios.length; i++) {
		if(radios[i].checked) {
			_useFlag = radios[i].value;
		}
	}
	_idNum = document.getElementById("idNum").value;
	_registerId = document.getElementById("registerId").value;
	_currPage = 1;
	loadPage();
}
//刷新分页数据，分页加载完毕后由回调函数重新设置
reload = function(){};

function loadPage(){
	var ev = window.event;
	var target = ev.target || ev.srcElement;
	disableButton(jQuery(target));
	var param = {'mode':"ajax",'volunteerSearchInfo.name':_name,'volunteerSearchInfo.rank':_rank, 'volunteerSearchInfo.consultantCertificateId':_consultantCertificateId, 'volunteerSearchInfo.useFlag':_useFlag, 'volunteerSearchInfo.idNum':_idNum, 'volunteerSearchInfo.registerId':_registerId};
	var pageconfig = {
		ajaxDataType:'html',
		proxyUrl:'volunteer_query.do',
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

//添加
function add_volunteer(e){
	location.href("volunteer_add.do");
}

//保存信息
function save_volunteer(e){
	e.disabled = "true";
	if(!$.formValidator.pageIsValid("1")){
		e.disabled = "";
		return;
	}
	var formData = $("#volunteerBean").serialize();
	$.ajax({
		type: "POST",
		url: "volunteer_save.do?mode=ajaxJson",
		data: formData,
		success: function(json){
			e.disabled = "";			
			alert(json.message);
			if(json.codeid == 0){
				location.href("volunteer_init.do");
			}	
		},
		dataType: "json"
	});

}
//在页面对话框中编辑用户信息
function edit_volunteer(id){
	location.href("volunteer_edit.do?volunteerId=" + id);
}

function update_volunteer(e){
	e.disabled = "true";
	if(!$.formValidator.pageIsValid("1")){
		e.disabled = "";
		return;
	}
	var formData = $("#volunteerBean").serialize();
	$.ajax({
		type: "POST",
		url: "volunteer_update.do?mode=ajaxJson",
		data: formData,
		success: function(json){
			e.disabled = "";
			alert(json.message);
			if(json.codeid == 0){
				location.href("volunteer_init.do");
			}
		},
		dataType: "json"
	});
}

//删除
function del_volunteer(id) {
	if(window.confirm(msg.CONFIRM_DELETE)){
		$.getJSON("volunteer_delete.do", { 'mode':"ajaxJson",ids: id }, function(json){
			alert(json.message);
			if(json.codeid == 0)
				reload();
		});
	}
}

//删除用户
function del_volunteers(name) {
	var id = getCheckboxValuesByName(name);
	if(id.length < 1){
		alert(msg.PLEASE_SELECT_TO_DELETE);
		return;
	}
	if(window.confirm(msg.CONFIRM_DELETE_ALL)){
		$.getJSON("volunteer_delete.do", { 'mode':"ajaxJson",'volunteerIds': id }, function(json){
			alert(json.message);
			if(json.codeid == 0)
				reload();
		});
	}
}
// 上传照片
function upload(type) {
	$("#dialog").dialog('open').html("正在加载页面...").load("volunteer_upload.do?mode=ajax&fileType=" + type);// /jsp/upload/fileSet.jsp
}

function callbackpic(fileName, type) {
	//alert(type);
	//alert(fileName);
	if(type == "pic") {
		document.getElementById("volunteerForm_picPath").value = fileName;
		var td = document.getElementById("picTd");
		//var img = document.createElement("img");
		//img.setAttribute("src", document.getElementById("contextPath").value + "/upload/" + fileName);
		//img.setAttribute("width", "90");
		//img.setAttribute("height", "120");
		//img.setAttribute("onload", "picsize(this)");
		//td.appendChild(img);
		var str = "<img src=\"" + document.getElementById("contextPath").value + "/upload/" + fileName + "\" width=\"90\" height=\"120\" onload=\"picsize(this)\"/>";
		td.innerHTML = str;
	}
	if(type == "req") {
		document.getElementById("volunteerForm_reqPath").value = fileName;
		var span = document.getElementById("reqSpan");
		var str = "<a href=\"" + document.getElementById("contextPath").value + "/upload/" + fileName + "\" target=\"_blank\">志愿申请书查看</a>";
		span.innerHTML = str;
	}
	closeDialog();
}

function picsize(obj) {
	obj.width = 90;
	obj.height = 120;
}

//定义弹出对话框
$(function() {
	$("#dialog").dialog({
		bgiframe: true,
		autoOpen: false,
		width:600,
		height: 150,
		modal: true,
		close: function() {
			$(this).html('');
		}
	});
});

//查看用户信息
function view_volunteer(id){
	location.href("volunteer_view.do?volunteerId="+id);
}

//关闭弹出对话框
function closeDialog(){
	$("#dialog").dialog('close');
}

function checkIDNum()
{
	if(!((window.event.keyCode >= 48) && (window.event.keyCode <= 57) || (window.event.keyCode == 120) || (window.event.keyCode == 88)))
	{
	   window.event.keyCode = 0 ;
	}
	return;
}
