<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include  file="/portal/header.jsp"%>

    <link rel="stylesheet" href="/portal/css/style.css">

</head>
<script src="/portal/js/vendor/jquery.js"></script>
<script src="/portal/vendor/jquery.form.js"></script>
<script src="/portal/js/app.js"></script>
<script type="text/javascript" src="/resource/js/geo.js"></script>
<body>
<%@ include  file="/portal/inc.jsp"%>
    <div class="comm-white-ctn">
        <div class="container">
            <%@ include  file="/portal/user/left.jsp"%>
            <!-- 用户中心主内容区 -->
            <div class="mod-user-main">
                <div class="mod-user-section-title">账户流水</div>
                <div class="mod-user-msg-list">
                	<table>
                		<tr>
                			<td>发生时间</td>
			                <td>订单号</td>
			                <td>收入</td>
			                <td>支出</td>
			                <td>银行</td>
			                <td>银行账户</td>
			                <td>事由</td>
			                <td>备注</td>
                		</tr>
                		<s:iterator var="payRecord" value="%{pageList.results}">
	                		<tr>
	                			<td><s:date name='#payRecord.insertDate' format="yyyy-MM-dd HH:mm"/></td>
				                <td>
				                	<a href="/orderprotal_view.do?viewType=buy&orderForm.id=<s:property value='#payRecord.orderid'/>">
			                        	<s:property value='#payRecord.orderCode'/>
			                    	</a>
			                    </td>
				                <td>0.00</td>
				                <td><s:property value='#payRecord.price'/></td>
				                <td><s:property value='#payRecord.bank'/></td>
				                <td><s:property value='#payRecord.bankAccount'/></td>
				                <td>
									<s:if test="%{#payRecord.type==0}">首款</s:if>
	                   	 			<s:if test="%{#payRecord.type==1}">余款</s:if>
								</td>
				                <td></td>
	                		</tr>
                		</s:iterator>
                	</table>
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


<script>
function updateBank()
{
	//$("#profileForm").submit();
	$.ajax({
		type: "POST",
		url: "/userportal_updateBank.do",
		data: $("#bankForm").formSerialize(),
		success: function(data){
			alert(data.message);
			if(data.codeid == 0){
				document.location.href="userportal_bank.do";
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			//alert("获取品牌失败");
		},
		dataType: "json"
	});

}
</script>
</body>
</html>
