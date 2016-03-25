<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="js/letterMgr.js"></script>
</head>

<body>
<%@include file="/jsp/include/navigation.jsp"%>
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_add" width="100%"  border="0" cellpadding="1" cellspacing="1">
		<tr>
            <th align="right" width="30%">标题：</th>
            <td><s:property value="letterForm.title"/></td> 
        </tr>
        <tr>
            <th align="right" width="30%">内容：</th>
            <td><s:property value="letterForm.content"/></td> 
        </tr>
        <tr>
            <th align="right" width="30%">类型：</th>
            <td>
            	<s:if test="%{letterForm.type==1}">用户审核通过</s:if>
	        	<s:elseif test="%{letterForm.type==2}">用户审核未通过</s:elseif>
	        	<s:elseif test="%{letterForm.type==3}">用户资料更新</s:elseif>
	        	<s:elseif test="%{letterForm.type==4}">上挂车源通过</s:elseif>
	        	<s:elseif test="%{letterForm.type==5}">上挂车源未通过</s:elseif>
	        	<s:elseif test="%{letterForm.type==6}">订单状态变更</s:elseif>	
			</td> 
        </tr>
        <tr>
            <th align="right" width="30%">发送人：</th>
            <td><s:property value="letterForm.senderName"/></td> 
        </tr>
        <tr>
            <th align="right" width="30%">发送时间：</th>
            <td><s:date name="letterForm.insertDate" format="yy-MM-dd HH:mm" /></td> 
        </tr>
        <tr>
        	<td colspan="2">接收用户列表</td>
        </tr>
        <tr>
        	<td colspan="2">
        	<table class="tb_add" width="100%"  border="0" cellpadding="1" cellspacing="1">
        		<tr>
        			<td>接收人</td>
        			<td>电话</td>
        			<td>查看状态</td>
        			<td>查看时间</td>
        		</tr>
        		<s:iterator id="letterUser" value="%{letterUserList}" status="sta">
        			<tr>
        				<td><s:property value="#letterUser.receiverName"/></td>
        				<td><s:property value="#letterUser.phone"/></td>
        				<td>
        					<s:if test="%{#letterUser.lookStatus==0}">未读</s:if>
        					<s:elseif test="%{#letterUser.lookStatus==1}">已读</s:elseif>
        				</td>
        				<td><s:date name="#letterUser.updateDate" format="yy-MM-dd HH:mm" /></td>
        			</tr>
        		</s:iterator>
        	</table>
        	</td>
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