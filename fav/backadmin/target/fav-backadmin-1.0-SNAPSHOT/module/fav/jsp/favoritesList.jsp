<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<html>
<head>
<title><s:property value="jsp_head_title"/>-收藏夹管理</title>
<%@include file="/jsp/common.jsp"%>
</head>

<body>
<%@include file="/jsp/include/pageInc.jsp"%>
<s:else>
	<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_result" border="0" cellspacing="1" cellpadding="1" align="center">
      <tr class="tr_head" align="center">
		<td><input id="checkboxtop"	type="checkbox" class="ckboxAll" title="选中/取消选中" value="" onclick="checkAll('checkboxtop','checkbox1');" /></td>
		<td width="">收藏人</td>
		<td width="">藏品</td>
		<td width="">收藏时间</td>
		<!--  <td width="">操作</td>-->
      </tr>
      <s:iterator id="favoritesInfo" value="%{pageList.results}" status="sta">
      <tr align="center" class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
		<td>
			<input name="items[<s:property value="#sta.index"/>].id" type="checkbox" class="ckboxItem" value="<s:property value="#favoritesInfo.id"/>" onclick="check('checkboxtop','checkbox1');" />
		</td>
        <!--  <td>
			<a href="javascript:view_favorites(<s:property value='#favoritesInfo.id'/>)"><s:property value="#favoritesInfo.userName"/></a>
		</td>-->
		 <td><a href="javascript:view_favUser(<s:property value='#favoritesInfo.userId'/>)"><s:property value="#favoritesInfo.userName"/></a></td>
		<td><a href="javascript:view_collection(<s:property value='#favoritesInfo.collectionId'/>)"><s:property value="#favoritesInfo.title"/></a></td>
		<td><s:date name="#favoritesInfo.favoriteTime" format="yyyy-MM-dd HH:mm:ss" /></td>
       <!-- <td align="center" >
        	<a href="javascript:edit_favorites(<s:property value='#favoritesInfo.id'/>)" >修改</a>
			<s:if test="%{userSession.userPermissions['/module/fav/favorites_edit.do'] != null}">
				<a href="javascript:edit_favorites(<s:property value='#favoritesInfo.id'/>)" >修改</a> &nbsp;&nbsp;
            </s:if>
		</td>-->
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
