<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/messagesMgr.js"></script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_add" width="100%"  border="0" cellpadding="1" cellspacing="1">
		<tr>
		    <th width="20%" style="text-align:right;">发送 人（发送人的用户id）<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="messagesForm.sender"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">接收人（接收人的用户id）<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="messagesForm.receiver"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">发送时间<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="messagesForm.sendTime"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">消息内容<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="messagesForm.content"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">是否已读，Y:已读，N：未读<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="messagesForm.isRead"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">是否删除，Y:已删除，N：未删除<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="messagesForm.isDelete"/></td>
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