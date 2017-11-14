package com.crtvu.web;

import com.crtvu.dto.CourseOpenInfo;
import com.crtvu.dto.admin.MajorClassStatisticsList;
import com.crtvu.dto.admin.StudentGradeForManager;
import com.crtvu.service.AdminGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jixw on 2017/4/6.
 */
@Controller
@RequestMapping("/admin")
public class AdminGradeController {

    @Autowired
    private AdminGradeService adminGradeService;

    @RequestMapping(value = "/{adminId}/grade", method = RequestMethod.GET)
    public String showStatistics(@PathVariable("adminId") int adminId, Model model) {
        model.addAttribute("adminId", adminId);
        return "admin/grade/admin_grade";
    }

    @ResponseBody
    @RequestMapping(value = "/{adminId}/showStats/{year}/gpa", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> showStats(@PathVariable("adminId") int adminId, @PathVariable("year") String year) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<String> majorList = adminGradeService.getMajorList(year);
        if (majorList.size() == 0) {
            modelMap.put("statsList", null);
        }
        List<MajorClassStatisticsList> majorClassStatisticsLists = adminGradeService.getClassStatisticsList(majorList, year);
        modelMap.put("statsList", majorClassStatisticsLists);
        return modelMap;
    }

    @ResponseBody
    @RequestMapping(value = "/{adminId}/showCourse/year={schoolYear}/term={term}", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> showCourse(@PathVariable("adminId") int adminId,@PathVariable("schoolYear") String schoolTear,@PathVariable("term")int term) {
        List<CourseOpenInfo> courseOpenInfoList = adminGradeService.getCourseOpenInfoByYearTerm(schoolTear,term);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("courseOpenList",courseOpenInfoList);
        System.out.print(courseOpenInfoList);
        //[CourseOpenInfo{courseId='E4DXF13', openId=1, courseName='英语', credit=6.0, nature='必修', department='文法学院', schoolYear='2014-2015', term=1, peopleNum=30},
        // CourseOpenInfo{courseId='E85ZVDW', openId=2, courseName='高数', credit=8.0, nature='必修', department='理学院', schoolYear='2013-2014', term=2, peopleNum=30}]
        return modelMap;
    }

    @ResponseBody
    @RequestMapping(value = "/{adminId}/showCourse/openId={openId}", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> showGrade(@PathVariable("adminId") int adminId,@PathVariable("openId") int openId) {
        List<StudentGradeForManager> gradeList = adminGradeService.getGradeByOpenid(openId);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("gradeList",gradeList);
        return modelMap;
    }

    /*
    @ResponseBody
    @RequestMapping(value = "/{adminId}/get",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> get(@PathVariable("adminId")int adminId){
        List<String> majorList = adminGradeService.getMajorList();
        //List<MajorClassStatisticsList> majorClassStatisticsLists = adminGradeService.getClassStatisticsList(majorList);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("majorList",majorList);
        return modelMap;
    }
    */
}
