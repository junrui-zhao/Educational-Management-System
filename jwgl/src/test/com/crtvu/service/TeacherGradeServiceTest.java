package com.crtvu.service;

import com.crtvu.dto.teacher.UpdateGrade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Jixw on 2017/5/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class TeacherGradeServiceTest {

    @Autowired
    TeacherGradeService teacherGradeService;

    @Test
    public void showTeacherAllCourseInfo() throws Exception {
        int teacherId = 201 ;
        System.out.println(teacherGradeService.showTeacherAllCourseInfo(201));
        //TeacherAllCourse{teacherId=201, teacherName='赵三', title='教授',
        // courseOpenInfoList=[
        // CourseOpenInfo{courseId='E4DXF13', openId=1, courseName='英语', credit=6.0, nature='必修', department='文法学院', schoolYear='2014-2015', term=1, peopleNum=30},
        // CourseOpenInfo{courseId='E85ZVDW', openId=2, courseName='高数', credit=9.0, nature='必修', department='理学院', schoolYear='2013-2014', term=1, peopleNum=30}]}

    }

    @Test
    public void getStudentGrade() throws Exception {
        System.out.println(teacherGradeService.getStudentGrade(1));
        //[StuGrade{studentId=2014123101, name='钱ii', openId=1, grade=55.0},
        // StuGrade{studentId=2014123102, name='冯jj', openId=1, grade=40.0},
        // StuGrade{studentId=2014123103, name='李aa', openId=1, grade=90.0},
        // StuGrade{studentId=2014123104, name='张bb', openId=1, grade=88.0},
        // StuGrade{studentId=2014123105, name='王cc', openId=1, grade=90.0},
        // StuGrade{studentId=2014123106, name='赵dd', openId=1, grade=90.0},
        // StuGrade{studentId=2014123107, name='孙ee', openId=1, grade=90.0},
        //etc
    }

    @Test
    public void updateGrade() throws Exception {
        List<UpdateGrade> list =new ArrayList<>();
        list.add(new UpdateGrade(2014123101,1,60.0f));
        System.out.println(teacherGradeService.updateGrade(list));
        //1 表示成功
    }

    @Test
    public void download() throws Exception {
        //不在web下无法测试
        //不进行测试
    }

    @Test
    public void readNetworkExcel() throws Exception {
        //不在web下无法测试
        //不进行测试
    }

}