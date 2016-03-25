<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/volunteerMgr.js"></script>
</head>
<body>
<%@include file="/jsp/include/navigation.jsp"%>
<s:fielderror/>
<s:form id="volunteerBean" name="volunteerBean" action="%{actionName}" onsubmit="return false">
<s:hidden id="volunteerForm_id" name="volunteerBean.id"></s:hidden>
<s:hidden id="volunteerForm_deptId" name="volunteerBean.deptId"></s:hidden>
<s:hidden id="volunteerForm_useFlag" name="volunteerBean.useFlag"></s:hidden>
<input id="contextPath" type="hidden" value="<%=request.getContextPath() %>"/>
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
        <tr>
          <td class="td_title" colspan="6" align="center">心理矫治志愿者登记表</td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">全国志愿者注册编号：</th>
          <td width="35%">
            <s:textfield name="volunteerBean.registerId" id="volunteerForm_registerId" cssClass="input2" size="40" onblur="clearBlank(this)"></s:textfield>
            <font class="redStar">*</font>
            <div id="volunteerForm_registerIdTip"></div>
          </td>
          <th class="tb_lable" width="15%" align="right" rowspan="7">相 &nbsp;&nbsp;片：</th>
          <td id="picTd" width="50%" rowspan="7">
          	<s:if test="volunteerBean.picPath != null && volunteerBean.picPath != ''">
          		<img src="<%=request.getContextPath() %>/upload/<s:property value='volunteerBean.picPath'/>" width="90" height="120" onload="picsize(this)"/>
          	</s:if>
          	<s:else>
          		<img src="<%=request.getContextPath() %>/upload/default.gif" width="90" height="120" onload="picsize(this)"/>
          	</s:else>
          </td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">咨询师证书编号：</th>
          <td width="35%">
            <s:textfield name="volunteerBean.consultantCertificateId" id="volunteerForm_consultantCertificateId" cssClass="input2" size="40" onblur="clearBlank(this)"></s:textfield>
            <font class="redStar">*</font>
            <div id="volunteerForm_consultantCertificateIdTip"></div>
          </td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">姓 &nbsp;&nbsp;名：</th>
          <td width="35%">
            <s:textfield name="volunteerBean.name" id="volunteerForm_name" cssClass="input2" size="40" onblur="clearBlank(this)"></s:textfield>
            <font class="redStar">*</font>
            <div id="volunteerForm_nameTip"></div>
          </td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">性 &nbsp;&nbsp;别：</th>
          <td width="35%">
          	<dict:select id="volunteerForm_sex" name="volunteerBean.sex" codeType="common-sex" emptyOption="false" onselect="volunteerBean.sex" onblur="clearBlank(this)"></dict:select>
          	<font class="redStar">*</font>
          </td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">身份证号：</th>
          <td width="35%">
            <s:textfield name="volunteerBean.idNum" id="volunteerForm_idNum" cssClass="input2" size="40" onblur="clearBlank(this)"></s:textfield>
            <div id="volunteerForm_idNumTip"></div>
          </td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">出生年月：</th>
          <td width="35%">
          	<input id="volunteerForm_birthday" name="volunteerBean.birthday" value='<s:date format="yyyy-MM-dd" name="%{volunteerBean.birthday}"/>' size="25" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate"></input>
          </td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">心理矫治志愿者服务证书编号：</th>
          <td width="35%">
            <s:textfield name="volunteerBean.serviceCertificateId" id="volunteerForm_serviceCertificateId" cssClass="input2" size="40" onblur="clearBlank(this)"></s:textfield>
            <div id="volunteerForm_serviceCertificateIdTip"></div>
          </td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">咨询师等级：</th>
          <td width="35%">
            <dict:select id="volunteerForm_rank" name="volunteerBean.rank" codeType="tb_volunteer-rank" onselect="volunteerBean.rank" onblur="clearBlank(this)"></dict:select>
          </td>
          <th class="tb_lable" width="15%" align="right">专 &nbsp;&nbsp;业：</th>
          <td width="35%">
            <s:textfield name="volunteerBean.specialty" id="volunteerForm_specialty" cssClass="input2" size="40" onblur="clearBlank(this)"></s:textfield>
            <div id="volunteerForm_specialtyTip"></div>
          </td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">籍 &nbsp;&nbsp;贯：</th>
          <td width="35%">
            <s:textfield name="volunteerBean.nativePlace" id="volunteerForm_nativePlace" cssClass="input2" size="40" onblur="clearBlank(this)"></s:textfield>
            <div id="volunteerForm_nativePlaceTip"></div>
          </td>
          <th class="tb_lable" width="15%" align="right">政治面貌：</th>
          <td width="35%">
          	<s:textfield name="volunteerBean.politicalStatus" id="volunteerForm_politicalStatus" cssClass="input2" size="40" onblur="clearBlank(this)"></s:textfield>
          	<div id="volunteerForm_politicalStatusTip"></div>
          </td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">民 &nbsp;&nbsp;族：</th>
          <td width="35%">
            <s:textfield name="volunteerBean.nation" id="volunteerForm_nation" cssClass="input2" size="40" onblur="clearBlank(this)"></s:textfield>
            <div id="volunteerForm_nationTip"></div>
          </td>
          <th class="tb_lable" width="15%" align="right">联系电话：</th>
          <td width="35%">
          	<s:textfield name="volunteerBean.phoneNo" id="volunteerForm_phoneNo" cssClass="input2" size="40" onblur="clearBlank(this)"></s:textfield>
          	<div id="volunteerForm_phoneNoTip"></div>
          </td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">工作单位：</th>
          <td width="35%">
            <s:textfield name="volunteerBean.workUnit" id="volunteerForm_workUnit" cssClass="input2" size="40" onblur="clearBlank(this)"></s:textfield>
            <div id="volunteerForm_workUnitTip"></div>
          </td>
          <th class="tb_lable" width="15%" align="right">职 &nbsp;&nbsp;务：</th>
          <td width="35%">
          	<s:textfield name="volunteerBean.duty" id="volunteerForm_duty" cssClass="input2" size="40" onblur="clearBlank(this)"></s:textfield>
          	<div id="volunteerForm_dutyTip"></div>
          </td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">职 &nbsp;&nbsp;称：</th>
          <td width="35%">
            <s:textfield name="volunteerBean.professionalTitle" id="volunteerForm_professionalTitle" cssClass="input2" size="40" onblur="clearBlank(this)"></s:textfield>
            <div id="volunteerForm_professionalTitleTip"></div>
          </td>
          <th class="tb_lable" width="15%" align="right">通讯地址：</th>
          <td width="35%">
          	<s:textfield name="volunteerBean.address" id="volunteerForm_address" cssClass="input2" size="40" onblur="clearBlank(this)"></s:textfield>
          	<div id="volunteerForm_addressTip"></div>
          </td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">学 &nbsp;&nbsp;历：</th>
          <td width="35%">
          	<dict:select codeType="common-edu_bg" id="volunteerForm_eduBg" name="volunteerBean.eduBg" emptyOption="true" value="volunteerBean.eduBg" onblur="clearBlank(this)"></dict:select>
          </td>
          <th class="tb_lable" width="15%" align="right">E-mail：</th>
          <td width="35%">
          	<s:textfield name="volunteerBean.email" id="volunteerForm_email" cssClass="input2" size="40" onblur="clearBlank(this)"></s:textfield>
          	<div id="volunteerForm_emailTip"></div>
          </td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">职业分类：</th>
          <td width="35%">
            <s:textfield name="volunteerBean.workType" id="volunteerForm_workType" cssClass="input2" size="40" onblur="clearBlank(this)"></s:textfield>
            <div id="volunteerForm_workTypeTip"></div>
          </td>
          <th class="tb_lable" width="15%" align="right">邮政编码：</th>
          <td width="35%">
          	<s:textfield name="volunteerBean.postcode" id="volunteerForm_postcode" cssClass="input2" size="40" onblur="clearBlank(this)"></s:textfield>
          	<div id="volunteerForm_postcodeTip"></div>
          </td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">个人简历：</th>
          <td width="75%" colspan="3">
          	<s:textarea name="volunteerBean.resume" id="volunteerForm_resume" cssClass="input2" cols="65" rows="4" onblur="clearBlank(this)"></s:textarea>
          	<div id="volunteerForm_resumeTip"></div>
          </td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">各类培训和专业资格：</th>
          <td width="75%" colspan="3">
          	<s:textarea name="volunteerBean.trainQualification" id="volunteerForm_trainQualification" cssClass="input2" cols="65" rows="4" onblur="clearBlank(this)"></s:textarea>
          	<div id="volunteerForm_trainQualificationTip"></div>
          </td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">个人特长和兴趣爱好：</th>
          <td width="75%" colspan="3">
          	<s:textarea name="volunteerBean.interest" id="volunteerForm_interest" cssClass="input2" cols="65" rows="4" onblur="clearBlank(this)"></s:textarea>
          	<div id="volunteerForm_interestTip"></div>
          </td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">志愿者服务经历：</th>
          <td width="75%" colspan="3">
          	<s:textarea name="volunteerBean.serviceExperience" id="volunteerForm_serviceExperience" cssClass="input2" cols="65" rows="4" onblur="clearBlank(this)"></s:textarea>
          	<div id="volunteerForm_serviceExperienceTip"></div>
          </td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">主要研究成果：</th>
          <td width="75%" colspan="3">
          	<s:textarea name="volunteerBean.researchFindings" id="volunteerForm_researchFindings" cssClass="input2" cols="65" rows="4" onblur="clearBlank(this)"></s:textarea>
          	<div id="volunteerForm_researchFindingsTip"></div>
          </td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">相 &nbsp;&nbsp;片：</th>
          <td width="75%" colspan="3">
          	<s:textfield name="volunteerBean.picPath" id="volunteerForm_picPath" size="40" cssClass="input2" onblur="clearBlank(this)" readonly="true"></s:textfield>
          	<input id="pic1" type="button" onclick="javascript:upload('pic');" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="上传"/>
          	<div id="pic1Tip"></div>
          </td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">志愿申请书：</th>
          <td width="75%" colspan="3">
          	<s:textfield name="volunteerBean.reqPath" id="volunteerForm_reqPath" size="40" cssClass="input2" onblur="clearBlank(this)" readonly="true"></s:textfield>
          	<input id="pic2" type="button" onclick="javascript:upload('req');" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="上传"/>
          	<div id="pic2Tip"></div>
          	<span id="reqSpan">
          	  <s:if test="volunteerBean.reqPath != null && volunteerBean.reqPath != ''">
          			<a href="<%=request.getContextPath()%>/upload/<s:property value='volunteerBean.reqPath'/>" target="_blank">志愿申请书查看</a>&nbsp;&nbsp;&nbsp;<div id="saveTip"> <font color="red" size="2">直接在浏览器打开可能存在乱码 ，建议您使用鼠标右键  "目标另存为" </font><span  onclick="javascript:document.getElementById('saveTip').style.display='none';"><b>关闭</b> </span></div>
          	  </s:if>
          	</span>
          </td>
        </tr>
        <tr>
          <td class="bottom" align="center" colspan="4"><div align="center">
			<s:if test="%{actionName == 'volunteer_save'}">
				<input type="button" onclick="javascript:save_volunteer(this);" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="增加"/>
			</s:if><s:else>
				<input type="button" onclick="javascript:update_volunteer(this);" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="保存"/>
			</s:else>
			<input type="button" onclick="javascript:history.back();" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="取消"/>
          </div></td>
          </tr>
      </table></td>
    </tr>
  </table>
