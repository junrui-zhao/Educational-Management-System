package com.crtvu.dao;

import com.crtvu.entity.Student;
import com.crtvu.entity.StudentEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lcf12 on 2017/3/15.
 */
public interface StudentDAO {

    /**
     * 添加学生实体
     * @param studentId
     * @param studentName
     * @param className
     * @param major
     * @param password
     */
    void insertStudent(@Param("studentId") int studentId, @Param("studentName") String studentName, @Param("className") String className, @Param("major") String major, @Param("password") String password);

    /**
     * 按照id删除学生
     * @param studentId
     */
    int deleteStudent(@Param("studentId") int studentId);

    /**
     * 修改该同学的密码
     * @param studentId
     * @param password
     */
    int updateStudentPassword(@Param("studentId") int studentId, @Param("password") String password);

    /**
     * 修改该同学的所有信息
     * @param studentId
     * @param studentName
     * @param className
     * @param major
     */
    int updateStudent(@Param("studentId") int studentId, @Param("studentName") String studentName, @Param("className") String className, @Param("major") String major);

    /**
     * 查找某一id的同学
     * @param studentId
     * @return 返回该同学实体的对象
     */
    StudentEntity selectStudent(@Param("studentId") int studentId);

    /**
     * 查找所有同学
     * @return
     */
    List<StudentEntity> selectAllStudent();

    /**
     * 对片段进行模糊查询并且分页
     * @return
     */
    List<StudentEntity> selectStudentByLimit(@Param("studentProperty") String studentProperty, @Param("index") int index, @Param("count") int count);

    /**
     * 查询课程数量
     * @return
     */
    int countAllStudent(String studentProperty);
    /**
     *按专业查询
     * @param major
     * @return
     */
    List<StudentEntity> selectStudentByMajor(@Param("major") String major);

    /**
     * 根据major和学年筛选学生
     * @param major
     * @param year
     * @return
     */
    List<StudentEntity> selectStudentByMajorYear(@Param("major") String major, @Param("year") String year);

    /**
     * 通过学生Id查询学生信息
     * @param studentId
     * @return
     */
    Student queryStudentById(int studentId);
}
