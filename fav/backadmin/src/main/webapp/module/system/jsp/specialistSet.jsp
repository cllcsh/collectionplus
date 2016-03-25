<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/specialistMgr.js"></script>

<script type="text/javascript">
</script>
</head>
<body>
<%@include file="/jsp/include/navigation.jsp"%>
<s:fielderror />
<s:form id="specialistForm" name="specialistForm" action="%{actionName}">
	<s:hidden name="specialistForm.id"></s:hidden>
	<table class="bg" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td>
				<table class="tb_add" width="100%" border="0" cellpadding="1"
					cellspacing="1">
					<tr>
						<td class="td_title" colspan="3" align="center">
							矫正专家信息
						</td>
					</tr>
					<tr>
						<th class="tb_lable" width="20%" align="right">相 &nbsp;&nbsp;片</th>
		          		<td id="picTd">
		          			<s:if test="specialistForm.picPath != null && specialistForm.picPath != ''">
		          				<img src="<%=request.getContextPath() %>/upload/<s:property value='specialistForm.picPath'/>" width="90" height="120"/>
		          			</s:if>
		          			<s:else>
		          				暂未提供照片，请先上传！
		          			</s:else>
							<input type="button" onclick="javascript:uploadpic('pic');" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="上传"/>
		          		</td>
						<td width="45%">
							<div id="picTdTip"></div>
						</td>	
					</tr>
					<tr>
						<th class="tb_lable" width="20%" align="right">
							证书编号：
						</th>
						<td width="35%">
							<s:textfield name="specialistForm.certificateId" cssClass="input2" size="40" maxlength="60" onblur="clearBlank(this);"></s:textfield>
						</td>
						<td width="45%">
							<div id="specialistForm_specialistForm_certificateIdTip"></div>
						</td>
					</tr>
					<tr>
						<th align="right">
							名称：<font class="redStar">*</font>
						</th>
						<td>
							<s:textfield name="specialistForm.name" cssClass="input2" size="40" onblur="clearBlank(this);"></s:textfield>
						</td>
						<td>
							<div id="specialistForm_specialistForm_nameTip"></div>
						</td>
					</tr>
					<tr>
						<th align="right">
							性别：<font class="redStar">*</font>
						</th>
						<td>
							<dict:select name="specialistForm.sex" codeType="common-sex" />
							<%--<s:if test="specialistForm.sex == 2">
							    <input type="radio" name="specialistForm.sex" value="1" >男
								<input type="radio" name="specialistForm.sex" value="2" checked>女
							</s:if>
							<s:else>
								<input type="radio" name="specialistForm.sex" value="1" checked>男
								<input type="radio" name="specialistForm.sex" value="2" >女
							</s:else>
							--%>
						</td>
						<td>
							<div id="specialistForm_specialistForm_sexTip"></div>
						</td>
					</tr>
					<tr>
						<th align="right">
							身份证号码：
						</th>
						<td>
							<s:textfield name="specialistForm.idNum" cssClass="input2" size="40" onblur="clearBlank(this);"></s:textfield>
						</td>
						<td>
							<div id="specialistForm_specialistForm_idNumTip"></div>
						</td>
					</tr>
					<tr>
						<th align="right">
							生日：
						</th>
						<td>
							<s:textfield  name="specialistForm.birthday" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssClass="Wdate"></s:textfield>
							<%--<s:textfield name="specialistForm.birthday" cssClass="input2" size="40" onblur="clearBlank(this);"></s:textfield>--%>
						</td>
						<td>
							<div id="specialistForm_specialistForm_birthdayTip"></div>
						</td>
					</tr>
					<tr>
						<th align="right">
							籍贯：
						</th>
						<td>
							<s:textfield name="specialistForm.nativePlace" cssClass="input2" size="40" maxlength="60" onblur="clearBlank(this);"></s:textfield>
						</td>
						<td>
							<div id="specialistForm_specialistForm_nativePlaceTip"></div>
						</td>
					</tr>
					<tr>
						<th align="right">
							政治面貌：
						</th>
						<td>
							<dict:select name="specialistForm.politicalStatus" codeType="tb_specialist-political_status" />
							<%--<select name="specialistForm.politicalStatus">
								<option value=""></option>
								<option value="1">群众</option>
								<option value="2">共青团员</option>
								<option value="3">中共党员</option>
							</select>
							--%><%--<s:textarea name="specialistForm.politicalStatus" cssClass="input2" rows="5" cols="40" onblur="clearBlank(this);"></s:textarea>--%>
						</td>
						<td>
							<div id="specialistForm_specialistForm_politicalStatusTip"></div>
						</td>
					</tr>
					<tr>
						<th align="right">
							民族：
						</th>
						<td>
							<s:textfield name="specialistForm.nation" cssClass="input2" size="40" onblur="clearBlank(this);"></s:textfield>
						</td>
						<td>
							<div id="specialistForm_specialistForm_nationTip"></div>
						</td>
					</tr>
					<tr>
						<th align="right">
							办公电话：<font class="redStar">*</font>
						</th>
						<td>
							<s:textfield name="specialistForm.phoneNo" cssClass="input2" size="40" onblur="clearBlank(this);"></s:textfield>
						</td>
						<td>
							<div id="specialistForm_specialistForm_phoneNoTip"></div>
						</td>
					</tr>

					<tr>
						<th align="right">
							移动电话：
						</th>
						<td>
							<s:textfield name="specialistForm.mobileNo" cssClass="input2" size="40" onblur="clearBlank(this);"></s:textfield>
						</td>
						<td>
							<div id="specialistForm_specialistForm_mobileNoTip"></div>
						</td>
					</tr>
					<tr>
						<th class="tb_lable" width="20%" align="right">
							工作单位：
						</th>
						<td width="35%">
							<s:textfield name="specialistForm.workUnit" cssClass="input2" size="40" onblur="clearBlank(this);"></s:textfield>
						</td>
						<td width="45%">
							<div id="specialistForm_specialistForm_workUnitTip"></div>
						</td>
					</tr>
					<tr>
						<th align="right">
							职称：
						</th>
						<td>
							<dict:select name="specialistForm.professionalTitle" codeType="tb_specialist-professional_title" />
							<%--<s:textfield name="specialistForm.professionalTitle" cssClass="input2" size="40" onblur="clearBlank(this);"></s:textfield>--%>
						</td>
						<td>
							<div id="specialistForm_specialistForm_professionalTitleTip"></div>
						</td>
					</tr>
					<tr>
						<th align="right">
							通讯住址：
						</th>
						<td>
							<s:textfield name="specialistForm.address" cssClass="input2" size="40" onblur="clearBlank(this);"></s:textfield>
						</td>
						<td>
							<div id="specialistForm_specialistForm_addressTip"></div>
						</td>
					</tr>
					<tr>
						<th align="right">
							文化水平：
						</th>
						<td>
							<dict:select name="specialistForm.eduBg" codeType="common-edu_bg" />
							<%--<select name="specialistForm.eduBg">
								<option value=""></option>
								<option value="1">高中</option>
								<option value="2">大学</option>
								<option value="3">研究生</option>
							</select>
							--%><%--<s:textfield name="specialistForm.eduBg" cssClass="input2" size="40" onblur="clearBlank(this);"></s:textfield>--%>
						</td>
						<td>
							<div id="specialistForm_specialistForm_eduBgTip"></div>
						</td>
					</tr>
					<tr>
						<th align="right">
							电子邮箱：
						</th>
						<td>
							<s:textfield name="specialistForm.email" cssClass="input2" size="40" onblur="clearBlank(this);"></s:textfield>
						</td>
						<td>
							<div id="specialistForm_specialistForm_emailTip"></div>
						</td>
					</tr>
					<tr>
						<th align="right">
							传真号码：
						</th>
						<td>
							<s:textfield name="specialistForm.faxNo" cssClass="input2" size="40" onblur="clearBlank(this);"></s:textfield>
						</td>
						<td>
							<div id="specialistForm_specialistForm_faxNoTip"></div>
						</td>
					</tr>
					<tr>
						<th align="right">
							邮政编码：
						</th>
						<td>
							<s:textfield name="specialistForm.postCode" cssClass="input2" size="40" onblur="clearBlank(this);"></s:textfield>
						</td>
						<td>
							<div id="specialistForm_specialistForm_postCodeTip"></div>
						</td>
					</tr>
					<tr>
						<th align="right">
							学习培训简历：
						</th>
						<td>
							<s:textarea name="specialistForm.studyHis" cssClass="input2" rows="5" cols="40"  onblur="clearBlank(this);"></s:textarea>
						</td>
						<td>
							<div id="specialistForm_specialistForm_studyHisTip"></div>
						</td>
					</tr>
					<tr>
						<th align="right">
							工作简历：
						</th>
						<td>
							<s:textarea name="specialistForm.workHis" cssClass="input2" rows="5" cols="40"  onblur="clearBlank(this);"></s:textarea>
						</td>
						<td>
							<div id="specialistForm_specialistForm_workHisTip"></div>
						</td>
					</tr>
					<tr>
						<th align="right">
							证书：
						</th>
						<td>
							<s:textfield name="specialistForm.certificate" cssClass="input2" size="40" onblur="clearBlank(this);"></s:textfield>
						</td>
						<td>
							<div id="specialistForm_specialistForm_certificateTip"></div>
						</td>
					</tr>
					<tr>
						<th align="right">
							研究方向：
						</th>
						<td>
							<s:textfield name="specialistForm.researchArea" cssClass="input2" size="40" onblur="clearBlank(this);"></s:textfield>
						</td>
						<td>
							<div id="specialistForm_specialistForm_researchAreaTip"></div>
						</td>
					</tr>
					<tr>
						<th align="right">
							研究成果：
						</th>
						<td>
							<s:textarea name="specialistForm.researchFindings" cssClass="input2" rows="5" cols="40"  onblur="clearBlank(this);"></s:textarea>
						</td>
						<td>
							<div id="specialistForm_specialistForm_researchFindingsTip"></div>
						</td>
					</tr>
					 <tr style="display:none">
						<th align="right">
							头像照片：
						</th>
						<td>
							<s:textfield id="picPath" name="specialistForm.picPath" cssClass="input2" size="40" onblur="clearBlank(this);" readonly="true"></s:textfield>
							<%-- <input type="button" onclick="javascript:upload('pic');" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="上传"/>--%>
						</td>
						<td>
							<div id="specialistForm_specialistForm_picPathTip"></div>
						</td>
					</tr>

					<tr>
						<td class="bottom" align="center" colspan="3">
							<div align="center">
								<s:if test="%{actionName == 'specialist_save'}">
									<input type="button" onclick="javascript:save_specialist();"
											id="button" class="button"
											onmouseout="this.className = 'button'"
											onmouseover="this.className = 'buttonOver'"  value="增加" />
								</s:if>
								<s:else>
									<input type="button" onclick="javascript:update_specialist();"
											id="button" class="button"
											onmouseout="this.className = 'button'"
											onmouseover="this.className = 'buttonOver'" value="保存" />
								</s:else>
									<input type="button" onclick="javascript:history.back();" 
											class="button" 
											onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="取消"/>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</s:form>
