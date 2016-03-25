<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/innercolorMgr.js"></script>
<script type="text/javascript">
	<s:if test="%{actionName == 'innercolor_save'}">
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'innercolor_save.do',
			    saveUrlTo:'innercolor_init.do',
			    saveBtn:'btn_save'
			});
			choseBrand();
		});
	</s:if>
	<s:else>
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'innercolor_update.do',
			    saveUrlTo:'innercolor_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:else>
</script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<s:form id="setForm" name="innercolorForm" action="%{actionName}">
	<s:hidden name="innercolorForm.id"></s:hidden>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr>
						<th width="20%" style="text-align:right;">品牌：</th>
						<td width="40%">
							<s:if test="%{actionName == 'innercolor_save'}">
							<s:select id="pinyin" name="innercolorForm.pinyin" onchange="choseBrand();" list="#{'A':'A','B':'B','C':'C','D':'D','E':'E','F':'F','G':'G','H':'H','I':'I','J':'J','K':'K','L':'L','M':'M','N':'N','O':'O','P':'P','Q':'Q','R':'R','S':'S','T':'T','U':'U','V':'V','W':'W','X':'X','Y':'Y','Z':'Z'}"></s:select>&nbsp;
							<select id="brandId" name="innercolorForm.brandId" onchange="choseVersion();">
								<option value='0'>请选择品牌</option>
							</select>
							<input type="hidden" id="_brandId" value="<s:property value='innercolorForm.brandId'/>" />
							</s:if>
							<s:else>
							<s:property value="innercolorForm.brandName"/>
							<s:hidden name="innercolorForm.brandId"></s:hidden>
							</s:else>
						</td>
						<td width="40%">&nbsp;</td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">版本：</th>
						<td width="40%">
							<s:if test="%{actionName == 'innercolor_save'}">
							<select id="versionId" name="innercolorForm.versionId" onchange="choseSeries();">
								<option value='0'>请选择版本</option>
							</select>
							<input type="hidden" id="_versionId" value="<s:property value='innercolorForm.versionId'/>" />
							</s:if>
							<s:else>
							<s:property value="innercolorForm.versionName"/>
							<s:hidden name="innercolorForm.versionId"></s:hidden>
							</s:else>
						</td>
						<td width="40%">&nbsp;</td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">系列：</th>
						<td width="40%">
							<s:if test="%{actionName == 'innercolor_save'}">
							<select id="seriesId" name="innercolorForm.seriesId" onchange="choseModels();">
								<option value='0'>请选择系列</option>
							</select>
							<input type="hidden" id="_seriesId" value="<s:property value='innercolorForm.seriesId'/>" />
							</s:if>
							<s:else>
							<s:property value="innercolorForm.versionName"/>
							<s:hidden id="seriesId" name="innercolorForm.seriesId"></s:hidden>
							</s:else>
						</td>
						<td width="40%">&nbsp;</td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">车型：</th>
						<td width="40%">
							<s:if test="%{actionName == 'innercolor_save'}">
							<select id="modelsId" name="innercolorForm.modelsId" onchange="choseModelyear();">
								<option value='0'>请选择车型</option>
							</select>
							<input type="hidden" id="_modelsId" value="<s:property value='innercolorForm.modelsId'/>" />
							</s:if>
							<s:else>
							<s:property value="innercolorForm.modelsName"/>
							<s:hidden id="modelsId" name="innercolorForm.modelsId"></s:hidden>
							</s:else>
						</td>
						<td width="40%">&nbsp;</td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">年款<font style="color: red;">*</font>：</th>
						<td width="40%">
							<s:if test="%{actionName == 'innercolor_save'}">
							<select id="modelyearId" name="innercolorForm.modelyearId">
								<option value='0'>请选择年款</option>
							</select>
							<input type="hidden" id="_modelyearId" value="<s:property value='innercolorForm.modelyearId'/>" />
							</s:if>
							<s:else>
							<s:property value="innercolorForm.modelyearName"/>
							<s:hidden id="modelyearId" name="innercolorForm.modelyearId"></s:hidden>
							</s:else>
						</td>
						<td width="40%"><div id="modelyearIdTip"></div></td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">名称：</th>
						<td width="40%"><s:textfield id="name" name="innercolorForm.name"></s:textfield></td>
						<td width="40%"><div id="nameTip"></div></td>
					</tr>
					<tr>
						<td class="bottom" align="center" colspan="4">
							<div align="center">
								<s:if test="%{actionName == 'innercolor_save'}">
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
$("#name").formValidator( {
	empty :false,
	onshow :"请输入名称",
	onfocus :"名称长度1-30个字符",
	oncorrect :"输入合法",
	forcevalid :false
})
.inputValidator( {
	min :1,
	max :30,
	onerror :"你输入的名称长度非法，请确认"
});
//select验证
$("#modelyearId").formValidator({
  onshow: "请选择年款",
  onfocus: "请选择年款",
  oncorrect: "已选择年款"
}).inputValidator({
  min: 1,  //开始索引
  onerror: "请选择年款"
});
</script>

</body>
</html>