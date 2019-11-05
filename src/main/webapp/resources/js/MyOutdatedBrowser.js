;
const MyOutdatedBrowser = {
    addLoadEvent: (func) => {
        const oldonload = window.onload;
        if (typeof window.onload != '') {
            window.onload = func;
        } else {
            window.onload = () => {
                if (oldonload) {
                    oldonload();
                }
                func();
            }
        }
    }
};
/**
 * <pre>
 * Lower Than (&lt;):
 *
 * IE11, borderImage
 * IE10, transform (Default property)
 * IE9, boxShadow
 * IE8, borderSpacing
 * </pre>
 */
jQuery(($) => {
    $('body').append('<div id=\"outdated\"></div>');
    MyOutdatedBrowser.addLoadEvent(() => {
        outdatedBrowser({
            bgColor: '#f25648',
            color: '#ffffff',
            lowerThan: 'transform',
            languagePath: '/resources/plugin/outdatebrowser/lang/ko.html'
        });
    });
});