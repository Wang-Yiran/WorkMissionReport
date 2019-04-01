package com.wangyiran.employee.management.po;

import com.wangyiran.employee.management.entity.TestReport;

import java.io.Serializable;

/**
 * @program: employee-management
 * @description:
 * @author: Mr.Wang
 * @create: 2019-03-28 14:57
 **/
public class TestReportReq implements Serializable {
    private String page;

    private String offset;

    private TestReport testReport;

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

    public TestReport getTestReport() {
        return testReport;
    }

    public void setTestReport(TestReport testReport) {
        this.testReport = testReport;
    }
}
