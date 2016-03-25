<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/commonNew.jsp"%>
<script type="text/javascript" src="js/auctionCollectionBidMgr.js"></script>
<script type="text/javascript">
	function fromValidate(){
		if ($("#collectionId").val() == '-1'){
			alert("请选择藏品");
			return false;
		}
		if ($.trim($("#username").val()) == ''){
			alert("用户名不能为空");
			return false;
		}
		var reg = new RegExp("^\\d+(\.\\d{1,2})?$");  
		var reg1 = new RegExp("^0+(\.0{1,2})?$");  
	    if(!reg.test($("#price").val()) || reg1.test($("#price").val())){  
	    	alert("请输入正确的竞价金额，金额不可以为零且最多保留小数点两位");
	    	return false;
	    } 
		
		if ($.trim($("#bidDate").val()) == ''){
			alert("竞价时间不能为空");
			return false;
		}
	    return true;
	}
	
	function previewAdd(){
		if (!fromValidate()){
			return;
		}
		$.ajax({
			type: "POST",
			url: "auctionCollectionBid_save.do",
			data: $("#setForm").serialize(),
			success: function(data){
				alert(data.message);
				window.location.href="auctionCollectionBid_init.do";
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				alert("新增失败");
			},
			dataType: "json"
		});
	}
	
	function previewEdit(){
		 if (!fromValidate()){
			return;
		 }
	     $.ajax({
				type: "POST",
				url: "auctionCollectionBid_update.do",
				data: $("#setForm").serialize(),
				success: function(data){
					alert(data.message);
					window.location.href="auctionCollectionBid_init.do";
				},
				error: function(XMLHttpRequest, textStatus, errorThrown){
					alert("修改失败");
				},
				dataType: "json"
			});
	}
	
 	$(function() {
   		$("#collectionId").combobox();
 	});
</script>
<style>
 .custom-combobox {
      position: relative;
      display: inline-block;
 }
 .custom-combobox-toggle {
      position: absolute;
      top: 2px;
      bottom: 2px;
      margin-left: -10px;
      padding: 0;
      width: 15px
 }
 .custom-combobox-input {
      margin: 2px;
      padding: 2px 4px;
      width: 170px;
      background: white;
      border:1px solid #64B8E4;
 }
  </style>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<s:form id="setForm" name="auctionCollectionBidForm" action="%{actionName}">
	<s:hidden name="auctionCollectionBidForm.id"></s:hidden>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr>
					    <th width="20%" style="text-align:right;">藏品：</th>
					    <td width="40%">
					    	<s:select id="collectionId" name="auctionCollectionBidForm.collectionId" list="collectionMap" listKey="key" listValue="value" headerKey="-1" headerValue="所有" cssStyle="width:150px"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">用户名：</th>
					    <td width="40%">
					        <input type="text" id="username" name="auctionCollectionBidForm.username" value="<s:property value="auctionCollectionBidForm.username"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">性别：</th>
					    <td width="40%">
					    	<s:select id="sexNick" name="auctionCollectionBidForm.sexNick" list="sexMap" listKey="key" listValue="value" cssStyle="width:150px"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">竞价价格：</th>
					    <td width="40%">
					        <input type="text" id="price" name="auctionCollectionBidForm.price" value="<s:property value="auctionCollectionBidForm.price"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">竞价价格单位：</th>
					    <td width="40%">
					   		<s:select id="priceUnit" name="auctionCollectionBidForm.priceUnit" list="unitMap" listKey="key" listValue="value" cssStyle="width:150px"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">竞价时间：</th>
					    <td width="40%">
					   		<input id="bidDate" name="auctionCollectionBidForm.bidDate" value="<s:date name="auctionCollectionBidForm.bidDate" format="yyyy-MM-dd HH:mm:ss" />" type="text" cssStyle="width:150px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" />
					    </td>
					</tr>
					<tr>
						<td class="bottom" align="center" colspan="4">
							<div align="center">
								<s:if test="%{actionName == 'auctionCollectionBid_save'}">
									<input type="button" class="button" onclick="previewAdd();" value="增加" />
								</s:if>
								<s:else>
									<input type="button" class="button" onclick="previewEdit();" value="保存" />
								</s:else>
								<input type="button" onclick="javascript:history.back();" class="button" value="返回"/>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</s:form>

<script type="text/javascript">
$.formValidator.initConfig({
	formid:"setForm",
	onerror:function(msg){
	alert(msg)},
	onsuccess:function(){
		return true;
}});
</script>

</body>
</html>