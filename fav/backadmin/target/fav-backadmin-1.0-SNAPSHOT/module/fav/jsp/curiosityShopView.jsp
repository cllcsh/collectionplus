<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/curiosityShopMgr.js"></script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_add" width="100%"  border="0" cellpadding="1" cellspacing="1">
		<tr>
		    <th width="20%" style="text-align:right;">名称<font style="color: red;">*</font>：</th>
	   		<td width="40%"><s:property value="curiosityShopForm.name"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">地址<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="curiosityShopForm.address"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">电话<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="curiosityShopForm.phone"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">简介<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="curiosityShopForm.introduction"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">纬度<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="curiosityShopForm.lat"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">经度<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="curiosityShopForm.longitude"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">省份<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="curiosityShopForm.province"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">城市<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="curiosityShopForm.city"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">区县<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="curiosityShopForm.county"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">图标<font style="color: red;">*</font>：</th>
		    <td width="40%"><img src="<s:property value='curiosityShopForm.icon'/>?imageView2/1/w/32/h/32" style="cursor: pointer;" width="32" height="32" /> </td>
		</tr>
		<s:iterator id="img" value="imgs" status="sta">
			<tr>
	    		<th width="20%" style="text-align:right;">藏品图片<font style="color: red;">*</font>：</th>
				<td width="40%"><img src="${qnImageUrl}<s:property value="#img"/>" style="cursor: pointer;max-width:500px;max-height:400px;" /></td>
	       	</tr>
        </s:iterator>
        <tr>
          <td class="bottom" align="center" colspan="2">
			<input type="button" onclick="javascript:history.back();" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="返回"/>
          </td>
        </tr>
      </table></td>
    </tr>
  </table>

</body>
</html>