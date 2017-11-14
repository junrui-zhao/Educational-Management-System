package com.crtvu.dto.student;

import com.crtvu.entity.CourseEntity;
import com.crtvu.entity.OpenEntity;

/**
 * Created by x6012 on 2017/3/22.
 */
public class StudentCourseGrade {

    private int studentId;

    private String courseId;

    private int openId;

    private String courseName;

    private float credit;

    private String nature;

    private String schoolYear;

    private int term;

    private Float grade;

    public StudentCourseGrade() {
    }

    public StudentCourseGrade(int studentId, CourseEntity courseEntity, OpenEntity openEntity, float grade){
        this.studentId = studentId;
        this.courseId = courseEntity.getCourseId();
        this.openId = openEntity.getOpenId();
        this.courseName = courseEntity.getCourseName();
        this.credit = courseEntity.getCredit();
        this.nature = courseEntity.getNature();
        this.schoolYear = openEntity.getSchoolYear();
        this.term = openEntity.getTerm();
        this.grade = grade;
    }

    public StudentCourseGrade(int studentId, String courseId, int openId, String courseName, float credit, String nature, String schoolYear, int term, Float grade) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.openId = openId;
        this.courseName = courseName;
        this.credit = credit;
        this.nature = nature;
        this.schoolYear = schoolYear;
        this.term = term;
        this.grade = grade;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
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

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "StudentCourseGrade{" +
                "studentId=" + studentId +
                ", courseId='" + courseId + '\'' +
                ", openId='" + openId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", credit=" + credit +
                ", nature='" + nature + '\'' +
                ", schoolYear='" + schoolYear + '\'' +
                ", term=" + term +
                ", grade=" + grade +
                '}';
    }
}
