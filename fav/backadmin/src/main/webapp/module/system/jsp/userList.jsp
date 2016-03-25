<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<html>
<head>
<title><s:property value="jsp_head_title"/>-系列管理</title>
</head>

<body>
<%@include file="/jsp/include/pageInc.jsp"%>
<s:else>
	<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_result" border="0" cellspacing="1" cellpadding="1" align="center">
      <tr class="tr_head" align="center">
		<td width="40"><input id="checkboxtop"	type="checkbox" class="ckboxAll" title="选中/取消选中" value="" onclick="checkAll('checkboxtop','ckboxItem');" /></td>
		<td width="15%">登录名</td>
        <td width="15%">用户姓名</td>
		<td width="8%">公司名</td>
        <td width="8%">用户类型</td>
        <td width="8%">用户状态</td>
        <td width="8%">身份证</td>
        <td width="8%">工商注册号</td>
        <td width="8%">注册时间</td>
        <td width="8%">更新时间</td>
		<td width="15%">操作</td>
      </tr>
      <s:iterator id="userInfo" value="%{pageList.results}" status="sta">
      <tr align="center" class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
		<td>
			<s:if test="%{#userInfo.userType != 0}">
			<input name="items[<s:property value="#sta.index"/>].id" type="checkbox" class="ckboxItem" value="<s:property value="#userInfo.id"/>" onclick="check('checkboxtop','checkbox1');" />
			</s:if>
		</td>
        <td>
			<a href="javascript:view_user(<s:property value='#userInfo.id'/>)"><s:property value="#userInfo.loginName"/></a>
		</td>
        <td><s:property value='#userInfo.name'/></td>
        <td><s:property value='#userInfo.companyName'/></td>
        <td>
        	<s:if test="%{#userInfo.userType == 0}">系统管理员</s:if>
        	<s:elseif test="%{#userInfo.userType == 1}">客服人员</s:elseif>
        	<s:elseif test="%{#userInfo.userType == 2}">普通用户</s:elseif>
        </td>
        <td>
        	<s:if test="%{#userInfo.approveFlag == 0}">审核中</s:if>
        	<s:elseif test="%{#userInfo.approveFlag == 1}">审核通过</s:elseif>
        	<s:elseif test="%{#userInfo.approveFlag == 2}">未通过</s:elseif>
        	<s:elseif test="%{#userInfo.approveFlag == 3}">已冻结</s:elseif>
        </td>
        <td><s:property value='#userInfo.idCard'/></td>
        <td><s:property value='#userInfo.regNumber'/></td>
        <td><s:date name="#userInfo.insertDate" format="yy-MM-dd HH:mm" /></td>
        <td><s:date name="#userInfo.updateDate" format="yy-MM-dd HH:mm" /></td>
        <td align="center" >
        	<s:if test="%{#userInfo.userType != 0}">
        	<a href="javascript:edit_user(<s:property value='#userInfo.id'/>)" >修改</a>
        	</s:if>
        	<a href="javascript:openDialogUpload(<s:property value='#userInfo.id'/>);" >审核</a>
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
