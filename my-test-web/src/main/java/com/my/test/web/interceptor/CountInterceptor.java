package com.my.test.web.interceptor;

import com.my.test.service.person.service.PersonService;
import com.my.test.web.person.PersonCtl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by vivi on 2017/12/10.
 */
public class CountInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger("com.my.test.web.interceptor");

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        LOGGER.info("访问路径=====================>{}",httpServletRequest.getRequestURI());
    }
}
