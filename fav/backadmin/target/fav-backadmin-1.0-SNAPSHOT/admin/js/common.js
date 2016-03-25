/** ******************* 公有JS ******************************* */
//判断是否为浮点类型
function isFloat(str) {
	var reg = /^(-|\+)?\d+\.\d*$/;
	return reg.test(str);
}
//判断是否为合法的IP地址格式
function isIp(str){
	var reg = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
	return reg.test(str);
}
//显示错误信息
function showErrorMsg(msg){
    var $msg_div =$("div.errormsg");
    $msg_div.find("strong").text(msg);
    $msg_div.show();
    setTimeout(function(){$msg_div.hide(500)}, 2000);
}