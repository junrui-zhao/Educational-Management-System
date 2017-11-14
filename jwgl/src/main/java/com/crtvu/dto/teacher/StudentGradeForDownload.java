package com.crtvu.dto.teacher;

/**
 * Created by Jixw on 2017/4/1.
 */
public class StudentGradeForDownload {

    private int stuId;

    private String stuName;

    private String stuClass;

    private String stuMajor;

    private Float grade;

    public StudentGradeForDownload() {
    }

    public StudentGradeForDownload(int stuId, String stuName, String stuClass, String stuMajor, Float grade) {
        this.stuId = stuId;
        this.stuName = stuName;
        this.stuClass = stuClass;
        this.stuMajor = stuMajor;
        this.grade = grade;
    }

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
    }

    public String getStuMajor() {
        return stuMajor;
    }

    public void setStuMajor(String stuMajor) {
        this.stuMajor = stuMajor;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "StudentGradeForDownload{" +
                "stuId=" + stuId +
                ", stuName='" + stuName + '\'' +
                ", stuClass='" + stuClass + '\'' +
                ", stuMajor='" + stuMajor + '\'' +
                ", grade=" + grade +
                '}';
    }
}
