<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN">
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="Content-Language" content="zh-CN" />
		<link href="../css/global.css" rel="stylesheet" type="text/css" />
		<title>中心点设置</title>
		<style type="text/css" media="screen">
		</style>
</head>
<script language="javaScript">
function save_centerPoint(){
	   document.getElementById("cpSaveBtn").disabled = "disabled";
	   
	   var ratio = document.getElementById("ratio").value;
	   var log = document.getElementById("log").value;
	   var lat = document.getElementById("lat").value;
	   document.getElementById("zoomLevel").value = ratio;

	   if(log=="" || lat==""){
	   		alert("经纬度不能为空!")
	   		return false;
	   }

	   var params = {'mode':"ajax", 'form.longitude':log, 'form.latitude':lat, 'form.zoomLevel':ratio};
	   
	   $.getJSON("centerPoint_save.do", params, function(json){
			alert(json.message);
			if(json.codeid == '0') {
				map.getMapControl().SetCenterAndZoom(log,lat,ratio);
				closeWin();
				reloadCenterPoint();
			} else {
				document.getElementById("cpSaveBtn").disabled = false;//按钮激活
			}
	});
}

//关闭窗口
function closeWin(){
	 jQuery("#saveCenterPoint").dialog('close');
	   
}

function reloadCenterPoint(){
	$('#mapfind').load( _contextPath+"/module/map/centerPoint_init.do");
}

function isValidLoad(){//判断选中的是否为点型图标
	   var type = document.getElementById("hidType").value;
	   if(type!="2"){
		   //alert("请选择点型图标");
		   closeWin();
	   }
}

   
</script>
	<body onload="isValidLoad();" bgcolor="#99CCFF">
		<s:form name="cpForm">
			<s:hidden id="hidType" name="form.type"/>
			<s:hidden id="zoomLevel" name="form.zoomLevel"/>
			<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
				<table class="tb_add" width="100%"  border="0" cellpadding="1" cellspacing="1">
        			<tr>
          				<td class="td_title" colspan="2" align="center">中心点设置</td>
        			</tr>
        			<tr>
          				<th class="tb_lable" width="30%" align="right">经度：</th>
          				<td width="70%">
          				<s:textfield id="log" name="form.longitude" readonly="true" />
          			    </td>
        			</tr>
        			<tr>
          				<th align="right">纬度：</th>
          			<td>
             			<s:textfield id="lat" name="form.latitude" readonly="true" />
          			</td>
        			</tr>
        			<tr>
          				<th class="tb_lable" width="50%" align="right">比例尺：</th>
          				<td width="50%">
          				<select id="ratio" name="ratio">
							<option value="0.00000025">
								1:400万
							</option>
							<option value="0.0000005">
								1:200万
							</option>
							<option value="0.000001">
								1:100万
							</option>
							<option value="0.000002">
								1:50万
							</option>
							<option value="0.0000037">
								1:27万(城市)
							</option>
							<option value="0.0000055">
								1:18万
							</option>
							<option value="0.0000125">
								1:8万
							</option>
							<option value="0.000025" selected>
								1:4万
							</option>
						</select>
          				</td>
        			</tr>
        <tr>
          <td class="bottom" align="center"  colspan="2" >
          	<div align="center">
          		<input type="button" id="cpSaveBtn" onclick="save_centerPoint();" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="保存"/>
	     	</div>
          </td>
        </tr> 
      </table></td>
    </tr>
  </table>		
  </s:form>
  </body>
</html>