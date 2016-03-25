<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<html>
<head>
<title><s:property value="jsp_head_title"/>-用户管理</title>
<%@include file="/jsp/common.jsp"%>
</head>

<body>
<%@include file="/jsp/include/pageInc.jsp"%>
<s:else>
	<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_result" border="0" cellspacing="1" cellpadding="1" align="center">
      <tr class="tr_head" align="center">
		<td><input id="checkboxtop"	type="checkbox" class="ckboxAll" title="选中/取消选中" value="" onclick="checkAll('checkboxtop','checkbox1');" /></td>
		<td width="">名称</td>
		<td width="">账号</td>
		<td width="">手机号</td>
		<td width="">热度</td>
		<td width="">个性签名</td>
		<td width="">用户等级</td>
		<td width="">用户称号</td>
		<td width="">个人头像</td>
		<td width="">更换头像时间</td>
		<td width="">描述</td>
		<td width="5%">用户当前积分</td>
		<td width="5%">用户累计积分</td>
		<td width="">经验值</td>
		<td width="">个人私信设置</td>
		<td width="10%">操作</td>
      </tr>
      <s:iterator id="favUserInfo" value="%{pageList.results}" status="sta">
      <tr align="center" class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
		<td>
			<input name="items[<s:property value="#sta.index"/>].id" type="checkbox" class="ckboxItem" value="<s:property value="#favUserInfo.id"/>" onclick="check('checkboxtop','checkbox1');" />
		</td>
        <td>
			<a href="javascript:view_favUser(<s:property value='#favUserInfo.id'/>)"><s:property value="#favUserInfo.userName"/></a>
		</td>
		<td><s:property value="#favUserInfo.account"/></td>
		<td><s:property value="#favUserInfo.phone"/></td>
		<td><s:property value="#favUserInfo.heat"/></td>
		<td><s:property value="#favUserInfo.signature"/></td>
		<td><s:property value="#favUserInfo.userLevelDesc"/></td>
		<td width="120px;">
			<s:iterator value="#favUserInfo.userTitleImgs" id="titleImg">  
			  <img src="<s:property value="titleImg"/>"  style="width:50px;height:50px;" />
			</s:iterator> 
		</td>
		<td><img src="<s:property value="#favUserInfo.avatar"/>"  width="32" height="32" /></td>
		<td><s:date name="#favUserInfo.upateAvatarTime" format="yyyy-MM-dd HH:mm:ss" /></td>
		<td><s:property value="#favUserInfo.description"/></td>
		<td><s:property value="#favUserInfo.userPoints"/></td>
		<td><s:property value="#favUserInfo.userAllPoints"/></td>
		<td><s:property value="#favUserInfo.experience"/></td>
		<td><s:property value="#favUserInfo.personalMsgSetDesc"/></td>
        <td align="center" >
        	<a href="javascript:reset_favUser(<s:property value='#favUserInfo.id'/>)" >重置密码</a>
        	<a href="javascript:edit_favUser(<s:property value='#favUserInfo.id'/>)" >修改</a>
			<s:if test="%{userSession.userPermissions['/module/fav/favUser_edit.do'] != null}">
				<a href="javascript:edit_favUser(<s:property value='#favUserInfo.id'/>)" >修改</a> &nbsp;&nbsp;
            </s:if>
		</td>
      </tr>
      </s:iterator>
    </table>
    <script type="text/javascript">
	$(".tb_result").checkbox([{all:'ckboxAll',item:'ckboxItem'}]);
	</script>
	</td></tr></table>
</s:else>

</body>
</html>
