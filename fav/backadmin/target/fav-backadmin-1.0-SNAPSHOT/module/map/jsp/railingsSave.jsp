<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN">
<head>
<script type="text/javascript" src="js/railingsMgr.js"></script>
<script type="text/javascript">
</script>
<title>区域报警设置</title>
<style type="text/css" media="screen"></style>
</head>

<script language="javaScript">
   function closeAreaWin(){
	   //关闭窗口
	   jQuery("#saveArea").dialog('close');//关闭公用围栏窗口
   }
</script>
<body>
	<form name="rectForm">
      <s:hidden id="longtit" name="form.logs" />
	  <s:hidden id="lattit" name="form.lats" />
	  <s:hidden id="type" name="form.type" />
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