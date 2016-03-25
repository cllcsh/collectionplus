<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<html>
<head>
<title><s:property value="jsp_head_title"/>-实时监控</title>
<%@include file="/jsp/common.jsp"%>
</head>

<body>
<%@include file="/jsp/include/pageInc.jsp"%>
<s:else>
	<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_result" border="0" cellspacing="1" cellpadding="1" align="center">
      <tr class="tr_head" align="center">
		<td><input id="checkboxtop"	type="checkbox" class="ckboxAll" title="选中/取消选中" value="" onclick="checkAll('checkboxtop','ckboxItem');" /></td>
		<td width="">机构名称</td>
        <td width="">矫正工作者</td>
		<td width="">社区矫正人员</td>
        <td width="">性别</td>
        <td width="">罪名</td>
        <td width="">手机号</td>
        <td width="">定位时间</td>
        <td width="18%">地点</td>
        <td width="8%">定位结果</td>
        <!--
		<td width="">操作</td>
      	-->
      </tr>
      <s:iterator id="actualBean" value="%{pageList.results}" status="sta">
      <tr align="center" class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
		<td>
			<input name="checkbox1" type="checkbox" class="ckboxItem" value="<s:property value='#actualBean.id'/>" onclick="check('checkboxtop','checkbox1');" />
		</td><!--
        <td>
			<a href="javascript:view_actual(<s:property value='#actualBean.id'/>)"><s:property value="#actualBean.name"/></a>
		</td>
        -->
        <td><s:property value='#actualBean.deptName'/></td>
        <td><s:property value='#actualBean.staffName'/></td>
        <td><s:property value='#actualBean.criminalName'/></td>
        <td><dict:property codeType="common-sex" value="#actualBean.sex" /></td>
        <td><s:property value='#actualBean.chargeDetail'/></td>
        <td><s:property value='#actualBean.locNo'/></td>
        <td><s:date format="yyyy-MM-dd HH:mm:ss" name="%{#actualBean.locDate}"/></td>
        <td><s:property value="#actualBean.placeName"/>
		</td>
		<td class="locCode" code="<s:property value="#actualBean.locCode"/>"><dict:property codeType="tb_location_loc_code" value="#actualBean.locCode"/><s:if test="#actualBean.locCode > 0"><font color="red">(错误码:<s:property value="#actualBean.locCode"/>)</font></s:if></td>
        <!--
        <td align="center" >
			<s:if test="%{userSession.userPermissions['/module/map/actual_edit.do'] != null}">
				<a href="javascript:edit_actual(<s:property value='#actualBean.id'/>)" >修改</a> &nbsp;&nbsp;
            </s:if>
		</td>
      --></tr>
      </s:iterator>
    </table>
    <script type="text/javascript">
	$(".tb_result").checkbox([{all:'ckboxAll',item:'ckboxItem'}]);
	</script>
	</td></tr></table>
</s:else>

</body>
</html>
