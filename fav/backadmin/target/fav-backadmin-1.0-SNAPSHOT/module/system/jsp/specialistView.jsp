<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/specialistMgr.js"></script>

<%-- <%@include file="/jsp/meta.jsp" %> --%>
</head>
<body>
<%@include file="/jsp/include/navigation.jsp"%>
<s:fielderror/>
<s:form>
<s:hidden name="specialistForm.id"></s:hidden>
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_add" width="100%"  border="0" cellpadding="1" cellspacing="1">
		<tr>
			<td class="td_title" colspan="2" align="center">
				矫正专家登记
			</td>
		</tr>
		<tr>
			<th class="tb_lable" width="20%" align="right">
				相 &nbsp;&nbsp;片：
			</th>
			<td>
				<s:if test="specialistForm.picPath != null && specialistForm.picPath != ''">
				     <img src="<%=request.getContextPath() %>/upload/<s:property value='specialistForm.picPath'/>" width="90" height="120"/>
				</s:if>
				<s:else>
				          暂未提供照片，请先上传！
				</s:else>
			</td>
		</tr>
		<tr>
			<th class="tb_lable" width="20%" align="right">
				证书编号：
			</th>
			<td>
				<s:property value="specialistForm.certificateId" />
			</td>
		</tr>
		<tr>
			<th align="right">
				名称：
			</th>
			<td>
				<s:property value="specialistForm.name" />
			</td>
		</tr>
		<tr>
			<th align="right">
				性别：
			</th>
			<td>
				<dict:property codeType="common-sex" value="specialistForm.sex" />
				<%--<s:if test="specialistForm.sex == 1">男</s:if>
		      	<s:else>女</s:else>
				--%><%--<s:property value="specialistForm.sex" />--%>
			</td>
		</tr>
		<tr>
			<th align="right">
				身份证号码：
			</th>
			<td>
				<s:property value="specialistForm.idNum" />
			</td>
		</tr>
		<tr>
			<th align="right">
				生日：
			</th>
			<td>
				<s:property value="specialistForm.birthday" />
			</td>
		</tr>
		<tr>
			<th align="right">
				籍贯：
			</th>
			<td>
				<s:property value="specialistForm.nativePlace" />
			</td>
		</tr>
		<tr>
			<th align="right">
				政治面貌：
			</th>
			<td>
				<dict:property codeType="tb_specialist-political_status" value="specialistForm.politicalStatus" />
				<%--<s:if test="specialistForm.politicalStatus == 1">群众</s:if>
		      	<s:elseif test="specialistForm.politicalStatus == 2">共青团员</s:elseif>
				<s:elseif test="specialistForm.politicalStatus == 3">中共党员</s:elseif>
				<s:else>&nbsp;</s:else>
				--%><%--<s:property value="specialistForm.politicalStatus" />--%>
			</td>
		</tr>
		<tr>
			<th align="right">
				民族：
			</th>
			<td>
				<s:property value="specialistForm.nation" />
			</td>
		</tr>
		<tr>
			<th align="right">
				办公电话：
			</th>
			<td>
				<s:property value="specialistForm.phoneNo" />
			</td>
		</tr>

		<tr>
			<th align="right">
				移动电话：
			</th>
			<td>
				<s:property value="specialistForm.mobileNo" />
			</td>
		</tr>
		<tr>
			<th>
				工作单位：
			</th>
			<td>
				<s:property value="specialistForm.workUnit" />
			</td>
		</tr>
		<tr>
			<th align="right">
				职称：
			</th>
			<td>
				<dict:property codeType="tb_specialist-professional_title" value="specialistForm.professionalTitle" />
				<%--<s:property value="specialistForm.professionalTitle" />--%>
			</td>
		</tr>
		<tr>
			<th align="right">
				通讯住址：
			</th>
			<td>
				<s:property value="specialistForm.address" />
			</td>
		</tr>
		<tr>
			<th align="right">
				文化水平：
			</th>
			<td>
				<dict:property codeType="common-edu_bg" value="specialistForm.eduBg" />
				<%--<s:if test="specialistForm.eduBg == 1">高中</s:if>
		      	<s:elseif test="specialistForm.eduBg == 2">大学</s:elseif>
				<s:elseif test="specialistForm.eduBg == 3">研究生</s:elseif>
				<s:else>&nbsp;</s:else>
				--%><%--<s:property value="specialistForm.eduBg" />--%>
			</td>
		</tr>
		<tr>
			<th align="right">
				电子邮箱：
			</th>
			<td>
				<s:property value="specialistForm.email" />
			</td>
		</tr>
		<tr>
			<th align="right">
				传真号码：
			</th>
			<td>
				<s:property value="specialistForm.faxNo" />
			</td>
		</tr>
		<tr>
			<th align="right">
				邮政编码：
			</th>
			<td>
				<s:property value="specialistForm.postCode" />
			</td>
		</tr>
		<tr>
			<th align="right">
				学习培训简历：
			</th>
			<td>
				<s:property value="specialistForm.studyHis" />
			</td>
		</tr>
		<tr>
			<th align="right">
				工作简历：
			</th>
			<td>
				<s:property value="specialistForm.workHis" />
			</td>
		</tr>
		<tr>
			<th align="right">
				证书：
			</th>
			<td>
				<s:property value="specialistForm.certificate" />
			</td>
		</tr>
		<tr>
			<th align="right">
				研究方向：
			</th>
			<td>
				<s:property value="specialistForm.researchArea" />
			</td>
		</tr>
		<tr>
			<th align="right">
				研究成果：
			</th>
			<td>
				<s:property value="specialistForm.researchFindings" />
			</td>
		</tr>
		<!--  <tr>
			<th align="right">
				头像照片
			</th>
			<td>
				<s:property value="specialistForm.picPath" />
			</td>
		</tr>-->


        <tr>
          <td class="bottom" align="center" colspan="2">
			<input type="button" onclick="javascript:history.back();" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="返回"/>
          </td>
        </tr>
      </table></td>
    </tr>
  </table>
</s:form>
</body>
</html>