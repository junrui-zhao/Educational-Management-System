package com.crtvu.dto;

/**
 * 学生查看课题信息列表
 * Created by zhao on 2017/4/1.
 */
public class StudentCheckSubjectItem {

    private int subjectId;
    private String subjectName;
    private String teacherName;
    private int subjectStatus;

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

    public int getSubjectStatus() {
        return subjectStatus;
    }

    public void setSubjectStatus(int subjectStatus) {
        this.subjectStatus = subjectStatus;
    }

    public StudentCheckSubjectItem() {
    }

    public StudentCheckSubjectItem(int subjectId, String subjectName, String teacherName, int subjectStatus) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.teacherName = teacherName;
        this.subjectStatus = subjectStatus;
    }

    @Override
    public String toString() {
        return "StudentCheckSubjectItem{" +
                "subjectId='" + subjectId + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", subjectStatus=" + subjectStatus +
                '}';
    }
}
