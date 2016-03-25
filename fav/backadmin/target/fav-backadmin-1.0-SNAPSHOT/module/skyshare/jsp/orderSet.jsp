<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>
<%@taglib prefix="ict" uri="/ict-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<%@include file="/jsp/common.jsp"%>
		<script type="text/javascript" src="js/orderMgr.js"></script>
		<script type="text/javascript">
/**	<s:if test="%{actionName == 'order_save'}">
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'order_save.do',
			    saveUrlTo:'order_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:if>
	<s:else>
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'order_update.do',
			    saveUrlTo:'order_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:else>
	*/
	       function tableAddRow(tableName)	 
             {	 
               var row = tableName.insertRow(tableName.rows.length); 
               row.align="center";
               var i  = row.rowIndex;
               //行对象   var i = tableName.rows.length - 2; 
               //table原有的行数  
                var col;  //列对象 	
                   if(tableName==tableId)	 
                   {	    
                    col = row.insertCell(0);	 
       				col.innerHTML = '<s:textfield size="20" cssClass="td03" name="orderForm.product[' + i +'].productName" maxlength="60"/>';
        			col = row.insertCell(1);	  
                    col.innerHTML =  '<s:textfield size="20" cssClass="td03" name="orderForm.product[' + i +'].salePrice" maxlength="60"/>';	 
                    col = row.insertCell(2);	  
                    col.innerHTML =  '<s:textfield size="20" cssClass="td03" name="orderForm.product[' + i +'].weight" maxlength="60"/>';	  
                    col = row.insertCell(3);	 
                    col.innerHTML =  '<s:textfield size="20" cssClass="td03" name="orderForm.product[' + i +'].productNum" maxlength="60"/>';	
                    col = row.insertCell(4);
                    col.innerHTML = '<input type="button" value="删除" onClick="delRow(this)" />'; 
                    }	  
              }	
          function getRowObj(obj)
          {
             var i = 0;  
              while(obj.tagName.toLowerCase() != "tr")
              {    
              	obj = obj.parentNode;   
               	if(obj.tagName.toLowerCase() == "table")
               	{
               		return null;
                }  
           	   }  
            return obj;
           }
          function delRow(obj)
          {
          	var tr = getRowObj(obj); 
          	if(tr != null)
          	{
          		tr.parentNode.removeChild(tr);      
          	}
          	else
          	{
              throw new Error("the given object is not contained by the table");      
            }  
          } 
          
