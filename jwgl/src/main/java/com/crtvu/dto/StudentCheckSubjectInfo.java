package com.crtvu.dto;

/**
 * 学生查看课题详细信息
 * Created by zhao on 2017/4/1.
 */
public class StudentCheckSubjectInfo {
    private int subjectId;
    private String subjectName;
    private String teacherName;
    private String requirement;

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

    public StudentCheckSubjectInfo() {
    }

    public StudentCheckSubjectInfo(int subjectId, String subjectName, String teacherName, String requirement) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.teacherName = teacherName;
        this.requirement = requirement;
    }

    @Override
    public String toString() {
        return "StudentCheckSubjectInfo{" +
                "subjectId=" + subjectId +
                ", subjectName='" + subjectName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", requirement='" + requirement + '\'' +
                '}';
    }
}
