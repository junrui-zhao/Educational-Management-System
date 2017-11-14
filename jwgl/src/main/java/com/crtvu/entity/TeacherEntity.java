package com.crtvu.entity;

/**
 * Created by Phoenix on 2017/4/11.
 */

public class TeacherEntity {

    private int teacherId;

    private String teacherName;

    private String title;

    private String password;

    public TeacherEntity() {
    }

    public TeacherEntity(int teacherId, String teacherName, String title, String password) {
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
        return "TeacherEntity{" +
                "teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", title='" + title + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
