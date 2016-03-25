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
    <div class="comm-white-ctn">
        <div class="container">
            <div class="mod-sell-form">
                <form id="sellForm" action="/module/gxfc/sell_save.action" onsubmit="modal.show();return false;">
                    <div class="title">标题：<input name="carForm.title" id="title" type="text" placeholder="请输入标题"></div>
                    <div class="fields">
                        <div class="form-group">
                            <label class="label" for="">品牌拼音：</label>
                            <div class="input">
                                <span class="select">
                                    <select id="pinyin" onchange="choseBrand();">
                                        <option value="A">A</option>
                                        <option value="B">B</option>
                                        <option value="C">C</option>
                                        <option value="D">D</option>
                                        <option value="E">E</option>
                                        <option value="F">F</option>
                                        <option value="G">G</option>
                                        <option value="H">H</option>
                                        <option value="I">I</option>
                                        <option value="J">J</option>
                                        <option value="K">K</option>
                                        <option value="L">L</option>
                                        <option value="M">M</option>
                                        <option value="N">N</option>
                                        <option value="O">O</option>
                                        <option value="P">P</option>
                                        <option value="Q">Q</option>
                                        <option value="R">R</option>
                                        <option value="S">S</option>
                                        <option value="T">T</option>
                                        <option value="U">U</option>
                                        <option value="V">V</option>
                                        <option value="W">W</option>
                                        <option value="X">X</option>
                                        <option value="Y">Y</option>
                                        <option value="Z">Z</option>
                                    </select>
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="label" for="">选择品牌：</label>
                            <div class="input">
                                <span class="select">
                                    <select name="carForm.brandId" id="brandId" onchange="choseVersion();genTitle();">
                                        <option value="">请选择</option>
                                    </select>
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="label" for="">选择版本：</label>
                            <div class="input">
                                <span class="select">
                                    <select name="carForm.versionId" id="versionId" onchange="choseSeries();genTitle();showProcedures();">
                                        <option value=""></option>
                                    </select>
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="label" for="">选择系列：</label>
                            <div class="input">
                                <span class="select">
<%--                                    <select name="carForm.seriesId" id="seriesId" onchange="choseModels();choseEngines();genTitle();">--%>
                                    <select name="carForm.seriesId" id="seriesId" onchange="choseModelsModelyear();choseEngines();genTitle();">
                                        <option value="">请选择</option>
                                    </select>
                                </span>
                            </div>
                        </div>
