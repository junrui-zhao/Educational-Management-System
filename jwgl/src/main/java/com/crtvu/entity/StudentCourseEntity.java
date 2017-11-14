package com.crtvu.entity;

/**
 * Created by Phoenix on 2017/4/4.
 */
public class StudentCourseEntity {

    // 学生学号
    private int studentId;

    // 开设id
    private int openId;

    // 课程成绩
    private double grade;

    // 课程评价
    private String evaluate;

    public StudentCourseEntity() {
    }

    public StudentCourseEntity(int studentId, int openId, double grade, String evaluate) {
        this.studentId = studentId;
        this.openId = openId;
        this.grade = grade;
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

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    @Override
    public String toString() {
        return "StudentCourseEntity{" +
                "studentId=" + studentId +
                ", openId=" + openId +
                ", grade=" + grade +
                ", evaluate='" + evaluate + '\'' +
                '}';
    }
}
