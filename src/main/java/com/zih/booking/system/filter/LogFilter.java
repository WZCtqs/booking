package com.zih.booking.system.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zih.booking.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * 日志过滤器
 * @author shahy
 */
@Slf4j
@Aspect
@Component
public class LogFilter {

    private static final ObjectMapper mapper = new ObjectMapper();

    private long startTimeMillis;

    private long endTimeMillis;

    @Pointcut("execution(* com.zih.booking.controller.*.*(..))")
    public void controller() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void requestMapping() {
    }

    @Before("controller()")
    public void doBefore(JoinPoint joinPoint) {
        startTimeMillis = System.currentTimeMillis();
        org.springframework.web.context.request.RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = null;
        if (sra != null) {
            request = sra.getRequest();
        }
        log.debug("===================== request start =====================", new Object[0]);
        if (request != null) {
            log.debug("URL:[ {} ]", new Object[] { request.getRequestURL() });
            printMap("Request Params", request.getParameterMap());
        }
        log.debug("===================== request end =====================\n", new Object[0]);
    }

    @After("controller()")
    public void doAfter(JoinPoint joinPoint) {
        endTimeMillis = System.currentTimeMillis();
    }

    @AfterReturning(pointcut = "controller()", returning = "returnValue")
    public JoinPoint afterReturning(JoinPoint joinPoint, Object returnValue) {
        log.debug("===================== return start =====================", new Object[0]);

        org.springframework.web.context.request.RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = null;
        if (sra != null) {
            request = sra.getRequest();
        }
        if (request != null) {
            log.debug("计时结束：{}  耗时：{}  URI: {}  最大内存: {}m  已分配内存: {}m  已分配内存中的剩余空间: {}m  最大可用内存: {}m",
                    new SimpleDateFormat("hh:mm:ss.SSS").format(endTimeMillis), DateUtils.formatDateTime(endTimeMillis - startTimeMillis),
                    request.getRequestURI(), Runtime.getRuntime().maxMemory()/1024/1024, Runtime.getRuntime().totalMemory()/1024/1024, Runtime.getRuntime().freeMemory()/1024/1024,
                    (Runtime.getRuntime().maxMemory()-Runtime.getRuntime().totalMemory()+Runtime.getRuntime().freeMemory())/1024/1024);
        }

        log.debug("===================== return end =====================\n", new Object[0]);
        return joinPoint;
    }

    @AfterThrowing(pointcut = "controller()", throwing = "e")
    public void afterThrowing(Throwable e) {
        log.error("ERROR:", new Object[] { e });
    }

    private void printMap(String prefix, Map map) {
        StringBuilder buf = new StringBuilder();
        for (Object o : map.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            buf.append("  ");
            buf.append((String) entry.getKey());
            buf.append(" = ");
            try {
                buf.append(mapper.writeValueAsString(entry.getValue()));
                buf.append("\n");
            }
            catch (IOException e) {
                log.error("print map error", new Object[] { e.getMessage() });
            }
        }

        if (buf.length() > 0) {
            log.debug("{}:\n{}", new Object[] { prefix, buf.substring(0, buf.length() - 1) });
        }
    }
}
