package com.crtvu.web;

import com.crtvu.dto.downloadFile;
import com.crtvu.dto.teacher.StuGrade;
import com.crtvu.dto.teacher.TeacherAllCourse;
import com.crtvu.dto.teacher.UpdateGradeModel;
import com.crtvu.service.TeacherGradeService;
import jxl.read.biff.BiffException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/teacher")
public class TeacherGradeController {

    @Autowired
    private TeacherGradeService teacherGradeService;

    @RequestMapping(value = "/{teacherId}/grade/list", method = RequestMethod.GET)
    public String listCourse(@PathVariable("teacherId") int teacherId, Model model) {
        TeacherAllCourse teacherAllCourse = teacherGradeService.showTeacherAllCourseInfo(teacherId);
        model.addAttribute("courseList", teacherAllCourse);
        return "/teacher/teacher_grade";
    }

    @RequestMapping(value = "/{teacherId}/grade/{openId}/insert", method = RequestMethod.GET)
    public String insertGrade(@PathVariable("teacherId") int teacherId, @PathVariable("openId") int openId, Model model) {
        List<StuGrade> stuGrades = teacherGradeService.getStudentGrade(openId);
        model.addAttribute("teacherId", teacherId);
        model.addAttribute("openId", openId);
        model.addAttribute("gradeInsert", stuGrades);
        return "/teacher/teacher_grade_insert";
    }

    @RequestMapping(value = "/{teacherId}/grade/{openId}/insert/update", method = RequestMethod.GET)
    public String updateGrade(@PathVariable("teacherId") int teacherId, @PathVariable("openId") int openId,
                              UpdateGradeModel gradeInsert, Model model) {
        System.out.print(gradeInsert);
        teacherGradeService.updateGrade(gradeInsert.getStuGradeList());
        //UpdateGradeModel{stuGradeList=[UpdateGrade{studentId=2014014001, openId=2, grade=4.0},
        // UpdateGrade{studentId=2014014002, openId=2, grade=2.0}]}
        return "redirect:/teacher/{teacherId}/grade/list";
    }

    @RequestMapping(value = "/grade/downloadDemo")
    public String downloadDemo(HttpServletRequest request, HttpServletResponse response){
        downloadFile downloadFile = new downloadFile();
        downloadFile.downloadFile(request,response,"grade_demo.xls");
        return null;
    }

    @RequestMapping(value = "/{teacherId}/grade/{openId}/download", method = RequestMethod.GET)
    public String download(@PathVariable("teacherId") int teacherId, @PathVariable("openId") int openId, Model model) {
        String filename = teacherGradeService.download(teacherId, openId);
        System.out.println(filename);
        String path = "/download/teacher/" + filename;
        model.addAttribute("jump", path);
        return "/teacher/jump";
    }

    @RequestMapping(value = "/{teacherId}/grade/{openId}/upload", method = RequestMethod.GET)
    public String uploadGrade(@PathVariable("teacherId") int teacherId, @PathVariable("openId") int openId, Model model) {
        model.addAttribute("teacherId", teacherId);
        model.addAttribute("openId", openId);
        return "/teacher/teacher_grade_upload";
    }

    @RequestMapping(value = "/{teacherId}/grade/{openId}/uploadCheck",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public String uploadGradeCheck(@PathVariable("teacherId") int teacherId, @PathVariable("openId") int openId,
                                   @RequestParam("file") MultipartFile file,Model model) throws IOException, BiffException {
        if(file==null) return null;
        Map<String ,Object> map = teacherGradeService.readNetworkExcel(file,openId);
        List<String> errorList = (List<String>)map.get("errorList");
        System.out.println(errorList);
        if(errorList.get(0).equals("OK")){
            model.addAttribute("success","success");
        }else{
            model.addAttribute("error",errorList);
        }
        return "/teacher/teacher_grade_upload";
    }


}
