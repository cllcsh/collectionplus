<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
  <head>
    <%@include file="/jsp/common.jsp" %>
	<%@include file="/jsp/xtree.jsp" %>
<script language="javascript">

// 显示目录图标
function display(nodeId) {
    var node = document.getElementById("tr_" + nodeId);
    var img = document.getElementById("img_" + nodeId);
    <%String contextPath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();%>
    if (img.src.indexOf("images/openfoldericon.png") != -1) {
        img.src = "<%=contextPath%>/module/map/images/foldericon.png";
        if (node != null) {
            node.style.display="none";
        }
    } else {
        img.src = "<%=contextPath%>/module/map/images/openfoldericon.png";
        if (node != null) {
            node.style.display="";
        }
    }
}

// 选择一个终端,如果选择的是组则同时选择组下的所有项,如果选择的是终端则选择该终端
function selectNode(node) {
	if(getLastNode()!= null){//将前一个点Color去掉
    	var trNode = lastNode.parentNode.parentNode;
        if (trNode != null) {
            setSelectColumnColor(lastNode.value, trNode, false);
        }
    }

    
    if (node.name.indexOf("cb_tu")!=-1) {//选择组或终端
        if(getLastNode()!= null){
        	var lNode = getLastNode();
        	
			if(lNode.id == node.id){//点同一个点
				setLastNode(null);
            	node.checked = false;
            }else{
            
        		lNode.checked = false;
        		node.checked = true;
        		setLastNode(node);
            }
        }else{
        	node.checked = true;
        	setLastNode(node);
        }

        
        //设定选中的Color点
        var trNode = node.parentNode.parentNode;
        if (trNode != null) {
            setSelectColumnColor(node.value, trNode, node.checked);
        }
    }
}

function setSelectColumnColor(selectId, trNode, isSelect) {
	if (isSelect) {
        trNode.bgColor="#C8DBF5";
    } else {
        trNode.bgColor="#f7fafe";
    }
}

function showTarget(target) {
    if (target.id != lockTuId) {
        var element = document.getElementById("locationTU" + target.id);
        if (element != null && !element.disabled) {
            //frameShowTarget.location.href="/StarMap/palcemap/placeservice/selecttarget.jsp?tuId=" + target.id;
            parent.parent.parent.left.webgps.gps.selectGPS(target.id);
        }
        var node = parent.parent.parent.left.framePositionInfo.document.getElementById(target.id);
        if (node != null) {
            parent.parent.parent.left.framePositionInfo.selectNode(node);
        }
    }
}

function getCheckedValue() {
	var checkboxes = getLastNode();
	var i = 0;
	var arrValue = "";
	if(checkboxes != null) {
			if(checkboxes.checked) {
				arrValue = arrValue + checkboxes.value + ",";
			}
	}

	return arrValue.slice(0, arrValue.length - 1);
}

function getCheckedNum(ids) {
	if(ids == "") {
		return "";
	}
	var arrValue = "";
	var arr = ids.split(",");
	for(var i = 0; i < arr.length; i++) {
		arrValue = arrValue + document.getElementById("hd_tu" + arr[i]).value + ",";
	}
	return arrValue.slice(0, arrValue.length - 1);
}

function setHtmlValue(id, htmlValue, color) {
	var tdTag = document.getElementById("td_tu" + id);
	tdTag.style.color = color;
	tdTag.innerHTML = htmlValue;
}

var lastNode = null;
function getLastNode() {
	return lastNode;
}

function setLastNode(node) {
	lastNode = node;
}

//上一次定位成功后的点
function getLastPoint(locNum) {
//目前历史轨迹不做处理 	
}

</script>
  </head>
  
  <body id="idBody">
  	<%=request.getAttribute("strHtml") %>
	<!--<input type="button" value="删除Cookie, 清除节点的展开状态" onclick="webFXTreeHandler.cookies.deleteCookie(); location.reload()">&nbsp; -->
	<!--<input type="button" value="Cookie值" onclick="alert(document.cookie)">&nbsp;&nbsp;-->
	<script>
		//document.write(tree);
		// tree.expand();
	</script>
  </body>
</html>
<script language="JavaScript">
</script>