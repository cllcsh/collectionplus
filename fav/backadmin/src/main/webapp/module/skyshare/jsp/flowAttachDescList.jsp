<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<html>
<head>
<title><s:property value="jsp_head_title"/>-照片描述</title>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="<%=path%>/resource/fancybox/jquery.fancybox-1.3.4.js"></script>
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
	<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_result" border="0" cellspacing="1" cellpadding="1" align="center">
      <tr class="tr_head" align="center">
		<td width="">照片名称</td>
        <td width="">照片描述</td>
		<td width="">反馈日期</td>
        <td width="">照片预览</td>
		<td width="">订单信息</td>
      </tr>
      <s:iterator id="attachment" value="%{pageList.results}" status="sta">
      <tr align="center" class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>

        <td><s:property value="#attachment.name"/></td>
        <td>
			<s:property value="#attachment.description"/>
		</td>
		<td>
			<s:date format="yyyy-MM-dd" name="#attachment.updateDate"/>
		</td>
        <td><a rel="single" href="/<s:property value="#attachment.filePath"/>"><s:property value="#attachment.filePath"/></a></td>
        <td><a href="javascript:view_order(<s:property value='#attachment.orderId'/>)" >查看订单</a></td>
        
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
