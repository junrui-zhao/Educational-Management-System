package com.crtvu.dao;

import com.crtvu.entity.StudentCourseEntity;

import java.util.LinkedList;

/**
 * Created by Phoenix on 2017/4/4.
 */
public interface StudentCourseDAO {

    // 根据学生学号查询选课信息
    LinkedList<StudentCourseEntity> queryOpenIdByStudentId(int studentId);

    // 根据开设id查询所有学生
    LinkedList<StudentCourseEntity> queryStudentsByOpenId(int openId);

    // 选课
    int insertCourse(int studentId, int openId);

    // 退课
    int quitCourse(int studentId, int openId);

    // 根据学号和开设id查询
    StudentCourseEntity queryStudentCourseByStudentAndOpenId(int studentId, int openId);

}
