package com.crtvu.service.implementation;

import com.crtvu.dao.*;
import com.crtvu.dto.ReleasedSubjectListItem;
import com.crtvu.dto.TeacherCheckDocumentItem;
import com.crtvu.dto.TeacherCheckSubjectItem;
import com.crtvu.entity.*;
import com.crtvu.service.GMTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhao on 2017/3/31.
 */
@Service
public class GMTeacherServiceImpl implements GMTeacherService {

    @Autowired
    private ArrangeSubjectDAO arrangeSubjectDAO;

    @Autowired
    private ConfirmSubjectDAO confirmSubjectDAO;

    @Autowired
    private SelectSubjectDAO selectSubjectDAO;

    @Autowired
    private GMStudentDAO GMStudentDAO;

    @Autowired
    private TeachCourseDAO teachCourseDAO;

    @Autowired
    private GMTeacherDAO GMTeacherDAO;

    /**
     * 根据教师id查询该教师发布的课题id、名称、状态（确认/未确认）
     * @param teacherId
     * @return List<ReleasedSubjectListItem>
     */
    public List<ReleasedSubjectListItem> getAllReleasedSubject(int teacherId) {
        List<ReleasedSubjectListItem> releasedSubjectItemList = new ArrayList<ReleasedSubjectListItem>();
        List<ArrangeSubject> arrangeSubjectList = arrangeSubjectDAO.queryArrangeSubjectByTeacherId(teacherId);
        for (ArrangeSubject arrangeSubjectItem : arrangeSubjectList) {
            int subjectId = arrangeSubjectItem.getSubject_id();
            String subjectName = arrangeSubjectItem.getName();
            ConfirmSubject confirmSubject = confirmSubjectDAO.querySubjectBySubjectId(subjectId);
            int subjectStatus = 0;
            if( confirmSubject == null) {
                subjectStatus = 0; //未确认状态
            }else {
                subjectStatus = 2; //已确认状态
            }
            ReleasedSubjectListItem releasedSubjectItem = new ReleasedSubjectListItem(subjectId,subjectName,subjectStatus);
            releasedSubjectItemList.add(releasedSubjectItem);
        }
        return releasedSubjectItemList;
    }

    /**
     * 教师发布课题
     * @param teacherId
     * @param subjectName
     * @param subjectRequirement
     * @return int
     */
    public int insertSubject(int teacherId, String subjectName, String subjectRequirement) {
        ArrangeSubject arrangeSubject = new ArrangeSubject();
        int openId = teachCourseDAO.queryOpenIdByTeacherId(teacherId);
        arrangeSubject.setOpen_id(openId);
        arrangeSubject.setName(subjectName);
        arrangeSubject.setTeacher_id(teacherId);
        arrangeSubject.setRequirement(subjectRequirement);
        int result = arrangeSubjectDAO.insertSubject(arrangeSubject);
        if(result == 1) {
            return 1;
        }
        return 0;
    }

    /**
     * 教师根据课题id查看某课题的id、名称、要求和学生选择表（班级、学号、姓名）
     * @param subjectId
     * @return teacherCheckSubjectItem
     */
    public TeacherCheckSubjectItem getSubjectInfo(int subjectId) {
        TeacherCheckSubjectItem teacherCheckSubjectItem = new TeacherCheckSubjectItem();
        ArrangeSubject subject = arrangeSubjectDAO.queryArrangeSubjectBySubjectId(subjectId);
        teacherCheckSubjectItem.setSubjectId(subject.getSubject_id());
        teacherCheckSubjectItem.setSubjectName(subject.getName());
        teacherCheckSubjectItem.setSubjectRequirement(subject.getRequirement());
        List<Student> studentList = new ArrayList<Student>();
        List<SelectSubject> selectSubjectList = selectSubjectDAO.querySelectSubjectBySubjectIdAndStatus(subjectId,0);
        for(SelectSubject selectSubjectItem: selectSubjectList){
            int stuId = selectSubjectItem.getStudentId();
            Student student = GMStudentDAO.queryStudentInfo(stuId);
            student.setClassName(GMStudentDAO.queryStudentClass(stuId));
            studentList.add(student);
        }
        teacherCheckSubjectItem.setSelectedStudentList(studentList);
        return teacherCheckSubjectItem;
    }

    /**
     * 教师查看题目信息及已通过的学生信息和提交的文档
     * @param subjectId
     * @return teacherCheckDocument
     */
    @Override
    public TeacherCheckDocumentItem getSubjectDocument(int subjectId) {
        TeacherCheckDocumentItem teacherCheckDocument = new TeacherCheckDocumentItem();
        teacherCheckDocument.setSubjectId(subjectId);
        ArrangeSubject subject = arrangeSubjectDAO.queryArrangeSubjectBySubjectId(subjectId);
        teacherCheckDocument.setSubjectName(subject.getName());
        teacherCheckDocument.setRequirement(subject.getRequirement());
        ConfirmSubject confirmSubject = confirmSubjectDAO.querySubjectBySubjectId(subjectId);
        int studentId = confirmSubject.getStudentId();
        teacherCheckDocument.setStudentId(studentId);
        teacherCheckDocument.setDocument(confirmSubject.getDocument());
        Student student = GMStudentDAO.queryStudentInfo(studentId);
        teacherCheckDocument.setStudentName(student.getStudentName());
        teacherCheckDocument.setStatus(2);
        return teacherCheckDocument;
    }

    /**
     * 教师选定某个学生后，将此学生加入选定表，从预选表中删除
     * 将预选表中其他选择此课题的同学的项目改为已申请未通过状态1
     * @param subjectId
     * @param studentId
     * @return
     */
    @Override
    public int confirmStudent(int subjectId, int studentId) {
        SelectSubject selectSubject = selectSubjectDAO.queryStudentSubjectBySubjectIdAndStudentId(subjectId,studentId);

        ConfirmSubject confirmSubject = new ConfirmSubject();
        confirmSubject.setSubjectId(subjectId);
        confirmSubject.setStudentId(studentId);
        int insertResult = confirmSubjectDAO.insertConfirmSubject(confirmSubject);
        if(insertResult == 1){
            int deleteResult = selectSubjectDAO.deleteStudentBySubjectIdAndStudentId(subjectId,studentId);
            if(deleteResult == 1){
                int updateResult = selectSubjectDAO.updateStatusBySubjectId(subjectId, 1);
                if(updateResult != 0){
                    return  1;
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    /**
     * 根据教师id查询教师信息
     * @param teacherId
     * @return
     */
    @Override
    public Teacher queryTeacher(int teacherId) {
        Teacher teacher = GMTeacherDAO.queryTeacherById(teacherId);
        return teacher;
    }

    /**
     * 根据课题id删除课题
     * @param subjectId
     * @return
     */
    @Override
    public int deleteSubject(int subjectId) {

        int result1 = selectSubjectDAO.deleteSubjectBySubjectId(subjectId);

        int result = arrangeSubjectDAO.deleteSubject(subjectId);
        if (result != 0){
            return 1;
        }
        return 0;

    }

    @Override
    public int updateSubject(int subjectId, String subjectName, String requirement) {
        int result = arrangeSubjectDAO.updateSubject(subjectId,subjectName,requirement);
        if (result == 1){
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteConfirmSubject(int subjectId) {
        int result = confirmSubjectDAO.cancelSubject(subjectId);
        if (result == 1){
            return 1;
        }
        return 0;
    }

}
