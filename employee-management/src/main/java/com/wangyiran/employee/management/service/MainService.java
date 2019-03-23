package com.wangyiran.employee.management.service;

import com.wangyiran.employee.management.entity.Report;
import com.wangyiran.employee.management.po.ReportReq;

import java.math.BigDecimal;
import java.util.List;

/**
 * @program: employee-management
 * @description: 主服务
 * @author: Mr.Wang
 * @create: 2019-03-21 09:53
 **/
public interface MainService {
    public List<Report> SelectAll();

    public List<Report> queryAll();

    public List<Report> queryAll(ReportReq reportReq);

    public Report SelectById(Integer id);

    void EditById(Report report);

    public void add(Report report);

    void deleteById(Integer id);
}
