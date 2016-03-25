<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="ict" uri="/ict-tags" %>
<%@taglib prefix="dict" uri="/dict-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <%@include file="/jsp/common.jsp" %>
	<script type="text/javascript" src="js/deptMgr.js"></script>
</head>
<body style="overflow:hidden">
<%@include file="/jsp/include/navigation.jsp"%>
<s:fielderror/>
<form id="saveForm" action="">
<s:hidden name="deptInfoForm.deptEntity.id.stringValue"/>
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
        <td>
            <table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
                <tr>
                    <td class="td_title" colspan="3" align="center">机构信息</td>
                </tr>
                 <tr>
                    <th width="20%" align="right">机构名称：</th>
                    <td width="35%">
                        <s:if test="%{deptInfoForm.editFlag}">
                            <s:textfield name="deptInfoForm.deptEntity.name.stringValue" id="name"
                                         size="20" cssClass="td03" maxlength="60" onblur="clearBlank(this);"/>
                                         <font class="redStar">*</font>
                        </s:if>
                        <s:if test="%{!deptInfoForm.editFlag}">
                            <s:property value="deptInfoForm.deptEntity.name.stringValue"/>
                        </s:if>
                    </td>
					<td width="45%">
							<div id="nameTip"></div>
					</td>
                </tr>
                <tr>
                    <th align="right">所属区域：</th>
                    <td>
                        <s:if test="%{deptInfoForm.editFlag}">
                            <ict:select beanContextId="provinceSelect" id="fromProvince" name="deptInfoForm.deptEntity.province" onchange="cityChange('fromProvince','fromCity');createjgbm();"></ict:select>
							<select id="fromCity"  name="deptInfoForm.deptEntity.city" onchange="areaChange('fromCity','fromArea');createjgbm();"></select>
							<select id="fromArea"  name="deptInfoForm.deptEntity.area" onchange="createjgbm();"></select>
							<input type="hidden" id="fromCity_hid"  value="<s:property value="deptInfoForm.deptEntity.city" />">
							<input type="hidden" id="fromArea_hid" value="<s:property value="deptInfoForm.deptEntity.area" />">
                            <font class="redStar">*</font>
                        </s:if>
                        <s:if test="%{!deptInfoForm.editFlag}">
                            <ict:property beanContextId="provinceSelect" value="deptInfoForm.deptEntity.province"></ict:property>
                            <ict:property beanContextId="provinceSelect" value="deptInfoForm.deptEntity.city"></ict:property>
                            <ict:property beanContextId="provinceSelect" value="deptInfoForm.deptEntity.area"></ict:property>
                        </s:if>
                    </td>
					<td>
							<div id="domainTip"></div>
					</td>
				</tr>
				<!--<tr>
                    <th align="right">机构编码：</th>
                    <td >
                        <s:if test="%{deptInfoForm.editFlag}">
                            <s:textfield name="deptInfoForm.deptEntity.jgbmpro" id="jgbmpro"
                                         size="20" cssClass="td03" maxlength="60" onblur="clearBlank(this);" readonly="true"/>
                            <s:textfield name="deptInfoForm.deptEntity.dwbm" id="dwbm"
                                         size="2" cssClass="td03" maxlength="2" onblur="clearBlank(this);" readonly="true"/>
                            <s:textfield name="deptInfoForm.deptEntity.bmsxm" id="bmsxm"
                                         size="4" cssClass="td03 numOnly" maxlength="4" onblur="clearBlank(this);"/>
                                         <font class="redStar">*</font>
                            <input type="hidden" id="jgbm_hid" name="deptInfoForm.deptEntity.jgbm.stringValue" value="<s:property value="deptInfoForm.deptEntity.jgbmp.stringValue" />">
                        </s:if>
                        <s:if test="%{!deptInfoForm.editFlag}">
                            <s:property value="deptInfoForm.deptEntity.jgbm.stringValue"/>
                        </s:if>
                    </td>
					<td>
							<div id="bmsxmTip"></div>
					</td>
                </tr>
                -->
                <!--<tr>
                    <th width="20%" align="right">机构代码：</th>
                    <td width="35%">
                        <s:if test="%{deptInfoForm.editFlag}">
                            <s:textfield name="deptInfoForm.deptEntity.code.stringValue" id="code"
                                         size="20" cssClass="td03" maxlength="60" onblur="clearBlank(this);"/>
                                         <font class="redStar">*</font>
                        </s:if>
                        <s:if test="%{!deptInfoForm.editFlag}">
                            <s:property value="deptInfoForm.deptEntity.code.stringValue"/>
                        </s:if>
                    </td>
					<td width="45%">
							<div id="codeTip"></div>
					</td>
                 </tr>
				--><tr>
                    <th align="right">机构简称：</th>
                    <td >
                        <s:if test="%{deptInfoForm.editFlag}">
                            <s:textfield name="deptInfoForm.deptEntity.jgjc.stringValue" id="jgjc"
                                         size="20" cssClass="td03" maxlength="60" onblur="clearBlank(this);"/>
                        </s:if>
                        <s:if test="%{!deptInfoForm.editFlag}">
                            <s:property value="deptInfoForm.deptEntity.jgjc.stringValue"/>
                        </s:if>
                    </td>
					<td>
							<div id="jgjcTip"></div>
					</td>
                </tr>
				<tr>
                    <th align="right">英文名称：</th>
                    <td >
                        <s:if test="%{deptInfoForm.editFlag}">
                            <s:textfield name="deptInfoForm.deptEntity.ywmc.stringValue" id="ywmc"
                                         size="20" cssClass="td03" maxlength="60" onblur="clearBlank(this);"/>
                        </s:if>
                        <s:if test="%{!deptInfoForm.editFlag}">
                            <s:property value="deptInfoForm.deptEntity.ywmc.stringValue"/>
                        </s:if>
                    </td>
					<td>
							<div id="ywmcTip"></div>
					</td>
                </tr>
				<!--<tr>
                    <th align="right">机构类别：</th>
                    <td >
                        <s:if test="%{deptInfoForm.editFlag}">
                            <dict:select codeType="sfb_sfxzjglb" name="deptInfoForm.deptEntity.jglb.stringValue" id="jglb"
                                    value="deptInfoForm.deptEntity.jglb.stringValue" onchange="createjgbm();"/>
                                         <font class="redStar">*</font>
                        </s:if>
                        <s:if test="%{!deptInfoForm.editFlag}">
                            <dict:property value="deptInfoForm.deptEntity.jglb.stringValue" codeType="sfb_sfxzjglb"/>
                        </s:if>
                    </td>
					<td>
							<div id="jglbTip"></div>
					</td>
                </tr>
				<tr>
                    <th align="right">机构行政级别：</th>
                    <td>
                        <s:if test="%{deptInfoForm.editFlag}">
                            <dict:select codeType="sfb_jgxzjb" name="deptInfoForm.deptEntity.rank.stringValue" id="rank"
                                    value="deptInfoForm.deptEntity.rank.stringValue"/>
                        </s:if>
                        <s:if test="%{!deptInfoForm.editFlag}">
                            <dict:property value="deptInfoForm.deptEntity.rank.stringValue" codeType="sfb_jgxzjb"/>
                        </s:if>
                    </td>
					<td>
							<div id="rankTip"></div>
					</td>
				</tr>
				<tr>
                    <th align="right">机构隶属层级：</th>
                    <td>
                        <s:if test="%{deptInfoForm.editFlag}">
                            <dict:select codeType="sfb_jglscj" name="deptInfoForm.deptEntity.jglscj.stringValue" id="jglscj"
                                    value="deptInfoForm.deptEntity.jglscj.stringValue" onchange="createjgbm();"/>
                        </s:if>
                        <s:if test="%{!deptInfoForm.editFlag}">
                            <dict:property value="deptInfoForm.deptEntity.jglscj.stringValue" codeType="sfb_jglscj"/>
                        </s:if>
                    </td>
					<td>
							<div id="jglscjTip"></div>
					</td>
				</tr>
				--><!--<tr>
                    <th align="right">上级司法行政主管机关：</th>
                    <td>
                        <s:if test="%{deptInfoForm.editFlag}">
                            <s:select list="deptInfoForm.deptList" emptyOption="true"
                                      listKey="key" listValue="value" id="sjsfxzzgjg"
                                      value="deptInfoForm.deptEntity.sjsfxzzgjg.stringValue"
                                      name="deptInfoForm.deptEntity.sjsfxzzgjg.stringValue">
                            </s:select>
                        </s:if>
                        <s:if test="%{!deptInfoForm.editFlag}">
                            <ict:property beanContextId="deptSelect" value="deptInfoForm.deptEntity.sjsfxzzgjg.stringValue"/>
                        </s:if>
                    </td>
					<td>
							<div id="sjsfxzzgjgTip"></div>
					</td>
                </tr>
                <tr>
                    <th align="right">上级司法行政主管部门：</th>
                    <td>
                        <s:if test="%{deptInfoForm.editFlag}">
                            <s:select list="deptInfoForm.deptList" emptyOption="true"
                                      listKey="key" listValue="value" id="sjsfxzzgbm"
                                      value="deptInfoForm.deptEntity.sjsfxzzgbm.stringValue"
                                      name="deptInfoForm.deptEntity.sjsfxzzgbm.stringValue">
                            </s:select>
                        </s:if>
                        <s:if test="%{!deptInfoForm.editFlag}">
                        	<ict:property beanContextId="deptSelect" value="deptInfoForm.deptEntity.sjsfxzzgbm.stringValue"/>
                        </s:if>
                    </td>
					<td>
							<div id="sjsfxzzgbmTip"></div>
					</td>
                </tr>
                --><tr>
                    <th align="right">责任人：</th>
                    <td >
                        <s:if test="%{deptInfoForm.editFlag}">
                            <s:textfield name="deptInfoForm.deptEntity.manager.stringValue" id="manager"
                                         size="20" cssClass="td03" maxlength="60" onblur="clearBlank(this);"/>
                                         <font class="redStar">*</font>
                        </s:if>
                        <s:if test="%{!deptInfoForm.editFlag}">
                            <s:property value="deptInfoForm.deptEntity.manager.stringValue"/>
                        </s:if>
                    </td>
					<td>
							<div id="managerTip"></div>
					</td>
                </tr>
				<tr>
                    <th align="right">电子邮箱：</th>
                    <td >
                        <s:if test="%{deptInfoForm.editFlag}">
                            <s:textfield name="deptInfoForm.deptEntity.dzyx.stringValue" id="dzyx"
                                         size="20" cssClass="td03" maxlength="60" onblur="clearBlank(this);"/>
                        </s:if>
                        <s:if test="%{!deptInfoForm.editFlag}">
                            <s:property value="deptInfoForm.deptEntity.dzyx.stringValue"/>
                        </s:if>
                    </td>
					<td>
							<div id="dzyxTip"></div>
					</td>
                </tr>
                <tr>
                    <th align="right">联系电话：</th>
                    <td >
                        <s:if test="%{deptInfoForm.editFlag}">
                            <s:textfield name="deptInfoForm.deptEntity.phoneNo.stringValue" id="phoneNo"
                                         size="20" cssClass="td03 telOnly" maxlength="20" onblur="clearBlank(this);"/>
                        </s:if>
                        <s:if test="%{!deptInfoForm.editFlag}">
                            <s:property value="deptInfoForm.deptEntity.phoneNo.stringValue"/>
                        </s:if>
                    </td>
					<td>
							<div id="phoneNoTip"></div>
					</td>
				</tr>
				<tr>
                    <th align="right">传真号码：</th>
                    <td >
                        <s:if test="%{deptInfoForm.editFlag}">
                            <s:textfield name="deptInfoForm.deptEntity.faxNo.stringValue" id="faxNo"
                                         size="20" cssClass="td03 telOnly" maxlength="60" onblur="clearBlank(this);"/>
                        </s:if>
                        <s:if test="%{!deptInfoForm.editFlag}">
                            <s:property value="deptInfoForm.deptEntity.faxNo.stringValue"/>
                        </s:if>
                    </td>
					<td>
							<div id="faxNoTip"></div>
					</td>
                </tr>
                <tr>
                    <th align="right">单位地址：</th>
                    <td >
                        <s:if test="%{deptInfoForm.editFlag}">
                            <s:textfield name="deptInfoForm.deptEntity.address.stringValue" id="address"
                                         size="20" cssClass="td03" maxlength="60" onblur="clearBlank(this);"/>
                        </s:if>
                        <s:if test="%{!deptInfoForm.editFlag}">
                            <s:property value="deptInfoForm.deptEntity.address.stringValue"/>
                        </s:if>
                    </td>
					<td>
							<div id="addressTip"></div>
					</td>
				</tr>
				<tr>
                    <th align="right">邮编：</th>
                    <td >
                        <s:if test="%{deptInfoForm.editFlag}">
                            <s:textfield name="deptInfoForm.deptEntity.postcode.stringValue" id="postcode"
                                         size="20" cssClass="td03 numOnly" maxlength="6" onblur="clearBlank(this);"/>
                        </s:if>
                        <s:if test="%{!deptInfoForm.editFlag}">
                            <s:property value="deptInfoForm.deptEntity.postcode.stringValue"/>
                        </s:if>
                    </td>
					<td>
							<div id="postcodeTip"></div>
					</td>
                </tr>
                <tr>
                    <th align="right">上级机构：</th>
                    <td>
                        <s:if test="%{deptInfoForm.editFlag}">
                            <s:select list="deptInfoForm.deptList"
                                      listKey="key" listValue="value" id="upperDept"
                                      value="deptInfoForm.deptEntity.upperDept.stringValue"
                                      name="deptInfoForm.deptEntity.upperDept.stringValue">
                            </s:select>
                        </s:if>
                        <s:if test="%{!deptInfoForm.editFlag}">
                            <s:property value="deptInfoForm.deptEntity.upperDeptName"/>
                        </s:if>
                    </td>
					<td>
							<div id="upperDeptTip"></div>
					</td>
                </tr>
                <tr>
                    <s:if test="%{deptInfoForm.editFlag}">
                        <td class="bottom" align="center" colspan="3">
                            <input type="button" onclick="_save();" class="button" value="保存"
                                   onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'"/>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <input type="button" onclick="history.back();" class="button" value="返回"
                                   onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'"/>
                        </td>
                    </s:if>
                    <s:if test="%{!deptInfoForm.editFlag}">
                        <td class="bottom" align="center" colspan="3">
                            <input type="button" onclick="history.back();" class="button" value="返回"
                                   onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'"/>
                        </td>
                    </s:if>
                </tr>
            </table>
        </td>
    </tr>
