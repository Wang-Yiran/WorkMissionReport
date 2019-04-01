package com.wangyiran.employee.management.service;

import com.wangyiran.employee.management.entity.TestReport;
import com.wangyiran.employee.management.po.TestReportReq;

import java.util.List;

/**
 * @program: employee-management
 * @description: 测试报告
 * @author: Mr.Wang
 * @create: 2019-03-28 14:52
 **/
public interface TestReportService {

    /*
     * 搜索全部的报告内容
     */
    public List<TestReport> queryAll();

    public List<TestReport> queryAll(TestReportReq testReportReq);

    public TestReport SelectById(Integer id);

    void EditById(TestReport testReport);

    public void add(TestReport testReport);

    void deleteById(Integer id);
}
