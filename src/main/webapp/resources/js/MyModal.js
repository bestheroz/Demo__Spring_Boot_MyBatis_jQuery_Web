;
const MyModal = {
    open: (paramObj) => {
        return $(paramObj).modal({
            backdrop: 'static',
            show: true
        });
    },
    close: (paramObj) => {
        $(paramObj).modal('hide');
    }
};