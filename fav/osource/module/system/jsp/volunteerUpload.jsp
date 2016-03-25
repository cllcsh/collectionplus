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
<s:if test="fileType == 'pic'">
<form onsubmit="up_load();" id="corpForm" name="corpForm" action="<%=request.getContextPath()%>/upload.do?maximumSize=512000&allowedTypes=image/bmp,image/png,image/x-png,image/gif,image/jpeg,image/pjpeg&path=volunteerpic" method="post" enctype="multipart/form-data" target="aaa">
</s:if>
<s:else>
<form onsubmit="up_load();" id="corpForm" name="corpForm" action="<%=request.getContextPath()%>/upload.do?maximumSize=512000&allowedTypes=application/octet-stream,application/vnd.ms-excel,application/msword,text/plain&path=volunteerreq" method="post" enctype="multipart/form-data" target="aaa">
</s:else>
<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<s:hidden id="fileType" name="fileType"></s:hidden>
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
	if(filename == null ||filename == "" || filename == undefined)
	{
		alert("请上传文件");
		return false;
	}
	else
	{
		this.submit();
		//document.getElementById("corpForm").submit();
	}
}

function success(fileName){
	alert("上传成功");
	$("#upload").dialog('close');
	var type = document.getElementById("fileType").value;
	callbackpic(fileName, type);
}
</script>
</body>
</html>