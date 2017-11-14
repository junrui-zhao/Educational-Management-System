package com.crtvu.entity;

import com.crtvu.util.JDateFormater;

import java.util.Date;

/**
 * Created by danagi on 2017/4/10.
 */
public class OpenEntity extends CourseEntity{
    private int openId;
    private String schoolYear;
    private int term;
    private int peopleNum;
    private Date startSelectTime;
    private Date endSelectTime;
    private String startSelectTimeString;
    private String endSelectTimeString;

    public OpenEntity(){}

    public OpenEntity(int openId, String schoolYear, int term, int peopleNum, Date startSelectTime, Date endSelectTime) {
        this.openId = openId;
        this.schoolYear = schoolYear;
        this.term = term;
        this.peopleNum = peopleNum;
        this.startSelectTime = startSelectTime;
        this.endSelectTime = endSelectTime;
        this.startSelectTimeString = JDateFormater.dateToString(startSelectTime);
        this.endSelectTimeString = JDateFormater.dateToString(endSelectTime);
    }

    public OpenEntity(CourseEntity courseEntity, int openId, String schoolYear, int term, int peopleNum, Date startSelectTime, Date endSelectTime) {
        super(courseEntity.getCourseId(), courseEntity.getCourseName(), courseEntity.getCredit(), courseEntity.getNature(), courseEntity.getDepartment());
        this.openId = openId;
        this.schoolYear = schoolYear;
        this.term = term;
        this.peopleNum = peopleNum;
        this.startSelectTime = startSelectTime;
        this.endSelectTime = endSelectTime;
        this.startSelectTimeString = JDateFormater.dateToString(startSelectTime);
        this.endSelectTimeString = JDateFormater.dateToString(endSelectTime);
    }

    public int getOpenId() {
        return openId;
    }

    public void setOpenId(int openId) {
        this.openId = openId;
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

    public String getStartSelectTimeString() {
        return startSelectTimeString;
    }

    public void setStartSelectTimeString(String startSelectTimeString) {
        this.startSelectTimeString = startSelectTimeString;
    }

    public String getEndSelectTimeString() {
        return endSelectTimeString;
    }

    public void setEndSelectTimeString(String endSelectTimeString) {
        this.endSelectTimeString = endSelectTimeString;
    }

    @Override
    public String toString() {
        return "OpenEntity{" +
                "openId=" + openId +
                ", schoolYear='" + schoolYear + '\'' +
                ", term=" + term +
                ", peopleNum=" + peopleNum +
                ", startSelectTime=" + startSelectTime +
                ", endSelectTime=" + endSelectTime +
                '}';
    }
}
