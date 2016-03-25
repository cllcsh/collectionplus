<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<TITLE>藏品查询</TITLE>
<base target="_self" />
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/collectionMgr.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$(document).ict({
		queryForm:'queryForm',
		queryUrl:'collection_query.do',
		queryBtn:'btn_query',
		pagecontent:'pagecontent',
		addUrl:'collection_add.do',
		delsUrl:'collection_deletes.do',
		delsBtn:'btn_del'
	});
});
</script>
</head>

<body>
<form id="queryForm" action="">
<s:hidden name="pageType" value="model"></s:hidden>
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_query" border="0" cellspacing="1" cellpadding="1" align="center" >
		<tr>
		    <th>标题</th>
		    <td><s:textfield size="25" id="title" name="collectionForm.title" onblur="clearBlank(this);" maxlength="60"  /></td>
		    <th>藏品类别</th>
		    <td>
		    	<s:select id="categoryId" name="collectionForm.categoryId" list="collectionCategorys" listKey="key" listValue="value" headerKey="-1" headerValue="所有" cssStyle="width:180px"/>
		    </td>
		</tr>
		<tr>
		    <th>所属时期</th>
		    <td>
		    	<s:select id="collectionPeriodId" name="collectionForm.collectionPeriodId" list="collectionPeriods" listKey="key" listValue="value" headerKey="-1" headerValue="所有" cssStyle="width:180px"/>
		    </td>
		    <th>标签</th>
		    <td>
		    	<s:select id="labelId" name="collectionForm.labelId" list="collectionLables" listKey="key" listValue="value" headerKey="-1" headerValue="所有" cssStyle="width:180px"/>
		    </td>
		</tr>
		<tr>
		    <th>是否愿意送拍</th>
		    <td>
		    	<s:select id="isSendRacket" name="collectionForm.isSendRacket" list="whethers" listKey="key" listValue="value" headerKey="-1" headerValue="所有" cssStyle="width:180px"/>
		    </td>
		    <th>是否愿意出售</th>
		    <td>
		    	<s:select id="isSold" name="collectionForm.isSold" list="whethers" listKey="key" listValue="value" headerKey="-1" headerValue="所有" cssStyle="width:180px"/>
		    </td>
		</tr>
		<tr>
		    <th>是否鉴定</th>
		    <td>
		    	<s:select id="isIdentify" name="collectionForm.isIdentify" list="whethers" listKey="key" listValue="value" headerKey="-1" headerValue="所有" cssStyle="width:180px"/>
		    </td>
		    <th>状态</th>
		    <td>
		    	<s:select id="status" name="collectionForm.status" list="statusMap" listKey="key" listValue="value" headerKey="-1" headerValue="所有" cssStyle="width:180px"/>
		    </td>
		</tr>
		<tr>
			<td class="bottom" colspan="4" align="center">
		   		<input name="btn" id="btn_query" type="button" class="button" value="查询" />
		   		<s:if test="%{userSession.userPermissions['/module/fav/collection_query.do'] != null}">
					<input name="btn" id="btn_query" type="button" class="button" value="查询" />
		   		</s:if>
				<input name="btn" type="reset" class="button" value="重置" />
			</td>
		</tr>
	</table>
</td></tr></table>
</form>
<br/>
<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td>
			<form id="listForm" action="">
				<div id="pagecontent"></div>
			</form>
		</td>
	</tr>
</table>
</body>
</html>
