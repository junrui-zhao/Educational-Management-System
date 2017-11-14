package com.crtvu.entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by yangming on 2017/4/27/0027.
 */
public class InfoEntity {

    private int id;
    private String title;
    private String context;
    private Timestamp time;
    private String sender;
    private int studentId;
    private int hasRead;

    public InfoEntity() {
    }

    public InfoEntity(int id, String title, String context, Timestamp time, String sender, int studentId, int hasRead) {
        this.id = id;
        this.title = title;
        this.context = context;
        this.time = time;
        this.sender = sender;
        this.studentId = studentId;
        this.hasRead = hasRead;
    }

    public InfoEntity(String title, String context, Timestamp time, String sender, int studentId) {
        this.title = title;
        this.context = context;
        this.time = time;
        this.sender = sender;
        this.studentId = studentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getHasRead() {
        return hasRead;
    }

    public void setHasRead(int hasRead) {
        this.hasRead = hasRead;
    }

    @Override
    public String toString() {
        return "InfoEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", context='" + context + '\'' +
                ", time=" + time +
                ", sender='" + sender + '\'' +
                ", studentId=" + studentId +
                ", hasRead=" + hasRead +
                '}';
    }
}
