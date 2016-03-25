<%@page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	<style type="text/css">
		a:link,a:visited {
			text-decoration: none; /*超链接无下划线*/
		}
		
		a:hover {
			text-decoration: underline; /*鼠标放上去有下划线*/
		}
	</style>
	<input type="hidden" id="actionInput" />
	<table width="100%;" border="0" cellpadding="0" cellspacing="0"
		style="word-break: break-all;">
		<tr>
			<td align="right" style="font-size: 13px;">
				共
				<span id="countSpan"></span>
				个
			  	<a href="#" onclick="toFirstPage();" name="firstPage">首页</a>
				<a href="#" onclick="toPage(-1);" name="previousPage">上一页</a>
				
				<a href="#" onclick="toPage(1);" name="nextPage">下一页</a>
				<a href="#" onclick="toLastPage();" name="endPage">末页</a>
				<span id="currentPageSpan"></span>
				/
				<span id="countPageSpan"></span>
				页 每页
				<select name="pageSizeId" style="width: 50px;"
					onchange="toPageSize();">
					<option value="10">
						10
					</option>
					<option value="20">
						20
					</option>
					<option value="50">
						50
					</option>
					<option value="100">
						100
					</option>
				</select>
				条到第
				<input type="text" id="currentPageInput" name="inputCurrentPage" style="width: 25px" onkeyup="changePage();"/>
				页
				<input type="button" class="button_cls" value="跳转" onclick="toPage(0);" id="go" />
			</td>
		</tr>
	</table>
	<script type="text/javascript">
	var cpage = 1;
	var countpage = 1;
	function initPage(){
		cpage = parseInt($("#currentPageSpan").text(),10);
		countpage = parseInt($("#countPageSpan").text(),10);
		if (cpage == 1){
			$("a[name='previousPage']").attr("disabled",true);
			$("a[name='firstPage']").attr("disabled",true);
		}else{
			$("a[name='previousPage']").attr("disabled",false);
			$("a[name='firstPage']").attr("disabled",false);
		}
		
		if (cpage == countpage){
			$("a[name='nextPage']").attr("disabled",true);
			$("a[name='endPage']").attr("disabled",true);
		}else{
			$("a[name='nextPage']").attr("disabled",false);
			$("a[name='endPage']").attr("disabled",false);
		}
	}
	
	function toFirstPage(){
		if ($("#currentPageSpan").text() == 1){
			return;
		}
		setFormPage(1,$("select[name='pageSizeId']").val());
		pageAjaxFormSubmit();
	}
	
	function toLastPage(){
		if ($("#currentPageSpan").text() == $("#countPageSpan").text()){
			return;
		}
		setFormPage($("#countPageSpan").text(),$("select[name='pageSizeId']").val());
		pageAjaxFormSubmit();
	}
	
	function toPage(goToPage){
		if(goToPage==-1 && $("a[name='previousPage']").attr("disabled")){
			return;
		}
		
		if(goToPage==1 && $("a[name='nextPage']").attr("disabled")){
			return;
		}
	
		var value = $("select[name='pageSizeId']").val();	
		if(goToPage==0){
			var p = $("input[name='inputCurrentPage']").val();
			var reg = /^[0-9]*[1-9][0-9]*$/ ;
			if (!reg.test(p)){
				alert("请输入正整数跳转！");
				return;
			}
			
			setFormPage(p,value);
		}else{
			setFormPage(cpage+goToPage,value);
		}
		
		pageAjaxFormSubmit();
	}
	function toPageSize(){
		var pageSize = $("#pageSize").val();
		$("select[name='pageSizeId']").each(function() {
			if ($(this).val() != pageSize){
				pageSize = $(this).val();
				return false;
			}
		});
		$("select[name='pageSizeId']").val(pageSize);
		setFormPage(cpage,pageSize);
		pageAjaxFormSubmit();
	}
	
	function changePage(){
		$("input[name='inputCurrentPage']").each(function() {
			if ($(this).val() != cpage){
				cpage = $(this).val();
				return false;
			}
		});
		$("input[name='inputCurrentPage']").val(cpage);
	}
	
	//form提交
	function pageAjaxFormSubmit(){
		document.browseForm.action = $("#actionInput").val();
		dialogForQuery();
	}
			
	function setFormPage(currentPage,pageSize){
		document.browseForm.currentPage.value = currentPage;
		document.browseForm.pageSize.value = pageSize;
	}
			
	//重置分页参数
	function resetFormPage(){
		$("select[name='pageSizeId']").val($("#pageSize").val()); 
	}
	resetFormPage();
</script>