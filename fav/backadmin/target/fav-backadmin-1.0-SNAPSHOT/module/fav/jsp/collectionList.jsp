<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<html>
<head>
<title><s:property value="jsp_head_title"/>-藏品管理</title>
<%@include file="/jsp/common.jsp"%>
</head>

<body>
<%@include file="/jsp/include/pageInc.jsp"%>
<s:else>
	<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_result" border="0" cellspacing="1" cellpadding="1" align="center">
      <tr class="tr_head" align="center">
		<td><input id="checkboxtop"	type="checkbox" class="ckboxAll" title="选中/取消选中" value="" onclick="checkAll('checkboxtop','checkbox1');" /></td>
		<td width="">标题</td>
		<td width="">藏品类别</td>
		<td width="">所属时期</td>
		<td width="">藏品简介</td>
		<td width="">是否送拍</td>
		<td width="">是否出售</td>
		<td width="">是否鉴定</td>
		<td width="">标签</td>
		<td width="">图标</td>
		<td width="">热度</td>
		<td width="">鉴定结果</td>
		<td width="">状态</td>
		<td width="">发布人</td>
		<td width="">拍卖行</td>
		<td width="">估价</td>
		<td width="">成交价</td>
		<td width="">操作</td>
      </tr>
      <s:iterator id="collectionInfo" value="%{pageList.results}" status="sta">
      <tr align="center" class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
		<td>
			<input name="items[<s:property value="#sta.index"/>].id" type="checkbox" class="ckboxItem" value="<s:property value="#collectionInfo.id"/>" onclick="check('checkboxtop','checkbox1');" />
		</td>
        <td>
			<a href="javascript:view_collection(<s:property value='#collectionInfo.id'/>)"><s:property value="#collectionInfo.title"/></a>
		</td>
		<td><s:property value="#collectionInfo.categoryName"/></td>
		<td><s:property value="#collectionInfo.collectionPeriodName"/></td>
		<td width="20%"><s:property value="#collectionInfo.introduction"/></td>
		<td><s:property value="#collectionInfo.isSendRacketDesc"/></td>
		<td><s:property value="#collectionInfo.isSoldDesc"/></td>
		<td><s:property value="#collectionInfo.isIdentifyDesc"/></td>
		<td><s:property value="#collectionInfo.labelName"/></td>
		<td><img id="imgPathImg" src="${qnImageUrl}<s:property value="#collectionInfo.iconImg"/>?imageView2/1/w/32/h/32" style="cursor: pointer;" width="32" height="32" /></td>
		<td><s:property value="#collectionInfo.heat"/></td>
		<td><s:property value="#collectionInfo.identifyResult"/></td>
		<td><s:property value="#collectionInfo.statusDesc"/></td>
		<td><a href="javascript:view_favUser(<s:property value='#collectionInfo.insertId'/>)"><s:property value="#collectionInfo.insertUserName"/></a></td>
		<td><a href="javascript:view_auction(<s:property value='#collectionInfo.auctionId'/>)"><s:property value="#collectionInfo.auctionName"/></a></td>
		<td><s:property value="#collectionInfo.appraisalDesc"/></td>
		<td><s:property value="#collectionInfo.transactionPriceDesc"/></td>
        <td align="center" >
        	<a href="javascript:edit_collection(<s:property value='#collectionInfo.id'/>)" >修改</a>
			<s:if test="%{userSession.userPermissions['/module/fav/collection_edit.do'] != null}">
				<a href="javascript:edit_collection(<s:property value='#collectionInfo.id'/>)" >修改</a> &nbsp;&nbsp;
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
