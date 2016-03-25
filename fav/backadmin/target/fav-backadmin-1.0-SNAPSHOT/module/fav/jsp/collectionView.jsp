<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/collectionMgr.js"></script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_add" width="100%"  border="0" cellpadding="1" cellspacing="1">
		<tr>
		    <th width="20%" style="text-align:right;">标题<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="collectionForm.title"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">藏品类别<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="collectionForm.categoryName"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">所属时期<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="collectionForm.collectionPeriodName"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">藏品简介<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="collectionForm.introduction"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">是否愿意送拍<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="collectionForm.isSendRacketDesc"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">是否愿意出售<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="collectionForm.isSoldDesc"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">是否鉴定<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="collectionForm.isIdentifyDesc"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">标签<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="collectionForm.labelName"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">图标<font style="color: red;">*</font>：</th>
		    <td width="40%"><img id="imgPathImg" src="${qnImageUrl}<s:property value="collectionForm.iconImg"/>?imageView2/1/w/32/h/32" style="cursor: pointer;" width="32" height="32" /></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">热度<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="collectionForm.heat"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">鉴定结果<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="collectionForm.identifyResult"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">状态<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="collectionForm.statusName"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">发布人<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="collectionForm.insertUserName"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">拍卖行<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="collectionForm.auctionName"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">估价<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="collectionForm.appraisalDesc"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">估价时间<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="collectionForm.appraisalTime"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">估计人<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="collectionForm.appraisalUserName"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">成交价<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="collectionForm.transactionPriceDesc"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">成交时间<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="collectionForm.transactionPriceTime"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">成交人<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="collectionForm.transactionUserName"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">成交说明<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="collectionForm.transactionDesc"/></td>
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