package com.crtvu.entity;

/**
 * Created by Phoenix on 2017/4/4.
 */
public class Student {

    // 学生学号
    private int studentId;

    // 学生姓名
    private String studentName;

    // 学生班级
    private String className;

    // 学生专业
    private String major;

    // 学生账号密码
    private String password;

    public Student() {
    }

    public Student(int studentId, String studentName, String className, String major, String password) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.className = className;
        this.major = major;
        this.password = password;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", className='" + className + '\'' +
                ", major='" + major + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
