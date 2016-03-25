//全局变量 
var _roleName = "";
var _departmentId = "";
var _currPage = 1;		//保存当前页码
var _perPage = 10;

//查询
function query(){
	_roleName = document.getElementById("roleName").value;
	_currPage = 1;
	loadPage();
}
//刷新分页数据，分页加载完毕后由回调函数重新设置
reload = function(){};

function loadPage(){
	/*var ev = window.event;
	var target = ev.target || ev.srcElement;
	disableButton(jQuery(target));*/
	
	var param = {'mode':"ajax",'roleInfoForm.roleName':_roleName};
	var pageconfig = {
		ajaxDataType:'html',
		proxyUrl:'role_query.do',
		currentPage:_currPage,
		perPage:_perPage,
		ajaxParam:param,
		barPosition:'bottom',
		showMode:'full',
		callback:function(perpage,reloadFn){
			_perPage = perpage;
			reload = reloadFn;
			//enableButton(jQuery(target));
		}
	};
	$("#pagecontent").pagination(pageconfig);
}

//添加
function add_role(e){
	$('#dialog').dialog('option', 'width', 750); 
	$('#dialog').dialog('option', 'height', 400);
	$('#dialog').dialog('option', 'position', 'top');
	$("#dialog").dialog('open').html("正在加载页面...").load("role_add.do?mode=ajax",function(){
		$("#treeboxTd").addmask();
		mytree = initTree("treeboxbox_tree","",'0');
	});
}

//保存信息
function save_role(e){
	e.disabled = "true";
	if(mytree)
	{
		$('#functionValue').val(mytree.getAllChecked());
	}
	
	var funcs = $('#functionValue').val();
	if(funcs == "" || funcs.length < 1){
		alert("请为角色选择相应功能");
		return;
	}
	
	if(!$.formValidator.pageIsValid("1")){
		e.disabled = "";
		return;
	}
	var formData = $("#roleInfoForm").serialize();
	$.ajax({
		type: "POST",
		url: "role_save.do?mode=ajaxJson",
		data: formData,
		success: function(json){
			e.disabled = "";			
			alert(json.message);
			if(json.codeid == 0){
				$("#dialog").dialog('close');
				reload();
			}	
		},
		dataType: "json"
	});

}
//在页面对话框中编辑用户信息
function edit_role(roleId){
	$('#dialog').dialog('option', 'width', 750);
	$('#dialog').dialog('option', 'height', 500);
	$("#dialog").dialog('open').html("正在加载页面...").load("role_edit.do?mode=ajax&roleId=" + roleId,function(){
		$("#treeboxTd").addmask();
		mytree = initTree("treeboxbox_tree",roleId,'0');
	});
}

function update_role(e){
	e.disabled = "true";
	if(mytree)
	{
		$('#functionValue').val(mytree.getAllChecked());
	}
	
	var funcs = $('#functionValue').val();
	if(funcs == "" || funcs.length < 1){
		alert("请为角色选择相应功能");
		return;
	}
	
	if(!$.formValidator.pageIsValid("1")){
		e.disabled = "";
		return;
	}
	var formData = $("#roleInfoForm").serialize();
	$.ajax({
		type: "POST",
		url: "role_update.do?mode=ajaxJson",
		data: formData,
		success: function(json){
			e.disabled = "";
			alert(json.message);
			if(json.codeid == 0){
				$("#dialog").dialog('close');
				reload();
			}
		},
		dataType: "json"
	});
}

//删除
function del_role(id) {
	if(window.confirm(msg.CONFIRM_DELETE)){
		$.getJSON("role_delete.do", { 'mode':"ajaxJson",ids: id }, function(json){
			alert(json.message);
			if(json.codeid == 0)
			reload();
		});
	}
}

//删除用户
function del_roles(name) {
	var id = getCheckboxValuesByName(name);
	if(id.length < 1){
		alert(msg.PLEASE_SELECT_TO_DELETE);
		return;
	}
	if(window.confirm(msg.CONFIRM_DELETE_ALL)){
		$.getJSON("role_delete.do", { 'mode':"ajaxJson",ids: id }, function(json){
			alert(json.message);
			if(json.codeid == 0)
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
		height: 400,
		modal: true,
		close: function() {
			$(this).html('');
		}
	});
});

var mytree;

//查看用户信息
function view_role(roleId){
	$('#dialog').dialog('option', 'width', 750);
	$('#dialog').dialog('option', 'height', 500);
	$("#dialog").dialog('open').html("正在加载页面...").load("role_view.do?mode=ajax&roleId="+ roleId,function(){
		$("#treeboxTd").addmask();
		mytree = initTree("treeboxbox_tree",roleId,'1');
	});
}

//关闭弹出对话框
function closeDialog(){
	$("#dialog").dialog('close');
}
