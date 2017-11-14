package com.crtvu.dto.student;

import com.crtvu.entity.CourseEntity;

public class StudentEvaluate {

    private int studentId;

    private int openId;

    private CourseEntity courseEntity;

    private TeacherNoPwd teacher;

    private String evaluate;

    public StudentEvaluate(int studentId, int openId, CourseEntity courseEntity, TeacherNoPwd teacher, String evaluate) {
        this.studentId = studentId;
        this.openId = openId;
        this.courseEntity = courseEntity;
        this.teacher = teacher;
        this.evaluate = evaluate;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getOpenId() {
        return openId;
    }

    public void setOpenId(int openId) {
        this.openId = openId;
    }

    public CourseEntity getCourseEntity() {
        return courseEntity;
    }

    public void setCourseEntity(CourseEntity courseEntity) {
        this.courseEntity = courseEntity;
    }

    public TeacherNoPwd getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherNoPwd teacher) {
        this.teacher = teacher;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    @Override
    public String toString() {
        return "StudentEvaluate{" +
                "studentId=" + studentId +
                ", openId=" + openId +
                ", courseEntity=" + courseEntity +
                ", teacher=" + teacher +
                ", evaluate='" + evaluate + '\'' +
                '}';
    }
}
