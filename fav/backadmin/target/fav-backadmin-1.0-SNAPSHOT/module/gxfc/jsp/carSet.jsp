<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/carMgr.js"></script>
<script type="text/javascript">
	<s:if test="%{actionName == 'car_save'}">
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'car_save.do',
			    saveUrlTo:'car_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:if>
	<s:else>
		$(document).ready(function(){
			$(document).ict({
				saveForm:'setForm',
			    saveUrl:'car_update.do',
			    saveUrlTo:'car_init.do',
			    saveBtn:'btn_save'
			});
		});
	</s:else>
</script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<s:form id="setForm" name="carForm" action="%{actionName}">
	<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr>
						<td class="td_title" colspan="4" align="center">
							<b>详细信息</b>
						</td>         
					</tr>
					<tr>
						<th align="right" width="50%">品牌拼音：</th>
						<td>
							<input type="hidden" name="carForm.title" value="<s:property value='carForm.title'/>"/>
							<input type="hidden" name="carForm.id" value="<s:property value='carForm.id'/>"/>
							<input type="hidden" name="carForm.surplusNum" value="<s:property value='carForm.surplusNum'/>"/>
							<input type="hidden" name="carForm.approveRemark" value="<s:property value='carForm.approveRemark'/>"/>
							<input type="hidden" name="carForm.carStatus" value="<s:property value='carForm.carStatus'/>"/>
							<s:select id="pinyin" onchange="choseBrand();" list="#{'A':'A','B':'B','C':'C','D':'D','E':'E','F':'F','G':'G','H':'H','I':'I','J':'J','K':'K','L':'L','M':'M','N':'N','O':'O','P':'P','Q':'Q','R':'R','S':'S','T':'T','U':'U','V':'V','W':'W','X':'X','Y':'Y','Z':'Z'}"></s:select></td> 
					</tr>
					<tr>
						<th align="right" width="50%">选择品牌：</th>
						<td>
							<select name="carForm.brandId" id="brandId" onchange="choseVersion();genTitle();">
								<option value="<s:property value='carForm.brandId'/>" selected="selected"><s:property value='carForm.brandInfo.name'/></option>
							</select>
						</td>
					</tr>
					<tr>
						<th align="right" width="50%">选择版本：</th>
						<td>
							<select name="carForm.versionId" id="versionId" onchange="choseSeries();genTitle();">
								<option value="<s:property value='carForm.versionId'/>" selected="selected"><s:property value='carForm.carVersionInfo.name'/></option>
							</select>
						</td> 
					</tr>
					<tr>
						<th align="right" width="50%">选择系列：</th>
						<td>
							<select name="carForm.seriesId" id="seriesId" onchange="choseModels();choseEngines();genTitle();">
								<option value="<s:property value='carForm.seriesId'/>" selected="selected"><s:property value='carForm.seriesInfo.name'/></option>
							</select>
						</td>
					</tr>
					<tr>
						<th align="right" width="50%">选择车型：</th>
						<td>
							<select name="carForm.modelsId" id="modelsId" onchange="choseModelyear();genTitle();">
								<option value="<s:property value='carForm.modelsId'/>" selected="selected"><s:property value='carForm.modelsInfo.name'/></option>
							</select>
						</td>
					</tr>
					<tr>
						<th align="right" width="50%">选择年款：</th>
						<td>
							<select name="carForm.modelyearId" id="modelyearId" onchange="choseInnercolor();choseOutercolor();genTitle();">
								<option value="<s:property value='carForm.modelyearId'/>" selected="selected"><s:property value='carForm.modelyearInfo.name'/></option>
							</select>
						</td>
					</tr>
					<tr>
						<th align="right" width="50%">选择排量：</th>
						<td>
							<select name="carForm.enginesId" id="enginesId" onchange="genTitle();">
								<option value="<s:property value='carForm.enginesId'/>" selected="selected"><s:property value='carForm.enginesName'/></option>
							</select>
						</td>
					</tr>
					<tr>
						<th align="right" width="50%">选择燃油：</th>
						<td><s:select name="carForm.fuel" list="#{'汽油':'汽油','柴油':'柴油','油电混合':'油电混合','电动':'电动'}"></s:select></td> 
					</tr>
					<tr>
						<th align="right" width="50%">外饰颜色：</th>
						<td>
							<select name="carForm.outercolorId" id="outercolorId">
								<option value="<s:property value='carForm.outercolorId'/>" selected="selected"><s:property value='carForm.outercolorName'/></option>
							</select>
						</td>
					</tr>
					<tr>
						<th align="right" width="50%">内饰颜色：</th>
						<td>
							<select name="carForm.innercolorId" id="innercolorId">
								<option value="<s:property value='carForm.innercolorId'/>" selected="selected"><s:property value='carForm.outercolorName'/></option>
							</select>
						</td>
					</tr>
					<tr>
						<th align="right" width="50%">所在省份：</th>
						<td>
							<select name="carForm.province" id="province"></select>
							<input type="hidden" id="province_value" value="<s:property value='carForm.province'/>"/></td> 
					</tr>
					<tr>
						<th align="right" width="50%">预付车款比例：</th>
						<td><s:select name="carForm.depositRatio" list="#{'0':'请选择预付款车款比例',1:'10%',2:'10%',3:'30%',4:'40%',5:'50%',6:'60%',7:'70%',8:'80%',9:'90%',10:'100%'}"></s:select></td> 
					</tr>
					<tr>
						<th align="right" width="50%">选择货源：</th>
						<td><s:select name="carForm.source" list="#{'0':'请选择货源',1:'现货',2:'期货'}"></s:select></td> 
					</tr>
					<tr>
						<th align="right" width="50%">车辆手续：</th>
						<td>
							<input class="checkbox" type="checkbox" name="procedures" value="1" onchange="getCheckboxValuesByName('procedures');" <s:if test="%{(carForm.procedures&1)==1}">checked="checked"</s:if>/>用户发票<br/>
							<input class="checkbox" type="checkbox" name="procedures" value="2" onchange="getCheckboxValuesByName('procedures');" <s:if test="%{(carForm.procedures&2)==2}">checked="checked"</s:if>/>随车检验单<br/>
							<input class="checkbox" type="checkbox" name="procedures" value="4" onchange="getCheckboxValuesByName('procedures');" <s:if test="%{(carForm.procedures&4)==4}">checked="checked"</s:if>/>车辆一致性证书<br/>
							<input class="checkbox" type="checkbox" name="procedures" value="8" onchange="getCheckboxValuesByName('procedures');" <s:if test="%{(carForm.procedures&8)==8}">checked="checked"</s:if>/>购置税电子证书<br/>
							<input class="checkbox" type="checkbox" name="procedures" value="16" onchange="getCheckboxValuesByName('procedures');" <s:if test="%{(carForm.procedures&16)==16}">checked="checked"</s:if>/>基本信息表<br/>
							<input class="checkbox" type="checkbox" name="procedures" value="32" onchange="getCheckboxValuesByName('procedures');" <s:if test="%{(carForm.procedures&32)==32}">checked="checked"</s:if>/>货物进口证明书<br/>
							<input class="checkbox" type="checkbox" name="procedures" value="64" onchange="getCheckboxValuesByName('procedures');" <s:if test="%{(carForm.procedures&64)==64}">checked="checked"</s:if>/>车辆销售正规发票<br/>
							<input class="checkbox" type="checkbox" name="procedures" value="128" onchange="getCheckboxValuesByName('procedures');" <s:if test="%{(carForm.procedures&128)==128}">checked="checked"</s:if>/>车辆合格证<br/>
							<input class="checkbox" type="checkbox" name="procedures" value="256" onchange="getCheckboxValuesByName('procedures');" <s:if test="%{(carForm.procedures&256)==256}">checked="checked"</s:if>/>车辆保修手册<br/>
							<input class="checkbox" type="hidden" id="procedures" name="carForm.procedures" value="<s:property value='carForm.procedures'/>"/>
						</td> 
					</tr>
					<tr>
						<th align="right" width="50%">出售数量：</th>
						<td><input type="text" name="carForm.num" id="num" value="<s:property value='carForm.num'/>" onkeyup="this.value=this.value.replace(/^\D+$/g,'')"/></td> 
					</tr>
					<tr>
						<th align="right" width="50%">出售价格：</th>
						<td><input type="text" name="carForm.price" id="price" value="<s:property value='carForm.price'/>" onkeyup="this.value=this.value.replace(/^\D+$/g,'')"/></td> 
					</tr>
					<tr>
						<th align="right" width="50%">配送方式：</th>
						<td><s:select value="<s:property value='carForm.logistics'/>" name="carForm.logistics" list="#{1:'买家自提',2:'卖家配送',3:'平台配送'}"></s:select></td> 
					</tr>
					<tr>
						<th align="right" width="50%">提货周期：</th>
						<td><s:select value="<s:property value='carForm.deliveryPeriod'/>" name="carForm.deliveryPeriod" list="#{'0':'任意',3:'3天',7:'7天',15:'15天',30:'30天'}"></s:select></td> 
					</tr>
					<tr>
						<th align="right" width="50%">特殊说明：</th>
						<td>
							<textarea rows="" cols="" name="carForm.remark"><s:property value="carForm.remark"/></textarea>
						</td> 
					</tr>
					<tr>
						<td class="bottom" align="center" colspan="4">
							<div align="center">
								<s:if test="%{actionName == 'car_save'}">
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