</script>
	</head>

	<body>
		<%@include file="/jsp/include/navigation.jsp"%>
		<s:form id="setForm" name="orderForm" action="%{actionName}">
			<s:hidden name="orderForm.id"></s:hidden>
			<table class="bg" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td>
						<table class="tb_add" width="100%" border="0" cellpadding="1"
							cellspacing="1">
							
							<tr>
								<th width="15%">
									订单人：
								</th>
								<td colspan="2">
									 <ict:select beanContextId="userSelect" id="userid" name="orderForm.userId" cssStyle="width:140px"></ict:select> 
									<font class="redStar">*</font>

								</td>
								<td></td>
							</tr>
							<tr>
								<th width="15%">
									订单号：
								</th>
								<td colspan="2">
									<s:textfield size="20" id="orderCode" cssClass="td03"
										name="orderForm.orderCode" maxlength="60"
										onblur="clearBlank(this);" />
									<font class="redStar">*</font>

								</td>
								<td width="35%">
									<div id="orderCodeTip"></div>
								</td>
							</tr>
							
							
							<tr>
								<th>
									订单日期：
								</th>
								<td colspan="2">
									<s:textfield name="orderForm.orderDate" readonly="true"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssClass="Wdate"></s:textfield>
								</td>
								<td>
									<div id="orderDateTip"></div>
								</td>
							</tr>

							<tr>
								<th width="15%">
									step5 PRCDSSED：
								</th>
								<td colspan="2">
									<s:textfield size="60" id="step5" cssClass="td03"
										name="orderForm.step5" maxlength="60"
										onblur="clearBlank(this);" />
								</td>
								<td width="35%">
									<div id="step5Tip"></div>
								</td>
							</tr>
							<tr>
								<th width="15%">
									step6 ON BOARD：
								</th>
								<td colspan="2">
									<s:textfield size="60" id="step6" cssClass="td03"
										name="orderForm.step6" maxlength="60"
										onblur="clearBlank(this);" />
									

								</td>
								<td width="35%">
									<div id="step6Tip"></div>
								</td>
							</tr>
							<tr>
								<th width="15%">
									step7 DOCS：
								</th>
								<td colspan="2">
									<s:textfield size="60" id="step7" cssClass="td03"
										name="orderForm.step7" maxlength="60"
										onblur="clearBlank(this);" />
									

								</td>
								<td width="35%">
									<div id="step7Tip"></div>
								</td>
							</tr>
							<tr>
								<th width="15%">
									step8：
								</th>
								<td colspan="2">
									<s:textfield size="60" id="step8" cssClass="td03"
										name="orderForm.step8" maxlength="60"
										onblur="clearBlank(this);" />
									

								</td>
								<td width="35%">
									<div id="step8Tip"></div>
								</td>
							</tr>							
							<!-- 
							<tr>
								<th align="right">
									图标：
								</th>
								<td colspan="3">
									<s:textfield id="picPath" name="messageForm.picPath"
										cssClass="input2" size="20" onblur="clearBlank(this);"
										readonly="true"></s:textfield>
									<input type="button" id="btn_upfile" onclick="up_file();"
										class="button2" onmouseout="this.className = 'button2'"
										onmouseover="this.className = 'button2Over'" value="图片上传" />
									<input type="button" id="btn_clear" onclick="clear_upfile();"
										class="button" onmouseout="this.className = 'button'"
										onmouseover="this.className = 'buttonOver'" value="清空" />
								</td>
							</tr> -->
							
							<tr>
								<td class="bottom" align="center" colspan="4">
									<div align="center">
										<s:if test="%{actionName == 'order_save'}">
											<input type="button" id="btn_save" class="button" onclick="javascript:order_save('ckboxItem');" value="增加" />
										</s:if>
										<s:else>
											<input type="button" id="btn_save" class="button" onclick="javascript:order_update('ckboxItem');" value="保存" />
										</s:else>
										<input type="button" onclick="javascript:history.back();"
											class="button" value="返回" />
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
								<td style="padding-right: 20px" colspan="4">
								
								<div>
									<input type="button" onclick="tableAddRow(tableId)" class="button2" value="添加产品"/>
								</div>
								</td>
							</tr>
			</table>
		<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
		<table id="tableId" class="tb_result" border="0" cellspacing="1" cellpadding="1" align="center">
    	 <tr class="tr_head" align="center">
			<td width="25%">产品名称</td>
       	 	<td width="25%">单价</td>
			<td width="20%">单位</td>
			<td width="20%">产品数量</td>
			<td width="10%">操作</td>
     	 </tr>
		<s:iterator id="product" value="%{orderForm.product}" status="sta">
      <tr align="center" class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
		<td>
			<s:textfield size="20"  cssClass="td03" 
										name="%{'orderForm.product['+#sta.index+'].productName'}"  maxlength="60"
										onblur="clearBlank(this);" />
		</td>
        <td>
			<s:textfield size="20" cssClass="td03" 
										name="%{'orderForm.product['+#sta.index+'].salePrice'}" maxlength="60" 
										onblur="clearBlank(this);" />
		</td>
        <td><s:textfield size="20" cssClass="td03" 
										name="%{'orderForm.product['+#sta.index+'].weight'}" maxlength="60" 
										onblur="clearBlank(this);" /></td>
        <td><s:textfield size="20" cssClass="td03" 
										name="%{'orderForm.product['+#sta.index+'].productNum'}" maxlength="60" 
										onblur="clearBlank(this);" /></td>
		<td><input type="button" value="删除" onClick="delRow(this)" /></td>
      </tr>
      </s:iterator>
		</table>
		</td></tr></table>
		</s:form>

		<script type="text/javascript">
	$.formValidator.initConfig({
	formid:"setForm",
	onerror:function(msg){
	alert(msg)},
	onsuccess:function(){
		return true;
	}});
	
$("#orderCode").formValidator( {
		onshow : "请输入订单编码，长度为1~60个字符",
		onfocus : "订单编码不能为空",
		oncorrect : "输入合法"
	}).inputValidator( {
		min:1,
		max:60,
		onerror:"请确认标题长度(1~60个字符)"
	});	
	
</script>

	</body>
</html>