<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<head>
    <title>个人中心</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="baidu-site-verification" content="hWGw6qbfv7" />
    
    <link rel="stylesheet" href="/m/css/style.css">

</head>
<body>
    <header class="comm-header">
        <h1 class="title">个人中心</h1>
    </header>
    <div class="wrap">

<s:if test="%{userSession != null}">
		<s:if test="%{userSession.approveFlag == 0}">
        <a href="/mhome.do" class="mod-user-profile">
            <h1 class="name"><s:property value="userSession.userName"/></h1>
            <div class="info">
                <div class="reason">
                    <span class="comm-text-red comm-text-large"><i class="ico-error"></i>您的还没通过审核</span>   
                </div>
            </div>
        </a>
		</s:if>
		<s:elseif test="%{userSession.approveFlag == 2}">
        <a href="/muserportal_reasonDetail.do?reason=<s:property value='userForm.reason'/>" class="mod-user-profile">
            <h1 class="name"><s:property value="userSession.userName"/></h1>
            <div class="info">
                <div class="reason">
                    <span class="comm-text-red comm-text-large"><i class="ico-error"></i>您的审核未通过</span>              
                    点击查看原因
                </div>
            </div>
        </a>
		<ul class="mod-user-menu list-unstyled">
			<li><a href="/muserportal_profile.do"><span class="ico ico-pen"></span>个人资料</a></li>
            <li><a href="/morderprotal_init.do"><span class="ico ico-note"></span>我的订单</a></li>
            <li><a href="/muserportal_company.do"><span class="ico ico-pen"></span>公司信息</a></li>
            <li><a href="/muserportal_bank.do"><span class="ico ico-pen"></span>银行账户信息</a></li>
            <li><a href="/muserportal_msg.do"><span class="ico ico-msg"></span>通知消息</a></li>
            <li><a href="/muserportal_phone.do"><span class="ico ico-phone"></span>更换手机</a></li>
			<!--<li><a href="/mhome_login.do"><span class="ico ico-pen"></span>注销</a></li>-->
		</ul>
		</s:elseif>
		<s:elseif test="%{userSession.approveFlag == 1}">
			<a href="/muserportal_profile.do" class="mod-user-profile">
				<div class="avatar" style="background-image:url(<s:property value='userForm.imgPath'/>);"></div>
				<div class="info">
					<div class="reason">
						<span class="comm-text-large comm-text-black"><s:property value="userSession.userName"/></span>
					</div>
				</div>
			</a>
        <ul class="mod-user-menu list-unstyled">
            <li><a href="/morderprotal_init.do"><span class="ico ico-note"></span>我的订单</a></li>
            <li><a href="/muserportal_company.do"><span class="ico ico-pen"></span>公司信息</a></li>
<%--             <li><a href="/muserportal_msg.do"><span class="ico ico-msg"></span>通知消息</a></li> --%>
            <li><a href="/muserportal_letter.do"><span class="ico ico-msg"></span>站内信</a></li>
            <li><a href="/muserportal_phone.do"><span class="ico ico-phone"></span>更换手机</a></li>
			<li><a href="/mhome_login.do"><span class="ico ico-pen"></span>注销</a></li>
        </ul>
		</s:elseif>
	</s:if>	
    </div>


   <%@include file="/m/user/footer.jsp" %>

</div>

</body>
</html>
