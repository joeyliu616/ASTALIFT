package com.aoe.astalift.web.interceptor;

import com.aoe.astalift.web.annotation.SessionValidIgnore;
import com.aoe.astalift.web.constants.SessionConstants;
import com.aoe.astalift.web.exception.SessionInvalidException;
import com.aoe.astalift.web.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by joey on 16-3-21.
 */
@Component
public class SessionInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);

    @Resource
    SessionService sessionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("------");
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        SessionValidIgnore annotation = handlerMethod.getMethod().getAnnotation(SessionValidIgnore.class);
        if(null == annotation){
            String sessionKey = this.getSessionKey(request);
            if(null == sessionKey){
                throw new SessionInvalidException("session key 为空");
            }
            Integer userId = sessionService.getUserId(sessionKey);
            if(null == userId){
                throw new SessionInvalidException("session key 为空");
            }
        }
        return super.preHandle(request, response, handler);
    }


    private String getSessionKey(HttpServletRequest request){
        HttpSession session = request.getSession();
        String sessionKey = (String)session.getAttribute(SessionConstants.SESSION_KEY);

        if(null == sessionKey){
            String headerKey = request.getHeader(SessionConstants.SESSION_KEY);
            if(null == headerKey){
                String paramsKey = request.getParameter(SessionConstants.SESSION_KEY);
                return paramsKey;
            }else{
                return headerKey;
            }
        }else{
            return sessionKey;
        }
    }
}
