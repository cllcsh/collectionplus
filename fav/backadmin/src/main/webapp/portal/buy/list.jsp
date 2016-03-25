<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<%@ include  file="/portal/header.jsp"%>

    <link rel="stylesheet" href="/portal/css/style.css">

</head>
<body>
	<%@ include  file="/portal/inc.jsp"%>
    <%@ include  file="/portal/incbrandhot.jsp"%>

    <!-- 过滤条件、空结果、商品列表 -->
    <div class="comm-white-ctn">
        <div class="container">
			<s:if test="%{pageList.results != null && pageList.results.size > 0}">
			<form id="queryForm" name="queryForm" action="/buy_query.do" method="post">
            <s:hidden name="carForm.brandId"></s:hidden>
			<div class="mod-buy-brand-filter clearfix">
                <div class="logo"><img src="<s:property value='brandInfo.picPath'/>" alt=""></div>
                <h1 class="title"><s:property value='brandInfo.name'/></h1>
                <span class="split comm-text-lightgray">&gt;</span>
                <span class="select">
                	<s:select onchange="reloadPage(1);" cssClass="current" id="carVersionId" name="carForm.versionId" listKey="id" listValue="name" list="carVersionInfoList" headerKey="0" headerValue="全部版本"></s:select>
                </span>
                <span class="split comm-text-lightgray">&gt;</span>
                <span class="select">
                	<s:select onchange="reloadPage(1);" cssClass="current" id="seriesId" name="carForm.seriesId" listKey="id" listValue="name" list="seriesInfoList" headerKey="0" headerValue="全部系列"></s:select>
<%--                    <div class="current">全部车型</div>--%>
<%--                    <div class="menu">--%>
<%--                        <div class="item">全部车型</div>--%>
<%--                    </div>--%>
                </span>
                <span class="split comm-text-lightgray">&gt;</span>
                <span class="select">
                	<s:select onchange="reloadPage(1);" cssClass="current" id="modelsId" name="carForm.modelsId" listKey="id" listValue="name" list="modelsInfoList" headerKey="0" headerValue="全部车型"></s:select>
<%--                    <div class="current">全部年款</div>--%>
<%--                    <div class="menu">--%>
<%--                        <div class="item">全部年款</div>--%>
<%--                    </div>--%>
                </span>
                <span class="split comm-text-lightgray">&gt;</span>
                <span class="select">
                	<s:select onchange="reloadPage(1);" cssClass="current" id="modelyearId" name="carForm.modelyearId" listKey="id" listValue="name" list="modelyearInfoList" headerKey="0" headerValue="全部年款"></s:select>
<%--                    <div class="current">全部版本</div>--%>
<%--                    <div class="menu">--%>
<%--                        <div class="item">全部版本</div>--%>
<%--                    </div>--%>
                </span>
                <span class="split comm-text-lightgray">&gt;</span>
                <span class="select">
                	<s:select onchange="reloadPage(1);" cssClass="current" id="source" name="carForm.source" list="#{'0':'全部货源','1':'现货','2':'期货'}"></s:select>
