package com.wangyiran.employee.management.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangyiran.employee.management.entity.Report;
import com.wangyiran.employee.management.entity.TestReport;
import com.wangyiran.employee.management.mapper.TestReportMapper;
import com.wangyiran.employee.management.po.ReportReq;
import com.wangyiran.employee.management.po.TestReportReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @program: employee-management
 * @description: 测试报告的服务
 * @author: Mr.Wang
 * @create: 2019-03-28 14:53
 **/
@Service
public class TestReportServiceImpl implements TestReportService{

    @Autowired
    TestReportMapper testReportMapper;

    @Override
    public List<TestReport> queryAll() {
        TestReportReq testReportReq = new TestReportReq();
        testReportReq.setPage("1");
        testReportReq.setOffset("10");
        testReportReq.setTestReport(new TestReport());
        testReportReq.getTestReport().setUsername("");
        return queryAll(testReportReq);
    }

    @Override
    public List<TestReport> queryAll(TestReportReq testReportReq) {
//        Report reportExample = new Report();
        Integer pageNum = Integer.valueOf(testReportReq.getPage());
        Integer pageSize = Integer.valueOf(testReportReq.getOffset());
        PageHelper.startPage((int)pageNum, (int)pageSize);
        Example example = new Example(TestReport.class);
        example.setOrderByClause("startdate desc");

        Example.Criteria criteria = example.createCriteria();
        if(!(testReportReq.getTestReport().getUsername().isEmpty())) {
            criteria.andLike("username", "%" + testReportReq.getTestReport().getUsername()+ "%");
        }
        if(testReportReq.getTestReport().getProjectname()!=null) {
            criteria.andLike("projectname", "%" + testReportReq.getTestReport().getProjectname()+ "%");
        }
        if(testReportReq.getTestReport().getPrincipal()!=null) {
            criteria.andLike("principal", "%" + testReportReq.getTestReport().getPrincipal()+ "%");
        }
        if(testReportReq.getTestReport().getProblemStatus()!=null) {
            criteria.andLike("problemStatus", "%" + testReportReq.getTestReport().getProblemStatus()+ "%");
        }
        if(testReportReq.getTestReport().getProblemType()!=null) {
            criteria.andLike("problemType", "%" + testReportReq.getTestReport().getProblemType()+ "%");
        }
        if(testReportReq.getTestReport().getStartdate()!=null){
            criteria.andGreaterThanOrEqualTo("startdate", testReportReq.getTestReport().getStartdate());
        }
        if(testReportReq.getTestReport().getEnddate()!=null){
            criteria.andLessThanOrEqualTo("enddate", testReportReq.getTestReport().getEnddate());
        }
        criteria.andEqualTo("isdelete", "0");
        PageInfo<TestReport> pageInfo = new PageInfo<>(testReportMapper.selectByExample(example));
        List<TestReport> testReports = pageInfo.getList();
        return testReports;
    }

    @Override
    public TestReport SelectById(Integer id) {
        TestReport testReport = testReportMapper.selectByPrimaryKey(id);
        if(testReport == null) {
            //应该扔待处理异常：查询不存在
            return null;
        }
        return testReport;
    }

    @Override
    public void EditById(TestReport testReport) {
        testReportMapper.updateByPrimaryKeySelective(testReport);
    }

    @Override
    public void add(TestReport testReport) {
        if(testReport.getUsername().isEmpty()) return;
        if(testReport.getUsername().equalsIgnoreCase("王一然")){
            testReport.setColor("white");
        } else if(testReport.getUsername().equalsIgnoreCase("余丹芬")) {
            testReport.setColor("orange");
        } else if(testReport.getUsername().equalsIgnoreCase("梁宁婴")) {
            testReport.setColor("skyblue");
        }
        testReportMapper.insertSelective(testReport);
    }

    @Override
    public void deleteById(Integer id) {
        TestReport testReport = testReportMapper.selectByPrimaryKey(id);
        testReport.setIsdelete("1");
        testReportMapper.updateByPrimaryKeySelective(testReport);
    }
}
