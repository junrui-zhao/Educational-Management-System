package com.crtvu.service;

import com.crtvu.dto.teacher.TeachCourseItem;
import com.crtvu.dto.teacher.TeachStudentItem;
import com.crtvu.entity.TeacherEntity;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by gao27024037 on 2017/3/24.
 */
public interface TeacherService {

    enum Result{
        SUCCESS,//成功
        NAME_FAIL,//姓名
        MAJOR_FAIL,//专业错误
        TITLE_FAIL,//职称
        ID_FAIL,//学号重复
        ID_LENGTH_FAIL//ID长度不符合规则
    }


    /**
     * 根据页码查询教师列表
     * @param page
     * @param teacherProperty
     * @return
     */
    List<TeacherEntity> getTeacherList(int page, String teacherProperty);

    /**
     * 获取被编辑教师信息
     * @param id
     * @return
     */
    TeacherEntity getTeacher(int id);

    /**
     * 修改教师信息
     *
     * @param id
     * @param name
     * @param title
     * @return
     */
    Result updateTeacher(int id, String name, String title);

    /**
     * @param id
     * @param passwored
     * @return
     */
    Result updateTeacherPassword(int id, String passwored);

    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    Result deleteTeacher(int id);

    /**
     * 增加教师
     *
     * @param teacherEntity
     * @return
     */
    Result insertTeacher(TeacherEntity teacherEntity);

    List<TeacherEntity> getTeacherList();
    /**
     * 找出总页数
     * @return
     */
    int getPageCount(String teacherProperty);

    /**
     * 创建报表并上传
     * @return
     */
    HSSFWorkbook creatTable();

    /**
     * 获取用户传来的报表文件并进行处理，添加教师
     * @param in
     * @param fileName
     * @return
     * @throws Exception
     */
    List<List<Object>> getListByExcel(InputStream in, String fileName) throws Exception;

    // 查看教师本人授课表
    LinkedList<TeachCourseItem> getAllTeachCourseById(int teacherId);

    // 获取选课名单
    HashMap<Integer, LinkedList<TeachStudentItem>> getAllCourseStudentList(int teacherId);
}
