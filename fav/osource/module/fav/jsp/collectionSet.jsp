<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/collectionMgr.js"></script>
<script type="text/javascript">
	<s:if test="%{actionName == 'collection_save'}">
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'collection_save.do',
			    saveUrlTo:'collection_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:if>
	<s:else>
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'collection_update.do',
			    saveUrlTo:'collection_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:else>
</script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<%@include file="source_browse.jsp"%>
<s:form id="setForm" name="collectionForm" action="%{actionName}">
	<s:hidden name="collectionForm.id"></s:hidden>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr>
					    <th width="20%" style="text-align:right;">标题：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true" name="collectionForm.title" value="<s:property value="collectionForm.title"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">藏品类别：</th>
					    <td width="40%">
					    	<s:select id="categoryId" name="collectionForm.categoryId" list="collectionCategorys" listKey="key" listValue="value" cssClass="easyui-validatebox" data-options="required:true" cssStyle="width:150px"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">所属时期：</th>
					    <td width="40%">
		    				<s:select id="collectionPeriodId" name="collectionForm.collectionPeriodId" list="collectionPeriods" listKey="key" listValue="value" cssClass="easyui-validatebox" data-options="required:true" cssStyle="width:150px"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">藏品简介：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true" name="collectionForm.introduction" value="<s:property value="collectionForm.introduction"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">是否愿意送拍：</th>
					    <td width="40%">
					    	<s:select id="isSendRacket" name="collectionForm.isSendRacket" list="whethers" listKey="key" listValue="value" cssClass="easyui-validatebox" data-options="required:true" cssStyle="width:150px"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">是否愿意出售：</th>
					    <td width="40%">
					    	<s:select id="isSold" name="collectionForm.isSold" list="whethers" listKey="key" listValue="value" cssClass="easyui-validatebox" data-options="required:true" cssStyle="width:150px"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">是否鉴定：</th>
					    <td width="40%">
					    	<s:select id="isIdentify" name="collectionForm.isIdentify" list="whethers" listKey="key" listValue="value" cssClass="easyui-validatebox" data-options="required:true" cssStyle="width:150px"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">标签：</th>
					    <td width="40%">
					    	<s:select id="labelId" name="collectionForm.labelId" list="collectionLables" listKey="key" listValue="value" cssClass="easyui-validatebox" data-options="required:true" cssStyle="width:150px"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">图标：</th>
					    <td width="40%">
					   		<s:if test="%{collectionForm.iconImg != null && collectionForm.iconImg != ''}">
								<img id="imgPathIconImg" src="<s:property value='collectionForm.iconImg'/>" alt="点击编辑" style="cursor: pointer;" width="32" height="32" onclick="openDialogUploadIcon('imgPathIcon');"/>
							</s:if>
							<s:else>
								<img id="imgPathIconImg" src="images/ryxx02.gif" alt="点击上传" style="cursor: pointer;" width="32" height="32" onclick="openDialogUploadIcon('imgPathIcon');"/>
							</s:else>
					        <input type="hidden" id="imgPathIcon" name="collectionForm.iconImg" value="<s:property value="collectionForm.iconImg"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">上传图片：</th>
					    <td width="40%">
							<s:if test="%{imgs != null}">
								 <s:iterator id="img" value="%{imgs}" status="sta">
									<div id="imgDiv<s:property value="#sta.index"/>">
										<img id="imgPath<s:property value="#sta.index"/>Img" src="<s:property value='#img'/>" alt="点击编辑" style="cursor: pointer;" width="32" height="32" onclick="openDialogUpload(this);"/>
										<input type="hidden" id="imgPath<s:property value="#sta.index"/>" name="imgs[<s:property value="#sta.index"/>]" value="<s:property value="#img"/>"/>
								    	<span>&nbsp;&nbsp;</span>
								    	<a onclick="addButton(this)"><img src="images/plus.png"/></a>
								        <a onclick="delButton(this)"><img src="images/delete.png"/></a>
								        <a onclick="moveUpButton(this)"><img src="images/arrow_up.png"/></a>
								        <a onclick="moveDownButton(this)"><img src="images/arrow_down.png"/></a>
								 	</div>
								 </s:iterator>
							</s:if>
							<s:else>
								<div id="imgDiv0">
									<img id="imgPath0Img" src="images/ryxx02.gif" alt="点击上传" style="cursor: pointer;" width="32" height="32" onclick="openDialogUpload(this);"/>
									<input type="hidden" id="imgPath0" name="imgs[0]" />
							    	<span>&nbsp;&nbsp;</span>
							    	<a onclick="addButton(this)"><img src="images/plus.png"/></a>
							        <a onclick="delButton(this)"><img src="images/delete.png"/></a>
							        <a onclick="moveUpButton(this)"><img src="images/arrow_up.png"/></a>
							        <a onclick="moveDownButton(this)"><img src="images/arrow_down.png"/></a>
							     </div>
							</s:else>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">热度：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true,validType:'naturalNumber'" name="collectionForm.heat" value="<s:property value="collectionForm.heat"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">鉴定结果：</th>
					    <td width="40%">
					        <input type="text" name="collectionForm.identifyResult" value="<s:property value="collectionForm.identifyResult"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">状态：</th>
					    <td width="40%">
					    	<s:select id="status" name="collectionForm.status" list="statusMap" listKey="key" listValue="value" cssClass="easyui-validatebox" data-options="required:true" cssStyle="width:150px"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">发布人：</th>
					    <td width="40%">
					    	<input type="text" readonly="readonly" class="easyui-validatebox" data-options="required:true" id="insertUserName" name="collectionForm.insertUserName" value="<s:property value="collectionForm.insertUserName"/>"/>
					    	<input type="hidden" class="easyui-validatebox" data-options="required:true" id="insertId" name="collectionForm.insertId" value="<s:property value="collectionForm.insertId"/>"/>
					    	<input type="button" class="button" onclick="dialogForBrowse('insertId','insertUserName','<%=request.getContextPath()%>/module/fav/favUser_queryForBrowse.do');" value="浏览" />
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">拍卖行：</th>
					    <td width="40%">
					        <s:select id="auctionId" name="collectionForm.auctionId" list="auctionMap" listKey="key" listValue="value" cssClass="easyui-validatebox" data-options="required:true" cssStyle="width:150px"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">估价：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="validType:'money'" name="collectionForm.appraisal" value="<s:property value="collectionForm.appraisal"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">估价单位：</th>
					    <td width="40%">
					        <s:select id="appraisalUnit" name="collectionForm.appraisalUnit" list="moneyMap" listKey="key" listValue="value" cssStyle="width:150px"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">估价时间：</th>
					    <td width="40%">
					    	<input id="appraisalTime" name="collectionForm.appraisalTime" value="<s:date name="collectionForm.appraisalTime" format="yyyy-MM-dd HH:mm:ss" />" type="text" cssStyle="width:150px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" />
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">估计人id：</th>
					    <td width="40%">
					        <input type="text" name="collectionForm.appraisalUserId" value="<s:property value="collectionForm.appraisalUserId"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">估计人：</th>
					    <td width="40%">
					        <input type="text" name="collectionForm.appraisalUserName" value="<s:property value="collectionForm.appraisalUserName"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">成交价：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="validType:'money'" name="collectionForm.transactionPrice" value="<s:property value="collectionForm.transactionPrice"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">成交价单位：</th>
					    <td width="40%">
					    	<s:select id="transactionPriceUnit" name="collectionForm.transactionPriceUnit" list="moneyMap" listKey="key" listValue="value" cssStyle="width:150px"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">成交时间：</th>
					    <td width="40%">
					   		<input id="transactionPriceTime" name="collectionForm.transactionPriceTime" value="<s:date name="collectionForm.transactionPriceTime" format="yyyy-MM-dd HH:mm:ss" />" type="text" cssStyle="width:150px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" />
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">成交人id：</th>
					    <td width="40%">
					        <input type="text"  name="collectionForm.transactionUserId" value="<s:property value="collectionForm.transactionUserId"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">成交人：</th>
					    <td width="40%">
					        <input type="text" name="collectionForm.transactionUserName" value="<s:property value="collectionForm.transactionUserName"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">成交说明：</th>
					    <td width="40%">
					        <input type="text" name="collectionForm.transactionDesc" value="<s:property value="collectionForm.transactionDesc"/>"/>
					    </td>
					</tr>
					<tr>
						<td class="bottom" align="center" colspan="4">
							<div align="center">
								<s:if test="%{actionName == 'collection_save'}">
									<input type="button" id="btn_save" class="button" value="增加" />
								</s:if>
								<s:else>
									<input type="button" id="btn_save" class="button" value="保存" />
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
<div id="uploadDialog" title="图片上传"></div>
<script type="text/javascript">
var picType;
//添加图片
function openDialogUploadIcon(labelId){
	picType = labelId;
	$("#uploadDialog").dialog('open').html("正在加载页面...").load(_contextPath + "/upload_uploadInit.do?mode=ajax&uploadContentType=pic&path=file");
}
function callbackpic(fileName, type) {
	fileName = "/upload/" + fileName;
	$("#" + picType).val(fileName);
	$("#" + picType + "Img").prop("src", fileName);
	$("#uploadDialog").dialog('close');
}


