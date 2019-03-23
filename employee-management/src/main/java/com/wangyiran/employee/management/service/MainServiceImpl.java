package com.wangyiran.employee.management.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangyiran.employee.management.entity.Report;
import com.wangyiran.employee.management.mapper.ReportMapper;
import com.wangyiran.employee.management.po.ReportReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.List;

/**
 * @program: employee-management
 * @description: 主服务
 * @author: Mr.Wang
 * @create: 2019-03-21 09:54
 **/
@Service
public class MainServiceImpl implements MainService {

    @Autowired
    private ReportMapper reportMapper;

    public List<Report> SelectAll() {
        List<Report> reports = reportMapper.selectAll();
//        for (Report report : reports) {
//            System.out.println(report.getUsername());
//        }
        return reports;
    }

    public List<Report> queryAll(ReportReq reportReq) {
//        Report reportExample = new Report();
        Integer pageNum = Integer.valueOf(reportReq.getPage());
        Integer pageSize = Integer.valueOf(reportReq.getOffset());
        PageHelper.startPage((int)pageNum, (int)pageSize);
        Example example = new Example(Report.class);
        example.setOrderByClause("enddate desc");

        Example.Criteria criteria = example.createCriteria();
        if(!(reportReq.getReport().getUsername().isEmpty())) {
            criteria.andLike("username", "%" + reportReq.getReport().getUsername()+ "%");
        }
        if(reportReq.getReport().getStartdate()!=null){
            criteria.andGreaterThanOrEqualTo("startdate", reportReq.getReport().getStartdate());
        }
        if(reportReq.getReport().getEnddate()!=null){
            criteria.andLessThanOrEqualTo("enddate", reportReq.getReport().getEnddate());
        }
        criteria.andEqualTo("isdelete", "0");

        PageInfo<Report> pageInfo = new PageInfo<>(reportMapper.selectByExample(example));
        List<Report> reports = pageInfo.getList();
        return reports;
    }


    public List<Report> queryAll() {
        //构造默认查询条件
        ReportReq reportReq = new ReportReq();
        reportReq.setPage("1");
        reportReq.setOffset("10");
        reportReq.setReport(new Report());
        reportReq.getReport().setUsername("");
        return queryAll(reportReq);

    }
    public Report SelectById(Integer id) {
        Report report = reportMapper.selectByPrimaryKey(id);
        if(report == null) {
            //应该扔待处理异常：查询不存在
            return null;
        }
        return report;
    }

    public void EditById(Report report) {
        reportMapper.updateByPrimaryKeySelective(report);
    }

    public void add(Report report){

        if(report.getUsername().isEmpty()) return;
        if(report.getUsername().equalsIgnoreCase("王一然")){
            report.setColor("white");
        } else if(report.getUsername().equalsIgnoreCase("余丹芬")) {
            report.setColor("green");
        } else if(report.getUsername().equalsIgnoreCase("梁宁婴")) {
            report.setColor("skyblue");
        }
        reportMapper.insertSelective(report);
    }

    public void deleteById(Integer id){
        Report report = reportMapper.selectByPrimaryKey(id);
        report.setIsdelete("1");
        reportMapper.updateByPrimaryKeySelective(report);
    }
}
