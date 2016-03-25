<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<s:hidden id="totalNum" value="%{pageList.pages.total}"></s:hidden>
<s:if test="pageList.pages.total == -1">
	<br/><br/><p align="center">数据库异常，请稍后再试!</p><br/><br/><br/>
</s:if>
<s:elseif test="pageList.pages.total == 0">
	<br/><br/><br/><p align="center">没有检索到您要的信息</p><br/><br/>
</s:elseif>
