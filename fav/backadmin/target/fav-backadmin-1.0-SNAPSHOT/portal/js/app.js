(function(){
    'use strict';

    var Fache = {UI:{}};

    var bod = $('body');
    var win = $(window);
    var doc = $(document);

    /*===============
     回到顶部
     ===============*/
    Fache.UI.Btp = function() {
        var btp;
        bod.append('<div id="J-BackTop" class="comm-back-top"><i class="comm-img back-top"></i></div>');
        btp = $('#J-BackTop');
        function reset (){
            if (win.scrollTop() > 50) {
                btp.show();
            } else {
                btp.hide();
            }
        }
        btp.click(function(){
            win.scrollTop(0);
        });
        win.scroll(reset);
    };

    /*===============
     搜索框
     ===============*/
    var searchIpt = $('#J-SearchIpt');
    var placeholder = searchIpt.prev();
    searchIpt.focus(function(){
        placeholder.hide();
    }).blur(function(){
        if (!searchIpt.val()) {
            placeholder.show();
        }
    });

    /*===============
     头部菜单
     ===============*/
    // css 处理了
    var menu = $('#J-HeaderMenu');
    if (!menu.hasClass('menu-active')) {
        menu.click(function(){
            new FACHE.UI.Modal({
                type : 'warn',
                body : '您还没通过审核',
                btnText : '逛逛首页',
                ok : function(){window.location.href = '/?from=modal';}
            });
        });
    }

    /*===============
     弹出提示层

     option:
     id(String) - dom id
     type(String) - 类型: waring, right
     body(String) - 显示内容
     btnText(String) - 按钮文案
     close(function) - 关闭回调
     ok(function) - 确认回调
     ===============*/
    Fache.UI.Modal = function(option){
        this.option = option;
        if (this.option.id) {
            this.modal = $('#' + option.id);
            this.bind();
        } else {
            this.init();
        }
    };
    Fache.UI.Modal.prototype = {
        init : function(){
            var tmpl = '<div id="_Modal" class="comm-modal">';
            tmpl += '<div class="modal">';
            tmpl += '    <div class="close j-close">&times;</div>';
            if (this.option.type === 'warn') {
                tmpl += '    <div class="type"><i class="comm-img gray-warn"></i></div>';
            } else if (this.option.type === 'right') {
                tmpl += '    <div class="type"><i class="comm-img gray-tick"></i></div>';
            }
            tmpl += '    <div class="text">'+ this.option.body +'</div>';
            tmpl += '    <div class="action">';
            tmpl += '        <div class="button button-primary j-ok">'+ this.option.btnText +'</div>';
            tmpl += '    </div>';
            tmpl += '    </div>';
            tmpl += '</div>';
            bod.append(tmpl);
            this.modal = $('#_Modal');
            this.bind();
        },
        bind : function(){
            var _this = this;
            this.modal.find('.j-close').click(function(){
                _this.option.close && _this.option.close.call(_this);
                if (_this.option.id) {
                    _this.modal.hide();
                } else {
                    _this.modal.remove();
                }
            });
            this.modal.find('.j-ok').click(function(){
                _this.option.ok && _this.option.ok.call(_this, _this.modal);
            });
        },
        show : function() {
            this.modal.show();
        },
        close: function() {
            if (this.option.id) this.modal.hide();
            else this.modal.remove();
        }
    };
    /*===============
     表单验证
     ===============*/
    var validateRules = {
        mobile: function () {
            return {
                regexp: new RegExp('^(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$', 'g'),
                msg: '手机号格式错误'
            }
        },
        password: function () {
            return {
                regexp: new RegExp('^.{6,20}$', 'g'),
                msg: '密码为6-20个字符'
            }
        },
        phoneCode: function () {
            return {
                regexp: new RegExp('^[0-9]{6}$', 'g'),
                msg: '手机校验码为6位数字'
            }
        },
        chs: function () {
            return {
                regexp: new RegExp('^[^u4e00-u9fa5]+$', 'g'),
                msg: '此项必须是中文'
            }
        },
        idNo: function () {
            return {
                regexp: new RegExp('(^\\d{15}$)|(^\\d{17}([0-9]|X)$)', 'g'),
                msg: '身份证格式错误'
            }
        },
        number: function () {
            return {
                regexp: new RegExp('^[0-9]*$', 'g'),
                msg: '此项必须是数字'
            }
        },
        username: function () {
            return {
                regexp: new RegExp('^[0-9a-zA-Z_\-]{2,32}$', 'g'),
                msg: '用户名格式错误'
            }
        },
        email: function () {
            return {
                regexp: new RegExp(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/),
                msg: '邮箱格式错误'
            }
        },
        required: function() {
            return {
                regexp: new RegExp('^\\S+', 'g'),
                msg: '此项不能为空'
            }
        }
    }
    Fache.UI.Form = function(selector){
        this.form = $(selector);
        this.bindEvent();
    };
    Fache.UI.Form.prototype = {
        bindEvent : function(){
            var _this = this;
            _this.form.find('input').focus(function(){
                $(this).prev('.placeholder').hide();
            }).blur(function(){
                var _ = $(this);
                var val = _.val();
                var rule = _.data('validate');
                if (!val) {
                    _.prev('.placeholder').show();
                }else {
                    _.prev('.placeholder').hide();
                }
                if (rule) {
                    var result = _this.validateField(rule, val);
                    if (result !== true) _this.renderErrorField(_, result);
                    else _this.renderSuccessField(_);
                }
            });

            this.form.on('submit', function(){
                return true;
            });
        },
        validateField : function(rule, value) {
            var rule = validateRules[rule]();
            if (!rule.regexp.test(value)) return rule.msg;
            else return true;
        },
        renderErrorField: function(input, msg) {
            if (input.parent().find('.comm-form-error-tip').length) return;
            input.parent().append('<span class="comm-form-error-tip" style="color:red;position:absolute;top: 0;width:200px;right:-200px;padding-left:10px;">'+msg+'</span>');
        },
        renderSuccessField: function(input) {
            input.parent().find('.comm-form-error-tip').remove();
        }
    };

    /*===============
     品牌按字母选择逻辑
     options:
     domId: // 容器id default: 'J-AlphaChoose'
     alphaDomClass: // 字母容器class, default: 'j-alphas'
     brandsDomClass: // 品牌容器class, default: 'j-brands'
     ok: function(brand){} // 选择完成回调函数
     url: '' // 查询品牌列表的接口，例如: /brands?alpha=A
     onBrandsReceive: functon() {} // 点击字母获取到品牌列表
     ===============*/
    var alphas = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
    var alphaChooseDefault = {
        domId : 'J-AlphaChoose',
        alphaDomClass: 'j-alphas',
        brandsDomClass: 'j-brands',
        ok: function(){},
        url: null
    };
    Fache.UI.AlphaChoose = function(option){
        this.option = $.extend({}, alphaChooseDefault, option);
        this.dom = $('#' + this.option.domId);
        this.alphas = this.dom.find('.' + this.option.alphaDomClass);
        this.brands = this.dom.find('.' + this.option.brandsDomClass);
        this.render();
        this.bindEvent();
        var alpha = this.alphas.find('.active').html();
        if (alpha) this.doRequest(alpha);
    };
    Fache.UI.AlphaChoose.prototype = {
        bindEvent: function(){
            var _this = this;
            _this.alphas.on('click', 'span', function(){
                var _span = $(this);
                _span.addClass('active').siblings().removeClass('active');
                _this.doRequest(_span.html());
            });
            _this.brands.on('click', 'span', function(){
                var _span = $(this);
                _span.addClass('active').siblings().removeClass('active');
                _this.option.ok(_span.html());
            });
        },
        render: function() {
            var tmpl = '';
            for (var i in alphas) {
                // if (parseInt(i) === 0)
                // tmpl += '<span class="active">'+ alphas[i] +'</span>';
                // else
                tmpl += '<span>'+ alphas[i] +'</span>';
            }
            this.alphas.html(tmpl);
        },
        doRequest: function(alpha) {
            if (!this.option.url) throw new Error('[Fache.UI.AlphaChoose]: 没有指定url参数');
            $.get(this.option.url, {"brandForm.pinyin": alpha}, this.option.onBrandsReceive);
        }
    };

    /*===============
     添加收藏
     ===============*/
    Fache.addFavorite = function () {
        var url = window.location;
        var title = document.title;
        var ua = navigator.userAgent.toLowerCase();
        if (ua.indexOf("360se") > -1) {
            alert("由于360浏览器功能限制，请按 Ctrl+D 手动收藏！");
        }
        else if (ua.indexOf("msie 8") > -1) {
            window.external.AddToFavoritesBar(url, title); //IE8
        }
        else if (document.all) {
            try{
                window.external.addFavorite(url, title);
            }catch(e){
                alert('您的浏览器不支持,请按 Ctrl+D 手动收藏!');
            }
        }
        else if (window.sidebar) {
            window.sidebar.addPanel(title, url, "");
        }
        else {
            alert('您的浏览器不支持,请按 Ctrl+D 手动收藏!');
        }
        return false;
    }

    /*===============
     数量选择
     ===============*/
    var nsIpt = $('.comm-number-selection').find('input');
    var ns = $('.comm-number-selection');
    ns.find('.plus').click(function(){
        var val = nsIpt.val();
        val++;
        nsIpt.val(val);
    });
    ns.find('.sub').click(function(){
        var val = nsIpt.val();
        val--;
        if (val <= 1) val = 1;
        nsIpt.val(val);
    });

    /*===============
     图片上传组件
     option
     url: 接收图片的服务器地址
     ok: function 图片上传成功回调
     ===============*/
    Fache.UI.ImageUploader = function(option){
        var _this = this;
        _this.option = option;
        var modal = new Fache.UI.Modal({
            body: this.getTmpl(),
            btnText: '确定上传',
            close: function(){},
            ok: function(modal){_this.doUpload(modal)}
        });
        _this.modal = modal;
    };
    Fache.UI.ImageUploader.prototype = {
        getTmpl: function(){
            var tmpl = '<form id="_TxbbImageUploader" action="'+ this.option.url +'" style="text-align:center;font-size:12px;" method="post" enctype="multipart/form-data"><h2 style="font-size:20px;margin: 0 auto 1em;">上传图片</h2>';
            tmpl += '<div><input name="upload" type="file"/></div><div style="color:red;">请上传图片格式文件，大小限制10M</div><p class="_j_txbbuploadtext" style="visibility:hidden;color:blue;margin:1em auto 0;">请稍后，正在上传... <span></span></p></form>';
            return tmpl;
        },
        doUpload: function(modal){
            var form = modal.find('form');
            var _this = this;
            form.ajaxForm({
                beforeSend: function() {
                    modal.find('._j_txbbuploadtext').css('visibility', 'visible');
                },
                uploadProgress: function(event, position, total, percentComplete) {
                    var percentVal = percentComplete + '%';
                    modal.find('span').html(percentVal);
                },
                success: function() {
                    var percentVal = '100%';
                    modal.find('._j_txbbuploadtext').css('color','green').html('上传成功！');
                },
                complete: function(xhr) {
                    _this.option.ok && _this.option.ok(xhr, _this.modal);
                }
            });
            form.submit();
        }
    };

    /*===============
     暴露API
     ===============*/
    window.Fache = Fache;
    /*===============
     兼容老版本
     ===============*/
    window.FACHE = Fache;
    /*===============
     整站通用
     ===============*/
    window.onload = function(){
        $('.comm-header').find('.left > a:eq(1)').click(Fache.addFavorite);
    }
}());
