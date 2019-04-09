package com.wangyiran.employee.management.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "management.test_report")
public class TestReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    /**
     * 项目名称
     */
    private String projectname;

    private Date startdate;

    private Date enddate;

    /**
     * 问题模块名称
     */
    private String module;

    /**
     * 问题描述
     */
    @Column(name = "problem_type")
    private String problemType;

    private String remark;

    @Column(name = "isDelete")
    private String isdelete;

    /**
     * 负责人
     */
    private String principal;

    /**
     * 问题状态
     */
    @Column(name = "problem_status")
    private String problemStatus;

    private String color;

    /**
     * 问题描述
     */
    @Column(name = "problem_describe")
    private String problemDescribe;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取项目名称
     *
     * @return projectname - 项目名称
     */
    public String getProjectname() {
        return projectname;
    }

    /**
     * 设置项目名称
     *
     * @param projectname 项目名称
     */
    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    /**
     * @return startdate
     */
    public Date getStartdate() {
        return startdate;
    }

    /**
     * @param startdate
     */
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    /**
     * @return enddate
     */
    public Date getEnddate() {
        return enddate;
    }

    /**
     * @param enddate
     */
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    /**
     * 获取问题模块名称
     *
     * @return module - 问题模块名称
     */
    public String getModule() {
        return module;
    }

    /**
     * 设置问题模块名称
     *
     * @param module 问题模块名称
     */
    public void setModule(String module) {
        this.module = module;
    }

    /**
     * 获取问题描述
     *
     * @return problem_type - 问题描述
     */
    public String getProblemType() {
        return problemType;
    }

    /**
     * 设置问题描述
     *
     * @param problemType 问题描述
     */
    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return isDelete
     */
    public String getIsdelete() {
        return isdelete;
    }

    /**
     * @param isdelete
     */
    public void setIsdelete(String isdelete) {
        this.isdelete = isdelete;
    }

    /**
     * 获取负责人
     *
     * @return principal - 负责人
     */
    public String getPrincipal() {
        return principal;
    }

    /**
     * 设置负责人
     *
     * @param principal 负责人
     */
    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    /**
     * 获取问题状态
     *
     * @return problem_status - 问题状态
     */
    public String getProblemStatus() {
        return problemStatus;
    }

    /**
     * 设置问题状态
     *
     * @param problemStatus 问题状态
     */
    public void setProblemStatus(String problemStatus) {
        this.problemStatus = problemStatus;
    }

    /**
     * @return color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * 获取问题描述
     *
     * @return problem_describe - 问题描述
     */
    public String getProblemDescribe() {
        return problemDescribe;
    }

    /**
     * 设置问题描述
     *
     * @param problemDescribe 问题描述
     */
    public void setProblemDescribe(String problemDescribe) {
        this.problemDescribe = problemDescribe;
    }
}