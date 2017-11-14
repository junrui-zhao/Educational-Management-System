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
public class ClassroomDAOTest {

    @Autowired
    private ClassroomDAO classroomDAO;

    @Test
    public void insertClassroom() throws Exception {
        classroomDAO.insertClassroom("东配楼302",10);
    }

    @Test
    public void deleteClassroom() throws Exception {
        classroomDAO.deleteClassroom("东配楼302");
    }

    @Test
    public void updateClassroom() throws Exception {
        classroomDAO.updateClassroom("东配楼302","东配楼302",80);
    }

    @Test
    public void selectClassroom() throws Exception {
        System.out.println(classroomDAO.selectClassroom("东配楼302"));
    }

    @Test
    public void selectClassroomByLimit() throws Exception {
        System.out.println(classroomDAO.selectClassroomByLimit("0",0,10));
    }

    @Test
    public void countAllClassroom() throws Exception {
        System.out.println(classroomDAO.selectAllClassroom());
    }

    @Test
    public void selectFreeClassroom() throws Exception {

    }

    @Test
    public void selectAllClassroom() throws Exception {

    }

}