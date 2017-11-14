package com.crtvu.dto.admin;

/**
 * Created by Jixw on 2017/4/11.
 */
public class StudentGradeForManager {

    private int studentId;

    private String studentName;

    private String className;           //学生班级

    private String major;

    private Float grade;

    public StudentGradeForManager(int studentId, String studentName, String className, String major, Float grade) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.className = className;
        this.major = major;
        this.grade = grade;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "StudentGradeForManager{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", className='" + className + '\'' +
                ", major='" + major + '\'' +
                ", grade=" + grade +
                '}';
    }
}
