package com.crtvu.service;

import com.crtvu.dto.StudentCheckSubjectItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by zhao on 2017/5/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class GMStudentServiceTest {

    @Autowired
    GMStudentService gmStudentService;

    @Test
    public void queryAll() {
        List<StudentCheckSubjectItem> all = gmStudentService.queryAllSubject();
        for(StudentCheckSubjectItem item : all)
            System.out.println(item);
    }

    @Test
    public void query() {
        List<StudentCheckSubjectItem> all = gmStudentService.keyNotAppliedSubject(2014014362,"万");
        for(StudentCheckSubjectItem item : all)
            System.out.println(item);
    }
}