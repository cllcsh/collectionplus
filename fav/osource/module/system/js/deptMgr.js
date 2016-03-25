/**
 * 用户管理页面脚本
 * @author fengjingzhun
 * @create 2009-11-19
 * @file deptMgr.js
 * @since v0.1
 */

//全局变量
var _perPage = 10;//每页显示条数
var _currPage = 1;//要查看页码数
var _param;

//刷新分页数据，分页加载完毕后由回调函数重新设置
reload = function()
{
	window.location = "dept_init.do";
};

/**
 * 载入页面
 */
function loadPage()
{
    _query();
}

//查询
function _query()
{
//    if (!checkQuery())
//    {
//        return;
//    }
   // _param = buildParam("#queryForm");
    paging();
}

function checkQuery()
{
    var code = $("#queryForm").find("input[name='deptForm.deptEntity.code.stringValue']").val();
    var codePattern = /^([0-9]){0,20}$/;

    if (!codePattern.test(code))
    {
        alert('您要查询的编码不正确，请重新输入');
        $('#loginName').val('');
        return false;
    }

    return true;

}

function paging()
{
	var jgbm = $('#jgbm').val();
	var name = $('#name').val();
	var address = $('#address').val();
	var manager = $('#manager').val();
	var rank = $('#rank').val();
	var upperDept = $('#upperDept').val();
	_param = {'deptInfoForm.jgbm':jgbm,
			'deptInfoForm.name':name,
			'deptInfoForm.manager':manager,
			'deptInfoForm.rank':rank,
			'deptInfoForm.address':address,
			'deptInfoForm.upperDept':upperDept};
	//_param = jQuery("form#queryForm").serialize();
	
	/*var ev = window.event;
    var target = ev.target || ev.srcElement;
    disableButton(jQuery(target));*/
    
    var pageconfig = {
        ajaxDataType:'html',
        proxyUrl:'dept_query.do?mode=ajax',
        currentPage:_currPage,
        perPage:_perPage,
        ajaxParam:_param,
        barPosition:'bottom',
        showMode:'full',
        callback:function(perpage, reloadFn)
        {
            _perPage = perpage;
            reload = reloadFn;
            //enableButton(jQuery(target));
        }
    };
    $("#pagecontent").pagination(pageconfig);
}

//删除
function _delete()
{
    /*if (!checkDelete())
    {
        return;
    }*/
	var deptId = $("#deptId").val();
	
    if (window.confirm("确定要删除选中的记录吗?"))
    {
        //        alert($('#queryList').serialize());
        $.ajax({
            type: "POST",
            url: "dept_delete.do?mode=ajax",
            //data: $('#queryList').serialize(),
            data:{"id":deptId},
            dataType:"json",
            success: function(msg)
            {
                alert(msg.message);
                if (msg.codeid == 0)
                {
                    reload();
                }
            }
        });
    }
}

function checkDelete()
{
    var id = $("#queryList").find("input[name='deptForm.deptEntity.id.stringValues']:checked");

    if (id.length == 0)
    {
        alert("请选择需要删除的记录");
        return false;
    }
    return true;

}

//查看
function _view(deptId)
{
    if (deptId == null)
    {
        alert("错误的用户编号");
        return;
    }
    //$("#dialog").dialog('open').html("正在加载页面...").load("dept_view.do?mode=ajax&deptForm.deptId=" + deptId);
    window.location = "dept_view.do?deptInfoForm.deptId=" + deptId;
}

//编辑(当deptId为null时：新增；当deptId非null时：修改)
function _edit(deptId)
{
    if (deptId == null)
    {
    	deptId = $("#deptId").val();//赋值供添加时默认选择上级部门使用
    	if(deptId == null)
    		deptId = "";
    }
    //$("#dialog").dialog('open').html("正在加载页面...").load("dept_edit.do?mode=ajax&deptForm.deptId=" + deptId);
    $("#deptDiv").html("正在加载页面...").load("dept_edit.do?mode=ajax&deptForm.deptId=" + deptId);
    //window.location = "dept_edit.do?deptInfoForm.deptId=" + deptId;
}

//保存
function _save()
{//createjgbm();

    if (!checkSave())
    {
        return;
    }
    var ev = window.event;
    var target = ev.target || ev.srcElement;
    disableButton(jQuery(target));
    
    $.ajax({
        type: "POST",
        url: "dept_save.do?mode=ajax",
        data: $('#saveForm').serialize(),
        dataType:"json",
        success: function(msg)
        {
            alert(msg.message);
            if (msg.codeid == 0)
            {
                closeDialog();
                reload();
            	//location.href("dept_init.do");
            	//window.location = "dept_init.do";
            }
        }
    });
}

function createjgbmpro(){
	$('#jgbmpro').val($('#fromArea').val()+$('#jglscj').val()+$('#jglb').val());
	$('#dwbm').val($('#fromArea').val().substr(4,2))
}

function createjgbm(){
	createjgbmpro();
	$('#jgbm_hid').val($('#jgbmpro').val()+$('#dwbm').val()+$('#bmsxm').val());
}

function checkSave()
{

	if(!$.formValidator.pageIsValid("1")){
		return false;
	}
	else{
        return true;
	}

}


//----------------------------------------------------------------------
//关闭弹出对话框
function closeDialog()
{
    $("#dialog").dialog('close');
}

//定义弹出对话框
$(function()
{
    $("#dialog").dialog({
        bgiframe: true,
        autoOpen: false,
        width:720,
        height:600,
        modal: true,
        close: function()
        {
            $(this).html('');
        }
    });
});

function buildParam(tag)
{
    var param = {'mode':'ajax'};
    var fields = $(tag).serializeArray();
    var JSONstr = "";
    jQuery.each(fields, function(i, field)
    {
        if ("" != field.value)
        {
            JSONstr += ",'" + field.name + "':'" + field.value + "'";
        }
    });
    JSONstr = "param = {'mode':'ajax'" + JSONstr + "};";
    //alert("json = " + JSONstr);
    eval(JSONstr);
    return param;
}