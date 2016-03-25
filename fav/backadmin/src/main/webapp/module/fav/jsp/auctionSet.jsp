<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/auctionMgr.js"></script>
<script type="text/javascript">
	<s:if test="%{actionName == 'auction_save'}">
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'auction_save.do',
			    saveUrlTo:'auction_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:if>
	<s:else>
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'auction_update.do',
			    saveUrlTo:'auction_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:else>
</script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<s:form id="setForm" name="auctionForm" action="%{actionName}">
	<s:hidden name="auctionForm.id"></s:hidden>
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr>
					    <th width="20%" style="text-align:right;">拍卖行名字：</th>
					    <td width="40%">
					        <input type="text" class="easyui-validatebox" data-options="required:true" name="auctionForm.name" value="<s:property value="auctionForm.name"/>"/>
					    </td>
					</tr>
					<tr>
					    <th width="20%" style="text-align:right;">拍卖行图标：</th>
					    <td width="40%">
						    <s:if test="%{auctionForm.icon != null && auctionForm.icon != ''}">
								<img id="imgPathImg" src="${qnImageUrl}<s:property value='auctionForm.icon'/>?imageView2/1/w/32/h/32" alt="点击编辑" style="cursor: pointer;" width="32" height="32" onclick="openDialogUpload('imgPath');"/>
							</s:if>
							<s:else>
								<img id="imgPathImg" src="images/ryxx02.gif" alt="点击上传" style="cursor: pointer;" width="32" height="32" onclick="openDialogUpload('imgPath');"/>
							</s:else>
					        <input type="hidden" id="imgPath" name="auctionForm.icon" value="<s:property value="auctionForm.icon"/>"/>
					    </td>
					</tr>
					<tr>
						<td class="bottom" align="center" colspan="4">
							<div align="center">
								<s:if test="%{actionName == 'auction_save'}">
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
$.formValidator.initConfig({
	formid:"setForm",
	onerror:function(msg){
	alert(msg)},
	onsuccess:function(){
		return true;
}});

var picType;

//添加图片
function openDialogUpload(labelId){
	picType = labelId;
	$("#uploadDialog").dialog('open').html("正在加载页面...").load(_contextPath + "/upload_uploadInit.do?mode=ajax&uploadContentType=pic&path=file");
}

function callbackpic(fileName, type) {
//	fileName = "/upload/" + fileName;
	$("#" + picType).val(fileName);
	$("#" + picType + "Img").prop("src", "${qnImageUrl}"+fileName+"?imageView2/1/w/32/h/32");
	$("#uploadDialog").dialog('close');
}
</script>

</body>
</html>