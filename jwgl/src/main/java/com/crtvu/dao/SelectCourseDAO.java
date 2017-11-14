package com.crtvu.dao;

import com.crtvu.entity.SelectCourseEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by x6012 on 2017/3/17.
 */
public interface SelectCourseDAO {


    /**
     * 添加评价
     * @param studentId
     * @param openId
     * @param evaluate
     */
    void updateEvaluate(@Param("studentId") int studentId, @Param("openId") int openId, @Param("evaluate") String evaluate);
    /**
     *
     * @param studentId
     * @param openId
     * @param grade
     * 添加一门的成绩
     */

    void updateGrade(@Param("studentId") int studentId, @Param("openId") int openId, @Param("grade") float grade);

    /**
     * 根据id查看成绩
     * @param studentId
     * @return
     */
    List<SelectCourseEntity> selectGradeByStudentId(int studentId);

    /**
     * 查看单门成绩
     * @param studentId
     * @param openId
     * @return
     */
    SelectCourseEntity selectGradeByStuIdOpenId(@Param("studentId") int studentId, @Param("openId") int openId);

    /**
     * 根据openid找到选这门课的所有学生;
     * @param openId
     * @return
     */
    List<SelectCourseEntity> selectGradeByOpenId(int openId);

}
