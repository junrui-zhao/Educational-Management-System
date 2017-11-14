package com.crtvu.entity;

/**
 * Created by Phoenix on 2017/4/4.
 */
public class TeacherCourse {

    // 教师Id
    private int teacherId;

    // 开设Id
    private int openId;

    public TeacherCourse() {
    }

    public TeacherCourse(int teacherId, int openId) {
        this.teacherId = teacherId;
        this.openId = openId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getOpenId() {
        return openId;
    }

    public void setOpenId(int openId) {
        this.openId = openId;
    }

    @Override
    public String toString() {
        return "TeacherCourse{" +
                "teacherId=" + teacherId +
                ", openId=" + openId +
                '}';
    }
}
