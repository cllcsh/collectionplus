<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include  file="/portal/header.jsp"%>

    <link rel="stylesheet" href="/portal/css/style.css">

</head>
<body>
<%@ include  file="/portal/inc.jsp"%>
    <div class="comm-white-ctn">
        <div class="container">
            <%@ include  file="/portal/user/left.jsp"%>
            <!-- 用户中心主内容区 -->
            <div class="mod-user-main">
				<div class="mod-user-card">
                    <div class="item">
						<s:if test="%{userSession != null}">						
                        <div class="avatar"><a href="userportal_avatar.do"><img src="<s:property value='userForm.imgPath'/>" alt=""></a></div>
                        <div class="name">
                            <s:property value="userSession.userName"/>
								 <a href="userportal_profile.do"><i class="sml-ico ico-smlpen"></i>个人资料</a>
                        </div>
						</s:if>					
                    </div>
                    <div class="item nav">
                        <a href="orderprotal_init.do"><i class="ico ico-book"></i>我的订单<!-- <span class="comm-text-red">4</span>--></a>
                        <!--<a href="#"><i class="ico ico-msg"></i>消息通知 <span class="comm-text-red">4</span></a>-->
                    </div>
                </div>
            </div>
            <!-- ./用户中心主内容区 -->
        </div>
    </div>
 <%@ include  file="/portal/footer.jsp"%>
<script src="/portal/js/vendor/jquery.js"></script>
<script src="/portal/js/app.js"></script>

</body>
</html>
