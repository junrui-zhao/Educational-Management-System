package com.crtvu.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by gao27024037 on 2017/5/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class InfoDAOTest {

    @Autowired
    private InfoDAO infoDAO;
    private static final int pageNumber = 10;

    @Test
    public void insertInfo() throws Exception {

    }

    @Test
    public void deleteInfo() throws Exception {

    }

    @Test
    public void updateInfo() throws Exception {

    }

    @Test
    public void selectInfo() throws Exception {

    }

    @Test
    public void selectAllInfo() throws Exception {

    }

    @Test
    public void selectInfoByLimit() throws Exception {

    }

    @Test
    public void countAllInfo() throws Exception {
        String infoProperty = "大";
        int count = infoDAO.countAllInfo(infoProperty);
        int page_count = count==0?1:count/pageNumber+(count%pageNumber>0?1:0);
        System.out.println(page_count);
    }

    @Test
    public void selectInfoByStudentId() throws Exception {
        int page =1;
        String infoProperty = "大";
        System.out.println(infoDAO.selectInfoByLimit(infoProperty,(page-1)*pageNumber,pageNumber));
    }

}