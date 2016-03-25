<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/modelyearMgr.js"></script>
<script type="text/javascript">
	<s:if test="%{actionName == 'modelyear_save'}">
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'modelyear_save.do',
			    saveUrlTo:'modelyear_init.do',
			    saveBtn:'btn_save'
			});
			choseBrand();
		});
	</s:if>
	<s:else>
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'modelyear_update.do',
			    saveUrlTo:'modelyear_init.do',
			    saveBtn:'btn_save'
			});
			choseBrand();
		});
	</s:else>
</script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<s:form id="setForm" name="modelyearForm" action="%{actionName}">
	<s:hidden name="modelyearForm.id"></s:hidden>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr>
						<th width="20%" style="text-align:right;">品牌：</th>
						<td width="40%">
							<s:select id="pinyin" name="modelyearForm.pinyin" onchange="choseBrand();" list="#{'A':'A','B':'B','C':'C','D':'D','E':'E','F':'F','G':'G','H':'H','I':'I','J':'J','K':'K','L':'L','M':'M','N':'N','O':'O','P':'P','Q':'Q','R':'R','S':'S','T':'T','U':'U','V':'V','W':'W','X':'X','Y':'Y','Z':'Z'}"></s:select>&nbsp;
							<select id="brandId" name="modelyearForm.brandId" onchange="choseVersion();">
								<option value='0'>请选择品牌</option>
							</select>
							<input type="hidden" id="_brandId" value="<s:property value='modelyearForm.brandId'/>" />
						</td>
						<td width="40%">&nbsp;</td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">版本：</th>
						<td width="40%">
							<select id="versionId" name="modelyearForm.versionId" onchange="choseSeries();">
								<option value='0'>请选择版本</option>
							</select>
							<input type="hidden" id="_versionId" value="<s:property value='modelyearForm.versionId'/>" />
						</td>
						<td width="40%">&nbsp;</td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">系列：</th>
						<td width="40%">
							<select id="seriesId" name="modelyearForm.seriesId" onchange="choseModels();">
								<option value='0'>请选择系列</option>
							</select>
							<input type="hidden" id="_seriesId" value="<s:property value='modelyearForm.seriesId'/>" />
						</td>
						<td width="40%">&nbsp;</div></td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">车型：</th>
						<td width="40%">
							<select id="modelsId" name="modelyearForm.modelsId">
								<option value='0'>请选择车型</option>
							</select>
							<input type="hidden" id="_modelsId" value="<s:property value='modelyearForm.modelsId'/>" />
						</td>
						<td width="40%"><div id="modelsIdTip"></div></td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">
							名称： 
						</th>
						<td width="40%">
							<s:textfield id="name" name="modelyearForm.name"></s:textfield>
						</td>
						<td>
						<div id="nameTip"></div>
						</td>
						<td width="40%"><div id="modelsIdTip"></div></td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">
							功率： 
						</th>
						<td width="40%">
							<s:textfield id="efficiency" name="modelyearForm.efficiency"></s:textfield>
						</td>
						<td>
						<div id="nameTip"></div>
						</td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">
							驱动方式： 
						</th>
						<td width="40%">
							<s:textfield id="driving" name="modelyearForm.driving"></s:textfield>
						</td>
						<td>
						<div id="nameTip"></div>
						</td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">
							油耗： 
						</th>
						<td width="40%">
							<s:textfield id="fuel" name="modelyearForm.fuel"></s:textfield>
						</td>
						<td>
						<div id="nameTip"></div>
						</td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">
							座位数： 
						</th>
						<td width="40%">
							<s:textfield id="seats" name="modelyearForm.seats"></s:textfield>
						</td>
						<td>
						<div id="nameTip"></div>
						</td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">
							指导价： 
						</th>
						<td width="40%">
							<s:textfield id="price" name="modelyearForm.price" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"></s:textfield>
						</td>
						<td>
						<div id="nameTip"></div>
						</td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">全车正面图：</th>
						<td width="40%">
						<s:if test="%{modelyearForm.picPath1 != null && modelyearForm.picPath1 != ''}">
							<img id="picPath1Img" src="<s:property value='modelyearForm.picPath1'/>" alt="点击编辑" style="cursor: pointer;" width="32" height="32" onclick="openDialogUpload('picPath1');"/>
						</s:if>
						<s:else>
							<img id="picPath1Img" src="images/ryxx02.gif" alt="点击上传" style="cursor: pointer;" width="32" height="32" onclick="openDialogUpload('picPath1');"/>
						</s:else>
						<s:hidden id="picPath1" name="modelyearForm.picPath1"></s:hidden>
						</td>
						<td width="40%">&nbsp;</td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">全车侧面图：</th>
						<td width="40%">
						<s:if test="%{modelyearForm.picPath2 != null && modelyearForm.picPath2 != ''}">
							<img id="picPath2Img" src="<s:property value='modelyearForm.picPath2'/>" alt="点击编辑" style="cursor: pointer;" width="32" height="32" onclick="openDialogUpload('picPath2');"/>
						</s:if>
						<s:else>
							<img id="picPath2Img" src="images/ryxx02.gif" alt="点击上传" style="cursor: pointer;" width="32" height="32" onclick="openDialogUpload('picPath2');"/>
						</s:else>
						<s:hidden id="picPath2" name="modelyearForm.picPath2"></s:hidden>
						</td>
						<td width="40%">&nbsp;</td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">全车后方图：</th>
						<td width="40%">
						<s:if test="%{modelyearForm.picPath3 != null && modelyearForm.picPath3!= ''}">
							<img id="picPath3Img" src="<s:property value='modelyearForm.picPath3'/>" alt="点击编辑" style="cursor: pointer;" width="32" height="32" onclick="openDialogUpload('picPath3');"/>
						</s:if>
						<s:else>
							<img id="picPath3Img" src="images/ryxx02.gif" alt="点击上传" style="cursor: pointer;" width="32" height="32" onclick="openDialogUpload('picPath3');"/>
						</s:else>
						<s:hidden id="picPath3" name="modelyearForm.picPath3"></s:hidden>
						</td>
						<td width="40%">&nbsp;</td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">中控图：</th>
						<td width="40%">
						<s:if test="%{modelyearForm.picPath4 != null && modelyearForm.picPath4 != ''}">
							<img id="picPath4Img" src="<s:property value='modelyearForm.picPath4'/>" alt="点击编辑" style="cursor: pointer;" width="32" height="32" onclick="openDialogUpload('picPath4');"/>
						</s:if>
						<s:else>
							<img id="picPath4Img" src="images/ryxx02.gif" alt="点击上传" style="cursor: pointer;" width="32" height="32" onclick="openDialogUpload('picPath4');"/>
						</s:else>
						<s:hidden id="picPath4" name="modelyearForm.picPath4"></s:hidden>
						</td>
						<td width="40%">&nbsp;</td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">座椅图：</th>
						<td width="40%">
						<s:if test="%{modelyearForm.picPath5 != null && modelyearForm.picPath5 != ''}">
							<img id="picPath5Img" src="<s:property value='modelyearForm.picPath5'/>" alt="点击编辑" style="cursor: pointer;" width="32" height="32" onclick="openDialogUpload('picPath5');"/>
						</s:if>
						<s:else>
							<img id="picPath5Img" src="images/ryxx02.gif" alt="点击上传" style="cursor: pointer;" width="32" height="32" onclick="openDialogUpload('picPath5');"/>
						</s:else>
						<s:hidden id="picPath5" name="modelyearForm.picPath5"></s:hidden>
						</td>
						<td width="40%">&nbsp;</td>
					</tr>
										<tr>
						<th width="20%" style="text-align:right;">内饰图：</th>
						<td width="40%">
						<s:if test="%{modelyearForm.picPath6 != null && modelyearForm.picPath6 != ''}">
							<img id="picPath6Img" src="<s:property value='modelyearForm.picPath6'/>" alt="点击编辑" style="cursor: pointer;" width="32" height="32" onclick="openDialogUpload('picPath6');"/>
						</s:if>
						<s:else>
							<img id="picPath6Img" src="images/ryxx02.gif" alt="点击上传" style="cursor: pointer;" width="32" height="32" onclick="openDialogUpload('picPath6');"/>
						</s:else>
						<s:hidden id="picPath6" name="modelyearForm.picPath6"></s:hidden>
						</td>
						<td width="40%">&nbsp;</td>
					</tr>
					<tr>
						<td class="bottom" align="center" colspan="4">
							<div align="center">
								<s:if test="%{actionName == 'modelsHot_save'}">
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
<div id="uploadDialog" title="图片上传"></div>
<script type="text/javascript">
$.formValidator.initConfig({
	formid:"setForm",
	onerror:function(msg){
	alert(msg)},
	onsuccess:function(){
		return true;
}});
//select验证
$("#modelsId").formValidator({
  onshow: "请选择车型",
  onfocus: "请选择车型",
  oncorrect: "已选择车型"
}).inputValidator({
  min: 1,  //开始索引
  onerror: "请选择车型"
});
//添加图片
function openDialogUpload(labelId){
	picType = labelId;
	$("#uploadDialog").dialog('open').html("正在加载页面...").load(_contextPath + "/upload_uploadInit.do?mode=ajax&uploadContentType=pic&path=file");
}

function callbackpic(fileName, type) {
	fileName = "/upload/" + fileName;
	$("#" + picType).val(fileName);
	$("#" + picType + "Img").prop("src", fileName);
	$("#uploadDialog").dialog('close');
}
</script>

</body>
</html>