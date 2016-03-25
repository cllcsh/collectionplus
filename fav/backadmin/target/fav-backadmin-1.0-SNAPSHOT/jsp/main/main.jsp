<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<title>收藏</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
<meta name="author" content="Muhammad Usman">

<!-- The styles -->

<link id="bs-css" href="admin/css/bootstrap-eagle.css" rel="stylesheet">
<style type="text/css">
    body {
/*        padding-bottom: 10px;*/
    }
    .sidebar-nav {
        padding: 9px 0;
    }

	.headh1{
background-color: #0067C6;
color: #fff;
font-size: 20px;
font-family: "黑体";
font-weight: normal;
line-height: 40px;
width: 100%;
text-align: center;
letter-spacing: 2px;
padding: 20px;
margin:32px;
}
</style>
<link href="admin/css/opa-icons.css" rel='stylesheet'>
<link rel="shortcut icon" href="admin/img/favicon.ico">
</head>
<body style="min-width: 1000px">

<!-- topbar starts -->
<div class="navbar">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
			
             <a class="brand" href="main.do" style="width: auto;"> <img alt="Charisma Logo" src="admin/img/icon.png"  style="height: 40px ;width: auto ;margin: -8px 0px -12px"/></a>

            
            <!-- <font class="headh1">收藏平台</font>
            <!-- user dropdown starts -->
            <div class="btn-group pull-right" >
			
                <a class="btn dropdown-toggle" data-toggle="dropdown" href="javascript:void(0)">
                    <i class="icon-user icon icon-green"></i><span class="hidden-phone"><s:property value="userSession.userName"/></span>
                    <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="module/system/user_modifyPasswordInit.do" target="mainIFrame">修改密码</a></li>
                    <!--<li class="divider"></li>-->
                    <li><a href="logout.do">注销</a></li>
                </ul>
            </div>
            <!-- user dropdown ends -->
            <!-- hidden nav starts -->
            <ul class="nav nab-pills pull-right">
                <li>
                    <a href="javascript:void(0)" id="hidden_nav">
                        <i class="icon-carat-2-ew icon icon-green" ></i>
                    </a>
                </li>

            </ul>
        </div>
    </div>
</div>
<!-- topbar ends -->
<div class="container-fluid">
    <div class="row-fluid">

        



<!-- left menu starts -->
<div class="span2 main-menu-span" id="mainNav">
    <div class="well nav-collapse sidebar-nav">
        <ul class="nav nav-tabs nav-stacked main-menu">

        </ul>
        <!--<label id="for-is-ajax" class="hidden-tablet" for="is-ajax"><input id="is-ajax" type="checkbox"> Ajax on menu</label>-->
    </div><!--/.well -->
</div><!--/span-->
<!-- left menu ends -->

        <noscript>
        <div class="alert alert-block span10">
            <h4 class="alert-heading">Warning!</h4>
            <p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a> enabled to use this site.</p>
        </div>
        </noscript>

        <div id="content" class="span10">
            <!-- content starts -->
            
<table width="100%" height="90%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="100%" valign="top"><iframe height="100%" width="100%" style="height:90%" id="mainIFrame" name="mainIFrame" frameborder="0" src="firstpage_init.do"></iframe></td>
  </tr>
</table>



            <!-- content ends -->
        </div><!--/#content.span10-->
    </div><!--/fluid-row-->

</div><!--/.fluid-container-->

</body>





<!-- external javascript
        ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->

<!-- jQuery -->
<script src="admin/js/jquery-1.7.2.min.js"></script>
<script >
    $.url = function(path){
        return "" + path;
    }
</script>
<!-- jQuery UI -->
<script src="admin/js/jquery-ui-1.8.21.custom.min.js"></script>
<!-- transition / effect library -->
<script src="admin/js/bootstrap-transition.js"></script>
<!-- alert enhancer library -->
<script src="admin/js/bootstrap-alert.js"></script>
<!-- modal / dialog library -->
<script src="admin/js/bootstrap-modal.js"></script>
<!-- custom dropdown library -->
<script src="admin/js/bootstrap-dropdown.js"></script>
<!-- scrolspy library -->
<script src="admin/js/bootstrap-scrollspy.js"></script>
<!-- library for creating tabs -->
<script src="admin/js/bootstrap-tab.js"></script>
<!-- library for advanced tooltip -->
<script src="admin/js/bootstrap-tooltip.js"></script>
<!-- popover effect library -->
<script src="admin/js/bootstrap-popover.js"></script>
<!-- button enhancer library -->
<script src="admin/js/bootstrap-button.js"></script>
<!-- accordion library (optional, not used in demo) -->
<script src="admin/js/bootstrap-collapse.js"></script>
<!-- carousel slideshow library (optional, not used in demo) -->
<script src="admin/js/bootstrap-carousel.js"></script>
<!-- autocomplete library -->
<script src="admin/js/bootstrap-typeahead.js"></script>
<!-- tour library -->
<script src="admin/js/bootstrap-tour.js"></script>
<!-- library for cookie management -->
<script src="admin/js/jquery.cookie.js"></script>
<!-- calander plugin -->
<script src='admin/js/fullcalendar.min.js'></script>
<!-- data table plugin -->
<script src='admin/js/jquery.dataTables.min.js'></script>


