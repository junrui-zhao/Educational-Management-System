package com.crtvu.service.implementation;

import com.crtvu.dao.*;
import com.crtvu.dto.CourseOpenInfo;
import com.crtvu.dto.admin.ClassStatistics;
import com.crtvu.dto.admin.MajorClassStatisticsList;
import com.crtvu.dto.admin.StudentGradeForManager;
import com.crtvu.entity.CourseEntity;
import com.crtvu.entity.OpenEntity;
import com.crtvu.entity.SelectCourseEntity;
import com.crtvu.entity.StudentEntity;
import com.crtvu.service.AdminGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Jixw on 2017/4/6.
 */
@Service
public class AdminGradeServiceImpl implements AdminGradeService {

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private SelectCourseDAO selectCourseDAO;

    @Autowired
    private CourseDAO courseDAO;

    @Autowired
    private OpenDAO openDAO;

    @Autowired
    private TeacherDAO teacherDAO;

    @Autowired
    private TeachCourseDAO teachCourseDAO;

    public List<String> getMajorList(String year) {

        List<String> list = new ArrayList<String>();
        List<StudentEntity> studentEntityList = studentDAO.selectAllStudent();
        for (StudentEntity temp : studentEntityList) {
            if (temp.getClassName().substring(0, 2).equals(year.substring(2))) {
                if (!list.contains(temp.getMajor()))
                    list.add(temp.getMajor());
            }
        }
        return list;
    }

    public List<MajorClassStatisticsList> getClassStatisticsList(List<String> majorList, String year) {
        List<MajorClassStatisticsList> list = new ArrayList<MajorClassStatisticsList>();
        for (String temp : majorList) {
            MajorClassStatisticsList majorClassStatisticsList = new MajorClassStatisticsList(temp, year, getClassStatistics(temp, year));
            list.add(majorClassStatisticsList);
        }
        return list;
    }


    //List<>{'classId' ,average, excellentRate   }
    public List<ClassStatistics> getClassStatistics(String major, String year) {
        //通过classname来判断年级
        //2014ji,2013ji
        //更改year格式 '2014'->'14__'

        String year2 = year.substring(2) + "__";

        List<ClassStatistics> stats = new ArrayList<ClassStatistics>();
        //按专业来统计
        List<StudentEntity> studentEntityList = studentDAO.selectStudentByMajorYear(major, year2);


        //遍历找到所有的班级id
        List<String> stuClass = new ArrayList<String>();
        for (StudentEntity temp : studentEntityList) {
            if (!stuClass.contains(temp.getClassName()))
                stuClass.add(temp.getClassName());
        }

        //float[] a = new float[stuClass.size()];
        //学生统计完一次就删除 减少查询次数
        for (String temp : stuClass) {
            Iterator<StudentEntity> it = studentEntityList.iterator();
            float sum = 0;
            int i = 0; // 统计班级人数
            int j = 0; //统计优秀人数,gpa>=3.5
            while (it.hasNext()) {
                StudentEntity studentEntity = it.next();
                if (studentEntity.getClassName().equals(temp)) {
                    i++;
                    if (getGPA(studentEntity.getStudentId()) >= 3.5) j++;
                    sum += getGPA(studentEntity.getStudentId());
                    it.remove();
                }
            }

            float avg = (float) (Math.round(sum / i * 100)) / 100;
            float excellentRate;
            if (j == 0)
                excellentRate = 0;
            else
                excellentRate = (float) (Math.round(j / i * 100)) / 100;
            ClassStatistics stat = new ClassStatistics(temp, avg, excellentRate);
            stats.add(stat);
        }
        return stats;
    }

    private float getGPA(int studentId) {
        //gpa公式为: 类似 常见算法GPA＝（4*4＋3*3＋2*4＋6*2＋3*3）／（4＋3＋2＋6＋3）＝3.00
        //并且设满绩点为4.33,0分不获得学分
        //考虑到grade=null

        float top = 0;  //分子
        float down = 0;  //分母

        List<SelectCourseEntity> selectCourseEntityList = selectCourseDAO.selectGradeByStudentId(studentId);
        for (SelectCourseEntity temp : selectCourseEntityList) {
            if (temp.getGrade() != null) {
                float credit = courseDAO.selectCourse(openDAO.selectOpenByOpenId(temp.getOpenid()).getCourseId()).getCredit();
                top += temp.getPoint() * credit;
                down += credit;
            }
        }
        if (down == 0) return 0;
        return (float) (Math.round(top / down * 100)) / 100;
    }

    public List<CourseOpenInfo> getCourseOpenInfoByYearTerm(String schoolYear, int term) {
        List<CourseOpenInfo> courseOpenInfoList = new ArrayList<CourseOpenInfo>();
        List<OpenEntity> openEntityList = openDAO.selectAllOpen();

        if (!schoolYear.equals("NO")) {
            if (term == 0) {
                Iterator<OpenEntity> it = openEntityList.iterator();
                while (it.hasNext()) {
                    OpenEntity openEntity = it.next();
                    if (!(openEntity.getSchoolYear().equals(schoolYear))) {
                        it.remove();
                    }
                }
            } else {
                Iterator<OpenEntity> it = openEntityList.iterator();
                while (it.hasNext()) {
                    OpenEntity openEntity = it.next();
                    if (!(openEntity.getSchoolYear().equals(schoolYear) && openEntity.getTerm() == term)) {
                        it.remove();
                    }
                }
            }
        }

        for (OpenEntity temp : openEntityList) {
            String courseId = temp.getCourseId();
            CourseEntity courseEntity = courseDAO.selectCourse(courseId);
            CourseOpenInfo courseOpenInfo = new CourseOpenInfo(courseId, temp.getOpenId(), courseEntity.getCourseName(), courseEntity.getCredit(),
                    courseEntity.getNature(), courseEntity.getDepartment(), temp.getSchoolYear(), temp.getTerm(), temp.getPeopleNum());
            courseOpenInfoList.add(courseOpenInfo);
        }
        return courseOpenInfoList;
    }

    public List<StudentGradeForManager> getGradeByOpenid(int openId) {
        List<StudentGradeForManager> list = new ArrayList<StudentGradeForManager>();
        List<SelectCourseEntity> selectCourseEntities = selectCourseDAO.selectGradeByOpenId(openId);
        for (SelectCourseEntity temp : selectCourseEntities) {
            int studentId = temp.getStudentId();
            StudentEntity studentEntity = studentDAO.selectStudent(studentId);
            Float grade = temp.getGrade();
            StudentGradeForManager stuGrade = new StudentGradeForManager(studentId, studentEntity.getStudentName(), studentEntity.getClassName(), studentEntity.getMajor(), grade);
            list.add(stuGrade);
        }
        return list;
    }
}
