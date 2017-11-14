package com.crtvu.dao;

import com.crtvu.entity.ArrangeSubject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Phoenix on 2017/5/7.
 */
public interface ArrangeSubjectDAO {
    /**
     * 查询所有毕设题目
     * @return
     */
    List<ArrangeSubject> queryAllArrangeSubject();

    /**
     * 添加毕设题目
     * @param subject
     * @return
     */
    int insertSubject(@Param("subject") ArrangeSubject subject);

    /**
     * 通过毕设id查询毕设
     * @param subjectId
     * @return
     */
    ArrangeSubject queryArrangeSubjectBySubjectId(int subjectId);

    /**
     * 通过教师id查询毕设
     * @param teacherId
     * @return
     */
    List<ArrangeSubject> queryArrangeSubjectByTeacherId(int teacherId);

    /**
     * 查询所有课题id
     * @return
     */
    List<Integer> queryAllArrangeSubjectId();

    /**
     * 删除课题
     * @param subjectId
     * @return
     */
    int deleteSubject(int subjectId);

    /**
     * 修改课题信息
     * @param subjectId
     * @return
     */
    int updateSubject(@Param("subjectId") int subjectId, @Param("subjectName") String subjectName, @Param("requirement") String requiremnt);


}
