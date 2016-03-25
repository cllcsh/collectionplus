var ShareTip = function() {
}
// 分享到腾讯微博
ShareTip.prototype.sharetoqq = function(content, url, picurl) {
	var shareqqstring = 'http://v.t.qq.com/share/share.php?title=' + content
			+ '&url=' + url + '&pic=' + picurl;
	window.open(shareqqstring, 'newwindow',
			'height=400,width=400,top=100,left=100');
}
// 分享到新浪微博
ShareTip.prototype.sharetosina = function(title, url, picurl) {
	var sharesinastring = 'http://v.t.sina.com.cn/share/share.php?title='
			+ title + '&url=' + url + '&content=utf-8&sourceUrl=' + url
			+ '&pic=' + picurl;
	window.open(sharesinastring, 'newwindow',
			'height=400,width=400,top=100,left=100');
}
// 分享到QQ空间
ShareTip.prototype.sharetoqqzone = function(title, url, picurl) {
	var shareqqzonestring = 'http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?summary='
			+ title + '&url=' + url + '&pics=' + picurl;
	window.open(shareqqzonestring, 'newwindow',
			'height=400,width=400,top=100,left=100');
}

//var shareUrl = window.location.href;
var shareUrl = "http://120.132.92.55/fv/";

function shareclntoqq(id, title, imgUrl) {
	var share1 = new ShareTip();
	//var reqURL = shareUrl + "collectionDetail?id=" + id;
	var reqURL = window.location.href;
	share1.sharetoqq(title, encodeURIComponent(reqURL), encodeURIComponent(imgUrl));
}
function shareclntosina(id, title, imgUrl) {
	var share1 = new ShareTip();
	//var reqURL = shareUrl + "collectionDetail?id=" + id;
	var reqURL = window.location.href;
	share1.sharetosina(title, encodeURIComponent(reqURL), encodeURIComponent(imgUrl));
}
function shareclntoqqzone(id, title, imgUrl) {
	var share1 = new ShareTip();
	//var reqURL = shareUrl + "collectionDetail?id=" + id;
	var reqURL = window.location.href;
	share1.sharetoqqzone(title, encodeURIComponent(reqURL), encodeURIComponent(imgUrl));
}

function shareusertoqq(id, title, imgUrl) {
	var share1 = new ShareTip();
	var reqURL = shareUrl + "shareUserInfo?id=" + id;
	share1.sharetoqq(title, encodeURIComponent(reqURL), encodeURIComponent(imgUrl));
}
function shareusertosina(id, title, imgUrl) {
	var share1 = new ShareTip();
	var reqURL = shareUrl + "shareUserInfo?id=" + id;
	share1.sharetosina(title, encodeURIComponent(reqURL), encodeURIComponent(imgUrl));
}
function shareusertoqqzone(id, title, imgUrl) {
	var share1 = new ShareTip();
	var reqURL = shareUrl + "shareUserInfo?id=" + id;
	share1.sharetoqqzone(title, encodeURIComponent(reqURL), encodeURIComponent(imgUrl));
}
