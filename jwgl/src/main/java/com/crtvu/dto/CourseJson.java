package com.crtvu.dto;

/**
 * Created by yangming on 2017/3/28/0028.
 */
public class CourseJson {
    private String courseId;
    private String courseName;
    private Float credit;
    private String nature;
    private String department;
    private boolean success;
    private String error;

    @Override
    public String toString() {
        return "CourseJson{" +
                "courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", credit=" + credit +
                ", nature='" + nature + '\'' +
                ", department='" + department + '\'' +
                ", success=" + success +
                ", error='" + error + '\'' +
                '}';
    }
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public CourseJson() {
    }

    public CourseJson(String courseId, String courseName, Float credit, String nature, String department, boolean success, String error) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credit = credit;
        this.nature = nature;
        this.department = department;
        this.success = success;
        this.error = error;
    }

    public CourseJson(String courseId, String courseName, Float credit, String nature, String department) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credit = credit;
        this.nature = nature;
        this.department = department;
    }

    public CourseJson(String courseId, String courseName, String nature, String department) {
        this.courseId = courseId;
        this.courseName = courseName;
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
}