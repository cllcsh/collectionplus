<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<html>
<head>
<title><s:property value="jsp_head_title"/>-列表</title>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="<%=path%>/resource/fancybox/jquery.fancybox-1.3.4.js"></script>
<script type="text/javascript" src="js/flowMgr.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("a[rel=single]").fancybox({
		  openEffect  : 'none',
		  closeEffect	: 'none',
          helpers: {
              title : {
                  type : 'float'
              }
          }
      });
});
</script>
</head>

<body>

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
            <td width="3%" align="center">序号</td>
			<td width="15%" align="center">名称</td>
	        <td width="50%" align="center">描述</td>
			<td width="15%">操作</td>
	      </tr>
        <s:iterator id="orderFlow" value="%{orderFlowList}" status="sta">
	      <tr align="center" class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
	       <td>
				<s:property value="#sta.index+1"/>
			</td>
	        <td style="word-break:break-all">
				<s:property value="#orderFlow.name"/>
			</td>
			<td style="word-break:break-all"><s:property value="#orderFlow.description"/></td>
			<td><a href="javascript:editFlowPic(<s:property value='#orderFlow.id'/>);">修改</a>&nbsp;&nbsp;<a href="javascript:deleteFlowPic(<s:property value='#orderFlow.id'/>);">删除</a></td>
	      </tr>
	      <tr>
	        <td width="20%" align="center"><b>附件信息</b></td>
	      	<td colspan="3"><table width="100%">
			      <tr class="tr_head" align="center">
					<!--  <td><input id="checkboxtop"	type="checkbox" class="ckboxAll" title="选中/取消选中" value="" onclick="checkAll('checkboxtop','ckboxItem');" /></td>-->
					<td width="20%">名称</td>
					<td width="30%">预览</td>
			        <td width="40%">反馈</td>
					<!--  <td width="">操作</td>-->
			      </tr>
			      <s:iterator id="album" value="%{#orderFlow.attachmentList}" status="sta">
			      <tr align="center" class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
					<!--  <td>
						<input name="items[<s:property value="#sta.index"/>].id" type="checkbox" class="ckboxItem" value="<s:property value="#album.id"/>" onclick="check('checkboxtop','checkbox1');" />
					</td>-->
			        <td>
						<s:property value="#album.name"/>
						<input type="hidden" value="<s:property value="#album.id"/>" name="albumList[<s:property value="#sta.index"/>].albumId"/>
					</td>
					<td><a  href="/<s:property value="#album.filePath"/>" target="view_window"><s:property value="#album.filePath"/></a></td>
					<td><s:property value="#album.description"/></td>
			        <!-- <td align="center" >        	
							<a href="javascript:edit_album(<s:property value='#album.id'/>)" >修改</a> &nbsp;&nbsp;
					</td> -->
			      </tr>
			      </s:iterator>
	      	</table></td>
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
