<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<html>
<head>
<title><s:property value="jsp_head_title"/>-列表</title>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/flowMgr.js"></script>
</head>

<body>
<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_result" border="0" cellspacing="1" cellpadding="1" align="center">
		<tr>
	        <td class="td_title" colspan="4" align="center">
	        	<b>产品信息</b>
	        </td>         
        </tr>
        <tr class="tr_head" align="center">
			<td width="" align="center">名称</td>
	        <td width="" align="center">描述</td>
	      </tr>
        <s:iterator id="orderFlow" value="%{orderFlowList}" status="sta">
	      <tr align="center" class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
	        <td>
				<s:property value="#orderFlow.name"/>
			</td>
			<td><s:property value="#orderFlow.description"/></td>
	      </tr>
       </s:iterator>
	</table>
</td></tr></table>

<%@include file="/jsp/include/pageInc.jsp"%>
<s:else>
	<form id="albumForm" name="albumForm">
	<s:hidden id="albumId" name="id"/>
	<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_result" border="0" cellspacing="1" cellpadding="1" align="center">
		<tr>
	        <td class="td_title" colspan="4" align="center">
	        	<b>附件信息</b>
	        </td>         
        </tr>
      <tr class="tr_head" align="center">
		<!--  <td><input id="checkboxtop"	type="checkbox" class="ckboxAll" title="选中/取消选中" value="" onclick="checkAll('checkboxtop','ckboxItem');" /></td>-->
		<td width="">名称</td>
        <td width="">路径</td>
		<!--  <td width="">操作</td>-->
      </tr>
      <s:iterator id="album" value="%{attachmentList}" status="sta">
      <tr align="center" class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
		<!--  <td>
			<input name="items[<s:property value="#sta.index"/>].id" type="checkbox" class="ckboxItem" value="<s:property value="#album.id"/>" onclick="check('checkboxtop','checkbox1');" />
		</td>-->
        <td>
			<s:property value="#album.fileName"/>
			<input type="hidden" value="<s:property value="#album.id"/>" name="albumList[<s:property value="#sta.index"/>].albumId"/>
		</td>
		<td><s:property value="#album.filePath"/></td>
        <!-- <td align="center" >        	
				<a href="javascript:edit_album(<s:property value='#album.id'/>)" >修改</a> &nbsp;&nbsp;
		</td> -->
      </tr>
      </s:iterator>
    </table>
    <script type="text/javascript">
	$(".tb_result").checkbox([{all:'ckboxAll',item:'ckboxItem'}]);
	</script>
	</td></tr></table>
	</form>
</s:else>

</body>
</html>
