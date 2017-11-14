package com.crtvu.dto.student;

import java.util.List;

/**
 * Created by Jixw on 2017/4/30.
 */
public class StudentStatistics {

    private int studentId;

    private float gpa;

    private float creditGotCom; //必修已获得学分

    private float creditGotEle; //选修已获得学分

    private List<StudentCourseGrade> failList;//挂科信息

    public StudentStatistics() {
    }

    public StudentStatistics(int studentId, float gpa, float creditGotCom, float creditGotEle, List<StudentCourseGrade> failList) {
        this.studentId = studentId;
        this.gpa = gpa;
        this.creditGotCom = creditGotCom;
        this.creditGotEle = creditGotEle;
        this.failList = failList;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public float getCreditGotCom() {
        return creditGotCom;
    }

    public void setCreditGotCom(float creditGotCom) {
        this.creditGotCom = creditGotCom;
    }

    public float getCreditGotEle() {
        return creditGotEle;
    }

    public void setCreditGotEle(float creditGotEle) {
        this.creditGotEle = creditGotEle;
    }

    public List<StudentCourseGrade> getFailList() {
        return failList;
    }

    public void setFailList(List<StudentCourseGrade> failList) {
        this.failList = failList;
    }

    @Override
    public String toString() {
        return "StudentStatistics{" +
                "studentId=" + studentId +
                ", gpa=" + gpa +
                ", creditGotCom=" + creditGotCom +
                ", creditGotEle=" + creditGotEle +
                ", failList=" + failList +
                '}';
    }
}
