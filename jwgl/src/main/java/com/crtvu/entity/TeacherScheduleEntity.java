package com.crtvu.entity;

/**
 * Created by danagi on 2017/4/10.
 */
public class TeacherScheduleEntity extends ScheduleEntity{
    private String teacherName;
    private String teacherTitle;
    private String teacherPassword;

    public TeacherScheduleEntity() {}

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherTitle() {
        return teacherTitle;
    }

    public void setTeacherTitle(String teacherTitle) {
        this.teacherTitle = teacherTitle;
    }

    public String getTeacherPassword() {
        return teacherPassword;
    }

    public void setTeacherPassword(String teacherPassword) {
        this.teacherPassword = teacherPassword;
    }
}
