package com.crtvu.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Jixw on 2017/5/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class AdminGradeServiceTest {
    @Autowired
    AdminGradeService adminGradeService;
    @Test
    public void getClassStatistics() throws Exception {
        System.out.println(adminGradeService.getClassStatistics("计科","2014"));
        //[ClassStatistics{className='1404', average=3.77, excellentRate=0.0},
        // ClassStatistics{className='1405', average=4.0, excellentRate=1.0},
        // ClassStatistics{className='1406', average=4.0, excellentRate=1.0}]
    }

    @Test
    public void getClassStatisticsList() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("计科");
        list.add("自动化");
        System.out.println(adminGradeService.getClassStatisticsList(list,"2014"));
        //[MajorClassStatisticsList{major='计科', year='2014',classStatisticsList=[
        // ClassStatistics{className='1404', average=3.77, excellentRate=0.0},
        // ClassStatistics{className='1405', average=4.0, excellentRate=1.0},
        // ClassStatistics{className='1406', average=4.0, excellentRate=1.0}
        // ]},
        // MajorClassStatisticsList{major='自动化', year='2014',
        // classStatisticsList=[ClassStatistics{className='1404', average=1.73, excellentRate=0.0},
        // ClassStatistics{className='1405', average=3.96, excellentRate=1.0},
        // ClassStatistics{className='1406', average=4.0, excellentRate=1.0}
        // ]}]
    }

    @Test
    public void getMajorList() throws Exception {
        System.out.println(adminGradeService.getMajorList("2014"));
        //[自动化, 信管, 计科]
    }

    @Test
    public void getCourseOpenInfoByYearTerm() throws Exception {
        System.out.println(adminGradeService.getCourseOpenInfoByYearTerm("2014-2015",1));
        //[CourseOpenInfo{courseId='E4DXF13', openId=1, courseName='英语', credit=6.0, nature='必修', department='文法学院', schoolYear='2014-2015', term=1, peopleNum=30}]
    }

    @Test
    public void getGradeByOpenid() throws Exception {
        System.out.println(adminGradeService.getGradeByOpenid(1));
        //[StudentGradeForManager{studentId=2014123101, studentName='钱ii', className='1404', major='自动化', grade=60.0},
        // StudentGradeForManager{studentId=2014123102, studentName='冯jj', className='1404', major='自动化', grade=40.0},
        // StudentGradeForManager{studentId=2014123103, studentName='李aa', className='1405', major='自动化', grade=90.0},
        // etc...
    }

}