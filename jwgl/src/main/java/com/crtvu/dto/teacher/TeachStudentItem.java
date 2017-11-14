package com.crtvu.dto.teacher;

/**
 * Created by Phoenix on 2017/4/5.
 */
public class TeachStudentItem {

    // 学生学号
    private int studentId;

    // 学生姓名
    private String studentName;

    // 学生班级
    private String studentClassName;

    // 学生专业
    private String studentMajor;

    // 课程名称
    private String courseName;

    public TeachStudentItem() {
    }

    public TeachStudentItem(int studentId, String studentName, String studentClassName, String studentMajor, String courseName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentClassName = studentClassName;
        this.studentMajor = studentMajor;
        this.courseName = courseName;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentClassName() {
        return studentClassName;
    }

    public void setStudentClassName(String studentClassName) {
        this.studentClassName = studentClassName;
    }

    public String getStudentMajor() {
        return studentMajor;
    }

    public void setStudentMajor(String studentMajor) {
        this.studentMajor = studentMajor;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "TeachStudentItem{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studentClassName='" + studentClassName + '\'' +
                ", studentMajor='" + studentMajor + '\'' +
                ", courseName='" + courseName + '\'' +
                '}';
    }
}
