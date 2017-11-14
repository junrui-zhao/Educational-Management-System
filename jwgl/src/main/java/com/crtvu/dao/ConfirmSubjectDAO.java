package com.crtvu.dao;

import com.crtvu.entity.ConfirmSubject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Phoenix on 2017/5/7.
 */
public interface ConfirmSubjectDAO {
    /**
     * 添加定题学生
     * @param confirmSubject
     * @return
     */
    int insertConfirmSubject(@Param("confirmSubject") ConfirmSubject confirmSubject);

    /**
     * 通过学生id查询学生毕设选择情况
     * @param studentId
     * @return
     */
    ConfirmSubject queryStudentSubjectByStudentId(int studentId);

    /**
     * 查询所有已选毕设id
     * @return
     */
    List<Integer> queryAllConfirmSubjectId();

    /**
     * 通过学生id更改学生毕设成绩
     * @param studentId
     * @param subjectGrade
     * @return
     */
    int updateStudentSubjectGradeByStudentId(@Param("studentId") int studentId, @Param("subjectGrade") float subjectGrade);

    /**
     * 通过毕设id更改学生毕设成绩
     * @param subjectId
     * @param subjectGrade
     * @return
     */
    int updateStudentSubjectGradeBySubjectId(@Param("subjectId") int subjectId, @Param("subjectGrade") float subjectGrade);

    /**
     * 通过学生id更改学生文档
     * @param studentId
     * @param subjectDocument
     * @return
     */
    int updateStudentDocumentByStudentId(@Param("studentId") int studentId, @Param("subjectDocument") String subjectDocument);

    /**
     * 通过毕设id更改学生毕设文档
     * @param subjectId
     * @param subjectDocument
     * @return
     */
    int updateStudentDocumentBySubjectId(@Param("subjectId") int subjectId, @Param("subjectDocument") String subjectDocument);

    /**
     * 通过毕设Id查看毕设
     * @param subjectId
     * @return
     */
    ConfirmSubject querySubjectBySubjectId(int subjectId);

    List<ConfirmSubject> queryAllConfirmSubject();

    int deleteSubjectBySubjectId(int subjectId);

    int cancelSubject(int subjectId);
}
