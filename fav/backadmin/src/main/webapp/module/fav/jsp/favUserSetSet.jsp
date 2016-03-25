<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/favUserSetMgr.js"></script>
<script type="text/javascript">
	<s:if test="%{actionName == 'favUserSet_save'}">
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'favUserSet_save.do',
			    saveUrlTo:'favUserSet_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:if>
	<s:else>
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'favUserSet_update.do',
			    saveUrlTo:'favUserSet_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:else>
</script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<s:form id="setForm" name="favUserSetForm" action="%{actionName}">
	<s:hidden name="favUserSetForm.id"></s:hidden>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
<tr>
    <th width="20%" style="text-align:right;">用户id：</th>
    <td width="40%">
        <input type="text" class="easyui-validatebox" data-options="required:true" name="favUserSetForm.userId" value="<s:property value="favUserSetForm.userId"/>"/>
    </td>
</tr>
<tr>
    <th width="20%" style="text-align:right;">好友用户id：</th>
    <td width="40%">
        <input type="text" class="easyui-validatebox" data-options="required:true" name="favUserSetForm.friendId" value="<s:property value="favUserSetForm.friendId"/>"/>
    </td>
</tr>
<tr>
    <th width="20%" style="text-align:right;">是否屏蔽私信  1:屏蔽;0:不屏蔽：</th>
    <td width="40%">
        <input type="text" class="easyui-validatebox" data-options="required:true" name="favUserSetForm.blockMsg" value="<s:property value="favUserSetForm.blockMsg"/>"/>
    </td>
</tr>
<tr>
    <th width="20%" style="text-align:right;">是否屏蔽话题  1:屏蔽;0:不屏蔽：</th>
    <td width="40%">
        <input type="text" class="easyui-validatebox" data-options="required:true" name="favUserSetForm.blockDynamic" value="<s:property value="favUserSetForm.blockDynamic"/>"/>
    </td>
</tr>
<tr>
    <th width="20%" style="text-align:right;">是否屏蔽回复  1:屏蔽;0:不屏蔽：</th>
    <td width="40%">
        <input type="text" class="easyui-validatebox" data-options="required:true" name="favUserSetForm.blockReply" value="<s:property value="favUserSetForm.blockReply"/>"/>
    </td>
</tr>
<tr>
    <th width="20%" style="text-align:right;">是否屏蔽评论  1:屏蔽;0:不屏蔽：</th>
    <td width="40%">
        <input type="text" class="easyui-validatebox" data-options="required:true" name="favUserSetForm.blockComment" value="<s:property value="favUserSetForm.blockComment"/>"/>
    </td>
</tr>

					<tr>
						<td class="bottom" align="center" colspan="4">
							<div align="center">
								<s:if test="%{actionName == 'favUserSet_save'}">
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
</script>

</body>
</html>
