<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/dynamicCommentsMgr.js"></script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_add" width="100%"  border="0" cellpadding="1" cellspacing="1">
		<tr>
		    <th width="20%" style="text-align:right;">动态id<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="dynamicCommentsForm.dynamicId"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">好友用户id<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="dynamicCommentsForm.friendId"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">评论内容<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="dynamicCommentsForm.commentContent"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">评论时间<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="dynamicCommentsForm.commentTime"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">评论类型 0：评论 1：回复<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="dynamicCommentsForm.type"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">顶的数量<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="dynamicCommentsForm.topSize"/></td>
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