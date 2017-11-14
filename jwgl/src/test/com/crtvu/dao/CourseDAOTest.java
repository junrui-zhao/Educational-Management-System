package com.crtvu.dao;

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
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class CourseDAOTest {

    @Autowired
    private CourseDAO courseDAO;
    @Test
    public void insertCourse() throws Exception {
        courseDAO.insertCourse("001","人工智能",(float)2.0,"必修","信息学院");
    }

    @Test
    public void insertCourseNoCre() throws Exception {
        courseDAO.insertCourseNoCre("002","人工智能","必修","信息学院");
    }

    @Test
    public void deleteCourse() throws Exception {
        courseDAO.deleteCourse("001");
    }

    @Test
    public void updateCourse() throws Exception {
        courseDAO.updateCourse("人工智能",(float)2.0,"必修","信息lexue学院","002");
    }

    @Test
    public void updateCourseNoCre() throws Exception {
        courseDAO.updateCourseNoCre("人工智能","必修","xinxi","002");
    }

    @Test
    public void selectCourse() throws Exception {
        System.out.println(courseDAO.selectCourse("002"));
    }

    @Test
    public void selectCourseByLimit() throws Exception {
        System.out.println(courseDAO.selectCourseByLimit("必修",0,10));
    }

    @Test
    public void countAllCourse() throws Exception {
        System.out.println(courseDAO.countAllCourse(""));
    }

    @Test
    public void selectAllCourse() throws Exception {
        System.out.println(courseDAO.selectAllCourse());
    }

}