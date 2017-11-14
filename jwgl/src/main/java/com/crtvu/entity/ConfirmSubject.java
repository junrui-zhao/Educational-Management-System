package com.crtvu.entity;

/**
 * Created by apple on 2017/3/17.
 */
public class ConfirmSubject {

    private int studentId;

    private int subjectId;

    private float grade;

    private String document;

    //构造空函数
    public ConfirmSubject(){}

    //构造参数函数
    public ConfirmSubject
    (int studentId, int subjectId, float grade, String document) {

        this.studentId = studentId;
        this.subjectId = subjectId;
        this.grade = grade;
        this.document = document;

    }

    //get函数
    public int getStudentId() {

        return studentId;

    }

    public void setStudentId(int studentId) {

        this.studentId = studentId;

    }

    public int getSubjectId() {

        return subjectId;

    }

    public void setSubjectId(int subjectId) {

        this.subjectId = subjectId;

    }

    public float getGrade() {

        return grade;

    }

    //set函数
    public void setGrade(float grade) {

        this.grade = grade;

    }

    public String getDocument() {

        return document;

    }

    public void setDocument(String document) {

        this.document = document;

    }

    //tostring函数
    @Override
    public String toString() {

        return "ConfirmSubject{" +
                "studentId=" + studentId +
                ", subjectId=" + subjectId +
                ", grade=" + grade +
                ", document='" + document + '\'' +
                '}';

    }
}
