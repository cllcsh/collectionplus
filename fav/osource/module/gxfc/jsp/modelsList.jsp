<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<html>
<head>
<title><s:property value="jsp_head_title"/>-车型管理</title>
<%@include file="/jsp/common.jsp"%>
</head>

<body>
<%@include file="/jsp/include/pageInc.jsp"%>
<s:else>
	<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_result" border="0" cellspacing="1" cellpadding="1" align="center">
      <tr class="tr_head" align="center">
		<td width="40"><input id="checkboxtop"	type="checkbox" class="ckboxAll" title="选中/取消选中" value="" onclick="checkAll('checkboxtop','ckboxItem');" /></td>
		<td width="">品牌</td>
		<td width="">版本</td>
		<td width="">系列</td>
		<td width="">车型</td>
		<td width="60">操作</td>
      </tr>
      <s:iterator id="modelsInfo" value="%{pageList.results}" status="sta">
      <tr align="center" class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
		<td>
			<input name="items[<s:property value="#sta.index"/>].id" type="checkbox" class="ckboxItem" value="<s:property value="#modelsInfo.id"/>" onclick="check('checkboxtop','checkbox1');" />
		</td>
        <td>
			<s:property value="#modelsInfo.brandName"/>
		</td>
        <td>
			<s:property value="#modelsInfo.versionName"/>
		</td>
        <td>
			<s:property value="#modelsInfo.seriesName"/>
		</td>
        <td>
			<s:property value="#modelsInfo.name"/>
		</td>
        <td align="center" >
        	<a href="javascript:edit_models(<s:property value='#modelsInfo.id'/>)" >修改</a>
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
