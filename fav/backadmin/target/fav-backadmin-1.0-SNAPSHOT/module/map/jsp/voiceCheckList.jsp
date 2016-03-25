<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>
<%@taglib prefix="ict" uri="/ict-tags"%>

<html>
<head>
<title><s:property value="jsp_head_title"/>-区域监管</title>
<%@include file="/jsp/common.jsp"%>
</head>

<body>
<%@include file="/jsp/include/pageInc.jsp"%>
<s:else>
<s:form>
	<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_result" border="0" cellspacing="1" cellpadding="1" align="center">
      <tr class="tr_head" align="center">
		<td width="5%"><input id="checkboxtop"	type="checkbox" class="ckboxAll" title="选中/取消选中" value="" onclick="checkAll('checkboxtop','ckboxItem');" /></td>
		<td width="10%">抽查对象</td>
        <td width="20%">所属单位</td>
		<td width="10%">定位号码</td>
        <td width="12%">验证时间</td>
        <td width="12%">返回时间</td>
        <td width="12%">抽查结果</td>
        <td width="15%">操作</td>
      </tr>
      <s:iterator id="voiceCheckBean" value="%{pageList.results}" status="sta">
      <tr align="center" class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
		<td>
			<input name="items[<s:property value="#sta.index"/>].id" type="checkbox" class="ckboxItem" value="<s:property value='#voiceCheckBean.id'/>,<s:property value='#voiceCheckBean.criminalId'/>" onclick="check('checkboxtop','checkbox1');" />
		</td>
        <td>
			<s:property value="#voiceCheckBean.criminalName"/>
		</td>
        <td>
        	<ict:property beanContextId="deptSelect" value="#voiceCheckBean.deptId"/>
        </td>
        <td>
        	<s:property value="#voiceCheckBean.msdn"/>
        </td>
        <td>
        	<s:date format="yyyy-MM-dd HH:mm:ss" name="#voiceCheckBean.startDate" />
        </td>
        <td>
        	<s:date format="yyyy-MM-dd HH:mm:ss" name="#voiceCheckBean.endDate" />
        </td>
        <td>
        <s:if test=" #voiceCheckBean.summary =='' || #voiceCheckBean.summary == null || #voiceCheckBean.resultCode== 0">
        <dict:property codeType="voice_check_code" value="#voiceCheckBean.resultCode"/>
        </s:if>
        <s:else>
        	<dict:property codeType="voice_check_code" value="#voiceCheckBean.resultCode"/>/<s:property value="#voiceCheckBean.summary"/>
        </s:else>
        </td>
        <td align="center">
        <a href="javascript:view_voiceCheck(<s:property value='#voiceCheckBean.id'/>)" >查看录音</a> &nbsp;&nbsp;
		<s:if test="%{userSession.userPermissions['/module/map/voiceCheck_edit.do'] != null}">
        <a href="javascript:edit_voiceCheck(<s:property value='#voiceCheckBean.id'/>)" >修改</a> &nbsp;&nbsp;
        </s:if>
        </td>
      </tr>
      </s:iterator>
    </table>
    <script type="text/javascript">
	$(".tb_result").checkbox([{all:'ckboxAll',item:'ckboxItem'}]);
	</script>
	</td></tr></table>
</s:form>
</s:else>

</body>
</html>
