package com.wangyiran.employee.management.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "management.report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private Date startdate;

    private Date enddate;

    @Column(name = "finishing_rate")
    private Integer finishingRate;

    private String remark;

    @Column(name = "isDelete")
    private String isdelete;

    private String color;

    private String target;

    private String reporting;

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
     * @return finishing_rate
     */
    public Integer getFinishingRate() {
        return finishingRate;
    }

    /**
     * @param finishingRate
     */
    public void setFinishingRate(Integer finishingRate) {
        this.finishingRate = finishingRate;
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
     * @return target
     */
    public String getTarget() {
        return target;
    }

    /**
     * @param target
     */
    public void setTarget(String target) {
        this.target = target;
    }

    /**
     * @return reporting
     */
    public String getReporting() {
        return reporting;
    }

    /**
     * @param reporting
     */
    public void setReporting(String reporting) {
        this.reporting = reporting;
    }
}