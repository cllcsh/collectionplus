<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<html>
<head>
<title><s:property value="jsp_head_title"/>-抽查人机分离</title>
<%@include file="/jsp/common.jsp"%>
</head>

<body>
<%@include file="/jsp/include/pageInc.jsp"%>
<s:else>
	<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_result" border="0" cellspacing="1" cellpadding="1" align="center">
      <tr class="tr_head" align="center">
		<td width="5%"><input id="checkboxtop"	type="checkbox" class="ckboxAll" title="选中/取消选中" value="" onclick="checkAll('checkboxtop','ckboxItem');" /></td>
		<td width="10%" align="center">抽查对象</td>
        <td width="8%" align="center">抽查时间</td>
		<td width="8%" align="center">抽查类型</td>
        <td width="8%" align="center">抽查分值</td>
        <td width="50%" align="center">抽查内容</td>
		<td width="" align="center">操作</td>
      </tr>
      <s:iterator id="checkBean" value="%{pageList.results}" status="sta">
      <tr align="center" class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
		<td>
			<input name="checkbox1" type="checkbox" class="ckboxItem" value="<s:property value="#checkBean.id"/>" onclick="check('checkboxtop','checkbox1');" />
		</td>
        <td align="center">
			<a href="javascript:view_check(<s:property value='#checkBean.id'/>)"><s:property value="#checkBean.criminalName"/></a>
		</td>
        <td align="center"><s:property value="#checkBean.checkDate"/></td>
        <td align="center"><s:property value="#checkBean.checkResultName"/></td>
        <td align="center"><s:property value="#checkBean.cent"/></td>
        <td align="center" style="word-break:break-all"><s:property value="#checkBean.checkExplain"/></td>
        <td align="center">
<%--        	<a href="javascript:edit_check(<s:property value='#checkBean.id'/>)" >修改</a>--%>
			<s:if test="%{userSession.userPermissions['/module/map/check_edit.do'] != null}">
				<a href="javascript:edit_check(<s:property value='#checkBean.id'/>)" >修改</a> &nbsp;&nbsp;
            </s:if>
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
