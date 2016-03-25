<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include  file="/portal/header.jsp"%>

    <link rel="stylesheet" href="/portal/css/style.css">

    <!--[if lte IE 8]>
        <script src="/portal/vendor/chartjs/excanvas.js"></script>
    <![endif]-->
    <style>
    	.tip-title {
    		display:block;
    	}
    	.tip-content {
    		display:block;
    	}
    </style>
    <style type="text/css">
		#h1 {
	   position: relative; left: 1px; top: 1px;
	}
	#h2 {
	   position: relative; left: 1px; top: 1px;
	}
	#h3 {
	   position: relative; left: 340px; top: -18px;
	}
	#h4 {
	   position: relative; left: 1px; top: -18px;
	}
	#h5 {
	   position: relative; left: 340px; top: -37px;
	}
	#h6 {
	   position: relative; left: 1px; top: -37px;
	}
	#h7 {
	   position: relative; left: 340px; top: -56px;
	}
	#h8 {
	   position: relative; left: 1px; top: -50px;
	}
	#h9 {
	   position: relative; left: 1px; top: -50px;
	}
	#h10 {
	   position: relative; left: 1px; top: -42px;
	}
	#h11 {
	   position: relative; left: 1px; top: -43px;
	}
	#h12 {
	   position: relative; left: 1px; top: -44px;
	}
	#h13 {
	   position: relative; left: 1px; top: -40px;
	}
    </style>
