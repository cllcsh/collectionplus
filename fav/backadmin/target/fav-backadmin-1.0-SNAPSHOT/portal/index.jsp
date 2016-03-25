<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<%@ include  file="/portal/header.jsp"%>
    <link rel="stylesheet" href="/portal/css/style.css">
    <link rel="stylesheet" href="/portal/vendor/bjqs/bjqs.css">
</head>
<body>
	<%@ include  file="inc.jsp"%>
    <div class="comm-wrap">
        <div id="J-Banner" class="module-index-banner">
            <ul class="bjqs">
            	<s:iterator var="album" value="#application.albumList" status="sta">
                <li style="background-image:url(<s:property value='picPath'/>);"></li>
            	</s:iterator>
            </ul>
        </div>
    </div>

    <%@include file="incbrandhot.jsp"%>

    <div class="module-index-products">
    	<s:set name="num" value="(pageList.results.size - 1) / 2"></s:set>
    	<s:set name="first" value="0"></s:set>
    	<s:iterator var="cur" begin="#first" end="#num" status="secSta">
    	<div class="sec <s:if test='%{#secSta.odd}'>sec-1</s:if>">
            <div class="container">
                <div class="row">
                	<s:set name="end" value="pageList.results.size - 1"></s:set>
                	<s:if test="%{(pageList.results.size - 1) > (#cur * 2 + 1)}">
                	<s:set name="end" value="#cur * 2 + 1"></s:set>
                	</s:if>
			    	<s:iterator var="modelsHotInfo" value="%{pageList.results}" begin="#cur * 2" end="#end">
                    <div class="col col-6">
                        <a href="/buy_query.do?carForm.brandId=<s:property value='#modelsHotInfo.brandId'/>&carForm.versionId=<s:property value='#modelsHotInfo.versionId'/>&carForm.seriesId=<s:property value='#modelsHotInfo.seriesId'/>" class="product">
                            <div class="img img-1">
                                <div class="img-1-title">
                                    <div class="car-title car-time"><s:property value="#modelsHotInfo.modelyearName"/> <s:property value="#modelsHotInfo.modelsName"/></div>
                                    <br>
                                    <div class="car-title car-name"><s:property value="#modelsHotInfo.brandName"/>-<s:property value="#modelsHotInfo.seriesName"/></div>
                                </div>
                                <s:if test="%{#modelsHotInfo.modelyearInfo.picPath2 == null || #modelsHotInfo.modelyearInfo.picPath2 == ''}">
                                <img src="/portal/img/nocarpic.png" alt="">
                                </s:if>
                                <s:else>
                                <img src="<s:property value='#modelsHotInfo.modelyearInfo.picPath2'/>" alt="">
                                </s:else>
                            </div>
                            <div class="img-group">
                                <div class="img img-2">
                                <s:if test="%{#modelsHotInfo.modelyearInfo.picPath4 == null || #modelsHotInfo.modelyearInfo.picPath4 == ''}">
                                <img src="/portal/img/nocarpic.png" alt="">
                                </s:if>
                                <s:else>
                                <img src="<s:property value='#modelsHotInfo.modelyearInfo.picPath4'/>" alt="">
                                </s:else>
                                </div>
                                <div class="img img-3">
                                <s:if test="%{#modelsHotInfo.modelyearInfo.picPath5 == null || #modelsHotInfo.modelyearInfo.picPath5 == ''}">
                                <img src="/portal/img/nocarpic.png" alt="">
                                </s:if>
                                <s:else>
                                <img src="<s:property value='#modelsHotInfo.modelyearInfo.picPath5'/>" alt="">
                                </s:else>
                                </div>
                            </div>
                            <div class="info">
                                <div class="price left">发车价：<strong>&yen;<s:property value="#modelsHotInfo.showPrice"/>万</strong>起</div>
                                <div class="comm-img red-yellow-split"></div>
                                <div class="number right">全国总数：<strong><s:property value="#modelsHotInfo.totalNum"/></strong>辆</div>
                            </div>
                        </a>
                    </div>
			    	</s:iterator>
                </div>
            </div>
        </div>
    	</s:iterator>
    </div>

<%@include file="footer.jsp" %>
<script src="/portal/js/vendor/jquery.js"></script>
<script src="/portal/js/app.js"></script>

    <script src="/portal/vendor/bjqs/bjqs-1.3.min.js"></script>
    <script>
    $(function(){
        $('#J-Banner').bjqs({
    		'height' : 400,
    		'width' : '100%',
            'showcontrols' : false
    	});

        new FACHE.UI.Btp();

        var interceptError = "<s:property value='#request.interceptError'/>";
        if ($.trim(interceptError) != "") {
        	new FACHE.UI.Modal({
                type : 'warn',
                body : '<s:property value="#request.interceptError"/>',
                btnText : '逛逛首页',
                close : function(){},
                ok: function(){window.location.href = '/';}
            });
        }
    });
    </script>
</body>
</html>
