<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<html>
<head>
<title><s:property value="jsp_head_title"/>-消息管理</title>
<%@include file="/jsp/common.jsp"%>
</head>

<body>
<%@include file="/jsp/include/pageInc.jsp"%>
<s:else>
	<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_result" border="0" cellspacing="1" cellpadding="1" align="center">
      <tr class="tr_head" align="center">
		<td><input id="checkboxtop"	type="checkbox" class="ckboxAll" title="选中/取消选中" value="" onclick="checkAll('checkboxtop','checkbox1');" /></td>
		<td width="">发送 人</td>
		<td width="">接收人</td>
		<td width="">发送时间</td>
		<td width="">消息内容</td>
		<td width="">是否已读</td>
      </tr>
      <s:iterator id="messagesInfo" value="%{pageList.results}" status="sta">
      <tr align="center" class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
		<td>
			<input name="items[<s:property value="#sta.index"/>].id" type="checkbox" class="ckboxItem" value="<s:property value="#messagesInfo.id"/>" onclick="check('checkboxtop','checkbox1');" />
		</td>
        <td><a href="javascript:view_favUser(<s:property value='#messagesInfo.sender'/>)"><s:property value="#messagesInfo.senderName"/></a></td>
		<td><a href="javascript:view_favUser(<s:property value='#messagesInfo.receiver'/>)"><s:property value="#messagesInfo.receiverName"/></a></td>
		<td width="15%"><s:date name="#messagesInfo.sendTime" format="yyyy-MM-dd HH:mm:ss" /></td>
		<td><s:property value="#messagesInfo.content"/></td>
		<td><s:property value="#messagesInfo.isReadDesc"/></td>
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