<%--                    <div class="current">全部货源</div>--%>
<%--                    <div class="menu">--%>
<%--                        <div class="item">全部版本</div>--%>
<%--                    </div>--%>
                </span>
                <!-- <div class="selection">全部货源</div>
                <div class="selection">现货</div>
                <div class="selection">期货</div> -->
            </div>

            <div class="mod-buy-conditions">
                <h1 class="title comm-text-gray"><s:property value='brandInfo.name'/><small>共<s:property value='%{pageList.pages.total}'/>个商品</small></h1>
                <div class="condition-group">
                    <div class="label">外观：</div>
                    <div id="innercolorDiv" class="conditions">
                        <div class="item <s:if test='%{carForm.innercolorId == 0}'>active</s:if>" value="0">全部</div>
                        <s:iterator var="innercolor" value="innercolorInfoList">
                        <div class="item <s:if test='%{carForm.innercolorId == #innercolor.id}'>active</s:if>" value="<s:property value='#innercolor.id'/>"><s:property value='#innercolor.name'/></div>
                        </s:iterator>
                        <s:hidden id="innercolorId" name="carForm.innercolorId"></s:hidden>
                    </div>
                </div>
                <div class="condition-group">
                    <div class="label">内饰：</div>
                    <div id="outercolorDiv" class="conditions">
                        <div class="item <s:if test='%{carForm.outercolorId == 0}'>active</s:if>" value="0">全部</div>
                        <s:iterator var="outercolor" value="outercolorInfoList">
                        <div class="item <s:if test='%{carForm.outercolorId == #outercolor.id}'>active</s:if>" value="<s:property value='#outercolor.id'/>"><s:property value='#outercolor.name'/></div>
                        </s:iterator>
                        <s:hidden id="outercolorId" name="carForm.outercolorId"></s:hidden>
                    </div>
                </div>
                <div class="condition-group">
                    <div class="label">燃油：</div>
                    <div id="fuelDiv" class="conditions">
                        <div class="item <s:if test='%{carForm.fuel == null || carForm.fuel == ""}'>active</s:if>" value="">全部</div>
                        <div class="item <s:if test='%{carForm.fuel == "汽油"}'>active</s:if>" value="汽油">汽油</div>
                        <div class="item <s:if test='%{carForm.fuel == "柴油"}'>active</s:if>" value="柴油">柴油</div>
                        <div class="item <s:if test='%{carForm.fuel == "油电混合"}'>active</s:if>" value="油电混合">油电混合</div>
                        <div class="item <s:if test='%{carForm.fuel == "电动"}'>active</s:if>" value="电动">电动</div>
                        <s:hidden id="fuel" name="carForm.fuel"></s:hidden>
                    </div>
                </div>
                <div class="condition-group">
                    <div class="label">排量：</div>
                    <div id="enginesDiv" class="conditions">
                        <div class="item <s:if test='%{carForm.enginesId == 0}'>active</s:if>" value="0">全部</div>
                        <s:iterator var="engines" value="enginesInfoList">
                        <div class="item <s:if test='%{carForm.enginesId == #engines.id}'>active</s:if>" value="<s:property value='#engines.id'/>"><s:property value='#engines.name'/></div>
                        </s:iterator>
                        <s:hidden id="enginesId" name="carForm.enginesId"></s:hidden>
                    </div>
                </div>
                <div class="condition-group">
                    <div class="label">价位：</div>
                    <div id="searchPriceDiv" class="conditions">
                        <div class="item <s:if test='%{searchPrice == null || searchPrice == ""}'>active</s:if>" value="">全部</div>
                        <s:iterator var="price" value="searchPriceList">
                        <div class="item <s:if test='%{searchPrice == #price}'>active</s:if>" value="<s:property value='price'/>"><s:property value='price'/>万</div>
                        </s:iterator>
                        <s:hidden id="searchPrice" name="searchPrice"></s:hidden>
                    </div>
                </div>
                <div class="condition-group">
                    <div class="label">订金：</div>
                    <div id="depositRatioDiv" class="conditions">
                        <div class="item <s:if test='%{carForm.depositRatio == 0}'>active</s:if>" value="0">全部</div>
                        <div class="item <s:if test='%{carForm.depositRatio == 1}'>active</s:if>" value="1">10%</div>
                        <div class="item <s:if test='%{carForm.depositRatio == 2}'>active</s:if>" value="2">20%</div>
                        <div class="item <s:if test='%{carForm.depositRatio == 3}'>active</s:if>" value="3">30%</div>
                        <div class="item <s:if test='%{carForm.depositRatio == 4}'>active</s:if>" value="4">40%</div>
                        <div class="item <s:if test='%{carForm.depositRatio == 5}'>active</s:if>" value="5">50%</div>
                        <div class="item <s:if test='%{carForm.depositRatio == 6}'>active</s:if>" value="6">60%</div>
                        <div class="item <s:if test='%{carForm.depositRatio == 7}'>active</s:if>" value="7">70%</div>
                        <div class="item <s:if test='%{carForm.depositRatio == 8}'>active</s:if>" value="8">80%</div>
                        <div class="item <s:if test='%{carForm.depositRatio == 9}'>active</s:if>" value="9">90%</div>
                        <div class="item <s:if test='%{carForm.depositRatio == 10}'>active</s:if>" value="10">100%</div>
                        <s:hidden id="depositRatio" name="carForm.depositRatio"></s:hidden>
                    </div>
                </div>
                <div class="condition-group">
                    <div class="label">排序：</div>
                    <div id="orderDiv" class="conditions">
                    	<s:if test="%{orderName == 'insertDate'}">
                        <div class="item active" value="insertDate">时间
                        	<s:if test="%{orderType == 'desc'}"><span class="anchor-down"></span></s:if>
                        	<s:if test="%{orderType == 'asc'}"><span class="anchor-up"></span></s:if>
                        </div>
                        <div class="item" value="price">价格</div>
                    	</s:if>
                    	<s:else>
                        <div class="item" value="insertDate">时间 </div>
                        <div class="item active" value="price">价格 
                        	<s:if test="%{orderType == 'desc'}"><span class="anchor-down"></span></s:if>
                        	<s:if test="%{orderType == 'asc'}"><span class="anchor-up"></span></s:if>
                        </div>
                    	</s:else>
                    	<s:hidden id="orderName" name="orderName"></s:hidden>
                    	<s:hidden id="orderType" name="orderType"></s:hidden>
                    </div>
                </div>
            </div>
            <s:hidden id="cuurentPage" name="page"></s:hidden>
			</form>
			
            <div class="mod-buy-carlist">
            	<s:iterator var="car" value="%{pageList.results}">
                <div class="item">
                    <div class="inner">
                        <div class="img"><img src="<s:property value='#car.modelyearInfo.picPath1'/>" alt=""></div>
                        <h1 class="title"><s:property value='#car.modelyearInfo.name'/><s:property value='#car.brandInfo.name'/><s:property value='#car.seriesInfo.name'/><s:property value='#car.versionInfo.name'/></h1>
                        <div class="info-group">
                            <div class="number right"><span class="comm-text-red"><s:property value='#car.surplusNum'/></span>辆</div>
                            <div class="price comm-text-red">
                                <span class="comm-price">&yen;<strong><s:property value='#car.showPrice'/></strong>万</span>
                            </div>
                            <div class="comm-text-gray">比市场价低：&yen;<s:property value='#car.showDifferentPrice'/>万</div>
                        </div>
                        <a href="/buy_view.do?carForm.id=<s:property value='#car.id'/>" class="button button-primary">立即购买</a>
                        <a href="/buy_view.do?carForm.id=<s:property value='#car.id'/>" class="comm-text-gray">查看详情</a>
                    </div>
                </div>
            	</s:iterator>
            </div>
            <div class="comm-pagin clearfix">
                <a class="item" onclick="turnToPage(-1);">上一页</a>
            	<s:if test="%{(pageList.pages.allPage - page) > 4}">
            	<s:iterator begin="page" end="page + 3" var="currentPage">
            	<a onclick="turnToPage(<s:property value='#currentPage'/>);" class="item <s:if test='%{#currentPage == page}'>active</s:if>"><s:property value='#currentPage'/></a>
            	</s:iterator>
            	<a class="text">...</a>
            	</s:if>
            	<s:else>
            	<s:if test="%{0 >= (pageList.pages.allPage - 4)}">
            	<s:set name="firstPage" value="1"></s:set>
            	</s:if>
            	<s:else>
            	<s:set name="firstPage" value="pageList.pages.allPage - 3"></s:set>
            	</s:else>
            	<s:iterator begin="firstPage" end="pageList.pages.allPage" var="currentPage">
            	<a onclick="turnToPage(<s:property value='#currentPage'/>);" class="item <s:if test='%{#currentPage == page}'>active</s:if>"><s:property value='#currentPage'/></a>
            	</s:iterator>
            	</s:else>
                <a class="item" onclick="turnToPage(-2);">下一页</a>
                <div class="text">共<s:property value="pageList.pages.allPage"/>页，到第<input id="turnPage" type="text" value="<s:property value='page'/>">页</div>
                <a class="item" onclick="turnToPage(0);">确定</a>
                <s:hidden id="allPage" name="pageList.pages.allPage"></s:hidden>
            </div>
			</s:if>
			<s:else>
            <div class="comm-result">
                <div class="img"><i class="comm-img no-result"></i></div>
                <div class="title">抱歉，未找到您搜索的相关结果</div>
                <div class="body">前您<a onclick="javascript:history.go(-1);" style="cursor: pointer;">返回</a>重新选择</div>
            </div>
			</s:else>
        </div>
    </div>

    <%@include file="/portal/footer.jsp" %>

<script src="/portal/js/vendor/jquery.js"></script>
<script src="/portal/js/app.js"></script>
<script src="/portal/js/buy.js"></script>

</body>
</html>
