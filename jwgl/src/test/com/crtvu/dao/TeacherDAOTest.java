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
public class TeacherDAOTest {
    @Autowired
    TeacherDAO teacherDAO;
    @Test
    public void selectTeacher() throws Exception {
        System.out.println(teacherDAO.selectTeacher(666));
    }

    @Test
    public void selectAllTeacher() throws Exception {
        System.out.println(teacherDAO.selectAllTeacher());
    }

    @Test
    public void insertTeacher() throws Exception {
        teacherDAO.insertTeacher(606,"进行","校长","1111111");
    }

    @Test
    public void deleteTeacher() throws Exception {
        teacherDAO.deleteTeacher(606);
    }

    @Test
    public void updateTeacher() throws Exception {
        teacherDAO.updateTeacher(666,"徐小明","校长");
    }

    @Test
    public void updateTeacherPassword() throws Exception {
        teacherDAO.updateTeacherPassword(666,"123");
    }

    @Test
    public void countAllTeacher() throws Exception {
        System.out.println(teacherDAO.countAllTeacher(""));
    }

    @Test
    public void selectTeacherByLimit() throws Exception {
        System.out.println(teacherDAO.selectTeacherByLimit("教授",0,10));
    }

}