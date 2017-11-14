package com.crtvu.dto;

/**
 * 学生查看课题信息及文件信息
 * Created by zhao on 2017/4/1.
 */
public class StudentCheckDocument {
    private int subjectId;
    private String subjectName;
    private String teacherName;
    private String requirement;
    private String document;

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

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public StudentCheckDocument() {
    }

    public StudentCheckDocument(int subjectId, String subjectName, String teacherName, String requirement, String document) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.teacherName = teacherName;
        this.requirement = requirement;
        this.document = document;
    }

    @Override
    public String toString() {
        return "StudentCheckDocument{" +
                "subjectId=" + subjectId +
                ", subjectName='" + subjectName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", requirement='" + requirement + '\'' +
                ", document='" + document + '\'' +
                '}';
    }
}
