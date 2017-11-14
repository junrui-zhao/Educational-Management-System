package com.crtvu.dao;

import com.crtvu.entity.Teacher;

/**
 * Created by Phoenix on 2017/5/7.
 */
public interface GMTeacherDAO {

    /**
     * 根据教师Id查询教师信息
     * @param teacherId
     * @return Teacher
     */
    Teacher queryTeacherById(int teacherId);
}
