$.extend($.fn.validatebox.defaults.rules, { 
	minLength: {//最少字符数
		validator: function(value, param){ 
		    return value.length >= param[0]; 
		}, 
		message: '请输入至少 {0} 个字符。'
	},
	maxLength: {//最大字符数
		validator: function(value, param){ 
		    return value.length <= param[0]; 
		}, 
		message: '请输入字符不超过 {0} 个。' 
	},
	idcard : {// 验证身份证 
        validator : function(value) { 
            return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value); 
        }, 
        message : '身份证号码格式不正确' 
    },
    phone : {// 验证电话号码 
        validator : function(value) { 
            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value); 
        }, 
        message : '格式不正确,请使用下面格式:025-88888888' 
    },
    mobile : {// 验证手机号码 
        validator : function(value) { 
            return /^(13|14|15|17|18)\d{9}$/i.test(value); 
        }, 
        message : '手机号码格式不正确' 
    },
    phoneOrMobile : {// 验证手机号码或者电话号码
        validator : function(value) { 
        	return /^(13|14|15|17|18)\d{9}$/i.test(value) || /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value); 
	    }, 
	    message : '号码格式不正确' 
    },
    intOrFloat : {// 验证整数或小数 
        validator : function(value) { 
            return /^\d+(\.\d+)?$/i.test(value); 
        }, 
        message : '请输入数字，并确保格式正确' 
    },
    money : {// 验证整数或小数 非0
        validator : function(value) {
            return /^\d+(\.\d{1,2})?$/i.test(value) && !/^0+(\.0{1,2})?$/i.test(value); 
        }, 
        message : '请输入正确金额，金额不可以为零且最多保留小数点两位' 
    },
    settleMoney : {// 验证整数或小数 非0
        validator : function(value) {
            return /^(-)?\d+(\.\d{1,2})?$/i.test(value); 
        }, 
        message : '请输入正确金额，金额最多保留小数点两位' 
    },
    int: {// 验证整数
        validator : function(value) { 
            return /^(-)?\d+$/i.test(value); 
        }, 
        message : '请输入整数，并确保格式正确' 
    }, 
    positiveint: {// 验证整数
        validator : function(value) { 
            return /^[1-9][0-9]*$/i.test(value); 
        }, 
        message : '请输入正整数，并确保格式正确' 
    }, 
    naturalNumber: {// 验证整数
        validator : function(value) { 
            return /^\d+$/i.test(value); 
        }, 
        message : '请输入自然数，并确保格式正确' 
    }, 
    currency : {// 验证货币 
        validator : function(value) { 
            return /^\d+(\.\d+)?$/i.test(value); 
        }, 
        message : '货币格式不正确' 
    }, 
    qq : {// 验证QQ,从10000开始 
        validator : function(value) { 
            return /^[1-9]\d{4,11}$/i.test(value); 
        }, 
        message : 'QQ号码格式不正确' 
    }, 
    integer : {// 验证整数 
        validator : function(value) { 
            return /^[+]?[0-9]+\d*$/i.test(value);
        }, 
        message : '请输入整数' 
    }, 
    age : {// 验证年龄
        validator : function(value) { 
            return /^(?:[1-9][0-9]?|1[01234][0-9]|150)$/i.test(value); 
        }, 
        message : '年龄必须是0到150之间的整数' 
    }, 
    picSize : {// 验证图片的个数
        validator : function(value) { 
            return /^([1-9])$/i.test(value); 
        }, 
        message : '图片的个数必须是1到9之间的整数' 
    }, 
    chinese : {// 验证中文 
        validator : function(value) { 
            return /^[\Α-\￥]+$/i.test(value); 
        }, 
        message : '请输入中文' 
    }, 
    english : {// 验证英语 
        validator : function(value) { 
            return /^[A-Za-z]+$/i.test(value); 
        }, 
        message : '请输入英文' 
    }, 
    unnormal : {// 验证是否包含空格和非法字符 
        validator : function(value) { 
            return /.+/i.test(value); 
        }, 
        message : '输入值不能为空和包含其他非法字符' 
    }, 
    username : {// 验证用户名 
        validator : function(value) { 
            return /^[a-zA-Z][a-zA-Z0-9_]{4,15}$/i.test(value); 
        }, 
        message : '用户名不合法（字母开头，允许5-16字节，允许字母数字下划线）' 
    }, 
    password : {// 验证密码 
        validator : function(value) { 
            return /^[a-zA-Z][a-zA-Z0-9_@#|!.&*-^#()]{5,15}$/i.test(value); 
        }, 
        message : '密码不合法（字母开头，允许6-16字节，允许字母数字_@#|!.&*-^#()）' 
    },
    faxno : {// 验证传真 
        validator : function(value) { 
            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value); 
        }, 
        message : '传真号码不正确' 
    }, 
    zip : {// 验证邮政编码 
        validator : function(value) { 
            return /^[1-9]\d{5}$/i.test(value); 
        }, 
        message : '邮政编码格式不正确' 
    }, 
    ip : {// 验证IP地址 
        validator : function(value) { 
            return /d+.d+.d+.d+/i.test(value); 
        }, 
        message : 'IP地址格式不正确' 
    }, 
    name : {// 验证姓名，可以是中文或英文 
            validator : function(value) { 
                return /^[\Α-\￥]+$/i.test(value)|/^\w+[\w\s]+\w+$/i.test(value); 
            }, 
            message : '请输入姓名' 
    },
    date : {// 验证日期格式 
        validator : function(value) { 
        //格式yyyy-MM-dd或yyyy-M-d
            return /^(?:(?!0000)[0-9]{4}([-]?)(?:(?:0?[1-9]|1[0-2])\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\1(?:29|30)|(?:0?[13578]|1[02])\1(?:31))|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-]?)0?2\2(?:29))$/i.test(value); 
        },
        message : '请输入合适的日期格式'
    },
    msn:{ 
        validator : function(value){ 
        return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value); 
    }, 
    message : '请输入有效的msn账号(例：abc@hotnail(msn/live).com)' 
    },
    same:{ 
        validator : function(value, param){ 
            if($("#"+param[0]).val() != "" && value != ""){ 
                return $("#"+param[0]).val() == value; 
            }else{ 
                return true; 
            } 
        }, 
        message : '两次输入的密码不一致！'    
    },
    docNumber:{
    	validator : function(value) { 
        	return /^[a-zA-Z0-9]{1,}[-][a-zA-Z0-9]{1,}$/i.test(value) || /^[a-zA-Z0-9]{1,20}$/i.test(value); 
    	}, 
    	message : '单据编号输入格式不正确，只能是数字字母，中间可以有-' 
    },
	custRegExp : {//正则表达式验证
		validator : function(value, param) {
			var regExp = new RegExp(eval(param[0]));
			
			return regExp.test(value);
		},
		message : ""
	},
	checkDayNumber:{//检测 一个月中的天数，最大不能超过30
			validator: function(value) {
			return /^(?:[1-9]?|[1-2][0-9]|30)$/i.test(value);
        },
        message : '请填写正确的日期！'
	},
    timeComparison:{ 
        validator : function(value, param){
            if($("#"+param[0]).val() != "" && $("#"+param[1]).val() != "")
            {
                var startTime = new Date($("#"+param[0]).val());
                var endTime = new Date($("#"+param[1]).val());
                return startTime.getTime() < endTime.getTime();
            }
            else
            { 
                return true;
            } 
        }, 
        message : '生效时间大于失效时间！'
    },
    numberComparison:{ 
        validator : function(value, param){
            if($("#"+param[0]).val() != "" && $("#"+param[1]).val() != "")
            {
                return $("#"+param[0]).val() < $("#"+param[1]).val();
            }
            else
            { 
                return true;
            } 
        }, 
        message : '结束积分大于开始积分！'
    },
    replyType:{
			validator: function(value) {
			if($("#type").val() == "1" && $.trim($("#replyContent").val()) == ""){
            	return false
            }
			return true;
	    },
	    message : '回复对应的评论不能为空！'
	},
    checkAccount:{
		validator: function(value, param) {
    	    var dataJson = {};
    	    dataJson["account"] = value;
    	    if (param != null)
    	    {
    	    	dataJson["id"] = $("#" + param[0]).val();
    	    }
			var result = $.ajax({
				url : 'favUser_checkAccount.do',
				dataType : "text",
				data : dataJson,
				async : false,//需要设置为同步
				cache : false,
				type : "post"
			}).responseText;
			var m = eval("(" + $.trim(result)+ ")");
			return m.message == "true";
	    },
	    message : '账号重复，请重新填写！'
	}
});
