<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/dailyPolemicVoteMgr.js"></script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_add" width="100%"  border="0" cellpadding="1" cellspacing="1">
		<tr>
		    <th width="20%" style="text-align:right;">投票人<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="dailyPolemicVoteForm.userId"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">投票意见<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="dailyPolemicVoteForm.type"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">投票时间 <font style="color: red;">*</font>：</th>
		    <td width="40%"><s:date name="dailyPolemicVoteForm.insertDate" format="yyyy-MM-dd HH:mm:ss" /></td>
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