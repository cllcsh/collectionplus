<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
    <head>
        <title>�ϴ��ɹ�</title>
    </head>
    <body onload="test(fileName,fileNames,attachmentId,orgName)">
        �ϴ��ɹ�!<br>
		<!--  �ļ�����:<s:property value=" + title"/><br>
		�ļ�Ϊ��<img src="<s:property value="'EagleEye/upload/' + uploadFileName"/>"/><br>-->
    </body>
<script type="text/javascript">
	var fileName = '<s:property value="finalPath"/>';
	var attachmentId =  '<s:property value="attachmentId"/>';
	var orgName =  '<s:property value="name"/>'
	var fileNames = new Array();
	<s:iterator id="path" value="finalPaths">
		fileNames.push('<s:property value="#path"/>');
	</s:iterator>
	function test(fileName,fileNames,attachmentId,orgName){
		if(fileNames.length==0)
		{
			parent.success(fileName,attachmentId,orgName);
		}
		else
		{
			parent.success(fileNames,attachmentId,orgName);
		}
	}
</script>
</html>
