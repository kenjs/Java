package com.cy.driver.common.springex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2014/9/19.
 */
@Component("ServletContextInitInterceptor")
public class ServletContextInitInterceptor extends org.springframework.web.servlet.handler.HandlerInterceptorAdapter{
    private static final Logger log = LoggerFactory.getLogger(ServletContextInitInterceptor.class);
    private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("各请求开始时间");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long beginTime = System.currentTimeMillis();
        startTimeThreadLocal.set(beginTime);

        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long endTime = System.currentTimeMillis();//2、结束时间
        long beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）
        long consumeTime = endTime - beginTime;//3、消耗的时间
        if (log.isDebugEnabled()) {
            log.debug(new StringBuilder("本次请求:").append(request.getRequestURL()).append("总耗时为：").append(consumeTime).append("毫秒").toString());
        }
        if (consumeTime > 500) {//此处认为处理时间超过500毫秒的请求为慢请求
//            log.warn((String.format("%s consume %d millis", request.getRequestURI(), consumeTime)));
            log.warn("本次请求耗时超过500毫秒，可考虑优化处理效率！URL:" + request.getRequestURL());
        }
        super.afterCompletion(request, response, handler, ex);
    }
}
