<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<html>
<head>
<title><s:property value="jsp_head_title"/>-动态管理</title>
<%@include file="/jsp/common.jsp"%>
</head>

<body>
<%@include file="/jsp/include/pageInc.jsp"%>
<s:else>
	<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_result" border="0" cellspacing="1" cellpadding="1" align="center">
      <tr class="tr_head" align="center">
		<td><input id="checkboxtop"	type="checkbox" class="ckboxAll" title="选中/取消选中" value="" onclick="checkAll('checkboxtop','checkbox1');" /></td>
		<td width="">动态内容</td>
		<td width="">发布人</td>
		<td width="">发布动态时间</td>
		<td width="">评论数</td>
		<td width="">点赞数</td>
		<!--  <td width="">操作</td> -->
      </tr>
      <s:iterator id="dynamicInfo" value="%{pageList.results}" status="sta">
      <tr align="center" class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
		<td>
			<input name="items[<s:property value="#sta.index"/>].id" type="checkbox" class="ckboxItem" value="<s:property value="#dynamicInfo.id"/>" onclick="check('checkboxtop','checkbox1');" />
		</td>
        <td width="70%">
			<a href="javascript:view_dynamic(<s:property value='#dynamicInfo.id'/>)"><s:property value="#dynamicInfo.dynamicContent"/></a>
		</td>
		<td><a href="javascript:view_favUser(<s:property value='#dynamicInfo.releaseBy'/>)"><s:property value="#dynamicInfo.releaseName"/></a></td>
		<td><s:date name="#dynamicInfo.releaseTime" format="yyyy-MM-dd HH:mm:ss" /></td>
		<td><s:property value="#dynamicInfo.commentNumber"/></td>
		<td><s:property value="#dynamicInfo.likeNumber"/></td>
        <!--<td align="center" >
        	<a href="javascript:edit_dynamic(<s:property value='#dynamicInfo.id'/>)" >修改</a>
			<s:if test="%{userSession.userPermissions['/module/fav/dynamic_edit.do'] != null}">
				<a href="javascript:edit_dynamic(<s:property value='#dynamicInfo.id'/>)" >修改</a> &nbsp;&nbsp;
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
