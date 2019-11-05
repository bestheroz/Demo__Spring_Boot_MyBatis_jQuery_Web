;
const MyKeyup = {
    isNumber: (e, obj) => {
        if (MyCommon.isNotEmpty(obj) && MyCommon.isNotEmpty(obj.value) && !_.isNumber(obj.value)) {
            const evt = e || window.e;
            obj.value = obj.value.replace(/[^0-9]/g, '');
            evt.preventDefault ? evt.preventDefault() : (evt.returnValue = false);
            alert('숫자만 입력해주세요.');
        }
    }
};
// 포커스가 text readonly 속성의 input에 있을 경우 백스페이스 불가
$(document).off('keydown', 'input').on('keydown', 'input', (e) => {
    if (e.key === 'Backspace') {
        let doPrevent = true;
        const types = ["text", "password", "file", "search", "email", "number", "date", "color", "datetime", "datetime-local", "month", "range", "search", "tel", "time", "url", "week"];
        const d = $(e.srcElement || e.target);
        const disabled = d.prop("readonly") || d.prop("disabled");
        if (!disabled) {
            const type = d.attr("type");
            if (MyCommon.isNotEmpty(type)) {
                if (_.includes(types, type.toLowerCase())) {
                    doPrevent = false;
                }
            }
        }
        if (doPrevent) {
            e.preventDefault();
            return false;
        }
    } else if (e.key === 'Enter') {
        e.preventDefault();
        return false;
    }
});
$(document).off('keydown', 'textarea').on('keydown', 'textarea', (e) => {
    if (e.key === 'Backspace') {
        let doPrevent = true;
        const d = $(e.srcElement || e.target);
        const disabled = d.prop("readonly") || d.prop("disabled");
        if (!disabled) {
            if (d.is("textarea")) {
                doPrevent = false;
            }
        }
        if (doPrevent) {
            e.preventDefault();
            return false;
        }
    }
});