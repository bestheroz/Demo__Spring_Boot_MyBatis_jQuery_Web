;
// noinspection DuplicatedCode
const MyCommon = {
    isEmpty: (obj) => {
        if (obj === undefined || obj === null || obj === '' || obj.length === 0) {
            return true;
        }
        if (typeof obj === 'object') {
            return $.isEmptyObject(obj);
        }
        return false;
    },
    isNotEmpty: (obj) => {
        return !MyCommon.isEmpty(obj);
    },
    // 재요청시 XSS가 중복으로 처리되어 &, # 등의 문자가 중복 필터처리 된다. 때문에 값을 원본으로 변환하여 .text(value), .val(value) 로 처리한다.
    unescapeXss: (input) => {
        return $('<textarea></textarea>').html(input).text();
    },
    defaultLinkOptions: {
        center: 'screen',
        // createNew : true,
        height: $(window).outerHeight() * 0.85,
        left: 0,
        location: false, // 메뉴아이콘 출력
        menubar: false, // 메뉴 바 사용
        name: null,
        onUnload: null,
        resizable: true, // 사이즈 변경 사용
        scrollbars: true, // 스크롤 바 사용
        status: false, // 하단 상태바 출력
        toolbar: false, // 도구창 사용
        top: 0,
        width: $(window).outerWidth() * 0.80
    },
    goLink: (url, isNewWindow, paramOption) => {
        // option은 다음 경로에서 확인 https://github.com/mkdynamic/jquery-popupwindow
        let tempUrl = _.trim(url);
        if (MyCommon.isEmpty(tempUrl) || tempUrl === '#' || tempUrl === 'null') {
            alert('준비중입니다.');
            return;
        }
        if (!_.startsWith(tempUrl, 'http') && !_.startsWith(tempUrl, CONTEXT_PATH)) {
            tempUrl = CONTEXT_PATH + tempUrl;
        }

        const options = MyCommon.getOptions(MyCommon.defaultLinkOptions, paramOption);

        // url 앞에 http가 있으면 새창으로 띄운다.
        if (isNewWindow === 'L') { // 이동
            location.href = tempUrl;
        } else if (isNewWindow === 'W') { // 팝업
            $.popupWindow(tempUrl, options);
        } else if (isNewWindow === 'T') { // 탭
            options['location'] = true;
            options['menubar'] = true;
            options['status'] = true;
            options['toolbar'] = true;
            $.popupWindow(tempUrl, options);
        } else { // default
            if (_.startsWith(tempUrl, 'http')) { // 탭
                options['location'] = true;
                options['menubar'] = true;
                options['status'] = true;
                options['toolbar'] = true;
                $.popupWindow(tempUrl, options);
            } else { // 이동
                location.href = tempUrl;
            }
        }
    },
    goWindow: (url, paramOption) => {
        MyCommon.goLink(url, 'W', paramOption);
    },
    goTab: (url) => {
        MyCommon.goLink(url, 'T');
    },
    getLabelFromSelectTag: (selectObj, value) => {
        return $(selectObj).children('option[value="' + value + '"]').text();
    },
    getLabelFromRadioCheckboxTag: (radioCheckboxObj, value) => {
        let result = "";
        $(radioCheckboxObj).each((index, element) => {
            if ($(element).val() === value) {
                result = $(element).data('label');
                }
            }
        );
        return result;
    },
    // 사용법 <img src="" onerror="MyCommon.setNoImg(this);" />
    setNoImg: (obj, paramnoImgPath) => {
        let noImgPath = _.trim(paramnoImgPath);
        if (MyCommon.isEmpty(noImgPath)) {
            noImgPath = CONTEXT_PATH + '/resources/images/no-image-available.png';
        }
        $(obj).removeAttr('onerror').attr('data-original-src', $(obj).attr('src')).attr('src', noImgPath).addClass('noImg');
    },
    getOptions: (defaultOption, userOptions) => {
        const tempOptions = _.clone(defaultOption);
        if (userOptions) {
            for (const key in userOptions) {
                tempOptions[key] = userOptions[key];
            }
        }
        return tempOptions;
    },
    getFileExtension: (filename) => {
        return filename.slice((filename.lastIndexOf(".") - 1 >>> 0) + 2);
    },
    submitFormGET: (url, param, target) => {
        const $form = $('<form method="GET"></form>');
        $form.attr('action', url);
        target && $form.attr('target', target);
        for (const key in param) {
            $form.append('<input type="hidden" name="' + key + '" value="' + param[key] + '" >');
        }
        $(document.body).append($form[0]);
        $form[0].submit();
    },
    submitFormPOST: (url, param, target) => {
        const $form = $('<form method="POST"></form>');
        $form.attr('action', url);
        target && $form.attr('target', target);
        for (const key in param) {
            $form.append('<input type="hidden" name="' + key + '" value="' + param[key] + '" >');
        }
        $(document.body).append($form[0]);
        $form[0].submit();
    },
    isLocalhost: (url) => {
        url = url || document.domain;
        return _.endsWith(url, "localhost");
    }
};