<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/seriesMgr.js"></script>
<script type="text/javascript">
	<s:if test="%{actionName == 'series_save'}">
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'series_save.do',
			    saveUrlTo:'series_init.do',
			    saveBtn:'btn_save'
			});
			choseBrand();
		});
	</s:if>
	<s:else>
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'series_update.do',
			    saveUrlTo:'series_init.do',
			    saveBtn:'btn_save'
			});
			choseBrand();
		});
	</s:else>
</script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<s:form id="setForm" name="seriesForm" action="%{actionName}">
	<s:hidden name="seriesForm.id"></s:hidden>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr>
						<th width="15%">
							品牌名称： 
						</th>
						<td width="60%">
							<s:select id="pinyin" name="seriesForm.pinyin" onchange="choseBrand();" list="#{'A':'A','B':'B','C':'C','D':'D','E':'E','F':'F','G':'G','H':'H','I':'I','J':'J','K':'K','L':'L','M':'M','N':'N','O':'O','P':'P','Q':'Q','R':'R','S':'S','T':'T','U':'U','V':'V','W':'W','X':'X','Y':'Y','Z':'Z'}"></s:select>&nbsp;
							<select id="brandId" name="seriesForm.brandId" onchange="choseVersion();">
								<option value='0'>请选择品牌</option>
							</select>
							<input type="hidden" id="_brandId" value="<s:property value='seriesForm.brandId'/>" />
						</td>
						<td>
							&nbsp;
						</td>
					</tr>
					<tr>
						<th>
							版本名称：
						</th>
						<td>
							<select id="versionId" name="seriesForm.versionId">
								<option value='0'>请选择版本</option>
							</select>
							<input type="hidden" id="_versionId" value="<s:property value='seriesForm.versionId'/>" />
						</td>
						<td>
							<div id="versionIdTip"></div>
						</td>
					</tr>
					<tr>
						<th>
							名称： 
						</th>
						<td>
							<s:textfield id="name" name="seriesForm.name"></s:textfield>
						</td>
						<td>
							<div id="nameTip"></div>
						</td>
					</tr>
								<tr>
									 <th align="right">图标上传：</th>
										<td>
											<s:textfield id="picPath" name="seriesForm.picPath"
												cssClass="input2" size="20" onblur="clearBlank(this);"
												readonly="true"></s:textfield>
											<input type="button" id="btn_upfile" onclick="up_file();"
												class="button2" onmouseout="this.className = 'button2'"
												onmouseover="this.className = 'button2Over'" value="图片上传" />
											<input type="button" id="btn_clear" onclick="clear_upfile();"
												class="button" onmouseout="this.className = 'button'"
												onmouseover="this.className = 'buttonOver'" value="清空" />
										</td>
									<td>
										<div id="picPathTip"></div>
									</td>
								</tr>
					<tr>
						<td class="bottom" align="center" colspan="5">
							<div align="center">
								<s:if test="%{actionName == 'series_save'}">
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
<div id="uploadDialog" title="文件上传"></div>
<script type="text/javascript">
$.formValidator.initConfig({
	formid:"setForm",
	onerror:function(msg){
	alert(msg)},
	onsuccess:function(){
		return true;
}});
$("#name").formValidator( {
		onshow : "请输入系列名称，长度为1~60个字符",
		onfocus : "系列名称不能为空",
		oncorrect : "输入合法"
	}).inputValidator( {
		min:1,
		max:60,
		onerror:"请确认系列名称长度(1~60个字符)"
});	
//select验证
$("#versionId").formValidator({
  onshow: "请选择版本",
  onfocus: "请选择版本",
  oncorrect: "已选择版本"
}).inputValidator({
  min: 1,  //开始索引
  onerror: "请选择版本"
});

function callbackpic(fileName, type) {
	fileName = "/upload/" + fileName;
	$("#picPath").val(fileName);
	$("#uploadDialog").dialog('close');
}
</script>

</body>
</html>