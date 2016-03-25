<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/applyRecordMgr.js"></script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_add" width="100%"  border="0" cellpadding="1" cellspacing="1">
		<tr>
		    <th width="20%" style="text-align:right;">申请拍卖的藏品id<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="applyRecordForm.collectionId"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">申请人Id<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="applyRecordForm.applierId"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">申请状态，等审核：collection_status_wait_apply，审核中：collection_status_applying，审核通过：collection_status_pass_apply，审核不通过：collection_status_fail_apply，估价：collection_status_appraisal，拍卖中：collection_status_auction，已卖：collection_status_solded，流拍：collection_status_invalid<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="applyRecordForm.status"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">申请时间<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="applyRecordForm.applyTime"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">申请类型<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="applyRecordForm.applyType"/></td>
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