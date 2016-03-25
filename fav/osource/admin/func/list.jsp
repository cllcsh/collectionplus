<%-- 
    Document   : 功能列表
    Created on : 2013-7-12, 17:41:36
    Author     : MaYichao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="a" tagdir="/WEB-INF/tags/admin/"%>
<a:view>

    <div>
        <ul class="breadcrumb">
            <li>
                <a href="#">首页</a> <span class="divider">/</span>
            </li>
            <li>
                功能管理
            </li>
        </ul>
    </div>

    <div class="row-fluid sortable">		
        <div class="box span12">
            <div class="box-header" data-original-title>
                <h2><i class="icon-list"></i> 功能列表</h2>
                <!--                <div class="box-icon">
                                    <a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
                                    <a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
                                    <a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
                                </div>-->
                <div class="box-icon">
                    <a href="#" class="btn btn-round add"><i class="icon-plus"></i></a>

                </div>

            </div>
            <div class="box-content">
                <table id="functionTable" class="table table-striped table-bordered  ">
                    <thead>
                        <tr>
                            <th>功能名称</th>
                            <th>接口Url</th>
                            <th>所属菜单</th>
                            <th>操作</th>
                        </tr>
                    </thead>   
                    <tbody>
                    </tbody>
                </table>   
            </div>
            <div class="pagination pagination-centered"> </div>
        </div>
             
    </div>

<div id="template" style="display: none;">
    <table>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td class="center">                              
            <a class="btn btn-info edit" href="#"><i class="icon-edit icon-white"></i> 编辑</a>
            <a class="btn btn-danger delete" href="#"><i class="icon-trash icon-white"></i>删除</a>
            </td>
        </tr>
    </table>
</div>    
</a:view>

<script>
    $(function(){
		var $table = $("#functionTable tbody");
		var $tld = $("#template").find("tr").eq(0);
		var action = "<c:url value="/action/function/getListFunction.action"/>";
		var page = new Page($(".pagination"),action);
		var menuList;
		initMenuList();
		function initMenuList(){
			var url = "<c:url value="/action/menu/findListMenu.action"/>";
			$.getJSON(url,null,function(json){
				menuList = json.menuList;
				//菜单列表初始完后,再初始化功能信息
				page.init();
			});
		}
		function buildRow(i,fnc){
			var $tr = $tld.clone();
			var type_text;
			var $type_span = $("<span/>");
			var $a_enabld =$("<a/>");
			$tr.attr("fncid",fnc.id);
			$tr.children("td").eq(0).text(fnc.name);
			$tr.children("td").eq(1).text(fnc.url);
			$tr.children("td").eq(2).text(fnc.parentMenuId);
			if(menuList!=undefined){
				$tr.children("td").eq(2).text(menuList[fnc.parentMenuId]);
			}
			var $buttons = $tr.children("td").eq(3);
			var id = fnc.id;
			$buttons.find("a.delete").click(function(){
				if(confirm("确定删除"+fnc.name+"功能吗?")){
					$.post("<c:url value="/action/function/delFunction.action"/>",{"function.id":id},function(json){
						if(json.status=="success"){
							page.pageTo(page.getPageNumber());
						}else{
							alert("删除失败,原因:"+json.message);
						}
					
					},"json");
				}
			});
			$buttons.find("a.edit").click(function(){
					location.href = "<c:url value="/action/function/editFunction.action"/>"+"?function.id="+id;
			});
			return $tr;
		}
		page.showContent = function(){
			$table.html("");
			$.each(page.getPageList(),function(i,fnc){
				var $tr = buildRow(i,fnc);
				$table.append($tr);
			});
		}
		$("a.add").click(function(){
			location.href = "<c:url value="/action/function/editFunction.action"/>";	
		});
		
    })
</script>

