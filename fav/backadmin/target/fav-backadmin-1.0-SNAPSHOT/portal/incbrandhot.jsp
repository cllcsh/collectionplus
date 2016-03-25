<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="%{userSession != null}">
<div class="comm-index-brands">
    <div class="container">
        <div class="title">
            <h1>热门品牌 <small>HOT BRAND</small></h1>
            <div class="comm-img angle-right"></div>
        </div>
        <ul class="brand-list list-unstyled">
        	<s:iterator var="brandHot" value="#application.brandHotList" status="sta">
        	<s:if test="%{#sta.index < 5}">
        	<li>
                <a href="/buy_init.do?brandId=<s:property value='brandId'/>">
                    <div class="img"><img src="<s:property value='picPath'/>" alt=""></div>
                    <div class="text"><s:property value='brandName'/></div>
                </a>
            </li>
        	</s:if>
        	</s:iterator>
        </ul>
    </div>
</div>
</s:if>