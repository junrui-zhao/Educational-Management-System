package com.crtvu.entity;

/**
 * Created by x6012 on 2017/3/17.
 */
public class SelectCourseEntity {

    private int studentId;

    private int openId;

    private Float grade;

    private  String evaluate;

    public SelectCourseEntity() {
    }

    public SelectCourseEntity(int studentId, int openid, float grade) {
        this.studentId = studentId;
        this.openId = openid;
        this.grade = grade;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getOpenid() {
        return openId;
    }

    public void setOpenid(int openid) {
        this.openId = openid;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public float getPoint() {
        //如果成绩为空,返回-1
        if(this.grade == null){
            return -1;
        }else if(this.grade<82){
            if(this.grade<=81 && this.grade>=78) return 3.0f;
            if(this.grade<=77 && this.grade>=75) return 2.7f;
            if(this.grade<=74 && this.grade>=72) return 2.3f;
            if(this.grade<=71 && this.grade>=68) return 2.0f;
            if(this.grade<=67 && this.grade>=64) return 1.5f;
            if(this.grade<=63 && this.grade>=60) return 1.0f;
            if(this.grade<60) return 0;
        } else if(this.grade>=82) {
            if (this.grade <= 84 && this.grade >= 82) return 3.3f;
            if (this.grade <= 89 && this.grade >= 85) return 3.7f;
            if (this.grade <= 94 && this.grade >= 90) return 4.0f;
            if (this.grade <= 100 && this.grade >= 95) return 4.3f;
        }
        return  0;
    }

    @Override
    public String toString() {
        return "SelectCourseEntity{" +
                "studentId=" + studentId +
                ", openId=" + openId +
                ", grade=" + grade +
                ", evaluate='" + evaluate + '\'' +
                '}';
    }
}
