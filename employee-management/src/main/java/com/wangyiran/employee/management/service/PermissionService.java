package com.wangyiran.employee.management.service;

import com.wangyiran.employee.management.entity.Permission;

import java.util.List;

/**
 * @program: employee-management
 * @description:
 * @author: Mr.Wang
 * @create: 2019-04-01 21:58
 **/
public interface PermissionService {
    public List<Permission> findPermissionByRole(Integer roleId);
}
