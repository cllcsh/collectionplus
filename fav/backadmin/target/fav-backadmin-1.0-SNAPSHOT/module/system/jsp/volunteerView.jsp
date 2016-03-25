<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/volunteerMgr.js"></script>

</head>
<body>
<%@include file="/jsp/include/navigation.jsp"%>
<s:fielderror/>
<s:form>
<s:hidden name="specialistForm.id"></s:hidden>
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_add" width="100%"  border="0" cellpadding="1" cellspacing="1">
		<tr>
			<td class="td_title" colspan="4" align="center">
				心理矫治志愿者信息查看
			</td>
		</tr>
 		<tr>
          <th class="tb_lable" width="15%" align="right">全国志愿者注册编号：</th>
          <td>
            <s:property value="volunteerBean.registerId" />
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
             <s:property value="volunteerBean.consultantCertificateId" />
          </td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">姓 &nbsp;&nbsp;名：</th>
          <td width="35%">
            <s:property value="volunteerBean.name" />
          </td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">性 &nbsp;&nbsp;别：</th>
          <td width="35%">
          	 <dict:property value="volunteerBean.sex" codeType="common-sex"/>
          </td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">身份证号：</th>
          <td width="35%">
            <s:property value="volunteerBean.idNum" />
          </td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">出生年月：</th>
          <td width="35%">
          	<s:date format="yyyy-MM-dd" name="volunteerBean.birthday"/>
          </td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">心理矫治志愿者服务证书编号：</th>
          <td width="35%">
            <s:property value="volunteerBean.serviceCertificateId" />
          </td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">咨询师等级：</th>
          <td width="35%">
            <dict:property value="volunteerBean.rank" codeType="tb_volunteer-rank"/>
          </td>
		   <td colspan="2"></td>
		</tr>
		<tr>
          <th class="tb_lable" width="15%" align="right">专 &nbsp;&nbsp;业：</th>
          <td width="35%">
          	<s:property value="volunteerBean.specialty" />
          </td>
		<td colspan="2"></td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">籍 &nbsp;&nbsp;贯：</th>
          <td width="35%">
            <s:property value="volunteerBean.nativePlace" />
          </td>
		<td colspan="2"></td>
		</tr>
		<tr>
          <th class="tb_lable" width="15%" align="right">政治面貌：</th>
          <td width="35%">
          	<s:property value="volunteerBean.politicalStatus" />
          </td>
		<td colspan="2"></td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">民 &nbsp;&nbsp;族：</th>
          <td width="35%">
            <s:property value="volunteerBean.nation" />
          </td>
		<td colspan="2"></td>
		</tr>
		<tr>
          <th class="tb_lable" width="15%" align="right">联系电话：</th>
          <td width="35%">
          	<s:property value="volunteerBean.phoneNo" />
          </td>
		<td colspan="2"></td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">工作单位：</th>
          <td width="35%">
            <s:property value="volunteerBean.workUnit" />
          </td>
		<td colspan="2"></td>
		</tr>
		<tr>
          <th class="tb_lable" width="15%" align="right">职 &nbsp;&nbsp;务：</th>
          <td width="35%">
          	<s:property value="volunteerBean.duty" />
          </td>
		<td colspan="2"></td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">职 &nbsp;&nbsp;称：</th>
          <td width="35%">
            <s:property value="volunteerBean.professionalTitle" />
          </td>
		<td colspan="2"></td>
		</tr>
		<tr>
          <th class="tb_lable" width="15%" align="right">通讯地址：</th>
          <td width="35%">
          	<s:property value="volunteerBean.address" />
          </td>
		<td colspan="2"></td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">学 &nbsp;&nbsp;历：</th>
          <td width="35%">
          	<dict:property codeType="common-edu_bg" value="volunteerBean.eduBg"/>
          </td>
			<td colspan="2"></td>
		</tr>
		<tr>
          <th class="tb_lable" width="15%" align="right">E-mail：</th>
          <td width="35%">
          	<s:property value="volunteerBean.email" />
          </td>
			<td colspan="2"></td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">职业分类：</th>
          <td width="35%">
            <s:property value="volunteerBean.workType" />
          </td>
			<td colspan="2"></td>
		</tr>
		<tr>
          <th class="tb_lable" width="15%" align="right">邮政编码：</th>
          <td width="35%">
          	<s:property value="volunteerBean.postcode" />
          </td>
		<td colspan="2"></td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">个人简历：</th>
          <td width="75%" colspan="3">
          	<s:property value="volunteerBean.resume" />
          </td>
			<td colspan="2"></td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">各类培训和专业资格：</th>
          <td width="75%" colspan="3">
          	<s:property value="volunteerBean.trainQualification" />
          </td>
			<td colspan="2"></td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">个人特长和兴趣爱好：</th>
          <td width="75%" colspan="3">
          	<s:property value="volunteerBean.interest" />
          </td>
		<td colspan="2"></td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">志愿者服务经历：</th>
          <td width="75%" colspan="3">
          	<s:property value="volunteerBean.serviceExperience" />
          </td>
		<td colspan="2"></td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">主要研究成果：</th>
          <td width="75%" colspan="3">
          	<s:property value="volunteerBean.researchFindings" />
          </td>
		<td colspan="2"></td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">相 &nbsp;&nbsp;片：</th>
          <td width="75%" colspan="3">
          	<s:property value="volunteerBean.picPath" />
          </td>
			<td colspan="2"></td>
        </tr>
        <tr>
          <th class="tb_lable" width="15%" align="right">志愿申请书：</th>
          <td width="75%" colspan="3">
          	<s:property value="volunteerBean.reqPath" />
          	<span id="reqSpan">
          	  <s:if test="volunteerBean.reqPath != null && volunteerBean.reqPath != ''">
          			<a href="<%=request.getContextPath()%>/upload/<s:property value='volunteerBean.reqPath'/>" target="_blank">志愿申请书查看</a>&nbsp;&nbsp;&nbsp;<div id="saveTip"> <font color="red" size="2">直接在浏览器打开可能存在乱码 ，建议您使用鼠标右键  "目标另存为" </font><span  onclick="javascript:document.getElementById('saveTip').style.display='none';"><b>关闭</b> </span></div>
          	  </s:if>
          	</span>
          </td>
			<td colspan="2"></td>
        </tr>


        <tr>
          <td class="bottom" align="center" colspan="4">
			<input type="button" onclick="javascript:history.back();" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="返回"/>
          </td>
        </tr>
      </table></td>
    </tr>
  </table>
</s:form>
</body>
</html>