package com.crtvu.service;

import com.crtvu.dto.CourseOpenInfo;
import com.crtvu.dto.admin.ClassStatistics;
import com.crtvu.dto.admin.MajorClassStatisticsList;
import com.crtvu.dto.admin.StudentGradeForManager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Jixw on 2017/4/6.
 */
public interface AdminGradeService {

    /**
     * 按专业学年查询一个专业的统计信息
     * @param major
     * @param year
     * @return
     */
    List<ClassStatistics> getClassStatistics(String major, String year);

    /**
     * 查询一个专业list的统计信息
     * @param majorList
     * @param year
     * @return
     */
    List<MajorClassStatisticsList> getClassStatisticsList(List<String> majorList, String year);

    /**
     * 得到所有的专业名称
     * @param year
     * @return
     */
    List<String> getMajorList(String year);

    /**
     * 根据year，term查询课程详细信息列表
     * @param schoolYear
     * @param term
     * @return
     */
    List<CourseOpenInfo> getCourseOpenInfoByYearTerm(@Param("schoolYear") String schoolYear, @Param("term") int term);

    /**
     * 得到学生成绩信息
     * @param openId
     * @return
     */
    List<StudentGradeForManager> getGradeByOpenid(int openId);
}
