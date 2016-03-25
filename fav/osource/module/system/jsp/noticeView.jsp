<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="<%=path%>/resource/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="js/noticeMgr.js"></script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_add" width="100%"  border="0" cellpadding="1" cellspacing="1">
		<tr>
				<th  width="15%">
						标题： 
						</th>
						<td colspan="2">
							<s:property value="noticeForm.title"/>							
						</td>
					</tr>
					<tr>
						<th  width="15%">
							内容
						</th>
						<td colspan="2">
							<textarea class="ckeditor" id="ckeditor1" name="ckeditor1"></textarea>
							<s:hidden id="content" name="noticeForm.content"></s:hidden>
						</td>
					</tr>
        <tr>
          <td class="bottom" align="center" colspan="2">
			<input type="button" onclick="javascript:history.back();" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="返回"/>
          </td>
        </tr>
      </table></td>
    </tr>
  </table>
<script type="text/javascript">
$(document).ready(function() {	
	setTimeout(loadEditorData, 10);
	//alert(content);
	//$("#noticeContent").val(content);
});

function loadEditorData() {
	var content = $("#content").val();
	
	if (content != "")
	{
		var oEditor = CKEDITOR.instances.ckeditor1; 
		oEditor.setData(content);
	}
}
</script>
</body>
</html>