package com.crtvu.dto;

/**
 * Created by yangming on 2017/3/28/0028.
 */

public class DeleteCJson {

    private int page;
    private String courseId;

    public DeleteCJson() {
    }

    public DeleteCJson(int page) {
        this.page = page;
    }

    public DeleteCJson(int page, String courseId) {
        this.page = page;
        this.courseId = courseId;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "DeleteCJson{" +
                "page=" + page +
                ", courseId='" + courseId + '\'' +
                '}';
    }
}
