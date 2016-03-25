<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include  file="/portal/header.jsp"%>

    <link rel="stylesheet" href="/portal/css/style.css">
	<style type="text/css">
	.mod-order-list .table .order-item .tr .td {
		height: 24px;
	}
	.mod-order-list .order-item .tr .lh100 {
		line-height: 40px;
	}
	</style>
</head>
<body>
<%@ include file="/portal/inc.jsp"%>
    <div class="comm-white-ctn">
        <div class="container">
            <%@ include  file="/portal/user/left.jsp"%>

            <!-- 用户中心主内容区 -->
            <div class="mod-user-main">
                <div class="mod-user-section-title">个人关注
                	<div class="right">
						<a class="button button-primary" href="/userportal_addUserAttention.do" style="font-size:12px;color:#ffffff;">添加关注</a>
		            </div>
                </div>
                <div class="mod-order-list">
                	<div class="table">
	                    <div class="tr thead">
	                        <div class="td td-main" style="width: 24%;">车型</div>
	                        <div class="td" style="width: 10%;">品牌</div>
	                        <div class="td" style="width: 10%;">版本</div>
	                        <div class="td" style="width: 13%;">系列</div>
	                        <div class="td" style="width: 18%;">价格区间</div>
	                        <div class="td" style="width: 12%;">首款区间</div>
	                        <div class="td" style="width: 13%;">操作</div>
	                    </div>
						<s:iterator var="userAttentionInfo" value="%{pageList.results}">
						<div class="order-item">
							<div class="tr">
								<div class="td td-main" style="width: 24%;"><s:property value='#userAttentionInfo.modelsInfo.name'/></div>
								<div class="td lh100" style="width: 10%;"><s:property value='#userAttentionInfo.modelsInfo.brandName'/></div>
								<div class="td lh100" style="width: 10%;"><s:property value='#userAttentionInfo.modelsInfo.versionName'/></div>
								<div class="td lh100" style="width: 13%;"><s:property value='#userAttentionInfo.modelsInfo.seriesName'/></div>
								<div class="td lh100" style="width: 18%;">&yen; <s:property value='#userAttentionInfo.showStartPrice'/>万 - &yen; <s:property value='#userAttentionInfo.showEndPrice'/>万</div>
								<div class="td lh100" style="width: 12%;"><s:property value='#userAttentionInfo.startDepositRatio'/>0% - <s:property value='#userAttentionInfo.endDepositRatio'/>0%</div>
								<div class="td action" style="width: 13%;">
									<div class="button button-link" onclick="unAttention(<s:property value='#userAttentionInfo.id'/>);">取消关注</div>
								</div>
							</div>
						</div>
						</s:iterator>
	            	</div>
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
            <!-- ./用户中心主内容区 -->
        </div>
    </div>

<%@ include  file="/portal/footer.jsp"%>
<script src="/portal/js/vendor/jquery.js"></script>
<script src="/portal/js/app.js"></script>
<script type="text/javascript">
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
		var page = parseInt($("#cuurentPage").val()) - 1;
		if (page < 1) {
			$("#cuurentPage").val(1);
		} else {
			$("#cuurentPage").val(page);
		}
	} else if (toPage == -2) { // 下一页
		if ($("#cuurentPage").val() == $("#allPage").val()) {
			return false;
		}
		var page = parseInt($("#cuurentPage").val()) + 1;
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
	
	document.location.href = "/userportal_userAttention.do?page=" +  $("#cuurentPage").val();
}

// 取消关注
function unAttention(id) {
	var se=confirm("是否确认取消关注？");
	if (se==true) {
		$.ajax({
			type: "POST",
			url: "/userportal_deleteUserAttention.do",
			data: {"id":id},
			success: function(data){
				alert(data.message);
				if (data.codeid == 0) {
					document.location.href = "/userportal_userAttention.do";
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				alert("获取数据失败");
			},
			dataType: "json"
		});
	}
}
</script>
</body>
</html>

