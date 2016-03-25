<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include  file="/portal/header.jsp"%>

    <link rel="stylesheet" href="/portal/css/style.css">
</head>
<body>
<%@ include  file="/portal/inc.jsp"%>
    <div class="comm-white-ctn">
        <div class="container">
            <%@ include  file="/portal/user/left.jsp"%>
            <!-- 用户中心主内容区 -->
            <div class="mod-user-main">
                <div class="mod-user-section-title">个人关注</div>
                <div class="mod-user-form">
                     <form id="userAttentionForm" action="/userportal_saveUserAttention.do" method="post">
                        <div class="form-group">
                           <label class="label" for="">品牌拼音：</label>
                            <div class="input">
                                <span class="select">
                                    <select id="pinyin" onchange="choseBrand();">
                                        <option value="A">A</option>
                                        <option value="B">B</option>
                                        <option value="C">C</option>
                                        <option value="D">D</option>
                                        <option value="E">E</option>
                                        <option value="F">F</option>
                                        <option value="G">G</option>
                                        <option value="H">H</option>
                                        <option value="I">I</option>
                                        <option value="J">J</option>
                                        <option value="K">K</option>
                                        <option value="L">L</option>
                                        <option value="M">M</option>
                                        <option value="N">N</option>
                                        <option value="O">O</option>
                                        <option value="P">P</option>
                                        <option value="Q">Q</option>
                                        <option value="R">R</option>
                                        <option value="S">S</option>
                                        <option value="T">T</option>
                                        <option value="U">U</option>
                                        <option value="V">V</option>
                                        <option value="W">W</option>
                                        <option value="X">X</option>
                                        <option value="Y">Y</option>
                                        <option value="Z">Z</option>
                                    </select>
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="label" for="">选择品牌：</label>
                            <div class="input">
                                <span class="select" style="width: 120px;">
                                    <select id="brandId" onchange="choseVersion();">
                                        <option value="">请选择</option>
                                    </select>
                                </span>
                            </div>
                        </div>                      
                        <div class="form-group">
                            <label class="label" for="">选择版本：</label>
                            <div class="input">
                                <span class="select" style="width: 120px;">
                                    <select id="versionId" onchange="choseSeries();">
                                        <option value="">请选择</option>
                                    </select>
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="label" for="">选择系列：</label>
                            <div class="input">
                                <span class="select" style="width: 160px;">
                                    <select id="seriesId" onchange="choseModels();">
                                        <option value="">请选择</option>
                                    </select>
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="label" for="">选择车型：</label>
                            <div class="input">
                                <span class="select" style="width: 220px;">
                                    <select name="userAttentionForm.modelsId" id="modelsId">
                                        <option value="">请选择</option>
                                    </select>
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="label">价格区间：</label>
							<div class="input">
                                <input id="startPrice" style="width: 80px;" type="text" onkeyup="this.value=this.value.replace(/^\D+(\.\D{2})?$/g,'')" onafterpaste="this.value=this.value.replace(/^\D+(\.\D{2})?$/g,'')"> 万元
                                &nbsp;-&nbsp;
                                <input id="endPrice" style="width: 80px;" type="text" onkeyup="this.value=this.value.replace(/^\D+(\.\D{2})?$/g,'')" onafterpaste="this.value=this.value.replace(/^\D+(\.\D{2})?$/g,'')"> 万元
                                <input type="hidden" id="_startPrice" name="userAttentionForm.startPrice" />
                                <input type="hidden" id="_endPrice" name="userAttentionForm.endPrice" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="label">首款区间：</label>
							<div class="input">
                                <span class="select" style="width: 100px;">
                                    <select name="userAttentionForm.startDepositRatio" id="startDepositRatio">
                                        <option value="0">请选择</option>
                                        <option value="1">10%</option>
                                        <option value="2">20%</option>
                                        <option value="3">30%</option>
                                        <option value="4">40%</option>
                                        <option value="5">50%</option>
                                        <option value="6">60%</option>
                                        <option value="7">70%</option>
                                        <option value="8">80%</option>
                                        <option value="9">90%</option>
                                        <option value="10">100%</option>
                                    </select>
                                </span>
                                &nbsp;-&nbsp;
                                <span class="select" style="width: 100px;">
                                    <select name="userAttentionForm.endDepositRatio" id="endDepositRatio">
                                        <option value="0">请选择</option>
                                        <option value="1">10%</option>
                                        <option value="2">20%</option>
                                        <option value="3">30%</option>
                                        <option value="4">40%</option>
                                        <option value="5">50%</option>
                                        <option value="6">60%</option>
                                        <option value="7">70%</option>
                                        <option value="8">80%</option>
                                        <option value="9">90%</option>
                                        <option value="10">100%</option>
                                    </select>
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <span class="label"></span>
                            <div class="actions">
								<button id="saveButton" type="button" class="button button-primary" onclick="saveAttention();">确认</button>
								<button class="button button-secondary" onclick="history.back();">取消</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <!-- ./用户中心主内容区 -->
        </div>
    </div>

 <%@ include  file="/portal/footer.jsp"%>

<script src="/portal/js/vendor/jquery.js"></script>
<script src="/portal/vendor/jquery.form.js"></script>
<script src="/portal/js/app.js"></script>
<script>
$(document).ready(function () {
	choseBrand();
});

function saveAttention() {
	if ($("#modelsId").val() == "" || $("#modelsId").val() == 0) {
		alert("请选择车型");
		return false;
	}
	if ($("#startPrice").val() == "" || $("#endPrice").val() == "") {
		alert("请输入价格区间");
		return false;
	}
	if (parseFloat($("#startPrice").val()) > parseFloat($("#endPrice").val())) {
		alert("请输入正确的价格区间");
		return false;
	}
	if ($("#startDepositRatio").val() == "" || $("#startDepositRatio").val() == 0 || $("#endDepositRatio").val() == "" || $("#endDepositRatio").val() == 0) {
		alert("请选择首款区间");
		return false;
	}
	if (parseInt($("#startDepositRatio").val()) > parseInt($("#endDepositRatio").val())) {
		alert("请选择正确的首款区间");
		return false;
	}

	$("#saveButton").attr("disabled", "disabled");
	var startPrice = parseFloat($("#startPrice").val()) * 10000;
	$("#_startPrice").val(parseInt(startPrice));
	var endPrice = parseFloat($("#endPrice").val()) * 10000;
	$("#_endPrice").val(parseInt(endPrice));

	$.ajax({
		type: "POST",
		url: "/userportal_saveUserAttention.do",
		data: $('#userAttentionForm').serialize(),
		success: function(data){
			alert(data.message);
			if (data.codeid == 0) {
				document.location.href = "/userportal_userAttention.do";
			} else {
				$("#saveButton").removeAttr("disabled");
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("获取数据失败");
			$("#saveButton").removeAttr("disabled");
		},
		dataType: "json"
	});
}

//查询品牌
function choseBrand() {
	var pinyin = $("#pinyin").val();
	$.ajax({
		type: "POST",
		url: "/module/gxfc/brand_queryBypinyin.do",
		data: {"brandForm.pinyin":pinyin},
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
function choseVersion() {
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
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("获取版本失败");
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
</script>
</body>
</html>
