var Validform = function(selector, opts){
	var defaults = {
		callback: null,
		beforeCheck: null,
		beforeSubmit: null
	}
	
	var form = $(selector);
	opts = $.extend(defaults, opts);
	
	var regex = {
		email: /\w+((-w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+/,
		phone: /0?(13|14|15|17|18)[0-9]{9}/,
		number: /^[1-9]\d*$/,
		currency: /^\d+(?:\.\d{0,2})?$/ 
	}
	
	var params = {};
	
	if($.isFunction(opts.beforeCheck) && !opts.beforeCheck(form)){
		return false;
	}

	form.on("submit", function(){
		var flag;
		
		form.find("[data-type]").each(function(){
			flag = true;
			var type = $.trim($(this).data("type"));
			if(type !== ""){
				var val = getValue($(this)), msg;
				
				if(val === ""){
					$(this).focus();
					flag = false;
					msg = getMsg($(this), "null");
					
					if($.isFunction(opts.callback)){
						opts.callback($(this), msg, form);
					}else{
						tips(msg);
					}
					return false;
				}else{
					var reg = getRegex(type);
					if(reg && !reg.test(val)){
						$(this).focus();
						flag = false;
						msg = getMsg($(this), "error");
						
						if($.isFunction(opts.callback)){
							opts.callback($(this), msg, form);
						}else{
							tips(msg);
						}
						return false;
					}
				}
				
				var name = $(this).attr("name");
				if(name){
					params[name] = val;
				}
				
			}
		});
		
		if($.isFunction(opts.beforeSubmit)){
			if(flag){
				return opts.beforeSubmit(form, params);
			}
			return false;
		}else{
			return flag;
		}
	});
	
	// 获取值
	function getValue(obj){
		var val = "";
		
		switch(obj[0].tagName){
			case "INPUT":
			case "TEXTAREA":
				var type = obj.attr("type");
				if(type == "radio"){
					val = form.find("input[name="+ obj.attr("name") +"]:checked").val();
				}else if(type == "checkbox"){
					var valarr = [];
					form.find("input[name="+ obj.attr("name") +"]:checked").each(function(){
						valarr.push($(this).val());
					});
					val = valarr.join(",");
				}else{
					val = obj.val();
				}
			break;
			case "SELECT":
				val = obj[0].options[obj[0].selectedIndex].value;
			break;
		}
		return $.trim(val);
	}

	// 获取提示信息
	function getMsg(obj, type){
		var msg = obj.data(type);
		
		if(!msg){
			if((obj[0].tagName == "INPUT" && obj.hasClass("ui-input-text")) || obj[0].tagName == "TEXTAREA"){
				msg = type == "null" ? "请输入信息" : "信息格式错误";
			}
		}
		
		if(!msg){
			msg = "请选择信息";
		}
		
		return msg;
	}
	
	// 是否有这个类型
	function getRegex(type){
	
		if(type.charAt(0) == "/" && type.charAt(type.length - 1) == "/"){
			return new RegExp(type.substring(1, type.length - 1));
		}
		
		for(var item in regex){
			if(type == item){
				return regex[item];
			}
		}
		
		return null;
	}
}

$(function(){
	var loading = $('<div class="ui-loading-block show"><div class="ui-loading-cnt"><i class="ui-loading-bright"></i><p>正在加载中...</p></div></div>');
	$('body').append(loading);
	window.onload = function(){
		/*setTimeout(function(){
			loading.removeClass('show');
		}, 300);*/
		loading.removeClass('show');
	}
	$('.main, .footer').on('click', '[data-href]', function(){
		window.location.href = $(this).data('href');
	});
	
	if($('.footer').length){
		$('body').addClass('has-footer');
	}
	
	$('[data-panel]').on('click', function(){
        $("#" + $(this).data('panel')).addClass('show');   
	});
	
	$('.panel .ui-icon-return').on('click', function(){
		$(this).parents('.panel').removeClass('show');
	});
});

function loadMore(callback){
	var page = 1;
	$(window).scroll(function(){
		if($('body').height() - $(window).scrollTop() - $(window).height() === 0){
			page ++;
			callback(page);
		}
	});
}

function tips(msg){
	$.tips({
		content: msg,
		stayTime:2000,
		type:"warn"
	});
}

function doingTips(msg){
	$.tips({
		content: msg,
		stayTime: 2 * 60 * 1000,
		type:"warn"
	});
}

function searchCollection(id)
{
	$("#" + id).submit();
}

function matchLable(lable){
    var lbe = "";
    if(lable == '拍卖品'){
	      lbe = '<span class="tag tag-warning">'+lable+'</span>';
	  }
	  else if(lable == '展品' || lable == '展 品'){
        lbe = '<span class="tag tag-primary">'+lable+'</span>';   	
	  }
	  else if(lable == '热门' || lable == '热 门'){
        lbe = '<span class="tag tag-danger">'+lable+'</span>';
	  }
	  else if(lable == '已鉴定'){
		  lbe = '<span class="tag tag-success">'+lable+'</span>';
	  }
	  else if(lable == ''){
		  lbe = '<span class="blank_tag"></span>';
	  }
	  else{
		  lbe = '<span class="tag tag-danger">'+lable+'</span>';
    }
    return lbe;
}

//判断是否iphone中Safari浏览器
function isIphoneSafari()
{
	var ua = navigator.userAgent.toLowerCase(); 
	/**
	 *  (s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] : 
        (s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] : 
        (s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] : 
        (s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] : 
        (s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0; 
	 */
	if (ua.indexOf('iphone') > -1)
	{
		var s = ua.match(/version\/([\d.]+).*safari/);
		if (s != null && s[1]) {
	        return true;
	    }
	}
	return false;
}

function addInputElement(idx)
{
    var upload = $('.add input[name=loadmultfile'+idx+']');
    var add = upload.parents('.add');
    upload.on('change', function(){
        for(var i = 0; i < this.files.length; i ++){
            var files = this.files;
            (function(n){
                lrz(files[n]).then(function(result){
                    add.before('<li class="yu_lan_image"><figure style="background-image:url('+ result.base64 +')"></figure></li>'); 
                }).catch(function(error){
                    console.log(error);  
                });
            })(i);
        }
        $(this).parent().hide();
        if (($(".yu_lan_image").length + this.files.length) < 6)
        {
            $('.add input[name=loadmultfile'+(idx+1)+']').parent().show();
        }
    });
}