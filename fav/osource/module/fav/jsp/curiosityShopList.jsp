<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<html>
<head>
<title><s:property value="jsp_head_title"/>-古玩店管理</title>
<%@include file="/jsp/common.jsp"%>
</head>

<body>
<%@include file="/jsp/include/pageInc.jsp"%>
<s:else>
	<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_result" border="0" cellspacing="1" cellpadding="1" align="center">
      <tr class="tr_head" align="center">
		<td><input id="checkboxtop"	type="checkbox" class="ckboxAll" title="选中/取消选中" value="" onclick="checkAll('checkboxtop','checkbox1');" /></td>
		<td width="">名称</td>
		<td width="">地址</td>
		<td width="">电话</td>
		<td width="">省份</td>
		<td width="">城市</td>
		<td width="">区县</td>
		<td width="">图标</td>
		<td width="">简介</td>
		<td width="">操作</td>
      </tr>
      <s:iterator id="curiosityShopInfo" value="%{pageList.results}" status="sta">
      <tr align="center" class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
		<td>
			<input name="items[<s:property value="#sta.index"/>].id" type="checkbox" class="ckboxItem" value="<s:property value="#curiosityShopInfo.id"/>" onclick="check('checkboxtop','checkbox1');" />
		</td>
        <td>
			<a href="javascript:view_curiosityShop(<s:property value='#curiosityShopInfo.id'/>)"><s:property value="#curiosityShopInfo.name"/></a>
		</td>
		<td><s:property value="#curiosityShopInfo.address"/></td>
		<td><s:property value="#curiosityShopInfo.phone"/></td>
		<td><s:property value="#curiosityShopInfo.province"/></td>
		<td><s:property value="#curiosityShopInfo.city"/></td>
		<td><s:property value="#curiosityShopInfo.county"/></td>
		<td><img id="imgPathImg" src="<s:property value="#curiosityShopInfo.icon"/>" style="cursor: pointer;" width="32" height="32" /></td>
        <td><s:property value="#curiosityShopInfo.introduction"/></td>
        <td align="center" >
        	<a href="javascript:edit_curiosityShop(<s:property value='#curiosityShopInfo.id'/>)" >修改</a>
			<s:if test="%{userSession.userPermissions['/module/fav/curiosityShop_edit.do'] != null}">
				<a href="javascript:edit_curiosityShop(<s:property value='#curiosityShopInfo.id'/>)" >修改</a> &nbsp;&nbsp;
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
