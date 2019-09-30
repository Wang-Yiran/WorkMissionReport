package com.wangyiran.employee.management.adapter.interceptor;

import com.wangyiran.employee.management.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: employee-management
 * @description: 记录请求日志
 * @author: Mr.Wang
 * @create: 2019-09-27 16:09
 **/
public class WriteLogInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = LoggerFactory.getLogger(WriteLogInterceptor.class);
    //重写addIntercepetors方法，注册拦截器
    //请求发生前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object Handler) throws Exception{
        Long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        return true;
    }
    //请求完成后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object Handler,
                           ModelAndView modelAndView) throws Exception {
        Long startTime = (Long) request.getAttribute("startTime");
        request.removeAttribute("startTime");
        Long endTime = System.currentTimeMillis();
        String requestURI = request.getRequestURI();
        String name = request.getUserPrincipal()==null?"空":request.getUserPrincipal().getName();
        //TODO 通过ehcache在拦截器中知道是谁请求
        log.info("请求人：" + name + "  本次请求的地址：" + requestURI + "  本次请求处理时间为：" + new Long(endTime - startTime) + "ms");
        request.setAttribute("handlingTime", endTime -startTime);
    }
}
