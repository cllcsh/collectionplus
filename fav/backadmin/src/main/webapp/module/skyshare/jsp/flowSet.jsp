<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/flowMgr.js"></script>
<script type="text/javascript">
	<s:if test="%{actionName == 'flow_save'}">
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'flow_save.do',
			    saveUrlTo:'flow_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:if>
	<s:else>
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'flow_update.do',
			    saveUrlTo:'flow_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:else>
</script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<s:form id="setForm" name="flowForm" action="%{actionName}">
	<s:hidden name="flowForm.id"></s:hidden>
	<s:hidden name="flowForm.node"></s:hidden>
	<s:hidden name="flowForm.deptId"></s:hidden>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr>
						<th width="15%" > 
							编号：
						</th>
						<td colspan="2" width="40%" >  
							<s:textfield size="20" id="flowCode" cssClass="td03"
										name="flowForm.code" maxlength="60"
										onblur="clearBlank(this);" />
							<font class="redStar">*</font>
						</td>
						<td>
							<div id="flowCodeTip"></div>
						</td>
					</tr>
					<tr>
						<th  width="15%">
							名称： 
						</th>
						<td colspan="2">
							<s:textfield size="20" id="flowName" cssClass="td03"
										name="flowForm.name" maxlength="60"
										onblur="clearBlank(this);" />
						</td>
						<td>
							<div id="flowNameTip"></div>
						</td>
					</tr>
					<tr>
						<th  width="15%">
							父流程：
						</th>
						<td colspan="2">
							<s:select id="parentFlowId" name="flowForm.parentId" list="flowList" listKey="key"
												listValue="value" value="%{flowForm.parentId}" cssStyle="width:155px"></s:select>
						</td>
						<td>
							<div id="parentFlowIdTip"></div>
						</td>
					</tr>
					<tr>
	                    <th align="right">显示顺序：</th>
	                    <td colspan="2">
                            <s:textfield name="flowForm.viewOrder" id="viewOrder"
                                         size="20" cssClass="td03 numOnly" maxlength="6" onblur="clearBlank(this);"/>
	                    </td>
						<td>
							<div id="viewOrderTip"></div>
						</td>
                	</tr>
					
					<tr>
						<td class="bottom" align="center" colspan="4">
							<div align="center">
								<s:if test="%{actionName == 'flow_save'}">
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