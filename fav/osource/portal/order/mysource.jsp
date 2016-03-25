<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>收藏</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="baidu-site-verification" content="hWGw6qbfv7" />

    <link rel="shortcut icon" href="/portal/img/favicon.png">
    <link rel="apple-touch-icon" sizes="57x57" href="/portal/img/apple-touch-icon.png">
    <link rel="apple-touch-icon" sizes="72x72" href="/portal/img/apple-touch-icon-72x72.png">
    <link rel="apple-touch-icon" sizes="114x114" href="/portal/img/apple-touch-icon-114x114.png">

    <link rel="stylesheet" href="/portal/css/style.css">

</head>
<body>
<%@ include  file="/portal/inc.jsp"%>
    <div class="comm-white-ctn">
        <div class="container">
            <div class="mod-order-tabs">
                <span class="title">我的订单</span>
                <a href="/orderprotal_init.do" class="item">进货订单</a>
                <a href="/orderprotal_sell.do" class="item">出货订单</a>
                <a href="/orderprotal_my.do" class="item active">我的车源</a>
            </div>
            <div class="mod-order-list">
                <div class="table">
                    <div class="tr thead">
                        <div class="td td-main">商品信息</div>
                        <div class="td">单价</div>
                        <div class="td">库存</div>
                        <div class="td">总价</div>
                        <div class="td">车源状态 <span class="anchor"></span></div>
                        <div class="td">操作</div>
                    </div>
					
					<s:iterator var="carInfo" value="pageList.results">
                    <div class="order-item">
                        <div class="tr">
                            <div class="td-block"><strong><s:date name="#carInfo.insertDate" format="yyyy-MM-dd HH:mm:ss"></s:date></strong></div>
                        </div>
                        <div class="tr">
                            <div class="td td-main">
                                <div class="img">
                                <a href="/buy_view.do?carForm.id=<s:property value='#carInfo.id'/>">
                                <s:if test="%{#carInfo.modelyearInfo.picPath2 == null || #carInfo.modelyearInfo.picPath2 == ''}">
                                <img src="/portal/img/nocarpic.png" alt="">
                                </img>
                                </s:if>
                                <s:else>
                                <img src="<s:property value='#carInfo.modelyearInfo.picPath2'/>" alt="">
                                </s:else>
                                </a>
                                </div>
                                <a href="/buy_view.do?carForm.id=<s:property value='#carInfo.id'/>" class="info">
                                    <s:property value='#carInfo.brandInfo.name'/><br>
                                    <s:property value='#carInfo.seriesInfo.name'/><br>
                                    <s:property value='#carInfo.modelyearInfo.name'/><s:property value='#carInfo.carVersionInfo.name'/>
                                </a>
                            </div>
                            <div class="td lh100">&yen; <s:property value='#carInfo.orderNum'/><span class="comm-text-red"><s:property value='#carInfo.showPrice'/>万</span></div>
                            <div class="td lh100"><s:property value='#carInfo.surplusNum'/>(<s:property value='#carInfo.num'/>)</div>
                            <div class="td lh100">&yen; <span class="comm-text-red"><s:property value='#carInfo.showTotalPrice'/>万</span></div>
                            <div class="td lh100">
                            <s:if test="%{#carInfo.carStatus == 0}">审核中</s:if>
                            <s:if test="%{#carInfo.carStatus == 1}">在售中</s:if>
                            <s:if test="%{#carInfo.carStatus == 2}">已下架</s:if>
                            <s:if test="%{#carInfo.carStatus == 3}">已售完</s:if>
                            </div>
                            <div class="td">
                            	<s:if test="%{#carInfo.carStatus == 0 || (#carInfo.surplusNum == #carInfo.num && #carInfo.carStatus == 1)}">
                                <div class="button button-primary" onclick="showUndoSell(<s:property value='#carInfo.id'/>)">货品下架</div>
                            	</s:if>
                            </div>
                        </div>
                    </div>
                    </s:iterator>
                </div>
            </div>
            <div class="comm-pagin clearfix">
                <a class="item" onclick="turnToPage(-1);">上一页</a>
            	<s:if test="%{(pageList.pages.allPage - page) > 4}">
            	<s:iterator begin="page" end="page + 3" var="currentPage">
            	<a onclick="turnToPage(<s:property value='#currentPage'/>);" class="item <s:if test='%{#currentPage == page}'>active</s:if>"><s:property value='#currentPage'/></a>
            	</s:iterator>
            	<a class="text">...</a>
            	</s:if>
            	<s:else>
            	<s:if test="%{0 >= (pageList.pages.allPage - 4)}">
            	<s:set name="firstPage" value="1"></s:set>
            	</s:if>
            	<s:else>
            	<s:set name="firstPage" value="pageList.pages.allPage - 3"></s:set>
            	</s:else>
            	<s:iterator begin="firstPage" end="pageList.pages.allPage" var="currentPage">
            	<a onclick="turnToPage(<s:property value='#currentPage'/>);" class="item <s:if test='%{#currentPage == page}'>active</s:if>"><s:property value='#currentPage'/></a>
            	</s:iterator>
            	</s:else>
                <a class="item" onclick="turnToPage(-2);">下一页</a>
                <div class="text">共<s:property value="pageList.pages.allPage"/>页，到第<input id="turnPage" type="text" value="<s:property value='page'/>">页</div>
                <a class="item" onclick="turnToPage(0);">确定</a>
                <s:hidden id="cuurentPage" name="page"></s:hidden>
                <s:hidden id="allPage" name="pageList.pages.allPage"></s:hidden>
            </div>
        </div>
    </div>
    
    <div id="J-UndoSell" class="comm-modal none">
        <div class="modal">
            <div class="close j-close">&times;</div>
            <h1 class="title">商品下架</h1>
            <div class="text"><textarea id="J-TextReason" placeholder="填写下架原因"></textarea></div>
            <div class="action">
                <div class="button button-primary j-ok" onclick="undoSell()">确认下架</div>
            </div>
            <s:hidden id="undoCarId"></s:hidden>
        </div>
    </div>