//动态添加图片
function openDialogUpload(obj){
	var idStr = $(obj).attr("id");
	picType = idStr.substr(0, idStr.indexOf("Img"));
	$("#uploadDialog").dialog('open').html("正在加载页面...").load(_contextPath + "/upload_uploadInit.do?mode=ajax&uploadContentType=pic&path=file");
}

$.formValidator.initConfig({
	formid:"setForm",
	onerror:function(msg){
	alert(msg)},
	onsuccess:function(){
		return true;
}});

function addButton(obj){
	var index =  $("div[id^='imgDiv']").length;
	if (index >= 6){
		alert("最多添加6个图片");
		return;
	}
	$(obj).parent().after("<div id=\"imgDiv\"></div>");
	var imgContent = "<img id=\"imgPathImg\" src=\"images/ryxx02.gif\" alt=\"点击上传\" style=\"cursor: pointer;\" width=\"32\" height=\"32\" onclick=\"openDialogUpload(this);\"/>";
	$(obj).parent().next().append("<input type=\"hidden\" id=\"imgPath\" name=\"imgs\"/>")
		.append(imgContent).append("<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>")
		.append("<a onclick=\"addButton(this)\"><img src=\"images/plus.png\"/></a>&nbsp")
		.append("<a onclick=\"delButton(this)\"><img src=\"images/delete.png\"/></a>&nbsp")
		.append("<a onclick=\"moveUpButton(this)\"><img src=\"images/arrow_up.png\"/></a>&nbsp")
		.append("<a onclick=\"moveDownButton(this)\"><img src=\"images/arrow_down.png\"/></a>");
	indexHelp();
}

