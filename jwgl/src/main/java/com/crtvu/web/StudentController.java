package com.crtvu.web;

import com.crtvu.dto.CourseJson;
import com.crtvu.dto.student.CourseItem;
import com.crtvu.entity.Course;
import com.crtvu.entity.Open;
import com.crtvu.entity.ScheduleEntity;
import com.crtvu.entity.TermYearEntity;
import com.crtvu.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Phoenix on 2017/4/11.
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private UserService userService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private InfoService infoService;

    @RequestMapping(value = "/{studentId}", method = RequestMethod.GET)
    public String getStudentMainPage(@PathVariable String studentId,Model model) {
        System.out.println(studentId);
        model.addAttribute("infos",infoService.getRecentInfo(Integer.parseInt(studentId)));
        return "/student/student";
    }

    @RequestMapping(value = "/{studentId}/course", method = RequestMethod.GET)
    public String getCoursePage(@PathVariable int studentId, Model model, HttpServletRequest request) {
        TermYearEntity termYearEntity = userService.getCurrentTermAndYear();
        if (termYearEntity.equals(null)) {
            termYearEntity = userService.getRecentTermAndYear();
        }
        List<ScheduleEntity> scheduleEntities = scheduleService.queryStudentSchedule(studentId, termYearEntity.getSchoolYear(), termYearEntity.getTerm());
        String[][] courseInfo = new String[15][15];
        for(int i=0;i<15;++i) {
            for(int j=0;j<15;++j) {
                courseInfo[i][j]="";
            }
        }
        for(ScheduleEntity scheduleEntity : scheduleEntities) {
            int from_row = scheduleEntity.getStartTime(), to_row = scheduleEntity.getEndTime();
            int col = scheduleEntity.getDay();
            String coureString = ""+scheduleEntity.getCourseName()+'\n'+"{第"+scheduleEntity.getStartWeek()+"-"+scheduleEntity.getEndWeek()+"周}"+'\n'+teacherService.getTeacher(scheduleEntity.getTeacherId()).getTeacherName()+'\n'+scheduleEntity.getClassroom();
            for (int i = from_row; i <= to_row; ++i) {
                courseInfo[i][col]=coureString;
                System.out.println(coureString);
            }
        }
        model.addAttribute("result", scheduleEntities);
        request.setAttribute("courseTable",courseInfo);
        return "/student/student_course";
    }

    @RequestMapping(value = "/{studentId}/recomcourse", method = RequestMethod.GET)
    public String getRecommendationCoursePage(@PathVariable int studentId, Model model, HttpServletRequest request) {
        TermYearEntity termYearEntity = userService.getCurrentTermAndYear();
        if (termYearEntity.equals(null)) {
            termYearEntity = userService.getRecentTermAndYear();
        }
        List<ScheduleEntity> scheduleEntities = scheduleService.queryMajorSchedule(studentService.getStudent(studentId).getMajor(), termYearEntity.getSchoolYear(), termYearEntity.getTerm());
        String[][] courseInfo = new String[15][15];
        for(int i=0;i<15;++i) {
            for(int j=0;j<15;++j) {
                courseInfo[i][j]="";
            }
        }
        for(ScheduleEntity scheduleEntity : scheduleEntities) {
            int from_row = scheduleEntity.getStartTime(), to_row = scheduleEntity.getEndTime();
            int col = scheduleEntity.getDay();
            String coureString = ""+scheduleEntity.getCourseName()+'\n'+"{第"+scheduleEntity.getStartWeek()+"-"+scheduleEntity.getEndWeek()+"周}"+'\n'+teacherService.getTeacher(scheduleEntity.getTeacherId()).getTeacherName()+'\n'+scheduleEntity.getClassroom();
            for (int i = from_row; i <= to_row; ++i) {
                courseInfo[i][col]=coureString;
            }
        }
        model.addAttribute("result", scheduleEntities);
        request.setAttribute("courseTable",courseInfo);
        return "/student/student_recomCourse";
    }

    @RequestMapping(value = "/quitcourse", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> quitCourse(@RequestBody CourseJson course, HttpSession session) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        try{
            int stuId = Integer.parseInt((String) session.getAttribute("userid"));
            int oId = Integer.parseInt(course.getCourseId());
            modelMap.put("result", studentService.quitCourse(stuId, oId));
            return modelMap;
        } catch (Exception e) {
            e.printStackTrace();
            return modelMap;
        }
    }

    @RequestMapping(value = "/select", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> quitCourse(@RequestBody Open open, HttpSession session) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        try{
            int stuId = Integer.parseInt((String) session.getAttribute("userid"));
            int oId = open.getOpenId();
            modelMap.put("result", studentService.selectCourse(stuId, oId));
            System.out.println(modelMap.get("result"));
            return modelMap;
        } catch (Exception e) {
            e.printStackTrace();
            return modelMap;
        }
    }

    @RequestMapping(value = "/{studentId}/courselist", method = RequestMethod.GET)
    public String getCoursesList(@PathVariable("studentId") String studentId, Model model) {
        try {
            int id = Integer.parseInt(studentId);
            HashMap<String, LinkedList<CourseItem>> courseMap = studentService.getAllCourse(id);
            model.addAttribute("selectedcompulsory", courseMap.get("selectedcompulsory"));
            model.addAttribute("selectedoptional", courseMap.get("selectedoptional"));
            model.addAttribute("unselected", courseMap.get("unselected"));
            return "student/studentCoursePage";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "student/studentPage";
    }

    @RequestMapping(value = "/{studentId}/selectlist", method = RequestMethod.GET)
    public String getSelectCourseList(@PathVariable("studentId") String studentId, Model model) {
        try {
            int id = Integer.parseInt(studentId);
            LinkedList<Course> courseLinkedList = studentService.getAllSelectCourse(id);
            model.addAttribute("courseLinkedList", courseLinkedList);
            return "student/studentSelectPage";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "student/studentPage";
    }

    @RequestMapping(value = "/{studentId}/{openId}/selectcourse", method = RequestMethod.POST)
    public String selectCourse(@PathVariable("studentId") String studentId, @PathVariable("openId") String openId, Model model) {
        try {
            int id = Integer.parseInt(studentId);
            int open = Integer.parseInt(openId);
            int result = studentService.selectCourse(id, open);
            model.addAttribute("result", result);
            return "student/studentCoursePage";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "student/studentCoursePage";
    }

    @RequestMapping(value = "/{studentId}/{openId}/getcourseleftnum",method = RequestMethod.POST)
    public String getCurrentCourseLeftNumber(@PathVariable("studentId") String studentId, @PathVariable("openId") String openId, Model model) {
        try {
            int open = Integer.parseInt(openId);
            int result = studentService.getCurrentCourseLeftNum(open);
            model.addAttribute("result", result);
            return "student/studentCoursePage";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "student/studentCoursePage";
    }

}
