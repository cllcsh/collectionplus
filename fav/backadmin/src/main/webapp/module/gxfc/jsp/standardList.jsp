<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<html>
<head>
<title><s:property value="jsp_head_title"/>-标准配置管理</title>
<%@include file="/jsp/common.jsp"%>
</head>

<body>
<%@include file="/jsp/include/pageInc.jsp"%>
<s:else>
	<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_result" border="0" cellspacing="1" cellpadding="1" align="center">
      <tr class="tr_head" align="center">
		<td width="40" nowrap><input id="checkboxtop"	type="checkbox" class="ckboxAll" title="选中/取消选中" value="" onclick="checkAll('checkboxtop','ckboxItem');" /></td>
		<td width="15%">品牌</td>
        <td width="15%">版本</td>
		<td width="15%">系列</td>
        <td width="15%">车型</td>
        <td width="15%">年款</td>
        <td width="25%">标准配置</td>
		<td width="80" nowrap>操作</td>
      </tr>
      <s:iterator id="standardInfo" value="%{pageList.results}" status="sta">
      <tr align="center" class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
		<td>
			<input name="items[<s:property value="#sta.index"/>].id" type="checkbox" class="ckboxItem" value="<s:property value="#standardInfo.id"/>" onclick="check('checkboxtop','checkbox1');" />
		</td>
        <td><s:property value="#standardInfo.brandName"/></td>
        <td><s:property value="#standardInfo.versionName"/></td>
        <td><s:property value="#standardInfo.seriesName"/></td>
        <td><s:property value="#standardInfo.modelsName"/></td>
        <td><s:property value="#standardInfo.modelyearName"/></td>
        <td><s:property value="#standardInfo.name.replaceAll('\\r\\n', '<br/>')" escape="false"/></td>
        <td align="center" >
        	<a href="javascript:edit_standard(<s:property value='#standardInfo.id'/>)" >修改</a>
		</td>
      </tr>
      </s:iterator>
    </table>
    <script type="text/javascript">
	$(".tb_result").checkbox([{all:'ckboxAll',item:'ckboxItem'}]);
	</script>
	</td></tr></table>
</s:else>

</body>
</html>