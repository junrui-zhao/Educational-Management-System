package com.crtvu.entity;

public class ArrangeSubject {
  private int openId;
  private int teacherId;
  private String name;
  private String requirement;
  private int subjectId;

  public int getOpen_id() {
    return openId;
  }

  public void setOpen_id(int open_id) {
    this.openId = open_id;
  }

  public int getTeacher_id() {
    return teacherId;
  }

  public void setTeacher_id(int teacher_id) {
    this.teacherId = teacher_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRequirement() {
    return requirement;
  }

  public void setRequirement(String requirement) {
    this.requirement = requirement;
  }

  public int getSubject_id() {
    return subjectId;
  }

  public void setSubject_id(int subject_id) {
    this.subjectId = subject_id;
  }

  public ArrangeSubject() {
  }

  public ArrangeSubject(int openId, int teacherId, String name, String requirement, int subjectId) {
    this.openId = openId;
    this.teacherId = teacherId;
    this.name = name;
    this.requirement = requirement;
    this.subjectId = subjectId;
  }

  @Override
  public String toString() {
    return "ArrangeSubject{" +
            "openId=" + openId +
            ", teacherId=" + teacherId +
            ", name='" + name + '\'' +
            ", requirement='" + requirement + '\'' +
            ", subjectId=" + subjectId +
            '}';
  }
}
