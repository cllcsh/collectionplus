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
    <div class="left opt">
        <a href="javascript:history.go(-1);">返回</a>
    </div>
    <h1 class="title"><s:property value="brandInfo.name"/></h1>
</header>
    <div class="wrap">
        <div class="comm-search-form">
            <div class="input">输入所查询的车名<input type="text"></div>
        </div>

        <div class="comm-car-list mod-serial-list">
            <h2 class="comm-section-title">车辆系列</h2>
            <s:iterator var="sellSeries" value="sellSeriesInfoList">
            <a href="/msell_sell.do?seriesId=<s:property value='#sellSeries.id'/>" class="product">
                <div class="img">
                    <img src="<s:property value='#sellSeries.picPath'/>" alt="">
                </div>
                <div class="info">
                    <div class="title"><s:property value='#sellSeries.brandName'/> <s:property value='#sellSeries.name'/> <s:property value='#sellSeries.versionName'/></div>
                    <div class="price-info">
                        <div class="price left">发车价：<strong>&yen;<s:property value='#sellSeries.showPrice'/>万</strong>起</div>
                    </div>
                </div>
            </a>
            </s:iterator>
        </div>
    </div>
</body>
</html>
