<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/modelsMgr.js"></script>
<script type="text/javascript">
	<s:if test="%{actionName == 'models_save'}">
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'models_save.do',
			    saveUrlTo:'models_init.do',
			    saveBtn:'btn_save'
			});
			choseBrand();
		});
	</s:if>
	<s:else>
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'models_update.do',
			    saveUrlTo:'models_init.do',
			    saveBtn:'btn_save'
			});
			choseBrand();
		});
	</s:else>
</script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<s:form id="setForm" name="modelsForm" action="%{actionName}">
	<s:hidden name="modelsForm.id"></s:hidden>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr>
						<th  width="15%">
							品牌名称： 
						</th>
						<td>
							<s:select id="pinyin" name="modelsForm.pinyin" onchange="choseBrand();" list="#{'A':'A','B':'B','C':'C','D':'D','E':'E','F':'F','G':'G','H':'H','I':'I','J':'J','K':'K','L':'L','M':'M','N':'N','O':'O','P':'P','Q':'Q','R':'R','S':'S','T':'T','U':'U','V':'V','W':'W','X':'X','Y':'Y','Z':'Z'}"></s:select>&nbsp;
							<select id="brandId" name="modelsForm.brandId" onchange="choseVersion();">
								<option value='0'>请选择品牌</option>
							</select>
							<input type="hidden" id="_brandId" value="<s:property value='modelsForm.brandId'/>" />
						</td>
						<th  width="25%">
							&nbsp; 
						</th>
					</tr>
					<tr>
						<th  width="15%">
								版本名称： 
						</th>
						<td>
							<select id="versionId" name="modelsForm.versionId" onchange="choseSeries();">
								<option value='0'>请选择版本</option>
							</select>
							<input type="hidden" id="_versionId" value="<s:property value='modelsForm.versionId'/>" />
						</td>
						<th  width="15%">
							&nbsp; 
						</th>
					</tr>
					<tr>
						<th  width="15%">
								系列名称： 
						</th>
						<td>
							<select id="seriesId" name="modelsForm.seriesId">
								<option value='0'>请选择系列</option>
							</select>
							<input type="hidden" id="_seriesId" value="<s:property value='modelsForm.seriesId'/>" />
						</td>
						<th  width="15%">
							<div id="seriesIdTip"></div>
						</th>
					</tr>
					<tr>
						<th  width="30%">
							名称： 
						</th>
						<td>
							<s:textfield id="name" name="modelsForm.name"></s:textfield>
						</td>
						<td colspan="2">
							<div id="nameTip"></div>
						</td>
					</tr>
					<tr>
						<td class="bottom" align="center" colspan="4">
							<div align="center">
								<s:if test="%{actionName == 'models_save'}">
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
		onshow : "请输入车型名称，长度为1~60个字符",
		onfocus : "车型名称不能为空",
		oncorrect : "输入合法"
	}).inputValidator( {
		min:1,
		max:60,
		onerror:"请确认车型名称长度(1~60个字符)"
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