//全局变量 
var _rpName = ""; // 奖惩类型名称
var _rp = ""; // 奖惩类型，如 奖励 或 惩罚
var _defScore = ""; // 默认分值
var _currPage = 1;		//保存当前页码
var _perPage = 10;

//查询
function query(){

   if(!checkValue()){
		return false;
	}
   

	_rpName = document.getElementById("rpName").value;
	_defScore = document.getElementById("defScore").value;
	_rp = document.getElementById("rp").value;
	_currPage = 1;
	loadPage();
}


//刷新分页数据，分页加载完毕后由回调函数重新设置
reload = function(){};

function loadPage(){
	var ev = window.event;
	var target = ev.target || ev.srcElement;
	disableButton(jQuery(target));
	var param = {'mode':"ajax",'rpTypeBean.rpName':_rpName,'rpTypeBean.rp':_rp, 'rpTypeBean.defScore':_defScore};
	var pageconfig = {
		ajaxDataType:'html',
		proxyUrl:'rpType_query.do',
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
function add_rpType(e){
	$("#dialog").dialog('open').html("正在加载页面...").load("rpType_add.do?mode=ajax");
}

//保存信息
function save_rpType(e){
	e.disabled = "true";
    //setFunctionValue();
	if(!$.formValidator.pageIsValid("1")){
		e.disabled = "";
		return;
	}
	var formData = $("#rpTypeSaveForm").serialize();
	$.ajax({
		type: "POST",
		url: "rpType_save.do?mode=ajaxJson",
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
function edit_rpType(id){
	$("#dialog").dialog('open').html("正在加载页面...").load("rpType_edit.do?mode=ajax&rpTypeId=" + id);
}

function update_rpType(e){
	e.disabled = "true";
	//setFunctionValue();//edit by weiwu
	if(!$.formValidator.pageIsValid("1")){
		e.disabled = "";
		return;
	}
	
	var formData = $("#rpTypeSaveForm").serialize();
	$.ajax({
		type: "POST",
		url: "rpType_update.do?mode=ajaxJson",
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

//删除奖惩类型
function del_rpType(name) {
	var id = getCheckboxValuesByName(name);
	if(id.length < 1){
		alert("请选择要删除的记录");
		return;
	}
	if(window.confirm("确定要删除选中的记录吗?")){
		$.getJSON("rpType_deletes.do", { rpTypeId: id }, function(json){
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
		height: 200,
		modal: true,
		close: function() {
			$(this).html('');
		}
	});
});

//查看用户信息
function view_rpType(id){
	$("#dialog").dialog('open').html("正在加载页面...").load("rpType_view.do?mode=ajax&rpTypeId=" + id);
}

function checkValue(){
	var rpName = $('#rpName').val();
	var defScore = $('#defScore').val();
	
	var pattern1 = /^([0-9a-zA-Z]|_){0,20}$|^[\u4E00-\u9FA5]{0,20}$/;
	
	if(!pattern1.test(rpName)){
		alert('您要查询的奖惩类型名称不合法，请重新输入');
		$('#rpName').val('');
		return false;
	}
	if(!(defScore>=0&&defScore<=100)){
		alert('您要查询的默认值不合法，请重新输入');
		$('#defScore').val('');
		return false;
	}
	
	return true;
	}

//关闭弹出对话框
function closeDialog(){
	$("#dialog").dialog('close');
}



