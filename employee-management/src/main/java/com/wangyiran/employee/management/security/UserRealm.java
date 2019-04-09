package com.wangyiran.employee.management.security;

import com.wangyiran.employee.management.entity.Permission;
import com.wangyiran.employee.management.entity.User;
import com.wangyiran.employee.management.service.PermissionService;
import com.wangyiran.employee.management.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @program: employee-management
 * @description:
 * @author: Mr.Wang
 * @create: 2019-04-01 14:58
 **/
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;
    /**
     * 执行授权逻辑
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        //给资源进行授权
        //创建资源授权对象AuthorizationInfo
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //添加资源授权字符串
//        info.addStringPermission("user:reportAddAndDelete");

        //获取当前登陆用户
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();//这里的principal就是验证中的第一个参数的对象
        Integer roleId = user.getRole();
        List<Permission> permissions = permissionService.findPermissionByRole(roleId);
        if(permissions!=null){
            for(Permission permission:permissions){
                info.addStringPermission(permission.getPerms());
            }
        }
//        info.addStringPermission("user:reportAddAndDelete");

        return info;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行验证逻辑");

        //编写shiro验证逻辑，判断用户名和密码
        //1. 判断用户输入
        if(authenticationToken==null){
            return null;
        }
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.findByUser(token.getUsername());
        if(user==null){
            //用户名不存在
            return null;
        }
//        if(!token.getUsername().equalsIgnoreCase(name)){
//            //用户名不存在
//            return null;//shiro底层会自动抛出unknownAccountException
//        }
        //2. 判断密码

        return new SimpleAuthenticationInfo(user,user.getPassword(),"");//principal,输入的密码,realmName
    }

}
