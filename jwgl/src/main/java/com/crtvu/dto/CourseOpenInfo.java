package com.crtvu.dto;

/**
 * Created by Jixw on 2017/3/30.
 */
public class CourseOpenInfo {

    private String courseId;

    private int openId;

    private String courseName;

    private float credit;

    private String nature;           //课程性质

    private  String department;      //开设院系

    private String schoolYear;

    private int term;

    private  int peopleNum;

    public CourseOpenInfo() {
    }

    public CourseOpenInfo(String courseId, int openId, String courseName, float credit, String nature, String department, String schoolYear, int term, int peopleNum) {
        this.courseId = courseId;
        this.openId = openId;
        this.courseName = courseName;
        this.credit = credit;
        this.nature = nature;
        this.department = department;
        this.schoolYear = schoolYear;
        this.term = term;
        this.peopleNum = peopleNum;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public int getOpenId() {
        return openId;
    }

    public void setOpenId(int openId) {
        this.openId = openId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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

    @Override
    public String toString() {
        return "CourseOpenInfo{" +
                "courseId='" + courseId + '\'' +
                ", openId=" + openId +
                ", courseName='" + courseName + '\'' +
                ", credit=" + credit +
                ", nature='" + nature + '\'' +
                ", department='" + department + '\'' +
                ", schoolYear='" + schoolYear + '\'' +
                ", term=" + term +
                ", peopleNum=" + peopleNum +
                '}';
    }
}