</s:form>
<div id="dialog" title="文件上传"></div>
<script type="text/javascript">
$.formValidator.initConfig({formid:"volunteerBean",onerror:function(msg){alert(msg)},onsuccess:function(){return true;}});
$("#volunteerForm_registerId").formValidator({
		onshow:"请输入全国志愿者注册编号",
		onfocus:"全国志愿者注册编号由15位或18位身份证号组成",
		oncorrect:"输入合法"
	}).regexValidator({
		regexp:"idcard",
		datatype:"enum",
		onerror:"全国志愿者注册编号格式错误"
	});
$("#volunteerForm_consultantCertificateId").formValidator({onshow:"请输入咨询师证书编号",onfocus:"咨询师证书编号不能为空",oncorrect:"咨询师证书编号正确"}).inputValidator({min:1,max:64,onerror:"咨询师证书编号长度不能超过64"}).regexValidator({regexp:"^[0-9]{1,64}$",onerror :"请输入正确的咨询师证书编号"});
$("#volunteerForm_name").formValidator({onshow:"请输入志愿者姓名",onfocus:"志愿者姓名不能为空",oncorrect:"志愿者姓名正确"}).inputValidator({min:1,max:60,onerror:"姓名长度20个汉字"});
$("#volunteerForm_idNum").formValidator({
		empty :true,
		onshow:"请输入正确的身份证号",
		onfocus:"身份证号由15位或18位数字组成",
		oncorrect:"身份证号正确"
	}).regexValidator({
		regexp:"idcard",
		datatype:"enum",
		onerror:"身份证号格式不正确"
	});
