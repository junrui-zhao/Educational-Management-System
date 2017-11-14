package com.crtvu.dao;

import com.crtvu.entity.Teacher;
import com.crtvu.entity.TeacherEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by gao27024037 on 2017/3/18.
 * Edit by Phoenix on 2017/4/10
 */
public interface TeacherDAO {

    /**
     * 根据ID查询老师
     * @param teacherId
     * @return
     */
    TeacherEntity selectTeacher(int teacherId);

    /**
     * 根据全部
     * @param
     * @param
     * @return
     */
    List<TeacherEntity> selectAllTeacher();

    /**
     *
     * @param teacherId
     * @param teacherName
     * @param title
     * @param password
     */
    void insertTeacher(int teacherId, String teacherName, String title, String password);

    /**
     * 根据ID删除老师
     * @param teacherId
     * @return
     */
    int deleteTeacher(int teacherId);

    /**
     * 修改老师信息
     * @param teacherId
     * @param teacherName
     * @param title
     * @return
     */
    int updateTeacher(int teacherId, String teacherName, String title);

    /**
     * 修改老师密码
     * @param teacherId
     * @param password
     * @return
     */
    int updateTeacherPassword(int teacherId, String password);

    /**
     * 查询教师数量
     * @return
     */
    int countAllTeacher(String teacherProperty);

    /**
     * 根据关键字查询并分页
     * @param teacherProperty
     * @param index
     * @param count
     * @return
     */
    List<TeacherEntity> selectTeacherByLimit(@Param("teacherProperty") String teacherProperty, @Param("index") int index, @Param("count") int count);

    /**
     * 通过教师Id查询教师
     * @param teacherId
     * @return
     */
    Teacher queryTeacherById(int teacherId);

}

