var msg ={
	PLEASE_SELECT_TO_DELETE	: "请选择要删除的记录",
	CONFIRM_DELETE_ALL	: "确定要删除选中的所有记录吗?",
	CONFIRM_DELETE	: "确定要删除吗?",
	SELECT_ONE	: "请选择一条记录!",
	CAN_ONLY_SELECT_ONE	: "只能选择一条记录!",
	LOADING 				: "正在加载页面..."
};
		


var JsonMsgObj = function(responseText) {
  this.json = eval('(' + responseText + ')');
};

JsonMsgObj.prototype.getCodeid = function() {
  return this.json.codeid;
};

JsonMsgObj.prototype.getMessage = function() {
  return this.json.message;
};

JsonMsgObj.prototype.getText = function() {
  return this.json.text;
};


function checkAll(checkTopId, checkItemName){
  //var objs = document.all(checkItemName);
  var objs = jQuery(":checkbox."+checkItemName);
  var check = document.getElementById(checkTopId).checked;  
  if (objs != null) {  
	  if(objs.length == undefined){
		  if(check == true){
		      objs.checked = true;
			}else{
		      objs.checked = false;
			}
		}else{	
		  for(var i=0; i<objs.length; i++) {
			if(check == true){	
		      objs[i].checked = true;
			}else{
		      objs[i].checked = false;
			}
		}
	  }
  }
}

function check(checkTopId, checkItemName){
  //var objs = document.all(checkItemName);
  var objs = jQuery(":checkbox."+checkItemName);
  
  document.getElementById(checkTopId).checked = true;
    if (objs != null) {
	     if(objs.length == undefined){
	     	if(objs.checked == false)
		  		{
		  			document.getElementById(checkTopId).checked = false;
		  		}
		 }else {
			for(var i=0; i<objs.length; i++) {
	      	if(objs[i].checked == false)
			  	{
			  		document.getElementById(checkTopId).checked = false;
			  	}
		 }
	  }
  }
}

/**
 * 根据class属性取得所有选中的checkbox的值用逗号分隔
 * @param className
 * @return
 */
function getCheckboxValuesByClass(className){
	var str = '';
    jQuery(":checkbox." + className + ":checked").each(function(){
    	str += jQuery(this).val() + ",";
    });
    return str;
}
/**
 * 根据name属性取得所有选中的checkbox的值用逗号分隔
 * @param name
 * @return
 */
function getCheckboxValuesByName(name){
	var str = '';
    jQuery(":checkbox[name='" + name + "']:checked").each(function(){
    	str += jQuery(this).val() + ",";
    });
    return str;
}
/**
 * 根据name属性取得所有未选中的checkbox的值用逗号分隔
 * @param name
 * @return
 */
function getNotCheckboxValuesByName(name){
	var str = '';
	jQuery(":checkbox[name='" + name + "']").not("[checked]").each(function(){
    	str += jQuery(this).val() + ",";
    });
    return str;
}

function disableButton(buttons){
	buttons.each(function(){
			jQuery(this).attr('disabled','true');
		}
	);
}
function enableButton(buttons){
	buttons.each(function(){
			jQuery(this).attr('disabled','');
			jQuery(this).mouseout();
		}
	);
}

//初始化弹出对话框
function initDialog(domId, params) {
	param = jQuery.extend({
		bgiframe: true,
		autoOpen: false, 
		width:480,
		height: 280,
		modal: true,
		close: function() {
			jQuery(this).html('');
		},
		callback:function(){
		}
	},params||{});
	if(jQuery("#"+domId).size() === 0){
		jQuery('body').append('<div id="'+ domId +'"></div>')
	}
	jQuery("#"+domId).dialog(param);
}
//在页面弹出对话框
document.openDialog = function(domId, params) {
	if(!domId)domId = "_noid_dialog"
	param = jQuery.extend({
		loadingText : "正在加载页面..."
	},params||{});
	if((jQuery("#" + domId).dialog('open').html(param.loadingText)).size() === 0){
		initDialog(domId, param);
		jQuery("#" + domId).dialog('open').html(param.loadingText);
	}
	return jQuery("#" + domId);
}