$("#volunteerForm_serviceCertificateId").formValidator({oncorrect:"心理矫治志愿者服务证书编号正确"}).inputValidator({min:0,max:64,onerror:"心理矫治志愿者服务证书编号长度不能超过64位"}).regexValidator({regexp:"^[0-9]{0,64}$",onerror :"请输入正确的心理矫治志愿者服务证书编号"});
$("#volunteerForm_specialty").formValidator({oncorrect:"专业正确"}).inputValidator({min:0,max:60,onerror:"专业长度不能超过20个汉字"});
$("#volunteerForm_nativePlace").formValidator({oncorrect:"籍贯正确"}).inputValidator({min:0,max:60,onerror:"籍贯长度不能超过20个汉字"});
$("#volunteerForm_politicalStatus").formValidator({oncorrect:"政治面貌正确"}).inputValidator({min:0,max:60,onerror:"政治面貌长度不能超过20个汉字"});
$("#volunteerForm_nation").formValidator({oncorrect:"民族正确"}).inputValidator({min:0,max:60,onerror:"民族长度不能超过20个汉字"});
$("#volunteerForm_phoneNo").formValidator({oncorrect:"联系电话正确"}).inputValidator({min:0,max:24,onerror:"联系电话长度不能超过24位"}).regexValidator({regexp:"^[0-9]{0,24}$",onerror :"请输入正确的联系电话"});
$("#volunteerForm_workUnit").formValidator({oncorrect:"工作单位正确"}).inputValidator({min:0,max:60,onerror:"工作单位长度不能超过20个汉字"});
$("#volunteerForm_duty").formValidator({oncorrect:"职务正确"}).inputValidator({min:0,max:60,onerror:"职务长度不能超过20个汉字"});
$("#volunteerForm_professionalTitle").formValidator({oncorrect:"职称正确"}).inputValidator({min:0,max:60,onerror:"职称长度不能超过20个汉字"});
$("#volunteerForm_address").formValidator({oncorrect:"通讯地址正确"}).inputValidator({min:0,max:510,onerror:"通讯地址长度不能超过170个汉字"});
$("#volunteerForm_email").formValidator( {
				empty :true,
				onshow :"请输入邮箱",
				onfocus :"邮箱长度0-60个字符",
				oncorrect :"输入合法",
				forcevalid :false
			})
			.inputValidator( {
				min :0,
				max :60,
				onerror :"你输入的邮箱长度非法，请确认"
			})
			.regexValidator(
					{
						regexp:"email",datatype:"enum",onerror:"E-mail格式不正确"
					});