<%@include file="/portal/footer.jsp" %>   
<script src="/portal/js/vendor/jquery.js"></script>
<script src="/portal/js/app.js"></script>
<script type="text/javascript">
$(function(){
	// 显示下架原因
	modal = new FACHE.UI.Modal({
        id : 'J-UndoSell'
    });
    new FACHE.UI.Btp();
});

// 弹出下架原因框
function showUndoSell(id) {
	modal.show();
	$("#undoCarId").val(id);
}

function undoSell() {
	var reason = $.trim($("#J-TextReason").val());
	 if (reason == "") {
		 alert("请您填写下架原因!");
		 return false;
	 }

	 $.ajax({
		type: "POST",
		url: "/sell_undoSell.do",
		data: {"carForm.id":$("#undoCarId").val(), "carForm.reason":reason, "carForm.carStatus":2},
		success: function(data){
			alert(data.message);
			if (data.codeid == 0) {
				document.location.href = "/orderprotal_my.do";
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("订单取消失败");
		},
		dataType: "json"
	});
}

/**
 * 分页跳转
 * @param toPage
 * @return
 */
function turnToPage(toPage) {
	if (toPage == -1) { // 上一页
		if ($("#cuurentPage").val() == 1) {
			return false;
		}
		var page = $("#cuurentPage").val() - 1;
		if (page < 1) {
			$("#cuurentPage").val(1);
		} else {
			$("#cuurentPage").val(page);
		}
	} else if (toPage == -2) { // 下一页
		if ($("#cuurentPage").val() == $("#allPage").val()) {
			return false;
		}
		var page = $("#cuurentPage").val() + 1;
		if (page > $("#allPage").val()) {
			$("#cuurentPage").val($("#allPage").val());
		} else {
			$("#cuurentPage").val(page);
		}
	} else if (toPage == 0) { // 输入框跳转
		if ($("#cuurentPage").val() == $("#turnPage").val()) {
			return false;
		}
		var page = $("#turnPage").val();
		if (page > $("#allPage").val()) {
			return false;
		} else if (page < 1) {
			return false;
		} else {
			$("#cuurentPage").val(page);
		}
	} else {
		if (toPage == $("#cuurentPage").val()) {
			return false;
		} else {
			$("#cuurentPage").val(toPage);
		}
	}

	document.location.href = "/orderprotal_my.do?page=" + $("#cuurentPage").val();
}
</script>
</body>
</html>