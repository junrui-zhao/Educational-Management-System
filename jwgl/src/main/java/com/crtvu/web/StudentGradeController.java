package com.crtvu.web;


import com.crtvu.dto.student.StudentCourseGrade;
import com.crtvu.dto.student.StudentEvaluate;
import com.crtvu.dto.student.StudentStatistics;
import com.crtvu.dto.student.UpdateEvaluate;
import com.crtvu.service.StudentGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentGradeController {

    @Autowired
    private StudentGradeService studentGradeService;

    //查看学生所有成绩
    @RequestMapping(value = "/{studentId}/grade",method = RequestMethod.GET)
    public String allGradeDetail (@PathVariable("studentId") int studentId, Model model) {
        List<StudentCourseGrade> studentCourseGrades = studentGradeService.getByIdYearTerm(studentId,"NO",0);
        model.addAttribute("grade", studentCourseGrades);
        model.addAttribute("studentId",studentId);
        return "/student/student_grade";
    }

    //查看学生部分成绩
    @RequestMapping(value = "/{studentId}/grade/choose",method = RequestMethod.POST)
    public String gradeDetail (@PathVariable("studentId") int studentId, Model model,String schoolYear,int term) {
        List<StudentCourseGrade> studentCourseGrades = studentGradeService.getByIdYearTerm(studentId,schoolYear,term);
        model.addAttribute("grade", studentCourseGrades);
        model.addAttribute("studentId",studentId);
        return "/student/student_grade";
    }

    //查看学生成绩汇总
    @RequestMapping(value = "/{studentId}/gradeSTAT",method = RequestMethod.GET)
    public String gradeSTAT(@PathVariable("studentId") int studentId,Model model) {
        if(studentId == 0){
            return "redirect:index";
        }
        StudentStatistics stat = studentGradeService.getStudentStat(studentId);
        model.addAttribute("stat",stat);
        return "/student/student_gradeStat";
    }

    @RequestMapping(value = "/{studentId}/grade/download",method = RequestMethod.GET)
    public String download(@PathVariable("studentId")int studentId,Model model){
        String filename = studentGradeService.download(studentId);
        System.out.println(filename);
        String path = "/download/student/"+filename;
        model.addAttribute("jump",path);
        return  "/student/jump";
    }

    @RequestMapping(value = "/{studentId}/grade/{openId}/evaluate",method = RequestMethod.GET)
    public String evaluate(@PathVariable("studentId")int studentId, @PathVariable("openId") int openId, Model model){

        StudentEvaluate evaluate = studentGradeService.getEvaluate(studentId,openId);
        //TS2303Q:StudentEvaluate{studentId=2014014002, openId=2, course=CourseEntity{id='TS2303Q', name='英语', credit=8.0, nature='必修', department='文法'}, teacher=TeacherNoPwd{id=201, name='杨老师', title='教授'}, evaluate='null'}
        model.addAttribute("evaluate",evaluate);
        return "/student/student_grade_evaluate";
    }

    @RequestMapping(value = "/{studentId}/grade/{openId}/updateEvaluate",method = RequestMethod.GET)
    public String updateEvaluate(@PathVariable("studentId")int studentId, @PathVariable("openId") int openId, UpdateEvaluate evaluate, Model model){
        studentGradeService.addEvaluate(studentId,openId,evaluate.toStringT());

        return "/student/student_grade";
    }

}
