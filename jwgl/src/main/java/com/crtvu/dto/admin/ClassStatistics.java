package com.crtvu.dto.admin;

/**
 * Created by Jixw on 2017/4/6.
 */
public class ClassStatistics {

    String className;

    float average;   //平均成绩GPA

    float excellentRate;  //优秀率

    public ClassStatistics(String className, float average, float excellentRate) {
        this.className = className;
        this.average = average;
        this.excellentRate = excellentRate;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }

    public float getExcellentRate() {
        return excellentRate;
    }

    public void setExcellentRate(float excellentRate) {
        this.excellentRate = excellentRate;
    }

    @Override
    public String toString() {
        return "ClassStatistics{" +
                "className='" + className + '\'' +
                ", average=" + average +
                ", excellentRate=" + excellentRate +
                '}';
    }
}
