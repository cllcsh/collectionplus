<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>
<html>
<head>
<%@include file="/jsp/meta.jsp" %>
<title><s:property value="jsp_head_title"/></title>
</head>
<body style="overflow:hidden"><s:fielderror/>
<s:hidden name="rpTypeBean.id"></s:hidden>
<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_view" width="100%"  border="0" cellpadding="1" cellspacing="1">
		<tr>
			<td class="td_title" colspan="2" align="center">
				奖惩类型查看
			</td>
		</tr>
		<tr>
			<th class="tb_lable" width="20%" align="right">
				奖惩类型名称：
			</th>
			<td>
				<s:property value="rpTypeBean.rpName" />
			</td>
		</tr>
		<tr>
			<th align="right">
				默认值：
			</th>
			<td>
			<s:property value="rpTypeBean.defScore" />
			</td>
		</tr>
		<tr>
			<th align="right">
				奖惩类型：
			</th>
			<td>
			<dict:property codeType="common-rp_type" value="rpTypeBean.rp"/>
			</td>
		</tr>
        <tr>
          <td class="bottom" align="center" colspan="2">
			<input type="button" onclick="javascript:closeDialog();" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="关闭"/>
          </td>
        </tr>
      </table></td>
    </tr>
  </table>

</body>
</html>