package com.crtvu.service;

import java.util.*;

import com.crtvu.entity.CourseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by yangming on 2017/3/16/0016.
 */
public interface CourseService {
    enum Result{
        SUCCESS,//成功
        NAME_FAIL,//课程名
        DEPARTMENT_FAIL,//开设院系
        NATURE_FAIL,//课程
        CREDIT_FAIL,//学分
        ID_FAIL,//学号重复
        ID_LENGTH_FAIL//ID长度不符合规则
    }//(String courseName, Float credit, String nature, String department, String courseId
    /**
     * 对课程信息进行分页，并可以翻到第page
     * @param page
     * @return
     */
    List<CourseEntity> getCourseList(int page, String courseProperty);


    /**
     * 找出总页数
     * @return
     */
    int getPageCount(String courseProperty);

    /**
     * 通过id查找某门课程
     * @param courseId
     * @return
     */
    CourseEntity getCourse(String courseId);

    /**
     * 得到所有课程列表
     * @return
     */
    List<CourseEntity> getAllCourseList();

    /**
     * 添加有学分课程
     * @param course
     */
    Result insertCourse(CourseEntity course);

    /**
     * 添加无学分课程
     * @param course
     */
    Result insertCourseNoCre(CourseEntity course);

    /**
     * 通过id删除课程
     * @param courseId
     */
    Result deleteCourse(String courseId);

    /**
     * 修改某门课程，学分不为空
     * @param courseId
     * @param courseName
     * @param credit
     * @param nature
     * @param department
     */
    Result updateCourse(String courseName, Float credit, String nature, String department, String courseId);

    /**
     * 修改课程，学分为空
     * @param courseName
     * @param nature
     * @param department
     * @param courseId
     */
    Result updateCourseNoCre(String courseName, String nature, String department, String courseId);
    /**
     * 批量添加
     * @param name
     * @param file
     * @return
     */
    Map<String, Object> batchImport(String name, MultipartFile file);
}
