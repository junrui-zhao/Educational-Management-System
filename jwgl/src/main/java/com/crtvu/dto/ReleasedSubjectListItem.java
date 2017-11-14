package com.crtvu.dto;

/**
 * 已发布的课题
 * Created by zhao on 2017/3/31.
 */
public class ReleasedSubjectListItem {

    private int subjectId;

    private String subjectName;

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

    public int getSubjectStatus() {
        return subjectStatus;
    }

    public void setSubjectStatus(int subjectStatus) {
        this.subjectStatus = subjectStatus;
    }

    public ReleasedSubjectListItem() {
    }

    public ReleasedSubjectListItem(int subjectId, String subjectName, int subjectStatus) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.subjectStatus = subjectStatus;
    }

    @Override
    public String toString() {
        return "ReleasedSubjectListItem{" +
                "subjectId='" + subjectId + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", subjectStatus=" + subjectStatus +
                '}';
    }
}
