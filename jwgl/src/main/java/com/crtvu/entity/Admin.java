package com.crtvu.entity;

/**
 * Created by Phoenix on 2017/4/4.
 */
public class Admin {

    // 管理员Id
    private int adminId;

    // 管理员密码
    private String password;

    public Admin() {
    }

    public Admin(int adminId, String password) {
        this.adminId = adminId;
        this.password = password;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", password='" + password + '\'' +
                '}';
    }
}
