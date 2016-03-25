<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<html>
<head>
<title><s:property value="jsp_head_title"/>-拍卖行管理</title>
<%@include file="/jsp/common.jsp"%>
</head>

<body>
<%@include file="/jsp/include/pageInc.jsp"%>
<s:else>
	<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_result" border="0" cellspacing="1" cellpadding="1" align="center">
      <tr class="tr_head" align="center">
		<td><input id="checkboxtop"	type="checkbox" class="ckboxAll" title="选中/取消选中" value="" onclick="checkAll('checkboxtop','checkbox1');" /></td>
		<td width="">拍卖行名字</td>
		<td width="">拍卖行图标</td>
		<td width="">操作</td>
      </tr>
      <s:iterator id="auctionInfo" value="%{pageList.results}" status="sta">
      <tr align="center" class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
		<td>
			<input name="items[<s:property value="#sta.index"/>].id" type="checkbox" class="ckboxItem" value="<s:property value="#auctionInfo.id"/>" onclick="check('checkboxtop','checkbox1');" />
		</td>
        <td>
			<a href="javascript:view_auction(<s:property value='#auctionInfo.id'/>)"><s:property value="#auctionInfo.name"/></a>
		</td>
		<td>
			<img src="<s:property value="#auctionInfo.icon"/>" style="cursor: pointer;" width="32" height="32" />
		</td>
        <td align="center" >
        	<a href="javascript:edit_auction(<s:property value='#auctionInfo.id'/>)" >修改</a>
			<s:if test="%{userSession.userPermissions['/module/fav/auction_edit.do'] != null}">
				<a href="javascript:edit_auction(<s:property value='#auctionInfo.id'/>)" >修改</a> &nbsp;&nbsp;
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