jQuery.fn.extend({
	addmask : function() {
		mask = document.createElement('div');
		jQuery(mask).addClass("bodymask");
		jQuery(mask).css("height",jQuery(this).outerHeight());
	//	jQuery(mask).css("top",jQuery(this).position().top);
		jQuery(mask).css("left",jQuery(this).position().left);
		jQuery(this).append(mask);
	},
	removemask : function() {
		jQuery(this).find('.bodymask').remove()
  }
});

//时间格式化函数	
Date.prototype.format = function(format){
	var o = {
		"M+" :this.getMonth() + 1, // month
		"d+" :this.getDate(), // day
		"h+" :this.getHours(), // hour
		"m+" :this.getMinutes(), // minute
		"s+" :this.getSeconds(), // second
		"q+" :Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" :this.getMilliseconds()	// millisecond
	}
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
	return format;
}

/**
 * 将全角转换为半角
 */
String.prototype.DBC2SBC = function(){ 
	var result = ''; 
	for (i=0 ; i<this.length; i++) {
		code = this.charCodeAt(i);//获取当前字符的unicode编码 
		if (code >= 65281 && code <= 65373){ //在这个unicode编码范围中的是所有的英文字母已经各种字符 
			result += String.fromCharCode(this.charCodeAt(i) - 65248);//把全角字符的unicode编码转换为对应半角字符的unicode码 
		}else if (code == 12288){ //空格 
			result += String.fromCharCode(this.charCodeAt(i) - 12288 + 32); 
		}else { 
			result += this.charAt(i); 
		} 
	} 
	return result; 
}

function getRealLength(str){
	return str.replace(/[^\x00-\xff]/g,"***").length;//1 utf-8 character = 3 ascii character 
}

function clearBlank(widget){
	var messageBox = $(widget);
	var content = messageBox.val();
	messageBox.val($.trim(content));
}


/**
 * 将回车模拟为tab键
 */
function enterToTab(e) {
if(event.srcElement.type != 'submit' && event.srcElement.type!="button" && event.srcElement.type!="image" && event.srcElement.type != 'textarea' && event.keyCode == 13) 
	event.keyCode = 9;
} 
document.onkeydown = enterToTab; 

$(function(){
	$('.IDNumOnly').keypress(function(){
		if(!((window.event.keyCode >= 48 && window.event.keyCode <= 57) || (window.event.keyCode == 120) || (window.event.keyCode == 88)))
		{
		   window.event.keyCode = 0 ;
		}
		return;
	}).bind('paste',function(){
	var   s=clipboardData.getData('text');
	if(!/\D/.test(s))
		this.value=s.replace(/^0*/,'');
	return false;
  });

	$('.numOnly').keypress(function(){
		if(!((window.event.keyCode >= 48) && (window.event.keyCode <= 57)))
		{
		   window.event.keyCode = 0 ;
		}
		return;
	}).bind('paste',function(){
	var   s=clipboardData.getData('text');
	if(!/\D/.test(s))
		this.value=s.replace(/^0*/,'');
	return false;
  });
	
	$('.telOnly').keypress(function(){
		if(!((window.event.keyCode >= 48 && window.event.keyCode <= 57) || (window.event.keyCode == 45)))
		{
		   window.event.keyCode = 0 ;
		}
		return;
	}).bind('paste',function(){
	var   s=clipboardData.getData('text');
	if(!/\D/.test(s))
		this.value=s.replace(/^0*/,'');
	return false;
  });
	
	$(':text').keypress(function(){
		if(window.event.keyCode == 39){
		   window.event.keyCode = 0 ;
		}
		return;
	});
	
})
