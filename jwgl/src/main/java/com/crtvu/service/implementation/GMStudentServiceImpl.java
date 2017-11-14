package com.crtvu.service.implementation;

import com.crtvu.dao.*;
import com.crtvu.dto.StudentCheckDocument;
import com.crtvu.dto.StudentCheckSubjectInfo;
import com.crtvu.dto.StudentCheckSubjectItem;
import com.crtvu.entity.*;
import com.crtvu.service.GMStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhao on 2017/3/31.
 */
@Service
public class GMStudentServiceImpl implements GMStudentService {

    @Autowired
    private ConfirmSubjectDAO confirmSubjectDAO;

    @Autowired
    private ArrangeSubjectDAO arrangeSubjectDAO;

    @Autowired
    private SelectSubjectDAO selectSubjectDAO;

    @Autowired
    private GMTeacherDAO GMTeacherDAO;

    @Autowired
    private GMStudentDAO GMStudentDAO;

    /**
     * 学生查看已确认的课题
     * @param studentId
     * @return StudentCheckSubjectItem
     */
    @Override
    public List<StudentCheckSubjectItem> checkConfirmedSubject(int studentId) {
        List<StudentCheckSubjectItem> subjectList = new ArrayList<StudentCheckSubjectItem>();
        StudentCheckSubjectItem subjectItem = new StudentCheckSubjectItem();
        ConfirmSubject confirmSubject = confirmSubjectDAO.queryStudentSubjectByStudentId(studentId);
        if(confirmSubject != null){
            int subjectId = confirmSubject.getSubjectId();
            subjectItem.setSubjectId(subjectId);
            ArrangeSubject arrangeSubject = arrangeSubjectDAO.queryArrangeSubjectBySubjectId(subjectId);
            String subjectName = arrangeSubject.getName();
            int teacherId = arrangeSubject.getTeacher_id();
            subjectItem.setSubjectName(subjectName);
            subjectItem.setSubjectStatus(2);
            String teacherName = GMTeacherDAO.queryTeacherById(teacherId).getTeacherName();
            subjectItem.setTeacherName(teacherName);
            subjectList.add(subjectItem);
            return subjectList;
        }
        return null;
    }

    /**
     * 查询未审核和未通过的课题列表
     * @param studentId
     * @return List<StudentCheckSubjectItem>
     */
    @Override
    public List<StudentCheckSubjectItem> checkNotConfirmedSubject(int studentId) {
        List<StudentCheckSubjectItem> subjectList = new ArrayList<StudentCheckSubjectItem>();

        List<SelectSubject> notConfirmedSubjectList = selectSubjectDAO.querySelectSubjectByStudentIdAndStatus(studentId,0);
        if(notConfirmedSubjectList != null){
            for(SelectSubject notConfirmedItem : notConfirmedSubjectList){
                StudentCheckSubjectItem subjectItem = new StudentCheckSubjectItem();
                int subjectId = notConfirmedItem.getSubjectId();
                subjectItem.setSubjectId(subjectId);
                ArrangeSubject arrangeSubject = arrangeSubjectDAO.queryArrangeSubjectBySubjectId(subjectId);
                String subjectName = arrangeSubject.getName();
                int teacherId = arrangeSubject.getTeacher_id();
                subjectItem.setSubjectName(subjectName);
                subjectItem.setSubjectStatus(0);
                String teacherName = GMTeacherDAO.queryTeacherById(teacherId).getTeacherName();
                subjectItem.setTeacherName(teacherName);
                subjectList.add(subjectItem);
            }
        }
        List<SelectSubject> notPassedSubjectList = selectSubjectDAO.querySelectSubjectByStudentIdAndStatus(studentId,1);
        if(notPassedSubjectList != null){
            for(SelectSubject notPassedItem : notPassedSubjectList){
                StudentCheckSubjectItem subjectItem = new StudentCheckSubjectItem();
                int subjectId = notPassedItem.getSubjectId();
                subjectItem.setSubjectId(subjectId);
                ArrangeSubject arrangeSubject = arrangeSubjectDAO.queryArrangeSubjectBySubjectId(subjectId);
                String subjectName = arrangeSubject.getName();
                int teacherId = arrangeSubject.getTeacher_id();
                subjectItem.setSubjectName(subjectName);
                subjectItem.setSubjectStatus(1);
                String teacherName = GMTeacherDAO.queryTeacherById(teacherId).getTeacherName();
                subjectItem.setTeacherName(teacherName);
                subjectList.add(subjectItem);
            }
        }
        if(subjectList != null){
            return subjectList;
        }
        return null;
    }

