window.Fache = {UI: {}};

(function () {
    'use strict';

    var body = $('body');
    var ua = window.navigator.userAgent;
    if (ua.match(/Android/i)
        || ua.match(/webOS/i)
        || ua.match(/iPhone/i)
        || ua.match(/iPad/i)
        || ua.match(/iPod/i)
        || ua.match(/BlackBerry/i)
        || ua.match(/Windows Phone/i)
    ) {
        Fache.mainEvent = 'tap';
    }
    else {
        Fache.mainEvent = 'click';
    }

    /*===================
     选择组件
     ===================*/
    var selectorDefault = {
        title: '请选择'
    };
    Fache.UI.Selector = function (dom, option) {
        this.dom = dom;
        this.option = $.extend({}, selectorDefault, option);
        if (dom.data('single') == 1) {
            this.single = true;
        } else {
            this.single = false;
        }
        this.getCurrent();
        this.getOptions();
        this.render();
        this.style();
        this.eventBind();
    };

    Fache.UI.Selector.prototype = {
        getOptions: function () {
            var options = {};
            var _this = this;
            _this.dom.find('.item').each(function () {
                var _ = $(this);
                options[_.data('key')] = {
                    key: _.data('key'),
                    value: _.data('value'),
                    checked: _this.value.indexOf(_.data('key') + "") > -1
                };
            });
            _this.options = options;
        },
        getCurrent: function () {
            this.value = this.dom.find('input').val().split(',');
        },
        render: function () {
            var tmpl = '<div id="J-CommSelectorOverlay" class="comm-selector-overlay">';
            tmpl += '  <div class="inner">';
            tmpl += '    <div class="header">' + this.option.title + '</div>';
            for (var k in this.options) {
                var item = this.options[k];
                if (item.checked) {
                    tmpl += '<div class="item active" id="J-CSOItem' + item.key + '" data-key="' + item.key + '">' + item.value + '</div>';
                } else {
                    tmpl += '<div class="item" id="J-CSOItem' + item.key + '" data-key="' + item.key + '">' + item.value + '</div>';
                }
            }
            tmpl += '  </div>';
            tmpl += '</div>';

            $('body').append(tmpl);
        },
        eventBind: function () {
            var wrap = $('#J-CommSelectorOverlay');
            var _this = this;
            wrap.on(Fache.mainEvent, function (e) {
                var tar = $(e.target);
                if (tar.hasClass('comm-selector-overlay')) {
                    _this.update();
                    wrap.remove();
                } else if (tar.hasClass('item')) {
                    var key = tar.data('key');
                    if (tar.hasClass('active') && !_this.single) {
                        _this.options[key].checked = false;
                    } else if (!tar.hasClass('active')) {
                        _this.options[key].checked = true;
                        if (_this.single) {
                            tar.siblings('.item').each(function () {
                                var _key = $(this).data('key');
                                _this.options[_key].checked = false;
                            });
                            setTimeout(function(){
                                wrap.remove();
                            }, 300);
                        }
                    }
                }
                _this.update();
                e.preventDefault();
                e.stopPropagation();
            });
        },
        update: function () {
            var value = [];
            var text = [];
            var selected = [];
            for (var key in this.options) {
                var item = this.options[key];
                if (item.checked) {
                    $('#J-CSOItem' + item.key).addClass('active');
                    value.push(item.key);
                    text.push(item.value);
                    selected.push(item);
                } else {
                    $('#J-CSOItem' + item.key).removeClass('active');
                }
            }
            this.value = value;
            this.dom.find('input').val(this.value.join(','));
            if (value.length) {
                this.dom.find('.value').html(text.join(','));
            } else {
                this.dom.find('.value').html('选择');
            }
            var callbackName = this.dom.data('onchange');
            if (callbackName && window[callbackName]) {
                window[callbackName].call(window, selected);
            }
        },
        style: function() {
            var overlay = $('#J-CommSelectorOverlay');
            var inner = overlay.find('.inner');
            if (inner.height() > $(window).height()) {
                inner.css({'position' : 'static'});
                overlay.css({'overflow' : 'auto'});
            }
        }
    };

    /*====================
     checkbox
     =====================*/
    var checkCheckbox = function () {
        var _this = $(this);
        var checkbox = _this.find('input');
        if (checkbox.is(':checked')) {
            _this.addClass('active');
        } else {
            _this.removeClass('active');
        }
    };

    Fache.UI.checkbox = function () {
        $('.checkbox').each(checkCheckbox);
        $('.checkbox').on('click', checkCheckbox);
    };

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
    Fache.UI.Modal = function (option) {
        this.option = option;
        if (this.option.id) {
            this.modal = $('#' + option.id);
            this.bind();
        } else {
            this.init();
        }
    };
    Fache.UI.Modal.prototype = {
        init: function () {
            var tmpl = '<div id="_Modal" class="comm-modal">';
            tmpl += '<div class="modal">';
            tmpl += '    <div class="close j-close">&times;</div>';
            if (this.option.type === 'warn') {
                tmpl += '    <div class="type"><i class="comm-gray-warn"></i></div>';
            } else if (this.option.type === 'right') {
                tmpl += '    <div class="type"><i class="comm-gray-tick"></i></div>';
            }
            tmpl += '    <div class="text">' + this.option.body + '</div>';
            tmpl += '    <div class="action">';
            tmpl += '        <div class="button button-primary j-ok">' + this.option.btnText + '</div>';
            tmpl += '    </div>';
            tmpl += '    </div>';
            tmpl += '</div>';
            body.append(tmpl);
            this.modal = $('#_Modal');
            this.bind();
        },
        bind: function () {
            var _this = this;
            this.modal.find('.j-close').on(Fache.mainEvent, function () {
                _this.option.close && _this.option.close.call(_this);
                if (_this.option.id) {
                    _this.modal.hide();
                } else {
                    _this.modal.remove();
                }
            });
            this.modal.find('.j-ok').on(Fache.mainEvent, function () {
                if (_this.option.ok)
                    _this.option.ok.call(_this, _this.modal);
                else {
                    if (_this.option.id) {
                        _this.modal.hide();
                    } else {
                        _this.modal.remove();
                    }
                }
            });
        },
        show: function () {
            this.modal.show();
        },
        close: function () {
            if (this.option.id) this.modal.hide();
            else this.modal.remove();
        }
    };

    /*====================
     alert
     =====================*/
    Fache.UI.alert = function (msg) {
        alert(msg);
    };

    /*====================
     comm-search-form
     =====================*/
    var searchForm = $('.comm-search-form');
    searchForm.append('<span class="j-search" style="display:none;position:absolute;top:1rem;right:0;padding:0 2rem;line-height: 3.2rem;color:#cd5c5c;z-index:2;">搜索</span>');
    $('.comm-search-form').find('input').on('focus', function () {
        searchForm.find('.placeholder').hide();
        searchForm.find('.j-search').show();
    }).on('blur', function () {
        if (!$(this).val()) {
            setTimeout(function(){
                searchForm.find('.j-search').hide();
            }, 200);
            searchForm.find('.placeholder').show();
        }
    });
    searchForm.find('.j-search').on(Fache.mainEvent, function(){
        searchForm.find('form').submit();
    });

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
                regexp: new RegExp('([0-9]{15})|([0-9]{17}[0-9]|X)', 'g'),
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
                regexp: new RegExp('^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$', 'g'),
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
    Fache.UI.Form = function (selector) {
        this.form = $(selector);
        this.bindEvent();
    };
    Fache.UI.Form.prototype = {
        bindEvent: function () {
            var _this = this;
            _this.form.find('input').focus(function () {
                $(this).prev('.placeholder').hide();
            }).blur(function () {
                var _ = $(this);
                var val = _.val();
                var rule = _.data('validate');
                if (!val) {
                    _.prev('.placeholder').show();
                } else {
                    _.prev('.placeholder').hide();
                }
                if (rule) {
                    var result = _this.validateField(rule, val);
                    if (result !== true) _this.renderErrorField(_, result);
                    else _this.renderSuccessField(_);
                }
            });

            this.form.on('submit', function () {
                return _this.validate();
            });
        },
        validateField: function (rule, value) {
            var rule = validateRules[rule]();
            if (!rule.regexp.test(value)) return rule.msg;
            else return true;
        },
        renderErrorField: function (input, msg) {
            if (input.parent().find('.comm-form-error-tip').length) return;
            input.parent().append('<span class="comm-form-error-tip" style="color:red;position:absolute;top: 0;width:auto;right:10px;padding-left:10px;font-size:12px;line-height: 3.8;">' + msg + '</span>');
        },
        renderSuccessField: function (input) {
            input.parent().find('.comm-form-error-tip').remove();
        },
        validate: function() {
            var elems = this.form.find('[data-validate]');
            var rightSize = 0;
            var _this = this;
            elems.each(function(){
                var _ = $(this);
                var val = _.val();
                var rule = _.data('validate');
                var result = _this.validateField(rule, val);
                if (result !== true) {
                    _this.renderErrorField(_, result);
                } else {
                    _this.renderSuccessField(_);
                    rightSize++;
                }
            });
            return rightSize >= elems.length;
        }
    };

    /*====================
     筛选条件
     =====================*/
    var Fp = function () {
        this.dom = $('#J-FPanel');
        this.eventBind();
    };
    var fp;
    Fp.prototype = {
        show: function () {
            var _this = this;
            _this.dom.css('display', 'block');
            setTimeout(function () {
                _this.dom.addClass('active');
                _this.dom.find('.inner').addClass('active');
            }, 16);
        },
        eventBind: function () {
            var _this = this;
            var header = this.dom.find('.j-header0');
            var selectors = this.dom.find('.comm-selector');
            header.children('.left').on(Fache.mainEvent, function () {
                _this.hide();
            });
            header.children('.right').on(Fache.mainEvent, function () {
                _this.hide();
            });
            selectors.on(Fache.mainEvent, function () {
                var items = {};
                var _ = $(this);
                var title = _.parents('.form-group').find('.label').html();
                var keyVal = _.find('input').val();
                _.find('.item').each(function () {
                    var _item = $(this);
                    var key = _item.data('key');
                    var val = _item.data('value');
                    items[key] = {
                        value: val,
                        checked: key == keyVal
                    };
                });
                _this.showSubPanel({
                    selector: _,
                    title: title,
                    key: keyVal,
                    items: items,
                    ok: function (key) {
                        console.debug(key);
                        _.find('input').val(key);
                        _.find('.value').html(items[key].value);

                        var callbackName = _.data('onchange');
                        if (callbackName && window[callbackName]) {
                            window[callbackName].call(window, key);
                        }
                    }
                });
            });
            this.dom.on(Fache.mainEvent, function(e) {
                var tar = $(e.target);
                if (tar.hasClass('mod-buy-fpanel')) _this.hide();
            });
        },
        hide: function () {
            var _this = this;
            _this.dom.removeClass('active');
            _this.dom.find('.inner').removeClass('active');
            _this.hideSubPanel && _this.hideSubPanel();
            setTimeout(function () {
                _this.dom.css('display', 'none');
            }, 400);
        },
        showSubPanel: function (option) {
            var _this = this;
            var tmpl = '';
            var checkedKey;
            tmpl += '<div id="J-SubPanel" class="inner">';
            tmpl += '   <div class="header"><div class="left">取消</div><div class="right">确定</div><div class="title">' + option.title + '</div></div>';
            tmpl += '     <div class="comm-form">';
            for (var key in option.items) {
                var item = option.items[key];
                if (item.checked) {
                    checkedKey = key;
                    tmpl += '<div class="form-group mod-buy-tick" data-key="' + key + '"><span class="label">' + option.items[key].value + '</span><div class="input">&nbsp;</div></div>';
                } else {
                    tmpl += '<div class="form-group" data-key="' + key + '"><span class="label">' + option.items[key].value + '</span><div class="input">&nbsp;</div></div>';
                }
            }
            tmpl += '    </div>';
            tmpl += '</div>';

            this.dom.append(tmpl);

            var subPanel = $('#J-SubPanel');
            subPanel.css('display', 'block');
            setTimeout(function () {
                subPanel.addClass('active');
            }, 16);

            _this.hideSubPanel = function () {
                subPanel.removeClass('active');
                setTimeout(function () {
                    subPanel.remove();
                }, 400);
            }

            subPanel.find('.left').on(Fache.mainEvent, _this.hideSubPanel);
            subPanel.find('.right').on(Fache.mainEvent, function () {
                option.ok(checkedKey);
                _this.hideSubPanel();
            });

            subPanel.find('.form-group').on(Fache.mainEvent, function () {
                var _ = $(this);
                _.addClass('mod-buy-tick')
                    .siblings().removeClass('mod-buy-tick');
                for (var key in option.items) {
                    if (key == _.data('key')) {
                        checkedKey = key;
                        option.items[key].checked = true;
                    } else {
                        option.items[key].checked = false;
                    }
                }
            });
        }
    };
    Fache.UI.FilterPanel = {
        show: function () {
            if (!fp) fp = new Fp();
            fp.show();
        }
    };

    /*===============
     图片上传组件
     option
     url: 接收图片的服务器地址
     ok: function 图片上传成功回调
     ===============*/
    Fache.UI.ImageUploader = function (option) {
        var _this = this;
        _this.option = option;
        var modal = new Fache.UI.Modal({
            body: this.getTmpl(),
            btnText: '确定上传',
            close: function () {
            },
            ok: function () {
                _this.doUpload(modal)
            }
        });
        _this.modal = modal;
    };
    Fache.UI.ImageUploader.prototype = {
        getTmpl: function () {
            var tmpl = '<form id="_TxbbImageUploader" action="' + this.option.url + '" style="text-align:center;font-size:12px;" method="post" enctype="multipart/form-data"><h2 style="font-size:20px;margin: 0 auto 1em;">上传图片</h2>';
            tmpl += '<div><input name="upload" type="file"/></div><div style="color:red;">请上传图片格式文件，大小限制3M</div><p class="_j_txbbuploadtext" style="visibility:hidden;color:blue;margin:1em auto 0;">请稍后，正在上传... <span></span></p></form>';
            return tmpl;
        },
        doUpload: function (m) {
            var _this = this;
            var form = m.modal.find('form');
            var fileElem = form.find('input[type=file]')[0];
            if (!fileElem.files.length) {
                alert('请先选择文件');
                return;
            }

            $('#_TxbbImageUploader').find('._j_txbbuploadtext').css('visibility', 'visible');

            var file = fileElem.files[0];

            var formData = new FormData();
            formData.append('upload', file);

            var request = new XMLHttpRequest();
            request.onload = function (e) {
                if (request.status == 200) {
                    $('#_TxbbImageUploader').find('._j_txbbuploadtext').css('color', 'green').html('上传成功!');
                    _this.option.ok && _this.option.ok.call(_this, request, m);
                } else {
                    _this.option.error && _this.option.error.call(_this, request, m);
                }
            };
            request.open('POST', this.option.url);
            request.send(formData);
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
        'N', 'O', 'P', 'Q', 'I', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
    var alphaChooseDefault = {
        domId: 'J-AlphaChoose',
        alphaDomClass: 'j-alphas',
        brandsDomClass: 'j-brands',
        ok: function () {
        },
        url: null
    };
    Fache.UI.AlphaChoose = function (option) {
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
        bindEvent: function () {
            var _this = this;
            _this.alphas.on('click', 'span', function () {
                var _span = $(this);
                _span.addClass('active').siblings().removeClass('active');
                _this.doRequest(_span.html());
            });
            _this.brands.on('click', 'span', function () {
                var _span = $(this);
                _span.addClass('active').siblings().removeClass('active');
                _this.option.ok(_span.html());
            });
        },
        render: function () {
            var tmpl = '';
            for (var i in alphas) {
                // if (parseInt(i) === 0)
                // tmpl += '<span class="active">'+ alphas[i] +'</span>';
                // else
                tmpl += '<span>' + alphas[i] + '</span>';
            }
            this.alphas.html(tmpl);
        },
        doRequest: function (alpha) {
            if (!this.option.url) throw new Error('[Fache.UI.AlphaChoose]: 没有指定url参数');
            $.get(this.option.url, {"brandForm.pinyin": alpha}, this.option.onBrandsReceive);
        }
    };

    /*====================
     发送短信校验码
     option
       url: null(default)
       btnId: J-GetPhoneCode(default)
       countDown: 120(default)
       name: mobile(default)
     =====================*/
    Fache.PhoneCode = function(option){
        option = $.extend({
            url: null,
            btnId: 'J-GetPhoneCode',
            countDown: 120,
            name: 'mobile',
            success: function(){}
        }, option);

        var btn = $('#' + option.btnId);
        var mobileElem = $(btn.data('ref'));

        btn.on(Fache.mainEvent, function(){
            var data = {};
            var count = option.countDown;
            data[option.name] = mobileElem.val();

            if (btn.data('counting')) return;
            btn.data('counting', true);

            btn.css({'background-color' : 'gray', 'border-color' : 'gray'});
            btn.data('origin', btn.html());

            $.get(option.url, data, function(resp){
                if (!option.success.call(null, resp)) {
                    btn.css({'background-color' : '', 'border-color' : ''})
                        .data('counting', false)
                        .html(btn.data('origin'));
                    return;
                }
                Fache.PhoneCode.timing = setInterval(function(){
                    btn.html(--count);
                    if (count <= 0) {
                        btn.css({'background-color' : '', 'border-color' : ''})
                            .data('counting', false)
                            .html(btn.data('origin'));
                        clearInterval(Fache.PhoneCode.timing);
                    }
                }, 1000);
            }, 'json');
        });
    };

    /*=====================
     加载更多逻辑
    =====================*/
    Fache.bindLoadMore = function() {
        var trigger = $('.comm-loadmore');
        if (!trigger.length) return;

        var listId = trigger.data('list-id');
        var size = trigger.data('size');
        var url = trigger.data('url');

        var loading = false;
        
        var _ctn = $("#" + listId);
        if (_ctn.children().length < size) {
            trigger.html('没有更多了');
            loading = true;
        }

        trigger.on(Fache.mainEvent, function() {
            var offset = trigger.data('offset');
            
            if (loading) return;
            loading = true;
            trigger.html('加载中...');

            setTimeout(function(){
                $.get(url, {'page': offset + 1}, function(resp){
                    var htmlDiv=$('<div></div>');
                    htmlDiv.html(resp);
                    var ctn = htmlDiv.find("#" + listId);
                    $('#' + listId).append(ctn.prop("outerHTML"));
                    trigger.data('offset', offset + 1);
                    if (ctn.children().length < size) {
                        trigger.html('没有更多了');
                    } else {
                        trigger.html('点击加载更多');
                        loading = false;
                    }
                });
            }, 200);
        });
    };

    /*===============
     数量选择
     ===============*/
    var nsIpt = $('.comm-number-selection').find('input');
    var ns = $('.comm-number-selection');
    ns.find('.plus').on(Fache.mainEvent, function(){
        var val = nsIpt.val();
        val++;
        nsIpt.val(val);
    });
    ns.find('.sub').on(Fache.mainEvent, function(){
        var val = nsIpt.val();
        val--;
        if (val <= 1) val = 1;
        nsIpt.val(val);
    });
}());
