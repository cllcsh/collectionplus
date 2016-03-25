/**
 * 用户权限管理页面脚本
 * @author lif
 * @create 2010-02-26
 * @file userPermissionMgr.js
 * @since v0.1
 */

//在页面对话框中编辑用户权限信息
function edit_userPermission(id)
{
	location.href="userPermission_edit.do?id="+id;
}

//在页面对话框中查看用户权限信息
function view_userPermission(id)
{
	location.href="userPermission_view.do?id="+id;
}

//删除多个用户权限信息
function del_userPermissions(name) {
	var id = getCheckboxValuesByName(name);
	if(id.length < 1){
		alert("请选择要删除的记录");
		return;
	}
	if(window.confirm("确定要删除选中的记录吗?")){
		$.getJSON("userPermission_deletes.do", { ids: id, mode:'ajaxJson' }, function(json){
			alert(json.message);
			if(json.codeid == 0)
			   reload();
		});
	}
}