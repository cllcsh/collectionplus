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
                菜单管理
            </li>
        </ul>
    </div>

    <div class="row-fluid sortable">		
        <div class="box span12">
            <div class="box-header" data-original-title>
                <h2><i class="icon-list"></i> 菜单列表</h2>
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
                            <th>菜单名称</th>
                            <th>菜单描述</th>
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
        var action = "<c:url value="/action/menu/findMenuList.action"/>";
        var page = new Page($(".pagination"),action);
		
        
        function buildRow(i,menu){
            var $tr = $tld.clone();
            //			var type_text;
            //			var $type_span = $("<span/>");
            //			var $a_enabld =$("<a/>");
            $tr.attr("menuid",menu.id);
            $tr.children("td").eq(0).text(menu.name);
            $tr.children("td").eq(1).text(menu.description);
            //			$tr.children("td").eq(2).text(menu.parentMenuId);
            //			if(menuList!=undefined){
            //				$tr.children("td").eq(2).text(menuList[menu.parentMenuId]);
            //			}
            var $buttons = $tr.children("td").eq(2);
            var id = menu.id;
            $buttons.find("a.delete").click(function(){
                if(confirm("确定删除"+menu.name+"菜单吗?")){
                    $.post("<c:url value="/action/menu/removeMenu.action"/>",{"menu.id":id},function(json){
                        if(json.status=="success"){
                            page.pageTo(page.getPageNumber());
                        }else{
                            alert("删除失败,原因:"+json.message);
                        }
					
                    },"json");
                }
            });
            $buttons.find("a.edit").click(function(){
                location.href = "<c:url value="/action/menu/initEditMenu.action"/>"+"?menu.id="+id;
            });
            return $tr;
        }
        page.showContent = function(){
            $table.html("");
            $.each(page.getPageList(),function(i,menu){
                var $tr = buildRow(i,menu);
                $table.append($tr);
            });
        }
        $("a.add").click(function(){
            location.href = "<c:url value="/action/menu/initAddMenu.action"/>";	
        });
        
        page.init();
		
    })
</script>

