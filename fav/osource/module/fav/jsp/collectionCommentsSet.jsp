<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/collectionCommentsMgr.js"></script>
<script type="text/javascript">
	<s:if test="%{actionName == 'collectionComments_save'}">
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'collectionComments_save.do',
			    saveUrlTo:'collectionComments_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:if>
	<s:else>
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'collectionComments_update.do',
			    saveUrlTo:'collectionComments_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:else>
</script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<%@include file="source_browse.jsp"%>
<s:form id="setForm" name="collectionCommentsForm" action="%{actionName}">
	<s:hidden name="collectionCommentsForm.id"></s:hidden>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr>
					    <th width="20%" style="text-align:right;">评论来源：</th>
					    <td width="40%">
					    	<s:select id="sourceType" name="collectionCommentsForm.sourceType" list="sourceTypeMap" listKey="key" listValue="value" cssClass="easyui-validatebox" data-options="required:true" cssStyle="width:150px" onchange="changeSourceType();"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">来源：</th>
					    <td width="40%">
					        <input type="text" readonly="readonly" class="easyui-validatebox" data-options="required:true" id="sourceName" name="collectionCommentsForm.sourceName" value="<s:property value="collectionCommentsForm.sourceName"/>" onchange="changeSourceName();"/>
					    	<input type="hidden" class="easyui-validatebox" data-options="required:true" id="sourceId" name="collectionCommentsForm.sourceId" value="<s:property value="collectionCommentsForm.sourceId"/>"/>
					    	<input type="button" class="button" onclick="dialogForSourceBrowse('sourceId','sourceName');" value="浏览" />
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">好友：</th>
					    <td width="40%">
					    	<input type="text" readonly="readonly" class="easyui-validatebox" data-options="required:true" id="userName" name="collectionCommentsForm.userName" value="<s:property value="collectionCommentsForm.userName"/>"/>
					    	<input type="hidden" class="easyui-validatebox" data-options="required:true" id="friendId" name="collectionCommentsForm.friendId" value="<s:property value="collectionCommentsForm.friendId"/>"/>
					    	<input type="button" class="button" onclick="dialogForBrowse('friendId','userName','<%=request.getContextPath()%>/module/fav/favUser_queryForBrowse.do');" value="浏览" />
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">评论内容：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true" name="collectionCommentsForm.content" value="<s:property value="collectionCommentsForm.content"/>"/>
					    </td>
					</tr>
					<!--<tr>
					    <th width="20%" style="text-align:right;">评分：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true" name="collectionCommentsForm.point" value="<s:property value="collectionCommentsForm.point"/>"/>
					    </td>
					</tr>-->
					<tr>
					    <th width="20%" style="text-align:right;">评论发表时间：</th>
					    <td width="40%">
					   		<input class="easyui-validatebox" data-options="required:true" id="commentTime" name="collectionCommentsForm.commentTime" value="<s:date name="collectionCommentsForm.commentTime" format="yyyy-MM-dd HH:mm:ss" />" type="text" cssStyle="width:150px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" />
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">评论类型：</th>
					    <td width="40%">
					    	<s:select id="type" name="collectionCommentsForm.type" list="typeMap" listKey="key" listValue="value" cssClass="easyui-validatebox" data-options="required:true" cssStyle="width:150px" onchange="changeType();"/>
					    </td>
					</tr>
					<tr id="replyTR" style="display: none">
					    <th width="20%" style="text-align:right;">回复对应的评论：</th>
					    <td width="40%">
					    	<input type="text" readonly="readonly" class="easyui-validatebox" data-options="validType:'replyType'" id="replyContent" name="collectionCommentsForm.replyContent" value="<s:property value="collectionCommentsForm.replyContent"/>"/>
					    	<input type="hidden" id="replyId" name="collectionCommentsForm.replyId" value="<s:property value="collectionCommentsForm.replyId"/>"/>
					    	<input type="button" class="button" onclick="dialogForCommentBrowse('replyId','replyContent');" value="浏览" />
					    </td>
					</tr>
					<tr>
						<td class="bottom" align="center" colspan="4">
							<div align="center">
								<s:if test="%{actionName == 'collectionComments_save'}">
									<input type="button" id="btn_save" class="button" value="增加" />
								</s:if>
								<s:else>
									<input type="button" id="btn_save" class="button" value="保存" />
								</s:else>
								<input type="button" onclick="javascript:history.back();" class="button" value="返回"/>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</s:form>
<script type="text/javascript">
$.formValidator.initConfig({
	formid:"setForm",
	onerror:function(msg){
	alert(msg)},
	onsuccess:function(){
		return true;
}});
changeType();
function dialogForSourceBrowse(sourceId,sourceName){
	if ($("#sourceType").val() == '0'){
		dialogForBrowse(sourceId,sourceName,'<%=request.getContextPath()%>/module/fav/collection_queryForBrowse.do');
	}else{
		dialogForBrowse(sourceId,sourceName,'<%=request.getContextPath()%>/module/fav/dynamic_queryForBrowse.do');
	}
}

function dialogForCommentBrowse(sourceId,sourceName){
	if ($.trim($("#sourceName").val()) == ""){
		alert("请先选择来源");
		return;
	}
	var sourceType = $("#sourceType").val();
	var sourceIdVar = $("#sourceId").val();
	dialogForBrowse(sourceId,sourceName,'<%=request.getContextPath()%>/module/fav/collectionComments_queryForBrowse.do?sourceType=' + sourceType + "&sourceId=" + sourceIdVar);
}

function changeSourceName(){
	$("#replyContent").val(" ");
}

function changeType(){
	if ($("#type").val() == '0'){
		$("#replyTR").hide();
	}else{
		if ($.trim($("#replyContent").val()) == ""){
			$("#replyContent").val(" ");
		}
		$("#replyTR").show();
	}
}

function changeSourceType(){
	$("#sourceName").val("");
	$("#sourceId").val("");
	$("#replyContent").val(" ");
}
</script>

</body>
</html>