<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<html>
<head>
<title><s:property value="jsp_head_title"/>-天天论战管理</title>
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
		<td width="">内容</td>
		<td width="">甲方观点</td>
		<td width="">乙方观点</td>
		<td width="">支持甲方观点票数</td>
		<td width="">支持乙方观点票数</td>
		<td width="">操作</td>
      </tr>
      <s:iterator id="dailyPolemicInfo" value="%{pageList.results}" status="sta">
      <tr align="center" class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
		<td>
			<input name="items[<s:property value="#sta.index"/>].id" type="checkbox" class="ckboxItem" value="<s:property value="#dailyPolemicInfo.id"/>" onclick="check('checkboxtop','checkbox1');" />
		</td>
        <td>
			<a href="javascript:view_dailyPolemic(<s:property value='#dailyPolemicInfo.id'/>)"><s:property value="#dailyPolemicInfo.title"/></a>
		</td>
		<td><s:property value="#dailyPolemicInfo.content"/></td>
		<td><s:property value="#dailyPolemicInfo.aViewpoint"/></td>
		<td><s:property value="#dailyPolemicInfo.bViewpoint"/></td>
		<td><s:property value="#dailyPolemicInfo.supportAViewpoint"/></td>
		<td><s:property value="#dailyPolemicInfo.supportBViewpoint"/></td>
        <td align="center" >
        	<a href="javascript:edit_dailyPolemic(<s:property value='#dailyPolemicInfo.id'/>)" >修改</a>
			<s:if test="%{userSession.userPermissions['/module/fav/dailyPolemic_edit.do'] != null}">
				<a href="javascript:edit_dailyPolemic(<s:property value='#dailyPolemicInfo.id'/>)" >修改</a> &nbsp;&nbsp;
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
<script type="text/javascript">
</script>
</body>
</html>
