<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<html>
<head>
<title><s:property value="jsp_head_title"/>-首页管理</title>
<%@include file="/jsp/common.jsp"%>
</head>

<body>
<%@include file="/jsp/include/pageInc.jsp"%>
<s:else>
	<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_result" border="0" cellspacing="1" cellpadding="1" align="center">
      <tr class="tr_head" align="center">
		<td><input id="checkboxtop"	type="checkbox" class="ckboxAll" title="选中/取消选中" value="" onclick="checkAll('checkboxtop','checkbox1');" /></td>
		<td width="">首页</td>
		<td width="">广告位图片</td>
		<td width="">广告位图片</td>
		<td width="">广告位图片</td>
		<td width="">广告位图片</td>
		<td width="">广告位图片</td>
		<td width="">广告位图片</td>
		<td width="">广告位图片</td>
		<td width="">广告位图片</td>
		<td width="">广告位图片</td>
		<td width="">推荐藏品展示个数</td>
		<td width="">热门藏家展示个数</td>
		<td width="">操作</td>
      </tr>
      <s:iterator id="homeInfo" value="%{pageList.results}" status="sta">
      <tr align="center" class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
		<td>
			<input name="items[<s:property value="#sta.index"/>].id" type="checkbox" class="ckboxItem" value="<s:property value="#homeInfo.id"/>" onclick="check('checkboxtop','checkbox1');" />
		</td>
        <td>
			<a href="javascript:view_home(<s:property value='#homeInfo.id'/>)">首页</a>
		</td>
        <td><img src="${qnImageUrl}<s:property value="#homeInfo.adImages1"/>?imageView2/1/w/32/h/32" width="32" height="32" /></td>
		<td><img src="${qnImageUrl}<s:property value="#homeInfo.adImages2"/>?imageView2/1/w/32/h/32" width="32" height="32" /></td>
		<td><img src="${qnImageUrl}<s:property value="#homeInfo.adImages3"/>?imageView2/1/w/32/h/32" width="32" height="32" /></td>
		<td><img src="${qnImageUrl}<s:property value="#homeInfo.adImages4"/>?imageView2/1/w/32/h/32" width="32" height="32" /></td>
		<td><img src="${qnImageUrl}<s:property value="#homeInfo.adImages5"/>?imageView2/1/w/32/h/32" width="32" height="32" /></td>
		<td><img src="${qnImageUrl}<s:property value="#homeInfo.adImages6"/>?imageView2/1/w/32/h/32" width="32" height="32" /></td>
		<td><img src="${qnImageUrl}<s:property value="#homeInfo.adImages7"/>?imageView2/1/w/32/h/32" width="32" height="32" /></td>
		<td><img src="${qnImageUrl}<s:property value="#homeInfo.adImages8"/>?imageView2/1/w/32/h/32" width="32" height="32" /></td>
		<td><img src="${qnImageUrl}<s:property value="#homeInfo.adImages9"/>?imageView2/1/w/32/h/32" width="32" height="32" /></td>
		<td><s:property value="#homeInfo.recommendCollectionShowNum"/></td>
		<td><s:property value="#homeInfo.topCollectorsShowNum"/></td>
        <td align="center" >
        	<a href="javascript:edit_home(<s:property value='#homeInfo.id'/>)" >修改</a>
			<s:if test="%{userSession.userPermissions['/module/fav/home_edit.do'] != null}">
				<a href="javascript:edit_home(<s:property value='#homeInfo.id'/>)" >修改</a> &nbsp;&nbsp;
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
