<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/feedbackMgr.js"></script>
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
            <th align="right" width="50%">反馈人：</th>
            <td><s:property value="feedbackForm.userName"/></td> 
        </tr>
        <tr>
            <th align="right">反馈内容：</th>
            <td><s:property value="feedbackForm.content"/></td> 
        </tr>
         <tr>
            <th align="right">反馈时间：</th>
            <td><s:date name="feedbackForm.time" format="yyyy-MM-dd HH:mm:ss"/></td> 
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