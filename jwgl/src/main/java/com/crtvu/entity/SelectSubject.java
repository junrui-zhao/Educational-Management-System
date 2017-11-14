package com.crtvu.entity;

/**
 * Created by apple on 2017/3/17.
 */
public class SelectSubject {

    private int studentId;

    private int subjectId;

    private int status;

    //构造空函数
    public SelectSubject(){}

    //构造参数函数
    public SelectSubject
    (int studentId, int subjectId, int status) {

        this.studentId = studentId;
        this.subjectId = subjectId;
        this.status = status;

    }

    //get函数
    public int getStudentId() {

        return studentId;

    }

    public int getSubjectId() {

        return subjectId;

    }

    public int getStatus() {

        return status;

    }

    //set函数
    public void setStudentId(int studentId) {

        this.studentId = studentId;

    }

    public void setSubjectId(int subjectId) {

        this.subjectId = subjectId;

    }

    public void setStatus(int status) {

        this.status = status;

    }

    //tostring函数
    @Override
    public String toString() {

        return "SelectSubject{" +
                "studentId=" + studentId +
                ", subjectId=" + subjectId +
                ", status=" + status +
                '}';

    }
}