<body>
	<%@ include  file="/portal/inc.jsp"%>
    <%@ include  file="/portal/incbrandhot.jsp"%>

    <div class="comm-white-ctn">
        <div class="container">
            <div class="comm-breadcrumbs">
                当前位置：
                <a href="/buy_init.do">买车</a>&gt;<a href="/buy_init.do?brandId=<s:property value='carInfo.brandInfo.id'/>"><s:property value="carInfo.brandInfo.name"/></a>&gt;<span class="current">综述</span>
            </div>

            <!-- 基本信息 -->
            <div class="mod-buy-baseinfo clearfix">
                <div class="left-info">
                    <h1><s:property value="carInfo.title"/></h1>
                    <div class="comm-imgs-viewer">
                        <div class="big-img img">
                        <s:if test="%{carInfo.modelyearInfo.picPath2 == null || carInfo.modelyearInfo.picPath2 == ''}">
                        <img id="carImg" src="/portal/img/nocarpic.png" alt="">
                        </s:if>
                        <s:else>
                        <img id="carImg" src="<s:property value='carInfo.modelyearInfo.picPath2'/>" alt="">
                        </s:else>
                        </div>
                        <div class="small-imgs">
                            <div class="item img">
                            <s:if test="%{carInfo.modelyearInfo.picPath1 == null || carInfo.modelyearInfo.picPath1 == ''}">
	                        <img src="/portal/img/nocarpic.png" alt="">
	                        </s:if>
	                        <s:else>
	                        <img src="<s:property value='carInfo.modelyearInfo.picPath1'/>" alt="">
	                        </s:else>
                            </div>
                            <div class="item img">
                            <s:if test="%{carInfo.modelyearInfo.picPath2 == null || carInfo.modelyearInfo.picPath2 == ''}">
	                        <img src="/portal/img/nocarpic.png" alt="">
	                        </s:if>
	                        <s:else>
	                        <img src="<s:property value='carInfo.modelyearInfo.picPath2'/>" alt="">
	                        </s:else>
                            </div>
                            <div class="item img">
                            <s:if test="%{carInfo.modelyearInfo.picPath3 == null || carInfo.modelyearInfo.picPath3 == ''}">
	                        <img src="/portal/img/nocarpic.png" alt="">
	                        </s:if>
	                        <s:else>
	                        <img src="<s:property value='carInfo.modelyearInfo.picPath3'/>" alt="">
	                        </s:else>
                            </div>
                            <div class="item img">
                            <s:if test="%{carInfo.modelyearInfo.picPath4 == null || carInfo.modelyearInfo.picPath4 == ''}">
	                        <img src="/portal/img/nocarpic.png" alt="">
	                        </s:if>
	                        <s:else>
	                        <img src="<s:property value='carInfo.modelyearInfo.picPath4'/>" alt="">
	                        </s:else>
	                        </div>
                            <div class="item img">
                            <s:if test="%{carInfo.modelyearInfo.picPath5 == null || carInfo.modelyearInfo.picPath5 == ''}">
	                        <img src="/portal/img/nocarpic.png" alt="">
	                        </s:if>
	                        <s:else>
	                        <img src="<s:property value='carInfo.modelyearInfo.picPath5'/>" alt="">
	                        </s:else>
	                        </div>
                            <div class="item img">
                            <s:if test="%{carInfo.modelyearInfo.picPath6 == null || carInfo.modelyearInfo.picPath6 == ''}">
	                        <img src="/portal/img/nocarpic.png" alt="">
	                        </s:if>
	                        <s:else>
	                        <img src="<s:property value='carInfo.modelyearInfo.picPath6'/>" alt="">
	                        </s:else>
	                        </div>
                        </div>
                    </div>
                    <s:if test="%{carInfo.carStatus != 4 && carInfo.carStatus != 0}">
                    <div class="special-desc">
                        <div class="title"><i class="ico-help"></i> 特殊说明：</div>
                        <div class="body" style="font-size:16px;color:#000000;"><s:property value="carInfo.remark"/></div>
                    </div>
                    </s:if>
                </div>
                <div class="right-info">
                    <div class="price-info">
                        <div class="info-group">
                            零售价：<span class="comm-text-red comm-price">&yen; <strong><s:property value="carInfo.showPrice"/>万</strong></span>
                            <div class="right">库存：<span class="comm-text-red"><s:property value="carInfo.surplusNum"/>辆</span></div>
                        </div>
                        <div class="info-group comm-text-lightgray">市场指导价：<s:property value='carInfo.modelyearInfo.showPrice'/>万，为您节省：<s:property value="carInfo.showDifferentPrice"/>万</div>
                        <div class="info-group">汽车状态：
                            <span class="comm-text-huge">
                            	<s:if test="%{carInfo.source == 1}">现货</s:if>
                            	<s:else>期货</s:else>
                    		</span>
                        </div>
                    </div>
                    <div class="config-info">
                        <div class="info-group row">
                            <div class="col col-6">
                                <span class="label">燃油：</span>
                                <span class="">
                                    <s:property value="carInfo.fuel"/>
                                </span>
                            </div>
                            <div class="col col-6">
                                <span class="label">排量：</span>
                                <span class="">
                                    <s:property value="carInfo.enginesName"/>
                                </span>
                            </div>
                        </div>
                        <div class="info-group row">
                            <div class="col col-12">
                                <span class="label">颜色：</span>
                                <span class="">
                                    外饰:<s:property value="carInfo.outercolorName"/> / 内饰:<s:property value="carInfo.innercolorName"/>
                                </span>
                            </div>
                        </div>
                        <%-- <div class="info-group row">
                            <div class="col col-12">
                                <span class="label"> 地区:</span>
                                <span class="">
                                  	<s:property value="carInfo.area"/>
                                </span>
                            </div>
                        </div> --%>
                        <div class="info-group row">
                            <div class="col col-12">
                                <span class="label">车型：</span>
                                <span class="">
                                	<s:property value="carInfo.modelyearInfo.name"/><s:property value="carInfo.brandInfo.name"/><s:property value="carInfo.seriesInfo.name"/><s:property value="carInfo.modelsInfo.name"/>
                                </span>
                            </div>
                        </div>
                        <div class="info-group row" style="display:none;">
                            <div class="col col-12">
                                <span class="label">所在省份：</span>
                                <span class="text">咨询400客服</span><!-- <s:property value="carInfo.province"/> -->
                            </div>
                        </div>
                        <div class="info-group row">
                            <div class="col col-6">
                                <span class="label">首款比例：</span>
                                <span class="">
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
                                </span>
                            </div>
                            <div class="col col-6">
                                <span class="label">提货周期：</span>
                                <span class="">
                                   	<s:if test="%{carInfo.deliveryPeriod == 0}">任意</s:if>
                                   	<s:if test="%{carInfo.deliveryPeriod == 3}">3天</s:if>
                                   	<s:if test="%{carInfo.deliveryPeriod == 7}">7天</s:if>
                                   	<s:if test="%{carInfo.deliveryPeriod == 15}">15天</s:if>
                                   	<s:if test="%{carInfo.deliveryPeriod == 30}">30天</s:if>
                                </span>
                            </div>
                        </div>
                        <div class="info-group row">
                            <div class="col col-12">
                                <span class="label">物流方式：</span>
                                <span class="">
                                   	<s:if test="%{carInfo.logistics == 1}">买家自提</s:if>
                                   	<s:if test="%{carInfo.logistics == 2}">卖家配送</s:if>
                                   	<s:if test="%{carInfo.logistics == 3}">平台配送</s:if>
                                </span>
                            </div>
                        </div>
                        <div class="info-group row">
                            <div class="col col-12">
                                <span class="label">购买数量：</span>
                                <div class="text">
                                    <div class="comm-number-selection inline-block">
                                        <div class="sub comm-trigger">-</div>
                                        <input id="orderNum" class="value" value="1" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
                                        <div class="plus comm-trigger">+</div>
                                    </div>&nbsp;&nbsp;
                                    <span class="inline-block vertical-top">库存：<s:property value="carInfo.surplusNum"/>辆</span>
                                </div>
                            </div>
                        </div>
                        <div class="info-group action">
                        	<s:if test="%{carInfo.carStatus == 2}">
                            <div class="button button-primary">已下架</div>
                        	</s:if>
                        	<s:else>
                            <div onclick="setCarData();" class="button button-primary">立即购买</div>
                        	</s:else>
                        </div>
                    </div>
                </div>
            </div>
            <!-- ./基本信息 -->

            <!-- 图表 -->
            <div class="mod-buy-chartinfo">
                <div class="tabs" id="B-Canvas">
                    <a id="realLine" href="javascript:viewReal();" class="item">实时</a>
                    <a id="dayLine" href="javascript:viewDay();" class="item">日线</a>
                    <a id="weekLine" href="javascript:viewWeek();" class="item">周线</a>
                </div>
                <div class="chart">
                    <div class="title">底价走势（万元）</div>
                    <canvas id="J-Canvas" width="600" height="300"></canvas>
