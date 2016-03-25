<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/flowMgr.js"></script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_add" width="100%"  border="0" cellpadding="1" cellspacing="1">
		<tr>
	        <td class="td_title" colspan="4" align="center">
	        	<b>详细信息</b>
	        </td>         
        </tr>
        <tr>
            <th align="right" width="50%">编号：</th>
            <td><s:property value="flowForm.code"/></td> 
        </tr>
        <tr>
            <th align="right">名称：</th>
            <td><s:property value="flowForm.name"/></td> 
        </tr>
         <tr>
            <th align="right">父流程：</th>
            <td><s:property value="flowForm.parentName"/></td> 
        </tr>
         <tr>
            <th align="right">显示顺序：</th>
            <td><s:property value="flowForm.viewOrder"/></td> 
        </tr>
        
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