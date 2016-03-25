<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>站内信详情</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="baidu-site-verification" content="hWGw6qbfv7" />
    <link rel="stylesheet" href="/m/css/style.css">

</head>
<body class="white">
    <header class="comm-header">
    <div class="left opt">
        <a href="javascript:window.history.go(-1);">返回</a>
    </div>
    <h1 class="title">站内信详情</h1>
</header>


    <div class="wrap">
        <div class="mod-user-msg-detail">
            <h1><s:property value='letterUserForm.title'/></h1>
            <div class="time comm-text-gray"><s:date name='letterUserForm.insertDate' format="yyyy-MM-dd HH:mm"/></div>
            <s:property escape="false" value='letterUserForm.content'/>
        </div>
    </div>

</body>
</html>
