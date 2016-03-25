<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.Date"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<div align="right" style="padding-right:5px;">
	（<%
	out.println((new Date()).getYear() + 1900);
	%>）
	<dict:select codeType="assess_code" id="assessCode" cssStyle="width:80px" emptyOption="true"/>
	字&nbsp;第_____号
</div>