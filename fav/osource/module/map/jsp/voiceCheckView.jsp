<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>
<%@taglib prefix="ict" uri="/ict-tags"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/voiceCheckMgr.js"></script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_add" width="100%"  border="0" cellpadding="1" cellspacing="1">
	<tr>
	<td colspan="4" height="20">
	&nbsp;
	</td>
	</tr>
		<tr>
								<th>
									司法单位：
								</th>
								<td>
								<ict:property beanContextId="deptSelect" value="voiceCheckForm.deptId"/>
								</td>
								<th>
									矫正对象：
								</th>
								<td>
								<ict:property beanContextId="tb_criminal" value="voiceCheckForm.criminalId"/>
								</td>
							</tr>
				            <tr>
								<th>
									定位号码：
								</th>
								<td>
								<s:property value="voiceCheckForm.msdn"/>
								</td>
								<th>
									起始时间：
								</th>
								<td>
								<s:date format="yyyy-MM-dd HH:mm:ss" name="voiceCheckForm.startDate"/>
								</td>
							</tr>
							<tr>
								<th>
									结束时间：
								</th>
								<td>
								<s:date format="yyyy-MM-dd HH:mm:ss" name="voiceCheckForm.endDate"/>
								</td>
								<th>
									抽查结果：
								</th>
								<td>
								<dict:property codeType="voice_check_code" value="voiceCheckForm.resultCode"/>
								</td>
							</tr>
							<tr>
								<th>
									声纹录入时声音片段：
								</th>
								<td>
								<s:iterator id="it" value="voiceCheckForm.oldVoicePath1" status="s">
								<a href="javascript: play('http://221.226.150.105:8082/record/'+'<s:property value="it"/>')">片段${s.index+1}</a>
								</s:iterator>
								</td>
								<th>
									声纹验证时声音片段：
								</th>
								<td>
								
								<s:iterator var="it" value="voiceCheckForm.voicePath1" status="s">
								<a href="javascript: play('http://221.226.150.105:8082/record/'+'<s:property value="it"/>')">片段${s.index+1}</a>
								</s:iterator>
								</td>
							</tr>
							<tr id="tr" style="display: none">
							<td colspan="4">
							<div align="center">
							<object ID="video2" CLASSID="clsid:CFCDAA03-8BE4-11cf-B84B-0020AFBBCCFA">
							  <embed id="video" src="" type="audio/x-pn-realaudio-plugin" autostart="true" height="40">
							  </embed>
							</object>
							</div>
							</td>
							</tr>
        <tr>
          <td class="bottom" align="center" colspan="4">
			<input type="button" onclick="javascript:history.back();" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="返回"/>
          </td>
        </tr>
      </table></td>
    </tr>
  </table>

</body>
<script>
function play(it){
document.getElementById("tr").style.display="";
document.getElementById('video').outerHTML="<embed src="+it+" id=video type=audio/x-pn-realaudio-plugin autostart=true height=40>";
}
</script>
</html>