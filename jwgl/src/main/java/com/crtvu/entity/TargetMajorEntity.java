package com.crtvu.entity;

/**
 * Created by Phoenix on 2017/4/4.
 */
public class TargetMajorEntity {

    // 开设Id
    private int openId;

    // 面向专业
    private String major;

    public TargetMajorEntity() {
    }

    public TargetMajorEntity(int openId, String major) {
        this.openId = openId;
        this.major = major;
    }

    public int getOpenId() {
        return openId;
    }

    public void setOpenId(int openId) {
        this.openId = openId;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "TargetMajorEntity{" +
                ", openId=" + openId +
                ", major='" + major + '\'' +
                '}';
    }
}
