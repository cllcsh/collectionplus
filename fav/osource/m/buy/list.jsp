<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>车辆列表</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="baidu-site-verification" content="hWGw6qbfv7" />
    <link rel="stylesheet" href="/m/css/style.css">

</head>
<body>
    <header class="comm-header">
    <div class="left opt">
        <a href="/mbuy_queryByBrandId.do?brandId=<s:property value='brandInfo.id'/>">返回</a>
    </div>
    <h1 class="title"><s:property value="brandInfo.name"/>-车源列表</h1>
</header>
    <div class="wrap">
		<!--
        <div class="comm-search-form">
            <form action="">
                <div class="input"><span class="placeholder"><i class="ico"></i>输入所查询的车名</span><input type="text" name="keyword"></div>
                <button type="submit">搜索</button>
            </form>
        </div>
		-->
        <div class="mod-buy-listtab">
        	<s:if test="%{orderName == 'insertDate'}">
            <div onclick="changeOrder(this);" class="item <s:if test='%{orderType == "desc"}'>down</s:if><s:if test='%{orderType == "asc"}'>up</s:if>" value="insertDate">时间
            	<span class="anchor"></span>
            </div>
            <div onclick="changeOrder(this);" class="item" value="price">价格 <span class="anchor"></span></div>
        	</s:if>
        	<s:else>
            <div onclick="changeOrder(this);" class="item" value="insertDate">时间<span class="anchor"></span> </div>
            <div onclick="changeOrder(this);" class="item <s:if test='%{orderType == "desc"}'>down</s:if><s:if test='%{orderType == "asc"}'>up</s:if>" value="price">价格 
            	<span class="anchor"></span>
            </div>
        	</s:else>
            <div class="item" onclick="javascript:Fache.UI.FilterPanel.show();">筛选 <i class="ico-filter"></i></div>
        </div>

        <div class="mod-buy-car-list" id="J-CarList">
		<s:iterator var="carInfo" value="%{pageList.results}">
            <a href="/mbuy_view.do?carForm.id=<s:property value='#carInfo.id'/>" class="item">
                <div class="img">
                    <img src="<s:property value='#car.modelyearInfo.picPath2'/>" alt="">
                </div>
                <div class="info">
                    <div class="title">
									<s:property value='#carInfo.brandInfo.name'/>
                                    <s:property value='#carInfo.seriesInfo.name'/>
                                    <s:property value='#carInfo.modelyearInfo.name'/><s:property value='#carInfo.carVersionInfo.name'/>
					</div>
                    <span class="number"><span class="comm-text-red"><s:property value='#carInfo.num'/></span>辆</span>
                    <div class="price">
                        <span class="comm-price comm-text-red">&yen;<strong><s:property value='#carInfo.showPrice'/></strong>万</span>
                        &nbsp;&nbsp;&nbsp;
                        <span class="comm-text-gray">比市场价低：&yen;<s:property value='#carInfo.showDifferentPrice'/>万</span>
                    </div>
                </div>
            </a>
		</s:iterator>	
        </div>
        <div class="comm-loadmore" data-offset="1" data-list-id="J-CarList" data-size="10" data-url="/mbuy_query.do?orderName=<s:property value='orderName'/>&orderType=<s:property value='orderType'/>&carForm.brandId=<s:property value='carForm.brandId'/>&carForm.versionId=<s:property value='carForm.versionId'/>&carForm.seriesId=<s:property value='carForm.seriesId'/>&carForm.modelsId=<s:property value='carForm.modelsId'/>&carForm.modelyearId=<s:property value='carForm.modelyearId'/>&carForm.source=<s:property value='carForm.source'/>&carForm.outercolorId=<s:property value='carForm.outercolorId'/>&carForm.innercolorId=<s:property value='carForm.innercolorId'/>&carForm.fuel=<s:property value='carForm.fuel'/>&carForm.enginesId=<s:property value='carForm.enginesId'/>&searchPrice=<s:property value='carForm.searchPrice'/>&carForm.depositRatio=<s:property value='carForm.depositRatio'/>">点击加载更多</div>

        <div id="J-FPanel" class="mod-buy-fpanel">
            <div class="inner">
                <div class="header j-header0">
                    <div class="left">取消</div>
                    <div class="right" onclick="reloadPage();">确定</div>
                    <div class="title">筛选</div>
                </div>
                <div class="comm-form">
                	<form id="queryForm" name="queryForm" action="/mbuy_query.do" method="get">
		        	<s:hidden id="orderName" name="orderName"></s:hidden>
		        	<s:hidden id="orderType" name="orderType"></s:hidden>
		        	<s:hidden name="carForm.brandId"></s:hidden>
                    <div class="form-group">
                        <span class="label">版本</span>
                        <div class="input">
                            <div class="comm-selector" data-single="1" data-onchange="onVersionChange">
                                <span class="value"><s:if test="%{carForm.versionName == null || carForm.versionName == ''}">全部</s:if><s:else><s:property value="carForm.versionName"/></s:else></span>
                                <div class="options">
                                    <div class="item" data-key="0" data-value="全部"></div>
                                    <s:iterator var="versionInfo" value="carVersionInfoList">
                                    <div class="item" data-key="<s:property value='#versionInfo.id'/>" data-value="<s:property value='#versionInfo.name'/>"></div>
                                    </s:iterator>
                                </div>
                                <s:hidden name="carForm.versionId"></s:hidden>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <span class="label">系列</span>
                        <div class="input">
                            <div class="comm-selector" data-single="1" data-onchange="onSeriesChange">
                                <span class="value" id="seriesValue"><s:if test="%{carForm.seriesName == null || carForm.seriesName == ''}">全部</s:if><s:else><s:property value="carForm.seriesName"/></s:else></span>
                                <div class="options" id="seriesOption">
                                    <div class="item" data-key="0" data-value="全部"></div>
                                    <s:iterator var="seriesInfo" value="seriesInfoList">
                                    <div class="item" data-key="<s:property value='#seriesInfo.id'/>" data-value="<s:property value='#seriesInfo.name'/>"></div>
                                    </s:iterator>
                                </div>
                                <s:hidden id="seriesId" name="carForm.seriesId"></s:hidden>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <span class="label">车型</span>
                        <div class="input">
                            <div class="comm-selector" data-single="1" data-onchange="onModelsChange">
                                <span class="value" id="modelsValue"><s:if test="%{carForm.modelsName == null || carForm.modelsName == ''}">全部</s:if><s:else><s:property value="carForm.modelsName"/></s:else></span>
                                <div class="options" id="modelsOption">
                                    <div class="item" data-key="0" data-value="全部"></div>
                                    <s:iterator var="modelsInfo" value="modelsInfoList">
                                    <div class="item" data-key="<s:property value='#modelsInfo.id'/>" data-value="<s:property value='#modelsInfo.name'/>"></div>
                                    </s:iterator>
                                </div>
                                <s:hidden id="modelsId" name="carForm.modelsId"></s:hidden>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <span class="label">年款</span>
                        <div class="input">
                            <div class="comm-selector" data-single="1" data-onchange="onModelyearChange">
                                <span class="value" id="modelyearValue"><s:if test="%{carForm.modelyearName == null || carForm.modelyearName == ''}">全部</s:if><s:else><s:property value="carForm.modelyearName"/></s:else></span>
                                <div class="options" id="modelyearOption">
                                    <div class="item" data-key="0" data-value="全部"></div>
                                    <s:iterator var="modelyearInfo" value="modelyearInfoList">
                                    <div class="item" data-key="<s:property value='#modelyearInfo.id'/>" data-value="<s:property value='#modelyearInfo.name'/>"></div>
                                    </s:iterator>
                                </div>
                                <s:hidden id="modelyearId" name="carForm.modelyearId"></s:hidden>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <span class="label">货源</span>
                        <div class="input">
                            <div class="comm-selector" data-single="1">
                                <span class="value">
                                <s:if test="%{carForm.source == 1}">现货</s:if>
                                <s:elseif test="%{carForm.source == 2}">期货</s:elseif>
                                <s:else>全部</s:else>
                                </span>
                                <div class="options">
                                    <div class="item" data-key="0" data-value="全部"></div>
                                    <div class="item" data-key="1" data-value="现货"></div>
                                    <div class="item" data-key="2" data-value="期货"></div>
                                </div>
                                <s:hidden id="source" name="carForm.source"></s:hidden>
                            </div>
                        </div>
                    </div>
                    <div class="split"></div>
                    <div class="form-group">
                        <span class="label">外饰</span>
                        <div class="input">
                            <div class="comm-selector" data-single="1">
                                <span class="value" id="outercolorValue"><s:if test="%{carForm.outercolorName == null || carForm.outercolorName == ''}">全部</s:if><s:else><s:property value="carForm.outercolorName"/></s:else></span>
                                <div class="options" id="outercolorOption">
                                    <div class="item" data-key="0" data-value="全部"></div>
                                    <s:iterator var="outercolorInfo" value="outercolorInfoList">
                                    <div class="item" data-key="<s:property value='#outercolorInfo.id'/>" data-value="<s:property value='#outercolorInfo.name'/>"></div>
                                    </s:iterator>
                                </div>
                                <s:hidden id="outercolorId" name="carForm.outercolorId"></s:hidden>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <span class="label">内饰</span>
                        <div class="input">
                            <div class="comm-selector" data-single="1">
                                <span class="value" id="innercolorValue"><s:if test="%{carForm.innercolorName == null || carForm.innercolorName == ''}">全部</s:if><s:else><s:property value="carForm.innercolorName"/></s:else></span>
                                <div class="options" id="innercolorOption">
                                    <div class="item" data-key="0" data-value="全部"></div>
                                    <s:iterator var="innercolorInfo" value="innercolorInfoList">
                                    <div class="item" data-key="<s:property value='#innercolorInfo.id'/>" data-value="<s:property value='#innercolorInfo.name'/>"></div>
                                    </s:iterator>
                                </div>
                                <s:hidden id="innercolorId" name="carForm.innercolorId"></s:hidden>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <span class="label">燃油</span>
                        <div class="input">
                            <div class="comm-selector" data-single="1">
                                <span class="value"><s:if test="%{carForm.fuel == null || carForm.fuel == ''}">全部</s:if><s:else><s:property value="carForm.fuel"/></s:else></span>
                                <div class="options">
                                    <div class="item" data-key=" " data-value="全部"></div>
                                    <div class="item" data-key="汽油" data-value="汽油"></div>
                                    <div class="item" data-key="柴油" data-value="柴油"></div>
                                    <div class="item" data-key="油电混合" data-value="油电混合"></div>
                                    <div class="item" data-key="电动" data-value="电动"></div>
                                </div>
                                <s:hidden id="fuel" name="carForm.fuel"></s:hidden>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <span class="label">排量</span>
                        <div class="input">
                            <div class="comm-selector" data-single="1">
                                <span class="value" id="enginesValue"><s:if test="%{carForm.enginesName == null || carForm.enginesName == ''}">全部</s:if><s:else><s:property value="carForm.enginesName"/></s:else></span>
                                <div class="options" id="enginesOption">
                                    <div class="item" data-key="0" data-value="全部"></div>
                                    <s:iterator var="enginesInfo" value="enginesInfoList">
                                    <div class="item" data-key="<s:property value='#enginesInfo.id'/>" data-value="<s:property value='#enginesInfo.name'/>"></div>
                                    </s:iterator>
                                </div>
                                <s:hidden id="enginesId" name="carForm.enginesId"></s:hidden>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <span class="label">价位</span>
                        <div class="input">
                            <div class="comm-selector" data-single="1">
                                <span class="value" id="searchPriceValue"><s:if test="%{searchPrice == null || searchPrice == ''}">全部</s:if><s:else><s:property value="searchPrice"/>万</s:else></span>
                                <div class="options">
                                    <div class="item" data-key=" " data-value="全部"></div>
                                    <s:iterator var="price" value="searchPriceList">
                                    <div class="item" data-key="<s:property />" data-value="<s:property />万"></div>
                                    </s:iterator>
                                </div>
                                <s:hidden id="searchPrice" name="searchPrice"></s:hidden>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <span class="label">订金</span>
                        <div class="input">
                            <div class="comm-selector" data-single="1">
                                <span class="value">
                                <s:if test="%{carForm.depositRatio == 1}">10%</s:if>
                                <s:elseif test="%{carForm.depositRatio == 2}">20%</s:elseif>
                                <s:elseif test="%{carForm.depositRatio == 3}">30%</s:elseif>
                                <s:elseif test="%{carForm.depositRatio == 4}">40%</s:elseif>
                                <s:elseif test="%{carForm.depositRatio == 5}">50%</s:elseif>
                                <s:elseif test="%{carForm.depositRatio == 6}">60%</s:elseif>
                                <s:elseif test="%{carForm.depositRatio == 7}">70%</s:elseif>
                                <s:elseif test="%{carForm.depositRatio == 8}">80%</s:elseif>
                                <s:elseif test="%{carForm.depositRatio == 9}">90%</s:elseif>
                                <s:elseif test="%{carForm.depositRatio == 10}">100%</s:elseif>
                                <s:else>全部</s:else>
                                </span>
                                <div class="options">
                                    <div class="item" data-key="0" data-value="全部"></div>
                                    <div class="item" data-key="1" data-value="10%"></div>
                                    <div class="item" data-key="2" data-value="20%"></div>
                                    <div class="item" data-key="3" data-value="30%"></div>
                                    <div class="item" data-key="4" data-value="40%"></div>
                                    <div class="item" data-key="5" data-value="50%"></div>
                                    <div class="item" data-key="6" data-value="60%"></div>
                                    <div class="item" data-key="7" data-value="70%"></div>
                                    <div class="item" data-key="8" data-value="80%"></div>
                                    <div class="item" data-key="9" data-value="90%"></div>
                                    <div class="item" data-key="10" data-value="100%"></div>
                                </div>
                                <s:hidden id="depositRatio" name="carForm.depositRatio"></s:hidden>
                            </div>
                        </div>
                    </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
<script src="/m/bower_components/zepto/zepto.min.js"></script>
<script src="/m/bower_components/zeptotouch/zepto-touch.min.js"></script>
<script src="/m/vendor/zepto-selector.js"></script>
<script src="/m/js/app.js"></script>
<script src="/m/js/buy.js"></script>
<script>
window.onload = function(){
    Fache.bindLoadMore();
}
//此方法是售卖车型
var onVersionChange = function(selected) {
	choseSeries(selected);
};

var onSeriesChange = function(selected) {
	choseEngines(selected);
	choseModels(selected);
};

//selected 是选中的选项的 array，请自行查看
var onModelsChange = function(selected) {
	choseModelyear(selected);
};

var onModelyearChange = function(selected) {
	choseInnercolor(selected);
	choseOutercolor(selected);
};
</script>
</body>
</html>
