package com.crtvu.service;

import com.crtvu.dto.teacher.StuGrade;
import com.crtvu.dto.teacher.TeacherAllCourse;
import com.crtvu.dto.teacher.UpdateGrade;
import com.crtvu.entity.SelectCourseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface TeacherGradeService {
    /**
     *
     * @param teacherId
     * @return
     */
    TeacherAllCourse showTeacherAllCourseInfo(int teacherId);

    /**
     * 根据id查询课程
     * @param openId
     * @return
     */
    List<SelectCourseEntity> queryCourseByOpenid(int openId);

    /**
     * 根据开设ID得到学生成绩集合
     * @param openId
     * @return
     */
    List<StuGrade> getStudentGrade(int openId);

    /**
     * 提交学生成绩
     * @param updateGradeList
     */
    int updateGrade(List<UpdateGrade> updateGradeList);

    /**
     * 先判断是否有资格下载
     * @param teacherId
     * @param openId
     */
    String download(int teacherId, int openId);

    Map<String, Object> readNetworkExcel(MultipartFile file, int openId) throws IOException;

}
