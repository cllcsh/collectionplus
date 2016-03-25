<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/brandSearchMgr.js"></script>
<script type="text/javascript">
	<s:if test="%{actionName == 'brandSearch_save'}">
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'brandSearch_save.do',
			    saveUrlTo:'brandSearch_init.do',
			    saveBtn:'btn_save'
			});
			choseBrand();
		});
	</s:if>
	<s:else>
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'brandSearch_update.do',
			    saveUrlTo:'brandSearch_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:else>
</script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<s:form id="setForm" name="brandSearchForm" action="%{actionName}">
	<s:hidden name="brandSearchForm.id"></s:hidden>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr>
						<th width="20%" style="text-align:right;">品牌<font style="color: red;">*</font>：</th>
						<td width="40%">
							<s:if test="%{actionName == 'brandSearch_save'}">
							<s:select id="pinyin" onchange="choseBrand();" list="#{'A':'A','B':'B','C':'C','D':'D','E':'E','F':'F','G':'G','H':'H','I':'I','J':'J','K':'K','L':'L','M':'M','N':'N','O':'O','P':'P','Q':'Q','R':'R','S':'S','T':'T','U':'U','V':'V','W':'W','X':'X','Y':'Y','Z':'Z'}"></s:select>&nbsp;
							<select id="brandId" name="brandSearchForm.brandId"></select>
							</s:if>
							<s:else>
							<s:property value="brandSearchForm.brandName"/>
							<s:hidden name="brandSearchForm.brandId"></s:hidden>
							</s:else>
						</td>
						<td width="40%"><div id="brandIdTip"></div></td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">排序</th>
						<td width="40%"><s:textfield name="brandSearchForm.sorting"></s:textfield></td>
						<td width="40%">&nbsp;</td>
					</tr>
					<tr>
						<td class="bottom" align="center" colspan="4">
							<div align="center">
								<s:if test="%{actionName == 'brandSearch_save'}">
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

//select验证
$("#brandId").formValidator({
  onshow: "请选择品牌",
  onfocus: "请选择品牌",
  oncorrect: "已选择品牌"
}).inputValidator({
  min: 0,  //开始索引
  onerror: "请选择品牌"
});
</script>

</body>
</html>