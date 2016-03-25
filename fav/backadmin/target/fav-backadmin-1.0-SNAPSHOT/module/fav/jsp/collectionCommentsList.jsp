<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<html>
<head>
<title><s:property value="jsp_head_title"/>-评论管理</title>
<%@include file="/jsp/common.jsp"%>
</head>

<body>
<%@include file="/jsp/include/pageInc.jsp"%>
<s:else>
	<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_result" border="0" cellspacing="1" cellpadding="1" align="center">
      <tr class="tr_head" align="center">
		<td><input id="checkboxtop"	type="checkbox" class="ckboxAll" title="选中/取消选中" value="" onclick="checkAll('checkboxtop','checkbox1');" /></td>
		<td width="150px;">评论内容</td>
		<td width="200px;">来源</td>
		<td width="">来源类型</td>
		<td width="">评论人</td>
		<td width="">评论时间</td>
		<td width="">评论类型</td>
		<!--<td width="">评分</td>-->
		<td width="">顶</td>
		<td width="">赞同</td>
		<td width="">反对</td>
		<td width="">操作</td>
      </tr>
      <s:iterator id="collectionCommentsInfo" value="%{pageList.results}" status="sta">
      <tr align="center" class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
		<td>
			<input name="items[<s:property value="#sta.index"/>].id" type="checkbox" class="ckboxItem" value="<s:property value="#collectionCommentsInfo.id"/>" onclick="check('checkboxtop','checkbox1');" />
		</td>
        <td>
			<a href="javascript:view_collectionComments(<s:property value='#collectionCommentsInfo.id'/>)"><s:property value="#collectionCommentsInfo.content"/></a>
		</td>
		<td>
			<s:if test='#collectionCommentsInfo.type == "0"'>
				<a href="javascript:view_collection(<s:property value='#collectionCommentsInfo.sourceId'/>)"><s:property value="#collectionCommentsInfo.sourceName"/></a>
			</s:if>
			<s:else>
				<a href="javascript:view_dynamic(<s:property value='#collectionCommentsInfo.sourceId'/>)"><s:property value="#collectionCommentsInfo.sourceName"/></a>
			</s:else>
		</td>
		<td><s:property value="#collectionCommentsInfo.sourceTypeDesc"/></td>
		<td><a href="javascript:view_favUser(<s:property value='#collectionCommentsInfo.friendId'/>)"><s:property value="#collectionCommentsInfo.userName"/></a></td>
		<td><s:date name="#collectionCommentsInfo.commentTime" format="yyyy-MM-dd HH:mm:ss" /></td>
		<td><s:property value="#collectionCommentsInfo.typeDesc"/></td>
		<!--<td><s:property value="#collectionCommentsInfo.point"/></td>-->
		<td><s:property value="#collectionCommentsInfo.topSize"/></td>
		<td><s:property value="#collectionCommentsInfo.likeSize"/></td>
		<td><s:property value="#collectionCommentsInfo.opposeSize"/></td>
        <td align="center" >
        	  <a href="javascript:edit_collectionComments(<s:property value='#collectionCommentsInfo.id'/>)" >修改</a>
			<s:if test="%{userSession.userPermissions['/module/fav/collectionComments_edit.do'] != null}">
				<a href="javascript:edit_collectionComments(<s:property value='#collectionCommentsInfo.id'/>)" >修改</a> &nbsp;&nbsp;
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
