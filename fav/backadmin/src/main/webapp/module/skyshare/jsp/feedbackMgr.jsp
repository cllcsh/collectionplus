<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title><s:property value="jsp_head_title"/></title>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/feedbackMgr.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$(document).ict({
		queryForm:'queryForm',
		queryUrl:'feedback_query.do',
		queryBtn:'btn_query',
		pagecontent:'pagecontent',
		addUrl:'feedback_add.do',
		delsUrl:'feedback_deletes.do',
		delsBtn:'btn_del'
	});
});
</script>
</head>

<body>
<form id="queryForm" action="">
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_query" border="0" cellspacing="1" cellpadding="1" align="center" >
	<tr>
          <th align="right">有效期起：</th>
          <td>
			<input id="start_date" name="feedbackForm.startTime" readonly="readonly" type="text" size="25" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'end_date\')}'})" class="Wdate"/>
          <%--  <s:textfield id="start_date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'end_date\')}'})" cssClass="Wdate"></s:textfield> --%>
          </td>
          <th align="right">有效期止：</th>
          <td>
			<input id="end_date" name="feedbackForm.endTime" readonly="readonly" type="text" size="25" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'start_date\')}'})" class="Wdate"/>
             <%-- <s:textfield  id="end_date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssClass="Wdate"></s:textfield> --%>
          </td>
        </tr>		
		<tr>
			<td class="bottom" colspan="4" align="center">
		   		<s:if test="%{userSession.userPermissions['/module/skyshare/feedback_query.do'] != null}">
					<input name="btn" id="btn_query" type="button" class="button" value="查询" />
		   		</s:if>
				<input name="btn" type="reset" class="button" value="重置" />
			</td>
		</tr>
	</table>
</td></tr></table>
</form>
<br/>
<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td align="right" style="padding-right: 20px">
			<s:if test="%{userSession.userPermissions['/module/skyshare/feedback_add.do'] != null}">
				<input type="button" id="btn_add" class="button" value="添加"/>
			</s:if>
			<s:if test="%{userSession.userPermissions['/module/skyshare/feedback_deletes.do'] != null}">
				<input type="button" id="btn_del" class="button" value="删除"/>
			</s:if>
		</td>
	</tr>
	<tr>
		<td>
			<form id="listForm" action="">
				<div id="pagecontent"></div>
			</form>
		</td>
	</tr>
</table>
</body>
</html>
