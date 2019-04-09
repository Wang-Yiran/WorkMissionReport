package com.wangyiran.employee.management.service;

import com.wangyiran.employee.management.entity.User;
import com.wangyiran.employee.management.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @program: employee-management
 * @description:
 * @author: Mr.Wang
 * @create: 2019-04-01 16:41
 **/
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;

    public User findByUser(String name){
        User userVo = new User();
        userVo.setUser(name);
        userVo.setIsdelete("0");
        User user = userMapper.selectOne(userVo);
        if(user!=null)
            return user;
        return null;
    }

    @Override
    public List<User> queryAll() {
        Example example = new Example(User.class);
        example.setOrderByClause("user asc");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isdelete", "0");
        return userMapper.selectByExample(example);
    }


}
