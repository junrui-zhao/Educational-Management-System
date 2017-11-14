package com.crtvu.service;

import com.crtvu.dto.ReleasedSubjectListItem;
import com.crtvu.dto.TeacherCheckDocumentItem;
import com.crtvu.dto.TeacherCheckSubjectItem;
import com.crtvu.entity.Teacher;

import java.util.List;

/**
 * Created by zhao on 2017/3/31.
 */

public interface GMTeacherService {

    public List<ReleasedSubjectListItem> getAllReleasedSubject(int teacherId);

    public int insertSubject(int teacherId, String subjectName, String subjectRequirement);

    public TeacherCheckSubjectItem getSubjectInfo(int subjectId);

    public TeacherCheckDocumentItem getSubjectDocument(int subjectId);

    public int confirmStudent(int subjectId, int studentId);

    public Teacher queryTeacher(int teacherId);

    public int deleteSubject(int subjectId);

    public int updateSubject(int subjectId, String subjectName, String requirement);

    public int deleteConfirmSubject(int subjectId);
}
