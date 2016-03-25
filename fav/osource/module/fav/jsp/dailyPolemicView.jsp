<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/dailyPolemicMgr.js"></script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_add" width="100%"  border="0" cellpadding="1" cellspacing="1">
		<tr>
		    <th width="20%" style="text-align:right;">标题<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="dailyPolemicForm.title"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">内容<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="dailyPolemicForm.content"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">甲方观点<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="dailyPolemicForm.aViewpoint"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">乙方观点<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="dailyPolemicForm.bViewpoint"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">支持甲方观点票数<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="dailyPolemicForm.supportAViewpoint"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">支持乙方观点票数<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="dailyPolemicForm.supportBViewpoint"/></td>
		</tr>
		<s:iterator id="img" value="imgs" status="sta">
			<tr>
	    		<th width="20%" style="text-align:right;">图片<font style="color: red;">*</font>：</th>
				<td width="40%"><img src="<s:property value="#img"/>" style="cursor: pointer;max-width:500px;max-height:400px;" /></td>
	       	</tr>
        </s:iterator>
        <tr>
          <td class="bottom" align="center" colspan="2">
			<input type="button" onclick="javascript:history.back();" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="返回"/>
          </td>
        </tr>
      </table></td>
    </tr>
  </table>

</body>
</html>