package com.crtvu.dto.teacher;

import com.crtvu.dto.CourseOpenInfo;

import java.util.List;

/**
 * Created by Jixw on 2017/3/30.
 */
public class TeacherAllCourse {

    private int teacherId;

    private String teacherName;

    private String title;

    private List<CourseOpenInfo> courseOpenInfoList;

    public TeacherAllCourse() {
    }

    public TeacherAllCourse(int teacherId, String teacherName, String title, List<CourseOpenInfo> courseOpenInfoList) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.title = title;
        this.courseOpenInfoList = courseOpenInfoList;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CourseOpenInfo> getCourseOpenInfoList() {
        return courseOpenInfoList;
    }

    public void setCourseOpenInfoList(List<CourseOpenInfo> courseOpenInfoList) {
        this.courseOpenInfoList = courseOpenInfoList;
    }

    @Override
    public String toString() {
        return "TeacherAllCourse{" +
                "teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", title='" + title + '\'' +
                ", courseOpenInfoList=" + courseOpenInfoList +
                '}';
    }
}
