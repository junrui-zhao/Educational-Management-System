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
public class TeacherServiceTest {

    @Autowired
    TeacherService teacherService;
    @Test
    public void getTeacherList() throws Exception {
        System.out.println(teacherService.getTeacherList(1,""));
    }

    @Test
    public void getTeacher() throws Exception {

    }

    @Test
    public void updateTeacher() throws Exception {

    }

    @Test
    public void updateTeacherPassword() throws Exception {

    }

    @Test
    public void deleteTeacher() throws Exception {

    }

    @Test
    public void insertTeacher() throws Exception {

    }

    @Test
    public void getTeacherList1() throws Exception {

    }

    @Test
    public void getPageCount() throws Exception {

    }

    @Test
    public void creatTable() throws Exception {

    }

    @Test
    public void getListByExcel() throws Exception {

    }

}