package com.crtvu.entity;

/**
 * Created by danagi on 2017/4/6.
 */
public class ClassroomEntity {
    private String classroom;
    private int peopleNum;

    public ClassroomEntity(){}

    public ClassroomEntity(String classroom,int peopleNum){
        this.classroom = classroom;
        this.peopleNum = peopleNum;
    }

    public int getPeopleNum() {
        return peopleNum;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public void setPeopleNum(int peopleNum) {
        this.peopleNum = peopleNum;
    }

    @Override
    public String toString() {
        return "ClassroomEntity{"
                +"classroom = '"+classroom+"', "
                +"peopleNum = '"+peopleNum+"'}";
    }
}
