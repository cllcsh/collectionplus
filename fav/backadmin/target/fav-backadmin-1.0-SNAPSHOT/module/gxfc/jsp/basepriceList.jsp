<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<html>
<head>
<title><s:property value="jsp_head_title"/>-基础价格数据管理</title>
<%@include file="/jsp/common.jsp"%>
</head>

<body>
<%@include file="/jsp/include/pageInc.jsp"%>
<s:else>
	<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_result" border="0" cellspacing="1" cellpadding="1" align="center">
      <tr class="tr_head" align="center">
		<td width="150">数据批次</td>
        <td width="300">车型</td>
		<td width="100">全国底价</td>
        <td width="100">起始日期</td>
        <td width="100">截止日期</td>
      </tr>
      <s:iterator id="basePriceWeek" value="%{pageList.results}" status="sta">
      <tr align="center" class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
        <td>
			<a href="javascript:view_baseprice(<s:property value='#basePriceWeek.id'/>)"><s:property value="#basePriceWeek.dataYear"/>年<s:property value="#basePriceWeek.dataMonth"/>月第<s:property value="#basePriceWeek.dataWeek"/>周</a>
		</td>
        <td><s:property value="#basePriceWeek.modelyearName"/> <s:property value="#basePriceWeek.brandName"/> <s:property value="#basePriceWeek.seriesName"/> <s:property value="#basePriceWeek.modelsName"/> <s:property value="#basePriceWeek.versionName"/></td>
        <td><s:property value="#basePriceWeek.lpMix"/></td>
        <td><s:date name="#basePriceWeek.startDate" format="yyyy-MM-dd"/></td>
        <td><s:date name="#basePriceWeek.endDate" format="yyyy-MM-dd"/></td>
      </tr>
      </s:iterator>
    </table>
	</td></tr></table>
</s:else>
</body>
</html>