<%--                        <div class="form-group">--%>
<%--                            <label class="label" for="">选择车型：</label>--%>
<%--                            <div class="input">--%>
<%--                                <span class="select">--%>
<%--                                    <select name="carForm.modelsId" id="modelsId" onchange="choseModelyear();genTitle();">--%>
<%--                                        <option value="">请选择</option>--%>
<%--                                    </select>--%>
<%--                                </span>--%>
<%--                            </div>--%>
<%--                        </div>--%>
						<input type="hidden" name="carForm.modelsId" id="modelsId" value="0" />
                        <div class="form-group">
                            <label class="label" for="">选择年款：</label>
                            <div class="input">
                                <span class="select">
                                    <select name="carForm.modelyearId" id="modelyearId" onchange="choseInnercolor();choseOutercolor();changeCarImg();genTitle();">
                                        <option value="">请选择</option>
                                    </select>
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="label" for="">选择排量：</label>
                            <div class="input">
                                <span class="select">
                                    <select name="carForm.enginesId" id="enginesId" onchange="genTitle();">
                                        <option value=""></option>
                                    </select>
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="label" for="">选择燃油：</label>
                            <div class="input">
                                <span class="select">
                                    <select name="carForm.fuel" id="fuel">
                                        <option value="汽油">汽油</option>
                                        <option value="柴油">柴油</option>
                                        <option value="油电混合">油电混合</option>
                                        <option value="电动">电动</option>
                                    </select>
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="label" for="">外饰颜色：</label>
                            <div class="input">
                                <span class="select">
                                    <select name="carForm.outercolorId" id="outercolorId">
                                        <option value=""></option>
                                    </select>
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="label" for="">内饰颜色：</label>
                            <div class="input">
                                <span class="select">
                                    <select name="carForm.innercolorId" id="innercolorId">
                                        <option value=""></option>
                                    </select>
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="label" for="">所在省份：</label>
                            <div class="input">
                                <span class="select">
                                    <select name="carForm.province" id="province">
                                        <option value=""></option>
                                    </select>
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="label" for="">预付车款比例：</label>
                            <div class="input">
                                <span class="select">
                                    <select name="carForm.depositRatio" id="depositRatio">
                                        <option value="0">请选择预付车款比例</option>
                                        <option value="1">10%</option>
                                        <option value="2">20%</option>
                                        <option value="3">30%</option>
                                        <option value="4">40%</option>
                                        <option value="5">50%</option>
                                        <option value="6">60%</option>
                                        <option value="7">70%</option>
                                        <option value="8">80%</option>
                                        <option value="9">90%</option>
                                        <option value="10">100%</option>
                                    </select>
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="label" for="">选择货源：</label>
                            <div class="input">
                                <span class="select">
                                    <select name="carForm.source" id="source">
                                        <option value="0">请选择货源</option>
                                        <option value="1">现货</option>
                                        <option value="2">期货</option>
                                    </select>
                                </span>
                            </div>
                        </div>
                        <div class="form-group" id="proceduresDiv" style="display: none;">
                            <label class="label" for="">车辆手续：</label>
                            <div class="input">
                                <span class="checkbox">
                                    <label for="">用户发票</label>
                                    <input name="procedures" type="checkbox" value="1">
                                </span>&nbsp;&nbsp;
                                <span class="checkbox">
                                    <label for="">随车检验单</label>
                                    <input name="procedures" type="checkbox" value="2">
                                </span>&nbsp;&nbsp;
                                <span class="checkbox">
                                    <label for="">车辆一致性证书</label>
                                    <input name="procedures" type="checkbox" value="4">
                                </span>&nbsp;&nbsp;
                                <span class="checkbox">
                                    <label for="">购置税电子证书</label>
                                    <input name="procedures" type="checkbox" value="8">
                                </span>
                                <span class="checkbox">
                                    <label for="">基本信息表</label>
                                    <input name="procedures" type="checkbox" value="16">
                                </span>&nbsp;&nbsp;
                                <span class="checkbox">
                                    <label for="">货物进口证明书</label>
                                    <input name="procedures" type="checkbox" value="32">
                                </span>&nbsp;&nbsp;
                                <span class="checkbox">
                                    <label for="">车辆销售正规发票</label>
                                    <input name="procedures" type="checkbox" value="64">
                                </span>
                                <span class="checkbox">
                                    <label for="">车辆合格证</label>
                                    <input name="procedures" type="checkbox" value="128">
                                </span>
                                <span class="checkbox">
                                    <label for="">车辆保修手册</label>
                                    <input name="procedures" type="checkbox" value="256">
                                </span>
                            </div>
                            <s:hidden id="procedures" name="carForm.procedures"></s:hidden>
                        </div>
                        <div class="form-group">
                            <label class="label" for="">出售数量：</label>
                            <div class="input">
                                <div class="comm-number-selection">
                                    <div class="sub comm-trigger">-</div>
                                    <input id="num" name="carForm.num" class="value" value="1" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
                                    <div class="plus comm-trigger">+</div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="label" for="">出售价格：</label>
                            <div class="input">
                                <input id="price" name="price" type="text" onkeyup="this.value=this.value.replace(/^\D+(\.\D{2})?$/g,'')" onafterpaste="this.value=this.value.replace(/^\D+(\.\D{2})?$/g,'')"> 万元
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="label" for="">配送方式：</label>
                            <div class="input">
                                <span class="select">
                                    <select name="carForm.logistics" id="logistics">
                                        <option value="1">买家自提</option>
                                        <option value="2">卖家配送</option>
                                        <option value="3">平台配送</option>
                                    </select>
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="label" for="">提货周期：</label>
                            <div class="input">
                                <span class="select">
                                    <select name="carForm.deliveryPeriod" id="deliveryPeriod">
                                        <option value="0">任意</option>
                                        <option value="3">3天</option>
                                        <option value="7">7天</option>
                                        <option value="15">15天</option>
                                        <option value="30">30天</option>
                                    </select>
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="label" for="">特殊说明：</label>
                            <div class="input">
                                <textarea name="carForm.remark" id="remark" cols="30" rows="10" placeholder="请输入车源的特殊配置、上牌区域、生产日期等特殊信息；如若填写任何公司或个人信息，该车源可能会被下架。"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="comm-imgs-viewer">
                        <div class="big-img img"><img id="carImg" src="/portal/img/data/car.jpg" alt=""></div>
                        <div class="small-imgs">
                            <div class="item img"><img id="carImg1" src="/portal/img/data/car.jpg" alt=""></div>
                            <div class="item img"><img id="carImg2" src="/portal/img/data/car.jpg" alt=""></div>
                            <div class="item img"><img id="carImg3" src="/portal/img/data/car.jpg" alt=""></div>
                            <div class="item img"><img id="carImg4" src="/portal/img/data/car.jpg" alt=""></div>
                            <div class="item img"><img id="carImg5" src="/portal/img/data/car.jpg" alt=""></div>
                            <div class="item img"><img id="carImg6" src="/portal/img/data/car.jpg" alt=""></div>
                        </div>
                    </div>
                    <div class="actions">
                        <button class="button button-primary" type="button" onclick="saveCar();">确认上架</button>
                        <div class="button button-secondary" onclick="carReview();">上架预览</div>
                    </div>
                </form>
            </div>

            <div id="J-Modal" class="comm-modal none">
                <div class="modal" style="background: rgba(0,0,0,.5);border: 2px solid #fff;">
                    <div class="close j-close">&times;</div>
                    <div class="img"><i class="comm-img gray-tick"></i></div>
                    <div class="tip-title" style="color: #fff;">上架提交成功</div>
                    <div class="body" style="color: #fff;">恭喜您，填写的汽车上架内容提交成功请等待我们工作人员审核！</div>
                    <div id="main" style="width: 400px;height: 400px;margin: -40px 0;"></div>
                    <div class="clearfix" style="position:relative;z-index:5;padding-right: 20px;">
                        <span class="right"><a href="/sell_init.do" style="color: #fff;">继续挂单</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/home.do" style="color: #fff;">返回首页</a></span>
                        <p style="color: #fff;text-align: left;padding-left: 20px;">已通知 <script>document.write(parseInt(Math.random()*(500-100+1)+100));</script> 家经销商</p>
                    </div>
                </div>
            </div>

            <div id="J-ModalDanger" class="comm-modal none">
                <div class="modal">
                    <div class="close j-close">&times;</div>
                    <div style="margin: 20px auto;"><img src="/portal/img/common/warn.jpg" alt=""/></div>
                    <p style="color: #e22529;text-align: center;">该售价低于平台平均价30%，请确认您的售价是否正确！</p>
                </div>
            </div>
            
            <div id="J-Modal-Review" class="comm-modal none">
		        <div class="modal">
		            <div class="close j-close">&times;</div>
		            <div class="title">上架预览</div>
		            <div class="mod-buy-modal">
		                <div id="reviewTitle" class="title"></div>
		                <div class="row">
		                    <div class="col col-6">
		                        <div id="reviewImg" class="img"></div>
		                    </div>
		                    <div class="col col-6">
		                        <div class="info-item">
		                            <span class="label">发车价：</span>
		                            <div class="input comm-text-red comm-price">&yen;<strong id="reviewPrice"></strong></div>
		                        </div>
		                        <div class="info-item">
		                            <span class="label">车辆状态：</span>
		                            <div class="input">
		                            <strong class="comm-text-huge" id="reviewSource"></strong>
		                            </div>
		                        </div>
		                    </div>
		                </div>
		                <div class="row">
		                    <div class="col col-6">
		                        <div class="info-item">
		                            <span class="label">燃油：</span>
		                            <div class="input" id="reviewFuel"></div>
		                        </div>
		                    </div>
		                    <div class="col col-6">
		                        <div class="info-item">
		                            <span class="label">排量：</span>
		                            <div class="input" id="reviewEngines"></div>
		                        </div>
		                    </div>
		                </div>
		                <div class="row">
		                    <div class="col col-12">
		                        <div class="info-item">
		                            <span class="label">颜色：</span>
		                            <div class="input" id="reviewColor"></div>
		                        </div>
		                    </div>
		                </div>
		                <div class="row">
		                    <div class="col col-12">
		                        <div class="info-item">
		                            <span class="label">车型：</span>
		                            <div class="input" id="reviewModels"></div>
		                        </div>
		                    </div>
		                </div>
		                <div class="row">
		                    <div class="col col-12">
		                        <div class="info-item">
		                            <span class="label">订金：</span>
		                            <div class="input" id="reviewDepositRatio">
		                            </div>
		                        </div>
		                    </div>
		                </div>
		                <div class="row">
		                    <div class="col col-12">
		                        <div class="info-item">
		                            <span class="label">数量：</span>
		                            <div class="input"><span class="comm-text-red" id="reviewNum"></span>辆</div>
		                        </div>
		                    </div>
		                </div>
		                <div class="row">
		                    <div class="col col-12">
		                        <div class="info-item">
		                            <span class="label">总价：</span>
		                            <div class="input text-right comm-text-red"><span class="comm-price">&yen;<strong id="reviewTotalPrice"></strong><strong>万</strong></span></div>
		                        </div>
		                    </div>
		                </div>
		                <div class="action text-center">
		                    <div class="button button-primary" onclick="saveReviewCar();">确认上架</div>
		                </div>
		            </div>
		        </div>
		    </div>
        </div>
    </div>

    <%@ include  file="/portal/footer.jsp"%>

<script src="/portal/js/vendor/jquery.js"></script>
<script src="/portal/js/app.js"></script>
<script src="/portal/js/numberFormat.js"></script>
<script src="/portal/js/sell.js"></script>
<script src="/portal/vendor/echartjs/echarts.js"></script>
<script src="/portal/js/sell0.js"></script>
    <script>
        // 显示警告弹出层
        //        dangerModal.show();

        // 显示雷达
        //        successModal.show();
    </script>
</body>
</html>
