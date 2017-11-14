package com.crtvu.dto;

/**
 * 教师查看课题详细信息及文档
 * Created by zhao on 2017/4/1.
 */
public class TeacherCheckDocumentItem {

    private int subjectId;
    private String subjectName;
    private String requirement;
    private int studentId;
    private String studentName;
    private String document;
    private int status;

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
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

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public TeacherCheckDocumentItem() {
    }

    public TeacherCheckDocumentItem(int subjectId, String subjectName, String requirement, int studentId, String studentName, String document, int status) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.requirement = requirement;
        this.studentId = studentId;
        this.studentName = studentName;
        this.document = document;
        this.status = status;
    }

    @Override
    public String toString() {
        return "TeacherCheckDocumentItem{" +
                "subjectId=" + subjectId +
                ", subjectName='" + subjectName + '\'' +
                ", requirement='" + requirement + '\'' +
                ", studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", document='" + document + '\'' +
                ", status=" + status +
                '}';
    }
}
