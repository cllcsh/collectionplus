<%@page contentType="text/html; charset=UTF-8"%>
    <div class="comm-header">
    <div class="comm-fixed-bar">
        <div class="container clearfix">
            <div class="left">
                <a href="/home.do">网站首页</a>
                <span class="split">|</span>
                <a href="#">收藏本站</a>
                <span class="split">|</span>
                咨询电话：<i><a href="#"><span class="comm-img phone"></span> 400 - 966 - 8300</a></i>
            </div>
            <div class="right">
             <s:if test="%{userSession != null}">
						<!-- 王小波，您好 <span class="bubble">5</span> -->
						<!-- 如果用户通过授权，就给 J-HeaderMenu 添加 menu-active 类 -->
				 <s:if test="%{userSession.approveFlag == 0}">
					 <div id="J-HeaderMenu" class="menu">
					 <s:property value="userSession.userName"/><span class="comm-text-red">(审核中)</span>
				 </s:if>
				 <s:elseif test="%{userSession.approveFlag == 1}">
					 <div id="J-HeaderMenu" class="menu menu-active">
					 <s:property value="userSession.userName"/>，您好
				 </s:elseif>
				 <s:elseif test="%{userSession.approveFlag == 2}">
					 <div id="J-HeaderMenu" class="menu menu-active">
					 <s:property value="userSession.userName"/><span class="comm-text-red">(未通过)</span>
				 </s:elseif>
				 <s:else>
					 <div id="J-HeaderMenu" class="menu">
					 <s:property value="userSession.userName"/><span class="comm-text-red">(已冻结)</span>
					 </s:else>
							<span class="anchor"></span>
							<div class="submenu">
								<!--<a href="userportal_password.do" class="item"><i class="comm-img lock"></i> 修改密码</a>-->
								<a href="userportal_init.do" class="item"><i class="comm-img lock"></i> 中心首页</a>
								<a href="userportal_phone.do" class="item"><i class="comm-img mobile"></i>更换手机</a>
								<a href="userportal_profile.do" class="item"><i class="comm-img set"></i>修改资料</a>
								<a href="userportal_letter.do" class="item"><i class="comm-img msg"></i>站内信<span class="bubble"><s:property value="userSession.notReadLetterNum"/></span></a>
<!-- 								<a href="userportal_msg.do" class="item"><i class="comm-img msg"></i>通知消息</a> -->
							</div>
						</div>
						<span class="split">|</span>
						<a href="/home_logout.do" class="danger">退出</a>
			</s:if>
				<s:else>
					<a href="/home_reg.do">注册</a>
					<span class="split">|</span>
					<a href="/home_login.do">登录</a>
               	</s:else>
            </div>
        </div>
    </div>
    <div class="comm-header-ctn">
        <div class="container clearfix">
            <a href="/home.do" class="logo left">LOGO</a>
            <div class="searcher">
<%--                <div class="text">收藏平台共有<span class="comm-text-red">568</span>个车系，<span class="comm-text-red">4986</span>款车型上线</div>--%>
                <div class="text">收藏平台共有<span class="comm-text-red"><s:property value="#session.seriesCount"/></span>个车系，<span class="comm-text-red"><s:property value="#session.modelsCount"/></span>款车型上线</div>
                <form action="/buy_query.do" class="search-form" method="post">
                    <div class="input"><span class="placeholder"><i class="comm-img search"></i>搜索您想找的汽车型号</span><input id="J-SearchIpt" name="carForm.title" type="text" placeholder=""></div>
                    <button class="do-search">搜索</button>
                </form>
                <s:if test="%{userSession != null}">
                <div class="brands">
                	<s:iterator var="brandSearch" value="#application.brandSearchList" status="sta">
                	<s:if test="%{#sta.index < 10}">
                	<a href="/buy_init.do?brandId=<s:property value='brandId'/>"><s:property value="brandName"/></a>
                	</s:if>
                	</s:iterator>
                </div>
                </s:if>
            </div>
        </div>
    </div>
    <div class="comm-header-nav">
        <div class="container clearfix">
            <a href="/home.do" class="item <s:if test='%{actionType == "home"}'>active</s:if>">首页</a>
            <span class="split">|</span>
            <a href="/buy_init.do" class="item @@buy <s:if test='%{actionType == "buy"}'>active</s:if>">买车</a>
            <span class="split">|</span>
            <a href="/sell_init.do" class="item @@sell <s:if test='%{actionType == "sell"}'>active</s:if>">卖车</a>
            <span class="split">|</span>
            <a href="/orderprotal_init.do" class="item @@order <s:if test='%{actionType == "orderportal"}'>active</s:if>">订单</a>
            <span class="split">|</span>
            <a href="/dowloadprotal_init.do" class="item <s:if test='%{actionType == "downloadportal"}'>active</s:if>">下载APP</a>
        </div>
    </div>
</div>