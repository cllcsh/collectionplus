<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="meta.jsp" %>
<script type="text/javascript">
var _contextPath = '<%=path %>';
var _remoteServer = '<%=remoteServer %>';

</script>
<link rel="stylesheet" type="text/css" href="<%=path%>/resource/ui/themes/<%=themeName%>/ui.all.css" /><!-- Jquery UI样式文件 -->
<link rel="stylesheet" type="text/css" href="<%=path%>/resource/jPagination/theme/<%=themeName%>/css/pagination.css"/><!-- 分页CSS样式文件 -->

<link rel="stylesheet" type="text/css" href="<%=path%>/resource/formValidator/style/validator.css"/><!-- 验证框架CSS样式文件 -->

<link rel="stylesheet" type="text/css" href="<%=path%>/resource/themes/<%=themeName%>/archive.css"/><!--<s:text name="ictmap.tittle"/>档案样式文件 -->

<link rel="stylesheet" type="text/css" href="<%=path%>/resource/themes/<%=themeName%>/common.css"/><!-- 自定义样式文件 -->

<link rel="stylesheet" type="text/css" href="<%=path%>/resource/jquery-tooltip/jquery.tooltip.css" /><!-- tooltip css -->
<link rel="stylesheet" type="text/css" href="<%=path%>/resource/jquery-tooltip/qtip/css/qtip.css" /><!--  漂亮的提示插件-->

<link rel="stylesheet" type="text/css" href="<%=path%>/resource/fancybox/jquery.fancybox-1.3.4.css" /><!--  图片显示效果  -->

<link rel="stylesheet" type="text/css" href="<%=path%>/resource/combobox/jquery-ui.css" />

<script type="text/javascript" src="<%=path%>/resource/js/jquery-1.7.2.min.js"></script><!-- Jquery 脚本文件 -->
<script type="text/javascript" src="<%=path%>/resource/js/jquery.form.js"></script>
<script type="text/javascript" src="<%=path%>/resource/js/jquery.bgiframe.js"></script><!-- Jquery 插件-->
<script type="text/javascript" src="<%=path%>/resource/js/jquery.easydrag.handler.beta2.js"></script>
<script type="text/javascript" src="<%=path%>/resource/js/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=path%>/resource/My97DatePicker/WdatePicker.js"></script><!-- 日期控件脚本 -->

<script type="text/javascript" src="<%=path%>/resource/jPagination/pagination.js"></script><!-- 分页脚本 -->

<script type="text/javascript" src="<%=path%>/resource/formValidator/formValidator.js"></script><!-- 验证框架脚本 -->
<script type="text/javascript" src="<%=path%>/resource/formValidator/formValidatorRegex.js"></script><!-- 验证框架脚本 -->
<script type="text/javascript" src="<%=path%>/resource/js/jquery.checkbox.js"></script><!-- 消息框插件-->
<script type="text/javascript" src="<%=path%>/resource/js/jquery.ict.js"></script><!-- 消息框插件-->
<script type="text/javascript" src="<%=path%>/resource/jquery-tooltip/qtip/js/jquery.qtip-1.0.js"></script><!-- 漂亮的提示插件-->

<script type="text/javascript" src="<%=path%>/resource/js/locationErrorCode.js"></script><!-- 定位错误码-->
<script type="text/javascript" src="<%=path%>/resource/js/comm.js"></script><!-- 公共脚本 -->
<script src="<%=request.getContextPath()%>/resource/combobox/jquery-ui.min.js"></script>
<script src="<%=request.getContextPath()%>/resource/combobox/combobox.js"></script>
<script type="text/javascript">
jQuery(function() {
	jQuery('span,input,a').tooltip();
});
</script>
