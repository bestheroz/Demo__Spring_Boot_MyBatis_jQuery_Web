;
const MyDatetimePicker = {
    makeDatetimepicker: (targetObj, format) => {
        if ($(targetObj).length === 0) {
            console.warn('올바르지않은 대상');
        } else {
            MyDatetimePicker.setDefaultAttr(targetObj);
            $(targetObj).datetimepicker('format', format || 'YYYY-MM-DD');
        }
        return $(targetObj);
    },

    setFromTo: (fromObj, toObj, format) => {
        if ($(fromObj).length === 0 || $(toObj).length === 0) {
            console.warn('올바르지않은 대상');
            return;
        }
        MyDatetimePicker.makeDatetimepicker(fromObj, format);
        MyDatetimePicker.makeDatetimepicker(toObj, format);
        $(fromObj).on("change.datetimepicker", (e) => {
            if (_.isEmpty($(fromObj).val())) {
                $(toObj).datetimepicker('minDate', false);
                $(toObj).datetimepicker('useCurrent', false);
            } else {
                $(toObj).datetimepicker('minDate', e.date);
                $(toObj).datetimepicker('useCurrent', false);
            }
        });
        $(toObj).on("change.datetimepicker", (e) => {
            if (_.isEmpty($(toObj).val())) {
                $(fromObj).datetimepicker('maxDate', false);
            } else {
                $(fromObj).datetimepicker('maxDate', e.date);
            }
        });
    },

    // 달력이 모달이나 새로 띄워진 화면에 띄우려면 위치를 지정해줘야한다.
    setDefaultAttr: (targetObj) => {
        if (!$(targetObj).attr('id')) {
            $(targetObj).attr('id', 'datepickerid_' + MyDatetimePicker.guid());
        }
        $(targetObj).addClass('datetimepicker-input').attr('data-toggle', 'datetimepicker').attr('data-target', `#${$(targetObj).attr('id')}`);
    },

    destroy: (targetObj) => {
        if ($(targetObj).length === 0) {
            console.warn('올바르지않은 대상');
            return;
        }
        $(targetObj).datetimepicker().destroy();
    },
    guid: () => {
        function s4() {
            return ((1 + Math.random()) * 0x10000 | 0).toString(16).substring(1);
        }

        return s4() + s4() + '-' + s4() + '-' + s4() + '-' + s4() + '-' + s4() + s4() + s4();
    }
};
$.fn.datetimepicker.Constructor.Default = $.extend({}, $.fn.datetimepicker.Constructor.Default, {
    format: 'YYYY-MM-DD',
    icons: {
        time: 'far fa-clock',
        date: 'far fa-calendar',
        up: 'fas fa-arrow-up',
        down: 'fas fa-arrow-down',
        previous: 'fas fa-chevron-left',
        next: 'fas fa-chevron-right',
        today: 'far fa-calendar-check',
        clear: 'fas fa-trash',
        close: 'fas fa-times'
    },
    buttons: {
        showClear: true,
        showToday: true,
        showClose: false,
    },
    ignoreReadonly: true,
    useCurrent: false,
    locale: moment.locale(),
});