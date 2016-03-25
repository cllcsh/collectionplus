<%@page contentType="text/html; charset=UTF-8"%>
<div class="comm-hotbrands">
	<h1 class="comm-section-title">热门品牌</h1>
	<ul class="list-unstyled">
	<s:iterator var="brandHot" value="#application.brandHotList" status="sta">
		<li><a href="/mbuy_init.do?brandId=<s:property value='brandId'/>"><div class="img"><img src="<s:property value='picPath'/>" alt=""></div><s:property value='brandName'/></a></li>
	</s:iterator>
    </ul>
</div>