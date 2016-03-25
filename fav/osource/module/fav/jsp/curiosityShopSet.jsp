<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/curiosityShopMgr.js"></script>
<script type="text/javascript" src="<%=path%>/resource/js/geo.js"></script>
<script type="text/javascript">
	<s:if test="%{actionName == 'curiosityShop_save'}">
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'curiosityShop_save.do',
			    saveUrlTo:'curiosityShop_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:if>
	<s:else>
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'curiosityShop_update.do',
			    saveUrlTo:'curiosityShop_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:else>
</script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<s:form id="setForm" name="curiosityShopForm" action="%{actionName}">
	<s:hidden name="curiosityShopForm.id"></s:hidden>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr>
					    <th width="20%" style="text-align:right;">名称：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true" name="curiosityShopForm.name" value="<s:property value="curiosityShopForm.name"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">地址：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true" name="curiosityShopForm.address" value="<s:property value="curiosityShopForm.address"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">电话：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true,validType:'phoneOrMobile'" name="curiosityShopForm.phone" value="<s:property value="curiosityShopForm.phone"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">简介：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true" name="curiosityShopForm.introduction" value="<s:property value="curiosityShopForm.introduction"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">纬度：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true,validType:'intOrFloat'" name="curiosityShopForm.lat" value="<s:property value="curiosityShopForm.lat"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">经度：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true,validType:'intOrFloat'" name="curiosityShopForm.longitude" value="<s:property value="curiosityShopForm.longitude"/>"/>
					    </td>
					</tr>
					<tr>
						<th width="20%" style="text-align:right;">省市县：</th>
						<td width="40%">
							<select class="select" name="curiosityShopForm.province" id="s1">
							  <option></option>
							</select>
							<select class="select" name="curiosityShopForm.city" id="s2">
							  <option></option>
							</select>
							<select class="select" name="curiosityShopForm.county" id="s3">
							  <option></option>
							</select>
						</td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">图标：</th>
					    <td width="40%">
					   		<s:if test="%{curiosityShopForm.icon != null && curiosityShopForm.icon != ''}">
								<img id="imgPathIconImg" src="<s:property value='curiosityShopForm.icon'/>" alt="点击编辑" style="cursor: pointer;" width="32" height="32" onclick="openDialogUploadIcon('imgPathIcon');"/>
							</s:if>
							<s:else>
								<img id="imgPathIconImg" src="images/ryxx02.gif" alt="点击上传" style="cursor: pointer;" width="32" height="32" onclick="openDialogUploadIcon('imgPathIcon');"/>
							</s:else>
					        <input type="hidden" id="imgPathIcon" name="curiosityShopForm.icon" value="<s:property value="curiosityShopForm.icon"/>"/>
					    
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">上传图片：</th>
					    <td width="40%">
					    	<input type="hidden" name="curiosityShopForm.images" value="<s:property value="curiosityShopForm.images"/>"/>
							<s:if test="%{curiosityShopForm.images != null && curiosityShopForm.images != ''}">
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
						<td class="bottom" align="center" colspan="4">
							<div align="center">
								<s:if test="%{actionName == 'curiosityShop_save'}">
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


// 动态添加图片
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
$(document).ready(function(){
	setup();// 查询条件的省市县初始化
	var province = '<s:property value="curiosityShopForm.province"/>';
	if (province != "") {
		preselect(province);
		var city = '<s:property value="curiosityShopForm.city"/>';
		if (city != "") {
			$("#s2").val(city);
			change(2);
			var area = '<s:property value="curiosityShopForm.county"/>';
			if (area != "") {
				$("#s3").val(area);
			}
		}
	}
});
function addButton(obj){
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