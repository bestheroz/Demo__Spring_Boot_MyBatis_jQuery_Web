;
const MyHandlebars = {
    templates: Object.create(null);,
    setTemplateScript: (targetObj, isAppendMode) => {
        if (!isAppendMode) {
            MyHandlebars.templates = Object.create(null);;
        }
        targetObj = targetObj || $(document);

        $(targetObj).find('script[type="text/x-handlebars-template"]').each((index, element) => {
            if (MyCommon.isNotEmpty($(element).attr('id'))) {
                if (isAppendMode && MyHandlebars.templates[$(element).attr('id')]) {
                    console.warn('이미 존재하는 templates 값입니다 ==> ' + $(element).attr('id'));
                    return;
                }
                MyHandlebars.templates[$(element).attr('id')] = Handlebars.compile($(element).html());
            }
        });
    },
    removeTemplateScript: (targetObj) => {
        targetObj = targetObj || $(document);
        $(targetObj).find('script[type="text/x-handlebars-template"]').each((index, element) => {
            if (MyCommon.isNotEmpty($(element).attr('id'))) {
                delete MyHandlebars.templates[$(element).attr('id')];
            }
        });
    },
    drawDynamicHtml: (targetObj, mode, template, data, options) => {
        return $.when().done(() => {
            if (typeof MyHandlebars.templates[template] === 'function') {
                if (mode === 'html') {
                    $(targetObj).html('').html(MyHandlebars.templates[template](data));
                } else if (mode === 'append') {
                    $(targetObj).append(MyHandlebars.templates[template](data));
                } else if (mode === 'appendTo') {
                    $(targetObj).appendTo(MyHandlebars.templates[template](data));
                } else if (mode === 'prepend') {
                    $(targetObj).prepend(MyHandlebars.templates[template](data));
                } else if (mode === 'prependTo') {
                    $(targetObj).prependTo(MyHandlebars.templates[template](data));
                } else if (mode === 'after') {
                    $(targetObj).after(MyHandlebars.templates[template](data));
                } else if (mode === 'before') {
                    $(targetObj).before(MyHandlebars.templates[template](data));
                } else if (mode === 'text') {
                    $(targetObj).text(MyHandlebars.templates[template](data));
                }
            } else {
                console.warn('handlebars 잘못된 template 값 에러 ==> ' + template);
                console.warn('등록된 templates ==> ' + JSON.stringify(MyHandlebars.templates));
            }
        }).done(() => {
            MyAjax.initView(targetObj);
        });
    },
    init: () => {
        Handlebars.registerHelper({
            // if 모음
            if_eq: function (v1, v2, options) {
                if (v1 === v2) {
                    return options.fn(this);
                }
                return options.inverse(this);
            },
            if_empty: function (value, options) {
                if (MyCommon.isEmpty(value)) {
                    return options.fn(this);
                }
                return options.inverse(this);
            },
            if_not_empty: function (value, options) {
                if (MyCommon.isNotEmpty(value)) {
                    return options.fn(this);
                }
                return options.inverse(this);
            },
            if_size: function (v1, v2, options) {
                if (v1 > v2) {
                    return options.fn(this);
                }
                return options.inverse(this);
            },
            if_lt: function (v1, v2, options) {
                if (v1 < v2) {
                    return options.fn(this);
                }
                return options.inverse(this);
            },
            if_gt: function (v1, v2, options) {
                if (v1 > v2) {
                    return options.fn(this);
                }
                return options.inverse(this);
            },
            if_lte: function (v1, v2, options) {
                if (v1 <= v2) {
                    return options.fn(this);
                }
                return options.inverse(this);
            },
            if_gte: function (v1, v2, options) {
                if (v1 >= v2) {
                    return options.fn(this);
                }
                return options.inverse(this);
            },
            if_and: function (v1, v2, options) {
                if (v1 && v2) {
                    return options.fn(this);
                }
                return options.inverse(this);
            },
            if_or: function (v1, v2, options) {
                if (v1 || v2) {
                    return options.fn(this);
                }
                return options.inverse(this);
            },
            if_contains: function (v1, v2, options) {
                if (!v1 ? false : !!~v1.indexOf(v2)) {
                    return options.fn(this);
                }
                return options.inverse(this);
            },
            if_notContains: function (v1, v2, options) {
                if (!v1 ? false : !!~~v1.indexOf(v2)) {
                    return options.fn(this);
                }
                return options.inverse(this);
            },

            // String 처리 모음
            substring: (str, beginIndex, endIndex) => {
                if (MyCommon.isEmpty(str)) {
                    return '';
                }
                return str.substring(beginIndex, endIndex);
            },
            replace: (str, beforeStr, afterStr) => {
                if (MyCommon.isEmpty(str)) {
                    return '';
                }
                return str.split(beforeStr).join(afterStr);
            },
            unescapeXss: (str) => {
                return MyCommon.unescapeXss(str);
            },
            unescapeHtml: (str) => {
                return new Handlebars.SafeString(MyCommon.unescapeXss(str));
            },
            thousandComma: (number) => {
                if (MyCommon.isNotEmpty(number) && _.isNumber(number)) {
                    return $.number(number);
                }
                return number;
            },
            getYYYYMMDD: (longValue) => {
                if (MyCommon.isEmpty(longValue)) {
                    return '';
                }
                return moment(longValue).format('YYYY-MM-DD');
            },
            getYYYYMMDDHHMM: (longValue) => {
                if (MyCommon.isEmpty(longValue)) {
                    return '';
                }
                return moment(longValue).format('YYYY-MM-DD HH:mm');
            },
            getYYYYMMDDHHMMSS: (longValue) => {
                if (MyCommon.isEmpty(longValue)) {
                    return '';
                }
                return moment(longValue).format('YYYY-MM-DD HH:mm:ss');
            },
            formatDate: (longValue, pattern) => {
                if (MyCommon.isEmpty(longValue)) {
                    return '';
                }
                return moment(longValue).format(pattern);
            },
            getMobileTelFull: (str) => {
                if (MyCommon.isEmpty(str)) {
                    return '';
                }

                let mobile = str.split('-').join('');
                if (mobile.length === 7) {
                    mobile = mobile.substring(0, 3) + '-' + mobile.substring(3, 7);
                } else if (mobile.length === 8) {
                    mobile = mobile.substring(0, 4) + '-' + mobile.substring(4, 8);
                } else if (mobile.length === 9) {
                    if (mobile.substring(0, 2) === '02') {
                        mobile = mobile.substring(0, 2) + '-' + mobile.substring(2, 5) + '-' + mobile.substring(5, 9);
                    } else {
                        mobile = mobile.substring(0, 3) + '-' + mobile.substring(3, 5) + '-' + mobile.substring(5, 9);
                    }
                } else if (mobile.length === 10) {
                    if (mobile.substring(0, 2) === '02') {
                        mobile = mobile.substring(0, 2) + '-' + mobile.substring(2, 6) + '-' + mobile.substring(6, 10);
                    } else {
                        mobile = mobile.substring(0, 3) + '-' + mobile.substring(3, 6) + '-' + mobile.substring(6, 10);
                    }
                } else if (mobile.length === 11) {
                    mobile = mobile.substring(0, 3) + '-' + mobile.substring(3, 7) + '-' + mobile.substring(7, 11);
                }
                return mobile;
            },

            // index + 1 을 사용할때
            inc: (value, options) => {
                return parseInt(value) + 1;
            }
        });

    }
};
jQuery(($) => {
    MyHandlebars.init();
    MyHandlebars.setTemplateScript();
});
