package com.crtvu.web;

import com.crtvu.dto.ReleasedSubjectListItem;
import com.crtvu.dto.TeacherCheckDocumentItem;
import com.crtvu.dto.teacher.TeachCourseItem;
import com.crtvu.dto.teacher.TeachStudentItem;
import com.crtvu.entity.TeacherEntity;
import com.crtvu.entity.TeacherScheduleEntity;
import com.crtvu.entity.TermYearEntity;
import com.crtvu.service.GMTeacherService;
import com.crtvu.service.ScheduleService;
import com.crtvu.service.TeacherService;
import com.crtvu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Phoenix on 2017/4/11.
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private UserService userService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private GMTeacherService gmTeacherService;

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/{teacherId}/tchcourselist", method = RequestMethod.GET)
    public String getTeacherCourseList(@PathVariable("teacherId") String teacherId, Model model) {
        try {
            int id = Integer.parseInt(teacherId);
            LinkedList<TeachCourseItem> teacherCourseList = teacherService.getAllTeachCourseById(id);
            model.addAttribute("teacherCourseList", teacherCourseList);
            return "teacher/tacherCoursePage";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "teacher/teacherPage";
    }

    @RequestMapping(value = "/{teacherId}/coursestulist", method = RequestMethod.GET)
    public String getCourseStudentList(@PathVariable("teacherId") String teacherId, Model model) {
        try {
            int id = Integer.parseInt(teacherId);
            HashMap<Integer, LinkedList<TeachStudentItem>> courseStudentList= teacherService.getAllCourseStudentList(id);
            model.addAttribute("courseStudentList", courseStudentList);
            return "teacher/courseStudentPage";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "teacher/teacherPage";
    }

    @RequestMapping(value = "/{teacherId}", method = RequestMethod.GET)
    public String getTeacherMainPage(@PathVariable("teacherId") String teacherId) {
        System.out.println(teacherId);
        return "/teacher/teacher";
    }

    @RequestMapping(value = "/{teacherId}/teacherinfo", method = RequestMethod.GET)
    public String getTeacherInfoPage(@PathVariable("teacherId") String teacherId) {
        return "/teacher/teacher_courseInfo";
    }

    @RequestMapping(value = "/teachercourseinfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> queryTeacherCourseInfo(@RequestBody TeacherEntity teacherEntity) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        TermYearEntity termYearEntity = userService.getCurrentTermAndYear();
        if (termYearEntity.equals(null)) {
            termYearEntity = userService.getRecentTermAndYear();
        }

        List<TeacherScheduleEntity> teacherScheduleEntityList = scheduleService.queryTeacherSchedule(teacherEntity.getTeacherId(), termYearEntity.getSchoolYear(), termYearEntity.getTerm());
        modelMap.put("result",teacherScheduleEntityList);
        return modelMap;
    }

}
