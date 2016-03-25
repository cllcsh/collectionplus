<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/carMgr.js"></script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_add" width="100%"  border="0" cellpadding="1" cellspacing="1">
		<tr>
			<th width="20%" style="text-align:right;">标题<font style="color: red;">*</font>：</th>
			<td width="40%"><s:property value="carForm.title"/></td>
		</tr>
		<tr>
			<th width="20%" style="text-align:right;">品牌<font style="color: red;">*</font>：</th>
			<td width="40%"><s:property value="carForm.brandInfo.name"/></td>
		</tr>
		<tr>
			<th width="20%" style="text-align:right;">版本<font style="color: red;">*</font>：</th>
			<td width="40%"><s:property value="carForm.carVersionInfo.name"/></td>
		</tr>
		<tr>
			<th width="20%" style="text-align:right;">系列<font style="color: red;">*</font>：</th>
			<td width="40%"><s:property value="carForm.seriesInfo.name"/></td>
		</tr>
		<tr>
			<th width="20%" style="text-align:right;">车型<font style="color: red;">*</font>：</th>
			<td width="40%"><s:property value="carForm.modelsInfo.name"/></td>
		</tr>
		<tr>
			<th width="20%" style="text-align:right;">年款<font style="color: red;">*</font>：</th>
			<td width="40%"><s:property value="carForm.modelyearInfo.name"/></td>
		</tr>
		<tr>
			<th width="20%" style="text-align:right;">排量<font style="color: red;">*</font>：</th>
			<td width="40%"><s:property value="carForm.enginesName"/></td>
		</tr>
		<tr>
			<th width="20%" style="text-align:right;">外饰颜色<font style="color: red;">*</font>：</th>
			<td width="40%"><s:property value="carForm.outercolorName"/></td>
		</tr>
		<tr>
			<th width="20%" style="text-align:right;">内饰颜色<font style="color: red;">*</font>：</th>
			<td width="40%"><s:property value="carForm.innercolorName"/></td>
		</tr>
		<tr>
			<th width="20%" style="text-align:right;">车源时间<font style="color: red;">*</font>：</th>
			<td width="40%"><s:date name="carForm.insertDate" format="yy-MM-dd HH:mm" /></td>
		</tr>
		<tr>
			<th width="20%" style="text-align:right;">车源状态<font style="color: red;">*</font>：</th>
			<td width="40%">
				<s:if test="%{carForm.carStatus == 0}">审核中</s:if>
	            <s:if test="%{carForm.carStatus == 1}">在售中</s:if>
	            <s:if test="%{carForm.carStatus == 2}">已下架</s:if>
	            <s:if test="%{carForm.carStatus == 3}">已售完</s:if>
			</td>
		</tr>
		<tr>
			<th width="20%" style="text-align:right;">审批原因<font style="color: red;">*</font>：</th>
			<td width="40%"><s:property value="carForm.approveRemark"/></td>
		</tr>	
		<tr>
			<th width="20%" style="text-align:right;">更新时间<font style="color: red;">*</font>：</th>
			<td width="40%"><s:date name="carForm.updateDate" format="yy-MM-dd HH:mm" /></td>
		</tr>
		<tr>
			<th width="20%" style="text-align:right;">所属用户名<font style="color: red;">*</font>：</th>
			<td width="40%"><s:property value="carForm.userInfo.loginName"/></td>
		</tr>
		<tr>
			<th width="20%" style="text-align:right;">燃油<font style="color: red;">*</font>：</th>
			<td width="40%"><s:property value="carForm.fuel"/></td>
		</tr>
		<tr>
			<th width="20%" style="text-align:right;">所在省份<font style="color: red;">*</font>：</th>
			<td width="40%"><s:property value="carForm.province"/></td>
		</tr>
		<tr>
			<th width="20%" style="text-align:right;">首款比例<font style="color: red;">*</font>：</th>
			<td width="40%"><s:property value="carForm.depositRatio"/>0%</td>
		</tr>
		<tr>
			<th width="20%" style="text-align:right;">货源<font style="color: red;">*</font>：</th>
			<td width="40%"><s:property value="carForm.source"/></td>
		</tr>
		<tr>
			<th width="20%" style="text-align:right;">车辆手续<font style="color: red;">*</font>：</th>
			<td width="40%">
				<s:if test="%{carForm.procedures&1 == 1}">用户发票&nbsp;</s:if>
				<s:if test="%{carForm.procedures&1 == 2}">随车检验单&nbsp;</s:if>
				<s:if test="%{carForm.procedures&1 == 4}">车辆一致性证书&nbsp;</s:if>
				<s:if test="%{carForm.procedures&1 == 8}">购置税电子证书&nbsp;</s:if>
				<s:if test="%{carForm.procedures&1 == 16}">基本信息表&nbsp;</s:if>
				<s:if test="%{carForm.procedures&1 == 32}">车辆销售正规发票&nbsp;</s:if>
				<s:if test="%{carForm.procedures&1 == 64}">车辆销售正规发票</s:if>
			</td>
		</tr>
		<tr>
			<th width="20%" style="text-align:right;">出车数量<font style="color: red;">*</font>：</th>
			<td width="40%"><s:property value="carForm.num"/></td>
		</tr>
		<tr>
			<th width="20%" style="text-align:right;">价格<font style="color: red;">*</font>：</th>
			<td width="40%"><s:property value="carForm.price"/></td>
		</tr>
		<tr>
			<th width="20%" style="text-align:right;">剩余车辆数<font style="color: red;">*</font>：</th>
			<td width="40%"><s:property value="carForm.surplusNum"/></td>
		</tr>
		<tr>
			<th width="20%" style="text-align:right;">特殊说明<font style="color: red;">*</font>：</th>
			<td width="40%"><s:property value="carForm.remark"/></td>
		</tr>
		<tr>
			<th width="20%" style="text-align:right;">下架原因<font style="color: red;">*</font>：</th>
			<td width="40%"><s:property value="carForm.reason"/></td>
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