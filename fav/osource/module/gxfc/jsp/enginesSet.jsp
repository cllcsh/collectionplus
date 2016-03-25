<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/enginesMgr.js"></script>
<script type="text/javascript">
	<s:if test="%{actionName == 'engines_save'}">
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'engines_save.do',
			    saveUrlTo:'engines_init.do',
			    saveBtn:'btn_save'
			});
			choseBrand();
		});
	</s:if>
	<s:else>
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'engines_update.do',
			    saveUrlTo:'engines_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:else>
</script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<s:form id="setForm" name="enginesForm" action="%{actionName}">
	<s:hidden name="enginesForm.id"></s:hidden>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr>
						<th width="20%" style="text-align:right;">名称<font style="color: red;">*</font>：</th>
						<td width="40%">
							<s:textfield id="name" name="enginesForm.name"></s:textfield>
						</td>
						<td width="40%"><div id="nameTip"></div></td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">品牌：</th>
						<td width="40%">
							<s:if test="%{actionName == 'engines_save'}">
							<s:select id="pinyin" name="enginesForm.pinyin" onchange="choseBrand();" list="#{'A':'A','B':'B','C':'C','D':'D','E':'E','F':'F','G':'G','H':'H','I':'I','J':'J','K':'K','L':'L','M':'M','N':'N','O':'O','P':'P','Q':'Q','R':'R','S':'S','T':'T','U':'U','V':'V','W':'W','X':'X','Y':'Y','Z':'Z'}"></s:select>&nbsp;
							<select id="brandId" name="enginesForm.brandId" onchange="choseVersion();">
								<option value='0'>请选择品牌</option>
							</select>
							<input type="hidden" id="_brandId" value="<s:property value='enginesForm.brandId'/>" />
							</s:if>
							<s:else>
							<s:property value="enginesForm.brandName"/>
							<s:hidden name="enginesForm.brandId"></s:hidden>
							</s:else>
						</td>
						<td width="40%">&nbsp;</td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">版本：</th>
						<td width="40%">
							<s:if test="%{actionName == 'engines_save'}">
							<select id="versionId" name="enginesForm.versionId" onchange="choseSeries();">
								<option value='0'>请选择版本</option>
							</select>
							<input type="hidden" id="_versionId" value="<s:property value='enginesForm.versionId'/>" />
							</s:if>
							<s:else>
							<s:property value="enginesForm.versionName"/>
							<s:hidden name="enginesForm.versionId"></s:hidden>
							</s:else>
						</td>
						<td width="40%">&nbsp;</td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">系列<font style="color: red;">*</font>：</th>
						<td width="40%">
							<s:if test="%{actionName == 'engines_save'}">
							<select id="seriesId" name="enginesForm.seriesId" onchange="choseModels();">
								<option value='0'>请选择系列</option>
							</select>
							<input type="hidden" id="_seriesId" value="<s:property value='enginesForm.seriesId'/>" />
							</s:if>
							<s:else>
							<s:property value="enginesForm.versionName"/>
							<s:hidden id="seriesId" name="enginesForm.seriesId"></s:hidden>
							</s:else>
						</td>
						<td width="40%"><div id="seriesIdTip"></div></td>
					</tr>
					<tr>
						<td class="bottom" align="center" colspan="4">
							<div align="center">
								<s:if test="%{actionName == 'engines_save'}">
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
	onfocus :"名称长度1-20个字符",
	oncorrect :"输入合法",
	forcevalid :false
})
.inputValidator( {
	min :1,
	max :20,
	onerror :"你输入的名称长度非法，请确认"
});

//select验证
$("#seriesId").formValidator({
  onshow: "请选择系列",
  onfocus: "请选择系列",
  oncorrect: "已选择系列"
}).inputValidator({
  min: 1,  //开始索引
  onerror: "请选择系列"
});
</script>

</body>
</html>