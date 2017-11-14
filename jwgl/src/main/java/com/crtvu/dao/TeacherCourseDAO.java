package com.crtvu.dao;

import com.crtvu.entity.TeacherCourse;

import java.util.LinkedList;

/**
 * Created by Phoenix on 2017/5/7.
 */
public interface TeacherCourseDAO {

    /**
     * 通过教师id查询授课信息
     * @param teacherId
     * @return
     */
    LinkedList<TeacherCourse> queryTeacherCourseByTeacherId(int teacherId);

    /**
     * 通过开设id查询授课信息
     * @param openId
     * @return
     */
    LinkedList<TeacherCourse> queryTeacherCourseByOpenId(int openId);

}
