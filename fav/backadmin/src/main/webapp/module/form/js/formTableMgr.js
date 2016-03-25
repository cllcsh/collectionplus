
 //打开编辑页面
function edit_formTable(id){
	location.href("formTable_edit.do?id=" + id);
}

//打开查看页面
function view_formTable(id){
	location.href("formTable_view.do?id="+id);
}

function genCode(id){
	location.href("formTable_genCode.do?id="+id);
}

function genField(id){
	location.href("formTableField_init.do?tableId="+id);
}