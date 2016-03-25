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
                	<a href="#" name="listfnc">功能管理</a>
                <span class="divider">/</span>
            </li>
            <li name="info">
			添加功能
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
						<h2><i class="icon-edit"></i><span name="info">添加功能</span></h2>
						<div class="box-icon">
						</div>
					</div>
					<div class="box-content">
						<form class="form-horizontal">
							<fieldset>
							  <div class="control-group" name="name">
								<label class="control-label" for="focusedInput">功能名称</label>
								<div class="controls">
								  <input class="input-xlarge focused" id="focusedInput" type="text" name="name" length="20">
								</div>
								</div>
								 <div class="control-group" name="url">
								<label class="control-label" for="focusedInput" >接口url</label>
								<div class="controls">
								  <input class="input-xlarge focused" id="focusedInput" type="text" name="url" length="255">
								</div>
							  </div>
							  	<div class="control-group" name="menu">
								<label class="control-label" for="focusedInput" >所属菜单</label>
								<div class="controls">
								<select id="selectError3" name="menu">
					
								  </select>									
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
    	var id=${function.id!=null?function.id:-1};
    	
    	if(id!=-1){
    		$("[name='info']").text("编辑功能");
    	}
    	//返回上一页
		$("a[name='listfnc']").click(function(){
			back();
		});
		$("a[name='save']").click(function(){
			var fnc = getFncInfo();
			if(fnc['name']==""){
				showErrorMsg("接口名称不能为空");
				return;
			}
			if(fnc['url']==""){
				showErrorMsg("接口url不能为空");
				return;
			}
			if(fnc['parentMenuId']==""){
				showErrorMsg("所属菜单不可以为空");
				return;
			}
			var action = id==-1?"saveFunction.action":"updateFunction.action";
			action = "<c:url value="/action/function/"/>"+action;
			$.post(action,{fncJson:JSON.stringify(fnc)},function(json){
				if(json.status=="success"){
					alert('操作成功');
					back();
				}else{
					showErrorMsg(json.message);
				}
			},"json");
		
		});
	
		function back(){
			location.href = "<c:url value="/action/function/listFunction.action"/>";	
		}
		function initMenu(id){
			var url = "<c:url value="/action/menu/findListMenu.action"/>";
			$.getJSON(url,null,function(json){
				var menuList = json.menuList;
				if(menuList!=undefined){
					var $sel = $("select[name='menu']");
					for(var key in menuList){
						var $option = $("<option/>").val(key).text(menuList[key]);
						$sel.append($option);
					}
					if(id!=-1){
						$sel.find("option[value="+id+"]").attr("selected","true")
					}
				}
			});
		}
		function initFncInfo(id){
			var url = "<c:url value="/action/function/findFunctionById.action"/>";
			$.getJSON(url,{'function.id':id},function(json){
				setFncInfo(json.fnc);
			});
		}
		function getFncInfo(){
			var fnc={
				name:$("input[name='name']").val().trim(),
				url:$("input[name='url']").val().trim(),
				parentMenuId:$("select[name='menu'] option:selected").val()
			}
			if(id!=-1){
				fnc.id=id;
			}
			return fnc;
		}
		function setFncInfo(fnc){
			if(fnc!=undefined){
				$("input[name='name']").val(fnc.name);
				$("input[name='url']").val(fnc.url);
				initMenu(fnc.parentMenuId);
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
    	if(id!=-1){
			initFncInfo(id);
		}else{
			initMenu(-1);
		}
		$("input[name='name']").focus();
    })
</script>

