<%@ page language="java" pageEncoding="UTF-8"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<%@include file="/jsp/meta.jsp" %>
</head>
<body style="overflow:hidden"><s:fielderror/>
<!-- application/vnd.ms-excel 
image/bmp,image/png,image/x-png,image/gif,image/jpeg,image/pjpeg-->
<form onsubmit="up_load();" id="corpForm" name="corpForm" action="<%=request.getContextPath()%>/upload.do?allowedTypes=image/bmp,image/png,image/x-png,image/gif,image/jpeg,image/pjpeg,application/pdf,application/octet-stream,application/vnd.ms-excel,application/msword,text/plain&path=file"  method="post" enctype="multipart/form-data" target="aaa">
<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_add" width="100%"  border="0" cellpadding="1" cellspacing="1">
        <tr>
          <td class="td_title" colspan="3" align="center">文件上传</td>
        </tr>
<tr>
</tr>
         <tr>
          <th class="tb_lable" width="22%" align="right">选择文件<font class="redStar">*</font></th>
          <td><input id="filename" type="file" name="upload"/> </td>
          
        </tr>
        <tr>
          <td class="bottom" align="center" colspan="3"><div align="center">
          
            <input value="上传" type="submit"  onmouseout="this.className = 'button'"
								class="button"		onmouseover="this.className = 'buttonOver'" />

          </div></td>
        </tr>
      </table></td>
    </tr>
  </table>
</form>

<iframe name="aaa" style="display:none"></iframe>
<script type="text/javascript">


function up_load()
{
	var filename = document.getElementById("filename").value;
	if(filename == null ||filename == "")
	{
		alert("请上传文件");
		return false;
	}
	else
	{
		return true;
		//document.getElementById("corpForm").submit();
	}
}

function success(fileName,attachmentId,orgName){
	alert("上传成功");
	//$("#uploadFileDialog").dialog('close');
	callbackpic(fileName,attachmentId,orgName);
}
</script>
</body>
</html>