package com.crtvu.dto.teacher;

/**
 * Created by Jixw on 2017/3/31.
 */
public class UpdateGrade {

    private int studentId;

    private int openId;

    private Float grade;

    public UpdateGrade() {
    }

    public UpdateGrade(int studentId, int openId, Float grade) {
        this.studentId = studentId;
        this.openId = openId;
        this.grade = grade;
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

    public float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "UpdateGrade{" +
                "studentId=" + studentId +
                ", openId=" + openId +
                ", grade=" + grade +
                '}';
    }
}
