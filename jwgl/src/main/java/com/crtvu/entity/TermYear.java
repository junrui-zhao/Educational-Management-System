package com.crtvu.entity;

import java.util.Date;

/**
 * Created by Phoenix on 2017/4/8.
 */
public class TermYear {

    // 学年
    private String schoolYear;

    //学期
    private int term;

    // 开学日期
    private Date beginTime;

    // 放假日期
    private Date overTime;

    public TermYear() {
    }

    public TermYear(String schoolYear, int term, Date beginTime, Date overTime) {
        this.schoolYear = schoolYear;
        this.term = term;
        this.beginTime = beginTime;
        this.overTime = overTime;
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

    @Override
    public String toString() {
        return "TermYear{" +
                "schoolYear='" + schoolYear + '\'' +
                ", term=" + term +
                ", beginTime=" + beginTime +
                ", overTime=" + overTime +
                '}';
    }
}
