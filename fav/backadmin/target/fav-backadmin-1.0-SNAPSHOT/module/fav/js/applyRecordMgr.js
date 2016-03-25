/**
 * 审批记录管理页面脚本
 * @author gaoxiang
 * @create 2015-11-08
 * @file applyRecordMgr.js
 * @since v0.1
 */

 //打开编辑审批记录信息页面
function edit_applyRecord(id){
	location.href = "applyRecord_edit.do?id=" + id;
}

//打开查看审批记录信息页面
function view_applyRecord(id){
	location.href = "applyRecord_view.do?id="+id;
}

//打开查看藏品信息页面
function view_collection(id){
	location.href = "collection_view.do?id="+id;
}

//打开查看用户信息页面
function view_favUser(id){
	location.href = "favUser_view.do?id="+id;
}