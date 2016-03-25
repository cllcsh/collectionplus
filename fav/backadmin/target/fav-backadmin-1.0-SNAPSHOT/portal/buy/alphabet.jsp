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
    <%@ include  file="/portal/incbrandhot.jsp"%>

    <div class="comm-white-ctn">
        <div class="container">
            <div id="J-AlphaChoose" class="mod-buy-alphabet">
                <div class="title">按<span class="comm-text-red">拼音首字母</span>查找：</div>
                <div class="body">
                    <div class="j-alphas items">
                        <span>A</span>
                        <span>B</span>
                        <span>C</span>
                        <span>D</span>
                        <span>E</span>
                        <span>F</span>
                        <span>G</span>
                        <span>H</span>
                        <span>I</span>
                        <span>J</span>
                        <span>K</span>
                        <span>L</span>
                        <span>M</span>
                        <span>N</span>
                        <span>O</span>
                        <span>P</span>
                        <span>Q</span>
                        <span>R</span>
                        <span>S</span>
                        <span>T</span>
                        <span>U</span>
                        <span>V</span>
                        <span>W</span>
                        <span>X</span>
                        <span>Y</span>
                        <span>Z</span>
                    </div>
                    <div class="j-brands brands items">
                    </div>
                </div>
            </div>

<script src="/portal/js/vendor/jquery.js"></script>
<script src="/portal/js/app.js"></script>
<script>
    var choose = new Fache.UI.AlphaChoose({
        url : '/module/gxfc/brand_queryBypinyin.do', // 写下后端查询接口，比如: /brands，将会发起 /brands?alpha=A
        onBrandsReceive : function(data) {
            // 当点击字母，查询到对应的brands之后，这个函数会被调用
            // 你需要替换为服务端的数据
            var dataObj=eval("("+data+")");
            var tmpl = '';
            if(dataObj.message != undefined && dataObj.message.length>0){
	            for (var i in dataObj.message) {
                    tmpl += '<span value="' + dataObj.message[i].id + '">'+ dataObj.message[i].name +'</span>';
	            }
            }
            choose.brands.html(tmpl);
            $(".j-brands").children("span").on("click", function () {
                document.location.href = "/buy_init.do?brandId=" + $(this).attr("value");
            });
        },
        ok : function(brand) {
            // 选中品牌回调
        }
    });

$().ready(function () {
});
</script>
			<s:if test="%{firstCarList != null && firstCarList.size > 0}">
			<div class="mod-buy-alphabet-groups">
				<s:iterator var="firstCarInfo" value="firstCarList" status="carSta">
                <div class="group">
                    <h1 class="alphabet"><s:property value="#firstCarInfo.pinyin"/></h1>
                    <s:iterator var="carBrand" value="#firstCarInfo.carBrandList" status="carBrandSta">
                    <div class="brand-item">
                        <div class="brand">
                            <a class="comm-nounderline" href="/buy_query.do?carForm.brandId=<s:property value='#carBrand.brandId'/>">
                                <div class="img"><img src="<s:property value='#carBrand.picPath'/>" alt=""></div>
                                <h2><s:property value='#carBrand.brandName'/></h2>
                            </a>
                        </div>
                        <div class="cars-wrap">
                        	<s:iterator var="carVersion" value="#carBrand.carVersionList" status="carVersionSta">
                        	<div class="cars">
                                <div class="inner">
                                    <h3><s:property value="#carVersion.versionName"/></h3>
                                    <s:iterator var="carSeries" value="#carVersion.carSeriesList" status="carSeriesSta">
                                    <a href="/buy_query.do?carForm.brandId=<s:property value='#carBrand.brandId'/>&carForm.versionId=<s:property value='#carVersion.versionId'/>&carForm.seriesId=<s:property value='#carSeries.seriesId'/>" class="car">
                                        <div class="car-number"><span class="comm-text-red"><s:property value="#carSeries.carNum"/></span>辆</div>
                                        <div class="car-title"><s:property value="#carBrand.brandName"/><s:property value="#carSeries.seriesName"/></div>
                                        <div class="car-img">
                                        <s:if test="%{#carSeries.picPath == null || #carSeries.picPath == ''}">
                                        <img src="/portal/img/nocarpic.png" alt="">
                                        </s:if>
                                        <s:else>
                                        <img src="<s:property value='#carSeries.picPath'/>" alt="">
                                        </s:else>
                                        </div>
                                        <div class="car-price">指导价：<s:property value="#carSeries.guideShowName"/>万</div>
                                        <div class="car-price">平台底价：<strong class="comm-text-red"><s:property value="#carSeries.showPrice"/>万起</strong></div>
                                    </a>
                                    </s:iterator>
                                </div>
                            </div>
                        	</s:iterator>
                        </div>
                    </div>
                    </s:iterator>
                </div>
				</s:iterator>
			</div>
			</s:if>
			<s:else>
			<div class="comm-result">
                <div class="img"><i class="comm-img no-result"></i></div>
                <div class="title">抱歉，未找到您搜索的相关结果</div>
                <div class="body">建议您重新建华筛选内容或者尝试其他相关词</div>
            </div>
			</s:else>
        </div>
    </div>

<%@include file="../footer.jsp" %>
</body>
</html>
