package com.crtvu.dto.teacher;

import java.util.List;

/**
 * Created by Jixw on 2017/3/31.
 */
public class UpdateGradeModel {
    private List<UpdateGrade> updateGradeList;

    public UpdateGradeModel() {
    }

    public List<UpdateGrade> getStuGradeList() {
        return updateGradeList;
    }

    public void setStuGradeList(List<UpdateGrade> stuGradeList) {
        this.updateGradeList = stuGradeList;
    }

    @Override
    public String toString() {
        return "UpdateGradeModel{" +
                "stuGradeList=" + updateGradeList +
                '}';
    }

}
