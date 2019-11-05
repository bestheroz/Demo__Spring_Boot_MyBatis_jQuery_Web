;
const MyMaxlength = {
    init: (targetObj) => {
        targetObj = targetObj || $(document);
        $(targetObj).find('[maxlength]').each((index, element) => {
            $(element).maxlength({
                threshold: $(element).attr('maxlength') - 1,
                validate: true
            });
        });
    }
};
jQuery(($) => {
    MyMaxlength.init();
});