$("#volunteerForm_workType").formValidator({oncorrect:"职业分类正确"}).inputValidator({min:0,max:60,onerror:"职业分类长度不能超过20个字"});
$("#volunteerForm_postcode").formValidator({
		empty :true,
		onshow:"请输入邮编",
		onfocus:"由6位数字组成",
		oncorrect:"邮编输入正确"
	}).regexValidator({
		regexp:"zipcode",
		datatype:"enum",
		onerror:"邮编格式不正确"
	});

$("#volunteerForm_resume").formValidator({oncorrect:"个人简历正确"}).inputValidator({min:0,max:510,onerror:"个人简历长度不能超过170个字"});
$("#volunteerForm_trainQualification").formValidator({oncorrect:"各类培训和专业资格正确"}).inputValidator({min:0,max:510,onerror:"各类培训和专业资格长度不能超过170个汉字"});
$("#volunteerForm_interest").formValidator({oncorrect:"个人特长和兴趣爱好正确"}).inputValidator({min:0,max:510,onerror:"个人特长和兴趣爱好长度不能超过170个汉字"});
$("#volunteerForm_serviceExperience").formValidator({oncorrect:"志愿者服务经历正确"}).inputValidator({min:0,max:510,onerror:"志愿者服务经历长度不能超过170个汉字"});
$("#volunteerForm_researchFindings").formValidator({oncorrect:"主要研究成果正确"}).inputValidator({min:0,max:510,onerror:"主要研究成果长度不能超过170个汉字"});
$("#pic1").formValidator( {
	onshow :"请上传图片",
	onfocus :"请选择JPG、JEPG等图片文件，大小不要超过500K",
	oncorrect :"路径合法"
});
$("#pic2").formValidator( {
	onshow :"请上传志愿申请书",
	onfocus :"请选择word、excel或.txt文件，大小不要超过500K",
	oncorrect :"路径合法"
});
</script>
</body>
</html>