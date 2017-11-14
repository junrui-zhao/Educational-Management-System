package com.crtvu.web;

import com.crtvu.dao.CourseDAO;
import com.crtvu.dto.OpenJson;
import com.crtvu.dto.admin.CourseSearch;
import com.crtvu.dto.admin.CourseStatiscisItem;
import com.crtvu.entity.*;
import com.crtvu.service.*;
import com.crtvu.util.JDateFormater;
//import jdk.nashorn.internal.scripts.JD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.*;

/**
 * Created by Phoenix on 2017/4/11.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private OpenService openService;

    @Autowired
    private UserService userService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseDAO courseDAO;

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/{adminId}", method = RequestMethod.GET)
    public String getAdminPage(@PathVariable("adminId") String adminId) {
        System.out.println(adminId);
        return "/admin/admin";
    }

    @RequestMapping(value = "/{adminId}/open", method = RequestMethod.GET)
    public String getAdminOpenPage(@PathVariable("adminId") String adminId, Model model) {
        TermYearEntity termYearEntity = userService.getCurrentTermAndYear();
        LinkedList<TermYearEntity> termYearEntityLinkedList = userService.getAllTermAndYear();
        if (termYearEntity.equals(null)) {
            termYearEntity = userService.getRecentTermAndYear();
        }
        List<OpenEntity> openEntityList = openService.queryOpen(termYearEntity.getSchoolYear(), termYearEntity.getTerm());
        for (OpenEntity openEntity : openEntityList) {
            openEntity.setStartSelectTimeString(JDateFormater.dateToString(openEntity.getStartSelectTime()));
            openEntity.setEndSelectTimeString(JDateFormater.dateToString(openEntity.getEndSelectTime()));
        }
        model.addAttribute("openlist", openEntityList);
        model.addAttribute("allterm", termYearEntityLinkedList);
        model.addAttribute("termyear", termYearEntity);
        return "/admin/admin_open";
    }

    @RequestMapping(value = "/openinfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getOpenInfo(@RequestBody TermYearEntity termYearEntity) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<OpenEntity> openEntityList = openService.queryOpen(termYearEntity.getSchoolYear(), termYearEntity.getTerm());
        modelMap.put("result", openEntityList);
        return modelMap;
    }

    @RequestMapping(value = "/{adminId}/open/edit/{openId}", method = RequestMethod.GET)
    public String editOpenView(@PathVariable int adminId, @PathVariable int openId, Model model) {
        TermYearEntity termYearEntity = userService.getCurrentTermAndYear();
        LinkedList<TermYearEntity> termYearEntityLinkedList = userService.getAllTermAndYear();
        if (termYearEntity.equals(null)) {
            termYearEntity = userService.getRecentTermAndYear();
        }
        List<OpenEntity> openEntityList = openService.queryOpen(termYearEntity.getSchoolYear(), termYearEntity.getTerm());
        OpenEntity targetEntity = new OpenEntity();
        for (OpenEntity open : openEntityList) {
            if (open.getOpenId() == openId) {
                targetEntity = open;
                break;
            }
        }
        model.addAttribute("openid",openId);
        model.addAttribute("result",targetEntity);
        return "/admin/admin_edit_open";
    }

    @RequestMapping(value = "/{adminId}/open/arrange/{openId}", method = RequestMethod.GET)
    public String arrangeOpen(@PathVariable int adminId, @PathVariable int openId, Model model) {
        TermYearEntity termYearEntity = userService.getCurrentTermAndYear();
        List<OpenEntity> openEntityList = openService.queryOpen(termYearEntity.getSchoolYear(), termYearEntity.getTerm());
        OpenEntity targetEntity = new OpenEntity();
        for (OpenEntity open : openEntityList) {
            if (open.getOpenId() == openId) {
                targetEntity = open;
                break;
            }
        }
        model.addAttribute("userid",adminId);
        model.addAttribute("curarrangement",scheduleService.queryCourseSchedule(openId));
        model.addAttribute("classroom",classroomService.getAllClassroom());
        model.addAttribute("teacher",teacherService.getTeacherList());
        model.addAttribute("classinfo",studentService.getAllStudentClass());
        model.addAttribute("result",targetEntity);
        return "/admin/admin_arrange_open";
    }

    @RequestMapping(value = "/{adminId}/room", method = RequestMethod.GET)
    public String getAdminFreeRoomPage(@PathVariable("adminId") String adminId) {
        System.out.println(adminId);
        return "/admin/admin_freeRoom";
    }

    @RequestMapping(value = "/{adminId}/teacherinfo", method = RequestMethod.GET)
    public String getAdminTeacherInfoPage(@PathVariable("adminId") String adminId) {
        System.out.println(adminId);
        return "/admin/admin_teacherInfo";
    }

    @RequestMapping(value = "/{adminId}/addopen", method = RequestMethod.GET)
    public String getAdminAddOpenPage(@PathVariable("adminId") String adminId, Model model) {
        List<CourseEntity> courseEntityList = courseService.getAllCourseList();
        LinkedList<TermYearEntity> termYearEntityLinkedList = userService.getAllTermAndYear();
        model.addAttribute("courelist",courseEntityList);
        model.addAttribute("allterm", termYearEntityLinkedList);
        return "admin/admin_add_open";
    }

    @RequestMapping(value = "/removeopen", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> removeOpen(@RequestBody OpenEntity open) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        OpenService.Result result = openService.removeOpen(open.getOpenId());
        System.out.println(result);
        modelMap.put("result", result);
        System.out.println(result);
        return modelMap;
    }

    @RequestMapping(value = "/freerooms", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> queryFreeRooms(@RequestBody ArrangementEntity arrangement) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        System.out.println("FUCK");
        TermYearEntity termYearEntity = userService.getCurrentTermAndYear();
        if (termYearEntity.equals(null)) {
            termYearEntity = userService.getRecentTermAndYear();
        }
        System.out.println(arrangement);
        ScheduleService.ClassroomResult classroomResult = scheduleService.queryFreeClassroom(termYearEntity.getSchoolYear(), termYearEntity.getTerm(), arrangement.getDay(), arrangement.getStartWeek(), arrangement.getEndWeek(), arrangement.getStartTime(), arrangement.getEndTime());
        System.out.println(classroomResult);
        modelMap.put("resulttype", classroomResult.resultType);
        modelMap.put("result",classroomResult.classroomEntities);
        return modelMap;
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

    @RequestMapping(value = "/editopen", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> editOpenInfo(@RequestBody OpenJson openJson) throws ParseException {
        Map<String, Object> modelMap = new HashMap<>();
        Date startTime = JDateFormater.stringToDate(openJson.getStartTime());
        Date endTime = JDateFormater.stringToDate(openJson.getEndTime());
        OpenEntity openEntity = new OpenEntity(openJson.getOpenId(),openJson.getSchoolYear(),openJson.getTerm(),openJson.getPeopleNum(),startTime, endTime);
        OpenEntity curEntity = openService.queryOpenByOpenId(openJson.getOpenId());
        openEntity.setCourseId(curEntity.getCourseId());
        openEntity.setSchoolYear(curEntity.getSchoolYear());
        openEntity.setTerm(curEntity.getTerm());
        openEntity.setNature(courseDAO.selectCourse(curEntity.getCourseId()).getNature());
        Set<String> majorSet = new HashSet<>();
        String[] majors = openJson.getMajor().split(",");
        for (String str : majors) {
            majorSet.add(str);
        }
        OpenService.Result resultType = openService.modifyOpen(openEntity, majorSet);
        System.out.println(resultType);
        modelMap.put("result", resultType);
        return modelMap;
    }

    @RequestMapping(value = "/addschedule", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addSchedule(@RequestBody ScheduleEntity scheduleEntity) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        OpenEntity openEntity = openService.queryOpenByOpenId(scheduleEntity.getOpenId());
        ScheduleService.ScheduleResult scheduleResult = scheduleService.addSchedule(openEntity, scheduleEntity.getTeacherId(), scheduleEntity.getClassroom(), scheduleEntity.getClassName(), scheduleEntity.getDay(), scheduleEntity.getStartWeek(), scheduleEntity.getEndWeek(), scheduleEntity.getStartTime(), scheduleEntity.getEndTime());
        modelMap.put("result", scheduleResult);
        return modelMap;
    }

    @RequestMapping(value = "/editschedule", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> editSchedule(@RequestBody ScheduleEntity scheduleEntity) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        ScheduleService.ScheduleResult scheduleResult = scheduleService.modifySchedule(scheduleEntity);
        modelMap.put("result", scheduleResult);
        return modelMap;
    }

    @RequestMapping(value = "/deleteschedule", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteSchedule(@RequestBody ScheduleEntity scheduleEntity) {
        Map<String, Object> modelMap = new HashMap<>();
        ScheduleService.ResultType resultType = scheduleService.removeSchedule(scheduleEntity.getId());
        modelMap.put("result", resultType);
        return modelMap;
    }

    @RequestMapping(value = "/addnewopen", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addNewOpen(@RequestBody OpenJson openJson) throws ParseException {
        Map<String, Object> modelMap = new HashMap<>();
        Date startTime = JDateFormater.stringToDate(openJson.getStartTime());
        Date endTime = JDateFormater.stringToDate(openJson.getEndTime());
        OpenEntity openEntity = new OpenEntity(0,openJson.getSchoolYear(),openJson.getTerm(),openJson.getPeopleNum(),startTime, endTime);
        openEntity.setCourseId(openJson.getCourseId());
        openEntity.setNature(courseDAO.selectCourse(openJson.getCourseId()).getNature());
        Set<String> majorSet = new HashSet<>();
        String[] majors = openJson.getMajor().split(",");
        for (String str : majors) {
            majorSet.add(str);
        }
        OpenService.Result resultType = openService.openCourse(openEntity, majorSet);
        System.out.println(resultType);
        modelMap.put("result", resultType);
        return modelMap;
    }

    @RequestMapping(value = "/{adminId}/schedule/{id}/edit", method = RequestMethod.GET)
    public String pushToEditPage(@PathVariable int adminId, @PathVariable int id, Model model) {
        ScheduleEntity scheduleEntity = scheduleService.queryScheduleById(id);
        model.addAttribute("arrid",id);
        model.addAttribute("result", scheduleEntity);
        model.addAttribute("classroom",classroomService.getAllClassroom());
        model.addAttribute("teacher",teacherService.getTeacherList());
        model.addAttribute("classinfo",studentService.getAllStudentClass());
        return "/admin/admin_edit_schedule";
    }

    @RequestMapping(value = "/{adminId}/detail", method = RequestMethod.GET)
    public String getDetailedInfo(@PathVariable("adminId")String adminId, Model model) {
        LinkedList<TermYearEntity> termYearLinkedList = adminService.getAllTermAndYear();
        model.addAttribute("termyear", termYearLinkedList);
        return "admin/detailPage";
    }

    @RequestMapping(value = "/{adminId}/detailinfo", method = RequestMethod.GET)
    public String getTargetTermCourseDetail(@PathVariable("adminId") String adminId, @RequestParam("year")String year, @RequestParam("term") String term, Model model) {
        try{
            int curTerm = Integer.parseInt(term);
            LinkedList<CourseStatiscisItem> courseStatiscisItemLinkedList = adminService.getSelectCourseInfoByTermAndYear(curTerm, year);
            model.addAttribute("statisticres", courseStatiscisItemLinkedList);
            return "admin/detailInfoPage";
        } catch(Exception e) {
            e.printStackTrace();
        }
        return "admin/detailPage";
    }
    @RequestMapping(value = "/{adminId}/search", method = RequestMethod.GET)
    public String getSearch(@PathVariable("adminId")String adminId,Model model) {
        return "admin/searchPage";
    }

    @RequestMapping(value = "/{adminId}/searchresult", method = RequestMethod.GET)
    public String getSearchResult(@PathVariable("adminId")String adminId, @RequestParam("courseId")String courseId,Model model) {
        try {
            LinkedList<CourseSearch> courseSearchLinkedList = adminService.getCourseSearchByCourseId(courseId);
            model.addAttribute("coursesearch", courseSearchLinkedList);
            return "admin/searchResultPage";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "warningPage";

    }
}
