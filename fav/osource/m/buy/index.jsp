<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>我要买车</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="baidu-site-verification" content="hWGw6qbfv7" />
    <link rel="stylesheet" href="/m/css/style.css">

</head>
<body>
    <header class="comm-header">
        <h1 class="title">我要买车</h1>
    </header>

    <div class="wrap" style="margin-bottom: 5rem;">
	    <!--
        <div class="comm-search-form">
            <form action="">
                <div class="input"><span class="placeholder"><i class="ico"></i>输入所查询的车名</span><input type="text" name="keyword"></div>
                <button type="submit">搜索</button>
            </form>
        </div>-->
 		<%@include file="/m/incbrandhot.jsp" %>
        
		<s:if test="%{buyBrandPinyinInfoList != null && buyBrandPinyinInfoList.size > 0}">
        <div class="comm-brand-groups">
            <s:iterator var="brandPinyinInfo" value="buyBrandPinyinInfoList" status="carSta">
			<div class="group" id="<s:property value='#brandPinyinInfo.pinyin'/>">
                <h2 class="title"><s:property value='#brandPinyinInfo.pinyin'/></h2>
                <ul class="list-unstyled">
					<s:iterator var="carBrand" value="#brandPinyinInfo.buyBrandInfoList" status="carBrandSta">
                    <li>
                        <a href="/mbuy_queryByBrandId.do?brandId=<s:property value='#carBrand.brandId'/>">
                            <div class="img"><img src="<s:property value='#carBrand.picPath'/>" alt=""></div>
                            <div class="info">
                                <span class="car-title"><s:property value='#carBrand.brandName'/></span>
                            </div>
                        </a>
                    </li>
					</s:iterator>
                </ul>
            </div>
			</s:iterator>
        </div>
		</s:if>
        <div class="comm-alphabet-list">
            <div class="inner">
            	<s:iterator var="brandPinyinInfo" value="buyBrandPinyinInfoList" status="carSta">
                <a href="#<s:property value='#brandPinyinInfo.pinyin'/>"><s:property value='#brandPinyinInfo.pinyin'/></a>
            	</s:iterator>
            </div>
        </div>
    </div>
 <%@include file="/m/buy/footer.jsp" %>
</div>
</body>
</html>