<div id="dialog" title="文件上传"></div>
<script type="text/javascript">
		
	$.formValidator.initConfig( {
		formid :"specialistForm",
		onerror : function(msg) {
			alert(msg)
		},
		onsuccess : function() {
			return true;
		}
	});
	
	$("#specialistForm_specialistForm_certificateId").formValidator( {
		onshow :"请输入证书编号",
		onfocus :"最多可输入60个字符",
		oncorrect :"输入合法"
	}).inputValidator( {
		min :0,
		max :60,
		onerror :"请确认证书编号长度"
	});
	
	$("#specialistForm_specialistForm_name").formValidator( {
		onshow :"请输入矫正专家姓名",
		onfocus :"最多60个半角英文字母、数字和下划线（20个汉字），可输入中文",
		oncorrect :"输入合法"
	}).regexValidator( {
		regexp :"^[\u4E00-\u9FA5|A-Z|a-z|0-9|_]{1,60}$",
		onerror :"名称输入格式或长度错误"
	}).inputValidator( {
		min :1,
		max :60,
		onerror :"请确认姓名长度"
	});

	$("#specialistForm_specialistForm_idNum").formValidator({
		empty:true,
		onshow : "请输入身份证号码15位或者18位",
		onfocus : "请输入身份证号码15位或者18位",
		oncorrect :"身份证号码格式正确"
	}).functionValidator({fun:isCardID});
	
	$("#specialistForm_specialistForm_nativePlace").formValidator( {
		onshow :"请输入籍贯",
		onfocus :"最多60个半角英文字母、数字和下划线（20个汉字），可输入中文",
		oncorrect :"输入合法"
	}).regexValidator( {
		regexp :"^[\u4E00-\u9FA5|A-Z|a-z|0-9|_]{0,60}$",
		onerror :"籍贯输入格式或长度错误"
	}).inputValidator( {
		min :0,
		max :60,
		onerror :"请确认籍贯长度"
	});

	$("#specialistForm_specialistForm_nation").formValidator( {
		onshow :"请输入民族",
		onfocus :"最多60个半角英文字母、数字和下划线（20个汉字），可输入中文",
		oncorrect :"输入合法"
	}).regexValidator( {
		regexp :"^[\u4E00-\u9FA5|A-Z|a-z|0-9|_]{0,60}$",
		onerror :"民族输入格式或长度错误"
	}).inputValidator( {
		min :0,
		max :60,
		onerror :"请确认民族长度"
	});

	$("#specialistForm_specialistForm_phoneNo").formValidator( {
		onshow :"请输入办公电话",
		onfocus :"只能输入固定电话（区号-号码）",
		oncorrect :"输入正确",
		onempty :"请至少输入一个联系电话"
	}).regexValidator( {
		regexp :"^([0-9]{3,4}-[0-9]{7,8})$",
		onerror :"办公电话格式不正确，请输入固定电话（区号-号码）"
	});

	$("#specialistForm_specialistForm_mobileNo").formValidator( {
		empty :true,
		onshow :"请输入手机号码",
		onfocus :"请输入11位手机号",
		oncorrect :"输入正确"	
	}).regexValidator( {
		regexp :"^(1[3|5|8]{1}[0-9]{9})$",
		onerror :"手机号码格式不正确，请输入合法的11位手机号"
	});
	
	$("#specialistForm_specialistForm_faxNo").formValidator( {
		empty :true,
		onshow :"请输入传真",
		onfocus :"只能输入固定电话（区号-号码）",
		oncorrect :"输入正确"
	}).regexValidator( {
		regexp :"^([0-9]{3,4}-[0-9]{7,8})$",
		onerror :"传真格式不正确，请输入固定电话（区号-号码）"
	});

	$("#specialistForm_specialistForm_postCode").formValidator({
		empty :true,
		onshow:"请输入邮编",
		onfocus:"由6位数字组成",
		oncorrect:"谢谢你的合作，邮编输入正确"
	}).regexValidator({
		regexp:"zipcode",
		datatype:"enum",
		onerror:"邮编格式不正确"
	});

	$("#specialistForm_specialistForm_workUnit").formValidator( {
		empty :true,
		onshow :"请输入工作单位",
		onfocus :"最多60个半角英文字母、数字和下划线（20个汉字），可输入中文",
		oncorrect :"输入合法"
	}).regexValidator( {
		regexp :"^[\u4E00-\u9FA5|A-Z|a-z|0-9|_]{1,60}$",
		onerror :"工作单位输入格式或长度错误"
	}).inputValidator( {
		min :1,
		max :60,
		onerror :"请确认工作单位长度"
	});
	
	$("#specialistForm_specialistForm_studyHis").formValidator( {
		onshow :"请输入学习培训简历",
		onfocus :"最多512个半角英文字母、数字和下划线（170个汉字），可输入中文",
		oncorrect :"输入合法"
	}).inputValidator( {
		min :0,
		max :512,
		onerror :"请确认学习培训简历长度"
	});

	$("#specialistForm_specialistForm_workHis").formValidator( {
		onshow :"请输入工作简历",
		onfocus :"最多512个半角英文字母、数字和下划线（170个汉字），可输入中文",
		oncorrect :"输入合法"
	}).inputValidator( {
		min :0,
		max :512,
		onerror :"请确认工作简历长度"
	});
	
	$("#specialistForm_specialistForm_workHis").formValidator( {
		onshow :"请输入研究成果",
		onfocus :"最多512个半角英文字母、数字和下划线（170个汉字），可输入中文",
		oncorrect :"输入合法"
	}).inputValidator( {
		min :0,
		max :512,
		onerror :"请确认研究成果长度"
	});
	
	$("#specialistForm_specialistForm_address").formValidator( {
		empty :true,
		onshow :"请输入通讯住址",
		onfocus :"最多60个半角英文字母、数字和下划线（20个汉字），可输入中文",
		oncorrect :"输入合法"
	}).regexValidator( {
		regexp :"^[\u4E00-\u9FA5|A-Z|a-z|0-9|_]{0,60}$",
		onerror :"通讯住址输入格式或长度错误"
	}).inputValidator( {
		min :0,
		max :60,
		onerror :"请确认通讯住址长度"
	});

	$("#specialistForm_specialistForm_certificate").formValidator( {
		empty :true,
		onshow :"请输入证书名称",
		onfocus :"最多512个半角英文字母、数字和下划线（170个汉字），可输入中文",
		oncorrect :"输入合法"
	}).inputValidator( {
		min :0,
		max :512,
		onerror :"请确认证书名称长度"
	});

	$("#specialistForm_specialistForm_researchArea").formValidator( {
		empty :true,
		onshow :"请输入研究方向",
		onfocus :"最多512个半角英文字母、数字和下划线（170个汉字），可输入中文",
		oncorrect :"输入合法"
	}).inputValidator( {
		min :0,
		max :512,
		onerror :"请确认输入的研究方向长度"
	});
	
	$("#specialistForm_specialistForm_email").formValidator( {
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
		//regexp :"^([\\w-.]+)@(([[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.)|(([\\w-]+.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(]?)$",onerror :"你输入的邮箱格式不正确"
	});

	$("#specialistForm_specialistForm_picPath").formValidator({
		empty :true,
		onshow:"请输入图片名",
		oncorrect:"谢谢你的合作，图片名输入正确"
	}).regexValidator({
		regexp:"picture",
		datatype:"enum",
		onerror:"图片名格式不正确"
	});
	
	$("#picTd").formValidator({
		onshow :"请选择JPG、JEPG等图片文件，大小不要超过500K"
	});
	
</script>

</body>
</html>