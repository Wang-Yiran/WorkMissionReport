package com.wangyiran.employee.management.service;

import com.wangyiran.employee.management.entity.User;

import java.util.List;

/**
 * @program: employee-management
 * @description:
 * @author: Mr.Wang
 * @create: 2019-04-01 16:43
 **/
public interface UserService {
    public User findByUser(String name);

    public List<User> queryAll();
}
