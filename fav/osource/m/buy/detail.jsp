<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>车辆详情</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="baidu-site-verification" content="hWGw6qbfv7" />
    <link rel="stylesheet" href="/m/bower_components/swiper/dist/css/swiper.min.css">
    <link rel="stylesheet" href="/m/css/style.css">

</head>
<body class="white">
    <header class="comm-header">
    <div class="left opt">
        <a href="javascript:window.history.go(-1);">返回</a>
    </div>
    <h1 class="title">车辆详情</h1>
</header>
    <div class="wrap" style="margin-bottom: 5rem;">
        <div class="swiper-container comm-swiper">
            <div class="swiper-wrapper">
                <div class="swiper-slide"><img src="<s:property value='carInfo.modelyearInfo.picPath1'/>" alt=""></div>
                <div class="swiper-slide"><img src="<s:property value='carInfo.modelyearInfo.picPath2'/>" alt=""></div>
                <div class="swiper-slide"><img src="<s:property value='carInfo.modelyearInfo.picPath3'/>" alt=""></div>
                <div class="swiper-slide"><img src="<s:property value='carInfo.modelyearInfo.picPath4'/>" alt=""></div>
                <div class="swiper-slide"><img src="<s:property value='carInfo.modelyearInfo.picPath5'/>" alt=""></div>
                <div class="swiper-slide"><img src="<s:property value='carInfo.modelyearInfo.picPath6'/>" alt=""></div>
            </div>
            <div class="swiper-pagination"></div>
        </div>

        <div class="mod-buy-detail">
            <div class="title">
                <div class="img"></div>
                <h1><s:property value='carInfo.title'/></h1>
            </div>
            <div class="price-info">
                <div class="info-group">
                    零售价：<span class="comm-text-red comm-price">&yen; <strong><s:property value='carInfo.showPrice'/>万</strong></span>
                    <div class="right">库存：<span class="comm-text-red"><s:property value='carInfo.surplusNum'/></span>辆</div>
                </div>
                <div class="info-group comm-text-gray">市场指导价：<s:property value='carInfo.modelyearInfo.showPrice'/>万，为您节省：<s:property value='carInfo.showDifferentPrice'/>万</div>
                <div class="info-group">
                    汽车状态：<span class="comm-text-huge">
                    <s:if test="%{carInfo.source == 1}">现货</s:if>
                    <s:if test="%{carInfo.source == 2}">期货</s:if>
                    </span>
                </div>
            </div>
            <div class="comm-form">
            	<%-- <div class="form-group">
                    <span class="label">所在地区：</span>
                    <div class="input"><s:property value="carInfo.area"/></div>
                </div> --%>
                <div class="form-group">
                    <span class="label">燃油：</span>
                    <div class="input"><s:property value="carInfo.fuel"/></div>
                </div>
                <div class="form-group">
                    <span class="label">排量：</span>
                    <div class="input"><s:property value="carInfo.enginesName"/></div>
                </div>
                <div class="form-group">
                    <span class="label">车型：</span>
                    <div class="input"><s:property value="carInfo.modelyearInfo.name"/><s:property value="carInfo.brandInfo.name"/><s:property value="carInfo.seriesInfo.name"/><s:property value="carInfo.modelsInfo.name"/></div>
                </div>
                <div class="form-group">
                    <span class="label">车身颜色：</span>
                    <div class="input"><s:property value="carInfo.innercolorName"/></div>
                </div>
                <div class="form-group">
                    <span class="label">内饰颜色：</span>
                    <div class="input"><s:property value="carInfo.outercolorName"/></div>
                </div>
                <div class="form-group" style="display:none;">
                    <span class="label">所在省份：</span>
                    <div class="input">咨询400客服</div><!-- <s:property value="carInfo.province"/> -->
                </div>
                <div class="form-group">
                    <span class="label">金额（百分比）：</span>
                    <div class="input">
                    	<s:if test="%{carInfo.depositRatio == 1}">10%</s:if>
                       	<s:elseif test="%{carInfo.depositRatio == 2}">20%</s:elseif>
                       	<s:elseif test="%{carInfo.depositRatio == 3}">30%</s:elseif>
                       	<s:elseif test="%{carInfo.depositRatio == 4}">40%</s:elseif>
                       	<s:elseif test="%{carInfo.depositRatio == 5}">50%</s:elseif>
                       	<s:elseif test="%{carInfo.depositRatio == 6}">60%</s:elseif>
                       	<s:elseif test="%{carInfo.depositRatio == 7}">70%</s:elseif>
                       	<s:elseif test="%{carInfo.depositRatio == 8}">80%</s:elseif>
                       	<s:elseif test="%{carInfo.depositRatio == 9}">90%</s:elseif>
                       	<s:elseif test="%{carInfo.depositRatio == 10}">100%</s:elseif>
                    </div>
                </div>
                <div class="form-group">
                    <span class="label">提货周期：</span>
                    <div class="input"> 
                    	<s:if test="%{carInfo.logistics == 0}">任意</s:if>
                       	<s:elseif test="%{carInfo.logistics == 3}">3天</s:elseif>
                       	<s:elseif test="%{carInfo.logistics == 3}">7天</s:elseif>
                       	<s:elseif test="%{carInfo.logistics == 3}">15天</s:elseif>
                       	<s:elseif test="%{carInfo.logistics == 3}">30天</s:elseif>
                    </div>
                </div>
                <div class="form-group">
                    <span class="label">物流方式：</span>
                    <div class="input"> 
                    	<s:if test="%{carInfo.deliveryPeriod == 1}">买家自提</s:if>
                       	<s:elseif test="%{carInfo.deliveryPeriod == 2}">卖家配送</s:elseif>
                       	<s:elseif test="%{carInfo.deliveryPeriod == 3}">平台配送</s:elseif>
                    </div>
                </div>
                <s:if test="%{carInfo.carStatus > 0}">
	            <div class="special-desc">
	                <div class="title"><i class="ico-help"></i> 特殊说明：</div>
	                <div class="body" style="font-size:16px;color:#000000;">&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="carInfo.remark"/></div>
	            </div>
                </s:if>
        </div>

        <!-- 图表 -->
        <div class="mod-buy-chartinfo">
            <div class="tabs">
                <a id="realLine" href="javascript:viewReal();" class="item">实时</a>
                <a id="dayLine" href="javascript:viewDay();" class="item">日线</a>
                <a id="weekLine" href="javascript:viewWeek();" class="item">周线</a>
            </div>
            <div class="chart">
                <div class="title">底价走势（万元）</div>
                <canvas id="J-Canvas" width="320" height="200">你的设备不支持图表查看</canvas>
