package com.crtvu.dto;

import com.crtvu.entity.Student;

import java.util.List;

/**
 * 教师查看课题信息列表
 * Created by zhao on 2017/4/1.
 */
public class TeacherCheckSubjectItem {
    private int subjectId;
    private String subjectName;
    private String subjectRequirement;
    private List<Student> SelectedStudentList;

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

    public String getSubjectRequirement() {
        return subjectRequirement;
    }

    public void setSubjectRequirement(String subjectRequirement) {
        this.subjectRequirement = subjectRequirement;
    }

    public List<Student> getSelectedStudentList() {
        return SelectedStudentList;
    }

    public void setSelectedStudentList(List<Student> selectedStudentList) {
        SelectedStudentList = selectedStudentList;
    }

    public TeacherCheckSubjectItem() {
    }

    public TeacherCheckSubjectItem(int subjectId, String subjectName, String subjectRequirement, List<Student> selectedStudentList) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.subjectRequirement = subjectRequirement;
        SelectedStudentList = selectedStudentList;
    }

    @Override
    public String toString() {
        return "TeacherCheckSubjectItem{" +
                "subjectId='" + subjectId + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", subjectRequirement='" + subjectRequirement + '\'' +
                ", SelectedStudentList=" + SelectedStudentList +
                '}';
    }
}
