<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/brandMgr.js"></script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_add" width="100%"  border="0" cellpadding="1" cellspacing="1">
		<tr>
			<td>
				<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr>
						<th  width="15%">
							名称： 
						</th>
						<td colspan="2">
							<s:textarea id="name" name="#brandInfo.name" cols="50" rows="5"></s:textarea>
						</td>
						<td>
							<div id="nameTip"></div>
						</td>
					</tr>
					<tr>
						<th  width="15%">
							拼音： 
						</th>
						<td colspan="2">
							<s:textarea id="type" name="#brandInfo.pinyin" cols="50" rows="5"></s:textarea>
						</td>
						<td>
							<div id="pinyinTip"></div>
						</td>
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