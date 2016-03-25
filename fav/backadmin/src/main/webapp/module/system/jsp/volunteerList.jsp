<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>心理矫正志愿者登记列表</title>
</head>
<body>
<%@include file="/jsp/include/pageInc.jsp"%>
<s:else>
	<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_result" border="0" cellspacing="1" cellpadding="1" align="center">
      <tr class="tr_head">
		<td width="10%"><input id="checkboxtop"	type="checkbox" class="ckboxAll" title="选中/取消选中" value="" onclick="checkAll('checkboxtop','ckboxItem');" /></td>
        <td width="20%">全国志愿者注册编号</td>
        <td width="10%">姓名</td>
        <td width="10%">性别</td>
        <td width="20%">咨询师证书编号</td>
        <td width="20%">咨询师等级</td>
        <s:if test="%{userSession.userPermissions['/module/system/volunteer_edit.do'] != null}">
	 	<td width="10%">操作</td>
		</s:if>
      </tr>
      <s:iterator id="volunteerBean" value="%{pageList.results}" status="sta">
      <tr class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
		<td align="center"><input name="checkbox1" type="checkbox" class="ckboxItem" value="<s:property value="#volunteerBean.id"/>" onclick="check('checkboxtop','checkbox1');" /></td>
        <td align="center"><s:property value="#volunteerBean.registerId"/></td>
        <td align="center"><a href="javascript:view_volunteer(<s:property value='#volunteerBean.id'/>)"><s:property value="#volunteerBean.name"/></a></td>
        <td align="center">
        	<dict:property codeType="common-sex" value="#volunteerBean.sex"/>
        </td>
        <td align="center"><s:property value="#volunteerBean.consultantCertificateId"/></td>
        <td align="center">
        	<dict:property codeType="tb_volunteer-rank" value="#volunteerBean.rank"/>
        </td>
        <s:if test="%{userSession.userPermissions['/module/system/volunteer_edit.do'] != null}">
        <td align="center">
        	<s:if test="#volunteerBean.useFlag == 1">
   		 		<a href="javascript:edit_volunteer(<s:property value='#volunteerBean.id'/>)" ><s:text name="ictmap.modify"/></a>&nbsp;&nbsp;
   		 	</s:if>
		</td>
		</s:if>
      </tr>
      </s:iterator>
    </table>
	</td></tr></table>
</s:else>

</body>
</html>
