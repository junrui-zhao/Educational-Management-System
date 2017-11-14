package com.crtvu.dto.admin;

import java.util.List;

/**
 * Created by Jixw on 2017/4/7.
 */
public class MajorClassStatisticsList {

    private String major;

    private String year;

    private List<ClassStatistics> classStatisticsList ;

    public MajorClassStatisticsList() {
    }

    public MajorClassStatisticsList(String major, String year, List<ClassStatistics> classStatisticsList) {
        this.major = major;
        this.year = year;
        this.classStatisticsList = classStatisticsList;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<ClassStatistics> getClassStatisticsList() {
        return classStatisticsList;
    }

    public void setClassStatisticsList(List<ClassStatistics> classStatisticsList) {
        this.classStatisticsList = classStatisticsList;
    }

    @Override
    public String toString() {
        return "MajorClassStatisticsList{" +
                "major='" + major + '\'' +
                ", year='" + year + '\'' +
                ", classStatisticsList=" + classStatisticsList +
                '}';
    }
}
