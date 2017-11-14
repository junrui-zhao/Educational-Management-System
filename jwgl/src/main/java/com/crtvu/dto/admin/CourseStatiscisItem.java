package com.crtvu.dto.admin;

import com.crtvu.entity.Course;
import com.crtvu.entity.Student;

import java.util.LinkedList;

/**
 * Created by Phoenix on 2017/4/6.
 */
public class CourseStatiscisItem {

    // 课程代号
    private String courseId;

    // 课程名称
    private String courseName;

    // 课程容量
    private int peopleNum;

    // 选课人数
    private int leftNum;

    public CourseStatiscisItem() {
    }

    public CourseStatiscisItem(String courseId, String courseName, int peopleNum, int leftNum) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.peopleNum = peopleNum;
        this.leftNum = leftNum;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(int peopleNum) {
        this.peopleNum = peopleNum;
    }

    public int getLeftNum() {
        return leftNum;
    }

    public void setLeftNum(int leftNum) {
        this.leftNum = leftNum;
    }

    @Override
    public String toString() {
        return "CourseStatiscisItem{" +
                "courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", peopleNum=" + peopleNum +
                ", leftNum=" + leftNum +
                '}';
    }
}
