	//取cookies函数   
function getCookie(name) {
	var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
	if (arr != null) {
		return unescape(arr[2]);
	}
	return null;
}
    //删除cookie
function delCookie(name) {
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
	var cval = getCookie(name);
	if (cval != null) {
		document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
	}
}

function SetCookie(name, value, expires, path, domain, secure) {
	var expDays = expires * 24 * 60 * 60 * 1000;
	var expDate = new Date();
	expDate.setTime(expDate.getTime() + expDays);
	var expString = ((expires == null) ? "" : (";expires=" + expDate.toGMTString()));
	var pathString = ((path == null) ? "" : (";path=" + path));
	var domainString = ((domain == null) ? "" : (";domain=" + domain));
	var secureString = ((secure == true) ? ";secure" : "");
	document.cookie = name + "=" + escape(value) + expString + pathString + domainString + secureString;
}

