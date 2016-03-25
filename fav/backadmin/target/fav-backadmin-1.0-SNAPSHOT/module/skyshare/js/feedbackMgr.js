/**
 * 用户反馈管理页面脚本
 * @author lif
 * @create 2014-03-26
 * @file feedbackMgr.js
 * @since v0.1
 */

 //打开编辑用户反馈信息页面
function edit_feedback(id){
	//location.href("feedback_edit.do?id=" + id);
	window.location="feedback_edit.do?id=" + id;
}

//打开查看用户反馈信息页面
function view_feedback(id){
	//location.href("feedback_view.do?id="+id);
	window.location="feedback_view.do?id=" + id;
}