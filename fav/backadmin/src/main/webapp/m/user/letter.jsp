<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>站内信</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="baidu-site-verification" content="hWGw6qbfv7" />
    <link rel="stylesheet" href="/m/css/style.css">

</head>
<body>
    <header class="comm-header">
    <div class="left opt">
        <a href="javascript:window.history.go(-1);">返回</a>
    </div>
    <h1 class="title">站内信</h1>
</header>


    <div class="wrap" style="margin-bottom: 0;">
        <div class="mod-user-msg-list" id="J-MsgList">
		<s:iterator var="letterInfo" value="%{pageList.results}">
            <a href="/muserportal_letterDetail.do?id=<s:property value='#letterInfo.id'/>"  class="item">
                <s:if test="#letterUserInfo.lookStatus==0"><span class="bubble"></span></s:if>
                <div class="title"><s:property value='#letterInfo.title'/> <div class="right comm-text-gray"><s:date name='#letterInfo.insertDate' format="yyyy-MM-dd HH:mm"/></div></div>
                <div class="body comm-text-gray"><s:property escape="false" value='#letterInfo.content'/></div>
            </a>
         </s:iterator>  
        </div>
    </div>
        <div class="comm-loadmore" data-offset="1" data-list-id="J-MsgList" data-size="10" data-url="/muserportal_msg.do">点击加载更多</div>
    <script src="/m/bower_components/zepto/zepto.min.js"></script>
    <script src="/m/bower_components/zeptotouch/zepto-touch.min.js"></script>
    <script src="/m/vendor/zepto-selector.js"></script>
    <script src="/m/js/app.js"></script>
	<script>
		window.onload = function(){
            Fache.bindLoadMore();
		}
	</script>
</body>
</html>
