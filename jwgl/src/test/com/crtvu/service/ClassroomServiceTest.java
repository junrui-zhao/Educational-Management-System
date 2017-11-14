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
public class ClassroomServiceTest {
    @Autowired
    private ClassroomService classroomService;
    @Test
    public void insertClassroom() throws Exception {

    }

    @Test
    public void updateClassroom() throws Exception {

    }

    @Test
    public void deleteClassroom() throws Exception {

    }

    @Test
    public void getClassroom() throws Exception {
        System.out.println(classroomService.getClassroom("电301"));
    }

    @Test
    public void getClassroomList() throws Exception {

    }

    @Test
    public void getPageCount() throws Exception {

    }

    @Test
    public void getAllClassroom() throws Exception {

    }

}