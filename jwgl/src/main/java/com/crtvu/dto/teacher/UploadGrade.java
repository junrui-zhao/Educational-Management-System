package com.crtvu.dto.teacher;

/**
 * Created by Jixw on 2017/4/18.
 */
public class UploadGrade {

    private int studentId;

    private float grade;

    public UploadGrade(int studentId, float grade) {
        this.studentId = studentId;
        this.grade = grade;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "UploadGrade{" +
                "studentId=" + studentId +
                ", grade=" + grade +
                '}';
    }
}
