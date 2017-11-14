package com.crtvu.dto.Json;

/**
 * Created by Phoenix on 2017/4/9.
 */
public class CourseJson {

    private String courseId;

    public CourseJson() {
    }

    public CourseJson(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "CourseJson{" +
                "courseId='" + courseId + '\'' +
                '}';
    }
}
