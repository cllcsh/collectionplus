/**
 * 配置管理页面脚本
 * @author luoj
 * @create 2009-4-15
 * @file userMgr.js
 * @since v0.1
 */

//全局变量 
var _configKey = "";
var _currPage = 1;		//保存当前页码
var _perPage = 10;

//查询
function query(){
	_configKey = document.getElementById("config_Key").value;
	_currPage = 1;
	loadPage();
}
//刷新分页数据，分页加载完毕后由回调函数重新设置
reload = function(){};
/**
 * 载入页面
 * 读取全局变量_userName,_loginName,_currPage作为ajax调用的参数
 */
function loadPage(){
	var param = {'mode':"ajax",'configInfoForm.configKey':_configKey};
	var pageconfig = {
		ajaxDataType:'html',
		proxyUrl:'config_query.do',
		currentPage:_currPage,
		perPage:_perPage,
		ajaxParam:param,
		barPosition:'bottom',
		showMode:'full',
		callback:function(perpage,reloadFn){
			_perPage = perpage;
			reload = reloadFn;
		}
	};
	$("#pagecontent").pagination(pageconfig);
}

//添加配置
function add_config(e){
	$("#dialog").dialog('open').html("正在加载页面...").load("config_add.do?mode=ajax");
}

//保存配置信息
function save_config(){
	if(!$.formValidator.pageIsValid("1")){
		return;
	}
	$('#button').attr('disabled','disabled');
	var formData = $("#configInfoForm").serialize();
	$.ajax({
		type: "POST",
		url: "config_save.do?mode=ajaxJson",
		data: formData,
		success: function(json){			
			alert(json.message);
			if(json.codeid == 0){
				$("#dialog").dialog('close');
				//window.location.href="notice_init.do";
				reload();
			}	
		$('#button').attr('disabled','');
		},
		dataType: "json"
	});
}

//在页面对话框中编辑配置信息
function edit_config(configCode){
	$("#dialog").dialog('open').html("正在加载页面...").load("config_edit.do?mode=ajax&configCode=" + configCode);
}

//更新配置信息
function update_config(){
	if(!$.formValidator.pageIsValid("1")){
		return;
	}
	$('#button').attr('disabled','disabled');
	var formData = $("#configInfoForm").serialize();
	$.ajax({
		type: "POST",
		url: "config_update.do?mode=ajaxJson",
		data: formData,
		success: function(json){
			alert(json.message);
			if(json.codeid == 0){
				$("#dialog").dialog('close');
				//window.location.href="notice_init.do";
				reload();
			}	
			$('#button').attr('disabled','');
		},
		dataType: "json"
	});
}


//在页面对话框中编辑配置信息
function edit_checked_config(checkboxName){
	if($(":checkbox[name='" + checkboxName + "']:checked").size() < 1){
		alert("请选择一个信息!");
	} else if($(":checkbox[name='" + checkboxName + "']:checked").size() > 1) {
		alert("一次只能编辑一个信息!")
	} else {
		var configCode = $(":checkbox[name='" + checkboxName + "']:checked").val();
		edit_config(configCode);
	}
}

//删除配置信息
function del_configs(code) {
	var code = getCheckboxValuesByName(code);
	if(code.length < 1){
		alert("请选择要删除的配置");
		return;
	}
	if(window.confirm("确定要选中的配置删除吗?")){
		$.getJSON("config_deletes.do", { configCode: code }, function(json){
			alert(json.message);
			if(json.codeid == 0)
			//loadPage();
			reload();
		});
	}
}

//定义弹出对话框
$(function() {
	$("#dialog").dialog({
		bgiframe: true,
		autoOpen: false,
		width:680,
		height: 240,
		modal: true,
		resizable: false,
		close: function() {
			$(this).html('');
		}
	});
});

//关闭弹出对话框
function closeDialog(){
	$("#dialog").dialog('close');
}
