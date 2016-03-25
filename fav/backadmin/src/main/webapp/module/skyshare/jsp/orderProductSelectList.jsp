<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<html>
<head>
<title><s:property value="jsp_head_title"/>-产品查询</title>
<%@include file="/jsp/common.jsp"%>
</head>

<body>
<%@include file="/jsp/include/pageInc.jsp"%>
<s:else>
	<s:form id="productselectForm" name="productselectForm" action="%{actionName}">
	<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_result" border="0" cellspacing="1" cellpadding="1" align="center">
     <tr class="tr_head" align="center">
		<td><input id="checkboxtop"	type="checkbox" class="ckboxAll" title="选中/取消选中" value="" onclick="checkAll('checkboxtop','checkbox2');addAllProduct();" /></td>
		<td width="">产品名称</td>
        <td width="">产品类型</td>
		<td width="">上架时间</td>
		<td width="">产品数量</td>
      </tr>
      <s:iterator id="product" value="%{pageList.results}" status="sta">
      <tr align="center" class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
		<td>
			<input name="checkbox2" type="checkbox" class="ckboxItem" checked="checked"  value="<s:property value="#product.id"/>" onclick="return false;" />
		</td>
        <td>
			<s:property value="#product.productName"/>
		</td>
        <td><s:property value="#product.typeName"/></td>
        <td><s:date format="yyyy-MM-dd HH:mm:ss" name="#product.upDat"/></td>
		<td><s:textfield size="20" id="orderCode" cssClass="td03" value="1"
										name="orderForm.productNum" maxlength="60"
										onblur="clearBlank(this);" /></td>
      </tr>
      </s:iterator>
    </table>
	</td></tr></table>
</s:form>
</s:else>

</body>
</html>
