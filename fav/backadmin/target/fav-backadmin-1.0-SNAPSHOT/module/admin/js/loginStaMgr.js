/**
 * 用户管理页面脚本
 * @author zhangyan
 * @create 2009-11-4
 * @file loginLogMgr.js
 * @since v2.0
 */

//全局变量 
var _loginName = "";
var _loginIp = "";
var _loginResult = "";
var _loginFirDate = "";
var _loginEndDate = "";
//var _loginDate = "";
var _currPage = 1;		//保存当前页码
var _perPage = 10;

//查询
function query(){
	
	
	if(!checkValue()){
		return false;
	}
	
	
	_loginName = document.getElementById("loginName").value;// $("#loginName").val();
	_loginIp = document.getElementById("loginIp").value;
	_loginResult = document.getElementById("loginResult").value;
	//_loginDate = document.getElementById("loginDate").value;
	_loginFirDate = document.getElementById("loginFirDate").value;
	_loginEndDate = document.getElementById("loginEndDate").value;
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
//	var ev = window.event;
//	var target = ev.target || ev.srcElement;
//	disableButton(jQuery(target));
	var param = {'mode':"ajax",'loginLogForm.loginName':_loginName,'loginLogForm.loginIp':_loginIp,'loginLogForm.loginResult':_loginResult,'loginLogForm.loginFirDate':_loginFirDate,'loginLogForm.loginEndDate':_loginEndDate};
	var pageconfig = {
		ajaxDataType:'html',
		proxyUrl:'loginLog_query.do',
		currentPage:_currPage,
		perPage:_perPage,
		ajaxParam:param,
		barPosition:'bottom',
		showMode:'full',
		callback:function(perpage,reloadFn){
			_perPage = perpage;
			reload = reloadFn;
//			enableButton(jQuery(target));
		}
	};
	$("#pagecontent").pagination(pageconfig);
}


//删除用户


//删除日志
function del_logs(name) {
	var id = getCheckboxValuesByName(name);
	if(id.length < 1){
		alert("请选择要删除的记录");
		return;
	}
	if(window.confirm("确定要删除选中的记录吗?")){
		$.getJSON("loginLog_deletes.do", { userId: id }, function(json){
			alert(json.message);
			if(json.codeid == 0)
			loadPage();
//			reload();
		});
	}
}

function checkValue(){
	var loginName = $('#loginName').val();
	var loginIp = $('#loginIp').val();
	
	var pattern1 = /^([0-9a-zA-Z]|_){0,20}$/;
	var pattern2 = /^((2[0-4]\d|25[0-5]|[01]?\d\d?)\.){3}(2[0-4]\d|25[0-5]|[01]?\d\d?)$/;
	
	if(!pattern1.test(loginName)){
		alert('您要查询的登录帐号不合法，请重新输入');
		$('#loginName').val('');
		return false;
	}
	if(!pattern2.test(loginIp)&&!(loginIp.length<1)){
		alert('您要查询的IP不合法，请重新输入');
		$('#loginIp').val('');
		return false;
	}
	

	
	return true;
	
}

//清除输入文本框内容的前后空格
function clearBlank(widget){
	var messageBox = $(widget);
	var content = messageBox.val();
	messageBox.val($.trim(content));
}


function export_Query(){
	_loginName = document.getElementById("loginName").value;
	_loginIp = document.getElementById("loginIp").value;
	_loginResult = document.getElementById("loginResult").value;
	_loginFirDate = document.getElementById("loginFirDate").value;
	_loginEndDate = document.getElementById("loginEndDate").value;
	location.href = "loginSta_export.do?loginStaForm.loginName="+_loginName+"&loginStaForm.loginIp="+_loginIp+"&loginStaForm.loginFirDate="+_loginFirDate+"&loginStaForm.loginEndDate="+_loginEndDate;
}
