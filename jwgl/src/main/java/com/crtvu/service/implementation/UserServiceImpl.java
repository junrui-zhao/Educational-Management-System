package com.crtvu.service.implementation;

import com.crtvu.dao.AdminDAO;
import com.crtvu.dao.StudentDAO;
import com.crtvu.dao.TeacherDAO;
import com.crtvu.dao.TermYearDAO;
import com.crtvu.entity.AdminEntity;
import com.crtvu.entity.StudentEntity;
import com.crtvu.entity.TeacherEntity;
import com.crtvu.entity.TermYearEntity;
import com.crtvu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

/**
 * Created by Phoenix on 2017/4/11.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private TeacherDAO teacherDAO;

    @Autowired
    private AdminDAO adminDAO;

    @Autowired
    private TermYearDAO termYearDAO;

    public int login(String userName, String userPassword, String userType) {
        int userId = 0;
        try {
            userId = Integer.parseInt(userName);
        } catch (Exception e) {
            return 0;
        }
        if (userType.equals("1")) {
            StudentEntity student = studentDAO.selectStudent(userId);
            if (userPassword.equals(student.getPassword())) {
                return 1;
            }
        } else if (userType.equals("2")) {
            TeacherEntity teacher = teacherDAO.selectTeacher(userId);
            if (userPassword.equals(teacher.getPassword())) {
                return 2;
            }

        } else if (userType.equals("3")) {
            AdminEntity admin = adminDAO.selectAdmin(userId);
            if (userPassword.equals(admin.getPassword())) {
                return 3;
            }
        } else {
            return 0;
        }
        return 0;
    }

    @Override
    public TermYearEntity getCurrentTermAndYear() {
        return termYearDAO.queryCurrentTermAndSchoolYear();
    }

    @Override
    public TermYearEntity getRecentTermAndYear() {
        return termYearDAO.queryRecentTermAndSchooYear();
    }

    @Override
    public LinkedList<TermYearEntity> getAllTermAndYear() {
        return termYearDAO.queryAllTermAndSchoolYear();
    }
}
