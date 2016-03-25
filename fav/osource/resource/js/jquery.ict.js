/**
 * 页面查询
 * 功能：初始化操作
 * @author luoj
 * @version 1.0
 * @date 2009-12-29
 * @param config 插件配置
 */

(function($) {

  // plugin definition
jQuery.fn.ict = function(opts) {
	  var _opts = {
		formid:'queryForm',//不建议使用，已废弃
		divid:'',//弹出对话框对应的层id，当保存成功后无须页面跳转时使用
		
		currPage:1,//设置当前页
		perPage:10,//设置每页显示条数
		showMode:'full',
		
		pagecontent:'pagecontent',//装载查询结果列表的div的Id
		param:{},	//保存查询条件参数
		requery : function(){_query();},	//保存查询条件参数
		reload : function(){},//重新装载（刷新页面）
		
		isLoadQuery:true,//是否刚进入查询条件页面就执行查询
		isPaging:true,//是否分页
		saveuseFormValidator:true,//设置保存或修改时是否校验
		validatorgroup:"1",
		
		queryForm:'queryForm',//查询功能对应表单Id
		queryUrl:'',//查询功能对应Url
		queryBtn:'btn_query',//查询按钮Id
		
		addUrl:'',//添加功能对应Url
		addBtn:'btn_add',//添加按钮Id
		saveForm:'addForm',//保存或修改功能对应表单Id
		saveUrl:'',//保存或修改功能对应Url
		saveUrlTo:'', //保存或修改成功后跳转到的页面Url
		saveSuccess:function(){},
		saveBtn:'btn_save',//保存或修改按钮Id
		
		checkboxName:'',//复选框名字
		checkboxClass:'ckboxItem',//复选框class,在checkboxName为空时使用
		delsUrl:'',//“删除多个”功能对应Url
		delsBtn:'btn_dels'//“删除多个”功能按钮Id
    };	
    var _btn_save, _btn_query, _btn_dels;
	return init(opts);

	function init(opts){
		jQuery.extend(_opts, opts);
		
		if(opts.queryForm == null || opts.queryForm =='')
			_opts.queryForm = _opts.formid;
		
		/* 查询功能 */
		if(_opts.queryUrl != '' && jQuery('#'+_opts.queryBtn) && jQuery('#'+_opts.queryForm) && jQuery('#'+_opts.pagecontent)){
			_btn_query = jQuery('#'+_opts.queryBtn);
			if((_opts.queryUrl.indexOf('?mode=') == -1) && (_opts.queryUrl.indexOf('&mode=') == -1))
				_opts.queryUrl = (_opts.queryUrl.indexOf('?') == -1) ? _opts.queryUrl + '?mode=ajax' : _opts.queryUrl + '&mode=ajax';
			_loadparam();
			jQuery('#'+_opts.queryBtn).click(
					function(){
						_query();
					}
			);
			if(_opts.isLoadQuery)
				_query();
		}
		/* 添加功能 */
		if(_opts.addUrl != '' && jQuery('#'+_opts.addBtn)){
			_btn_add = jQuery('#'+_opts.addBtn);
			jQuery('#'+_opts.addBtn).click(
				function(){
					window.location=_opts.addUrl;
				}
			);
		}
		/* 保存功能 */
		if(_opts.saveUrl != '' && jQuery('#'+_opts.saveForm) && jQuery('#'+_opts.saveBtn)){
			if((_opts.saveUrl.indexOf('?mode=') == -1) && (_opts.saveUrl.indexOf('&mode=') == -1))
				_opts.saveUrl = (_opts.saveUrl.indexOf('?') == -1) ? _opts.saveUrl + '?mode=ajaxJson' : _opts.saveUrl + '&mode=ajaxJson';
			_btn_save = jQuery('#'+_opts.saveBtn);
			jQuery('#'+_opts.saveBtn).click(
				function(){
					_save();
				}
			);
		}
		
		/* 删除多个 功能 */
		if(_opts.delsUrl != '' && jQuery('#'+_opts.delsBtn)){
			_btn_dels = jQuery('#'+_opts.delsBtn);
			jQuery('#'+_opts.delsBtn).click(
				function(){
					if(_opts.checkboxName != '')
						_dels(_opts.checkboxName);
					else
						_dels(_opts.checkboxClass);
				}
			);
		}
		
		return _opts;
  }
  
  //取得查询条件参数
  function _loadparam(){
	  _opts.param = jQuery("form#"+_opts.queryForm).serializeArray();
  }
  
  //查询
  function _query(){
	if(!$.submitValidate("#" + _opts.queryForm)){
		return;
    }
	_loadparam();
  	_opts.currPage = 1;
  	_loadPage();
  }
  
  //保存(新增)和修改共用方法，只有url不同
  function _save(){
	var setParam = jQuery("form#"+_opts.saveForm).serialize();
	if(_opts.saveuseFormValidator){
		if(!jQuery.formValidator.pageIsValid(_opts.validatorgroup)){
			return;
		}
	}
	
	if(!$.submitValidate("#" + _opts.saveForm)){
		return;
    }
	
	//_btn_save.attr('disabled','disabled');//不能重复单击同一按钮
	jQuery.ajax({
		type: "POST",
		url: _opts.saveUrl,
		data: setParam,
		success: function(json){
			alert(json.message);
			if(json.codeid == 0){
				if(_opts.saveSuccess && jQuery.isFunction(_opts.saveSuccess)){
					_opts.saveSuccess(json);
				}
				if(_opts.saveUrlTo && _opts.saveUrlTo != '')
					window.location=_opts.saveUrlTo;
				if(_opts.divid && _opts.divid != ''){
					jQuery("#"+_opts.divid).dialog('close');//关闭对话框
				}
			}
			//_btn_save.attr('disabled','').mouseout();
			_btn_save.mouseout();
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("保存失败");
			//_btn_save.attr('disabled','').mouseout();
			_btn_save.mouseout();
		},
		dataType: "json"
	});
  }
  
  //删除多个
  function _dels(name) {
  	var id;
  	
  	if(_opts.checkboxName != '')
  		id = getCheckboxValuesByName(name);
	else
		id = getCheckboxValuesByClass(name);
  	
  	if(id.length < 1){
  		alert("请选择要删除的记录");
  		return;
  	}
  	if(window.confirm("确定要删除选中的记录吗?")){
  		jQuery.getJSON(_opts.delsUrl, { ids: id, mode:'ajaxJson' }, function(json){
  			alert(json.message);
  			if(json.codeid == 0)
  				_opts.reload();
  		});
  	}
  }
  
  /**
   * 载入页面
   * 读取全局变量作为ajax调用的参数
   */
  function _loadPage(){
	  //_btn_query.attr('disabled','disabled');
  	if(_opts.isPaging){
	  	var pageconfig = {
	  		ajaxDataType:'html',
	  		proxyUrl:_opts.queryUrl,
	  		currentPage:_opts.currPage,
	  		perPage:_opts.perPage,
	  		ajaxParam:_opts.param,
	  		barPosition:'bottom',
	  		showMode:_opts.showMode,
	  		callback:function(_perpage,reloadFn){
	  			_opts.perPage = _perpage;
	  			_opts.reload = reloadFn;
	  			//_btn_query.attr('disabled','').mouseout();
	  			_btn_query.mouseout();
	  		}
	  	};
	  	jQuery("#"+_opts.pagecontent).pagination(pageconfig);
  	} else {
  		var url = _opts.queryUrl + "page=1&limit=10000"
  		jQuery("#"+_opts.pagecontent).load(_opts.queryUrl, _opts.param, function(){
  			//_btn_query.attr('disabled','').mouseout();
  			_btn_query.mouseout();
  		});
  	}
  }
  };
  
  
  jQuery.fn.ictBtn = function(callback){
	  return this.each(function(){
		  $(this).find('input.button').mouseover(function(){
				this.className = 'buttonOver';
			}).mouseout(function(){
				this.className = 'button';
			});
	  });
  }
})(jQuery);

