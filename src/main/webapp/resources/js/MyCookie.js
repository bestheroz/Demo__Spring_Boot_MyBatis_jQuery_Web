;
const MyCookie = {
    getCookie: (name) => {
        const wcname = name + '=';
        let wcstart, wcend, end;
        let i = 0;

        while (i <= document.cookie.length) {
            wcstart = i;
            wcend = (i + wcname.length);

            if (document.cookie.substring(wcstart, wcend) === wcname) {
                if ((end = document.cookie.indexOf(';', wcend)) === -1) {
                    end = document.cookie.length;
                }
                return decodeURI(document.cookie.substring(wcend, end));
            }
            i = document.cookie.indexOf('', i) + 1;
            if (i === 0) {
                break;
            }
        }
        return null;
    },
    setCookie: (name, value, expiredays) => {
        const todayDate = new Date();
        todayDate.setDate(todayDate.getDate() + expiredays);
        document.cookie = name + "=" + escape(value) + "; path=/; expires=" + todayDate.toGMTString() + ";";
    },
    // 지정된 expiredays의 00시 기준으로 setting
    setCookieAt00: (name, value, expiredays) => {
        const todayDate = new Date();
        todayDate.setHours(0);
        todayDate.setMinutes(0);
        todayDate.setSeconds(0);
        todayDate.setDate(todayDate.getDate() + expiredays);
        document.cookie = name + "=" + escape(value) + "; path=/; expires=" + todayDate.toGMTString() + ";";
        // 00시 쿠키 셋팅에 문제가 있는 브라우저가 간혹 있는 관계로 쿠키가 셋팅이 되지 않는 경우 기존의 24시간 기준으로 쿠키를 셋팅함.
        if (expiredays > -1 && MyCommon.getCookie(name) !== value) {
            MyCommon.setCookie(name, value, expiredays);
        }
    },
    removeCookie: (name) => {
        document.cookie = name + '=; path=/; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
    }
};
