package com.wangyiran.employee.management.entity;

import javax.persistence.*;

@Table(name = "management.permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer role;

    private String perms;

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
     * @return role
     */
    public Integer getRole() {
        return role;
    }

    /**
     * @param role
     */
    public void setRole(Integer role) {
        this.role = role;
    }

    /**
     * @return perms
     */
    public String getPerms() {
        return perms;
    }

    /**
     * @param perms
     */
    public void setPerms(String perms) {
        this.perms = perms;
    }
}