<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/commonNew.jsp"%>
<script type="text/javascript" src="js/auctionDynamicImagesMgr.js"></script>
<script type="text/javascript">
	function fromValidate(){
		if ($("#auctionDynamicId").val() == '-1'){
			alert("请选择动态");
			return false;
		}
		if ($.trim($("#content").val()) == ''){
			alert("内容不能为空");
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
			url: "auctionDynamicImages_save.do",
			data: $("#setForm").serialize(),
			success: function(data){
				alert(data.message);
				window.location.href="auctionDynamicImages_init.do";
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
				url: "auctionDynamicImages_update.do",
				data: $("#setForm").serialize(),
				success: function(data){
					alert(data.message);
					window.location.href="auctionDynamicImages_init.do";
				},
				error: function(XMLHttpRequest, textStatus, errorThrown){
					alert("修改失败");
				},
				dataType: "json"
			});
	}
	
 	$(function() {
 		$("#auctionDynamicId").combobox();
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
<s:form id="setForm" name="auctionDynamicImagesForm" action="%{actionName}">
	<s:hidden name="auctionDynamicImagesForm.id"></s:hidden>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr>
					    <th width="20%" style="text-align:right;">拍卖行动态：</th>
					    <td width="40%">
					        <s:select id="auctionDynamicId" name="auctionDynamicImagesForm.auctionDynamicId" list="dynamicMap" listKey="key" listValue="value" headerKey="-1" headerValue="所有" cssStyle="width:150px"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">内容：</th>
					    <td width="40%">
					        <input type="text" id="content" name="auctionDynamicImagesForm.content" value="<s:property value="auctionDynamicImagesForm.content"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">图片</th>
					    <td width="40%">
					    	<input type="hidden" name="auctionDynamicImagesForm.images" value="<s:property value="auctionDynamicImagesForm.images"/>"/>
							<s:if test="%{auctionDynamicImagesForm.images != null && auctionDynamicImagesForm.images != ''}">
								 <s:iterator id="img" value="%{imgs}" status="sta">
									<div id="imgDiv<s:property value="#sta.index"/>">
										<img id="imgPath<s:property value="#sta.index"/>Img" src="<s:property value='#img'/>?imageView2/1/w/32/h/32" alt="点击编辑" style="cursor: pointer;" width="32" height="32" onclick="openDialogUpload(this);"/>
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
								<s:if test="%{actionName == 'auctionDynamicImages_save'}">
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
<div id="uploadDialog" title="图片上传"></div>
<script type="text/javascript">
var picType;
$.formValidator.initConfig({
	formid:"setForm",
	onerror:function(msg){
	alert(msg)},
	onsuccess:function(){
		return true;
}});
//添加图片
function openDialogUpload(obj){
	var idStr = $(obj).attr("id");
	picType = idStr.substr(0, idStr.indexOf("Img"));
	$("#uploadDialog").dialog('open').html("正在加载页面...").load(_contextPath + "/upload_uploadInit.do?mode=ajax&uploadContentType=pic&path=file");
}
function callbackpic(fileName, type) {
	fileName = "/upload/" + fileName;
	$("#" + picType).val(fileName);
	$("#" + picType + "Img").prop("src", fileName+"?imageView2/1/w/32/h/32");
	$("#uploadDialog").dialog('close');
}

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