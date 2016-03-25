<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<head>
    <title>我要卖车</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="baidu-site-verification" content="hWGw6qbfv7" />
    <link rel="stylesheet" href="/m/css/style.css">

</head>
<body>
    <header class="comm-header">
        <h1 class="title">我要卖车</h1>
    </header>

    <div class="wrap" style="margin-bottom: 5rem;">
        <div class="comm-search-form">
            <form action="">
                <div class="input"><span class="placeholder"><i class="ico"></i>输入所查询的车名</span><input type="text" name="keyword"></div>
                <button type="submit">搜索</button>
            </form>
        </div>
        
 		<%@include file="/m/incbrandhot.jsp" %>

        <div class="comm-brand-groups">
        	<s:iterator var="brandPinyin" value="brandPinyinList">
       		<div class="group" id="<s:property value='#brandPinyin.pinyin'/>">
                <h2 class="title"><s:property value="#brandPinyin.pinyin"/></h2>
                <ul class="list-unstyled">
                	<s:iterator var="brand" value="#brandPinyin.brandInfoList">
                	<li>
                        <a href="/msell_seriesList.do?brandId=<s:property value='#brand.id'/>">
                            <div class="img"><img src="<s:property value='#brand.picPath'/>" alt=""></div>
                            <div class="info">
                                <span class="car-title"><s:property value='#brand.name'/></span>
                            </div>
                        </a>
                    </li>
                	</s:iterator>
                </ul>
            </div>
        	</s:iterator>
        </div>

        <div class="comm-alphabet-list">
            <div class="inner">
            	<s:iterator var="brandPinyin" value="brandPinyinList">
                <a href="#<s:property value='#brandPinyin.pinyin'/>"><s:property value='#brandPinyin.pinyin'/></a>
            	</s:iterator>
            </div>
        </div>

    </div>

    <%@include file="/m/sell/footer.jsp" %>
</div>

</body>
</html>
