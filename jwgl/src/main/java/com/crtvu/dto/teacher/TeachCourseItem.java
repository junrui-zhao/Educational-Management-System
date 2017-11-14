package com.crtvu.dto.teacher;

/**
 * Created by Phoenix on 2017/4/5.
 */
public class TeachCourseItem {

    // 课程名称
    private String courseName;

    // 所在教室
    private String classroomName;

    // 上课星期
    private int day;

    // 上课时间
    private String classTime;

    public static String reamkeClassTime(int startWeek, int endWeek, int startTime, int endTime) {
        return "第"+startWeek+"周至"+endWeek+",第"+startTime+"至第"+endTime+"节";
    }

    public TeachCourseItem() {
    }

    public TeachCourseItem(String courseName, String classroomName, int day, String classTime) {
        this.courseName = courseName;
        this.classroomName = classroomName;
        this.day = day;
        this.classTime = classTime;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    @Override
    public String toString() {
        return "TeachCourseItem{" +
                "courseName='" + courseName + '\'' +
                ", classroomName='" + classroomName + '\'' +
                ", day=" + day +
                ", classTime='" + classTime + '\'' +
                '}';
    }
}
