/**
 * 在线统计页面脚本
 * @author luoj
 * @create 2010-01-07
 * @file onlineMgr.js
 * @since v0.1
 */
function doSchedule() {
	setInterval("doGetOnlineUsers();",1000 * 60 * 2);
}

function doGetOnlineUsers() {
	location.href("online_view.do");
}