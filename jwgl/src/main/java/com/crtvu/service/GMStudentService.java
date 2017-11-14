package com.crtvu.service;

import com.crtvu.dto.StudentCheckDocument;
import com.crtvu.dto.StudentCheckSubjectInfo;
import com.crtvu.dto.StudentCheckSubjectItem;
import com.crtvu.entity.Student;

import java.util.List;

/**
 * Created by Phoenix on 2017/5/7.
 */
public interface GMStudentService {

    public List<StudentCheckSubjectItem> checkConfirmedSubject(int studentId);

    public List<StudentCheckSubjectItem> checkNotConfirmedSubject(int studentId);

    public List<StudentCheckSubjectItem> checkNotAppliedSubject(int studentId);

    public StudentCheckSubjectInfo checkSubjectInfo(int subjectId);

    public int updateSubjectDocument(int subjectId, String document);

    public int insertSelectSubject(int subjectId, int studentId);

    public StudentCheckDocument checkSubjectDocu(int subjectId);

    public Student queryStudent(int studentId);

    public int deleteApplication(int studentId, int subjectId);

    public List<StudentCheckSubjectItem> queryAllSubject();

    public List<StudentCheckSubjectItem> keywordSubject(String keyword);

    public List<StudentCheckSubjectItem> keyNotAppliedSubject(int studentId, String keyword);
}
