<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<%@include file="/jsp/common.jsp" %>
<script type="text/javascript">
var _buttonname = "";

function clearCodeBook(){
	var codeBook = document.getElementById("CodeBook");
	codeBook.disabled = "true";
	_buttonname = "codebook";
	$.getJSON("memory_clearMemory.do", {'mode':"ajaxJson",buttonName: _buttonname }, function(json){			
			alert(json.message);			
			if(json.codeid == 0)
			{
				codeBook.disabled = "";
				reload();
			}
		});
}
function clearFunc(){
	var func = document.getElementById("Func");
	func.disabled = "true";
	_buttonname = "func";
	$.getJSON("memory_clearMemory.do", {'mode':"ajaxJson",buttonName: _buttonname}, function(json){
			alert(json.message);
			//if(json.codeid == 0)
			func.disabled = "";
			//reload();
		});
}
function clearMenu(){
	var menu = document.getElementById("Menu");
	menu.disabled = "true";
	_buttonname = "menu";
	$.getJSON("memory_clearMemory.do", {'mode':"ajaxJson",buttonName: _buttonname}, function(json){
			alert(json.message);
			menu.disabled = "";
			//if(json.codeid == 0)
			//reload();
		});
}
function clearProperties(){
	var properties = document.getElementById("Properties");
	properties.disabled = "true";
	_buttonname = "properties";
	$.getJSON("memory_clearMemory.do", {'mode':"ajaxJson",buttonName: _buttonname}, function(json){
			alert(json.message);
			properties.disabled = "";
			//if(json.codeid == 0)
			//reload();
		});
}
function clearSystemParams(){
	var systemParams = document.getElementById("SystemParams");
	systemParams.disabled = "true";
	$.getJSON("memory_clearSysParams.do", {'mode':"ajaxJson"}, function(json){
			alert(json.message);
			systemParams.disabled = "";
			//if(json.codeid == 0)
			//reload();
		});
}
</script>
</head>
<body style="overflow:hidden"><s:fielderror/>
<table class="bg100" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_add" width="100%" border="0" cellpadding="1" cellspacing="1">
        <tr>
          <td class="td_title" colspan="3" align="center">内存管理</td>
        </tr>
          <tr>
          <td class="bottom" align="center" colspan="3"><div align="center">
				<input id="CodeBook" type="button" onclick="clearCodeBook()" class="button2" onmouseout="this.className = 'button2'" onmouseover="this.className = 'button2Over'" value="清空业务字典"/>
		  </div></td>
		  </tr>
		  <tr>
		  <td class="bottom" align="center" colspan="3"><div align="center">
				<input id="Func" type="button" onclick="clearFunc()" class="button2" onmouseout="this.className = 'button2'" onmouseover="this.className = 'button2Over'" value="清空功能数据"/>
		  </div></td>		  
          </tr>
          <tr>
		  <td class="bottom" align="center" colspan="3"><div align="center">
				<input id="Menu" type="button" onclick="clearMenu()" class="button2" onmouseout="this.className = 'button2'" onmouseover="this.className = 'button2Over'" value="清空菜单数据"/>
		  </div></td>		  
          </tr>
           <tr>
		  <td class="bottom" align="center" colspan="3"><div align="center">
				<input id="Properties" type="button" onclick="clearProperties()" class="button2" onmouseout="this.className = 'button2'" onmouseover="this.className = 'button2Over'" value="清空属性数据"/>
		  </div></td>		  
          </tr> 
          <!--  
          <tr>
		  <td class="bottom" align="center" colspan="3"><div align="center">
				<input type="button" onclick="clearSystemParams()" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="清空系统参数"/>
		  </div></td>		  
          </tr>
          -->
         
      </table></td>
    </tr>
  </table>
</body>
</html>