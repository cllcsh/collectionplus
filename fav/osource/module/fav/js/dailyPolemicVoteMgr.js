/**
 * 天天论战投票管理页面脚本
 * @author gaoxiang
 * @create 2015-11-10
 * @file dailyPolemicVoteMgr.js
 * @since v0.1
 */

 //打开编辑天天论战投票信息页面
function edit_dailyPolemicVote(id){
	location.href = "dailyPolemicVote_edit.do?id=" + id;
}

//打开查看天天论战投票信息页面
function view_dailyPolemicVote(id){
	location.href = "dailyPolemicVote_view.do?id="+id;
}