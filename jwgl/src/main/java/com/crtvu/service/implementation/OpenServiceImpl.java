package com.crtvu.service.implementation;

import com.crtvu.dao.OpenDAO;
import com.crtvu.dao.StudentCourseDAO;
import com.crtvu.dao.StudentDAO;
import com.crtvu.dao.TargetMajorDAO;
import com.crtvu.entity.OpenEntity;
import com.crtvu.entity.StudentEntity;
import com.crtvu.entity.TargetMajorEntity;
import com.crtvu.service.OpenService;
import com.crtvu.util.JDateFormater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by danagi on 2017/4/10.
 */
@Service
public class OpenServiceImpl implements OpenService{

    @Autowired
    private OpenDAO openDAO;

    @Autowired
    private TargetMajorDAO targetMajorDAO;

    @Autowired
    private StudentCourseDAO studentCourseDAO;

    @Autowired
    private StudentDAO studentDAO;

    public Result openCourse(OpenEntity openEntity,Set<String> majors) {
        if(openEntity.getPeopleNum()<=0) {
            return Result.NUM_FAIL;
        }
        if(openEntity.getStartSelectTime()!=null&&openEntity.getStartSelectTime().after(openEntity.getEndSelectTime())) {
            return Result.DATE_FAIL;
        }
        if(majors.size()==0) {
            return Result.MAJOR_FAIL;
        }
        try {
            String startTime = JDateFormater.dateToString(openEntity.getStartSelectTime());
            String endTime = JDateFormater.dateToString(openEntity.getEndSelectTime());
            openDAO.insertOpen(openEntity.getCourseId(),openEntity.getSchoolYear(),openEntity.getTerm(),openEntity.getPeopleNum(),startTime,endTime);
        }
        catch (Exception e) {
            return Result.KEY_FAIL;
        }
        int openId = openDAO.selectLastInsertId();
        for(String major:majors) {
            targetMajorDAO.insertTargetMajor(openId,major);
            if(!openEntity.getNature().equals("选修")) {
                List<StudentEntity> studentEntities = studentDAO.selectStudentByMajor(major);
                for(StudentEntity studentEntity:studentEntities){
                    studentCourseDAO.insertCourse(studentEntity.getStudentId(),openId);
                }
            }
        }
        return Result.SUCCESS;
    }

    public Result removeOpen(int openId) {
        return openDAO.deleteOpen(openId)>0?Result.ID_FAIL:Result.SUCCESS;
    }

    public Result modifyOpen(OpenEntity openEntity,Set<String> majors) {
        if(openEntity.getPeopleNum()<=0) {
            return Result.NUM_FAIL;
        }
        if(openEntity.getStartSelectTime()!=null&&openEntity.getStartSelectTime().after(openEntity.getEndSelectTime())) {
            return Result.DATE_FAIL;
        }
        if(majors.size()==0) {
            return Result.MAJOR_FAIL;
        }
        int result;
        try {
            String startTime = JDateFormater.dateToString(openEntity.getStartSelectTime());
            String endTime = JDateFormater.dateToString(openEntity.getEndSelectTime());
            result = openDAO.updateOpen(openEntity.getOpenId(),openEntity.getCourseId(),openEntity.getSchoolYear(),openEntity.getTerm(),openEntity.getPeopleNum(),startTime,endTime);
        }
        catch (Exception e){
            return Result.KEY_FAIL;
        }
        if(result==0) {
            return Result.ID_FAIL;
        }
        System.out.println("TTTTT");
        List<TargetMajorEntity> targetMajorEntities = targetMajorDAO.queryTargetMajorById(openEntity.getOpenId());
        Set<String> oldMajors = new TreeSet<String>();
        Set<String> deleteMajors = new TreeSet<String>();
        for(TargetMajorEntity targetMajorEntity:targetMajorEntities){
            oldMajors.add(targetMajorEntity.getMajor());
            deleteMajors.add(targetMajorEntity.getMajor());
        }
        deleteMajors.removeAll(majors);
        majors.removeAll(oldMajors);
        for(String major:deleteMajors){
            targetMajorDAO.deleteTargetMajor(openEntity.getOpenId(),major);
            List<StudentEntity> studentEntities = studentDAO.selectStudentByMajor(major);
            for(StudentEntity studentEntity:studentEntities){
                studentCourseDAO.quitCourse(studentEntity.getStudentId(),openEntity.getOpenId());
            }
        }
        for(String major:majors){
            targetMajorDAO.insertTargetMajor(openEntity.getOpenId(),major);
            if(!openEntity.getNature().equals("选修")){
                List<StudentEntity> studentEntities = studentDAO.selectStudentByMajor(major);
                for(StudentEntity studentEntity:studentEntities){
                    studentCourseDAO.insertCourse(studentEntity.getStudentId(),openEntity.getOpenId());
                }
            }
        }
        return Result.SUCCESS;
    }

    public List<OpenEntity> queryOpen(String schoolYear, int term) {
        return openDAO.selectOpenBySchoolYearAndTerm(schoolYear,term);
    }

    @Override
    public OpenEntity queryOpenByOpenId(int openId) {
        return openDAO.selectOpenByOpenId(openId);
    }
}
