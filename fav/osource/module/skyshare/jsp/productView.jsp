<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dict" uri="/dict-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/jsp/common.jsp"%>
<script type="text/javascript" src="<%=path%>/resource/fancybox/jquery.fancybox-1.3.4.js"></script>
<script type="text/javascript" src="js/productMgr.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("a[rel=single]").fancybox({
		  openEffect  : 'none',
		  closeEffect	: 'none',
          helpers: {
              title : {
                  type : 'float'
              }
          }
      });
});
</script>
</head>

<body>
<table class="bg" border="0" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<table class="tb_add" width="100%"  border="0" cellpadding="1" cellspacing="1">
		<tr>
	        <td class="td_title" colspan="4" align="center">
	        	<b>详细信息</b>
	        </td>         
        </tr>
		<tr>
		<th  align="right" width="50%">
			类型：
		</th>
		<td>
			<s:select list="productTypeList" listKey="id" listValue="typeName" disabled="true"
				id="productTypeId" value="productForm.typeId"
				name="productForm.typeId">
			</s:select>
		</td>
		</tr>

		<tr>
					<th align="right">
						Product Name：
					</th>
					<td colspan="2">
						<s:property value="productForm.productName" />
					</td>
					
		</tr>
		<tr>
			<th align="right">
				上架时间：
			</th>
			<td>
				<s:date format="yyyy-MM-dd HH:mm:ss" name="productForm.upDat"/>
			</td>
		</tr>
		<tr>
			<th align="right">
				price：
			</th>
			<td colspan="2">
				<s:property value="productForm.salePrice"/>
			</td>
		</tr>
		<tr>
			<th align="right">
				weight：
			</th>
			<td colspan="2">
				<s:property value="productForm.weight"/>
			</td>
		</tr>
		<tr>
            <th align="right">
				是否新品：
			</th>
			<td>
				<dict:property codeType="product_is_new" value="productForm.isNew" />
			</td>
        </tr>
		<tr>
			<th align="right">
				Product Specifications：
			</th>
			<td colspan="2">
				<s:property value="productForm.productDesc"/>
			</td>
		</tr>
		<tr>
			<th align="right">
				CAS NO.：
			</th>
			<td colspan="2">
				<s:property value="productForm.casNo"/>
			</td>
		</tr>
		<tr>
			<th align="right">
				Product Features：
			</th>
			<td colspan="2">
				<s:property value="productForm.productFeature"/>
						
			</td>
		</tr>
		<tr>
			<th align="right">
				For crops：
			</th>
			<td colspan="2">
				<s:property value="productForm.productUse" />
						
			</td>
		</tr>
		<tr>
			<th align="right">
				Control objects：
			</th>
			<td>
				<s:property value="productForm.useNote"/>
			</td>
		</tr>
		<tr>
			<th align="right">
				存储运输 ：
			</th>
			<td colspan="2">
				<s:property value="productForm.transportStorage"/>
			</td>
		</tr>

		 <tr>
            <th align="right">图片列表：</th>
            <td colspan="2">
            	<table width="100%">
			      <tr class="tr_head" align="center">
					<!--  <td><input id="checkboxtop"	type="checkbox" class="ckboxAll" title="选中/取消选中" value="" onclick="checkAll('checkboxtop','ckboxItem');" /></td>-->
					
			       
					<!--  <td width="">操作</td>-->
			      </tr>
			      <s:iterator id="album" value="%{attachmentList}" status="sta">
			      <tr align="center" class='<s:if test="#sta.odd">tr_odd</s:if><s:else>tr_even</s:else>'>
					<!--  <td>
						<input name="items[<s:property value="#sta.index"/>].id" type="checkbox" class="ckboxItem" value="<s:property value="#album.id"/>" onclick="check('checkboxtop','checkbox1');" />
					</td>-->
			        <td>
						<a rel="single" href="/<s:property value='#album.filePath'/>"><s:property value="#album.fileName"/></a>
						<input type="hidden" value="<s:property value="#album.id"/>" name="albumList[<s:property value="#sta.index"/>].albumId"/>
					</td>
					
			        <!-- <td align="center" >        	
							<a href="javascript:edit_album(<s:property value='#album.id'/>)" >修改</a> &nbsp;&nbsp;
					</td> -->
			      </tr>
			      </s:iterator>
	      	</table>
            </td>
        </tr>
		<%-- 
		<tr>
       				<th align="right">产品图片1</th>
	          <td><input type="file" id="upload1" name="productForm.pic" style="width:300px" /></td>
		    	<td align="left"><input type="button" id="btn1" class="button2" onmouseout="this.className = 'button2'" onmouseover="this.className = 'button2Over'"  value="重置"  onclick="clear_upfile('upload1');"/></td>
			  </td>
     			</tr>
		<tr>
       				<th align="right">产品图片2</th>
	          <td><input type="file" id="upload2" name="productForm.pic" value="" style="width:300px" ContentEditable="false" /></td>
		    	<td align="left"><input type="button" id="btn1" class="button2" onmouseout="this.className = 'button2'" onmouseover="this.className = 'button2Over'"  value="重置"  onclick="clear_upfile('upload2');"/></td>
			  </td>
	          <td></td>
     			</tr>
		<tr>
       				<th align="right">产品图片3</th>
	          <td><input type="file" id="upload3" name="productForm.pic" value="" style="width:300px" ContentEditable="false" /></td>
		    	<td align="left"><input type="button" id="btn1" class="button2" onmouseout="this.className = 'button2'" onmouseover="this.className = 'button2Over'"  value="重置"  onclick="clear_upfile('upload3');"/></td>
			  </td>
	          <td></td>
     			</tr>					
		<tr>
       				<th align="right">产品图片4</th>
	          <td><input type="file" id="upload4" name="productForm.pic" value="" style="width:300px" ContentEditable="false" /></td>
		    	<td align="left"><input type="button" id="btn1" class="button2" onmouseout="this.className = 'button2'" onmouseover="this.className = 'button2Over'"  value="重置"  onclick="clear_upfile('upload4');"/></td>
			  </td>
	          <td></td>
     			</tr>
		<tr>
       				<th align="right">产品图片5</th>
	          <td><input type="file" id="upload5" name="productForm.pic" value="" style="width:300px" ContentEditable="false" /></td>
		    	<td align="left"><input type="button" id="btn1" class="button2" onmouseout="this.className = 'button2'" onmouseover="this.className = 'button2Over'"  value="重置"  onclick="clear_upfile('upload5');"/></td>
			  </td>
	          <td></td>
     	</tr>
     	--%>
     		
        <tr>
          <td class="bottom" align="center" colspan="2">
			<input type="button" onclick="javascript:history.back();" class="button" onmouseout="this.className = 'button'" onmouseover="this.className = 'buttonOver'" value="返回"/>
          </td>
        </tr>
      </table></td>
    </tr>
  </table>

</body>
</html>