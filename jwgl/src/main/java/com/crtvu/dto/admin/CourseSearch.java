package com.crtvu.dto.admin;

/**
 * Created by cxd on 2017/4/12.
 */
public class CourseSearch {

    // 课程名称
    private String courseName;

    // 课程容量
    private int peopleNum;

    // 选课人数
    private int leftNum;

    public CourseSearch() {
    }

    public CourseSearch( String courseName, int peopleNum, int leftNum) {
        this.courseName = courseName;
        this.peopleNum = peopleNum;
        this.leftNum = leftNum;
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
        return "CourseSearch{" +
                ", courseName='" + courseName + '\'' +
                ", peopleNum=" + peopleNum +
                ", leftNum=" + leftNum +
                '}';
    }
}
