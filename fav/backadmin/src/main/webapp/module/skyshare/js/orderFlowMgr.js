/**
 * 订单流程管理页面脚本
 * @author lif
 * @create 2014-06-24
 * @file orderFlowMgr.js
 * @since v0.1
 */

 //打开编辑订单流程信息页面
function edit_orderFlow(id){
	location.href("orderFlow_edit.do?id=" + id);
}

//打开查看订单流程信息页面
function view_orderFlow(id){
	location.href("orderFlow_view.do?id="+id);
}