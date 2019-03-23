package com.wangyiran.employee.management;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangyiran.employee.management.entity.Report;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import com.wangyiran.employee.management.mapper.ReportMapper;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmployeeManagementApplication.class)
@Transactional
@Rollback
public class EmployeeManagementApplicationTests {

    @Test
    public void contextLoads() {
    }

    /**
     * 注入数据查询接口
     */
    @Autowired
    private ReportMapper reportMapper;

    /**
     * 测试插入数据
     */
//    @Test
//    public void testInsert() {
//        // 构造一条测试数据
//        TbUser tbUser = new TbUser();
//        tbUser.setUsername("Lusifer");
//        tbUser.setPassword("123456");
//        tbUser.setPhone("15888888888");
//        tbUser.setEmail("topsale@vip.qq.com");
//        tbUser.setCreated(new Date());
//        tbUser.setUpdated(new Date());
//
//        // 插入数据
//        tbUserMapper.insert(tbUser);
//    }

    /**
     * 测试删除数据
     */
//    @Test
//    public void testDelete() {
//        // 构造条件，等同于 DELETE from tb_user WHERE username = 'Lusifer'
//        Example example = new Example(TbUser.class);
//        example.createCriteria().andEqualTo("username", "Lusifer");
//
//        // 删除数据
//        tbUserMapper.deleteByExample(example);
//    }

    /**
     * 测试修改数据
     */
//    @Test
//    public void testUpdate() {
//        // 构造条件
//        Example example = new Example(TbUser.class);
//        example.createCriteria().andEqualTo("username", "Lusifer");
//
//        // 构造一条测试数据
//        TbUser tbUser = new TbUser();
//        tbUser.setUsername("LusiferNew");
//        tbUser.setPassword("123456");
//        tbUser.setPhone("15888888888");
//        tbUser.setEmail("topsale@vip.qq.com");
//        tbUser.setCreated(new Date());
//        tbUser.setUpdated(new Date());
//
//        // 修改数据
//        tbUserMapper.updateByExample(tbUser, example);
//    }

    /**
     * 测试查询集合
     */
    @Test
    public void testSelect() {
        List<Report> reports = reportMapper.selectAll();
        for (Report report : reports) {
            System.out.println(report.getUsername());
        }
    }

    /**
     * 测试分页查询
     */
    @Test
    public void testPage() {
        // PageHelper 使用非常简单，只需要设置页码和每页显示笔数即可
        PageHelper.startPage(1, 2);

        // 设置分页查询条件
        Example example = new Example(Report.class);
        PageInfo<Report> pageInfo = new PageInfo<>(reportMapper.selectByExample(example));

        // 获取查询结果
        List<Report> reports = pageInfo.getList();
        for (Report report : reports) {
            System.out.println(report.getUsername());
        }
    }

}
