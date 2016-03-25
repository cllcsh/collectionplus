/**
 * 用户积分记录管理页面脚本
 * @author gaoxiang
 * @create 2015-11-10
 * @file userPointsRecordMgr.js
 * @since v0.1
 */

 //打开编辑用户积分记录信息页面
function edit_userPointsRecord(id){
	location.href = "userPointsRecord_edit.do?id=" + id;
}

//打开查看用户积分记录信息页面
function view_userPointsRecord(id){
	location.href = "userPointsRecord_view.do?id="+id;
}

//打开查看任务积分设置信息页面
function view_taskPointsConfig(id){
	location.href = "taskPointsConfig_view.do?id="+id;
}

//打开查看用户信息页面
function view_favUser(id){
	location.href = "favUser_view.do?id="+id;
}