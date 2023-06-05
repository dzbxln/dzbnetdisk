package com.dengzebin.netdisk.untils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author DengZebing
 * @Description TODO
 * @Date 2023/5/31 10:53
 */
public class RequestDetail implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(RequestDetail.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("客户端请求路径：{}", request.getRequestURI());
        return true;
    }
}