</table>
</form>

<script type="text/javascript">
$(function(){
	$("#fromProvince").change();
})
$.formValidator.initConfig( {
	formid :"saveForm",
	onerror : function(msg) {
		alert(msg)
	},
	onsuccess : function() {
		return true;
	}
});

$("#code").formValidator( {
	onshow :"请输入所属单位代码",
	onfocus :"请输入1~60位数字",
	oncorrect :"输入合法"
}).regexValidator( {
	regexp :"^[0-9]{1,60}$",
	onerror :"所属单位代码输入格式错误"
}).inputValidator( {
	min :1,
	max :60,
	onerror :"请确认所属单位代码长度"
});

//$("#bmsxm").formValidator( {
//	onshow :"请输入机构编码顺序码",
//	onfocus :"请输入4位数字(如0000-9999)",
//	oncorrect :"输入合法"
//}).regexValidator( {
//	regexp :"^[0-9]{4,4}$",
//	onerror :"顺序码输入格式错误"
//}).inputValidator( {
//	min :4,
//	max :4,
//	onerror :"请确认顺序码长度"
//});

$("#name").formValidator( {
	onshow :"请输入所属单位名称",
	onfocus :"最多60个半角英文字母、数字和下划线（20个汉字），可输入中文",
	oncorrect :"输入合法"
}).regexValidator( {
	regexp :"^[\u4E00-\u9FA5|A-Z|a-z|0-9|_]{1,60}$",
	onerror :"所属单位名称输入格式或长度错误"
}).inputValidator( {
	min :1,
	max :60,
	onerror :"请确认所属单位名称长度"
});

