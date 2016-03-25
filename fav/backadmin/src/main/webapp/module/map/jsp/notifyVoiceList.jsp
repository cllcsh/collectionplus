<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>
<%@taglib prefix="ict" uri="/ict-tags"%>
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<title><s:property value="jsp_head_title"/></title>
<style type="text/css">
.txt {
	color: #003366;
	border-bottom: 0px; 
	border-top: 0px;
	border-left: 0px;
	border-right: 0px;
	background-color: transparent; /* 背景色透明 */
	text-align: center;
}
</style>
<script type="text/javascript">
//查看历史验证记录
function voice_view(id, msdn)
{ 
	location.href("<%=path%>/module/map/voiceCheck_init.do?voiceCheckForm.criminalId="+id);
}
</script>
</head>
<body>
<%@include file="/jsp/include/pageInc.jsp"%>
<s:else>

	<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_result" border="0" cellspacing="1" cellpadding="1" align="center">
      <tr class="tr_head">
		<td><input id="checkboxtop"	type="checkbox" class="ckboxAll" title="选中/取消选中" value="" onclick="checkAll('checkboxtop','ckboxItem');" /></td>
        <td width="10%">抽查对象</td>
         <td width="15%">所属单位</td>
        <td width="14%">矫正工作者</td>
        <td width="10%">主要罪名</td>
        <td width="8%">矫正类型</td>
        <td width="10%">定位号码</td>
        <td width="10%">声纹识别号码</td>
        <td width="23%">操作</td>
      </tr>
      <s:iterator id="criminal" value="%{pageList.results}" status="sta">
      <tr align="center" class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
		<td>
			<input name='criminalList[<s:property value="#sta.index"/>].id' type="checkbox" class="ckboxItem" value="<s:property value="#criminal.id"/>" onclick="check('checkboxtop','checkbox1');" />
        </td>
        <td align="center"><s:property value="#criminal.name"/></td>
        <td><ict:property beanContextId="deptSelect" value="#criminal.dept_id"/></td>
        <td align="center"><s:property value="#criminal.staff_name"/></td>
        <td><s:property value="#criminal.charge_detail"/></td>
        <td><dict:property codeType="common-outside_type" value="#criminal.outside_type"/></td>
        <td><s:property value="#criminal.loc_no"/></td>
          <td>
        	  <input id="voiceNo" type="text" class="txt" value="<s:property  value="#criminal.voiceNo"/>" readonly="true"/>
          </td>
		<td> 
			<s:if test='(#criminal.voiceNo) != null && ((#criminal.voiceNo) != "")'>
				&nbsp;&nbsp;&nbsp;注册&nbsp;&nbsp;
				<s:if test="%{userSession.userPermissions['/module/map/notifyVoice_check.do'] != null}">
					<a href="javascript:voice_check(<s:property value='#criminal.id'/>, '<s:property value='#criminal.loc_no'/>', '<s:property  value="#criminal.voiceNo"/>')" >验证</a> &nbsp;&nbsp;
	            </s:if>
				<s:if test="%{userSession.userPermissions['/module/map/notifyVoice_update.do'] != null}">
					<a href="javascript:voice_update(<s:property value='#criminal.id'/>, '<s:property value='#criminal.loc_no'/>', '<s:property  value="#criminal.voiceNo"/>')" >修改</a> &nbsp;&nbsp;
	            </s:if>
				<s:if test="%{userSession.userPermissions['/module/map/notifyVoice_delete.do'] != null}">
					<a href="javascript:voice_delete(<s:property value='#criminal.id'/>, '<s:property value='#criminal.loc_no'/>', '<s:property  value="#criminal.voiceNo"/>')" >删除</a> &nbsp;&nbsp;
	            </s:if>
			</s:if>
			<s:else>
				<s:if test="%{userSession.userPermissions['/module/map/notifyVoice_register.do'] != null}">
					<a href="javascript:voice_register(<s:property value='#criminal.id'/>, <s:property value='#criminal.loc_no'/>)" >注册</a>&nbsp;&nbsp;
           		</s:if>
           			验证&nbsp;&nbsp;&nbsp;&nbsp;修改&nbsp;&nbsp;&nbsp;&nbsp;删除&nbsp;&nbsp;&nbsp;&nbsp;
			</s:else>
			<s:if test="%{userSession.userPermissions['/module/map/voiceCheck_init.do'] != null}">
				<a href="javascript:voice_view(<s:property value='#criminal.id'/>, <s:property value='#criminal.loc_no'/>)" >历史验证</a> &nbsp;&nbsp;
	        </s:if>
		</td>
      </tr>
      </s:iterator>
    </table>
	</td></tr></table>
</s:else>
<SCRIPT type="text/javascript">
	$(".tb_result").checkbox([{all:'ckboxAll',item:'ckboxItem'}]);
</SCRIPT>
</body>
</html>