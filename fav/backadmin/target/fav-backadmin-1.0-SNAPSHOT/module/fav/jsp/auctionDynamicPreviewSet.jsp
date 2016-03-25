<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/commonNew.jsp"%>
<script type="text/javascript" src="js/auctionDynamicPreviewMgr.js"></script>
<script type="text/javascript">
	function fromValidate(){
		if ($("#auctionDynamicId").val() == '-1'){
			alert("请选择动态");
			return false;
		}
		if ($("#collectionId").val() == '-1'){
			alert("请选择藏品");
			return false;
		}
		var reg = new RegExp("^[1-9][0-9]{0,2}[0]?$");  
	    if(!reg.test($("#displayOrder").val())){  
	    	alert("顺序必须是1-1000之间的正整数");
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
			url: "auctionDynamicPreview_save.do",
			data: $("#setForm").serialize(),
			success: function(data){
				alert(data.message);
				window.location.href="auctionDynamicPreview_init.do";
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
				url: "auctionDynamicPreview_update.do",
				data: $("#setForm").serialize(),
				success: function(data){
					alert(data.message);
					window.location.href="auctionDynamicPreview_init.do";
				},
				error: function(XMLHttpRequest, textStatus, errorThrown){
					alert("修改失败");
				},
				dataType: "json"
			});
	}
	
 	$(function() {
 		$("#auctionDynamicId").combobox();
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
<s:form id="setForm" name="auctionDynamicPreviewForm" action="%{actionName}">
	<s:hidden name="auctionDynamicPreviewForm.id"></s:hidden>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr>
					    <th width="20%" style="text-align:right;">拍卖行动态：</th>
					    <td width="40%">
					    	<s:select id="auctionDynamicId" name="auctionDynamicPreviewForm.auctionDynamicId" list="dynamicMap" listKey="key" listValue="value" headerKey="-1" headerValue="所有" cssStyle="width:150px"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">藏品：</th>
					    <td width="40%">
					    	<s:select id="collectionId" name="auctionDynamicPreviewForm.collectionId" list="collectionMap" listKey="key" listValue="value" headerKey="-1" headerValue="所有" cssStyle="width:150px"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">显示顺序：</th>
					    <td width="40%">
					        <input type="text" id="displayOrder" name="auctionDynamicPreviewForm.displayOrder" value="<s:property value="auctionDynamicPreviewForm.displayOrder"/>"/>
					    </td>
					</tr>
					<tr>
						<td class="bottom" align="center" colspan="4">
							<div align="center">
								<s:if test="%{actionName == 'auctionDynamicPreview_save'}">
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
</body>
</html>