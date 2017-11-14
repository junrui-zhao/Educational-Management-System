package com.crtvu.entity;

/**
 * Created by lcf12 on 2017/3/15.
 */
public class StudentEntity {

    private int studentId;

    private String studentName;

    private String className;

    private String major;

    private String password;

    public StudentEntity() {
    }

    public StudentEntity(int studentId, String studentName, String className, String major, String password) {
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
        return "StudentEntity{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", className='" + className + '\'' +
                ", major='" + major + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
