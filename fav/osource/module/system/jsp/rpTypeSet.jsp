<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>
<html>
<head>
<%@include file="/jsp/meta.jsp"%>
	<style type="text/css">
	</style>
	<script type="text/javascript">
	</script>
<title><s:property value="jsp_head_title"/></title>
</head>
	<body style="overflow: hidden">
		<s:fielderror />
		<s:form id="rpTypeSaveForm" name="rpTypeSaveForm" action="%{actionName}">
			<s:hidden name="rpTypeBean.id"></s:hidden>
			<table class="bg100" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td>
						<table class="tb_add" width="100%" border="0" cellpadding="1"
							cellspacing="1">
							<tr>
								<td class="td_title" colspan="3" align="center">
									奖惩类型编辑：
								</td>
							</tr>
							<tr>
								<th class="tb_lable" width="20%" align="right">
									奖惩类型名称：
								</th>
								<td width="40%">
									<s:textfield name="rpTypeBean.rpName" cssClass="input2" size="35" onblur="clearBlank(this);"></s:textfield>
									<font class="redStar">*</font>
								</td>
								<td width="40%">
									<div id="rpTypeSaveForm_rpTypeBean_rpNameTip"></div>
								</td>
							</tr>
							
							<tr>
								<th align="right">
									默认值：
								</th>
								<td>
									<s:textfield name="rpTypeBean.defScore" cssClass="input2" size="35" onblur="clearBlank(this);"></s:textfield>
								</td>
								<td>
									<div id="rpTypeSaveForm_rpTypeBean_defScoreTip"></div>
								</td>
							</tr>
							
							<tr>
								<th>
									奖惩类型：
								</th>
								<td>
								  <dict:select name="rpTypeBean.rp" id="rp"  codeType="common-rp_type" emptyOption="false"> </dict:select>
								</td>
								
						      <td><div id="rpTypeSaveForm_rpTypeBean__rpTip"></div></td>
							</tr>
							

							<tr>
								<td class="bottom" align="center" colspan="3">
									<div align="center">
										<s:if test="%{actionName == 'rpType_save'}">
											<input type="button" onclick="javascript:save_rpType(this);"
													id="button" class="button"
													onmouseout="this.className = 'button'"
													onmouseover="this.className = 'buttonOver'"  value="增加" />
										</s:if>
										<s:else>
											<input type="button" onclick="javascript:update_rpType(this);"
													id="button" class="button"
													onmouseout="this.className = 'button'"
													onmouseover="this.className = 'buttonOver'" value="保存" />
										</s:else>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</s:form>

		<script type="text/javascript">
		
	$.formValidator.initConfig( {
		formid :"rpTypeForm",
		onerror : function(msg) {
			alert(msg)
		},
		onsuccess : function() {
			return true;
		}
	});

	$("#rpTypeSaveForm_rpTypeBean_rpName").formValidator( {
		onshow :"请输入奖惩类型名称",
		onfocus :"最多20个汉字",
		oncorrect :"奖惩类型名称合法"
	}).regexValidator( {
		regexp :"^[\u4E00-\u9FA5|A-Z|a-z|0-9|_]{1,60}$",
		onerror :"奖惩类型名称 输入格式或长度错误"
	}).inputValidator( {
		min :1,
		max :60,
		onerror :"请确认奖惩类型名称长度"
	});
	
	$("#rpTypeSaveForm_rpTypeBean_defScore").formValidator( {
		onshow :"请输入默认分值",
		oncorrect :"输入正确"
	});
	
</script>

	</body>
</html>