<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>车辆系列</title>
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
    <h1 class="title"><s:property value="brandInfo.name"/></h1>
</header>
    <div class="wrap">
		<!--
        <div class="comm-search-form">
            <form action="mbuy_queryByBrandId.do?carForm.brandId=<s:property value='#carForm.brandId'/>">
                <div class="input"><span class="placeholder"><i class="ico"></i>输入所查询的车名</span><input type="text" name="keyword"></div>
                <button type="submit">搜索</button>
            </form>
        </div>
		-->
        <div class="comm-car-list mod-serial-list" id="J-BuyList">
			<s:iterator var="carVersion" value="carVersionList">
			<s:iterator var="carSeries" value="#carVersion.carSeriesList">
            <a href="/mbuy_query.do?carForm.brandId=<s:property value='brandInfo.id'/>&carForm.versionId=<s:property value='#carVersion.versionId'/>&carForm.seriesId=<s:property value='#carSeries.seriesId'/>" class="product">
                <div class="img">
                    <img src="<s:property value='#carSeries.picPath'/>" alt="">
                </div>
                <div class="info">
                    <div class="title"><s:property value='brandInfo.name'/>
                                    <s:property value='#carSeries.seriesName'/>
                                    <s:property value='#carVersion.versionName'/></div>
                    <div class="price-info">
                        <div class="price left">发车价：<strong>&yen;<s:property value='#carSeries.showPrice'/>万</strong>起</div>
						<div class="number right">全国总数：<strong><s:property value='#carSeries.carNum'/></strong>辆</div>
                    </div>
                </div>
            </a>
			</s:iterator>
			</s:iterator>
        </div>
    </div>
    <script src="/m/bower_components/zepto/zepto.min.js"></script>
    <script src="/m/bower_components/zeptotouch/zepto-touch.min.js"></script>
    <script src="/m/vendor/zepto-selector.js"></script>
    <script src="/m/js/app.js"></script>
	<script>
	</script>
</body>
</html>