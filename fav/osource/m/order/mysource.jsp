<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<head>
    <title>进货订单</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="baidu-site-verification" content="hWGw6qbfv7" />
    
    <link rel="stylesheet" href="/m/css/style.css">

</head>
<body>
    <header class="comm-header">
    <div class="left opt">
        <a href="/muserportal_init.do">返回</a>
    </div>
    <h1 class="title">进货订单</h1>
</header>


    <div class="wrap">
        <div class="mod-order-tab">
            <div class="item"><a href="/morderprotal_init.do">进货订单</a></div>
            <div class="item"><a href="/morderprotal_sell.do">出货订单</a></div>
            <div class="item active"><a href="/morderprotal_my.do">我的车源</a></div>
        </div>

        <div id="J-OrderMyList" class="mod-order-list">
		<s:iterator var="carInfo" value="pageList.results">
           <a href="/mbuy_view.do?carForm.id=<s:property value='#carInfo.id'/>" class="item">
                <div class="no"><s:date name="#carInfo.insertDate" format="yyyy-MM-dd HH:mm:ss"></s:date></div>
                <div class="base-info">
                    <div class="img">
                    	<img src="<s:property value='#carInfo.modelyearInfo.picPath2'/>" alt="">
                    </div>
                    <div class="info">
                        <div class="title">
							<s:property value='#carInfo.brandInfo.name'/>
							<s:property value='#carInfo.seriesInfo.name'/>
							<s:property value='#carInfo.modelyearInfo.name'/>
							<s:property value='#carInfo.carVersionInfo.name'/>
						</div>
                        <div class="comm-text-gray">库存：<s:property value='#carInfo.surplusNum'/>(<s:property value='#carInfo.num'/>)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;单价:<span class="comm-text-red"><s:property value='#carInfo.showPrice'/>万</div>
                        <div class="comm-text-gray">总价：<s:property value='#carInfo.showTotalPrice'/>万</span>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            车源状态：
							<s:if test="%{#carInfo.carStatus == 0}">审核中</s:if>
                            <s:if test="%{#carInfo.carStatus == 1}">在售中</s:if>
                            <s:if test="%{#carInfo.carStatus == 2}">已下架</s:if>
                            <s:if test="%{#carInfo.carStatus == 3}">已售完</s:if>
                        </div>
                    </div>
    			</div>
             	<div class="actions">
						<div  class="item" onclick="undoSell(<s:property value='#carInfo.id'/>);return false;">货品下架</div>
                    <!--<div class="item" onclick="cancelModal();return false;">取消订单</div>-->
                </div>
            </a>
		</s:iterator>
		<!--
            <a href="#" class="item">
                <div class="no">订单号：12342321567 <span class="right status" style="background-color: #c3b735">在售中</span></div>
                <div class="base-info">
                    <div class="img"><img src="../img/data/car.jpg" alt=""></div>
                    <div class="info">
                        <div class="title">东风现代<span class="comm-text-gray">|</span>408 1.6T 两厢版本</div>
                        <div class="comm-text-gray">购买时间：2015年7月1日 13:43:21</div>
                        <div class="comm-text-gray">总价：<span class="comm-text-red">210万</span>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            数量：<span class="comm-text-red">5辆</span>
                        </div>
                    </div>
                </div>
                <div class="actions">
                    <div class="item" onclick="payModal();return false;">立即支付</div>
                </div>
            </a>
            <a href="#" class="item">
                <div class="no">订单号：12342321567 <span class="right status" style="background-color: #e22529">审核中</span></div>
                <div class="base-info">
                    <div class="img"><img src="/m/img/data/car.jpg" alt=""></div>
                    <div class="info">
                        <div class="title">东风现代<span class="comm-text-gray">|</span>408 1.6T 两厢版本</div>
                        <div class="comm-text-gray">购买时间：2015年7月1日 13:43:21</div>
                        <div class="comm-text-gray">总价：<span class="comm-text-red">210万</span>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            数量：<span class="comm-text-red">5辆</span>
                        </div>
                    </div>
                </div>
                <div class="actions">
                    <div class="item" onclick="undoSell();return false;">货品下架</div>
                </div>
            </a>
            <a href="#" class="item">
                <div class="no">订单号：12342321567 <span class="right status" style="background-color: #5e9bd9">已下架</span></div>
                <div class="base-info">
                    <div class="img"><img src="../img/data/car.jpg" alt=""></div>
                    <div class="info">
                        <div class="title">东风现代<span class="comm-text-gray">|</span>408 1.6T 两厢版本</div>
                        <div class="comm-text-gray">购买时间：2015年7月1日 13:43:21</div>
                        <div class="comm-text-gray">总价：<span class="comm-text-red">210万</span>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            数量：<span class="comm-text-red">5辆</span>
                        </div>
                    </div>
                </div>
            </a>
            <a href="#" class="item">
                <div class="no">订单号：12342321567 <span class="right status" style="background-color: #db9ef4">待确认</span></div>
                <div class="base-info">
                    <div class="img"><img src="../img/data/car.jpg" alt=""></div>
                    <div class="info">
                        <div class="title">东风现代<span class="comm-text-gray">|</span>408 1.6T 两厢版本</div>
                        <div class="comm-text-gray">购买时间：2015年7月1日 13:43:21</div>
                        <div class="comm-text-gray">总价：<span class="comm-text-red">210万</span>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            数量：<span class="comm-text-red">5辆</span>
                        </div>
                    </div>
                </div>
            </a>-->
        </div>
        <div class="comm-loadmore" data-offset="1" data-list-id="J-OrderMyList" data-size="10" data-url="/morderprotal_my.do">点击加载更多</div>
    </div>

    <div id="J-Pay" class="comm-modal none">
        <div class="modal" style="margin-top: 20px;">
            <div class="close j-close">&times;</div>
            <h1 class="title">支付</h1>
            <div class="body">
                <div class="form-group">
                    <label for="" class="label">开户行：</label>
                    <div class="input">中国招商银行</div>
                </div>
                <div class="form-group">
                    <label for="" class="label">银行账号：</label>
                    <div class="input">1234 1234 1234 1234</div>
                </div>
                <div class="form-group">
                    <label for="" class="label">开户人：</label>
                    <div class="input">张三</div>
                </div>
                <div class="form-group">
                    <label for="" class="label">备注：</label>
                    <div class="input">这里是备注这里是备注这里是备注这里是备注这里是备注这里是备注这里是备注这里是备注这里是备注这里是备注这里是备注这里是备注这里是备注</div>
                </div>
            </div>
            <div class="action">
                <div class="button button-primary j-ok">确认</div>
            </div>
        </div>
    </div>

    <div id="J-Modal" class="comm-modal none">
        <div class="modal" style="margin-top: 20px;">
            <div class="close j-close">&times;</div>
            <div class="title">取消订单的理由</div>
            <div class="mod-buy-modal" style="padding: 10px 20px;">
                <div class="radio form-group">
                    <label for="dontwant">不想买了</label>
                    <input type="radio" id="dontwant" name="reason" value="不想买了"/>
                </div>
                <div class="radio form-group">
                    <label for="cantgo">无法及时到店提车</label>
                    <input type="radio" id="cantgo" name="reason" value="无法及时到店提车"/>
                </div>
                <div class="radio form-group">
                    <label for="missing">卖家车源已不在</label>
                    <input type="radio" id="missing" name="reason" value="卖家车源已不在"/>
                </div>
                <div class="radio form-group">
                    <label for="notfit">车源颜色、配置、随车工具与合同不符</label>
                    <input type="radio" id="notfit" name="reason" value="车源颜色、配置、随车工具与合同不符"/>
                </div>
                <div class="radio form-group">
                    <label for="unsell">卖家未按合同规定日期发货</label>
                    <input type="radio" id="unsell" name="reason" value="卖家未按合同规定日期发货"/>
                </div>
                <div class="radio form-group">
                    <label for="other">其他理由</label>
                    <input type="radio" id="other" name="reason" value="其他理由"/>
                </div>
                <div class="form-group">
                    <textarea name="" id="" cols="30" rows="10"></textarea>
                </div>
                <div class="action text-center">
                    <input type="hidden" id="cancelOrderId" />
                    <div class="button button-primary" onclick="cancelOrder();">确认取消</div>
                </div>
            </div>
        </div>
    </div>

    <div id="J-UndoSell" class="comm-modal none">
        <div class="modal">
            <div class="close j-close">&times;</div>
            <h1 class="title">商品下架</h1>
            <div class="text"><textarea id="J-TextReason" placeholder="填写下架原因"></textarea></div>
            <div class="action">
                <div class="button button-primary j-ok">确认下架</div>
				<s:hidden id="undoCarId"></s:hidden>
            </div>
        </div>
    </div>

    <script src="/m/bower_components/zepto/zepto.min.js"></script>
    <script src="/m/bower_components/zeptotouch/zepto-touch.min.js"></script>
    <script src="/m/vendor/zepto-selector.js"></script>
    <script src="/m/js/app.js"></script>
    <script>
        window.onload = function(){
            Fache.bindLoadMore();

            var payModal;
            window.payModal = function(){
                if (payModal) {
                    payModal.show();
                    return;
                }
                payModal = new Fache.UI.Modal({
                    id: 'J-Pay',
                    ok: function(){
                        payModal.close();
                    }
                });
                payModal.show();
            };

            var cancelModal;
            window.cancelModal = function(){
                if (cancelModal) {
                    cancelModal.show();
                    return;
                }
                cancelModal = new Fache.UI.Modal({
                    id: 'J-Modal',
                    ok: function(){
                        cancelModal.close();
                    }
                });
                cancelModal.show();
            };

            // 显示下架原因
            var undoModal;
            window.undoSell = function(id){
				$("#undoCarId").val(id);
                var textarea = $('#J-TextReason');
                textarea.val('');
                if (undoModal) {
                    undoModal.show();
                    return;
                }
                undoModal = new Fache.UI.Modal({
                    id: 'J-UndoSell',
                    ok: function(){
                        console.debug(textarea.val());
                        undoModal.close();
                    }
                });
                undoModal.show();
            };
        };
    </script>
</body>
</html>
