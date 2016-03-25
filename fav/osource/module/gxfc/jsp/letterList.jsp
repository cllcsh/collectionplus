<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<html>
<head>
<title><s:property value="jsp_head_title"/>-系列管理</title>
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
        <td width="">类型</td>
		<td width="">发送人</td>
        <td width="">发送时间</td>
		<td width="">操作</td>
      </tr>
      <s:iterator id="letterInfo" value="%{pageList.results}" status="sta">
      <tr align="center" class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
		<td>
			<input name="items[<s:property value="#sta.index"/>].id" type="checkbox" class="ckboxItem" value="<s:property value="#letterInfo.id"/>" onclick="check('checkboxtop','checkbox1');" />
		</td>
        <td>
			<a href="javascript:view_letter(<s:property value='#letterInfo.id'/>)"><s:property value="#letterInfo.title"/></a>
		</td>
        <td><s:property value="#letterInfo.content"/></td>
        <td>
        	<s:if test="%{#letterInfo.type==1}">用户审核通过</s:if>
        	<s:elseif test="%{#letterInfo.type==2}">用户审核未通过</s:elseif>
        	<s:elseif test="%{#letterInfo.type==3}">用户资料更新</s:elseif>
        	<s:elseif test="%{#letterInfo.type==4}">上挂车源通过</s:elseif>
        	<s:elseif test="%{#letterInfo.type==5}">上挂车源未通过</s:elseif>
        	<s:elseif test="%{#letterInfo.type==6}">订单状态变更</s:elseif>
        	<s:elseif test="%{#letterInfo.type==7}">用户等待审核</s:elseif>
        	<s:elseif test="%{#letterInfo.type==8}">车源等待审核</s:elseif>
        </td>
        <td><s:property value="#letterInfo.senderName"/></td>
        <td><s:date name="#letterInfo.insertDate" format="yy-MM-dd HH:mm" /></td>
        <td align="center" >
<%--             <a href="javascript:edit_letter(<s:property value='#letterInfo.id'/>)" >删除</a> --%>
<%-- 			<s:if test="%{userSession.userPermissions['/module/gxfc/letter_edit.do'] != null}"> --%>
<%-- 				<a href="javascript:edit_letter(<s:property value='#letterInfo.id'/>)" >删除</a> &nbsp;&nbsp; --%>
<%--             </s:if> --%>
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
