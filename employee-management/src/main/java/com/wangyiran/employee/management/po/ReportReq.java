package com.wangyiran.employee.management.po;

import com.wangyiran.employee.management.entity.Report;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: employee-management
 * @description: 用于封装首页的请求
 * @author: Mr.Wang
 * @create: 2019-03-21 17:03
 **/
public class ReportReq implements Serializable {
    private String page;

    private String offset;

    private Report report;

//    private String username;
//
//    private Date startdate;
//
//    private Date enddate;
//
//    private Integer finishingRate;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }
}
