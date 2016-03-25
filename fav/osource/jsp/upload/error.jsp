<%@ page language="java" pageEncoding="UTF-8"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
    <body onload="error(errorCode)">
        
    </body>
<script type="text/javascript">
var errorCode = '<s:property value="#errorCode"/>';
var errorInfo = '<s:property value="#errorInfo"/>';
function error(errorCode){
    if(errorCode=="1")
	//    alert(errorInfo);
		alert("文件格式不合法，应为bmp,png,x-png,gif,jpg,jpeg,pjpeg格式！");
    else if(errorCode=="2")
    	//alert(errorInfo);
    	alert("上传的文件大小不能超过500k!");
    else
    	alert(errorCode);
    var tt = parent.document.getElementById("btn_save");
    if(tt!=null){
	    tt.disabled = false;
    }
	//parent.success(fileName);
}
</script>
</html>
