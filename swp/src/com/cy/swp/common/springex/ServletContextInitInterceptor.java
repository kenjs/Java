package com.cy.swp.common.springex;

import com.cy.swp.action.BaseAction;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by Richie.Lee on 2014/9/9.
 */
@Component("ServletContextInitInterceptor")
public class ServletContextInitInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(ServletContextInitInterceptor.class);
    private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("各请求开始时间");
    
    
    
    

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        long beginTime = System.currentTimeMillis();
        startTimeThreadLocal.set(beginTime);
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            BaseAction baseAction = (BaseAction) handlerMethod.getBean();
            baseAction.setRequest(request);
            baseAction.setResponse(response);
            baseAction.setSession(request.getSession());
        } else if (handler instanceof DefaultServletHttpRequestHandler) {
            log.warn(new StringBuilder("当前的请求:").append(request.getRequestURL()).append("没有找到对应的RequestMapping，可能出现渲染，请检查您的Controller配置！").toString());
        }
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

    public static String getBodyString(BufferedReader br) {
        String inputLine;
        StringBuilder stringBuilder = new StringBuilder("");
        try {
            while ((inputLine = br.readLine()) != null) {
                stringBuilder.append(inputLine);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        return stringBuilder.toString();
    }
}
