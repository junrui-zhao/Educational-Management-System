package com.crtvu.service;

import com.crtvu.dto.student.StudentCourseGrade;
import com.crtvu.dto.student.StudentEvaluate;
import com.crtvu.dto.student.StudentStatistics;
import com.crtvu.entity.StudentEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentGradeService {

    /**
     * 得到学生成绩的综合信息
     * String year为“NO”时,不选择年份，term为0时，不选择学期!
     * @param studentId
     * @param schoolYear
     * @param term
     * @return
     */
    List<StudentCourseGrade> getByIdYearTerm(@Param("studentId") int studentId, @Param("schoolYear") String schoolYear, @Param("term") int term);

    /**
     * 根据studentID查询学生信息
     * @param studentId
     * @return
     */
    StudentEntity getStudentInfo(int studentId);

    /**
     * 得到评价信息
     * @param studentId
     * @param openId
     * @return
     */
    StudentEvaluate getEvaluate(int studentId, int openId);

    /**
     * 得到成绩统计
     * @param studentId
     * @return
     */
    StudentStatistics getStudentStat(int studentId);

    /**
     * 添加评价
     * @param studentId
     * @param openId
     * @param evaluate
     */
    void addEvaluate(int studentId, int openId, String evaluate);

    /**
     * 下载
     * @param studentId
     * @return
     */
    String download(int studentId);
}
