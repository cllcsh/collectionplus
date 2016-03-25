 //打开编辑事件页面
function edit_album(id){
	window.location="order_edit.do?id=" + id;
}

//添加图片
function up_file(){
	//document.openDialog("uploadDialog").html("正在加载页面...").load(_contextPath+"/upload_init.do");
	$("#uploadDialog").dialog('open').html("正在加载页面...").load(_contextPath + "/upload_uploadInit.do?mode=ajax&uploadContentType=pic&path=file");
}

//清空标题图片输入框内容
function clear_upfile(){
	$('#picPath').val('');
}

function callbackpic(fileName){
	$('#picPath').val(fileName);
	$("#uploadDialog").dialog('close');
}

//查看订单流程
function view_orderFlow(orderId){
	window.location = _contextPath+"/module/skyshare/flow_init.do?showType=tree&orderId="+orderId;
}

//定义弹出对话框
$(function() {
	$("#dialog").dialog({
		bgiframe: true,
		autoOpen: false,
		width:800,
		height:600,
		modal: true,
		close: function() {
			$(this).html('');
		}
	});
});

function add_product()
{
	 $("#dialog").dialog('open').html("正在加载页面...").load(
				"orderProduct_init.do");
}

var _productList = new Array(); 

/* 查询接受者列表中的多选框的选择 */
function checkAdd(checkbox,id)
{
	 if(checkbox.checked == true)
	 {
		 addOne(id);
	 }
	 else
	 {
		 removeOne(id);
	 }
}
function removeOne(id)
{
		 for(var i=0;i<_productList.length;i++)
		 {
			 if(_productList[i] == id)
				 break;
		 }
		 _productList.splice(i,1);
}

function addOne(id)
{
	 var sign = true;

		 for(var i=0;i<_productList.length;i++)
		 {
			 if(_productList[i] == id)
			 {
				 sign = false;
				 break;
			 }
		 }
		 if(sign)
			 _productList.push(id);
}

function addAllProduct()
{
	 var objs = document.getElementsByName("checkbox2");
	 if(document.getElementById("checkboxtopperson").checked == true)
	 {
		 for(var i = 0;i<objs.length;i++)
		 {
			 objs[i].click();
			 objs[i].click();
		 }
	 }
	 else
	 {
		 for(var i = 0;i<objs.length;i++)
		 {
			 objs[i].click();
			 objs[i].click();
		 }
	 }
}

//关闭弹出对话框
function closeDialog() {
	
	 if(_productList.length == 0&&_productList.length == 0)
	 {
		 alert("请选择订单产品！");
	 }else
	 {
		 searchForList();
		 $("#dialog").dialog('close');
	 }
}

function searchForList()
{
	 var param = {'mode':"ajax",
			 'product':_productList.join(",")
	 };
	 $("#pagecontentinfo").load('orderProduct_querySelected.do?mode=ajax',param,function(){
	 });
}

function order_save(className)
{
	//var id = getCheckboxValuesByClass(className);
	 
	// if(id.length < 1){
	//	 alert("请选择产品！");
	//	 return;
	 //}
	 
	 if (!$.formValidator.pageIsValid("1")) {
		 return;
	 }
	 
	 
	$('#btn_save').attr('disabled', 'disabled');// 不能重复单击同一按钮
	var formData = $("#setForm").serialize();
	
	var selectedData = $("#productselectForm").serialize();
	// alert("selectedData:"+selectedData);
	$.ajax( {
		type : "POST",
		url : "order_save.do?mode=ajaxJson",
		data : formData,
		success : function(json) {
			alert(json.message);
			if (json.codeid == 0) {
				location.href = "order_init.do";
			}
			$('#btn_save').attr('disabled', '');
		},
		dataType : "json"
	});
	 
}

function order_update(className)
{
	 if (!$.formValidator.pageIsValid("1")) {
		 return;
	 }
	 
	 
	$('#btn_save').attr('disabled', 'disabled');// 不能重复单击同一按钮
	var formData = $("#setForm").serialize();
	
	//var selectedData = $("#productselectForm").serialize();
	// alert("selectedData:"+selectedData);
	
	$.ajax( {
		type : "POST",
		url : "order_update.do?mode=ajaxJson",
		data : formData,
		success : function(json) {
			alert(json.message);
			if (json.codeid == 0) {
				location.href = "order_init.do";
			}
			$('#btn_save').attr('disabled', '');
		},
		dataType : "json"
	});
	 
}
//打开编辑产品页面
function edit_order(id){
	//location.href("product_edit.do?id=" + id);
	window.location = "order_edit.do?id=" + id;
}
function view_order(id){
	//location.href("product_view.do?id="+id);
	window.location = "order_view.do?id="+id;
}

