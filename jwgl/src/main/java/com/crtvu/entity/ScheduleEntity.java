package com.crtvu.entity;

import java.util.Date;

/**
 * Created by danagi on 2017/4/9.
 */
public class ScheduleEntity extends OpenEntity{
    private int id;
    private int teacherId;
    private String classroom;
    private String className;
    private int day;
    private int startWeek;
    private int endWeek;
    private int startTime;
    private int endTime;

    public ScheduleEntity(){}

    public ScheduleEntity(int id, int teacherId, String classroom, String className, int day, int startWeek, int endWeek, int startTime, int endTime) {
        this.id = id;
        this.teacherId = teacherId;
        this.classroom = classroom;
        this.className = className;
        this.day = day;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getEndTime() {
        return endTime;
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

    @Override
    public String toString() {
        return "ScheduleEntity{" +
                "id=" + id +
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
