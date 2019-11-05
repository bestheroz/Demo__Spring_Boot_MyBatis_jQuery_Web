package com.github.bestheroz.standard.context.aop.logging;

import com.github.bestheroz.standard.common.util.MyMapperUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Aspect
public class TraceLoggingInAOP {
    private static final Logger LOGGER = LoggerFactory.getLogger(TraceLoggingInAOP.class);
    private static final String STR_CLASS_METHOD = "{0}.{1}({2})";
    private static final String STR_START_EXECUTE_TIME = "{} START ....... Execute Time ....... : {}";
    private static final String STR_END_EXECUTE_TIME = "{} E N D ....... Execute Time ....... : {} - return Value({}) : {}";

    @Around("execution(* com.github.bestheroz..*Controller.*(..)) || execution(* com.github.bestheroz..*Service.*(..)) || execution(* com.github.bestheroz..*DAO.*(..))")
    public Object doLoggingAround(final ProceedingJoinPoint pjp) throws Throwable {
        Object retVal;

        final Class<?> targetClass = pjp.getTarget().getClass();
        final String formatClassMethod = MessageFormat.format(STR_CLASS_METHOD,
                StringUtils.startsWith(targetClass.getSimpleName(), "$Proxy") ? targetClass.getInterfaces()[0].getSimpleName() : targetClass.getSimpleName(), pjp.getSignature().getName(),
                this.getAgumentNames(pjp.getArgs()));
        try {
            final StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            LOGGER.info(STR_START_EXECUTE_TIME, formatClassMethod, stopWatch.toString());

            retVal = pjp.proceed();

            stopWatch.stop();
            LOGGER.info(STR_END_EXECUTE_TIME, formatClassMethod, stopWatch.toString(), ((MethodSignature) pjp.getSignature()).getReturnType().getSimpleName(),
                    StringUtils.defaultString(MyMapperUtils.writeObjectAsString(retVal), "null"));
        } catch (final Throwable e) {
            LOGGER.warn("{} -\n{}", formatClassMethod, ExceptionUtils.getStackTrace(e));
            throw e;
        }
        return retVal;
    }

    private String getAgumentNames(final Object[] obj) {
        final List<String> list = new ArrayList<>();
        for (final Object element : obj) {
            if (element != null) {
                list.add(element.getClass().getSimpleName());
            }
        }
        return StringUtils.join(list, ", ");
    }
}
