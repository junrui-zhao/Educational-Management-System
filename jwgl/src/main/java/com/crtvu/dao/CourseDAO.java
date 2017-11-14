package com.crtvu.dao;
import com.crtvu.entity.Course;
import com.crtvu.entity.CourseEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yangming on 2017/3/16/0016.
 */
public interface CourseDAO {

    /**
     * 添加课程,有学分
     * @param courseId
     * @param courseName
     * @param credit
     * @param nature
     * @param department
     */
    void insertCourse(@Param("courseId") String courseId, @Param("courseName") String courseName, @Param("credit") Float credit, @Param("nature") String nature,
                      @Param("department") String department);

    /**
     * 添加课程，无学分
     * @param courseId
     * @param courseName
     * @param nature
     * @param department
     */
    void insertCourseNoCre(@Param("courseId") String courseId, @Param("courseName") String courseName, @Param("nature") String nature,
                           @Param("department") String department);

    /**
     * 通过id删除课程
     * @param courseId
     */
    int deleteCourse(@Param("courseId") String courseId);

    /**
     * 修改课程学分不为空
     * @param courseId
     */
    int updateCourse(@Param("courseName") String courseName, @Param("credit") Float credit, @Param("nature") String nature,
                     @Param("department") String department, @Param("courseId") String courseId);

    /**
     * 修改课程学分为空
     * @param courseName
     * @param nature
     * @param department
     * @param courseId
     */
    int updateCourseNoCre(@Param("courseName") String courseName, @Param("nature") String nature,
                          @Param("department") String department, @Param("courseId") String courseId);

    /**
     * 通过id查找课程
     * @param courseId
     */
    CourseEntity selectCourse(@Param("courseId") String courseId);

    /**
     * 对片段进行模糊查询并且分页
     * @return
     */
    List<CourseEntity> selectCourseByLimit(@Param("courseProperty") String courseProperty, @Param("index") int index, @Param("count") int count);

    /**
     * 查询课程数量
     * @return
     */
    int countAllCourse(String courseProperty);

    /**
     * 显示所有课程
     * @return
     */
    List<CourseEntity> selectAllCourse();

    /**
     * 通过课程Id查询课程
     * @param courseId
     * @return
     */
    Course queryCourseById(String courseId);

}
