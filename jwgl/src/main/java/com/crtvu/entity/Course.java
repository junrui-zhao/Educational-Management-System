package com.crtvu.entity;

/**
 * Created by Phoenix on 2017/4/4.
 */
public class Course {

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

    public Course() {
    }

    public Course(String courseId, String courseName, Double credit, String nature, String department) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credit = credit;
        this.nature = nature;
        this.department = department;
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

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", credit=" + credit +
                ", nature='" + nature + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
