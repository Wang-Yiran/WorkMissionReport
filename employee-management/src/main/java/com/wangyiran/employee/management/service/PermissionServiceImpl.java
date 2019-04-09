package com.wangyiran.employee.management.service;

import com.wangyiran.employee.management.entity.Permission;
import com.wangyiran.employee.management.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: employee-management
 * @description:
 * @author: Mr.Wang
 * @create: 2019-04-01 21:55
 **/
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    public List<Permission> findPermissionByRole(Integer roleId){
        Permission permissionVo = new Permission();
        permissionVo.setRole(roleId);
        return permissionMapper.select(permissionVo);
    }
}