jQuery(function(){
	$(document).ictBtn();/*
	jQuery('input.button').mouseover(function(){
		this.className = 'buttonOver';
	}).mouseout(function(){
		this.className = 'button';
	});*/
	$(":reset").click(function(){
		$("form").get(0).reset();
		$("form").find("select").change();
	});
});

/**
 * 实现两级下拉列表关联
 * @author lifa
 * @param pid
 * @param childId
 * @param beanContextId
 * @return
 */
function selectOnChange(pid, childId, beanContextId, flag){
	var parentId = jQuery('#'+pid).val();
	if(parentId == null || parentId == "" || parentId == undefined){
		document.getElementById(childId).length = 1;
		return;
	}
	
	var paramUrl = _contextPath+"/select_getIctList.do?mode=ajaxJson&parentId="+parentId+"&beanContextId="+beanContextId;
	if(flag != undefined && flag != null)
		paramUrl = paramUrl+"&flag="+flag;
		
	jQuery('#'+childId).attr('disabled','true');
	jQuery.ajax({
		type: "POST",
		url: paramUrl,
		data: "",
		dataType: "json",
		success: function(data){
			var select = document.getElementById(childId);
			select.length = 0;
			var i = 0;
			select.options.add(new Option('',''));
			for(;i<data.length;i++){
				var item = new Option(data[i].value,data[i].key); 
				select.options.add(item);
			}
			
			jQuery('#'+childId).removeAttr("disabled");
			//alert(select.length);
			//alert(jQuery('#'+childId).attr('disabled'));
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
		    alert("获取数据失败");
		    jQuery('#'+childId).removeAttr("disabled");
		}
	});	
}

