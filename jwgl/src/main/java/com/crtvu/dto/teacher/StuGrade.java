package com.crtvu.dto.teacher;

/**
 * Created by Jixw on 2017/3/29.
 */
public class StuGrade {

    private int studentId;

    private String studentName;

    private int openId;

    private Float grade;

    public StuGrade() {
    }

    public StuGrade(int studentId, String studentName, int openId, Float grade) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.openId = openId;
        this.grade = grade;
    }

    public int getOpenId() {
        return openId;
    }

    public void setOpenId(int openId) {
        this.openId = openId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "StuGrade{" +
                "studentId=" + studentId +
                ", name='" + studentName + '\'' +
                ", openId=" + openId +
                ", grade=" + grade +
                '}';
    }
}