function delButton(obj){
   var index =  $("div[id^='imgDiv']").length;
	// 只有一个删除不处理
	if (index == 1){
		return;
	}
	$(obj).parent().remove();
	indexHelp();
}

function moveUpButton(obj){
	var divId = $(obj).parent().attr("id"); 
	var index =  parseInt(divId.substring('imgDiv'.length), 10);
	// 第一个上移不处理
	if (index == 0){
		return;
	}
	var indexA1 = $("#imgPath" + index + "Img");
	var indexB1 = $("#imgPath" + index);
	var indexA2 =  $("#imgPath" + (index-1) + "Img");
	var indexB2 = $("#imgPath" + (index-1));
	var a1 = indexA1.attr("src");
	var b1 = indexB1.val();
	var a2 = indexA2.attr("src");
	var b2 = indexB2.val();
	indexA1.attr("src", a2);
	indexA2.attr("src", a1);
	indexB1.val(b2);
	indexB2.val(b1);
}

function moveDownButton(obj){
	var divId = $(obj).parent().attr("id"); 
	var index =  parseInt(divId.substring('imgDiv'.length), 10);
	// 最后一个下移不处理
	if ((index+1) == $("div[id^='imgDiv']").length){
		return;
	}
	var indexA1 = $("#imgPath" + index + "Img");
	var indexB1 = $("#imgPath" + index);
	var indexA2 =  $("#imgPath" + (index+1) + "Img");
	var indexB2 = $("#imgPath" + (index+1));
	var a1 = indexA1.attr("src");
	var b1 = indexB1.val();
	var a2 = indexA2.attr("src");
	var b2 = indexB2.val();
	indexA1.attr("src", a2);
	indexA2.attr("src", a1);
	indexB1.val(b2);
	indexB2.val(b1);
}

function indexHelp(){
	// 从新增或者删除的下一个元素开始变更index值
	$("div[id^='imgDiv']").each(function(i, item){
         $(item).attr("id", "imgDiv" + i);
         $(item).find("input[name^='imgs']").each(function(j, inputItem){
        	 $(inputItem).attr("name", "imgs[" + i + "]");
        	 $(inputItem).attr("id", "imgPath" + i);
         });
         $(item).find("img[id^='imgPath']").each(function(j, inputItem){
        	 $(inputItem).attr("id", "imgPath" + i + "Img");
         });
    });
}
</script>

</body>
</html>