function areaChange(pid, childId){
	var parentId = jQuery('#'+pid).val();
	if(parentId == null || parentId == "" || parentId == undefined){
		document.getElementById(childId).length = 1;
		return;
	}
	var childval = jQuery('#'+childId+"_hid").val();
	var paramUrl = _contextPath+"/area_getAreaList.do?mode=ajaxJson&ids="+parentId;
		
	jQuery('#'+childId).attr('disabled','true');
	jQuery.ajax({
		type: "POST",
		url: paramUrl,
		data: "",
		dataType: "json",
		success: function(data){
			var select = document.getElementById(childId);
			select.length = 0;
			var i = 0;
		//	select.options.add(new Option('',''));
			for(;i<data.length;i++){
				if(data[i].key == childval){
					var item = new Option(data[i].value,data[i].key); 
					item.selected = true;
					select.options.add(item);	
				} else {
					var item = new Option(data[i].value,data[i].key); 
					select.options.add(item);
				}
			}
			jQuery('#'+childId).attr('disabled','');
			jQuery('#'+childId).change();
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
		    alert("获取数据失败");
		    jQuery('#'+childId).attr('disabled','');
		}
	});	
}
function cityChange(pid, childId){
	var parentId = jQuery('#'+pid).val();
	if(parentId == null || parentId == "" || parentId == undefined){
		document.getElementById(childId).length = 1;
		return;
	}
	var childval = jQuery('#'+childId+"_hid").val();
	var paramUrl = _contextPath+"/area_getCityList.do?mode=ajaxJson&ids="+parentId;
		
	jQuery('#'+childId).attr('disabled','true');
	jQuery.ajax({
		type: "POST",
		url: paramUrl,
		data: "",
		dataType: "json",
		success: function(data){
			var select = document.getElementById(childId);
			select.length = 0;
			var i = 0;
		//	select.options.add(new Option('',''));
			for(;i<data.length;i++){
				if(data[i].key == childval){
					var item = new Option(data[i].value,data[i].key);
					item.selected = true;
					select.options.add(item,true);	
				} else {
					var item = new Option(data[i].value,data[i].key); 
					select.options.add(item);
				}
			}
			jQuery('#'+childId).attr('disabled','');
			jQuery('#'+childId).change();
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
		    alert("获取数据失败");
		    jQuery('#'+childId).attr('disabled','');
		}
	});	
}


function areaChange(pid, childId){
	var parentId = jQuery('#'+pid).val();
	if(parentId == null || parentId == "" || parentId == undefined){
		document.getElementById(childId).length = 1;
		return;
	}
	var childval = jQuery('#'+childId+"_hid").val();
	var paramUrl = _contextPath+"/area_getAreaList.do?mode=ajaxJson&ids="+parentId;
		
	jQuery('#'+childId).attr('disabled','true');
	jQuery.ajax({
		type: "POST",
		url: paramUrl,
		data: "",
		dataType: "json",
		success: function(data){
			var select = document.getElementById(childId);
			select.length = 0;
			var i = 0;
		//	select.options.add(new Option('',''));
			for(;i<data.length;i++){
				if(data[i].key == childval){
					var item = new Option(data[i].value,data[i].key); 
					item.selected = true;
					select.options.add(item);	
				} else {
					var item = new Option(data[i].value,data[i].key); 
					select.options.add(item);
				}
			}
			jQuery('#'+childId).attr('disabled','');
			jQuery('#'+childId).change();
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
		    alert("获取数据失败");
		    jQuery('#'+childId).attr('disabled','');
		}
	});	
}
/* 
 * by luojin,old version
 */

function deptOnChange(pid,childId){
	var deptId = jQuery('#'+pid).val();
	if(deptId == null || deptId == "" || deptId == undefined){
		document.getElementById(childId).length = 1;
		return;
	}
	jQuery('#'+childId).attr('disabled','true');
	jQuery.ajax({
		type: "POST",
		url: _contextPath+"/module/archives/monitor_userList.do?mode=ajaxJson&deptId="+deptId,
		data: "",
		dataType: "json",
		success: function(data){
			var select = document.getElementById(childId);
			select.length = 0;
			var i = 0;
			select.options.add(new Option('',''));
			for(;i<data.length;i++){
				var item = new Option(data[i].value,data[i].key); 
				select.options.add(item);
			}
			jQuery('#'+childId).attr('disabled','');
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
		    alert("获取数据失败");
		    jQuery('#'+childId).attr('disabled','');
		}
	});	
}

//js日期格式化为字符串
Date.prototype.pattern=function(fmt) {        
    var o = {        
    "M+" : this.getMonth()+1, //月份        
    "d+" : this.getDate(), //日        
    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时        
    "H+" : this.getHours(), //小时        
    "m+" : this.getMinutes(), //分        
    "s+" : this.getSeconds(), //秒        
    "q+" : Math.floor((this.getMonth()+3)/3), //季度        
    "S" : this.getMilliseconds() //毫秒        
    };        
    var week = {        
    "0" : "\u65e5",        
    "1" : "\u4e00",        
    "2" : "\u4e8c",        
    "3" : "\u4e09",        
    "4" : "\u56db",        
    "5" : "\u4e94",        
    "6" : "\u516d"       
    };        
    if(/(y+)/.test(fmt)){        
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));        
    }        
    if(/(E+)/.test(fmt)){        
        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "\u661f\u671f" : "\u5468") : "")+week[this.getDay()+""]);        
    }        
    for(var k in o){        
        if(new RegExp("("+ k +")").test(fmt)){        
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));        
        }        
    }        
    return fmt;        
}      