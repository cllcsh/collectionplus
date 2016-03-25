<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/famousHomeMgr.js"></script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_add" width="100%"  border="0" cellpadding="1" cellspacing="1">
		<tr>
		    <th width="20%" style="text-align:right;">名字<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="famousHomeForm.name"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">图标<font style="color: red;">*</font>：</th>
		    <td width="40%"><img id="imgPathImg" src="${qnImageUrl}<s:property value="famousHomeForm.icon"/>?imageView2/1/w/32/h/32" style="cursor: pointer;" width="32" height="32" /></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">个人简介<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="famousHomeForm.introduction"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">是否入驻<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="famousHomeForm.statusDesc"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">专项<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="famousHomeForm.specialidsDesc"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">名人类型<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="famousHomeForm.typeDesc"/></td>
		</tr>

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