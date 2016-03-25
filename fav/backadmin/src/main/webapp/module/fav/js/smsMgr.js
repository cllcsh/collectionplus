/**
 * 短信管理页面脚本
 * @author gaoxiang
 * @create 2015-11-10
 * @file smsMgr.js
 * @since v0.1
 */

 //打开编辑短信信息页面
function edit_sms(id){
	location.href = "sms_edit.do?id=" + id;
}

//打开查看短信信息页面
function view_sms(id){
	location.href = "sms_view.do?id="+id;
}