<%--                    <p class="body text-center">--%>
<%--                        市场解读：<span class="line green"></span>走势平稳，可放心购买--%>
<%--                    </p>--%>
                </div>
            </div>
            <!-- ./图表 -->

            <!-- 步骤 挑选车源－支付订金－锁定车源－支付余款－物流配送－交易完成-->
            <div class="mod-buy-process">
                <div class="title">购车流程：</div>
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

            <!-- Tab页签 -->
            <div class="mod-buy-tabs">
                <a href="#tab0" class="active">配置详情</a>
                <a href="#tab1">车辆手续</a>
                <a href="#tab2">购车需知</a>
                <a href="#tab3">免责声明</a>
            </div>
            <!-- ./Tab页签 -->

            <!-- 配置详情 -->
            <%--  
           <div id="tab0" class="mod-buy-tab0 mod-buy-tab">
                <div class="mod-buy-tab-title">
                    <h2>配置详情</h2>
                </div>
                <div class="inner">
                    <h3 class="title">基础参数</h3>
                    <div class="row">
                        <div class="col col-6">
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
                        </div>
                        <div class="col col-6">
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
                        </div>
                    </div>
                    <h3 class="title">标准配置</h3>
                    <ul class="list-unstyled">
                    	<s:property value="carInfo.standardInfo.name.replaceAll('\r\n','<br/>')" escape="false"/>
                    </ul>
                </div>
            </div>
            --%> 
            <!-- ./亮点配置 -->

            <%@include file="/portal/buy/incdetail.jsp" %>
        </div>
    </div>

    <div id="J-Modal" class="comm-modal none">
        <div class="modal">
            <div class="close j-close">&times;</div>
            <div class="title">订单详情</div>
            <div class="mod-buy-modal">
                <div class="title"><s:property value="carInfo.modelyearInfo.name"/><s:property value="carInfo.brandInfo.name"/></div>
                <div class="row">
                    <div class="col col-6">
                        <div class="img" style="background-image: url(<s:property value='carInfo.modelyearInfo.picPath2'/>);"></div>
                    </div>
                    <div class="col col-6">
                        <div class="info-item">
                            <span class="label">发车价：</span>
                            <div class="input comm-text-red comm-price">&yen;<strong><s:property value="carInfo.showPrice"/>万</strong></div>
                        </div>
                        <div class="info-item">
                            <span class="label">车辆状态：</span>
                            <div class="input">
                            <strong class="comm-text-huge">
                            	<s:if test="%{carInfo.source == 1}">现货</s:if>
                            	<s:else>期货</s:else>
                            </strong>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col col-6">
                        <div class="info-item">
                            <span class="label">燃油：</span>
                            <div class="input"><s:property value="carInfo.fuel"/></div>
                        </div>
                    </div>
                    <div class="col col-6">
                        <div class="info-item">
                            <span class="label">排量：</span>
                            <div class="input"><s:property value="carInfo.enginesName"/></div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col col-12">
                        <div class="info-item">
                            <span class="label">颜色：</span>
                            <div class="input"> 外饰:<s:property value="carInfo.outercolorName"/> / 内饰:<s:property value="carInfo.innercolorName"/></div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col col-12">
                        <div class="info-item">
                            <span class="label">车型：</span>
                            <div class="input"><s:property value="carInfo.modelsInfo.name"/></div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col col-6">
                        <div class="info-item">
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
                    </div>
                    <div class="col col-6">
                        <div class="info-item">
                            <span class="label">提货周期：</span>
                          	<s:if test="%{carInfo.deliveryPeriod == 0}">任意</s:if>
                            <s:elseif test="%{carInfo.deliveryPeriod == 3}">3天</s:elseif>
                            <s:elseif test="%{carInfo.deliveryPeriod == 7}">7天</s:elseif>
                            <s:elseif test="%{carInfo.deliveryPeriod == 15}">15天</s:elseif>
                            <s:elseif test="%{carInfo.deliveryPeriod == 30}">30天</s:elseif>
