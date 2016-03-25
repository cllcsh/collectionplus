<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<head>
    <title>下单出售</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="baidu-site-verification" content="hWGw6qbfv7" />
    <link rel="stylesheet" href="/m/bower_components/swiper/dist/css/swiper.min.css">
    <link rel="stylesheet" href="/m/css/style.css">

</head>
<body>
    <header class="comm-header">
        <div class="left opt">
            <a href="javascript:history.go(-1);">返回</a>
        </div>
<%--        <div class="right opt">--%>
<%--            <a href="#">预览</a>--%>
<%--        </div>--%>
        <h1 class="title">下单出售</h1>
    </header>

    <div class="wrap">
        <div class="swiper-container comm-swiper">
            <div class="swiper-wrapper" id="carImg">
                <div class="swiper-slide"><img src="<s:property value='seriesInfo.picPath'/>" alt=""></div>
            </div>
            <s:hidden id="seriesPic" name="seriesInfo.picPath"></s:hidden>
            <div class="swiper-pagination"></div>
        </div>
        <div class="comm-form">
            <form id="sellForm" action="">
                <div class="form-group">
                    <span class="label">标题：</span>
                    <div class="input">
                        <input id="title" name="carForm.title" value="<s:property value='seriesInfo.brandName'/> <s:property value='seriesInfo.name'/> <s:property value='seriesInfo.versionName'/>" type="text" placeholder="请输入标题">
                        <input name="carForm.brandId" value="<s:property value='seriesInfo.brandId'/>" type="hidden" />
                        <input name="carForm.versionId" value="<s:property value='seriesInfo.versionId'/>" type="hidden" />
                        <input name="carForm.seriesId" value="<s:property value='seriesInfo.id'/>" type="hidden" />
                        <input id="brandName" value="<s:property value='seriesInfo.brandName'/>" type="hidden" />
                        <input id="versionName" value="<s:property value='seriesInfo.versionName'/>" type="hidden" />
                        <input id="seriesName" value="<s:property value='seriesInfo.name'/>" type="hidden" />
                        <input id="showFlag" value="<s:property value='seriesInfo.showFlag'/>" type="hidden" />
                    </div>
                </div>
                <div class="split"></div>
                <div class="form-group">
                    <span class="label">售卖车型：</span>
                    <div class="input">
                        <div class="comm-selector" data-single="1" data-onchange="onModelsChange">
                            <span class="value" id="modelsName">选择</span>
                            <div class="options">
                                <div class="item" data-key="0" data-value="选择"></div>
                            	<s:iterator var="modelsInfo" value="modelsInfoList">
                                <div class="item" data-key="<s:property value='#modelsInfo.id'/>" data-value="<s:property value='#modelsInfo.name'/>"></div>
                            	</s:iterator>
                            </div>
                            <input id="modelsId" type="hidden" name="carForm.modelsId" value="0">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <span class="label">选择年款：</span>
                    <div class="input">
                        <div class="comm-selector" data-single="1" data-onchange="onModelyearChange">
                            <span class="value" id="modelyearName">选择</span>
                            <div class="options" id="modelyearOption">
                                <div class="item" data-key="0" data-value="选择"></div>
                            </div>
                            <input id="modelyearId" type="hidden" name="carForm.modelyearId" value="0">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <span class="label">选择燃油：</span>
                    <div class="input">
                        <div class="comm-selector" data-single="1">
                            <span class="value">选择</span>
                            <div class="options">
                                <div class="item" data-key="" data-value="选择"></div>
                                <div class="item" data-key="汽油" data-value="汽油"></div>
                                <div class="item" data-key="柴油" data-value="柴油"></div>
                                <div class="item" data-key="油电混合" data-value="油电混合"></div>
                                <div class="item" data-key="电动" data-value="电动"></div>
                            </div>
                            <input id="fuel" type="hidden" name="carForm.fuel" value="">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <span class="label">选择排量：</span>
                    <div class="input">
                        <div class="comm-selector" data-single="1" data-onchange="onEnginesChange">
                            <span class="value" id="enginesName">选择</span>
                            <div class="options" id="enginesOption">
                                <div class="item" data-key="0" data-value="选择"></div>
                                <s:iterator var="enginesInfo" value="enginesInfoList">
                                <div class="item" data-key="<s:property value='#enginesInfo.id'/>" data-value="<s:property value='#enginesInfo.name'/>"></div>
                            	</s:iterator>
                            </div>
                            <input id="enginesId" type="hidden" name="carForm.enginesId" value="0">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <span class="label">内饰颜色：</span>
                    <div class="input">
                        <div class="comm-selector" data-single="1">
                            <span class="value" id="innercolorValue">选择</span>
                            <div class="options" id="innercolorOption">
                                <div class="item" data-key="0" data-value="选择"></div>
                            </div>
                            <input id="innercolorId" type="hidden" name="carForm.innercolorId" value="0">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <span class="label">外饰颜色：</span>
                    <div class="input">
                        <div class="comm-selector" data-single="1">
                            <span class="value" id="outercolorValue">选择</span>
                            <div class="options" id="outercolorOption">
                                <div class="item" data-key="0" data-value="选择"></div>
                            </div>
                            <input id="outercolorId" type="hidden" name="carForm.outercolorId" value="0">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <span class="label">所在省份：</span>
                    <div class="input">
                        <div class="comm-selector" data-single="1">
                            <span class="value">选择</span>
                            <div class="options" id="provinceDiv">
                            </div>
                            <input id="province" type="hidden" name="carForm.province" value="0">
                        </div>
                    </div>
                </div>
                <s:if test="%{seriesInfo.showFlag == 1}">
                <div class="form-group">
                    <span class="label">车辆手续：</span>
                    <div class="input">
                        <div class="comm-selector" data-onchange="onProceduresChange">
                            <span class="value">选择</span>
                            <div class="options">
                                <div class="item" data-key="0" data-value="选择"></div>
                                <div class="item" data-key="1" data-value="用户发票"></div>
                                <div class="item" data-key="2" data-value="随车检验单"></div>
                                <div class="item" data-key="4" data-value="车辆一致性证书"></div>
                                <div class="item" data-key="8" data-value="购置税电子证书"></div>
                                <div class="item" data-key="16" data-value="基本信息表"></div>
                                <div class="item" data-key="32" data-value="货物进口证明书"></div>
                                <div class="item" data-key="64" data-value="车辆销售正规发票"></div>
                                <div class="item" data-key="128" data-value="车辆合格证"></div>
                                <div class="item" data-key="256" data-value="车辆保修手册"></div>
                            </div>
                            <input type="hidden" id="procedures" name="carForm.procedures" value="0">
                        </div>
                    </div>
                </div>
                </s:if>
                <s:else>
                <input type="hidden" id="procedures" name="carForm.procedures" value="0">
                </s:else>
                <div class="split"></div>
                <div class="form-group">
                    <span class="label">首款比例：</span>
                    <div class="input">
                        <div class="comm-selector" data-single="1">
                            <span class="value">选择</span>
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
                            <input type="hidden" name="carForm.depositRatio" id="depositRatio" value="0">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <span class="label">选择货源：</span>
                    <div class="input">
                        <div class="comm-selector" data-single="1">
                            <span class="value">选择</span>
                            <div class="options">
                                <div class="item" data-key="0" data-value="选择"></div>
                                <div class="item" data-key="1" data-value="现货"></div>
                                <div class="item" data-key="2" data-value="期货"></div>
                            </div>
                            <input type="hidden" name="carForm.source" id="source" value="0">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <span class="label">填写单台售价：</span>
                    <div class="input">
                        <input id="price" name="price" type="text" onkeyup="this.value=this.value.replace(/^\D+(\.\D{2})?$/g,'')" onafterpaste="this.value=this.value.replace(/^\D+(\.\D{2})?$/g,'')">
                        <span class="right comm-text-black">万元</span>
                    </div>
                </div>
                <div class="form-group">
                    <span class="label">物流方式：</span>
                    <div class="input">
                        <div class="comm-selector" data-single="1">
                            <span class="value">买家自提</span>
                            <div class="options">
                                <div class="item" data-key="1" data-value="买家自提"></div>
                                <div class="item" data-key="2" data-value="卖家配送"></div>
                                <div class="item" data-key="3" data-value="平台配送"></div>
                            </div>
                            <input type="hidden" name="carForm.logistics" id="logistics" value="0">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <span class="label">提货周期：</span>
                    <div class="input">
                        <div class="comm-selector" data-single="1">
                            <span class="value">任意</span>
                            <div class="options">
                                <div class="item" data-key="0" data-value="任意"></div>
                                <div class="item" data-key="3" data-value="3天"></div>
                                <div class="item" data-key="7" data-value="7天"></div>
                                <div class="item" data-key="15" data-value="15天"></div>
                                <div class="item" data-key="30" data-value="30天"></div>
                            </div>
                            <input type="hidden" name="carForm.deliveryPeriod" id="deliveryPeriod" value="1">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <span class="label">填写出货数量：</span>
                    <div class="input">
                        <div class="comm-number-selection right">
                            <div class="sub comm-trigger">-</div>
                            <input id="num" name="carForm.num" class="value" value="1" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
                            <div class="plus comm-trigger">+</div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <span class="label">特殊说明：</span>
                    <div class="input">
                        <textarea name="carForm.remark" id="remark" placeholder="请输入车源的特殊配置、上牌区域、生产日期等特殊信息；如若填写任何公司或个人信息，该车源可能会被下架。"></textarea>
                    </div>
                </div>
                <div class="split"></div>
                <div class="action text-center">
                    <button type="button" class="button button-primary" onclick="saveCar();">确定上架</button>
                </div>
            </form>
        </div>
    </div>

    <!-- <div class="comm-selector-overlay">
        <div class="inner">
            <div class="header">选择车辆手续</div>
            <div class="item active">购车发票</div>
            <div class="item">随车检验单</div>
            <div class="item">车辆一致证书</div>
        </div>
    </div> -->

