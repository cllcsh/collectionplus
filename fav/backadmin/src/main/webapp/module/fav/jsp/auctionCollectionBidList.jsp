<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<html>
<head>
<title><s:property value="jsp_head_title"/>-拍卖行藏品竞价管理</title>
<%@include file="/jsp/common.jsp"%>
</head>

<body>
<%@include file="/jsp/include/pageInc.jsp"%>
<s:else>
	<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_result" border="0" cellspacing="1" cellpadding="1" align="center">
      <tr class="tr_head" align="center">
		<td><input id="checkboxtop"	type="checkbox" class="ckboxAll" title="选中/取消选中" value="" onclick="checkAll('checkboxtop','checkbox1');" /></td>
		<td width="">藏品</td>
		<td width="">用户名</td>
		<td width="">性别</td>
		<td width="">竞价价格</td>
		<td width="">竞价时间</td>
		<td width="">操作</td>
      </tr>
      <s:iterator id="auctionCollectionBidInfo" value="%{pageList.results}" status="sta">
      <tr align="center" class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
		<td>
			<input name="items[<s:property value="#sta.index"/>].id" type="checkbox" class="ckboxItem" value="<s:property value="#auctionCollectionBidInfo.id"/>" onclick="check('checkboxtop','checkbox1');" />
		</td>
        <!--  <td>
			<a href="javascript:view_auctionCollectionBid(<s:property value='#auctionCollectionBidInfo.id'/>)"><s:property value="#auctionCollectionBidInfo.collectionId"/></a>
		</td>-->
		<td><a href="javascript:view_collection(<s:property value='#auctionCollectionBidInfo.collectionId'/>)"><s:property value="#auctionCollectionBidInfo.collectionTitle"/></a></td>
		<td><a href="javascript:view_favUser(<s:property value='#auctionCollectionBidInfo.userid'/>)"><s:property value="#auctionCollectionBidInfo.username"/></a></td>
		<td><s:property value="#auctionCollectionBidInfo.sexNickDesc"/></td>
		<td><s:property value="#auctionCollectionBidInfo.priceDesc"/></td>
		<td><s:date name="#auctionCollectionBidInfo.bidDate" format="yyyy-MM-dd HH:mm:ss" /></td>
        <td align="center" >
        	<a href="javascript:edit_auctionCollectionBid(<s:property value='#auctionCollectionBidInfo.id'/>)" >修改</a>
			<s:if test="%{userSession.userPermissions['/module/fav/auctionCollectionBid_edit.do'] != null}">
				<a href="javascript:edit_auctionCollectionBid(<s:property value='#auctionCollectionBidInfo.id'/>)" >修改</a> &nbsp;&nbsp;
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
