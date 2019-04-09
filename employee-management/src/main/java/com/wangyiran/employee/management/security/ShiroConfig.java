package com.wangyiran.employee.management.security;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @program: employee-management
 * @description:
 * @author: Mr.Wang
 * @create: 2019-04-01 14:46
 **/
@Configuration
public class ShiroConfig {
    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        /**
         * 添加Shiro内置过滤器
         * 常用过滤器
         * anon:无需认证（登陆）可以访问
         * authc:必须认证才可以访问
         * user:如果使用rememberMe的功能可以直接访问
         * perms:该资源必须得到资源权限才可以访问
         * role：该资源必须得到角色权限才可以访问
         */
        Map<String, String> filterMap = new LinkedHashMap<String, String>();

        filterMap.put("/test/**", "authc");
        filterMap.put("/report", "authc");
        filterMap.put("/report/edit", "authc");
        filterMap.put("/report/find", "authc");
        filterMap.put("/user", "authc");
        filterMap.put("/index", "anon");

        //授权过滤器
        filterMap.put("/report/add", "perms[user:reportAddAndDelete]");
        filterMap.put("/report/delete", "perms[user:reportAddAndDelete]");
        //当授权拦截后。shiro会自动跳转到未授权页面
        //设置未授权提示页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");

        //修改调整的登陆页面
        shiroFilterFactoryBean.setLoginUrl("/index");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     * @param userRealm
     * @return
     */
    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    @Bean("userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }

    /**
     * 配置Shiro的Dialect，用于thymeleaf和shiro的标签配合使用
     */
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
