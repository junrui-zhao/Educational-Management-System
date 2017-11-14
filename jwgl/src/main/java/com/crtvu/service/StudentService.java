package com.crtvu.service;

/**
 * Created by lcf12 on 2017/3/16.
 */

import java.util.*;

import com.crtvu.dto.student.CourseItem;
import com.crtvu.entity.Course;
import com.crtvu.entity.StudentEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * 站在使用者的角度设计接口
 * 方法定义粒度，参数，返回类型，类型
 */
public interface StudentService {

    enum Result{
        SUCCESS,//成功
        NAME_FAIL,//姓名
        MAJOR_FAIL,//专业错误
        CLASSNAME_FAIL,//班级
        ID_FAIL,//学号重复
        ID_LENGTH_FAIL//ID长度不符合规则
    }

    Set<String> getAllStudentClass();
    /**
     * 对一组学生信息进行分组，并翻到第page页。
     * @param page
     * @return
     */
    List<StudentEntity> getStudentList(int page, String studentProperty);

    /**
     * 找出总页数
     * @return
     */
    int getPageCount(String studentProperty);
    /**
     * 找到指定id的同学
     * @param id
     * @return
     */
    StudentEntity getStudent(int id);
    /**
     * 添加新同学
     * @param student
     */
    Result insertStudent(StudentEntity student);

    /**
     * 删除某一ID的同学
     * @param id
     */
    Result deleteStudent(int id);

    /**
     * 修改某同学的信息
     * @param id
     * @param name
     * @param className
     * @param major
     */
    Result updateStudent(int id, String name, String className, String major);

    /**
     * 修改密码
     * @param id
     * @param newPassword
     */
    Result updateStudentPassword(int id, String newPassword);

    /**
     * 批量添加
     * @param name
     * @param file
     * @return
     */
    Map<String, Object> batchImport(String name, MultipartFile file);

    // 查看全部课程
    HashMap<String, LinkedList<CourseItem>> getAllCourse(int studentId);

    // 查看已选课程
    LinkedList<Course> getAllSelectCourse(int studentId);

    // 选课(成功返回True，失败返回False)
    int selectCourse(int studentId, int openId);

    int quitCourse(int studentId, int openId);

    // 查询所选课程余量
    int getCurrentCourseLeftNum(int openId);

}
