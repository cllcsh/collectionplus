<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>矫正专家列表</title>

</head>

<body>
<%@include file="/jsp/include/pageInc.jsp"%>
<s:else>
	<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_result" border="0" cellspacing="1" cellpadding="1" align="center">
      <tr class="tr_head" align="center">
		<td><input id="checkboxtop"	type="checkbox" class="ckboxAll" title="选中/取消选中" value="" onclick="checkAll('checkboxtop','ckboxItem');" /></td>
        <td width="10%">姓名</td>
        <td width="15%">证书编号</td>
        <td width="10%">性别</td>
		<td width="15%">工作单位</td>
        <td width="15%">移动电话</td>
        <td width="15%">职称</td>
		<td width="10%">操作</td>
      </tr>
      <s:iterator id="specialistInfo" value="%{pageList.results}" status="sta">
      <tr align="center" class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
		<td>
			<input name="checkbox1" type="checkbox" class="ckboxItem" value="<s:property value="#specialistInfo.id"/>" onclick="check('checkboxtop','checkbox1');" />
		</td>
        <td>
			<a href="javascript:view_specialist(<s:property value='#specialistInfo.id'/>)"><s:property value="#specialistInfo.name"/></a>
		</td>
		<td><s:property value="#specialistInfo.certificateId"/></td>
        <td>
           	<dict:property codeType="common-sex" value="#specialistInfo.sex" />
        </td>
        <td><s:property value="#specialistInfo.workUnit"/></td>
        <td><s:property value="#specialistInfo.mobileNo"/></td>
        <td><dict:property codeType="tb_specialist-professional_title" value="#specialistInfo.professionalTitle" /></td>
        <td align="center" >
			<s:if test="%{userSession.userPermissions['/module/system/specialist_edit.do'] != null}">
				<a href="javascript:edit_specialist(<s:property value='#specialistInfo.id'/>)" >修改</a> &nbsp;&nbsp;
            </s:if>
		</td>
      </tr>
      </s:iterator>
    </table>
	</td></tr></table>
</s:else>

</body>
</html>
