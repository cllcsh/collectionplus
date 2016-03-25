<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>收藏</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="baidu-site-verification" content="hWGw6qbfv7" />
    <link rel="stylesheet" href="/m/bower_components/swiper/dist/css/swiper.min.css">
    <link rel="stylesheet" href="/m/css/style.css">

</head>
<body>
    <header class="comm-header">
        <h1 class="title"><div class="logo"></div></h1>
    </header>

    <div class="wrap">
        <div class="swiper-container comm-swiper">
            <div class="swiper-wrapper">
				<!--
                <div class="swiper-slide"><img src="/m/img/data/car.jpg" alt=""></div>
                <div class="swiper-slide"><img src="/m/img/data/car.jpg" alt=""></div>
                <div class="swiper-slide"><img src="/m/img/data/car.jpg" alt=""></div>
				-->
            	<s:iterator var="album" value="#application.albumList" status="sta">
               <div class="swiper-slide"><a href="#"><div class="img" style="background-image:url(<s:property value='picPath'/>)"></div></a></div>
            	</s:iterator>
				
            </div>
            <div class="swiper-pagination"></div>
        </div>

		<s:if test="%{userSession != null}">
        <div class="comm-car-list">
            <h2 class="comm-section-title">热门车型</h2>
			<s:iterator var="modelsHotInfo" value="%{pageList.results}">
            <a href="/mbuy_query.do?carForm.brandId=<s:property value='#modelsHotInfo.brandId'/>&carForm.seriesId=<s:property value='#modelsHotInfo.seriesId'/>" class="product">
                <div class="img">
                    <img src="<s:property value='#modelsHotInfo.modelyearInfo.picPath2'/>" alt="">
                </div>
                <div class="info">
                    <div class="title"><s:property value="#modelsHotInfo.modelyearName"/>款 <s:property value="#modelsHotInfo.seriesName"/> <s:property value="#modelsHotInfo.modelsName"/>  <s:property value="#modelsHotInfo.brandName"/> </div>
                    <div class="price-info">
                        <div class="price left">发车价：<strong>&yen;<s:property value="#modelsHotInfo.showPrice"/></strong>万元起</div>
                        <div class="number right">全国总数：<strong><s:property value="#modelsHotInfo.totalNum"/></strong>辆</div>
                    </div>
                </div>
            </a>
			</s:iterator>
        </div>
		</s:if>
    </div>
<%@include file="footer.jsp" %>
</div>


<script src="/m/bower_components/zepto/zepto.min.js"></script>
<script src="/m/bower_components/zeptotouch/zepto-touch.min.js"></script>
<script src="/m/js/app.js"></script>

    <script src="/m/bower_components/swiper/dist/js/swiper.min.js"></script>
    <script>
    $('.swiper-container').css({height: $(window).width()/2});
    var mySwiper = new Swiper ('.swiper-container', {
        loop: true,
        pagination: '.swiper-pagination',
        autoplay: 3000,
        loop: true
    });
    </script>
</body>
</html>
