<%@ page language="java" pageEncoding="UTF-8"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<%@include file="/jsp/meta.jsp" %>
</head>
<body style="overflow:hidden"><s:fielderror/>

<s:if test="uploadContentType == 'pic'">
	<form onsubmit="up_load();" id="uploadForm" name="uploadForm" action="<%=request.getContextPath()%>/upload.do?maximumSize=10240000&allowedTypes=image/bmp,image/png,image/x-png,image/gif,image/jpg,image/jpeg,image/pjpeg&path=<s:property value='path'/>" method="post" enctype="multipart/form-data" target="uploadFrame">
</s:if>
<s:else>
	<form onsubmit="up_load();" id="uploadForm" name="uploadForm" action="<%=request.getContextPath()%>/upload.do?maximumSize=10240000&allowedTypes=application/octet-stream,application/vnd.ms-excel,application/msword,text/plain&path=<s:property value='path'/>" method="post" enctype="multipart/form-data" target="uploadFrame">
</s:else>
<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<s:hidden id="fileType" name="uploadContentType"></s:hidden>
	<table class="tb_add" width="100%"  border="0" cellpadding="1" cellspacing="1">
        <tr>
          <td class="td_title" colspan="3" align="center">图片上传</td>
        </tr>
		<tr>
		</tr>
         <tr>
          <th class="tb_lable" width="22%" align="right">选择图片<font class="redStar">*</font></th>
          <td id="fileTd"><input id="filename" type="file" name="upload"/> </td>
          <td width="45%"><div id=""></div></td>
        </tr>
        <tr>
          <td colspan="3" width="100%" style="color:red;">请上传图片格式文件，大小限制10M</td>
        </tr>
        <tr>
          <td class="bottom" align="center" colspan="3"><div align="center">
            <input value="上传" type="submit"  onmouseout="this.className = 'button'" class="button"		onmouseover="this.className = 'buttonOver'" />
            <input value="清空" type="button" class="button"	 onclick="javascript:clearFile();"/>
          </div></td>
        </tr>
      </table></td>
    </tr>
  </table>
</form>

<iframe name="uploadFrame" style="display:none"></iframe>
<script type="text/javascript">


function up_load()
{
	var filename = document.getElementById("filename").value;
	if(filename == null ||filename == "" || filename == undefined)
	{
		alert("请上传图片");
		return false;
	}
	else
	{
		return true;
		//this.submit();
	}
}

function success(fileName){
	alert("上传成功，请保存");
	$("#upload").dialog('close');
	var type = document.getElementById("fileType").value;
	callbackpic(fileName, type);
}

function clearFile(){
	document.uploadForm.reset();//清除 
}

</script>
</body>
</html>