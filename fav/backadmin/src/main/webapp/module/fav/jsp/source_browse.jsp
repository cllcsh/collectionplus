<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 提示信息 -->
<style type="text/css">
	    .tr-color{
	         background-color:#FFFFFF;
	    }
	    .cursor{
	        cursor:pointer;
	    }
	</style>
<SCRIPT type="text/javascript">
$(function() {
	$("#dialog-cancel-form").dialog({
		bgiframe: true,
		autoOpen: false,
		width:900,
		height:350,
		modal: true,
		buttons: {
		},
		close: function() {
		}
	});
});

var sourceIdT,sourceNameT,sourceActionT;
function dialogForBrowse(sourceId,sourceName, sourceAction){
	$("#searchName").val("");
   	$("#pageSize").val(10);	
    $("#currentPage").val(1);		
	sourceIdT = sourceId;
	sourceNameT = sourceName;
	sourceActionT = sourceAction;
	$("#actionInput").val(sourceActionT);
	$.ajax({
		  type : "POST",
		  url : sourceActionT,
		  dataType : "JSON",
		  data : $("#browseForm").serialize(),
		  async: false,
		  success : function(jsonMessage){
			  $.each(jsonMessage,function(key,value){
			  	  if (key == 'list'){
			  	  	  var payments = eval(value);
			  	  	  $("#paymentsTable tr:not(:eq(0))").remove();
					  $.each(payments, function(indexArray,item){
					  	$("#paymentsTable").append("<tr id=" + indexArray + " class=\"tr-color cursor\"></tr>");
					  	$("#paymentsTable #" + indexArray).append("<td width=\"20%\" align=\"center\" bgcolor=\"#f8f8f8\"><a href=\"#\" onclick=\"choose('" + item.id + "','" + item.name + "');\">选择</a></td>")
					  		.append("<td width=\"85%\" bgcolor=\"#f8f8f8\">" + item.name + "</td>")
				  	  });
			  	  }else if (key == 'page'){
				  	   	var page = eval(value);	  
				  	   	$("#countSpan").text(page.count);
				  	   	$("#currentPageSpan").text(page.currentPage);
				  	   	$("#countPageSpan").text(page.countPage);
				  	   	$("#currentPageInput").val(page.currentPage);
				  	   	$("#pageSize").val(page.pageSize);	
				  	    $("#currentPage").val(page.currentPage);		
			  	  }
			  });
			  
			  initPage();
		  }
	});
	$("#dialog-cancel-form").dialog("open");		
}
    
function dialogForQuery(){
	$.ajax({
		  type : "POST",
		  url : sourceActionT,
		  dataType : "JSON",
		  data : $("#browseForm").serialize(),
		  async: false,
		  success : function(jsonMessage){
			  $.each(jsonMessage,function(key,value){
			  	  if (key == 'list'){
			  	  	  var payments = eval(value);
			  	  	  $("#paymentsTable tr:not(:eq(0))").remove();
					  $.each(payments, function(indexArray,item){
					  	$("#paymentsTable").append("<tr id=" + indexArray + " class=\"tr-color cursor\"></tr>");
					  	$("#paymentsTable #" + indexArray).append("<td width=\"20%\" align=\"center\" bgcolor=\"#f8f8f8\"><a href=\"#\" onclick=\"choose('" + item.id + "','" + item.name + "');\">选择</a></td>")
					  		.append("<td width=\"85%\" bgcolor=\"#f8f8f8\">" + item.name + "</td>")
				  	  });
			  	  }else if (key == 'page'){
				  	   	var page = eval(value);	  
				  	   	$("#countSpan").text(page.count);
				  	   	$("#currentPageSpan").text(page.currentPage);
				  	   	$("#countPageSpan").text(page.countPage);
				  	   	$("#currentPageInput").val(page.currentPage);
				  	   	$("#pageSize").val(page.pageSize);	
				  	    $("#currentPage").val(page.currentPage);		
			  	  }
			  });
			  initPage();
		  }
	});
}

function choose(sourceIdVar,sourceNameVar){
	$("#dialog-cancel-form").dialog("close");
	$("#"+ sourceIdT).val(sourceIdVar);
	$("#"+ sourceNameT).val(sourceNameVar);
	
}
</SCRIPT>
<div id="dialog-cancel-form" title="浏览">
	<div class="border_line_5 backgroudbar margin_top_5 margin_bottom_5">
			<form id="browseForm" name="browseForm" action="" method="post" onsubmit="return false;">
				<input id="currentPage" type="hidden" value="1" name="page">
				<input id="pageSize" type="hidden" value="10" name="limit">	
				 <div class="margin_serach">
				 	<table>
				 		<tr>
				 			<td>
						   		名称:<input type="text" id="searchName" name="searchName" />
						   		<input class="button_cls" type="button" onclick="dialogForQuery();" value="查询"/>
						   	</td>
						</tr>
					</table>
				</div>
			</form>
	</div>
	<table id="paymentsTable" width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#000000">
		<tr class="tr-color cursor">	
		  <th width="20%" bgcolor="#f8f8f8">选择</th>
		  <th width="80%" bgcolor="#f8f8f8">名称</th>
		</tr>
	</table>
	<div id="pageDiv" class="pageBar">
		<%@ include file="pageJson.jsp"%>
    	</div>
</div>