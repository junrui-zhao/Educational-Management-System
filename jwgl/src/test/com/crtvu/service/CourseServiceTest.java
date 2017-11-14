package com.crtvu.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by lcf12 on 2017/4/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class CourseServiceTest {

    @Autowired
    private CourseService courseService;

    @Test
    public void getCourseList() throws Exception {
        System.out.println(courseService.getCourseList(1,""));
    }

    @Test
    public void getPageCount() throws Exception {

    }

    @Test
    public void getCourse() throws Exception {

    }

    @Test
    public void getAllCourseList() throws Exception {

    }

    @Test
    public void insertCourse() throws Exception {

    }

    @Test
    public void insertCourseNoCre() throws Exception {

    }

    @Test
    public void deleteCourse() throws Exception {

    }

    @Test
    public void updateCourse() throws Exception {

    }

    @Test
    public void updateCourseNoCre() throws Exception {

    }

    @Test
    public void batchImport() throws Exception {

    }

}