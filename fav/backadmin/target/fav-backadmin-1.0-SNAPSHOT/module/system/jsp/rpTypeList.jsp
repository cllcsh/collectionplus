<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="jsp_head_title"/></title>
</head>
<body>
<%@include file="/jsp/include/pageInc.jsp"%>
<s:else>
	<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_result" border="0" cellspacing="1" cellpadding="1" align="center">
      <tr class="tr_head" align="center">
		<td width="10%"><input id="checkboxtop"	type="checkbox" class="ckboxAll" title="选中/取消选中" value="" onclick="checkAll('checkboxtop','ckboxItem');" /></td>
        <td width="30%">奖惩类型名称</td>
		<td width="25%">默认值</td>
		<td width="25%">奖惩类型</td>
		<td width="10%">操作</td>
      </tr>
      <s:iterator id="rpType" value="%{pageList.results}" status="sta">
      <tr align="center" class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
		<td>
			<input name="checkbox1" type="checkbox" class="ckboxItem" value="<s:property value="#rpType.id"/>" onclick="check('checkboxtop','checkbox1');" />
		</td>
        <td>
			<a href="javascript:view_rpType(<s:property value='#rpType.id'/>)"><s:property value="#rpType.rpName"/></a>
		</td>
		<td><s:property value="#rpType.defScore"/></td>
		<td><dict:property codeType="common-rp_type" value="#rpType.rp"/></td>
        <td align="center" >
			<s:if test="%{userSession.userPermissions['/module/system/rpType_edit.do'] != null}">
				<a href="javascript:edit_rpType(<s:property value='#rpType.id'/>)" >修改</a> &nbsp;&nbsp;
            </s:if>
		</td>
      </tr>
      </s:iterator>
    </table>
	</td></tr></table>
</s:else>

</body>
</html>
