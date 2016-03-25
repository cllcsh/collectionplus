<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<html>
<head>
<title><s:property value="jsp_head_title"/>-消息</title>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="<%=path%>/resource/fancybox/jquery.fancybox-1.3.4.js"></script>
<script type="text/javascript" src="js/messageMgr.js"></script>
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
      <tr class="tr_head" align="center">
		<td><input id="checkboxtop"	type="checkbox" class="ckboxAll" title="选中/取消选中" value="" onclick="checkAll('checkboxtop','ckboxItem');" /></td>
		<td width="">标题</td>
        <td width="60%">内容</td>
		<td width="">操作</td>
      </tr>
      <s:iterator id="album" value="%{pageList.results}" status="sta">
      <tr align="center" class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
		<td>
			<input name="items[<s:property value="#sta.index"/>].id" type="checkbox" class="ckboxItem" value="<s:property value="#album.id"/>" onclick="check('checkboxtop','ckboxItem');" />
		</td>
        <td>
			<!-- <a href="javascript:view_album('<s:property value="#album.picPath"/>')" ><s:property value="#album.name"/> </a> -->&nbsp;&nbsp;
			<s:property value="#album.name"/>
			<input type="hidden" value="<s:property value="#album.id"/>" name="albumList[<s:property value="#sta.index"/>].albumId"/>
		</td>
		<td><a rel="single" href="/<s:property value='#album.picPath'/>"><s:property value="#album.picPath"/></a>
		 
		</td>
        <td align="center" >        	
				<a rel="" href="javascript:edit_album(<s:property value='#album.id'/>)" >修改</a> &nbsp;&nbsp;
		</td>
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
