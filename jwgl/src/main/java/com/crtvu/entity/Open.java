package com.crtvu.entity;

import java.util.Date;

/**
 * Created by Phoenix on 2017/4/4.
 */
public class Open {

    // 开设Id
    private int openId;

    // 课程Id
    private String courseId;

    // 学年
    private String schoolYear;

    // 学期
    private int term;

    // 课程人数
    private int peopleNum;

    // 开始选课时间
    private Date startSelectTime;

    // 结束选课时间
    private Date endSelectTime;

    public Open() {
    }

    public Open(int openId, String courseId, String schoolYear, int term, int peopleNum, Date startSelectTime, Date endSelectTime) {
        this.openId = openId;
        this.courseId = courseId;
        this.schoolYear = schoolYear;
        this.term = term;
        this.peopleNum = peopleNum;
        this.startSelectTime = startSelectTime;
        this.endSelectTime = endSelectTime;
    }

    public int getOpenId() {
        return openId;
    }

    public void setOpenId(int openId) {
        this.openId = openId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
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

    public int getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(int peopleNum) {
        this.peopleNum = peopleNum;
    }

    public Date getStartSelectTime() {
        return startSelectTime;
    }

    public void setStartSelectTime(Date startSelectTime) {
        this.startSelectTime = startSelectTime;
    }

    public Date getEndSelectTime() {
        return endSelectTime;
    }

    public void setEndSelectTime(Date endSelectTime) {
        this.endSelectTime = endSelectTime;
    }

    @Override
    public String toString() {
        return "Open{" +
                "openId=" + openId +
                ", courseId='" + courseId + '\'' +
                ", schoolYear='" + schoolYear + '\'' +
                ", term=" + term +
                ", peopleNum=" + peopleNum +
                ", startSelectTime=" + startSelectTime +
                ", endSelectTime=" + endSelectTime +
                '}';
    }
}
