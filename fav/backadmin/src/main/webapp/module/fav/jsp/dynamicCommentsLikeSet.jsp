<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/dynamicCommentsLikeMgr.js"></script>
<script type="text/javascript">
	<s:if test="%{actionName == 'dynamicCommentsLike_save'}">
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'dynamicCommentsLike_save.do',
			    saveUrlTo:'dynamicCommentsLike_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:if>
	<s:else>
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'dynamicCommentsLike_update.do',
			    saveUrlTo:'dynamicCommentsLike_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:else>
</script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<%@include file="source_browse.jsp"%>
<s:form id="setForm" name="dynamicCommentsLikeForm" action="%{actionName}">
	<s:hidden name="dynamicCommentsLikeForm.id"></s:hidden>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr>
					    <th width="20%" style="text-align:right;">评论来源：</th>
					    <td width="40%">
					    	<s:select id="sourceType" name="dynamicCommentsLikeForm.sourceType" list="sourceTypeMap" listKey="key" listValue="value" cssClass="easyui-validatebox" data-options="required:true" cssStyle="width:150px" onchange="changeSourceType();"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">来源：</th>
					    <td width="40%">
					        <input type="text" readonly="readonly" class="easyui-validatebox" data-options="required:true" id="sourceName" name="dynamicCommentsLikeForm.sourceName" value="<s:property value="dynamicCommentsLikeForm.sourceName"/>" onchange="changeSourceName();"/>
					    	<input type="hidden" class="easyui-validatebox" data-options="required:true" id="sourceId" name="dynamicCommentsLikeForm.sourceId" value="<s:property value="dynamicCommentsLikeForm.sourceId"/>"/>
					    	<input type="button" class="button" onclick="dialogForSourceBrowse('sourceId','sourceName');" value="浏览" />
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">好友：</th>
					    <td width="40%">
					    	<input type="text" readonly="readonly" class="easyui-validatebox" data-options="required:true" id="userName" name="dynamicCommentsLikeForm.userName" value="<s:property value="dynamicCommentsLikeForm.userName"/>"/>
					    	<input type="hidden" class="easyui-validatebox" data-options="required:true" id="friendId" name="dynamicCommentsLikeForm.friendId" value="<s:property value="dynamicCommentsLikeForm.friendId"/>"/>
					    	<input type="button" class="button" onclick="dialogForBrowse('friendId','userName','<%=request.getContextPath()%>/module/fav/favUser_queryForBrowse.do');" value="浏览" />
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">评论：</th>
					    <td width="40%">
					    	<input type="text" readonly="readonly" class="easyui-validatebox" data-options="required:true" id="commentContent" name="dynamicCommentsLikeForm.commentContent" value="<s:property value="dynamicCommentsLikeForm.commentContent"/>"/>
					    	<input type="hidden" id="commentId" name="dynamicCommentsLikeForm.commentId" value="<s:property value="dynamicCommentsLikeForm.commentId"/>"/>
					    	<input type="button" class="button" onclick="dialogForCommentBrowse('commentId','commentContent');" value="浏览" />
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">赞同反对：</th>
					    <td width="40%">
					    	<s:select id="type" name="dynamicCommentsLikeForm.type" list="typeMap" listKey="key" listValue="value" cssClass="easyui-validatebox" data-options="required:true" cssStyle="width:150px"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">赞同反对时间：</th>
					    <td width="40%">
					    	<input id="insertDate" class="easyui-validatebox" data-options="required:true" name="dynamicCommentsLikeForm.insertDate" value="<s:date name="dynamicCommentsLikeForm.insertDate" format="yyyy-MM-dd HH:mm:ss" />" type="text" cssStyle="width:150px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" />
					    </td>
					</tr>
					
					<tr>
						<td class="bottom" align="center" colspan="4">
							<div align="center">
								<s:if test="%{actionName == 'dynamicCommentsLike_save'}">
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
function changeSourceType(){
	$("#sourceName").val("");
	$("#sourceId").val("");
	$("#commentContent").val(" ");
}
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
	$("#commentContent").val(" ");
}
</script>

</body>
</html>