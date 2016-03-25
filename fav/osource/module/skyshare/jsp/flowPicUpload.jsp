<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%--@include file="/jsp/common.jsp"--%>
<script type="text/javascript" src="js/flowMgr.js"></script>
<script type="text/javascript">
   var tourl = 'flow_save.do?showType=tree';
	<s:if test="%{actionName == 'flow_updateOrderFlow'}">
       tourl = 'flow_updateOrderFlow.do?showType=tree';
    </s:if>
    
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
						//window.location='flow_init.do?showType=tree';
						closeDialog();

						var arr = json.text.split("|");//text中包含订单Id和流程Id信息：orderId + "|" + id
						showFlowInfo(arr[0], arr[1], arr[2]);
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
			
			//var chkObjs = document.getElementById("title");
			//if(chkObjs)
			//{
			//	document.getElementById("title").value = filename.substring(0,filename.lastIndexOf(".")); 
			//}
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
        	//attachfileManager.delbyId(attachId, function(){
        	
           // });
        }
	
</script>
</head>

<body>
<%--@include file="/jsp/include/navigation.jsp"--%>
<s:form id="setForm" name="setForm" action="%{actionName}" enctype="multipart/form-data">
	<s:hidden id="orderId" name="orderId"/>
	<s:hidden id="flowId" name="id"/>
	<s:hidden id="flowId" name="orderFlowForm.id"/>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td>
			 <table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
			 	 <tr>
						<th width="15%">
							名称： 
						</th>
						<td colspan="2" width="70%">
							<s:textfield size="20" id="orderFlowName" cssClass="td03"
										name="orderFlowForm.name" maxlength="250"
										onblur="clearBlank(this);" />
						</td>
						<td>
							<div id="orderFlowNameTip"></div>
						</td>
				   </tr>
				   <tr>
						<th width="15%">
							描述： 
						</th>
						<td colspan="2">
						    <s:textarea id="orderFlowDesc" name="orderFlowForm.description" rows="5" cols="50" ></s:textarea>
						</td>
						<td>
							<div id="orderFlowDescTip"></div>
						</td>
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
			   </table>
			</td>
		</tr>
		<tr>
			<td>
				<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
					<!-- <tr>
          				<th align="right">图片1</th>
				          <td><input type="file" id="upload1" name="pic" style="width:300px" /></td>
					    	<td align="left"><input type="button" id="btn1" class="button2" onmouseout="this.className = 'button2'" onmouseover="this.className = 'button2Over'"  value="重置"  onclick="clear_upfile('upload1');"/></td>
						  </td>
				          <td></td>
        			</tr>
					<tr>
          				<th align="right">图片2</th>
				          <td><input type="file" id="upload2" name="pic" value="" style="width:300px" ContentEditable="false" /></td>
					    	<td align="left"><input type="button" id="btn2" class="button2" onmouseout="this.className = 'button2'" onmouseover="this.className = 'button2Over'"  value="重置"  onclick="clear_upfile('upload2');"/></td>
						  </td>
				          <td></td>
        			</tr>
					<tr>
          				<th align="right">图片3</th>
				          <td><input type="file" id="upload3" name="pic" value="" style="width:300px" ContentEditable="false" /></td>
					    	<td align="left"><input type="button" id="btn3" class="button2" onmouseout="this.className = 'button2'" onmouseover="this.className = 'button2Over'"  value="重置"  onclick="clear_upfile('upload3');"/></td>
						  </td>
				          <td></td>
        			</tr>					
					<tr>
          				<th align="right">图片4</th>
				          <td><input type="file" id="upload4" name="pic" value="" style="width:300px" ContentEditable="false" /></td>
					    	<td align="left"><input type="button" id="btn4" class="button2" onmouseout="this.className = 'button2'" onmouseover="this.className = 'button2Over'"  value="重置"  onclick="clear_upfile('upload4');"/></td>
						  </td>
				          <td></td>
        			</tr>
					<tr>
          				<th align="right">图片5</th>
				          <td><input type="file" id="upload5" name="pic" value="" style="width:300px" ContentEditable="false" /></td>
					    	<td align="left"><input type="button" id="btn5" class="button2" onmouseout="this.className = 'button2'" onmouseover="this.className = 'button2Over'"  value="重置"  onclick="clear_upfile('upload5');"/></td>
						  </td>
				          <td></td>
        			</tr>--> 						
					<tr>
						<td class="bottom" align="center" colspan="4">
							<div align="center">
									<input type="submit" id="btn_save" class="button" value="保存" />
									<input type="button" onclick="javascript:closeDialog();" class="button" value="返回"/>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
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