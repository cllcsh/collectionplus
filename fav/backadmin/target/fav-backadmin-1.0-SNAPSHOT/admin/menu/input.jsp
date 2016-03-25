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
                <a href="#" name="listfnc">菜单管理</a>
                <span class="divider">/</span>
            </li>
            <li name="info">
                添加菜单
            </li>
        </ul>
    </div>
    <div class="alert alert-error center span12 errormsg" style="display: none;">
        <button type="button" class="close" data-dismiss="alert">×</button>
        <strong></strong> 
    </div>
    <div class="row-fluid sortable ui-sortable">
        <div class="box span12">
            <div class="box-header well" data-original-title="">
                <h2><i class="icon-edit"></i><span name="info">添加菜单</span></h2>
                <div class="box-icon">
                </div>
            </div>
            <div class="box-content">
                <form id="menuEditForm" class="form-horizontal">
                    <input type="hidden" name="menu.id" value="${menu.id}"/>
                    <fieldset>
                        <div class="control-group" name="name">
                            <label class="control-label" for="focusedInput">菜单名称</label>
                            <div class="controls">
                                <input class="input-xlarge focused"  type="text" name="menu.name" length="20" value="${menu.name}" />
                            </div>
                        </div>
                        <div class="control-group" name="url">
                            <label class="control-label" for="focusedInput" >菜单描述</label>
                            <div class="controls">
                                <textarea name="menu.description">${menu.description}</textarea>
                            </div>
                        </div>
                        <div class="form-actions">
                            <a href="#" class="btn btn-primary" name="save"><i class="icon-plus icon-white"></i>&nbsp;保&nbsp;存&nbsp;</a>
                            &nbsp;&nbsp;	
                            <a href="#" class="btn btn-info btn-setting" name="listfnc"><i class="icon-chevron-left icon-white"></i>&nbsp;返&nbsp;回&nbsp;</a>
                        </div>
                    </fieldset>
                </form>

            </div>
        </div><!--/span-->

    </div>


</a:view>

<script>
    $(function(){
        var id=${menu.id!=null?menu.id:-1};
        var $form = $("#menuEditForm");
        if(id!=-1){
            $("[name='info']").text("编辑菜单");
        }
        //返回上一页
        $("a[name='listfnc']").click(function(){
            back();
        });
        $("a[name='save']").click(function(){
            var fnc = getFncInfo();
            if(fnc['name']==""){
                showErrorMsg("菜单名称不能为空");
                return;
            }
            var action = id==-1?"addMenu.action":"modifyMenu.action";
            action = "<c:url value="/action/menu/"/>"+action;
            $.post(action,$form.serialize(),function(json){
                if(json.status=="success"){
                    alert('操作成功');
                    back();
                }else{
                    showErrorMsg(json.message);
                }
            },"json");
		
        });
        
        function back(){
            location.href = "<c:url value="/action/menu/listMenu.action"/>";	
        }
        
        function getFncInfo(){
            var menu={
                name:$("input[name='menu.name']").val().trim(),
                description:$("textarea[name='menu.description']").val().trim()
            }
            if(id!=-1){
                menu.id=id;
            }
            return menu;
        }
        function setFncInfo(fnc){
            if(fnc!=undefined){
                $("input[name='menu.name']").val(fnc.name);
                $("input[name='menu.description']").val(fnc.description);
            }else{
                showErrorMsg("没有找到有效信息");
                $("a[name='save']").remove();
            }
        }
        //显示错误信息
        function showErrorMsg(msg){
            var $msg_div =$("div.errormsg");
            $msg_div.find("strong").text(msg);
            $msg_div.show();
            setTimeout(function(){$msg_div.hide(500)}, 2000);
        }
        
        $("input[name='menu.name']").focus();
    })
</script>

