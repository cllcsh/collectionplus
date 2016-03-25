<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<head>
    <title>个人资料</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="baidu-site-verification" content="hWGw6qbfv7" />
    
    <link rel="stylesheet" href="/m/css/style.css">

</head>
<body>
    <header class="comm-header">
        <div class="left opt">
            <a href="javascript:window.history.go(-1);">返回</a>
        </div>
        <div class="right opt">
            <s:if test="%{userSession.approveFlag == 2}">
				<a id="J-Submit" href="javascript:;">完成</a>
			</s:if>
        </div>
        <h1 class="title">个人资料</h1>
    </header>

    <div class="wrap">
        <div class="comm-form mod-user-info">
            <form action="reg-3.html">
                <div class="form-group">
                    <label class="label">所在地：</label>
                    <div class="input">
					<select class="select" name="userForm.province" id="s1">
						<option></option>
					</select>
					<select class="select" name="userForm.city" id="s2">
						<option></option>
					</select>
					<select class="select" name="userForm.area" id="s3">
						<option></option>
					</select></div>
                </div>
                <div class="form-group">
                    <label class="label">详细地址：</label>
                    <div class="input"><input id="address" name="userForm.address" type="text" data-validate="chs" value="<s:property value='userForm.address'/>"></div>
                </div>
                <div class="form-group">
                    <label class="label">公司名称：</label>
                    <input id="companyName" name="userForm.companyName" type="text" data-validate="username" value="<s:property value='userForm.companyName'/>">
                </div>
                <div class="form-group">
                    <label class="label">工商注册号：</label>
                    <input id="companyName" name="userForm.regNumber" type="text" data-validate="username" value="<s:property value='userForm.regNumber'/>">
                </div>
                <div class="form-group">
                    <label class="label">营业执照：</label>
                    <div class="input">
						<div class="comm-file-upload">
									<!--
                                    <div class="comm-img camera-line"></div>
                                    <div class="title">上传营业执照</div>-->
							 <img src="<s:property value='userForm.businessPicPath'/>" alt="">
							 <s:hidden id="businessPicPath" name="userForm.businessPicPath"></s:hidden>
                        </div>
					</div>
                </div>
            </form>
        </div>
    </div>

    <div id="J-BrandModal" class="comm-modal none">
        <div class="modal">
            <div class="close j-close">&times;</div>
            <div class="title">选择汽车品牌</div>
            <div class="module-auth-selcarbrands">
                <div class="title">按品牌<span class="comm-text-red">拼音</span>首字母查找</div>
                <div class="words strong">
                    <span class="active">A</span><span>B</span><span>C</span><span>D</span><span>E</span>
                    <span>A</span><span>B</span><span>C</span><span>D</span><span>E</span>
                    <span>A</span><span>B</span><span>C</span><span>D</span><span>E</span>
                    <span>A</span><span>B</span><span>C</span><span>D</span><span>E</span>
                    <span>A</span><span>B</span><span>C</span><span>D</span><span>E</span>
                    <span>A</span><span>B</span><span>C</span><span>D</span><span>E</span>
                </div>
                <div class="brands">
                    <span class="active">劳斯兰斯</span><span>大众</span><span>奥迪</span><span>劳斯兰斯</span><span>大众</span><span>奥迪</span>
                    <span>劳斯兰斯</span><span>大众</span><span>奥迪</span>
                </div>
            </div>
            <div class="button button-primary j-close">确定</div>
        </div>
    </div>

    <script src="/m/bower_components/zepto/zepto.min.js"></script>
<script src="/m/bower_components/zeptotouch/zepto-touch.min.js"></script>
<script src="/m/js/app.js"></script>
<script type="text/javascript" src="/resource/js/geo.js"></script>

    <script>
$(document).ready(function(){
	setup();// 查询条件的省市县初始化
	var province = '<s:property value="userForm.province"/>';
	if (province != "") {
		preselect(province);
		var city = '<s:property value="userForm.city"/>';
		if (city != "") {
			$("#s2").val(city);
			change(2);
			var area = '<s:property value="userForm.area"/>';
			if (area != "") {
				$("#s3").val(area);
			}
		}
	}
});
    // 选择品牌
    var brands = $('#J-InputItems');
    var modal = new Fache.UI.Modal({
        id : 'J-BrandModal',
        close : function(obj) {
            var brand = this.modal.find('.brands').find('.active').html();
            brands.append('<div class="item">'+ brand +'<i class="comm-img angle-close"></i></div>');
        }
    });
    // 删除品牌
    brands.on('tap','.item',function(){
        var _this = $(this);
        if (_this.hasClass('plus')) {
            return;
        }
        _this.remove();
    });

    // 提交注册表单
    $('#J-Submit').on('tap', function(){
        $('form').submit();
    })
    </script>

</body>
</html>
