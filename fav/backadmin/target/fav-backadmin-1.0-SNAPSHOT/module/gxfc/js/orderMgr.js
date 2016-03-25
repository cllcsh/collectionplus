/**
 * 订单管理页面脚本
 * @author pachong
 * @create 2015-07-26
 * @file orderMgr.js
 * @since v0.1
 */

 //打开编辑订单信息页面
function edit_order(id){
	location.href = "order_edit.do?id=" + id;
}

//打开查看订单信息页面
function view_order(id){
	location.href = "order_view.do?id=" + id;
}

//打开查看车源信息页面
function view_car(id){
	location.href = "car_view.do?id="+id;
}

//无效化订单
function disable_order(id){
	$.ajax({
        type: "POST",
        url:"order_deletes.do",
        data:{"ids":id},
        async: false,
        error: function(request) {
            alert("网络错误");
        },
        success: function(data) {
        	data = $.parseJSON(data);
        	alert(data.message);
        	if(data.codeid == '0') {
	            window.location.reload();
        	}
        }
    });
}