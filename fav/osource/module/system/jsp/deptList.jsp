<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="dict" uri="/dict-tags" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>机构列表</title>

</head>

<body>
<%@include file="/jsp/include/pageInc.jsp" %>
<s:else>
        <table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0">
        <s:form id="queryList">
            <tr>
                <td>
                    <table class="tb_result" border="0" cellspacing="1" cellpadding="1" align="center">
                        <tr class="tr_head" align="center">
                            <td><input id="checkboxtop" type="checkbox" class="ckboxAll" title="选中/取消选中"
                                                  value=""
                                                  onclick="checkAll('checkboxtop','ckboxItem');"/>
                            </td>
                            <td width="">名称</td>
                            <td width="">电话</td>
                            <td width="">类型</td>
							<td width="">信息损失指数</td>
                            <td width="">风险损失指数</td>
							<td width="">安全状态指数</td>
                            <td width="">地址</td>
                            <td width="">操作</td>
                        </tr>
                        <s:iterator id="deptInfo" value="%{pageList.results}" status="sta">
                            <tr align="center" class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
                                <td>
                                    <input name="deptForm.deptEntity.id.stringValues" type="checkbox" class="ckboxItem"
                                           value="<s:property value="#deptInfo.id.stringValue"/>"
                                           onclick="check('checkboxtop','ckboxItem');"/>
                                </td>
                                <td>
                                	<s:property value="#deptInfo.name.stringValue"/>
	                                <%--<s:if test="%{userSession.userPermissions['/module/system/dept_edit.do'] != null}">
	                                    <a href="dept_view.do?deptInfoForm.deptId=<s:property value='#deptInfo.id.stringValue'/>">
	                                        <s:property value="#deptInfo.name.stringValue"/>
	                                    </a>
	                                </s:if>
	                                <s:else>
	                               	 	
										<a href="#" onclick="_view(<s:property value='#deptInfo.id.stringValue'/>)">
	                                        <s:property value="#deptInfo.name.stringValue"/>
	                                    </a>
	                                </s:else>--%>
                                </td>
                                <td>
                                    <s:property value="#deptInfo.phoneNo.stringValue"/>
                                </td>
								<td>
                                     <s:property value="#deptInfo.organizationTypeName"/>
                                </td>
                                <td>
                                    <s:property value="#deptInfo.uvalue.stringValue"/>
                                </td>
								<td>
                                    <s:property value="#deptInfo.fvalue.stringValue"/>
                                </td>
                                <td>
                                    <s:property value="#deptInfo.svalue.stringValue"/>
                                </td>
                            
								<td>
                                    <s:property value="#deptInfo.address.stringValue"/>
                                </td>
								

                               
                                <td align="center">
<%--                                <s:if test="%{userSession.userPermissions['/module/system/dept_edit.do'] != null}">--%>
                                    <a href="dept_edit.do?deptInfoForm.deptId=<s:property value='#deptInfo.id.stringValue'/>">
                                        修改
                                    </a>
<%--                                 </s:if>--%>
                                </td>
                            </tr>
                        </s:iterator>
                    </table>
                </td>
            </tr>
            </s:form>
        </table>
</s:else>

</body>
</html>
