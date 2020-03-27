package com.github.bestheroz.standard.common.interceptor;

import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.util.MySessionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class Interceptor extends HandlerInterceptorAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(Interceptor.class);
    private static final String REQUEST_COMPLETE_EXECUTE_TIME_INCLUDE_JSP = "{} ....... Request Complete Execute Time(include JSP) viewName : {} ....... : {}";
    private static final String STR_STOP_WATCH = "mi.stopWatch";
    private static final String VIEW_NAME = "viewName";

    @Override
    // preHandle : controller 이벤트 호출전
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws CommonException {
        try {
            if (MySessionUtils.isNotLoggedIn()) {
                if (!StringUtils.contains(request.getHeader("accept"), "html") && (StringUtils.startsWith(request.getContentType(), MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        || StringUtils.startsWith(request.getContentType(), MediaType.APPLICATION_JSON_VALUE))) {
                    log.warn(CommonException.FAIL_TRY_LOGIN_FIRST.getJsonObject().toString());
                    throw CommonException.FAIL_TRY_LOGIN_FIRST;
                }
                request.getSession().invalidate();
                String pathWithinApplication = new UrlPathHelper().getPathWithinApplication(request);
                final String originatingQueryString = new UrlPathHelper().getOriginatingQueryString(request);
                if (StringUtils.isNotEmpty(originatingQueryString)) {
                    pathWithinApplication = pathWithinApplication.concat("?").concat(originatingQueryString);
                }
                request.getSession().setAttribute("returnUrl", pathWithinApplication);
                response.sendRedirect(request.getContextPath() + "/sample/login/login.view");
                return false;
            }
        } catch (final IOException e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new CommonException(e);
        }

        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        request.setAttribute(STR_STOP_WATCH, stopWatch);
        return true;
    }

    @Override
    // postHandle : controller 호출 후 view 페이지 출력전
    public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final ModelAndView modelAndView) {
        if (StringUtils.contains(request.getHeader("accept"), "html") && modelAndView != null) {
            request.setAttribute(VIEW_NAME, modelAndView.getViewName());
        }
    }

    @Override
    // afterCompletion : controller + view 페이지 모두 출력 후
    public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final Exception ex) {
        final StopWatch stopWatch = (StopWatch) request.getAttribute(STR_STOP_WATCH);
        stopWatch.stop();
        log.info(REQUEST_COMPLETE_EXECUTE_TIME_INCLUDE_JSP, new UrlPathHelper().getPathWithinApplication(request), request.getAttribute(VIEW_NAME), stopWatch.toString());
        request.removeAttribute(STR_STOP_WATCH);
    }

    @Override
    // afterConcurrentHandlingStarted: 뭐 동시에 핸들링 해주는 메서드인대 정확히는 모르겠습니다
    public void afterConcurrentHandlingStarted(final HttpServletRequest request, final HttpServletResponse response, final Object handler) {
        //
    }
}
