/**
 * Project: haier
 *
 * File Created at 2015-12-02
 * Joey
 *
 * Copyright 2015 Dafy Finance Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Dafy Finance Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with http://www.dafy.com.
 */
package com.aoe.astalift.web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Joey
 * @since 2015年12月02日 16:20
 */
@Component
public class LoggingInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        logger.info("--");
        request.setAttribute("start-time", System.currentTimeMillis());
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        Long endTime = System.currentTimeMillis();
        Long useTime = endTime - (Long) request.getAttribute("start-time");
        logger.info("controller:{} ip:{}, params:{}, use time:{}ms", request.getRequestURI(),
                getIpAddr(request), getRequestMap(request), useTime);
        super.afterCompletion(request, response, handler, ex);
    }

    public static Map<String, String> getRequestMap(HttpServletRequest httpServletRequest) {
        Map<String, String> requestMap = new HashMap<String, String>();
        @SuppressWarnings("unchecked")
        Enumeration<String> enu = httpServletRequest.getParameterNames();
        while (enu.hasMoreElements()) {
            String paramName = enu.nextElement();
            String paramValue;
            try {
                paramValue = URLDecoder.decode(httpServletRequest.getParameter(paramName), "UTF-8");
                if (paramName.contains("pwd") || paramName.contains("bb")) {
                    // 过滤掉敏感信息
                    paramValue = "";
                }
                requestMap.put(paramName, paramValue);
            } catch (UnsupportedEncodingException e) {
                logger.error("", e);
            }
        }
        return requestMap;
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