    /**
     * 查询可申请的课题列表
     * @param studentId
     * @return List<StudentCheckSubjectItem>
     */
    @Override
    public List<StudentCheckSubjectItem> checkNotAppliedSubject(int studentId) {
        //查询是否有已确定课题
        ConfirmSubject confirmSubject = confirmSubjectDAO.queryStudentSubjectByStudentId(studentId);
        //查询是否有未审核的课题
        List<SelectSubject> notConfirmedSubjectList = selectSubjectDAO.querySelectSubjectByStudentIdAndStatus(studentId,0);
        //查询所有已申请过的课题
        List<StudentCheckSubjectItem> notAppliedSubjectList = new ArrayList<StudentCheckSubjectItem>();
        if(confirmSubject == null && notConfirmedSubjectList.isEmpty()) {
            //查询所有课题
            List<Integer> allSubjectList = arrangeSubjectDAO.queryAllArrangeSubjectId();
            //查询所有已确认的课题
            List<Integer> allConfirmList = confirmSubjectDAO.queryAllConfirmSubjectId();
            //查询select表里某学生选过的课题
            List<Integer> notConfirmSubjectList = selectSubjectDAO.queryAllSelectSubjectIdByStudentId(studentId);
            allSubjectList.removeAll(allConfirmList);
            allSubjectList.removeAll(notConfirmSubjectList);
            for(Integer subjectId: allSubjectList){
                StudentCheckSubjectItem notAppliedSubject = new StudentCheckSubjectItem();
                notAppliedSubject.setSubjectId(subjectId);
                ArrangeSubject arrangeSubject = arrangeSubjectDAO.queryArrangeSubjectBySubjectId(subjectId);
                notAppliedSubject.setSubjectName(arrangeSubject.getName());
                int teacherId = arrangeSubject.getTeacher_id();
                String teacherName = GMTeacherDAO.queryTeacherById(teacherId).getTeacherName();
                notAppliedSubject.setTeacherName(teacherName);
                notAppliedSubject.setSubjectStatus(3);
                notAppliedSubjectList.add(notAppliedSubject);
            }
        }
        return notAppliedSubjectList;
    }

    @Override
    public List<StudentCheckSubjectItem> keyNotAppliedSubject(int studentId, String keyword) {
        //查询是否有已确定课题
        ConfirmSubject confirmSubject = confirmSubjectDAO.queryStudentSubjectByStudentId(studentId);
        //查询是否有未审核的课题
        List<SelectSubject> notConfirmedSubjectList = selectSubjectDAO.querySelectSubjectByStudentIdAndStatus(studentId,0);
        //查询所有已申请过的课题
        List<StudentCheckSubjectItem> notAppliedSubjectList = new ArrayList<StudentCheckSubjectItem>();
        if(confirmSubject == null && notConfirmedSubjectList.isEmpty()) {
            //查询所有课题
            List<Integer> allSubjectList = arrangeSubjectDAO.queryAllArrangeSubjectId();
            //查询所有已确认的课题
            List<Integer> allConfirmList = confirmSubjectDAO.queryAllConfirmSubjectId();
            //查询select表里某学生选过的课题
            List<Integer> notConfirmSubjectList = selectSubjectDAO.queryAllSelectSubjectIdByStudentId(studentId);
            allSubjectList.removeAll(allConfirmList);
            allSubjectList.removeAll(notConfirmSubjectList);
            for(Integer subjectId: allSubjectList){
                StudentCheckSubjectItem notAppliedSubject = new StudentCheckSubjectItem();
                notAppliedSubject.setSubjectId(subjectId);
                ArrangeSubject arrangeSubject = arrangeSubjectDAO.queryArrangeSubjectBySubjectId(subjectId);
                notAppliedSubject.setSubjectName(arrangeSubject.getName());
                int teacherId = arrangeSubject.getTeacher_id();
                String teacherName = GMTeacherDAO.queryTeacherById(teacherId).getTeacherName();
                notAppliedSubject.setTeacherName(teacherName);
                notAppliedSubject.setSubjectStatus(3);
                if (subjectId.toString().contains(keyword) || arrangeSubject.getName().contains(keyword)
                        || teacherName.contains(keyword)){
                    notAppliedSubjectList.add(notAppliedSubject);
                }
            }
        }
        return notAppliedSubjectList;
    }

    /**
     * 查看课题详细信息
     * @param subjectId
     * @return StudentCheckSubjectInfo
     */
    @Override
    public StudentCheckSubjectInfo checkSubjectInfo(int subjectId) {
        StudentCheckSubjectInfo subjectInfo = new StudentCheckSubjectInfo();
        subjectInfo.setSubjectId(subjectId);
        ArrangeSubject subject = arrangeSubjectDAO.queryArrangeSubjectBySubjectId(subjectId);
        subjectInfo.setSubjectName(subject.getName());
        subjectInfo.setRequirement(subject.getRequirement());
        Teacher teacher = GMTeacherDAO.queryTeacherById(subject.getTeacher_id());
        subjectInfo.setTeacherName(teacher.getTeacherName());
        return subjectInfo;
    }

