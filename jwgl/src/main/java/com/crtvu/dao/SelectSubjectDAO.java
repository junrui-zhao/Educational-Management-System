package com.crtvu.dao;

import com.crtvu.entity.SelectSubject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Phoenix on 2017/5/7.
 */
public interface SelectSubjectDAO {
    /**
     * 添加预选学生
     * @param selectSubject
     * @return
     */
    int insertSelectSubject(@Param("selectSubject") SelectSubject selectSubject);

    /**
     * 通过毕设id查询学生预选情况
     * @param subjectId
     * @return
     */
    List<SelectSubject> querySelectSubjectBySubjectId(int subjectId);


    /**
     * 通过课程id更改学生选题状态
     * @param subjectId
     * @param studentStatus
     * @return
     */
    int updateStatusBySubjectId(@Param("subjectId") int subjectId, @Param("studentStatus") int studentStatus);

    /**
     * 通过毕设id和状态查询学生预选情况
     * @return
     */
    List<SelectSubject> querySelectSubjectBySubjectIdAndStatus(@Param("subjectId") int subjectId, @Param("status") int status);

    /**
     * 通过学生id和状态查询预选情况
     * @param studentId
     * @param status
     * @return subjectId
     */
    List<SelectSubject> querySelectSubjectByStudentIdAndStatus(@Param("studentId") int studentId, @Param("status") int status);

    /**
     * 通过学生Id查询某学生已选过的课题id
     * @param studentId
     * @return
     */
    List<Integer> queryAllSelectSubjectIdByStudentId(int studentId);

    /**
     * 通过毕设id和学生id查询课题
     * @param subjectId
     * @param studentId
     * @return
     */
    SelectSubject queryStudentSubjectBySubjectIdAndStudentId(@Param("subjectId") int subjectId, @Param("studentId") int studentId);

    /**
     * 通过毕设id和学生id删除学生
     * @param subjectId
     * @param studentId
     * @return
     */
    int deleteStudentBySubjectIdAndStudentId(@Param("subjectId") int subjectId, @Param("studentId") int studentId);

    /**
     * 根据课题id删除课题
     * @param subjectId
     * @return
     */
    int deleteSubjectBySubjectId(int subjectId);

    /**
     * 查询已申请人数
     * @param subjectId
     * @return
     */
    int querySelectNumber(int subjectId);
}
