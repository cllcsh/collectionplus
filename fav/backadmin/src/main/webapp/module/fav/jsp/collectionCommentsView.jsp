<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/collectionCommentsMgr.js"></script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_add" width="100%"  border="0" cellpadding="1" cellspacing="1">
		<tr>
		    <th width="20%" style="text-align:right;">评论来源<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="collectionCommentsForm.sourceName"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">评论来源类型<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="collectionCommentsForm.sourceTypeDesc"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">评论人<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="collectionCommentsForm.userName"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">评论内容<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="collectionCommentsForm.content"/></td>
		</tr>
		<!--  <tr>
		    <th width="20%" style="text-align:right;">评分<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="collectionCommentsForm.point"/></td>
		</tr>-->
		<tr>
		    <th width="20%" style="text-align:right;">评论时间<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:date name="collectionCommentsForm.commentTime" format="yyyy-MM-dd HH:mm:ss" /></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">评论类型<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="collectionCommentsForm.typeDesc"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">回复评论<font style="color: red;">*</font>：</th>
		    <td width="40%"><a href="javascript:view_collectionComments(<s:property value='collectionCommentsForm.replyId'/>)"><s:property value="collectionCommentsForm.replyContent"/></a></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">回复评论人<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="collectionCommentsForm.replyUserName"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">顶的数量<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="collectionCommentsForm.topSize" /></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">赞同的数量<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="collectionCommentsForm.likeSize" /></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">反对的数量<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="collectionCommentsForm.opposeSize" /></td>
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