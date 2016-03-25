<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/formTableMgr.js"></script>
</head>

<body>
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_add" width="100%"  border="0" cellpadding="1" cellspacing="1">
		<tr> 
		 <th width=50% align="right">模块名称</th>
		 <td width=50%><s:property value="formTableForm.moduleName"/></td>
		</tr>
        <tr> 
		 <th width=50% align="right">作者</th>
		 <td width=50%><s:property value="formTableForm.author"/></td>
		</tr>
		<tr> 
		 <th width=50% align="right">实体JAVABean名称</th>
		 <td width=50%><s:property value="formTableForm.entityBean"/></td>
		</tr>
		<tr> 
		 <th width=50% align="right">实体名称</th>
		 <td width=50%><s:property value="formTableForm.entity"/></td>
		</tr>
		<tr> 
		 <th width=50% >存储表名</th>
		 <td width=50%><s:property value="formTableForm.tableName"/></td>
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