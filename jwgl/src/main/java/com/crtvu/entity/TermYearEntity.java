package com.crtvu.entity;

import java.util.Date;

/**
 * Created by Phoenix on 2017/4/11.
 */
public class TermYearEntity {
    // 学年
    private String schoolYear;

    //学期
    private int term;

    // 开学日期
    private Date beginTime;

    // 放假日期
    private Date overTime;

    //id
    private  int id;

    public TermYearEntity(String schoolYear, int term, Date beginTime, Date overTime) {
        this.schoolYear = schoolYear;
        this.term = term;
        this.beginTime = beginTime;
        this.overTime = overTime;
    }

    public TermYearEntity(String schoolYear, int term, Date beginTime, Date overTime, int id) {
        this.schoolYear = schoolYear;
        this.term = term;
        this.beginTime = beginTime;
        this.overTime = overTime;
        this.id = id;
    }

    public TermYearEntity() {
    }
    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getOverTime() {
        return overTime;
    }

    public void setOverTime(Date overTime) {
        this.overTime = overTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TermYearEntity{" +
                "schoolYear='" + schoolYear + '\'' +
                ", term=" + term +
                ", beginTime=" + beginTime +
                ", overTime=" + overTime +
                ", id=" + id +
                '}';
    }
}
