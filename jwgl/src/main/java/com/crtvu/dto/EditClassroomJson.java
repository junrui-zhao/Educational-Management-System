package com.crtvu.dto;

/**
 * Created by danagi on 2017/4/7.
 */
public class EditClassroomJson {
    private String classroom;
    private String newRoomName;
    private int peopleNum;

    public EditClassroomJson(){}

    public EditClassroomJson(String classroom,String newRoomName,int peopleNum){
        this.classroom = classroom;
        this.newRoomName = newRoomName;
        this.peopleNum = peopleNum;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setNewRoomName(String newRoomName) {
        this.newRoomName = newRoomName;
    }

    public String getNewRoomName() {
        return newRoomName;
    }

    public void setPeopleNum(int peopleNum) {
        this.peopleNum = peopleNum;
    }

    public int getPeopleNum() {
        return peopleNum;
    }
}
