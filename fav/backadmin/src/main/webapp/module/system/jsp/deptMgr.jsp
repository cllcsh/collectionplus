<%@ page import="com.osource.base.model.ColBean" %>
<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="dict" uri="/dict-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <%@include file="/jsp/common.jsp" %>
    <script type="text/javascript" src="js/deptMgr.js"></script>
    <script type="text/javascript">
    </script>
</head>
<body onload="loadPage()">
<%@include file="/jsp/include/navigation.jsp"%>
<form id="queryForm" action="">
    <table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <td>
                <table class="tb_query" border="0" cellspacing="1" cellpadding="1" align="center">
                    <tr>
            <%--       <th>
                            司法单位代码：
                        </th>
                        <td>
                            <s:textfield size="20" cssClass="td03" name="deptInfoForm.code" id="code"
                                         maxlength="60"
                                         onblur="clearBlank(this);"/>
                        </td> --%>
                        <!--<th>
                            机构编码：
                        </th>
                        <td>
                            <s:textfield size="20" cssClass="td03" name="deptInfoForm.jgbm" id="jgbm"
                                         maxlength="60"
                                         onblur="clearBlank(this);"/>
                        </td>
                        -->
                        <th>
                           	机构名称：
                        </th>
                        <td>
                            <s:textfield size="20" cssClass="td03" name="deptInfoForm.name" id ="name"
                                         maxlength="60"
                                         onblur="clearBlank(this);"/>
                        </td>
                        <!--<th>
                            	负责人：
                        </th>
                        <td >
                            <s:textfield size="20" cssClass="td03" name="deptInfoForm.manager" id="manager"
                                         maxlength="60"
                                         onblur="clearBlank(this);"/>
                        </td>
                    -->
                    	<th>
                            	上级机构：
                        </th>
                        <td>
                            <s:select list="deptInfoForm.deptList" id="upperDept"
                                      listKey="key" listValue="value"
                                      value="deptInfoForm.deptEntity.upperDept.stringValue"
                                      name="deptInfoForm.upperDept" emptyOption="true" cssStyle="width:135px">
                            </s:select>
                        </td>
                    </tr>
                    <!--  
                    <tr>
                        <th>
                            单位地址：
                        </th>
                        <td>
                            <s:textfield size="20" cssClass="td03" name="deptInfoForm.address" id="address"
                                         maxlength="60"
                                         onblur="clearBlank(this);"/>
                        </td>
                        <th>
                            行政级别：
                        </th>
                        <td>
                            <dict:select codeType="sfb_jgxzjb" emptyOption="true"  id="rank"
                                         name="deptInfoForm.rank" cssStyle="width:135px"/>
                        </td>
                    </tr>
                    --><tr>
                        <td class="bottom" colspan="6" align="center">
                            <input name="btn" type="button" class="button" onmouseout="this.className = 'button'"
                                   onmouseover="this.className = 'buttonOver'" onclick="_query();" value="查询"/>
                            <input name="btn" type="reset" class="button" onmouseout="this.className = 'button'"
                                   onmouseover="this.className = 'buttonOver'" value="重置"/>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</form>
<br/>
<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
        <td align="right" style="padding-right: 20px">
        
        	
        	<s:if test="%{userSession.userPermissions['/module/system/dept_add.do'] != null}">
	            <input type="button" id="btn_add_dept" onclick="_edit();" class="button"
                   onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="添加"/>
			</s:if>
                   
<s:if test="%{userSession.userPermissions['/module/system/dept_delete.do'] != null}">
            <input type="button" onclick="_delete();" class="button"
                   onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="删除"/>
</s:if>
            
        </td>
    </tr>
    <tr>
        <td>
            <div id="pagecontent"></div>
        </td>
    </tr>
</table>
<div id="dialog" title="机构管理"></div>
</body>
</html>