<script type="text/javascript">
$.formValidator.initConfig({
	formid:"setForm",
	onerror:function(msg){
	alert(msg)},
	onsuccess:function(){
		return true;
}});

//查询品牌
function choseBrand() {
	var pinyin = $("#pinyin").val();
	var params = {};
	if(pinyin)
		params = {"brandForm.pinyin":pinyin};
	
	$.ajax({
		type: "POST",
		url: "/module/gxfc/brand_queryBypinyin.do",
		data: params,
		success: function(data){
			$("#brandId").html("<option value='0'>请选择品牌</option>");
			if(data.message != undefined && data.message.length>0){
				var optionHtml = "<option value='0'>请选择品牌</option>";
				for(var i=0; i<data.message.length; i++){
					optionHtml += "<option value='" + data.message[i].id + "'>" + data.message[i].name + "</option>";
				}
				$("#brandId").html(optionHtml);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("获取品牌失败");
		},
		dataType: "json"
	});
}

//查询版本
function choseVersion(val) {
	var brandId = $("#brandId").val();
	versionObj = [];
	$.ajax({
		type: "POST",
		url: "/module/gxfc/carVersion_queryByBrand.do",
		data: {"carVersionForm.brandId":brandId},
		success: function(data){
			$("#versionId").html("<option value='0'>请选择版本</option>");
			if(data.message != undefined && data.message.length>0){
				var optionHtml = "<option value='0'>请选择版本</option>";
				for(var i=0; i<data.message.length; i++){
					versionObj[data.message[i].id] = data.message[i];
					optionHtml += "<option value='" + data.message[i].id + "'>" + data.message[i].name + "</option>";
				}
				$("#versionId").html(optionHtml);
				
				if(val) {
					$("#brandId").val(val);
				}
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("获取版本失败");
		},
		dataType: "json"
	});
}

//查询车型
function choseModels() {
	var seriesId = $("#seriesId").val();
	$.ajax({
		type: "POST",
		url: "/module/gxfc/models_queryBySeries.do",
		data: {"modelsForm.seriesId":seriesId},
		success: function(data){
			$("#modelsId").html("<option value='0'>请选择车型</option>");
			if(data.message != undefined && data.message.length>0){
				var optionHtml = "<option value='0'>请选择车型</option>";
				for(var i=0; i<data.message.length; i++){
					optionHtml += "<option value='" + data.message[i].id + "'>" + data.message[i].name + "</option>";
				}
				$("#modelsId").html(optionHtml);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("获取车型失败");
		},
		dataType: "json"
	});
}

// 查询年款
function choseModelyear() {
	var modelsId = $("#modelsId").val();
	carObj = [];
	$.ajax({
		type: "POST",
		url: "/module/gxfc/modelyear_queryByModels.do",
		data: {"modelyearForm.modelsId":modelsId},
		success: function(data){
			$("#modelyearId").html("<option value='0'>请选择年款</option>");
			if(data.message != undefined && data.message.length>0){
				var optionHtml = "<option value='0'>请选择年款</option>";
				for(var i=0; i<data.message.length; i++){
					carObj[data.message[i].id] = data.message[i];
					optionHtml += "<option value='" + data.message[i].id + "'>" + data.message[i].name + "</option>";
				}
				$("#modelyearId").html(optionHtml);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("获取年款失败");
		},
		dataType: "json"
	});
}

//查询内饰
function choseInnercolor() {
	var modelyearId = $("#modelyearId").val();
	$.ajax({
		type: "POST",
		url: "/module/gxfc/innercolor_queryByModelyear.do",
		data: {"innercolorForm.modelyearId":modelyearId},
		success: function(data){
			$("#innercolorId").html("<option value='0'>请选择内饰颜色</option>");
			if(data.message != undefined && data.message.length>0){
				var optionHtml = "<option value='0'>请选择内饰颜色</option>";
				for(var i=0; i<data.message.length; i++){
					optionHtml += "<option value='" + data.message[i].id + "'>" + data.message[i].name + "</option>";
				}
				$("#innercolorId").html(optionHtml);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("获取内饰颜色失败");
		},
		dataType: "json"
	});
}

//查询外饰
function choseOutercolor() {
	var modelyearId = $("#modelyearId").val();
	$.ajax({
		type: "POST",
		url: "/module/gxfc/outercolor_queryByModelyear.do",
		data: {"outercolorForm.modelyearId":modelyearId},
		success: function(data){
			$("#outercolorId").html("<option value='0'>请选择外饰颜色</option>");
			if(data.message != undefined && data.message.length>0){
				var optionHtml = "<option value='0'>请选择外饰颜色</option>";
				for(var i=0; i<data.message.length; i++){
					optionHtml += "<option value='" + data.message[i].id + "'>" + data.message[i].name + "</option>";
				}
				$("#outercolorId").html(optionHtml);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("获取外饰颜色失败");
		},
		dataType: "json"
	});
}

//查询系列
function choseSeries() {
	var versionId = $("#versionId").val();
	$.ajax({
		type: "POST",
		url: "/module/gxfc/series_queryByVersion.do",
		data: {"seriesForm.versionId":versionId},
		success: function(data){
			$("#seriesId").html("<option value='0'>请选择系列</option>");
			if(data.message != undefined && data.message.length>0){
				var optionHtml = "<option value='0'>请选择系列</option>";
				for(var i=0; i<data.message.length; i++){
					optionHtml += "<option value='" + data.message[i].id + "'>" + data.message[i].name + "</option>";
				}
				$("#seriesId").html(optionHtml);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("获取系列失败");
		},
		dataType: "json"
	});
}

//查询排量
function choseEngines() {
	var seriesId = $("#seriesId").val();
	$.ajax({
		type: "POST",
		url: "/module/gxfc/engines_queryBySeries.do",
		data: {"enginesForm.seriesId":seriesId},
		success: function(data){
			$("#enginesId").html("<option value='0'>请选择排量</option>");
			if(data.message != undefined && data.message.length>0){
				var optionHtml = "<option value='0'>请选择排量</option>";
				for(var i=0; i<data.message.length; i++){
					optionHtml += "<option value='" + data.message[i].id + "'>" + data.message[i].name + "</option>";
				}
				$("#enginesId").html(optionHtml);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("获取车型失败");
		},
		dataType: "json"
	});
}

var provinces = new Array("北京市", "天津市", "上海市", "重庆市", "河北省", "山西省", "内蒙古", "辽宁省", "吉林省", "黑龙江省", "江苏省", "浙江省", "安徽省", "福建省", "江西省", "山东省", "河南省", "湖北省", "湖南省", "广东省", "广西", "海南省", "四川省", "贵州省", "云南省", "西藏", "陕西省", "甘肃省", "青海省", "宁夏", "新疆", "香港", "澳门", "台湾省");

function initProvince() {
	var optionHtml = "<option value='0'>请选择省份</option>";
	for (var i = 0; i < provinces.length; i++) {
		optionHtml += "<option value='" + provinces[i] + "'>" + provinces[i] + "</option>";
	}
	$("#province").html(optionHtml);
	$("#province").val($("#province_value").val());
	$("#province_value").remove();
}

/**
 * 根据name属性取得所有选中的checkbox的值用逗号分隔
 * @param name
 * @return
 */
function getCheckboxValuesByName(name){
	var str = 0;
    jQuery(":checkbox[name='" + name + "']:checked").each(function(){
    	str += parseInt(jQuery(this).val());
    });
    $("#procedures").val(str);
}

//生成标题
function genTitle() {
	var title = "";
	if ($("#modelyearId").val() != "" && $("#modelyearId").val() > 0) {
		title += $("#modelyearId").find("option:selected").text() + " ";
	}
	if ($("#brandId").val() != "" && $("#brandId").val() > 0) {
		title += $("#brandId").find("option:selected").text() + " ";
	}
	if ($("#seriesId").val() != "" && $("#seriesId").val() > 0) {
		title += $("#seriesId").find("option:selected").text() + " ";
	}
	if ($("#modelsId").val() != "" && $("#modelsId").val() > 0) {
		title += $("#modelsId").find("option:selected").text() + " ";
	}
	if ($("#enginesId").val() != "" && $("#enginesId").val() > 0) {
		title += $("#enginesId").find("option:selected").text() + " ";
	}
	if ($("#versionId").val() != "" && $("#versionId").val() > 0) {
		if (versionObj[$("#versionId").val()].showFlag == 1) {
			title += $("#versionId").find("option:selected").text();
		}
	}
	$("#title").val(title);
}

$(function() {
	initProvince();
});
</script>

</body>
</html>