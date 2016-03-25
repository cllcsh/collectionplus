<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN">
<head>
<title>区域围栏设置</title>
<style type="text/css" media="screen"></style>
</head>

<script language="javaScript">
//围栏保存
function save_crawl(){ 
	$('#saveBtn').attr('disabled','disabled');//不能重复单击同一按钮
	   var name =  document.getElementById("railingsName2").value;
	   if (name=="" || name== null)
	   {
	   		alert("围栏名称不能为空");
	   		$("#saveBtn").attr("disabled","");
			return;
	   }
	   var alarm =  document.getElementById("alarm").value;
	   var longtit = document.getElementById("longtit").value;
	   var lattit = document.getElementById("lattit").value;
	   var overlayType = document.getElementById("overlayType").value;
	   var params = {'mode':"ajaxJson", 
			   		 'form.longitudes':longtit, 
			         'form.latitudes':lattit, 
			         'form.overlayType':overlayType, 
				     'form.railingsType':alarm,
				     'form.railingsName':name};
	   jQuery.post(_contextPath+"/module/map/railings_saveCrawl.do", params, function(json){//post方法处理中文,而用.getJSON()方法中文为乱码
		   $("#saveBtn").attr("disabled","");
			alert(json.message);
			if(json.codeid == 0){
				closeAreaWin();
			}
	   },"json");
}

function closeAreaWin(){
	//关闭窗口
	jQuery("#saveArea").dialog('close');//关闭公用围栏窗口
}

</script>
<body>
	<form name="rectForm">
      <s:hidden id="longtit" name="form.longitudes" />
	  <s:hidden id="lattit" name="form.latitudes" />
	  <s:hidden id="overlayType" name="form.overlayType" />
	<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
				<table class="tb_add" width="100%"  border="0" cellpadding="1" cellspacing="1">
        			<tr>
          				<td class="td_title" colspan="2" align="center">区域报警设置</td>
        			</tr>
        			<tr>
          				<th class="tb_lable" width="30%" align="right">围栏名称：</th>
          				<td width="70%">
          					<input type="text" id="railingsName2" name="railingsName2" maxLength="30" />
          			    </td>
        			</tr>
        			<tr style="display:none">
          				<th align="right">报警设置：</th>
          				<td>
             			<select id="alarm">
							<option value="1">出区域报警</option>
							<option value="2">进区域报警</option>
				    	</select>		
          				</td>
        			</tr> 
        <tr>
          <td class="bottom" align="center"  colspan="2" >
          	<div align="center">
          		  <input type="button" id="saveBtn" value="保 存 " onclick="save_crawl();" />
		          <input type="reset" value=" 取 消 " onclick="closeAreaWin();"/>
	     	</div>
          </td>
        </tr> 
      </table></td>
    </tr>
  </table>		

 </form>

</body>
</html>