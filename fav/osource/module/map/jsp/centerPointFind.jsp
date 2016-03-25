<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<%@include file="/jsp/common.jsp"%>
<head>
<title>中心点坐标</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE3 {
	font-size: 12px;
	color: #435255;
}
.STYLE4 {font-size: 12px}
.STYLE5 {font-size: 12px; font-weight: bold; }
-->
</style></head>

<body>
<table class="tb_query" style="padding-top: 3px; " width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="top"><table class="tb_query"  width="100%" border="1" cellspacing="0" cellpadding="0" valign="top">
      <tr>
          <td class="td_title" colspan="2" align="center">中心点坐标</td>
  	  </tr>
      <tr valign="center">
        <td width="60">经度：</td>
        <td><s:property value="form.longitude"/></td>
      </tr>
      <tr valign="center">
        <td width="60">纬度：</td>
        <td><s:property value="form.latitude"/></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
