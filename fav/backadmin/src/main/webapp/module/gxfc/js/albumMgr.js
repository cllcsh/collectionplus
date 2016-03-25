/**
 * 焦点图管理页面脚本
 * @author pachong
 * @create 2015-07-29
 * @file albumMgr.js
 * @since v0.1
 */

//打开编辑焦点图信息页面
function add_album(){
	location.href = "album_add.do";
}

 //打开编辑焦点图信息页面
function edit_album(id){
	location.href = "album_edit.do?id=" + id;
}

//打开查看焦点图信息页面
function view_album(id){
	location.href = "album_view.do?id="+id;
}