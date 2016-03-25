/**
 * 热度管理页面脚本
 * @author gaoxiang
 * @create 2015-11-10
 * @file heatMgr.js
 * @since v0.1
 */

 //打开编辑热度信息页面
function edit_heat(id){
	location.href = "heat_edit.do?id=" + id;
}

//打开查看热度信息页面
function view_heat(id){
	location.href = "heat_view.do?id="+id;
}