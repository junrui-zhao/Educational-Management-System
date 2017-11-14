package com.crtvu.entity;

import java.util.Date;

/**
 * Created by Phoenix on 2017/4/4.
 */
public class ArrangementEntity {

    // 安排序号
    private int arrangementId;

    // 开设Id
    private int openId;

    // 教师Id
    private int teacherId;

    // 教室名称
    private String classroom;

    // 班级名称
    private String className;

    // 上课日期
    private int day;

    // 上课星期
    private int startWeek;

    // 结课星期
    private int endWeek;

    // 上课时间(相对于当前星期)
    private int startTime;

    // 下课时间
    private int endTime;

    public ArrangementEntity() {
    }

    public ArrangementEntity(int arrangementId, int openId, int teacherId, String classroom, String className, int day, int startWeek, int endWeek, int startTime, int endTime) {
        this.arrangementId = arrangementId;
        this.openId = openId;
        this.teacherId = teacherId;
        this.classroom = classroom;
        this.className = className;
        this.day = day;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getArrangementId() {
        return arrangementId;
    }

    public void setArrangementId(int arrangementId) {
        this.arrangementId = arrangementId;
    }

    public int getOpenId() {
        return openId;
    }

    public void setOpenId(int openId) {
        this.openId = openId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(int startWeek) {
        this.startWeek = startWeek;
    }

    public int getEndWeek() {
        return endWeek;
    }

    public void setEndWeek(int endWeek) {
        this.endWeek = endWeek;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Arrangement{" +
                "arrangementId=" + arrangementId +
                ", openId=" + openId +
                ", teacherId=" + teacherId +
                ", classroom='" + classroom + '\'' +
                ", className='" + className + '\'' +
                ", day=" + day +
                ", startWeek=" + startWeek +
                ", endWeek=" + endWeek +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
