<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<html>
<head>
<title><s:property value="jsp_head_title"/>-动态评论顶管理</title>
<%@include file="/jsp/common.jsp"%>
</head>

<body>
<%@include file="/jsp/include/pageInc.jsp"%>
<s:else>
	<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_result" border="0" cellspacing="1" cellpadding="1" align="center">
      <tr class="tr_head" align="center">
		<td><input id="checkboxtop"	type="checkbox" class="ckboxAll" title="选中/取消选中" value="" onclick="checkAll('checkboxtop','checkbox1');" /></td>
		<td width="200px;">来源</td>
		<td width="">来源类型</td>
		<td width="150px;">评论内容</td>
		<td width="">顶的人</td>
		<td width="">顶的时间</td>
		<td width="">操作</td>
      </tr>
      <s:iterator id="dynamicCommentTopInfo" value="%{pageList.results}" status="sta">
      <tr align="center" class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
		<td>
			<input name="items[<s:property value="#sta.index"/>].id" type="checkbox" class="ckboxItem" value="<s:property value="#dynamicCommentTopInfo.id"/>" onclick="check('checkboxtop','checkbox1');" />
		</td>
        <!--  <td>
			<a href="javascript:view_dynamicCommentTop(<s:property value='#dynamicCommentTopInfo.id'/>)"><s:property value="#dynamicCommentTopInfo.dynamicId"/></a>
		</td>-->
		<td>
			<s:if test='#dynamicCommentTopInfo.sourceType == "1"'>
				<a href="javascript:view_dynamic(<s:property value='#dynamicCommentTopInfo.sourceId'/>)"><s:property value="#dynamicCommentTopInfo.sourceName"/></a></td>
			</s:if>
			<s:else>
				<a href="javascript:view_collection(<s:property value='#dynamicCommentTopInfo.sourceId'/>)"><s:property value="#dynamicCommentTopInfo.sourceName"/></a></td>
			</s:else>
		<td><s:property value="#dynamicCommentTopInfo.sourceTypeDesc"/></td>
		<td><a href="javascript:view_collectionComments(<s:property value='#dynamicCommentTopInfo.commentId'/>)"><s:property value="#dynamicCommentTopInfo.commentContent"/></a></td>
		<td><a href="javascript:view_favUser(<s:property value='#dynamicCommentTopInfo.friendId'/>)"><s:property value="#dynamicCommentTopInfo.userName"/></a></td>
		<td><s:date name="#dynamicCommentTopInfo.topTime" format="yyyy-MM-dd HH:mm:ss" /></td>
		
        <td align="center" >
        	<a href="javascript:edit_dynamicCommentTop(<s:property value='#dynamicCommentTopInfo.id'/>)" >修改</a>
			<s:if test="%{userSession.userPermissions['/module/fav/dynamicCommentTop_edit.do'] != null}">
				<a href="javascript:edit_dynamicCommentTop(<s:property value='#dynamicCommentTopInfo.id'/>)" >修改</a> &nbsp;&nbsp;
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