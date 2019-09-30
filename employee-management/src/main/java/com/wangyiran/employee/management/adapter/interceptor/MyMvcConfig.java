package com.wangyiran.employee.management.adapter.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @program: employee-management
 * @description: 添加拦截器
 * @author: Mr.Wang
 * @create: 2019-09-27 16:19
 **/
@Configuration
//@EnableWebMvc
//public class MyMvcConfig extends WebMvcConfigurerAdapter {
public class MyMvcConfig implements WebMvcConfigurer {
    @Bean
    public WriteLogInterceptor writeLogInterceptor(){
        return new WriteLogInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(writeLogInterceptor());//.addPathPatterns("/**");
    }
}