$("#manager").formValidator( {
	onshow :"请输入所属单位负责人",
	onfocus :"最多60个半角英文字母、数字和下划线（20个汉字），可输入中文",
	oncorrect :"输入合法"
}).regexValidator( {
	regexp :"^[\u4E00-\u9FA5|A-Z|a-z|0-9|_]{1,60}$",
	onerror :"所属单位负责人输入格式或长度错误"
}).inputValidator( {
	min :1,
	max :60,
	onerror :"请确认所属单位负责人长度"
});

$("#phoneNo").formValidator( {
	empty :true,
	onshow :"请输入您的联系电话",
	onfocus :"可输入固定电话（区号-号码）或者11位手机号",
	oncorrect :"谢谢您的合作"
}).regexValidator( {
	regexp :"^([0-9]{3,4}-[0-9]{7,8})$|^(1[3|5|8]{1}[0-9]{9})$",
	onerror :"联系电话格式不正确，请输入固定电话（区号-号码）或者11位手机号"
});

$("#faxNo").formValidator( {
	empty :true,
	onshow :"请输入传真",
	onfocus :"只能输入固定电话（区号-号码）",
	oncorrect :"输入正确"
}).regexValidator( {
	regexp :"^([0-9]{3,4}-[0-9]{7,8})$",
	onerror :"传真格式不正确，请输入固定电话（区号-号码）"
});
$("#dzyx").formValidator( {
		empty :true,
		onshow :"请输入邮箱",
		onfocus :"邮箱长度6-60个字符",
		oncorrect :"输入合法",
		forcevalid :false
	}).inputValidator( {
		min :6,
		max :60,
		onerror :"你输入的邮箱长度非法，请确认"
	}).regexValidator({
		regexp:"email",datatype:"enum",onerror:"email格式不正确"
	});
$("#address").formValidator( {
	onshow :"请输入所属单位地址",
	onfocus :"最多60个半角英文字母、数字和下划线、横线（20个汉字），可输入中文",
	oncorrect :"输入合法"
}).regexValidator( {
	regexp :"^[\u4E00-\u9FA5|A-Z|a-z|0-9|_|-]{0,60}$",
	onerror :"所属单位地址输入格式或长度错误"
}).inputValidator( {
	min :0,
	max :60,
	onerror :"请确认所属单位地址长度"
});

$("#postcode").formValidator({
	empty :true,
	onshow:"请输入邮编",
	onfocus:"由6位数字组成",
	oncorrect:"谢谢你的合作，邮编输入正确"
}).regexValidator({
	regexp:"zipcode",
	datatype:"enum",
	onerror:"邮编格式不正确"
});

</script>
</body>
</html>