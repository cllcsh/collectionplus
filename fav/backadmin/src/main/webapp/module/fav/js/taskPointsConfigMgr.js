/**
 * 任务积分设置管理页面脚本
 * @author gaoxiang
 * @create 2015-11-06
 * @file taskPointsConfigMgr.js
 * @since v0.1
 */

 //打开编辑任务积分设置信息页面
function edit_taskPointsConfig(id){
	location.href = "taskPointsConfig_edit.do?id=" + id;
}

//打开查看任务积分设置信息页面
function view_taskPointsConfig(id){
	location.href = "taskPointsConfig_view.do?id="+id;
}