package com.crtvu.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by zhao on 2017/5/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class GMTeacherServiceTest {

    @Autowired
    GMTeacherService gmTeacherService;

    @Test
    public void queryAll() {
        System.out.println(gmTeacherService.getSubjectDocument(1));
    }

    @Test
    public void deleteSub(){
        System.out.println(gmTeacherService.deleteConfirmSubject(1));
    }
}