    /**
     * 学生提交文档
     * @param subjectId
     * @param document
     * @return int
     */
    @Override
    public int updateSubjectDocument(int subjectId, String document) {
        int result = confirmSubjectDAO.updateStudentDocumentBySubjectId(subjectId,document);
        if(result == 1) {
            return 1;
        }
        return 0;
    }

    /**
     * 学生预选课题
     * @param subjectId
     * @param studentId
     * @return int
     */
    @Override
    public int insertSelectSubject(int subjectId, int studentId) {
        SelectSubject selectSubject = new SelectSubject(studentId,subjectId,0);
        int result = selectSubjectDAO.insertSelectSubject(selectSubject);
        if(result == 1){
            return 1;
        }
        return 0;
    }

    /**
     * 查看已确认的课题信息
     * @param subjectId
     * @return
     */
    @Override
    public StudentCheckDocument checkSubjectDocu(int subjectId) {
        StudentCheckDocument subjectInfo = new StudentCheckDocument();
        subjectInfo.setSubjectId(subjectId);
        ArrangeSubject subject = arrangeSubjectDAO.queryArrangeSubjectBySubjectId(subjectId);
        subjectInfo.setSubjectName(subject.getName());
        subjectInfo.setRequirement(subject.getRequirement());
        Teacher teacher = GMTeacherDAO.queryTeacherById(subject.getTeacher_id());
        subjectInfo.setTeacherName(teacher.getTeacherName());
        ConfirmSubject confirmSubject = confirmSubjectDAO.querySubjectBySubjectId(subjectId);
        subjectInfo.setDocument(confirmSubject.getDocument());
        return subjectInfo;
    }

    /**
     * 查询所有学生信息
     * @param studentId
     * @return
     */
    @Override
    public Student queryStudent(int studentId) {
        Student student = GMStudentDAO.queryStudentInfo(studentId);
        return student;
    }

    /**
     * 撤回申请
     * @param studentId
     * @param subjectId
     * @return
     */
    @Override
    public int deleteApplication(int studentId, int subjectId) {
        int result = selectSubjectDAO.deleteStudentBySubjectIdAndStudentId(subjectId, studentId);
        if(result == 1){
            return 1;
        }
        return 0;
    }

    @Override
    public List<StudentCheckSubjectItem> queryAllSubject() {

        List<StudentCheckSubjectItem> all = new ArrayList<StudentCheckSubjectItem>();
        List<ArrangeSubject> allSubject = arrangeSubjectDAO.queryAllArrangeSubject();
        List<Integer> allid = confirmSubjectDAO.queryAllConfirmSubjectId();
        for(ArrangeSubject subject : allSubject){
            StudentCheckSubjectItem item = new StudentCheckSubjectItem();
            item.setSubjectId(subject.getSubject_id());
            item.setSubjectName(subject.getName());
            int teacherId = subject.getTeacher_id();
            Teacher teacher = GMTeacherDAO.queryTeacherById(subject.getTeacher_id());
            item.setTeacherName(teacher.getTeacherName());
            //课题状态还需考虑一下
            if(allid.contains(subject.getSubject_id())){
                item.setSubjectStatus(1);
            }
            else {
                item.setSubjectStatus(0);
            }
            all.add(item);
        }
        return all;
    }

    @Override
    public List<StudentCheckSubjectItem> keywordSubject(String keyword) {

        List<StudentCheckSubjectItem> all = new ArrayList<StudentCheckSubjectItem>();
        List<ArrangeSubject> allSubject = arrangeSubjectDAO.queryAllArrangeSubject();
        List<Integer> allid = confirmSubjectDAO.queryAllConfirmSubjectId();
        for(ArrangeSubject subject : allSubject){
            StudentCheckSubjectItem item = new StudentCheckSubjectItem();

//            item.setSubjectId(subject.getSubject_id());
//            item.setSubjectName(subject.getName());
            int teacherId = subject.getTeacher_id();
            Teacher teacher = GMTeacherDAO.queryTeacherById(subject.getTeacher_id());
//            item.setTeacherName(teacher.getTeacherName());
            //课题状态还需考虑一下
            int stat = -1,status = -1;
            if(keyword.contains("已选")){
                stat = 1;
            }
            if (keyword.contains("未选")){
                stat = 0;
            }
            if(allid.contains(subject.getSubject_id())){
                status = 1;
               // item.setSubjectStatus(1);
            }
            else {
                status = 0;
               // item.setSubjectStatus(0);
            }
            String id = String.valueOf(subject.getSubject_id());
            String name = subject.getName();
            String teach = teacher.getTeacherName();
            if(id.contains(keyword) || name.contains(keyword) || teach.contains(keyword) || stat == status) {
                item.setSubjectId(Integer.parseInt(id));
                item.setSubjectName(name);
                item.setSubjectStatus(status);
                item.setTeacherName(teach);
                all.add(item);
            }
        }
        return all;
    }

}