<!--                             <div id="showDeliveryPeriod" class="input"></div> -->
<!--                             <input id="orderDeliveryPeriod" type="hidden" /> -->
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col col-12">
                        <div class="info-item">
                            <span class="label">物流：</span>
                            <s:if test="%{carInfo.logistics == 2}">卖家配送</s:if>
                            <s:else>
	                            <span class="select">
	                                 <select name="orderForm.logistics" id="logistics">
	                                     <option value="1">买家自提</option>
	                                     <option value="3">平台配送</option>
	                                 </select>
	                             </span>
                            </s:else>
<%--                             <s:if test="%{carInfo.logistics == 1}">买家自提</s:if> --%>
<%--                             <s:elseif test="%{carInfo.logistics == 2}">卖家配送</s:elseif> --%>
<%--                             <s:elseif test="%{carInfo.logistics == 3}">平台配送</s:elseif> --%>
<!--                             <div id="showLogistics" class="input"></div> -->
                            <input id="orderLogistics" type="hidden" value="<s:property value='carInfo.logistics'/>"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col col-12">
                        <div class="info-item">
                            <span class="label">数量：</span>
                            <div class="input"><span class="comm-text-red" id="showNum"></span>辆</div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col col-12">
                        <div class="info-item">
                            <span class="label">物流费：</span>
                            <div class="input">请咨询400客服</div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col col-12">
                        <div class="info-item" style="height:auto;position:relative;">
                            <span id="serviceBtn" class="label" style="display:inline-block;background:url('/portal/img/question.png') no-repeat 60px 11px;">平台服务费&nbsp;&nbsp;&nbsp;&nbsp;：</span>
                            <div class="input" style="line-height: 20px;padding-top: 10px;">
                            	<s:if test="%{carInfo.versionId != 161}">
                            		<span class="comm-text-red" id="oriTradePrice"><s:property value="oriTradePrice"/></span>元，实收<span class="comm-text-red" id="tradePrice"><s:property value="tradePrice"/></span>元<img src="/portal/img/hot.gif"/>
                            	</s:if>
                            	<span style="display:block">9月27日至10月31日贵宾体验期，为回馈优质客户，平台服务费4.8折
                            		<s:if test="%{carInfo.versionId == 161}">
                       				;具体收费方式咨询400客服.
                       				</s:if>
                       			</span>
                            </div>
                            <input type="hidden" id="_oriTradePrice" name="<s:property value='oriTradePrice'/>" />
                        	<div id="tip" style="display:none;width:500px;height:385px;position:absolute;top:-278px;left:78px;">
	                        	<div style="width:100%;height:100%;position: absolute;background-color: black;z-index: 0;opacity: 0.7;filter:alpha(opacity=70);">
	                        	</div>
	                        	<div style="line-height:18px;color: white;z-index: 100;position: absolute; padding: 15px;">
		                        	<p>
			                        	<span class="tip-title">1.什么是平台服务费？<span>
										<span class="tip-content">收藏为买卖方双方提供了一个公平、安全、高效的交易平台，在每笔成功交易之后会向买方收取小额度的平台交易服务费。</span>
									</p>
                                               	<p>
							<div id="h1">
								<span class="tip-title">平台服务内容包括：<span> 
							</div>
							<div id="h2">
								<span class="tip-content">①平台促进成交 </span> 
							</div>
							<div id="h3">
								<span class="tip-content">②平台400电话咨询指导</span> 
							</div>
							<div id="h4">
								<span class="tip-content">③平台第三方PDI仲裁（买卖双方PDI如有异议） </span> 
							</div>
							<div id="h5">
								<span class="tip-content">④平台交车协助 </span> 
							</div>
							<div id="h6">
								<span class="tip-content">⑤买家第三方支付 </span> 
							</div>
							<div id="h7">
								<span class="tip-content">⑥平台物流协助 </span> 
							</div>
						</p>
						<p>
							<div id="h8">
								<span class="tip-title">2.平台服务费何时收取？</span>
							</div>
							<div id="h9">
							<span class="tip-content">在每一笔交易过程中，当买卖双方对车源无异议确认成交之后，买方支付购车尾款同时向平台支付此费用。</span>
							</div>		 
						</p>
						<p>
							<div id ="h10">
								<span class="tip-title">3.平台交易费计算公式是什么？</span>
							</div>
							<div id ="h11">
								<span class="tip-content">平台交易费=（31省平均底价-成交价）*购买数量*0.2，如果31省平均底价低于成交价则本次交易不收取平台交易费。</span>
							</div>
							<div id ="h12">
								<span class="tip-content">31省平均底价计算方式为：过去一周每个省份平台成交最低价累加/31。</span>
							</div>
						</p>
						<p>
							<div id ="h13">
								例如：今天买卖双方在平台交易成功，交易内容为12辆车型A，交易单价为41.35万人民币，本款车上周31省平均底价为41.6万，则买方需要支付总车款496.2万+6000元平台交易费，平台交易费为：6000元=（41.6万-41.35万）*12*0.2
							</div>
						</p>
	                        	</div>
                        	</div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col col-12">
                        <div class="info-item">
                            <span class="label">总价：</span>
                            <div class="input text-right comm-text-red"><span class="comm-price">&yen;<strong id="showTotalPrice"></strong><strong>万</strong></span></div>
                            <input type="hidden" id="totalPrice" />
                        </div>
                    </div>
                </div>
                <div class="action text-center">
                	<input type="hidden" id="carId" value="<s:property value='carInfo.id'/>" />
                	<input type="hidden" id="surplusNum" value="<s:property value='carInfo.surplusNum'/>" />
                	<input type="hidden" id="price" value="<s:property value='carInfo.price'/>" />
                    <div id="orderSubmitDiv" class="button button-primary">确认提交</div>
                    <s:hidden id="brandId" name="carInfo.brandId"></s:hidden>
                    <s:hidden id="versionId" name="carInfo.versionId"></s:hidden>
                    <s:hidden id="seriesId" name="carInfo.seriesId"></s:hidden>
                    <s:hidden id="modelsId" name="carInfo.modelsId"></s:hidden>
                    <s:hidden id="modelyearId" name="carInfo.modelyearId"></s:hidden>
                    <s:hidden id="carSource" name="carInfo.source"></s:hidden>
                </div>
            </div>
        </div>
    </div>
    <s:hidden id="carUserId" name="carInfo.insertId"></s:hidden>
    <s:hidden id="userId" name="userSession.userId"></s:hidden>
    <%@include file="/portal/footer.jsp" %>

<script src="/portal/js/vendor/jquery.js"></script>
<script src="/portal/js/app.js"></script>
<script src="/portal/js/numberFormat.js"></script>
<script src="/portal/vendor/chartjs/Chart.min.js"></script>
<script src="/portal/js/detail.js"></script>
<script>
$(function(){
    modal = new FACHE.UI.Modal({
        id : 'J-Modal'
    });
    new FACHE.UI.Btp();

    $(".small-imgs img").on("click", function() {
		$("#carImg").prop("src", $(this).prop("src"));
	});
    
    $("#serviceBtn").hover(function() {
		$("#tip").css("display","");
    }, function() {
    	$("#tip").css("display","none");
    });
});
</script>
</body>
</html>
