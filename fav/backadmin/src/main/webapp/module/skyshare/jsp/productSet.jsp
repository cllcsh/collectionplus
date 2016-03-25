<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/productMgr.js"></script>
<script type="text/javascript">
   var tourl = 'product_save.do';
	<s:if test="%{actionName == 'product_save'}">
	tourl = 'product_save.do';
		$(document).ready(function(){
			//$(document).ict({
			//	saveForm:'setForm',
			 //   saveUrl:'product_save.do',
			 //   saveUrlTo:'product_init.do',
			//    saveBtn:'btn_save'
			//});
			
		});
	</s:if>
	<s:else>
	tourl = 'product_update.do';
		//$(document).ready(function(){
		//	$(document).ict({
		//		saveForm:'setForm',
		//	    saveUrl:'product_update.do',
		//	    saveUrlTo:'product_init.do',
		///	    saveBtn:'btn_save'
		//	});
		//});
	</s:else>
	
	$(document).ready(function() 
	{
		$('#setForm').submit(function(ss) { 
	     var options = {
		  url:tourl, //提交给哪个执行 
		  type:'POST', 
		  dataType: "json",
		  success: function(json){
		  alert(json.message);
			if(json.codeid == 0){
			window.location='product_init.do';
			}
			} //显示操作提示 
		 }; 
		$('#setForm').ajaxSubmit(options); 
		return false; //为了不刷新页面,返回false，反正都已经在后台执行完了，没事！ 
			
	    });  
	});

function callbackpic(filename,attachmentId,orgName)
{
	$("#uploadFileDialog").dialog('close');
	var htmlvar = document.getElementById("AllFiles").innerHTML;
	//for (var filei = 0; filei < list.length; filei++) 
	//{ 
		//att = list[filei]; 
	//	filename = att.attachName; 
		
		htmlvar+="<div id='big_file_" + attachmentId +"' >";

		htmlvar+="<a href='#' onclick='open_file(\""
				+ filename + "\")'>"
				+ orgName + "</a> &nbsp;&nbsp;";

		htmlvar+="<input type='button' onclick=\"del_big_file("
						+ attachmentId
						+ ")\" value='删除'>";
		htmlvar+="<input name='attachmentId' type='hidden'  value=\""
						+ attachmentId
						+ "\"></div>";
	//}
	document.getElementById("AllFiles").innerHTML = htmlvar;
	
}
function del_big_file(attachId){
$.ajax({
type: "POST",
url: _contextPath + "/module/system/attachment_deletes.do?mode=ajaxJson&ids=" + attachId,
success: function(json){			
	alert(json.message);
	if(json.codeid == 0){
		var div = document.getElementById("big_file_"+attachId);
        div.parentNode.removeChild(div);
      	
	}	

},
  dataType: "json"
});
}	
</script>
</head>

