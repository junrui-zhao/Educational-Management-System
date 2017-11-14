package com.crtvu.dto;
import com.crtvu.service.CourseService;

public class ErrorCJson {

    private String courseId;
    private String errors;

    public ErrorCJson() {
    }

    public ErrorCJson(String courseId, String errors) {
        this.courseId = courseId;
        this.errors = errors;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "ErrorCJson{" +
                "courseId='" + courseId + '\'' +
                ", errors='" + errors + '\'' +
                '}';
    }
}
