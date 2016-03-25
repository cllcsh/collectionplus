/**
 * 实时监控页面脚本
 * @author zhangneng
 * @create 2013-06-28
 * @file actualMgr.js
 * @since v0.1
 */

 //打开编辑实时监控页面
function edit_actual(id){
	location.href("actual_edit.do?id=" + id);
}

//打开查看实时监控页面
function view_actual(id){
	location.href("actual_view.do?id="+id);
}

function view_locationinfo(locationId){
	window.open(_contextPath+'/module/map/locaQuery_mapView.do?displayWay=0&locationId='+locationId,'newwindow','width=600,height=500, top=150, left=200');
}

function export_Query(){
	var deptId = $("#dept_id").val();
	var criminalName = $("#name").val();
	var sex = document.getElementsByName("actualForm.sex").value;
	var status = document.getElementsByName("actualForm.status").value;
	var locCode = $("#locCode").val();

	location.href = "actual_export.do?actualForm.deptId="+deptId+"&actualForm.criminalName="+criminalName+"&actualForm.sex="+sex+"&actualForm.status="+status+"&actualForm.locCode="+locCode;
}

function mapView(name) {
	var id = getCheckboxValuesByName(name);
	if(id.length < 1){
		alert("请选择要显示的记录");
		return;
	}
	window.open(_contextPath+'/module/map/locaQuery_mapView.do?terminalIds='+id,'newwindow','width=600,height=500, top=150, left=200');
}

