package com.crtvu.entity;

/**
 * Created by yangming on 2017/3/16/0016.
 */
public class CourseEntity {
    private String courseId;
    private String courseName;
    private Float  credit;
    private String nature;
    private String department;

    public CourseEntity(String courseId, String courseName, String nature, String department) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.nature = nature;
        this.department = department;
    }

    public CourseEntity(String courseId, String courseName, Float credit, String nature, String department) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credit = credit;
        this.nature = nature;
        this.department = department;
    }

    public CourseEntity() {
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

    public Float getCredit() {
        return credit;
    }

    public void setCredit(Float credit) {
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
        return "CourseEntity{" +
                "courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", credit=" + credit +
                ", nature='" + nature + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
