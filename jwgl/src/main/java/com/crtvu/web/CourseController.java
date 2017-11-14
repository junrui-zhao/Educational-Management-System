package com.crtvu.web;

import com.crtvu.dto.CourseJson;
import com.crtvu.dto.DeleteCJson;
import com.crtvu.dto.ErrorCJson;
import com.crtvu.dto.downloadFile;
import com.crtvu.entity.CourseEntity;
import com.crtvu.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/course")
public class CourseController {

    @Autowired
    private CourseService CourseService;


    @RequestMapping(value = "/upload",method = RequestMethod.GET)
    public String upload(){
        return  "admin/course/upload";
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(){
        return "redirect:/admin/course/list/1";
    }

    @RequestMapping(value = "/list/{page}",method = RequestMethod.GET)
    public String list(@RequestParam(value = "searchKey",defaultValue = "") String courseProperty,
                       Model model,@PathVariable("page") int page){
        try{
            //courseProperty = new String(courseProperty.getBytes("ISO-8859-1"), "UTF-8");
            List<CourseEntity> list= CourseService.getCourseList(page,courseProperty);
            int pages = CourseService.getPageCount(courseProperty);
            model.addAttribute("pages",pages);
            model.addAttribute("list",list);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "admin/course/list";
    }


    @RequestMapping(value = "/edit/{courseId}",method = RequestMethod.GET)
    public String edit(@PathVariable("courseId")String courseId, Model model){
        if (courseId == null){
            return "redirect:/admin/course/list/1";
        }

        try{
            CourseEntity course = CourseService.getCourse(courseId);
            if (course == null){
                return "forward:/admin/course/list/1";
            }
            model.addAttribute("course",course);
            return "admin/course/edit";
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  "admin/index";
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public CourseService.Result delete(@RequestBody DeleteCJson DeleteCJson){
            return CourseService.deleteCourse(DeleteCJson.getCourseId());
    }


    @RequestMapping(value = "/insertInfo" ,method = RequestMethod.GET)
    public String insertInfo(){
        return "admin/course/insert";
    }

    @RequestMapping(value = "/insert" ,method = RequestMethod.POST ,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public CourseService.Result insert(@RequestBody CourseJson course ){
            if(course.getCredit() == -1){
                CourseEntity CourseEntity = new CourseEntity(course.getCourseId(),course.getCourseName(),course.getNature(),course.getDepartment());
                return CourseService.insertCourseNoCre(CourseEntity);
            }else{
               CourseEntity CourseEntity = new CourseEntity(course.getCourseId(),course.getCourseName(),course.getCredit(),course.getNature(),course.getDepartment());
               return CourseService.insertCourse(CourseEntity);}
    }

    @RequestMapping(value = "/updateCourse" ,method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public CourseService.Result updateCourse(@RequestBody CourseJson course){
            if(course.getCredit() == -1){
                return CourseService.updateCourseNoCre(course.getCourseName(),course.getNature(),course.getDepartment(),course.getCourseId());
            }
           return CourseService.updateCourse(course.getCourseName(),course.getCredit(),course.getNature(),course.getDepartment(),course.getCourseId());
        }


    @RequestMapping(value="/batchimport")
    public  String batchimport(@RequestParam("file") MultipartFile file,
                               HttpServletRequest request, HttpServletResponse response , Model model) throws IOException {
        //判断文件是否为空
        if(file==null) return null;
        //获取文件名
        String name=file.getOriginalFilename();
        //进一步判断文件是否为空（即判断其大小是否为0或其名称是否为null）
        long size=file.getSize();
        if(name==null || ("").equals(name) && size==0) return null;
        Map<String, Object> modelMap = new HashMap<>();
        //批量导入。参数：文件名，文件。
        modelMap = CourseService.batchImport(name,file);
        if(modelMap != null){
            String Msg ="批量导入EXCEL成功！";
            request.getSession().setAttribute("msg",Msg);
        }else{
            String Msg ="批量导入EXCEL失败！";
            request.getSession().setAttribute("msg",Msg);
        }

        List<CourseEntity> courseList = (List<CourseEntity>)modelMap.get("result");
        List<ErrorCJson> errorList = (List<ErrorCJson>)modelMap.get("error");
        System.out.println("1"+courseList);
        System.out.println("1"+errorList);
        model.addAttribute("success",courseList);
        model.addAttribute("error",errorList);
        return "/admin/course/upload";
    }


    @RequestMapping("/download")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "SampleCourseExcel.xls";

        downloadFile downloadFile = new downloadFile();
        downloadFile.downloadFile(request,response,fileName);
        return null;
    }

}