<!-- select or dropdown enhancer -->
<script src="admin/js/jquery.chosen.min.js"></script>
<!-- checkbox, radio, and file input styler -->
<script src="admin/js/jquery.uniform.min.js"></script>
<!-- plugin for gallery image view -->
<script src="admin/js/jquery.colorbox.min.js"></script>
<!-- rich text editor library -->
<script src="admin/js/jquery.cleditor.min.js"></script>
<!-- notification plugin -->
<script src="admin/js/jquery.noty.js"></script>
<!-- file manager library -->
<script src="admin/js/jquery.elfinder.min.js"></script>
<!-- star rating plugin -->
<script src="admin/js/jquery.raty.min.js"></script>
<!-- for iOS style toggle switch -->
<script src="admin/js/jquery.iphone.toggle.js"></script>
<!-- autogrowing textarea plugin -->
<script src="admin/js/jquery.autogrow-textarea.js"></script>
<!-- multiple file upload plugin -->
<script src="admin/js/jquery.uploadify-3.1.min.js"></script>
<!-- history.js for cross-browser state change on ajax -->
<script src="admin/js/jquery.history.js"></script>
<!-- application script for Charisma demo -->
<script src="admin/js/charisma.js"></script>
<script src="admin/js/page.js"></script>
<script src="admin/js/common.js"></script>
<script src="admin/js/jquery.treeview.js"></script>

<script type="text/javascript" src="admin/demo/jquery.speedometer.js"></script>
<script>
    $(function(){
        var $menu = $("ul.main-menu");
        $.getJSON("main_menu.do",function(json){
//			json = {"status":"success","menu":[["module\/system\/role_init.do","角色管理","约束",1163,'mainIFrame'],
//				["module\/system\/user_init.do","用户管理","约束",1167,'mainIFrame'],
//				["presentation.do","综合情况展示视图","综合情况展示",1168],
//				["module\/basedata\/organizationType_init.do","组织架构类型","基础数据",1220,'mainIFrame'],
//				["module\/system\/dept_init.do","组织架构","基础数据",1221,'mainIFrame'],
//				["module\/basedata\/nodeType_init.do","节点类型","基础数据",1222,'mainIFrame'],
//				["module\/basedata\/node_init.do","节点","基础数据",1223,'mainIFrame'],
//				["module\/basedata\/eventType_init.do","事件类型","基础数据",1224,'mainIFrame'],
//				["module\/basedata\/event_init.do","事件","基础数据",1225,'mainIFrame'],
//				["module\/basedata\/risk_init.do?kind=10","漏洞风险","基础数据",1227,'mainIFrame'],
//				["module\/basedata\/risk_init.do?kind=20","基线风险","基础数据",1228,'mainIFrame'],
//				["module\/basedata\/risk_init.do?kind=30","攻击预警风险","基础数据",1229,'mainIFrame'],
//				["module\/basedata\/risk_init.do?kind=40","病毒风险","基础数据",1230,'mainIFrame'],
//				["module\/basedata\/organization_init.do","装备管理","基础数据",1231,'mainIFrame']]};
             
            if (json.status == "success") {
                $menu.html("");
                var menuList = json.menu;
                var lastMenuName = "";
                $.each(menuList,function(i,menu){
                    if (lastMenuName != menu.target) {
                        lastMenuName = menu.target;
                        //加入菜单组信息.
                        var $li = $('<li class="nav-header hidden-tablet"></li>');
                        $li.text(lastMenuName);
                        $menu.append($li);
                    }
                    //子菜单
                    var $li = $("<li/>");
                  
					if(menu.link =='/presentation.do'){
					
						$li.append($('<a class="ajax-link"/>').attr("href","" + menu.link.substr(1)).attr("target","")
						//                        .append($('<i class="icon-align-justify"></i>'))
						.append($('<span class="hidden-tablet"/>').text(menu.name)));
						//                        $li.text(i + ":"+JSON.stringify(menu));
					} else {
						$li.append($('<a class="ajax-link"/>').attr("href","" + menu.link.substr(1)).attr("target","mainIFrame")
						//                        .append($('<i class="icon-align-justify"></i>'))
						.append($('<span class="hidden-tablet"/>').text(menu.name)));
						//                        $li.text(i + ":"+JSON.stringify(menu));
					}
                    $menu.append($li);
                })
               // $(document).ready();
                    
                //animating menus on hover
                $('ul.main-menu li:not(.nav-header)').hover(function(){
                    $(this).animate({'margin-left':'+=5'},300);
                },
                function(){
                    $(this).animate({'margin-left':'-=5'},300);
                });
            }
            
            // 加载iframe的高度
            $("#mainIFrame").height($("#mainNav").height());
        })
    })
</script>

</html>