<script src="/m/bower_components/zepto/zepto.min.js"></script>
<script src="/m/bower_components/zeptotouch/zepto-touch.min.js"></script>
<script src="/m/js/app.js"></script>
<script src="/m/js/sell.js"></script>
<script src="/m/bower_components/swiper/dist/js/swiper.min.js"></script>
<script>
$('.swiper-container').css({height: $(window).width()/2});
if ($('.swiper-wrapper').length > 1) {
    new Swiper ('.swiper-container', {
        loop: true,
        pagination: '.swiper-pagination',
        autoplay: 3000,
        loop: true
    });
}

$('.comm-selector').each(function () {
    var _this = $(this);
    var title = _this.parents('.form-group').find('.label').html();
    _this.on(Fache.mainEvent, function () {
        new Fache.UI.Selector(_this, {title: title});
    });
});

// 此方法是售卖车型
// selected 是选中的选项的 array，请自行查看
var onModelsChange = function(selected) {
	choseModelyear(selected);
	genTitle();
};
var onModelyearChange = function(selected) {
	choseOutercolor(selected);
	choseInnercolor(selected);
	changeCarImg(selected);
	genTitle();
};
var onEnginesChange = function(selected) {
	genTitle();
};
var onProceduresChange = function(selected) {
	culProcedures(selected);
};
</script>

</body>
</html>
