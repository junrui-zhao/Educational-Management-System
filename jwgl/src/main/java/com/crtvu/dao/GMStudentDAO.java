package com.crtvu.dao;

import com.crtvu.entity.Student;

import java.util.List;

/**
 * Created by Phoenix on 2017/5/7.
 */
public interface GMStudentDAO {
    /**
     * 根据学生id查询学生信息（密码除外）
     * @param studentId
     * @return Student
     */
    Student queryStudentInfo(int studentId);

    /**
     * 查询所有学生信息
     * @return List<Student>
     */
    List<Student> queryAllStudent();

    /**
     * 根据学生id查询学生班级
     * @param studentId
     * @return studentClass
     */
    String queryStudentClass(int studentId);
}