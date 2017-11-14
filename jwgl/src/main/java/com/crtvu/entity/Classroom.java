package com.crtvu.entity;

/**
 * Created by Phoenix on 2017/4/4.
 */
public class Classroom {

    // 教室名称
    private String classroom;

    // 教室容纳人数
    private int peopleNum;

    public Classroom() {
    }

    public Classroom(String classroom, int peopleNum) {
        this.classroom = classroom;
        this.peopleNum = peopleNum;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public int getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(int peopleNum) {
        this.peopleNum = peopleNum;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "classroom='" + classroom + '\'' +
                ", peopleNum=" + peopleNum +
                '}';
    }
}
