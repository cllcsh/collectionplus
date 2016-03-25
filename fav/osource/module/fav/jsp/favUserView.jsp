<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/favUserMgr.js"></script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_add" width="100%"  border="0" cellpadding="1" cellspacing="1">
		<tr>
		    <th width="20%" style="text-align:right;">名称<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="favUserForm.userName"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">账号<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="favUserForm.account"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">手机号<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="favUserForm.phone"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">热度<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="favUserForm.heat"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">个性签名<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="favUserForm.signature"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">用户等级<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="favUserForm.userLevelDesc"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">用户称号<font style="color: red;">*</font>：</th>
		    <td width="40%">
		    	<s:iterator value="favUserForm.userTitleImgs" id="titleImg">  
				  <img src="<s:property value="titleImg"/>"  style="width:50px;height:50px;" />&nbsp;&nbsp;&nbsp;&nbsp;
				</s:iterator> 
		    </td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">个人头像<font style="color: red;">*</font>：</th>
		    <td width="40%"><img src="<s:property value="favUserForm.avatar"/>"  width="32" height="32" /></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">更换头像时间<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:date name="favUserForm.upateAvatarTime" format="yyyy-MM-dd HH:mm:ss" /></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">描述<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="favUserForm.description"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">用户当前积分<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="favUserForm.userPoints"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">用户累计积分<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="favUserForm.userAllPoints"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">经验值<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="favUserForm.experience"/></td>
		</tr>
		<tr>
		    <th width="20%" style="text-align:right;">经验值<font style="color: red;">*</font>：</th>
		    <td width="40%"><s:property value="favUserForm.personalMsgSetDesc"/></td>
		</tr>
        <tr>
          <td class="bottom" align="center" colspan="2">
			<input type="button" onclick="javascript:history.back();" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="返回"/>
          </td>
        </tr>
      </table></td>
    </tr>
  </table>

</body>
</html>