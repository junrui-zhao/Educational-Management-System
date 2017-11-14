package com.crtvu.service.implementation;


import com.crtvu.dao.*;
import com.crtvu.dto.student.StudentCourseGrade;
import com.crtvu.dto.student.StudentEvaluate;
import com.crtvu.dto.student.StudentStatistics;
import com.crtvu.dto.student.TeacherNoPwd;
import com.crtvu.entity.*;
import com.crtvu.service.StudentGradeService;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class StudentGradeServiceImpl implements StudentGradeService {

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

    private List<StudentCourseGrade> createShowStudentCourseList(int studentId){
        List<SelectCourseEntity> selectCourseEntityList = selectCourseDAO.selectGradeByStudentId(studentId);
        List<StudentCourseGrade> list = new ArrayList<StudentCourseGrade>();
        for (SelectCourseEntity temp: selectCourseEntityList){
            int openId = temp.getOpenid();
            Float grade = temp.getGrade();

            OpenEntity openEntity = openDAO.selectOpenByOpenId(openId);
            String courseId = openEntity.getCourseId();
            String schoolYear1 = openEntity.getSchoolYear();
            int term1 = openEntity.getTerm();

            CourseEntity courseEntity = courseDAO.selectCourse(courseId);
            String courseName = courseEntity.getCourseName();
            float credit = courseEntity.getCredit();
            String nature = courseEntity.getNature();

            StudentCourseGrade studentCourseGrade = new StudentCourseGrade(studentId,courseId,openId,courseName,credit,nature,schoolYear1,term1,grade);
            list.add(studentCourseGrade);
        }
        return list;
    }

    public List<StudentCourseGrade> getByIdYearTerm (int studentId, String schoolYear, int term) {
        List<StudentCourseGrade> list = createShowStudentCourseList(studentId);
        if (schoolYear.equals("NO")) {
            return list;
        } else if (term == 0) {
            Iterator<StudentCourseGrade> it = list.iterator();
            while(it.hasNext()){
                StudentCourseGrade temp = it.next();
                if(!temp.getSchoolYear().equals(schoolYear)){
                    it.remove();
                }
            }
            return list;
        } else {
            Iterator<StudentCourseGrade> it = list.iterator();
            while (it.hasNext()){
                StudentCourseGrade temp = it.next();
                if(!(temp.getSchoolYear().equals(schoolYear)&&(temp.getTerm()==term)))
                {
                    it.remove();
                }
            }
            return list;
        }
    }

    public StudentEntity getStudentInfo (int studentId) {
        return studentDAO.selectStudent(studentId);
    }

    public StudentEvaluate getEvaluate(int studentId, int openId) {
        //courseId->openId
        CourseEntity courseEntity = courseDAO.selectCourse(openDAO.selectOpenByOpenId(openId).getCourseId());
        String evaluate = selectCourseDAO.selectGradeByStuIdOpenId(studentId,openId).getEvaluate();
        TeacherEntity t = teacherDAO.selectTeacher(teachCourseDAO.getTeacherId(openId));
        TeacherNoPwd teacher = new TeacherNoPwd(t.getTeacherId(),t.getTeacherName(),t.getTitle());
        StudentEvaluate studentEvaluate = new StudentEvaluate(studentId,openId, courseEntity,teacher,evaluate);
        return studentEvaluate;
    }

    public StudentStatistics getStudentStat (int studentId) {
        List<SelectCourseEntity> selectCourseEntityList = selectCourseDAO.selectGradeByStudentId(studentId);
        List<StudentCourseGrade> failList =new ArrayList<StudentCourseGrade>();//保存挂科的课程信息
        float top = 0;//分子
        float down = 0;//分母
        float gpa = 0;//gpa
        float creditGotCom = 0; //必修已获得学分
        float creditGotEle = 0; //选修已获得学分
        for(SelectCourseEntity temp: selectCourseEntityList){
            if(temp.getGrade()!=null){
                CourseEntity courseEntity = courseDAO.selectCourse(openDAO.selectOpenByOpenId(temp.getOpenid()).getCourseId());
                OpenEntity openEntity = openDAO.selectOpenByOpenId(temp.getOpenid());
                String nature = courseEntity.getNature();
                float credit = courseEntity.getCredit();
                if(temp.getGrade()>=60) {
                    if(nature.equals("必修")) creditGotCom += credit;
                    else if(nature.equals("选修")) creditGotEle += credit;
                }else {
                    StudentCourseGrade courseGrade = new StudentCourseGrade(studentId, courseEntity, openEntity,temp.getGrade());
                    failList.add(courseGrade);
                }
                top += temp.getPoint()*credit;
                down += credit;
            }
        }
        if(down == 0) gpa = 0;
        else gpa = (float) (Math.round(top / down * 100)) / 100;//保留两位小数
        return new StudentStatistics(studentId,gpa,creditGotCom,creditGotEle,failList);
    }

    private String buildGradeExcel(List<StudentCourseGrade> list, String fileName) {

        String path = this.getClass().getResource("/").getPath().replaceFirst("/", "").replaceAll("/WEB-INF/classes/", "");
        //path://C:~/jwgl/target/jwgl/
        //设置目录下的download目录
        String filePath = path + "/download/student/" ;
        File file = new File(filePath);
        if(!file.exists()){
            file.mkdirs();
        }
        //生成表格
        String columnName[] = {"课程ID", "课程名称", "学分", "性质", "学年", "学期", "成绩"};
        WritableWorkbook book = null;

        try {
            //生成excel文件!
            book = Workbook.createWorkbook(new File(filePath+fileName));
            // 生成名为“sheet1”的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("sheet1", 0);
            //表头导航
            for (int i = 0; i < 7; i++) {
                Label label = new Label(i, 0, columnName[i]);
                sheet.addCell(label);
            }
            for (int i = 0; i < list.size(); i++) {
                /**
                 sheet.addCell(new Label(0,i+1,String.valueOf(list.get(i).getStuId())));
                 sheet.addCell(new Label(1,i+1,list.get(i).getStuName()));
                 sheet.addCell(new Label(2,i+1,list.get(i).getStuClass()));
                 sheet.addCell(new Label(3,i+1,list.get(i).getStuMajor()));
                 sheet.addCell(new Label(4,i+1,String.valueOf(list.get(i).getGrade())));
                 */
                sheet.addCell(new Label(0, i + 1, list.get(i).getCourseId()));
                sheet.addCell(new Label(1, i + 1, list.get(i).getCourseName()));
                sheet.addCell(new Label(2, i + 1, String.valueOf(list.get(i).getCredit())));
                sheet.addCell(new Label(3, i + 1, list.get(i).getNature()));
                sheet.addCell(new Label(4, i + 1, list.get(i).getSchoolYear()));
                sheet.addCell(new Label(5, i + 1, String.valueOf(list.get(i).getTerm())));
                sheet.addCell(new Label(6, i + 1, String.valueOf(list.get(i).getGrade())));
            }
            book.write();
        } catch (IOException e) {
            e.printStackTrace();
            return "createFileERROR";
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
            return "WriteError";
        }finally {
            if(book!=null){
                try {
                    book.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (WriteException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileName;
    }

    public String download(int studentId) {
        List<StudentCourseGrade> list = createShowStudentCourseList(studentId);
        String name = String.valueOf(studentId)+".xls";
        String fileName = buildGradeExcel(list,name);
        return fileName;
    }

    public void addEvaluate(int studentId, int openId, String evaluate) {
        selectCourseDAO.updateEvaluate(studentId,openId, evaluate);
    }
}
