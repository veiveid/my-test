package com.my.test.web.interceptor;

import com.my.test.service.person.service.PersonService;
import com.my.test.web.person.PersonCtl;
import com.my.test.web.util.IpUtil;
import org.omg.PortableInterceptor.INACTIVE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by vivi on 2017/12/10.
 */
public class CountInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger("com.my.test.web.interceptor");

    private static final ThreadLocal<Long> REQ_TIME_THL = new ThreadLocal<Long>();

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        REQ_TIME_THL.set(System.currentTimeMillis());
        //String ip = httpServletRequest.getRemoteAddr();
        String ip = IpUtil.getIpFromRequest(httpServletRequest);
        LOGGER.info("客户端ip：{}",ip);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        Long start = REQ_TIME_THL.get();
        long end = System.currentTimeMillis();
        long timeCons = end-start;
        LOGGER.info("访问路径：{} 耗时：{}",httpServletRequest.getRequestURI(),timeCons+"ms");
    }
}
