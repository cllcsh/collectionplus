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
                <div class="mod-user-section-title"><a href="userportal_msg.do">系统消息</a> <span class="split">&gt;</span> 详情</div>
                <div class="mod-user-msg-detail">
                    <h1><s:property value='letterUserForm.title'/></h1>
                    <div class="time comm-text-gray"><s:date name='letterUserForm.insertDate' format="yyyy-MM-dd HH:mm"/></div>
                    <s:property escape="false" value='letterUserForm.content'/>
                </div>
            </div>
            <!-- ./用户中心主内容区 -->
        </div>
    </div>

<%@ include  file="/portal/footer.jsp"%>
<script src="/portal/js/vendor/jquery.js"></script>
<script src="/portal/js/app.js"></script>
<script type="text/javascript">

</script>
</body>
</html>
