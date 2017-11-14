package com.crtvu.dto.student;

import com.crtvu.entity.Course;

/**
 * Created by Phoenix on 2017/4/9.
 */
public class CourseItem{

    // 课程id
    private String courseId;

    // 课程名称
    private String courseName;

    // 课程学分
    private Double credit;

    // 课程性质
    private String nature;

    // 课程开设院系
    private String department;

    private int openId;

    private int peopleNum;

    private int leftNum;

    public CourseItem() {
    }

    public CourseItem(String courseId, String courseName, Double credit, String nature, String department, int openId, int peopleNum, int leftNum) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credit = credit;
        this.nature = nature;
        this.department = department;
        this.openId = openId;
        this.peopleNum = peopleNum;
        this.leftNum = leftNum;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
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

    public int getOpenId() {
        return openId;
    }

    public void setOpenId(int openId) {
        this.openId = openId;
    }

    public int getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(int peopleNum) {
        this.peopleNum = peopleNum;
    }

    public int getLeftNum() {
        return leftNum;
    }

    public void setLeftNum(int leftNum) {
        this.leftNum = leftNum;
    }

    @Override
    public String toString() {
        return "CourseItem{" +
                "courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", credit=" + credit +
                ", nature='" + nature + '\'' +
                ", department='" + department + '\'' +
                ", openId=" + openId +
                ", peopleNum=" + peopleNum +
                ", leftNum=" + leftNum +
                '}';
    }
}
