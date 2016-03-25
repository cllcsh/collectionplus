<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<html>
<head>
<title><s:property value="jsp_head_title"/>-通知下载</title>
<%@include file="/jsp/common.jsp"%>
</head>

<body>
<%@include file="/jsp/include/pageInc.jsp"%>
<s:else>
	<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_result" border="0" cellspacing="1" cellpadding="1" align="center">
      <tr class="tr_head" align="center">
		<td><input id="checkboxtop"	type="checkbox" class="ckboxAll" title="选中/取消选中" value="" onclick="checkAll('checkboxtop','ckboxItem');" /></td>
		<td width="">文件名</td>
		<td width="">所属机构</td>
		<td width="">文件描述</td>
		<td width="">操作</td>
      </tr>
      <s:iterator id="attachmentBean" value="%{pageList.results}" status="sta">
      <tr align="center" class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
		<td>
			<input name="items[<s:property value="#sta.index"/>].id" type="checkbox" class="ckboxItem" value="<s:property value="#attachmentBean.id"/>" onclick="check('checkboxtop','checkbox1');" />
		</td>
        <td>
			<a href="downloadFile_download.do?id=<s:property value='#attachmentBean.fileName'/>&fileName=<s:property value="#attachmentBean.name"/>"><s:property value="#attachmentBean.name"/></a>
		</td>
		<td><s:property value="#attachmentBean.deptName"/></td>
        <td><s:property value="#attachmentBean.description"/></td>
        <td align="center" >
        	<a href="attachment_edit.do?id=<s:property value='#attachmentBean.id'/>">修改</a>
        	<a href="downloadFile_download.do?id=<s:property value='#attachmentBean.fileName'/>&fileName=<s:property value="#attachmentBean.name"/>">下载</a>
		</td>
      </tr>
      </s:iterator>
    </table>
    <script type="text/javascript">
	$(".tb_result").checkbox([{all:'ckboxAll',item:'ckboxItem'}]);
	</script>
	</td></tr></table>
</s:else>

</body>
</html>