<%--                <p class="body text-center">--%>
<%--                    市场解读：<span class="line green"></span>走势平稳，可放心购买--%>
<%--                </p>--%>
            </div>
        </div>
        <!-- ./图表 -->

        <!-- 步骤 挑选车源－支付订金－锁定车源－支付余款－物流配送－交易完成-->
        <div class="mod-buy-process">
            <div class="title">购车流程</div>
            <div class="step">挑选车源</div>
            <span class="split">&gt;</span>
            <div class="step">支付订金</div>
            <span class="split">&gt;</span>
            <div class="step">锁定车源</div>
            <span class="split">&gt;</span>
            <div class="step step-two">支付余款<br>物流配送</div>
            <span class="split">&gt;</span>
            <div class="step">交易完成</div>
        </div>
        <!-- ./步骤 -->

        <!-- 配置详情 -->
        <%-- 
        <div id="tab0" class="mod-buy-tab0 mod-buy-tab">
            <div class="mod-buy-tab-title">
                <h2>配置详情</h2>
            </div>
            <div class="inner">
                <h3 class="title">基础参数</h3>
                    <table>
                        <tr>
                            <td class="strong">功率</td>
                            <td class="text-right"><s:property value="carInfo.modelyearInfo.efficiency"/></td>
                        </tr>
                        <tr>
                            <td class="strong">驱动方式</td>
                            <td class="text-right"><s:property value="carInfo.modelyearInfo.driving"/></td>
                        </tr>
                    </table>
                    <table>
                        <tr>
                            <td class="strong">座位数</td>
                            <td class="text-right"><s:property value="carInfo.modelyearInfo.seats"/></td>
                        </tr>
                        <tr style="display:none;">
                            <td class="strong">油耗</td>
                            <td class="text-right"><s:property value="carInfo.modelyearInfo.fuel"/></td>
                        </tr>
                    </table>
                <h3 class="title">标准配置</h3>
                <ul class="list-unstyled">
                    <s:property value="carInfo.standardInfo.name.replaceAll('\r\n','<br/>')" escape="false"/>
                </ul>
                <h3 class="title">亮点套件</h3>
                <p><img src="/m/img/data/car.jpg" alt=""></p>
                <p>从之前刚开始都是一些很入门的话题，到后来也会有团从之前刚开始都是一些很入门的话题，到后来也会有团</p>
                <p>做点什么永</p>
            </div>
        </div>
         --%>
        <!-- ./亮点配置 -->

        <!-- 车辆手续 -->
        <s:if test="%{carInfo.procedures != 0}">
	        <div id="tab1" class="mod-buy-tab1 mod-buy-tab">
	            <div class="mod-buy-tab-title">
	                <h2>车辆手续</h2>
	            </div>
	            <div class="inner">
	                <div class="row">
	                    <p>
							因排放标准或政策原因不能上牌？收藏网承诺向购车用户提供所购车辆办理牌照所需的全部合法手续！
						</p>
						<p>
							收藏网所售车辆，均为保税港口进口，合法完税，并经由海关商检、检验、检疫证明，手续完备，质量可靠，是合法进口的车辆。
						</p>
						<s:if test="%{(carInfo.procedures & 1) == 1}">
							<img src="/portal/img/procedure/1.png" alt="">
						</s:if>
						<s:if test="%{(carInfo.procedures & 2) == 2}">
							<img src="/portal/img/procedure/2.png" alt="">
						</s:if>
						<s:if test="%{(carInfo.procedures & 4) == 4}">
							<img src="/portal/img/procedure/4.png" alt="">
						</s:if>
						<s:if test="%{(carInfo.procedures & 8) == 8}">
							<img src="/portal/img/procedure/8.png" alt="">
						</s:if>
						<s:if test="%{(carInfo.procedures & 16) == 16}">
							<img src="/portal/img/procedure/16.png" alt="">
						</s:if>
						<s:if test="%{(carInfo.procedures & 32) == 32}">
							<img src="/portal/img/procedure/32.png" alt="">
						</s:if>
						<s:if test="%{(carInfo.procedures & 64) == 64}">
							<img src="/portal/img/procedure/64.jpg" alt="">
						</s:if>
						<s:if test="%{(carInfo.procedures & 128) == 128}">
							<img src="/portal/img/procedure/128.jpg" alt="">
						</s:if>
						<s:if test="%{(carInfo.procedures & 256) == 256}">
							<img src="/portal/img/procedure/256.jpg" alt="">
						</s:if>
	                </div>
	            </div>
	        </div>
        </s:if>
        <!-- ./车辆手续 -->

        <!-- 购车需知 -->
        <div class="mod-buy-tab2 mod-buy-tab">
            <div class="mod-buy-tab-title">
                <h2>购车需知</h2>
            </div>
            <div class="inner">
                <div class="group">
                    <div class="icon"><i class="ico-phone"></i></div>
                    <div class="text">
                        <h3 class="title">预约说明</h3>
                        <p>登陆帐号，挑选中意车款与数量，完成在线支付（第三方支付）</p>
                    </div>
                </div>
                <div class="group">
                    <div class="icon"><i class="ico-car"></i></div>
                    <div class="text">
                        <h3 class="title">提车点</h3>
                        <p>由收藏根据地区城市就近安排指定地点提车</p>
                    </div>
                </div>
                <div class="group">
                    <div class="icon"><i class="ico-calendar"></i></div>
                    <div class="text">
                        <h3 class="title">需知细则</h3>
                        <p>
						1.挑选车源：挑选中意车源，正确填写订单信息，等待卖家确认。<br>
						2.支付订金：客服将尽快与您进一步核实订单信息，确认无误后支付订金。<br>
						3.锁定车源：订金支付完成后，收藏平台将尽快准备车源及相关手续。<br>
						4.支付余款：收藏平台按照订单信息准备完成后，400客服会与您联系支付余款。<br>
						5.物流配送：收藏平台收到您的购车余款后，按照订单要求安排物流配送。<br>
						6.交易完成：您验收通过后，交易完成。<br>
                        </p>
                    </div>
                </div>
            </div>
        </div>
        <!-- ./购车需知 -->

        <!-- 免责声明 -->
        <div class="mod-buy-tab3 mod-buy-tab">
            <div class="mod-buy-tab-title">
                <h2>免责声明</h2>
            </div>
            <div class="inner">
                <p>以上所展示的信息由企业自行提供，内容的真实性、准确性和合法性由发布企业负责。收藏对此不承担任何保证责任</p>
            </div>
        </div>
        <!-- ./免责声明 -->

        <!-- 购车承诺 -->
        <div class="mod-buy-promise text-center">
            <h3 class="title">购车承诺</h3>
            <div class="row">
                <div class="col col-6">
                    <i class="ico ico-car"></i>
                    <h4 class="item-title">真实车源</h4>
                    <p class="item-body">浏览信息，100%真实车源</p>
                </div>
                <div class="col col-6">
                    <i class="ico ico-user"></i>
                    <h4 class="item-title">认证商家</h4>
                    <p class="item-body">严格核实企业的合法性、真实性</p>
                </div>
                <div class="col col-6">
                    <i class="ico ico-note"></i>
                    <h4 class="item-title">手续齐备</h4>
                    <p class="item-body">机动车相关手续一应俱全</p>
                </div>
                <div class="col col-6">
                    <i class="ico ico-tools"></i>
                    <h4 class="item-title">售后三包</h4>
                    <p class="item-body">按照国家质检总局的汽车三包政策</p>
                </div>
            </div>
        </div>
        <!-- ./购车承诺 -->

        <div class="mod-buy-action">
            <a href="javascript:prepare(<s:property value='carInfo.id'/>);" class="button">立即购买</a>
           	<input type="hidden" id="price" value="<s:property value='carInfo.price'/>" />
            <s:hidden id="brandId" name="carInfo.brandId"></s:hidden>
            <s:hidden id="versionId" name="carInfo.versionId"></s:hidden>
            <s:hidden id="seriesId" name="carInfo.seriesId"></s:hidden>
            <s:hidden id="modelsId" name="carInfo.modelsId"></s:hidden>
            <s:hidden id="modelyearId" name="carInfo.modelyearId"></s:hidden>
            <s:hidden id="carSource" name="carInfo.source"></s:hidden>
        </div>
    </div>

<script src="/m/bower_components/zepto/zepto.min.js"></script>
<script src="/m/bower_components/zeptotouch/zepto-touch.min.js"></script>
<script src="/m/js/app.js"></script>
<script src="/m/bower_components/swiper/dist/js/swiper.min.js"></script>
<script src="/m/vendor/chartjs/Chart.min.js"></script>
<script src="/m/js/detail.js"></script>
<script type="text/javascript">
function prepare(id) {
	var deliveryPeriod = $("#deliveryPeriod").val();
	var logistics = $("#logistics").val();
	document.location.href = "mbuy_prepare.do?carForm.id=" + id + "&orderForm.deliveryPeriod=" + deliveryPeriod + "&orderForm.logistics=" + logistics;
}
</script>
</body>
</html>
