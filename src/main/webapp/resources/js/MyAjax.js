;
const MyAjax = {
    defaultOptions: {
        type: 'POST',
        async: true,
        cache: false,
        dataType: 'json',
        contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
        successMessage: undefined,
        autoResultFunctionTF: false,
        // autoAjaxErrorFunctionTF : 오픈 후 false로... true는 개발용
        autoAjaxErrorFunctionTF: true,
        // 아래는 getSelectOptions() 옵션
        placeholder: '선택하세요.',
        withCodeTF: false,
        value: 'value',
        label: 'label'
    },

    execute: (url, data, options) => {
        const chk = MyAjax.checkMaxPostSize(data);
        if (chk !== null) {
            return chk;
        }
        let promise;
        Pace.track(() => {
            Pace.restart();
            const ajaxOptions = MyCommon.getOptions(MyAjax.defaultOptions, options);
            ajaxOptions['url'] = url;
            ajaxOptions['data'] = data;
            promise = $.ajax(ajaxOptions).done((response) => {
                MyAjax.doneDefaultFunction(response, ajaxOptions);
            }).done((response) => {
                MyAjax.doneAutoResultFunction(response, ajaxOptions);
            }).fail((jqXHR, textStatus, errorThrown) => {
                MyAjax.failFunction(jqXHR, textStatus, errorThrown, ajaxOptions);
            });
        });
        return promise;
    },
    executeWithFile: (url, paramFormData, options) => {
        const chk = MyAjax.checkMaxPostSize(paramFormData);
        if (chk !== null) {
            return chk;
        }
        let promise;
        Pace.track(() => {
            Pace.restart();
            const ajaxOptions = MyCommon.getOptions(MyAjax.defaultOptions, options);
            ajaxOptions['url'] = url;
            ajaxOptions['data'] = paramFormData;
            ajaxOptions['processData'] = false;
            ajaxOptions['contentType'] = false;
            promise = $.ajax(ajaxOptions).done((response) => {
                MyAjax.doneDefaultFunction(response, ajaxOptions);
            }).done((response) => {
                MyAjax.doneAutoResultFunction(response, ajaxOptions);
            }).fail((jqXHR, textStatus, errorThrown) => {
                MyAjax.failFunction(jqXHR, textStatus, errorThrown, ajaxOptions);
            });
        });
        return promise;
    },

    getSelectOptions: (paramTargetObj, url, data, options) => {
        const chk = MyAjax.checkMaxPostSize(data);
        if (chk !== null) {
            return chk;
        }
        let promise;
        Pace.track(() => {
            Pace.restart();
            const ajaxOptions = MyCommon.getOptions(MyAjax.defaultOptions, options);
            ajaxOptions['url'] = url;
            ajaxOptions['data'] = data;
            promise = $.ajax(ajaxOptions).done((response) => {
                MyAjax.doneDefaultFunction(response, ajaxOptions)
            }).done((response) => {
                $(paramTargetObj).html('');
                if (!$(paramTargetObj).prop('required') || (MyCommon.isNotEmpty(ajaxOptions) && MyCommon.isNotEmpty(ajaxOptions.placeholder))) {
                    $(paramTargetObj).append("<option value=''>" + ajaxOptions.placeholder + "</option>");
                }
                $.each(response, (index, value) => {
                    if (ajaxOptions.withCodeTF) {
                        $(paramTargetObj).append("<option value='" + value[ajaxOptions.value] + "'>" + "[" + value[ajaxOptions.value] + "]" + value[ajaxOptions.label] + "</option>");
                    } else {
                        $(paramTargetObj).append("<option value='" + value[ajaxOptions.value] + "'>" + value[ajaxOptions.label] + "</option>");
                    }
                });
            }).fail((jqXHR, textStatus, errorThrown) => {
                MyAjax.failFunction(jqXHR, textStatus, errorThrown, ajaxOptions);
            });
        });
        return promise;
    },
    downloadFile: (url, data, options) => {
        const chk = MyAjax.checkMaxPostSize(data);
        if (chk !== null) {
            return chk;
        }
        let promise;
        Pace.track(() => {
            Pace.restart();
            const ajaxOptions = MyCommon.getOptions(MyAjax.defaultOptions, options);
            promise = $.fileDownload(url, {
                httpMethod: ajaxOptions.type,
                data: data,
                prepareCallback: (url) => {
                    //
                }
            }).fail((responseHtml, url, error) => {
                console.warn("responseHtml : " + responseHtml + "\nurl : " + url + "\nerror : " + error);
                const response = MyAjax.getJsonFromWrappedInPreTag(responseHtml);
                if (MyCommon.isNotEmpty(response.message)) {
                    alert(response.message);
                } else {
                    alert('파일이 존재하지 않습니다.');
                }
            });
        });
        return promise;
    },
    checkMaxPostSize: (data) => {
        if (MyCommon.isNotEmpty(data) && JSON.stringify(data).length > 8388608) { // 현재8MB : server.xml maxPoolSize값 참고
            alert('입력하신 값이 너무 많아서 문제가 발생하고 있습니다. 입력하신 값을 줄여주세요.');
            return $.Deferred().reject('입력하신 값이 너무 많아서 문제가 발생하고 있습니다. 입력하신 값을 줄여주세요.').promise();
        } else {
            return null;
        }
    },
    // 가끔 ajax 에러발생시 <pre>를 포함한 데이터가 넘어올 때가 있다.
    getJsonFromWrappedInPreTag: (paramHtml) => {
        if (!!~paramHtml.toString().indexOf('<pre ') || !!~paramHtml.toString().indexOf('<pre>')) {
            try {
                return JSON.parse(paramHtml.substring(paramHtml.indexOf('>') + 1, paramHtml.indexOf('</')));
            } catch (e) {
                console.warn(e);
            }
        }
        return paramHtml;
    },
    doneDefaultFunction: (response, options) => {
        if (options.dataType === 'json') {
            response = MyAjax.getJsonFromWrappedInPreTag(response);
        }
        if (response.code === 'E011') {
            if (confirm("페이지 시간초과\n페이지를 새로고침하여 로그인페이지로 안내합니다.")) {
                window.location.reload();
            }
            return false;
        }
    },
    doneAutoResultFunction: (response, options) => {
        if (options.autoResultFunctionTF) {
            if (MyCommon.isNotEmpty(response.code) && MyCommon.isNotEmpty(response.message)) {
                let message = null;
                if (MyCommon.isNotEmpty(options.successMessage) && _.startsWith(response.code, 'S')) {
                    message = options.successMessage;
                } else {
                    message = response.message;
                }
                alert(message);
            }
        }
    },
    failFunction: (jqXHR, textStatus, errorThrown, options) => {
        console.warn("jqXHR : " + jqXHR + "\ntextStatus : " + textStatus + "\nerrorThrown : " + errorThrown);
        if (textStatus === "timeout") {
            console.warn("요청시간 초과");
            if (options.autoAjaxErrorFunctionTF) {
                alert("요청시간 초과");
            }
        } else {
            console.warn("시스템에러: return null 또는 HTTP 500 ");
            if (options.autoAjaxErrorFunctionTF) {
                alert("잠시후 다시 시도해주세요.");
            }
        }
    },
    drawHtml: (targetObj, mode, html) => {
        return $.when().done(() => {
            if (mode === 'html') {
                MyHandlebars.removeTemplateScript(targetObj);
                $(targetObj).html('').html(html);
            } else if (mode === 'append') {
                $(targetObj).append(html);
            } else if (mode === 'appendTo') {
                $(targetObj).appendTo(html);
            } else if (mode === 'prepend') {
                $(targetObj).prepend(html);
            } else if (mode === 'prependTo') {
                $(targetObj).prependTo(html);
            } else if (mode === 'after') {
                $(targetObj).after(html);
            } else if (mode === 'before') {
                $(targetObj).before(html);
            }
        }).done(() => {
            MyAjax.initView(targetObj);
            MyAjax.removeDuplicationModal(targetObj);
        });
    },
    initView: (targetObj) => {
        targetObj = targetObj || $(document);

        // Handlebars
        try {
            MyHandlebars.setTemplateScript(targetObj, true);
        } catch (e) {
            // console.warn(e);
        }
        // MaxLength
        try {
            MyMaxlength.init($(targetObj));
        } catch (e) {
            // console.warn(e);
        }
        // MyDatetimePicker
        try {
            MyDatetimePicker.init($(targetObj));
        } catch (e) {
            // console.warn(e);
        }
        // MyTextEditor
        try {
            MyTextEditor.init($(targetObj));
        } catch (e) {
            // console.warn(e);
        }
    },
    removeDuplicationModal: (targetObj) => {
        if (!targetObj) {
            return;
        }
        // ajax를 통해 가져온 (기존)중복된 Model 제거
        try {
            $.each($(targetObj).find('.modal'), (index, value) => {
                if ($('body > #' + $(value).prop('id')) !== $(value)) {
                    $('body > #' + $(value).prop('id')).remove();
                }
            });
        } catch (e) {
            console.warn(e);
        }
    }
};
