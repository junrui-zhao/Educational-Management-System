package com.crtvu.dto.student;

public class UpdateEvaluate {

    private int xueshu;

    private int jiaoxue;

    private int taidu;

    private String other;

    public UpdateEvaluate() {
    }

    public UpdateEvaluate(int xueshu, int jiaoxue, int taidu, String other) {
        this.xueshu = xueshu;
        this.jiaoxue = jiaoxue;
        this.taidu = taidu;
        this.other = other;
    }

    public int getXueshu() {
        return xueshu;
    }

    public void setXueshu(int xueshu) {
        this.xueshu = xueshu;
    }

    public int getJiaoxue() {
        return jiaoxue;
    }

    public void setJiaoxue(int jiaoxue) {
        this.jiaoxue = jiaoxue;
    }

    public int getTaidu() {
        return taidu;
    }

    public void setTaidu(int taidu) {
        this.taidu = taidu;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public String toString() {
        return "UpdateEvaluate{" +
                "xueshu=" + xueshu +
                ", jiaoxue=" + jiaoxue +
                ", taidu=" + taidu +
                ", other='" + other + '\'' +
                '}';
    }

    public String toStringT(){
        return "xueshu:"+xueshu+","+"jiaoxuue:"+jiaoxue+","+"taidu:"+taidu+","+"other:"+other;
    }

}
