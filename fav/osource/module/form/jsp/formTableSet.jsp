<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>
<%@taglib prefix="ict" uri="/ict-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/formTableMgr.js"></script>
<script type="text/javascript">
	<s:if test="%{actionName == 'formTable_save'}">
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'formTable_save.do',
			    saveUrlTo:'formTable_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:if>
	<s:else>
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'formTable_update.do',
			    saveUrlTo:'formTable_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:else>
</script>
</head>

<body>
<s:form id="setForm" name="formTableForm" action="%{actionName}">
	<s:hidden name="formTableForm.id"></s:hidden>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr>
					    <th  width="15%">模块名称</th>
					    <td width="35%"><s:textfield id="moduleName" name="formTableForm.moduleName"
										maxlength="50" cssStyle="width:150px" /><font class="redStar">*</font><div id="moduleNameTip"></div>
						</td>
						<th width="15%">作者</th>
						<td width="35%"><s:textfield id="author" name="formTableForm.author"
										maxlength="20" cssStyle="width:150px" /><font class="redStar">*</font><div id="authorTip"></div>
						</td>
					</tr>
					<tr>
					    <th width="15%">实体JAVABean名称</th>
					    <td width="35%"><s:textfield id="entityBean" name="formTableForm.entityBean"
										maxlength="20" cssStyle="width:150px" /><font class="redStar">*</font><div id="entityBeanTip"></div>
						</td>
					    
					    <th width="15%">实体名称</th>
					    <td width="35%"><s:textfield id="entity" name="formTableForm.entity"
										maxlength="20" cssStyle="width:150px" /><font class="redStar">*</font><div id="entityTip"></div>
						</td>
					</tr>
					<tr>
					  <th>存储表名</th>
					  <td colspan="3"><s:textfield id="tableName" name="formTableForm.tableName"
										maxlength="50" cssStyle="width:150px" /><font class="redStar">*</font><div id="tableNameTip"></div>
					  </td>
					</tr>
					<tr>
						<td class="bottom" align="center" colspan="4">
							<div align="center">
								<s:if test="%{actionName == 'formTable_save'}">
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
</body>

<script type="text/javascript">
	$.formValidator.initConfig( {
	formid :"setForm",
	onerror : function(msg) {
		alert(msg)
	},
	onsuccess : function() {
		return true;
	}
	});

	$("#moduleName").formValidator( {
		onshow :"请输入功能模块的中文名称",
		oncorrect :"输入合法"
	}).inputValidator( {
		min :1,
		max :50,
		onerror :"请输入功能模块的中文名称(1-50字符)"
	});
	$("#author").formValidator( {
		onshow :"请输入代码编写者",
		oncorrect :"输入合法"
	}).inputValidator( {
		min :1,
		max :20,
		onerror :"请输入代码编写者(1-20字符)"
	});
	$("#entityBean").formValidator( {
		onshow :"请输入JAVA实体类的名称",
		oncorrect :"输入合法"
	}).inputValidator( {
		min :1,
		max :20,
		onerror :"请输入JAVA实体类的名称(1-20字符)"
	});
	$("#entity").formValidator( {
		onshow :"请输入模块实体名称",
		oncorrect :"输入合法"
	}).inputValidator( {
		min :1,
		max :20,
		onerror :"请输入模块实体的名称(1-20字符)"
	});
	$("#tableName").formValidator( {
		onshow :"请输入数据库表名",
		oncorrect :"输入合法"
	}).inputValidator( {
		min :1,
		max :50,
		onerror :"请输入数据库表名(1-20字符)"
	});
</script>
</html>