package com.crtvu.dto;

/**
 * Created by Phoenix on 2017/4/12.
 */
public class ScheduleJson {
    /*
    openId: openId,
                    teacherId: teacherId,
                    classroom: classroom,
                    className: className,
                    day: day,
                    startWeek: startWeek,
                    endWeek: endWeek,
                    startTime: startTime,
                    endTime: endTime
    * */

    private int openId;
    private int teacherId;
    private String classrooom;
    private String className;
    private int day;
    private int startWeek;
    private int endWeek;
    private int startTime;
    private int endTime;

    public ScheduleJson() {
    }

    public ScheduleJson(int openId, int teacherId, String classrooom, String className, int day, int startWeek, int endWeek, int startTime, int endTime) {
        this.openId = openId;
        this.teacherId = teacherId;
        this.classrooom = classrooom;
        this.className = className;
        this.day = day;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public String getClassrooom() {
        return classrooom;
    }

    public void setClassrooom(String classrooom) {
        this.classrooom = classrooom;
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
        return "ScheduleJson{" +
                "openId=" + openId +
                ", teacherId=" + teacherId +
                ", classrooom='" + classrooom + '\'' +
                ", className='" + className + '\'' +
                ", day=" + day +
                ", startWeek=" + startWeek +
                ", endWeek=" + endWeek +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
