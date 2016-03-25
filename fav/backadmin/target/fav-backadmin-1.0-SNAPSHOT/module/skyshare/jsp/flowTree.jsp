<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="/jsp/common.jsp" %>
<link href="<%=path%>/admin/css/xr_hss_style.css" rel="stylesheet">
<link href="<%=path%>/admin/css/zTreeStyle.css" rel="stylesheet">
<link href="<%=path%>/admin/css/hsstpu.css" rel="stylesheet">
<script type="text/javascript" src="js/flowMgr.js"></script>
<script type="text/javascript" src="<%=path%>/admin/js/jquery.ztree.core-3.5.min.js" charset="UTF8"></script>
<style>

</style>
<body style="background-color: #ffffff;">
<div id="sViewBox">
	<s:hidden id="orderId" name="orderId"/>
	<s:hidden id="flowId" name="id"/>
	<s:hidden id="orderFlowId" name="orderFlowId"/>
	<div id="treeDiv" class="box">
		<div class="box-header">
				<%--@include file="/jsp/include/navigation.jsp"--%>
		</div>
		<div id="org_tree" class="span2" style="float: left;width: 18%;border-right: 1px solid #dddddd;">
			<ul id="tree" class="ztree" style="width: 300px; overflow: auto;"></ul>
		</div>
		<div id="org_tree_table" style="float: right;width: 80%; bottom:0px;">
			<div class="box-content" style="float:right;width:100%;overflow-y: auto;overflow-style: auto;">
				<div id="tableDiv" class="container">
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					    <tr>
					        <td align="right" style="padding-right: 20px">
					        	<%--<s:if test="%{userSession.userPermissions['/module/skyshare/flow_add.do'] != null}">--%>
						            <input type="button" id="btn_add" onclick="uploadFlowPic();" class="button"
					                   onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="添加"/>
								<%--</s:if>--%>
	    
								<%--<s:if test="%{userSession.userPermissions['/module/skyshare/flow_deletes.do'] != null}">
								            <input type="button" onclick="_delete();" class="button"
								                   onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="删除"/>
								<%--</s:if>--%>      
					        </td>
					    </tr>
					    <tr>
					        <td>
					            <div id="flowDiv"></div>
					        </td>
					    </tr>
					</table>
				</div>	 
			</div>
		</div>
		<div id="dataLoad" style="display:none"><!--页面载入显示-->
				   <table width=100% height=100% border="0" align="center" valign="middle">
				    <tr height=50--%><td align="center">&nbsp;</td></tr>
				    <tr><td align="center"><img src="<%=path %>/admin/img/loading2.gif"/></td></tr>
				    <tr><td align="center">数据载入中，请稍后......</td></tr>
				    <tr height=50%><td align="center">&nbsp;</td></tr>
				   </table>
		</div>
	</div>
	<div id="uploadDialog" title="图片上传"></div>
</div>
</body>
<script>
function generateFlowTree(orderId, flowId){
	$.post(_contextPath+"/module/skyshare/flow_getZTree.do",{"id":flowId},function(json){
		if (json.code == "success") {
			var zNodes = json.znode;
			var t = $("#tree");
			var setting = {
				showLine : true,
				callback : {
					onClick : zTreeOnClick
				}
			};
			t = $.fn.zTree.init(t, setting, zNodes);
			showFlowInfo(orderId, flowId);//初始查询流程下图片信息

			$("#dataLoad").hide();
		}else{
			alert(json.msg);
		}
		//$("#dataLoad").hide();
	},"json");
}


function zTreeOnClick(event, treeId, treeNode, clickFlag) {
	//alert(treeNode.id);
	var orderId = $("#orderId").val();
	showFlowInfo(orderId, treeNode.id);
}

function showFlowInfo(orderId, flowId, orderFlowId){
	//alert(orderId+"|"+flowId+"|"+orderFlowId);
	
    $("#orderId").val(orderId);//赋值供页面其他地方调用
    $("#flowId").val(flowId);//赋值供页面其他地方调用
    var param = "&orderId="+orderId+"&id="+flowId;
 //   if(orderFlowId != undefined){
   // 	param = param + "&orderFlowId="+orderFlowId;
   // }
	
    $("#flowDiv").html("正在加载页面...").load("flow_query.do?showType=tree"+param);
	//$("#flowDiv").html("正在加载页面...").load("flow_query.do?showType=tree&orderId="+orderId+"&id="+flowId+"&orderFlowId="+orderFlowId);
}


$( function() {
	$("#dataLoad").show();
	
	var orderId = $("#orderId").val();
	generateFlowTree(orderId, 1);//树形组织结构
	//showFlowInfo(flowId);//初始查询部门信息
})
</script>
