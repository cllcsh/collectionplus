<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<%@include file="/jsp/meta.jsp" %>
</head>
<body style="overflow:hidden"><s:fielderror/>
<s:form id="configInfoForm" name="configInfoForm" action="%{actionName}">
<s:hidden name="configInfoForm.configCode"></s:hidden>
<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_add" width="100%"  border="0" cellpadding="1" cellspacing="1">
        <tr>
          <td class="td_title" colspan="4" align="center">配置配置</td>
        </tr>
         <tr>
          <td class="tb_lable" width="15%" align="right">配置类型</td>
          <td width="35%">
            <s:textfield id="configType" name="configInfoForm.configType" cssClass="input2" size="40"></s:textfield><font class="redStar">*</font>
          </td>
          <td colspan="2" width="50%"><div id="configTypeTip"></div></td>
        </tr>
        <tr>
          <th align="right">配置键</th>
          <td>
          	<s:textfield id="configKey" name="configInfoForm.configKey" cssClass="input2" size="40" maxlength="20"></s:textfield><font class="redStar">*</font>
          </td>
          <td colspan="2"><div id="configKeyTip"></div></td>
        </tr>
        <tr>
          <th align="right">配置值</th>
          <td colspan="3">
            <s:textfield id="configValue" name="configInfoForm.configValue" cssClass="input2" size="60" maxlength="300"></s:textfield>
          </td>
        </tr>
        <tr>
          <th align="right">描述</th>
          <td colspan="3">
          	<s:textarea id="configDesc" name="configInfoForm.configDesc" cssClass="input2" maxlength="1000"></s:textarea>
          </td>
        </tr>
        <tr>
          <td class="bottom" align="center" colspan="4"><div align="center">
			<s:if test="%{actionName == 'config_save'}">
				<input id="button" type="button" onclick="save_config();" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="增加"/>
			</s:if><s:else>
				<input id="button" type="button" onclick="update_config();" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="保存"/>
			</s:else>
			
          </div></td>
          </tr>
      </table></td>
    </tr>
  </table>
</s:form>
<script type="text/javascript">
	$.formValidator.initConfig({formid:"configInfoForm",onerror:function(msg){alert(msg)},onsuccess:function(){return true;}});
	$("#configType").formValidator({onshow:"请输入配置类型",onfocus:"配置类型不能为空且最多60个字符(20个汉字)",oncorrect:"配置类型合法"}).inputValidator({min:1,max:60,onerror:"请确认配置类型长度"});
	$("#configKey").formValidator({onshow:"请输入配置键",onfocus:"配置键不能为空且最多30个字符(10个汉字)",oncorrect:"配置键合法"}).inputValidator({min:1,max:30,onerror:"请确认配置键长度"});
</script>
</body>
</html>