<body>
<s:form id="setForm" name="productForm" action="%{actionName}" enctype="multipart/form-data">
	<s:hidden name="productForm.id"></s:hidden>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr>
								<th>
									类型：
								</th>
								<td colspan="2">
									<s:select list="productTypeList" listKey="id" listValue="typeName"
										id="productTypeId" value="productForm.typeId"
										name="productForm.typeId">
									</s:select>
								</td>
								<td><div id="productTypeIdTip"></div></td>
					</tr>

					<tr>
								<th width="15%">
									Product Name：
								</th>
								<td colspan="2">
									<s:textfield size="20" id="name" cssClass="td03"
										name="productForm.productName" maxlength="60"
										onblur="clearBlank(this);" />
									<font class="redStar">*</font>
									
								</td>
								<td width="35%"><div id="nameTip"></div></td>
					</tr>
					<tr>
						<th>
							上架时间：
						</th>
						<td colspan="2">
							<input name="productForm.upDat" readonly="readonly" value='<s:date format="yyyy-MM-dd HH:mm:ss" name="productForm.upDat"/>'  size="20" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate"></input>
						</td>
                         <td width="35%"><div id="nameTip"></div></td>
					</tr>
					<tr>
                       <th>
							是否新品：
						</th>
						<td colspan="2">
							<dict:select codeType="product_is_new" id="isNew"  name="productForm.isNew" cssStyle="width:135px"/>
									<font class="redStar">*</font>
									<div id="isNewTip"></div>
						</td>
						<td width="35%"><div id="nameTip"></div></td>
                    </tr>
					<tr>
								<th width="15%">
									price：
								</th>
								<td colspan="2">
									<s:textfield size="20" id="salePrice" cssClass="td03"
										name="productForm.salePrice" maxlength="60"
										onblur="clearBlank(this);" />
															</td>
								<td width="35%"><div id="salePriceTip"></div></td>
					</tr>
					<tr>
								<th width="15%">
									weight：
								</th>
								<td colspan="2">
									<s:textfield size="20" id="weight" cssClass="td03"
										name="productForm.weight" maxlength="60"
										onblur="clearBlank(this);" />
									
								</td>
								<td width="35%"><div id="weightTip"></div></td>
					</tr>
					<tr>
						<th>
							Product Specifications：
						</th>
						<td colspan="2">
							<s:textarea cols="30" rows="5" id="description" cssClass="td03"
										name="productForm.productDesc" maxlength="100"
										onblur="clearBlank(this);" />
									
						</td>
						<td><div id="productDescTip"></div></td>
					</tr>
					<tr>
						<th>
							CAS NO.：
						</th>
						<td colspan="2">
							<s:textarea cols="30" rows="5" id="description" cssClass="td03"
										name="productForm.casNo" maxlength="100"
										onblur="clearBlank(this);" />
									
						</td>
						<td><div id="casNoTip"></div></td>
					</tr>
					<tr>
						<th>
							Product Features：
						</th>
						<td colspan="2">
							<s:textarea cols="30" rows="5" id="description" cssClass="td03"
										name="productForm.productFeature" maxlength="100"
										onblur="clearBlank(this);" />
									
						</td>
						<td><div id="productFeatureTip"></div></td>
					</tr>
					<tr>
						<th>
							For crops：
						</th>
						<td colspan="2">
							<s:textarea cols="30" rows="5" id="description" cssClass="td03"
										name="productForm.productUse" maxlength="100"
										onblur="clearBlank(this);" />
									
						</td>
						<td><div id="productUseTip"></div></td>
					</tr>
					<tr>
						<th>
							Control objects：
						</th>
						<td colspan="2">
							<s:textarea cols="30" rows="5" id="useNote" cssClass="td03"
										name="productForm.useNote" maxlength="100"
										onblur="clearBlank(this);" />
									
						</td>
						<td><div id="useNoteTip"></div></td>
					</tr>
					<tr>
						<th>
							存储运输 ：
						</th>
						<td colspan="2">
							<s:textarea cols="30" rows="5" id="transportStorage" cssClass="td03"
										name="productForm.transportStorage" maxlength="100"
										onblur="clearBlank(this);" />
									
						</td>
						<td><div id="transportStorageTip"></div></td>
					</tr>
					 <tr>
	        	 <th align="right">附件上传：</th>
				 <td colspan="3">
					<div id="AllFiles">
						<s:iterator id="attachmentBean" value="attachmentList" status="st">
							<div id='big_file_<s:property value="#attachmentBean.id"/>'><s:property value="#attachmentBean.name" />&nbsp;<input type="button" onclick="del_big_file(<s:property value="#attachmentBean.id"/>)" value="删除"></div>
						</s:iterator> 
					</div>
					<input type="button" id="btn_upfile" onclick="up_file();"
										class="button2" onmouseout="this.className = 'button2'"
										onmouseover="this.className = 'button2Over'" value="附件上传" />
				</td>
			</tr>
				<!-- 	<tr>
          				<th align="right">产品图片1</th>
				          <td><input type="file" id="upload1" name="productForm.pic" style="width:300px" /></td>
					    	<td align="left"><input type="button" id="btn1" class="button2" onmouseout="this.className = 'button2'" onmouseover="this.className = 'button2Over'"  value="重置"  onclick="clear_upfile('upload1');"/></td>
						  </td>
				          <td></td>
        			</tr>
					<tr>
          				<th align="right">产品图片2</th>
				          <td><input type="file" id="upload2" name="productForm.pic" value="" style="width:300px" ContentEditable="false" /></td>
					    	<td align="left"><input type="button" id="btn1" class="button2" onmouseout="this.className = 'button2'" onmouseover="this.className = 'button2Over'"  value="重置"  onclick="clear_upfile('upload2');"/></td>
						  </td>
				          <td></td>
        			</tr>
					<tr>
          				<th align="right">产品图片3</th>
				          <td><input type="file" id="upload3" name="productForm.pic" value="" style="width:300px" ContentEditable="false" /></td>
					    	<td align="left"><input type="button" id="btn1" class="button2" onmouseout="this.className = 'button2'" onmouseover="this.className = 'button2Over'"  value="重置"  onclick="clear_upfile('upload3');"/></td>
						  </td>
				          <td></td>
        			</tr>					
					<tr>
          				<th align="right">产品图片4</th>
				          <td><input type="file" id="upload4" name="productForm.pic" value="" style="width:300px" ContentEditable="false" /></td>
					    	<td align="left"><input type="button" id="btn1" class="button2" onmouseout="this.className = 'button2'" onmouseover="this.className = 'button2Over'"  value="重置"  onclick="clear_upfile('upload4');"/></td>
						  </td>
				          <td></td>
        			</tr>
					<tr>
          				<th align="right">产品图片5</th>
				          <td><input type="file" id="upload5" name="productForm.pic" value="" style="width:300px" ContentEditable="false" /></td>
					    	<td align="left"><input type="button" id="btn1" class="button2" onmouseout="this.className = 'button2'" onmouseover="this.className = 'button2Over'"  value="重置"  onclick="clear_upfile('upload5');"/></td>
						  </td>
				          <td></td>
        			</tr>	-->					
					<tr>
						<td class="bottom" align="center" colspan="4">
							<div align="center">
								<s:if test="%{actionName == 'product_save'}">
									<input type="submit" id="btn_save" class="button" value="增加" />
								</s:if>
								<s:else>
									<input type="submit" id="btn_save" class="button" value="保存" />
								</s:else>
								<input type="button" onclick="javascript:history.back();" class="button" value="返回"/>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
<div id="uploadDialog" title="文件上传"></div>
<div id="uploadFileDialog" title="文件上传"></div>
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