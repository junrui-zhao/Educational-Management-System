package com.crtvu.dto;

/**
 * Created by Phoenix on 2017/4/13.
 */
public class OpenJson {

    private int openId;

    private String courseId;

    private String schoolYear;

    private int term;

    private int peopleNum;

    private String startTime;

    private String endTime;

    private String major;

    public OpenJson() {
    }

    public OpenJson(int openId, String courseId, String schoolYear, int term, int peopleNum, String startTime, String endTime, String major) {
        this.openId = openId;
        this.courseId = courseId;
        this.schoolYear = schoolYear;
        this.term = term;
        this.peopleNum = peopleNum;
        this.startTime = startTime;
        this.endTime = endTime;
        this.major = major;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "OpenJson{" +
                "openId=" + openId +
                ", courseId='" + courseId + '\'' +
                ", schoolYear='" + schoolYear + '\'' +
                ", term=" + term +
                ", peopleNum=" + peopleNum +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", major='" + major + '\'' +
                '}';
    }
}
