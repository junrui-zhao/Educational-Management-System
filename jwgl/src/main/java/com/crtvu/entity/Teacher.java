package com.crtvu.entity;

/**
 * Created by Phoenix on 2017/4/4.
 */
public class Teacher {

    // 教师Id
    private int teacherId;

    // 教师姓名
    private String teacherName;

    // 教师职称
    private String title;

    // 教师密码
    private String password;

    public Teacher() {
    }

    public Teacher(int teacherId, String teacherName, String title, String password) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.title = title;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", title='" + title + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
