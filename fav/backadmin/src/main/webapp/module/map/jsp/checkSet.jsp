<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>
<%@taglib prefix="ict" uri="/ict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/checkMgr.js"></script>
<script type="text/javascript">
	<s:if test="%{actionName == 'check_save'}">
		$(document).ready(function(){
			$(document).ict({
				saveForm:'checkForm',
			    saveUrl:'check_save.do',
			    saveUrlTo:'check_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:if>
	<s:else>
		$(document).ready(function(){
			$(document).ict({
				saveForm:'checkForm',
			    saveUrl:'check_update.do',
			    saveUrlTo:'check_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:else>

	function checkNum()
	{
		if(!(((window.event.keyCode >= 48) && (window.event.keyCode <= 57))||window.event.keyCode == 46))
		{
		   window.event.keyCode = 0 ;
		}
		return;
	}
</script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<s:form id="checkForm" name="checkForm">
	<s:hidden name="checkForm.id"></s:hidden>
	<s:if test="%{actionName != 'check_save'}">
		<s:hidden id="criminalId" name="checkForm.criminalId"></s:hidden>
	</s:if>
	<s:hidden name="actionName" id="actionName"></s:hidden>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr>
						<th width="15%" align="right">抽查对象：</th>
					    <td width="35%">
					    	<s:if test="%{actionName == 'check_save'}">
						    	<ict:select beanContextId="criminalSelect" id="criminalId" name="checkForm.criminalId" cssStyle="width:200px" onchange="findPhone();"/>
					    	</s:if>
					    	<s:else>
					    		<ict:property  beanContextId="tb_criminal" value="checkForm.criminalId"/>
					    	</s:else>
					    </td>
					    <th width="15%">
					    	电话：
					    </th>
					    <td width="35%">
							<s:textfield cssStyle="width:195px" id="phone" cssClass="td03" readonly="true"
										name="checkForm.phone" maxlength="20"
										onblur="clearBlank(this);" />
					    </td>
					</tr>
					<tr>
					    <th width="15%" align="right">抽查日期：</th>
						<td width="35%">
							<s:textfield id="checkDate" cssStyle="width:200px" readonly="true" name="checkForm.checkDate"   size="25"  cssClass="Wdate"></s:textfield>
							<font class="redStar">*</font>
							<div id="checkDateTip"></div>
						</td>
						<th>
							抽查类型：
						</th>
						<td>
							<dict:select codeType="check_result" id="checkResult" name="checkForm.checkResult" emptyOption="true" cssStyle="width:200px"/>
							<font class="redStar">*</font>
							<div id="checkResultTip"></div>
						</td>
					</tr>
					<tr>
						<th>
							分值：
						</th>
						<td colspan="3">
							<s:textfield cssStyle="width:195px" id="cent" cssClass="td03" onkeypress="checkNum();"
										name="checkForm.cent" maxlength="6"
										onblur="clearBlank(this);" />
										<font class="redStar">*</font>
							<div id="centTip"></div>
						</td>
					</tr>
					<tr>
						<th width="15%" align="right">抽查内容：</th>
						<td colspan="3"><s:textarea id="checkExplain" name="checkForm.checkExplain" cols="100" rows="5"></s:textarea>
							<div id="checkExplainTip"></div>
						</td>
					</tr>
					<tr>
						<th width="15%" align="right">备注：</th>
						<td colspan="3"><s:textarea id="remark" name="checkForm.remark" cols="100" rows="5"></s:textarea>
							<div id="remarkTip"></div>
						</td>
					</tr>
					<tr>
						<td class="bottom" align="center" colspan="4">
							<div align="center">
								<s:if test="%{actionName == 'check_save'}">
									<input type="button" id="btn_save" class="button2" value="发送消息" />
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
if(document.getElementById("actionName").value == "check_save")
{
	var criminalId = document.getElementById("criminalId");
	if(criminalId.options.length == 0)
	{
		alert("对不起！您今天不能抽查<s:text name="ictmap.tittle"/>！");
		location.href="check_init.do";
	}
	else
	{
		var random = Math.floor(Math.random()*criminalId.options.length);
		criminalId.options[random].selected = true;
		findPhone();
	}
}
else
{
	findPhone();
}


$.formValidator.initConfig( {
	formid :"checkForm",
	onerror : function(msg) {
		alert(msg);
	},
	onsuccess : function() {
		return true;
	}
});


$("#checkResult").formValidator({
	onshow:"请选择抽查类型",
    onfocus:"请选择抽查类型",
    oncorrect:"选择合法"
}).inputValidator({
	min:1,
	onerror:"必须选择抽查类型"
});

$("#cent").formValidator( {
	onshow :"请输入分值",
	onfocus :"必须输入分值,小于100，且最多两位小数",
	oncorrect :"输入合法"
}).inputValidator({
	min:1,
	onerror:"必须输入分值"
}).regexValidator( {
	regexp:"^(0|[1-9]\\d{0,1})(\\.\\d{1,2})?$",
	onerror:"分值不正确"
});

$("#checkExplain").formValidator( {
	onshow :"抽查内容",
	onfocus :"最多1333汉字或4000个英文字符",
	oncorrect :"抽查内容输入合法"
}).inputValidator( {
	min :0,
	max :4000,
	onerror :"抽查内容(0-1333)个汉字或(0-4000)个英文字符"
});
$("#remark").formValidator( {
	onshow :"备注",
	onfocus :"最多1333汉字或4000个英文字符",
	oncorrect :"备注输入合法"
}).inputValidator( {
	min :0,
	max :4000,
	onerror :"备注(0-1333)个汉字或(0-4000)个英文字符"
});
</script>

</body>
</html>