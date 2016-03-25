<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>
<%@taglib prefix="ict" uri="/ict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/checkMgr.js"></script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<s:form>
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_add" width="100%"  border="0" cellpadding="1" cellspacing="1">
		<tr>
			<th width="20%" align="right">抽查对象：</th>
		    <td width="30%">
		    	<s:property value="checkForm.criminalName"/>
		    </td>
		    <th width="20%" align="right">抽查日期：</th>
			<td width="30%">
				<s:property value="checkForm.checkDate"/>
			</td>
		</tr>
		<tr>
			<th> 
				抽查类型：
			</th>
			<td>
				<s:property value="checkForm.checkResultName"/>
			</td>
			<th>
				分值：
			</th>
			<td>
				<s:property value="checkForm.cent"/>
			</td>
		</tr>
		<tr>
			<th width="20%" align="right">抽查内容：</th>
			<td colspan="3">
				<s:property value="checkForm.checkExplain"/>
			</td>
		</tr>
		<tr>
			<th width="20%" align="right">备注：</th>
			<td colspan="3">
				<s:property value="checkForm.remark"/>
			</td>
		</tr>
        <tr>
          <td class="bottom" align="center" colspan="4">
			<input type="button" onclick="javascript:history.back();" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="返回"/>
          </td>
        </tr>
      </table></td>
    </tr>
  </table>
</s:form>
</body>
</html>