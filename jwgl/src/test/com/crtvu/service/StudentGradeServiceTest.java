package com.crtvu.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Jixw on 2017/5/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class StudentGradeServiceTest {
    @Autowired
    StudentGradeService studentGradeService;

    @Test
    public void getByIdYearTerm() throws Exception {
        System.out.println(studentGradeService.getByIdYearTerm(2014123101,"2014-2015",1));
        //[StudentCourseGrade{studentId=2014123101, courseId='E4DXF13', openId='1', courseName='英语', credit=6.0, nature='必修', schoolYear='2014-2015', term=1, grade=60.0}]
        System.out.println(studentGradeService.getByIdYearTerm(2014123101,"NO",1));
        //[StudentCourseGrade{studentId=2014123101, courseId='E4DXF13', openId='1', courseName='英语', credit=6.0, nature='必修', schoolYear='2014-2015', term=1, grade=60.0},
        //StudentCourseGrade{studentId=2014123101, courseId='E85ZVDW', openId='2', courseName='高数', credit=9.0, nature='必修', schoolYear='2013-2014', term=1, grade=90.0}]
    }

    @Test
    public void getStudentInfo() throws Exception {
        System.out.println(studentGradeService.getStudentInfo(2014123101));
        //StudentEntity{studentId=2014123101, studentName='钱ii', className='1404', major='自动化', password='123456'}
    }

    @Test
    public void getEvaluate() throws Exception {
        System.out.println(studentGradeService.getEvaluate(2014123101,1));
        // StudentEvaluate{studentId=2014123101, openId=1,
        // courseEntity=CourseEntity{courseId='E4DXF13', courseName='英语', credit=6.0, nature='必修', department='文法学院'},
        // teacher=TeacherNoPwd{id=201, name='赵三', title='教授'},
        // evaluate='xueshu:2,jiaoxuue:5,taidu:4,other:'}

    }

    @Test
    public void getStudentStat() throws Exception {
        System.out.println(studentGradeService.getStudentStat(2014123100));
        //StudentStatistics{studentId=2014123101, gpa=2.8, creditGotCom=15.0, creditGotEle=0.0, failList=[]}
    }

    @Test
    public void download() throws Exception {
        //不进行测试
    }

}