<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title><s:property value="jsp_head_title"/></title>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/basepriceMgr.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$(document).ict({
		queryForm:'queryForm',
		queryUrl:'baseprice_query.do',
		queryBtn:'btn_query',
		pagecontent:'pagecontent',
		addUrl:'baseprice_add.do',
		delsUrl:'baseprice_deletes.do',
		delsBtn:'btn_del'
	});
	choseBrand();
});
</script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<form id="queryForm" action="">
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_query" border="0" cellspacing="1" cellpadding="1" align="center" >
		<tr>
			<th width="15%">品牌</th>
			<td width="35%">
				<s:select id="pinyin" onchange="choseBrand();" list="#{'A':'A','B':'B','C':'C','D':'D','E':'E','F':'F','G':'G','H':'H','I':'I','J':'J','K':'K','L':'L','M':'M','N':'N','O':'O','P':'P','Q':'Q','R':'R','S':'S','T':'T','U':'U','V':'V','W':'W','X':'X','Y':'Y','Z':'Z'}"></s:select>&nbsp;
				<select id="brandId" name="queryBasePriceWeek.brandId" onchange="choseVersion();">
					<option value='0'>请选择品牌</option>
				</select>
			</td>
 			<th width="15%">
 				版本
			</th>
			<td width="35%">
				<select id="versionId" name="queryBasePriceWeek.versionId" onchange="choseSeries();">
					<option value='0'>请选择版本</option>
				</select>
			</td>
		</tr>
		<tr>
			<th width="15%">系列
			</th>
			<td width="35%">
				<select id="seriesId" name="queryBasePriceWeek.seriesId" onchange="choseModels();">
					<option value='0'>请选择系列</option>
				</select>
			</td>
 			<th>
 				车型
			</th>
			<td>
				<select id="modelsId" name="queryBasePriceWeek.modelsId" onchange="choseModelyear();">
					<option value='0'>请选择车型</option>
				</select>
			</td>
		</tr>
		<tr>
 			<th>
 				年款
			</th>
			<td colspan="3">
				<select id="modelyearId" name="queryBasePriceWeek.modelyearId">
					<option value='0'>请选择年款</option>
				</select>
			</td>
		</tr>
		<tr>
			<td class="bottom" colspan="4" align="center">
		   		<input name="btn" id="btn_query" type="button" class="button" value="查询" />
				<input name="btn" type="reset" class="button" value="重置" />
			</td>
		</tr>
	</table>
</td></tr></table>
</form>
<br/>
<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td align="right" style="padding-right: 20px">
			<input type="button" id="btn_import" class="button" onclick="openImportDialog();" value="导入"/>
<%--			<input type="button" id="btn_del" class="button" value="删除"/>--%>
		</td>
	</tr>
	<tr>
		<td>
			<form id="listForm" action="">
				<div id="pagecontent"></div>
			</form>
		</td>
	</tr>
</table>
</body>
<div id="uploadDialog" title="数据导入">
<form class="uploadForm" action="baseprice_priceImport.do" name="uploadForm" method="post" enctype="multipart/form-data">
    <input id="uploadFile" name="uploadFile" type="file" onchange="importIPlan();"/>
</form>
<button id="upload">上传</button>
</div>
</html>